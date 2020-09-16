package bank.clients;

import bank.accounts.Account;
import bank.accounts.IAccount;
import bank.utils.ErrorMessages;
import bank.utils.amount.Amount;
import bank.utils.amount.DoubleAmount;
import bank.utils.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

public abstract class Client {


    private String name = "client-" + UUID.randomUUID().toString();

    private LocalDate birthDate = LocalDate.now();

    private Gender gender = Gender.Female;

    private ArrayList<Account> accounts = new ArrayList<>();

    private Amount totalMoney = Amount.getAmountInstance(0.0);

    private int accountsSize = accounts.size();

    public Client(String name, LocalDate birthDate, Gender gender) {
        setName(name);
        setBirthDate(birthDate);
        setGender(gender);
    }

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.equals(""))
            this.name = name;
        else System.out.println(ErrorMessages.NOT_VALID_CLIENT_NAME);
    }

    public void setBirthDate(LocalDate birthDate) {
        // if birthDate is null or client age < 18,then it won't set a new birthDate
        if (birthDate == null || birthDate.isAfter(LocalDate.now().minusYears(18)))
            System.out.println(ErrorMessages.NOT_VALID_BIRTH_DATE);
        else
            this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean addAccount(Account account){
        if (account == null) {
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }

        // checks if the account owner is the client who will hold the account
//        if (account.getClient() == (this))
            accounts.add(account);
//        else System.out.println(ErrorMessages.NOT_VALID_ACCOUNT_CLIENT);
//            account.setClient(this);            // setClient only used here!
        return update();
    }

    public boolean deleteAccount(int index){
        if (index<0 || index> accounts.size()) {
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }
        try {
            accounts.remove(index);
            return update();
        }catch (IndexOutOfBoundsException ex){
            System.out.println(ErrorMessages.NOT_EXISTED_ACCOUNT);
            return false;
        }
    }

    public Account getAccountByIndex(int index){
        return accounts.get(index);
    }

    public boolean accountExists(IAccount account){
        return accounts.contains((Account) account);
    }

    public int getAccountsSize() {
        updateAccountsSizeField();
        return accountsSize;
    }

    private boolean updateAccountsSizeField(){
        this.accountsSize = accounts.size();
        return true;
    }

    private boolean updateTotalMoneyField(){
        Number total = 0.0;
        try {
            for (Account account: accounts){
                total = account.getBalance().sum(total);
            }
//            total = accounts.stream().map(account -> account.getBalance().getAmountValue()).reduce(0.0, DoubleAmount::sum);
            this.totalMoney = Amount.getAmountInstance(total);
            return true;
        }catch (Exception ex){
            System.out.println(ErrorMessages.UNDEFINABLE_ERROR);
            return false;
        }

    }

    public boolean update(){
        return updateAccountsSizeField() && updateTotalMoneyField();
    }

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
