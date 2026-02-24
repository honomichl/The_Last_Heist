package Game;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Filip Honomichl
 */

public class MainGame {
    // instance
    private static MainGame instance;
    // hrac
    private final Player player = new Player();
    // noise meter
    private final NoiseMeter noiseMeter = new NoiseMeter();
    // game data
    private final GameData gameData = GameData.loadFromResources("/gamedata.json");
    // ui
    private final UserInterface userInterface = new UserInterface();

    // obtiznost hry (3 = nejtezsi; 1 nejlehci)
    private int difficulty; // pri nastavovani inventare,  pri nastavovani noise meteru
    // uklada vzbraneho hackera
    private Hacker activeHacker;
    // jak se do budovy dostane
    private String infiltration;
    // jak se z budovy dostane
    private String escape;
    // hacknul uz laptop v pracovne a zjisti pravdu?
    private boolean hacked = false;
    // uklada vsechny mistnosti
    private final Map<String, Room> allRooms = new HashMap<>();
    // jestli jede hlavni ciklus
    private boolean running = true;
    // main target
    private Item mainTarget;



    public void StartGame() {
        setUp();
        createWorld();
        gameLoop();
        endGame();
    }

    public MainGame() {
        instance = this;
    }

    public static MainGame getInstance() {
        return instance;
    }

    public void setUp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 = nejlehčí, 2 = střední, 3 = těžká");
        System.out.println("Zvolte obtížnost (1-3): ");

        while (true) {
            System.out.print("> ");
            try {
                this.difficulty = scanner.nextInt();
                if (difficulty == 1||difficulty == 2||difficulty == 3){
                    break;
                } else {
                    System.out.println("Zkus od 1 do 3");
                }
            } catch (Exception e) {
                System.out.println("Zkus od 1 do 3");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        System.out.println("--- PLÁNOVACÍ MÍSTNOST ---");
        System.out.println("Sedíš v místnosti se svým starým parťákem Peterem. Ten se v minulém heistu zranil,");
        System.out.println("takže ti tentokrát bude krýt záda jen na dálku a pomůže s přípravou.");
        System.out.println("Peter: 'Robine, tohle je velká ryba. Jeden z nejbohatších lidí v kraji. Máme šanci na balík.'");
        System.out.println("Robin: 'Hlavně když to vyjde. Potřebuju peníze, ne vězení jako táta.'\n");

        System.out.println("\nPeter: 'Jak se dostaneš dovnitř?'");
        System.out.println("Autem (vysadím tě u zahrady)");
        System.out.println("Letecky (výsadek padákem na balkon)");

        while(true) {
            System.out.print("> ");
            this.infiltration = scanner.nextLine().toLowerCase();
            if (infiltration.equals("letecky")) {
                System.out.println("Peter: 'Padákem na balkon... Odvážné, ale tiché.'");
                break;
            } else if (infiltration.equals("autem")) {
                System.out.println("Peter: 'Auto je jistota. Půjdeš buď oknem, nebo hlavními dveřmi.'");
                break;
            } else {
                System.out.println("Zkus to znovu");
            }
        }

        System.out.println("\nPeter: 'A jak se odtud vypaříš?'");
        System.out.println("Autem (vyzvednu tě u zahrady)");
        System.out.println("Letecky (odlet helikoptérou z balkonu)");

        while(true) {
            System.out.print("> ");
            this.escape = scanner.nextLine().toLowerCase();
            if (escape.equals("letecky")) {
                System.out.println("Peter: 'Helikoptéra bude připravená na balkoně.'");
                break;
            } else if (escape.equals("autem")) {
                System.out.println("Peter: 'Budu čekat v autě u zahrady. Nezapomeň na psy!'");
                break;
            } else {
                System.out.println("Zkus to znovu");
            }
        }

        System.out.println("\nPeter: 'Kterého hackera mám najmout?'");
        System.out.println("Gabriel (bere 20%, ale tichý přístroj)");
        System.out.println("Erik (bere 10%, ale hlučný přístroj)");

        boolean run = true;
        while (run) {
            System.out.print("> ");
            String hackr = scanner.nextLine().toLowerCase();
            for (Hacker h : gameData.hackers) {
                if (hackr.equals(h.getName().toLowerCase())) {
                    this.activeHacker = gameData.findHacker(h.getId());
                    run = false;
                }
            }
            if (run) {
                System.out.println("Zkus to znovu");
            }
        }

        if (this.activeHacker.getId().equalsIgnoreCase("gabriel")) {
            System.out.println("Peter: 'Gabriel je profík. S ním tě nikdo neuslyší.'");
        } else if (activeHacker.getId().equalsIgnoreCase("erik")) {
            System.out.println("Peter: 'Erik je levnej, ale jeho vybavení dělá pěknej randál.'");
        } else {
            System.out.println("Peter: '" + activeHacker.getName() + "' Skvělá volba.'");
        }

        System.out.println("\nPeter: 'A co je náš hlavní cíl?'");
        System.out.println("zlato: Zlaté cihly v trezoru");
        System.out.println("obraz: Velký obraz v galerii");

        while(true) {
            System.out.print("> ");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("obraz")) {
                this.mainTarget = gameData.findItem("velkyObraz");
                System.out.println("Peter: 'Výborně obraz Pollard Willow to tedy  je'");
                break;
            } else if (choice.equals("zlato")) {
                this.mainTarget = gameData.findItem("zlato");
                System.out.println("Peter 'Zlaté cihly nejsou lehké, ale věřim že to zvládneš'");
                break;
            } else {
                System.out.println("Zkus to znovu");
            }
        }

        System.out.println("\nPeter: 'Tady máš pár věcí co se budou hodit. Nůž, vysílačku a hackovací stroj. Jdeme na to.'\n" +
                "--------- PŘÍJEZD K VILE ---------\n" +
                "začni příkazem 'prikazy'.\n");
    }

