package GUI.actions;

import GUI.mainScreen.SystemUsersGUI;
import GUI.panels.table_panels.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddItemAction extends AbstractAction {


    public AddItemAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button source = (Button) (e.getSource());
        TablePanel parent = (TablePanel) (source.getParent().getParent());
        parent.getContent().getSelectedRow();
        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), parent.getItemInfoPanel(), SystemUsersGUI.getCardLayout())).actionPerformed(e);
        //source.setVisible(false);
    }
}