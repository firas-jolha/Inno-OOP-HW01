package bank.accounts;

import bank.clients.Client;
import bank.utils.amount.Amount;

import java.time.LocalDate;

public class SavingAccount extends Account {

    // By default the first saving date is the current date plus 6 months
    private LocalDate savingDate = LocalDate.now().plusMonths(6);

    public SavingAccount(LocalDate savingDate, Amount amount) {
        super(amount);
        setSavingDate(savingDate);
    }

    public SavingAccount(LocalDate savingDate) {
        this(savingDate,Amount.getAmountInstance(0.0));
    }

    public LocalDate getSavingDate() {
        return savingDate;
    }

    public void setSavingDate(LocalDate savingDate) {
        if (savingDate.isAfter(savingDate))
            this.savingDate = savingDate;
    }

    @Override
    public boolean withdraw(Amount amount) {
        if (!LocalDate.now().isBefore(savingDate))
            return super.withdraw(amount);
        return false;
    }
}
