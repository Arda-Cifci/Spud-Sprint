package group19.GameLoop;

import group19.Entities.FryCook;


/**
 *  This class represents the moving enemy's player tracker / search algorithm
 */
public class PlayerTracker{
    //Variables and Objects used
    private GameLoop gameLoop;
    private final int tileSize = 32;

    /**
     * The constructor for this class, takes in gameloop screen/class and sets it for use in this class. 
     * 
     * @param gl : the gameloop object used throughout this class in order to manipulate the moving enemy.
     */
    public PlayerTracker (GameLoop gl) {
        this.gameLoop = gl;
    }

    /**
     *  The code for the frycook enemy to track the player and move in the player's direction.
     * 
     * @param fryCook  : the frycook to move towards the player.
     */
    public void fryCookTrackingPlayerMove(FryCook fryCook) {
        int fryCookXTile = fryCook.getX() / tileSize;
        int fryCookYTile = fryCook.getY() / tileSize;

        int playerXTile = gameLoop.getPlayerCharacter().getX() / tileSize;
        int playerYTile = gameLoop.getPlayerCharacter().getY() / tileSize;

        int xDifference = playerXTile - fryCookXTile;
        int yDifference = playerYTile - fryCookYTile;

        double random = Math.random();

        if (gameLoop.getDirectionTemp() != gameLoop.getDirection()) {
            gameLoop.setDirectionTemp(gameLoop.getDirection());
            resetForceAndCaused(fryCook);
        }

        if (fryCook.getForce() != Direction.STILL) {
            forceFryCookMovement(fryCook);
        } else if (Math.abs(xDifference) > Math.abs(yDifference)) {
            xPathSearch(fryCook, xDifference, yDifference, random);
        } else {
            yPathSearch(fryCook, xDifference, yDifference, random);
        }
    }

    /**
     *  If the player is farther away by their x - coordinate to the frycook enemy this method gets called.
     * 
     *  This method moves the frycook's x coordinate closer to the player.
     *  If x coordinate is 0 than force move the enemy to move around obstacles.  
     * 
     * @param fryCook      : The frycook to move.
     * @param xDifference  : The x coordinate difference between the frycook enemy and player.
     * @param yDifference  : The y coordinate difference between the frycook enemy and player.
     * @param random       : A random number between 0 and 1 , used to decide some movement logic.
     */
    public void xPathSearch(FryCook fryCook, Integer xDifference, Integer yDifference, Double random){
        if (xDifference > 0 && gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
            fryCook.move(2, 0);
        } else if (xDifference < 0 && gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
            fryCook.move(-2, 0);
        } else {
            setForceFromRightOrLeft(fryCook, yDifference, random);
        }
    }

    /**
     *  If the player is farther away by their y - coordinate to the frycook enemy this method gets called.
     * 
     *  This method moves the frycook's y coordinate closer to the player.
     *  If y coordinate is 0 than force move the enemy to move around obstacles.  
     * 
     * @param fryCook      : The frycook to move.
     * @param xDifference  : The x coordinate difference between the frycook enemy and player.
     * @param yDifference  : The y coordinate difference between the frycook enemy and player.
     * @param random       : A random number between 0 and 1 , used to decide some movement logic.
     */
    public void yPathSearch(FryCook fryCook, Integer xDifference, Integer yDifference, Double random){
        if (yDifference > 0 && gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
            fryCook.move(0, 2);
        } else if (yDifference < 0 && gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
            fryCook.move(0, -2);
        } else {
            setForceFromUpOrDown(fryCook, xDifference, random);
        }
    }

