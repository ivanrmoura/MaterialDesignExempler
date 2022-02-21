package br.com.lsdi.uhealthcare.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.lsdi.uhealthcare.R;
import br.com.lsdi.uhealthcare.adapter.ChatAdapter;
import br.com.lsdi.uhealthcare.adapter.ConversationAdapter;
import br.com.lsdi.uhealthcare.model.Message;
import br.com.lsdi.uhealthcare.model.User;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChatActivity extends AppCompatActivity {

    private List<Message> messageList;
    private RecyclerView rcvChat;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcvChat = findViewById(R.id.rcv_chat);
        initListMessages();
        setupRecyclerView(rcvChat);
    }

    private void setupRecyclerView(RecyclerView recyclerView){

        chatAdapter = new ChatAdapter(this, messageList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chatAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id  = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initListMessages(){
        messageList = new ArrayList<>();

        User ivan = new User(123L,"Ivan Moura", "");
        User lucas = new User(321L,"Lucas Rodrigues", "");

        Message msg01 = new Message(1, "Olá lucas, tudo bem?", LocalDateTime.now().plusSeconds(45), ivan);
        messageList.add(msg01);
        Message msg02 = new Message(1, "Tudo tranquilo.", LocalDateTime.now().plusSeconds(180), lucas);
        messageList.add(msg02);
        Message msg03 = new Message(1, "E o vascão?.", LocalDateTime.now().plusSeconds(220), lucas);
        messageList.add(msg03);
        Message msg04 = new Message(1, "O vasco sobe!.", LocalDateTime.now().plusSeconds(290), ivan);
        messageList.add(msg04);
        Message msg05 = new Message(1, "Vai assistir o CR7 em campo hoje? " +
                "joga demais! assista vale a pena", LocalDateTime.now().plusDays(1), lucas);
        messageList.add(msg05);
        Message msg06 = new Message(1, "Pode deixar, irei assistir, mas eu prefiro o messi, " +
                "que é um gênio da bola. respeita o GOAT.", LocalDateTime.now().plusDays(1).plusSeconds(50), ivan);
        messageList.add(msg06);
    }
}