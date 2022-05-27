package Utils;

import models.Stations;

import java.util.Comparator;

public class SortByLocation implements Comparator<Stations> {
    public int compare(Stations a, Stations b) {
        return a.location.compareTo(b.location);
    }
}

