
package com.example.bus_tracker;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalStop implements Serializable
{

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -316725799647468874L;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
