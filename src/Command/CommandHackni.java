package Command;

import Game.MainGame;
import Game.Player;

public class CommandHackni extends Command {
    public CommandHackni() {
        this.name = "hackni";
        this.description = "Pro hacknutí zabezpečení domu. (použití: hackni)";
    }

    public boolean isValid() {
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();

        if (currentRoomId.equals("pracovna")&&player.getInventory().hasItem("hackovaciPristroj")) {
            return true;
        }
        return false;

    }

    public String execute(String[] args) {
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();

        if (isValid()) {
            MainGame.getInstance().setHacked(true);
            player.getInventory().removeItem("hackovaciPristroj");
            MainGame.getInstance().getGameData().findItem("hackovaciPristroj").setCurrentLocation("");
            player.getInventory().addItem("slozka");
            // hluk z hackovani
            MainGame.getInstance().getNoiseMeter().increaseNoise(40/MainGame.getInstance().getAciveHacker().getSkill());
            // hluk ze zjisteni pravdy
            MainGame.getInstance().getNoiseMeter().decreaseNoise(30);
            return "Hacker: 'Jsem tam! Teď musím běžet skripty, chvíli počkej.'\n" +
                    "Zatímco čekáš, tvůj zrak zavadí o složku se stejným logem,\n" +
                    "které měla firma, co zničila tvého tátu. Otevřeš ji...\n" +
                    "\nŠOK! Ten milionář není jen náhodný zbohatlík.\n" +
                    "Je to PAN HUBERT – člověk, který nechal tvého tátu zavřít,\n" +
                    "aby sám nemusel platit pokutu 220 milionů!\n" +
                    "Tvůj otec je celých 64 let v tom vězení NEVINNĚ!\n" +
                    "V návalu vzteku jsi prudce vstal a shodil lampu ze stolu! (Hluk se zvýšil)";

        } else if (currentRoomId.equals("pracovna")) {
            return "Pro využití tohohle commandu je potřeba mít u sebe hackovací přístroj." +
                    "Pokud nevíš jaké commandy můžeš použít využij commandu 'prikazy'.";

        } else {
            return "Pro využití tohohle commandu je potřeba nejdříve najít notebook a mít u sebe hackovací přístroj. " +
                    "Pokud nevíš jaké commandy můžeš použít využij commandu 'prikazy'.";

        }
    }
}