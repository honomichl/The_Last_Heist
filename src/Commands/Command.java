package Commands;

public abstract class Command {
    protected String name;
    protected String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract String execute(String[] args);
}