import Game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.setSize(8);
    }

    @Test
    void testSetSizeAFreeSlots() {
        assertEquals(8, inventory.getSize());
        assertEquals(8, inventory.getFreeSlots());
    }

    @Test
    void testCheckSpace() {
        Item tezkyPredmet = new Item("Zlaté cihly", "zlato", "sjsivsuib", 3,"predsin" ,false );

        assertTrue(inventory.checkSpace(tezkyPredmet));

        inventory.setSize(2);
        assertFalse(inventory.checkSpace(tezkyPredmet));
    }

    @Test
    void testHasItem() {

        Item nuz = new Item("OstrýNůž", "nuz", "hahaha", 5,"loznice" ,true );
        inventory.getItems().add(nuz);

        assertTrue(inventory.hasItem("nuz"));
        assertFalse(inventory.hasItem("neexistuje"));
    }
}