    public void createWorld() {
        player.getInventory().setSize(8);

        int noise = switch (this.difficulty) {
            case 1 -> 130;
            case 2 -> 100;
            case 3 -> 70;
            default -> 0;
        };

        getNoiseMeter().setMaxNoise(noise);

        for (Room r : this.gameData.rooms) {
            this.allRooms.put(r.getId(), r);
        }

        if  (infiltration.equals("letecky")) {
            this.player.setCurrentRoom(allRooms.get("balkon"));
        } else if (infiltration.equals("autem")) {
            this.player.setCurrentRoom(allRooms.get("zahrada"));
        } else {
            System.out.println("Error!!!");
        }

        if (infiltration.equals("letecky")||escape.equals("letecky")) {
            player.getInventory().setSize(player.getInventory().getSize() - 1);
            System.out.println(player.getInventory().getSize());
        }

        for (Item item : this.gameData.items) {
            String itemLocation = item.getCurrentLocation();

            if (itemLocation.equalsIgnoreCase("inventory")) {
                player.getInventory().addItem(item.getId());
            }
        }

    }

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface();

        while (this.running) {
            System.out.print("> ");
            String input = scanner.nextLine();

            ui.CommandInput(input);

            if (noiseMeter.tooMuchNoise()) {
                fail();
            }

            if (escape.equals("letecky") && isHacked() && !getGameData().findEnemy("strazcePredsin").getCurrentLocation().equals("none")) {
                getGameData().findEnemy("strazcePredsin").setCurrentLocation("obyvaciPokoj");
            }
        }
    }

    public void stopGameLoop() {
        this.running = false;
    }

    public void fail() {
        System.out.println("Byl spuštěn poplach neni šance utéct.");
        System.out.println("Pan Hubert je vzbuzen. Jsi chycen.");
        System.out.println("Tvá cesta zde končí.");
        System.exit(0);
    }

    public void endGame() {
        System.out.println("\n--- EPILOG ---");
        Inventory batoh = getPlayer().getInventory();
        int expensiveLayers = 350000;
        int stolenGoods = 0;

        for (Item i : batoh.getItems()) {
            if (i.isLoot()) {
                stolenGoods += i.getValue();
            }
        }
        int tax = 20 + getAciveHacker().getPrice();
        int yourCut = stolenGoods - (stolenGoods * (tax/100));

        System.out.println("Zvládl si ukradnout: "+ stolenGoods + " kč");
        System.out.println("Peter cut: " + (stolenGoods - (stolenGoods * 0.2)) + " kč (za zajišťení dopravy a pomoc se setupem)");
        System.out.println(getAciveHacker().getName() + " cut: "+ (stolenGoods - (stolenGoods * (getAciveHacker().getPrice()/100))) + " kč (za hackování a odbornou pomoc)");
        System.out.println("Tvůj cut: " + yourCut + " kč");
        System.out.println("Cena drahých právníků: " + expensiveLayers + " kč");

        if (yourCut >= expensiveLayers) {
            System.out.println("Díky ukradenému lootu jsi najal špičkový právní tým, který rozbil Hubertovy lži.\n" +
                    "Tvůj otec je po 64 letech volný a ty máš konečně důvod žít normální život.\n" +
                    "VÍTĚZSTVÍ: Dosáhl jsi nejlepšího možného konce.");
        } else {
            System.out.println("Nemáš dost peněz na drahé právníky a tak tě čeká složité rozhodnutí.\n" +
                    "Máš v rukou pravdu, ale jediná cesta, jak ji použít, je přiznat se ke vloupání.\n" +
                    "Musíš si vybrat: Buď si necháš složku pro sebe a budeš volný (ale táta zůstane v base),\n" +
                    "nebo důkazy odevzdáš policii, očistíš tátu, ale sám půjdeš sedět za krádež.\n" +
                    "\nCo uděláš?\n" +
                    "1. Odevzdat složku policii (Očistíš tátu, ale půjdeš do vězení za vloupání)\n" +
                    "2. Nechat si složku a zmizet (Zůstaneš na svobodě, ale táta zůstane ve vězení)");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("> ");
                try {
                    int volba = scanner.nextInt();
                    scanner.nextLine();

                    if (volba == 1) {
                        System.out.println("Rozhodl ses pro spravedlnost. Došel jsi na policii a předal jim důkazy o Hubertových podvodech.\n" +
                                "Soud uznal, že tvůj otec byl nevinný a okamžitě ho propustili na svobodu. Viděl jsi ho po letech plakat.\n" +
                                "Cena však byla vysoká – za vloupání do vily jsi dostal několik let. Tvůj otec je volný, ale ty teď sedíš za něj.\n" +
                                "STAV: Sebeobětování. Otec je volný, ty jsi ve vězení.");
                        break;
                    } else if (volba == 2) {
                        System.out.println("Strach z vězení byl silnější. Složku jsi spálil v nejbližším koši a zmizel jsi do anonymity velkoměsta.\n" +
                                "Otec ve vězení dál věří v tvou nevinnu a Hubert si dál užívá svého nakradeného bohatství v klidu své vily.\n" +
                                "Ty jsi sice volný, ale každý večer tě pronásleduje svědomí, že jsi tátu nechal v pekle jen kvůli vlastnímu strachu.\n" +
                                "STAV: Hořká svoboda. Jsi volný, ale otec zůstal v žaláři.");
                        break;
                    } else {
                        System.out.println("Neplatná volba. Máš na výběr pouze 1 nebo 2.");
                    }
                } catch (Exception e) {
                    System.out.println("Neplatná volba. Máš na výběr pouze 1 nebo 2.");
                    scanner.nextLine();
                }
            }

        }

    }


    public Item getMainTarget() {
        return mainTarget;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public Player getPlayer() {
        return player;
    }

    public NoiseMeter getNoiseMeter() {
        return noiseMeter;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Hacker getAciveHacker() {
        return activeHacker;
    }

    public String getEscape() {
        return escape;
    }

    public boolean isHacked() {
        return hacked;
    }

    public Map<String, Room> getAllRooms() {
        return allRooms;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setHacked(boolean hacked) {
        this.hacked = hacked;
    }



}