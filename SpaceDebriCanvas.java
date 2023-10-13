
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: SpaceDebriCanvas.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for drawing spaceDebri
 */

import java.awt.*;
import java.util.ArrayList;


public class SpaceDebriCanvas implements DrawingCanvas {

    private double population = 20;
    private String name = "debri";
    private double xOffset;
    private double yOffset;
    private int scale = 15;
    private double speed = 1;


    private ArrayList<SpaceDebri> debriList = new ArrayList<>();

    public SpaceDebriCanvas () {
        for(int i = 0; i < population; i++) {
            //generates random offsets between 1.5 and 4.5 which is the map limits
            debriList.add(new SpaceDebri(Math.random() * 4.5 + 1.5, Math.random() * 4.5 + 1.5));
        }
    }

    public void set_offset(double xOffset, double yOffset){
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
    public double get_speed(){
        return speed;
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


        g.setColor(Color.gray);
        
        g.fillPolygon(xPoints, yPoints, numSides);

        //draws name of drawing
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerX - textWidth / 2;
        int textY = centerY + asteroidSize/2 + fm.getHeight();
        Name drName = new Name(textX, textY, name, 5);
        drName.draw(g);

    }

    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {
    }
}
