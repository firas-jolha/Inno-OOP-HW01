package bank.accounts;

import bank.clients.Client;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;

import java.time.LocalDate;

public class SavingAccount extends Account {

    // By default the first saving date is the current date plus 1 month
    private LocalDate savingDate = LocalDate.now().plusMonths(1);

    /**
     * Creates a Saving Account
     * @param client the account owner
     * @param savingDate the date before which the withdrawal operation is forbidden
     * @param amount the amount of money
     */
    public SavingAccount(Client client, LocalDate savingDate, Amount amount) {
        super(client, amount);
        setSavingDate(savingDate);
    }

    // Getter method for savingDate
    public LocalDate getSavingDate() {
        return savingDate;
    }

    // Ensures that balance >= 0
    @Override
    public boolean balanceCondition(Amount balance) {
        return !balance.isNegative();
    }

    // Setter method for savingDate
    public void setSavingDate(LocalDate savingDate) {
        // It's not allowed to set a new date before the saving date
        if (savingDate.isAfter(this.savingDate))
            this.savingDate = savingDate;
        else
            System.out.println(ErrorMessages.NOT_VALID_DATE);
    }

    /**
     * Withdraws money from the account but with a condition applied on savingDate
     * @param amount the amount of money to withdraw
     * @return status of operation
     */
    @Override
    public boolean withdraw(Amount amount) {
        if (!LocalDate.now().isBefore(savingDate))
            return super.withdraw(amount);
        System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        return false;
    }

    @Override
    public String toString() {
        return super.toString()+
                "savingDate=" + getSavingDate()+"\n";
    }
}
