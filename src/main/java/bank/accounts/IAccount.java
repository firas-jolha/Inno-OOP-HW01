package bank.accounts;

import bank.clients.Client;
import bank.utils.amount.Amount;


public interface IAccount {

    boolean withdraw(Amount amount);

    boolean deposit(Amount amount);
//    boolean transfer(Client client, IAccount account2, Amount amount);
    boolean transfer(IAccount account2, Amount amount);

}
