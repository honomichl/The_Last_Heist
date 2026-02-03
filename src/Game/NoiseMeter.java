package Game;

public class NoiseMeter {
    private int noiseLevel = 0;
    private int maxNoise = 100;

    public void startMaxNoise(int difficulty){
        maxNoise *= difficulty;
    }

    public void increaseNoise(int amount) {
        noiseLevel += amount;
    }
    public void decreaseNoise(int amount) {
        noiseLevel -= amount;
    }

    public String checkNoise() {
        return "Pan Hubert je zbuzen na: "+ noiseLevel/(maxNoise/100) +"%.";
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }
}