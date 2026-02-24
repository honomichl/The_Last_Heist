package Game;

public class NoiseMeter {
    // hladina hluku
    private int noiseLevel = 0;
    // maximalni hodnota pro hladinu hluku
    private int maxNoise;


    public void increaseNoise(int amount) {
        if (MainGame.getInstance().getPlayer().getCurrentRoom().getId().equals("loznice")) {
            noiseLevel += amount*2;
        } else {
            noiseLevel += amount;
        }
    }

    public String checkNoise() {
        if (noiseLevel == 0) {
            return "Pan Hubert je zbuzen na 0%.";
        }
        return "Pan Hubert je zbuzen na "+ noiseLevel/(maxNoise/100) +"%.";
    }

    public boolean tooMuchNoise() {
        if (noiseLevel > maxNoise) {
            return true;
        }
        return false;
    }

    public void decreaseNoise(int amount) {
        noiseLevel -= amount;
    }

    public void setMaxNoise(int maxNoise) {
        this.maxNoise = maxNoise;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public int getMaxNoise() {
        return maxNoise;
    }
}