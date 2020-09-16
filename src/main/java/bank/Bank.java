package bank;

import bank.accounts.Account;
import bank.clients.Client;
import bank.utils.amount.Amount;
import bank.utils.amount.DoubleAmount;
import bank.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String name;
    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private Amount totalMoney;

    public Bank() {
        this("Bank-"+ UUID.randomUUID().toString(), Amount.getAmountInstance(0.0));
    }

    public Bank(String name, Amount totalAmount) {
        this.name = name;
        this.totalMoney = totalAmount;
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
        for (Account account: accounts){
            total = account.getBalance().sum(total);
        }
        return true;
    }

    public boolean addClient(Client client) {
        if (Utils.notNull(client)) {
            for (int i = 0; i < client.getAccountsSize(); i++) {
                this.accounts.add(client.getAccountByIndex(i));
            }
            return clients.add(client);
        }
        return false;
    }

    public boolean addAccount(Account account){
        if (Utils.notNull(account))
            return this.accounts.add(account);
        return false;
    }

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
