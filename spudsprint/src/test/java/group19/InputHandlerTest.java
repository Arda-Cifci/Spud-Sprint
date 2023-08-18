package group19;

import group19.InputHandler.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.event.KeyEvent;
import static org.junit.Assert.assertEquals;

public class InputHandlerTest {
    private InputHandler inputHandler;
    private JFrame testFrame;

    @Before
    public void setInputHandler() {
        testFrame = new JFrame();
        inputHandler = new InputHandler();
        testFrame.addKeyListener(inputHandler);
        testFrame.setFocusable(true);
        testFrame.requestFocus();

    }

    @Test
    public void testWKeyHandler() {
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'W'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertEquals(MoveUp.class, command.getClass());
    }

    @Test
    public void testSKeyHandler() {
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertEquals(MoveDown.class, command.getClass());
    }

    @Test
    public void testAKeyHandler() {
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertEquals(MoveLeft.class, command.getClass());
    }

    @Test
    public void testDKeyHandler() {
        inputHandler.keyPressed(new KeyEvent(testFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'D'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertEquals(MoveRight.class, command.getClass());
    }
    @Test
    public void testNoMoveHandler() {
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertEquals(NoMove.class, command.getClass());
    }

    @Test
    public void testNoMoveKeyReleaseHandler() {
        inputHandler.keyReleased(new KeyEvent(testFrame, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'D'));
        CharacterCommand command = inputHandler.getCharacterCommand();
        assertEquals(NoMove.class, command.getClass());
    }



}
