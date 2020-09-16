package bank.utils.amount;

public class ShortAmount extends Amount {

    public ShortAmount(Number amount) {
        super(amount);
    }

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
}
