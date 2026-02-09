package Game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GameData {

    public ArrayList<Room> rooms;

    public ArrayList<Item> items;

    public ArrayList<Enemy> enemies;

    public ArrayList<Hacker> hackers;

    public static GameData loadFromResources(String resourcePath) {

        Gson gson = new Gson();

        try (InputStream is = GameData.class.getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath);
            }

            return gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    GameData.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání dat: " + e.getMessage());
        }
    }

    public Item findItem(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public Room findRoom(String id) {
        for (Room r : this.rooms) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public Enemy findEnemy(String id) {
        for (Enemy e : enemies) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Hacker findHacker(String id) {
        for (Hacker h : hackers) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        return null;
    }
}
