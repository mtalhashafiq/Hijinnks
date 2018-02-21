package com.example.mtalh.hijinnks.Activites.Tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mtalh.hijinnks.Activites.Authentication.Login_Activity;
import com.example.mtalh.hijinnks.Fragments.AddEvent_Fragment;
import com.example.mtalh.hijinnks.Fragments.Edit_ProfileFragment;
import com.example.mtalh.hijinnks.Fragments.HomeFragment;
import com.example.mtalh.hijinnks.Fragments.Map_Fragment;
import com.example.mtalh.hijinnks.Fragments.ProfileFragment;
import com.example.mtalh.hijinnks.Fragments.Search_Fragment;
import com.example.mtalh.hijinnks.Fragments.Setting_Fragment;
import com.example.mtalh.hijinnks.Fragments.Universal_Search_Fragment;
import com.example.mtalh.hijinnks.Interfaces.HomeFragmentInterface;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.Recycleradapter_Home_Home_NavigationDrawer;
import com.facebook.login.LoginManager;
import com.pixplicity.easyprefs.library.Prefs;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements HomeFragmentInterface {
    public static FragmentManager manager;
    public static FragmentTransaction transaction;

    private static long sayBackPress;
    FrameLayout home_framelayout;
    LinearLayout home_tablayout, search_tablayout, addEvent_tablayout, add_request_tablayout, setting_tablayout;
    DrawerLayout Home_Navigation_Drawer;
    TextView navigation_name_textview;
    RelativeLayout relative_layout_search_by_name;
    RelativeLayout bottombar;
    TextView logout;
    String[] name = {"Feed", "RSVP'd Events", "Favourites", "Messages", "Followers", "Following"};
    int[] image = {R.drawable.menu_feed_icon, R.drawable.ic_rsvpd_eye, R.drawable.menu_favourties_icon, R.drawable.ic_message_icon_gray,
            R.drawable.menu_followers_icon, R.drawable.menu_following_icon};
    String[] count_message = {"38", "", "", "75", "", "", "", "", "", "", ""};
    int[] border = {R.drawable.menu_tab_border_radius, R.drawable.menu_tab_border, R.drawable.menu_tab_border,
            R.drawable.menu_tab_border, R.drawable.menu_tab_border, R.drawable.menu_tab_border_radius_under};
    int[] count_border = {R.drawable.background_border_navigation_black, 0, 0,
            R.drawable.background_border_navigation_red, 0, 0};

    ImageView home_Image, search_image, add_image, add_request_image, setting_image;
    EditText edittext_search_by_name;
    private RecyclerView recyclerView_navigation;
    private Recycleradapter_Home_Home_NavigationDrawer adapter_navigation;
    private RecyclerView.LayoutManager layoutManager_navigation;
    private HomeFragment homeFragment = new HomeFragment(Home.this);
    private ProfileFragment profileFragment = new ProfileFragment(Home.this);
    private Map_Fragment mapFragment = new Map_Fragment();
    private AddEvent_Fragment addEvent_fragment = new AddEvent_Fragment(Home.this);
    private Search_Fragment search_fragment = new Search_Fragment(Home.this);
    private Edit_ProfileFragment edit_profileFragment = new Edit_ProfileFragment();
    private Setting_Fragment setting_fragment = new Setting_Fragment(Home.this);
    private Universal_Search_Fragment universal_search_fragment = new Universal_Search_Fragment();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        final TextView nav_user = (TextView) findViewById(R.id.navigation_name_textview);
        recyclerView_navigation = (RecyclerView) findViewById(R.id.recyclerview_home_navigationdrawer);
        adapter_navigation = new Recycleradapter_Home_Home_NavigationDrawer(name, image, count_message, border, count_border, getApplicationContext());
        layoutManager_navigation = new LinearLayoutManager(getApplicationContext());
        recyclerView_navigation.setLayoutManager(layoutManager_navigation);
        recyclerView_navigation.setAdapter(adapter_navigation);
        Home_Navigation_Drawer = (DrawerLayout) findViewById(R.id.home_navigation_drawer);
        home_framelayout = (FrameLayout) findViewById(R.id.home_framelayout);
        navigation_name_textview = (TextView) findViewById(R.id.navigation_name_textview);
        home_tablayout = (LinearLayout) findViewById(R.id.home_tab_layout);
        search_tablayout = (LinearLayout) findViewById(R.id.search_layout);
        addEvent_tablayout = (LinearLayout) findViewById(R.id.addevent_layout);
        add_request_tablayout = (LinearLayout) findViewById(R.id.add_request_layout);
        setting_tablayout = (LinearLayout) findViewById(R.id.setting_layout);
        home_Image = (ImageView) findViewById(R.id.home_image);
        search_image = (ImageView) findViewById(R.id.search_image);
        add_image = (ImageView) findViewById(R.id.add_image);
        add_request_image = (ImageView) findViewById(R.id.add_request_image);
        setting_image = (ImageView) findViewById(R.id.setting_image);
        CircleImageView navigation_profile_image = (CircleImageView) findViewById(R.id.navigation_profile_image);
        relative_layout_search_by_name = (RelativeLayout) findViewById(R.id.relative_layout_search_by_name);
        edittext_search_by_name = (EditText) findViewById(R.id.edittext_search_by_name);
        logout = (TextView) findViewById(R.id.logout);
        bottombar = (RelativeLayout) findViewById(R.id.bottombar);
        Toast.makeText(getApplicationContext(), "Home " + bottombar.getHeight(), Toast.LENGTH_SHORT).show();
        nav_user.setText(Prefs.getString("pref", ""));
        String url = Prefs.getString("pref_image", "");
        Glide.with(getApplicationContext()).load(url).into((navigation_profile_image));
        nav_user.setText(Prefs.getString("pref", ""));
        String url_twitter = Prefs.getString("pref_image", "");
        Glide.with(getApplicationContext()).load(url_twitter).into((navigation_profile_image));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "You are Logout", Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
                Prefs.remove("pref");
                Prefs.remove("pref_image");
                nav_user.setText("");
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(Home.this, Login_Activity.class);
                startActivity(intent);
            }
        });
        navigation_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, profileFragment);
                fragmentTransaction.commit();
                Home_Navigation_Drawer.closeDrawer(Gravity.LEFT);
                home_Image.setImageResource(R.drawable.home_icon);
                search_image.setImageResource(R.drawable.search_icon_main_menu);
                add_image.setImageResource(R.drawable.add_icon);
                add_request_image.setImageResource(R.drawable.add_request_icon);
                setting_image.setImageResource(R.drawable.setting_icon);

            }
        });
        relative_layout_search_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext_search_by_name.requestFocus();
                Toast.makeText(Home.this, "Search by name is clicking", Toast.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edittext_search_by_name, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_Home_Home_NavigationDrawer.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getApplicationContext(), "Navigation Drawer " + Pos, Toast.LENGTH_SHORT).show();
            }
        });
        if (savedInstanceState == null) {
            homeProfile();
            home_Image.setImageResource(R.drawable.home_selected_icon);
            search_image.setImageResource(R.drawable.search_icon_main_menu);
            add_image.setImageResource(R.drawable.add_icon);
            add_request_image.setImageResource(R.drawable.add_request_icon);
            setting_image.setImageResource(R.drawable.setting_icon);
        }
        home_tablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeProfile();
                home_Image.setImageResource(R.drawable.home_selected_icon);
                search_image.setImageResource(R.drawable.search_icon_main_menu);
                add_image.setImageResource(R.drawable.add_icon);
                add_request_image.setImageResource(R.drawable.add_request_icon);
                setting_image.setImageResource(R.drawable.setting_icon);
            }
        });
        search_tablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, universal_search_fragment);
                fragmentTransaction.commit();
                home_Image.setImageResource(R.drawable.home_icon);
                search_image.setImageResource(R.drawable.seaarch_selected_icon);
                add_image.setImageResource(R.drawable.add_icon);
                add_request_image.setImageResource(R.drawable.add_request_icon);
                setting_image.setImageResource(R.drawable.setting_icon);
            }
        });
        addEvent_tablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, addEvent_fragment);
                fragmentTransaction.commitAllowingStateLoss();
                home_Image.setImageResource(R.drawable.home_icon);
                search_image.setImageResource(R.drawable.search_icon_main_menu);
                add_image.setImageResource(R.drawable.add_icon);
                add_request_image.setImageResource(R.drawable.add_request_icon);
                setting_image.setImageResource(R.drawable.setting_icon);
            }
        });
        add_request_tablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, search_fragment);
                fragmentTransaction.commit();
                home_Image.setImageResource(R.drawable.home_icon);
                search_image.setImageResource(R.drawable.search_icon_main_menu);
                add_image.setImageResource(R.drawable.add_icon);
                add_request_image.setImageResource(R.drawable.add_request_selected_icon);
                setting_image.setImageResource(R.drawable.setting_icon);
            }
        });
        setting_tablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, edit_profileFragment);
                fragmentTransaction.commitAllowingStateLoss();
                home_Image.setImageResource(R.drawable.home_icon);
                search_image.setImageResource(R.drawable.search_icon_main_menu);
                add_image.setImageResource(R.drawable.add_icon);
                add_request_image.setImageResource(R.drawable.add_request_icon);
                setting_image.setImageResource(R.drawable.setting_selected_icon);
            }
        });
    }
    public void homeProfile() {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_framelayout, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (sayBackPress + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
            sayBackPress = System.currentTimeMillis();
        }
    }

    @Override
    public void icon_change_method_on_back_click(int index) {
        if (index == 0) {
            home_Image.setImageResource(R.drawable.home_selected_icon);
        } else if (index == 1) {
            search_image.setImageResource(R.drawable.search_icon_main_menu);
        } else if (index == 2) {
            add_image.setImageResource(R.drawable.add_icon);
        } else if (index == 3) {
            setting_image.setImageResource(R.drawable.setting_icon);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void creatNewTransection() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.disallowAddToBackStack();
    }
}
