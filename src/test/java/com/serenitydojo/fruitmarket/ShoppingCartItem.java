package com.serenitydojo.fruitmarket;

public class ShoppingCartItem {
    private FruitType fruitType;
    private double price;
    private int kilosQuantity;

    public ShoppingCartItem(FruitType fruitType, double price, int kilosQuantity) {
        this.fruitType = fruitType;
        this.price = price;
        this.kilosQuantity = kilosQuantity;
    }

    public double getPrice() {
        return price;
    }

    public int getKilosQuantity() {
        return kilosQuantity;
    }
}
