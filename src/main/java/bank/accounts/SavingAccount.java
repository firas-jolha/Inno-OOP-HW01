package bank.accounts;

import bank.clients.Client;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;

import java.time.LocalDate;

public class SavingAccount extends Account {

    // By default the first saving date is the current date plus 6 months
    private LocalDate savingDate = LocalDate.now().plusMonths(1);

    public SavingAccount(Client client, LocalDate savingDate, Amount amount) {
        super(client, amount);
        setSavingDate(savingDate);
    }

    public SavingAccount(Client client, LocalDate savingDate) {
        this(client, savingDate,Amount.getAmountInstance(0.0));
    }

    public LocalDate getSavingDate() {
        return savingDate;
    }

    public void setSavingDate(LocalDate savingDate) {
        if (savingDate.isAfter(this.savingDate))
            this.savingDate = savingDate;
        else
            System.out.println(ErrorMessages.NOT_VALID_DATE);
    }

    @Override
    public boolean withdraw(Amount amount) {
        if (!LocalDate.now().isBefore(savingDate))
            return super.withdraw(amount);
        System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        return false;
    }
}
