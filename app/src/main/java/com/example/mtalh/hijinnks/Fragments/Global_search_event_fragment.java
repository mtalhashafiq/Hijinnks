package com.example.mtalh.hijinnks.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Models.Model_Home_Fragment;
import com.example.mtalh.hijinnks.Models.PostMedia;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_Home_HomeFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Global_search_event_fragment extends Fragment {


    ArrayList<Model_Home_Fragment> model_home_fragments = new ArrayList<>();
    String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    String videourl2 = "http:// www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String videourl3="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4";
    String videourl = "http://www.demonuts.com/Demonuts/smallvideo.mp4";
    String ImageUrl_1 = "https://fakeimg.pl/350x200/?text=World&font=lobster";
    String ImageUrl_2 = "https://fakeimg.pl/250x100/ff0000/";
    ArrayList<PostMedia> media1;
    ArrayList<PostMedia> media2;
    ArrayList<PostMedia> media3;
    ArrayList<PostMedia> media4;
    private RecyclerView recyclerView;
    private RecyclerAdapter_Home_HomeFragment adapter;
    private RecyclerView.LayoutManager layoutManager;
    public Global_search_event_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_global_search_event_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home_fragment);

        adapter = new RecyclerAdapter_Home_HomeFragment(model_home_fragments, getActivity());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        media1 = new ArrayList<>();
        media1.add(new PostMedia("Video", videourl, videourl));

        media2 = new ArrayList<>();
        media2.add(new PostMedia("Video", videourl2, videourl2));
        media2.add(new PostMedia("Image", ImageUrl_2, null));


        media3 = new ArrayList<>();
        media3.add(new PostMedia("Image", ImageUrl_1, null));
        media3.add(new PostMedia("Image", ImageUrl_2, null));
        media3.add(new PostMedia("Video", videourl2, videourl2));
        media3.add(new PostMedia("Video", videourl, videourl));



        media4 = new ArrayList<>();
        media4.add(new PostMedia("Image", ImageUrl_1, null));
        media4.add(new PostMedia("Video", videourl2, videourl2));
        media4.add(new PostMedia("Video", videourl, videourl));
        media4.add(new PostMedia("Image", ImageUrl_1, null));
        media4.add(new PostMedia("Image", ImageUrl_2, null));



        if (model_home_fragments.size() == 0) {
//            for (int i = 0; i < profile_name.length; i++) {
            model_home_fragments.add(new Model_Home_Fragment("Adam", R.drawable.person1, "Food", media1, "Welcome to Party"));
            model_home_fragments.add(new Model_Home_Fragment("John", R.drawable.person2, "COmputer", media2, "Welcome to Party"));
            model_home_fragments.add(new Model_Home_Fragment("Michael", R.drawable.person3, "Laptop", media3, "Welcome to Party"));
            model_home_fragments.add(new Model_Home_Fragment("Radika", R.drawable.person4, "BAtBall", media4, "Welcome to Party"));

//                model_home_fragments.add(new Model_Home_Fragment(profile_name[i], user_profile_image[i], intrests[i], media[i], title_of_events[i]));
//            }
        }
        adapter.SsetClickListener(new RecyclerAdapter_Home_HomeFragment.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "Global Event Number  " + Pos, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.notifyDataSetChanged();
        return view;
    }

}
