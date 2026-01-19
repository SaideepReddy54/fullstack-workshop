public record Order(
        Long orderId,
        User user,
        Product product,
        int quantity,
        double totalAmount) {
}