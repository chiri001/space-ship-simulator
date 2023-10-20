
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: SpaceDebriListener.java
 * Date modified: 10/12/23
 * 
 * This file implements a listener for the Shipcanvas class
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements the rotation aspect of the pointer */
public class SpaceDebriListener implements ActionListener {
    
    private SpaceDebriCanvas debri;
    private MyMap myMap;

    /* ShipListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    public SpaceDebriListener(SpaceDebriCanvas pCanvas, MyMap map) {
        this.debri = pCanvas;
        this.myMap = map;
    }

    /*implements the rotation action but relies on SpaceShipCanvas class fn()
    */
    public void actionPerformed(ActionEvent e) {
        debri.updateLocation();
        myMap.repaint();
    }
}
