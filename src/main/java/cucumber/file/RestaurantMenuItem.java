package cucumber.file;

public class RestaurantMenuItem {
    private String name;
    private int price;
    private String description;

    public RestaurantMenuItem() {

    }
    public RestaurantMenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
