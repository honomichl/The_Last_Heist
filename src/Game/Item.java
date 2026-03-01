package Game;

import static java.lang.Math.random;

/**
 * Třída reprezentuje herní předmět (Item).
 * Předměty mohou být buď isLoot s náhodnou peněžní hodnotou,
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
    /** je to hlavní isLoot? */
    private boolean mainLoot;
    /** random hodnota itemu z rozmezí (zaokrouhleno na tísíce) */
    private int value;
    /** lokace itemu */
    private String currentLocation;
    /** má hodnotu? */
    private boolean loot;
    /** je ukrytý? */
    private boolean hidden;
    /** minimální hodnota pro value */
    private int minValue;
    /** maximální hodnota pro value */
    private int maxValue;


    /**
     * Výchozí konstruktor pro vytvoření prázdného itemu.
     */
    public Item() {
    }

    /**
     * konstruktor pro vytvoření plně definovaného předmětu.
     * Pokud je předmět typu loot hodnota předmětu je automaticky generována v rozmezí
     * mezi minValue a maxValue * 1000.
     * @param minValue minimalní hodnota pro random v tisících.
     * @param maxValue maximalní hodnota pro random v tisících.
     */
    public Item(String name, String id, String description, int size, boolean mainLoot, boolean loot, int minValue, int maxValue, String currentLocation, boolean hidden) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.size = size;
        this.mainLoot = mainLoot;
        this.currentLocation = currentLocation;
        this.hidden = hidden;
        this.loot = loot;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Vrátí cenu předmětu.
     * Pokud hodnota ještě nebyla vypočítána a předmět je označen jako loot,
     * vygeneruje náhodnou cenu v zadaném rozmezí (minValue až maxValue)
     * a vynásobí ji tisícem.
     * @return Celková peněžní hodnota předmětu v tisících, nebo 0 pokud předmět není loot.
     */
    public int getValue() {
        if (this.loot && this.value == 0 && this.maxValue > 0) {
            this.value = ((int)(Math.random() * (maxValue - minValue + 1)) + minValue) * 1000;
        }
        return this.value;
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
        return mainLoot;
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


