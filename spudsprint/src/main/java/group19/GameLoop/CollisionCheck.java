package group19.GameLoop;

import group19.Board.Board;
import group19.Entities.DoorKey;
import group19.Entities.DeepFryer;
import group19.Entities.Entity;
import group19.Entities.FryCook;

import java.util.ArrayList;

/**
 * A utility class that checks collisions between entities, and other entities,
 * and entities and the board.
 */

public class CollisionCheck {
    public CollisionCheck(int tileSize) {
        this.tileSize = tileSize;
    }

    private final int tileSize;
    private final int solidTopEdge =  16;
    private final int solidLeftEdge = 12;
    private final int solidRightEdge = 24;
    private final int solidBottomEdge = 28;
    /**
     * Checks if an entity collided with fry cook
     *
     * @return True if collided, False otherwise
     */
    public boolean checkEntityFryCookCollision(Entity singleEntity, ArrayList<FryCook> fryCookList) {
        int firstXTile = singleEntity.getX();
        int firstYTile = singleEntity.getY();

        int playerTopTiled = firstYTile / tileSize;
        int playerLeftTiled = firstXTile / tileSize;
        int playerRightTiled = firstXTile / tileSize;
        int playerBottomTiled = firstYTile / tileSize;

        for (FryCook fryCook : fryCookList) {
            int entityXTile = fryCook.getX() / tileSize;
            int entityYTile = fryCook.getY() / tileSize;
            if ((playerTopTiled == entityYTile && playerBottomTiled == entityYTile) &&
                    (playerLeftTiled == entityXTile && playerRightTiled == entityXTile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an entity can move
     *
     * @return True if can move, False otherwise
     */

    public boolean checkIfEntityCanMove(Direction direction, Entity entity, Board board) {
        int entityXTile = entity.getX();
        int entityYTile = entity.getY();

        int entityTopEdge = entityYTile + solidTopEdge;
        int entityLeftEdge = entityXTile + solidLeftEdge;
        int entityRightEdge = entityXTile + solidRightEdge;
        int entityBottomEdge = entityYTile + solidBottomEdge;

        int entityTopTiled = entityTopEdge / tileSize;
        int entityLeftTiled = entityLeftEdge / tileSize;
        int entityRightTiled = entityRightEdge / tileSize;
        int entityBottomTiled = entityBottomEdge / tileSize;

        switch (direction) {
            case DOWN -> {
                entityBottomTiled = (entityBottomEdge + 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(entityLeftTiled, entityBottomTiled);
                boolean tileTwoOpen = board.getIfTileOpen(entityRightTiled, entityBottomTiled);
                return tileOneOpen && tileTwoOpen;
            }
            case UP -> {
                entityTopTiled = (entityTopEdge - 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(entityLeftTiled, entityTopTiled);
                boolean tileTwoOpen = board.getIfTileOpen(entityRightTiled, entityTopTiled);
                return tileOneOpen && tileTwoOpen;
            }
            case LEFT -> {
                entityLeftTiled = (entityLeftEdge - 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(entityLeftTiled, entityTopTiled);
                boolean tileTwoOpen = board.getIfTileOpen(entityLeftTiled, entityBottomTiled);
                return tileOneOpen && tileTwoOpen;
            }
            case RIGHT -> {
                entityRightTiled = (entityRightEdge + 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(entityRightTiled, entityBottomTiled);
                boolean tileTwoOpen = board.getIfTileOpen(entityRightTiled, entityTopTiled);
                return tileOneOpen && tileTwoOpen;
            }
            default -> {
                return true;
            }
        }
    }
    /**
     * Checks if an entity collides with deep fryer
     * Sets deep fryer to triggered to disappear from map
     *
     * @return True if collision occurs, False otherwise
     */

    public boolean checkEntityCollisionDeepFryer(Entity entity, ArrayList<DeepFryer> deepFryers) {
        int entityXTile = entity.getX();
        int entityYTile = entity.getY();

        int entityTopEdge = entityYTile + solidTopEdge;
        int entityLeftEdge = entityXTile + solidLeftEdge;
        int entityRightEdge = entityXTile + solidRightEdge;
        int playerBottomEdge = entityYTile + solidBottomEdge;

        int entityTopTiled = entityTopEdge / tileSize;
        int entityLeftTiled = entityLeftEdge / tileSize;
        int entityRightTiled = entityRightEdge / tileSize;
        int entityBottomTiled = playerBottomEdge / tileSize;

        for (DeepFryer deepFryer : deepFryers) {
            int deepFryerXTile = deepFryer.getX() / tileSize;
            int deepFryerYTile = deepFryer.getY() / tileSize;
            if ((entityTopTiled == deepFryerYTile && entityBottomTiled == deepFryerYTile)
                    && (entityLeftTiled == deepFryerXTile && entityRightTiled == deepFryerXTile
                    && !deepFryer.getHasBeenTriggered())) {
                deepFryer.triggerFryer();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an entity collides with a door key
     * Sets door key to collected to disappear from map
     *
     * @return True if collision occurs, False otherwise
     */

    public boolean checkEntityCollisionKey(Entity entity, ArrayList<DoorKey> doorKeys) {
        int entityXTile = entity.getX();
        int entityYTile = entity.getY();

        int entityTopEdge = entityYTile + solidTopEdge;
        int entityLeftEdge = entityXTile + solidLeftEdge;
        int entityRightEdge = entityXTile + solidRightEdge;
        int entityBottomEdge = entityYTile + solidBottomEdge;

        int entityTopTiled = entityTopEdge / tileSize;
        int entityLeftTiled = entityLeftEdge / tileSize;
        int entityRightTiled = entityRightEdge / tileSize;
        int entityBottomTiled = entityBottomEdge / tileSize;

        for (DoorKey doorkey : doorKeys) {
            int doorKeyXTile = doorkey.getX() / tileSize;
            int doorKeyYTile = doorkey.getY() / tileSize;

            if ((entityTopTiled == doorKeyYTile && entityBottomTiled == doorKeyYTile)
                    && (entityLeftTiled == doorKeyXTile && entityRightTiled == doorKeyXTile && !doorkey.getHasBeenCollected())) {
                doorkey.setToCollected();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an entity collides with an entity that is static
     *
     * @return True if collision occurs, False otherwise
     */
    public boolean checkIfMovingEntityCollidesWithStaticEntity(Entity first, Entity second) {
        int firstXTile = first.getX();
        int firstYTile = first.getY();

        int firstTopEdge = firstYTile + solidTopEdge;
        int firstLeftEdge = firstXTile + solidLeftEdge;
        int firstRightEdge = firstXTile + solidRightEdge;
        int firstBottomEdge = firstYTile + solidBottomEdge;

        int firstTopTiled = firstTopEdge / tileSize;
        int firstLeftTiled = firstLeftEdge / tileSize;
        int firstRightTiled = firstRightEdge / tileSize;
        int firstBottomTiled = firstBottomEdge / tileSize;

        int secondFriendXTile = second.getX() / tileSize;
        int secondFriendYTile = second.getY() / tileSize;

        return (firstTopTiled == secondFriendYTile && firstBottomTiled == secondFriendYTile)
                && firstLeftTiled == secondFriendXTile && firstRightTiled == secondFriendXTile;
    }

    /**
     * Checks if an entity collides with the door
     *
     * @return True if collision occurs, False otherwise
     */
    public boolean checkPlayerCollisionDoorOpen(Entity entity, int difficulty, boolean doorOpen, int[] saveDoor) {

        int playerXTile = entity.getX();
        int playerYTile = entity.getY();

        int playerTopEdge = playerYTile + solidTopEdge;
        int playerLeftEdge = playerXTile + solidTopEdge;

        int playerLeftTiled;
        int playerTopTiled;
        switch(difficulty) {
            case 1, 6, 2 -> {
                playerTopTiled = (playerTopEdge / tileSize) - 1;
                playerLeftTiled = playerLeftEdge / tileSize;
            }
            case 3, 9, 8, 4 -> {
                playerTopTiled = (playerTopEdge / tileSize);
                playerLeftTiled = playerLeftEdge / tileSize + 1;
            }
            case 5, 7 -> {
                playerTopTiled = (playerTopEdge / tileSize) + 1;
                playerLeftTiled = playerLeftEdge / tileSize;
            }
            default -> {
                playerLeftTiled = playerLeftEdge / tileSize;
                playerTopTiled = (playerTopEdge / tileSize);
            }
        }

        int doorX = saveDoor[0];
        int doorY = saveDoor[1];

        return (playerLeftTiled == doorX) && (playerTopTiled == doorY) && doorOpen;
    }
}


