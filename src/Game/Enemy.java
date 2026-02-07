package Game;

public class Enemy {
    // jmeno pro vypis
    private String name;
    // jmeno do kodu
    private String id;
    // typ nepritele kazdy se chova jinak
    private String type;
    // popis
    private String description;
    // lokace nepritele
    private String currentLocation;
    // item diky kteremu se zmeni interakce s nepritelem
    private String usefulItem;

    public Enemy() {
    }

    public Enemy(String name, String id, String type, String description, String currentLocation) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.description = description;
        this.currentLocation = currentLocation;
    }

    public Enemy(String name, String id, String type, String description, String currentLocation, String usefulItem) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.description = description;
        this.currentLocation = currentLocation;
        this.usefulItem = usefulItem;
    }


    public void removeEnemy() {
        this.currentLocation = "none";
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getUsefulItem() {
        return usefulItem;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }


}