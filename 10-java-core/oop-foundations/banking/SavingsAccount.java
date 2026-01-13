package banking;

public class SavingsAccount extends Account {

    private static final double INTEREST_RATE = 0.04; // 4%
    private static final double MIN_BALANCE = 100;

    public SavingsAccount(String holderName, double initialBalance) {
        super(holderName, initialBalance);
        if (initialBalance < MIN_BALANCE) {
            throw new IllegalArgumentException("Minimum balance must be $100");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            throw new IllegalArgumentException("Withdrawal denied: Minimum balance must be maintained");
        }
        super.withdraw(amount);
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}
