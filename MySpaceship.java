

import java.awt.*;

import java.awt.event.MouseEvent;

//SpaceShipCanvas Class that draws a spaceship
public class MySpaceship extends SpaceShipCanvas{


    private double xOffset;
    private double yOffset;
    private String name = "Benatar";
    private ObjectListener lstn;
    private Color default_color = Color.BLACK;
    private boolean isAlarmActivated = false;

    public MySpaceship(ObjectListener lstn) {
        super(lstn, 2, 2);
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

    public void set_offset(double xOffset, double yOffset){
        this.xOffset = 2;
        this.yOffset = 2;
    }
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        int baseSize = Math.min(canvasSize.width, canvasSize.height);

        //length of spaceship landing base
        int spaceshipLength = baseSize / Global.SCALE; 
        
        //height for the main body for the spaceship (taller)
        int spaceshipHeight = spaceshipLength / 2;

        //height of the side compartments for a spaceship(shorter sides)
        int smallTriangleHeight = spaceshipHeight / 3; 

        int centerX = (int) (canvasSize.height / yOffset);
        int centerY = (int) (canvasSize.width / xOffset);
        int x_divisor = 2;

        int[] yPoints = {
            centerX + spaceshipHeight / x_divisor,//base level
            //top of left compartment
            centerX + spaceshipHeight / x_divisor - smallTriangleHeight,

            centerX - spaceshipHeight / x_divisor,//height of ship

            //top of right compartment
            centerX + spaceshipHeight / x_divisor - smallTriangleHeight,
            
            centerX + spaceshipHeight / x_divisor, //base level
            centerX + spaceshipHeight / x_divisor, //bottom of right
            centerX + spaceshipHeight / x_divisor //bottom of left
        };

        int[] xPoints = {
            centerY - spaceshipLength / x_divisor, //most left vertex
            centerY - spaceshipLength / 4, //base of left side
            centerY, //top of main compartment
            centerY + spaceshipLength / 4, //base of right side
            centerY + spaceshipLength / x_divisor, //most right vertex
            centerY + spaceshipLength / 6, //bottom of right side
            centerY - spaceshipLength / 6 //bottom of left side
        };

        g.setColor(default_color);
        g.fillPolygon(xPoints, yPoints, 7);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = centerY - spaceshipLength / x_divisor - textWidth/6; //put text center
        int textY =  centerX - spaceshipHeight / x_divisor + (int) ( 1.5 * fm.getHeight()); //put text below drawing
        Name drName = new Name(textX, textY, name, 10);
        drName.draw(g);
    }


    //updates location of planet on map when called
    public void rewind() {
            yOffset += 0.08;
    }

    public void activate_alarm(){
        if(!isAlarmActivated){
            Color translucent_red = new Color(255, 0, 0, 128);
            default_color = translucent_red;
            isAlarmActivated = true;
        } else {
            reset_color();
            isAlarmActivated = false;
        }
    }

    public void reset_color(){
        default_color = Color.black;
    }

    public void mouseClicked(MouseEvent e) {
        ScreenPosition obj = new ScreenPosition();
        Color clickedObj = obj.getPixelColor(e.getXOnScreen(), e.getYOnScreen());

        if(clickedObj.equals(Color.black)) {
            if(lstn != null){
                lstn.onObjectClicked(this);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
}

