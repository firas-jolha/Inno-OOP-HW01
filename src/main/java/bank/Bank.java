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
    private String name = "Bank-"+ UUID.randomUUID().toString();
    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private Amount totalMoney = Amount.getAmountInstance(0.0);

    public Bank(){}

    public Bank(String name, Amount totalAmount) {
        if (Utils.notNull(name, totalAmount)) {
            this.name = name;
            this.totalMoney = totalAmount;
        }
    }

    public Amount getTotalMoney() {
        return totalMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Utils.notNull(name) && !name.equals(""))
            this.name = name;
    }

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
        boolean status = true; // will track the status of success of operation
        // checks if new client is existed in system
        // duplicate clients is forbidden
        if (clients.contains(client)) {
            System.out.println(ErrorMessages.CLIENT_EXIST_IN_SYSTEM);
            return false;
        }
        // checks if new client is existed
        if (Utils.notNull(client)) {
            // add the new client to the list of clients
            status &= clients.add(client);
            // Iterates through the accounts of client to add them
            // to list of accounts in the bank
            for (int i = 0; i < client.getAccountsSize(); i++) {
                // Calls the addAccount method in class Client
                // getAccountByIndex method will return the account according the passed param
                status &= this.addAccount(client.getAccountByIndex(i)); //, this.clients.lastIndexOf(client)
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
     * @param account
     * @return
     */
    public boolean addAccount(Account account) {
        try {
//            Client client = clients.get(clientIndex);
            Client client = null;
            if (Utils.notNull(account)) client = account.getClient();
            else {
                System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
                return false;
            }
            if(accounts.contains(account)){ // client.accountExists(account) ||
                System.out.println(ErrorMessages.RESERVED_ACCOUNT);
                return false;
            }else{
                this.accounts.add(account);
                return true;
            }
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ErrorMessages.NOT_EXISTED_CLIENT);
            return false;
        }
    }
//
//
//    public boolean addAccount(Account account){
//        if (Utils.notNull(account))
//            return this.accounts.add(account);
//        return false;
//    }

    public String listClients() {
        String out = "";
        if (clients.size() == 0) {
            out += "We don't have any client\n";
        } else {
            out += String.format("We have %d client(s)\n", clients.size());
        }
        for (Client client : clients) {
            out += client.toString() + "\n";
        }
        return out;
    }

    public static String listAccounts(List<Account> accounts) {
        String out = "";
        if (accounts.size() == 0) {
            out += "We don't have any accounts\n";
        } else {
            out += String.format("We have %d account(s)\n", accounts.size());
        }
        for (Account account : accounts) {
            out += account.toString() + "\n";
        }
        return out;
    }

    public String listAccounts() {
        return listAccounts(accounts);
    }

    public String listAccountByClient(Client client) {
        if (client == null) return "That client isn't existed!";
        return listAccounts();
    }

    public String listOperationsByAccount(Account account) {
        synchronized (account) {
            if (account == null) return "Account is not existed";
            return account.toString();
        }
    }


}
