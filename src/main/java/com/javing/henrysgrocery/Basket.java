package com.javing.henrysgrocery;

import static com.javing.henrysgrocery.Item.getByName;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class Basket {

    public double price(String... items) throws IllegalArgumentException {

        double total = 0D;
        int soupCount = (int) stream(items).filter(i -> i.equals("soup")).count();
        int breadCount = (int) stream(items).filter(i -> i.equals("bread")).count();
        double breadDiscounts = 0D;

        for (String item : items) {

            total += getByName(item).getPrice();
        }

        if (soupCount >= 2 && breadCount > 0) {

            double applicableDiscounts = soupCount / 2D;

            if (applicableDiscounts > breadCount) {
                applicableDiscounts -= breadCount;
            }

            breadDiscounts = applicableDiscounts * 0.4;
        }

        return parseDouble(format("%.2f", total - breadDiscounts));
    }

}
