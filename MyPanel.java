/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Mypanel.java
 * Date modified: 09/29/23
 * 
 * This file contains MyPanel calss that creates a new JPanel for use
 */
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

/*class that creates a panel for drawing and adding other items */
public class MyPanel extends JPanel {


    private JPanel eastPanel;
    /* constructor 
     * creates a new layout for the panel
     * creates other layouts for the panel
    */
    public MyPanel() {

        setLayout(new GridBagLayout());/*new layout for the panel */

        //using GridBagConstraints to position panels in the frame
        GridBagConstraints constraint = new GridBagConstraints();

        //panel to hold the west and southpanel
        JPanel westSouthPanel = new JPanel(new GridBagLayout());

        //functions to set the panels of the window
        MyMap map = create_west_layout(westSouthPanel);
        create_south_layout(westSouthPanel, map);
        create_north_layout(westSouthPanel, map);

        //adding constraints for the westsouth panel
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.weightx = 0.6; // takes 60% of the width
        constraint.weighty = 1;   // takes 100% of the height
        constraint.fill = GridBagConstraints.BOTH; //stretch both sides
        add(westSouthPanel, constraint);

        //get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        if (width > 700 && height > 500){
            //creates the east panel only if width and height are greater than
            //600px
            create_east_layout(constraint, map); 
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustLayout(constraint, map);
            }
        });
    }

    private void adjustLayout(GridBagConstraints constraint, MyMap map){
        Dimension size = getSize();

        if(size.width < 700 || size.height < 500){
            //remove east layout if present
            if(eastPanel != null){
                remove(eastPanel);
                eastPanel = null;
            } 
        } else {
        
            //return east layout if absent
            if(eastPanel == null){
                //return the east layout
                create_east_layout(constraint, map);
            }
        }
        revalidate(); //revalidate panel to adjust new changes

        repaint(); //repaint to reflect changes
    }

    /*function that creates the layout for the west side of the panel
     * takes a jpanel to be added to
     * returns a map created for the panel
     */
    private MyMap create_west_layout(JPanel container) {
        JPanel westPanel = new  JPanel(new GridBagLayout());
        westPanel.setBorder(BorderFactory.createLineBorder(
                                                    Color.BLACK, 2));

        //circle drawing where the map will showcase
        MyMap map = new MyMap();
        GridBagConstraints mapConstraints = new GridBagConstraints();
        mapConstraints.fill = GridBagConstraints.BOTH;
        mapConstraints.weightx = 1.0;
        mapConstraints.weighty = 1.0; //circle to cover available space
        westPanel.add(map, mapConstraints);

        GridBagConstraints westConstraints = new GridBagConstraints();
        westConstraints.gridx = 0;
        westConstraints.gridy = 1;
        westConstraints.fill = GridBagConstraints.BOTH;
        westConstraints.weightx = 0.6; //cover 60% of width
        westConstraints.weighty = 0.92; //cover 95% of height

        container.add(westPanel, westConstraints);

        return map;
    }

    /*function creates the layout of the south panel
     * It takes no parameter
     */
    private void create_south_layout(JPanel container, MyMap map) {
        /*create a new jpanel with given dimensions */
        JPanel southPanel = new  JPanel(new GridLayout(1, 0));
        southPanel.setBorder(BorderFactory.createLineBorder(
                                                    Color.BLACK, 2));

        //calls a function that creates buttons for the bottom panel
        addButtonToPanel(southPanel, "START", Color.GREEN, map);
        addButtonToPanel(southPanel, "STOP", Color.RED, map);
        addButtonToPanel(southPanel, "RESET", Color.GRAY, map);
        addButtonToPanel(southPanel, "FIRE", Color.GRAY, map);
        addButtonToPanel(southPanel, "ALARM", Color.RED, map);

        GridBagConstraints southConstraints = new GridBagConstraints();
        southConstraints.gridx = 0;
        southConstraints.gridy = 2;
        southConstraints.fill = GridBagConstraints.BOTH;
        southConstraints.weightx = 0.6;
        southConstraints.weighty = 0.05;

        container.add(southPanel, southConstraints);

    }

    private void create_north_layout(JPanel container, MyMap map) {
        /*create a new jpanel with given dimensions */
        JPanel northPanel = new  JPanel(new GridLayout(1, 0));
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //score panel
        ScorePanel score_panel = new ScorePanel();

        //add to northpanel
        northPanel.add(score_panel);

        //style for stating positition to put north panel
        GridBagConstraints northConstraints = new GridBagConstraints();
        northConstraints.gridx = 0; //top of page
        northConstraints.gridy = 0; //left most
        northConstraints.fill = GridBagConstraints.BOTH; //stretch both ways
        northConstraints.weightx = 0.6; //occupy 60% of width
        northConstraints.weighty = 0.03; //occupy 5% of height

        

        container.add(northPanel, northConstraints);
    }

    /*function that creates buttons and adds it to panel 
     * NOTE: depends on MyButton class implementation
    */
    private void addButtonToPanel(JPanel panel, String btnTxt, Color color,
                                MyMap map) {

        if("START".equals(btnTxt)) {
            StartButton btn = new StartButton(btnTxt, map);
            btn.set_btn_color(color);
            btn.set_size(80, 40);
            panel.add(btn);
        } 
        else if("STOP".equals(btnTxt)) {
            StopButton btn = new StopButton(btnTxt, map);
            btn.set_btn_color(color);
            btn.set_size(80, 40);
            panel.add(btn);
        } 
        else if("ALARM".equals(btnTxt)) {
            AlarmButton btn = new AlarmButton(btnTxt, map);
            btn.set_btn_color(color);
            btn.set_size(80, 40);
            panel.add(btn);
        } 
        else if("RESET".equals(btnTxt)) {
            ResetButton btn = new ResetButton(btnTxt, map);
            btn.set_btn_color(color);
            btn.set_size(80, 40);
            panel.add(btn);
        } else if("FIRE".equals(btnTxt)) {
            FireButton btn = new FireButton(btnTxt, map);
            btn.set_btn_color(color);
            btn.set_size(80, 40);
            panel.add(btn);
        }
    }

    /*
     * Function that creates the east Layout of the panel
     * takes no parameters
     */
    private void create_east_layout(GridBagConstraints constraint, MyMap map) {
        eastPanel = new  JPanel(new GridBagLayout());
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 
                                                            2));
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 0.4; //take 40% the x space
        constraint.weighty = 1; //take 100% the y space
        add(eastPanel, constraint);

        GridBagConstraints gbc = new GridBagConstraints();

        //a jpanel to hold the buttons
        JPanel btnPanel = new JPanel(new GridLayout(4, 2));
        gbc.gridy = 0;
        gbc.weighty = 0.7;
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
                                                        BorderLayout.WEST, map);
        addButtonToPanel(yArrowPanel, "DOWN", Color.GRAY, 
                                                        BorderLayout.EAST, map);
        addButtonToPanel(xArrowPanel, "LEFT", Color.GRAY, 
                                                        BorderLayout.EAST, map);
        addButtonToPanel(xArrowPanel, "RIGHT", Color.GRAY, 
                                                        BorderLayout.WEST, map);

        //zoom buttons
        JPanel zoomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(zoomPanel);
        addButtonToPanel(zoomPanel, "Z(+)", Color.GRAY, 
                                                        BorderLayout.WEST, map);
        addButtonToPanel(zoomPanel, "Z(-)", Color.GRAY, 
                                                        BorderLayout.EAST, map);

        //speed buttons
        JPanel speedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(speedPanel);
        addButtonToPanel(speedPanel, "<<", Color.GRAY, 
                                                        BorderLayout.WEST,map);
        addButtonToPanel(speedPanel, ">>", Color.GRAY, 
                                                        BorderLayout.EAST,map);
        
        //add drawing panel
        JPanel addPanel = new JPanel(new BorderLayout());
        addPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 
                                                            2));
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        eastPanel.add(addPanel, gbc);
        //add a title
        JLabel title = new JLabel("Draw Object");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        addPanel.add(title, BorderLayout.NORTH);

        //add drop down
        ObjectDetails detailPanel = new ObjectDetails(map);
        DropDown dropdown = new DropDown(detailPanel);
        addPanel.add(detailPanel, BorderLayout.SOUTH);
        addPanel.add(dropdown, BorderLayout.CENTER);
    }

    /*function that creates buttons and adds it to panel 
     * depends on the implementation of MyButton class
     * parameters: a jpanel to add buttons to, a button text, a color for 
     * the button, a string that shows where button should be on the panel
    */
    private void addButtonToPanel(JPanel panel, String btnTxt, Color color, 
                            String constraint, MyMap map) {

        if(">>".equals(btnTxt)) {
            ForwardButton btn = new ForwardButton(btnTxt, map);
            btn.set_btn_color(color); //set button color
            btn.set_size(60, 50);
            panel.add(btn, constraint); //add to panel and set constraint
        } 
        else if("<<".equals(btnTxt)) {
            RewindButton btn = new RewindButton(btnTxt, map);
            btn.set_btn_color(color); //set button color
            btn.set_size(60, 50);
            panel.add(btn, constraint); //add to panel and set constraint
        } 
        else if(("UP".equals(btnTxt)) || ("DOWN".equals(btnTxt)) ||
                ("LEFT".equals(btnTxt)) || ("RIGHT".equals(btnTxt)) ) {
            ArrowButton btn = new ArrowButton(btnTxt, map);
            btn.set_btn_color(color); //set button color
            btn.set_size(60, 50);
            panel.add(btn, constraint); //add to panel and set constraint
        }
        else {
            //other buttons
            MyButton btn = new MyButton(btnTxt);
            btn.set_btn_color(color); //set button color
            btn.set_size(60, 50);
            panel.add(btn, constraint); //add to panel and set constraint
        }
        

    }
    
}
