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

        if (currentRoomId.equals("pracovna")&&player.getInventory().hasItem("hackovaciStroj")) {
            return true;
        }
        return false;

    }

    public String execute(String[] args) {
        Player player = MainGame.getInstance().getPlayer();
        String currentRoomId = player.getCurrentRoom().getId();

        if (isValid()) {
            MainGame.getInstance().setHacked(true);
            player.getInventory().removeItem("hackovaciStroj");
            MainGame.getInstance().getGameData().findItem("hackovaciStroj").setCurrentLocation("pracovna");
            player.getInventory().addItem("slozka");
            // hluk z hackovani
            MainGame.getInstance().getNoiseMeter().increaseNoise(50/MainGame.getInstance().getAciveHacker().getSkill());
            // hluk ze zjisteni pravdy
            MainGame.getInstance().getNoiseMeter().increaseNoise(20);
            return "Hacker: 'Jsem tam! Teď musím běžet skripty, chvíli počkej.'\n" +
                    "Zatímco čekáš, tvůj zrak zavadí o složku se stejným logem,\n" +
                    "které měla firma, co zničila tvého tátu. Otevřeš ji...\n" +
                    "\nŠOK! Ten milionář není jen náhodný zbohatlík.\n" +
                    "Je to PAN HUBERT – člověk, který nechal tvého tátu zavřít,\n" +
                    "aby sám nemusel platit pokutu 220 milionů!\n" +
                    "Tvůj otec je celých 64 let v tom vězení NEVINNĚ!\n" +
                    "V návalu vzteku jsi prudce vstal a shodil lampu ze stolu! (Hluk se zvýšil)";

        } else if (currentRoomId.equals("pracovna")) {
            return "Pro využití tohohle commandu je potřeba mít u sebe hackovací přístroj.\n" +
                    "Pokud nevíš jaké commandy můžeš použít využij commandu 'prikazy'.";

        } else {
            return "Pro využití tohohle commandu je potřeba nejdříve najít notebook a mít u sebe hackovací přístroj.\n" +
                    "Pokud nevíš jaké commandy můžeš použít využij commandu 'prikazy'.";

        }
    }
}