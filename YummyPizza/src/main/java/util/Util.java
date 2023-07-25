package util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Xiang Weng
 * This class is used for window size calculation.
 */
public class Util {
    public static Rectangle getBounds() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        //get Screen Size
        Dimension screenSize = defaultToolkit.getScreenSize();
        // get Screen Insets
        Insets screenInsets = defaultToolkit
                .getScreenInsets(new JFrame().getGraphicsConfiguration());

        // Calculate the size of rectangle for covering the whole screen

        Rectangle rectangle = new Rectangle(screenInsets.left, screenInsets.top,
                screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);

        return rectangle;
    }
}
