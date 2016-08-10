package com.bignerdranch.android.criminalintent;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by Tomas on 7/31/2016.
 */
public class FormattedDate extends Date {

    public FormattedDate(Date d) {
        super(d.getTime());
    }
    public FormattedDate() {
        super(new Date().getTime());
    }

    @Override
    public String toString() {
        return DateFormat.format("EEE, dd. MMM ''yy. 'at' HH:mm", this).toString();
    }
}
