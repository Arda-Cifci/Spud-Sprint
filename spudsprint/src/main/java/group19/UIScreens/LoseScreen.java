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
 * Creates and paints the Lose screen and its buttons.
 */
public class LoseScreen extends JPanel implements ActionListener {
    //Variables and objects used.
    private final BufferedImage buff;
    private final JFrame ui;
    private final GameLoop gm;
    private final HelpScreen h;
    private final GameState gameState;
    private HomeScreen homescreen;
    private JButton replay;
    private JButton mainmenu;
    private JButton help;
    private PauseScreen pause;

    /**
     * Creates a Losescreen with the given GameLoop, Difficultyscreen, Helpscreen, JFrame, and GameState GUI objects.
     *
     * @param game :the main gameloop jpanel is passed into this class in order to set the difficulty and initialize the game.
     * @param df   :the difficulty screen is passed into this class in order to create a new instance of the game.
     * @param help :The helpscreen is passed into this class inorder to switch to the helpscreen from a button press.
     * @param ui   :ui is passed into this class to correct the screens height and width.
     * @param gs   :The games current state is passed into this class to create a new instance of the game.
     * @throws IOException
     */
    public LoseScreen(GameLoop game, DifficultyScreen df, HelpScreen help, JFrame ui, GameState gs) throws IOException {
        //Set parameters into class variables.
        this.gameState = gs;
        this.gm = game;
        this.h = help;
        this.ui = ui;

        setLayout(null);

        //Paint background img
        buff = ImageIO.read(getClass().getResourceAsStream("/gameOver.jpg"));

        //Add buttons
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
     * Toggles the Losescreen's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Losescreen's buttons if equal to "ON". Otherwise, turns them off
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) replay).setEnabledAndVisibleON();
            ((Button) mainmenu).setEnabledAndVisibleON();
            ((Button) help).setEnabledAndVisibleON();
        } else {
            ((Button) replay).setEnabledAndVisibleOFF();
            ((Button) mainmenu).setEnabledAndVisibleOFF();
            ((Button) help).setEnabledAndVisibleOFF();
        }
    }

    /**
     * Creates and paints the Losescreen's buttons.
     */
    public void buttons() {
        replay = new Button("REPLAY", this, 140, 400, 200, 100);
        mainmenu = new Button("MAIN MENU", this, 400, 400, 200, 100);
        help = new Button("HELP", this, 660, 400, 200, 100);
    }


    /**
     *  This method gets called when the player presses the replay, mainmenu, and help buttons in the panel. 
     *  This method either opens the help screen, new gamescreen screen, or returns to main menu depending on what the player presses.
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
            if (gameState.getDifficulty() == 3 || gameState.getDifficulty() == 2 || gameState.getDifficulty() == 1) {
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
        } else if (pressed.equals("MAIN MENU")) {
            togglebuttons("OFF");
            setVisible(false);
            homescreen.togglebuttons("ON");
            homescreen.setVisible(true);
            homescreen.requestFocus();
        } else if (pressed.equals("HELP")) {
            togglebuttons("OFF");
            setVisible(false);
            h.setOption(3);
            h.togglebuttons("ON");
            h.setVisible(true);
            h.requestFocus();
        }
    }

    /**
     * Set the Losescreen's homescreen object to the parameter title.
     *
     * @param title title to set
     */
    public void setTitle(HomeScreen title) {
        this.homescreen = title;
    }

    /**
     * Set the Losescreen's pause screen object to the parameter ps.
     *
     * @param ps pausescreen to set
     */
    public void setPause(PauseScreen ps) {
        this.pause = ps;
    }
}