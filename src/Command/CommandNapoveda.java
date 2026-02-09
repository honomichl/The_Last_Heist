package Command;

import Game.MainGame;
import Game.Player;

public class CommandNapoveda extends Command {

    public CommandNapoveda() {
        this.name = "napoveda";
        this.description = "Pro dostání nápovědy od hackera. (použití: napoveda)";
    }

    public boolean isValid() {
        if (MainGame.getInstance().getPlayer().getInventory().hasItem("vysilacka")){
            return true;
        }
        return false;

    }

    public String execute(String[] args) {
        if (!isValid()) {
            return "Bez vysílačky se s hackerem nespojíš.";
        }


        String hackerName = MainGame.getInstance().getAciveHacker().getName();
        int noise = MainGame.getInstance().getNoiseMeter().getNoiseLevel/(MainGame.getInstance().getNoiseMeter().getMaxNoise()/100);
        String escape = MainGame.getInstance().getEscape();
        String text = hackerName + ": ";
        MainGame.getInstance().getNoiseMeter().increaseNoise(6); //ZVUK


        if (!MainGame.getInstance().isHacked()) {
            return hackerName + ": musíš najít notebook, koukni se do pracovny.";
        }
        if (noise < 60){
            text += "máš prostor zajít pro hlavní loot věř mi. ";
        } else if (noise >= 60 && noise <= 80){
            text += "můžeš ještě vzít pár maličkostí ale skus se uz balit. ";
        } else {
            text += "pan Hubert uz je skoro vzhůru nic neriskuj a makej na pryč. ";
        }
        if (escape.equals("letecky")){
            text += "Dávej pozor helikoptera je dost slyšet musíš na to myslet. ";
        } else {
            text += "Nezapomeň na toho psa co tě málem kousl směrem sem, budeš mu muset vzít něco dobrýho.";
        }

        return text;

    }
}