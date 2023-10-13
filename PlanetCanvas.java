
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


//planetCanvas Class that draws a DrawingCanvas
public class PlanetCanvas implements DrawingCanvas,  MouseListener{
    private double xOffset;
    private double yOffset;
    private String name = "Planet";
    private int diameter = 0;
    private int x = 0;
    private int y = 0;
    private ObjectListener lstn;
    private int population;
    private int scale = 15;
    private double speed = 0.05;

    private ArrayList<SateLite> sateLiteList = new ArrayList<>();

    public PlanetCanvas(ObjectListener lstn, double xOffset, double yOffset, int satelites) {
        this.lstn = lstn;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.population = satelites;

        int orbitRadius = diameter;
        double angle = 2 * Math.PI / population; //divide angles equally

        for(int i = 0; i < population; i++) {
            SateLite sl = new SateLite(orbitRadius);
            sl.set_angle(i * angle); //sets angle for each satelite from planet
            sateLiteList.add(sl);
        }
    }

    public void set_offset(double xOffset, double yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
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

    public double get_speed(){
        return this.speed;
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

        g.setColor(Color.blue);
        g.fillOval(x, y, diameter, diameter);

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
        yOffset -= speed;

        //update position of satelite
        for(SateLite sate_lite : sateLiteList) {
            sate_lite.updateLocation();
        }
    }

    //rewinds planet movement when called
    public void rewind() {
        yOffset += 2 * speed;
    }

    //mouse eventlistener for planet drawing
    public void mouseClicked(MouseEvent e) {
        //setting the bounds of the planet
        Rectangle2D planetBounds = new Rectangle2D.Double(x, y, diameter, diameter);

        //check if the region bound is clicked
        if(planetBounds.contains(e.getPoint())) {
            if(lstn != null){
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