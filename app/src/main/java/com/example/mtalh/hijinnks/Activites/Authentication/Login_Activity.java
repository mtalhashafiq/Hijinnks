package com.example.mtalh.hijinnks.Activites.Authentication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mtalh.hijinnks.Activites.Tab.Home;
import com.example.mtalh.hijinnks.JSONRequest.CustomRequest;
import com.example.mtalh.hijinnks.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.pixplicity.easyprefs.library.Prefs;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class Login_Activity extends AppCompatActivity {

    final String[] id = new String[1];
    LoginButton loginButton;
    TwitterLoginButton twitterLoginButton;
    ImageView facebook_button, twitter_button;
    String imageURL;
    String Compelete_information;
    EditText username;
    TextView connectwith;
    String FB_URL = "http://139.162.37.73/hijinnks/api/v1/fblogin";
    String Twitter_URL = "http://139.162.37.73/hijinnks/api/v1/twitter_login";
    String fb_username, fb_email, description, fb_location, timezone, status, fb_id;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(getApplicationContext());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_login_);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
      /*  username = (EditText) findViewById(R.id.username);
        connectwith = (TextView)findViewById(R.id.connectwith);*/
        loginButton = (LoginButton) findViewById(R.id.login_button);
        facebook_button = (ImageView) findViewById(R.id.facebook_button);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.login_button_twitter);
        twitter_button = (ImageView) findViewById(R.id.twitter_button);
        if (!Prefs.getString("pref", "").equals("")) {
            Log.d("PREFRENCES", Prefs.getString("pref", ""));
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        } else {
        }
        FacebookSdk.sdkInitialize(Login_Activity.this);
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "public_profile");
        loginButton.setReadPermissions(permissionNeeds);
        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("onSuccess");
                        String accessToken = loginResult.getAccessToken().getToken();
                        Log.i("accessToken", accessToken);
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.i("LoginActivity", response.toString());
                                        try {
                                            id[0] = object.getString("id");
                                            try {
                                                URL profile_pic = new URL("http://graph.facebook.com/" + id[0] + "/picture?type=large");
                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            }
                                            final String name = object.getString("name");
                                            Login_Activity.this.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Compelete_information = name;
                                                }
                                            });
                                            Prefs.putString("pref", Compelete_information);
                                            Prefs.putString("pref_image", "http://graph.facebook.com/" + id[0] + "/picture?type=large");
                                            Login_Activity.this.finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(new Intent(Login_Activity.this, Home.class));
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields",
                                "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                    }
                });
        twitter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twitterLoginButton.performClick();
            }
        });
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                fb_username = result.data.getUserName();
                Call<User> user = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(true, false, true);
                user.enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {

                        User userInfo = result.data;
                        fb_username = userInfo.name;
                        fb_email = userInfo.email;
                        description = userInfo.description;
                        fb_location = userInfo.location;
                        timezone = userInfo.timeZone;
                        status = String.valueOf(userInfo.status);
                        fb_id = String.valueOf(userInfo.id);

                        imageURL = userInfo.profileImageUrl.replace("_normal", "");
                        int freindsCount = userInfo.friendsCount;
                        int favouritCount = userInfo.favouritesCount;
                        int followersCount = userInfo.followersCount;
                        StringBuilder sb = new StringBuilder();
                        sb.append(fb_username);
//                        sb.append(username);
                        Prefs.putString("pref", sb.toString());
                        Prefs.putString("pref_image", imageURL);
//                        registerTwitter2(userInfo);
                        TwitterLogin3();
                        startActivity(new Intent(Login_Activity.this, Home.class));
                        Toast.makeText(Login_Activity.this, sb.toString(), Toast.LENGTH_LONG).show();
                        Login_Activity.this.finish();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e("ERRORIN", exception.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    /* class RegisterUser extends AsyncTask<Void, Void, String> {



         @Override
         protected String doInBackground(Void... voids) {
             //creating request handler object
             RequestHandlerAPI requestHandler = new RequestHandlerAPI();
             HashMap<String, String> params = new HashMap<>();
             params.put("username", fb_username);
             params.put("email", fb_email);
             params.put("fb_id", fb_id);

             return requestHandler.sendPostRequest(FB_URL, params);
         }

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             //displaying the progress bar while user registers on the server

         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             //hiding the progressbar after completion
             //converting response to json object
                 *//*JSONObject obj = new JSONObject(s);

                //if no error in response
                if (!obj.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("user");

                    //creating a new user object
                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("username"),
                            userJson.getString("email"),
                            userJson.getString("gender")
                    );

                    //storing the user in shared preferences
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    //starting the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                }*//*
        }
    }*/


  /*  public void registerTwitter2(final User userInfo) {
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user", userInfo.name);
        map.put("email", fb_email.toString());
        map.put("username", fb_username.toString());
        map.put("location", "lahore");
        map.put("lat", "131.33");
        map.put("lng", "131.33");
        map.put("device_type", "Android");
        map.put("device_id", "399338882918392819");
        map.put("time_zone", "4");
        map.put("twitter_id", "cmmfm4mm4m4");
        map.put("pic", "");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Twitter_URL, new JSONObject(map), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response => ", response.toString());
                Log.d("RESPONESUBMIT", "" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERRORSUBMIT", "" + error);
            }
        }) { //Code to send parameters to server

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {

                return super.getBody();
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user", userInfo.name);
                map.put("email", fb_email.toString());
                map.put("username", fb_username.toString());
                map.put("location", "lahore");
                map.put("lat", "131.33");
                map.put("lng", "131.33");
                map.put("device_type", "Android");
                map.put("device_id", "399338882918392819");
                map.put("time_zone", "4");
                map.put("twitter_id", "cmmfm4mm4m4");
                map.put("pic", "");
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("app_key", "MdeDKSXifoYhQZYpEvh+Eol2PvuPWBuL7rVjaHRO7j0=");

                return map;
            }
        };

        queue.add(jsonObjReq);

    }*/


public void TwitterLogin3(){
    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    Map<String,String>params = null;
    CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Twitter_URL, params, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("RESPONESUBMIT", "" + response);

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("ERRORSUBMIT", "" + error);
        }
    }){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", fb_email.toString());
            map.put("username", fb_username.toString());
            map.put("location", "lahore");
            map.put("lat", "131.33");
            map.put("lng", "131.33");
            map.put("device_type", "Android");
            map.put("device_id", "399338882918392819");
            map.put("time_zone", "4");
            map.put("twitter_id", "cmmfm4mm4m4");
            map.put("pic", "jjjjjj.jpg");
            return map;
        }
    };



    requestQueue.add(jsObjRequest);
}
/*    public void registerTwitter() {
        RequestQueue queue = Volley.newRequestQueue(Login_Activity.this);
        StringRequest request = new StringRequest(Request.Method.POST, Twitter_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Login_Activity.this, "Data is submitted" + response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_Activity.this, "Error in submitting" + error, Toast.LENGTH_SHORT).show();
                        Log.d("ERRORSUBMIT", "" + error);
                        error.printStackTrace();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", fb_email.toString());
                map.put("username", fb_username.toString());
                map.put("location", "lahore");
                map.put("lat", "131.33");
                map.put("lng", "131.33");
                map.put("device_type", "Android");
                map.put("device_id", "399338882918392819");
                map.put("time_zone", "4");
                map.put("twitter_id", "cmmfm4mm4m4");
                map.put("pic", "");


                return map;
            }
        };

        queue.add(request);
    }*/
}
