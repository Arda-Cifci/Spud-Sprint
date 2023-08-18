package group19.GameLoop;

/**
 * Represents the game state such as difficulty, score, win/lose, # of keys collected, time, etc.
 * One can get/set and change such states.
 */
public class GameState {
    //Various gamestate variables used
    private final int playerStartXCoordinate = 94;
    private final int playerStartYCoordinate = 700;
    private final double framesPerSecond = 60;
    private final int gamePixelWidth = 1224;
    private final int gamePixelHeight = 768;
    private final int playableWidth = 32;
    private final int playableHeight = 24;
    long stunTime;
    private boolean playerStun = false;
    private int difficulty = 0;
    private int score = 100;
    private int keysCollected;
    private int levelNum = 1;
    private long time = 0;
    private double totalSecs;
    private double min;
    private double sec;
    private int rounded;

    /**
     * Gets the level of difficulty of this game .
     *
     * @return this game's difficulty.
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Sets this game's difficulty to a given difficulty.
     *
     * @param difficulty - this game's new difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the score of this game .
     *
     * @return this game's score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Decrements this game's score by 60.
     */
    public void decrementScore() {
        this.score -= 90;
    }

    /**
     * Gets the elapsed time of this game .
     *
     * @return elapsed time(minute:second) of this game
     */
    public String getTime() {
        totalSecs = this.time / 1_000_000_000.0; // 1 sec = 1000000000 nanosec
        sec = (totalSecs / 60);
        min = (sec / 60) % 60;
        sec = sec % 60;
        rounded = (int) Math.floor(min);
        String timeString = String.format("%d:%.0f", rounded, sec);
        return timeString;
    }

    /**
     * Increments the elapsed time of this game by 1 second .
     */
    public void incrementTime() {
        this.time = this.time + 1_000_000_000;
    } // increment by 1 second (expressed in nanosec)

    /**
     * Adds 120 score earned from Bonus Reward to total score.
     */
    public void addBonusRewardToScore() {
        this.score += 120;
    }

    /**
     * Adds 60 score earned from Regular Reward to total score.
     */
    public void addRewardToScore() {
        this.score += 60;
    }

    /**
     * Sets the number of keys collected to 0.
     */
    public void resetKeys() {
        this.keysCollected = 0;
    }

    /**
     * Increments the number of keys collected by 1.
     */
    public void incrementKeysCollected() {
        this.keysCollected = this.keysCollected + 1;
    }

    /**
     * Gets the number of keys collected.
     *
     * @return the number of keys collected from this game.
     */
    public int getKeysCollected() {
        return this.keysCollected;
    }

    /**
     * Checks if the player gets hit by the deep fryer.
     *
     * @return True if stunned, False otherwise.
     */
    public boolean isPlayerStunned() {
        checkIfTimeElapsed();
        return playerStun;
    }

    /**
     * Un-stun player after stun time has passed
     */
    private void checkIfTimeElapsed() {
        if ((this.time - stunTime) / 1_000_000_000 >= 100) {
            unStunPlayer();
        }
    }

    /**
     * Creates a new stun player and sets its stun state to True
     * and time to the 0.
     */
    public void stunPlayer() {
        this.playerStun = true;
        this.stunTime = this.time;
    }

    /**
     * Reactivate player by setting this player's stun state to False.
     */
    public void unStunPlayer() {
        this.playerStun = false;
    }

    /**
     * Gets the level of this game.
     *
     * @return the level of this game.
     */
    public int getLevelNum() {
        return this.levelNum;
    }

    /**
     * Sets the level of this game to a new number.
     *
     * @param num - new level of the game
     */
    public void setLevelNum(int num) {
        this.levelNum = num;
    }

    /**
     * Sets the level of this game to 0.
     */
    public void resetLevelNum() {
        this.levelNum = 1;
    }

    /**
     * Resets the score back to 100
     */
    public void resetScore() {
        this.score = 100;
    }

    /**
     * Resets the time back to 0
     */
    public void resetTime() {
        this.totalSecs = 0;
        this.sec = 0;
        this.min = 0;
        this.time = 0;
    }

    /**
     * Checks to see if the the potato friend bonus reward should respawn
     * 
     * @return returns true or false depending on the amount of time passed
     */
    public boolean checkIfPotatoFriendRespawn() {
        return (time / 1_000_000_000) % 500 == 0;
    }

    /**
     * Gets the number of tiles that fits in the width of the gamescreen (32)
     * @return  : returns 32
     */
    public int getPlayableWidth() {
        return playableWidth;
    }

    /**
     * Gets the number of tiles that fits in the height of the gamescreen (24)
     * @return  : returns 24
     */
    public int getPlayableHeight() {
        return playableHeight;
    }

    /**
     * Gets the gamescreen max pixel width, which is 1224
     * @return  :  returns 1224
     */
    public int getGamePixelWidth() {
        return gamePixelWidth;
    }

    /**
     * Gets the gamescreen max pixel height, which is 768
     * @return  :  returns 768
     */
    public int getGamePixelHeight() {
        return gamePixelHeight;
    }

    /**
     * Returns the number of frames per second (60)
     * @return  : returns 60
     */
    public double getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     *  Gets the players x start coordinate
     * @return  : return 94
     */
    public int getPlayerStartXCoordinate() {
        return playerStartXCoordinate;
    }

    /**
     *  Gets the players y start coordinate
     * @return   : returns 700
     */
    public int getPlayerStartYCoordinate() {
        return playerStartYCoordinate;
    }
}
