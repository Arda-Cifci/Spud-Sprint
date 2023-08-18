package group19.GameLoop;

import group19.Board.Board;
import group19.Board.BoardSpriteManager;
import group19.Entities.DoorKey;
import group19.Entities.DeepFryer;
import group19.Entities.FryCook;
import group19.Entities.PlayerCharacter;
import group19.Entities.PotatoFriend;
import group19.Entities.EntityFactory;
import group19.InputHandler.CharacterCommand;
import group19.InputHandler.InputHandler;
import group19.UIScreens.LoseScreen;
import group19.UIScreens.PauseScreen;
import group19.UIScreens.WinScreen;
import group19.UIScreens.Label;
import group19.UIScreens.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * This class represents the game screen UI and the game loop which
 * loops through the game logic at each tick to display and interpret all things
 * that are needed to run the game. 
 */
public class GameLoop extends JPanel implements Runnable, ActionListener {
    // Variables and objects used.
    private final int tileSize = 32;
    private final BoardSpriteManager boardSpriteManager = new BoardSpriteManager();
    private final PlayerCharacter playerCharacter;
    private final JFrame ui;
    private final GameState gameState;
    private final int[] saveDoor = {0, 0};
    boolean pauseGameFlag = false;
    private Thread renderThread;
    private InputHandler inputHandler = new InputHandler();
    private ArrayList<DoorKey> doorKeys;
    private ArrayList<DeepFryer> deepFryers;
    private ArrayList<FryCook> fryCooks;
    private PotatoFriend potatoFriend;
    private Board board;
    private JButton pause;
    private JLabel scoreL;
    private JLabel timerL;
    private JLabel levelL;
    private PauseScreen pausescreen;
    private WinScreen winscreen;
    private LoseScreen losescreen;
    private boolean nextScreen = false;
    private boolean doorOpen = false;
    private CharacterCommand characterCommand;
    private CollisionCheck collisionCheck = new CollisionCheck(tileSize);
    private PlayerTracker playerTracker = new PlayerTracker(this);
    private Direction direction;
    private Direction directionTemp = Direction.STILL;

    /**
     * Creates the game loop UI with the given JFrame ui and GameState gs.
     * Sets visual attributes and add labels
     *
     * @param ui - the visual user interface of a game
     * @param gameState - the game state
     * @throws IOException if I/O error occurs
     */
    public GameLoop(JFrame ui, GameState gameState) throws IOException {
        this.gameState = gameState;
        playerCharacter = EntityFactory.makePlayerCharacter(this.gameState.getPlayerStartXCoordinate(), this.gameState.getPlayerStartYCoordinate());
        this.ui = ui;
        setLayout(null);
        this.setBackground(new Color(204, 161, 96));
        this.setPreferredSize(new Dimension(this.gameState.getGamePixelWidth(), this.gameState.getGamePixelHeight()));
        this.setDoubleBuffered(true);
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
        buttons();
        addLabels();
        setVisible(false);
    }

    /**
     * This method resets the game back to the game start state.
     */
    public void reset() {
        resetEntityAndKey();
        gameState.resetScore();
        gameState.resetTime();
        timerL.setText(" Time: " + gameState.getTime());
        gameState.resetLevelNum();
        this.doorOpen = false;
        saveDoor[0] = 0;
        saveDoor[1] = 0;
        nextScreen = false;
        inputHandler = new InputHandler();
        this.addKeyListener(inputHandler);
    }

    /**
     *  Resets the entities and key objects of the game specifically.
     */
    public void resetEntityAndKey(){
        playerCharacter.setX(94);
        playerCharacter.setY(700);
        deepFryers = new ArrayList<>();
        doorKeys = new ArrayList<>();
        fryCooks = new ArrayList<>();
        gameState.resetKeys();
    }

    /**
     * This method opens the lose screen if the player hits the moving enemy or goes below 0 score.
     */
    public void openLoseScreen(){
        resetEntityAndKey();
        togglebuttons("OFF");
        ui.setSize(1024, 768);
        setVisible(false);
        losescreen.togglebuttons("ON");
        losescreen.setVisible(true);
        losescreen.requestFocus();
        gameState.unStunPlayer();
        gameState.stunTime = 0;
        setPauseFlag();
    }

    /**
     *  This method checks if the player has a negative score because of the static punishment deepfryer,
     *  If there is a negative score display lose screen.
     */
    public void checkDeadFromFryer(){
        if (gameState.getScore() < 0) {
            openLoseScreen();
        }
    }

