import bank.Bank;
import bank.accounts.*;
import bank.clients.Client;
import bank.clients.GoldenCardClient;
import bank.clients.RegularClient;
import bank.clients.VIPClient;
import bank.utils.amount.Amount;
import bank.utils.Gender;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Client clients[] = new Client[4];
        clients[0] = new RegularClient("Firas - Regular Client", LocalDate.of(1992, 6, 20), Gender.Male);
        clients[1] = new GoldenCardClient("Alissa - Golden Client", LocalDate.of(1992, 2,9), Gender.Female);
        clients[2] = new VIPClient("Jade - VIP Client", LocalDate.of(1992, 10,2), Gender.Female);

        Account accounts[] = new Account[5];
        accounts[0] = new BusinessAccount(clients[0], Amount.getAmountInstance(1000));
        accounts[1] = new CreditAccount(clients[1], Amount.getAmountInstance(1000));
        accounts[2] = new DebitAccount(clients[2], Amount.getAmountInstance(1000));
        accounts[3] = new SavingAccount(clients[0], LocalDate.now().plusMonths(2), Amount.getAmountInstance(1000));
//        accounts[0].setClient(clients[0]);
        Bank bank = new Bank();
        bank.addClient(clients[0]);
        bank.addClient(clients[1]);
        bank.addClient(clients[2]);
//        bank.addClient(clients[3]);
        bank.addAccount(accounts[0]);
        bank.addAccount(accounts[1]);
        bank.addAccount(accounts[2]);
        bank.addAccount(accounts[3]);
        System.out.println(bank.listAccounts());
        System.out.println(bank.listClients());
        System.out.println(accounts[1].deposit(Amount.getAmountInstance(120.0)));
        System.out.println(accounts[0].transfer(accounts[1], Amount.getAmountInstance(150.0)));
        System.out.println(accounts[0].transfer(accounts[2], Amount.getAmountInstance(150.0)));
        System.out.println(bank.listAccounts());

//
//        Amount i = Amount.getAmountInstance(7);
//        Amount d = Amount.getAmountInstance(12.3);
//        System.out.println(d.moreThan(Amount.getAmountInstance(12.2)));
//        System.out.println(i.mul(4.9));

    }
}
