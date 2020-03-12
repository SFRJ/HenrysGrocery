package com.javing.henrysgrocery;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DiscountCalculator discountCalculator;
    private Basket basket;

    @Before
    public void setUp() throws Exception {

        discountCalculator = Mockito.mock(DiscountCalculator.class);
        basket  = new Basket(discountCalculator);

        Mockito.when(discountCalculator.discountBread(Mockito.any())).thenReturn(0D);
        Mockito.when(discountCalculator.discountApples(Mockito.any())).thenReturn(0D);
    }

    @Test
    public void priceSingleSoup() throws IllegalArgumentException {

        Double price = basket.price("soup");

        assertThat(price).isEqualTo(0.65D);
    }

    @Test
    public void priceTwoCansOfSoup() throws IllegalArgumentException {

        Mockito.when(discountCalculator.discountBread(Mockito.any())).thenReturn(0D);
        Mockito.when(discountCalculator.discountApples(Mockito.any())).thenReturn(0D);

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
    public void priceApplesAndMilkBoughtToday() {

        Double price = basket.price("apples", "apples", "apples", "apples", "apples", "apples", "milk");

        assertThat(price).isEqualTo(1.9D);
    }

}
