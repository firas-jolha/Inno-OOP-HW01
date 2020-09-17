package bank.utils;

// an enum represents the available operation types in Bank
// used for keeping a history of operations that could be
// applied on the account
public enum BankOperationType {
    DEPOSIT("deposit"), WITHDRAW("withdraw"), TRANSFER("transfer");

    private final String operationType;

    BankOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }

    @Override
    public String toString() {
        return getOperationType();
    }
}
