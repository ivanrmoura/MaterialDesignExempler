package br.com.lsdi.uhealthcare.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.lsdi.uhealthcare.R;
import br.com.lsdi.uhealthcare.adapter.ConversationAdapter;
import br.com.lsdi.uhealthcare.model.Message;
import br.com.lsdi.uhealthcare.model.User;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ConversationActivity extends AppCompatActivity {

    private RecyclerView rcvConversation;
    private ConversationAdapter conversationAdapter;
    private List<Message> conversationList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Conversas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcvConversation = findViewById(R.id.rcv_conversation);
        initChatList();
        setupRecyclerView(rcvConversation);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id  = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(RecyclerView recyclerView){

        conversationAdapter = new ConversationAdapter(this, conversationList, item -> {
            Intent intent = new Intent(this, ChatActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(conversationAdapter);
    }

    private void initChatList(){
        conversationList = new ArrayList<>();

        User lucas= new User(123L,"Ivan Moura", "");
        conversationList.add(new Message(1L, "Oi mano, tudo em ordem?", LocalDateTime.now(),lucas));
        User marlene = new User(2L,"Marlene Vieira", "");
        conversationList.add(new Message(2L, "Vamos sair para festa às 18:00, esteja preparado?",LocalDateTime.now(),marlene));
        User larissa = new User(3L,"Larissa Ozório", "");
        conversationList.add(new Message(3L, "Manda as anotações quando puder",LocalDateTime.now(),larissa));
        User daniel = new User(4L,"Daniel Amorim", "");
        conversationList.add(new Message(4L, "Fut hoje às 18:00, leva teus 10 conto",LocalDateTime.now(),daniel));
        User julia = new User(5L,"Júlia Rodrigues", "");
        conversationList.add(new Message(5L, "Vai para a festa do barrão da pisadinha?",LocalDateTime.now(),julia));
        User denilson = new User(6L,"Denilson Ventura", "");
        conversationList.add(new Message(1L, "Trás uma caixinha de Skol parceiro",LocalDateTime.now(),denilson));
        User mariana = new User(6L,"Mariana Rios", "");
        conversationList.add(new Message(1L, "oi, como vai você",LocalDateTime.now(),mariana));
        User jose = new User(6L,"José Sousa Lima", "");
        conversationList.add(new Message(1L, "como vai, vai jogar a Champions?",LocalDateTime.now(),jose));
    }

}