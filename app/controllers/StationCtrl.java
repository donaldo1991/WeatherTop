package controllers;

import java.util.Date;
import java.util.List;

import Utils.HelperClass;
import Utils.WeatherAnalytics;
import models.Stations;
import models.Readings;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Stations station = Stations.findById(id);
        Logger.info ("Station id = " + id);
        Stations stationToDisplay = HelperClass.setStation(station);
        render("stations.html", stationToDisplay);
    }

    public static void index2(Long id)
    {
        Stations station = Stations.findById(id);
        Logger.info ("Station id = " + id);
        Stations stationToDisplay = HelperClass.setStation(station);
        render("stations.html", stationToDisplay);
    }

    public static void deleteReading (Long id, Long readingid)
    {
        Stations station = Stations.findById(id);
        Readings reading = Readings.findById(readingid);
        Logger.info ("Removing" + reading.id);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect ("/stations/" + id);
    }

    public static void addReading(Long id, String code, float temp, int wind_speed, float wind_direction, int pressure)
    {
        Date date = new Date();
        Readings reading = new Readings(date, code, temp, wind_speed, wind_direction, pressure);
        Stations station = Stations.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/stations/" + id);
    }
}