package bank.utils;

/**
 * An enum of Error Messages
 */
public enum ErrorMessages {
    NOT_EXISTED_ACCOUNT("Account doesn't exist!"),
    NOT_VALID_CLIENT_NAME("Not valid client name!"),
    NOT_VALID_BIRTH_DATE("Not valid birth date!"),
    NOT_EXISTED_CLIENT("Client doesn't exist!"),
    NOT_PERMITTED_OPERATION("This operation isn't permitted"),
    RESERVED_ACCOUNT("This account is reserved or belongs to another client!"),
    NOT_VALID_AMOUNT("The specified amount isn't valid!"),
    NOT_VALID_DATE("The specified date isn't valid!"),
    NOT_VALID_ACCOUNT_CLIENT("The account owner doesn't match the client!"),
    UNDEFINABLE_ERROR("The error is not specified clearly!"),
    CLIENT_EXIST_IN_SYSTEM("The client is existed in the system! Duplicate is forbidden!")
    ;

    private final String message;
    ErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ERROR: " + message ;
    }
}
