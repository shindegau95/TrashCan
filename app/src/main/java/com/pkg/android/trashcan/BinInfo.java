package com.pkg.android.trashcan;

/**
 * Created by GAURAV on 29-03-2017.
 */

public class BinInfo {
    private String bin_id;
    private String location;
    private String datetime;
    private String level;
    private String measurement_id;

    public BinInfo(String bin_id, String location, String datetime, String level, String measurement_id) {
        this.bin_id = bin_id;
        this.location = location;
        this.datetime = datetime;
        this.level = level;
        this.measurement_id = measurement_id;
    }

    @Override
    public String toString() {
        return "BinInfo{" +
                "bin_id='" + bin_id + '\'' +
                ", location='" + location + '\'' +
                ", datetime='" + datetime + '\'' +
                ", level='" + level + '\'' +
                ", measurement_id='" + measurement_id + '\'' +
                '}';
    }

    public String getBin_id() {
        return bin_id;
    }

    public void setBin_id(String bin_id) {
        this.bin_id = bin_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(String measurement_id) {
        this.measurement_id = measurement_id;
    }
}
