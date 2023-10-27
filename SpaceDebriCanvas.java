
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: SpaceDebriCanvas.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for drawing spaceDebri
 */

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Timer;

import java.awt.event.MouseEvent;


public class SpaceDebriCanvas implements DrawingCanvas, MouseListener {

    private double population = 20;
    private String name = "debri";
    private double xOffset;
    private double yOffset;
    private int scale = 15;
    private double move = 0.05;
    private int speed = 150;
    private Timer debriTimer;
    private MyMap myMap;
    private int highestY;
    private Polygon single_debri;
    private Color default_color = Color.gray;


    private ArrayList<SpaceDebri> debriList = new ArrayList<>();
    private ArrayList<SpaceDebri> debriDefPos = new ArrayList<>();
    private ArrayList<Polygon> debri_polygons = new ArrayList<>();

    public SpaceDebriCanvas (MyMap map) {
        for(int i = 0; i < population; i++) {
            //generates random offsets between 1.5 and 4.5 which is the map limits
            debriList.add(new SpaceDebri(Math.random() * 4.5 + 1.5, Math.random() * 4.5 + 1.5));
        }

        for(SpaceDebri debri : debriList){
            debriDefPos.add(new SpaceDebri(debri.get_offset_x(), debri.get_offset_y()));
        }
        this.myMap = map;
        this.myMap.addMouseListener(this);

        debriTimer = new Timer(speed, new SpaceDebriListener(this, myMap));
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

    public void start() {
        debriTimer.start();
    }

    public void stop() {
        debriTimer.stop();
    }

    public void forward(int forward) {
        debriTimer.setDelay(speed/forward);
    }

    public void set_speed(int speed2) {
        speed = speed2;
    }

    public void set_color(Color selected_color) {
        default_color = selected_color;
    }

    public Shape get_position() {
        return null;
    }

    public void removeMyListener() {
        myMap.removeMouseListener(this);
    }
    

    public void draw(Graphics2D g, Dimension canvasSize) {
        for(SpaceDebri debri : debriList){
            drawDebri(g, canvasSize, debri); //draw each debri
        }
    }
    /* draw 
        * parameters include a 2d graphics and dimensions of drawing canvas
        * returns nothing
    */
    public void drawDebri(Graphics2D g, Dimension canvasSize, SpaceDebri debri) {

        int baseSize = Math.min(canvasSize.width, canvasSize.height);
        int asteroidSize = baseSize /(int) (8 * scale);  //small size

        //gets x and y to position  debri on map
        int centerX = (int) (canvasSize.width / debri.get_offset_x());
        int centerY = (int) (canvasSize.height / debri.get_offset_y());

        //debri has similar shape to asteroid in my implementaion
        int numSides = 13;
        double theta = 2 * Math.PI / numSides; //getting equal angles/side

        int[] xPoints = new int[numSides];
        int[] yPoints = new int[numSides];

        //static offsets for each point to keep the shape consistent
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
            double angle = theta * i;
            
            //using the formula r * cos(angle) to find x coordinate
            //then adding the center coordinate
            xPoints[i] = centerX + (int)(Math.cos(angle) * (asteroidSize - offsets[i]));
            yPoints[i] = centerY + (int)(Math.sin(angle) * (asteroidSize - offsets[i]));
        }

        single_debri = new Polygon(xPoints, yPoints, numSides);
        debri_polygons.add(single_debri);
        g.setColor(default_color);
        g.fillPolygon(single_debri);

        this.highestY = Integer.MAX_VALUE;
        for (int i = 0; i < numSides; i++) {
            int currentY = centerY + (int)(Math.sin(theta * i) * (asteroidSize - offsets[i]));
            if (currentY < highestY) {
                this.highestY = currentY;
            }
        }

        //draws name of drawing
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerX - textWidth / 2;
        int textY = centerY + asteroidSize/2 + fm.getHeight();
        Name drName = new Name(textX, textY, name, 5);
        drName.draw(g);

    }

    public boolean isWithinMap(int y, int dimaeter){
        return true;
    }
    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {
        for(SpaceDebri debri : debriList){
            debri.set_offsets(debri.get_offset_x() - move, debri.get_offset_y() - move);
        }
    }

    //rewinds the movement of the debri to its previous position
    public void rewind() {
        debriTimer.setDelay(speed);
        for(SpaceDebri debri : debriList){
            debri.set_offsets(debri.get_offset_x() + move, debri.get_offset_y() + move);
        }
    }

    //resets value to initial values
    public void reset() {

        //reset to initial values
        debriTimer.setDelay(speed);
        default_color = Color.gray;
        for (int i = 0; i < debriList.size(); i++) {
            SpaceDebri originalDebri = debriDefPos.get(i); //get original ofst
            SpaceDebri currentDebri = debriList.get(i); //get curr offset
    
            //set offset values of debri list to default values
            currentDebri.set_offsets(originalDebri.get_offset_x(), originalDebri.get_offset_y());
        }
    }

    //moves the drawing in the direction passed
    //parameter is the direction to move the item on the map
    public void move_item(String direction) {
        if(direction.equals("LEFT")) {
            //adjust to left by move
            for(SpaceDebri debri : debriList){
                debri.set_offsets(debri.get_offset_x() - move, debri.get_offset_y());
            }
        } 
        else if(direction.equals("UP")){
            //adjust to up by move
            for(SpaceDebri debri : debriList){
                debri.set_offsets(debri.get_offset_x(), debri.get_offset_y() - move);
            }
        }
        else if(direction.equals("RIGHT")){
            //adjust to right by move
            for(SpaceDebri debri : debriList){
                debri.set_offsets(debri.get_offset_x() + move, debri.get_offset_y());
            }
        }
        else if(direction.equals("DOWN")){
            //adjust to down by move
            for(SpaceDebri debri : debriList){
                debri.set_offsets(debri.get_offset_x(), debri.get_offset_y() + move);
            }
        }
    }

    //function to handle when drawing is clicked
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
