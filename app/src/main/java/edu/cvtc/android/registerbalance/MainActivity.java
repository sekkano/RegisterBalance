package edu.cvtc.android.registerbalance;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Will hold the values in the text fields
    private String quarters;
    private String dimes;
    private String nickles;
    private String pennies;
    private String ones;
    private String fives;
    private String tens;
    private String twenties;

    private double total;

    // These will hold the values from the text fields after they've been converted
    private double convertedQuarters;
    private double convertedDimes;
    private double convertedNickles;
    private double convertedPennies;
    private double convertedOnes;
    private double convertedFives;
    private double convertedTens;
    private double convertedTwenties;

    private final double BALANCE_ONE_FIFTY = 150.00;
    private double neededOnes;

    private EditText quartersEditText;
    private EditText dimesEditText;
    private EditText nicklesEditText;
    private EditText penniesEditText;
    private EditText onesEditText;
    private EditText fivesEditText;
    private EditText tensEditText;
    private EditText twentiesEditText;

    private Button calculateButton;

    // Used for hiding keyboard
    private RelativeLayout layout;

    private static AppCompatActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        instance = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quartersEditText = (EditText) findViewById(R.id.quarters_edit_text);
        dimesEditText = (EditText) findViewById(R.id.dimes_edit_text);
        nicklesEditText = (EditText) findViewById(R.id.nickles_edit_text);
        penniesEditText = (EditText) findViewById(R.id.pennies_edit_text);
        onesEditText = (EditText) findViewById(R.id.ones_edit_text);
        fivesEditText = (EditText) findViewById(R.id.fives_edit_text);
        tensEditText = (EditText) findViewById(R.id.tens_edit_text);
        twentiesEditText = (EditText) findViewById(R.id.twenties_edit_text);

        calculateButton = (Button) findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(this);

        layout = (RelativeLayout) findViewById(R.id.activity_main);
        
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard(view);
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {

        getInput();

        if (ValidateInput.isDouble(quarters) && ValidateInput.isDouble(dimes) &&
                ValidateInput.isDouble(nickles) && ValidateInput.isDouble(pennies) &&
                ones.length() > 0 && ValidateInput.isDouble(ones) && ValidateInput.isDouble(fives) &&
                ValidateInput.isDouble(tens) && ValidateInput.isDouble(twenties)) {

            convertedQuarters = Calculate.calculateQuarters(Double.parseDouble(quarters));
            convertedDimes = Calculate.calculateDimes(Double.parseDouble(dimes));
            convertedNickles = Calculate.calculateNickles(Double.parseDouble(nickles));
            convertedPennies = Calculate.calculatePennies(Double.parseDouble(pennies));

            // Don't need to calculate Ones since the quantities are the same
            // as their values.

            convertedOnes = Double.parseDouble(ones);

            convertedFives = Calculate.calculateFives(Double.parseDouble(fives));
            convertedTens = Calculate.calculateTens(Double.parseDouble(tens));
            convertedTwenties = Calculate.calculateTwenties(Double.parseDouble(twenties));

            double[] storedValues = {convertedQuarters, convertedDimes, convertedNickles,
                convertedPennies, convertedOnes, convertedFives, convertedTens, convertedTwenties};

            for (int i = 0; i < storedValues.length; i++) {
                total += storedValues[i];
            }
            showTotals(this);

        }else{
            convertedQuarters = Calculate.calculateQuarters(Double.parseDouble(quarters));
            convertedDimes = Calculate.calculateDimes(Double.parseDouble(dimes));
            convertedNickles = Calculate.calculateNickles(Double.parseDouble(nickles));
            convertedPennies = Calculate.calculatePennies(Double.parseDouble(pennies));

            convertedFives = Calculate.calculateFives(Double.parseDouble(fives));
            convertedTens = Calculate.calculateTens(Double.parseDouble(tens));
            convertedTwenties = Calculate.calculateTwenties(Double.parseDouble(twenties));

            double[] storedValues = {convertedQuarters, convertedDimes, convertedNickles,
                    convertedPennies, convertedFives, convertedTens, convertedTwenties};

            for (int i = 0; i < storedValues.length; i++) {
                total += storedValues[i];
            }

            neededOnes = BALANCE_ONE_FIFTY - Math.floor(total);
            showTotals(this);
        }

    }

    private void getInput() {
        quarters = quartersEditText.getText().toString();
        dimes = dimesEditText.getText().toString();
        nickles = nicklesEditText.getText().toString();
        pennies = penniesEditText.getText().toString();
        ones = onesEditText.getText().toString();
        fives = fivesEditText.getText().toString();
        tens = tensEditText.getText().toString();
        twenties = twentiesEditText.getText().toString();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    private void showTotals(Context context) {

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        if (onesEditText.getText().length() > 0) {
            dialog.setContentView(R.layout.popup_count_in_activity);

            TextView quartersTextView = (TextView) dialog.findViewById(R.id.quarters_quantity_text_view);
            TextView dimesTextView = (TextView) dialog.findViewById(R.id.dimes_quantity_text_view);
            TextView nicklesTextView = (TextView) dialog.findViewById(R.id.nickles_quantity_text_view);
            TextView penniesTextView = (TextView) dialog.findViewById(R.id.pennies_quantity_text_view);

            TextView onesTextView = (TextView) dialog.findViewById(R.id.ones_quantity_text_view);
            TextView fivesTextView = (TextView) dialog.findViewById(R.id.fives_quantity_text_view);
            TextView tensTextView = (TextView) dialog.findViewById(R.id.tens_quantity_text_view);
            TextView twentiesTextView = (TextView) dialog.findViewById(R.id.twenties_quantity_text_view);

            TextView totalTextView = (TextView) dialog.findViewById(R.id.total_quantity_text_view);

            Button dismissButton = (Button) dialog.findViewById(R.id.dismiss_button);

            quartersTextView.setText("$" + String.valueOf(convertedQuarters).format("%.2f", convertedQuarters));
            dimesTextView.setText("$" + String.valueOf(convertedDimes).format("%.2f", convertedDimes));
            nicklesTextView.setText("$" + String.valueOf(convertedNickles).format("%.2f", convertedNickles));
            penniesTextView.setText("$" + String.valueOf(convertedPennies).format("%.2f", convertedPennies));

            onesTextView.setText("$" + String.valueOf(convertedOnes).format("%.2f", convertedOnes));
            fivesTextView.setText("$" + String.valueOf(convertedFives).format("%.2f", convertedFives));
            tensTextView.setText("$" + String.valueOf(convertedTens).format("%.2f", convertedTens));
            twentiesTextView.setText("$" + String.valueOf(convertedTwenties).format("%.2f", convertedTwenties));

            totalTextView.setText("$" + String.valueOf(total).format("%.2f", total));

            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    total = 0;
                    dialog.dismiss();

                }
            });
        }else{
            dialog.setContentView(R.layout.popup_count_out_activity);

            TextView quartersTextView = (TextView) dialog.findViewById(R.id.quarters_quantity_text_view);
            TextView dimesTextView = (TextView) dialog.findViewById(R.id.dimes_quantity_text_view);
            TextView nicklesTextView = (TextView) dialog.findViewById(R.id.nickles_quantity_text_view);
            TextView penniesTextView = (TextView) dialog.findViewById(R.id.pennies_quantity_text_view);

            TextView onesTextView = (TextView) dialog.findViewById(R.id.ones_quantity_text_view);
            TextView fivesTextView = (TextView) dialog.findViewById(R.id.fives_quantity_text_view);
            TextView tensTextView = (TextView) dialog.findViewById(R.id.tens_quantity_text_view);
            TextView twentiesTextView = (TextView) dialog.findViewById(R.id.twenties_quantity_text_view);

            TextView totalTextView = (TextView) dialog.findViewById(R.id.total_quantity_text_view);

            Button dismissButton = (Button) dialog.findViewById(R.id.dismiss_button);

            quartersTextView.setText("$" + String.valueOf(convertedQuarters).format("%.2f", convertedQuarters));
            dimesTextView.setText("$" + String.valueOf(convertedDimes).format("%.2f", convertedDimes));
            nicklesTextView.setText("$" + String.valueOf(convertedNickles).format("%.2f", convertedNickles));
            penniesTextView.setText("$" + String.valueOf(convertedPennies).format("%.2f", convertedPennies));

            fivesTextView.setText("$" + String.valueOf(convertedFives).format("%.2f", convertedFives));
            tensTextView.setText("$" + String.valueOf(convertedTens).format("%.2f", convertedTens));
            twentiesTextView.setText("$" + String.valueOf(convertedTwenties).format("%.2f", convertedTwenties));

            totalTextView.setText("$" + String.valueOf(total).format("%.2f", total));
            onesTextView.setText("$" + String.valueOf(neededOnes).format("%.2f", neededOnes));

            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    total = 0;
                    dialog.dismiss();

                }
            });
        }
        dialog.show();

    }

    // TODO: 12/29/16 Find out what this all means.
    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
