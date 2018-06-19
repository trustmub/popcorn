package com.example.hp.login.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.hp.login.Fragments.ItemOneFragment;
import com.example.hp.login.Fragments.ItemThreeFragment;
import com.example.hp.login.Fragments.ItemTwoFragment;
import com.example.hp.login.MoviesPOJO;
import com.example.hp.login.QueryUtils;
import com.example.hp.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<MoviesPOJO> moviesArrayObjects;
    private String fetchedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        /**
         * run the async task as a new object in on create so that i fetches new data on orientation change
         * NOTE: you can use ViewModels from the Android Architecture Components to handle the complexities of
         * Activity Lifecycle
         */
        new JsonAsyncTask().execute();

//        ArrayList<MoviesPOJO> fetchedData = QueryUtils.extractFeatureFromJson();


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ItemOneFragment(), "Now Showing");
        adapter.addFragment(new ItemTwoFragment(), "Coming Soon");
        adapter.addFragment(new ItemThreeFragment(), "ShowTime");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    /**
     * an inner class JsonAsyncTask which accepts
     */
    @SuppressLint("StaticFieldLeak")
    class JsonAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            fetchedData = QueryUtils.makeHttpRequest();
            return fetchedData;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            moviesArrayObjects = null;

            if (fetchedData != null) { // change you UYI elements with the fetched data
                Log.d("Fetched", fetchedData);
                // below code extract from
                try {
                    JSONArray movieObjects = new JSONArray(fetchedData);
                    for (int i = 0; i < movieObjects.length(); i++) {
                        // do what you have to do by access each element of the array populating a view etc

                        JSONObject movie = movieObjects.getJSONObject(i);

                        int id = movie.getInt("id");
                        String movie_title = movie.getString("movie_title");
                        String dimension = movie.getString("dimension");
                        String screen = movie.getString("screen");
                        String days = movie.getString("days");
                        String monday = movie.getString("monday");
                        String tuesday = movie.getString("tuesday");
                        String wednesday = movie.getString("wednesday");
                        String thursday = movie.getString("thursday");
                        String friday = movie.getString("friday");
                        String saturday = movie.getString("saturday");
                        String sunday = movie.getString("sunday");
                        String start_date = movie.getString("start_date");
                        String end_date = movie.getString("end_date");
                        String created_at = movie.getString("created_at");
                        String updated_at = movie.getString("updated_at");
                        String status = movie.getString("status");
                        String unit_price = movie.getString("unit_price");
                        String event_date = movie.getString("event_date");

                        // adding each record in the movies array objec maped to the MoviesPOJO

                        moviesArrayObjects.add(new MoviesPOJO(id, movie_title, dimension, screen,
                                days, monday, tuesday, wednesday, thursday, friday, saturday, sunday,
                                start_date, end_date, created_at, updated_at, status, unit_price, event_date));

                        Log.d("Fetched_pojo", movie_title);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}