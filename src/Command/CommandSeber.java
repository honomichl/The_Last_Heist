package Command;

import Game.*;
import java.util.*;

public class CommandSeber extends Command {

    public CommandSeber() {
        this.name = "seber";
        this.description = "Pro sebrání objevených věcí. (použití: seber [věc])";
    }

    public boolean isValid() {
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();

        for (Item item : MainGame.getInstance().getGameData().items) {
            if (item.getCurrentLocation().equals(currentRoomId)&&!item.isHidden()) {
                return true;
            }
        }
        return false;

    }

    public String execute(String[] args) {
        if (args.length < 1) {
            return "Musíš napsat, co chceš sebrat. (např. seber nuz)";
        }

        String itemId = args[0];
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();
        int x = player.getInventory().getFreeSlots();

        Item selectedItem = MainGame.getInstance().getGameData().findItem(itemId);

        if (selectedItem == null) {
            return "Předmět '" + itemId + "' neexistuje.";
        }

        if (!selectedItem.getCurrentLocation().equalsIgnoreCase(currentRoomId) || selectedItem.isHidden()) {
            return "Předmět není v této místosti nebo zatím nebyl objeven.";
        }

        if (selectedItem.isMainLoot()&&MainGame.getInstance().getMainTarget().equals(selectedItem.getName().toLowerCase())) {
            player.getInventory().addItem(itemId);
            MainGame.getInstance().getNoiseMeter().increaseNoise(10); //ZVUK
            return "Výborně sehnal si hlavní loot gratuluju teď co nejrychlejš pryč, čas zachránit otce!";
        } else if (selectedItem.isMainLoot()) {
            return "Tvůj batoh není přizpůsoben tomuhle main targetu soustřeď se na " + MainGame.getInstance().getMainTarget().getName();
        } else {
            player.getInventory().addItem(itemId);
        }



        if (x < player.getInventory().getFreeSlots()) {
            return "Přidal si " + itemId + " do inventare.\n" +
                    "Volná místa: " + player.getInventory().getFreeSlots();

        }

        return "";
    }
}