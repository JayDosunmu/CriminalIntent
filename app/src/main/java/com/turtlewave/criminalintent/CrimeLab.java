package com.turtlewave.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by TJsMac on 3/30/18.
 */

public class CrimeLab {

    private static CrimeLab crimeLab;

    private List<Crime> crimes;
    private HashMap<UUID, Crime> crimeMap;

    public static CrimeLab get(Context context) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    private CrimeLab(Context context) {
        crimes = new ArrayList<>();
        crimeMap = new HashMap<>();
        for (int i=0; i<100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i % 2 == 0);
            crimes.add(crime);
            crimeMap.put(crime.getId(), crime);
        }

    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id) {
        if (crimeMap.containsKey(id)) {
            return crimeMap.get(id);
        }
        return null;
    }

}
