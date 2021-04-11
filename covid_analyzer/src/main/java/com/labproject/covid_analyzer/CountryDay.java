package com.labproject.covid_analyzer;
import java.util.*;

public class CountryDay {
    private Date day;
    private int newDayCases;
    private int newDayDeaths;
    private int newDayRecovered;
    private int newDayActive;
    private String country;

    public CountryDay(String country,int cases, int deaths, int recov, Date day){
        this.country = country;
        this.day= day;
        this.newDayCases = cases;
        this.newDayDeaths = deaths;
        this.newDayRecovered = recov;
        this.newDayActive = 0;
    }
    
    public CountryDay(String country, int cases, int deaths, int recov, int active, Date day){
        this.country = country;
        this.day= day;
        this.newDayCases = cases;
        this.newDayDeaths = deaths;
        this.newDayRecovered = recov;
        this.newDayActive = active;
    }



    public String getCountry() {
        return country;
    }

    public void setDay(String country) {
        this.country = country;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getNewDayCases() {
        return newDayCases;
    }

    public void setNewDayCases(int newDayCases) {
        this.newDayCases = newDayCases;
    }

    public int getNewDayDeaths() {
        return newDayDeaths;
    }

    public void setNewDayDeaths(int newDayDeaths) {
        this.newDayDeaths = newDayDeaths;
    }

    public int getNewDayRecovered() {
        return newDayRecovered;
    }

    public void setNewDayRecovered(int newDayRecovered) {
        this.newDayRecovered = newDayRecovered;
    }

    public int getNewDayActive() {
        return newDayActive;
    }

    public void setNewDayActive(int newDayActive) {
        this.newDayActive = newDayActive;
    }

    @Override
    public String toString() {
        return "CountryDay{" +
                "day=" + day +
                ", newDayCases=" + newDayCases +
                ", newDayDeaths=" + newDayDeaths +
                ", newDayRecovered=" + newDayRecovered +
                ", newDayActive=" + newDayActive +
                ", country='" + country + '\'' +
                '}';
    }

}
