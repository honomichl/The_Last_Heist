package Game;

/**
 * Třída Player reprezentuje hlavního hrdinu hry.
 * Uchovává informace o identitě hráče, jeho aktuální polohu
 * a spravuje jeho inventář.
 *
 * @author Filip Honomichl
 */

public class Player {
    /** Jméno hráče */
    private String name = "Robin";
    /** Inventář */
    private Inventory inventory;
    /** Místnost, ve které se hráč aktuálně nachází. */
    private Room currentRoom;


    /**
     * Inicializuje novou instanci hráče.
     * Při vytvoření je automaticky udělán prázdný inventář.
     */
    public Player() {
        this.inventory = new Inventory();
    }

    /** gettery */
    public String getName() {
        return name;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /** settery */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}