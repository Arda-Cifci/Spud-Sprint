package group19;
import group19.Entities.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class deepFryerTest {
    private DeepFryer deepfryer;
    @Before
    public void setupDeepFryer() {
        deepfryer = new DeepFryer(10, 10);
    }

    @Test
    public void testTriggerFryer() {
        boolean expected = true;
        deepfryer.triggerFryer();
        assertEquals(expected, deepfryer.getHasBeenTriggered());
    }
}

