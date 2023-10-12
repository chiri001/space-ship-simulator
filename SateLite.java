
import java.awt.*;


//asteroid class that draws an asteroid
public class SateLite implements DrawingCanvas {

        private double xOffset;
        private double yOffset;
        private String name = "satelite";
        private int orbitRadius = 0;
        private double angle = 0;
        private int x = 0;
        private int y = 0;

        public SateLite(int orbitRadius) {
            this.orbitRadius = orbitRadius;
        }

        public void set_centre(int orbitRadius, int x, int y) {
            this.orbitRadius = orbitRadius;
            this.x = x;
            this.y = y;
        }
        public void set_angle(double angle) {
            this.angle = angle;
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

        /* draw 
         * parameters include a 2d graphics and dimensions of drawing canvas
         * returns nothing
        */
        public void draw(Graphics2D g, Dimension canvasSize) {
            
            int baseSize = Math.min(canvasSize.width, canvasSize.height);
            int diameter = baseSize / (int)(4 * Global.SCALE); // Satellite body diameter
    
            int centerX = x + 2 * (int) (orbitRadius * Math.cos(angle));
            int centerY = y + 2 * (int) (orbitRadius * Math.sin(angle));
    
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
        angle += 0.05;
        if(angle > 2 * Math.PI){
            angle -= 2 * Math.PI;
        }
    }
}
