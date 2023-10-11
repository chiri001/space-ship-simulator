
import java.awt.*;

//class that creates a circle drawing using the drawing canvas interface
public class Name {
    
    String name;
    int x;
    int y;
    float fontSize;

    public Name (int x, int y, String name, int fontSize) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.fontSize = fontSize;
    }

    public void draw(Graphics2D g) {
        Font originalFont = g.getFont();
        Font newFont = originalFont.deriveFont(10f);
        g.setFont(newFont);
        g.drawString(name, x, y);
    }
}
