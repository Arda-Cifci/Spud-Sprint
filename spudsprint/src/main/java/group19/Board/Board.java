package group19.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a board which is a matrix of board tiles.
 * One can get a random tiles col/row, and get a list
 * either deep fryers or key positions.
 */

public class Board {
    // Variables used
    protected int tileWidth;
    protected int tileHeight;
    protected int mapType;
    protected BoardTile[][] tiles;

    /**
     * Creates a new Board with the given width, height, and map type.
     *
     * @param tileWidth  - width of the tile
     * @param tileHeight - height of the tile
     * @param mapType    - type of the map
     */
    public Board(int tileWidth, int tileHeight, int mapType) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.mapType = mapType;
        this.tiles = MapReader.getMap(this.mapType);
    }

    /**
     * Extracts the X,Y coordinates of the tile.
     *
     * @param tileKeys        - List of boardtiles that are collected up by the stream() api
     * @param coordinatePairs - X,Y coordinates of a tile
     */
    private static void extractXYCoordinates(List<BoardTile> tileKeys, ArrayList<int[]> coordinatePairs) {
        for (BoardTile tile : tileKeys) {
            int[] coordinatePair = new int[2];
            coordinatePair[0] = tile.getXPosition();
            coordinatePair[1] = tile.getYPosition();
            coordinatePairs.add(coordinatePair);
        }
    }

    /**
     * Gets the X,Y coordinates of open tiles.
     *
     * @return this tile's X,Y coordinates.
     */
    public int[] getOpenTileCoordinates() {
        int[] XYCoords = new int[2];
        List<BoardTile> openTiles = Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(BoardTile::isOpen)
                .filter(tile -> tile.getTileType() == 0)
                .collect(Collectors.toList());

        Collections.shuffle(openTiles);
        BoardTile tile = openTiles.get(0);

        XYCoords[0] = tile.getXPosition();
        XYCoords[1] = tile.getYPosition();
        return XYCoords;
    }

    /**
     * Gets the X,Y coordinates of door key.
     *
     * @return this door key's X,Y coordinate pair.
     */
    public ArrayList<int[]> getDoorKeyPositions() {
        List<BoardTile> tileKeys = Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(tile -> tile.getTileType() == Character.getNumericValue('c'))
                .collect(Collectors.toList());
        ArrayList<int[]> coordinatePairs = new ArrayList<>();

        extractXYCoordinates(tileKeys, coordinatePairs);
        return coordinatePairs;
    }

    /**
     * Gets True/False value of whether tile's open or not.
     *
     * @param xCoordinate - x coordinate of the tile
     * @param yCoordinate - y coordinate of the tile
     * @return True if this tile's open, False otherwise.
     */
    public boolean getIfTileOpen(int xCoordinate, int yCoordinate) {
        return tiles[yCoordinate][xCoordinate].isOpen();
    }

    /**
     * Gets the width of the tile.
     *
     * @return this tile's width.
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Gets the height of the tile.
     *
     * @return this tile's height.
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Gets the type of the tile.
     *
     * @param xCoordinate - x coordinate of the tile
     * @param yCoordinate - y coordinate of the tile
     * @return this tile's type.
     */
    public int getTileType(int xCoordinate, int yCoordinate) {
        return tiles[yCoordinate][xCoordinate].getTileType();
    }

    /**
     * Gets the position of deep fryer.
     *
     * @return this deep fryer's X,Y coordinate pair.
     */
    public ArrayList<int[]> getDeepFryerPositions() {
        List<BoardTile> tileKeys = Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(tile -> tile.getTileType() == Character.getNumericValue('b'))
                .collect(Collectors.toList());
        ArrayList<int[]> coordinatePairs = new ArrayList<>();

        extractXYCoordinates(tileKeys, coordinatePairs);
        return coordinatePairs;
    }
}
