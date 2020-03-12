package com.javing.henrysgrocery;

import java.time.LocalDate;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        DiscountCalculator discountCalculator = new DiscountCalculator(LocalDate.now());
        Basket basket = new Basket(discountCalculator);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Henry's Grocery program");
        System.out.println("The items you can add to the basket are: soup, milk, apples, bread");
        System.out.println("Please enter a list of items separated by a blank space");

        double total = basket.price(scanner.nextLine().split(" "));

        System.out.println("The total price of the basket is: " + total);
    }

}