    /**
     * This method checks which screen /level should be displayed next during the game 
     * and sets the scene up accordingly by reseting all entites to correct position. 
     */
    public void nextGameScreen(){
        if (nextScreen) {
            resetEntityAndKey();
            nextScreen = false;
            doorOpen = false;

            switch (gameState.getDifficulty()) {
                case 1 -> {
                    gameState.setDifficulty(2);
                    gameState.setLevelNum(2); 
                }
                case 2 -> {
                    gameState.setDifficulty(3);
                    gameState.setLevelNum(3);               
                }
                case 3, 6, 9 -> {
                    togglebuttons("OFF");
                    ui.setSize(1024, 768);
                    setVisible(false);
                    winscreen.togglebuttons("ON");
                    winscreen.setVisible(true);
                    winscreen.requestFocus();
                    setPauseFlag();                 
                }
                case 4 -> {
                    gameState.setDifficulty(5);
                    gameState.setLevelNum(2);
                }
                case 5 -> {
                    gameState.setDifficulty(6);
                    gameState.setLevelNum(3);
                }
                case 7 -> {
                    gameState.setDifficulty(8);
                    gameState.setLevelNum(2);    
                }
                case 8 -> {
                    gameState.setDifficulty(9);
                    gameState.setLevelNum(3);   
                }
                default -> {
                }
            }
            if (gameState.getDifficulty() != 3 || gameState.getDifficulty() != 6 || gameState.getDifficulty() != 9) {
                initMap();
                render();
            }
        }
    }
    

    /**
     * Runs one round of a game until paused/won/lost.
     * Changes the level number and difficulty accordingly.
     * Displays score, elapsed time, and level number ( # / 3 ), etc
     */
    public void run() {
        double secondPerFrame = 1000000000 / gameState.getFramesPerSecond();
        double timeDelta = 0;
        long previousTime = System.nanoTime();
        long currentTime;

        while (checkIfPlaying()) {

            checkDeadFromFryer();
            nextGameScreen();

            pausescreen.setPause();

            scoreL.setText(" Score: " + gameState.getScore());
            timerL.setText(" Time: " + gameState.getTime());
            levelL.setText(" Room: " + gameState.getLevelNum() + " / 3");
            winscreen.setScore();
            winscreen.setTimer();

            if (pauseGameFlag) {
                try {
                    pauseSpin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    exit(69);
                }
                previousTime = System.nanoTime();
            }
            currentTime = System.nanoTime();
            timeDelta += (currentTime - previousTime) / secondPerFrame;
            previousTime = currentTime;
            if (timeDelta >= 1) {
                timeDelta--;
                updateState();
                render();
            }
        }
    }

    /**
     * Pause the current game.
     *
     * @throws InterruptedException if a thread is interrupted while it's sleeping
     */
    private void pauseSpin() throws InterruptedException {
        while (pauseGameFlag) {
            Thread.sleep(1);
            setPauseFlagFromScreen(pausescreen.getPauseFlag());
        }
    }

    /**
     * Updates the state of current game.
     * - Increments elapsed time
     * - Checks and displays whether player collided with potato friend, keys, or deep fryer
     * - Changes the game screen if the player collided with the door
     */
    private void updateState() {
        direction = inputHandler.getDirection();

        gameState.incrementTime();

        if (collisionCheck.checkIfEntityCanMove(direction, playerCharacter, board) && !gameState.isPlayerStunned()) {
            characterCommand = inputHandler.getCharacterCommand();
            characterCommand.execute(playerCharacter);
        }

        moveFryCooks();

        if (collisionCheck.checkEntityFryCookCollision(playerCharacter, fryCooks)) {
            openLoseScreen();
        }
        if (collisionCheck.checkIfMovingEntityCollidesWithStaticEntity(playerCharacter, potatoFriend)) {
            gameState.addBonusRewardToScore();
            int[] XYTiles = getOpenTileCoordinates();
            potatoFriend.setPotatoFriendPosition(XYTiles[0], XYTiles[1]);
        }
        if (collisionCheck.checkEntityCollisionKey(playerCharacter, doorKeys)) {
            gameState.incrementKeysCollected();
            gameState.addRewardToScore();
        }
        if (collisionCheck.checkEntityCollisionDeepFryer(playerCharacter, deepFryers)) {
            gameState.decrementScore();
            gameState.stunPlayer();
        }
        if (gameState.checkIfPotatoFriendRespawn()) {
            int[] XYTiles = getOpenTileCoordinates();
            potatoFriend.setPotatoFriendPosition(XYTiles[0], XYTiles[1]);
        }
        if (collisionCheck.checkPlayerCollisionDoorOpen(playerCharacter, gameState.getDifficulty(),
                                                        doorOpen,saveDoor)) {
            nextScreen = true;
        }
    }

