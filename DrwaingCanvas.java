import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrwaingCanvas extends JPanel {
    // This is the draw callback
    public void paintComponent (Graphics g) { 
	    super.paintComponent(g); 
        g.drawRect(75, 65, 100, 100);
    }
}
