package id.ac.umn.week13_29240;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MarkerAndShape {
    private String type = "Marker";
    private String desc = "";
    private double radius = 0.0;
    private List<LatLng> points = new ArrayList<LatLng>();
    private Object mapObj = null;

    public MarkerAndShape(String type, String desc, double rad, List<LatLng> points){
        this.type = type;
        this.desc = desc;
        this.radius = rad;
        this.points = new ArrayList<LatLng>();
        this.points.addAll(points);
    }

    public String getType(){
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public List<LatLng> getPoints() {
        return points;
    }

    public void setPoints(List<LatLng> points) {
        this.points = points;
    }

    public Object getMapObj() {
        return mapObj;
    }

    public void setMapObj(Object mapObj) {
        this.mapObj = mapObj;
    }

    public int getPointsCount(){
        return points.size();
    }
}


