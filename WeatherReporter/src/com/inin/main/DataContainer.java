package com.inin.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokesh on 28/3/16.
 */
public class DataContainer implements Serializable{
    int temperature;
    int year;
    List<Integer> stationList = new ArrayList<>();

    public DataContainer(int temperature, int year, int station) {
        this.temperature = temperature;
        this.year = year;
        this.stationList.add(station);
    }

    public String toString() {
        return "Year: " + this.year + " Max Temperature:" + this.temperature + " Stations: " + this.stationList;
    }
}
