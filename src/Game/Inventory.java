package Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída Inventory reprezentuje inventář hráče.
 * Stará se o správu předmětů, kontrolu kapacity a manipulaci s obsahem inventáře.
 *
 * @author Filip Honomichl
 */

public class Inventory {
    /** prázdné sloty v invetář */
    private int freeSlots;
    /** velikost inventáře */
    private int size;
    /** itemy v inventáři */
    private final List<Item> items = new ArrayList<>();

    /**
     * Pokusí se přidat předmět do inventáře na základě jeho id.
     * Metoda kontroluje existenci předmětu a zda je v inventáři dostatek místa.
     * Pokud je předmět přidán, aktualizuje se jeho lokace na "inventar" a sníží se počet volných slotů.
     *
     * @param itemId id předmětu, který má být přidán.
     */
    public void addItem(String itemId) {
        Item item = MainGame.getInstance().getGameData().findItem(itemId);

        if (item != null) {
            if (item.getSize() <= freeSlots) {
                this.items.add(item);
                item.setCurrentLocation("inventar");
                freeSlots = freeSlots - item.getSize();
            } else {
                System.out.println("neni dost mista");
            }
        } else {
            System.out.println("pozadovany predmet "+ item +" neexistuje");
        }

    }

    /**
     * Pokusí se odebrat předmět z inventáře na základě jeho id.
     * Metoda kontroluje existenci předmětu v inventáři.
     * Pokud je předmět odebrán, aktualizuje se jeho lokace na hráčovu current room id a zvýší se počet volných slotů.
     *
     * @param itemId id předmětu, který má být odebrán.
     */
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

    /**
     * Prověří, zda se předmět s daným id nachází v inventáři.
     *
     * @param itemId id hledaného předmětu.
     * @return true pokud je předmět v inventáři, jinak false.
     */
    public boolean hasItem(String itemId) {
        for (Item i : items) {
            if (i.getId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vytvoří přehledný text o předmětech v inventáři, velikosti jednotlivých předmětů a zbývajícím volným místě.
     *
     * @return přehledný text o obsahu inventáře.
     */
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

    /**
     * Zkontroluje, zda se konkrétní předmět vejde do zbývajícího prostoru v inventáři.
     *
     * @param item Objekt předmětu.
     * @return true pokud je v inventáři dostatek místa, jinak false.
     */
    public boolean checkSpace(Item item) {
        return item.getSize() <= this.freeSlots;
    }

    /** gettery */
    public List<Item> getItems() {
        return items;
    }
    public int getFreeSlots() {
        return freeSlots;
    }
    public int getSize() {
        return size;
    }

    /**
     * Nastavý velikost inventáře a počet volných slotů.
     *
     * @param number nová velikost inventáře.
     */
    public void setSize(int number) {
        this.size = number;
        this.freeSlots = this.size;
    }


}