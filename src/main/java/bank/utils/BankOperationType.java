package bank.utils;

public enum BankOperationType {
    DEPOSIT("Deposit"), WITHDRAW("Withdraw"), TRANSFER("Transfer");

    private String operationType;

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
