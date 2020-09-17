package bank.accounts;

import bank.utils.amount.Amount;

/**
 * An interface describes the account operations
 */
public interface IAccount {

    boolean withdraw(Amount amount);

    boolean deposit(Amount amount);

    boolean transfer(IAccount account2, Amount amount);

}
