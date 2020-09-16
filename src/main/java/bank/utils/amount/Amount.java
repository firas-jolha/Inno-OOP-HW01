package bank.utils.amount;

public abstract class Amount {

    private Number amountValue;

    public abstract Number sum(Number a);

    public abstract Number sub(Number a);

    public abstract Number mul(Number a);

    public abstract boolean moreThan(Amount amount);

    public abstract boolean lessThan(Amount amount);

    public abstract boolean equal(Amount amount);

    public Amount(Number amount) {
        if (amount == null) this.amountValue = 0.0;
        this.setAmountValue(amount);
    }

    public Number getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(Number amount) {
        this.amountValue = amount;
    }

    // factory method for amount objects
    public static Amount getAmountInstance(Number amount) {
        if (amount instanceof Double)
            return new DoubleAmount(amount);
        else if (amount instanceof Integer)
            return new IntegerAmount(amount);
        else
            return null;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + amountValue +
                '}';
    }
}
