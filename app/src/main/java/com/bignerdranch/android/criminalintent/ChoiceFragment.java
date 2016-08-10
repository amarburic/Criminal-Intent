package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Tomas on 8/10/2016.
 */
public class ChoiceFragment extends DialogFragment {
    public static final String EXTRA_CHOICE =
            "com.bignerdranch.android.criminalintent.choice";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog
                .Builder(getActivity())
                .setTitle(R.string.choice_dialog_title)
                .setItems(R.array.picker_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(getTargetFragment() == null)
                            return;
                        Intent i = new Intent();
                        i.putExtra(EXTRA_CHOICE, which);

                        getTargetFragment()
                                .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                    }

                })
                .create();
    }
}
