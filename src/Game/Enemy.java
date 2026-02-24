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

    public Enemy() {
    }

    public Enemy(String name, String id, String type, String description, String currentLocation) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.description = description;
        this.currentLocation = currentLocation;
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

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }


}