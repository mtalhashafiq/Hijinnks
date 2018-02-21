package com.example.mtalh.hijinnks.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtalh.hijinnks.CustomeUI.Layouts.MyLinearLayout;
import com.example.mtalh.hijinnks.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mtalh on 27-Jan-18.
 */

public class CarouselFragmentInfoWindow extends Fragment {
    public static Fragment newInstance(Map_Fragment context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return Fragment.instantiate(context.getContext(), CarouselFragmentInfoWindow.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.listrow_map_infowindow, container, false);
        RelativeLayout like_event_layout, view_event_layout, share_event_layout;
        final CircleImageView like_event_image, view_event_image;
        final ImageView like_event_image_heart, view_event_image_eye;
        final TextView like_events, rsvpd_events;
        final ImageView[] imageView = new ImageView[1];
        final boolean[] like_event = {false};
        final boolean[] view_event = {false};
        final int[] likes_event_counter = {0};
        final int[] rsvpd_event_counter = {0};
        like_event_layout = (RelativeLayout) view.findViewById(R.id.like_event_layout);
        view_event_layout = (RelativeLayout) view.findViewById(R.id.view_event_layout);
        share_event_layout = (RelativeLayout) view.findViewById(R.id.share_event_layout);
        like_event_image = (CircleImageView) view.findViewById(R.id.like_event_image);
        view_event_image = (CircleImageView) view.findViewById(R.id.view_event_image);
        like_event_image_heart = (ImageView) view.findViewById(R.id.like_event_image_heart);
        view_event_image_eye = (ImageView) view.findViewById(R.id.view_event_image_eye);
        like_events = (TextView) view.findViewById(R.id.like_event);
        rsvpd_events = (TextView) view.findViewById(R.id.rsvpd_event);
        int pos = this.getArguments().getInt("pos");
         imageView[0] = (ImageView) view.findViewById(R.id.mapcover);
        imageView[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "I am imageeeeeeeeeeeeee", Toast.LENGTH_SHORT).show();

            }
        });

        like_event_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!like_event[0]) {


                    like_event_image.setImageResource(R.drawable.red_circle);
                    like_event_image_heart.setImageResource(R.drawable.heart_white);
                    likes_event_counter[0]++;

                   like_events.setText(String.valueOf(likes_event_counter[0]));
                    like_event[0] = true;
                } else {


                   like_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                    like_event_image_heart.setImageResource(R.drawable.heart_gray);
                    likes_event_counter[0]--;
                   like_events.setText(String.valueOf(likes_event_counter[0]));
                    like_event[0] = false;
                }
            }
        });
        view_event_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!view_event[0]) {
                    rsvpd_event_counter[0]++;
                    rsvpd_events.setText(String.valueOf(rsvpd_event_counter[0]));
                    view_event_image.setImageResource(R.drawable.blue_circle);
                    view_event_image_eye.setImageResource(R.drawable.view_icon_white);

                    view_event[0] = true;
                } else {
                    rsvpd_event_counter[0]--;
                   rsvpd_events.setText(String.valueOf(rsvpd_event_counter[0]));
                    view_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                   view_event_image_eye.setImageResource(R.drawable.view_icon_gray);

                    view_event[0] = false;
                }
            }
        });
        MyLinearLayout root = (MyLinearLayout) view.findViewById(R.id.map_info_window_linearlayout);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return view;
    }
}

