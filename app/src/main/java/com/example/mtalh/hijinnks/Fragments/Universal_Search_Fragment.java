package com.example.mtalh.hijinnks.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtalh.hijinnks.CustomeUI.EditTexts.Custome_EditText_Varela_Regular;
import com.example.mtalh.hijinnks.Interfaces.HomeFragmentInterface;
import com.example.mtalh.hijinnks.R;


public class Universal_Search_Fragment extends Fragment {

    LinearLayout global_user_layout, global_events_layout;
    Button global_users_textview, global_events_textview;
    View global_user_view, global_events_view;
    HomeFragmentInterface homeFragmentInterface;
    Custome_EditText_Varela_Regular edittext_searchbar;
    TextView clear_edittext;
    private OnFragmentInteractionListener mListener;
    private HomeFragment homeFragment = new HomeFragment(homeFragmentInterface);
    public Universal_Search_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_universal__search_, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#41539b"));
        }

        global_user_layout = (LinearLayout) view.findViewById(R.id.global_user_layout);
        global_events_layout = (LinearLayout) view.findViewById(R.id.global_events_layout);
        global_users_textview = (Button) view.findViewById(R.id.global_users_textview);
        global_events_textview = (Button) view.findViewById(R.id.global_events_textview);
        global_user_view = (View) view.findViewById(R.id.global_user_view);
        global_events_view = (View) view.findViewById(R.id.global_events_view);
        edittext_searchbar = (Custome_EditText_Varela_Regular) view.findViewById(R.id.search_bar_edittext);
        clear_edittext = (TextView) view.findViewById(R.id.clear_edittext);
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.global_search_framelayout, new Global_search_users_fragments());
        fragmentTransaction.commit();
        if (savedInstanceState==null){
            global_search_user_fragment();
        }
        clear_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext_searchbar.setText("");
                Toast.makeText(getContext(), "Universal Search Bar clear", Toast.LENGTH_SHORT).show();
            }
        });
        global_users_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global_users_textview.setTextColor(getResources().getColor(R.color.login_button_blue));
                global_events_textview.setTextColor(getResources().getColor(R.color.light_grey));
                global_events_view.setVisibility(View.GONE);
                global_user_view.setVisibility(View.VISIBLE);
                global_search_user_fragment();
            }
        });
        global_events_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                global_events_textview.setTextColor(getResources().getColor(R.color.login_button_blue));
                global_users_textview.setTextColor(getResources().getColor(R.color.light_grey));
                global_events_view.setVisibility(View.VISIBLE);
                global_user_view.setVisibility(View.GONE);

                global_search_event_fragment();
            }
        });
        return view;

    }

    private void global_search_user_fragment() {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.global_search_framelayout, new Global_search_users_fragments());
        fragmentTransaction.commit();
    }
    private void global_search_event_fragment() {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.global_search_framelayout, new Global_search_event_fragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
