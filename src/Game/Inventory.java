package Game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int freeSlots;
    private int size;
    private List<Item> items = new ArrayList<>();



    public void addItem(Item item) {
        if (this.items.size() <= freeSlots) {
            items.add(item);
            freeSlots -= item.getSize();
        } else {
            System.out.println("neni dost mista");
        }
    }

    public boolean hasItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return null;
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            freeSlots += item.getSize();
        } else {
            System.out.println("nemas tento item");
        }
    }

    public void showItems() {
        for (Item i : items) {
            System.out.println(i.getName() + " - size: " + i.getSize());
        }
    }



    public List<Item> getItems() {
        return items;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int number) {
        this.size = number;
        this.freeSlots = this.size;
    }


}