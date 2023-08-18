package group19.UIScreens;

import group19.GameLoop.GameLoop;
import group19.GameLoop.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates and paints the Winscreen and its buttons.
 * Able to toggle its buttons on and off, paint its buttons, set its score label,
 * add its score label to the GameState, set its timer label, add its timer label to the GameState,
 * and set its Homescreen.
 */
public class WinScreen extends JPanel implements ActionListener {
    //Variables and objets used.
    private final BufferedImage buff;
    private final JFrame ui;
    private final GameLoop gm;
    private final GameState gameState;
    private HomeScreen homescreen;
    private JButton replay;
    private JButton mainmenu;
    private JLabel scoreL;
    private JLabel timerL;
    private PauseScreen pause;

    /**
     * Creates a new Winscreen with the given GameLoop, Difficultyscreen, JFrame, and GameState.
     *
     * @param game : used to return back to the gameloop screen and restart the game.
     * @param ui   : used for resizeing the screen
     * @param gs   : used to get the games current state in order to reset the game and displaying final winning scores.
     * @throws IOException
     */
    public WinScreen(GameLoop game, JFrame ui, GameState gs) throws IOException {
        //Set parameters into class variables.
        this.gameState = gs;
        this.gm = game;
        this.ui = ui;

        setLayout(null);

        //Print background img.
        buff = ImageIO.read(getClass().getResourceAsStream("/youWin.jpg"));

        //Add buttons
        buttons();
        //Add labels
        addLabels();
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
     * Toggles the Winscreen's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Winscreen's buttons if the parameter is "ON". Otherwise, turns them off
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) replay).setEnabledAndVisibleON();
            ((Button) mainmenu).setEnabledAndVisibleON();
        } else {
            ((Button) replay).setEnabledAndVisibleOFF();
            ((Button) mainmenu).setEnabledAndVisibleOFF();
        }
    }


    /**
     * Creates and paints the Wincreen's buttons.
     */
    public void buttons() {
        replay = new Button("REPLAY", this, 250, 400, 200, 100);
        mainmenu = new Button("MAIN MENU", this, 550, 400, 200, 100);
    }

    /**
     *  This method gets called when the player presses the replay or mainmenu buttons in the panel. 
     *  This method either opens a new gamescreen, or returns to main menu depending on what the player presses.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pressed = e.getActionCommand();
        if (pressed.equals("REPLAY")) {
            togglebuttons("OFF");
            setVisible(false);
            pause.setPauseFalse();
            gm.unSetPauseFlag();
            gm.reset();
            ui.setSize(1224, 807);
            if (gameState.getDifficulty() == 3) {
                gm.setGameDifficultyState(1);
            } else if (gameState.getDifficulty() == 6) {
                gm.setGameDifficultyState(4);
            } else if (gameState.getDifficulty() == 9) {
                gm.setGameDifficultyState(7);
            }
            gm.initMap();
            gm.togglebuttons("ON");
            gm.setVisible(true);
            gm.requestFocus();
        }
        else if (pressed.equals("MAIN MENU")){
            togglebuttons("OFF");
            setVisible(false);
            homescreen.togglebuttons("ON");
            homescreen.setVisible(true);
            homescreen.requestFocus();
        }
    }

    /**
     * Adds a score label to the win screen.
     */
    public void addLabels() {
        scoreL = new Label(" Score: " + gameState.getScore(), this, 200, 200, 200, 100);
        timerL = new Label(" Time: " + gameState.getTime(), this, 600, 200, 200, 100);
    }
  
    /**
     * Set the Winscreens's homescreen object to the parameter title.
     *
     * @param title title to set
     */
    public void setTitle(HomeScreen title) {
        this.homescreen = title;
    }

    /**
     * Sets the Winscreen's score label to the score from the GameState.
     */
    public void setScore() {
        scoreL.setText(" Score: " + gameState.getScore());
    }

    /**
     * Sets the Winscreen's timer label to the timer from the GameState.
     */
    public void setTimer() {
        timerL.setText(" Time: " + gameState.getTime());

    }

    /**
     * Sets the winscreen pausescreen object to the parameter ps.
     *
     * @param ps pausescreen to set
     */
    public void setPause(PauseScreen ps) {
        this.pause = ps;
    }

}