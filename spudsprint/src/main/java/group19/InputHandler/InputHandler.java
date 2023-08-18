package group19.InputHandler;

import group19.GameLoop.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles the user's keyboard input. Able to return its current Direction,
 * return its current CharacterCommand, and handle character input.
 */
public class InputHandler implements KeyListener {
    private Direction direction = Direction.STILL;
    private CharacterCommand characterCommand = new NoMove();

    /**
     * Gets the direction that the PlayerCharacter is moving.
     *
     * @return current direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Handles the given user input
     *
     * @param keyCode user input
     */
    private void handleCharacterInput(int keyCode) {

        switch (keyCode) {
            case (KeyEvent.VK_W) -> {
                characterCommand = new MoveUp();
                direction = Direction.UP;
            }
            case (KeyEvent.VK_A) -> {
                characterCommand = new MoveLeft();
                direction = Direction.LEFT;
            }
            case (KeyEvent.VK_S) -> {
                characterCommand = new MoveDown();
                direction = Direction.DOWN;
            }
            case (KeyEvent.VK_D) -> {
                characterCommand = new MoveRight();
                direction = Direction.RIGHT;
            }
            default -> {
                characterCommand = new NoMove();
                direction = Direction.STILL;
            }
        }
    }

    /**
     * Stub class required by KeyListener interface
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //stub
    }

    /**
     * Stub class required by KeyListener interface
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case (KeyEvent.VK_W), (KeyEvent.VK_A),
                    (KeyEvent.VK_S), (KeyEvent.VK_D) -> handleCharacterInput(keyCode);
        }
    }

    /**
     * Stub class required by KeyListener interface
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case (KeyEvent.VK_W), (KeyEvent.VK_A),
                    (KeyEvent.VK_S), (KeyEvent.VK_D) -> this.characterCommand = new NoMove();
        }

    }

    /**
     * Gets the current command.
     *
     * @return current characterCommand
     */
    public CharacterCommand getCharacterCommand() {
        return characterCommand;
    }
}