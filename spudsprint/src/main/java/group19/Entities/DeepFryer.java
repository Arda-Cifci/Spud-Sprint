package group19.Entities;

/**
 * Represents a deep fryer which consists of x,y coordinates.
 * One can set its 'has been triggered' state to True
 * and get True/False whether it has been triggered or not.
 */
public class DeepFryer extends Entity {
    private boolean hasBeenTriggered = false;

    /**
     * Creates a new deep fryer with the given x coordinate, y coordinate.
     *
     * @param xCoordinate - x coordinate of the deep fryer
     * @param yCoordinate - y coordinate of the deep fryer
     */
    public DeepFryer(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Sets the deep fryer's trigger state to True
     */
    public void triggerFryer() {
        hasBeenTriggered = true;
    }

    /**
     * Gets True/False whether the deep fryer has been triggered or not.
     *
     * @return True if deep fryer has been triggered, False otherwise.
     */
    public boolean getHasBeenTriggered() {
        return hasBeenTriggered;
    }
}
