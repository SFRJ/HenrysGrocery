package com.javing.henrysgrocery;

import static com.javing.henrysgrocery.Basket.Item.getByName;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;

public class Basket {

    public double price(String ... items) throws IllegalArgumentException {

        double total = 0D;

        for (String item : items) {

            total += getByName(item).price;

        }

        return parseDouble(format("%.2f", total));
    }

     enum Item {

        SOUP("soup", 0.65),
        BREAD("bread", 0.8);

        private final String itemKey;
        private final double price;

        Item(String itemKey, double price) {

            this.itemKey = itemKey;
            this.price = price;
        }

        public static Item getByName(String name) throws IllegalArgumentException {
            for (Item item : Item.values()) {
                if(item.itemKey.equals(name))
                    return item;
            }

            throw new IllegalArgumentException("unrecognised item");
        }
    }
}
