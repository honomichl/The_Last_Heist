package Game;

public class Hacker {
    // jmeno pro vypis
    private String name;
    // jmeno do kodu
    private String id;
    // popis
    private String description;
    // cena hackera v procentech
    private int price;
    // skill pro vypocitani hluku
    private int skill;

    public Hacker() {
    }

    public Hacker(String name, String id, String description, int price, int skill) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.skill = skill;
    }


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