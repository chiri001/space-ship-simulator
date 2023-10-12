//the start button is the button responsible for starting the simulation
//it is a subclass of Mybutton

import java.awt.event.ActionEvent;

public class AlarmButton extends MyButton {

    private MyMap myMap;

    public AlarmButton(String label, MyMap map){
        super(label);
        this.myMap = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(get_button_name().equals("ALARM")) {
            myMap.activate_alarm();
        } else {
            super.actionPerformed(e);
        }
    }

}