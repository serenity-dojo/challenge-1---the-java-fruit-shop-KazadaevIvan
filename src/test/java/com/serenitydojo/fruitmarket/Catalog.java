package com.serenitydojo.fruitmarket;

import java.util.*;
import java.util.stream.Collectors;

public class Catalog {
    private final Map<FruitType, Double> fruitMap = new HashMap<>();

    public double getPriceOf(FruitType fruit) {
        try {
            return fruitMap.get(fruit);
        } catch (NullPointerException e) {
            throw new FruitUnavailableException("Fruit " + fruit.name().toLowerCase() + " is currently unavailable in the catalog", e);
        }
    }

    public CatalogBuilder setPriceOf(FruitType fruit) {
        return new CatalogBuilder(fruit);
    }

    public List<String> getAllFruitsNames() {
        return fruitMap.keySet()
                .stream()
                .map(fruit -> fruit.name().toLowerCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public void addToCatalog(FruitType fruit, double price) {
        fruitMap.put(fruit,price);
    }

    public class CatalogBuilder {
        private FruitType fruit;

        public CatalogBuilder(FruitType fruit) {
            this.fruit = fruit;
        }

        public CatalogBuilder to (double price) {
            addToCatalog(fruit, price);
            return this;
        }
    }
}
