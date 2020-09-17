package bank.accounts;

import bank.clients.Client;
import bank.clients.RegularClient;
import bank.utils.BankOperationType;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;
import bank.utils.Utils;
import bank.utils.amount.DoubleAmount;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The {@code Account} is the base class of all {@code Account}
 * classes. It's an abstract class, because we cannot create an
 * account without specifying the type of account. It implements
 * {@code IAccount} interface which preserves that every account
 * should support three operations {@code deposit}, {@code withdraw}
 * and {@code transfer} operations
 */
public abstract class Account implements IAccount {

    /**
     * The {@code balance} attribute holds the actual amount money in the account.
     */
    private Amount balance = new DoubleAmount(0.0);

    /**
     * The {@code client} attribute specifies the <b>owner</b> of the account
     */
    private Client client;

    /**
     * list of operations applied on the account (history of account operations)
     */
    private final ArrayList<BankOperation> operations = new ArrayList<>();

    /**
     * An automatically-generated unique Id for each account
     */
    private final String Id = "Account-"+ UUID.randomUUID().toString();

    /**
     * <b>Constructor</b> with 2 parameters (the client and amount).
     *
     * If the {@code client} is {@code null}, a new {@code RegularClient}
     * will be created with default attribute values.
     *
     * @param client the owner of the account
     * @param amount the initial amount of money in the account
     */
    public Account(Client client, Amount amount) {
        // Check if client is existed
        if(client != null) this.client = client;
        else { // not existed client, so create a typical client
            System.out.println(ErrorMessages.NOT_EXISTED_CLIENT);
            this.client = new RegularClient();
        }
        setBalance(amount); // sets the new balance
        if (!this.client.addAccount(this)) {// adds the current account to the list of accounts of the client
            // This ensures that every account created should have an owner
            // This prevents random assigning of accounts to clients
            System.out.println(ErrorMessages.UNDEFINABLE_ERROR);
        }
    }

    /**
     * Sets the {@code balance}
     *
     * @param balance the new balance
     */
    public void setBalance(Amount balance) {
        if (Utils.notNull(balance) && balanceCondition(balance))
            this.balance = balance;
        else System.out.println(ErrorMessages.NOT_VALID_AMOUNT);
    }

    /**
     * Defines the condition applied on balance parameter
     * @param balance the new balance
     * @return the condition on balance
     */
    public abstract boolean balanceCondition(Amount balance);

    /**
     * Gets the Id of account
     *
     * @return Id of account
     */
    public String getId() {
        return Id;
    }

    /**
     * Gets the {@code balance}
     *
     * @return the balance of the account
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Gets the owner of the account
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the owner of the account
     *
     * @param client the new owner of the account
     */
    public void setClient(Client client) {
        if (Utils.notNull(client))
            this.client = client;
        else
            System.out.println(ErrorMessages.NOT_EXISTED_CLIENT);
    }

    /**
     * Adds the specified amount to the balance of the account.
     * If the amount is null, then the balance won't be affected.
     *
     * @param amount the amount of money to deposit
     * @return true for successful deposit, otherwise false
     */
    public boolean deposit(Amount amount) {
        // If the amount or balance are not existed, there will be unsuccessful deposit operation
        if (amount == null || this.balance == null) {
            System.out.println(ErrorMessages.NOT_VALID_AMOUNT);
            return false;
        }
        /* The following line will add the balance amount to the deposit amount
         * and create a new Amount object depending on the type of Amount
         */
        Amount changedAmount = Amount.getAmountInstance(this.balance.sum(amount.getAmountValue()));

        setBalance(changedAmount); // Sets the new balance of the account
        operations.add(new BankOperation(BankOperationType.DEPOSIT,this, amount)); // adds the deposit operation to list of operations
        return true; // Successful deposit operation
    }

    /**
     * Subtracts the specified amount from the balance of the account.
     * If the amount is null, then the balance won't be affected.
     *
     * @param amount the amount of money to withdraw
     * @return true for successful withdraw, otherwise false
     */
    public boolean withdraw(Amount amount) {
        // if the withdrawal amount is not existed or less than the balance
        // we refuse the withdraw operation
        if (!Utils.notNull(amount, this.balance) ||
                this.getBalance().lessThan(amount)) {
            System.out.println(ErrorMessages.NOT_VALID_AMOUNT);
            return false;
        }
        /* The following line will subtract the balance amount from the withdrawal amount
         * and create a new Amount object depending on the type of Amount
         */
        Amount changedAmount = Amount.getAmountInstance(this.getBalance().sub(amount.getAmountValue()));
        setBalance(changedAmount);// Sets the new balance of the account
        operations.add(new BankOperation(BankOperationType.WITHDRAW,this,amount));
        return true; // Successful withdrawal operation
    }

    /**
     * Transfers the specified amount of money from the current account
     * to the another account <code> account </code>
     *
//     * @param account the recipient of the transferred amount
     * @param amount the transfer amount
     * @return status of operation success
     */
    public boolean transfer(IAccount account, Amount amount) {
        // If any of the params isn't existed, the operation will be cancelled
        if (!Utils.notNull(account, amount)) {
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }
        // this will first withdraw then deposit to the another account
        if (this.withdraw(amount) && account.deposit(amount)){// Successful/Unsuccessful transfer operation
            operations.add(new BankOperation(BankOperationType.TRANSFER,this, (Account) account, amount));
            try {
                // removes the last withdrawal operation
                this.operations.remove(operations.size()-2);
                Account acc = (Account) account;
                // removes the last deposit operation
                acc.operations.remove(acc.operations.size()-1);
                return true;
            }catch (Exception ex){
                System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
                return false;
            }
        }else return false;
    }


    /**
     * Returns all operations as a String
     * @return string of operations' info
     */
    public String listOperations(){
        return operations.toString();
    }

    /**
     * Returns a string contains all info about the account.
     *
     * @return info string about the account
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+" :\n" +
                "id=" + Id +
                ", balance=" + balance +
                ",\n"+operations.size()+" operations : \n" + operations+"\n";
    }
}
