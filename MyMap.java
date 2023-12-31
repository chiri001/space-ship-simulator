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
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.*;

//My map class that is incharge of handling elements in the map
class MyMap extends JPanel {
    private CircleCanvas circleCanvas; //circle boundary for map
    private SpaceShipCanvas spaceShipCanvas; //spaceship drawing class
    private PlanetCanvas planetCanvas;//planet drawing class
    private PointerCanvas pointerCanvas; //pointer hand drawing class
    private AsteroidCanvas asteroidCanvas;
    private SpaceDebriCanvas debriCanvas;
    private MySpaceship mySpaceShip;

    
    private ArrayList<DrawingCanvas> allDrawables = new ArrayList<>();
    private ArrayList<DrawingCanvas> defDrawings = new ArrayList<>();
    private ArrayList<String> newDrawings = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private ArrayList<DrawingCanvas> collided = new ArrayList<>();
    private ArrayList<Missile> missiles = new ArrayList<>();

    private boolean myShipExploded  = false;
    private ScorePanel score_panel;
    private int forward = 2;
    private boolean isSimulationStopped = false;
    
    private Timer drawing_generator_timer;

    private double zoomFactor = 1.0;
    private double maxZoomFactor = 5.0;
    private double minZoomFactor = 0.2;

    public MyMap() {
        //create drawings

        this.circleCanvas = new CircleCanvas(); //boundary for map

        //other drawings
        this.spaceShipCanvas = new SpaceShipCanvas(3, 3, this);
        allDrawables.add(spaceShipCanvas);
        defDrawings.add(spaceShipCanvas);

        this.mySpaceShip = new MySpaceship(2, 2, this);
        allDrawables.add(mySpaceShip);
        defDrawings.add(mySpaceShip);

        this.planetCanvas = new PlanetCanvas(1.5, 4, 5, this);
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


    //starts simulation by starting the timer
    public void start_simulation() {
        for(DrawingCanvas drawing : allDrawables) {
            drawing.start(); //starts all timers for type
        }
        drawing_generator_timer.start(); //starts random generator timer

        //start destination timer 
        if(score_panel != null) {
            score_panel.start_destination_timer();
        }

        isSimulationStopped = false;
        repaint();

        check_game_state(); //checks if myShip has collided
    }

    //function to help with zoom in
    public void zoomIn() {
        if(zoomFactor < maxZoomFactor) {
            zoomFactor *= 1.1; //scale up dy factor of 1.1
            revalidate();
            repaint();
        }
    }

    //function to aid with zoom out
    public void zoomOut() {
        if(zoomFactor > minZoomFactor) {
            zoomFactor /= 1.1; //scale down by factor of 1.1
            revalidate();
            repaint();
        }
    
    }

    //stops simulation by stopping the timer
    public void stop_simulation() {
        stop_alarm();
        
        for(DrawingCanvas drawing : allDrawables) {
            drawing.stop(); //stops all timers for drawings
        }

        drawing_generator_timer.stop(); //stop drawing generation
        score_panel.stop_destination_timer(); //stop updating distance
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
            check_game_state(); //check if myspaceship hasn't collided
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
        check_game_state();
    }


    //activates my spaceship alarm when alarm button is clicked
    public void activate_alarm() {
        mySpaceShip.sound_alarm();
        repaint();
        check_game_state();
    }

    //the functions resets the screen with drawings at its initial position
    public void reset_simulation() {
        //stop all ongoing simulation
        stop_simulation();

        //reset all default objects that still exist
        for(DrawingCanvas drawing : allDrawables) {
            drawing.reset();
        }

        //clear alldrawables
        allDrawables.clear();
        collided.clear();
        missiles.clear();

        //set default drawings to alldrawables
        allDrawables.addAll(defDrawings);
        
        //reset values
        forward = 2;
        myShipExploded = false;
        isSimulationStopped = false;

        //start simulations
        start_simulation();
        
        //repaint screen
        revalidate();
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
        double y_offset = 6 + rand.nextDouble(); //appear top of map
        int satelite = 0;

        if(newDrawings.get(index).equals("Planet")){
            satelite = rand.nextInt(10); //bound betwn 0 -10 satelites
        }

        //call add drawings to add item to map
        addDrawings(x_offset, y_offset, satelite, newDrawings.get(index));

    }

    //the function starts the timer showing user remaining distance to 
    //end simulation
    public void set_score_timer(ScorePanel score_panel){
        this.score_panel = score_panel;
        score_panel.start_destination_timer();
    }

    //function checks the state of the game
    public void check_game_state() {
        if(myShipExploded) {
            //end game if myship exploded
            simulation_over end_sim = new simulation_over("FAILURE", 
                                            this);
            end_sim.showMessage(); //show the meessage
        }
    }

    //function is responsible for adding drawings to the map
    //parameters include the x_offset, y_offset, number of satelites, and 
    //name of item to draw
    //NOTE: satelites is always zero if item is not planet
    public void addDrawings(double x, double y, int satelites, String to_draw) {
    
        if(to_draw.equals("Planet")){
            PlanetCanvas newPlanet = new PlanetCanvas(x, y, satelites, this);
            allDrawables.add(newPlanet);
        }
        else if(to_draw.equals("Spaceship")){
            SpaceShipCanvas newShip = new SpaceShipCanvas(x, y, this);
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
        check_game_state();
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
        check_game_state();
    }

    //informs mySpaceship to create the missile
    public void activate_missile() {
        if(!myShipExploded && !isSimulationStopped) {
            Missile newMissile = mySpaceShip.createMissile();
            missiles.add(newMissile);
        }
    }
    
    /* Paintcomponent
     * parameters is graphics to draw with
     * returns nothing
    */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic_2d = (Graphics2D) g; //2d graphics for drawings

        // Storing the original transform
        AffineTransform originalTransform = graphic_2d.getTransform();

        // Get the center of the component
        int centerX = getSize().width / 2;
        int centerY = getSize().height / 2;

        // Translate to the center, scale, and then translate back
        graphic_2d.translate(centerX, centerY);
        graphic_2d.scale(zoomFactor, zoomFactor);
        graphic_2d.translate(-centerX, -centerY);   

        circleCanvas.draw(graphic_2d, getSize()); //drawing map

        //boundary clip
        int map_info[] = circleCanvas.get_map_info();
        int radius = map_info[2]/2;
        int x = map_info[0];// top left x coordinate
        int y = map_info[1];// top left y coordinate
        Ellipse2D clipMap = new Ellipse2D.Double(x, y, 2 * radius, 2 * radius);
        graphic_2d.setClip(clipMap); //setting new clip

        //check if drawings are widthin map boundary inorder to draw
        Iterator<DrawingCanvas> iterator = allDrawables.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            DrawingCanvas drawing = iterator.next();
            if (drawing.isWithinMap(y, 2 * radius)) {
                if(! check_for_collission(drawing, i)){
                    drawing.draw(graphic_2d, getSize());
                }
                
            } 
            else {
                
                if(drawing instanceof MySpaceship) {
                    defDrawings.add(drawing); //reuse for reset
                } else {
                    //remove the drawing using the iterator
                    drawing.removeMyListener();
                    iterator.remove();
                }
                
            }
            i++;
        }

        //remove collided items
        allDrawables.removeAll(collided);
        collided.clear();

        //draw explosions
        Explosion.updateAll();
        Explosion.drawAll(graphic_2d);

        //update game
        updateGame();

        //draw missiles
        for (Missile missile : missiles) {
            missile.draw(graphic_2d, getSize());
        }

        graphic_2d.setTransform(originalTransform);
    }

