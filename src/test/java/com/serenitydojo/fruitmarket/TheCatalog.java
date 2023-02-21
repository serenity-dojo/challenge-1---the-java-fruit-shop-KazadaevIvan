package com.serenitydojo.fruitmarket;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TheCatalog {

    @Test
    public void shouldBeAbleToUpdateTheCurrentPriceOfAFruit() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        assertThat(catalog.getPriceOf(FruitType.APPLE)).isEqualTo(4.00);
    }

    @Test
    public void shouldReportThePriceOfAGivenTypeOfFruit() {
        Catalog catalog = new Catalog();
        assertThat(catalog.getPriceOf(FruitType.APPLE)).isEqualTo(4.00);
    }

    @Test
    public void shouldBeAbleToSeeTheListOfFruitsAvailable() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);
        catalog.setPriceOf(FruitType.BANANA).to(6.00);

        List<String> fruitList = catalog.getAllFruitsNames();

        assertThat(fruitList).containsExactly("apple", "banana", "orange", "peer");
    }

    @Test
    public void shouldListTheNamesOfTheCurrentlyAvailableFruitsInAlphabeticalOrder() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);
        catalog.setPriceOf(FruitType.BANANA).to(6.00);

        List<String> fruitList = catalog.getAllFruitsNames();

        List<String> expectedFruits = Stream.of("orange", "apple", "banana", "peer").sorted().collect(Collectors.toList());

        assertThat(fruitList).isEqualTo(expectedFruits);
    }

    @Test(expected = FruitUnavailableException.class)
    public void shouldThrowAnExceptionIfTheFruitIsNotCurrentlyAvailable() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);

        assertThat(catalog.getPriceOf(FruitType.PEACH)).isNull();
    }

    @Test()
    public void shouldCountTotalPriceOfProductsInTheShoppingCart() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);

        ShoppingCart shoppingCart = new ShoppingCart(catalog);
        shoppingCart.addItemToCart(FruitType.APPLE);
        shoppingCart.addItemToCart(FruitType.ORANGE);

        assertThat(shoppingCart.getTotal()).isEqualTo(9.00);
    }

    @Test()
    public void shouldCountTotalPriceOfProductsKilosInTheShoppingCart() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);

        ShoppingCart shoppingCart = new ShoppingCart(catalog);
        shoppingCart.addItemToCart(FruitType.APPLE, 4);
        shoppingCart.addItemToCart(FruitType.ORANGE, 3);

        assertThat(shoppingCart.getTotal()).isEqualTo(31.00);
    }

    @Test()
    public void shouldApplyDiscountWhenBoughtMoreThanFiveKilosOfProduct() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);

        ShoppingCart shoppingCart = new ShoppingCart(catalog);
        shoppingCart.addItemToCart(FruitType.APPLE);
        shoppingCart.addItemToCart(FruitType.ORANGE, 6);

        assertThat(shoppingCart.getTotal()).isEqualTo(31);
    }

    @Test()
    public void shouldApplyDiscountWhenBoughtFiveKilosOfProduct() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);

        ShoppingCart shoppingCart = new ShoppingCart(catalog);
        shoppingCart.addItemToCart(FruitType.APPLE);
        shoppingCart.addItemToCart(FruitType.ORANGE, 5);

        assertThat(shoppingCart.getTotal()).isEqualTo(26.5);
    }

    @Test()
    public void shouldNotApplyDiscountWhenBoughtLessThanFiveKilosOfProduct() {
        Catalog catalog = new Catalog();
        catalog.setPriceOf(FruitType.APPLE).to(4.00);
        catalog.setPriceOf(FruitType.ORANGE).to(5.00);
        catalog.setPriceOf(FruitType.PEER).to(2.00);

        ShoppingCart shoppingCart = new ShoppingCart(catalog);
        shoppingCart.addItemToCart(FruitType.APPLE);
        shoppingCart.addItemToCart(FruitType.ORANGE, 4);

        assertThat(shoppingCart.getTotal()).isEqualTo(24);
    }
}
