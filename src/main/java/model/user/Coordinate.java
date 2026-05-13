package model.user;

public class Coordinate {

    private double lat,lng;

    public Coordinate() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Coordinate(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
