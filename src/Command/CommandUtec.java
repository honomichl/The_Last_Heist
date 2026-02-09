package Command;

import Game.MainGame;

public class CommandUtec extends Command {

    public CommandUtec() {
        this.name = "utec";
        this.description = "Po hacknutí zabezpečení je možné utéct. (použití: utec)";
    }

    public boolean isValid() {
        if (MainGame.getInstance().isHacked()&&MainGame.getInstance().getGameData().findItem("slozka").getCurrentLocation().equals("inventar")) {
            if (MainGame.getInstance().getEscape().equals("letecky")&&MainGame.getInstance().getPlayer().getCurrentRoom().getId().equals("balkon")) {
                return true;
            } else if (MainGame.getInstance().getEscape().equals("autem")&&MainGame.getInstance().getPlayer().getCurrentRoom().getId().equals("zahrada")) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    public String execute(String[] args) {
        if (isValid()) {
            MainGame.getInstance().stopGameLoop();
        } else if (!MainGame.getInstance().isHacked()) {
            return "Soustřeď se na hackování!";
        } else if (MainGame.getInstance().getGameData().findItem("slozka").getCurrentLocation().equals("inventar")) {
            return "Je potřeba odnést důkaz! Sežeň tu složku z pracovny";
        } else {
            return "Ji si jist že si na správném místě?";
        }
    return "";

    }

}
