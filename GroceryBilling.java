class Item {

    private String itemName;
    private double price;
    private int quantity;

    public Item(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price    = price;
        this.quantity = quantity;
    }

    public String getItemName() { return itemName; }
    public double getPrice()    { return price;    }
    public int    getQuantity() { return quantity; }
}


class Bill {

    private Item[] items;
    private double total;
    private double tax;
    private double grandTotal;

    public Bill(Item[] items) {
        this.items = items;
    }

    private void calculateTotal() {
        total = 0;

        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }

        tax        = total * 0.05;
        grandTotal = total + tax;
    }

    public void generateReceipt() {

        calculateTotal();

        StringBuilder receipt = new StringBuilder();

        receipt.append("\n");
        receipt.append("==========================================\n");
        receipt.append("         GROCERY BILLING RECEIPT          \n");
        receipt.append("==========================================\n");
        receipt.append(String.format("%-16s %9s %5s %9s\n",
                        "Item", "Price", "Qty", "Total"));
        receipt.append("------------------------------------------\n");

        for (Item item : items) {
            double lineTotal = item.getPrice() * item.getQuantity();
            receipt.append(String.format("%-16s %9.2f %5d %9.2f\n",
                            item.getItemName(),
                            item.getPrice(),
                            item.getQuantity(),
                            lineTotal));
        }

        receipt.append("------------------------------------------\n");
        receipt.append(String.format("%-31s %9.2f\n", "Subtotal:",    total));
        receipt.append(String.format("%-31s %9.2f\n", "GST (5%):",    tax));
        receipt.append("==========================================\n");
        receipt.append(String.format("%-31s %9.2f\n", "GRAND TOTAL:", grandTotal));
        receipt.append("==========================================\n");
        receipt.append("       Thank you for shopping with us!    \n");
        receipt.append("==========================================\n");

        System.out.println(receipt.toString());
    }
}


public class GroceryBilling {

    public static void main(String[] args) {

        Item item1 = new Item("Rice",   50.0, 2);
        Item item2 = new Item("Milk",   30.0, 3);
        Item item3 = new Item("Bread",  40.0, 1);
        Item item4 = new Item("Eggs",   60.0, 1);
        Item item5 = new Item("Butter", 90.0, 1);

        Item[] items = { item1, item2, item3, item4, item5 };

        Bill bill = new Bill(items);
        bill.generateReceipt();
    }
}