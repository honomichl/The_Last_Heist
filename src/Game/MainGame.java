package Game;

import java.util.*;


/**
 * Hlavní třída hry, která řídí herní logiku, inicializaci světa,
 * nastavení obtížnosti a hlavní herní smyčku.
 *
 * @author Filip Honomichl
 */
public class MainGame {
    /** instance hry (Singleton vzor) */
    private static MainGame instance;
    /** Instance hráče */
    private final Player player = new Player();
    /** Ukazatel hluku */
    private final NoiseMeter noiseMeter = new NoiseMeter();
    /** Načtená herní data z jsonu */
    private final GameData gameData = GameData.loadFromResources("/gamedata.json");
    /** Rozhraní pro zpracování příkazů */
    private final UserInterface userInterface = new UserInterface();

    /** Obtížnost hry (1 = lehká, 2 = střední, 3 = těžká) */
    private int difficulty;
    /** Hacker vybraný v setapu */
    private Hacker activeHacker;
    /** Způsob infiltrace do budovy */
    private String infiltration;
    /** Způsob úniku z budovy */
    private String escape;
    /** Je hacknutý laptop? */
    private boolean hacked = false;
    /** Mapa id všech místností */
    private final Map<String, Room> allRooms = new HashMap<>();
    /** Běží hlavní smyčka? */
    private boolean running = true;
    /** Hlavní loot vybraný v setapu */
    private Item mainTarget;


    /**
     * Spustí celou hru v definovaném pořadí: nastavení, vytvoření světa,
     * hlavní smyčka a epilog.
     */
    public void StartGame() {
        setUp();
        createWorld();
        gameLoop();
        endGame();
    }

    /**
     * Konstruktor třídy MainGame. Nastavuje statickou instanci pro přístup z jiných tříd.
     */
    public MainGame() {
        instance = this;
    }

    /**
     * Vrátí aktuální běžící instanci hry.
     */
    public static MainGame getInstance() {
        return instance;
    }

    /**
     * Provede úvodní nastavení hry pomocí uživatelského vstupu.
     * Zahrnuje volbu obtížnosti, způsobu infiltrace, úniku, výběr hackera a hlavního lootu.
     */
    public void setUp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 = nejlehčí, 2 = střední, 3 = těžká");
        System.out.println("Zvolte obtížnost (1-3): ");

