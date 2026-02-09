package Game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int freeSlots;
    private int size;
    private List<Item> items = new ArrayList<>();






    public void addItem(String itemId) {

        Item item = MainGame.getInstance().getGameData().findItem(itemId);

        if (item != null) {
            if (this.items.size() <= this.freeSlots) {
                this.items.add(item);
                MainGame.getInstance().getGameData().findItem(itemId).setCurrentLocation("inventar");
                this.freeSlots -= item.getSize();
            } else {
                System.out.println("neni dost mista");
            }
        } else {
            System.out.println("pozadovany predmet "+ item +" neexistuje");
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

    public void removeItem(String itemId) {
        Item item = MainGame.getInstance().getGameData().findItem(itemId);

        if (item != null) {
            if (items.contains(item)) {
                items.remove(item);
                MainGame.getInstance().getGameData().findItem(itemId).setCurrentLocation(MainGame.getInstance().getPlayer().getCurrentRoom().getId());
                freeSlots += item.getSize();
            } else {
                System.out.println("pozadovany predmet neni v inventari");
            }
        } else {
            System.out.println("pozadovany predmet neexistuje");
        }

    }

    public String showItems() {
        String text = "";

        if (items.isEmpty()) {
            text = "Inventář je prázdný\n";
        } else {
            text = "V inventáři máš:\n";
            for (Item i : items) {
                text += i.getName() + " - velikost: " + i.getSize() + "\n";
            }
        }

        if (freeSlots == 0) {
            text += "Tvůj inventář je plný.";
        } else {
            text += "Volná místa: " + freeSlots ;
        }

        return text;
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