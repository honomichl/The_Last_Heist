package Command;

import Game.*;

import java.util.List;


public class CommandJdi extends Command {

    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "Kam chceš jít? Pokud nevíš kam můžeš použij příkaz: Vychody";
        }

        String roomName = parameters[0];

        Player player = MainGame.getInstance().getPlayer();
        Room currentRoom = player.getCurrentRoom();

        List<String> validExits = currentRoom.getExits();
        boolean valid = false;

        for (String exitName : validExits) {
            if (exitName.equals(roomName)) {
                valid = true;
                break;
            }
        }

        if (!valid) {
            return "Nelze jít do '" + roomName + "' odtud. Pokud nevíš kam můžeš použij příkaz: Vychody";
        }

        //TODO trezor
        //TODO galerie
        //TODO vstup kuchyn


        Room nextRoom = MainGame.getInstance().getAllRooms().get(roomName.toLowerCase());

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            return "Přesunul si se do: " + nextRoom.getName() + "\n" + nextRoom.getDescription();
        }

        return "Error";
    }
}