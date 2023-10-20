/*
 * Name : Rennie Kipchirchir
 * Project: Spaceship Simulator
 * File: DropDOwn.java
 * Date modified: 10/05/23
 * 
 * The file handles drop down for draw object
 */

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

//class responsible for drop down
public class DropDown extends JComboBox<String>  {

    private ObjectDetails detailPanel;

    public DropDown(ObjectDetails detailPanel){

        //add items to draw
        addItem("Planet");
        addItem("Spaceship");
        addItem("Asteroid");
        
        //add an action listener to the drop down
        this.addActionListener(this);
        this.detailPanel = detailPanel;
    }


    //function tells myspaceship to acrivate alarm.
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String)this.getSelectedItem(); //convert to string

        //set the item to draw in object details
        detailPanel.set_item_to_draw(selectedItem);

        //update content since planets has extra parameter than the rest
        detailPanel.updateContent(selectedItem);
    }

}