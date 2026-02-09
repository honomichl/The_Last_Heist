package Game;

public class Item {
    // jmeno pro vypis
    private String name;
    // jmeno do kodu
    private String id;
    // popis
    private String description;
    // pocet slotu co item zabere
    private int size;
    // je hlavni loot?
    private boolean isMainLoot;
    // minimalni hodnota pro random v tisicich
    private int minValue;
    // maximalni hodnota pro random v tisicich
    private int maxValue;
    // random hodnota itemu z rozmezi (zaokrouhleno na tisice)
    private int value;
    // lokace itemu
    private String currentLocation;
    // ma hodnotu?
    private boolean loot;
    // je ukryty?
    private boolean hidden;


    public Item() {
    }

    public Item(String name, String id, String description, int size, boolean isMainLoot, int minValue, int maxValue, String currentLocation, boolean hidden) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.size = size;
        this.isMainLoot = isMainLoot;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = (int)(Math.random() * (maxValue- minValue + 1) - minValue) * 1000;
        this.currentLocation = currentLocation;
        this.loot = true;
        this.hidden = hidden;
    }

    public Item(String name, String id, String description, int size, String currentLocation, boolean hidden) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.size = size;
        this.currentLocation = currentLocation;
        this.loot = false;
        this.isMainLoot = false;
        this.hidden = hidden;
    }





    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }

    public boolean isMainLoot() {
        return isMainLoot;
    }

    public int getValue() {
        return value;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public boolean isLoot() {
        return loot;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}


