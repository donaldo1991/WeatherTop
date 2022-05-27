package Utils;

import models.Stations;
import play.Logger;

public class HelperClass {
    public static Stations setStation(Stations station) {
        station.currentWeather = WeatherAnalytics.getCurrentWeather(station);
        station.currentWeatherIcon = WeatherAnalytics.getCurrentWeatherIcon(station.currentWeather);
        Logger.info ("current weather icon is: " + station.currentWeatherIcon);
        station.currentTempInC = WeatherAnalytics.getCurrentTemp(station);
        station.maxTemp = WeatherAnalytics.getReadingWithMaxTemp(station);
        station.minTemp = WeatherAnalytics.getReadingWithMinTemp(station);
        station.currentTempInF = (station.currentTempInC * 1.8) + 32;
        station.currentTempTrend = WeatherAnalytics.getCurrentTempTrend(station);
        station.currentWindSpeed = WeatherAnalytics.getCurrentWindSpeed(station);
        station.maxWind = WeatherAnalytics.getReadingWithMaxWind(station);;
        station.minWind = WeatherAnalytics.getReadingWithMinWind(station);;
        station.currentWindDirection = WeatherAnalytics.getCurrentWindDirection(station);
        double windChillCalculation = 0;
        if (station.readings.size() - 1 > 0){
            windChillCalculation = 13.12 + (0.6215*station.currentTempInC)
                    - (11.37*(Math.pow(station.readings.get(station.readings.size()-1).Wind_Speed,0.16)))
                    + ((0.3965*station.currentTempInC)*(Math.pow(station.readings.get(station.readings.size()-1).Wind_Speed,0.16)));
        }
        station.currentWindChill = Math.round(windChillCalculation*100.0)/100.0;
        station.currentWindTrend = WeatherAnalytics.getCurrentWindTrend(station);
        station.currentPressure = WeatherAnalytics.getCurrentPressure(station);
        station.maxPressure = WeatherAnalytics.getReadingWithMaxPressure(station);
        station.minPressure = WeatherAnalytics.getReadingWithMinPressure(station);
        station.currentPressureTrend  = WeatherAnalytics.getCurrentPressureTrend(station);;
        return station;
    }
}