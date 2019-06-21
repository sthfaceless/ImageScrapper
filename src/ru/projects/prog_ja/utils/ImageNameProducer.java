package ru.projects.prog_ja.utils;

import java.util.Locale;
import java.util.Random;

public class ImageNameProducer {

    /*
    * Random generator
    * */
    private final Random random = new Random();

    private final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lower = upper.toLowerCase(Locale.ROOT);
    private final String digits = "0123456789";

    private final char[] chars = (lower + digits + upper).toCharArray();

    private final int bounds = chars.length;

    public String produce(){

        int rand = Math.abs(random.nextInt());

        if(rand % 2 == 0)
            return ""
                    + chars[(rand >> 1) % bounds]
                    + chars[(rand >> 2) % bounds]
                    + chars[(rand >> 3) % bounds]
                    + chars[(rand >> 4) % bounds]
                    + chars[(rand >> 5) % bounds];
        else
            return ""
                    + chars[(rand >> 1) % bounds]
                    + chars[(rand >> 2) % bounds]
                    + chars[(rand >> 3) % bounds]
                    + chars[(rand >> 4) % bounds]
                    + chars[(rand >> 5) % bounds]
                    + chars[(rand >> 6) % bounds];

    }

}
