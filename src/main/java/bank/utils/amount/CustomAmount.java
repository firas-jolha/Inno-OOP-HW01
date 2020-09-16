package bank.utils.amount;

public class CustomAmount extends Amount {

    public CustomAmount(Number amount) {
        super(amount);
    }

    @Override
    public Number sum(Number a) {
        return null;
    }

    @Override
    public Number sub(Number a) {
        return null;
    }

    @Override
    public Number mul(Number a) {
        return null;
    }

    @Override
    public boolean moreThan(Amount amount) {
        return false;
    }

    @Override
    public boolean lessThan(Amount amount) {
        return false;
    }

    @Override
    public boolean equal(Amount amount) {
        return false;
    }
}
