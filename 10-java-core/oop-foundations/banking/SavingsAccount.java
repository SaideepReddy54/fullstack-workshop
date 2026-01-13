
package banking;

public class SavingsAccount extends Account {

    private static final double INTEREST_RATE = 0.04;
    private static final double MIN_BALANCE = 100;

    public SavingsAccount(String holderName, double initialBalance) {
        super(holderName, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            throw new IllegalArgumentException(
                    "Withdrawal failed: Minimum balance of $100 required");
        }
        super.withdraw(amount);
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}