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

        setLayout(new GridBagLayout());/*new layout for the panel */
        GridBagConstraints constraint = new GridBagConstraints();

        //functions to set the layout of the window
        create_south_layout(constraint);
        create_east_layout(constraint);
        create_west_layout(constraint);

    }

    /*function creates the layout of the south panel
     * It takes no parameter
     */
    private void create_south_layout(GridBagConstraints constraint) {
        /*create a new jpanel with given dimensions */
        JPanel southPanel = new  JPanel(new GridLayout(1, 0));
        southPanel.setBorder(BorderFactory.createLineBorder(
                                                    Color.BLACK, 2));
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.weightx = 0.6; //take 60% the x space
        constraint.weighty = 0.1; //take 30% the y space
        constraint.fill = GridBagConstraints.BOTH;//stretches in both directions
        add(southPanel, constraint);

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
    private void create_west_layout(GridBagConstraints constraint) {
        /*create a new jpanel with given dimensions */
        JPanel westPanel = new  JPanel(new BorderLayout());
        westPanel.setBorder(BorderFactory.createLineBorder(
                                                    Color.BLACK, 2));
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.weightx = 0.6; //take 60% the x space
        constraint.weighty = 0.9; //take 70% the y space
        add(westPanel, constraint);

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
    private void create_east_layout(GridBagConstraints constraint) {
        JPanel eastPanel = new  JPanel(new GridBagLayout());
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 
                                                            2));
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 0.4; //take 40% the x space
        constraint.weighty = 1; //take 100% the y space
        add(eastPanel, constraint);

        GridBagConstraints gbc = new GridBagConstraints();

        //object information rectangle
        DrawingCanvas objInfoRect = new DrawingCanvas 
                                        (DrawingCanvas.Shape.RECTANGLE,
                                        10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.5; //take half the y space
        gbc.fill = GridBagConstraints.BOTH;//stretches in both directions
        eastPanel.add(objInfoRect, gbc);


        //a jpanel to hold the buttons
        JPanel btnPanel = new JPanel(new GridLayout(4, 2));
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        //adding some padding
        gbc.insets = new Insets(10, 10, 10, 10);
        eastPanel.add(btnPanel, gbc);

        //the arrow buttons
        JPanel yArrowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(yArrowPanel); //holds y axis arrow button(up and down)
        JPanel xArrowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
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
        JPanel zoomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(zoomPanel);
        addButtonToPanel(zoomPanel, "Z(+)", Color.GRAY, 
                                                        BorderLayout.WEST);
        addButtonToPanel(zoomPanel, "Z(-)", Color.GRAY, 
                                                        BorderLayout.EAST);

        //speed buttons
        JPanel speedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(speedPanel);
        addButtonToPanel(speedPanel, "<<", Color.GRAY, 
                                                        BorderLayout.WEST);
        addButtonToPanel(speedPanel, ">>", Color.GRAY, 
                                                        BorderLayout.EAST);
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

