

import javax.swing.*;

/*class that creates a window frame for the program when called in main */
public class MyFrame extends JFrame {
    /*constructor */
    public MyFrame() {
        setTitle("Spaceship Radar Simulator"); /*sets titile  */

        //set up the window's layout
        setSize(1000, 700);

        /*call a jpanel to open a panel in window */
        MyPanel panel = new MyPanel();
        add(panel);
    }
}