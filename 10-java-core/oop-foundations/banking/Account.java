
package banking;

public abstract class Account {

    private static int accountCounter = 1000;

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
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
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
