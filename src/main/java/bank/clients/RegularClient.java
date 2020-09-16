package bank.clients;

import bank.utils.Gender;

import java.time.LocalDate;

public class RegularClient extends Client {
    public RegularClient(String name, LocalDate birthDate, Gender gender) {
        super(name, birthDate, gender);
    }

    public RegularClient() {
    }
}
