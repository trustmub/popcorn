package com.example.hp.login;

/**
 * Created by Pato on 17/06/2018.
 */

public class MoviesPOJO {

    private int id;
    private String movieTitle, dimension, screen,days, monday, tuesday,
            wednesday, thurday, fridays, saturday, sunday, startDate, endDate,
            createdAt, updatedAt, status, unitPrice, eventDate;

    public MoviesPOJO(int id, String movieTitle, String dimension, String screen, String days, String monday, String tuesday, String wednesday, String thurday, String fridays, String saturday, String sunday, String startDate,
                      String endDate, String createdAt, String updatedAt, String status, String unitPrice, String eventDate) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.dimension = dimension;
        this.screen = screen;
        this.days = days;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thurday = thurday;
        this.fridays = fridays;
        this.saturday = saturday;
        this.sunday = sunday;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.unitPrice = unitPrice;
        this.eventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getDimension() {
        return dimension;
    }

    public String getScreen() {
        return screen;
    }

    public String getDays() {
        return days;
    }

    public String getMonday() {
        return monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public String getThurday() {
        return thurday;
    }

    public String getFridays() {
        return fridays;
    }

    public String getSaturday() {
        return saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getEventDate() {
        return eventDate;
    }
}
