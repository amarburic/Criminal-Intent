package com.bignerdranch.android.criminalintent;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tomas on 7/28/2016.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Date getDate() {
        return mDate;
    }

    public String getDateAsString() {
        return DateFormat.format("EEEE, MMM dd, yyyy", mDate).toString();
    }

    public void setDate(Date date) {
        mDate = new FormattedDate(date);
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new FormattedDate();
    }
}
