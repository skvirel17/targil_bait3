package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DepartmentOptionDTO;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.dto.TreatmentListOptionDTO;
import GUI.panels.BasePanel;
import model.Department;
import model.MedicalProblem;
import model.StaffMember;
import model.Treatment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditMedicalProblemPanel extends EditPanel{

    public static final String EDIT_MEDICAL_PROBLEM_PANEL = "EDIT_MEDICAL_PROBLEM_PANEL";
    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel departmentLabel;
    private JComboBox<DepartmentOptionDTO> departmentContent;
    private JLabel treatmentLabel;
    private JScrollPane activeTreatmentPane;
    private DefaultListModel<TreatmentListOptionDTO> activeTreatmentListModel;
    private JList<TreatmentListOptionDTO> activeTreatmentList;
    private DefaultListModel<TreatmentListOptionDTO> allTreatmentListModel;
    private JList<TreatmentListOptionDTO> allTreatmentList;
    private JScrollPane allTreatmentPane;
    private JButton button;
    private JButton saveMedicalProblemButton;
    private JButton backButton;
    private GroupLayout layout;


    public EditMedicalProblemPanel(BasePanel prev) {
        super(prev);

        buildNameField();
        buildDepartmentField();
        buildTreatmentField();
        buildSaveButton(prev);
        buildBackButton(prev);

        compose();

    }
    private void buildNameField(){
        nameLabel = new JLabel("Medical problem name:");
        nameText = new JTextField();
    }

    private void buildDepartmentField(){
        departmentLabel = new JLabel("Department:");
        departmentContent = createDepartmentContent();
    }


    private void buildTreatmentField(){
        treatmentLabel = new JLabel("Treatments:");
        activeTreatmentListModel = new DefaultListModel<>();
        activeTreatmentList = new JList<>(activeTreatmentListModel);
        activeTreatmentPane = new JScrollPane(activeTreatmentList);

        allTreatmentListModel = new DefaultListModel<>();
        allTreatmentList = new JList<>(allTreatmentListModel);
        allTreatmentPane = new JScrollPane(allTreatmentList);

        for (Treatment member : hospital.getTreatments().values()) {
            allTreatmentListModel.addElement(TreatmentListOptionDTO.map(member));
        }

        button = new JButton("Select Sublist");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String targetPanelKey = EDIT_MEDICAL_PROBLEM_PANEL;
                new OpenPanelAction(getMainScreen(), targetPanelKey, getCardLayout()).actionPerformed(e);
            }
        });
    }

    private void buildSaveButton(BasePanel prev){
        saveMedicalProblemButton = new JButton("Save");
        saveMedicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Doctor newDoctor = getNewInfo();
                JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
            }
        });
    }

    private void buildBackButton(BasePanel prev){
        backButton = new JButton("back");
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
    }

    private void compose(){
        layout = new GroupLayout(this);
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
                                .addComponent(nameLabel)
                                .addComponent(departmentLabel)
                                .addComponent(treatmentLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(departmentContent)
                                .addGroup(treatmentGroupHor)
                                .addComponent(saveMedicalProblemButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameText))
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


    private JComboBox<DepartmentOptionDTO> createDepartmentContent() {
        JComboBox<DepartmentOptionDTO> departmentContent = new JComboBox<>();
        for (Map.Entry department : hospital.getDepartments().entrySet()){
            departmentContent.addItem(DepartmentOptionDTO.map((Department) department.getValue()));
        }
        return departmentContent;
    }

    public void fillFromObject(MedicalProblem medicalProblem) {
        clearPanel();
        nameText.setText(medicalProblem.getName());
        for (int i = 0; i < departmentContent.getItemCount(); i++) {
            if (departmentContent.getItemAt(i).getNumber() == medicalProblem.getDepartment().getNumber()) {
                departmentContent.setSelectedIndex(i);
            }
        }
        for (Treatment treatment : medicalProblem.getTreatmentsList()) {
            activeTreatmentListModel.addElement(TreatmentListOptionDTO.map(treatment));
        }
    }

    private void clearPanel() {
        nameText.setText("");
        activeTreatmentListModel.removeAllElements();
    }
}

