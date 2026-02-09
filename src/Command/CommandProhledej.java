package Command;

import Game.*;

public class CommandProhledej extends Command {

    public CommandProhledej() {
        this.name = "prohledej";
        this.description = "Pro prohledání mísnosti po zajímavých věcech. (použití: prohledej)";
    }

    public boolean isValid() {
        return true;

    }

    public String execute(String[] args) {
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();
        boolean something = false;
        String text = "V místnosti '" + currentRoomId + "' si našel: \n";


        for (Item item : MainGame.getInstance().getGameData().items) {
            if (item.getCurrentLocation().equals(currentRoomId)) {
                item.setHidden(false);
                something = true;
                if (item.getValue()>0) {
                    text += item.getName() + " - cena: " + item.getValue() + "000 kč\n";
                } else {
                    text += item.getName() + "\n";
                }

            }
        }

        MainGame.getInstance().getNoiseMeter().increaseNoise(3); //ZVUK
        System.out.println("Udělal si hluk.");

        if (MainGame.getInstance().getPlayer().getCurrentRoom().getId().equals("loznice")) {
            MainGame.getInstance().getGameData().findRoom("trezor").setHidden(false);

        }

        if (something) {
            return text;
        } else {
            return "Místnost '" + currentRoomId + "' je prazndná.";
        }

    }
}