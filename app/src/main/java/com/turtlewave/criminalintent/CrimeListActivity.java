package com.turtlewave.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by TJsMac on 3/30/18.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
