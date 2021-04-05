package com.labproject.covid_analyzer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {
    private String ID;
    private String message;
    private Global global;
    private CountryEntry[] countries;

    public Summary(){}

    @JsonProperty("ID")
    public String getID(){
        return ID;
    }
    
    @JsonProperty("ID")
    public void setID(String ID){
        this.ID = ID;
    }

    @JsonProperty("Message")
    public String getMessage(){
        return message;
    }
    
    @JsonProperty("Message")
    public void setMessage(String message){
        this.message = message;
    }

    @JsonProperty("Global")
    public Global getGlobal(){
        return global;
    }
    
    @JsonProperty("Global")
    public void setGlobal(Global global){
        this.global = global;
    }

    @JsonProperty("Countries")
    public CountryEntry[] getCountries(){
        return countries;
    }
    
    @JsonProperty("Countries")
    public void setCountries(CountryEntry[] confirmed){
        this.countries = countries;
    }
    
}
