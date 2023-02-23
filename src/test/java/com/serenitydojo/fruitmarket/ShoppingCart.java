package com.serenitydojo.fruitmarket;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<FruitType, Double> fruitMap = new HashMap<>();
    Catalog catalog;

    public ShoppingCart(Catalog catalog) {
         this.catalog = catalog;
    }

    public void addItemToCart(FruitType fruit, int kilosQuantity) {
        if (kilosQuantity >= 5) {
            fruitMap.put(fruit, kilosQuantity * catalog.getPriceOf(FruitType.valueOf(fruit.name())) * 0.9);
        } else {
            fruitMap.put(fruit, kilosQuantity * catalog.getPriceOf(FruitType.valueOf(fruit.name())));
        }
    }

    public double getTotal() {
        return fruitMap.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
