package bank.clients;

import bank.accounts.Account;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;
import bank.utils.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Client {

    // Client name that could be generated automatically
    private String name = "client-" + UUID.randomUUID().toString();

    // Birthdate of client
    private LocalDate birthDate = LocalDate.now();

    // Gender of client
    private Gender gender = Gender.Female;

    // List of client accounts
    private final ArrayList<Account> accounts = new ArrayList<>();

    // Total money in all accounts of client
    private Amount totalMoney = Amount.getAmountInstance(0.0);

    // Size of accounts' list
    private int accountsSize = accounts.size();

    /**
     * Creates an instance of Client or could be used for initializing
     * of objects of subclasses
     *
     * @param name client name
     * @param birthDate birth date of client
     * @param gender client gender
     */
    public Client(String name, LocalDate birthDate, Gender gender) {
        setName(name);
        setBirthDate(birthDate);
        setGender(gender);
    }

    // Constructor with no parameters
    public Client() {
    }

    // Getter method of name
    public String getName() {
        return name;
    }

    // Setter method of name
    public void setName(String name) {
        // Checks if param name is a valid name
        if (name != null && !name.equals(""))
            this.name = name;
        else System.out.println(ErrorMessages.NOT_VALID_CLIENT_NAME);
    }

    // Setter method of birthdate
    public void setBirthDate(LocalDate birthDate) {
        // if birthDate is null or client age < 18,then it won't set a new birthDate
        if (birthDate == null || birthDate.isAfter(LocalDate.now().minusYears(18)))
            System.out.println(ErrorMessages.NOT_VALID_BIRTH_DATE);
        else
            this.birthDate = birthDate;
    }

    // Getter of birthdate
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Getter of gender
    public Gender getGender() {
        return gender;
    }

    // Setter of gender
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Adds a new account to the list of client accounts
     * @param account the new account
     * @return status of operation
     */
    public boolean addAccount(Account account){
        if (account == null) {
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }
        if (!accounts.contains(account)) // Checks if the account exists in list of client accounts
            accounts.add(account); // adding the account
        else {
            System.out.println(ErrorMessages.RESERVED_ACCOUNT);
            return false;
        }
        return update(); // This will update all fields in client like accountsSize and TotalMoney
    }
//
//    public boolean deleteAccount(int index){
//        if (index<0 || index> accounts.size()) {
//            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
//            return false;
//        }
//        try {
//            accounts.remove(index);
//            return update();
//        }catch (IndexOutOfBoundsException ex){
//            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
//            return false;
//        }
//    }

    /**
     * Gets the account based on param index
     * @param index the index of account to search for
     * @return the found account
     */
    public Account getAccountByIndex(int index){
        Account account = null;
        try{
            account = accounts.get(index);
        }catch (Exception ex){
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
        }
        return account;
    }

    /**
     * Returns all accounts of client as a String
     *
     * @return String of all accounts info
     */
    public String listAccounts(){
        StringBuilder out = new StringBuilder("\n");
        if (accounts.size() == 0) {
            out.append("We don't have any accounts\n");
        } else {
            out.append(String.format("We have %d account(s)\n", accounts.size()));
        }
        for (Account account : accounts) {
            out.append(account.toString()).append("\n");
        }
        return out.toString();
    }

    // Getter method of accountsSize
    public int getAccountsSize() {
        updateAccountsSizeField();
        return accountsSize;
    }

    // Update method of accountsSizeField
    private boolean updateAccountsSizeField(){
        this.accountsSize = accounts.size();
        return true;
    }

    // Update method of totalMoneyField
    private boolean updateTotalMoneyField(){
        Number total = 0.0;
        try {
            for (Account account: accounts){
                total = account.getBalance().sum(total);// accumulating the balances of accounts to total variable
            }
            // Another way of calculating the total money by using Java 8 streams
//            total = accounts.stream().map(account -> account.getBalance().getAmountValue()).reduce(0.0, DoubleAmount::sum);
            this.totalMoney = Amount.getAmountInstance(total);
            return true;
        }catch (Exception ex){
            System.out.println(ErrorMessages.UNDEFINABLE_ERROR);
            return false;
        }

    }

    // Update method of all fields
    public boolean update(){
        return updateAccountsSizeField() && updateTotalMoneyField();
    }

    // Getter method of totalMoney
    public Amount getTotalMoney() {
        updateTotalMoneyField();
        return this.totalMoney;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + getName() + '\'' +
                ", BirthDate=" + getBirthDate() +
                ", gender=" + getGender() +
                ", accounts=" + this.accounts +
                ", totalMoney=" + getTotalMoney() +
                ", accountsSize=" + getAccountsSize() +
                '}';
    }

}
