//the start button is the button responsible for starting the simulation
//it is a subclass of Mybutton

import java.awt.event.ActionEvent;

public class RewindButton extends MyButton {

    private MyMap myMap;

    public RewindButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("START")) {
            //start button should start the simulation
            //myMap.rewind_simulation();

        } else {
            super.actionPerformed(e);
        }
    }

}