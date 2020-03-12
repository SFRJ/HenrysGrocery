package com.javing.henrysgrocery;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {

    @Test
    public void priceSingleSoup() {

        Basket basket = new Basket();

        Double price = basket.price("soup");

        assertThat(price).isEqualTo(0.65D);
    }

    @Test
    public void pricingTwoCansOfSoup() {

        Basket basket = new Basket();

        Double price = basket.price("soup", "soup");

        assertThat(price).isEqualTo(1.3D);
    }
}
