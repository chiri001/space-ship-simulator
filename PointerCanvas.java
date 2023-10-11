
import java.awt.*;

//pointer hand to simulate a rotating radar
class PointerCanvas implements DrawingCanvas {

    private double pointerAngle = 0; //variable to track angle of rotation

    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //x and y coordinates and diameterof the circle map
        int diameter = Math.min(canvasSize.width, canvasSize.height) - Global.PADDING;
        int x = (canvasSize.width - diameter) / 2;
        int y = (canvasSize.height - diameter) / 2;
        

        //center of the circle map should be starting point for pointer(x + r)
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;

        //end of the circle map using the formula x = r * cos(angle)
        //using angles so that it is recalculated to simulate rotation of ptr
        int endX = centerX + (int) (diameter / 2 * Math.cos(pointerAngle - Math.PI / 2));
        int endY = centerY + (int) (diameter / 2 * Math.sin(pointerAngle - Math.PI / 2));
        
        g.setColor(Color.green);
        g.drawLine(centerX, centerY, endX, endY);
    }

    /* updatePointer
     * parameters none
     * returns nothing
     * The function updates the pointer angle every time it is called
    */
    public void updatePointer() {
        pointerAngle += Math.toRadians(2); //update angle by 2 degrees
    }
}