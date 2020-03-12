package com.javing.henrysgrocery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void priceSingleSoup() throws IllegalArgumentException {

        Basket basket = new Basket();

        Double price = basket.price("soup");

        assertThat(price).isEqualTo(0.65D);
    }

    @Test
    public void priceTwoCansOfSoup() throws IllegalArgumentException {

        Basket basket = new Basket();

        Double price = basket.price("soup", "soup");

        assertThat(price).isEqualTo(1.3D);
    }

    @Test
    public void priceSingleSoupAndSingleBread() throws IllegalArgumentException {

        Basket basket = new Basket();

        Double price = basket.price("soup", "bread");

        assertThat(price).isEqualTo(1.45D);
    }

    @Test
    public void priceSingleSoupAndTwoBreads() throws IllegalArgumentException {

        Basket basket = new Basket();

        Double price = basket.price("soup", "bread", "bread");

        assertThat(price).isEqualTo(2.25D);
    }

    @Test
    public void notAllowUnrecognisedItem() throws IllegalArgumentException {

        Basket basket = new Basket();

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unrecognised item");

        basket.price("INVALID ITEM");
    }
}
