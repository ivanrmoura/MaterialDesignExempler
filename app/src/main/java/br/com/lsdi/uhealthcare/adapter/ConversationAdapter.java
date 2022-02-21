package br.com.lsdi.uhealthcare.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import br.com.lsdi.uhealthcare.R;
import br.com.lsdi.uhealthcare.model.Message;
import lombok.Getter;
import ru.nikartm.support.ImageBadgeView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Message item);
    }

    private Context mContext;
    private List<Message> conversations;

    private final OnItemClickListener listener;

    public ConversationAdapter(Context context, List<Message> conversations, OnItemClickListener listener) {
        this.mContext = context;
        this.conversations = conversations;
        this.listener = listener;
        }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_conversation, viewGroup, false);

        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                Toast.makeText(mContext, String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Message message = conversations.get(position);
        Random rand = new Random();
        viewHolder.getContactName().setText(message.getSender().getName());
        viewHolder.getLastMessage().setText(message.getMessage());
        viewHolder.getBadgeView().setBadgeValue(rand.nextInt(100));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        viewHolder.getTimestamp().setText(message.getCreatedAt().format(dtf));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(message);
            }
        });
    }


    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactName, lastMessage, timestamp;
        ImageView contactImage;
        ImageBadgeView badgeView;

        ViewHolder(View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.txt_username);
            lastMessage = itemView.findViewById(R.id.txt_last_message);
            contactImage = itemView.findViewById(R.id.img_contact);
            badgeView =  itemView.findViewById(R.id.badge_view);
            timestamp = itemView.findViewById(R.id.txt_timestamp_other);
        }
    }

}
