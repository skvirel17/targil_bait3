package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.*;
import GUI.mainScreen.SystemUsersGUI;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.DepartmentsPanel;
import GUI.panels.table_panels.MedicalProblemsPanel;
import enums.Specialization;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.security.PrivateKey;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditMedicalProblemPanel extends EditPanel{

    public static final String EDIT_MEDICAL_PROBLEM_PANEL = "EDIT_MEDICAL_PROBLEM_PANEL";

    //MedicalProblem
    private MedicalProblem medicalProblem;
    //Name
    private JLabel nameLabel;
    private JTextField nameText;
    //Department
    private JLabel departmentLabel;
    private JComboBox<DepartmentOptionDTO> departmentContent;
    //Type
    private JLabel typeLabel;
    private JComboBox<String> typeContent;
    //Treatment
    private JLabel treatmentLabel;
    private JScrollPane activeTreatmentPane;
    private DefaultListModel<TreatmentListOptionDTO> activeTreatmentListModel;
    private JList<TreatmentListOptionDTO> activeTreatmentList;
    private DefaultListModel<TreatmentListOptionDTO> allTreatmentListModel;
    private JList<TreatmentListOptionDTO> allTreatmentList;
    private JScrollPane allTreatmentPane;
    private JButton button;
    //Save button
    private JButton saveMedicalProblemButton;
    //Back button
    private JButton backButton;
    private GroupLayout layout;


    public EditMedicalProblemPanel(BasePanel prev) {
        super(prev);

        medicalProblem = null;

        buildNameField();
        buildDepartmentField();
        buildTreatmentField();
        buildTypeField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);

        compose();
    }

    public void disableTypeField() {
        typeContent.setEnabled(false);
    }

    public void enableTypeField() {
        typeContent.setEnabled(true);
    }

    private void buildNameField(){
        nameLabel = new JLabel("Medical problem name:");
        nameText = new JTextField();
    }

    private void buildDepartmentField(){
        departmentLabel = new JLabel("Department:");
        departmentContent = createDepartmentContent();
    }
    private void buildTypeField(){
        typeLabel = new JLabel("Type:");
        typeContent = createTypeContent();
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
                List<TreatmentListOptionDTO> selected = allTreatmentList.getSelectedValuesList();
                for (TreatmentListOptionDTO item : selected) {
                    if (activeTreatmentListModel.indexOf(item) == -1) {
                        activeTreatmentListModel.addElement(item);
                    };
                }
            }
        });
    }

    private void buildSaveButton(BasePanel prev, EditMedicalProblemPanel panel){
        saveMedicalProblemButton = new JButton("Save");
        saveMedicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                Department department = (Department) departmentContent.getSelectedItem();
                HashSet<Treatment> treatments = new HashSet<>();
                for (int i = 0; i < activeTreatmentListModel.getSize(); i++) {
                    treatments.add(activeTreatmentListModel.get(i));
                }
                String location = "";
                boolean requiresCast = false;
                double recoveryTime = 0.0;
                String description = "";

                MedicalProblem newMedicalProblem = null;
                if (typeContent.getSelectedItem().equals("fracture")) {
                    newMedicalProblem = new Fracture(name, department, treatments, location, requiresCast);
                } else if (typeContent.getSelectedItem().equals("injury")) {
                    newMedicalProblem = new Injury(name, department, treatments, recoveryTime, location);
                } else if (typeContent.getSelectedItem().equals("disease")) {
                    newMedicalProblem = new Disease(name, department, treatments, description);
                }

                if (medicalProblem != null) {
                    medicalProblem.setName(name);
                    medicalProblem.setDepartment(department);
                    medicalProblem.setTreatmentsList(treatments);
                    if (typeContent.getSelectedItem().equals("fracture")) {
                        ((Fracture) medicalProblem).setLocation(location);
                        ((Fracture) medicalProblem).setRequiresCast(requiresCast);
                    } else if (typeContent.getSelectedItem().equals("injury")) {
                        ((Injury) medicalProblem).setCommonRecoveryTime(recoveryTime);
                        ((Injury) medicalProblem).setLocation(location);
                    } else if (typeContent.getSelectedItem().equals("disease")) {
                        ((Disease) medicalProblem).setDescription(description);
                    }
                }

                if (medicalProblem != null || SystemUsersGUI.hospital.addMedicalProblem(newMedicalProblem)) {
                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                    ((MedicalProblemsPanel) prev).reloadData(hospital.getMedicalProblems());
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void buildBackButton(BasePanel prev, EditMedicalProblemPanel panel){
        backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Changes will be lost! \nDo you want to continue?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
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
                                .addComponent(typeLabel)
                                .addComponent(departmentLabel)
                                .addComponent(treatmentLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(typeContent)
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
                                .addComponent(typeLabel)
                                .addComponent(typeContent))
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

    private JComboBox<String> createTypeContent() {
        return new JComboBox<>(new String[]{"injury", "disease", "fracture"});
    }

    public void fillFromObject(MedicalProblem medicalProblem) {
        clearPanel();
        this.medicalProblem = medicalProblem;
        nameText.setText(medicalProblem.getName());
        for (int i = 0; i < departmentContent.getItemCount(); i++) {
            if (departmentContent.getItemAt(i).getNumber() == medicalProblem.getDepartment().getNumber()) {
                departmentContent.setSelectedIndex(i);
            }
        }
        for (Treatment treatment : medicalProblem.getTreatmentsList()) {
            activeTreatmentListModel.addElement(TreatmentListOptionDTO.map(treatment));
        }
        for(int i = 0; i < typeContent.getItemCount(); i++){
            if(medicalProblem.getCode().substring(0,1).equals(typeContent.getItemAt(i).substring(0,1))){
                typeContent.setSelectedIndex(i);
            }
        }
    }

    void clearPanel() {
        medicalProblem = null;
        nameText.setText("");
        activeTreatmentListModel.removeAllElements();
        enableTypeField();
    }
}

