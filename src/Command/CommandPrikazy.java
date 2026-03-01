package Command;

import Game.MainGame;

public class CommandPrikazy extends Command {

    public CommandPrikazy() {
        this.name = "prikazy";
        this.description = "zobrazi použitelné příkazy. (použití: prikazy)";
    }

    // isValid porad true

    public String execute(String[] args) {
        String posible = "Commandy které je teď♦ možné použít:\n";
        String unposible = "Commandy které není možné použít:\n";

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