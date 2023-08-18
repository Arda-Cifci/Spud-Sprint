package group19.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 *  This Class reads in and stores various resources we will use throughout the program. 
 * 
 *  Getter methods are used to retrive the resources. 
 */
public class BoardSpriteManager {
    //Image Variables used to store the resources.
    BufferedImage bufferedPotatoFriend;
    BufferedImage bufferedOpen;
    BufferedImage bufferedTopBottomWall;
    BufferedImage bufferedSideWall;
    BufferedImage bufferedChairFront;
    BufferedImage bufferedChairBack;
    BufferedImage bufferedChairLeft;
    BufferedImage bufferedChairRight;
    BufferedImage bufferedHorizontalLeftTable;
    BufferedImage bufferedHorizontalRightTable;
    BufferedImage bufferedVerticalTopTable;
    BufferedImage bufferedVerticalBottomTable;
    BufferedImage bufferedDeepFryer;
    BufferedImage bufferedKey;
    BufferedImage bufferedClosedDoor;
    BufferedImage bufferedOpenDoor;

    /**
     * This constructor reads in resources and stores them in BufferedImage variables.
     * 
     * @throws IOException
     */
    public BoardSpriteManager() throws IOException {
        bufferedPotatoFriend = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_friend.png")));
        bufferedOpen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile.png")));
        bufferedTopBottomWall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/WallFrontAndBack.png")));
        bufferedSideWall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/WallSides.png")));
        bufferedChairFront = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ChairFront.png")));
        bufferedChairBack = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ChairBack.png")));
        bufferedChairLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ChairLeft.png")));
        bufferedChairRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ChairRight.png")));
        bufferedHorizontalLeftTable = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/HorizonTableLeft.png")));
        bufferedHorizontalRightTable = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/HorizonTableRight.png")));
        bufferedVerticalTopTable = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/VertTableTop.png")));
        bufferedVerticalBottomTable = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/VertTableBottom.png")));
        bufferedDeepFryer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/DeepFryer.png")));
        bufferedKey = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Key.png")));
        bufferedClosedDoor = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/DoorClosed.png")));
        bufferedOpenDoor = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/DoorOpen.png")));
    }

    /**
     * Getter method for the potato friend bonus reward
     * 
     * @return : returns the potato friend image
     */
    public BufferedImage getBufferedPotatoFriend() {
        return bufferedPotatoFriend;
    }

    /**
     * Getter method for the checkered floor tile
     * 
     * @return : returns the floor tile image.
     */
    public BufferedImage getBufferedOpen() {
        return bufferedOpen;
    }

    /**
     * Getter method for the top side of the wall
     * 
     * @return : returns the top side of wall image
     */
    public BufferedImage getBufferedTopBottomWall() {
        return bufferedTopBottomWall;
    }

    /**
     * Getter method for the side image of the wall. 
     * 
     * @return : returns the side wall image
     */
    public BufferedImage getBufferedSideWall() {
        return bufferedSideWall;
    }

    /**
     * Getter method for front facing chair image
     * 
     * @return : returns front facing chair image
     */
    public BufferedImage getBufferedChairFront() {
        return bufferedChairFront;
    }

    /**
     * Getter method for back facing chair image
     * 
     * @return : returns back facing chair image
     */
    public BufferedImage getBufferedChairBack() {
        return bufferedChairBack;
    }

    /**
     * Getter method for left facing chair image
     * 
     * @return : returns left facing chair image
     */
    public BufferedImage getBufferedChairLeft() {
        return bufferedChairLeft;
    }

     /**
     * Getter method for right facing chair image
     * 
     * @return : returns right facing chair image
     */
    public BufferedImage getBufferedChairRight() {
        return bufferedChairRight;
    }

    /**
     * Getter method for the horizontal table left image
     * 
     * @return : returns right horizontal table left image
     */    
    public BufferedImage getBufferedHorizontalLeftTable() {
        return bufferedHorizontalLeftTable;
    }

    /**
     * Getter method for the horizontal table right image
     * 
     * @return : returns right horizontal table right image
     */  
    public BufferedImage getBufferedHorizontalRightTable() {
        return bufferedHorizontalRightTable;
    }

    /**
     * Getter method for the vertical table top image
     * 
     * @return : returns right vertical table top image
     */  
    public BufferedImage getBufferedVerticalTopTable() {
        return bufferedVerticalTopTable;
    }

    /**
     * Getter method for the vertical table bottom image
     * 
     * @return : returns right vertical table bottom image
     */  
    public BufferedImage getBufferedVerticalBottomTable() {
        return bufferedVerticalBottomTable;
    }

    /**
     * Getter method for the deep fryer image
     * 
     * @return : returns deep fryer image
     */   
    public BufferedImage getBufferedDeepFryer() {
        return bufferedDeepFryer;
    }

    /**
     * Getter method for the key image
     * 
     * @return : returns key image
     */   
    public BufferedImage getBufferedKey() {
        return bufferedKey;
    }

    /**
     * Getter method for the closed door image
     * 
     * @return : returns closed door image
     */   
    public BufferedImage getBufferedClosedDoor() {
        return bufferedClosedDoor;
    }

    /**
     * Getter method for the open door image
     * 
     * @return : returns open door image
     */   
    public BufferedImage getBufferedOpenDoor() {
        return bufferedOpenDoor;
    }
}
