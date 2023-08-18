package group19;

import group19.Board.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class BoardTileTest {
    private BoardTile bt;
    private BoardTile bt2;
    
    @Before
    public void setupGameState() {
        bt = new BoardTile(0,0, true, 0);
        bt2 = new BoardTile(32,32, false, 2);
    }

    @Test
    public void testGetXPosition() {
         int expected = 32;
         assertEquals(expected, bt2.getXPosition());
    }

    @Test
    public void testGetYPosition() {
         int expected = 32;
         assertEquals(expected, bt2.getYPosition());
    }

    @Test
    public void testIsOpenTrue() {
         boolean expected = true;
         assertEquals(expected, bt.isOpen());
    }

    @Test
    public void testIsOpenFalse() {
         boolean expected = false;
         assertEquals(expected, bt2.isOpen());
    }

    @Test
    public void testGetTileType0() {
         int expected = 0;
         assertEquals(expected, bt.getTileType());
    }
 
    @Test
    public void testGetTileType2() {
         int expected = 2;
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType1() {
         int expected = 1;
         bt2 = new BoardTile(32,32, false, 1);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType3() {
         int expected = 3;
         bt2 = new BoardTile(32,32, false, 3);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType4() {
         int expected = 4;
         bt2 = new BoardTile(32,32, false, 4);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType5() {
         int expected = 5;
         bt2 = new BoardTile(32,32, false, 5);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType6() {
         int expected = 6;
         bt2 = new BoardTile(32,32, false, 6);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType7() {
         int expected = 7;
         bt2 = new BoardTile(32,32, false, 7);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType8() {
         int expected = 8;
         bt2 = new BoardTile(32,32, false, 8);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileType9() {
         int expected = 9;
         bt2 = new BoardTile(32,32, false, 9);
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileTypeA() {
         int expected = Character.getNumericValue('a');
         bt2 = new BoardTile(32,32, false, Character.getNumericValue('a'));
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileTypeB() {
         int expected = Character.getNumericValue('b');
         bt2 = new BoardTile(32,32, false, Character.getNumericValue('b'));
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileTypeC() {
         int expected = Character.getNumericValue('c');
         bt2 = new BoardTile(32,32, false, Character.getNumericValue('c'));
         assertEquals(expected, bt2.getTileType());
    }

    @Test
    public void testGetTileTypeD() {
         int expected = Character.getNumericValue('d');
         bt2 = new BoardTile(32,32, false, Character.getNumericValue('d'));
         assertEquals(expected, bt2.getTileType());
    }
}
