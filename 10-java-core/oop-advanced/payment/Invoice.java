public class Invoice implements Payable {

    private String partNumber;
    private String description;
    private int quantity;
    private double pricePerItem;

    public Invoice(String partNumber, String description, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.description = description;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }

    @Override
    public void printPaymentInfo() {
        System.out.println("Invoice:");
        System.out.println("Part Number: " + partNumber);
        System.out.println("Description: " + description);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per Item: $" + pricePerItem);
        System.out.println("Total Invoice Amount: $" + getPaymentAmount());
    }
}
