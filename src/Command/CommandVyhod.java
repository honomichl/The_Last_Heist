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

        String itemId = args[0];
        Player player = MainGame.getInstance().getPlayer();
        int x = player.getInventory().getFreeSlots();

        player.getInventory().removeItem(itemId);

        if (x < player.getInventory().getFreeSlots()) {
            return "Odebral si " + itemId + " z inventare.\n" +
                    "Volná místa: " + player.getInventory().getFreeSlots();

        }

        return "";
    }
}