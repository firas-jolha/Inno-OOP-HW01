package bank.accounts;

import bank.clients.Client;
import bank.utils.amount.Amount;

public class CreditAccount extends Account {

    // The maximum negative amount of money that could be withdrawn from account
    private static final Amount MAX_CREDIT_AMOUNT = Amount.getAmountInstance(-7000);

    public CreditAccount(Client client, Amount amount) {
        super(client, amount);
    }

    // The new balance shouldn't be less than maximum credit amount
    @Override
    public boolean balanceCondition(Amount balance) {
        return !balance.lessThan(MAX_CREDIT_AMOUNT);
    }

}
