package Game;

public class Hacker {
    private String name;
    private String id;
    private String description;
    private int price;
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