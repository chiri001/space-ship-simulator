
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: PlanetListener.java
 * Date modified: 10/12/23
 * 
 * This file contains Planet Canvas listener responsible for moving the planet
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements the rotation aspect of the pointer */
public class PlanetListener implements ActionListener {
    
    private PlanetCanvas planet;
    private MyMap myMap;

    /* 
    * Constructor
    * takes planet and map
    */
    public PlanetListener(PlanetCanvas planet, MyMap map) {
        this.planet = planet;
        this.myMap = map;
    }

    /*
    * implements actions to be performed
    */
    public void actionPerformed(ActionEvent e) {
        planet.updateLocation();
        myMap.repaint(); //redraw the entire map
    }
}