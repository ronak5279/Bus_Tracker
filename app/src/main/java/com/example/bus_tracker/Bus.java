package com.example.bus_tracker;

/**
 * Created by Harsh on 06-02-2017.
 */

public class Bus {

    @com.google.gson.annotations.SerializedName("id")
    private String Id;

    @com.google.gson.annotations.SerializedName("condId")
    private String CondId;

    @com.google.gson.annotations.SerializedName("busnum")
    private String BusNum;

    @com.google.gson.annotations.SerializedName("lat")
    private double Lat;

    @com.google.gson.annotations.SerializedName("lng")
    private double Lng;

    @com.google.gson.annotations.SerializedName("status")
    private String Status;

    /**
     * BusItemconstructor
     */
    public Bus() {

    }

   // @Override
   // public String toString() {return getLocation();}

    public Bus(String id, String condId, String busnum, double lat, double lng, String status){
        this.setCondId(condId);
        this.setBusNum(busnum);
        this.setLat(lat);
        this.setLng(lng);
        this.setStatus(status);
        this.setId(id);


    }

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        this.Id = id;
    }

    public String getBusNum() {
        return BusNum;
    }
    public final void setBusNum(String busNum) {
        BusNum = busNum;
    }


    public String getCondId() {
        return CondId;
    }
    public void setCondId(String condId) {
        this.CondId = condId;
    }

    public double getLng() {
        return Lng;
    }
    public void setLng(double lng) {
        Lng = lng;
    }

    public double getLat() {
        return Lat;
    }
    public void setLat(double lat) {
        Lat = lat;
    }

    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        this.Status = status;
    }


}
