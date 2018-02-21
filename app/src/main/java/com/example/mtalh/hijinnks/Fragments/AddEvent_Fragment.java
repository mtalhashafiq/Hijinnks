package com.example.mtalh.hijinnks.Fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter.ImageVideoViewpager;
import com.example.mtalh.hijinnks.CustomeUI.EditTexts.Custome_EditText_addEvent;
import com.example.mtalh.hijinnks.Interfaces.HomeFragmentInterface;
import com.example.mtalh.hijinnks.Models.Model_Image_Video_ViewPager;
import com.example.mtalh.hijinnks.Models.Model_addEvent_addIntrest;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_addEvent_addIntrest;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_addEvent_showIntrest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class AddEvent_Fragment extends Fragment {

    public static List<Model_addEvent_addIntrest> list_addEvent_addIntrest = new ArrayList<Model_addEvent_addIntrest>();
    final Uri photo_alert_dialouge_uri = null;
    HomeFragmentInterface homeFragmentInterface;
    int RESULT_LOAD_IMAGE = 92;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int RESULT_LOAD_IMAGE_FROM_GALLERY = 100;
    LinearLayout coverPhoto_layout, slider_layout;
    LinearLayout tab1_layout, tab2_layout, tab3_layout, tab4_layout;
    ImageView tab1_image, tab2_image, tab3_image, tab4_image;
    View tab1_view, tab2_view, tab3_view, tab4_view;

    TextView intrest_view, description_of_event;
    ScrollView scrollView_add_event;
    LinearLayout tab_1_layout_scrollView, tab_3_layout_scrollView, tab_4_layout_scrollView;
    LinearLayout tab_2_layout_scrollView;
    EditText add_intrest_edittext;
    CircleImageView add_intrest_button;
    RelativeLayout uploadImage_video_layout;
    ImageView alert_dialoge_image_upload;
    VideoView alert_dialoge_video_upload;
    File videoFile;
    Uri video_uri;
    LinearLayout lower_part_tab_3;
    Button done_button_for_preview, done_button_for_live_event;
    ImageView back_button_for_discard_events, back_button_to_change_event;
    LinearLayout preview_linearlayout;
    LinearLayout make_event_public, make_event_private;
    LinearLayout make_event_public_box, make_event_private_box;
    ImageView make_event_public_image, make_event_private_image;
    TextView make_event_public_text, make_event_private_text;
    Custome_EditText_addEvent title_of_descriptionof_event, description_of_description_ofevent;
    TextView title_of_descriptionof_event_final, description_of_description_ofevent_final;
    ImageView public_private_image_final;
    TextView public_private_textview_final;
    int make_event_public_check = 1, make_event_private_check = 0;
    TextView select_hours, select_minut, select_aMpM;
    LinearLayout event_time_select;
    TimePicker timePicker;
    int phone_hour, phone_minut;
    String phone_hour_string;
    String phone_minut_string;
    String ampm;
    TextView pickdate;
    DatePicker datePicker;
    int pick_day, pick_month, pick_year;
    int counter_date = 0;
    TextView get_date_final, get_time_final;
    LinearLayout header_layout, header_layout_final;
    LinearLayout cover_photo_slider_layout;
    RelativeLayout upload_image_video_layout;
    TextView upload_image_video_text;
    LinearLayout navigation_addevent_layout;
    ScrollView scroll_view_add_event_final;
    TextView location_of_event_location;
    TextView show_location_of_event_final;
    Place place;
    String info_ofevent;
    RecyclerAdapter_addEvent_showIntrest recyclerAdapter_addEvent_showIntrest;
    RecyclerView.LayoutManager layoutManager_show_intrest;
    ViewPager viewPager_image_video;
    String stringImageUri;
    private int video_request_code = 92;
    private RecyclerView recyclerView_navigation;
    private RecyclerAdapter_addEvent_addIntrest adapter_navigation;
    private RecyclerView.LayoutManager layoutManager_navigation;
    private RecyclerView recyclerview_addevent_location_intrest_final;
    private ArrayList<Model_Image_Video_ViewPager> modelImageVideoViewPagerArrayList = new ArrayList<>();

    public AddEvent_Fragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_event_, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#41539b"));
        }
        final int[] counter = {0};
        /*alert_dialoge_video_upload = (VideoView) view.findViewById(R.id.alert_dialoge_video_upload);
        alert_dialoge_image_upload = (ImageView) view.findViewById(R.id.alert_dialoge_image_upload);
        if (!getVideo().equals("hiiiii")) {
            File videofile = new File(getVideo());
            alert_dialoge_video_upload.setVisibility(View.GONE);
            alert_dialoge_video_upload.setVideoPath(videofile.getAbsolutePath());
        }
        if (!geteCOlor().equals("hiiiii")) {
            alert_dialoge_image_upload.setVisibility(View.VISIBLE);
            Uri abc = Uri.fromFile(new File(geteCOlor()));

            Drawable yourDrawable;
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(abc);
                yourDrawable = Drawable.createFromStream(inputStream, abc.toString());
            } catch (FileNotFoundException e) {
                yourDrawable = getResources().getDrawable(R.drawable.hijinnks_logo);
            }
            ((ImageView) view.findViewById(R.id.alert_dialoge_image_upload)).setBackground(yourDrawable);
        }*/
        coverPhoto_layout = (LinearLayout) view.findViewById(R.id.cover_photo_layout);
        slider_layout = (LinearLayout) view.findViewById(R.id.slider_layout);
        scrollView_add_event = (ScrollView) view.findViewById(R.id.scroll_view_add_event);
        intrest_view = (TextView) view.findViewById(R.id.intrest_view);
        description_of_event = (TextView) view.findViewById(R.id.description_of_event);
        tab_1_layout_scrollView = (LinearLayout) view.findViewById(R.id.tab_1_layout_scrollView);
        tab_2_layout_scrollView = (LinearLayout) view.findViewById(R.id.tab_2_layout_scrollView);
        tab_3_layout_scrollView = (LinearLayout) view.findViewById(R.id.tab_3_layout_scrollView);
        tab_4_layout_scrollView = (LinearLayout) view.findViewById(R.id.tab_4_layout_scrollView);
        recyclerView_navigation = (RecyclerView) view.findViewById(R.id.recyclerview_addevent_location_intrest);
        add_intrest_edittext = (EditText) view.findViewById(R.id.add_intrest_edittext);
        add_intrest_button = (CircleImageView) view.findViewById(R.id.add_intrest_button);
        uploadImage_video_layout = (RelativeLayout) view.findViewById(R.id.uploadImage_video_layout);
        tab1_layout = (LinearLayout) view.findViewById(R.id.tab1_layout);
        tab2_layout = (LinearLayout) view.findViewById(R.id.tab2_layout);
        tab3_layout = (LinearLayout) view.findViewById(R.id.tab3_layout);
        tab4_layout = (LinearLayout) view.findViewById(R.id.tab4_layout);
        tab1_image = (ImageView) view.findViewById(R.id.tab1_image);
        tab2_image = (ImageView) view.findViewById(R.id.tab2_image);
        tab3_image = (ImageView) view.findViewById(R.id.tab3_image);
        tab4_image = (ImageView) view.findViewById(R.id.tab4_image);
        tab1_view = (View) view.findViewById(R.id.tab1_view);
        tab2_view = (View) view.findViewById(R.id.tab2_view);
        tab3_view = (View) view.findViewById(R.id.tab3_view);
        tab4_view = (View) view.findViewById(R.id.tab4_view);
        done_button_for_preview = (Button) view.findViewById(R.id.done_button_for_preview);
        done_button_for_live_event = (Button) view.findViewById(R.id.done_button_for_live_event);
        back_button_to_change_event = (ImageView) view.findViewById(R.id.back_button_to_change_event_final);
        back_button_for_discard_events = (ImageView) view.findViewById(R.id.back_button_to_discard_event);
        preview_linearlayout = (LinearLayout) view.findViewById(R.id.linearlayout_for_preview_event);
        lower_part_tab_3 = (LinearLayout) view.findViewById(R.id.lower_part_tab_3);
        make_event_public = (LinearLayout) view.findViewById(R.id.public_linearlayout);
        make_event_public_box = (LinearLayout) view.findViewById(R.id.public_linearlayout_box);
        make_event_public_image = (ImageView) view.findViewById(R.id.public_linearlayout_image);
        make_event_public_text = (TextView) view.findViewById(R.id.public_linearlayout_text);
        make_event_private = (LinearLayout) view.findViewById(R.id.private_linearlayout);
        make_event_private_box = (LinearLayout) view.findViewById(R.id.private_linearlayout_box);
        make_event_private_image = (ImageView) view.findViewById(R.id.private_linearlayout_image);
        make_event_private_text = (TextView) view.findViewById(R.id.private_linearlayout_text);
        title_of_descriptionof_event = (Custome_EditText_addEvent) view.findViewById(R.id.title_of_descriptionof_event);
        description_of_description_ofevent = (Custome_EditText_addEvent) view.findViewById(R.id.description_of_description_ofevent);
        public_private_image_final = (ImageView) view.findViewById(R.id.public_private_image_final);
        title_of_descriptionof_event_final = (TextView) view.findViewById(R.id.title_of_descriptionof_event_final);
        description_of_description_ofevent_final = (TextView) view.findViewById(R.id.description_of_description_ofevent_final);
        public_private_textview_final = (TextView) view.findViewById(R.id.public_private_textview_final);
        select_hours = (TextView) view.findViewById(R.id.select_hours);
        select_minut = (TextView) view.findViewById(R.id.select_minute);
        select_aMpM = (TextView) view.findViewById(R.id.select_AM_PM);
        location_of_event_location = (TextView) view.findViewById(R.id.location_of_event_location);
        show_location_of_event_final = (TextView) view.findViewById(R.id.show_location_of_event_final);
        event_time_select = (LinearLayout) view.findViewById(R.id.event_time_select);
        pickdate = (TextView) view.findViewById(R.id.pick_date);
        get_date_final = (TextView) view.findViewById(R.id.get_date_final);
        get_time_final = (TextView) view.findViewById(R.id.get_time_final);
        recyclerview_addevent_location_intrest_final = (RecyclerView) view.findViewById(R.id.recyclerview_addevent_location_intrest_final);
        header_layout = (LinearLayout) view.findViewById(R.id.header_layout);
        header_layout_final = (LinearLayout) view.findViewById(R.id.header_layout_final);
        cover_photo_slider_layout = (LinearLayout) view.findViewById(R.id.cover_photo_slider_layout);
        upload_image_video_layout = (RelativeLayout) view.findViewById(R.id.uploadImage_video_layout);
        upload_image_video_text = (TextView) view.findViewById(R.id.uploadImage_video_text);
        navigation_addevent_layout = (LinearLayout) view.findViewById(R.id.navigation_addevent_layout);
        scroll_view_add_event_final = (ScrollView) view.findViewById(R.id.scroll_view_add_event_final);
        viewPager_image_video = (ViewPager) view.findViewById(R.id.viewPager_image_video);

        adapter_navigation = new RecyclerAdapter_addEvent_addIntrest(list_addEvent_addIntrest, getContext());
        layoutManager_navigation = new LinearLayoutManager(getContext());
        recyclerView_navigation.setLayoutManager(layoutManager_navigation);
        recyclerView_navigation.stopNestedScroll();
        recyclerview_addevent_location_intrest_final.setNestedScrollingEnabled(false);
        recyclerView_navigation.setHasFixedSize(true);
        recyclerView_navigation.setAdapter(adapter_navigation);

        add_intrest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!add_intrest_edittext.getText().toString().isEmpty()) {
                    list_addEvent_addIntrest.add(new Model_addEvent_addIntrest(add_intrest_edittext.getText().toString()));
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                } else {
                    Toast.makeText(getContext(), "Please Enter Intrest", Toast.LENGTH_SHORT).show();
                }
                recyclerView_navigation.scrollToPosition(list_addEvent_addIntrest.size());
                add_intrest_edittext.setText("");
            }
        });
        adapter_navigation.SetItemClcik(new RecyclerAdapter_addEvent_addIntrest.ItemClickInterface() {
            @Override
            public void itemclickmethod(int position) {
                Toast.makeText(getContext(), "Intrest position is " + position, Toast.LENGTH_SHORT).show();

            }
        });
        make_event_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_event_public_box.setBackground(getActivity().getResources().getDrawable(R.drawable.description_public_private_ckeck));
                make_event_public_image.setImageResource(R.drawable.ic_public_icon_selected);
                make_event_public_text.setTextColor(Color.parseColor("#1f4ba4"));
                make_event_private_box.setBackground(getActivity().getResources().getDrawable(R.drawable.description_public_private_unckeck));
                make_event_private_image.setImageResource(R.drawable.ic_lock_icon_unselected);
                make_event_private_text.setTextColor(Color.parseColor("#e0e0e0"));
                make_event_public_check = 1;
                make_event_private_check = 0;
            }
        });
        make_event_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_event_private_box.setBackground(getActivity().getResources().getDrawable(R.drawable.description_public_private_ckeck));
                make_event_private_image.setImageResource(R.drawable.ic_lock_icon_selected);
                make_event_private_text.setTextColor(Color.parseColor("#1f4ba4"));

                make_event_public_box.setBackground(getActivity().getResources().getDrawable(R.drawable.description_public_private_unckeck));
                make_event_public_image.setImageResource(R.drawable.ic_public_icon_unselected);
                make_event_public_text.setTextColor(Color.parseColor("#e0e0e0"));
                make_event_public_check = 0;
                make_event_private_check = 1;
            }
        });

        location_of_event_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                }
            }
        });
        event_time_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder add_video_alert_dialouge_sub = new AlertDialog.Builder(getContext());
                View view_add_video = getLayoutInflater().inflate(R.layout.time_layout, null);

                add_video_alert_dialouge_sub.setView(view_add_video);
                add_video_alert_dialouge_sub.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        phone_hour = timePicker.getCurrentHour();
                        phone_minut = timePicker.getCurrentMinute();
                        String aMpM = "AM";
                        if (phone_hour > 11) {
                            aMpM = "PM";
                        }
                        int currentHour;
                        if (phone_hour > 12) {
                            currentHour = phone_hour - 12;
                        } else {
                            currentHour = phone_hour;
                        }
                        phone_hour_string = String.valueOf(currentHour);
                        phone_minut_string = String.valueOf(phone_minut);
                        ampm = aMpM;
                       /*  phone_hour_string= String.valueOf(phone_hour);
                         phone_minut_string= String.valueOf(phone_minut);*/
                        String total_phonetime = phone_hour_string + phone_minut_string + ampm;
                        select_hours.setText("" + phone_hour_string);
                        select_minut.setText("" + phone_minut_string);
                        select_aMpM.setText("" + ampm);
                        Toast.makeText(getContext(), "" + phone_hour + phone_minut, Toast.LENGTH_SHORT).show();
                        counter[0]++;
                    }
                });
                final AlertDialog alertDialog = add_video_alert_dialouge_sub.create();
                alertDialog.show();
                timePicker = (TimePicker) view_add_video.findViewById(R.id.timePicker);

                if (DateFormat.is24HourFormat(getContext())) {
                    timePicker.setIs24HourView(true);
                } else {
                    timePicker.setIs24HourView(false);
                }
                if (counter[0] > 0) {
                    timePicker.setCurrentHour(phone_hour);
                    timePicker.setCurrentMinute(phone_minut);

                }
            }
        });
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder add_video_alert_dialouge_sub = new AlertDialog.Builder(getContext());
                View view_add_video = getLayoutInflater().inflate(R.layout.calander_layout, null);
                add_video_alert_dialouge_sub.setView(view_add_video);
                add_video_alert_dialouge_sub.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pick_day = datePicker.getDayOfMonth();
                        pick_month = datePicker.getMonth() + 1;
                        pick_year = datePicker.getYear();
                        pickdate.setText(" " + pick_day + " " + pick_month + " " + pick_year);
                        counter_date++;
                    }
                });
                final AlertDialog alertDialog = add_video_alert_dialouge_sub.create();
                alertDialog.show();
                datePicker = (DatePicker) view_add_video.findViewById(R.id.datePicker);
                if (counter_date > 0) {

                    datePicker.updateDate(pick_year, pick_month - 1, pick_day);
                }

            }
        });
        done_button_for_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                header_layout.setVisibility(View.GONE);
                header_layout_final.setVisibility(View.VISIBLE);
                cover_photo_slider_layout.setVisibility(View.GONE);
                upload_image_video_layout.setVisibility(View.GONE);
                upload_image_video_text.setVisibility(View.GONE);
                scrollView_add_event.setVisibility(View.GONE);
                scroll_view_add_event_final.setVisibility(View.VISIBLE);
                title_of_descriptionof_event_final.setText(title_of_descriptionof_event.getText());
                description_of_description_ofevent_final.setText(description_of_description_ofevent.getText());
                show_location_of_event_final.setText(location_of_event_location.getText());
                String getmonthNmae = getMonthName(pick_month);
                get_date_final.setText(getmonthNmae + " " + pick_day + "," + pick_year);
                get_time_final.setText(phone_hour_string + ":" + phone_minut_string + ampm);
                if (make_event_public_check == 1 && make_event_private_check == 0) {
                    public_private_image_final.setImageResource(R.drawable.ic_public_icon_unselected);
                    public_private_textview_final.setText("Public");

                } else {
                    public_private_image_final.setImageResource(R.drawable.ic_lock_icon_unselected);
                    public_private_textview_final.setText("Private");

                }
                recyclerAdapter_addEvent_showIntrest = new RecyclerAdapter_addEvent_showIntrest(list_addEvent_addIntrest, getContext());
                layoutManager_show_intrest = new LinearLayoutManager(getContext());
                recyclerview_addevent_location_intrest_final.setLayoutManager(layoutManager_show_intrest);
                recyclerview_addevent_location_intrest_final.stopNestedScroll();
                recyclerview_addevent_location_intrest_final.setNestedScrollingEnabled(false);
                recyclerview_addevent_location_intrest_final.setHasFixedSize(true);
                recyclerview_addevent_location_intrest_final.setAdapter(recyclerAdapter_addEvent_showIntrest);
            }
        });
        done_button_for_live_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Event has become live", Toast.LENGTH_SHORT).show();
            }
        });
        back_button_for_discard_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Event is discarded", Toast.LENGTH_SHORT).show();
            }
        });
        back_button_to_change_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                header_layout.setVisibility(View.VISIBLE);
                header_layout_final.setVisibility(View.GONE);
                cover_photo_slider_layout.setVisibility(View.VISIBLE);
                upload_image_video_layout.setVisibility(View.VISIBLE);
                upload_image_video_text.setVisibility(View.VISIBLE);
                scrollView_add_event.setVisibility(View.VISIBLE);
                scroll_view_add_event_final.setVisibility(View.GONE);
            }
        });
        uploadImage_video_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder add_photo_video_dialouge = new AlertDialog.Builder(getContext());
                View view_alert_dialouge = getLayoutInflater().inflate(R.layout.alert_dialouge_add_photo_video_layout, null);
                add_photo_video_dialouge.setView(view_alert_dialouge);
                Log.d("add_photovideo", "onClick: ");
                final AlertDialog dialog = add_photo_video_dialouge.create();
                dialog.show();

                TextView add_photo_alert_dialouge = (TextView) view_alert_dialouge.findViewById(R.id.choose_photo);
                TextView add_video_alert_dialouge = (TextView) view_alert_dialouge.findViewById(R.id.choose_video);

                add_photo_alert_dialouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      /*  alert_dialoge_video_upload.setVisibility(View.GONE);
                        alert_dialoge_image_upload.setVisibility(View.VISIBLE);
*/

                        Toast.makeText(getContext(), "Photo opton is clicked", Toast.LENGTH_SHORT).show();
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.OFF)
                                .setActivityTitle("My Crop")
                                .setCropShape(CropImageView.CropShape.RECTANGLE)
                                .setCropMenuCropButtonTitle("Done")
                                .setRequestedSize(8000, 80)
                                .setCropMenuCropButtonIcon(R.mipmap.ic_launcher);
                        Intent intent = CropImage.activity(photo_alert_dialouge_uri).getIntent(getContext());
                        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
                        dialog.dismiss();
                    }
                });
                add_video_alert_dialouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        galleryIntent. setType("video/*");
                        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE_FROM_GALLERY);
                        dialog.dismiss();


                    /*    Toast.makeText(getActivity(), "Video Button is clicked", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder add_video_alert_dialouge_sub = new AlertDialog.Builder(getContext());
                        View view_add_video = getLayoutInflater().inflate(R.layout.alert_dialouge_video, null);
                        add_video_alert_dialouge_sub.setView(view_add_video);
                        final AlertDialog alertDialog = add_video_alert_dialouge_sub.create();
                        alertDialog.show();
                        TextView add_video_from_gallery_alert_dialouge = (TextView) view_add_video.findViewById(R.id.choose_from_gallery);
                        TextView add_video_from_camera_alert_dialouge = (TextView) view_add_video.findViewById(R.id.choose_from_camera);
                        *//*alert_dialoge_video_upload.setVisibility(View.VISIBLE);
                        alert_dialoge_image_upload.setVisibility(View.GONE);*//*

                        add_video_from_gallery_alert_dialouge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                galleryIntent. setType("video*//*");
                                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE_FROM_GALLERY);
                                alertDialog.dismiss();
                            }
                        });
                        add_video_from_camera_alert_dialouge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    video_uri = FileProvider.getUriForFile(getContext(), "com.example.mtalh.hijinnks.provider", getFilePath());
                                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, video_uri);

                                } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    File videoFile = getFilePath();
                                    video_uri = Uri.fromFile(videoFile);
                                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, video_uri);

                                } else {
                                    File videoFile = getFilePath();
                                    video_uri = Uri.fromFile(videoFile);
                                }

                                ;
                                cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                                startActivityForResult(cameraIntent, video_request_code);
                                alertDialog.dismiss();

                            }
                        });*/

                        dialog.dismiss();
                    }
                });
            }
        });
        scrollView_add_event.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Rect scrollBounds = new Rect();
                scrollView_add_event.getHitRect(scrollBounds);
                if (tab_1_layout_scrollView.getLocalVisibleRect(scrollBounds)) {

                    tab1_view.setVisibility(View.VISIBLE);
                    tab2_view.setVisibility(View.INVISIBLE);
                    tab3_view.setVisibility(View.INVISIBLE);
                    tab4_view.setVisibility(View.INVISIBLE);
                } else if (tab_2_layout_scrollView.getLocalVisibleRect(scrollBounds)) {

                    tab1_view.setVisibility(View.INVISIBLE);
                    tab2_view.setVisibility(View.VISIBLE);
                    tab3_view.setVisibility(View.INVISIBLE);
                    tab4_view.setVisibility(View.INVISIBLE);
                } else if (tab_3_layout_scrollView.getLocalVisibleRect(scrollBounds)) {
                    tab1_view.setVisibility(View.INVISIBLE);
                    tab2_view.setVisibility(View.INVISIBLE);
                    tab3_view.setVisibility(View.VISIBLE);
                    tab4_view.setVisibility(View.INVISIBLE);
                } else if (tab_4_layout_scrollView.getLocalVisibleRect(scrollBounds)) {
                    tab1_view.setVisibility(View.INVISIBLE);
                    tab2_view.setVisibility(View.INVISIBLE);
                    tab3_view.setVisibility(View.INVISIBLE);
                    tab4_view.setVisibility(View.VISIBLE);
                }
            }
        });


        tab1_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1_view.setVisibility(View.VISIBLE);
                tab2_view.setVisibility(View.INVISIBLE);
                tab3_view.setVisibility(View.INVISIBLE);
                tab4_view.setVisibility(View.INVISIBLE);
                //   scrollView_add_event.scrollTo(0, (int) catogry_edittext.getY());
