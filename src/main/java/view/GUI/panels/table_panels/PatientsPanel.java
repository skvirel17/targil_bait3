package view.GUI.panels.table_panels;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;

import view.GUI.actions.OpenPanelAction;
import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.panels.table_panels.edit_panels.EditPatientsPanel;
import model.Patient;
import utils.UtilsMethods;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static view.GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static view.GUI.mainScreen.SystemUsersGUI.hospital;
import static view.GUI.panels.table_panels.edit_panels.EditPatientsPanel.EDIT_PATIENT_PANEL;

public class PatientsPanel extends TablePanel {
    private static JPopupMenu patientPopupMenu;
    private static JMenuItem exportToDocItem;
    public static final String PATIENTS_PANEL = "PATIENTS_PANEL";

    private static final Object[] columns = {"ID", "FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "ADDRESS", "PHONE_NUMBER",
            "EMAIL", "GENDER", "HELTH_FUND", "BIOLOGICAL_GENDER"};

    public JPanel itemPanel;

    public PatientsPanel(Map<Integer, Patient> patients) {
        super(mapData(patients), columns, EDIT_PATIENT_PANEL);

        //Adding Edit button action
        ((JButton) ((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        Patient editPatient = hospital.getRealPatient(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditPatientsPanel) itemPanel).fillFromObject(editPatient);

                    }

                }
        );

        patientPopupMenu = new JPopupMenu();
        exportToDocItem = new JMenuItem("Export to DOC");
        patientPopupMenu.add(exportToDocItem);

        this.getContent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() && getContent().getSelectedRow() != -1) {
                    patientPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        exportToDocItem.addActionListener(e -> {
            if (this.getContent().getSelectedRow() != -1) {
                int selectedRow = this.getContent().getSelectedRow();
                Integer patientId = (Integer) this.getContent().getModel().getValueAt(selectedRow, 0);
                Patient selectedPatient = hospital.getRealPatient(patientId);
                exportPatientToDoc(selectedPatient);
            }
        });

        //Adding Delete button action
        ((JButton) ((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removePatient(
                                hospital.getRealPatient(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getPatients());
                    }
                }
        );
    }

    private static Object[][] mapData(Map<Integer, Patient> dataMap) {
        Object[][] data = new Object[dataMap.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Patient> entry : dataMap.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().getFirstName();
            data[i][2] = entry.getValue().getLastName();
            data[i][3] = UtilsMethods.format(entry.getValue().getBirthDate());
            data[i][4] = entry.getValue().getAddress();
            data[i][5] = entry.getValue().getPhoneNumber();
            data[i][6] = entry.getValue().getEmail();
            data[i][7] = entry.getValue().getGender().equals("M") ? "MALE" :
                    entry.getValue().getGender().equals("F") ? "FEMALE" :
                            entry.getValue().getGender().equals("O") ? "OTHER" : entry.getValue().getGender();
            data[i][8] = entry.getValue().getHealthFund().toString();
            data[i][9] = entry.getValue().getBiologicalSex().toString();
            i++;
        }

        return data;
    }

    @Override
    public String getPanelStringKey() {
        return PATIENTS_PANEL;
    }

    public void reloadData(Map<Integer, Patient> patients) {
        reloadData(mapData(patients), columns);
    }

    private void exportPatientToDoc(Patient patient) {

        try  {
            Document doc = new Document();
            DocumentBuilder builder = new DocumentBuilder(doc);

            builder.write("Patient Information");
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("ID: " + patient.getId());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("First Name: " + patient.getFirstName());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Last Name: " + patient.getLastName());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Birth Date: " + UtilsMethods.format(patient.getBirthDate()));
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Address: " + patient.getAddress());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Phone Number: " + patient.getPhoneNumber());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Email: " + patient.getEmail());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Gender: " + patient.getGender());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Health Fund: " + patient.getHealthFund().toString());
            builder.insertBreak(BreakType.LINE_BREAK);
            builder.write("Biological Gender: " + patient.getBiologicalSex().toString());
            builder.insertBreak(BreakType.LINE_BREAK);

            doc.save("info_" + patient.getId() + ".docx");


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to process the patient document.");
        }
    }
}
