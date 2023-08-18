package group19.UIScreens;

import javax.swing.*;
import java.awt.*;

/**
 *  This class is for creating the various JLabels that the game uses.
 */
public class Label extends JLabel{
    /**
     * This constructor creates the JLabel with correct options.
     * 
     * @param textInput        : The JLabels initial displayed text.
     * @param panelToAdd       : The Panel the label gets added to.
     * @param x                : the x - coordinate of the label.
     * @param y                : the y - coordinate of the label.
     * @param width            : the width of the JLabel
     * @param height           : the height of the JLabel
     */
    public Label(String textInput, JPanel panelToAdd, Integer x, Integer y, Integer width, Integer height) {
        setText(textInput);
        setOpaque(true);
        setBackground(new Color(170, 92, 28, 255));
        setForeground(Color.white);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setFont(new Font("SansSerif", Font.BOLD, 28));
        setBounds(x, y, width, height);
        panelToAdd.add(this);
    }
}
