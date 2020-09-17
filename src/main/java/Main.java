import bank.Bank;
import bank.accounts.*;
import bank.clients.Client;
import bank.clients.GoldenCardClient;
import bank.clients.RegularClient;
import bank.clients.VIPClient;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;
import bank.utils.Gender;

import java.time.LocalDate;

/**
 * @author firasjolha
 * @since 2020 sep.
 *
 */
public class Main {
    public static void main(String[] args) {
        // Creating 4 clients
        Client[] clients;
        clients = new Client[4];
        clients[0] = new RegularClient("Firas - Regular Client", LocalDate.of(1992, 6, 20), Gender.Male);
        clients[1] = new GoldenCardClient("Alissa - Golden Client", LocalDate.of(1992, 2,9), Gender.Female);
        clients[2] = new VIPClient("Jade - VIP Client", LocalDate.of(1992, 10,2), Gender.Female);
        clients[3] = new RegularClient();

        // Creating 5 accounts
        // We assign the client (account owner) as a parameter to ensure that each account is assigned to only 1 client
        Account[] accounts;
        accounts = new Account[5];
        accounts[0] = new BusinessAccount(clients[0], Amount.getAmountInstance(1000.0));
        accounts[1] = new CreditAccount(clients[1], Amount.getAmountInstance(1000.0));
        accounts[2] = new DebitAccount(clients[2], Amount.getAmountInstance(1000.0));
        accounts[3] = new SavingAccount(clients[0], LocalDate.now().plusMonths(2), Amount.getAmountInstance(1000.0));
        accounts[4] = new BusinessAccount(clients[2], Amount.getAmountInstance(1000.0));
        //The following statement will print error
        // because this account belongs to another client
//        clients[0].addAccount(accounts[0]); // Uncomment


        // Creating a Bank
        Bank bank = new Bank("");
        System.out.println("Default bank name = "+bank.getName());
        bank.setName("Sberbank");
        System.out.println("Bank name = "+bank.getName());
        // adding clients
        bank.addClient(clients[0]);
        System.out.println("Total Money till now = "+bank.getTotalMoney());
        bank.addClient(clients[1]);
        System.out.println("Total Money till now = "+bank.getTotalMoney());
        bank.addClient(clients[2]);
        System.out.println("Total Money till now = "+bank.getTotalMoney());
        bank.addClient(clients[3]);
        System.out.println("Total Money till now = "+bank.getTotalMoney());
        // Uncomment the following line
//        bank.addClient(clients[3]); // Prints error because duplicate clients is forbidden in bank
        System.out.println(bank.listAccounts());
        System.out.println(bank.listClients());
        System.out.println("Total Money = "+bank.getTotalMoney()+"\n");
        System.out.println("==========================================\n");
        if (accounts[1].deposit(Amount.getAmountInstance(120.0))){
            System.out.println("Deposit performed to "+accounts[1].getId());
            System.out.println("Operations in account "+accounts[1].getId());
            System.out.println(bank.listOperationsByAccount(accounts[1]));
        }else{
            System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        }
        System.out.println();
        if (accounts[1].withdraw(Amount.getAmountInstance(120.0))){
            System.out.println("Withdraw performed from "+accounts[1].getId());
            System.out.println("Operations in account "+accounts[1].getId());
            System.out.println(bank.listOperationsByAccount(accounts[1]));
        }else{
            System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        }
        System.out.println();
        if (accounts[0].transfer(accounts[1], Amount.getAmountInstance(150.0))){
            System.out.println("Transfer performed from "+accounts[0].getId()+" to "+accounts[1].getId());
            System.out.println("Operations in account "+accounts[0].getId());
            System.out.println(bank.listOperationsByAccount(accounts[0]));
            System.out.println("Operations in account "+accounts[1].getId());
            System.out.println(bank.listOperationsByAccount(accounts[1]));
        }else{
            System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        }
        System.out.println();
        if (accounts[0].transfer(accounts[2], Amount.getAmountInstance(150.0))){
            System.out.println("Transfer performed from "+accounts[0].getId()+" to "+accounts[2].getId());
            System.out.println("Operations in account "+accounts[0].getId());
            System.out.println(bank.listOperationsByAccount(accounts[0]));
            System.out.println("Operations in account "+accounts[2].getId());
            System.out.println(bank.listOperationsByAccount(accounts[2]));
        }else {
            System.out.println(ErrorMessages.NOT_PERMITTED_OPERATION);
        }

        System.out.println(bank.listAccounts());
        System.out.println(bank.listClients());
        System.out.println(bank.listAccountsByClient(clients[0]));// lists the accounts of client 0

        System.out.println("Checking the Credit Account condition\n");
        //accounts[1] instance of CreditAccount
        // max credit amount = -7000
        accounts[1].setBalance(Amount.getAmountInstance(-2000)); // This is valid
        accounts[1].setBalance(Amount.getAmountInstance(-9000)); // This is not valid because it exceeds the negative limit -7000
        accounts[2].setBalance(Amount.getAmountInstance(-12)); // This is not valid because accounts doesn't accept negative values

        System.out.println("\nChecking the Business Account condition\n");
        Client c1 = new RegularClient();
        Client c2 = new VIPClient("VIP client", LocalDate.of(1990,12,3),Gender.Male);
        Client c3 = new GoldenCardClient("Golden Card client", LocalDate.of(1960,12,3),Gender.Male);
        Account b1 = new BusinessAccount(c1, Amount.getAmountInstance(1200.0));
        Account b2 = new BusinessAccount(c2, Amount.getAmountInstance(1200.0));
        Account b3 = new BusinessAccount(c3, Amount.getAmountInstance(1200.0));
        b1.transfer(b2, Amount.getAmountInstance(1000.0));
        b2.transfer(b3, Amount.getAmountInstance(1000.0));
        b3.transfer(b1, Amount.getAmountInstance(1000.0));
        System.out.println(b1.listOperations());
        System.out.println(b2.listOperations());
        System.out.println(b3.listOperations());

    }
}
