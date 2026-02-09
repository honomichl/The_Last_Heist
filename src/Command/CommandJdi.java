package Command;

import Game.*;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.List;


public class CommandJdi extends Command {

    public CommandJdi() {
        this.name = "jdi";
        this.description = "Pro posouvání po domě. (použití: jdi [lokace])";
    }

    public boolean isValid() {
        return true;

    }


    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "Kam chceš jít? Pokud nevíš kam můžeš použij příkaz: Vychody";
        }

        String roomName = parameters[0];

        Player player = MainGame.getInstance().getPlayer();
        Room currentRoom = player.getCurrentRoom();


        List<String> validExits = currentRoom.getExits();
        boolean valid = false;






        for (String exitName : validExits) {
            if (exitName.equals(roomName)) {
                valid = true;
                break;
            }
        }

        if (roomName.equals("galerie")&&!MainGame.getInstance().isHacked()) {
            return "Galerie je zamčená skus se vrátit po hacknutí notebooku.";
        }

        if (roomName.equals("trezor")&&!MainGame.getInstance().getGameData().findRoom("trezor").isHidden()) {
            return "Trezor je potřeba nejdřív najít pomocí příkazu: 'prohledej'.";
        }

        if (roomName.equals("zahrada")&&valid) {
            if(player.getInventory().hasItem("maso")) {
                MainGame.getInstance().getGameData().findEnemy("dog1").removeEnemy();
                player.getInventory().removeItem("maso");
                System.out.println("Pes je do konce hry zabaven nemusis se ho uz bat");
            } else if (MainGame.getInstance().getGameData().findEnemy("dog1").getCurrentLocation().equals("zahrada")) {
                MainGame.getInstance().getNoiseMeter().increaseNoise(10); //ZVUK zahnani psem
                return "Pes te vyhnal zpatky dovnitr a zpusobil dost hluku.";
            } else if (currentRoom.getId().equals("kuchyne")){
                MainGame.getInstance().getNoiseMeter().increaseNoise(4); //ZVUK pruchod oknem
                System.out.println("Jit oknem neni ta nejpotisejsi vec");
            }
        }

        if (roomName.equals("kuchyne")&&currentRoom.getId().equals("zahrada")) {
            MainGame.getInstance().getNoiseMeter().increaseNoise(4); //ZVUK pruchod oknem
            System.out.println("Jit oknem neni ta nejpotisejsi vec");
        }



        if (!valid) {
            return  "Nelze jít do '" + roomName + "' odtud. Pokud nevíš kam můžeš použij příkaz: 'Vychody'.";
        }










        Room nextRoom = MainGame.getInstance().getAllRooms().get(roomName);

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);

            for (Enemy e : MainGame.getInstance().getGameData().enemies) {
                if (e.getCurrentLocation().equals(nextRoom.getId()) && e.getType().equals("guard")) {
                    System.out.println("!!! Překvapil tě strážce " + e.getName() + " !!!");

                    while (e.getCurrentLocation().equals(nextRoom.getId())) {
                        double chance = 0;

                        switch (MainGame.getInstance().getDifficulty()) {
                            case 1: chance = 0.8; break;
                            case 2: chance = 0.7; break;
                            case 3: chance = 0.6; break;
                        }

                        if (Math.random() < chance) {
                            System.out.println("Vyhrál jsi! Strážce je mimo hru.");
                            e.removeEnemy();
                        } else {
                            System.out.println("Strážce tě udeřil! Zkoušíš to znovu...");
                            MainGame.getInstance().getNoiseMeter().increaseNoise(5); // ZVUK boj
                        }
                    }
                    break; // Strážce vyřízen, můžeme přestat hledat v seznamu
                } else if (e.getCurrentLocation().equals(nextRoom.getId()) && e.getType().equals("oldGuard")) {
                        System.out.println("U vchodu do ložnic stojí starý strážce a mhouří oči: ");
                        System.out.println("'Zadrž, mladíku! Než tě pustím dál, musíš mi odpovědět na dvě otázky. Stačí ANO nebo NE.'");

                        java.util.Scanner sc = new java.util.Scanner(System.in);


                        System.out.println("\nOtázka první: Je pravda, že kód Enigma, používaný za druhé světové války, rozluštil tým vedený Alanem Turingem?");
                        String odpoved1 = sc.nextLine().toLowerCase();

                        if (!odpoved1.equals("ano")) {
                            MainGame.getInstance().getNoiseMeter().increaseNoise(1000);
                            return "Strážce zavrtí hlavou: Chyba! Historie ti nic neříká. Poplach!!!!";
                        }

                        System.out.println("\nOtázka druhá: Je pravda, že tučňáci žijí ve volné přírodě na severním pólu?");
                        String odpoved2 = sc.nextLine().trim().toLowerCase();

                        if (!odpoved2.equals("ne")) {
                            MainGame.getInstance().getNoiseMeter().increaseNoise(1000);
                            return "Strážce si odfrkne: Špatně! O vesmíAni nevíš, kde žijí ptáci. Poplach!!!!";
                        }

                        System.out.println("\n'Správně... vidím, že máš za ušima. Můžeš jít.'");
                        e.removeEnemy();
                }

                }
            }

            return "Přesunul si se do: " + nextRoom.getName() + "\n" + nextRoom.getDescription();
        }

    }

