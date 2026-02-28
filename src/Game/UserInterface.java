package Game;

import Command.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Třída UserInterface představuje uživatelské rozhraní hry.
 * Má na starosti registraci dostupných příkazů a zpracování vstupů od hráče.
 *
 * @author Filip Honomichl
 */

public class UserInterface{
    /** Mapa uchovávající příkazy */
    private Map<String, Command> CommandMap = new HashMap<>();

    /**
     * Vrátí kolekci všech aktuálně registrovaných příkazů.
     * @return kolekce commandů
    */
    public Collection<Command> getCommands() {
        return this.CommandMap.values();
    }

    /**
     * Provádí inicializaci a registraci všech dostupných příkazů do command mapy.
     */
    public UserInterface() {
        CommandMap.put("hackni".toLowerCase(), new CommandHackni());
        CommandMap.put("hlasitost".toLowerCase(), new CommandHlasitost());
        CommandMap.put("inventar".toLowerCase(), new CommandInventar());
        CommandMap.put("jdi".toLowerCase(), new CommandJdi());
        CommandMap.put("konec".toLowerCase(), new CommandKonec());
        CommandMap.put("napoveda".toLowerCase(), new CommandNapoveda());
        CommandMap.put("prikazy".toLowerCase(), new CommandPrikazy());
        CommandMap.put("prohledej".toLowerCase(), new CommandProhledej());
        CommandMap.put("seber".toLowerCase(), new CommandSeber());
        CommandMap.put("utec".toLowerCase(), new CommandUtec());
        CommandMap.put("vychody".toLowerCase(), new CommandVychody());
        CommandMap.put("vyhod".toLowerCase(), new CommandVyhod());
    }

    /**
     * Zpracuje řetězec zadaný uživatelem. Rozdělí jej na jméno příkazu a parametry,
     * a pokud příkaz existuje, provede jej.
     * @param line kompletní řádek textu zadaný uživatelem
     */
    public void CommandInput(String line) {
        String[] word = line.split(" ");
        String commandName = word[0];

        if (CommandMap.containsKey(commandName)) {
            Command command1 = CommandMap.get(commandName);

            String[] parameters = new String[word.length - 1];

            for (int i = 1; i < word.length; i++) {
                parameters[i - 1] = word[i];
            }

            String vysledek = command1.execute(parameters);
            System.out.println(vysledek + "\n");

        } else {
            System.out.println("Tenhle příkaz neznám. Pokud nevíš jaké jsou příkazy zkus příkaz 'prikazy'.");
        }
    }



}