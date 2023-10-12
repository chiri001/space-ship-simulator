
import java.awt.*;
import java.util.Random;


//asteroid class that draws an asteroid
public class SateLite implements DrawingCanvas {

        private double xOffset = 1.5;
        private double yOffset = 1.7;
        private String name = "satelite";


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

        /* draw 
         * parameters include a 2d graphics and dimensions of drawing canvas
         * returns nothing
        */
        public void draw(Graphics2D g, Dimension canvasSize) {
            
            int baseSize = Math.min(canvasSize.width, canvasSize.height);
            int diameter = baseSize / (int)(4 * Global.SCALE); // Satellite body diameter
    
            int centerX = (int) (canvasSize.width / xOffset);
            int centerY = (int) (canvasSize.height / yOffset);
    
            // Drawing the satellite main body
            g.setColor(new Color(40, 40, 40));
            g.fillOval(centerX - diameter/2, centerY - diameter/2, diameter, diameter);
    
            // Solar panel dimensions
            int panelWidth = diameter / 3;
            int panelHeight = diameter;
    
            // Drawing the solar panels
            g.setColor(Color.BLUE);
            g.fillRect(centerX - diameter/2 - panelWidth, centerY - panelHeight/2, panelWidth, panelHeight); // Left panel
            g.fillRect(centerX + diameter/2, centerY - panelHeight/2, panelWidth, panelHeight); // Right panel
    
            // Drawing the antenna
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(2));
            int antennaLength = diameter / 3;
            g.drawLine(centerX, centerY - diameter/2, centerX, centerY - diameter/2 - antennaLength);


            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(name);
            int textX = centerX + textWidth/3; //put text center
            int textY = centerY; //put text below drawing
            Name drName = new Name(textX, textY, name, 10);
            drName.draw(g);

        }

    
    /* updateLocation
     * parameters none
     * returns nothing
     * updates location of the planet on the map
    */
    public void updateLocation() {
        Random rand = new Random();

        double minX = 1.5;
        double maxX = 4.5;

        //limit values to be betwen minX and maxX
        xOffset =  minX + (maxX - minX) * rand.nextDouble();
        yOffset =  minX + (maxX - minX) * rand.nextDouble();
    }
}
