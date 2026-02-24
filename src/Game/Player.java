package Game;

public class Player {
    // jmeno hrace
    private String name = "Robin";
    // inventar
    private Inventory inventory;
    // mistnost ve ktere prave je
    private Room currentRoom;


    public Player() {
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}