package bank.accounts;

import bank.utils.BankOperationType;
import bank.utils.ErrorMessages;
import bank.utils.Utils;
import bank.utils.amount.Amount;

/**
 * Represents the available operations on account for creating history of performed operations
 */
public class BankOperation {
    // Bank Operation Type
    private BankOperationType operationType;
    // First Account
    private Account account;
    // Second Account
    private Account account2;
    // amount of money
    private Amount amount;

    /**
     * Creates a new bank operation
     * @param operationType The type of operation (Withdrawal, Deposit,...)
     * @param account The account which initiates the operation
     * @param account2 The account which receives the result, could
     * @param amount The amount of money
     */
    public BankOperation(BankOperationType operationType, Account account, Account account2, Amount amount) {
        if (Utils.notNull(operationType, account, account2, amount)){
            this.operationType = operationType;
            this.account = account;
            this.account2 = account2;
            this.amount = amount;
        }else{
            System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        }
    }

    public BankOperation(BankOperationType operationType, Account account, Amount amount) {
        this(operationType, account, account, amount);
    }

    @Override
    public String toString() {
        String out = "";
        out += "\nBankOperation{" +
                " operationType = " + operationType+" ";
        switch (operationType){
            case DEPOSIT:
                out += "to Account={"+account.getId()+"}";
                break;
            case TRANSFER:
                out += "from Account={"+account.getId()+"} to Account={"+account2.getId()+"}";
                break;
            case WITHDRAW:
                out += "from Account={"+account.getId()+"}";
                break;
            default:
        }
        out += ", amount="+amount+"}";
        return out;
    }
}
