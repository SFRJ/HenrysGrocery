package com.javing.henrysgrocery;

public class Basket {

    public double price(String ... soup) {

        double total = 0D;

        for (String item : soup) {
            if(item.equals("bread"))
                total += 0.8;
        }

        return Double.parseDouble(String.format("%.2f", 0.65D + total));
    }
}
