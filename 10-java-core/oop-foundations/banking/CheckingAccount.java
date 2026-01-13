package banking;

public class CheckingAccount extends Account {

    private static final double OVERDRAFT_LIMIT = 500;

    public CheckingAccount(String holderName, double initialBalance) {
        super(holderName, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance + OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException(
                    "Withdrawal exceeds overdraft limit");
        }
        balance -= amount;
    }

    @Override
    public double calculateInterest() {
        return 0; // No interest
    }
}