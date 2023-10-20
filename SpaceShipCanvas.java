
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: SpaceShipCanvas.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for drawing a spaceship
 */

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import java.awt.event.MouseEvent;

//SpaceShipCanvas Class that draws a spaceship
public class SpaceShipCanvas implements DrawingCanvas, MouseListener {

    private double xOffset;
    private double yOffset;
    private String name = "Spaceship";
    private ObjectListener lstn;
    private double move = 0.02;
    private int speed = 1000;
    private int scale = 15;
    private double def_xOffset;
    private double def_yOffset;
    private Timer shipTimer;
    private MyMap myMap;

    public SpaceShipCanvas(ObjectListener lstn, double xOffset, double yOffset, MyMap map) {
        this.lstn = lstn;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.def_xOffset = xOffset;
        this.def_yOffset = yOffset;
        this.myMap = map;

        shipTimer = new Timer(speed, new ShipListener(this, myMap));
    }

    public double get_xOffset(){
        return this.xOffset;
    }
    public double get_yOffset(){
        return this.yOffset;
    }
    public String get_name(){
        return name;
    }
    public int get_speed(){
        return speed;
    }

    public void set_offset(double xOffset, double yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void start() {
        shipTimer.start();
    }
    public void stop() {
        shipTimer.stop();
    }
    public void forward(int forward){
        shipTimer.setDelay(speed/forward);
    }

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        int baseSize = Math.min(canvasSize.width, canvasSize.height);

        //length of spaceship landing base
        int spaceshipLength = baseSize / scale; 
        
        //height for the main body for the spaceship (taller)
        int spaceshipHeight = spaceshipLength / 2;

        //height of the side compartments for a spaceship(shorter sides)
        int smallTriangleHeight = spaceshipHeight / 3; 

        //coordinates to put drawing on map (center of ship position)
        int centerX = (int) (canvasSize.height / yOffset);
        int centerY = (int) (canvasSize.width / xOffset);
        int x_divisor = 2;

        int[] yPoints = {
            centerX + spaceshipHeight / x_divisor,//base level
            //top of left compartment
            centerX + spaceshipHeight / x_divisor - smallTriangleHeight,

            centerX - spaceshipHeight / x_divisor,//height of ship

            //top of right compartment
            centerX + spaceshipHeight / x_divisor - smallTriangleHeight,
            
            centerX + spaceshipHeight / x_divisor, //base level
            centerX + spaceshipHeight / x_divisor, //bottom of right
            centerX + spaceshipHeight / x_divisor //bottom of left
        };

        int[] xPoints = {
            centerY - spaceshipLength / x_divisor, //most left vertex
            centerY - spaceshipLength / 4, //base of left side
            centerY, //top of main compartment
            centerY + spaceshipLength / 4, //base of right side
            centerY + spaceshipLength / x_divisor, //most right vertex
            centerY + spaceshipLength / 6, //bottom of right side
            centerY - spaceshipLength / 6 //bottom of left side
        };

        g.setColor(Color.black);
        g.fillPolygon(xPoints, yPoints, 7);

        // this.x = 
        // this.y = 

        //draw the name of drawing
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        //find most left of the ship
        int textX = centerY - spaceshipLength / x_divisor - textWidth/6; //put text center
        //find the base level of ship and add some distance down
        int textY =  centerX - spaceshipHeight / x_divisor + (int) ( 1.8 * fm.getHeight()); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);
    }

    //updates location of the planet
    public void updateLocation() {
        yOffset -= move;
    }

    //rewinds planet movement
    public void rewind() {
        shipTimer.setDelay(speed);
        yOffset += 2 * move;
    }

    //the functions resets the location of the plaent on the map
    public void reset() {
        //reset to initial values
        shipTimer.setDelay(speed);
        yOffset = def_yOffset;
        xOffset = def_xOffset;
    }

    //moves the drawing in the direction passed
    //parameter is the direction to move the item on the map
    public void move_item(String direction) {

        if(direction.equals("LEFT")) {
            //adjust to left by move
            xOffset -= move;
        } 
        else if(direction.equals("UP")){
            //adjust to up by move
            yOffset -= move;
        }
        else if(direction.equals("RIGHT")){
            //adjust to right by move
            xOffset += move;
        }
        else if(direction.equals("DOWN")){
            //adjust to down by move
            yOffset += move;
        }
        
    }

    // //function to check whether the drawing is within map boundary
    // public boolean isWithinMap(int x, int y, int width, int height) {

    //     return this.x + this.width > x &&
        
    // }

    //function to handle when drawing is clicked
    public void mouseClicked(MouseEvent e) {
        ScreenPosition obj = new ScreenPosition();
        Color clickedObj = obj.getPixelColor(e.getXOnScreen(), e.getYOnScreen());

        if(clickedObj.equals(Color.black)) {
            if(lstn != null){
                //calls object listener to draw object on display box
                lstn.onObjectClicked(this);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
}