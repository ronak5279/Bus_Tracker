
package com.example.bus_tracker;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransitDetails implements Serializable
{

    @SerializedName("arrival_stop")
    @Expose
    private ArrivalStop arrivalStop;
    @SerializedName("arrival_time")
    @Expose
    private ArrivalTime_ arrivalTime;
    @SerializedName("departure_stop")
    @Expose
    private DepartureStop departureStop;
    @SerializedName("departure_time")
    @Expose
    private DepartureTime_ departureTime;
    @SerializedName("headsign")
    @Expose
    private String headsign;
    @SerializedName("line")
    @Expose
    private Line line;
    @SerializedName("num_stops")
    @Expose
    private Integer numStops;
    private final static long serialVersionUID = 3154781142597578270L;

    public ArrivalStop getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(ArrivalStop arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public ArrivalTime_ getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrivalTime_ arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public DepartureStop getDepartureStop() {
        return departureStop;
    }

    public void setDepartureStop(DepartureStop departureStop) {
        this.departureStop = departureStop;
    }

    public DepartureTime_ getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(DepartureTime_ departureTime) {
        this.departureTime = departureTime;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Integer getNumStops() {
        return numStops;
    }

    public void setNumStops(Integer numStops) {
        this.numStops = numStops;
    }

}
