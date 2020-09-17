package bank.utils.amount;
/**
 * Represents Amount objects of value type Integer
 * A subclass of Amount
 */
public class IntegerAmount extends Amount {

    public IntegerAmount(Number amount) {
        super(amount);
    }

    //Other methods are just implementations of abstract methods in Amount class
    @Override
    public Number sum(Number a) {
        return this.getAmountValue().intValue() + a.intValue();
    }

    @Override
    public Number sub(Number a) {
        return this.getAmountValue().intValue() - a.intValue();
    }

    @Override
    public Number mul(Number a) {
        return this.getAmountValue().intValue() * a.intValue();
    }

    @Override
    public boolean moreThan(Amount amount) {
        return this.getAmountValue().intValue()>amount.getAmountValue().intValue();
    }


    @Override
    public boolean lessThan(Amount amount) {
        return (this.getAmountValue().intValue() < amount.getAmountValue().intValue());
    }

    @Override
    public boolean equal(Amount amount) {
        return (this.getAmountValue().intValue() == amount.getAmountValue().intValue());
    }

    @Override
    public boolean isNegative() {
        return getAmountValue().intValue()<0;
    }
}
