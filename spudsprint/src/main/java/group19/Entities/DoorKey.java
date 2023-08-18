package group19.Entities;

/**
 * Represents a Door key which consists of x,y coordinates.
 * One can set its 'has been collected' state to True
 * and get True/False whether it has been collected or not.
 */

public class DoorKey extends Entity {
    private boolean hasBeenCollected = false;

    /**
     * Creates a new door key with the given x coordinate, y coordinate.
     *
     * @param xCoordinate - x coordinate of the door key
     * @param yCoordinate - y coordinate of the door key
     */
    public DoorKey(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Sets the door key's collection state to True
     */
    public void setToCollected() {
        this.hasBeenCollected = true;
    }

    /**
     * Gets True/False whether the door key has been collected or not.
     *
     * @return True if door key has been collected, False otherwise.
     */
    public boolean getHasBeenCollected() {
        return hasBeenCollected;
    }

    /**
     * Gets the door's x coordinate
     * 
     * @return
     */
    public int getXCoord() {
        return this.xCoordinate;
    }

    /**
     * Gets the door's y coordinate
     * 
     * @return
     */
    public int getYCoord() {
        return this.yCoordinate;
    }
}