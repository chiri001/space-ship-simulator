/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: MyMap.java
 * Date modified: 09/29/23
 * 
 * This file contains MyMap class. It is inform of a circle. The map contians
 * other items drawn in it. 
 */


import java.awt.*;
import javax.swing.*;

// setting up a drawing canvas interface for the map
interface DrawingCanvas {
    /* draw 
     * parameters include a 2d graphics and dimesnion
     * drawing interface for the file
    */
    void draw(Graphics2D g, Dimension canvasSize);
}

//class that creates a circle drawing
class CircleCanvas implements DrawingCanvas {
    /* draw 
     * parameters include a 2d graphics and dimesnion
     * drawing interface for the file
    */
    public void draw(Graphics2D g, Dimension canvasSize) {
        int padding = 15;
        int diameter = Math.min(canvasSize.width, canvasSize.height) - padding;
        int x = (canvasSize.width - diameter) / 2;
        int y = (canvasSize.height - diameter) / 2;
        g.drawOval(x, y, diameter, diameter);
    }
}

//SpaceShipCanvas Class that draws a spaceship
class SpaceShipCanvas implements DrawingCanvas {
    public void draw(Graphics2D g, Dimension canvasSize) {
        int baseSize = Math.min(canvasSize.width, canvasSize.height);
        int spaceshipLength = baseSize / 3;  // Length of the horizontal base
        int spaceshipHeight = spaceshipLength / 2;  // Height of the central triangle

        int smallTriangleHeight = spaceshipHeight / 3;  // Height of the side triangles

        int[] xPoints = {
            canvasSize.width / 3  - spaceshipLength / 2,  // Leftmost point of the base
            canvasSize.width / 3 - spaceshipLength / 4,  // Base of left triangle
            canvasSize.width / 3,  // Top of the central triangle
            canvasSize.width / 3 + spaceshipLength / 4,  // Base of right triangle
            canvasSize.width / 3 + spaceshipLength / 2,  // Rightmost point of the base
            canvasSize.width / 3 + spaceshipLength / 6,  // Bottom of the right triangle
            canvasSize.width / 3 - spaceshipLength / 6   // Bottom of the left triangle
        };

        int[] yPoints = {
            canvasSize.height / 3 + spaceshipHeight / 2,  // Base level
            canvasSize.height / 3 + spaceshipHeight / 2 - smallTriangleHeight,  // Top of left triangle
            canvasSize.height / 3 - spaceshipHeight / 2,  // Tip of the central triangle
            canvasSize.height / 3 + spaceshipHeight / 2 - smallTriangleHeight,  // Top of right triangle
            canvasSize.height / 3 + spaceshipHeight / 2,  // Base level
            canvasSize.height / 3 + spaceshipHeight / 2,  // Bottom of the right triangle
            canvasSize.height / 3 + spaceshipHeight / 2   // Bottom of the left triangle
        };

        g.fillPolygon(xPoints, yPoints, 7);


    }
}

//SpaceShipCanvas Class that draws a spaceship
class PlanetCanvas implements DrawingCanvas {
    public void draw(Graphics2D g, Dimension canvasSize) {
        int diameter = (Math.min(canvasSize.width, canvasSize.height) / 5);
        int x = (int) ((canvasSize.width - diameter) / 1.5);
        int y = (int) ((canvasSize.height - diameter) / 1.5);
        g.setColor(Color.gray);
        g.fillOval(x, y, diameter, diameter);
    }
}

//SpaceShipCanvas Class that draws a spaceship
// class pointerCanvas implements DrawingCanvas {
//     public void draw(Graphics2D g, Dimension canvasSize) {
//         g.filLine(35, 45, 75, 95);
//     }
// }


// Step 4: MyMap Class
class MyMap extends JPanel {
    private CircleCanvas circleCanvas;
    private SpaceShipCanvas spaceShipCanvas;
    private PlanetCanvas planetCanvas;

    public MyMap() {
        this.circleCanvas = new CircleCanvas();
        this.spaceShipCanvas = new SpaceShipCanvas();
        this.planetCanvas = new PlanetCanvas();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        circleCanvas.draw(g2d, getSize());
        spaceShipCanvas.draw(g2d, getSize());
        planetCanvas.draw(g2d, getSize());
    }
}






