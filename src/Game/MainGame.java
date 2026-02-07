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
    //obtiznost hry (3 = nejtezsi; 1 nejlehci)
    private int difficulty; // pri nastavovani inventare, pri fighteni, pri nastavovani noise meteru
    // uklada vzbraneho hackera
    private String hacker;
    // jak se do budovy dostane
    private String infiltration;
    // jak se z budovy dostane
    private String escape;
    // hacknul uz laptop v pracovne a zjisti pravdu?
    private boolean hacked = false;
    // uklada vsechny mistnosti
    private Map<String, Room> allRooms = new HashMap<>();


    public void StartGame() {
        setUp();
        createWorld();
        gameLoop();
    }

    public void setUp() {
        this.difficulty = 1;
        this.hacker = "hacker1";
        this.infiltration = "letecky";
        this.escape = "letecky";
        player.getInventory().setSize(10);
    }

    public void createWorld() {
        GameData data = GameData.loadFromResources("/gamedata.json");

        for (Room r : data.rooms) {
            this.allRooms.put(r.getId(), r);
        }

        if  (infiltration.equals("letecky")) {
            this.player.setCurrentRoom(allRooms.get("balkon"));
        } else if (infiltration.equals("autem")) {
            this.player.setCurrentRoom(allRooms.get("zahrada"));
        } else {
            System.out.println("Error!!!");
        }

        for (Item item : data.items) {
            String itemLocation = item.getCurrentLocation();

            if (itemLocation.equalsIgnoreCase("inventory")) {
                player.getInventory().addItem(item);
            }
        }

        if (infiltration.equals("letecky")||escape.equals("letecky")) {
            player.getInventory().setSize(player.getInventory().getSize() - 1); //
        }

        player.getInventory().showItems(); // TEST
    }

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface();

        boolean running = true;
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();

            ui.CommandInput(input);

            if (noiseMeter.tooMuchNoise()) {
                System.out.println("Pan Hubert se probudil! Jsi chycen.");
                running = false; //TODO dialog pro chyceni
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



    public Player getPlayer() {
        return player;
    }

    public NoiseMeter getNoiseMeter() {
        return noiseMeter;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getHacker() {
        return hacker;
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


    public void setHacked(boolean hacked) {
        this.hacked = hacked;
    }
}