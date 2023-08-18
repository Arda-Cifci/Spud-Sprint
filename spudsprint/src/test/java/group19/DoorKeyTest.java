package group19;

import group19.Entities.DoorKey;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class DoorKeyTest {
    private DoorKey key;

    @Before
    public void setupDoorKey() {
       key = new DoorKey(32, 32);
    }

    @Test
    public void testGetHasBeenCollectedFalse() {
        boolean expected = false;
        assertEquals(expected, key.getHasBeenCollected());
    }

    @Test
    public void testSetToCollected() {
        boolean expected = true;
        key.setToCollected();
        assertEquals(expected, key.getHasBeenCollected());
    }
}
