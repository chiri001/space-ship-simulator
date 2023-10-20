

/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Name.java
 * Date modified: 10/12/23
 * 
 * This file is responsible for drawing the box to display clicked drawings. It
 * is also responsible for drawing the drawings in the box.
 */

import java.awt.*;

//class that creates a box drawing to display object information
public class ObjectBoxCanvas implements DrawingCanvas {

    private int xOffset;
    private int yOffset;
    private int padding = 15;
    private int x = 10;
    private int y = 10;


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
        return 0;//doesn't move
    }
    public boolean isWithinMap(int y, int dimaeter){
        return true; //default since N/A to this drawing
    }

    //function that draws object box then draws items in it
    public void drawObj(Graphics2D g, Dimension canvasSize, DrawingCanvas obj){
        
        //add padding to put drawings from the edge
        int length = canvasSize.width - padding;
        int width = canvasSize.height - padding;

        draw(g, new Dimension(length, width)); //draw box display screen
        
        //checks that object was clicked to proceed
        if (obj != null) {
            //drawing text to be displayed on object information screen
            g.setColor(Color.black);
            String name_info = "Name : " + obj.get_name();
            String speed_info = "Speed : " + obj.get_speed() + " M/h";
            g.drawString(name_info,length / 3, width/3);
            g.drawString(speed_info,length / 3, width/2);
        }
    }
    
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //add padding to rectangle
        int length = canvasSize.width - padding;
        int width = canvasSize.height - padding;

        g.drawRect(x, y, length, width);
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
}
