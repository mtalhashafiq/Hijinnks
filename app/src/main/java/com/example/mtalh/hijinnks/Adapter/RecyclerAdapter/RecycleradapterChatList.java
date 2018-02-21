package com.example.mtalh.hijinnks.Adapter.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mtalh.hijinnks.Activites.Social.Chat;
import com.example.mtalh.hijinnks.CustomeUI.ViewGroups.SwipeRevealLayout;
import com.example.mtalh.hijinnks.CustomeUI.ViewGroups.ViewBinderHelper;
import com.example.mtalh.hijinnks.Models.ModelChatList;
import com.example.mtalh.hijinnks.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CP on 1/26/2018.
 */

public class RecycleradapterChatList extends RecyclerView.Adapter<RecycleradapterChatList.RecyclerViewHolder> {
    static itemClick itemClick;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    public ArrayList<ModelChatList> arrayList_search = new ArrayList<>();
    Context context;
    int poss;

    public RecycleradapterChatList(ArrayList<ModelChatList> arrayList_search, Context applicationContext) {
        this.arrayList_search = arrayList_search;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatlist, parent, false);
        RecycleradapterChatList.RecyclerViewHolder recyclerViewHolder = new RecycleradapterChatList.RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.profileName.setText(arrayList_search.get(position).getProfileName());
        holder.profileImage.setImageResource(arrayList_search.get(position).getProfileImage());
        holder.lastSeen.setText(arrayList_search.get(position).getLastSeen());
        poss = holder.getAdapterPosition();
        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int newPosition = holder.getAdapterPosition();
                arrayList_search.remove(newPosition);
                notifyItemRemoved(newPosition);
                Log.d("DELETEITEMPOS", "" + poss);
            }
        });
        if (arrayList_search != null && 0 <= position && position < arrayList_search.size()) {
            final ModelChatList data = arrayList_search.get(position);
            binderHelper.bind(holder.swipeLayout, data);
        }
        holder.openChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent chat_activity = new Intent(activity, Chat.class);
                activity.startActivity(chat_activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList_search.size();
    }

    public void SsetClickListener(itemClick listener) {
        this.itemClick = listener;
    }

    public interface itemClick {
        public void OnitemClickmethod(int position);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView profileName, lastSeen;
        CircleImageView profileImage;
        FrameLayout openChat;
        private SwipeRevealLayout swipeLayout;
        private View deleteLayout;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            openChat = (FrameLayout) itemView.findViewById(R.id.open_chat);
            swipeLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipe_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profileImage);
            profileName = itemView.findViewById(R.id.profileName);
            lastSeen = itemView.findViewById(R.id.lastseen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            itemClick.OnitemClickmethod(getAdapterPosition());
        }
    }

}
