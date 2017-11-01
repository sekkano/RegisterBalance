package edu.cvtc.android.registerbalance;

/**
 * Created by Beast on 12/29/16.
 */

public class Calculate {
    
    private static final double QUARTER_VALUE = 0.25;
    private static final double DIME_VALUE = 0.10;
    private static final double NICKLE_VALUE = 0.05;

    // TODO: 12/29/16 Determine if I need these or should I just leave it off?
    private static final double PENNY_VALUE = 0.01;
    private final double ONES_VALUE = 1;

    private static final double FIVES_VALUE = 5;
    private static final double TENS_VALUE = 10;
    private static final double TWENTIES_VALUE = 20;

    public static double calculateQuarters(double quartersQuantity) {

        quartersQuantity = quartersQuantity * QUARTER_VALUE;

        return quartersQuantity;
    }

    public static double calculateDimes(double dimesQuantity) {

        dimesQuantity = dimesQuantity * DIME_VALUE;

        return dimesQuantity;
    }

    public static double calculateNickles(double nicklesQuantity) {

        nicklesQuantity = nicklesQuantity * NICKLE_VALUE;

        return nicklesQuantity;
    }

    public static double calculatePennies(double penniesQuantity) {
        penniesQuantity = penniesQuantity * PENNY_VALUE;

        return penniesQuantity;
    }

    public static double calculateFives(double fivesQuantity) {

        fivesQuantity = fivesQuantity * FIVES_VALUE;

        return fivesQuantity;
    }

    public static double calculateTens(double tensQuantity) {

        tensQuantity = tensQuantity * TENS_VALUE;

        return tensQuantity;
    }

    public static double calculateTwenties(double twentiesQuantity) {

        twentiesQuantity = twentiesQuantity * TWENTIES_VALUE;

        return twentiesQuantity;
    }
    
}
