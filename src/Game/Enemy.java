package Game;

public abstract class Enemy {
    protected String name;
    protected String description;

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public abstract String interact();
}