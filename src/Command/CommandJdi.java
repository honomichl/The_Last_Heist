package Command;

import Game.*;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.sql.SQLOutput;
import java.util.List;


public class CommandJdi extends Command {

    public CommandJdi() {
        this.name = "jdi";
        this.description = "Pro posouvání po domě. (použití: jdi [lokace])";
    }

    // isValid porad true


    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "Kam chceš jít? Pokud nevíš kam můžeš použij příkaz: Vychody";
        }

        String roomId = parameters[0];
        Player player = MainGame.getInstance().getPlayer();
        Room currentRoom = player.getCurrentRoom();
        List<String> validExits = currentRoom.getExits();
        boolean valid = false;

        // zjisti jestli zadana mistnost je vedle current mistnosti
        for (String exitId : validExits) {
            if (exitId.equals(roomId)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            return  "Nelze jít do '" + roomId + "' odtud. Pokud nevíš kam můžeš použij příkaz: 'Vychody'.";
        }

        if (roomId.equals("galerie")&&!MainGame.getInstance().isHacked()) {
            return "Galerie je zamčená skus se vrátit po hacknutí notebooku.";
        }

        if (roomId.equals("trezor")&&MainGame.getInstance().getGameData().findRoom("trezor").isHidden()) {
            return "Trezor je potřeba nejdřív najít pomocí příkazu: 'prohledej'.";
        }

        if (roomId.equals("zahrada")) {
            if(player.getInventory().hasItem("maso")) {
                MainGame.getInstance().getGameData().findEnemy("dog1").removeEnemy();
                player.getInventory().removeItem("maso");
                System.out.println("Pes je do konce hry zabaven nemusíš se ho už bát");
            } else if (MainGame.getInstance().getGameData().findEnemy("dog1").getCurrentLocation().equals("zahrada")) {
                MainGame.getInstance().getNoiseMeter().increaseNoise(8); //ZVUK zahnani psem
                return "Pes tě vyhnal zpátky dovnitř a způsobil dost hluku.";
            } else if (currentRoom.getId().equals("jidelna")){
                MainGame.getInstance().getNoiseMeter().increaseNoise(4); //ZVUK pruchod oknem
                System.out.println("Jít oknem není ta nejpotišejší věc");
            }
        }

        if (currentRoom.getId().equals("zahrada")&&roomId.equals("jidelna")) {
            MainGame.getInstance().getNoiseMeter().increaseNoise(4); //ZVUK pruchod oknem
            System.out.println("Jít oknem není ta nejpotišejší věc");
        }



        if (roomId.equals("kuchyne")&&currentRoom.getId().equals("zahrada")) {
            MainGame.getInstance().getNoiseMeter().increaseNoise(4); //ZVUK pruchod oknem
            System.out.println("Jít oknem není ta nejpotišejší věc");
        }





        Room nextRoom = MainGame.getInstance().getAllRooms().get(roomId);


        // logika interakci s enemy (pes uz vyresen)
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);

            for (Enemy e : MainGame.getInstance().getGameData().enemies) {

                // se strazci
                if (e.getCurrentLocation().equals(nextRoom.getId()) && e.getType().equals("guard")) {
                    System.out.println("!!! Překvapil tě strážce " + e.getName() + " !!\n");

                    if (!player.getInventory().hasItem("nuz")) {
                        System.out.println("Bez nože nejsi schopen boje strážce tě hravě přemůže a svolá poplach.");
                        MainGame.getInstance().fail();
                    } else {
                        System.out.println("Skočil jsi na něj se svým nožem jestě že sis ho vzal.");
                        System.out.println("Napřáhneš se na něj a snažíš se ho zranit.");
                    }

                    double chance = switch (MainGame.getInstance().getDifficulty()) {
                        case 1 -> 0.8;
                        case 2 -> 0.7;
                        case 3 -> 0.6;
                        default -> 0;
                    };

                    while (e.getCurrentLocation().equals(nextRoom.getId())) {

                        if (Math.random() < chance) {
                            System.out.println("Strefil ses! Strážce je mimo hru.");
                            e.removeEnemy();
                        } else {
                            System.out.println("Strážce se vyhnul a udeřil tě nazpět! Zkoušíš to znovu...");
                            MainGame.getInstance().getNoiseMeter().increaseNoise(5); // ZVUK boj
                        }
                    }
                    break;
                }

                // se starym strazcem
                else if (e.getCurrentLocation().equals(nextRoom.getId()) && e.getType().equals("oldGuard")) {
                    System.out.println("V posledni vteřině před vchodem do ložnice si všimneš starého strážce, který na tebe míří pistolí.\n" +
                                "Zadrž, mladíku! Než tě pustím dál, musíš mi odpovědět na dvě otázky. Stačí ANO nebo NE.\n" +
                                "Míří na tebe pistolí nesmíš riskovat. Máš štěstí, že je zmatený.\n" +
                                "Odpovidej jen ano/ne bez mezer a dej pozor ať se nepřepíšeš mohlo by tě to stát život!");

                    java.util.Scanner sc = new java.util.Scanner(System.in);

                    System.out.println("\nOtázka první: Je pravda, že kód Enigma, používaný za druhé světové války, rozluštil tým vedený Alanem Turingem?");
                    System.out.print("> ");
                    String odpoved1 = sc.nextLine().toLowerCase();

                    if (!odpoved1.equals("ano")) {
                        System.out.println("Strážce zavrtí hlavou: Chyba! Historie ti nic neříká. Poplach!!!!");
                        MainGame.getInstance().fail();
                    } else {
                        System.out.println("Správně, jdeme dál!");
                    }

                    System.out.println("\nOtázka druhá: Je pravda, že tučňáci žijí ve volné přírodě na severním pólu?");
                    System.out.print("> ");
                    String odpoved2 = sc.nextLine().toLowerCase();

                    if (!odpoved2.equals("ne")) {
                        System.out.println("Strážce si odfrkne: Špatně! Ani nevíš, kde žijí ptáci. Poplach!!!!");
                        MainGame.getInstance().fail();
                    } else {
                        System.out.println("\n'Správně... vidím, že máš za ušima. Můžeš jít, ale pohybuj se potichu spí tam můj šéf.'");
                        e.removeEnemy();
                    }
                }

            }
        }
        return "Přesunul si se do: " + nextRoom.getName();

    }

}

