
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class that implements the rotation aspect of the pointer */
class PointerListener implements ActionListener {
    
    private PointerCanvas pointerCanvas;
    private MyMap myMap;

    /* PointerUpdateListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    public PointerListener(PointerCanvas pCanvas, MyMap map) {
        this.pointerCanvas = pCanvas;
        this.myMap = map;
    }

    /*implements the rotation action but relies on PointerCanvas class fn()
    */
    public void actionPerformed(ActionEvent e) {
        pointerCanvas.updatePointer();
        myMap.repaint(); //redraw the entire map
    }
}