package Game;

import java.util.*;

public class Room {
    private String name;
    private String id;
    private String description;
    private List<String> exits = new ArrayList<>();


    public Room() {
    }

    public Room(String name, String id, String description, List<String> exits) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.exits = new ArrayList<>(exits);

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

    public List<String> getExits() {
        return exits;
    }
}