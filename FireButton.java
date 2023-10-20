/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: AlarmButton.java
 * Date modified: 10/05/23
 * 
 * The file handles alarm button
 */

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

//class responsible for alarm button
//subclass for MyButoon
public class FireButton extends MyButton {

    private MyMap myMap; //map of the program

    public FireButton(String label, MyMap map){
        super(label);
        this.myMap = map;
        ImageIcon missileIcon = new ImageIcon("Images/missile.png");
        this.setIcon(missileIcon); //set icon on button

        //put text on bottom of Icon
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
    }


    //function tells myspaceship to acrivate alarm.
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e); //call default listener for MyButton
    }

}