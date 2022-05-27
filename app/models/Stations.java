package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Stations extends Model
{
    public String location;
    public double latitude;
    public double longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Readings> readings = new ArrayList<Readings>();

    public String currentWeather;
    public String currentWeatherIcon;
    public float currentTempInC;
    public float maxTemp;
    public float minTemp;
    public double currentTempInF;
    public String currentTempTrend;
    public int currentWindSpeed;
    public float maxWind;
    public float minWind;
    public String currentWindDirection;
    public double currentWindChill;
    public String currentWindTrend;
    public float currentPressure;
    public int maxPressure;
    public int minPressure;
    public String currentPressureTrend;

    public Stations(String location, double latitude, double longitude)
    {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Stations(String location)
    {
        this.location = location;
    }
}