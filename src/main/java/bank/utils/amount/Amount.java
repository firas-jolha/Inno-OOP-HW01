package bank.utils.amount;

import bank.utils.Utils;

/**
 * Represents an amount which has a value
 * of type Number (Integer, Short, Double, ....)
 * The main idea is the ability to create different types of amount.
 * The type of amount depends on the value of {@code amountValue} data member.
 */
public abstract class Amount {

    private Number amountValue;

    // Set of abstract methods which should be implemented.
    public abstract Number sum(Number a); // Adds the amountValue to a

    public abstract Number sub(Number a);// Subtracts the amountValue from a

    public abstract Number mul(Number a);// Multiplies the amountValue with a

    public abstract boolean moreThan(Amount amount);// Compares the amountValue > a ?

    public abstract boolean lessThan(Amount amount);// Compares the amountValue < a ?

    public abstract boolean equal(Amount amount);// Compares the amountValue == a ?

    public abstract boolean isNegative(); // Checks if amount value is negative/positive

    //Constructor
    public Amount(Number amount) {
        if (amount == null) this.amountValue = 0.0;
        // calling the setter method to set the value of amount
        this.setAmountValue(amount);
    }

    /**
     * Gets the amount value
     * @return value of amount
     */
    public Number getAmountValue() {
        return amountValue;
    }

    /**
     * Sets the amount value
     * @param amount the new amount
     */
    public void setAmountValue(Number amount) {
        this.amountValue = amount;
    }


    /**
     * Creates an Amount object according to type of param amount
     * It's a static factory method for Amount objects
     *
     * @param amount the value of amount to be created
     * @return an instance of Amount object
     */
    public static Amount getAmountInstance(Number amount) {
        // if amount is null so assign 0.0 to it ==> DoubleAmount
        if (!Utils.notNull(amount)) amount = 0.0;
        // create an Amount object of type Double if param amount is Double
        if (amount instanceof Double)
            return new DoubleAmount(amount);
        // create an Amount object of type Integer if param amount is Integer
        else if (amount instanceof Integer)
            return new IntegerAmount(amount);
        // create an Amount object of type Double if param amount is Double
        else
            return new DoubleAmount(amount);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + amountValue +
                '}';
    }
}
