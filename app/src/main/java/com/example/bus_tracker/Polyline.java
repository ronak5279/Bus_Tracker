
package com.example.bus_tracker;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyline implements Serializable
{

    @SerializedName("points")
    @Expose
    private String points;
    private final static long serialVersionUID = -4036690885166618143L;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
