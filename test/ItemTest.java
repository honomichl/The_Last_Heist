import Game.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testValueGenerationRange() {

        Item diamant = new Item("Diamant", "diamant", "Drahý kámen", 1, false, 10, 20, "trezor", false);

        int hodnota = diamant.getValue();


        assertTrue(hodnota >= 10000 && hodnota <= 20000);
    }

    @Test
    void testItemTypeInitialization() {

        Item nuz = new Item("Nůž", "nuz", "Ostrý nůž", 1, "kuchyn", false);

        assertFalse(nuz.isLoot());
        assertFalse(nuz.isMainLoot());
        assertEquals(1, nuz.getSize());
    }
}