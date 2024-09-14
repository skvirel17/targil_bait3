package view.GUI.panels;

import view.GUI.tools.Methods;

import static view.GUI.mainScreen.SystemUsersGUI.hospital;

public class HowManyVisitBeforePanel extends BasePanel{
    public static final String HOW_MANY_VISIT_BEFORE_PANEL = "HOW_MANY_VISIT_BEFORE_PANEL";
    public HowManyVisitBeforePanel(){
        super();
        Methods.placeComponents(this, hospital);
    }
}
