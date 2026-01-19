import java.util.concurrent.CompletableFuture;

public class OrderProcessor {

    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();
    private final NotificationService notificationService = new NotificationService();

    public CompletableFuture<OrderResult> processOrder(
            Long userId, Long productId, int quantity) {

        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> userService.getUser(userId));

        CompletableFuture<Product> productFuture = CompletableFuture
                .supplyAsync(() -> productService.getProduct(productId));

        // Fetch user & product in parallel
        return userFuture.thenCombine(productFuture,
                (user, product) -> {
                    double total = product.price() * quantity;
                    return new Order(
                            System.currentTimeMillis(),
                            user, product, quantity, total);
                })

                // Check inventory (dependent step)
                .thenCompose(order -> CompletableFuture.supplyAsync(() -> {
                    boolean inStock = inventoryService.checkStock(
                            order.product().id(),
                            order.quantity());
                    if (!inStock) {
                        throw new RuntimeException("Out of stock");
                    }
                    return order;
                }))

                // Process payment
                .thenCompose(order -> CompletableFuture.supplyAsync(() -> {
                    boolean paid = paymentService.processPayment(order);
                    if (!paid) {
                        throw new RuntimeException("Payment failed");
                    }
                    return order;
                }))

                // Send notification (async side-effect)
                .thenCompose(order -> CompletableFuture.runAsync(() -> notificationService.sendConfirmation(order))
                        .thenApply(v -> order))

                // Success result
                .thenApply(order -> OrderResult.success(order.orderId()))

                // Fallback on error
                .exceptionally(ex -> OrderResult.failure(ex.getMessage()));
    }
}