import Game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoiseMeterTest {

    private NoiseMeter noiseMeter;

    @BeforeEach
    void setUp() {
        noiseMeter = new NoiseMeter();
        noiseMeter.setMaxNoise(100);
    }

    @Test
    void testInitialNoise() {
        // Testujeme, že na začátku je hluk 0
        assertEquals(0, noiseMeter.getNoiseLevel());
    }

    @Test
    void testTooMuchNoise() {
        noiseMeter.setMaxNoise(100);

        noiseMeter.decreaseNoise(-110);

        assertTrue(noiseMeter.tooMuchNoise());
    }

    @Test
    void testCheckNoiseCalculation() {

        noiseMeter.setMaxNoise(200);

        noiseMeter.decreaseNoise(-100);

        String result = noiseMeter.checkNoise();
        assertTrue(result.contains("50%"));
    }
}