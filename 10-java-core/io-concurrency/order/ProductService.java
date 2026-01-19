public class ProductService {

    public Product getProduct(Long id) {
        sleep(300);
        return new Product(id, "Product-" + id, 500.0);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}