        while (true) {
            System.out.print("> ");
            try {
                this.difficulty = scanner.nextInt();
                if (difficulty == 1 || difficulty == 2 || difficulty == 3) {
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

        System.out.println("""
                --- PLÁNOVACÍ MÍSTNOST ---
                Sedíš v místnosti se svým starým parťákem Peterem. Ten se v minulém heistu zranil,
                takže ti tentokrát bude krýt záda jen na dálku a pomůže s přípravou.
                Peter: 'Robine, tohle je velká ryba. Jeden z nejbohatších lidí v kraji. Máme šanci na balík.'
                Robin: 'Hlavně když to vyjde. Potřebuju peníze, ne vězení jako táta.'
                """
        );


        System.out.println("""
                
                Peter: 'Jak se dostaneš dovnitř?'
                Autem (vysadím tě u zahrady)
                Letecky (výsadek padákem na balkon)""");
        while (true) {
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

        System.out.println("""
                
                Peter: 'A jak se odtud vypaříš?'
                Autem (vyzvednu tě u zahrady)
                Letecky (odlet helikoptérou z balkonu)""");
        while (true) {
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

        System.out.println("""
                
                Peter: 'Kterého hackera mám najmout?'
                Gabriel (bere 20%, ale tichý přístroj)
                Erik (bere 10%, ale hlučný přístroj)""");
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

        System.out.println("""
                
                Peter: 'A co je náš hlavní cíl?'
                zlato: Zlaté cihly v trezoru\
                obraz: Velký obraz v galerii""");
        while (true) {
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

        System.out.println("""
                
                Peter: 'Tady máš pár věcí co se budou hodit. Nůž, vysílačku a hackovací stroj. Jdeme na to.'
                --------- PŘÍJEZD K VILE ---------
                začni příkazem 'prikazy'.
                """);
    }

    /**
     * Vytvoří herní svět podle voleb v setapu.
     * Nastavuje limit hluku, rozmísťuje místnosti, startovní pozici hráče
     * a přidá předměty do inventáře.
     */
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

        if (infiltration.equals("letecky")) {
            this.player.setCurrentRoom(allRooms.get("balkon"));
        } else if (infiltration.equals("autem")) {
            this.player.setCurrentRoom(allRooms.get("zahrada"));
        } else {
            System.out.println("Error!!!");
        }

        if (infiltration.equals("letecky") || escape.equals("letecky")) {
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

    /**
     * Hlavní herní smyčka.
     * Čte vstupy od uživatele, zpracovává příkazy,
     * kontroluje stav hluku a přesune strážce ke konci hry.
     */
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

    /**
     * Zastaví hlavní herní smyčku, čímž umožní přechod k epilogu.
     */
    public void stopGameLoop() {
        this.running = false;
    }

    /**
     * Metoda volaná při prohře (např. překročení limitu hluku).
     * Oznámí hráčovy že prohrál a ukončí program.
     */
    public void fail() {
        System.out.println("Byl spuštěn poplach není šance utéct.");
        System.out.println("Pan Hubert je vzbuzen a ty jsi chycen.");
        System.out.println("Tvá cesta zde končí.");
        System.exit(0);
    }

    /**
     * Zpracuje konec hry, vypočítá kolik bylo ukradeno, odečte podíly pro komplice a zobrazí závěrečný epilog
     * na základě získaných peněz a morálních rozhodnutí hráče.
     */
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
        int yourCut = stolenGoods - (stolenGoods * (tax / 100));

        System.out.println("Zvládl si ukradnout: " + stolenGoods + " kč");
        System.out.println("Peter cut: " + (stolenGoods - (stolenGoods * 0.2)) + " kč (za zajišťení dopravy a pomoc se setupem)");
        System.out.println(getAciveHacker().getName() + " cut: " + (stolenGoods - (stolenGoods * (getAciveHacker().getPrice() / 100))) + " kč (za hackování a odbornou pomoc)");
        System.out.println("Tvůj cut: " + yourCut + " kč");
        System.out.println("Cena drahých právníků: " + expensiveLayers + " kč");

        if (yourCut >= expensiveLayers) {
            System.out.println("""
                    Díky ukradenému lootu jsi najal špičkový právní tým, který rozbil Hubertovy lži.
                    Tvůj otec je po 64 letech volný a ty máš konečně důvod žít normální život.
                    VÍTĚZSTVÍ: Dosáhl jsi nejlepšího možného konce.""");
        } else {
            System.out.println("""
                    Nemáš dost peněz na drahé právníky a tak tě čeká složité rozhodnutí.
                    Máš v rukou pravdu, ale jediná cesta, jak ji použít, je přiznat se ke vloupání.
                    Musíš si vybrat: Buď si necháš složku pro sebe a budeš volný (ale táta zůstane v base),
                    nebo důkazy odevzdáš policii, očistíš tátu, ale sám půjdeš sedět za krádež.
                    
                    Co uděláš?
                    1. Odevzdat složku policii (Očistíš tátu, ale půjdeš do vězení za vloupání)
                    2. Nechat si složku a zmizet (Zůstaneš na svobodě, ale táta zůstane ve vězení)""");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("> ");
                try {
                    int volba = scanner.nextInt();
                    scanner.nextLine();

                    if (volba == 1) {
                        System.out.println("""
                                Rozhodl ses pro spravedlnost. Došel jsi na policii a předal jim důkazy o Hubertových podvodech.
                                Soud uznal, že tvůj otec byl nevinný a okamžitě ho propustili na svobodu. Viděl jsi ho po letech plakat.
                                Cena však byla vysoká – za vloupání do vily jsi dostal několik let. Tvůj otec je volný, ale ty teď sedíš za něj.
                                STAV: Sebeobětování. Otec je volný, ty jsi ve vězení.""");
                        break;
                    } else if (volba == 2) {
                        System.out.println("""
                                Strach z vězení byl silnější. Složku jsi spálil v nejbližším koši a zmizel jsi do anonymity velkoměsta.
                                Otec ve vězení dál věří v tvou nevinnu a Hubert si dál užívá svého nakradeného bohatství v klidu své vily.
                                Ty jsi sice volný, ale každý večer tě pronásleduje svědomí, že jsi tátu nechal v pekle jen kvůli vlastnímu strachu.
                                STAV: Hořká svoboda. Jsi volný, ale otec zůstal v žaláři.""");
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

    /** gettery */
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

    /** settery */
    public void setHacked(boolean hacked) {
        this.hacked = hacked;
    }
}