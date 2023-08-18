package group19.Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the user's character. Able to move and return its image.
 * 
 * @inheritDoc Represents the user's character. Able to move and return its image.
 */
public class PlayerCharacter extends Entity implements CharacterGraphic, Movable {
    /**
     * Creates a new PlayerCharacter with starting X and Y coordinates.
     *
     * @param startX starting X coordinate
     * @param startY starting Y coordinate
     * @throws IOException throws an exception when an IO error occurs
     */
    public PlayerCharacter(int startX, int startY) throws IOException {
        bufferedImageLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_left.png")));
        bufferedImageRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_right.png")));
        bufferedImageUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_front.png")));
        bufferedImageDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_front.png")));
        bufferedImageStill = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_front.png")));
        activeBufferedimage = bufferedImageStill;
        yCoordinate = startY;
        xCoordinate = startX;
    }

    /**
     * get image of the player
     */
    @Override
    public BufferedImage getActiveImage() {
        return activeBufferedimage;
    }

    /**
     * Move the player and set the new image of the player
     */
    @Override
    public void move(int xPosition, int yPosition) {

        if (xPosition != 0) {
            if (xPosition > 0) {
                activeBufferedimage = bufferedImageRight;
            } else {
                activeBufferedimage = bufferedImageLeft;
            }
        } else if (yPosition != 0) {

            if (yPosition > 0) {
                activeBufferedimage = bufferedImageDown;
            } else {
                activeBufferedimage = bufferedImageUp;
            }
        } else {
            activeBufferedimage = bufferedImageStill;
        }
        this.xCoordinate += xPosition;
        this.yCoordinate += yPosition;
    }

    /**
     * Getter method for the player x coordinate
     */
    public int getX() {
        return xCoordinate;
    }

    /**
     * Setter method for the player x coordinate
     * 
     * @param x : x coordinate
     */
    public void setX(int x) {
        this.xCoordinate = x;
    }

    /**
     * Setter method for the player y coordinate
     * 
     * @param i : y coordinate
     */
    public void setY(int i) {
        this.yCoordinate = i;
    }
}