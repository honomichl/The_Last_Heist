package Game;

/**
 * Třída NoiseMeter slouží ke sledování a správě hladiny hluku ve hře.
 * Pokud je hráč v ložnici hluk se zdvojnásobuje.
 *
 * @author Filip Honomichl
 */

public class NoiseMeter {
    /** Aktuální hladina hluku. */
    private int noiseLevel = 0;
    /** Maximální přípustná hodnota hluku, než dojde k prohře. */
    private int maxNoise;

    /**
     * Zvýší aktuální hladinu hluku o zadanou hodnotu.
     * Pokud se hráč nachází v místnosti s ID "loznice", hluk se zdvojnásobí.
     *
     * @param amount hodnota o kterou se má hluk zvýšit.
     */
    public void increaseNoise(int amount) {
        if (MainGame.getInstance().getPlayer().getCurrentRoom().getId().equals("loznice")) {
            noiseLevel += amount*2;
        } else {
            noiseLevel += amount;
        }
    }

    /**
     * Vrátí textovou informaci o aktuálním stavu probuzení pana Huberta v procentech.
     *
     * @return Přehledný texy s procentuálním vyjádřením hluku.
     */
    public String checkNoise() {
        if (noiseLevel == 0) {
            return "Pan Hubert je zbuzen na 0%.";
        }
        return "Pan Hubert je zbuzen na "+ noiseLevel/(maxNoise/100) +"%.";
    }

    /**
     * Kontroluje, zda hladina hluku překročila maximální povolenou mez.
     *
     * @return true pokud je noiseLevel vyšší než maxNoise, jinakfalse.
     */
    public boolean tooMuchNoise() {
        if (noiseLevel > maxNoise) {
            return true;
        }
        return false;
    }

    /**
     * Sníží aktuální hladinu hluku o zadanou hodnotu.
     *
     * @param amount Hodnota o kterou se má hluk snížit.
     */
    public void decreaseNoise(int amount) {
        noiseLevel -= amount;
    }

    /** gettery */
    public int getNoiseLevel() {
        return noiseLevel;
    }
    public int getMaxNoise() {
        return maxNoise;
    }

    /** settery */
    public void setMaxNoise(int maxNoise) {
        this.maxNoise = maxNoise;
    }



}