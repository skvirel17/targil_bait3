package GUI.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenPanelAction extends AbstractAction {

    private JPanel base;
    private String showPanel;
    private CardLayout layout;

    public OpenPanelAction(JPanel base, String showPanel, CardLayout layout) {
        this.base = base;
        this.showPanel = showPanel;
        this.layout = layout;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        layout.show(base, showPanel);
    }
}
