package Command;

/**
 * Abstraktní třída pro všechny příkazy.
 * Definuje základní atributy a metody, které musí každý
 * příkaz využít.
 *
 * @author Filip Honomichl
 */

public abstract class Command {
    /** název příkazu */
    protected String name;
    /** Krátký popis funkčnosti příkazu pro 'prikazy'. */
    protected String description;

    /**
     * Vykoná logiku příkazu.
     * Tato metoda musí být implementována v konkrétních podtřídách.
     * @return Výsledek provedení příkazu ve formě textu.
     */
    public abstract String execute(String[] args);

    /**
     * Ověřuje zda lze příkaz momentálně využít.
     * Výchozí implementace vždy vrací true.
     * @return true, pokud je příkaz vykonatelný, jinak false.
     */
    public boolean isValid() {
        return true;
    }

    /** gettery */
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }


}