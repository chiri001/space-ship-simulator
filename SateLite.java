/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: SateLite.java
 * Date modified: 10/12/23
 * 
 * This file contains Satelite drawing class
 */

import java.awt.*;

//Satelite class that draws an satelite
public class SateLite implements DrawingCanvas {

    private double xOffset;
    private double yOffset;
    private String name = "satelite";
    private int orbitRadius = 0;
    private double angle = 0;
    private int x = 0;
    private int y = 0;
    private double speed = 0.05;
    private int scale = 15;

    public SateLite(int orbitRadius) {
        this.orbitRadius = orbitRadius;
    }

    public void set_centre(int orbitRadius, int x, int y) {
        this.orbitRadius = orbitRadius;
        this.x = x;
        this.y = y;
    }
    public void set_angle(double angle) {
        this.angle = angle;
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
        return (int)speed;
    }
    public void set_speed(int speed2) {
    }

    public void set_color(Color selected_color) {
    }

    public Shape get_position() {
        return null;
    }

    public void removeMyListener() {
    }

    /* draw 
        * parameters include a 2d graphics and dimensions of drawing canvas
        * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        
        int baseSize = Math.min(canvasSize.width, canvasSize.height);
        int diameter = baseSize / (int)(4 * scale); // Satellite body diameter

        //finds the x and y coordinates of satelite while in orbit
        //x and y are is the orbits center/ planet center
        int centerX = x + (int) (orbitRadius * Math.cos(angle));
        int centerY = y + (int) (orbitRadius * Math.sin(angle));

        // Drawing the satellite main body
        g.setColor(new Color(40, 40, 40)); //color dark Grey

        //subtractring radius from new x and y coordinate of satelite to 
        //find where to position satelite on map
        int body_x = centerX - diameter/2;
        int body_y = centerY - diameter/2;
        g.fillOval(body_x, body_y, diameter, diameter);

        // Solar panel dimensions
        int panelWidth = diameter / 3; //width of panel is smaller
        int panelHeight = diameter;

        // Drawing the solar panels
        g.setColor(Color.BLUE);
        int panel_x = body_x - panelWidth; //positions it after body
        int panel_y = centerY - panelHeight/2; //same height as body(panelHeight == diameter)
        g.fillRect(panel_x, panel_y, panelWidth, panelHeight); // Left panel
        g.fillRect(centerX + diameter/2, panel_y, panelWidth, panelHeight); // Right panel

        // Drawing the antenna
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(2));
        int antennaLength = diameter / 3; //small antena than body
        //starts from top of body and positioned at the center of body
        int EndPoint = centerY - diameter/2 - antennaLength; //position the antena beyond body
        g.drawLine(centerX, body_y, centerX, EndPoint);

        //adding name to drawing
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerX + textWidth/3; //put text center
        int textY = centerY; //put text below drawing
        Name drName = new Name(textX, textY, name, 8);
        drName.draw(g);

    }

    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {
        angle += speed; //update the angle

        //if angle exceeds 360 - 360 from it i.e 540 - 360 = 180
        if(angle > 2 * Math.PI){
            angle -= 2 * Math.PI;
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void forward(int forward) {
    }

    @Override
    public void rewind() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void move_item(String direction) {
    }
    public boolean isWithinMap(int y, int dimaeter){
        return true;
    }
}
