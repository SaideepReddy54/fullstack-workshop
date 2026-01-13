package banking;

public abstract class Account {

    private static int accountCounter = 1000; // static generator

    protected int accountNumber;
    protected String holderName;
    protected double balance;

    public Account(String holderName, double initialBalance) {
        this.accountNumber = ++accountCounter;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public abstract double calculateInterest();

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getHolderName() {
        return holderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
