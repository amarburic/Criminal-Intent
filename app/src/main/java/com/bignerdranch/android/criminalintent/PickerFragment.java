package com.bignerdranch.android.criminalintent;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Tomas on 7/31/2016.
 */
public class PickerFragment extends DialogFragment {
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";
    public static final String EXTRA_TYPE =
            "com.bignerdranch.android.criminalintent.type";

    public static final int TYPE_DATE = 0;
    public static final int TYPE_TIME = 1;

    private Date mDate;
    private int mType;

    private void sendResult(int resultCode) {
        if(getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    public static PickerFragment newInstance(Date date, int mType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DATE, date);
        bundle.putInt(EXTRA_TYPE, mType);

        PickerFragment pickerFragment = new PickerFragment();
        pickerFragment.setArguments(bundle);

        return pickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        mType = getArguments().getInt(EXTRA_TYPE, 0);

        View v = getActivity()
                .getLayoutInflater()
                .inflate(getLayoutResource(), null);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        if(mType == TYPE_DATE) {
            DatePicker datePicker = (DatePicker) v.findViewById(getViewId());
            datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth, hour, minute).getTime();
                    getArguments().putSerializable(EXTRA_DATE, mDate);
                }
            });
        }

        if(mType == TYPE_TIME) {
            TimePicker timePicker = (TimePicker) v.findViewById(getViewId());
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    mDate = new GregorianCalendar(year, month, day, hourOfDay, minute).getTime();
                    getArguments().putSerializable(EXTRA_DATE, mDate);
                }
            });
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(getTitle())
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }

    private int getLayoutResource() {
        if(mType == TYPE_DATE)
            return R.layout.dialog_date;
        if(mType == TYPE_TIME)
            return R.layout.dialog_time;   
        return 0;
    }   
    
    private int getTitle() {
        if(mType == TYPE_DATE)
            return R.string.date_picker_title;
        if(mType == TYPE_TIME)
            return R.string.time_picker_title;
        return 0;
    }
    
    private int getViewId() {
        if(mType == TYPE_DATE)
            return R.id.dialog_date_picker;
        if(mType == TYPE_TIME)
            return R.id.dialog_time_picker;
        return 0;
    }
}
