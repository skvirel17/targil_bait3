package GUI.panels;

import GUI.tools.Method2;
import GUI.tools.Methods;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class HowManyVisitBeforePanel extends BasePanel{
    public static final String HOW_MANY_VISIT_BEFORE_PANEL = "HOW_MANY_VISIT_BEFORE_PANEL";
    public HowManyVisitBeforePanel(){
        super();
        Methods.placeComponents(this, hospital);
    }
}
