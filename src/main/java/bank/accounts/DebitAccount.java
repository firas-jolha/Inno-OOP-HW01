package bank.accounts;

import bank.clients.Client;
import bank.utils.amount.Amount;

public class DebitAccount extends Account {
    public DebitAccount(Client client, Amount amount) {
        super(client, amount);
    }

    // Ensures that balance >= 0
    @Override
    public boolean balanceCondition(Amount balance) {
        return !balance.isNegative();
    }


}