    //checks if there has been a missile hit
    public void updateGame() {
        updateMissiles(); //checks if missile is active
        checkMissileCollisions(); //handles collision of missile and drawing
        repaint();
    }

    //updates the number of missile still active
    private void updateMissiles() {
        Iterator<Missile> it = missiles.iterator();
        while (it.hasNext()) {
            Missile missile = it.next();
            missile.move(getSize());//moves the missile

            if (!missile.isActive()) {
                it.remove();
            }
        }
    }

    //checks if missile hit an object
    private void checkMissileCollisions() {
        Iterator<Missile> missileIterator = missiles.iterator();
        while (missileIterator.hasNext()) {
            Missile missile = missileIterator.next();
    
            for (DrawingCanvas drawable : allDrawables) {
                if (drawable instanceof MySpaceship) {
                    continue; //skip mySpaceship
                }
                if(missile.get_position() != null && drawable.get_position() != null) {
                    //item that can destroy ship on collision
                    if (missile.get_position().intersects(drawable.get_position().getBounds2D())) {
                        explode(missile, drawable); //add to explode list
                        missileIterator.remove();
                        break;
                    }
                }
            }
        }
    }

    //function  checks whether two objects on the map have collided
    //parameters include a Drawing Canvas object and the index of the object
    //           in the array list contianing all drawings.
    private boolean check_for_collission(DrawingCanvas object, int index){

        if(object.get_position() == null){
            return false; //nothing to do for some draiwngs, i.e pointer
        }

        //check the bounds of the drawings if they collide
        for(int i = index + 1; i < allDrawables.size(); i++){
                DrawingCanvas object2 = allDrawables.get(i);

                if(object2.get_position() != null){
                    Area  area2 = new Area(object2.get_position()); //area of other drw
                    Area area1 = new Area(object.get_position()); 
                    area1.intersect(area2);

                    if(! area1.isEmpty() ){  

                        //handle collission
                        if(object.get_name() == "Benatar" || object2.get_name() == "Benatar")
                            myShipExploded = true;
                        
                        explode(object, object2);
                        return true;
                    }
                }
        }
        return false; //no collission detected
    }

    //function that adds colliding objects to the explode list
    //Params: objects colliding
    private void explode(DrawingCanvas obj1, DrawingCanvas obj2)
    {
        //add objects to explostion list
        explosions.add(new Explosion(obj1.get_position(), this));
        explosions.add(new Explosion(obj2.get_position(), this));

        //remove listeners for objects
        obj1.removeMyListener();
        obj2.removeMyListener();

        //add drawings to removables
        collided.add(obj1);
        collided.add(obj2);
    }

}