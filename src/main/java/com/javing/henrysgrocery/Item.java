package com.javing.henrysgrocery;

public enum Item {

    SOUP("soup", 0.65),
    BREAD("bread", 0.8),
    MILK("milk", 1.3),
    APPLE("apples", 0.1);


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

    public double getPrice() {
        return price;
    }
}
