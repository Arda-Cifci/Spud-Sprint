package group19;

import group19.GameLoop.*;
import org.junit.Test;

import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Before;

import java.util.ArrayList;
import group19.Entities.FryCook;

import static org.junit.Assert.*;

public class GameLoopTest {
    private GameLoop gl;
    private GameState gs;


    @Before
    public void setupGameLoopTest() throws IOException{
        JFrame frame = new JFrame();
        gs = new GameState();
        gl = new GameLoop(frame, gs);
        gs.setDifficulty(1);
    }

    @Test
    public void testReset() {
        gl.initMap();
        boolean actual = false;
        gs.addRewardToScore();
        gs.setLevelNum(6);;
        gl.reset();

        if(gs.getScore() == 100 && gs.getLevelNum() == 1){
                actual = true;
        }
        assertEquals(true, actual);
    }

    @Test
    public void testEntityandKeyReset() {
        gl.initMap();
        boolean actual = false;
        gl.getPlayerCharacter().setX(100);
        gl.getPlayerCharacter().setY(100);

        gl.resetEntityAndKey();

        if(gl.getPlayerCharacter().getX() == 94 && gl.getPlayerCharacter().getY() == 700){
                actual = true;
        }
        assertEquals(true, actual);
    }

    @Test
    public void testDirectionTemp() {
        gl.initMap();
        gl.setDirectionTemp(Direction.UP);
        assertEquals(Direction.UP, gl.getDirectionTemp());
    }

    @Test
    public void testgetFryCooks() {
        gl.initMap();
        ArrayList<FryCook> frycooks = gl.getFryCooks();
        assertNotEquals(null, frycooks);
    }

    @Test
    public void testnextGameScreenNonFinish() {
        int expected = 2;
        int expected2 = 2;
        gl.initMap();
      
        gs.setDifficulty(1);
        gl.setDoorOpenTrue();
        gl.setNextScreenTrue();

        gl.nextGameScreen();

        assertEquals(expected, gs.getDifficulty());
        assertEquals(expected2, gs.getLevelNum());
    }

    @Test
    public void testnextGameScreenNonFinish2() {
        int expected = 3;
        int expected2 = 3;
        gl.initMap();
      
        gs.setDifficulty(2);
        gl.setDoorOpenTrue();
        gl.setNextScreenTrue();

        gl.nextGameScreen();

        assertEquals(expected, gs.getDifficulty());
        assertEquals(expected2, gs.getLevelNum());
    }

    @Test
    public void testnextGameScreenNonFinish3() {
        int expected = 5;
        int expected2 = 2;
        gl.initMap();
      
        gs.setDifficulty(4);
        gl.setDoorOpenTrue();
        gl.setNextScreenTrue();

        gl.nextGameScreen();

        assertEquals(expected, gs.getDifficulty());
        assertEquals(expected2, gs.getLevelNum());
    }

    @Test
    public void testnextGameScreenNonFinish4() {
        int expected = 6;
        int expected2 = 3;
        gl.initMap();
      
        gs.setDifficulty(5);
        gl.setDoorOpenTrue();
        gl.setNextScreenTrue();

        gl.nextGameScreen();

        assertEquals(expected, gs.getDifficulty());
        assertEquals(expected2, gs.getLevelNum());
    }

    @Test
    public void testnextGameScreenNonFinish5() {
        int expected = 8;
        int expected2 = 2;
        gl.initMap();
      
        gs.setDifficulty(7);
        gl.setDoorOpenTrue();
        gl.setNextScreenTrue();

        gl.nextGameScreen();

        assertEquals(expected, gs.getDifficulty());
        assertEquals(expected2, gs.getLevelNum());
    }

    @Test
    public void testnextGameScreenNonFinish6() {
        int expected = 9;
        int expected2 = 3;
        gl.initMap();
      
        gs.setDifficulty(8);
        gl.setDoorOpenTrue();
        gl.setNextScreenTrue();

        gl.nextGameScreen();

        assertEquals(expected, gs.getDifficulty());
        assertEquals(expected2, gs.getLevelNum());
    }
}
