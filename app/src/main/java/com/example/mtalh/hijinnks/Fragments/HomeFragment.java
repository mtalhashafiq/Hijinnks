package com.example.mtalh.hijinnks.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Interfaces.HomeFragmentInterface;
import com.example.mtalh.hijinnks.Models.Model_Home_Fragment;
import com.example.mtalh.hijinnks.Models.PostMedia;
import com.example.mtalh.hijinnks.R;
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_Home_HomeFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Random;

@SuppressLint("ValidFragment")

public class HomeFragment extends Fragment {
   public static ArrayList<PostMedia> media3;
    HomeFragmentInterface homeFragmentInterface;
    String videourl2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    String TEST_URL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String videourl3="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4";
    String videourl = "http://www.demonuts.com/Demonuts/smallvideo.mp4";
    String ImageUrl_1 = "http://www.intrawallpaper.com/static/images/jaguar_mexico-3840x2160.jpg";
    String ImageUrl_2 = "http://www.intrawallpaper.com/static/images/6903949-full-hd-wallpapers-27699_aAGj09F.jpg";
    String ImageUrl_3 = "http://www.intrawallpaper.com/static/images/728660-3d-nature-wallpaper.jpg";
    String ImageUrl_4 = "http://www.intrawallpaper.com/static/images/hd_wallpapers_hd10.jpg";
    String video_array[] = {TEST_URL, videourl, videourl2};
    String image_array[] = {ImageUrl_1, ImageUrl_2};
    int random_number = 2;
    int il;
    Random random = new Random();
    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    String[] array1;
    String[] array2;
    String[] profile_name = {"Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard",
            "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard"};
    String[] videos_URLS = {videourl, TEST_URL, videourl, TEST_URL, videourl, TEST_URL, videourl, TEST_URL, videourl, TEST_URL, videourl, TEST_URL, videourl, TEST_URL,
            videourl, TEST_URL, videourl, TEST_URL, videourl, TEST_URL, videourl};
    int[] user_profile_image = {R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1,
            R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3,
            R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5,
            R.drawable.person3, R.drawable.person4, R.drawable.person5,};
    ArrayList<PostMedia> media1;
    ArrayList<PostMedia> media2;
    ArrayList<PostMedia> media4;
    String[] intrests = {"Food", "Cricket", "FootBaLl", "COmputer", "Rovering", "Food", "Cricket", "FootBaLl", "COmputer", "Rovering", "Food", "Cricket", "FootBaLl", "COmputer", "Rovering",
            "Food", "Cricket", "FootBaLl", "COmputer", "Rovering", "End"};
    String[] title_of_events = {"Welcome to Ziro Valley Lahore 2018 to 2019", "IT seminar for Bachlor Students", "Bedminton Tournament at 8:00PM on 45 January",
            "Coding Pixel", "Lahore", "Welcome to Ziro Valley Lahore 2018 to 2019", "IT seminar for Bachlor Students", "Bedminton Tournament at 8:00PM on 45 January",
            "Coding Pixel", "Lahore", "Welcome to Ziro Valley Lahore 2018 to 2019", "IT seminar for Bachlor Students", "Bedminton Tournament at 8:00PM on 45 January",
            "Coding Pixel", "Lahore", "Welcome to Ziro Valley Lahore 2018 to 2019", "IT seminar for Bachlor Students", "Bedminton Tournament at 8:00PM on 45 January",
            "Coding Pixel", "Lahore", "End"};
    DrawerLayout fragment_drawer_get;
    ImageView open_chat_image;
    public static ImageLoader mImageLoader;
    ArrayList<Model_Home_Fragment> model_home_fragments = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerAdapter_Home_HomeFragment adapter;
    private RecyclerView.LayoutManager layoutManager;

    public HomeFragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        open_chat_image = (ImageView) view.findViewById(R.id.open_chat_image);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home_fragment);
        adapter = new RecyclerAdapter_Home_HomeFragment(model_home_fragments, getActivity());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

       /* for (int i = 0; i < 4; i++) {
            while (true) {
                int next = random.nextInt(4) + 1;
                if (!list1.contains(next)) {
                    list1.add((video_array[next]));
                    list2.add((image_array[next]));
                    break;
                }
            }
        }


        array1 = convertIntegers(list1);
        array2 = convertIntegers(list2);*/

        media1 = new ArrayList<>();
        media1.add(new PostMedia("Video", videourl2, videourl));

        media2 = new ArrayList<>();
        media2.add(new PostMedia("Video", videourl2, videourl2));
        media2.add(new PostMedia("Image", ImageUrl_3, null));


        media3 = new ArrayList<>();
        media3.add(new PostMedia("Image", ImageUrl_3, null));
        media3.add(new PostMedia("Image", ImageUrl_4, null));
        media3.add(new PostMedia("Video", videourl2, videourl2));
        media3.add(new PostMedia("Video", videourl, videourl));



        media4 = new ArrayList<>();
        media4.add(new PostMedia("Image", ImageUrl_3, null));
        media4.add(new PostMedia("Video", videourl2, videourl2));
        media4.add(new PostMedia("Video", videourl, videourl));
        media4.add(new PostMedia("Image", ImageUrl_4, null));
        media4.add(new PostMedia("Image", ImageUrl_2, null));
        media4.add(new PostMedia("Video", videourl, videourl));


        if (model_home_fragments.size() == 0) {
//            for (int i = 0; i < profile_name.length; i++) {
                model_home_fragments.add(new Model_Home_Fragment("Adam", R.drawable.person1, "Food", media1, "Welcome to Party"));
                model_home_fragments.add(new Model_Home_Fragment("John", R.drawable.person2, "COmputer", media2, "Welcome to Party"));
                model_home_fragments.add(new Model_Home_Fragment("Michael", R.drawable.person3, "Laptop", media3, "Welcome to Party"));
                model_home_fragments.add(new Model_Home_Fragment("Radika", R.drawable.person4, "BAtBall", media4, "Welcome to Party"));

//                model_home_fragments.add(new Model_Home_Fragment(profile_name[i], user_profile_image[i], intrests[i], media[i], title_of_events[i]));
//            }
        }
        adapter.notifyDataSetChanged();
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        fragment_drawer_get = (DrawerLayout) getActivity().findViewById(R.id.home_navigation_drawer);
        ImageView drawer_icon = (ImageView) view.findViewById(R.id.drawer_image);
        adapter.SsetClickListener(new RecyclerAdapter_Home_HomeFragment.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "Hi " + Pos, Toast.LENGTH_SHORT).show();
            }
        });

        drawer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment_drawer_get.openDrawer(Gravity.LEFT);
                Toast.makeText(getActivity(), "Drawer Open", Toast.LENGTH_SHORT).show();
            }
        });
        open_chat_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_framelayout, new ChatList());
                fragmentTransaction.commit();
            }
        });


        return view;
    }

  /*  public static int[] convertIntegers(List<String> integers) {
        int[] ret = new int[integers.size()];
        Iterator<String> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }*/

    /*public void givenList_whenNumberElementsChosen_shouldReturnRandomElementsNoRepeat() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("one", "two", "three", "four");

        int numberOfElements = 2;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            String randomElement = givenList.get(randomIndex);
            givenList.remove(randomIndex);
            Log.d("randomString",""+randomElement);
        }


    }
*/
    public ImageLoader getImageLoader() {

        if (mImageLoader == null) {
            mImageLoader = ImageLoader.getInstance();
            mImageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        }
        return this.mImageLoader;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
