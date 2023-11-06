
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
import java.util.ArrayList;

import javax.swing.Timer;
import java.util.Iterator;

//draws a spaceship that can be controlled in the simulation
public class MySpaceship extends SpaceShipCanvas{

    private double xOffset;
    private double yOffset;
    private String name = "Benatar";
    private Color default_color = Color.BLACK;
    private boolean isAlarmActivated = false;
    private int scale = 15;
    private int speed = 1000;
    private Timer alarmTimer;
    private Polygon myShip;
    private MyMap myMap;
    private Double def_xOffset;
    private Double def_yOffset;
    
    private Point ship_tip_coord = new Point(2, 2); /*center(placeholder) */

    //constructor
    //takes an Objectlistener for the ship
    public MySpaceship(double xOffset, double yOffset, MyMap map) {
        super(xOffset, yOffset, map);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.def_xOffset = xOffset;
        this.def_yOffset = yOffset;
        this.myMap = map;
        this.myMap.addMouseListener(this);

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
        return speed;
    }

    public Shape get_position() {
        return myShip;
    }

    public void removeMyListener() {
        myMap.removeMouseListener(this);
    }


    public boolean isWithinMap(int y, int dimaeter){
        return true;//always true since it is the central object
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

        //tip of ship coordinate
        ship_tip_coord = new Point(centerY, centerX - spaceshipHeight / x_divisor);
        myShip = new Polygon(xPoints, yPoints, 7);
        g.setColor(default_color);
        g.fillPolygon(myShip);

        //name of the drawing
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerY - spaceshipLength / x_divisor - textWidth/6; //put text center
        int textY =  centerX - spaceshipHeight / x_divisor + (int) ( 1.5 * fm.getHeight()); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);

    }

    //function sounds the spaceship alarm
    public void sound_alarm() {
        alarmTimer.start(); //start alarm timer
    }

    //function to reset
    public void reset() {
        //reset to initial values
        yOffset = def_yOffset;
        xOffset = def_xOffset;
        reset_alarm();
    }

    public void reset_alarm() {
        alarmTimer.stop(); //stop timer
        reset_color(); //reset spaceship color
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

    //creats a missile drawing and returns the missile created
    public Missile createMissile() {
        Point missileStart = new Point(ship_tip_coord);
        Double missileDirection = -1.0; //this represent change in y coord upwards
        Double missileSpeed = 0.05;

        Missile m1 = new Missile(missileStart, missileDirection, missileSpeed);
        return m1;
    }
    
    //function to handle when drawing is clicked
    public void mouseClicked(MouseEvent e) {
        if(myShip != null && myShip.contains(e.getPoint()))
        {
            ClickPopup popup = new ClickPopup(name, speed, this);
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

    public void set_speed(int speed2) {
        speed = speed2;
    }

    public void set_color(Color selected_color) {
        default_color = selected_color;
    }
}

