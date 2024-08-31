package GUI.panels.table_panels;

import model.Treatment;

import java.util.Map;

public class TreatmentsPanel extends TablePanel {
    public static final String TREATMENTS_PANEL = "TREATMENTS_PANEL";

    private static final Object[] columns = {"SERIAL_NUMBER", "DESCRIPTION"};

    public TreatmentsPanel(Map<Integer, Treatment> treatments) {
        super(mapData(treatments), columns, "");
    }

    private static Object[][] mapData(Map<Integer, Treatment> map) {
        Object[][] data = new Object[map.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Treatment> entry : map.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().getDescription();
            i++;
        }

        return data;
    }
}
