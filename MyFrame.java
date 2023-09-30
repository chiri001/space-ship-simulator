/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyFrame.java
 * Date modified: 09/29/23
 * 
 * This file contains MyFrame class that creates a new frame to hold other
 * items
 */

import javax.swing.*;

/*class that creates a window frame for the program when called in main */
public class MyFrame extends JFrame {
    /*constructor
     * takes no parameter
    */
    public MyFrame() {
        setTitle("Spaceship Radar Simulator"); /*sets titile  of window*/

        //set up the window's layout
        setSize(800, 700);

        /*call a jpanel to open a panel in window */
        MyPanel panel = new MyPanel();
        add(panel);
    }
}