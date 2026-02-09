package Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainGame {
    //
    private static MainGame instance;
    // hrac
    private Player player = new Player();
    // noise meter
    private NoiseMeter noiseMeter = new NoiseMeter();
    // game data
    private GameData gameData = GameData.loadFromResources("/gamedata.json");;
    // ui
    private UserInterface userInterface = new UserInterface();


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
    private Map<String, Room> allRooms = new HashMap<>();
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

//    player.getInventory().setSize(8);
//
//        this.difficulty = 1;
//        this.activeHacker = this.gameData.findHacker("hacker1");
//        this.infiltration = "letecky";
//        this.escape = "letecky";

    public void setUp() {
        player.getInventory().setSize(8);
        Scanner scanner = new Scanner(System.in);


        System.out.println("1 = nejlehčí, 2 = střední, 3 = těžká");
        System.out.println("Zvolte obtížnost (1-3): ");
        this.difficulty = scanner.nextInt();

        scanner.nextLine();

        System.out.println("--- PLÁNOVACÍ MÍSTNOST ---");
        System.out.println("Sedíš v místnosti se svým starým parťákem Peterem. Ten se v minulém heistu zranil,");
        System.out.println("takže ti tentokrát bude krýt záda jen na dálku a pomůže s přípravou.");
        System.out.println("Peter: 'Robine, tohle je velká ryba. Jeden z nejbohatších lidí v kraji. Máme šanci na balík.'");
        System.out.println("Robin: 'Hlavně když to vyjde. Potřebuju peníze, ne vězení jako táta.'\n");

        System.out.println("\nPeter: 'Jak se dostaneš dovnitř?'");
        System.out.println("Autem (vysadím tě u zahrady)");
        System.out.println("Letecky (výsadek padákem na balkon)");

        String volbaVstup = scanner.nextLine().toLowerCase();
        if (volbaVstup == "letecky") {
            this.infiltration = "letecky";
            System.out.println("Peter: 'Padákem na balkon... Odvážné, ale tiché.'");
        } else {
            this.infiltration = "autem";
            System.out.println("Peter: 'Auto je jistota. Půjdeš buď oknem, nebo hlavními dveřmi.'");
        }

        System.out.println("\nPeter: 'A jak se odtud vypaříš?'");
        System.out.println("Autem (vyzvednu tě u zahrady)");
        System.out.println("Helikoptérou (přiletí pro tebe na balkon)");

        String volbaUtek = scanner.nextLine().toLowerCase();
        if (volbaUtek == "letecky") {
            this.escape = "letecky";
            System.out.println("Peter: 'Helikoptéra bude připravená na balkoně.'");
        } else {
            this.escape = "autem";
            System.out.println("Peter: 'Budu čekat v autě u zahrady. Nezapomeň na psy!'");
        }

        // 5. Výběr hackera
        System.out.println("\nPeter: 'Kterého hackera mám najmout?'");
        System.out.println("Gabriel (bere 20%, ale tichý přístroj)");
        System.out.println("Erik (bere 10%, ale hlučný přístroj)");

        String hackerVolba = scanner.nextLine().toLowerCase();
        if (hackerVolba == "gabriel") {
            this.activeHacker = this.gameData.findHacker("hacker1"); // Gabriel
            System.out.println("Peter: 'Gabriel je profík. S ním tě nikdo neuslyší.'");
        } else {
            this.activeHacker = this.gameData.findHacker("hacker2"); // Erik
            System.out.println("Peter: 'Erik je levnej, ale jeho vybavení dělá pěknej randál.'");
        }

        System.out.println("\nPeter: 'A co je náš hlavní cíl?'");
        System.out.println("zlato: Zlaté cihly v trezoru");
        System.out.println("obraz: Velký obraz v galerii");

        String lootVolba = scanner.nextLine();
        if (lootVolba == "obraz") {
            this.mainTarget = gameData.findItem("velkyObraz");
        } else {
            this.mainTarget = gameData.findItem("zlato");
        }

        System.out.println("\nPeter: 'Všechno máš. Nůž, vysílačku i hackovací mašinu. Jdeme na to.'");
        System.out.println("--- PŘÍJEZD K VILE ---");
    }

    public void createWorld() {
        int noise = 0;

        switch (this.difficulty) {
            case 1: noise = 130; break;
            case 2: noise = 100; break;
            case 3: noise = 70; break;
        }

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

        for (Item item : this.gameData.items) {
            String itemLocation = item.getCurrentLocation();

            if (itemLocation.equalsIgnoreCase("inventory")) {
                player.getInventory().addItem(item.getId());
            }
        }

        if (infiltration.equals("letecky")||escape.equals("letecky")) {
            player.getInventory().setSize(player.getInventory().getSize() - 1); //
        }

        System.out.println(player.getInventory().showItems()); // TEST



        System.out.println("Načteno místností: " + this.gameData.rooms.size());
        System.out.println("Načteno předmětů: " + this.gameData.items.size());
        System.out.println("Načteno hackerů: " + this.gameData.hackers.size());
    }

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface();

        while (this.running) {
            System.out.print("\n> ");
            String input = scanner.nextLine();

            ui.CommandInput(input);

            if (noiseMeter.tooMuchNoise()) {
                System.out.println("Pan Hubert se probudil! Jsi chycen.");
                System.out.println("Tvá cesta zde končí.");
                System.exit(0);
            }
        }
    }

    public void stopGameLoop() {
        this.running = false;
    }

    public void endGame() {
        System.out.println("\n--- EPILOG ---");
        Inventory batoh = MainGame.getInstance().getPlayer().getInventory();
        int expensiveLayers = 700;
        int stolenGoods = 0;

        for (Item i : batoh.getItems()) {
            stolenGoods += i.getValue();
        }

        if (stolenGoods >= expensiveLayers) {
            System.out.println("Robin: 'Mám to! Tohle bohatství stačí na ty nejlepší právníky v zemi.'");
            System.out.println("Díky ukradenému lootu jsi najal špičkový právní tým, který rozbil Hubertovy lži.");
            System.out.println("Tvůj otec je po 64 letech volný a ty máš konečně důvod žít normální život.");
            System.out.println("VÍTĚZSTVÍ: Dosáhl jsi nejlepšího možného konce.");
        } else {
            System.out.println("Robin: 'Sakra, nemám peníze na právníky... zbývá jen ta složka.'");
            System.out.println("Máš v rukou pravdu, ale jediná cesta, jak ji použít, je přiznat se ke vloupání.");
            System.out.println("Musíš si vybrat: Buď si necháš složku pro sebe a budeš volný (ale táta zůstane v base),");
            System.out.println("nebo důkazy odevzdáš policii, očistíš tátu, ale sám půjdeš sedět za krádež.");
            System.out.println("\nCo uděláš?");
            System.out.println("1. Odevzdat složku policii (Očistíš tátu, ale půjdeš do vězení za vloupání)");
            System.out.println("2. Nechat si složku a zmizet (Zůstaneš na svobodě, ale táta shnije ve vězení)");
            Scanner scanner = new Scanner(System.in);
            int volba = scanner.nextInt();
            if (volba == 1) {
                System.out.println("Rozhodl ses pro spravedlnost. Došel jsi na policii a předal jim důkazy o Hubertových podvodech.");
                System.out.println("Soud uznal, že tvůj otec byl nevinný a okamžitě ho propustili na svobodu. Viděl jsi ho po letech plakat.");
                System.out.println("Cena však byla vysoká – za vloupání do vily jsi dostal několik let. Tvůj otec je volný, ale ty teď sedíš za něj.");
                System.out.println("STAV: Sebeobětování. Otec je volný, ty jsi ve vězení.");
            } else {
                System.out.println("Strach z vězení byl silnější. Složku jsi spálil v nejbližším koši a zmizel jsi do anonymity velkoměsta.");
                System.out.println("Otec ve vězení dál věří v tvou nevinnu a Hubert si dál užívá svého nakradeného bohatství v klidu své vily.");
                System.out.println("Ty jsi sice volný, ale každý večer tě pronásleduje svědomí, že jsi tátu nechal v pekle jen kvůli vlastnímu strachu.");
                System.out.println("STAV: Hořká svoboda. Jsi volný, ale otec zůstal v žaláři.");
            }
        }

    }


    public Room findRoom(String id) {
        return allRooms.get(id);
    }


    public MainGame() {
        instance = this;
    }

    public static MainGame getInstance() {
        return instance;
    }


    public String getInfiltration() {
        return infiltration;
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

    public void setDifficulty(int difficulty) {}


}