package bank.accounts;

import bank.clients.Client;
import bank.clients.GoldenCardClient;
import bank.clients.RegularClient;
import bank.clients.VIPClient;
import bank.utils.ErrorMessages;
import bank.utils.Utils;
import bank.utils.amount.Amount;

public class BusinessAccount extends Account {


    public BusinessAccount(Amount amount) {
        super(amount);
    }

    @Override
    public boolean transfer(Client client, IAccount account2, Amount amount) {
        if (!Utils.notNull(account2, amount)) {
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }
        if (!client.accountExists(this)){
            System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
            return false;
        }

        double fees = 0.0;
        if (client instanceof RegularClient) {
            fees = .02;
        } else if (client instanceof GoldenCardClient) {
            fees = .01;
        } else if (client instanceof VIPClient) {
            fees = 0.0; // doesnt change default value
        }
        amount.setAmountValue(amount.sub(amount.mul(fees)));
        return super.transfer(client, account2, amount);
    }

    @Override
    public void setBalance(Amount balance) {
        if (balance.moreThan(Amount.getAmountInstance(0.0)) ||
                balance.equal(Amount.getAmountInstance(0.0)))
            super.setBalance(balance);
    }
}
