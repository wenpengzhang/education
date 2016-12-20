package com.edu.pay.util;

/**
 * 
 * @author lee
 *
 */
public class MonetaryUnitUtils {

    public static String changeUnit(long price) {
        double priceUnitYuan = price / 1000.0;
        return priceUnitYuan + "";
    }
}
