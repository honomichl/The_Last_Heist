package Game;

public abstract class Enemy {

    private String name;

    private String id;

    private String type;

    private String description;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public abstract String interact();
}