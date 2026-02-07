package Game;

public class Player {
    private String name = "Robin";
    private Inventory inventory;
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