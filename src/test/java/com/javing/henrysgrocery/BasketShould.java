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

        assertThat(price).isEqualTo(0.09D);
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

        LocalDate purchaseDateAfterPromotion = now().plusDays(6);
        basket = new Basket(purchaseDateAfterPromotion);

        Double price = basket.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(2.1D);
    }

    @Test
    public void applyBreadDiscountUptoTheSeventhDayCountingFromYesterday() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        basket = new Basket(purchaseDateWithinPromotion);

        Double price = basket.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }

    @Test
    public void discountApplesWithinPromotionTime() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        basket = new Basket(purchaseDateWithinPromotion);

        Double price = basket.price("apples");

        assertThat(price).isEqualTo(0.09D);
    }
}
