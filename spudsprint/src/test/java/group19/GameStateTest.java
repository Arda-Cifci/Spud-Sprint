package group19;

import group19.GameLoop.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;



public class GameStateTest {
    private GameState gameState;
    
    @Before
    public void setupGameState() {
        gameState = new GameState();
    }

    @Test
    public void testGetDifficulty() {
        int expected = 0;
        assertEquals(expected, gameState.getDifficulty());
    }

    @Test
    public void testSetDifficulty() {
        int expected = 3;
        gameState.setDifficulty(3);
        assertEquals(expected, gameState.getDifficulty());
    }

    @Test
    public void testGetScore() {
        int expected = 100;
        assertEquals(expected, gameState.getScore());
    }

    @Test
    public void testDecrementScore() {
        int expected = 10;
        gameState.decrementScore();
        assertEquals(expected, gameState.getScore());
    }

    @Test
    public void testGetTime() {
        String expected = "0:0";
        assertEquals(expected, gameState.getTime());
    }

    @Test
    public void testGetTime2() {
        String expected = "0:1";
        for(int i = 0; i < 50; i++){
            gameState.incrementTime();
        }
        assertEquals(expected, gameState.getTime());
    }

    @Test
    public void testAddBonusRewardToScore() {
        int expected = 220;
        gameState.addBonusRewardToScore();
        assertEquals(expected, gameState.getScore());
    }

    @Test
    public void testAddRewardToScore() {
        int expected = 160;
        gameState.addRewardToScore();
        assertEquals(expected, gameState.getScore());
    }

    @Test
    public void testResetKeys() {
        int expected = 0;
        gameState.resetKeys();
        assertEquals(expected, gameState.getKeysCollected());
    }

    @Test
    public void testIncrementKeysCollected() {
        int expected = 1;
        gameState.incrementKeysCollected();
        assertEquals(expected, gameState.getKeysCollected());
    }

    @Test
    public void testIsPlayerStunnedFalse() {
        boolean expected = false;
        assertEquals(expected, gameState.isPlayerStunned());
    }

    @Test
    public void testStunPlayer() {
        boolean expected = true;
        gameState.stunPlayer();
        assertEquals(expected, gameState.isPlayerStunned());
    }

    @Test
    public void testCheckIfTimeElapsedFalse() {
        boolean expected = true;
        gameState.stunPlayer();
        for(int i = 0; i < 50; i++){
            gameState.incrementTime();
        }
        assertEquals(expected, gameState.isPlayerStunned());
    }

    @Test
    public void testCheckIfTimeElapsedTrue() {
        boolean expected = false;
        gameState.stunPlayer();
        for(int i = 0; i < 200; i++){
            gameState.incrementTime();
        }
        assertEquals(expected, gameState.isPlayerStunned());
    }

    @Test
    public void testUnstunPlayer() {
        boolean expected = false;
        gameState.stunPlayer();
        gameState.unStunPlayer();
        assertEquals(expected, gameState.isPlayerStunned());
    }

    @Test
    public void testGetLevelNum() {
        int expected = 1;
        assertEquals(expected, gameState.getLevelNum());
    }

    @Test
    public void testSetLevelNum() {
        int expected = 3;
        gameState.setLevelNum(3);
        assertEquals(expected, gameState.getLevelNum());
    }

    @Test
    public void testResetLevelNum() {
        int expected = 1;
        gameState.setLevelNum(3);
        gameState.resetLevelNum();
        assertEquals(expected, gameState.getLevelNum());
    }

    @Test
    public void testResetScore() {
        int expected = 100;
        gameState.addBonusRewardToScore();
        gameState.resetScore();
        assertEquals(expected, gameState.getScore());
    }

    @Test
    public void testResetTime() {
        String expected = "0:0";
        
        for(int i = 0; i < 50; i++){
            gameState.incrementTime();
        }
        gameState.resetTime();

        assertEquals(expected, gameState.getTime());
    }

    @Test
    public void testCheckIfPotatoFriendRespawnFALSE() {
        boolean expected = false;
        
        for(int i = 0; i < 50; i++){
            gameState.incrementTime();
        }

        assertEquals(expected, gameState.checkIfPotatoFriendRespawn());
    }

    @Test
    public void testCheckIfPotatoFriendRespawnTRUE() {
        boolean expected = true;
        
        for(int i = 0; i < 1000; i++){
            gameState.incrementTime();
        }

        assertEquals(expected, gameState.checkIfPotatoFriendRespawn());
    }
    
    @Test
    public void testGetPlayableWidth() {
        int expected = 32;
        assertEquals(expected, gameState.getPlayableWidth());
    }

    @Test
    public void testGetPlayableHeight() {
        int expected = 24;
        assertEquals(expected, gameState.getPlayableHeight());
    }

    @Test
    public void testGetGamePixelWidth() {
        int expected = 1224;
        assertEquals(expected, gameState.getGamePixelWidth());
    }

    @Test
    public void testGetGamePixelHeight() {
        int expected = 768;
        assertEquals(expected,  gameState.getGamePixelHeight());
    }

    @Test
    public void testGetFramesPerSecond() {
        double expected = 60;
        assertEquals(expected,  gameState.getFramesPerSecond(), 0.01d);
    }

    @Test
    public void testGetPlayerXCoord() {
        int expected = 94;
        assertEquals(expected,  gameState.getPlayerStartXCoordinate());
    }

    @Test
    public void testGetPlayerYCoord() {
        int expected = 700;
        assertEquals(expected,  gameState.getPlayerStartYCoordinate());
    }

}
