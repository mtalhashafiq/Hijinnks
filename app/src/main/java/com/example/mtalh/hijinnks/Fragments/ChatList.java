package com.example.mtalh.hijinnks.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Models.ModelChatList;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecycleradapterChatList;

import java.util.ArrayList;


public class ChatList extends Fragment {

    RecyclerView recyclerView;
    RecycleradapterChatList recycleradapter_ChatList;
    String[] profileName = {"Hong", "Kong", "Shing pao", "Moto yamaha", "Goku Palwan", "Hafiz Sahab", "Sam", "ABubakar Butt", "Battery", "Bonut", "Dash Board", "Laptop", "Mobile", "MacBook"};
    String[] lastseenmessage = {"0 minut ago", "1 day ago", "2 day ago", "3 secong ago", "4 minut ago", "5 dsecong ago", "6 day ago", "7 secong ago", "8 minut ago", "9 day ago", "10 minut ago", "11 minut ago", "12day ago", "13 secong ago"};
    int[] profileimage = {R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4,
            R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4,
            R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4,
    };
    ArrayList<ModelChatList> arrayList_search_all = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        recyclerView = view.findViewById(R.id.chat_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleradapter_ChatList = new RecycleradapterChatList(arrayList_search_all, getContext());
        recyclerView.setAdapter(recycleradapter_ChatList);
        if (arrayList_search_all.size() == 0) {
            for (int i = 0; i < profileName.length; i++) {
                arrayList_search_all.add(new ModelChatList(profileName[i], lastseenmessage[i], profileimage[i], false));
            }
        }
        recycleradapter_ChatList.SsetClickListener(new RecycleradapterChatList.itemClick() {
            @Override
            public void OnitemClickmethod(int position) {
                Log.d("POSITIONPOSITION", "" + position);
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_LONG).show();
            }
        });
     /*   recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getContext(), "I am position" + position, Toast.LENGTH_SHORT).show();
                       *//* Intent open_chat_intent=new Intent(getContext(), Chat.class);
                        startActivity(open_chat_intent)*//*;
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(),"I am Long Pree at :- "+position,Toast.LENGTH_LONG).show();
                    }
                })
        );*/
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
