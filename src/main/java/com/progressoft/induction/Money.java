package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {


    public static final Money ZERO = new Money(BigDecimal.valueOf(0));
    public static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    public static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.50));
    public static final Money DINAR = new Money(BigDecimal.valueOf(1.0));
    private BigDecimal value;


    public Money(BigDecimal value) {
        this.setValue(value);
    }

    public void setValue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) >= 0)
            this.value = value;
        else
            throw new IllegalArgumentException();
    }

    public BigDecimal getValue() {
        return this.value;
    }


    public Money add(Money dinar) {
        BigDecimal add = value.add(dinar.getValue());
        return new Money(add);

    }

    public boolean isLessThan(Money dinar) {
        if (dinar == null)
            return false;
        return this.value.compareTo(dinar.value) < 0;
    }

    public Money subtract(Money dinar) {
        if (value.subtract(dinar.value).compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException();
        value = value.subtract(dinar.value);
        return new Money(value);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Money))
            return false;
        if (obj == this)
            return true;
        return this.value.equals(((Money) obj).value);
    }
}
