package bank.accounts;

import bank.clients.Client;
import bank.clients.GoldenCardClient;
import bank.clients.RegularClient;
import bank.clients.VIPClient;
import bank.utils.ErrorMessages;
import bank.utils.Utils;
import bank.utils.amount.Amount;

public class BusinessAccount extends Account {

    /**
     * Creates a Business Account
     * @param client the client
     * @param amount the initial amount of money
     */
    public BusinessAccount(Client client, Amount amount) {
        super(client, amount);
    }

    /**
     * Transfers the money but charges different fee rates for each client
     * 2% fees for Regular Client
     * 1% fees for Clients with Golden Card
     * no fees for VIP clients
     *
     * @param account2 the receiver of the amount of money
     * @param amount the transfer amount
     * @return status of operation
     */
    @Override
    public boolean transfer(IAccount account2, Amount amount) {
        // If any parameters are null, we cannot perform the operation
        if (!Utils.notNull(account2, amount)) {
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }
        double fees = 0.0;
        if (getClient() instanceof RegularClient) {
            fees = .02;
        } else if (getClient() instanceof GoldenCardClient) {
            fees = .01;
        } else if (getClient() instanceof VIPClient) {
            fees = 0.0; // doesn't change default value
        }
        amount.setAmountValue(amount.sub(amount.getAmountValue().doubleValue()*(fees))); // we need to ensure that fees is not 0, so we convert it always into double
        return super.transfer(account2, amount);// Performs the transfer operation after calculating the fees
    }

    // Ensures that balance >= 0
    @Override
    public boolean balanceCondition(Amount balance) {
        return !balance.isNegative();
    }
}
