import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BankingSystem {

    private Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        bank.initializeSystem(); // Entry point for operations
    }

    // Method 1: Initializes the system with accounts and performs sample operations
    public void initializeSystem() {
        createAccount("A1001", "Alice", 10000);
        createAccount("B2002", "Bob", 5000);
        createAccount("C3003", "Charlie", 2000);

        displayAllAccounts();

        performDeposit("A1001", 1500);
        performWithdrawal("B2002", 1000);
        transferFunds("A1001", "C3003", 2500);
        applyMonthlyInterest();

        printTransactionHistory("C3003");
    }

    // Method 2: Create new bank account
    public void createAccount(String accountId, String holderName, double initialBalance) {
        Account acc = new Account(accountId, holderName, initialBalance);
        accounts.put(accountId, acc);
        logTransaction(accountId, "Account created with balance: " + initialBalance);
    }

    // Method 3: Deposit funds into account
    public void performDeposit(String accountId, double amount) {
        Account acc = getAccount(accountId);
        acc.balance += amount;
        logTransaction(accountId, "Deposited: " + amount);
    }

    // Method 4: Withdraw funds from account
    public void performWithdrawal(String accountId, double amount) {
        Account acc = getAccount(accountId);
        if (acc.balance >= amount) {
            acc.balance -= amount;
            logTransaction(accountId, "Withdrew: " + amount);
        } else {
            logTransaction(accountId, "Failed withdrawal due to insufficient funds.");
        }
    }

    // Method 5: Transfer funds between accounts
    public void transferFunds(String fromId, String toId, double amount) {
        if (fromId.equals(toId)) {
            System.out.println("Cannot transfer to same account.");
            return;
        }

        Account fromAcc = getAccount(fromId);
        Account toAcc = getAccount(toId);

        if (fromAcc.balance >= amount) {
            performWithdrawal(fromId, amount); // internally calls withdrawal
            performDeposit(toId, amount);      // internally calls deposit
            logTransaction(fromId, "Transferred " + amount + " to " + toId);
            logTransaction(toId, "Received " + amount + " from " + fromId);
        } else {
            logTransaction(fromId, "Failed transfer to " + toId + ": Insufficient funds.");
        }
    }

    // Method 6: Print all account details
    public void displayAllAccounts() {
        System.out.println("\n=== All Account Details ===");
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
    }

    // Method 7: Apply interest to all accounts
    public void applyMonthlyInterest() {
        System.out.println("\nApplying monthly interest to all accounts...");
        for (Account acc : accounts.values()) {
            double interest = acc.balance * 0.005; // 0.5% monthly interest
            performDeposit(acc.accountId, interest);
            logTransaction(acc.accountId, "Interest added: " + interest);
        }
    }

    // Method 8: Show transaction history
    public void printTransactionHistory(String accountId) {
        Account acc = getAccount(accountId);
        System.out.println("\nTransaction History for " + acc.holderName + " (" + accountId + "):");
        for (String t : acc.transactionHistory) {
            System.out.println(" - " + t);
        }
    }

    // Internal helper: Get account by ID or throw exception
    private Account getAccount(String accountId) {
        Account acc = accounts.get(accountId);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return acc;
    }

    // Internal helper: Record transaction
    private void logTransaction(String accountId, String message) {
        Account acc = getAccount(accountId);
        acc.transactionHistory.add(new Date() + " - " + message);
    }

    // Inner class to represent an account
    static class Account {
        String accountId;
        String holderName;
        double balance;
        List<String> transactionHistory = new ArrayList<>();

        public Account(String accountId, String holderName, double balance) {
            this.accountId = accountId;
            this.holderName = holderName;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "AccountID: " + accountId + ", Holder: " + holderName + ", Balance: ₹" + balance;
        }
    }
}
