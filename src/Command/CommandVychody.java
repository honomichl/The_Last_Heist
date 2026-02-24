package Command;

import Game.MainGame;
import Game.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandVychody extends Command {

    public CommandVychody() {
        this.name = "vychody";
        this.description = "Zobrazí všechny dostupné východy. (použití: východy)";
    }

    // isValid porad true

    public String execute(String[] args) {
        Player player = MainGame.getInstance().getPlayer();
        String text = "východy z místnosti '" + player.getCurrentRoom().getName() + "': \n";

        for (String exit : player.getCurrentRoom().getExits()) {
            if (exit.equals("galerie")&&!MainGame.getInstance().isHacked()) {
                text += exit + "(zamčená) \n";
            } else if (!MainGame.getInstance().getGameData().findRoom(exit).isHidden()) {
                text += exit + " \n";
            }
        }

        return text;
    }
}
