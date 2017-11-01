package edu.cvtc.android.registerbalance;

import android.widget.Toast;

/**
 * Created by Beast on 12/29/16.
 */

public class ValidateInput {

    public static boolean isDouble(String text) {

        try {
            Double.parseDouble(text);
            return true;
        }catch (NumberFormatException e) {
            Toast.makeText(MainActivity.getContext(), "Please check the fields.", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

}
