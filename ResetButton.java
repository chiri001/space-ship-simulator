/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ResetButton.java
 * Date modified: 10/05/23
 * 
 * The file handles alarm button
 */

import java.awt.event.ActionEvent;

//class responsible for alarm button
//subclass for MyButoon
public class ResetButton extends MyButton {

    private MyMap myMap; //map of the program

    public ResetButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }


    //function tells myspaceship to acrivate alarm.
    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("RESET")) {
            myMap.reset_simulation();
        } else {
            super.actionPerformed(e); //call default listener for MyButton
        }
    }

}