package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DepartmentOptionDTO;
import GUI.dto.TreatmentListOptionDTO;
import GUI.panels.BasePanel;
import model.Department;
import model.Treatment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditMedicalProblemPanel extends EditPanel{

    public static final String EDIT_MEDICAL_PROBLEM_PANEL = "EDIT_MEDICAL_PROBLEM_PANEL";

    private JComboBox<DepartmentOptionDTO> createDepartmentContent() {
        JComboBox<DepartmentOptionDTO> departmentContent = new JComboBox<>();
        for (Map.Entry department : hospital.getDepartments().entrySet()){
            departmentContent.addItem(DepartmentOptionDTO.map((Department) department.getValue()));
        }
        return departmentContent;
    }

    public EditMedicalProblemPanel(BasePanel prev){
        super(prev);

        JLabel medicalProblemLabel = new JLabel("Medical problem name:");
        JTextField medicalProblemText = new JTextField();

        JLabel departmentLabel = new JLabel("Department:");
        JComboBox<DepartmentOptionDTO> departmentContent = createDepartmentContent();

        JLabel treatmentLabel = new JLabel("Treatments:");
        JScrollPane activeTreatmentPane = new JScrollPane();
        DefaultListModel<String> activeTreatmentListModel = new DefaultListModel<>();
        JList<String> activeTreatmentList = new JList<>(activeTreatmentListModel);
        activeTreatmentPane.add(activeTreatmentList);


        DefaultListModel<String> allTreatmentListModel = new DefaultListModel<>();
        JList<String> allTreatmentList = new JList<>(allTreatmentListModel);
        JScrollPane allTreatmentPane = new JScrollPane(allTreatmentList);

        for (Treatment member : hospital.getTreatments().values()) {
            allTreatmentListModel.addElement(TreatmentListOptionDTO.map(member).toString());
        }

        JButton button = new JButton("Select Sublist");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String targetPanelKey = EDIT_MEDICAL_PROBLEM_PANEL;
                new OpenPanelAction(getMainScreen(), targetPanelKey, getCardLayout()).actionPerformed(e);
            }
        });


        JButton saveMedicalProblemButton = new JButton("Save");
        saveMedicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Doctor newDoctor = getNewInfo();
                JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Changes will be lost! \nDo you want to continue?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                }
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.Group treatmentGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeTreatmentPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(button))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allTreatmentPane));
        GroupLayout.Group treatmentGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeTreatmentPane)
                        .addComponent(button)
                        .addComponent(allTreatmentPane));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(medicalProblemLabel)
                                .addComponent(departmentLabel)
                                .addComponent(treatmentLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(medicalProblemText)
                                .addComponent(departmentContent)
                                .addGroup(treatmentGroupHor)
                                .addComponent(saveMedicalProblemButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(medicalProblemLabel)
                                .addComponent(medicalProblemText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(departmentLabel)
                                .addComponent(departmentContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(treatmentLabel)
                                .addGroup(treatmentGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveMedicalProblemButton))

        );
    }
}

