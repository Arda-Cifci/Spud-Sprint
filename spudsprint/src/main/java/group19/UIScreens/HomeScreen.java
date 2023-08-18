package group19.UIScreens;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates and paints the Home screen and its buttons.
 * Able to toggle its buttons on and off and paint its buttons,
 */
public class HomeScreen extends JPanel implements ActionListener{
    //Variables and objects used.
    private final BufferedImage buff;
    private final HelpScreen helpscrn;
    private final DifficultyScreen diffscreen;
    private JButton help;
    private JButton start;
    private JButton exit;

    /**
     * Creates a Homescreen with the given Helpscreen and Difficultyscreen objects for the buttons.
     *
     * @param helpscreen : both parameters are used inside the buttons to swtich screens.
     * @param diffscreen
     * @throws IOException
     */
    public HomeScreen(HelpScreen helpscreen, DifficultyScreen diffscreen) throws IOException {
        this.diffscreen = diffscreen;
        this.helpscrn = helpscreen;
        setLayout(null);

        //Paint background img.
        buff = ImageIO.read(getClass().getResourceAsStream("/Spud_Sprint.png"));
       
        //add buttons.
        buttons();
        setFocusable(true);
        setVisible(true);
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
     * Toggles the Homescreen's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Homescreen's buttons if equal to "ON". Otherwise, turns them off.
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) help).setEnabledAndVisibleON();
            ((Button) start).setEnabledAndVisibleON();
            ((Button) exit).setEnabledAndVisibleON();
        } else {
            ((Button) help).setEnabledAndVisibleOFF();
            ((Button) start).setEnabledAndVisibleOFF();
            ((Button) exit).setEnabledAndVisibleOFF();
        }
    }

    /**
     * Creates and paints the Homescreen's buttons.
     */
    public void buttons() {
        help = new Button("HELP", this, 730, 300, 200, 100);
        start = new Button("START", this, 730, 150, 200, 100);
        exit = new Button("EXIT", this, 730, 450, 200, 100);
    }

    /**
     *  This method gets called when the player presses the help,start, and exit buttons in the panel. 
     *  This method either opens the help screen, difficulty screen or exits the game depending on what the player presses.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pressed = e.getActionCommand();

        if (pressed.equals("HELP")) {
            togglebuttons("OFF");

            setVisible(false);
            helpscrn.setOption(1);
            helpscrn.togglebuttons("ON");
            helpscrn.setVisible(true);
            helpscrn.requestFocus();
        }
        else if (pressed.equals("START")){
            togglebuttons("OFF");
            setVisible(false);
            diffscreen.togglebuttons("ON");
            diffscreen.setVisible(true);
            diffscreen.requestFocus();
        }
        else if (pressed.equals("EXIT")){
            System.exit(0);
        }
    }
}