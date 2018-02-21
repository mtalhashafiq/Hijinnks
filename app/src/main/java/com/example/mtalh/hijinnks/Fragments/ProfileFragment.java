package com.example.mtalh.hijinnks.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Interfaces.HomeFragmentInterface;
import com.example.mtalh.hijinnks.Models.Model_Profile_Invites_rsvpd;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_Home_Profile_Invites;

import java.io.File;
import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment {

    ImageView share_icon;
    HomeFragmentInterface homeFragmentInterface;
    ArrayList<Model_Profile_Invites_rsvpd> list_invites = new ArrayList<>();
    ArrayList<Model_Profile_Invites_rsvpd> list_rsvpd = new ArrayList<>();
    LinearLayout invites_layout, rsvpd_layout;
    TextView invite_text, invite_count;
    TextView rsvpd_text, rsvpd_count;
    View invite_View, rsvpd_View;
    ImageView cover_photo;
    ImageView back_image;
    FrameLayout frameLayout;
    String[] profile_name = {"Welcome to ziro family 2018", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard"};
    String[] profile_type_invites = {"Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites"};
    String[] profile_name_rsvpd = {"Welcome to lahore city Model Town 2018", "Battey", "Bonut", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard"};
    String[] profile_type_rsvpd = {"RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd", "RSVP'd"};
    HomeFragment homeFragment = new HomeFragment(homeFragmentInterface);
    private RecyclerView recyclerView;
    private RecyclerAdapter_Home_Profile_Invites adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ProfileFragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        invites_layout = (LinearLayout) view.findViewById(R.id.invite_layout);
        rsvpd_layout = (LinearLayout) view.findViewById(R.id.rsvpd_layout);
        invite_text = (TextView) view.findViewById(R.id.invite_text);
        invite_count = (TextView) view.findViewById(R.id.invite_number);
        rsvpd_text = (TextView) view.findViewById(R.id.rsvpd_text);
        rsvpd_count = (TextView) view.findViewById(R.id.rsvpd_number);
        invite_View = (View) view.findViewById(R.id.invite_View);
        rsvpd_View = (View) view.findViewById(R.id.rsvpd_View);
        share_icon = (ImageView) view.findViewById(R.id.share_icon);
        cover_photo = (ImageView) view.findViewById(R.id.cover_photo);
        frameLayout = (FrameLayout) view.findViewById(R.id.home_framelayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home_profile_invite_fragment);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        back_image = (ImageView) view.findViewById(R.id.back_image);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, new HomeFragment(homeFragmentInterface));
                fragmentTransaction.commit();
            }
        });
        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationInfo api = getContext().getApplicationInfo();
                String apkPath = api.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                startActivity(Intent.createChooser(intent, "SHARE APP USING"));

            }
        });
        if (savedInstanceState == null) {
            profileInvites();
        }
        invites_layout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                profileInvites();
                invite_text.setTextColor(getResources().getColor(R.color.login_button_blue));
                invite_count.setTextColor(getResources().getColor(R.color.login_button_blue));
                invite_View.setBackgroundColor(getResources().getColor(R.color.login_button_blue));
                rsvpd_text.setTextColor(getResources().getColor(R.color.light_grey));
                rsvpd_count.setTextColor(getResources().getColor(R.color.light_grey));
                rsvpd_View.setBackgroundColor(getResources().getColor(R.color.light_grey));
            }
        });

        rsvpd_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileRSVPD();
                invite_text.setTextColor(getResources().getColor(R.color.light_grey));
                invite_count.setTextColor(getResources().getColor(R.color.light_grey));
                invite_View.setBackgroundColor(getResources().getColor(R.color.light_grey));
                rsvpd_text.setTextColor(getResources().getColor(R.color.login_button_blue));
                rsvpd_count.setTextColor(getResources().getColor(R.color.login_button_blue));
                rsvpd_View.setBackgroundColor(getResources().getColor(R.color.login_button_blue));
            }
        });
        return view;
    }

    public void profileInvites() {
        adapter = new RecyclerAdapter_Home_Profile_Invites(list_invites, getActivity());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        if (list_invites.size() == 0) {
            for (int i = 0; i < profile_name.length; i++) {
                list_invites.add(new Model_Profile_Invites_rsvpd(profile_name[i], profile_type_rsvpd[i]));
            }
        }
        adapter.SsetClickListener(new RecyclerAdapter_Home_Profile_Invites.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "I am Invite Fragment at position : " + Pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void profileRSVPD() {
        adapter = new RecyclerAdapter_Home_Profile_Invites((ArrayList<Model_Profile_Invites_rsvpd>) list_rsvpd, getActivity());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        if (list_rsvpd.size() == 0) {
            for (int j = 0; j < profile_name_rsvpd.length; j++) {
                list_rsvpd.add(new Model_Profile_Invites_rsvpd(profile_name_rsvpd[j], profile_type_rsvpd[j]));
            }
        }
        adapter.SsetClickListener(new RecyclerAdapter_Home_Profile_Invites.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "I am RSVP'D Recyclerview at position : " + Pos, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
