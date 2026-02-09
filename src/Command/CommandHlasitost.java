package Command;

import Game.MainGame;
import Game.Player;

public class CommandHlasitost extends Command {
    public CommandHlasitost() {
        this.name = "hlasitost";
        this.description = "zobrazí procenta probuzení pana Huberta. (použití: hlasitost)";
    }

    public boolean isValid() {
        return true;

    }

    public String execute(String[] args) {
        return MainGame.getInstance().getNoiseMeter().checkNoise();

    }
}