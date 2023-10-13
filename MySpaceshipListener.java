
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MySpaceshipListener.java
 * Date modified: 10/05/23
 * 
 * This file contains the listener for the MySpaceship class.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements listener for MySpaceship class
 * class is used by timer to achieve desired action
*/
public class MySpaceshipListener implements ActionListener {
    
    private MySpaceship spaceShipCanvas;
    private MyMap myMap;

    /* constructor
    */
    public MySpaceshipListener(MySpaceship pCanvas, MyMap map) {
        this.spaceShipCanvas = pCanvas;
        this.myMap = map;
    }

    /*
    * calls activate alarm when alarm is clicked
    */
    public void actionPerformed(ActionEvent e) {
        spaceShipCanvas.activate_alarm();
        myMap.repaint(); //redraw the entire map
    }
}