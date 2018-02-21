package com.example.mtalh.hijinnks.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter.MyPagerAdapter;
import com.example.mtalh.hijinnks.MapDirectionTimeDistance.DirectionFinderListener;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_MapView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;


@SuppressLint("ValidFragment")
public class Map_Fragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener,
        LocationListener{
    public static final int REQUEST_LOCATION_CODE = 99;
    public final static int PAGES = 10;
    public final static int LOOPS = 1;
    public final static int FIRST_PAGE = PAGES * LOOPS;
    public static int CURRENT_PAGE = 0;
    static DirectionFinderListener context;
    public MyPagerAdapter adapter;
    public ViewPager pager;
    protected RecyclerViewPager recyclerviewmapinfowindow;
    ImageView back_image;
    SupportMapFragment mapFragment;
    View view;
    double latitude, longitude;
    int open_viewpager_check = 0;
    FrameLayout map_main_layout;
    float divide_value, divide_value1;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Marker mSelectedMarker = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map_, container, false);
        back_image = (ImageView) view.findViewById(R.id.back_image);
        map_main_layout=(FrameLayout)view.findViewById(R.id.map_relative_layout);


//        pager = (ViewPager) view.findViewById(R.id.myviewpager);

        adapter = new MyPagerAdapter(this, this.getFragmentManager());
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#41539b"));
        }
        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission is granted
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else // permission is denied
                {
                    Toast.makeText(getContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {


        mMap = googleMap;
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .snippet("Snippet")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker)));
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (open_viewpager_check==1){
                    recyclerviewmapinfowindow.setVisibility(View.GONE);
                    open_viewpager_check=0;
                }
                Toast.makeText(getContext(), "Map is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        LatLng hcmus = new LatLng(31.48, 74.31);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 12));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (open_viewpager_check == 0) {
                 /*   pager.setAdapter(adapter);
                    pager.setPageTransformer(false, adapter);
                    pager.setCurrentItem(FIRST_PAGE);
                    pager.setOffscreenPageLimit(3);
                    pager.setPageMargin(-100);
                    open_viewpager_check=1;*/
                    initViewPager();
                    recyclerviewmapinfowindow.setVisibility(View.VISIBLE);
                    open_viewpager_check=1;
                }
                else {
                    recyclerviewmapinfowindow.setVisibility(View.GONE);
                    open_viewpager_check=0;
                }
                marker.hideInfoWindow();
                int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

                String toastMsg;
                switch (screenSize)
                {
                    case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                        toastMsg = "Extra Large screen";
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        break;
                    case Configuration.SCREENLAYOUT_SIZE_LARGE:
                        toastMsg = "Large screen";
                        mMap.setPadding(0, 100, 0, -500);

                        break;
                    case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                        toastMsg = "Normal screen";
                        mMap.setPadding(0, 0, 0, 00);

                        break;
                    case Configuration.SCREENLAYOUT_SIZE_SMALL:
                        toastMsg = "Small screen";
                        mMap.setPadding(0, 0, 0, -200);
                        break;
                    default:
                        toastMsg = "Screen size is neither large, normal or small";
                }
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.unselect_marker);
                if (mSelectedMarker != null) {
                    mSelectedMarker.setIcon(icon);
                }
                mSelectedMarker = marker;
                mSelectedMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.selected_marker));
                return false;
            }
        });
    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }
    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);
        if (client != null) {
            if (client.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
            }
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (getActivity() != null) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (client != null) {
                    if (client.isConnected()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
                    }
                }
            }
        }
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        } else
            return true;
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onMapClick(LatLng latLng) {
        if (null != mSelectedMarker) {
            mSelectedMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.hijinnks_logo));
        }
        mSelectedMarker = null;
    }

    protected void initViewPager() {
        recyclerviewmapinfowindow=(RecyclerViewPager)view.findViewById(R.id.recyclerviewmapinfowindow);
//        int displayHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
//        recyclerviewmapinfowindow.getLayoutParams().height = displayHeight / 3;

//        divide_value = map_main_layout.getMeasuredHeight();
//        divide_value1 = divide_value / 9;
//        float xyz =divide_value1;
//        divide_function(recyclerviewmapinfowindow, xyz);


        int divide_value_main = map_main_layout.getMeasuredHeight();
        recyclerviewmapinfowindow.getLayoutParams().height = divide_value_main / 2;
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewmapinfowindow.setLayoutManager(layout);
        recyclerviewmapinfowindow.setAdapter(new RecyclerAdapter_MapView(getContext(), recyclerviewmapinfowindow));
        recyclerviewmapinfowindow.setHasFixedSize(true);
        recyclerviewmapinfowindow.setLongClickable(true);
        recyclerviewmapinfowindow.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
//                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
                int childCount = recyclerviewmapinfowindow.getChildCount();
                int width = recyclerviewmapinfowindow.getChildAt(0).getWidth();
                int padding = (recyclerviewmapinfowindow.getWidth() - width) / 2;
//                mCountText.setText("Count: " + childCount);

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    ;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        recyclerviewmapinfowindow.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
                Toast.makeText(getContext(), "oldPosition:" + oldPosition + " newPosition:" + newPosition, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerviewmapinfowindow.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (recyclerviewmapinfowindow.getChildCount() < 3) {
                    if (recyclerviewmapinfowindow.getChildAt(1) != null) {
                        if (recyclerviewmapinfowindow.getCurrentPosition() == 0) {
                            View v1 = recyclerviewmapinfowindow.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = recyclerviewmapinfowindow.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (recyclerviewmapinfowindow.getChildAt(0) != null) {
                        View v0 = recyclerviewmapinfowindow.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (recyclerviewmapinfowindow.getChildAt(2) != null) {
                        View v2 = recyclerviewmapinfowindow.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }

            }
        });
    }

    private void divide_function(View v, float value) {

        if (v instanceof LinearLayout) {
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) v.getLayoutParams();
            param.height = (int) value;
            v.setLayoutParams(param);
        } else if (v instanceof RelativeLayout) {
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) v.getLayoutParams();
            param.height = (int) value;
            v.setLayoutParams(param);
        }
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
