/*
 * Name : Rennie Kipchirchir
 * Assignment one: Buttons and widgets
 * File: Button.java
 * Date modified: 09/22/23
 * 
 * This file contains Button class that is implemented using JFrame framework.
 * It implements a submit button.
 */

import javax.swing.JButton; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


//this class implements a button
public class MyButton extends JButton implements ActionListener{
    
    //constructor
    public MyButton(String label) {
        setText(label);
        addActionListener(this); //adding an action listener
    }

    //include an event listener to track when button is clicked
    public void actionPerformed(ActionEvent e) {
        System.out.println("Submitted Successfully!!");
    }
}
