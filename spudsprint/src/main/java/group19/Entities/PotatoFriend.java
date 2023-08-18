package group19.Entities;

/**
 * Represents the bonus reward of the game
 * 
 * @inheritDoc Able to set its X and Y coordinates.
 */
public class PotatoFriend extends Entity {

    /**
     * Creates a new PotatoFriend with the given coordinates
     *
     * @param openTileCoordinates X and Y coordinates
     */
    public PotatoFriend(int[] openTileCoordinates) {
        xCoordinate = openTileCoordinates[0];
        yCoordinate = openTileCoordinates[1];

    }

    /**
     * Sets the PotatoFriend's X and Y coordinates
     *
     * @param xCoordinate X coordinate to set
     * @param yCoordinate Y coordinate to set
     */
    public void setPotatoFriendPosition(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }
}