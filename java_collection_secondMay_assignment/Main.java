package java_collection_secondMay_assignment;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(1, "Laptop", "Electronics", 70000);
        Product p2 = new Product(2, "Phone", "Electronics", 30000);
        Product p3 = new Product(3, "Mouse", "Accessories", 500);

        ProductManagerService manager = new ProductManagerService();
        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.updateProduct(new Product(2, "Smartphone", "Electronics", 35000));

        System.out.println("Sorted Products by Name:");
        for (Product p : manager.sortByName()) {
            System.out.println(p);
        }

        ProductCatalogueService catalogue = new ProductCatalogueService();
        catalogue.addProduct(p1, 10);
        catalogue.addProduct(p3, 50);
        catalogue.updateQuantity(p1, 15);

        System.out.println("\nCatalogue Sorted by Product ID:");
        for (Map.Entry<Product, Integer> entry : catalogue.sortById()) {
            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
        }
    }
}