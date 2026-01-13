package banking;

public class FixedDepositAccount extends Account {

    private static final double INTEREST_RATE = 0.07; // 7%
    private static final int LOCK_PERIOD_MONTHS = 12;

    public FixedDepositAccount(String holderName, double initialBalance) {
        super(holderName, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException(
                "Withdrawals not allowed for Fixed Deposit Account (Lock period: "
                        + LOCK_PERIOD_MONTHS + " months)");
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}
