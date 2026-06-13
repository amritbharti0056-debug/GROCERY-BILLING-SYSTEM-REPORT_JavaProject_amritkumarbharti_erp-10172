// Class representing a grocery item
class Item {

    // Instance variables
    private String itemName; // Name of the item
    private double price;    // Price per unit
    private int quantity;    // Quantity purchased

    // Constructor to initialize item details
    public Item(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price    = price;
        this.quantity = quantity;
    }

    // Getter methods
    public String getItemName() { return itemName; }
    public double getPrice()    { return price;    }
    public int    getQuantity() { return quantity; }
}


// Class responsible for bill calculation and receipt generation
class Bill {

    private Item[] items;      // Array of purchased items
    private double total;      // Subtotal before tax
    private double tax;        // GST amount
    private double grandTotal; // Final amount including tax

    // Constructor
    public Bill(Item[] items) {
        this.items = items;
    }

    // Method to calculate subtotal, tax, and grand total
    private void calculateTotal() {
        total = 0;

        // Calculate subtotal
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }

        // Calculate 5% GST
        tax = total * 0.05;

        // Calculate final amount
        grandTotal = total + tax;
    }

    // Method to generate and display receipt
    public void generateReceipt() {

        // Calculate totals before printing receipt
        calculateTotal();

        StringBuilder receipt = new StringBuilder();

        // Receipt Header
        receipt.append("\n");
        receipt.append("==========================================\n");
        receipt.append("         GROCERY BILLING RECEIPT          \n");
        receipt.append("==========================================\n");

        // Column headings
        receipt.append(String.format("%-16s %9s %5s %9s\n",
                        "Item", "Price", "Qty", "Total"));
        receipt.append("------------------------------------------\n");

        // Display item details
        for (Item item : items) {
            double lineTotal = item.getPrice() * item.getQuantity();

            receipt.append(String.format("%-16s %9.2f %5d %9.2f\n",
                            item.getItemName(),
                            item.getPrice(),
                            item.getQuantity(),
                            lineTotal));
        }

        // Display billing summary
        receipt.append("------------------------------------------\n");
        receipt.append(String.format("%-31s %9.2f\n", "Subtotal:", total));
        receipt.append(String.format("%-31s %9.2f\n", "GST (5%):", tax));

        receipt.append("==========================================\n");
        receipt.append(String.format("%-31s %9.2f\n", "GRAND TOTAL:", grandTotal));
        receipt.append("==========================================\n");

        // Thank you message
        receipt.append("       Thank you for shopping with us!    \n");
        receipt.append("==========================================\n");

        // Print receipt
        System.out.println(receipt.toString());
    }
}


// Main class to run the Grocery Billing System
public class GroceryBilling {

    public static void main(String[] args) {

        // Creating grocery items
        Item item1 = new Item("Rice",   50.0, 2);
        Item item2 = new Item("Milk",   30.0, 3);
        Item item3 = new Item("Bread",  40.0, 1);
        Item item4 = new Item("Eggs",   60.0, 1);
        Item item5 = new Item("Butter", 90.0, 1);

        // Storing items in an array
        Item[] items = { item1, item2, item3, item4, item5 };

        // Creating Bill object
        Bill bill = new Bill(items);

        // Generating and displaying receipt
        bill.generateReceipt();
    }
}
