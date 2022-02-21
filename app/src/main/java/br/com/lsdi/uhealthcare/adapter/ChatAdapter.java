package br.com.lsdi.uhealthcare.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.lsdi.uhealthcare.R;
import br.com.lsdi.uhealthcare.model.Message;


@RequiresApi(api = Build.VERSION_CODES.O)
public class ChatAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final Long USER_ID = 123L;


    private Context mContext;
    private List<Message> mMessageList;

    public ChatAdapter(Context context, List<Message> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = (Message) mMessageList.get(position);

        if (message.getSender().getId() == USER_ID) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        Message messagePrev = null;
        if (position > 0) {
            messagePrev = mMessageList.get(position - 1);
        }
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message, messagePrev);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message, messagePrev);
        }
    }




    private static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText, dateText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.txt_message_other);
            timeText = itemView.findViewById(R.id.txt_timestamp_other);
            nameText = itemView.findViewById(R.id.txt_username);
            profileImage = itemView.findViewById(R.id.img_contact);
            dateText = itemView.findViewById(R.id.txt_date_other);
        }

        void bind(Message message, Message messagePrev) {
            messageText.setText(message.getMessage());

            // Format the stored timestamp into a readable String using method.
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            timeText.setText(message.getCreatedAt().toLocalTime().format(dtf));
            nameText.setText(message.getSender().getName());
            String date = message.getCreatedAt().toLocalDate().getMonth() + " " + message.getCreatedAt().toLocalDate().getDayOfMonth();;

            if ((messagePrev == null) || (!messagePrev.getCreatedAt().toLocalDate().equals(message.getCreatedAt().toLocalDate()))){
                dateText.setText(date);
            }

            // Insert the profile image from the URL into the ImageView.
            //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }

    private static class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, dateText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.txt_message_me);
            timeText = itemView.findViewById(R.id.txt_timestamp_me);
            dateText = itemView.findViewById(R.id.text_gchat_date_me);
        }


        void bind(Message message, Message messagePrev) {
            messageText.setText(message.getMessage());

            // Format the stored timestamp into a readable String using method.
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            timeText.setText(message.getCreatedAt().toLocalTime().format(dtf));
            String date = message.getCreatedAt().toLocalDate().getMonth() + " " + message.getCreatedAt().toLocalDate().getDayOfMonth();;

            if ((messagePrev == null) || (!messagePrev.getCreatedAt().toLocalDate().equals(message.getCreatedAt().toLocalDate()))){
                dateText.setText(date);
            }
        }
    }

}
