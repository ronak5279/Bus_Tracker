
package com.example.bus_tracker;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureStop implements Serializable
{

    @SerializedName("location")
    @Expose
    private Location_ location;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 6093081187093518752L;

    public Location_ getLocation() {
        return location;
    }

    public void setLocation(Location_ location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
