/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Main.java
 * Date modified: 09/29/23
 * 
 * This file contains Main class. The class calls a function that starts the 
 * entire program.
 */

import java.awt.Dimension;

import javax.swing.JFrame;

/*main class that starts the program */
public class Main extends JFrame{
    public static void main(String [] args){
        new Main();
    }

    public Main() {
        /*sets titile  of window*/
        setTitle("Spaceship Radar Simulator"); 

        //set up the window's layout
        setSize(800, 700);

        //setting a minmum screen to limit too much resizing of screen
        setMinimumSize(new Dimension(600, 500)); 

        /*ensure the program closes on exit */
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*call a jpanel to open a panel in window */
        MyPanel panel = new MyPanel();
        add(panel);

        /*make the frame visible */
        setVisible(true);
    }
}