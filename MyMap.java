/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyMap.java
 * Date modified: 09/29/23
 * 
 * This file contains MyMap class. It is inform of a circle. The map contians
 * other items drawn in it. 
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

//class that creates a circle drawing using the drawing canvas interface
class CircleCanvas implements DrawingCanvas {
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        int diameter = Math.min(canvasSize.width, canvasSize.height) - Global.PADDING;

        //centers the circle in the drawing panel
        int x = (canvasSize.width - diameter) / 2;
        int y = (canvasSize.height - diameter) / 2;
        g.drawOval(x, y, diameter, diameter);
    }
}

//SpaceShipCanvas Class that draws a spaceship
class SpaceShipCanvas implements DrawingCanvas {

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        int baseSize = Math.min(canvasSize.width, canvasSize.height);

        //length of spaceship landing base
        int spaceshipLength = baseSize / Global.SCALE; 
        
        //height for the main body for the spaceship
        int spaceshipHeight = spaceshipLength / 2;

        //height of the side compartments for a spaceship
        int smallTriangleHeight = spaceshipHeight / 3; 

        //TODO: randomize the division so that it can appear anywher on map

        int[] xPoints = {
            canvasSize.height / 3 + spaceshipHeight / 2,//base level
            //top of left compartment
            canvasSize.height / 3 + spaceshipHeight / 2 - smallTriangleHeight,

            canvasSize.height / 3 - spaceshipHeight / 2,//height of ship

            //top of left compartment
            canvasSize.height / 3 + spaceshipHeight / 2 - smallTriangleHeight,
            
            canvasSize.height / 3 + spaceshipHeight / 2, //base level
            canvasSize.height / 3 + spaceshipHeight / 2, //bottom of right
            canvasSize.height / 3 + spaceshipHeight / 2 //bottom of left
        };

        int[] yPoints = {
            canvasSize.width / 3 - spaceshipLength / 2, //most left vertex
            canvasSize.width / 3 - spaceshipLength / 4, //base of left side
            canvasSize.width / 3, //top of main compartment
            canvasSize.width / 3 + spaceshipLength / 4, //base of right side
            canvasSize.width / 3 + spaceshipLength / 2, //most right vertex
            canvasSize.width / 3 + spaceshipLength / 6, //bottom of right side
            canvasSize.width / 3 - spaceshipLength / 6 //bottom of left side
        };

        g.fillPolygon(xPoints, yPoints, 7);


    }
}

//SpaceShipCanvas Class that draws a spaceship
class PlanetCanvas implements DrawingCanvas {
    
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        int diameter = (Math.min(canvasSize.width, 
                        canvasSize.height) / Global.SCALE); //planet diameter
        
        //calculating x and y coordinate of planet
        //TODO: randomize the division
        int x = (int) ((canvasSize.width - diameter) / 1.5);
        int y = (int) ((canvasSize.height - diameter) / 1.5);

        g.setColor(Color.blue);
        g.fillOval(x, y, diameter, diameter);
    }
}

//pointer hand to simulate a rotating radar
class PointerCanvas implements DrawingCanvas {

    private double pointerAngle = 0; //variable to track angle of rotation

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //x and y coordinates and diameterof the circle map
        int diameter = Math.min(canvasSize.width, canvasSize.height) - Global.PADDING;
        int x = (canvasSize.width - diameter) / 2;
        int y = (canvasSize.height - diameter) / 2;
        

        //center of the circle map should be starting point for pointer
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;

        //end of the circle map using the formula x = r * cos(angle)
        //using angles so that it is recalculated to simulate rotation of ptr
        int endX = centerX + (int) (diameter / 2 * Math.cos(pointerAngle - Math.PI / 2));
        int endY = centerY + (int) (diameter / 2 * Math.sin(pointerAngle - Math.PI / 2));
        
        g.setColor(Color.green);
        g.drawLine(centerX, centerY, endX, endY);
    }

    /* updatePointer
     * parameters none
     * returns nothing
     * The function updates the pointer angle every time it is called
    */
    public void updatePointer() {
        pointerAngle += Math.toRadians(2); //update angle by 2 degrees
    }
}

