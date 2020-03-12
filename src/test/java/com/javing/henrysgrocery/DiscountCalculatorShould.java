package com.javing.henrysgrocery;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCalculatorShould {

    private DiscountCalculator discountCalculator = new DiscountCalculator(now());

    @Test
    public void applyDiscountOnBread() {

        Double price = discountCalculator.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }

    @Test
    public void applyDiscountOnBreadTwice() {

        Double price = discountCalculator.price("soup", "soup", "soup", "soup", "bread", "bread");

        assertThat(price).isEqualTo(3.4D);
    }

    @Test
    public void applyBreadDiscountWhenNecesaryOnly() {

        Double price = discountCalculator.price("soup", "soup", "soup", "soup", "bread");

        assertThat(price).isEqualTo(3D);
    }

    @Test
    public void notApplyBreadDiscountPassedSevenDaysCountingFromYesterday() {

        LocalDate purchaseDateAfterPromotion = now().plusDays(6);
        discountCalculator = new DiscountCalculator(purchaseDateAfterPromotion);

        Double price = discountCalculator.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(2.1D);
    }

    @Test
    public void applyBreadDiscountUptoTheSeventhDayCountingFromYesterday() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        discountCalculator = new DiscountCalculator(purchaseDateWithinPromotion);

        Double price = discountCalculator.price("soup", "soup", "bread");

        assertThat(price).isEqualTo(1.7D);
    }

    @Test
    public void applesPromotionStartsAfterThreedays() {

        LocalDate purchaseDateWithinPromotion = now().plusDays(4);
        discountCalculator = new DiscountCalculator(purchaseDateWithinPromotion);

        Double price = discountCalculator.price("apples");

        assertThat(price).isEqualTo(0.09D);
    }

    @Test
    public void applesPromotionValidUntilLastDayOfNextMonth() {

        LocalDate purchaseDateNotInPromotion = now().plusMonths(1).with(lastDayOfMonth());

        discountCalculator = new DiscountCalculator(purchaseDateNotInPromotion);

        Double price = discountCalculator.price("apples");

        assertThat(price).isEqualTo(0.09D);
    }

    @Test
    public void applesPromotionNotValidAfterLastDayOfNextMonth() {

        LocalDate purchaseDateNotInPromotion = now().plusMonths(1).with(lastDayOfMonth()).plusDays(1);

        discountCalculator = new DiscountCalculator(purchaseDateNotInPromotion);

        Double price = discountCalculator.price("apples");

        assertThat(price).isEqualTo(0.1D);
    }

    @Test
    public void priceCorrectlyAVarietyOfProductsPurchasedInFiveDays() {
        LocalDate purchaseDateWithinPromotion = now().plusDays(5);
        discountCalculator = new DiscountCalculator(purchaseDateWithinPromotion);

        Double price = discountCalculator.price("apples", "apples", "apples", "soup", "soup", "bread");

        assertThat(price).isEqualTo(1.97D);
    }
}
