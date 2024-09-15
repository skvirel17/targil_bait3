package view.GUI.panels;

import java.awt.*;

public class DashBoardPanel extends BasePanel{
    public static final String DASHBOARD_PANEL = "DASHBOARD_PANEL";

    public DashBoardPanel() {
        super();

        this.setLayout(new BorderLayout());
    }

    public String getPanelStringKey() {
        return DASHBOARD_PANEL;
    }
}
