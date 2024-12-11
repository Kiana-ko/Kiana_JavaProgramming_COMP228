package exercise1;

public class Account {
    private double balance;

    public Account(double startBalance) {
            this.balance = startBalance;
    }

    // The Thread-safe deposit method:
    public synchronized void deposit(double value) {
        if (value > 0.0) {
            balance += value;
            System.out.printf("Added: %.2f, Updated Balance: %.2f%n", value, balance);
        }
    }

    // The Thread-safe withdraw method:
    public synchronized void withdraw(double value) {
        if (value > 0.0 && value <= balance) {
            balance -= value;
            System.out.printf("Subtracted: %.2f, Updated Balance: %.2f%n", value, balance);
        } else {
            System.out.printf("Failed to subtract %.2f. Insufficient balance: %.2f%n", value, balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}
