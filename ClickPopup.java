/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Mypanel.java
 * Date modified: 10/28/23
 * 
 * This file contains ClickPopup calss that creates a new popup when an item
 * on the map is clicked
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClickPopup extends JDialog implements ActionListener{

    private JTextField speedInput;
    JComboBox<String> colorOptions;
    DrawingCanvas obj;
    int speed = 0;
    String selected_color = "BLACK";

    //constructor
    //parameters: name of the drawing, speed of the drawing, and the drawing
    public ClickPopup(String name, int speed, DrawingCanvas obj){

        this.obj = obj;

        //create a popup new dialog
        setTitle("Ship Properties");
        setSize(200, 200);
        setLayout(new GridLayout(0, 2));

        //highlights name of object clicked
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(nameLabel); //add to popup box
        add(new JLabel(name));

        //add speed of obj
        add(new JLabel("Speed: "));
        add(new JLabel(String.valueOf(speed)));


        //allow to update color and speed
        add(new JLabel("New Speed: "));
        speedInput = new JTextField();
        add(speedInput);

        //color collection
        add(new JLabel("New Color: "));
        colorOptions = new JComboBox<>(new String[]{
            "RED", "GREEN", "BLUE", "MAGENTA", "CYAN", "YELLOW", "BLACK", 
            "WHITE", "GRAY", "DARK_GRAY", "LIGHT_GRAY", "ORANGE", "PINK"
        });
        add(colorOptions);

        JButton btn = new JButton("SUBMIT");
        btn.addActionListener(this);

        JButton btn_2 = new JButton("Cancel");
        btn_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose(); //close the popup
            }
        });

        add(btn);
        add(btn_2);

        pack(); //fit in screen
        setLocationRelativeTo(null); //center the dialog box
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //close on submit
    }

    //action performed for the submit button
    public void actionPerformed(ActionEvent e) {
        try {
            //get the values
            speed = Integer.parseInt(speedInput.getText());
            selected_color = (String) colorOptions.getSelectedItem();

            obj.set_color(string_to_color(selected_color));
            obj.set_speed(speed);
            dispose();
        } catch(NumberFormatException ex){
            System.out.println("Error ocurring at click popup, cna't update speed/color");
            return; // do nothing
        }   
    }

    //function converts a given string to corresponding color
    //params include the name of the color
    private Color string_to_color(String color) {
        switch (color) {
            case "RED":
                return Color.RED;
            case "GREEN":
                return Color.GREEN;
            case "BLUE":
                return Color.BLUE;
            case "MAGENTA":
                return Color.MAGENTA;
            case "CYAN":
                return Color.CYAN;
            case "YELLOW":
                return Color.YELLOW;
            case "BLACK":
                return Color.BLACK;
            case "WHITE":
                return Color.WHITE;
            case "GRAY":
                return Color.GRAY;
            case "DARK_GRAY":
                return Color.DARK_GRAY;
            case "LIGHT_GRAY":
                return Color.LIGHT_GRAY;
            case "ORANGE":
                return Color.ORANGE;
            case "PINK":
                return Color.PINK;
            default:
                return null; 
        }
    }
}