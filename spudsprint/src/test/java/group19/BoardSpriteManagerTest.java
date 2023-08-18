package group19;

import group19.Board.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;

public class BoardSpriteManagerTest {
    private BoardSpriteManager sprites;

    @Before
    public void setupSpriteManager() throws IOException {
        sprites = new BoardSpriteManager();
    }

    @Test
    public void testGetBufferedPotatoFriend() throws IOException {
        assertNotNull(sprites.getBufferedPotatoFriend());
    }

    @Test
    public void testGetBufferedOpen() throws IOException {
        assertNotNull(sprites.getBufferedOpen());
    }

    @Test
    public void testGetBufferedTopBottomWall() throws IOException {
        assertNotNull(sprites.getBufferedTopBottomWall());
    }

    @Test
    public void testGetBufferedSideWall() throws IOException {
        assertNotNull(sprites.getBufferedSideWall());
    }
    
    @Test
    public void testGetBufferedChairFront() throws IOException {
        assertNotNull(sprites.getBufferedChairFront());
    }

    @Test
    public void testGetBufferedChairBack() throws IOException {
        assertNotNull(sprites.getBufferedChairBack());
    }

    @Test
    public void testGetBufferedChairLeft() throws IOException {
        assertNotNull(sprites.getBufferedChairLeft());
    }

    @Test
    public void testGetBufferedChairRight() throws IOException {
        assertNotNull(sprites.getBufferedChairRight());
    }
    @Test
    public void testGetBufferedVerticalBottomTable() throws IOException {
        assertNotNull(sprites.getBufferedVerticalBottomTable());
    }

    @Test
    public void testGetBufferedHorizontalRightTable() throws IOException {
        assertNotNull(sprites.getBufferedHorizontalRightTable());
    }

    @Test
    public void testGetBufferedHorizontalLeftTable() throws IOException {
        assertNotNull(sprites.getBufferedHorizontalLeftTable());
    }

    @Test
    public void  testGetBufferedVerticalTopTable() throws IOException {
        assertNotNull(sprites. getBufferedVerticalTopTable());
    }

    @Test
    public void testGetBufferedOpenDoor() throws IOException {
        assertNotNull(sprites.getBufferedOpenDoor());
    }

    @Test
    public void testGetBufferedClosedDoor() throws IOException {
        assertNotNull(sprites.getBufferedClosedDoor());
    }

    @Test
    public void testGetBufferedDeepFryer() throws IOException {
        assertNotNull(sprites.getBufferedDeepFryer());
    }

    @Test
    public void testGetBufferedKey() throws IOException {
        assertNotNull(sprites.getBufferedKey());
    }
}
    
