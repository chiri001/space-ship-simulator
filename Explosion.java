
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Mypanel.java
 * Date modified: 10/28/23
 * 
 * This file contains Explosion class. The class is responsible for drawing 
 * the image of an explosion after two objects collide
 */


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;



//draws a spaceship that can be controlled in the simulation
public class Explosion {

    private int x, y; //coordinates for explosion
    private int radius = 0; //starting r
    private int explosion_r = 45; //final readius of explosion

    private static ArrayList<Explosion> activeExplosion = new ArrayList<>();
    private Color expl_col = new Color(139, 0, 0);

    //constructor
    //params: shape of objects colliding and the map
    public Explosion(Shape Obj_shape, MyMap map) {
        //get coordinates of the explosion and add to active explosions list
        this.x = Obj_shape.getBounds().x;
        this.y = Obj_shape.getBounds().y;
        activeExplosion.add(this);

    }

    //updates the number of active explosions
    public static void updateAll() {
        Iterator<Explosion> iter = activeExplosion.iterator();

        while (iter.hasNext()) {
            Explosion exp = iter.next();
            exp.update();
            if (exp.isFinished()) {
                iter.remove(); //remove if explosion of object is finished
            }
        }
    }

    //updates the explosion readius of a single explosion
    public void update() {
        //increase redius by one each time
        if(radius < explosion_r) {
            radius += 1;
        }
    }

    //draws all the active explosion
    //param: the 2d graphics used for the map
    public static void drawAll(Graphics2D g){
        for(Explosion exp : activeExplosion) {
            exp.draw(g);
        }
    }

    //draws a single explosion
    //param: the 2d graphics used for the map
    public void draw(Graphics2D g){
        g.setColor(expl_col);
        g.fillOval(x - radius, y - radius, radius, radius);
    }


    //checks if explosion is finished
    public boolean isFinished() {
        return radius >= explosion_r; //complete if raidus exceeds explosion r
    }
}
