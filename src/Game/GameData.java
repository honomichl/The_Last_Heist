package Game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Třída reprezentující kompletní datový model hry.
 * zajišťuje načítání místností, předmětů, nepřátelů a hackerů z externích JSON zdrojů.
 *
 * @author Filip Honomichl
 */

public class GameData {
    /** Seznam všech dostupných místností v herním světě. */
    public ArrayList<Room> rooms;
    /** Seznam všech existujících předmětů. */
    public ArrayList<Item> items;
    /** Seznam všech nepřátel, které lze ve hře potkat. */
    public ArrayList<Enemy> enemies;
    /** Seznam všech hackerů. */
    public ArrayList<Hacker> hackers;


    /**
     * Statická metoda, která vytvoří instanci GameData načtením JSON souboru z resources.
     */
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

    /**
     * Vyhledá předmět podle jeho ID.
     * @param id id předmětu
     * @return objekt Item, nebo null pokud nebyl nalezen
     */
    public Item findItem(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Vyhledá místnost podle id.
     * @param id Identifikátor místnosti.
     * @return Objekt Room, nebo null pokud nebyla nalezena.
     */
    public Room findRoom(String id) {
        for (Room r : this.rooms) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Vyhledá nepřítele podle jeho id.
     * @param id Identifikátor nepřítele.
     * @return Objekt Enemy, nebo null pokud nebyl nalezen.
     */
    public Enemy findEnemy(String id) {
        for (Enemy e : enemies) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Vyhledá hackera podle jeho ID.
     * @param id Identifikátor hackera.
     * @return Objekt Hacker, nebo null pokud nebyl nalezen.
     */
    public Hacker findHacker(String id) {
        for (Hacker h : hackers) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        return null;
    }
}
