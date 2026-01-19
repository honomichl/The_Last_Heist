public class Room {
    public String getName() {}
    public String getDescription() {}
    public void addItem(Item item) {}
    public void removeItem(Item item) {}
    public void addEnemy(Enemy enemy) {}
    public void removeEnemy(Enemy enemy) {}
    public Room getExit(String roomName) {}
    public void setExit(Room neighbor) {}
    public String getExitNames() {}
    public Enemy getNPC(String name) {}
}