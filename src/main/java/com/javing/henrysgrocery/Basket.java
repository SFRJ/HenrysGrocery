package com.javing.henrysgrocery;

import static com.javing.henrysgrocery.Item.getByName;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;

public class Basket {

    public double price(String ... items) throws IllegalArgumentException {

        double total = 0D;

        for (String item : items) {

            total += getByName(item).getPrice();

        }

        return parseDouble(format("%.2f", total));
    }

}
