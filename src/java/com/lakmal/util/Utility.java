/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lakmal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author lakmal
 */
public class Utility {

    // Replace with KK:mma if you want 0-11 interval
    private static final DateFormat TWELVE_TF = new SimpleDateFormat("hh:mma");
    // Replace with kk:mm if you want 1-24 interval
    private static final DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");

    public static String getLastTwoCharacters(String str) {
        return str.length() > 2 ? str.substring(str.length() - 2) : str;
    }

    public static String convertTo24HoursFormat(String twelveHourTime) {
        String result = null;
        try {
            result = TWENTY_FOUR_TF.format(TWELVE_TF.parse(twelveHourTime));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
