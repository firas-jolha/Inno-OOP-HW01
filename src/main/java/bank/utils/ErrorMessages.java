package bank.utils;

public enum ErrorMessages {
    NOT_EXISTED_ACCOUNT("Account doesn't exist!"),
    NOT_VALID_CLIENT_NAME("Not valid client name!"),
    NOT_VALID_BIRTH_DATE("Not valid birth date!"),
    NOT_EXISTED_CLIENT("Client doesn't exist!"),
    NOT_PERMITTED_OPERATION("This operation isn't permitted")
    ;

    private String message;
    ErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ERROR: " + message ;
    }
}
