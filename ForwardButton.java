/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Forward.java
 * Date modified: 10/05/23
 * 
 * The file handles the event that happens when forward button is clicked
 */

import java.awt.event.ActionEvent;

//class implements forward action
//subclass of MyButton
public class ForwardButton extends MyButton {

    private MyMap myMap;

    public ForwardButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals(">>")) {
            myMap.forward_simulation();

        } else {
            super.actionPerformed(e); //default Mybutton handler
        }
    }

}