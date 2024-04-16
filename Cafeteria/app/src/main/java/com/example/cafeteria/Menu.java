public class Menu {
    private String dayOfWeek;
    private String itemName;
    private double price;

    public Menu(String dayOfWeek, String itemName, double price) {
        this.dayOfWeek = dayOfWeek;
        this.itemName = itemName;
        this.price = price;
    }

    // Getters and setters
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}