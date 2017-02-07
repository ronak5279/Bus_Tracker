package com.example.bus_tracker;

/**
 * Created by ronak on 7/2/17.
 */

public class Conductors {
    @com.google.gson.annotations.SerializedName("id")
    private String lId;

    @com.google.gson.annotations.SerializedName("condId")
    private String CondId;

    @com.google.gson.annotations.SerializedName("condPwd")
    private String CondPwd;


    /**
     * BusItemconstructor
     */
    public Conductors() {

    }

    // @Override
    // public String toString() {return getLocation();}

    public Conductors(String id, String condId, String condPwd){
        this.setCondId(condId);
        this.setCondPwd(condPwd);
        this.setlId(id);


    }

    public String getlId() {
        return lId;
    }
    public void setlId(String id) {
        this.lId = id;
    }

    public String getCondPwd() {
        return CondPwd;
    }
    public final void setCondPwd(String condPwd) {
        CondPwd = condPwd;
    }


    public String getCondId() {
        return CondId;
    }
    public void setCondId(String condId) {
        this.CondId = condId;
    }


}
