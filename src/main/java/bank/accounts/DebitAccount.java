package bank.accounts;

import bank.clients.Client;
import bank.utils.amount.Amount;

public class DebitAccount extends Account {
    public DebitAccount(Amount amount) {
        super(amount);
    }
//
//    public DebitAccount(Client client) {
//        super(client);
//    }
}
