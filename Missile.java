import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Missile implements DrawingCanvas {

    private Shape missileShape;
    private Point position;
    private double direction;
    private double speed;
    private boolean active;


    Missile(Point startPos, double direction, double speed) {
        this.position = startPos;
        this.direction = direction;
        this.speed = speed;
        this.active = true;


        //create the missile
        missileShape = createMissile(position);
    }

    public Shape createMissile(Point position){
        return new Line2D.Double(
            position.x, position.y, position.x, position.y - 10
        );
    }

    public void move(Dimension canvasSize) {
        //move the missile
        position.y -= speed;
        missileShape = createMissile(position);
    }

    public void draw(Graphics2D g, Dimension size) {
        move(size);
        if(active) {
            g.setColor(Color.RED);
            g.draw(missileShape);
        }
    }

    public Shape get_position() {
        return missileShape;
    }

    public boolean isWithinMap(int y, int radius) {
        return missileShape.getBounds2D().intersects(0, y, radius, radius);
    }

    public boolean isActive(){
        return active;
    }

    public void Deactivate(){
        active = false;
    }

    @Override
    public double get_xOffset() {
        return 2.0;
    }

    @Override
    public double get_yOffset() {
        return 2.0;
    }

    @Override
    public String get_name() {
        return "Missile";
    }

    @Override
    public int get_speed() {
        return 2;
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

    @Override
    public void set_color(Color selected_color) {
    }

    @Override
    public void set_speed(int new_speed) {
    }

    @Override
    public void removeMyListener() {}

}