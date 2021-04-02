package com.labproject.covid_analyzer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Country{

    private String country;
    private String iso2;


    public Country(){

    }
    @JsonProperty("Country")
    public String getCountry(){
        return country;
    }
    @JsonProperty("ISO2")
    public String getIso2(){
        return iso2;
    }
    @JsonProperty("Country")
    public void setCountry(String country){
        this.country = country;
    }
    @JsonProperty("ISO2")
    public void setIso2(String ISO2){
        this.iso2 = ISO2;
    }

    @Override
    public String toString() {
        return "Country{" +
            "country='" + country + '\'' +
            ", ISO2=" + iso2 +
            '}';
    }

}