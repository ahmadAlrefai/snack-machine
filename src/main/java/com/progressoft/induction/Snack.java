package com.progressoft.induction;

public class Snack {
    private Money price;
    private int quantity;

    public Snack( Money price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public int quantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Money getPrice()
    {
        return this.price;
    }

    public void setPrice(Money price)
    {
        this.price=price;
    }
}
