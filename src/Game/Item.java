package Game;

public class Item {
    private String name;
    private String description;
    private int size;
    private boolean isMainLoot;
    private int minPrice;
    private int maxPrice;
    private int price;

    public Item() {
    }

    public Item(String name, String description, int size, boolean isMainLoot, int minPrice, int maxPrice) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.isMainLoot = isMainLoot;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.price = 0;
    }

    //TODO price


    public String getName() { return ""; }
    public String getDescription() { return ""; }
    public int getSize() { return 0; }
}