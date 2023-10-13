/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: StartButton.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for handling when start button is clieked
 */

import java.awt.event.ActionEvent;

public class StartButton extends MyButton {

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

        } else {
            super.actionPerformed(e);
        }
    }

}