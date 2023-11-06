/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: AlarmButton.java
 * Date modified: 10/05/23
 * 
 * The file handles direction buttons.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

//class responsible for direction button
//subclass for MyButoon
public class ArrowButton extends MyButton {

    private MyMap myMap; //map of the program
    private Timer timer;

    public ArrowButton(String label, MyMap map){
        super(label);
        this.myMap = map;

        //adding a mouse listener for clicks
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                timer.start();
            }

            public void mouseReleased(MouseEvent e) {
                timer.stop();
            }
        });
    }



    private void move() {

        if(this.get_button_name().equals("UP")){
            //move up
            myMap.move_items(this.get_button_name());
        } 
        else if(this.get_button_name().equals("DOWN")){
            //move down
            myMap.move_items(this.get_button_name());
        }
        else  if(this.get_button_name().equals("LEFT")){
            //move left
            myMap.move_items(this.get_button_name());
        }
        else if(this.get_button_name().equals("RIGHT")){
            //move right
            myMap.move_items(this.get_button_name());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }

}