/*class that implements the rotation aspect of the pointer */
class PointerUpdateListener implements ActionListener {
    
    private PointerCanvas pointerCanvas;
    private MyMap myMap;

    /* PointerUpdateListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    public PointerUpdateListener(PointerCanvas pCanvas, MyMap map) {
        this.pointerCanvas = pCanvas;
        this.myMap = map;
    }

    /*implements the rotation action but relies on PointerCanvas class fn()
    */
    public void actionPerformed(ActionEvent e) {
        pointerCanvas.updatePointer();
        myMap.repaint(); //redraw the entire map
    }
}


//asteroid class that draws an asteroid
class AsteroidCanvas implements DrawingCanvas {
    private double xOffset = 1.5;;
    private double yOffset = 4.5;
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        
        int baseSize = Math.min(canvasSize.width, canvasSize.height);

        //length of spaceship landing base
        int asteroidSize = baseSize / Global.SCALE; 
        
        //calculating x and y coordinate of planet
        //TODO: randomize the division
        int centerX = (int) (canvasSize.width / xOffset);
        int centerY = (int) (canvasSize.height / yOffset);
        
        int[] xPoints = {
            centerX - asteroidSize / 2,
            centerX,
            centerX - asteroidSize / 2,
            centerX - asteroidSize / 2,
            centerX,
            centerX + asteroidSize / 2,
            centerX + asteroidSize / 2,
            centerX,
        };

        int[] yPoints = {
            centerY,
            centerY - asteroidSize / 2,
            centerY,
            centerY + asteroidSize / 2,
            centerY + asteroidSize / 2,
            centerY,
            centerY - asteroidSize / 2,
            centerY
        };

        g.setColor(Color.gray);
        g.fillPolygon(xPoints, yPoints, 8);
        
    }

    public void updateLocation() {
        Random rand = new Random();

        double minX = 1.5;
        double maxX = 4.5;

        xOffset =  minX + (maxX - minX) * rand.nextDouble();
        yOffset=  minX + (maxX - minX) * rand.nextDouble();
    }
}

//My map class that is incharge of handling elements in the map
class MyMap extends JPanel {
    private CircleCanvas circleCanvas; //circle boundary for map
    private SpaceShipCanvas spaceShipCanvas; //spaceship drawing class
    private PlanetCanvas planetCanvas;//planet drawing class
    private PointerCanvas pointerCanvas; //pointer hand drawing class
    private AsteroidCanvas asteroidCanvas;


    //timer variables
    private Timer timer;
    private Timer asteroidTimer;
    private Timer shipTimer;
    private Timer planetTimer;

    public MyMap() {
        //create drawings
        this.circleCanvas = new CircleCanvas();
        this.spaceShipCanvas = new SpaceShipCanvas();
        this.planetCanvas = new PlanetCanvas();
        this.pointerCanvas = new PointerCanvas();
        this.asteroidCanvas = new AsteroidCanvas();
        timer = new Timer(50, 
                                new PointerUpdateListener(pointerCanvas, this));
        asteroidTimer = new Timer(1000, e -> {
            asteroidCanvas.updateLocation();
            repaint();
        });
        
    }

    public void start_simulation() {
        timer.start();
        asteroidTimer.start();
    }

    public void stop_simulation() {
        timer.stop();
    }

    /* PointerUpdateListener
     * parameters include the pointer hand and the parent container i.e map
     * returns nothing
     * constructor for the class
    */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic_2d = (Graphics2D) g; //2d graphics for drawings

        //draw shapes using draw function in DrawingCanvas interface
        circleCanvas.draw(graphic_2d, getSize());
        spaceShipCanvas.draw(graphic_2d, getSize());
        planetCanvas.draw(graphic_2d, getSize());
        asteroidCanvas.draw(graphic_2d, getSize());
        pointerCanvas.draw(graphic_2d, getSize());
    }
}





