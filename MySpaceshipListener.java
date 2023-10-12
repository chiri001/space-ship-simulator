import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements the rotation aspect of the pointer */
public class MySpaceshipListener implements ActionListener {
    
    private MySpaceship spaceShipCanvas;
    private MyMap myMap;

    /* ShipListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    public MySpaceshipListener(MySpaceship pCanvas, MyMap map) {
        this.spaceShipCanvas = pCanvas;
        this.myMap = map;
    }

    /*implements the rotation action but relies on SpaceShipCanvas class fn()
    */
    public void actionPerformed(ActionEvent e) {
        spaceShipCanvas.activate_alarm();
        myMap.repaint(); //redraw the entire map
    }
}