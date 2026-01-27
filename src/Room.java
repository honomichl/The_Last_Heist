public class Room {
    private String name;
    private String description;
    private List<Item> items;
    private List<Enemy> enemies;
    private Map<String, Room> exits;



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