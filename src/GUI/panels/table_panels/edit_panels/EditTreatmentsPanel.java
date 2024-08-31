
package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DoctorListOptionDTO;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.dto.TreatmentListOptionDTO;
import GUI.panels.BasePanel;
import control.Hospital;
import enums.Specialization;
import model.Doctor;
import model.StaffMember;
import model.Treatment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditTreatmentsPanel extends EditPanel {

        public EditTreatmentsPanel(JPanel prev) {
            super(prev);
        }
    private JComboBox<Treatment> createTreatmentContent() {
        JComboBox<Treatment> treatmentContent = new JComboBox<>();
        for (Map.Entry<Integer, Treatment> entry : hospital.getTreatments().entrySet()) {
            treatmentContent.addItem(TreatmentListOptionDTO.map(entry.getValue()));
        }
        return treatmentContent;
    }


        public EditTreatmentsPanel(BasePanel prev) {
            super(prev);

            JLabel descriptionLabel = new JLabel("Treatment Description:");
            JTextField descriptionText = new JTextField();

            JLabel doctorLabel = new JLabel("Doctors:");
            JScrollPane activeDoctorPane = new JScrollPane();
            DefaultListModel<String> activeDoctorListModel = new DefaultListModel<>();
            JList<String> activeDoctorList = new JList<>(activeDoctorListModel);
            activeDoctorPane.add(activeDoctorList);

            DefaultListModel<String> allDoctorListModel = new DefaultListModel<>();
            JList<String> allDoctorList = new JList<>(allDoctorListModel);
            JScrollPane allDoctorPane = new JScrollPane(allDoctorList);

            for (StaffMember member : hospital.getStaffMembers().values()) {
                if (member instanceof Doctor) {
                    allDoctorListModel.addElement(DoctorListOptionDTO.map((Doctor) member).toString());
                }
            }
            JButton button = new JButton("Select Sublist");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });



            JButton addButton = new JButton("Save");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            JButton backButton = new JButton("Back");
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

            GroupLayout.Group doctorGroupHor = layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeDoctorPane))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(button))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allDoctorPane));
            GroupLayout.Group doctorGroupVer = layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(activeDoctorPane)
                            .addComponent(button)
                            .addComponent(allDoctorPane));


            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(doctorLabel)
                                    .addComponent(descriptionLabel)
                                    .addComponent(backButton))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(doctorGroupHor)
                                    .addComponent(descriptionText)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(addButton)
                                            .addComponent(backButton))
                            )
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(descriptionLabel)
                                    .addComponent(descriptionText))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(doctorLabel)
                                    .addGroup(doctorGroupVer))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(addButton)
                                    .addComponent(backButton))
            );
        }



}

