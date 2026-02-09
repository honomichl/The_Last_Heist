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

    public boolean isValid() {
        return true;
    }

    public String execute(String[] args) {
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomName = player.getCurrentRoom().getName();
        String text = "východy z místnosti" + currentRoomName + ": ";

        for (String exit : player.getCurrentRoom().getExits()) {
            if (exit.equals("gallerie")&&!MainGame.getInstance().isHacked()) {
                text += exit + "(zamčená) \n";
            } else if (!MainGame.getInstance().getGameData().findRoom(exit).isHidden()) {
                text += exit + " \n";
            }
        }

        return text;
    }
}
