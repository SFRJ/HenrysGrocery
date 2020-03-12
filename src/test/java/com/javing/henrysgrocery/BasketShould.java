package com.javing.henrysgrocery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Basket basket = new Basket(now());

    @Test
    public void priceSingleSoup() throws IllegalArgumentException {

        Double price = basket.price("soup");

        assertThat(price).isEqualTo(0.65D);
    }

    @Test
    public void priceTwoCansOfSoup() throws IllegalArgumentException {

        Double price = basket.price("soup", "soup");

        assertThat(price).isEqualTo(1.3D);
    }

    @Test
    public void priceSingleSoupAndSingleBread() throws IllegalArgumentException {

        Double price = basket.price("soup", "bread");

        assertThat(price).isEqualTo(1.45D);
    }

    @Test
    public void priceSingleSoupAndTwoBreads() throws IllegalArgumentException {

        Double price = basket.price("soup", "bread", "bread");

        assertThat(price).isEqualTo(2.25D);
    }

    @Test
    public void notAllowUnrecognisedItem() throws IllegalArgumentException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unrecognised item");

        basket.price("INVALID ITEM");
    }

    @Test
    public void priceSingleMilk() {

        Double price = basket.price("milk");

        assertThat(price).isEqualTo(1.3D);
    }

    @Test
    public void priceSingleApple() {

        Double price = basket.price("apples");

        assertThat(price).isEqualTo(0.1D);
    }

    @Test
    public void applyDiscountOnBread() {

        Double price = basket.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }

    @Test
    public void applyDiscountOnBreadTwice() {

        Double price = basket.price("soup", "soup", "soup", "soup", "bread", "bread");

        assertThat(price).isEqualTo(3.4D);
    }

    @Test
    public void applyBreadDiscountWhenNecesaryOnly() {

        Double price = basket.price("soup", "soup", "soup", "soup", "bread");

        assertThat(price).isEqualTo(3D);
    }

    @Test
    public void notApplyBreadDiscountPassedSevenDaysCountingFromYesterday() {
        basket = new Basket(LocalDate.now().plusDays(6));

        Double price = basket.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(2.1D);
    }

    @Test
    public void applyBreadDiscountUptoTheSeventhDayCountingFromYesterday() {
        basket = new Basket(LocalDate.now().plusDays(5));

        Double price = basket.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }
}
