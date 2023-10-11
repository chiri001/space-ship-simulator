


import java.awt.*;

//class that creates a box drawing to display object information
public class ObjectBoxCanvas implements DrawingCanvas {
    
    public void drawObj(Graphics2D g, Dimension canvasSize, DrawingCanvas obj){
        
        int length = canvasSize.width - Global.PADDING;
        int width = canvasSize.height - Global.PADDING;

        draw(g, new Dimension(length, width));
        
        if (obj != null) {
            obj.draw(g, new Dimension(length, width));
        }
    }
    /* draw 
     * parameters include a 2d graphics and dimesnions of drawing canvas
     * returns nothing
    */
    public void draw(Graphics2D g, Dimension canvasSize) {

        //add padding to rectangle
        int length = canvasSize.width - Global.PADDING;
        int width = canvasSize.height - Global.PADDING;
        int x = 10;
        int y = 10;

        g.drawRect(x, y, length, width);
    }
}
