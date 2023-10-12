
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


//asteroid class that draws an asteroid
public class SpaceDebriCanvas implements DrawingCanvas {

    private double population = 20;
    private String name = "debri";
    private double xOffset;
    private double yOffset;

    private ArrayList<SpaceDebri> debriList = new ArrayList<>();

    public SpaceDebriCanvas () {
        for(int i = 0; i < population; i++) {
            debriList.add(new SpaceDebri(Math.random() * 3 + 1.5, Math.random() * 3 + 1.5));
        }
    }

    public void set_offset(double xOffset, double yOffset){
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
        return name;
    }
    public double get_speed(){
        return yOffset;
    }

    public void draw(Graphics2D g, Dimension canvasSize) {
        for(SpaceDebri debri : debriList){
            drawDebri(g, canvasSize, debri);
        }
    }
    /* draw 
        * parameters include a 2d graphics and dimensions of drawing canvas
        * returns nothing
    */
    public void drawDebri(Graphics2D g, Dimension canvasSize, SpaceDebri debri) {

        int baseSize = Math.min(canvasSize.width, canvasSize.height);
        int asteroidSize = baseSize /(int) (8 * Global.SCALE); 

        int centerX = (int) (canvasSize.width / debri.xOffset);
        int centerY = (int) (canvasSize.height / debri.yOffset);

        int numSides = 13;
        double angleIncrement = 2 * Math.PI / numSides;

        int[] xPoints = new int[numSides];
        int[] yPoints = new int[numSides];

        // Define static offsets for each point to keep the shape consistent
        int[] offsets = {
            (int)(0.1 * asteroidSize),
            (int)(0.15 * asteroidSize),
            (int)(0.12 * asteroidSize),
            (int)(0.18 * asteroidSize),
            (int)(0.1 * asteroidSize),
            (int)(0.14 * asteroidSize),
            (int)(0.16 * asteroidSize),
            (int)(0.11 * asteroidSize),
            (int)(0.15 * asteroidSize),
            (int)(0.13 * asteroidSize),
            (int)(0.17 * asteroidSize),
            (int)(0.12 * asteroidSize),
            (int)(0.14 * asteroidSize)
        };

        for (int i = 0; i < numSides; i++) {
            double angle = angleIncrement * i;
            
            xPoints[i] = centerX + (int)(Math.cos(angle) * (asteroidSize - offsets[i]));
            yPoints[i] = centerY + (int)(Math.sin(angle) * (asteroidSize - offsets[i]));
        }


        g.setColor(Color.gray);
        
        g.fillPolygon(xPoints, yPoints, numSides);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerX - textWidth / 2;
        int textY = centerY + asteroidSize/2 + fm.getHeight();
        Name drName = new Name(textX, textY, name, 2);
        drName.draw(g);

    }

    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {
        Random rand = new Random();
        population = 10 + (40 - 10) * rand.nextDouble();
    }
}
