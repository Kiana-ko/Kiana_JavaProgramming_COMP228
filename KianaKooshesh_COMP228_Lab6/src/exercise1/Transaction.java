package exercise1;

public class Transaction implements Runnable {
    private final Account wallet;
    private final double value;
    private final String action;

    public Transaction(Account wallet, double value, String action) {
        this.wallet = wallet;
        this.value = value;
        this.action = action;
    }

    @Override
    public void run() {
        if ("add".equalsIgnoreCase(action)) {
            wallet.deposit(value);
        } else if ("subtract".equalsIgnoreCase(action)) {
            wallet.withdraw(value);
        } else {
            System.out.println("Unknown operation type: " + action);
        }
    }
}
