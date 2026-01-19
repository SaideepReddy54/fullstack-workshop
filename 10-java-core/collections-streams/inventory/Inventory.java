import java.util.*;

public class Inventory {

    private List<Product> products = new ArrayList<>();
    private Set<String> categories = new HashSet<>();
    private Map<String, Product> productMap = new HashMap<>();
    private Queue<Product> lowStockQueue = new LinkedList<>();

    // Add product
    public void addProduct(Product product) {
        products.add(product);
        categories.add(product.getCategory());
        productMap.put(product.getId(), product);

        if (product.getQuantity() < 10) {
            lowStockQueue.offer(product);
        }
    }

    // Update product
    public void updateProduct(Product product) {
        Product existing = productMap.get(product.getId());
        if (existing != null) {
            existing.setName(product.getName());
            existing.setCategory(product.getCategory());
            existing.setPrice(product.getPrice());
            existing.setQuantity(product.getQuantity());
        }
    }

    // Delete product
    public void deleteProduct(String id) {
        Product product = productMap.remove(id);
        if (product != null) {
            products.remove(product);
            lowStockQueue.remove(product);
        }
    }

    // Find by id
    public Product findById(String id) {
        return productMap.get(id);
    }

    // Find by category
    public List<Product> getByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    // Get all products sorted by price
    public List<Product> getAllSortedByPrice() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingDouble(Product::getPrice));
        return sorted;
    }

    // Get low stock alerts
    public Queue<Product> getLowStockAlerts() {
        return new LinkedList<>(lowStockQueue);
    }
}
