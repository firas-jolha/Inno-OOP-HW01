package bank.clients;

import bank.utils.Gender;

import java.time.LocalDate;

public class VIPClient extends Client {
    public VIPClient(String name, LocalDate birthDate, Gender gender) {
        super(name, birthDate, gender);
    }
}
