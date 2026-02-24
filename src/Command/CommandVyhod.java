package Command;

import Game.*;

public class CommandVyhod extends Command {

    public CommandVyhod() {
        this.name = "vyhod";
        this.description = "Pro vyhození věcí. (použití: vyhod [věc])";
    }

    public boolean isValid() {
        if (MainGame.getInstance().getPlayer().getInventory().showItems().isEmpty()) {
            return false;
        }
        return true;

    }

    public String execute(String[] args) {
        if (args.length < 1) {
            return "Musíš napsat, co chceš vyhodit!";
        }

        String itemName = args[0];
        String itemId = MainGame.getInstance().getGameData().getItemId(itemName);
        Player player = MainGame.getInstance().getPlayer();

        if (itemId == null) {
            return "Item neexistuje zkontroluj jestli si ho napsal správně.\n" +
                "Pokud si nejsi jistý co máš v inventáři použij command 'inventar'.";
        }
        if (!player.getInventory().hasItem(itemId)) {
            return "Tento item nemáš v inventáři.\n" +
                    "Pokud si nejsi jistý co v něm máš použij command 'inventar'.";
        }

        player.getInventory().removeItem(itemId);

        return "Odebral si " + itemId + " z inventare.\n" +
                "Volná místa: " + player.getInventory().getFreeSlots();

    }
}