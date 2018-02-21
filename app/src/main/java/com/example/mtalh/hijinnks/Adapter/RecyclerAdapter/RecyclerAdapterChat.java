package com.example.mtalh.hijinnks.Adapter.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mtalh.hijinnks.Models.Model_Chat;
import com.example.mtalh.hijinnks.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.mtalh.hijinnks.Activites.Social.Chat.model_chats;

/**
 * Created by CP on 1/30/2018.
 */


public class RecyclerAdapterChat extends RecyclerView.Adapter<RecyclerAdapterChat.RecyclerviewHolder> {
Context context;
    public RecyclerAdapterChat(Context context,List<Model_Chat>model_chats) {
        this.context=context;

    }

    @Override
    public RecyclerAdapterChat.RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_chat, parent, false);
        RecyclerAdapterChat.RecyclerviewHolder recyclerviewHolder = new RecyclerAdapterChat.RecyclerviewHolder(view);
        return recyclerviewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterChat.RecyclerviewHolder holder, int position) {


        if (model_chats.get(position).isSender_checked()) {
            holder.sender_layout.setVisibility(View.VISIBLE);
            holder.receiver_layout.setVisibility(View.GONE);
            holder.sender_profile_image.setImageResource(model_chats.get(position).getSender_profile_image());
            holder.sender_name.setText(model_chats.get(position).getSender_name());
            holder.sender_message.setText(model_chats.get(position).getSender_message());
            holder.sender_time.setText(model_chats.get(position).getSender_message_time_sent());
        } else {
            holder.sender_layout.setVisibility(View.GONE);
            holder.receiver_layout.setVisibility(View.VISIBLE);
            holder.receiver_name.setText(model_chats.get(position).getReceiver_name());
            holder.receiver_message.setText(model_chats.get(position).getReceiver_message());
            holder.receiver_time.setText(model_chats.get(position).getReceiver_message_time_sent());
            holder.receiver_profile_image.setImageResource(model_chats.get(position).getReceiver_profile_image());
        }

    }

    @Override
    public int getItemCount() {
        return model_chats.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView sender_name, sender_message, sender_time;
        TextView receiver_name, receiver_message, receiver_time;
        CircleImageView sender_profile_image;
        CircleImageView receiver_profile_image;
        LinearLayout sender_layout, receiver_layout;

        public RecyclerviewHolder(View itemView) {
            super(itemView);
            sender_name = itemView.findViewById(R.id.sender_name);
            sender_time = itemView.findViewById(R.id.sender_message_time);
            sender_message = itemView.findViewById(R.id.sender_message);
            receiver_name = itemView.findViewById(R.id.receiver_name);
            receiver_time = itemView.findViewById(R.id.receiver_message_time);
            receiver_message = itemView.findViewById(R.id.receiver_message);
            sender_profile_image = itemView.findViewById(R.id.sender_profile);
            receiver_profile_image = itemView.findViewById(R.id.receiver_profilepic);
            sender_layout = itemView.findViewById(R.id.sender_message_layout);
            receiver_layout = itemView.findViewById(R.id.receiver_message_layout);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
