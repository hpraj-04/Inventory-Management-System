import java.io.*;
import java.util.*;

public class InventoryManager {
    private HashMap<Integer, Product> productMap = new HashMap<>();
    private final String FILE_NAME = "inventory.txt";

    public void addProduct(Product product) {
        if (!productMap.containsKey(product.getProductId())) {
            productMap.put(product.getProductId(), product);
        }
    }

    public void updateProduct(int id, double price, double costPrice, int quantity) {
        Product p = productMap.get(id);
        if (p != null) {
            p.setPrice(price);
            p.setCostPrice(costPrice);
            p.setQuantity(quantity);
        }
    }

    public void deleteProduct(int id) {
        productMap.remove(id);
    }

    public Product searchProduct(int id) {
        return productMap.get(id);
    }

    public void viewAllProducts() {
        for (Product p : productMap.values()) {
            System.out.println(p);
        }
    }

    public void recordSale(int id, int qty) {
        Product p = productMap.get(id);
        if (p != null) {
            p.sellProduct(qty);
        }
    }

    public void lowStockReport(int threshold) {
        for (Product p : productMap.values()) {
            if (p.getQuantity() < threshold) {
                System.out.println(p);
            }
        }
    }

    public double totalProfit() {
        double total = 0;
        for (Product p : productMap.values()) {
            total += p.calculateProfit();
        }
        return total;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : productMap.values()) {
                writer.write(p.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File Error");
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product p = Product.fromFileFormat(line);
                productMap.put(p.getProductId(), p);
            }
        } catch (IOException ignored) {}
    }
}