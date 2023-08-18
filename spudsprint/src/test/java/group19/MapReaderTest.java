package group19;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import group19.Board.BoardTile;
import group19.Board.MapReader;

public class MapReaderTest {
    
    @Test
    public void testMapReader() {
        BoardTile[][] testBoard = MapReader.getMap(1);
        for (int i=0; i<24; i++) {
            for (int j=0; j<32; j++) {
                assertTrue(testBoard[i][j].getTileType() <= 13);
                assertTrue(testBoard[i][j].getTileType() >= 0);
                assertTrue(testBoard[i][j].getXPosition() == j);
                assertTrue(testBoard[i][j].getYPosition() == i);
            }
        }
    }
}