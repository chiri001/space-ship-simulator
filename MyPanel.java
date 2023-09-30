/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Mypanel.java
 * Date modified: 09/29/23
 * 
 * This file contains MyPanel calss that creates a new JPanel for use
 */
import java.awt.*;
import javax.swing.*;

/*class that creates a panel for drawing and adding other items */
public class MyPanel extends JPanel {
    /* constructor 
     * creates a new layout for the panel
     * creates other layouts for the panel
    */
    public MyPanel() {

        setLayout(new BorderLayout());/*new layout for the panel */

        //functions to set the layout of the window
        create_south_layout();
        create_east_layout();
        create_west_layout();

    }

    /*function creates the layout of the south panel
     * It takes no parameter
     */
    private void create_south_layout() {
        /*create a new jpanel with given dimensions */
        JPanel southPanel = new  JPanel(new GridLayout(1, 0));
        southPanel.setBorder(BorderFactory.createLineBorder(
                                                    Color.BLACK, 5));
        add(southPanel, BorderLayout.SOUTH);

        //calls a function that creates buttons for the bottom panel
        addButtonToPanel(southPanel, "START", Color.GREEN);
        addButtonToPanel(southPanel, "STOP", Color.RED);
        addButtonToPanel(southPanel, "RESET", Color.GRAY);
        addButtonToPanel(southPanel, "NEW PATH", Color.GRAY);
        addButtonToPanel(southPanel, "ALARM", Color.RED);

    }

    /*function that creates buttons and adds it to panel 
     * NOTE: depends on MyButton class implementation
    */
    private void addButtonToPanel(JPanel panel, String btnTxt, Color color) {
        MyButton btn = new MyButton(btnTxt);
        btn.set_btn_color(color);
        btn.set_size(80, 60);
        panel.add(btn);
    }

    /*function that creates the layout for the west side of the panel
     * takes no parameter
     */
    private void create_west_layout() {
        /*create a new jpanel with given dimensions */
        JPanel westPanel = new  JPanel(new BorderLayout());
        westPanel.setBorder(BorderFactory.createLineBorder(
                                                    Color.BLACK, 5));
        add(westPanel, BorderLayout.CENTER);

        //circle drawing where the map will showcase
        DrawingCanvas centralCircle = new DrawingCanvas 
                                        (DrawingCanvas.Shape.CIRCLE, 30, 
                                        10);
        westPanel.add(centralCircle, BorderLayout.CENTER);
        
    }

    /*
     * Function that creates the east Layout of the panel
     * takes no parameters
     */
    private void create_east_layout() {
        JPanel eastPanel = new  JPanel(new GridLayout(3, 1));
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 
                                                            5));
        add(eastPanel, BorderLayout.EAST);

        //a jpanel to hold the drawing to display object information
        JPanel drawingPanel = new JPanel(new BorderLayout());
        eastPanel.add(drawingPanel, BorderLayout.NORTH);

        //object information rectangle
        DrawingCanvas objInfoRect = new DrawingCanvas 
                                        (DrawingCanvas.Shape.RECTANGLE,
                                        10, 10);
        drawingPanel.add(objInfoRect, BorderLayout.CENTER);


        //a jpanel to hold the buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4, 2));
        eastPanel.add(btnPanel, BorderLayout.CENTER);

        //the arrow buttons
        JPanel yArrowPanel = new JPanel(new BorderLayout());
        btnPanel.add(yArrowPanel); //holds y axis arrow button(up and down)
        JPanel xArrowPanel = new JPanel(new BorderLayout());
        btnPanel.add(xArrowPanel);//holds x axis arrow button(left and right)

        //calls a function that creates buttons and adds to given panel
        addButtonToPanel(yArrowPanel, "UP", Color.GRAY, 
                                                        BorderLayout.WEST);
        addButtonToPanel(yArrowPanel, "DOWN", Color.GRAY, 
                                                        BorderLayout.EAST);
        addButtonToPanel(xArrowPanel, "LEFT", Color.GRAY, 
                                                        BorderLayout.EAST);
        addButtonToPanel(xArrowPanel, "RIGHT", Color.GRAY, 
                                                        BorderLayout.WEST);

        //zoom buttons
        JPanel zoomPanel = new JPanel(new BorderLayout());
        btnPanel.add(zoomPanel);
        addButtonToPanel(zoomPanel, "Z(+)", Color.GRAY, 
                                                        BorderLayout.WEST);
        addButtonToPanel(zoomPanel, "Z(-)", Color.GRAY, 
                                                        BorderLayout.EAST);

        //speed buttons
        JPanel speedPanel = new JPanel(new BorderLayout());
        btnPanel.add(speedPanel);
        addButtonToPanel(speedPanel, "<<", Color.GRAY, 
                                                        BorderLayout.WEST);
        addButtonToPanel(speedPanel, ">>", Color.GRAY, 
                                                        BorderLayout.EAST);

        //a jpanel to hold the drawing to display the maps key
        JPanel keyPanel = new JPanel(new BorderLayout());
        eastPanel.add(keyPanel, BorderLayout.SOUTH);

        //rectangle to showcase object information
        DrawingCanvas keyRect = new DrawingCanvas 
                                        (DrawingCanvas.Shape.RECTANGLE,
                                        10, 10);
        keyPanel.add(keyRect, BorderLayout.CENTER);


    }

    /*function that creates buttons and adds it to panel 
     * depends on the implementation of MyButton class
     * parameters: a jpanel to add buttons to, a button text, a color for 
     * the button, a string that shows where button should be on the panel
    */
    private void addButtonToPanel(JPanel panel, String btnTxt, Color color, 
                            String constraint) {
        
        MyButton btn = new MyButton(btnTxt);
        btn.set_btn_color(color); //set button color
        btn.set_size(60, 50);
        panel.add(btn, constraint); //add to panel and set constraint
    }
    
}

