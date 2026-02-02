package Game;

public class Player {
    private String name;
    private Inventory inventory;
    private int money;
    private Room currentRoom;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return "";
    }

    public Inventory getInventory() {
        return null;
    }

    public int getMoney() {
        return 0;
    }

    public void setMoney(int amount) {
    }
}