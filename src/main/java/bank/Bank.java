package bank;

import bank.accounts.Account;
import bank.clients.Client;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;
import bank.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    // Bank name that could be generated randomly automatically
    private String name = "Bank-"+ UUID.randomUUID().toString();
    // list of accounts
    private final List<Client> clients = new ArrayList<>();
    // list of clients
    private final List<Account> accounts = new ArrayList<>();
    // total of money on all accounts
    private Amount totalMoney = Amount.getAmountInstance(0.0);

    /**
     * Creates a Bank object
     * @param name the bank name
     */
    public Bank(String name) {
        if (Utils.notNull(name) && !name.equals("")) {
            this.name = name;
        }
    }

    /**
     * Getter method of totalMoney
     * @return the total amount of money in all accounts
     */
    public Amount getTotalMoney() {
        return totalMoney;
    }

    /**
     * Getter method of name
     * @return name of bank
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * @param name the bank name
     */
    public void setName(String name) {
        if (Utils.notNull(name) && !name.equals(""))
            this.name = name;
    }

    // Update method of totalMoneyField
    public boolean updateTotalMoneyField(){
        Number total = 0.0;
        try{
            for (Account account: accounts){
                total = account.getBalance().sum(total);
            }
            this.totalMoney = Amount.getAmountInstance(total);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    // Update of all fields (only 1 field here)
    public boolean update(){
        return updateTotalMoneyField();
    }

    /**
     * Adds a new client to the list of clients,
     *
     * @param client the new client
     * @return status of adding a new client
     */
    public boolean addClient(Client client) {
        boolean status; // will track the status of operation

        // checks if new client is existed
        if (Utils.notNull(client)) {
            // checks if new client is existed in system
            // duplicate clients is forbidden
            if (clients.contains(client)) {
                System.out.println(ErrorMessages.CLIENT_EXIST_IN_SYSTEM);
                return false;
            }
            // add the new client to the list of clients
            status = clients.add(client);
            // Iterates through the accounts of client to add them
            // to list of accounts in the bank
            for (int i = 0; i < client.getAccountsSize(); i++) {
                // Calls the addAccount method in class Client
                // getAccountByIndex method will return the account according the passed param
                status &= this.addAccount(client.getAccountByIndex(i));
            }
            status &= update(); // update all fields in bank like totalMoneyField
            return status;
        } else{
            System.out.println(ErrorMessages.NOT_EXISTED_CLIENT);
            return false;
        }
    }

    /**
     * Adds the new account the list of accounts
     * @param account the new account
     * @return status of operation
     */
    private boolean addAccount(Account account) {
        try {
            if (!Utils.notNull(account)) { // Checks if account is existed
                System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
                return false;
            }
            if(accounts.contains(account)){ // Checks if the account is existed in accounts' list
                System.out.println(ErrorMessages.RESERVED_ACCOUNT);
                return false;
            }else{
                // adds the account to the accounts' list
                this.accounts.add(account);
                return true;
            }
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ErrorMessages.NOT_EXISTED_CLIENT);
            return false;
        }
    }


    /**
     * Lists the clients of bank as a String
     * @return clients' info
     */
    public String listClients() {
        StringBuilder out = new StringBuilder();
        if (clients.size() == 0) {
            out.append("We don't have any client\n");
        } else {
            out.append(String.format("We have %d client(s)\n", clients.size()));
        }
        for (Client client : clients) {
            out.append(client.toString()).append("\n");
        }
        return out.toString();
    }


    /**
     * Lists the accounts of bank as a String (static method)
     * @return accounts' info
     */
    public static String listAccounts(List<Account> accounts) {
        StringBuilder out = new StringBuilder("\n");
        if (accounts.size() == 0) {
            out.append("We don't have any accounts in the Bank\n");
        } else {
            out.append(String.format("We have %d account(s) in the Bank\n", accounts.size()));
        }
        for (int i = 0; i < accounts.size(); i++) {
            out.append("(").append(i).append(")").append(accounts.get(i).toString()).append("\n");
        }
        return out.toString();
    }

    /**
     * Lists all accounts of the client as a String
     * @return account's info
     */
    public String listAccounts() {
        return listAccounts(accounts);
    }

    /**
     * Lists all accounts of the client as a String
     * @param client the accounts' owner
     * @return the accounts as a String
     */
    public String listAccountsByClient(Client client) {
        if (client == null) return ErrorMessages.NOT_VALID_ACCOUNT_CLIENT.toString();
        return client.listAccounts();
    }

    /**
     * Lists all operations performed on account
     * @param account the account under consideration
     * @return operation's info as a String
     */
    public String listOperationsByAccount(Account account) {
            if (account == null) return ErrorMessages.NOT_EXISTED_ACCOUNT.toString();
            return account.listOperations();
    }
}
