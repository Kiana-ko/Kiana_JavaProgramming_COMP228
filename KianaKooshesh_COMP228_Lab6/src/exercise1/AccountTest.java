package exercise1;

import java.util.LinkedList; // Collections API
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        // Responsible for Initializing the wallet:
        Account myWallet = new Account(3000.0);

        // For using Collections API (LinkedList) to manage transactions:
        LinkedList<Transaction> tasks = new LinkedList<>();
        tasks.add(new Transaction(myWallet, 100, "add"));
        tasks.add(new Transaction(myWallet, 200, "subtract"));
        tasks.add(new Transaction(myWallet, 150, "add"));

        // For Multithreading with ExecutorService:
        ExecutorService executor = Executors.newCachedThreadPool();
        for (Transaction task : tasks) {
            executor.execute(task);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Would Wait for threads to finish!
        }

        System.out.printf("Final Wallet Balance: %.2f%n", myWallet.getBalance());
    }
}
