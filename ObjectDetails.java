/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ObjectDetails.java
 * Date modified: 10/05/23
 * 
 * The file handles the class ObjectDetails and is responsible for taking
 * informatin from user baout adding a drawing
 */

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ObjectDetails extends JPanel implements ActionListener {

    private MyMap myMap;
    private JTextField xTextField = new JTextField(10);
    private JTextField yTextField = new JTextField(10);
    private JLabel pLabel = new JLabel("Satelites: ");
    private JTextField sTextField = new JTextField(10);
    private JButton btn = new JButton("SUBMIT");
    private String to_draw = "Planet";

    public ObjectDetails(MyMap map){

        this.myMap = map;
        btn.addActionListener(this); //add listener to submit button

        //set layout for adding the parameters
        setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5,5,5,5); //padding

        //common inputs
        addComponent(this,new JLabel("X Offset(1.5 - 4.5):"), 0, 0, constraint);
        addComponent(this, xTextField, 1, 0, constraint); //right of label
        addComponent(this, new JLabel("Y Offset(1.5 - 4.5):"), 0, 1, constraint); //blow x label
        addComponent(this, yTextField, 1, 1, constraint); //right of y label
        addComponent(this, btn, 0, 3, constraint); //2 level below y label

        //unique element for planet(number of satelites)
        addComponent(this, pLabel, 0, 2, constraint); //below y label
        addComponent(this, sTextField, 1, 2, constraint); //beside satelite label
    }

    //adds a component to the drawing panel taking not of the grid and 
    //constraint
    //params include: Container to add items, component to add, the x an y
    //grid posn of the component and the constraint to be applied on all 
    //components
    public void addComponent(Container cont, Component comp, int x , int y, 
                            GridBagConstraints constrt) {
        constrt.gridx = x; //posn to occupy on the x axis of layout
        constrt.gridy = y; //posn to occupy on y axis of layout
        cont.add(comp, constrt); //add to panel
    }

    //function updates the content of the display panel depending on choosen
    //object
    public void updateContent(String object) {

        if ("Planet".equals(object)) {
            //make the satelite number input available
            pLabel.setVisible(true);
            sTextField.setVisible(true);
        } else {
            //remove the satelite number input
            pLabel.setVisible(false);
            sTextField.setVisible(false);
        }

        revalidate(); //revalidating the panel so that updates reflect
        repaint(); //repaint panel
    }

    //the function sets the object needed to be drawn
    public void set_item_to_draw(String object){
        this.to_draw = object; //item to draw
    }


    public void actionPerformed(ActionEvent e) {
        //get the values
        double x_offset = Double.parseDouble(xTextField.getText());
        double y_offset = Double.parseDouble(yTextField.getText());
        int satelite = 0;

        if(!sTextField.getText().isEmpty()){
            //take satelite input
            satelite = Integer.parseInt(sTextField.getText());
        }

        //call map to draw
        myMap.addDrawings(x_offset, y_offset, satelite, to_draw);

        //reset inputs
        resetInputs();
    }

    //resets all inputs
    public void resetInputs() {
        //resets input fields
        xTextField.setText("");
        yTextField.setText("");
        sTextField.setText("");
    }

}