



import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;



//draws a spaceship that can be controlled in the simulation
public class Explosion {

    private int x, y; //coordinates for explosion
    private int radius = 0; //starting r
    private int explosion_r = 45;
    private static ArrayList<Explosion> activeExplosion = new ArrayList<>();
    private Color expl_col = new Color(139, 0, 0);
    private static ArrayList<Color> explosion_colors = new ArrayList<>();


    public Explosion(Shape Obj_shape, MyMap map) {
        this.x = Obj_shape.getBounds().x;
        this.y = Obj_shape.getBounds().y;
        activeExplosion.add(this);

    }

    public static void updateAll() {
        Iterator<Explosion> iter = activeExplosion.iterator();
        while (iter.hasNext()) {
            Explosion exp = iter.next();
            exp.update();
            if (exp.isFinished()) {
                iter.remove();
            }
        }
    }

    public void update() {
        if(radius < explosion_r) {
            radius += 1;
        }
    }

    public static void drawAll(Graphics2D g){
        for(Explosion exp : activeExplosion) {
            exp.draw(g);
        }
    }

    public void draw(Graphics2D g){
        g.setColor(expl_col);
        g.fillOval(x - radius, y - radius, radius, radius);
    }

    public boolean isFinished() {
        return radius >= explosion_r;
    }
}
