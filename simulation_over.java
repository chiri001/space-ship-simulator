




import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
    
    public simulation_over(String game_state, MyMap map){

        this.game_state = game_state;
        this.myMap = map;
        

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
                text.append(Character.toString(curr));
                charCount++;

                if(charCount >= fullMessage.length()) {
                    timer.stop();
                }
            }
        });
    }

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
                        "to your destination...\n";
        } else {
            //failed to reach destination
            fullMessage = "Your ship experienced a catastrophic failure! \n" + 
                        "Unfortunately Astronout, your journey has come to an end..."; 
        }
    }
}
