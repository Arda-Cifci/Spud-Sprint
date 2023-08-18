package group19;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import group19.Board.Board;

public class BoardTest {
    private Board board;

    @Before
    public void setupBoard() {
        board = new Board(32,24,9);
    }

    @Test
    public void testgetTileWidth() {
        int expected = 32;
        assertEquals(expected, board.getTileWidth());
    }

    @Test
    public void testgetTileHeight() {
        int expected = 24;
        assertEquals(expected, board.getTileHeight());
    }

    @Test
    public void testgetIfTileOpen() {
        boolean expected = true;
        assertEquals(expected, board.getIfTileOpen(16,3)); // empty space
        assertEquals(expected, board.getIfTileOpen(26, 22)); // key
        assertEquals(expected, board.getIfTileOpen(16, 1)); // deepfryer

        expected = false;
        assertEquals(expected, board.getIfTileOpen(31,20)); // top-facing wall
        assertEquals(expected, board.getIfTileOpen(17, 8)); // chair front
    }

   
    @Test
    public void testgetDoorKeyPositions() {
        int[][] actualkeypositions = {{20,2},{1,4},{16,8},{28,9},{8,12},{25,16},{6,20},{26,22}};
        int[][] boardpositions = new int[8][2];
        ArrayList<int[]> boardkeypositions = board.getDoorKeyPositions();

        assertEquals(8, boardkeypositions.size());

        for(int i = 0; i < 8 ;i++){
            for(int j = 0; j < 2 ; j++){
                int[] temp = boardkeypositions.get(i);
                boardpositions[i][j] = temp[j];

                assertEquals(actualkeypositions[i][j] , boardpositions[i][j]);
            }
        }
    }

    @Test
    public void testgetDeepFryerPositions() {
        int[][] actualdeepfryerpositions = {{16,1},{24,3},{1,5},{28,6},{9,7},{18,8},{9,21},{18,21},{25,21}};
        int[][] boarddeeppositions = new int[9][2];
        ArrayList<int[]> boarddeepfryerpositions = board.getDeepFryerPositions();
        assertEquals(9, boarddeepfryerpositions.size());

        for(int i = 0; i < 9 ;i++){
            for(int j = 0; j < 2 ; j++){
                int[] temp = boarddeepfryerpositions.get(i);
                boarddeeppositions[i][j] = temp[j];

                assertEquals(actualdeepfryerpositions[i][j] , boarddeeppositions[i][j]);
            }
        }
    }

    @Test
    public void testgetTileType() { // test from 0-d
        assertEquals(0,board.getTileType(2,1));
        assertEquals(1, board.getTileType(3, 0));
        assertEquals(2, board.getTileType(0, 0));
        assertEquals(3, board.getTileType(17,8));
        assertEquals(4, board.getTileType(6, 2));
        assertEquals(5, board.getTileType(26, 5));
        assertEquals(6, board.getTileType(23, 5));
        assertEquals(7, board.getTileType(5, 1));
        assertEquals(8, board.getTileType(2, 21));
        assertEquals(9, board.getTileType(15, 2));
        assertEquals(10, board.getTileType(15, 3));
        assertEquals(11, board.getTileType(16, 1));
        assertEquals(12, board.getTileType(1, 4));
        assertEquals(13, board.getTileType(31, 1));
    }

    @Test
    public void testgetOpenTileCoordinates() {
        int[] opentilecoords = board.getOpenTileCoordinates();
        assertEquals(true, board.getIfTileOpen(opentilecoords[0], opentilecoords[1]));
    }
}