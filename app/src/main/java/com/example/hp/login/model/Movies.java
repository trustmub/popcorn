//package com.example.hp.login.model;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
///**
// * Created by Pato on 16/06/2018.
// */
//
//public class Movies implements Parcelable {
//
//    private String movie_title;
//    private String dimension;
//    private String screen;
//    private String days;
//    private String monday;
//    private String tuesday;
//    private String wednesday;
//    private String thursday;
//    private String friday;
//    private String saturday;
//    private String sunday;
//    private String start_date;
//    private String end_date;
//    private String created_at;
//    private String updated_at;
//    private String status;
//    private String unit_price;
//    private String event_date;
//
//
//
//    private static final String URL_TO_MOVIES =
//            "//http://movies.safiriticketing.com/api/movie_events";
//
//    public Movies (String movie_title, String dimension, String screen
//            , String days, String start_date, String end_date, String status, String unit_price, String event_date) {
//        this.movie_title = movie_title;
//        this.dimension = dimension;
//        this.screen = screen;
//        this.days = days;
//        this.start_date = start_date;
//        this.end_date = end_date;
//        this.status = status;
//        this.unit_price = unit_price;
//        this.event_date = event_date;
//
//
//    }
//
//    private Movies (Parcel in) {
//        movie_title = in.readString();
//        dimension = in.readString();
//        screen = in.readString();
//        days = in.readString();
//        start_date = in.readString();
//        end_date = in.readString();
//        status = in.readString();
//        unit_price = in.readString();
//        event_date = in.readString();
//
//    }
//
//    public String getMovie_title() {
//        return movie_title;
//    }
//
//    public String getDimension() {
//        return dimension;
//    }
//
//    public String getScreen() {
//        return screen;
//    }
//
//    public String getDays() {
//        return days;
//    }
//
//    public String getStart_date() {
//        return start_date;
//    }
//
//    public String getEnd_date() {
//        return end_date;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public String getUnit_price() {
//        return unit_price;
//    }
//
//    public String getEvent_date() {
//        return event_date;
//    }
//
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(movie_title);
//        dest.writeString(dimension);
//        dest.writeString(screen);
//        dest.writeString(days);
//        dest.writeString(start_date);
//        dest.writeString(end_date);
//        dest.writeString(status);
//        dest.writeString(unit_price);
//        dest.writeString(event_date);
//
//
//    }
//
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Movies createFromParcel(Parcel in) {
//            return new Movies(in);
//        }
//
//        public Movies[] newArray(int size) {
//            return new Movies[size];
//        }
//    };
//}
