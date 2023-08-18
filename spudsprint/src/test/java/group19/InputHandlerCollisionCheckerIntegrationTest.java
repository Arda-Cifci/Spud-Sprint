package group19;

import group19.Board.Board;
import group19.Entities.PlayerCharacter;
import group19.GameLoop.CollisionCheck;
import group19.InputHandler.CharacterCommand;
import group19.InputHandler.InputHandler;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.lang.System.exit;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InputHandlerCollisionCheckerIntegrationTest {
    private Board board;
    private CollisionCheck collisionCheck;

    private PlayerCharacter playerCharacter;
    private InputHandler inputHandler;
    private JFrame testFrame;
    @Before
    public void CollisionCheckTest() {
        board = new Board(32,24, 1);
        collisionCheck = new CollisionCheck(32);
        inputHandler = new InputHandler();
        testFrame = new JFrame();
        inputHandler = new InputHandler();
        testFrame.addKeyListener(inputHandler);
        testFrame.setFocusable(true);
        testFrame.requestFocus();
    }

    @Test
    public void testPlayerMovesUpThenCollides() {
        try {
            playerCharacter = new PlayerCharacter(32, 20);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'W'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertTrue(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
        command.execute(playerCharacter);
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'W'));
        assertFalse(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
    }

    @Test
    public void testPlayerMovesDownThenCollides() {
        try {
            playerCharacter = new PlayerCharacter(64, 700);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertTrue(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
        command.execute(playerCharacter);
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S'));
        assertFalse(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
    }

    @Test
    public void testPlayerMovesLeftThenCollides() {
        try {
            playerCharacter = new PlayerCharacter(26, 704);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertTrue(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
        command.execute(playerCharacter);
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A'));
        assertFalse(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
    }

    @Test
    public void testPlayerMovesRightThenCollides() {
        try {
            playerCharacter = new PlayerCharacter(960, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'D'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertTrue(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
        command.execute(playerCharacter);
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'D'));
        assertFalse(collisionCheck.checkIfEntityCanMove(inputHandler.getDirection(), playerCharacter, board));
    }
}
