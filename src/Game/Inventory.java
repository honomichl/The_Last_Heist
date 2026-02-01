package Game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int size;
    private List<Item> items = new ArrayList<>();

    public void setSize(int number) {}
    public int getFreeSlots() { return 0; }
    public void addItem(Item item) {}
    public void removeItem(Item item) {}
    public boolean findItem(String itemName) { return false; }
    public String showItems() { return ""; }
}