    /**
     * Moves fry cooks in general direction of player character.
     *
     */
    private void moveFryCooks() {
        for (FryCook fryCook : fryCooks) {
                playerTracker.fryCookTrackingPlayerMove(fryCook);
        }
    }

    /**
     * Moves a given fry cook based on a given direction.
     *
     * @param fryCook      - given fry cook
     * @param newDirection - given direction (UP, DOWN, LEFT, RIGHT, or STILL)
     */
    public boolean checkFryCookMoveDirection(Direction newDirection, FryCook fryCook) {
        int fryCookXTile = fryCook.getX();
        int fryCookYTile = fryCook.getY();

        int fryCookTopEdge = fryCookYTile + 16;
        int fryCookLeftEdge = fryCookXTile + 12;
        int fryCookRightEdge = fryCookXTile + 24;
        int fryCookBottomEdge = fryCookYTile + 28;

        int fryCookTopTiled = fryCookTopEdge / tileSize;
        int fryCookLeftTiled = fryCookLeftEdge / tileSize;
        int fryCookRightTiled = fryCookRightEdge / tileSize;
        int fryCookBottomTiled = fryCookBottomEdge / tileSize;

        switch (newDirection) {
            case DOWN -> {
                fryCookBottomTiled = (fryCookBottomEdge + 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(fryCookLeftTiled, fryCookBottomTiled);
                boolean tileTwoOpen = board.getIfTileOpen(fryCookRightTiled, fryCookBottomTiled);
                return tileOneOpen && tileTwoOpen;
            }
            case UP -> {
                fryCookTopTiled = (fryCookTopEdge - 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(fryCookLeftTiled, fryCookTopTiled);
                boolean tileTwoOpen = board.getIfTileOpen(fryCookRightTiled, fryCookTopTiled);
                return tileOneOpen && tileTwoOpen;
            }
            case LEFT -> {
                fryCookLeftTiled = (fryCookLeftEdge - 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(fryCookLeftTiled, fryCookTopTiled);
                boolean tileTwoOpen = board.getIfTileOpen(fryCookLeftTiled, fryCookBottomTiled);
                return tileOneOpen && tileTwoOpen;
            }
            case RIGHT -> {
                fryCookRightTiled = (fryCookRightEdge + 4) / tileSize;
                boolean tileOneOpen = board.getIfTileOpen(fryCookRightTiled, fryCookBottomTiled);
                boolean tileTwoOpen = board.getIfTileOpen(fryCookRightTiled, fryCookTopTiled);
                return tileOneOpen && tileTwoOpen;
            }
            default -> {
                return true;
            }
        }
    }

    /**
     * Checks if a player is playing in a game.
     *
     * @return True if a player is active, False otherwise.
     */
    private boolean checkIfPlaying() {
        return renderThread != null;
    }

    /**
     * Starts a new render thread.
     */
    public void startRenderThread() {
        renderThread = new Thread(this);
        renderThread.start();
    }

    /**
     * Repaints a game screen.
     */
    private void render() {
        repaint();
    }

    /**
     * Repaints a game screen.
     *
     * @param g - given graphic
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        for (int yCoordinate = 0; yCoordinate < board.getTileHeight(); yCoordinate++) {
            for (int xCoordinate = 0; xCoordinate < board.getTileWidth(); xCoordinate++) {
                if (board.getTileType(xCoordinate, yCoordinate) == 0) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 1) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedTopBottomWall(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 2) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedSideWall(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 3) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedChairFront(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 4) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedChairBack(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 5) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedChairLeft(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 6) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedChairRight(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 7) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedHorizontalLeftTable(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 8) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedHorizontalRightTable(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == 9) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedVerticalTopTable(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == Character.getNumericValue('a')) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    graphics2D.drawImage(boardSpriteManager.getBufferedVerticalBottomTable(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == Character.getNumericValue('b')) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    if (checkIfDeepFryerTriggered(xCoordinate, yCoordinate)) {
                        continue;
                    }
                    graphics2D.drawImage(boardSpriteManager.getBufferedDeepFryer(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == Character.getNumericValue('c')) {
                    graphics2D.drawImage(boardSpriteManager.getBufferedOpen(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    if (checkIfKeyCollected(xCoordinate, yCoordinate)) {
                        continue;
                    }
                    graphics2D.drawImage(boardSpriteManager.getBufferedKey(), xCoordinate * tileSize, yCoordinate * tileSize,
                            this.tileSize, this.tileSize, null);
                    continue;
                }
                if (board.getTileType(xCoordinate, yCoordinate) == Character.getNumericValue('d')) {
                    if ((gameState.getDifficulty() == 1 || gameState.getDifficulty() == 2 || gameState.getDifficulty() == 3)
                            && gameState.getKeysCollected() == 3) {
                        graphics2D.drawImage(boardSpriteManager.getBufferedOpenDoor(), xCoordinate * tileSize, yCoordinate * tileSize,
                                this.tileSize, this.tileSize, null);
                        saveDoor[0] = (xCoordinate);
                        saveDoor[1] = (yCoordinate);
                        doorOpen = true;

                    } else if ((gameState.getDifficulty() == 4 || gameState.getDifficulty() == 5 || gameState.getDifficulty() == 6)
                            && gameState.getKeysCollected() == 5) {
                        graphics2D.drawImage(boardSpriteManager.getBufferedOpenDoor(), xCoordinate * tileSize, yCoordinate * tileSize,
                                this.tileSize, this.tileSize, null);
                        saveDoor[0] = (xCoordinate);
                        saveDoor[1] = (yCoordinate);
                        doorOpen = true;
                    } else if ((gameState.getDifficulty() == 7 || gameState.getDifficulty() == 8 || gameState.getDifficulty() == 9) && gameState.getKeysCollected() == 8) {
                        graphics2D.drawImage(boardSpriteManager.getBufferedOpenDoor(), xCoordinate * tileSize, yCoordinate * tileSize,
                                this.tileSize, this.tileSize, null);
                        saveDoor[0] = (xCoordinate);
                        saveDoor[1] = (yCoordinate);
                        doorOpen = true;
                    } else {
                        graphics2D.drawImage(boardSpriteManager.getBufferedClosedDoor(), xCoordinate * tileSize, yCoordinate * tileSize,
                                this.tileSize, this.tileSize, null);
                    }
                }
            }
        }

        for (FryCook fryCook : fryCooks) {
            graphics2D.drawImage(fryCook.getActiveImage(), fryCook.getX(), fryCook.getY(),
                    this.tileSize, this.tileSize, null);
        }
        graphics2D.drawImage(playerCharacter.getActiveImage(), playerCharacter.getX(), playerCharacter.getY(),
                this.tileSize, this.tileSize, null);
        graphics2D.drawImage(boardSpriteManager.getBufferedPotatoFriend(), potatoFriend.getX(), potatoFriend.getY(),
                this.tileSize, this.tileSize, null);
    }

    /**
     * Checks if a deep fryer is has been triggered at given x,y coordinates.
     *
     * @param xCoordinate - x coordinate of a given position
     * @param yCoordinate - y coordinate of a given position
     * @return True if it has been triggered, False otherwise
     */
    private boolean checkIfDeepFryerTriggered(int xCoordinate, int yCoordinate) {

        for (DeepFryer deepFryer : deepFryers) {
            if (deepFryer.getX() / tileSize == xCoordinate && deepFryer.getY() / tileSize == yCoordinate) {
                return deepFryer.getHasBeenTriggered();
            }
        }
        return true;
    }

    /**
     * Checks if a key is has been collected at given x,y coordinates.
     *
     * @param xCoordinate - x coordinate of a given position
     * @param yCoordinate - y coordinate of a given position
     * @return True if it has been collected, False otherwise
     */
    private boolean checkIfKeyCollected(int xCoordinate, int yCoordinate) {

        for (DoorKey doorKey : doorKeys) {
            if (doorKey.getX() / tileSize == xCoordinate && doorKey.getY() / tileSize == yCoordinate) {
                return doorKey.getHasBeenCollected();
            }
        }
        return true;
    }

    /**
     * Toggles the game screen's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Helpscreen's buttons if equal to "ON". Otherwise, turns them off.
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) pause).setEnabledAndVisibleON();
        } else {
            ((Button) pause).setEnabledAndVisibleOFF();
        }
    }

    /**
     * Creates and paints the game screen's buttons.
     */
    public void buttons() {

        pause = new Button("PAUSE", this, 1035, 360, 160, 80);

    }


    /**
     *  This method gets called when the player presses the pause button in the panel. 
     *  This method pauses the game when the button gets pushed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pressed = e.getActionCommand();
        if (pressed.equals("PAUSE")) {
            togglebuttons("OFF");
            ui.setSize(1024, 768);
            setVisible(false);
            pausescreen.setLabel(); // USE THIS ON WIN AND GAMEOVER SCREEN AFTER WE IMPLEMENT THEM CORRECTLY
            pausescreen.togglebuttons("ON");
            pausescreen.setVisible(true);
            pausescreen.requestFocus();
            setPauseFlag();
        }
    }


    /**
     * Creates a score label which displays a score, a level label which displays the room number,
     * and a timer label which displays the current game play time.
     */
    public void addLabels() {
        scoreL = new Label(" Score: " + gameState.getScore(), this, 1024, 100, 200, 100);
        levelL = new Label(" Room: " + gameState.getLevelNum() + " / 3", this, 1024, 0, 200, 100);
        timerL = new Label(" Time: " + gameState.getTime(), this, 1024, 200, 200, 100);
    }

    /**
     * Sets current game's pause flag to given pause flag.
     *
     * @param pauseFlagFromScreen - True/False value of given pause flag from screen
     */
    private void setPauseFlagFromScreen(boolean pauseFlagFromScreen) {
        this.pauseGameFlag = pauseFlagFromScreen;
    }

    /**
     * Sets current game's pause flag to True.
     */
    public void setPauseFlag() {
        pauseGameFlag = true;
    }

    public void unSetPauseFlag() {
        pauseGameFlag = false;
    }

    /**
     * Gets current game's pause state with given pause flag.
     *
     * @param pause - pause flag
     */
    public void getPause(PauseScreen pause) {
        this.pausescreen = pause;
    }

    /**
     * Gets current game's win state with given win flag.
     *
     * @param win - win flag
     */
    public void getWin(WinScreen win) {
        this.winscreen = win;
    }

    /**
     * Gets current game's lose state with given lose flag.
     *
     * @param lose - lose flag
     */
    public void getLose(LoseScreen lose) {
        this.losescreen = lose;
    }

    /**
     * Sets current game's difficulty state to given difficulty state.
     *
     * @param difficultyState - given difficulty state
     */
    public void setGameDifficultyState(int difficultyState) {
        gameState.setDifficulty(difficultyState);
    }

    /**
     * Initializes new map.
     * - Create new board, door keys, deep fryers, fry cooks, etc
     */
    public void initMap() {
        board = new Board(gameState.getPlayableWidth(), gameState.getPlayableHeight(), gameState.getDifficulty());
        int[] XYTiles = getOpenTileCoordinates();
        potatoFriend = EntityFactory.makePotatoFriend(XYTiles);
        doorKeys = EntityFactory.makeDoorKeys(board.getDoorKeyPositions(), this.tileSize);
        deepFryers = EntityFactory.makeDeepFryers(board.getDeepFryerPositions(), this.tileSize);
        try {
            fryCooks = EntityFactory.makeFryCooks(gameState.getDifficulty(), this.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
            exit(68);
        }
    }


    /**
     * Gets X,Y coordinates of open tile.
     *
     * @return X, Y coordinates of open tile
     */
    private int[] getOpenTileCoordinates() {
        int[] XYTiles = board.getOpenTileCoordinates();
        XYTiles[0] *= tileSize;
        XYTiles[1] *= tileSize;
        return XYTiles;
    }

    public PlayerCharacter getPlayerCharacter(){
        return playerCharacter;
    }

    public Direction getDirection(){
        return direction;
    }

    public Direction getDirectionTemp(){
        return directionTemp;
    }

    public void setDirectionTemp(Direction tmp){
        directionTemp = tmp;
    }

    /**
     * returns the arraylist of frycooks
     * @return   : returns the frycooks
     */
    public ArrayList<FryCook> getFryCooks(){
        return fryCooks;
    }

    /**
     *  Sets the doorOpen variable to true
     */
    public void setDoorOpenTrue(){
        doorOpen = true;
    }

    /**
     *  Sets the nextScreen variable to true
     */
    public void setNextScreenTrue(){
        nextScreen = true;
    }

}
