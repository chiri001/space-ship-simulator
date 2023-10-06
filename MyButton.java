/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyButton.java
 * Date modified: 09/29/23
 * 
 * This file contains MyButton class. The class creates buttons as called
 * from another class
 */

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;


//MyButton class implements a button using a JButton
//class implements actionlistener aspect of the button
public class MyButton extends JButton implements ActionListener{

    private String buttonName;
    
    //constructor
    //takes a string as a parameter
    //creates the label for the button and adds an actionlistener to it
    public MyButton(String label) {
        setText(label);
        this.buttonName = label;
        addActionListener(this); //adding an action listener
    }

    //the function an event listener to track when button is clicked
    //it takes and actionEvent as a parameter
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); //gets label of the string

        //switch case statement to check which button was clicked
        switch(command) {
            case "RESET":
                System.out.println("reset button was clicked");
                break;
            case "NEW PATH":
                System.out.println("new path button was clicked");
                break;
            case "ALARM":
                System.out.println("alarm button was clicked");
                break;
            case "UP":
                System.out.println("up button was clicked");
                break;
            case "DOWN":
                System.out.println("down button was clicked");
                break;
            case "LEFT":
                System.out.println("left button was clicked");
                break;
            case "RIGHT":
                System.out.println("right button was clicked");
                break;
            case "Z(+)":
                System.out.println("zoomin button was clicked");
                break;
            case "Z(-)":
                System.out.println("zoomout button was clicked");
                break;
            case ">>":
                System.out.println("forward button was clicked");
                break;
            case "<<":
                System.out.println("rewind button was clicked");
                break;
        }
    }

    //set_size function that maybe needed if there is need to alter size of
    //button
    public void set_size(int width, int height){
        Dimension btn_dimension = new Dimension(width, height);
        this.setPreferredSize(btn_dimension);
    }

    /*a function that sets the color of the button
     * Takes color as a parameter
     */
    public void set_btn_color(Color color){
        this.setBackground(color); //set background of color
        this.setOpaque(true);//set background to opaque
        this.setBorderPainted(true);//paint the border
    }

    protected String get_button_name() {
        return buttonName;
    }
}

//the start button is the button responsible for starting the simulation
//it is a subclass of Mybutton
class StartButton extends MyButton {

    private MyMap myMap;

    public StartButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("START")) {
            //start button should start the simulation
            myMap.start_simulation();
            //System.out.println("STAART button was clicked");

        } else {
            super.actionPerformed(e);
        }
    }

}

//the start button is the button responsible for starting the simulation
//it is a subclass of Mybutton
class EndButton extends MyButton {

    private MyMap myMap;

    public EndButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("END")) {
            //stop button should start the simulation
            myMap.stop_simulation();

        } else {
            super.actionPerformed(e);
        }
    }

}