package com.example.omar.calcounter.Util;

import java.text.DecimalFormat;

/**
 * Created by OMAR on 11/13/2018.
 */

public class Utils {

    public static String FormatNumner(int value){
        DecimalFormat formatter = new DecimalFormat ( "#,###,###" );
        String formatted = formatter.format ( value );
        return formatted;
    }
}
