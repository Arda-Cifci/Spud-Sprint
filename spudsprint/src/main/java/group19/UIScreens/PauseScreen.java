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
 * Creates and paints the Pause screen and its buttons.
 * Able to toggle its buttons on and off, paint its buttons, set its score label,
 * add its score label to the GameState, set its pause flag to true, return its pause flag,
 * and set its Homescreen.
 */
public class PauseScreen extends JPanel implements ActionListener{
    //Variables and objects used.
    private final BufferedImage buff;
    private final JFrame ui;
    private final HelpScreen helpS;
    private final GameState gameState;
    private final GameLoop gm;
    private boolean pauseFlag = true;
    private HomeScreen homescreen;
    private JButton resume;
    private JButton mainmenu;
    private JButton help;
    private JLabel scoreL;

    /**
     * Creates the Pausescreen with the given Helpscreen, GameLoop, JFrame, and GameState GUI objects.
     *
     * @param hp   : the helpscreen is passed into this class inorder to switch to the helpscreen from a button press.
     * @param game : used to return back to the gameloop screen.
     * @param ui   : used to set the UI size.
     * @param gs   : used to get the games current state information.
     * @throws IOException throws an exception when an IO error occurs
     */
    public PauseScreen(HelpScreen hp, GameLoop game, JFrame ui, GameState gs) throws IOException {
        //Set parameters into class variables.
        this.gameState = gs;
        this.helpS = hp;
        this.gm = game;
        this.ui = ui;

        setLayout(null);

        //Print background img
        buff = ImageIO.read(getClass().getResourceAsStream("/paused.jpg"));

        //Add buttons
        buttons();
        //Add label
        addScoreLabel();
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
     * Toggles the Pausescreen's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Pausescreen's buttons if the parameter is "ON". Otherwise, turns them off
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) resume).setEnabledAndVisibleON();
            ((Button) mainmenu).setEnabledAndVisibleON();
            ((Button) help).setEnabledAndVisibleON();
        } else {
            ((Button) resume).setEnabledAndVisibleOFF();
            ((Button) mainmenu).setEnabledAndVisibleOFF();
            ((Button) help).setEnabledAndVisibleOFF();
        }
    }


    /**
     * Creates and paints the Pausescreen's buttons.
     */
    public void buttons() {
        resume = new Button("RESUME", this, 400, 410, 200, 100);
        mainmenu = new Button("MAIN MENU", this, 140, 500, 200, 100);
        help = new Button("HELP", this, 660, 500, 200, 100);
    }

    /**
     *  This method gets called when the player presses the resume, mainmenu, and help buttons in the panel. 
     *  This method either opens the help screen, resumes the gamescreen, or returns to main menu depending on what the player presses.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pressed = e.getActionCommand();
        if (pressed.equals("RESUME")) {
            togglebuttons("OFF");

            setVisible(false);
            ui.setSize(1224, 807);
            gm.togglebuttons("ON");
            gm.setVisible(true);
            gm.requestFocus();
            pauseFlag = false;
        }
        else if (pressed.equals("MAIN MENU")){
            togglebuttons("OFF");
            setVisible(false);
            homescreen.togglebuttons("ON");
            homescreen.setVisible(true);
            homescreen.requestFocus();
        }
        else if (pressed.equals("HELP")){
            togglebuttons("OFF");
            setVisible(false);
            helpS.setOption(2);
            helpS.togglebuttons("ON");
            helpS.setVisible(true);
            helpS.requestFocus();
        }
    }

    /**
     * Adds a score label to the Pause screen.
     */
    public void addScoreLabel() {
        scoreL = new Label(" Score: " + gameState.getScore(), this, 400, 200, 200, 100);
    }

    /**
     * Sets the Pausescreen's label to the score from the GameState.
     */
    public void setLabel() {
        scoreL.setText(" Score: " + gameState.getScore());
    }

    /**
     * Gets the pause flag, which is either true or false.
     *
     * @return either true or false
     */
    public boolean getPauseFlag() {
        return pauseFlag;
    }

    /**
     * Set the pausescreen's homescreen object to the parameter title.
     *
     * @param title
     */
    public void setTitle(HomeScreen title) {
        this.homescreen = title;
    }

    /**
     * Sets the Pausescreen's pause flag to true.
     */
    public void setPause() {
        this.pauseFlag = true;
    }

    /**
     * Sets the pause sreen pause flag to false.
     */
    public void setPauseFalse() {
        this.pauseFlag = false;
    }
}