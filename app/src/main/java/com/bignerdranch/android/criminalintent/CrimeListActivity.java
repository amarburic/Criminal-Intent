package com.bignerdranch.android.criminalintent;

import android.app.Fragment;

/**
 * Created by Tomas on 7/29/2016.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
