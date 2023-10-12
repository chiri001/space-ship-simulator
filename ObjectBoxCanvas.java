


import java.awt.*;

//class that creates a box drawing to display object information
public class ObjectBoxCanvas implements DrawingCanvas {

    private int xOffset;
    private int yOffset;

    public void set_offset(double xOffset, double yOffset){
        this.xOffset = 2;
        this.yOffset = 2;
    }
    public double get_xOffset(){
        return this.xOffset;
    }
    public double get_yOffset(){
        return this.yOffset;
    }
    public String get_name(){
        return "";
    }
    public double get_speed(){
        return yOffset;
    }

    public void drawObj(Graphics2D g, Dimension canvasSize, DrawingCanvas obj){
        
        int length = canvasSize.width - Global.PADDING;
        int width = canvasSize.height - Global.PADDING;

        draw(g, new Dimension(length, width));
        
        if (obj != null) {

            double initial_xOffset = obj.get_xOffset();
            double initial_yOffset = obj.get_yOffset();

            obj.set_offset(2, 4);

            obj.draw(g, new Dimension(length, width));

            obj.set_offset(initial_xOffset, initial_yOffset);

            g.setColor(Color.black);
            String name_info = "Name : " + obj.get_name();
            String speed_info = "Speed : " + obj.get_speed() + " M/h";
            
            g.drawString(name_info, 20, 80);
            g.drawString(speed_info, 20, 100);
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
