package controllers;

import Utils.HelperClass;
import Utils.SortByLocation;
import models.Member;
import models.Stations;
import models.Readings;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dashboard extends Controller
{
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Stations> stations = member.stations;
        List<Stations> stationsToDisplay = new ArrayList<Stations>();
        for(Stations station: stations){
            Stations stationToDisplay = HelperClass.setStation(station);
            stationsToDisplay.add(stationToDisplay);
        }
        stationsToDisplay.sort(new SortByLocation());
        render ("dashboard.html", stationsToDisplay);
    }

    public static void deleteStation (Long id)
    {
        Logger.info("Deleting a Station");
        Member member = Accounts.getLoggedInMember();
        Stations station = Stations.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect ("/dashboard");
    }

    public static void addStation (String station_name, double latitude, double longitude)
    {
        Logger.info ("Adding a new station called " + station_name);
        Member member = Accounts.getLoggedInMember();
        Stations station = new Stations (station_name,latitude,longitude);
        member.stations.add(station);
        member.save();
        redirect ("/dashboard");
    }
}
