import Game.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testValueGenerationRange() {

        Item diamant = new Item("Diamant", "diamant", "Drahý kámen", 1, false, true, 10, 20, "trezor", false);

        int hodnota = diamant.getValue();


        assertTrue(hodnota >= 10000 && hodnota <= 20000);
    }

    @Test
    void testItemTypeInitialization() {

        Item nuz = new Item("Nůž", "nuz", "Ostrý nůž", 1, false,false,0, 0, "false", false);

        assertFalse(nuz.isLoot());
        assertFalse(nuz.isMainLoot());
        assertEquals(1, nuz.getSize());
    }

    @Test
    void testManualniVytvoreniZlata() {
        // Ručně vytvoříme zlato se všemi parametry
        Item zlato = new Item("Zlato", "zlato", "Popis", 3, true, true, 100, 200, "false", false);

        System.out.println("Vygenerovaná hodnota: " + zlato.getValue());

        assertTrue(zlato.isLoot());
        assertTrue(zlato.getValue() >= 100000);
    }
}