/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyMap.java
 * Date modified: 10/05/23
 * 
 * This file contains MyMap class. It is inform of a circle. The map contians
 * other items drawn in it. 
 */


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.*;

//My map class that is incharge of handling elements in the map
class MyMap extends JPanel implements ObjectListener {
    private CircleCanvas circleCanvas; //circle boundary for map
    private SpaceShipCanvas spaceShipCanvas; //spaceship drawing class
    private PlanetCanvas planetCanvas;//planet drawing class
    private PointerCanvas pointerCanvas; //pointer hand drawing class
    private AsteroidCanvas asteroidCanvas;
    private SpaceDebriCanvas debriCanvas;
    private SateLite sateLite;
    private SpaceShipCanvas mySpaceShip;


    //timer variables
    private Timer timer;
    private Timer asteroidTimer;
    private Timer shipTimer;
    private Timer planetTimer;


    private ObjectInformation objectInfoInstance;

    public MyMap() {
        //create drawings
        this.circleCanvas = new CircleCanvas();
        this.spaceShipCanvas = new SpaceShipCanvas(this);
        this.addMouseListener(spaceShipCanvas);
        this.mySpaceShip = new SpaceShipCanvas(this);
        this.addMouseListener(mySpaceShip);
        this.planetCanvas = new PlanetCanvas(this);
        this.addMouseListener(planetCanvas);
        this.pointerCanvas = new PointerCanvas();
        this.asteroidCanvas = new AsteroidCanvas();
        this.debriCanvas = new SpaceDebriCanvas();
        this.sateLite = new SateLite();



        //initialize timers
        //timer updates drawing position upon expiration
        timer = new Timer(50, 
                            new PointerListener(pointerCanvas, this));

        asteroidTimer = new Timer(10000, 
                                    new AsteroidListener(asteroidCanvas, this));

        planetTimer = new Timer(50000, 
                                new PlanetListener(planetCanvas, this));

        shipTimer = new Timer(25000, 
                                new ShipListener(spaceShipCanvas, this));
        
    }

    public void add_object_rectangle(ObjectInformation objInfoRect) {
        this.objectInfoInstance = objInfoRect;
    }

    public void onObjectClicked(DrawingCanvas canvas) {
        objectInfoInstance.set_object(canvas);
        repaint();
    }
    //starts simulation by starting the timer
    public void start_simulation() {
        timer.start();
        asteroidTimer.start();
        planetTimer.start();
        shipTimer.start();
    }

    //stops simulation by stopping the timer
    public void stop_simulation() {
        timer.stop();
        asteroidTimer.stop();
        planetTimer.stop();
        shipTimer.stop();
    }

    public void forward_simulation() {
        asteroidTimer.setDelay(8000);
        planetTimer.setDelay(48000);
        shipTimer.setDelay(23000);
    }

    public void rewind_simulation() {
        asteroidCanvas.rewind();
        planetCanvas.rewind();
        spaceShipCanvas.rewind();
    }

    /* Paintcomponent
     * parameters is graphics to draw with
     * returns nothing
    */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic_2d = (Graphics2D) g; //2d graphics for drawings

        circleCanvas.draw(graphic_2d, getSize()); //drawing map

        //boundary clip
        int map_info[] = circleCanvas.get_map_info();
        int radius = map_info[2]/2;
        int x = map_info[0];// top left x coordinate
        int y = map_info[1];// top left y coordinate
        Ellipse2D clipMap = new Ellipse2D.Double(x, y, 2 * radius, 2 * radius);
        Shape oldClip = graphic_2d.getClip();
        graphic_2d.setClip(clipMap); //setting new clip

        //drawing my spaceship
        mySpaceShip.set_offset(2, 2);
        mySpaceShip.draw(graphic_2d, getSize());

        //draw shapes using draw function in DrawingCanvas interface
        spaceShipCanvas.set_offset(1.5, 3);
        spaceShipCanvas.draw(graphic_2d, getSize());
        planetCanvas.draw(graphic_2d, getSize());
        asteroidCanvas.draw(graphic_2d, getSize());
        pointerCanvas.draw(graphic_2d, getSize());
        debriCanvas.draw(graphic_2d, getSize());
        sateLite.draw(graphic_2d, getSize());

        graphic_2d.setClip(oldClip);
    }

    private double offset_generator() {
    
        Random rand = new Random();
        double minX = 1.5;
        double maxX = 4.5;

        double offset = minX + (maxX - minX) * rand.nextDouble();

        return offset;
    }
}