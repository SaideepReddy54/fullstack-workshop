public class NotificationService {

    public void sendConfirmation(Order order) {
        sleep(400);
        System.out.println("Notification sent for order " + order.orderId());
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}