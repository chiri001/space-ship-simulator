
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MySpaceship.java
 * Date modified: 10/05/23
 * 
 * This file contains the drawing for the main spaceship for the simulation.
 */

import java.awt.*;

import java.awt.event.MouseEvent;

import javax.swing.Timer;

//draws a spaceship that can be controlled in the simulation
public class MySpaceship extends SpaceShipCanvas{

    private double xOffset;
    private double yOffset;
    private String name = "Benatar";
    private Color default_color = Color.BLACK;
    private boolean isAlarmActivated = false;
    private int scale = 15;
    private double speed = 1;
    private Timer alarmTimer;

    //constructor
    //takes an Objectlistener for the ship
    public MySpaceship(ObjectListener lstn, double xOffset, double yOffset, MyMap map) {
        super(lstn, xOffset, yOffset, map);
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        alarmTimer = new Timer(500, new MySpaceshipListener(this));
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
        return 0;
    }

    public void set_offset(double xOffset, double yOffset){
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

        g.setColor(default_color);
        g.fillPolygon(xPoints, yPoints, 7);

        //name of the drawing
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerY - spaceshipLength / x_divisor - textWidth/6; //put text center
        int textY =  centerX - spaceshipHeight / x_divisor + (int) ( 1.5 * fm.getHeight()); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);
    }

    public void sound_alarm() {
        alarmTimer.start();
    }

    public void reset_alarm() {
        alarmTimer.stop();
        reset_color();
    }
    //funtion activates the alarm of the ship when alarm button is clicked
    public void activate_alarm(){
        if(!isAlarmActivated){
            //activate alarm by turning the ship red
            Color translucent_red = new Color(255, 0, 0, 128);
            default_color = translucent_red;
            isAlarmActivated = true;
        } else {
            //turns the ship back to black to mimic alarm blinking
            reset_color();
            isAlarmActivated = false;
        }
    }

    //resets the color of the ship when called
    public void reset_color(){
        default_color = Color.black;
    }

    
    public void mouseClicked(MouseEvent e) {
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

