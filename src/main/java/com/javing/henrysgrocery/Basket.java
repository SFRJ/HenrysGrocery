package com.javing.henrysgrocery;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;

public class Basket {

    public double price(String ... soup) {

        double total = 0D;

        for (String item : soup) {
            if(item.equals("soup"))
                total += 0.65D;
            if(item.equals("bread"))
                total += 0.8;
        }

        return parseDouble(format("%.2f", total));
    }
}
