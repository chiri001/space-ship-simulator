/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: ObjectDetails.java
 * Date modified: 10/05/23
 * 
 * The file handles taking information about object to draw
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

//the end button is the button responsible for ending the simulation
//it is a subclass of Mybutton
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
        btn.addActionListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(5,5,5,5);

        //common inputs
        addComponent(this,new JLabel("X Offset"), 0, 0, constraint);
        addComponent(this, xTextField, 1, 0, constraint);
        addComponent(this, new JLabel("Y Offset"), 0, 1, constraint);
        addComponent(this, yTextField, 1, 1, constraint);
        addComponent(this, btn, 0, 3, constraint);

        //unique element for planet
        addComponent(this, pLabel, 0, 2, constraint);
        addComponent(this, sTextField, 1, 2, constraint);
    }

    public void addComponent(Container cont, Component comp, int x , int y, 
                            GridBagConstraints constrt) {
        constrt.gridx = x;
        constrt.gridy = y;
        cont.add(comp, constrt);
    }

    public void updateContent(String object) {

        if ("Planet".equals(object)) {
            pLabel.setVisible(true);
            sTextField.setVisible(true);
        } else {
            pLabel.setVisible(false);
            sTextField.setVisible(false);
        }
        revalidate();
        repaint();
    }

    public void set_item_to_draw(String object){
        this.to_draw = object;
    }


    public void actionPerformed(ActionEvent e) {
        //get the values
        double x_offset = Double.parseDouble(xTextField.getText());
        double y_offset = Double.parseDouble(yTextField.getText());
        int satelite = 0;
        if(!sTextField.getText().isEmpty()){
            satelite = Integer.parseInt(sTextField.getText());
        }

        //call map to draw
        myMap.addDrawings(x_offset, y_offset, satelite, to_draw);

        //reset inputs
        resetInputs();
    }

    public void resetInputs() {
        xTextField.setText("");
        yTextField.setText("");
        sTextField.setText("");
    }

}