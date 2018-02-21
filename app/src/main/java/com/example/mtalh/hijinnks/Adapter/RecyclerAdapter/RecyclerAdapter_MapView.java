package com.example.mtalh.hijinnks.Adapter.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mtalh.hijinnks.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mtalh on 12-Feb-18.
 */

public class RecyclerAdapter_MapView  extends RecyclerView.Adapter<RecyclerAdapter_MapView.SimpleViewHolder> {
    private static final int DEFAULT_ITEM_COUNT = 10;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final List<Integer> mItems;
    int likes_event_counter = 0;
    int rsvpd_event_counter = 0;
    private int mCurrentItemId = 0;
    private boolean like_event = false;
    private boolean view_event = false;
    public RecyclerAdapter_MapView(Context context, RecyclerView recyclerView) {
        this(context, recyclerView, DEFAULT_ITEM_COUNT);

    }

    public RecyclerAdapter_MapView(Context context, RecyclerView recyclerView, int itemCount) {
        mContext = context;
        mItems = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            addItem(i);
        }

        mRecyclerView = recyclerView;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_map_infowindow, parent, false);
        RecyclerAdapter_MapView.SimpleViewHolder recyclerviewholder = new RecyclerAdapter_MapView.SimpleViewHolder(view);
        return recyclerviewholder;
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {

      /*  final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            }
        });

        final int itemId = mItems.get(position);*/

        holder.like_event_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!like_event) {


                    holder.like_event_image.setImageResource(R.drawable.red_circle);
                    holder.like_event_image_heart.setImageResource(R.drawable.heart_white);
                    likes_event_counter++;

                    holder.like_events.setText(String.valueOf(likes_event_counter));
                    like_event = true;
                } else {


                    holder.like_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                    holder.like_event_image_heart.setImageResource(R.drawable.heart_gray);
                    likes_event_counter--;
                    holder.like_events.setText(String.valueOf(likes_event_counter));
                    like_event = false;
                }
            }
        });
        holder.view_event_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!view_event) {
                    rsvpd_event_counter++;
                    holder.rsvpd_events.setText(String.valueOf(rsvpd_event_counter));
                    holder.view_event_image.setImageResource(R.drawable.blue_circle);
                    holder.view_event_image_eye.setImageResource(R.drawable.view_icon_white);

                    view_event = true;
                } else {
                    rsvpd_event_counter--;
                    holder.rsvpd_events.setText(String.valueOf(rsvpd_event_counter));
                    holder.view_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                    holder.view_event_image_eye.setImageResource(R.drawable.view_icon_gray);

                    view_event = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout like_event_layout, view_event_layout, share_event_layout;
        CircleImageView like_event_image, view_event_image;
        ImageView like_event_image_heart, view_event_image_eye;
        TextView like_events, rsvpd_events;

        public SimpleViewHolder(View view) {
            super(view);
            like_event_layout = (RelativeLayout) view.findViewById(R.id.like_event_layout);
            view_event_layout = (RelativeLayout) view.findViewById(R.id.view_event_layout);
            share_event_layout = (RelativeLayout) view.findViewById(R.id.share_event_layout);
            like_event_image = (CircleImageView) view.findViewById(R.id.like_event_image);
            view_event_image = (CircleImageView) view.findViewById(R.id.view_event_image);
            like_event_image_heart = (ImageView) view.findViewById(R.id.like_event_image_heart);
            view_event_image_eye = (ImageView) view.findViewById(R.id.view_event_image_eye);
            like_events = (TextView) view.findViewById(R.id.like_event);
            rsvpd_events = (TextView) view.findViewById(R.id.rsvpd_event);

        }
    }
}

