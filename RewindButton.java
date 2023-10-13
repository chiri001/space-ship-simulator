/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: RewindButton.java
 * Date modified: 10/12/23
 * 
 * This file contains the implementaion of the rewindbutton
 */

import java.awt.event.ActionEvent;

public class RewindButton extends MyButton {

    private MyMap myMap;

    //constructor for rewindButton
    //takes label and Mymap instance
    public RewindButton(String label, MyMap map){
        super(label); //set label using class inheriting from
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("<<")) {
            //rewind the simulation
            myMap.rewind_simulation();

        } else {
            super.actionPerformed(e);
        }
    }

}