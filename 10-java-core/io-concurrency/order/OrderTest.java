public class OrderTest {

    public static void main(String[] args) {

        OrderProcessor processor = new OrderProcessor();

        CompletableFuture<OrderResult> future = processor.processOrder(1L, 100L, 2);

        future.thenAccept(result -> {
            if (result.isSuccess()) {
                System.out.println("Order completed: " +
                        result.getOrderId());
            } else {
                System.out.println("Order failed: " +
                        result.getError());
            }
        });

        // Prevent JVM exit before async completion
        future.join();
    }
}
