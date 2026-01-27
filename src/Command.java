public abstract class Command {
    public String getName() {}
    public String getDescription() {}
    public abstract String execute(String[] args);
}

