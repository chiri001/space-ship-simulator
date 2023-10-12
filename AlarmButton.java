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
public class AlarmButton extends MyButton {

    private MyMap myMap; //map of the program

    public AlarmButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }


    //function tells myspaceship to acrivate alarm.
    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("ALARM")) {
            myMap.activate_alarm();
        } else {
            super.actionPerformed(e); //call default listener for MyButton
        }
    }

}