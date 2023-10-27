
import java.awt.*;

import javax.swing.Timer;

//pointer hand to simulate a rotating radar
class PointerCanvas implements DrawingCanvas {

    private double pointerAngle = 0; //variable to track angle of rotation
    private double xOffset = 2;
    private double yOffset = 2;
    private int padding = 15;
    private Timer ptimer;
    private MyMap myMap;

    public PointerCanvas(MyMap map) {
        this.myMap = map;
        ptimer = new Timer(50, new PointerListener(this, myMap));
    }

    public double get_xOffset(){
        return this.xOffset;
    }
    public double get_yOffset(){
        return this.yOffset;
    }
    public String get_name(){
        return "";
    }
    public int get_speed(){
        return 0;
    }

    public void start(){
        ptimer.start();
    }
    public void stop(){
        ptimer.stop();
    }
    public void forward(int forward){
    }
    public void rewind(){
    }
    public void move_item(){
    }

    public void set_speed(int speed2) {
    }

    public void set_color(Color selected_color) {
    }

    public boolean isWithinMap(int y, int dimaeter){
        return true; //always true
    }

    public Shape get_position() {
        return null;
    }
    public void removeMyListener() {
    }

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //x and y coordinates and diameterof the circle map
        int diameter = Math.min(canvasSize.width, canvasSize.height) - padding;
        int x = (canvasSize.width - diameter) / 2;
        int y = (canvasSize.height - diameter) / 2;
        

        //center of the circle map should be starting point for pointer(x + r)
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;

        //end of the circle map using the formula x = r * cos(angle)
        //using angles so that it is recalculated to simulate rotation of ptr
        int endX = centerX + (int) (diameter / 2 * Math.cos(pointerAngle - Math.PI / 2));
        int endY = centerY + (int) (diameter / 2 * Math.sin(pointerAngle - Math.PI / 2));
        
        g.setColor(Color.green);
        g.drawLine(centerX, centerY, endX, endY);
    }

    /* updatePointer
     * parameters none
     * returns nothing
     * The function updates the pointer angle every time it is called
    */
    public void updatePointer() {
        pointerAngle += Math.toRadians(2); //update angle by 2 degrees
    }

    public void reset() {
    }
    public void move_item(String direction) {
    }
}