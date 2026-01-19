public class PaymentService {

    public boolean processPayment(Order order) {
        sleep(1000);
        return true; // simulate successful payment
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}