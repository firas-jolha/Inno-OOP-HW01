package bank.utils.amount;
/**
 * Represents Amount objects of value type Short
 * A subclass of Amount
 */
public class ShortAmount extends Amount {

    public ShortAmount(Number amount) {
        super(amount);
    }

    //Other methods are just implementations of abstract methods in Amount class
    @Override
    public Number sum(Number a) {
        return a.shortValue() + this.getAmountValue().shortValue();
    }

    @Override
    public Number sub(Number a) {
        return this.getAmountValue().shortValue() - a.shortValue();
    }

    @Override
    public Number mul(Number a) {
        return this.getAmountValue().shortValue() * a.shortValue();
    }

    @Override
    public boolean moreThan(Amount amount) {
        return this.getAmountValue().shortValue() > amount.getAmountValue().shortValue();
    }

    @Override
    public boolean lessThan(Amount amount) {
        return this.getAmountValue().shortValue() < amount.getAmountValue().shortValue();
    }

    @Override
    public boolean equal(Amount amount) {
        return this.getAmountValue().shortValue() == amount.getAmountValue().shortValue();
    }

    @Override
    public boolean isNegative() {
        return this.getAmountValue().shortValue()<0;
    }
}
