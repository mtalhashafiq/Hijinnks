package com.example.mtalh.hijinnks.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Models.Model_Global_Search_User;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.Recycleradapter_GlobalSearch_User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Global_search_users_fragments extends Fragment {

    String[] profile_name_followers = {"Feed_FOLLOWERS Start", "RSVP'd Events", "only", "Message", "Followers", "Talha", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers followers END"};
    int[] profile_image_followers = {R.drawable.person5, R.drawable.person4, R.drawable.person3, R.drawable.person2, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5};
    String[] number_invite_followers = {"38453", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232"};
    String[] text_follow_followers = {"Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers"};
    int[] follow_background_followers = {R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue,
            R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue,
            R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_blue};
    ArrayList<Model_Global_Search_User>model_global_search_users=new ArrayList<>();
    private RecyclerView recyclerView_navigation;
    private Recycleradapter_GlobalSearch_User adapter_navigation;
    private RecyclerView.LayoutManager layoutManager_navigation;
    public Global_search_users_fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_global_search_users, container, false);

        recyclerView_navigation=(RecyclerView)view.findViewById(R.id.recyclerview_global_user_search);
        users_list();
  return view;
    }
    public  void users_list(){
        adapter_navigation = new Recycleradapter_GlobalSearch_User(model_global_search_users, getActivity());
        layoutManager_navigation = new LinearLayoutManager(getContext());
        recyclerView_navigation.setLayoutManager(layoutManager_navigation);
        recyclerView_navigation.setHasFixedSize(true);
        recyclerView_navigation.setAdapter(adapter_navigation);
        if (model_global_search_users.size() == 0) {
            for (int i = 0; i < profile_name_followers.length; i++) {
                model_global_search_users.add(new Model_Global_Search_User(profile_name_followers[i], number_invite_followers[i], text_follow_followers[i], profile_image_followers[i], follow_background_followers[i]));
            }
        }
        adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_GlobalSearch_User.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), " ALL " + Pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
