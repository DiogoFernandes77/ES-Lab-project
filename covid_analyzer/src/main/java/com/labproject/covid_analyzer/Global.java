package com.labproject.covid_analyzer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Global {
    private String confirmed;
    private String deaths;
    private String recovered;
    private String newConfirmed;
    private String newDeaths;
    private String newRecovered;
    private String date;

    public Global(){}
    // @JsonProperty("Country")
    // public String getCountry(){
    //     return country;
    // }

    // @JsonProperty("Country")
    // public void setCountry(String country){
    //     this.country = country;
    // }

    // @JsonProperty("Global")
    // public Dictionary<String,Integer> getGlobal(){
    //     return global;
    // }

    // @JsonProperty("Global")
    // public void setCountry(Dictionary<String,Integer> global){
    //     this.global = global;
    // }


    @JsonProperty("TotalConfirmed")
    public String getConfirmed(){
        return confirmed;
    }
    
    @JsonProperty("TotalConfirmed")
    public void setConfirmed(String confirmed){
        this.confirmed = confirmed;
    }
    @JsonProperty("TotalRecovered")
    public String getRecovered(){
        return recovered;
    }
    
    @JsonProperty("TotalRecovered")
    public void setRecovered(String recovered){
        this.recovered = recovered;
    }
    @JsonProperty("TotalDeaths")
    public String getDeaths(){
        return deaths;
    }
    
    @JsonProperty("TotalDeaths")
    public void setDeaths(String deaths){
        this.deaths = deaths;
    }

    @JsonProperty("NewConfirmed")
    public String getNewConfirmed(){
        return newConfirmed;
    }
    
    @JsonProperty("NewConfirmed")
    public void setNewConfirmed(String newConfirmed){
        this.newConfirmed = newConfirmed;
    }
    @JsonProperty("NewRecovered")
    public String getNewRecovered(){
        return newRecovered;
    }
    
    @JsonProperty("NewRecovered")
    public void setNewRecovered(String newRecovered){
        this.newRecovered = newRecovered;
    }
    @JsonProperty("NewDeaths")
    public String getNewDeaths(){
        return newDeaths;
    }
    
    @JsonProperty("NewDeaths")
    public void setNewDeaths(String newDeaths){
        this.newDeaths = newDeaths;
    }


    // @JsonProperty("Active")
    // public String getActive(){
    //     return active;
    // }
    
    // @JsonProperty("Active")
    // public void setActive(String active){
    //     this.active = active;
    // }
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
        return "Global{" +
            // "country='" + country + '\'' +
            ", TotalConfirmed=" + confirmed +
            ", TotalDeaths=" + deaths +
            ", TotalRecovered=" + recovered +
            ", NewConfirmed=" + newConfirmed +
            ", NewDeaths=" + newDeaths +
            ", NewRecovered=" + newRecovered +
            // ", active=" + active +
            ", date=" + date +
            '}';
    }
}
