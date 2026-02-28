package Game;

/**
 * Reprezentuje postavu hackera v herním systému.
 * Třída uchovává informace o identitě hackera, jeho popisu,
 * ceně a herních dovednostech.
 *
 * @author Filip Honomichl
 */

public class Hacker {
    /** jméno pro výpis */
    private String name;
    /** jméno do kódu */
    private String id;
    /** popis */
    private String description;
    /** cena hackera v procentech */
    private int price;
    /** skill pro vypočítání hluku */
    private int skill;


    /**
     * Výchozí konstruktor pro vytvoření prázdného hackera.
     */
    public Hacker() {
    }

    /**
     * konstruktor pro vytvoření plně definovaného hackera.
     */
    public Hacker(String name, String id, String description, int price, int skill) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.skill = skill;
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
    public int getPrice() {
        return price;
    }
    public int getSkill() {
        return skill;
    }

}