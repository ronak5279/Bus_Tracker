
package com.example.bus_tracker;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalTime implements Serializable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("value")
    @Expose
    private Integer value;
    private final static long serialVersionUID = -7213075896637467541L;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