//                scrollView_add_event.smoothScrollTo(0, 0);
                tab_1_layout_scrollView.getParent().requestChildFocus(tab_1_layout_scrollView, tab_1_layout_scrollView);

            }
        });
        tab2_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1_view.setVisibility(View.INVISIBLE);
                tab2_view.setVisibility(View.VISIBLE);
                tab3_view.setVisibility(View.INVISIBLE);
                tab4_view.setVisibility(View.INVISIBLE);
                //focusOnView();
                tab_2_layout_scrollView.getParent().requestChildFocus(tab_2_layout_scrollView, tab_2_layout_scrollView);
//                scrollView_add_event.smoothScrollTo(0, 380);
                //    scrollView_add_event.scrollTo(0, (int) recyclerView_navigation.getY());
              /*  scrollView_add_event.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView_add_event.smoothScrollTo(0, intrest_view.getBottom());
                    }
                });*/
            }


        });
        tab3_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1_view.setVisibility(View.INVISIBLE);
                tab2_view.setVisibility(View.INVISIBLE);
                tab3_view.setVisibility(View.VISIBLE);
                tab4_view.setVisibility(View.INVISIBLE);
                //    scrollView_add_event.scrollTo(0, (int) description_of_event.getY());
                tab_3_layout_scrollView.getParent().requestChildFocus(lower_part_tab_3, tab_3_layout_scrollView);
