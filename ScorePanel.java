import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Timer;

import javax.swing.*;

public class ScorePanel extends JPanel {

    private HashMap<String, Integer> distanceMap = new HashMap<>();
    private JComboBox<String> destinations;
    private Timer destination_timer;
    private JLabel distLabel;


    //constructore 
    public ScorePanel() {


        //initialize destinations
        distanceMap.put("Andromeda Galaxy (M31)", 60);
        distanceMap.put("Triangulum Galaxy (M33):", 120);
        distanceMap.put("Messier 94 (M94)", 180);
        distanceMap.put("Messier 81 (M81, Bode's Galaxy)", 240);
        distanceMap.put("Messier 82 (M82, Cigar Galaxy)", 300);

        //panel to store destination and distance ot destination
        JLabel destLabel = new JLabel("Destination: " );
        add(destLabel);
        destinations = new JComboBox<>(distanceMap.keySet().toArray(new String[0]));
        //add an event listener
        destinations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update_distance();
            }
        });
        add(destinations);

        distLabel = new JLabel("Destination:");
        add(distLabel);

        //update distance and display
        destination_timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrement_distance();
            }
        });

        //start timer
        destination_timer.start();

    }
    
    private void update_distance() {
        String selectedDestination = (String) destinations.getSelectedItem();
        int distance = distanceMap.get(selectedDestination); //get dist to dest
        //show distance to destination
        distLabel.setText(distance + "LY");
    }

    private void decrement_distance() {

        String selectedDestination = (String) destinations.getSelectedItem();
        int currDist = distanceMap.get(selectedDestination); //get dist to dest

        //reduce distance with timer
        if(currDist > 0) {
            currDist--;
            //update distance to location
            distanceMap.put(selectedDestination, currDist);
            update_distance();
        }
    }
}




