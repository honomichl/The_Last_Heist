package Game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GameData {

    public ArrayList<Room> rooms;

    public ArrayList<Item> items;

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

    public Item findItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Room findRoom(String name) {
        for (Room r : rooms) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }
}
