package bank.utils.amount;

/**
 * Represents Amount objects of value type Double
 * A subclass of Amount
 */
public class DoubleAmount extends Amount {

    public DoubleAmount(Number a) {
        super(a);
    }

    //Other methods are just implementations of abstract methods in Amount class
    public Number sum(Number a) {
        return a.doubleValue() + this.getAmountValue().doubleValue();
    }

    public Number sub(Number a) {
        return this.getAmountValue().doubleValue() - a.doubleValue();
    }

    @Override
    public Number mul(Number a) {
        return a.doubleValue() * this.getAmountValue().doubleValue();
    }

    public boolean moreThan(Amount amount) {
        return (this.getAmountValue().doubleValue() > amount.getAmountValue().doubleValue());
    }

    @Override
    public boolean lessThan(Amount amount) {
        return (this.getAmountValue().doubleValue() < amount.getAmountValue().doubleValue());
    }

    @Override
    public boolean equal(Amount amount) {
        return (this.getAmountValue().doubleValue() == amount.getAmountValue().doubleValue());
    }

    @Override
    public boolean isNegative() {
        return this.getAmountValue().doubleValue() < 0.0;
    }
}
