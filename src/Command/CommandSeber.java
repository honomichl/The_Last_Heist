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
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();
        if (args.length < 1) {
            return "Musíš napsat, co chceš sebrat. (např. seber nuz)";
        }

        String itemName = args[0];
        int freeSlots = player.getInventory().getFreeSlots();
        String itemId = MainGame.getInstance().getGameData().getItemId(itemName);
        Item selectedItem = MainGame.getInstance().getGameData().findItem(itemId);
        Item mainTarget = MainGame.getInstance().getMainTarget();

        if (selectedItem == null) {
            return "Předmět '" + itemName + "' neexistuje.";
        }

        if (!selectedItem.getCurrentLocation().equals(currentRoomId) || selectedItem.isHidden()) {
            return "Předmět není v této místosti nebo zatím nebyl objeven.";
        }

        if (selectedItem.isMainLoot() ) {
            if (!itemId.equals(mainTarget.getId())) {
                return "Tvůj batoh není přizpůsoben tomuhle main targetu soustřeď se na " + mainTarget.getName();
            } else if (!player.getInventory().checkSpace(selectedItem)) {
                return "Nejdřív udělej místo pro hlavní loot.\n" +
                        "Velikost hlavního lootu: " + selectedItem.getSize();
            } else if ((mainTarget.getId().equals("zlato") && currentRoomId.equals("trezor"))||(mainTarget.getId().equals("velkyObraz") && currentRoomId.equals("galerie")) && selectedItem.getId().equals(mainTarget.getId())) {
                player.getInventory().addItem(selectedItem.getId());
                MainGame.getInstance().getNoiseMeter().increaseNoise(10); //ZVUK sebrani main lootu
                return "Výborně sehnal si hlavní loot gratuluju teď co nejrychlejš pryč, čas zachránit otce!";
            } else {
                return "Tenhle hlavní loot nelze momentálně sebrat.";
            }
        }

        if (player.getInventory().checkSpace(selectedItem)) {
            player.getInventory().addItem(itemId);
            return "Přidal si " + itemId + " do inventare.\n" +
                    "Volná místa: " + player.getInventory().getFreeSlots();
        }

        return "Nemáš dost místa na "+ itemName +"\n" +
                "velikost předmětu: " + selectedItem.getSize() +"\n" +
                "Tvoje volná místa: " + player.getInventory().getFreeSlots();

    }
}