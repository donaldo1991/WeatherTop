package Utils;

import java.util.*;

import models.Readings;
import models.Stations;

public class WeatherAnalytics {
    public static String getCurrentWeather(Stations station) {

        HashMap<String, String> responseMap = new HashMap<String, String>();

        responseMap.put("100", "Clear");
        responseMap.put("200", "Partial Clouds");
        responseMap.put("300", "Cloudy");
        responseMap.put("400", "Light Showers");
        responseMap.put("500", "Heavy Showers");
        responseMap.put("600", "Rain");
        responseMap.put("700", "Snow");
        responseMap.put("800", "Thunder");

        String var = "";
        if (station.readings.size() > 0) {
            Readings reading = station.readings.get(station.readings.size() - 1);
            String latestCode = reading.Code;
            var = responseMap.get(latestCode);
                if (var == null) {
                    return "Incorrect code entered";
                }
        }
        return var;
    }


    public static String getCurrentWeatherIcon(String currentWeather) {

        HashMap<String,String> responseMap = new HashMap<String, String>();

        responseMap.put("Clear","yellow sun icon");
        responseMap.put("Partial Clouds","orange cloud sun icon");
        responseMap.put("Cloudy","grey cloud icon");
        responseMap.put("Light Showers","olive cloud sun rain icon");
        responseMap.put("Heavy Showers","green cloud rain icon");
        responseMap.put("Rain","blue cloud showers heavy icon");
        responseMap.put("Snow","teal snowflake icon");
        responseMap.put("Thunder","black bolt icon");

        if (currentWeather == "Incorrect code entered") {
            return "asterisk icon";
        }
        return responseMap.get(currentWeather);
    }

    public static float getCurrentTemp(Stations station) {

        if (station.readings.size() > 0) {
            Readings reading = station.readings.get(station.readings.size() - 1);
            return reading.Temp;
        }
        return 0;
    }

    public static int getCurrentWindSpeed(Stations station) {

        float currentWindSpeed = 0;

        NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(0, 0);    // 0..1    => 0
        map.put(1, 1);    // 1..5    => 1
        map.put(6, 2);    // 6..11    => 2
        map.put(12, 3);   // 12..19  => 3
        map.put(20, 4);   // 20..28  => 4
        map.put(29, 5);   // 29..38  => 5
        map.put(39, 6);   // 39..49  => 6
        map.put(50, 7);   // 50..61  => 7
        map.put(62, 8);   // 62..74  => 8
        map.put(75, 9);   // 75..88  => 9
        map.put(89, 10);   // 89..102  => 10
        map.put(103, 11);   // 103..117  => 11
        map.put(118, null);   // >117  => null

        if (station.readings.size() > 0) {
            Readings reading = station.readings.get(station.readings.size() - 1);
            currentWindSpeed = reading.Wind_Speed;

            if (currentWindSpeed < 0 || currentWindSpeed > 117) {
                return 0; // out of range
            } else {
                return map.floorEntry((int) currentWindSpeed).getValue();
            }
        }
        return 0;
    }

    public static String getCurrentWindDirection(Stations station) {

        double currentWindDirection = 0;
        NavigableMap<Double, String> map = new TreeMap<Double, String>();

        map.put(0.00, "North");
        map.put(11.25, "North North East");
        map.put(33.75, "North East");
        map.put(56.25, "East North East");
        map.put(78.75, "East");
        map.put(101.25, "East South East");
        map.put(123.75, "South East");
        map.put(146.25, "South South East");
        map.put(168.75, "South");
        map.put(191.25, "South South West");
        map.put(213.75, "South West");
        map.put(236.25, "West South West");
        map.put(258.75, "West");
        map.put(281.25, "West North West");
        map.put(303.75, "North West");
        map.put(326.25, "North North West");
        map.put(348.75, "North");

        if (station.readings.size() > 0) {
            Readings reading = station.readings.get(station.readings.size() - 1);
            currentWindDirection = reading.Wind_Direction;

            if (currentWindDirection < 0 || currentWindDirection > 360) {
                return ""; // out of range
            } else {
                return map.floorEntry(currentWindDirection).getValue();
            }
        }
        return "";
    }

    public static float getCurrentPressure(Stations station) {

        if (station.readings.size()  > 0) {
            Readings reading = station.readings.get(station.readings.size() - 1);
            return reading.Pressure;
        }
        return 0;
    }

    public static float getReadingWithMaxTemp(Stations station) {

        if (station.readings.size()  > 0) {
            Readings readingWithMaxTemp = Collections.max(station.readings, Comparator.comparing(reading -> reading.Temp));
            return readingWithMaxTemp.Temp;
        }
        return 0;
    }

