package Game;

import java.util.*;

public class Room {
    private String name;
    private String id;
    private String description;
    private List<String> items;
    private List<String> enemies = new ArrayList<>();
    private List<String> exits = new ArrayList<>();

    public Room() {
    }

    public Room(String name, String id, String description, List<String> items, List<String> enemies, List<String> exits) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.items = new ArrayList<>(items);
        this.enemies = new ArrayList<>(enemies);
        this.exits = new ArrayList<>(exits);

    }

    public void AddItem(Item item){
        items.add(item.getName());
    }


    public String getName() {
        return name;
    }

    public List<String> getExits() {
        return this.exits;
    }

    public String getDescription() {
        return "";
    }

    public String getLongDescription() {
        return "";
    }

    public void addItem(Item item) {}

    public void addEnemy(Enemy enemy) {}
}