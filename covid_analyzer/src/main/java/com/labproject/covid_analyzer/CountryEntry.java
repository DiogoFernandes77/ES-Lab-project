package com.labproject.covid_analyzer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryEntry {
    private String country;
    private String confirmed;
    private String deaths;
    private String recovered;
    private String active;
    private String date;

    public CountryEntry(){}
    @JsonProperty("Country")
    public String getCountry(){
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country){
        this.country = country;
    }
    @JsonProperty("Confirmed")
    public String getConfirmed(){
        return confirmed;
    }
    
    @JsonProperty("Confirmed")
    public void setConfirmed(String confirmed){
        this.confirmed = confirmed;
    }
    @JsonProperty("Recovered")
    public String getRecovered(){
        return recovered;
    }
    
    @JsonProperty("Recovered")
    public void setRecovered(String recovered){
        this.recovered = recovered;
    }
    @JsonProperty("Deaths")
    public String getDeaths(){
        return deaths;
    }
    
    @JsonProperty("Deaths")
    public void setDeaths(String deaths){
        this.deaths = deaths;
    }
    @JsonProperty("Active")
    public String getActive(){
        return active;
    }
    
    @JsonProperty("Active")
    public void setActive(String active){
        this.active = active;
    }
    @JsonProperty("Date")
    public String getDate(){
        return date;
    }
    
    @JsonProperty("Date")
    public void setDate(String date){
        this.date = date;
    }


    @Override
    public String toString() {
        return "Country{" +
            "country='" + country + '\'' +
            ", confirmed=" + confirmed +
            ", deaths=" + deaths +
            ", recovered=" + recovered +
            ", active=" + active +
            ", date=" + date +
            '}';
    }
}
