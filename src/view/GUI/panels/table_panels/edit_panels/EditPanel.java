package view.GUI.panels.table_panels.edit_panels;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class EditPanel extends JPanel {
    private JPanel prevPanel;

    abstract void clearPanel();

    public EditPanel(JPanel prev) {
        super();
        this.prevPanel = prev;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                clearPanel();
                revalidate();
                repaint();
            }
        });
    }
}
