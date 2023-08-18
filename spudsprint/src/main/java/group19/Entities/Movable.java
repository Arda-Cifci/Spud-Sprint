package group19.Entities;

/**
 * An entity that is able to move.
 */
public interface Movable {
    /**
     * Changes the entity's position to the given X and Y coordinates.
     *
     * @param xPosition new X coordinate
     * @param yPosition new Y coordinate
     */
    void move(int xPosition, int yPosition);
}