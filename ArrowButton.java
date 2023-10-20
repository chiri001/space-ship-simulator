/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: AlarmButton.java
 * Date modified: 10/05/23
 * 
 * The file handles alarm button
 */

import java.awt.event.ActionEvent;

//class responsible for alarm button
//subclass for MyButoon
public class ArrowButton extends MyButton {

    private MyMap myMap; //map of the program

    public ArrowButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }


    //function tells myspaceship to acrivate alarm.
    @Override
    public void actionPerformed(ActionEvent e) {

        if(this.get_button_name().equals("UP")){
            //move up
            myMap.move_items(this.get_button_name());
        } 
        else if(this.get_button_name().equals("DOWN")){
            //move down
            myMap.move_items(this.get_button_name());
        }
        else  if(this.get_button_name().equals("LEFT")){
            //move left
            myMap.move_items(this.get_button_name());
        }
        else if(this.get_button_name().equals("RIGHT")){
            //move right
            myMap.move_items(this.get_button_name());
        } else {
            super.actionPerformed(e); //call default listener for MyButton
        }
    }

}