package Game;

/**
 * Třída reprezentuje herní předmět (Item).
 * Předměty mohou být buď loot s náhodnou peněžní hodnotou,
 * nebo běžné předměty bez hodnoty.
 *
 * @author Filip Honomichl
 */

public class Item {
    /** jméno pro výpis */
    private String name;
    /** jméno do kódu */
    private String id;
    /** popis */
    private String description;
    /** počet slotů co item zabere */
    private int size;
    /** je to hlavní loot? */
    private boolean isMainLoot;
    /** minimalní hodnota pro random v tisících */
    private int minValue;
    /** maximalní hodnota pro random v tisících */
    private int maxValue;
    /** random hodnota itemu z rozmezí (zaokrouhleno na tísíce) */
    private int value;
    /** lokace itemu */
    private String currentLocation;
    /** má hodnotu? */
    private boolean loot;
    /** je ukrytý? */
    private boolean hidden;

    /**
     * Výchozí konstruktor pro vytvoření prázdného itemu.
     */
    public Item() {
    }

    /**
     * Konstruktor pro předmět typu "Loot" (předmět s peněžní hodnotou).
     * Hodnota předmětu je automaticky generována v rozmezí mezi minValue a maxValue * 1000.
     */
    public Item(String name, String id, String description, int size, boolean isMainLoot, int minValue, int maxValue, String currentLocation, boolean hidden) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.size = size;
        this.isMainLoot = isMainLoot;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = ((int)(Math.random() * (maxValue - minValue + 1)) + minValue) * 1000;
        this.currentLocation = currentLocation;
        this.loot = true;
        this.hidden = hidden;
    }

    /**
     * Konstruktor pro běžný předmět (bez peněžní hodnoty).
     */
    public Item(String name, String id, String description, int size, String currentLocation, boolean hidden) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.size = size;
        this.value = 0;
        this.currentLocation = currentLocation;
        this.loot = false;
        this.isMainLoot = false;
        this.hidden = hidden;
    }

    /** gettery */
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public int getSize() {
        return size;
    }
    public boolean isMainLoot() {
        return isMainLoot;
    }
    public int getValue() {
        return value;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public boolean isLoot() {
        return loot;
    }
    public boolean isHidden() {
        return hidden;
    }

    /** settery */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}


