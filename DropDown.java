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
//subclass for MyButoon
public class DropDown extends JComboBox<String> {

    private ObjectDetails detailPanel;

    public DropDown(ObjectDetails detailPanel){
        addItem("Planet");
        addItem("Spaceship");
        addItem("Asteroid");
        

        this.addActionListener(this);
        this.detailPanel = detailPanel;
    }


    //function tells myspaceship to acrivate alarm.
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String)this.getSelectedItem();
        detailPanel.set_item_to_draw(selectedItem);
        detailPanel.updateContent(selectedItem);
    }

}