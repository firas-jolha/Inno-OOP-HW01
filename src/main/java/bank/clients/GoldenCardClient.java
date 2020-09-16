package bank.clients;

import bank.utils.Gender;

import java.time.LocalDate;

public class GoldenCardClient extends Client {

    public GoldenCardClient(String name, LocalDate birthDate, Gender gender) {
        super(name, birthDate, gender);
    }

}
