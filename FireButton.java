/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: FireButton.java
 * Date modified: 10/05/23
 * 
 * The file handles fire button.
 */

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

//class responsible for fire button
public class FireButton extends MyButton {

    private MyMap myMap; //map of the program

    public FireButton(String label, MyMap map){
        super(label);
        this.myMap = map;

        //add an image icon for button
        ImageIcon missileIcon = new ImageIcon("Images/missile.png");
        this.setIcon(missileIcon); //set icon on button

        //put text on bottom of Icon
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
    }


    //function tells myspaceship to release a missile
    @Override
    public void actionPerformed(ActionEvent e) {
        //initiate missile launch
        myMap.activate_missile();
    }

}