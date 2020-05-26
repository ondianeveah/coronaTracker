package io.javaBrains.ronatracker.models;

public class LocationStats {

    private String state;
    private String country;
    private String latitude;
    private String longitude;
    private int latestConfirmedCase;
    private int diffFromPreviousDay;
    private int diffFromLastWeek;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestConfirmedCase() {
        return latestConfirmedCase;
    }

    public void setLatestConfirmedCase(int latestConfirmedCase) {
        this.latestConfirmedCase = latestConfirmedCase;
    }

    public int getDiffFromPreviousDay() {
        return diffFromPreviousDay;
    }

    public void setDiffFromPreviousDay(int diffFromPreviousDay) {
        this.diffFromPreviousDay = diffFromPreviousDay;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getDiffFromLastWeek() {
        return diffFromLastWeek;
    }

    public void setDiffFromLastWeek(int diffFromLastWeek) {
        this.diffFromLastWeek = diffFromLastWeek;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latestConfirmedCase=" + latestConfirmedCase +
                ", diffFromPreviousDay=" + diffFromPreviousDay +
                ", diffFromLastWeek=" + diffFromLastWeek +
                '}';
    }
}
