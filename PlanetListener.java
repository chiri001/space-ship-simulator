



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements the rotation aspect of the pointer */
public class PlanetListener implements ActionListener {
    
    private PlanetCanvas planet;
    private MyMap myMap;

    /* PointerUpdateListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    public PlanetListener(PlanetCanvas planet, MyMap map) {
        this.planet = planet;
        this.myMap = map;
    }

    /*implements the rotation action but relies on PointerCanvas class fn()
    */
    public void actionPerformed(ActionEvent e) {
        planet.updateLocation();
        myMap.repaint(); //redraw the entire map
    }
}
