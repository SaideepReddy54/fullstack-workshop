public class InventoryService {

    public boolean checkStock(Long productId, int quantity) {
        sleep(200);
        return quantity <= 5; // simulate limited stock
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}