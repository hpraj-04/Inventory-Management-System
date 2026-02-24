import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();
        manager.loadFromFile();

        while (true) {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int id = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    double price = sc.nextDouble();
                    double cost = sc.nextDouble();
                    int qty = sc.nextInt();
                    manager.addProduct(new Product(id, name, price, cost, qty));
                    break;

                case 2:
                    int uid = sc.nextInt();
                    double newPrice = sc.nextDouble();
                    double newCost = sc.nextDouble();
                    int newQty = sc.nextInt();
                    manager.updateProduct(uid, newPrice, newCost, newQty);
                    break;

                case 3:
                    manager.deleteProduct(sc.nextInt());
                    break;

                case 4:
                    System.out.println(manager.searchProduct(sc.nextInt()));
                    break;

                case 5:
                    manager.viewAllProducts();
                    break;

                case 6:
                    int sid = sc.nextInt();
                    int sqty = sc.nextInt();
                    manager.recordSale(sid, sqty);
                    break;

                case 7:
                    manager.lowStockReport(sc.nextInt());
                    break;

                case 8:
                    System.out.println(manager.totalProfit());
                    break;

                case 9:
                    manager.saveToFile();
                    return;
            }
        }
    }
}