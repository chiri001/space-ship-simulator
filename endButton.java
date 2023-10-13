/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Endbutton.java
 * Date modified: 10/05/23
 * 
 * The file handles what happens when EndButton is clicked. It is a subclass
 * of MyButton
 */

import java.awt.event.ActionEvent;

//the end button is the button responsible for ending the simulation
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
            //end button should stop the simulation
            myMap.stop_simulation();

        } else {
            super.actionPerformed(e); //call default handler
        }
    }

}