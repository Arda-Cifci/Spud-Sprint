package group19.Entities;

import java.awt.image.BufferedImage;

/**
 * Abstract class that represents entity for all sprites (player character, fry cook, potato friend, key).
 * One can get x,y coordinates of an entity.
 */
public abstract class Entity {
    //Variables used
    protected int xCoordinate;
    protected int yCoordinate;
    protected BufferedImage bufferedImageLeft, bufferedImageRight,
            bufferedImageUp, bufferedImageDown, bufferedImageStill;
    protected BufferedImage activeBufferedimage;
    protected String graphicFile;
    protected int graphicHeight;
    protected int graphicWidth;

    /**
     * Gets x coordinate of an entity.
     *
     * @return x coordinate of an entity
     */
    public int getX() {
        return this.xCoordinate;
    }

    /**
     * Gets y coordinate of an entity.
     *
     * @return y coordinate of an entity
     */
    public int getY() {
        return this.yCoordinate;
    }
}