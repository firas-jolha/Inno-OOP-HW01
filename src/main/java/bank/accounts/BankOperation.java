package bank.accounts;

import bank.utils.BankOperationType;
import bank.utils.amount.Amount;

public class BankOperation {
    private BankOperationType operationType;
    private Account account;
    private Account account2;
    private Amount amount;

    public BankOperation(BankOperationType operationType, Account account, Account account2, Amount amount) {
        this.operationType = operationType;
        this.account = account;
        this.account2 = account2;
        this.amount = amount;
    }

    public BankOperation(BankOperationType operationType, Account account, Amount amount) {
        this(operationType, account, account, amount);
    }

    @Override
    public String toString() {
        String out = "";
        out += "BankOperation{" +
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
