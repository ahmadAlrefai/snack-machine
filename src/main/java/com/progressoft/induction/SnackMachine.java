package com.progressoft.induction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;


public class SnackMachine {
    public static final int DEFAULT_QUANTITY = 20;
    private Money moneyInMachine = new Money(BigDecimal.ZERO);
    private Money moneyInTransaction = new Money(BigDecimal.ZERO);
    private Snack chips;
    private Snack chocolate;
    private Snack chewingGum;
    private final List<Money> supportedUnits = Arrays.asList(new Money(BigDecimal.valueOf(0.25)),
            new Money(BigDecimal.valueOf(0.5)),
            new Money(BigDecimal.valueOf(1.0)),
            new Money(BigDecimal.valueOf(5.0)),
            new Money(BigDecimal.valueOf(10.0)));

    public SnackMachine() {
        chips = new Snack(new Money(BigDecimal.valueOf(1.0)), 20);
        chocolate = new Snack(new Money(BigDecimal.valueOf(2.0)), 20);
        chewingGum = new Snack(new Money(BigDecimal.valueOf(0.50)), 20);
    }

    public Money buySnack(SnackType snackType) {
        Snack snack = map(snackType);
        Money change = new Money(BigDecimal.ZERO);
        if (moneyInTransaction.getValue().compareTo(snack.getPrice().getValue()) < 0)
            throw new IllegalStateException();

        if (snack.getPrice().getValue().compareTo(moneyInTransaction.getValue()) < 0) {
            change = moneyInTransaction.subtract(snack.getPrice());
        }
        buy(snack);
        return change;
    }

    public Money moneyInside() {
        return moneyInMachine;
    }

    public void insertMoney(Money money) {
        if (!supportedUnits.contains(money))
            throw new IllegalArgumentException();

        moneyInTransaction.setValue(moneyInTransaction.getValue().add(money.getValue()));
    }

    public Money moneyInTransaction() {
        return moneyInTransaction;
    }

    public Snack chewingGums() {
        return chewingGum;
    }

    public Snack chips() {
        return chips;
    }

    public Snack chocolates() {
        return chocolate;
    }

    private void buy(Snack snack) {
        if (snack.quantity() > 0) {
            moneyInMachine.setValue(moneyInMachine.add(moneyInTransaction).getValue().setScale(1, RoundingMode.HALF_UP));
            moneyInTransaction.setValue(BigDecimal.ZERO);
            snack.setQuantity(snack.quantity() - 1);
        } else {
            throw new IllegalStateException();
        }
    }

    public Snack map(SnackType snackType) {
        switch (snackType) {
            case CHIPS:
                return this.chips;
            case CHOCOLATE:
                return this.chocolate;
            case CHEWING_GUM:
                return this.chewingGum;
            default:
                throw new IllegalStateException("Unexpected value: " + snackType);
        }
    }
}
