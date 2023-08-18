package group19.Entities;

import javax.imageio.ImageIO;

import group19.GameLoop.Direction;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a fry cook which consists of x,y coordinates and its left/right buffered images.
 * One can get its active buffered image and move it given its x,y positions.
 */
public class FryCook extends Entity implements CharacterGraphic, Movable {
    // Variables used
    private Direction force = Direction.STILL;
    private Direction forcedCaused = Direction.STILL;


    /**
     * Creates a new fry cook with the given x coordinate, y coordinate.
     *
     * @param xCoordinate - x coordinate of the fry cook
     * @param yCoordinate - y coordinate of the fry cook
     * @throws IOException if an I/O error occurs
     */
    public FryCook(int xCoordinate, int yCoordinate) throws IOException {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        bufferedImageLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/FryCookLeft.png")));
        bufferedImageRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/FryCookRight.png")));
        activeBufferedimage = bufferedImageLeft;
    }


    /**
     * Gets the buffered image of fry cook when active.
     *
     * @return fry cook's active buffered image.
     */
    @Override
    public BufferedImage getActiveImage() {
        return activeBufferedimage;
    }

    /**
     * Moves a fry cook with the given x position, y position.
     *
     * @param xPosition - x coordinate of the fry cook
     * @param yPosition - y coordinate of the fry cook
     */
    @Override
    public void move(int xPosition, int yPosition) {
        if (xPosition > 0) {
            activeBufferedimage = bufferedImageRight;
        } else if (xPosition < 0) {
            activeBufferedimage = bufferedImageLeft;
        } else {
            activeBufferedimage = bufferedImageLeft;
        }

        this.xCoordinate += xPosition;
        this.yCoordinate += yPosition;
    }

    /**
     *  Gets the force direction attribute
     * 
     * @return   : returns the force direction of the frycook
     */
    public Direction getForce() {
        return force;
    }

    /**
     *  Sets the force direction of the frycook
     * 
     * @param f   : the force direction to be set
     */
    public void setForce(Direction f) {
        this.force = f;
    }

    /**
     * Gets the direction the frycook got stopped by
     * 
     * @return  : returns the direction the frycook couldn't move in
     */
    public Direction getCaused() {
        return forcedCaused;
    }

    /**
     *  Sets the force caused direction of the frycook
     * 
     * @param f   : the force caused direction to be set
     */
    public void setCaused(Direction f) {
        this.forcedCaused = f;
    }

    /**
     * returns the x coord of the frycook enemy
     * 
     * @return : returns the x coordinate of the frycook
     */
    public int getX(){
        return this.xCoordinate;
    }

    /**
     * returns the y coord of the frycook enemy
     * 
     * @return : returns the y coordinate of the frycook
     */
    public int getY(){
        return this.yCoordinate;
    }

}
