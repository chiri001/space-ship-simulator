
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements the rotation aspect of the pointer */
public class AsteroidListener implements ActionListener {
    
    private AsteroidCanvas asteroidCanvas;
    private MyMap myMap;

    /* AsteroidListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    public AsteroidListener(AsteroidCanvas pCanvas, MyMap map) {
        this.asteroidCanvas = pCanvas;
        this.myMap = map;
    }

    /*implements the rotation action but relies on AsteroidCanvas class fn()
    */
    public void actionPerformed(ActionEvent e) {
        asteroidCanvas.updateLocation();
        myMap.repaint(); //redraw the entire map
    }
}