    /**
     *  This method gets called when the frycook enemy needs to get force moved around obstacles.
     *  Specifically if the enemy cannot move in the right or left direction.
     * 
     *  This method sets the frycook enemies force move direction.  
     * 
     * @param fryCook       : The frycook to move. 
     * @param yDifference   : The y coordinate difference between the frycook enemy and player.
     * @param random        : A random number between 0 and 1 , used to decide some movement logic.
     */
    public void setForceFromRightOrLeft(FryCook fryCook, Integer yDifference, Double random){
           // RIGHT LOGIC
        if (!gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
            if (yDifference > -8 && yDifference < 8) {
                if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook) && gameLoop.getDirection() == Direction.UP) {
                    setForceAndCaused(fryCook, Direction.UP, Direction.RIGHT); // force UP
                } else if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook) && gameLoop.getDirection() == Direction.DOWN) {
                    setForceAndCaused(fryCook, Direction.DOWN, Direction.RIGHT);// force DOWN
                } else {
                    if (random > 0.5) {
                        setForceAndCaused(fryCook, Direction.DOWN, Direction.RIGHT);// force DOWN
                    } else {
                        setForceAndCaused(fryCook, Direction.UP, Direction.RIGHT);// force UP
                    }
                }
            } else if (yDifference < 0 && gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                fryCook.move(0, -2);
            } else if (yDifference > 0 && gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                fryCook.move(0, 2);
            }
            // LEFT LOGIC BRANCH OF LOGIC
        } else if (!gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
            if (yDifference > -8 && yDifference < 8) {
                if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook) && gameLoop.getDirection() == Direction.UP) {
                    setForceAndCaused(fryCook, Direction.UP, Direction.LEFT); // force UP
                } else if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook) && gameLoop.getDirection() == Direction.DOWN) {
                    setForceAndCaused(fryCook, Direction.DOWN, Direction.LEFT); // force DOWN
                } else {
                    if (random > 0.5) {
                        setForceAndCaused(fryCook, Direction.DOWN, Direction.LEFT);// force DOWN
                    } else {
                        setForceAndCaused(fryCook, Direction.UP, Direction.LEFT);// force UP
                    }
                }
            } else if (yDifference < 0 && gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                fryCook.move(0, -2);
            } else if (yDifference > 0 && gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                fryCook.move(0, 2);
            }
        }
    }

    /**
     *  This method gets called when the frycook enemy needs to get force moved around obstacles.
     *  Specifically if the enemy cannot move in the Up or Down direction.
     * 
     *  This method sets the frycook enemies force move direction.  
     * 
     * @param fryCook       : The frycook to move. 
     * @param xDifference   : The x coordinate difference between the frycook enemy and player.
     * @param random        : A random number between 0 and 1 , used to decide some movement logic.
     */
    public void setForceFromUpOrDown(FryCook fryCook, Integer xDifference, Double random){
           // DOWN BRANCH OF LOGIC
        if (!gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
            if (xDifference > -8 && xDifference < 8) {
                if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook) && gameLoop.getDirection() == Direction.LEFT) {
                    setForceAndCaused(fryCook, Direction.LEFT, Direction.DOWN);// force left
                } else if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook) && gameLoop.getDirection() == Direction.RIGHT) {
                    setForceAndCaused(fryCook, Direction.RIGHT, Direction.DOWN); // force right
                } else {
                    if (random > 0.5) {
                        setForceAndCaused(fryCook, Direction.RIGHT, Direction.DOWN);// force right
                    } else {
                        setForceAndCaused(fryCook, Direction.LEFT, Direction.DOWN);// force left
                    }
                }
            } else if (xDifference < 0 && gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                fryCook.move(-2, 0);
            } else if (xDifference > 0 && gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                fryCook.move(2, 0);
            } // UP BRANCH OF LOGIC
        } else if (!gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
            if (xDifference > -8 && xDifference < 8) {
                if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook) && gameLoop.getDirection() == Direction.LEFT) {
                    setForceAndCaused(fryCook, Direction.LEFT, Direction.UP);// force left
                } else if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook) && gameLoop.getDirection() == Direction.RIGHT) {
                    setForceAndCaused(fryCook, Direction.RIGHT, Direction.UP);// force right
                } else {
                    if (random > 0.5) {
                        setForceAndCaused(fryCook, Direction.RIGHT, Direction.UP);// force right
                    } else {
                        setForceAndCaused(fryCook, Direction.LEFT, Direction.UP);// force left
                    }
                }
            } else if (xDifference < 0 && gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                fryCook.move(-2, 0);
            } else if (xDifference > 0 && gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                fryCook.move(2, 0);
            }
        }
    }

    /**
     *  This method checks to see if the forcefully moved frycook enemy can go in the forced direction. 
     * 
     * @param fryCook  : The frycook to move. 
     */
    public void forceFryCookMovement(FryCook fryCook){
        switch (fryCook.getForce()){
            case LEFT -> {
                forceFryCookLeftCheck(fryCook);
                break;
            }
            case UP -> {
                forceFryCookUpCheck(fryCook);
                break;
            }
            case RIGHT -> {
                forceFryCookRightCheck(fryCook);
                break;
            }
            case DOWN -> {
                forceFryCookDownCheck(fryCook);
                break;
            }
            default -> {break;}
        }
    }

    /**
     *  This method decides if the frycook can move in the choosen direction forcefully.
     *  If yes then forcefully moves frycook in said direction to clear obstacles.  
     *  If no then re-pick a new direction to force move.
     * 
     * @param fryCook : The frycook to move. 
     */
    public void forceFryCookLeftCheck(FryCook fryCook){
        switch(fryCook.getCaused()){
            case UP -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                        fryCook.move(-2, 0);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                            setForceAndCaused(fryCook, Direction.DOWN, Direction.LEFT);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                        fryCook.move(0, -8);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            case DOWN -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                        fryCook.move(-2, 0);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                            setForceAndCaused(fryCook, Direction.UP, Direction.LEFT);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                        fryCook.move(0, 8);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            default -> {break;}
        }
    }

    /**
     *  This method decides if the frycook can move in the choosen direction forcefully.
     *  If yes then forcefully moves frycook in said direction to clear obstacles.  
     *  If no then re-pick a new direction to force move.
     * 
     * @param fryCook : The frycook to move. 
     */
    public void forceFryCookUpCheck(FryCook fryCook){
        switch(fryCook.getCaused()){
            case RIGHT -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                        fryCook.move(0, -2);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                            setForceAndCaused(fryCook, Direction.LEFT, Direction.UP);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                        fryCook.move(8, 0);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            case LEFT -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                        fryCook.move(0, -2);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                            setForceAndCaused(fryCook, Direction.RIGHT, Direction.UP);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                        fryCook.move(-8, 0);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            default -> {break;}
        }
    }

    /**
     *  This method decides if the frycook can move in the choosen direction forcefully.
     *  If yes then forcefully moves frycook in said direction to clear obstacles.  
     *  If no then re-pick a new direction to force move.
     * 
     * @param fryCook : The frycook to move. 
     */
    public void forceFryCookRightCheck(FryCook fryCook){
        switch(fryCook.getCaused()){
            case UP -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                        fryCook.move(2, 0);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                            setForceAndCaused(fryCook, Direction.DOWN, Direction.RIGHT);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                        fryCook.move(0, -8);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            case DOWN -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                        fryCook.move(2, 0);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.UP, fryCook)) {
                            setForceAndCaused(fryCook, Direction.UP, Direction.RIGHT);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                        fryCook.move(0, 8);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            default -> {break;}
        }
    }

    /**
     *  This method decides if the frycook can move in the choosen direction forcefully.
     *  If yes then forcefully moves frycook in said direction to clear obstacles.  
     *  If no then re-pick a new direction to force move.
     * 
     * @param fryCook : The frycook to move. 
     */
    public void forceFryCookDownCheck(FryCook fryCook){
        switch (fryCook.getCaused()){
            case RIGHT -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                        fryCook.move(0, 2);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                            setForceAndCaused(fryCook, Direction.LEFT, Direction.DOWN);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                        fryCook.move(0, 8);
                    }
                    resetForceAndCaused(fryCook);
                }
                break;
            }
            case LEFT -> {
                if (!gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                    if (gameLoop.checkFryCookMoveDirection(Direction.DOWN, fryCook)) {
                        fryCook.move(0, 2);
                    } else {
                        if (gameLoop.checkFryCookMoveDirection(Direction.RIGHT, fryCook)) {
                            setForceAndCaused(fryCook, Direction.RIGHT, Direction.DOWN);
                        }
                    }
                } else {
                    if (gameLoop.checkFryCookMoveDirection(Direction.LEFT, fryCook)) {
                        fryCook.move(-8, 0);
                    }
                    resetForceAndCaused(fryCook);
                }  
                break;
            }
            default -> {break;}
        }
    }

    /**
     * Reset the force and caused varialbe of the frycook object to 0
     * @param fryCook   :The frycook enemy entity 
     */
    public void resetForceAndCaused(FryCook fryCook){
        fryCook.setForce(Direction.STILL);
        fryCook.setCaused(Direction.STILL);
    }

    /**
     * Sets the force and caused variable values of the frycook object to the parameters specified
     * 
     * @param fryCook :The frycook enemy entity 
     * @param force   :Force parameter value
     * @param caused  :Caused parameter value
     */
    public void setForceAndCaused(FryCook fryCook, Direction force, Direction caused){
        fryCook.setForce(force);
        fryCook.setCaused(caused);
    }
}
