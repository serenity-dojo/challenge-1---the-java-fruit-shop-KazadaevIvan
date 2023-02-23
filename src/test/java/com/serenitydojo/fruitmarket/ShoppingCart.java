package com.serenitydojo.fruitmarket;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
    Catalog catalog;

    public ShoppingCart(Catalog catalog) {
         this.catalog = catalog;
    }

    public void addItemToCart(FruitType fruit, int kilosQuantity) {
        shoppingCartItems.add(new ShoppingCartItem(fruit, catalog.getPriceOf(FruitType.valueOf(fruit.name())), kilosQuantity));
    }

    public double getTotalIncludingDiscount(double discountPercent, int requiredKilosQuantityForDiscount) {
        return shoppingCartItems.stream()
                .mapToDouble(shoppingCartItem -> applyDiscountForKilos(discountPercent, requiredKilosQuantityForDiscount, shoppingCartItem.getKilosQuantity(), shoppingCartItem.getPrice()))
                .sum();
    }

    public double applyDiscountForKilos(double discountPercent, int requiredKilosQuantityForDiscount, int currentKilosQuantity, double price) {
        if (currentKilosQuantity >= requiredKilosQuantityForDiscount) {
            return (100 - discountPercent) / 100 * price * currentKilosQuantity;
        } else {
            return price * currentKilosQuantity;
        }
    }
}
