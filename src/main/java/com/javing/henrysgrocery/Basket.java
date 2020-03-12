package com.javing.henrysgrocery;

import java.time.LocalDate;

import static com.javing.henrysgrocery.Item.getByName;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class Basket {

    private LocalDate purchaseDate;

    public Basket(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double price(String... items) throws IllegalArgumentException {

        double total = 0D;

        for (String item : items) {

            total += getByName(item).getPrice();
        }

        return parseDouble(format("%.2f", total - discountBread(items)));
    }

    private double discountBread(String[] items) {

        double breadDiscounts = 0D;
        int soupCount = (int) stream(items).filter(i -> i.equals("soup")).count();
        int breadCount = (int) stream(items).filter(i -> i.equals("bread")).count();

        LocalDate validFrom = LocalDate.now().minusDays(1);
        LocalDate validTo = LocalDate.now().plusDays(6L);
        boolean isValidDiscount = purchaseDate.isAfter(validFrom) && purchaseDate.isBefore(validTo);

        if (soupCount >= 2 && breadCount > 0 && isValidDiscount) {

            double applicableDiscounts = soupCount / 2D;

            if (applicableDiscounts > breadCount) {
                applicableDiscounts -= breadCount;
            }

            breadDiscounts = applicableDiscounts * 0.4;
        }
        return breadDiscounts;
    }

}
