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


    public Item() {
    }

    public Item(String name, String id, String description, int size, boolean isMainLoot, int minValue, int maxValue, String currentRoom) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.size = size;
        this.isMainLoot = isMainLoot;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = (int)(Math.random() * (maxValue- minValue + 1) - minValue) * 1000;
        this.currentLocation = currentRoom;
        this.loot = true;
    }

    public Item(int size, String description, String id, String name, String currentRoom) {
        this.size = size;
        this.description = description;
        this.id = id;
        this.name = name;
        this.currentLocation = currentRoom;
        this.isMainLoot = false;
        this.loot = false;
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

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }


}


