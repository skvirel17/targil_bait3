package GUI.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditItemAction extends AbstractAction {

    private DefaultListModel<String> listModel;
    private int selectedIndex;
    private String newItem;

    public EditItemAction(DefaultListModel<String> listModel, int selectedIndex, String newItem) {
        this.listModel = listModel;
        this.selectedIndex = selectedIndex;
        this.newItem = newItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedIndex >= 0 && selectedIndex < listModel.getSize()) {
            listModel.set(selectedIndex, newItem);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid item to edit.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}