//                scrollView_add_event.smoothScrollTo(0, 780);
                //       scroll_view_add_event_final.fullScroll(tab_3_layout_scrollView.FOCUS_UP);
            }
        });
        tab4_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1_view.setVisibility(View.INVISIBLE);
                tab2_view.setVisibility(View.INVISIBLE);
                tab3_view.setVisibility(View.INVISIBLE);
                tab4_view.setVisibility(View.VISIBLE);
                tab_4_layout_scrollView.getParent().requestChildFocus(tab_4_layout_scrollView, tab_4_layout_scrollView);
            }
        });


        tab1_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                 catogry_edittext.getParent().requestChildFocus(catogry_edittext, catogry_edittext);
                tab1_view.setVisibility(View.VISIBLE);
                tab2_view.setVisibility(View.INVISIBLE);
                tab3_view.setVisibility(View.INVISIBLE);
                tab4_view.setVisibility(View.INVISIBLE);
                //   scrollView_add_event.scrollTo(0, (int) catogry_edittext.getY());
//                scrollView_add_event.smoothScrollTo(0, 0);
                tab_1_layout_scrollView.getParent().requestChildFocus(tab_1_layout_scrollView, tab_1_layout_scrollView);
                Toast.makeText(getContext(), "first", Toast.LENGTH_SHORT).show();
            }
        });
        tab2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  catogry_edittext.getParent().requestChildFocus(catogry_edittext, catogry_edittext);
                tab1_view.setVisibility(View.INVISIBLE);
                tab2_view.setVisibility(View.VISIBLE);
                tab3_view.setVisibility(View.INVISIBLE);
                tab4_view.setVisibility(View.INVISIBLE);
                //focusOnView();
                tab_2_layout_scrollView.getParent().requestChildFocus(tab_2_layout_scrollView, tab_2_layout_scrollView);

                Toast.makeText(getContext(), "second", Toast.LENGTH_SHORT).show();
            }
        });

        tab3_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  catogry_edittext.getParent().requestChildFocus(catogry_edittext, catogry_edittext);
                tab1_view.setVisibility(View.INVISIBLE);
                tab2_view.setVisibility(View.INVISIBLE);
                tab3_view.setVisibility(View.VISIBLE);
                tab4_view.setVisibility(View.INVISIBLE);
                //    scrollView_add_event.scrollTo(0, (int) description_of_event.getY());
