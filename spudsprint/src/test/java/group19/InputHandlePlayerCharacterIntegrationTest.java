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
import static org.junit.Assert.assertEquals;

public class InputHandlePlayerCharacterIntegrationTest {

    private PlayerCharacter playerCharacter;
    private InputHandler inputHandler;
    private JFrame testFrame;
    @Before
    public void CollisionCheckTest() {
        inputHandler = new InputHandler();
        testFrame = new JFrame();
        inputHandler = new InputHandler();
        testFrame.addKeyListener(inputHandler);
        testFrame.setFocusable(true);
        testFrame.requestFocus();
    }

    @Test
    public void testPlayerMovesUpOnInput() {
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'W'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        command.execute(playerCharacter);
        assertEquals( 28,playerCharacter.getY());
        assertEquals( 32,playerCharacter.getX());
    }

    @Test
    public void testPlayerMovesDownOnInput() {
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        command.execute(playerCharacter);
        assertEquals( 36,playerCharacter.getY());
        assertEquals( 32,playerCharacter.getX());
    }

    @Test
    public void testPlayerMovesLeftOnInput() {
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        command.execute(playerCharacter);
        assertEquals( 32, playerCharacter.getY());
        assertEquals( 28, playerCharacter.getX());
    }

    @Test
    public void testPlayerMovesRightOnInput() {
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'D'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        command.execute(playerCharacter);
        assertEquals( 32, playerCharacter.getY());
        assertEquals( 36, playerCharacter.getX());
    }

    @Test
    public void testPlayerDoesNotMove() {
        try {
            playerCharacter = new PlayerCharacter(32, 32);
        }
        catch(IOException e) {
            e.printStackTrace();
            exit(0);
        }

        CharacterCommand command = inputHandler.getCharacterCommand();
        command.execute(playerCharacter);
        assertEquals( 32,playerCharacter.getY());
        assertEquals( 32,playerCharacter.getX());
    }

}
