package com.serenitydojo.fruitmarket;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<FruitType, Double> fruitMap = new HashMap<>();
    Catalog catalog;

    public ShoppingCart(Catalog catalog) {
         this.catalog = catalog;
    }

    public void addItemToCart(FruitType fruit) {
        fruitMap.put(fruit, catalog.getPriceOf(FruitType.valueOf(fruit.name())));
    }

    public void addItemToCart(FruitType fruit, int kilos) {
        if (kilos >= 5) {
            fruitMap.put(fruit, kilos * catalog.getPriceOf(FruitType.valueOf(fruit.name())) * 0.9);
        } else {
            fruitMap.put(fruit, kilos * catalog.getPriceOf(FruitType.valueOf(fruit.name())));
        }
    }

    public double getTotal() {
        return fruitMap.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
