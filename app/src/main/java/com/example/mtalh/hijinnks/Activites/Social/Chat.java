package com.example.mtalh.hijinnks.Activites.Social;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Models.Model_Chat;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapterChat;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    public static List<Model_Chat> model_chats = new ArrayList<>();
    RecyclerView recyclerViewChat;
    RecyclerAdapterChat recyclerAdapterChat;
    LinearLayoutManager linearLayoutManager;
    EditText message_text;
    ImageView chat_back_image;
    ImageView send_messgae_button;
    String[] sender_name = {"Abubakar sender"};
    String[] sender_date = {"2 minut ago"};
    String[] sender_message = {"THis is sender message 1"};
    int[] sender_profile_pic = {R.drawable.person5};
    String message;
    int counter = 0;
    String[] receiver_name = {"John receiver"};
    String[] receiver_date = {"6 minut ago"};
    String[] receiver_message = {"THis is receiver message1", "THis is receiver message2"};
    int[] receiver_profile_pic = {R.drawable.person4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recyclerViewChat = (RecyclerView) findViewById(R.id.chat_recyclerview);
        send_messgae_button = findViewById(R.id.message_button);
        message_text = findViewById(R.id.message_edittext);
        recyclerAdapterChat = new RecyclerAdapterChat(getApplicationContext(), model_chats);
        linearLayoutManager = new LinearLayoutManager(Chat.this);
        recyclerViewChat.setLayoutManager(linearLayoutManager);
        recyclerViewChat.stopNestedScroll();
        recyclerViewChat.setNestedScrollingEnabled(false);
        recyclerViewChat.setHasFixedSize(true);
        recyclerViewChat.setAdapter(recyclerAdapterChat);
        chat_back_image = (ImageView) findViewById(R.id.chat_back_image);
        chat_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


         /*        ChatList fragment = new ChatList();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.home_framelayout, fragment);
                transaction.commit();*/
            }
        });
        for (int i = 0; i < sender_name.length; i++) {
            model_chats.add(new Model_Chat(sender_name[i], sender_date[i], sender_message[i], sender_profile_pic[i], true));
        }
        for (int i = 0; i < receiver_name.length; i++) {
            model_chats.add(new Model_Chat(receiver_name[i], receiver_date[i], receiver_message[i], receiver_profile_pic[i]));
        }
        send_messgae_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!message_text.getText().toString().isEmpty()) {
                    message = message_text.getText().toString();


                    if (counter == 0) {
                        for (int i = 0; i < sender_name.length; i++) {
                            model_chats.add(new Model_Chat(sender_name[i], sender_date[i], message, sender_profile_pic[i], true));
                        }
                        counter = 1;
                    } else {
                        for (int i = 0; i < receiver_name.length; i++) {
                            model_chats.add(new Model_Chat(receiver_name[i], receiver_date[i], message, receiver_profile_pic[i]));
                        }
                        counter = 0;
                    }
                 /*   InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);*/
//                    recyclerAdapterChat.notifyDataSetChanged();
                    recyclerAdapterChat.notifyItemInserted(model_chats.size() - 1);

                } else if (message_text.getText().toString().isEmpty()) {
                    Toast.makeText(Chat.this, "Please Write Message", Toast.LENGTH_SHORT).show();
                }
                recyclerViewChat.smoothScrollToPosition(model_chats.size());
                message_text.setText("");
            }
        });
    }
}
