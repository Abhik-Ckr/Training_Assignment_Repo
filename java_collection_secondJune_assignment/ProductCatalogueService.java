package java_collection_secondMay_assignment;

import java.util.*;
public class ProductCatalogueService {
    private Map<Product, Integer> catalogue = new HashMap<>();

    public boolean addProduct(Product product, int quantity) {
        if (!catalogue.containsKey(product)) {
            catalogue.put(product, quantity);
            return true;
        }
        return false; 
    }

    public boolean updateQuantity(Product product, int quantity) {
        if (catalogue.containsKey(product)) {
            catalogue.put(product, quantity);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(Product product) {
        return catalogue.remove(product) != null;
    }

    public int getQuantity(Product product) {
        return catalogue.getOrDefault(product, 0);
    }

    public List<Map.Entry<Product, Integer>> sortById() {
        List<Map.Entry<Product, Integer>> list = new ArrayList<>(catalogue.entrySet());
        list.sort(Comparator.comparing(entry -> entry.getKey().getProductId()));
        return list;
    }

    public List<Map.Entry<Product, Integer>> sortByName() {
        List<Map.Entry<Product, Integer>> list = new ArrayList<>(catalogue.entrySet());
        list.sort(Comparator.comparing(entry -> entry.getKey().getProductName()));
        return list;
    }

    public Map<Product, Integer> getAllProducts() {
        return catalogue;
    }
}
