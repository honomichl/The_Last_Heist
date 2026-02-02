package Game;

import java.util.HashMap;
import java.util.Map;

public class MainGame {
    private static MainGame instance;
    private Player player;
    private NoiseMeter noiseMeter;
    private boolean hacked = false;
    private Map<String, Room> allRooms = new HashMap<>();


    public MainGame() {
        instance = this;
    }

    public static MainGame getInstance() {
        return instance;
    }


    public Player getPlayer() { return player; }
    public NoiseMeter getNoiseMeter() { return noiseMeter; }
    public Map<String, Room> getAllRooms() { return allRooms; }
    public boolean isHacked() { return hacked; }
    public void setHacked(boolean state) { this.hacked = state; }




}