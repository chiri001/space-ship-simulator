

import java.awt.*;
import javax.swing.*;

/*class that creates a window frame for the program when called in main */
public class MyPanel extends JPanel {
    /*constructor */
    public MyPanel() {

        setLayout(new BorderLayout());/*new layout for the panel */
        create_south_layout();

    }

    private void create_south_layout() {
        /*create a new jpanel with given dimensions */
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(400, 50));

        southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 
                                                                5)); 

        MyButton start_btn = new MyButton("START");
        southPanel.add(start_btn);
        MyButton stop_btn = new MyButton("STOP");
        southPanel.add(stop_btn);
        MyButton reset_btn = new MyButton("RESET");
        southPanel.add(reset_btn);
        MyButton new_path_btn = new MyButton("NEW_PATH");
        southPanel.add(new_path_btn);

        /*a wrapper pannel */
        JPanel wrapperPanel = new JPanel(new FlowLayout());
        wrapperPanel.add(southPanel);


        /*add southpanel to south layout of this panel */
        add(wrapperPanel, BorderLayout.SOUTH); 
    
    }
    
}