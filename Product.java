import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private double price;
    private double costPrice;
    private int quantity;
    private int soldQuantity;

    public Product(int productId, String productName, double price, double costPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.costPrice = costPrice;
        this.quantity = quantity;
        this.soldQuantity = 0;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public double getCostPrice() { return costPrice; }
    public int getQuantity() { return quantity; }
    public int getSoldQuantity() { return soldQuantity; }

    public void setPrice(double price) { this.price = price; }
    public void setCostPrice(double costPrice) { this.costPrice = costPrice; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void sellProduct(int qty) {
        if (qty <= quantity) {
            quantity -= qty;
            soldQuantity += qty;
        }
    }

    public double calculateProfit() {
        return (price - costPrice) * soldQuantity;
    }

    @Override
    public String toString() {
        return productId + " | " + productName + " | Price: " + price +
                " | Cost: " + costPrice + " | Qty: " + quantity +
                " | Sold: " + soldQuantity;
    }

    public String toFileFormat() {
        return productId + "," + productName + "," + price + "," +
                costPrice + "," + quantity + "," + soldQuantity;
    }

    public static Product fromFileFormat(String line) {
        String[] parts = line.split(",");
        Product p = new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4])
        );
        p.soldQuantity = Integer.parseInt(parts[5]);
        return p;
    }
}