package view.GUI.actions;

import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.panels.table_panels.TablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddItemAction extends AbstractAction {


    public AddItemAction() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) (e.getSource());
        TablePanel parent = (TablePanel) (source.getParent().getParent());
        parent.getContent().getSelectedRow();
        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), parent.getItemInfoPanel(), SystemUsersGUI.getCardLayout())).actionPerformed(e);
    }
}