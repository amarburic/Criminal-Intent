package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Tomas on 7/29/2016.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context  mAppContext;

    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context c) {
        mAppContext = c;
        mCrimes = new ArrayList<Crime>();

        for(int i = 0; i < 100; i++) {
            Crime newCrime = new Crime();
            newCrime.setTitle("Crime #" + i);
            newCrime.setSolved(i % 2 == 0);
            mCrimes.add(newCrime);
        }
    }

    public static CrimeLab get(Context c) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for(Crime c : mCrimes)
            if(c.getId().equals(id))
                return c;
        return null;
    }

}
