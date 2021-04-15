package com.labproject.covid_analyzer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class World {
    @Id @GeneratedValue long id;

  
    
    
    private String date;
    
    private int confirmed;
    
    private int deaths;
    
    private int recovered;
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    public int getConfirmed(){
        return confirmed;
    }
    
    
    public void setConfirmed(int confirmed){
        this.confirmed = confirmed;
    }
    
    public int getRecovered(){
        return recovered;
    }
    
    
    public void setRecovered(int recovered){
        this.recovered = recovered;
    }
    
    public int getDeaths(){
        return deaths;
    }
    
    
    public void setDeaths(int deaths){
        this.deaths = deaths;
    }
}