package group19.UIScreens;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates and paints the Help screen and its buttons.
 * Able to toggle its buttons on and off, paint its buttons, and set its
 * option, Homescreen, Pausescreen, and Losescreen.
 */
public class HelpScreen extends JPanel implements ActionListener {
    //Variables and objects used.
    private final BufferedImage buff;
    private int option;
    private HomeScreen homescreen;
    private PauseScreen pause;
    private LoseScreen lose;
    private JButton back;

    /**
     * Creates and paints the Helpscreen and it's correpsonding buttons.
     *
     * @throws IOException
     */
    public HelpScreen() throws IOException {
        setLayout(null);

        // Paint background img.
        buff = ImageIO.read(getClass().getResourceAsStream("/Spud_Sprint_Help_Screen.jpg"));

        // add buttons.
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
     * Toggles the Helpscreen's buttons if the parameter is "ON". Otherwise, turns them off.
     *
     * @param onoff toggles the Helpscreen's buttons if equal to "ON". Otherwise, turns them off.
     */
    public void togglebuttons(String onoff) {
        if (onoff == "ON") {
            ((Button) back).setEnabledAndVisibleON();
        } else {
            ((Button) back).setEnabledAndVisibleOFF();
        }
    }

    /**
     * Creates and paints the Helpscreen's buttons.
     */
    public void buttons() {
        //Create back button
        back = new Button("RETURN", this, 25, 600, 200, 100);
    }

    /**
     *  This method gets called when the player presses the back button in the panel. 
     *  This method returns the player to the previous panel they came from.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pressed = e.getActionCommand();
        if (pressed.equals("RETURN")) {
            if (option == 1) {
                togglebuttons("OFF");
                setVisible(false);
                homescreen.togglebuttons("ON");
                homescreen.setVisible(true);
                homescreen.requestFocus();
            }

            if (option == 2) {
                togglebuttons("OFF");
                setVisible(false);
                pause.togglebuttons("ON");
                pause.setVisible(true);
                pause.requestFocus();
            }

            if (option == 3) {
                togglebuttons("OFF");
                setVisible(false);
                lose.togglebuttons("ON");
                lose.setVisible(true);
                lose.requestFocus();
            }
        }
    }


    /**
     * Sets the Helpscreen's option to the parameter a.
     *
     * @param a option to set
     */
    public void setOption(int a) {
        this.option = a;
    }

    /**
     * Set the Helpscreens's homescreen object to the parameter title.
     *
     * @param title : title to set
     */
    public void setTitle(HomeScreen title) {
        this.homescreen = title;
    }

    /**
     * Sets the Helpscreen's pausescreen object to parameter pause.
     *
     * @param pause pause to set
     */
    public void setPause(PauseScreen pause) {
        this.pause = pause;
    }

    /**
     * Sets the Helpscreen's losescreen object to paramter lose.
     *
     * @param lose lose to set
     */
    public void setLose(LoseScreen lose) {
        this.lose = lose;
    }
}