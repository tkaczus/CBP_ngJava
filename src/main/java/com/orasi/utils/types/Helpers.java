package com.orasi.utils.types;

import java.util.UUID;

/**
 * Created by Lukasz.Tkaczyk on 2016-03-03.
 */
public class Helpers {

    public static double randomWithRange(double min, double max)
    {
        double range = (max - min);
        return (Math.random() * range) + min;
    }
    public static String losujKwote() {
        return String.format("%1$,.2f",randomWithRange(10,99));
    }

    public static String zwrocUID() {
        UUID idOne = UUID.randomUUID();
        String uid=idOne.toString().substring(0,10);
        return uid;
    }
}
