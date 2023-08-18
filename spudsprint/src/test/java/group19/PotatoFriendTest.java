package group19;
import group19.Entities.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class PotatoFriendTest {
    private PotatoFriend potatoFriend;
    @Before
    public void setupPotatoFriend() {
        int[] openTileCoordinates = {30,60};
        potatoFriend = new PotatoFriend(openTileCoordinates);
    }
    @Test
    public void testPotatoFriendPosition() {
        int xCoordinate = 30;
        int yCoordinate = 60;
        potatoFriend.setPotatoFriendPosition(xCoordinate,yCoordinate);
        assertEquals(xCoordinate, potatoFriend.getX());
        assertEquals(yCoordinate,potatoFriend.getY());
    }
}



