package group19.UIScreens;

import group19.GameLoop.GameLoop;
import group19.GameLoop.GameState;
import group19.GameLoop.Music;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates the GUI panels/objects of the game and initializes them.
 */
public class UICreator {

    /**
     * When the constructor is called it creates all the GUI components of the game.
     *
     * @throws IOException throws an exception when an IO error occurs
     */
    public UICreator() throws IOException {
        //Create holding frame.
        JFrame ui = new JFrame();

        ui.setLayout(null);
        ui.setTitle("Spud Sprint");

        //Create all panel objects
        GameState gameState = new GameState();
        HelpScreen helpScreen = new HelpScreen();
        GameLoop game = new GameLoop(ui, gameState);
        PauseScreen pause = new PauseScreen(helpScreen, game, ui, gameState);
        DifficultyScreen diffscreen = new DifficultyScreen(game, ui, pause);
        HomeScreen title = new HomeScreen(helpScreen, diffscreen);
        WinScreen win = new WinScreen(game, ui, gameState);
        LoseScreen lose = new LoseScreen(game, diffscreen, helpScreen, ui, gameState);

        getterSetter(helpScreen, game, pause, diffscreen, title, win, lose);
        setBounds(helpScreen, game, pause, diffscreen, title, win, lose);
        addPanels(ui, helpScreen, game, pause, diffscreen, title, win, lose);
        initGameWindow(ui);
    }

    //Add all panels to the screen.
    private static void addPanels(JFrame ui, HelpScreen helpScreen, GameLoop game, PauseScreen pause, DifficultyScreen diffscreen, HomeScreen title, WinScreen win, LoseScreen lose) {
        ui.add(lose);
        ui.add(win);
        ui.add(pause);
        ui.add(diffscreen);
        ui.add(helpScreen);
        ui.add(game);
        ui.add(title);
        ui.pack();
    }

    //Set bounds of each panel
    private static void setBounds(HelpScreen helpScreen, GameLoop game, PauseScreen pause, DifficultyScreen diffscreen, HomeScreen title, WinScreen win, LoseScreen lose) {
        pause.setBounds(0, 0, 1024, 768);
        title.setBounds(0, 0, 1024, 768);
        helpScreen.setBounds(0, 0, 1024, 768);
        diffscreen.setBounds(0, 0, 1024, 768);
        game.setBounds(0, 0, 1224, 768);
        win.setBounds(0, 0, 1024, 768);
        lose.setBounds(0, 0, 1024, 768);
    }

    //Object getter and setter methods
    private static void getterSetter(HelpScreen helpScreen, GameLoop game, PauseScreen pause, DifficultyScreen diffscreen, HomeScreen title, WinScreen win, LoseScreen lose) {
        helpScreen.setTitle(title);
        diffscreen.setTitle(title);
        pause.setTitle(title);
        win.setTitle(title);
        helpScreen.setPause(pause);
        game.getPause(pause);
        game.getWin(win);
        helpScreen.setLose(lose);
        game.getLose(lose);
        lose.setTitle(title);
        win.setPause(pause);
        lose.setPause(pause);
    }

    /**
     * initialize the game's main holding frame.
     * The music is also created and ran here.
     *
     * @param ui :the UI frame which holds everything, this is used to finish creating the frame.
     */
    public static void initGameWindow(JFrame ui) {
        Music bg = new Music();
        bg.playMusic();
        ui.setDefaultCloseOperation(ui.EXIT_ON_CLOSE);
        ui.setResizable(false);
        ui.setSize(1024, 768);
        ui.setVisible(true);
    }
}


