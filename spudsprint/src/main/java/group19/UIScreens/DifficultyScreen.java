package group19.UIScreens;

import group19.GameLoop.GameLoop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class creates and paints the difficulty select screen jpanel and its corresponding buttons.
 */
public class DifficultyScreen extends JPanel implements ActionListener {
    //Variable and objects used.
    private final BufferedImage buff;
    private final JFrame ui;
    private final PauseScreen ps;
    private final GameLoop game;
    protected int start = 0;
    private int difficultyLevel;
    private HomeScreen homescreen;
    private JButton easy;
    private JButton med;
    private JButton hard;
    private JButton back;

    /**
     * This constructor creates the difficulty screen by calling methods to add buttons and printing the background.
     *
     * @param game :the main gameloop jpanel is passed into this class in order to set the difficulty and initialize the game.
     * @param ui   :ui is passed into this class to correct the screens height and width.
     * @param ps   :The pause screen is passed into this class in order to un-pause the game.
     * @throws IOException
     */
    public DifficultyScreen(GameLoop game, JFrame ui, PauseScreen ps) throws IOException {
        //Set parameters into class variables.
        this.game = game;
        this.ui = ui;
        this.ps = ps;

        setLayout(null);

        // Paint background img.
        buff = ImageIO.read(getClass().getResourceAsStream("/chooseDifficulty.jpg"));
       
        //Add buttons.
        buttons();
        setFocusable(true);
        setVisible(false);
    }

    /**
     * Paints the UI screen with the background image.
     *
     * @param g - given graphic
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buff, 0, 0, 1008, 729, this);
    }

    /**
     * Toggles the Difficulty screens's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Losescreen's buttons if equal to "ON". Otherwise, turns them off
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) easy).setEnabledAndVisibleON();
            ((Button) med).setEnabledAndVisibleON();
            ((Button) hard).setEnabledAndVisibleON();
            ((Button) back).setEnabledAndVisibleON();
        } else {
            ((Button) easy).setEnabledAndVisibleOFF();
            ((Button) med).setEnabledAndVisibleOFF();
            ((Button) hard).setEnabledAndVisibleOFF();
            ((Button) back).setEnabledAndVisibleOFF();
        }
    }


    /**
     * Creates and paints the Difficulty screens's buttons.
     */
    public void buttons() {
        //create button blanks.
        easy = new Button("EASY", this, 140, 280, 200, 100);
        med = new Button("MEDIUM", this, 400, 280, 200, 100);
        hard = new Button("HARD", this, 660, 280, 200, 100);
        back = new Button("BACK", this, 60, 600, 200, 100);
    }

    /**
     *  This method gets called when the player presses the easy, med, hard, and back buttons in the panel. 
     *  This method sets the game difficulty and starts the game when the corresponding button gets pressed.
     */
    @Override
    public void actionPerformed (ActionEvent e){
        String pressed = e.getActionCommand();
        if (pressed.equals("EASY")) {
            difficultyLevel = 1;

            togglebuttons("OFF");
            setVisible(false);

            ui.setSize(1224, 807);

            if (start == 0) {
                start++;
                game.setGameDifficultyState(difficultyLevel);
                game.initMap();
                game.startRenderThread();
            } else {
                ps.setPauseFalse();
                game.unSetPauseFlag();
                game.reset();
                game.setGameDifficultyState(difficultyLevel);
                game.initMap();
            }
            game.togglebuttons("ON");
            game.setVisible(true);
            game.requestFocus();
        } else if (pressed.equals("MEDIUM")) {
            difficultyLevel = 4;

            togglebuttons("OFF");
            setVisible(false);

            ui.setSize(1224, 807);
            if (start == 0) {
                start++;
                game.setGameDifficultyState(difficultyLevel);
                game.initMap();
                game.startRenderThread();
            } else {
                ps.setPauseFalse();
                game.unSetPauseFlag();
                game.reset();
                game.setGameDifficultyState(difficultyLevel);
                game.initMap();
            }
            game.togglebuttons("ON");
            game.setVisible(true);
            game.requestFocus();
        } else if (pressed.equals("HARD")) {
            difficultyLevel = 7;

            togglebuttons("OFF");
            setVisible(false);
            ui.setSize(1224, 807);
            if (start == 0) {
                start++;
                game.setGameDifficultyState(difficultyLevel);
                game.initMap();
                game.startRenderThread();
            } else {
                game.reset();
                ps.setPauseFalse();
                game.unSetPauseFlag();
                game.setGameDifficultyState(difficultyLevel);
                game.initMap();
            }
            game.togglebuttons("ON");
            game.setVisible(true);
            game.requestFocus();
        } else if (pressed.equals("BACK")){
            setVisible(false);
            togglebuttons("OFF");
            homescreen.togglebuttons("ON");
            homescreen.setVisible(true);
            homescreen.requestFocus();
        }
    }


    /**
     * Set the Difficulty screen's homescreen object to the parameter title.
     *
     * @param title : title to set
     */
    public void setTitle(HomeScreen title) {
        this.homescreen = title;
    }

}
