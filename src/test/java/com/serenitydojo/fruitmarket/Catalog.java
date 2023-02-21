package com.serenitydojo.fruitmarket;

import java.util.*;
import java.util.stream.Collectors;

public class Catalog {
    private FruitType fruit;
    private final Map<FruitType, Double> fruitMap = new HashMap<FruitType, Double>() {{
        put(FruitType.APPLE, 4.00);
        put(FruitType.ORANGE, 5.50);
        put(FruitType.PEER, 4.50);
        put(FruitType.BANANA, 6.00);
    }};

    public Catalog() {
    }

    public double getPriceOf(FruitType fruit) {
        try {
            return fruitMap.get(fruit);
        } catch (NullPointerException e) {
            throw new FruitUnavailableException("Fruit " + fruit.name().toLowerCase() + " is currently unavailable in the catalog", e);
        }
    }

    public Catalog setPriceOf(FruitType fruit) {
        this.fruit = fruit;
        return this;
    }

    public void to(double price) {
        fruitMap.put(this.fruit, price);
    }

    public List<String> getAllFruitsNames() {
        return fruitMap.keySet()
                .stream()
                .map(fruit -> fruit.name().toLowerCase())
                .sorted()
                .collect(Collectors.toList());
    }
}
