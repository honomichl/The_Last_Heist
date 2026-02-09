package Game;

import Command.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UserInterface{
    private Map<String, Command> CommandMap = new HashMap<>();

    public Collection<Command> getCommands() {
        return this.CommandMap.values();
    }

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
            System.out.println(vysledek);

        } else {
            System.out.println("Tenhle příkaz neznám. Pokud nevíš jaké jsou příkazy zkus příkaz 'prikazy'.");
        }
    }



}