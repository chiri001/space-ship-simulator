/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: Mypanel.java
 * Date modified: 10/28/23
 * 
 * This file contains ScorePanel. Defines the structure and look of the score
 * panel
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class ScorePanel extends JPanel {

    private HashMap<String, Integer> distanceMap = new HashMap<>();
    private JComboBox<String> destinations;
    private Timer destination_timer;
    private JLabel distLabel;
    private MyMap myMap;

    private int a_speed = 30;
    private int t_speed = 60;
    private int m94_speed = 120;
    private int m81_speed = 180;
    private int m82_speed = 300;


    //constructore 
    public ScorePanel(MyMap map) {

        this.myMap = map;

        //initialize destinations
        distanceMap.put("Andromeda Galaxy (M31)", a_speed);
        distanceMap.put("Triangulum Galaxy (M33):", t_speed);
        distanceMap.put("Messier 94 (M94)", m94_speed);
        distanceMap.put("Messier 81 (M81, Bode's Galaxy)", m81_speed);
        distanceMap.put("Messier 82 (M82, Cigar Galaxy)", m82_speed);

        //panel to store destination and distance ot destination
        JLabel destLabel = new JLabel("Destination: " );
        add(destLabel);
        destinations = new JComboBox<>(distanceMap.keySet().toArray(new String[0]));
        //add an event listener
        destinations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update_distance(); //displays distance and update new distance
            }
        });
        add(destinations);

        //destination label
        distLabel = new JLabel("Destination:");
        add(distLabel);

        //update distance
        destination_timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrement_distance();
            }
        });
    }

    //displays the new distance on screen
    private void update_distance() {
        
        String selectedDestination = (String) destinations.getSelectedItem();
        int distance = distanceMap.get(selectedDestination); //get dist to dest
        //show distance to destination
        distLabel.setText(distance + "LY");
    }

    //starts the timer for calculating timer to destination
    public void start_destination_timer() {
        destination_timer.start();
    }

    //returns the destinationtimer
    public Timer get_destination_timer() {
        return destination_timer;
    }



    //stops the timer after arriving at destination/ when called
    public void stop_destination_timer() {
        destination_timer.stop();
    }

    //reduces distance to destination over time to simulate movement
    private void decrement_distance() {

        String selectedDestination = (String) destinations.getSelectedItem();
        int currDist = distanceMap.get(selectedDestination); //get dist to dest

        //reduce distance with timer
        if(currDist > 0) {
            currDist--;
            //update distance to location
            distanceMap.put(selectedDestination, currDist);
            update_distance();
        } else {
            //simulation over --> success arrived destination
            // give the class a message
            simulation_over end_sim = new simulation_over("SUCCESS", 
                                        myMap);
            //stop timer
            stop_destination_timer();
            
            //reset destination
            distanceMap.put("Andromeda Galaxy (M31)", a_speed);
            distanceMap.put("Triangulum Galaxy (M33):", t_speed);
            distanceMap.put("Messier 94 (M94)", m94_speed);
            distanceMap.put("Messier 81 (M81, Bode's Galaxy)", m81_speed);
            distanceMap.put("Messier 82 (M82, Cigar Galaxy)", m82_speed);

            //show message
            end_sim.showMessage();
        }
    }
}




