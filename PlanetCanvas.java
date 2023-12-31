
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: PlanetCanvas.java
 * Date modified: 10/12/23
 * 
 * This file contains Planet Canvas responsible for drawing planets
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Timer;


//planetCanvas Class that draws a DrawingCanvas
public class PlanetCanvas implements DrawingCanvas,  MouseListener{
    private double xOffset;
    private double yOffset;
    private String name = "Planet";
    private int diameter = 0;
    private int x = 0;
    private int y = 0;
    private int population;
    private int scale = 15;
    private double move = 0.05;
    private int speed = 200;
    private double def_xOffset;
    private double def_yOffset;
    private Timer planetTimer;
    private MyMap myMap;
    private int highestY;
    private Color default_color = Color.blue;

    private ArrayList<SateLite> sateLiteList = new ArrayList<>();

    public PlanetCanvas(double xOffset, double yOffset, int satelites, MyMap map) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.def_xOffset = xOffset;
        this.def_yOffset = yOffset;
        this.population = satelites;
        this.myMap = map;
        this.myMap.addMouseListener(this);

        int orbitRadius = diameter;
        double angle = 2 * Math.PI / population; //divide angles equally

        for(int i = 0; i < population; i++) {
            SateLite sl = new SateLite(orbitRadius);
            sl.set_angle(i * angle); //sets angle for each satelite from planet
            sateLiteList.add(sl);
        }

        planetTimer = new Timer(speed, new PlanetListener(this, myMap));
    }


    public double get_xOffset(){
        return this.xOffset;
    }

    public double get_yOffset(){
        return this.yOffset;
    }

    public String get_name(){
        return this.name;
    }

    public int get_speed(){
        return this.speed;
    }

    public void start() {
        planetTimer.start();
    }

    public void stop() {
        planetTimer.stop();
    }

    public void removeMyListener() {
        myMap.removeMouseListener(this);
    }

    public void forward(int forward) {
        planetTimer.setDelay(speed/forward); //update timer speeed
    }

    public Shape get_position() {
        Rectangle2D planetBounds = new Rectangle2D.Double(x, y, diameter, diameter);
        return planetBounds;
    }

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        this.diameter = (Math.min(canvasSize.width, 
                        canvasSize.height) / (int)(0.6 * scale));//planet diameter
        
        //calculating x and y coordinate of plane
        this.x = (int) ((canvasSize.width - diameter) / xOffset);
        this.y = (int) ((canvasSize.height - diameter) / yOffset);

        g.setColor(default_color);
        g.fillOval(x, y, diameter, diameter);
        this.highestY = y;

        int orbitRadius = diameter / 2; //get radius

        for(SateLite sate_lite : sateLiteList) {
            sate_lite.set_centre(orbitRadius, x, y); //radius, centre of circle
            sate_lite.draw(g, canvasSize); //draws satelite
        }

        //draw name of object
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = x + textWidth/3; //put text center
        int textY = y + diameter/2  + 3 * fm.getHeight(); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);

    }

    //updates location of planet on map when called
    public void updateLocation() {
        yOffset -= move;

        //update position of satelite
        for(SateLite sate_lite : sateLiteList) {
            sate_lite.updateLocation();
        }
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
    
    //the functions resets the location of the plaent on the map
    public void reset() {
        //reset to default values
        planetTimer.setDelay(speed);
        yOffset = def_yOffset;
        xOffset = def_xOffset;
        default_color = Color.blue;
    }

    //rewinds planet movement when called
    public void rewind() {
        planetTimer.setDelay(speed);
        yOffset += 2 * move;
    }

    public boolean isWithinMap(int y, int dimaeter){
        int bottomY = y + dimaeter;
        if(highestY > bottomY){
            //not within map boundary anymore
            return false;
        }
        return true;
    }

    public void set_speed(int speed2) {
        speed = speed2;
    }

    public void set_color(Color selected_color) {
        default_color = selected_color;
    }

    //mouse eventlistener for planet drawing
    public void mouseClicked(MouseEvent e) {
        //setting the bounds of the planet
        Rectangle2D planetBounds = new Rectangle2D.Double(x, y, diameter, diameter);

        //check if the region bound is clicked
        if(planetBounds.contains(e.getPoint())) {
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