package group19.GameLoop;

import group19.UIScreens.UICreator;

import java.io.IOException;

/**
 * The main class of this game.
 */
public class Main {
    /**
     * Creates a UICreator, which starts the game and all its components.
     *
     * @param args the command line arguments
     * @throws IOException throws an exception when an IO error occurs
     */
    public static void main(String[] args) throws IOException {
        
        new UICreator();
    }
}