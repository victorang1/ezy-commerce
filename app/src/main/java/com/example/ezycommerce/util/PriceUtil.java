package com.example.ezycommerce.util;

import java.text.DecimalFormat;

public class PriceUtil {

    public static String displayPriceFormat(Double price) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(price);
    }
}
