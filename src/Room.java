import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private List<Item> items = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Map<String, Room> exits = new HashMap<>();

    public String getName() {
        return "";
    }

    public String getDescription() {
        return "";
    }

    public String getLongDescription() {
        return "";
    }

    public void setExit(String direction, Room neighbor) {
    }

    public Room getExit(String direction) {
        return null;
    }

    public void addItem(Item item) {}

    public void addEnemy(Enemy enemy) {}
}