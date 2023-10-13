/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: AsteroidListener.java
 * Date modified: 10/05/23
 * 
 * The file handles listener for AsteroidCanvas
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*handles update of location*/
public class AsteroidListener implements ActionListener {
    
    private AsteroidCanvas asteroidCanvas;
    private MyMap myMap;

    /* constructor
    */
    public AsteroidListener(AsteroidCanvas pCanvas, MyMap map) {
        this.asteroidCanvas = pCanvas;
        this.myMap = map;
    }

    /*implements movement of the asteroid but relies on Asteroid Canvas class
    */
    public void actionPerformed(ActionEvent e) {
        asteroidCanvas.updateLocation();
        myMap.repaint(); //redraw the entire map
    }
}
