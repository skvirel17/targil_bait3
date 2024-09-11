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
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.security.PrivateKey;
import java.text.ParseException;
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
    //Description
    private JLabel descLabel;
    private JTextField descText;
    //Location
    private JLabel locationLabel;
    private JTextField locationText;
    //RequiresCast
    private JLabel requiresCastLabel;
    private JCheckBox requiresCastContent;
    //Common recovery time
    private JLabel recoveryTimeLabel;
    private JFormattedTextField recoveryTimeText;
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
        buildDescriptionField();
        buildLocationField();
        buildRequiresCastField();
        buildRecoveryTimeField();

        buildSaveButton(prev, this);
        buildBackButton(prev, this);

        compose(typeContent.getItemAt(typeContent.getSelectedIndex()));
    }

    public void disableTypeField() {
        typeContent.setEnabled(false);
    }

    public void enableTypeField() {
        typeContent.setEnabled(true);
    }

    private void buildRecoveryTimeField(){
        recoveryTimeLabel = new JLabel("Recovery time:");
        try {
            recoveryTimeText = new JFormattedTextField(new MaskFormatter("###.##"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void buildRequiresCastField(){
        requiresCastLabel = new JLabel("Requires cast:");
        requiresCastContent = new JCheckBox();
    }

    private void buildLocationField(){
        locationLabel = new JLabel("Location:");
        locationText = new JTextField();
    }

    private void buildDescriptionField(){
        descLabel = new JLabel("Description:");
        descText = new JTextField();
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
        typeContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compose(typeContent.getItemAt(typeContent.getSelectedIndex()));
            }
        });
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
                String location = locationText.getText();
                boolean requiresCast = requiresCastContent.isSelected();
                double recoveryTime = Double.parseDouble(recoveryTimeText.getText().trim());
                String description = descText.getText();

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

    private void composeDisease() {
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
                                .addComponent(descLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(typeContent)
                                .addComponent(departmentContent)
                                .addGroup(treatmentGroupHor)
                                .addComponent(descText)
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
                                .addComponent(descLabel)
                                .addComponent(descText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveMedicalProblemButton))

        );
    }

    private void composeFracture() {
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
                                .addComponent(locationLabel)
                                .addComponent(requiresCastLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(typeContent)
                                .addComponent(departmentContent)
                                .addGroup(treatmentGroupHor)
                                .addComponent(locationText)
                                .addComponent(requiresCastContent)
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
                                .addComponent(locationLabel)
                                .addComponent(locationText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(requiresCastLabel)
                                .addComponent(requiresCastContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveMedicalProblemButton))

        );
    }

    private void composeInjury() {
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
                                .addComponent(recoveryTimeLabel)
                                .addComponent(locationLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(typeContent)
                                .addComponent(departmentContent)
                                .addGroup(treatmentGroupHor)
                                .addComponent(recoveryTimeText)
                                .addComponent(locationText)
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
                                .addComponent(recoveryTimeLabel)
                                .addComponent(recoveryTimeText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(locationLabel)
                                .addComponent(locationText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveMedicalProblemButton))

        );
    }

    private void compose(String type){
        if ("fracture".equals(type)) {
            composeFracture();
            this.remove(recoveryTimeLabel);
            this.remove(recoveryTimeText);
            this.remove(descLabel);
            this.remove(descText);
        } else if ("injury".equals(type)) {
            composeInjury();
            this.remove(requiresCastLabel);
            this.remove(requiresCastContent);
            this.remove(descLabel);
            this.remove(descText);
        } else if ("disease".equals(type)) {
            composeDisease();
            this.remove(recoveryTimeLabel);
            this.remove(recoveryTimeText);
            this.remove(requiresCastLabel);
            this.remove(requiresCastContent);
            this.remove(locationLabel);
            this.remove(locationText);
        }
    }


    private JComboBox<DepartmentOptionDTO> createDepartmentContent() {
        JComboBox<DepartmentOptionDTO> departmentContent = new JComboBox<>();
        for (Department department : hospital.getDepartments().values()){
            departmentContent.addItem(DepartmentOptionDTO.map(department));
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

        if (medicalProblem instanceof Disease) {
            descText.setText(((Disease) medicalProblem).getDescription());
        } else if (medicalProblem instanceof Fracture) {
            locationText.setText(((Fracture) medicalProblem).getLocation());
            requiresCastContent.setSelected(((Fracture) medicalProblem).isRequiresCast());
        } else if (medicalProblem instanceof Injury) {
            locationText.setText(((Injury) medicalProblem).getLocation());
            recoveryTimeText.setText(String.valueOf(((Injury) medicalProblem).getCommonRecoveryTime()));
        }
    }

    void clearPanel() {
        medicalProblem = null;
        nameText.setText("");
        activeTreatmentListModel.removeAllElements();
        descText.setText("");
        locationText.setText("");
        requiresCastContent.setSelected(false);
        recoveryTimeText.setText("0.0");
        enableTypeField();
    }
}

