
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: AsteroidCanvas.java
 * Date modified: 10/05/23
 * 
 * The file handles drawing of the asteroids for program
 */

import java.awt.*;

//asteroid class that draws an asteroid
public class AsteroidCanvas implements DrawingCanvas {

        private double xOffset;
        private double yOffset;
        private String name = "Asteroid";

        //constructor
        //takes x and y offset values
        public AsteroidCanvas( double xOffset, double yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }

        //setter function that sets x and y offsets
        public void set_offset(double xOffset, double yOffset){
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }

        //returns the xOffset
        public double get_xOffset(){
            return this.xOffset;
        }

        //returns the y Offset
        public double get_yOffset(){
            return this.yOffset;
        }
        public String get_name(){
            return name;
        }
        public double get_speed(){
            return yOffset;
        }

        //updates location of planet on map when called
        public void rewind() {
            yOffset += 0.08;
        }
    
        /* draw 
         * parameters include a 2d graphics and dimensions of drawing canvas
         * returns nothing
        */
        public void draw(Graphics2D g, Dimension canvasSize) {
            
            int baseSize = Math.min(canvasSize.width, canvasSize.height);
            int asteroidSize = baseSize /(int) (2 * Global.SCALE); 
    
            int centerX = (int) (canvasSize.width / xOffset);
            int centerY = (int) (canvasSize.height / yOffset);

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

    
            g.setColor(new Color(65, 30, 3));
            g.fillPolygon(xPoints, yPoints, numSides);

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(name);
            int textX = centerX - textWidth / 2;
            int textY = centerY + asteroidSize/2 + fm.getHeight();
            Name drName = new Name(textX, textY, name, 10);
            drName.draw(g);
        }

    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {

        yOffset -= 0.05;
    }
}
