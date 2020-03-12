package com.javing.henrysgrocery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private DiscountCalculator discountCalculator = Mockito.mock(DiscountCalculator.class);
    private Basket basketOld = new Basket(now());
    private Basket basket = new Basket(discountCalculator);

    @Test
    public void priceSingleSoup() throws IllegalArgumentException {

        Double price = basketOld.price("soup");

        assertThat(price).isEqualTo(0.65D);
    }

    @Test
    public void priceTwoCansOfSoup() throws IllegalArgumentException {

        Double price = basketOld.price("soup", "soup");

        assertThat(price).isEqualTo(1.3D);
    }

    @Test
    public void priceSingleSoupAndSingleBread() throws IllegalArgumentException {

        Double price = basketOld.price("soup", "bread");

        assertThat(price).isEqualTo(1.45D);
    }

    @Test
    public void priceSingleSoupAndTwoBreads() throws IllegalArgumentException {

        Double price = basketOld.price("soup", "bread", "bread");

        assertThat(price).isEqualTo(2.25D);
    }

    @Test
    public void priceThreeSoupsAndTwoBreads() {

        Double price = basketOld.price("soup", "soup", "soup", "bread", "bread");

        assertThat(price).isEqualTo(3.15D);
    }

    @Test
    public void notAllowUnrecognisedItem() throws IllegalArgumentException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("unrecognised item");

        basketOld.price("INVALID ITEM");
    }

    @Test
    public void priceSingleMilk() {

        Double price = basketOld.price("milk");

        assertThat(price).isEqualTo(1.3D);
    }

    @Test
    public void priceSingleApple() {

        Double price = basketOld.price("apples");

        assertThat(price).isEqualTo(0.1D);
    }

    @Test
    public void priceApplesAndMilkBoughtToday() {

        Double price = basketOld.price("apples", "apples", "apples", "apples", "apples", "apples", "milk");

        assertThat(price).isEqualTo(1.9D);
    }

    @Test
    public void applesPromotionForApplesAndMilkBoughtInFiveDays() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        basketOld = new Basket(purchaseDateWithinPromotion);

        Double price = basketOld.price("apples", "apples", "apples", "apples", "apples", "apples", "milk");

        assertThat(price).isEqualTo(1.84D);
    }

    @Test
    public void applyDiscountOnBread() {

        Double price = basketOld.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }

    @Test
    public void applyDiscountOnBreadTwice() {

        Double price = basketOld.price("soup", "soup", "soup", "soup", "bread", "bread");

        assertThat(price).isEqualTo(3.4D);
    }

    @Test
    public void applyBreadDiscountWhenNecesaryOnly() {

        Double price = basketOld.price("soup", "soup", "soup", "soup", "bread");

        assertThat(price).isEqualTo(3D);
    }

    @Test
    public void notApplyBreadDiscountPassedSevenDaysCountingFromYesterday() {

        LocalDate purchaseDateAfterPromotion = now().plusDays(6);
        basketOld = new Basket(purchaseDateAfterPromotion);

        Double price = basketOld.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(2.1D);
    }

    @Test
    public void applyBreadDiscountUptoTheSeventhDayCountingFromYesterday() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        basketOld = new Basket(purchaseDateWithinPromotion);

        Double price = basketOld.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }

    @Test
    public void applesPromotionStartsAfterThreedays() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(4);
        basketOld = new Basket(purchaseDateWithinPromotion);

        Double price = basketOld.price("apples");

        assertThat(price).isEqualTo(0.09D);
    }

    @Test
    public void applesPromotionValidUntilLastDayOfNextMonth() {

        LocalDate purchaseDateNotInPromotion = now().plusMonths(1).with(lastDayOfMonth());

        basketOld = new Basket(purchaseDateNotInPromotion);

        Double price = basketOld.price("apples");

        assertThat(price).isEqualTo(0.09D);
    }

    @Test
    public void applesPromotionNotValidAfterLastDayOfNextMonth() {

        LocalDate purchaseDateNotInPromotion = now().plusMonths(1).with(lastDayOfMonth()).plusDays(1);

        basketOld = new Basket(purchaseDateNotInPromotion);

        Double price = basketOld.price("apples");

        assertThat(price).isEqualTo(0.1D);
    }

    @Test
    public void priceCorrectlyAVarietyOfProductsPurchasedInFiveDays() {
        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        basketOld = new Basket(purchaseDateWithinPromotion);

        Double price = basketOld.price("apples", "apples", "apples", "soup", "soup", "bread");

        assertThat(price).isEqualTo(1.97D);
    }

}
