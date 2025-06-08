package java_collection_secondMay_assignment;
import java.util.*;
public class ProductManagerService {
    private Set<Product> productSet = new HashSet<>();

    public boolean addProduct(Product product) {
        return productSet.add(product);
    }

    public Product getProductById(int id) {
        for (Product p : productSet) {
            if (p.getProductId() == id) return p;
        }
        return null;
    }

    public boolean updateProduct(Product updatedProduct) {
        for (Product p : productSet) {
            if (p.getProductId() == updatedProduct.getProductId()) {
                p.setProductName(updatedProduct.getProductName());
                p.setCategory(updatedProduct.getCategory());
                p.setPrice(updatedProduct.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        return productSet.removeIf(p -> p.getProductId() == id);
    }

    public List<Product> sortById() {
        List<Product> list = new ArrayList<>(productSet);
        list.sort(Comparator.comparingInt(Product::getProductId));
        return list;
    }

    public List<Product> sortByName() {
        List<Product> list = new ArrayList<>(productSet);
        list.sort(Comparator.comparing(Product::getProductName));
        return list;
    }

    public Set<Product> getAllProducts() {
        return productSet;
    }
}