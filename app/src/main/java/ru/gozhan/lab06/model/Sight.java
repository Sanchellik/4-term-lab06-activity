package ru.gozhan.lab06.model;

import com.yandex.mapkit.geometry.Point;

public class Sight {

    private String title;
    private String fullDescription;
    private String workHours;
    private Point coordinates;

    private String city;

    public Sight(String title, String fullDescription, Point coordinates) {
        this.title = title;
        this.fullDescription = fullDescription;
        this.coordinates = coordinates;
    }

    public Sight(String title, String fullDescription, Point coordinates, String city) {
        this.title = title;
        this.fullDescription = fullDescription;
        this.coordinates = coordinates;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getWorkHours() {
        return workHours;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
