package Command;

import Game.MainGame;

public class CommandPrikazy extends Command {

    public CommandPrikazy() {
        this.name = "prikazy";
        this.description = "zobrazi použitelné příkazy. (použití: prikazy)";
    }

    public boolean isValid() {
        return true;

    }

    public String execute(String[] args) {
        String posible = "Commandy které je možné použít:\n";
        String unposible = "Commandy které není možné použít:\n";

        // Projdeme všechny příkazy, které máš v MainGame uložené v mapě nebo seznamu
        for (Command c : MainGame.getInstance().getUserInterface().getCommands()) {
            String radek = ">> " + c.getName() + " = " + c.getDescription() + "\n";

            if (c.isValid()) {
                posible += radek;
            } else {
                unposible += radek;
            }
        }
        return posible + "\n" + unposible;

    }
}