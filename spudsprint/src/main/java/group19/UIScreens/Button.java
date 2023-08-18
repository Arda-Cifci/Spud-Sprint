package group19.UIScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *  This class is for creating the various JButtons that the game uses.
 */
public class Button extends JButton{
    /**
     * This constructor creates the JButton with correct options.
     *
     * @param textInput        : The JButton initial displayed text.
     * @param panelToAdd       : The Panel the button gets added to.
     * @param x                : the x - coordinate of the button.
     * @param y                : the y - coordinate of the button.
     * @param width            : the width of the JButton
     * @param height           : the height of the JButton
     */
    public Button(String textInput, JPanel panelToAdd, Integer x, Integer y, Integer width, Integer height) {
        setText(textInput);
        setOpaque(true);
        setBackground(new Color(170, 92, 28, 255));
        setForeground(Color.white);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setFont(new Font("SansSerif", Font.BOLD, 30));
        setBounds(x, y, width, height);
        panelToAdd.add(this);
        addActionListener((ActionListener) panelToAdd);
        setEnabled(true);
        setActionCommand(textInput);
    }

    /**
     * This method sets the button to enabled and visible.
     */
    public void setEnabledAndVisibleON(){
        setEnabled(true);
        setVisible(true);
    }

    /**
     * Thiss method sets the button to not enabled and not visible.
     */
    public void setEnabledAndVisibleOFF(){
        setEnabled(false);
        setVisible(false);
    }
}

