package Game;

import java.util.*;

public class Room {
    // jmeno pro vypis
    private String name;
    // jmeno do kodu
    private String id;
    // popis
    private String description;
    // je objeven?
    private boolean hidden;
    // list sousednich mistnosti
    private List<String> exits = new ArrayList<>();


    public Room() {
    }

    public Room(String name, String id, String description, boolean hidden, List<String> exits) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.hidden = hidden;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}