//                scroll_view_add_event_final.fullScroll(tab_3_layout_scrollView.FOCUS_UP);
                tab_3_layout_scrollView.getParent().requestChildFocus(pickdate, pickdate);
//                scroll_view_add_event_final.scrollBy(0,scroll_view_add_event_final.getBottom());
                // tab_3_layout_scrollView.getParent().requestChildFocus(tab_3_layout_scrollView, tab_3_layout_scrollView);
//                scroll_view_add_event_final.fullScroll(event_time_select.FOCUS_DOWN);
                Toast.makeText(getContext(), "three", Toast.LENGTH_SHORT).show();
            }
        });
        tab4_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1_view.setVisibility(View.INVISIBLE);
                tab2_view.setVisibility(View.INVISIBLE);
                tab3_view.setVisibility(View.INVISIBLE);
                tab4_view.setVisibility(View.VISIBLE);
                tab_4_layout_scrollView.getParent().requestChildFocus(tab_4_layout_scrollView, tab_4_layout_scrollView);
            }
        });


        coverPhoto_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coverPhoto_layout.setBackgroundResource(R.drawable.background_border_add_event_layout);
                slider_layout.setBackgroundResource(0);
            }
        });
        slider_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slider_layout.setBackgroundResource(R.drawable.background_border_add_event_layout);
                coverPhoto_layout.setBackgroundResource(0);
            }
        });


        return view;
    }

    public void adddata(String addIntrest_editText) {

        adapter_navigation.addnewdata(addIntrest_editText);

        list_addEvent_addIntrest.add(new Model_addEvent_addIntrest(addIntrest_editText));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
              /*  ((ImageView) getActivity().findViewById(R.id.alert_dialoge_image_upload)).setImageURI(result.getUri());
                ((ImageView) getActivity().findViewById(R.id.alert_dialoge_image_upload)).setBackground(((ImageView) getActivity().findViewById(R.id.alert_dialoge_image_upload)).getDrawable());
                ((ImageView) getActivity().findViewById(R.id.alert_dialoge_image_upload)).setImageURI(null);*/
                stringImageUri = (result.getUri()).getPath();
                modelImageVideoViewPagerArrayList.add(new Model_Image_Video_ViewPager(stringImageUri));
                viewPager_image_video.setAdapter(new ImageVideoViewpager(modelImageVideoViewPagerArrayList, getContext()));
                viewPager_image_video.setOffscreenPageLimit(0);



                /*String stringUri;
                stringUri = (result.getUri()).getPath();
                storeImage(stringUri);*/
            }
        }

        /*if (resultCode != RESULT_CANCELED && data != null) {
            if (requestCode == video_request_code) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getActivity(), "Video successfully recorded", Toast.LENGTH_SHORT).show();
                    Uri recorded_video = data.getData();
                    setVideo(recorded_video.getPath());
                    String[] filePathColumn;
                    filePathColumn = new String[]{MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(recorded_video, filePathColumn, null, null, null);
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       *//* alert_dialoge_video_upload.setVideoURI(recorded_video);
                        alert_dialoge_video_upload.start();*//*
                    } else {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        File from = new File(picturePath);
                        File to = getFilePath();
                        from.renameTo(to);
                        setVideo(to.getAbsolutePath());
                       *//* alert_dialoge_video_upload.setVideoPath(to.getAbsolutePath());
                        alert_dialoge_video_upload.start();*//*
                    }
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    }
                } else {
                    Toast.makeText(getActivity(), "Video donot  successfully recorded", Toast.LENGTH_SHORT).show();
                }
            }
        }*/

        if (requestCode == RESULT_LOAD_IMAGE_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            setVideo(selectedImage.getPath());
            String[] filePathColumn;
            filePathColumn = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
          /*  alert_dialoge_video_upload.setVideoURI(selectedImage);
            alert_dialoge_video_upload.start();*/
            modelImageVideoViewPagerArrayList.add(new Model_Image_Video_ViewPager(selectedImage, true));
            viewPager_image_video.setAdapter(new ImageVideoViewpager(modelImageVideoViewPagerArrayList, getContext()));
            viewPager_image_video.setOffscreenPageLimit(0);
        }
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                place = PlaceAutocomplete.getPlace(getContext(), data);
                info_ofevent = place.getName() + " " + place.getAddress() + place.getLatLng() + " " + place.getId();
                location_of_event_location.setText(info_ofevent);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);
            } else if (requestCode == RESULT_CANCELED) {

            }
        }
    }

    public File getFilePath() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/talha___");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
                return null;
            }
        }
        videoFile = new File(mediaStorageDir, "sample_video" + System.currentTimeMillis() + ".mp4");
        return videoFile;
    }

    private void storeImage(String color) {
        SharedPreferences msharedPreferences = this.getActivity().getSharedPreferences("Toolbar color", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = msharedPreferences.edit();
        mEditor.putString("color", String.valueOf(color));
        mEditor.apply();
    }

    private String geteCOlor() {
        SharedPreferences msharedPreferences = this.getActivity().getSharedPreferences("Toolbar color", MODE_PRIVATE);
        return msharedPreferences.getString("color", "hiiiii");
    }

    private String getVideo() {
        SharedPreferences msharedPreferences = this.getActivity().getSharedPreferences("Toolbar color", MODE_PRIVATE);
        return msharedPreferences.getString("video_shared_prefrences", "hiiiii");
    }

    private void setVideo(String color) {
        SharedPreferences msharedPreferences = this.getActivity().getSharedPreferences("Toolbar color", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = msharedPreferences.edit();
        mEditor.putString("video_shared_prefrences", String.valueOf(color));
        mEditor.apply();
    }

    private void focusOnView() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView_add_event.scrollTo(0, recyclerView_navigation.getTop());
            }
        });
    }

    public String getMonthName(int pick_month) {
        switch (pick_month) {
            case 1:
                return "January";
            case 2:
                return "Feburary";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "Septemper";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Month";
        }
    } // main method


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
