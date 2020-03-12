package com.javing.henrysgrocery;

import java.util.Arrays;

import static com.javing.henrysgrocery.Item.getByName;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class Basket {

    private DiscountCalculator discountCalculator;

    public Basket(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public double price(String... items) throws IllegalArgumentException {

        return stream(items)
                .map(item -> getByName(item).getPrice())
                .reduce(Double::sum)
                .map(result -> parseDouble(format("%.2f",
                        result - discountCalculator.discountBread(items) - discountCalculator.discountApples(items))))
                .get();
    }

}
