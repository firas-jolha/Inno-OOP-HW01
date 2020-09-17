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
        Client[] clients;
        clients = new Client[4];
        clients[0] = new RegularClient("Firas - Regular Client", LocalDate.of(1992, 6, 20), Gender.Male);
        clients[1] = new GoldenCardClient("Alissa - Golden Client", LocalDate.of(1992, 2,9), Gender.Female);
        clients[2] = new VIPClient("Jade - VIP Client", LocalDate.of(1992, 10,2), Gender.Female);
        clients[3] = new RegularClient();

        Account[] accounts;
        accounts = new Account[5];
        accounts[0] = new BusinessAccount(clients[0], Amount.getAmountInstance(1000));
        accounts[1] = new CreditAccount(clients[1], Amount.getAmountInstance(1000));
        accounts[2] = new DebitAccount(clients[2], Amount.getAmountInstance(1000));
        accounts[3] = new SavingAccount(clients[0], LocalDate.now().plusMonths(2), Amount.getAmountInstance(1000));
        accounts[4] = new BusinessAccount(clients[2], Amount.getAmountInstance(1000));
        //The following statement will print error
        // because this account belongs to another client
//        clients[0].addAccount(accounts[0]); // Uncomment

        Bank bank = new Bank("Sberbank");
        bank.addClient(clients[0]);
        bank.addClient(clients[1]);
        bank.addClient(clients[2]);
        bank.addClient(clients[3]);
        // Uncomment the following line
//        bank.addClient(clients[3]); // Prints error because duplicate clients is forbidden in bank
        System.out.println(bank.listAccounts());
        System.out.println(bank.listClients());
        System.out.println(accounts[1].deposit(Amount.getAmountInstance(120.0)));
        System.out.println(accounts[0].transfer(accounts[1], Amount.getAmountInstance(150.0)));
        System.out.println(accounts[0].transfer(accounts[2], Amount.getAmountInstance(150.0)));
        System.out.println(bank.listAccounts());
        System.out.println(bank.listClients());
        System.out.println(bank.listOperationsByAccount(accounts[0]));

//
//        Amount i = Amount.getAmountInstance(7);
//        Amount d = Amount.getAmountInstance(12.3);
//        System.out.println(d.moreThan(Amount.getAmountInstance(12.2)));
//        System.out.println(i.mul(4.9));

        /* We have 4 commands
        1- deposit money to account
        2- withdraw money from account
        3- transfer money from account to account
        4- list all accounts
        5- list all accounts
        6- compute total money in all accounts
        7- list all operations by account
        8- create an account
        9- create a client
        10- add client
        11- remove client
        */
        System.exit(0);
//        ArrayList<Client> clients = new ArrayList<>();
//        ArrayList<Account> accounts = new ArrayList<>();
//        CommandLineOption option;
//        Amount amount = Amount.getAmountInstance(0.0);
//        for (int i = 0; i < args.length; i++) {
////            CommandLine.parse(args);
//            if (args[i].startsWith("-")){ // option
//                switch (args[i].substring(1)){
//                    case "deposit": case "d":
//                        option = CommandLineOption.DEPOSIT;
//                        break;
//                    case "withdraw": case "w":
//                        option = CommandLineOption.WITHDRAW;
//                        break;
//                    case "transfer": case "t":
//                        option = CommandLineOption.TRANSFER;
//                        break;
//                    case "list": case "l":
//                        option = CommandLineOption.LIST;
//                        break;
//                    case "compute": case "c":
//                        option = CommandLineOption.COMPUTE;
////                        amount =
//                        break;
//                    case "amount": case "a":
//                        break;
//                    case "create": case "cr":
//                        break;
//                    case "add": case "ad":
//                        break;
//                    case "remove": case "re":
//                        break;
//                    default:
//                }
////                if (args[i].length()==2){
////                    switch ()
////                }else{
////
////                }
//            }else{
//
//            }
//        }

    }
}
