package group19;

import group19.Board.Board;
import group19.Entities.DoorKey;
import group19.Entities.DeepFryer;
import group19.Entities.FryCook;
import group19.Entities.PlayerCharacter;
import group19.Entities.PotatoFriend;
import group19.GameLoop.CollisionCheck;
import group19.GameLoop.Direction;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;
import static org.junit.Assert.*;

public class CollisionCheckTest {
    private Board board;
    private CollisionCheck collisionCheck;
    private PlayerCharacter playerCharacter;

    @Before
    public void CollisionCheckTest() {
        board = new Board(32, 24, 1);
        collisionCheck = new CollisionCheck(32);

    }

    @Test
    public void testPlayerCanMoveInOpenTile() {
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertTrue(collisionCheck.checkIfEntityCanMove(Direction.UP, playerCharacter, board));
        assertTrue(collisionCheck.checkIfEntityCanMove(Direction.DOWN, playerCharacter, board));
        assertTrue(collisionCheck.checkIfEntityCanMove(Direction.LEFT, playerCharacter, board));
        assertTrue(collisionCheck.checkIfEntityCanMove(Direction.RIGHT, playerCharacter, board));
    }

    @Test
    public void testPlayerCollidesWithWallUpDirection() {
        try {
            playerCharacter = new PlayerCharacter(32, 16);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkIfEntityCanMove(Direction.UP, playerCharacter, board));
    }

    @Test
    public void testPlayerCollidesWithWallDownDirection() {
        try {
            playerCharacter = new PlayerCharacter(64, 704);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkIfEntityCanMove(Direction.DOWN, playerCharacter, board));
    }
    @Test
    public void testPlayerCollidesWithWallLeftDirection() {
        try {
            playerCharacter = new PlayerCharacter(22, 704);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkIfEntityCanMove(Direction.LEFT, playerCharacter, board));
    }

    @Test
    public void testPlayerCollidesWithWallRightDirection() {
        try {
            playerCharacter = new PlayerCharacter(992, 704);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkIfEntityCanMove(Direction.RIGHT, playerCharacter, board));
    }

    @Test
    public void testPlayerCollidesWithFryCook() {
        ArrayList<FryCook> fryCookArrayList = new ArrayList<>();
        FryCook fryCook;
        try {
            playerCharacter = new PlayerCharacter(32, 32);
            fryCook = new FryCook(32,32);
            fryCookArrayList.add(fryCook);

        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertTrue(collisionCheck.checkEntityFryCookCollision(playerCharacter, fryCookArrayList));
    }
    @Test
    public void testPlayerDoesNotCollideWithFryCook() {
        ArrayList<FryCook> fryCookArrayList = new ArrayList<>();
        FryCook fryCook;
        try {
            playerCharacter = new PlayerCharacter(64, 64);
            fryCook = new FryCook(32,32);
            fryCookArrayList.add(fryCook);

        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkEntityFryCookCollision(playerCharacter, fryCookArrayList));
    }

    @Test
    public void testPlayerCollidesPotatoFriend() {
        PotatoFriend potatoFriend = new PotatoFriend(new int[]{32, 32});
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertTrue(collisionCheck.checkIfMovingEntityCollidesWithStaticEntity(playerCharacter, potatoFriend));
    }

    @Test
    public void testPlayerDoesNotCollideWithPotatoFriend() {
        PotatoFriend potatoFriend = new PotatoFriend(new int[]{64, 64});
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkIfMovingEntityCollidesWithStaticEntity(playerCharacter, potatoFriend));
    }
    @Test
    public void testPlayerCollidesWithDeepFryer() {
        ArrayList<DeepFryer> deepFryerArrayList = new ArrayList<>();
        deepFryerArrayList.add(new DeepFryer(32,32));
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertTrue(collisionCheck.checkEntityCollisionDeepFryer(playerCharacter, deepFryerArrayList));
    }

    @Test
    public void testPlayerDoesNotCollideWithDeepFryer() {
        ArrayList<DeepFryer> deepFryerArrayList = new ArrayList<>();
        deepFryerArrayList.add(new DeepFryer(32,32));
        try {
            playerCharacter = new PlayerCharacter(64, 64);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkEntityCollisionDeepFryer(playerCharacter, deepFryerArrayList));
    }

    @Test
    public void testPlayerCollidesWithDoorKey() {
        ArrayList<DoorKey> keyArrayList = new ArrayList<>();
        keyArrayList.add(new DoorKey(32,32));
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertTrue(collisionCheck.checkEntityCollisionKey(playerCharacter,keyArrayList));
    }

    @Test
    public void testPlayerDoesNotCollideWithDoorKey() {
        ArrayList<DoorKey> keyArrayList = new ArrayList<>();
        keyArrayList.add(new DoorKey(32,32));
        try {
            playerCharacter = new PlayerCharacter(64, 64);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkEntityCollisionKey(playerCharacter,keyArrayList));
    }

    @Test
    public void testPlayerCollideClosedDoor() {

        try {
            playerCharacter = new PlayerCharacter(64, 64);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkPlayerCollisionDoorOpen(playerCharacter, 1,false, new int[]{64,64}));
    }

    @Test
    public void testPlayerCollideOpenDoor() {

        try {
            playerCharacter = new PlayerCharacter(64, 64);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(195);
        }
        assertFalse(collisionCheck.checkPlayerCollisionDoorOpen(playerCharacter, 1,true, new int[]{64,64}));
    }

}