    public static float getReadingWithMinTemp(Stations station) {

        if (station.readings.size()  > 0) {
            Readings readingWithMinTemp = Collections.min(station.readings, Comparator.comparing(reading -> reading.Temp));
            return readingWithMinTemp.Temp;
        }
        return 0;
    }

    public static float getReadingWithMaxWind(Stations station) {

        if (station.readings.size()  > 0) {
            Readings readingWithMaxWind = Collections.max(station.readings, Comparator.comparing(reading -> reading.Wind_Speed));
            return readingWithMaxWind.Wind_Speed;
        }
        return 0;
    }

    public static float getReadingWithMinWind(Stations station) {

        if (station.readings.size()  > 0) {
            Readings readingWithMinWind = Collections.min(station.readings, Comparator.comparing(reading -> reading.Wind_Speed));
            return readingWithMinWind.Wind_Speed;
        }
        return 0;
    }

    public static int getReadingWithMaxPressure(Stations station) {

        if (station.readings.size()  > 0) {
            Readings readingWithMaxPressure = Collections.max(station.readings, Comparator.comparing(reading -> reading.Pressure));
            return readingWithMaxPressure.Pressure;
        }
        return 0;
    }

    public static int getReadingWithMinPressure(Stations station) {

        if (station.readings.size()  > 0) {
            Readings readingWithMinPressure = Collections.min(station.readings, Comparator.comparing(reading -> reading.Pressure));
            return readingWithMinPressure.Pressure;
        }
        return 0;
    }

    public static String getCurrentTempTrend(Stations station) {
        ArrayList<Float> currentReadings = new ArrayList<Float>();
        if (station.readings.size()  < 3) {
            return "teal arrows alternate horizontal icon";
        }
        else{
            for (Readings reading : station.readings) {
                currentReadings.add(reading.Temp);
            }
            if ((currentReadings.get(currentReadings.size()-1)) > (currentReadings.get(currentReadings.size()-2))
                    && (currentReadings.get(currentReadings.size() - 2)) > (currentReadings.get(currentReadings.size() - 3))){
                return "red arrow circle up icon";
            } else if ((currentReadings.get(currentReadings.size()-1)) < (currentReadings.get(currentReadings.size()-2))
                    && (currentReadings.get(currentReadings.size() - 2)) < (currentReadings.get(currentReadings.size() - 3))){
                return "green arrow circle down icon";
            } else {
                return "teal arrows alternate horizontal icon";
            }
        }
    }

    public static String getCurrentWindTrend(Stations station) {
        ArrayList<Float> currentReadings = new ArrayList<Float>();
        if (station.readings.size()  < 3) {
            return "teal arrows alternate horizontal icon";
        }
        else{
            for (Readings reading : station.readings) {
                currentReadings.add(reading.Wind_Speed);
            }
                if ((currentReadings.get(currentReadings.size()-1)) > (currentReadings.get(currentReadings.size()-2))
                        && (currentReadings.get(currentReadings.size() - 2)) > (currentReadings.get(currentReadings.size() - 3))){
                    return "red arrow circle up icon";
                } else if ((currentReadings.get(currentReadings.size()-1)) < (currentReadings.get(currentReadings.size()-2))
                        && (currentReadings.get(currentReadings.size() - 2)) < (currentReadings.get(currentReadings.size() - 3))){
                    return "green arrow circle down icon";
                } else {
                    return "teal arrows alternate horizontal icon";
                }
            }
    }

    public static String getCurrentPressureTrend(Stations station) {
        ArrayList<Integer> currentReadings = new ArrayList<Integer>();
        if (station.readings.size()  < 3) {
            return "teal arrows alternate horizontal icon";
        }
        else{
            for (Readings reading : station.readings) {
                currentReadings.add(reading.Pressure);
            }
            if ((currentReadings.get(currentReadings.size()-1)) > (currentReadings.get(currentReadings.size()-2))
                    && (currentReadings.get(currentReadings.size() - 2)) > (currentReadings.get(currentReadings.size() - 3))){
                return "red arrow circle up icon";
            } else if ((currentReadings.get(currentReadings.size()-1)) < (currentReadings.get(currentReadings.size()-2))
                    && (currentReadings.get(currentReadings.size() - 2)) < (currentReadings.get(currentReadings.size() - 3))){
                return "green arrow circle down icon";
            } else {
                return "teal arrows alternate horizontal icon";
            }
        }
    }
}
