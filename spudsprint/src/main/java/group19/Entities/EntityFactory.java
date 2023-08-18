package group19.Entities;

import java.util.ArrayList;
import java.io.IOException;

public class EntityFactory {
    /**
     * Creates fry cooks depending on the game's difficulty and level.
     *
     * @throws IOException if I/O error occurs.
     * @param difficulty the game's difficulty
     * @param tileSize the width of the tile
     * @return 
     */
    public static ArrayList<FryCook> makeFryCooks(int difficulty, int tileSize) throws IOException {
        ArrayList<FryCook> fryCooks = new ArrayList<FryCook>();
        switch (difficulty) {
            case (1), (2), (3) -> fryCooks.add(new FryCook(21 * tileSize, 11 * tileSize)); // tilesize (variable of GameLoop) is 32
            case (4), (5), (6) -> {
                fryCooks.add(new FryCook(21 * tileSize, 12 * tileSize));
                fryCooks.add(new FryCook(21 * tileSize, 17 * tileSize));
            }
            case (7) -> {
                fryCooks.add(new FryCook(10 * tileSize, 9 * tileSize));
                fryCooks.add(new FryCook(24 * tileSize, 9 * tileSize));
            }
            case (8) -> {
                fryCooks.add(new FryCook(9 * tileSize, 9 * tileSize));
                fryCooks.add(new FryCook(23 * tileSize, 9 * tileSize));
            }
            case (9) -> {
                fryCooks.add(new FryCook(9 * tileSize, 10 * tileSize));
                fryCooks.add(new FryCook(23 * tileSize, 9 * tileSize));
            }
            default -> {
            }
        }
        return fryCooks;
    }

    /**
     * Creates deep fryers.
     * 
     * @param deepFryerPositionList the x and y coordinates of all the deep fryers
     * @param tileSize the width of the tile
     * @return
     */
    public static ArrayList<DeepFryer> makeDeepFryers(ArrayList<int[]> deepFryerPositionList, int tileSize) {
        ArrayList<DeepFryer> deepFryers = new ArrayList<DeepFryer>();
        for (int[] XY : deepFryerPositionList) {
            deepFryers.add(new DeepFryer(XY[0] * tileSize,
                    XY[1] * tileSize));
        }
        return deepFryers;
    }

    /**
     * Creates a potato friend.
     * 
     * @param XYtiles the initial coordinates of the potato friend.
     * @return
     */
    public static PotatoFriend makePotatoFriend(int[] XYtiles) {
        return new PotatoFriend(XYtiles);
    }

    /**
     * Creates the player character.
     * 
     * @param xcoord the initial x coordinate of the player character
     * @param ycoord the initial y coordinate of the player character
     * @return
     * @throws IOException
     */
    public static PlayerCharacter makePlayerCharacter(int xcoord, int ycoord) throws IOException {
        return new PlayerCharacter(xcoord, ycoord);
    }

    /**
     * Creates the door keys.
     * 
     * @param keyPositionList the xand y coordinates of all the keys
     * @param tileSize the width of the tile
     * @return
     */
    public static ArrayList<DoorKey> makeDoorKeys(ArrayList<int[]> keyPositionList, int tileSize) {
        ArrayList<DoorKey> doorKeys = new ArrayList<DoorKey>();
        for (int[] XYTiles : keyPositionList) {
            doorKeys.add(new DoorKey(XYTiles[0] * tileSize,
                    XYTiles[1] * tileSize));
        }
        return doorKeys;
    }
}