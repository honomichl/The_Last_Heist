package Game;

import java.util.*;

/**
 * Třída Room reprezentuje jednu místnost v herním světě.
 * Uchovává informace o názvu, popisu, viditelnosti a propojení s dalšími místnostmi.
 *
 * @author Filip Honomichl
 */

public class Room {
    /** jmeno pro vypis */
    private String name;
    /** jmeno do kodu */
    private String id;
    /** popis */
    private String description;
    /** je objeven? */
    private boolean hidden;
    /** list sousednich mistnosti */
    private List<String> exits = new ArrayList<>();


    /**
     * Výchozí konstruktor pro vytvoření prázdného roomu.
     */
    public Room() {
    }

    /**
     * konstruktor pro vytvoření plně definovaného roomu.
     */
    public Room(String name, String id, String description, boolean hidden, List<String> exits) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.hidden = hidden;
        this.exits = new ArrayList<>(exits);
    }

    /** gettery */
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

    /** settery */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}