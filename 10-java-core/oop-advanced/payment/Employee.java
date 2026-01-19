
public class Employee implements Payable, Taxable {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double calculateTax() {
        return salary * Taxable.getTaxRate();
    }

    @Override
    public double getPaymentAmount() {
        return salary - calculateTax();
    }

    @Override
    public void printPaymentInfo() {
        System.out.println("Employee:");
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
        System.out.println("Tax: $" + calculateTax());
        System.out.println("Net Payment: $" + getPaymentAmount());
    }
}