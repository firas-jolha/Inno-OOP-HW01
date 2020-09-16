package bank.accounts;

import bank.clients.Client;
import bank.utils.amount.Amount;

public class CreditAccount extends Account {

    public CreditAccount(Client client, Amount amount) {
        super(client, amount);
    }
//
//    public CreditAccount(Client client) {
//        super(client);
//    }

    @Override
    public void setBalance(Amount balance) {
        super.setBalance(balance);
        // negative value is possible
    }
}
