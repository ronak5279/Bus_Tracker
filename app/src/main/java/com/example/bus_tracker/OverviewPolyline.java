
package com.example.bus_tracker;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverviewPolyline implements Serializable
{

    @SerializedName("points")
    @Expose
    private String points;
    private final static long serialVersionUID = -8569390283404364288L;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
