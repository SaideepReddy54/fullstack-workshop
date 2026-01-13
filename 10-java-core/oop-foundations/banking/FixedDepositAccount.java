package banking;

public class FixedDepositAccount extends Account {

    private static final double INTEREST_RATE = 0.07;
    private static final int LOCK_PERIOD_MONTHS = 12;

    public FixedDepositAccount(String holderName, double initialBalance) {
        super(holderName, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException(
                "Withdrawals not allowed for Fixed Deposit (Lock-in: 12 months)");
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}
