package banking;

public class BankingTest {

    public static void main(String[] args) {

        Bank bank = new Bank("MyBank");

        Account savings = new SavingsAccount("Alice", 1000);
        Account checking = new CheckingAccount("Bob", 500);
        Account fd = new FixedDepositAccount("Charlie", 10000);

        bank.addAccount(savings);
        bank.addAccount(checking);
        bank.addAccount(fd);

        savings.deposit(500);
        checking.withdraw(800); // uses overdraft

        System.out.println("Total deposits: $" + bank.getTotalDeposits());

        System.out.println("\nInterest for all accounts:");
        for (Account acc : bank.getAllAccounts()) {
            System.out.println(
                    acc.getHolderName() +
                            " interest: $" +
                            acc.calculateInterest());
        }
    }
}
