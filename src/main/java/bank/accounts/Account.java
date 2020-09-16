package bank.accounts;

import bank.clients.Client;
import bank.clients.RegularClient;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;
import bank.utils.Utils;
import bank.utils.amount.DoubleAmount;

/**
 * The {@code Account} is the base class of all {@code Account}
 * classes. It's an abstract class, because we cannot create an
 * account without specifying the type of account.<br> It implements
 * {@code IAccount} interface which preserves that every account
 * should support three operations {@code deposit}, {@code withdraw}
 * and {@code transfer} operations
 */
public abstract class Account implements IAccount {

    /**
     * The {@code balance} attribute holds the actual amount money in the account.
     */
    private Amount balance = new DoubleAmount(0.0);
//
//    /**
//     * The {@code client} attribute specifies the <b>owner</b> of the account
//     */
//    private final Client client;

    /**
     * <b>Constructor</b> with 2 parameters.
     *
     * If the {@code client} is {@code null}, a new {@code RegularClient}
     * will be created with default attribute values.
//     *
//     * @param client the owner of the account
     * @param amount the initial amount of money in the account
     */
    public Account(Amount amount) {
////        if(client != null) this.client = client;
//        else {
//            System.out.println(ErrorMessages.NOT_EXISTED_CLIENT);
//            this.client = new RegularClient();
//        }
        setBalance(amount);
    }

    /**
     * <b>Constructor</b> with 1 parameter.
     *
     * This constructor will call the generic constructor with 2 parameters but
     * the default value for balance will be 0.0
     *
     * @param client the owner of the account
     */
//    public Account(Client client) {
//        this(client, Amount.getAmountInstance(0.0));
//    }

    /**
     * Sets the {@code balance}
     *
     * @param balance the new balance
     */
    public void setBalance(Amount balance) {
        if (balance != null)
            this.balance = balance;
    }

    /**
     * Gets the {@code balance}
     *
     * @return the balance of the account
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Gets the owner of the account
     *
     * @return the client
     */
//    public Client getClient() {
//        return client;
//    }

    /**
     * Adds the specified amount to the balance of the account.
     * If the amount is null, then the balance won't be affected.
     *
     * @param amount the amount of money to deposit
     * @return true for successful deposit, otherwise false
     */
    public boolean deposit(Amount amount) {
        // If the amount or balance are not existed, there will be unsuccessful deposit operation
        if (amount == null || this.balance == null) return false;
        /* The following line will add the balance amount to the deposit amount
         * and create a new Amount object depending on the type of Amount
         */
        Amount changedAmount = Amount.getAmountInstance(this.balance.sum(amount.getAmountValue()));

        setBalance(changedAmount); // Sets the new balance of the account
        return true; // Successful deposit operation
    }
    /**
     * Subtracts the specified amount from the balance of the account.
     * If the amount is null, then the balance won't be affected.
     *
     * @param amount the amount of money to withdraw
     * @return true for successful withdraw, otherwise false
     */
    public boolean withdraw(Amount amount) {

        // if the withdrawal amount is not existed or less than the balance
        // we refuse the withdraw operation
        if (!Utils.notNull(amount, this.balance) ||
                this.getBalance().lessThan(amount))
            return false;
        /* The following line will subtract the balance amount from the withdrawal amount
         * and create a new Amount object depending on the type of Amount
         */
        Amount changedAmount = Amount.getAmountInstance(this.getBalance().sub(amount.getAmountValue()));
        setBalance(changedAmount);// Sets the new balance of the account
        return true; // Successful withdrawal operation
    }

    /**
     * Transfers the specified amount of money from the current account
     * to the another account <code> account </code>
     *
     * @param account the recipient of the transferred amount
     * @param amount the transfer amount
     * @return status of operation success
     */
    public boolean transfer(Client client, IAccount account, Amount amount) {
        // If any of the params isn't existed, the operation will be cancelled
        if (!Utils.notNull(account, amount)) return false;
        // this will first withdraw then deposit to the another account
        return this.withdraw(amount) && account.deposit(amount); // Successful/Unsuccessful transfer operation
    }

    /**
     * Returns a string contains all info about the account.
     *
     * @return info string about the account
     */
    @Override
    public String toString() {
        return "Account{" +
                "amount=" + balance +
                '}';
    }
}
