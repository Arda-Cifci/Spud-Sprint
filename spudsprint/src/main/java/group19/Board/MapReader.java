package group19.Board;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *  This class reads txt files which contain the game's levels and returns
 *  them in a BoardTiles array
 */
public class MapReader {
    /**
     * Reads a map file based on the mapType given, and creates and populates a BoardTiles array
     */
    public static BoardTile[][] getMap(int mapType) {
        String fileName = switch (mapType) {
            case 1 -> "src/main/resources/E1.txt";
            case 2 -> "src/main/resources/E2.txt";
            case 3 -> "src/main/resources/E3.txt";
            case 4 -> "src/main/resources/M1.txt";
            case 5 -> "src/main/resources/M2.txt";
            case 6 -> "src/main/resources/M3.txt";
            case 7 -> "src/main/resources/H1.txt";
            case 8 -> "src/main/resources/H2.txt";
            case 9 -> "src/main/resources/H3.txt";
            default -> "";
        };
        BoardTile[][] board = new BoardTile[24][32];
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr, 32);
            String line;
            boolean x;
            //
            for (int i = 0; i < 24; i++) {
                line = br.readLine();
                for (int j = 0; j < 32; j++) {
                    x = Character.getNumericValue(line.charAt(j)) == 0 || Character.getNumericValue(line.charAt(j)) == Character.getNumericValue('b')
                            || Character.getNumericValue(line.charAt(j)) == Character.getNumericValue('c');
                    board[i][j] = new BoardTile(j, i, x, Character.getNumericValue(line.charAt(j)));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return board;
    }
}