/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyMap.java
 * Date modified: 10/05/23
 * 
 * This file contains MyMap class. The map contians all the drawings on the 
 * map including the boundary drawing for the map. 
 */


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
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
    private MySpaceship mySpaceShip;

    //variable to store the the rectangle where object can be displayed 
    //when clicked
    private ObjectInformation objectInfoInstance;

    //variable used by timer to set speed of objects on map
    private int forward = 2;
    private boolean isSimulationStopped = false;
    private ArrayList<DrawingCanvas> allDrawables = new ArrayList<>();
    private ArrayList<DrawingCanvas> defDrawings = new ArrayList<>();
    private ArrayList<String> newDrawings = new ArrayList<>();
    private Timer drawing_generator_timer;

    public MyMap() {
        //create drawings

        this.circleCanvas = new CircleCanvas(); //boundary for map

        //other drawings
        this.spaceShipCanvas = new SpaceShipCanvas(this, 3, 3, this);
        this.addMouseListener(spaceShipCanvas);
        allDrawables.add(spaceShipCanvas);
        defDrawings.add(spaceShipCanvas);

        this.mySpaceShip = new MySpaceship(this, 2, 2, this);
        this.addMouseListener(mySpaceShip);
        allDrawables.add(mySpaceShip);
        defDrawings.add(mySpaceShip);

        this.planetCanvas = new PlanetCanvas(this, 1.5, 4, 5, this);
        this.addMouseListener(planetCanvas);
        allDrawables.add(planetCanvas);
        defDrawings.add(planetCanvas);

        this.pointerCanvas = new PointerCanvas(this);
        allDrawables.add(pointerCanvas);
        defDrawings.add(pointerCanvas);

        this.asteroidCanvas = new AsteroidCanvas(2, 4.5, this);
        allDrawables.add(asteroidCanvas);
        defDrawings.add(asteroidCanvas);

        this.debriCanvas = new SpaceDebriCanvas(this);
        allDrawables.add(debriCanvas);
        defDrawings.add(debriCanvas);
        
        //adding items to randomly generate on map
        newDrawings.add("Spaceship");
        newDrawings.add("Planet");
        newDrawings.add("Asteroid");
        newDrawings.add("Spacedebri");

        set_drawing_generator(); //initialize new drawing generator
        start_simulation(); //start the simulation
    }

    //the function takes the rectangle that displays clicked drawings and
    //stores it in the class
    public void add_object_rectangle(ObjectInformation objInfoRect) {
        this.objectInfoInstance = objInfoRect;
    }

    //function handles when a drawing on map is clicked
    public void onObjectClicked(DrawingCanvas canvas) {
        objectInfoInstance.set_object(canvas);
        repaint();
    }

    //starts simulation by starting the timer
    public void start_simulation() {
        for(DrawingCanvas drawing : allDrawables) {
            drawing.start(); //starts all timers for type
        }
        drawing_generator_timer.start(); //starts random generator timer

        isSimulationStopped = false;
        repaint();
        
    }

    //stops simulation by stopping the timer
    public void stop_simulation() {
        stop_alarm();
        
        for(DrawingCanvas drawing : allDrawables) {
            drawing.stop(); //stops all timers for drawings
        }

        drawing_generator_timer.stop();
        isSimulationStopped = true;

        repaint();
        

    }

    //functions stops alarm when called
    public void stop_alarm() {
        mySpaceShip.reset_alarm();
    }

    //forwards the simulation by updating speed
    public void forward_simulation() {
        
        if(! isSimulationStopped) {

            for(DrawingCanvas drawing : allDrawables) {
                drawing.forward(forward);
            }

            forward *= 2; //increases speed by multiples of 2

            repaint();
        }
    }

    //rewinds the simulation
    public void rewind_simulation() {

        //reset all values
        forward = 2;

        for(DrawingCanvas drawing : allDrawables) {
            drawing.rewind();
        }

        repaint();
    }


    //activates my spaceship alarm when alarm button is clicked
    public void activate_alarm() {
        mySpaceShip.sound_alarm();
        repaint();
    }

    //the functions resets the screen with drawings at its initial position
    public void reset_simulation() {
        //stop all ongoing simulation
        stop_simulation();

        //clear alldrawables
        allDrawables.clear();

        //set default drawings to alldrawables
        allDrawables.addAll(defDrawings);

        //reset all default objects
        for(DrawingCanvas drawing : allDrawables) {
            drawing.reset();
        }
        
        //reset values
        forward = 2;
        
        //repaint screen
        repaint();
    }

    //functions initializes timer for randomizing generation of new drawings
    public void set_drawing_generator() {
    
        int delay = 5000;//every 5seconds generate new drawing from top
        drawing_generator_timer = new Timer(delay, e->generate_item_to_draw()); 
    }

    //generates the item to draw randomly
    public void generate_item_to_draw() {
        Random rand = new Random();
        
        //generate a random index between 0 and 4
        int index = rand.nextInt(newDrawings.size());

        //generate a random offset between 1 and 5 so that it is within viewprt
        double x_offset = 1 + rand.nextDouble() * 5; //appear anywhere on x axis
        double y_offset = 5 + rand.nextDouble(); //appear top of map
        int satelite = 0;

        if(newDrawings.get(index).equals("Planet")){
            satelite = rand.nextInt(10); //bound betwn 0 -10 satelites
        }

        //call add drawings to add item to map
        addDrawings(x_offset, y_offset, satelite, newDrawings.get(index));

    }

    //function is responsible for adding drawings to the map
    //parameters include the x_offset, y_offset, number of satelites, and 
    //name of item to draw
    //NOTE: satelites is always zero if item is not planet
    public void addDrawings(double x, double y, int satelites, String to_draw) {
    
        if(to_draw.equals("Planet")){
            PlanetCanvas newPlanet = new PlanetCanvas(this, x, y, satelites, this);
            allDrawables.add(newPlanet);
        }
        else if(to_draw.equals("Spaceship")){
            SpaceShipCanvas newShip = new SpaceShipCanvas(this, x, y, this);
            allDrawables.add(newShip);
        }
        else if(to_draw.equals("Asteroid")){
            AsteroidCanvas newAsteroid = new AsteroidCanvas(x, y, this);
            allDrawables.add(newAsteroid);
        }
        else if(to_draw.equals("Spacedebri")){
            SpaceDebriCanvas newdebri = new SpaceDebriCanvas(this);
            allDrawables.add(newdebri);
        }

        //start timers for new drawings
        if(! isSimulationStopped){
            for(DrawingCanvas drawing: allDrawables){
                drawing.start(); //to start timer for the new drawings
            }
        }
        repaint();
    }

    //function is responsible for moving items in a given direction
    public void move_items(String direction) {
        if( ! isSimulationStopped) {

            for(DrawingCanvas drawing : allDrawables) {
                //call move item for each drawing to move it accordingly
                drawing.move_item(direction);
            }
        }
        repaint();
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
        graphic_2d.setClip(clipMap); //setting new clip

        //check if drawings are widthin map boundary inorder to draw
        for(DrawingCanvas drawing: allDrawables) {
            if(drawing.isWithinMap(y, 2 * radius)){
                //draw shapes using draw function in DrawingCanvas interface
                drawing.draw(graphic_2d, getSize());
            }
        }
    }
}