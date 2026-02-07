package Game;

public class NoiseMeter {
    private int noiseLevel = 0;
    private int maxNoise;


    public void increaseNoise(int amount) {
        noiseLevel += amount;
    }
    public void decreaseNoise(int amount) {
        noiseLevel -= amount;
    }

    public String checkNoise() {
        return "Pan Hubert je zbuzen na: "+ noiseLevel/(maxNoise/100) +"%.";
    }

    public boolean tooMuchNoise() {
        if (noiseLevel > maxNoise) {
            return true;
        } else {
            return false;
        }
    }


    public void setMaxNoise(int maxNoise) {
        this.maxNoise = maxNoise;
    }
}