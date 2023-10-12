//the start button is the button responsible for starting the simulation
//it is a subclass of Mybutton

import java.awt.event.ActionEvent;

public class ForwardButton extends MyButton {

    private MyMap myMap;

    public ForwardButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals(">>")) {
            //start button should start the simulation
            myMap.forward_simulation();

        } else {
            super.actionPerformed(e);
        }
    }

}