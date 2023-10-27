
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: AsteroidCanvas.java
 * Date modified: 10/05/23
 * 
 * The file handles drawing of the asteroids for program
 */

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import java.awt.event.MouseEvent;

//asteroid class that draws an asteroid
public class AsteroidCanvas implements DrawingCanvas, MouseListener {

    private double xOffset;
    private double yOffset;
    private String name = "Asteroid";
    private double move = 0.05;
    private int scale = 15;
    private double def_xOffset;
    private double def_yOffset;
    private Timer asteroidTimer;
    private int speed = 200;
    private MyMap myMap;
    private int highestY;
    private Polygon asteroid;
    private Color default_color = new Color(65, 30, 3);

    //constructor
    //takes x and y offset values
    public AsteroidCanvas( double xOffset, double yOffset, MyMap map) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.def_xOffset = xOffset;
        this.def_yOffset = yOffset;
        this.myMap = map;
        this.myMap.addMouseListener(this);

        asteroidTimer = new Timer(speed, new AsteroidListener(this, myMap));
    }

    //returns the xOffset
    public double get_xOffset(){
        return this.xOffset;
    }

    //returns the y Offset
    public double get_yOffset(){
        return this.yOffset;
    }

    //returns the name of object
    public String get_name(){
        return name;
    }

    //returns the speed of object
    public int get_speed(){
        return speed;
    }

    public void start(){
        asteroidTimer.start();
    }
    public void stop(){
        asteroidTimer.stop();
    }
    public void forward(int forward){
        asteroidTimer.setDelay(speed / forward);
    }

    //rewinds the asteroid back 2 times the speed
    public void rewind() {
        asteroidTimer.setDelay(speed);
        yOffset += (2 * move);
    }

    public Shape get_position() {
        return asteroid;
    }

    public void removeMyListener() {
        myMap.removeMouseListener(this);
    }

    /* draw 
        * parameters include a 2d graphics and dimensions of drawing canvas
        * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        
        //calculating the base size of asteroid from canvasSize and to scale
        int baseSize = Math.min(canvasSize.width, canvasSize.height);
        int asteroidSize = baseSize /(int) (2 * scale); 

        //positioning on map based on given offset values
        int centerX = (int) (canvasSize.width / xOffset);
        int centerY = (int) (canvasSize.height / yOffset);

        int numSides = 13;
        double theta = 2 * Math.PI / numSides; //getting equal angles/side

        //array to store c and y coordinates
        int[] xPoints = new int[numSides];
        int[] yPoints = new int[numSides];

        // static offsets for each point to keep the shape consistent
        int[] offsets = {
            (int)(0.1 * asteroidSize),
            (int)(0.15 * asteroidSize),
            (int)(0.12 * asteroidSize),
            (int)(0.18 * asteroidSize),
            (int)(0.1 * asteroidSize),
            (int)(0.14 * asteroidSize),
            (int)(0.16 * asteroidSize),
            (int)(0.11 * asteroidSize),
            (int)(0.15 * asteroidSize),
            (int)(0.13 * asteroidSize),
            (int)(0.17 * asteroidSize),
            (int)(0.12 * asteroidSize),
            (int)(0.14 * asteroidSize)
        };

        //finding the x and y positions
        for (int i = 0; i < numSides; i++) {
            double angle = theta * i; //angle for each side
            
            //using the formula r * cos(angle) to find coordinate
            //then adding the center coordinate for displacement
            xPoints[i] = centerX + (int)(Math.cos(angle) * (asteroidSize - offsets[i]));
            yPoints[i] = centerY + (int)(Math.sin(angle) * (asteroidSize - offsets[i]));
        }

        asteroid = new Polygon(xPoints, yPoints, numSides);
        g.setColor(default_color);
        g.fillPolygon(asteroid);
        
        //find the highest y value
        this.highestY = Integer.MAX_VALUE;
        for (int i = 0; i < numSides; i++) {
            int currentY = centerY + (int)(Math.sin(theta * i) * (asteroidSize - offsets[i]));
            if (currentY < highestY) {
                this.highestY = currentY;
            }
        }

        //adding name to drawing ---> depends on Name class
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerX - textWidth / 2;
        int textY = centerY + asteroidSize/2 + fm.getHeight();
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);
    }

    public boolean isWithinMap(int y, int dimaeter){
        int bottomY = y + dimaeter;
        
        if(highestY > bottomY){
            //beyond map boundary
            return false;
        }
        return true;
    }

    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {

        yOffset -= move;
    }

    //the functions resets the location of the asteroid on the map
    public void reset() {
        //reset to default values
        asteroidTimer.setDelay(speed);
        yOffset = def_yOffset;
        xOffset = def_xOffset;
        default_color = new Color(65, 30, 3);
    }

    public void set_speed(int speed2) {
        speed = speed2;
    }

    public void set_color(Color selected_color) {
        default_color = selected_color;
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

    //function to handle when drawing is clicked
    public void mouseClicked(MouseEvent e) {
        if(asteroid != null && asteroid.contains(e.getPoint()))
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
}
