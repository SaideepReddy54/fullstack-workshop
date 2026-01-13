public class BankAccount {
    private static String bankName = "MyBank";
    private static int totalAccounts = 0;
    private static int accountCounter = 1000;
    private int accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String holderName, double initialBalance) {
        this.accountNumber = ++accountCounter;
        this.holderName = holderName;
        this.balance = initialBalance;
        totalAccounts++;
    }

    public static String getBankInfo() {
        return bankName + " - Total Accounts: " + totalAccounts;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("Rohit", 100000);
        BankAccount acc2 = new BankAccount("Virat", 100000);
        acc1.deposit(10000);
        acc2.withdraw(20000);
        System.out.println("Account Number:" + acc1.accountNumber);
        System.out.println("Holder:" + acc1.getHolderName());
        System.out.println("Balance:" + acc1.getBalance());
        System.out.println("Account Number: " + acc2.accountNumber);
        System.out.println("Holder: " + acc2.holderName);
        System.out.println("Balance: " + acc2.getBalance());
        System.out.println(BankAccount.getBankInfo());
    }
}
