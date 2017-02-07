package com.example.bus_tracker;

/**
 * Created by Harsh on 06-02-2017.
 */

public class Step1 {


    private String busNum;

    private String headSign;


    private String dist;


    private String duration;

    public Step1(String busNum, String end, String dist, String duration) {
        this.busNum = busNum;

        this.headSign = end;
        this.dist = dist;
        this.duration = duration;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum;
    }

    public String getBusNum() {
        return busNum;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getDist() {
        return dist;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setEnd(String end) {
        this.headSign = end;
    }

    public String getEnd() {
        return headSign;
    }




}
