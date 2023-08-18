package group19.Board;

/**
 * Represents each tile that makes up the board
 * One can get X,Y position and the type of a tile
 * and whether a tile is open or not.
 */
public class BoardTile {
    // Variables used
    private final int xPosition;
    private final int yPosition;
    private final boolean isOpen;
    private final int tileType;

    /**
     * Creates a new board tile with the given x coordinate, y coordinate, and tile type.
     *
     * @param xPosition - x coordinate of this tile
     * @param yPosition - y coordinate of this tile
     * @param isOpen    - True/False whether this tile is open or not
     * @param tileType  - type of the tile
     */
    public BoardTile(int xPosition, int yPosition, boolean isOpen, int tileType) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isOpen = isOpen;
        this.tileType = tileType;
    }

    /**
     * Gets the X position of a tile.
     *
     * @return this tile's X position.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Gets the Y position of a tile.
     *
     * @return this tile's Y position.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Returns True/False whether this tile is open or not.
     *
     * @return True if this tile is open, False otherwise.
     */
    public boolean isOpen() {
        return isOpen;

    }

    /**
     * Gets the type of the tile.
     *
     * @return this tile's type.
     */
    public int getTileType() {
        return tileType;
    }
}
