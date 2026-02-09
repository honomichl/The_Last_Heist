package Command;

import java.util.Scanner;

public class CommandKonec extends Command {

    public CommandKonec() {
        this.name = "konec";
        this.description = "Pro okamžité ukončení hry. (použití: konec)";
    }

    public boolean isValid() {
        return true;

    }


    public String execute(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Jsi si jist ze chceš vypnout hru?");
        System.out.print("> ");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("ano")) {
            System.exit(0);
            return "";
        } else {
            return "To bylo o fous!";
        }

    }
}