/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ZoomButton.java
 * Date modified: 10/12/23
 * 
 * This file contains the implementaion of the zooming buttons.
 */

import java.awt.event.ActionEvent;

public class ZoomButton extends MyButton {

    private MyMap myMap;

    //constructor for rewindButton
    //takes label and Mymap instance
    public ZoomButton(String label, MyMap map){
        super(label); //set label using class inheriting from
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("Z(+)")) {
            myMap.zoomIn();

        } else if(get_button_name().equals("Z(-)")) {
            myMap.zoomOut();

        }
        else {
            super.actionPerformed(e);
        }
    }

}