package Command;

import Game.MainGame;

public class CommandInventar extends Command {
    public CommandInventar() {
        this.name = "inventar";
        this.description = "zobrazí co máš v inventáři a kolik zbývá volných míst. (použití: inventar)";
    }

    // isValid porad true

    public String execute(String[] args) {
        return MainGame.getInstance().getPlayer().getInventory().showItems();

    }
}