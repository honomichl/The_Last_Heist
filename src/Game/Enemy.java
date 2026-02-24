package Game;

/**
 * Třída reprezentující nepřítele v herním světě.
 * Uchovává informace o nepříteli.
 *
 * @author Filip Honomichl
 */

public class Enemy {
    /** jméno pro výpis */
    private String name;
    /** jméno do kódu */
    private String id;
    /** typ nepřítele každý se chová jinak */
    private String type;
    /** popis */
    private String description;
    /** lokace nepřítele */
    private String currentLocation;


    /**
     * Výchozí konstruktor pro vytvoření prázdného nepřítele.
     */
    public Enemy() {
    }

    /**
     * Konstruktor pro vytvoření plně definovaného nepřítele.
     */
    public Enemy(String name, String id, String type, String description, String currentLocation) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.description = description;
        this.currentLocation = currentLocation;
    }

    /**
     * Odstraní nepřítele z herního světa tím, že nastaví jeho aktuální lokaci na "none".
     */
    public void removeEnemy() {
        this.currentLocation = "none";
    }

    /** gettery */
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }

    /** settery */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }


}