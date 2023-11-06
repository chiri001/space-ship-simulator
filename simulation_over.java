
/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Simulation_over.java
 * Date modified: 10/28/23
 * 
 * This file contains simulation_over class. The class is responsible for 
 * telling user whether they have achieved their goal or have failed.
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class simulation_over {

    private JDialog dialog;
    private JTextArea text;
    private String fullMessage;
    private int charCount;
    private Timer timer;
    String game_state = "";
    private MyMap myMap;
    
    //constructor
    //params: a string with information whether user completed/ not the quest
    //         map -> where information are drawn.
    public simulation_over(String game_state, MyMap map){

        this.game_state = game_state;
        this.myMap = map;
        
        //Jdialog for the popup
        dialog = new JDialog();
        text = new JTextArea();
        text.setWrapStyleWord(true); //wrap to next row
        text.setLineWrap(true); //enables word wrappping
        text.setFont(new Font("monospaced", Font.BOLD, 16));
        text.setBackground(Color.black);
        text.setForeground(Color.GREEN); //text color
        dialog.add(text);
        dialog.setSize(500, 200);

        string_to_display(game_state);
        charCount = 0;

        //print the message
        timer = new Timer( 50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char curr = fullMessage.charAt(charCount); //get a letter
                text.append(Character.toString(curr)); //print to Jtextarea
                charCount++;

                if(charCount >= fullMessage.length()) {
                    timer.stop();
                }
            }
        });
    }

    //function responsible for showing the message to user
    public void showMessage() {
        //stop simulation
        myMap.stop_simulation();

        //display message
        timer.start();
        dialog.setVisible(true);
    }

    public void string_to_display(String game_state) {
        if(game_state.equals("SUCCESS")) {
            //success
            fullMessage = "Congratulations, Astronaut! \n" + 
                        "You've successfully navigated the vast cosmic expanse " + 
                        "to your destination.\n" +
                        "End of broadcast...\n";
        } else {
            //failed to reach destination
            fullMessage = "Your ship experienced a catastrophic failure! \n" + 
                        "Unfortunately Astronout, your journey has come to an end.\n"
                        + "End of broadcast...\n";
        }
    }
}
