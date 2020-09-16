package bank.utils.amount;

public class DoubleAmount extends Amount {

    public DoubleAmount(Number a) {
        super(a);
    }

    public void setAmountValue(Number amount) {
        if (amount.doubleValue() < 0.0) amount = 0.0;
        super.setAmountValue(amount);
    }

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


}
