package com.example.hp.login;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static com.example.hp.login.URLs.URL_TO_MOVIES;


/**
 * Created by Pato on 17/06/2018.
 */

public class QueryUtils {


    private static URL createUrl() throws MalformedURLException {
        Log.d("QueryUtils_createUrl", URL_TO_MOVIES);

        return new URL(URL_TO_MOVIES);
    }

    public static String makeHttpRequest() {
        String jsonResponse = "";
        URL url = null;

        try {
            url = createUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {

        } finally {
            if (urlConnection == null) {
                urlConnection.disconnect();

                if (inputStream == null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    public static ArrayList<MoviesPOJO> extractFeatureFromJson() {
        String moviesJSON = null;
        moviesJSON = makeHttpRequest();
        Log.d("QueryUtils_makeHTTP", moviesJSON);

        if (TextUtils.isEmpty(moviesJSON)) {
            return null;
        }
        ArrayList<MoviesPOJO> moviesArray = new ArrayList<>();

        try {

            JSONArray root = new JSONArray();

            for (int i = 0; i < moviesArray.size(); i++) {
                JSONObject movies = root.getJSONObject(i);

                int id = movies.getInt("id");
                String movie_title = movies.getString("movie_title");
                String dimension = movies.getString("dimension");
                String screen = movies.getString("screen");
                String days = movies.getString("days");
                String monday = movies.getString("monday");
                String tuesday = movies.getString("tuesday");
                String wednesday = movies.getString("wednesday");
                String thursday = movies.getString("thursday");
                String friday = movies.getString("friday");
                String saturday = movies.getString("saturday");
                String sunday = movies.getString("sunday");
                String start_date = movies.getString("start_date");
                String end_date = movies.getString("end_date");
                String created_at = movies.getString("created_at");
                String updated_at = movies.getString("updated_at");
                String status = movies.getString("status");
                String unit_price = movies.getString("unit_price");
                String event_date = movies.getString("event_date");


            }

        } catch (JSONException e) {
            Log.e("Query utils", "Problem parsing the JSON request report", e);

        }
        return moviesArray;
    }

}
