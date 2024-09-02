package GUI.panels.table_panels;

import GUI.actions.AddItemAction;
import GUI.panels.BasePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablePanel extends BasePanel {

    private JTable content;
    private String itemInfoPanel;

    public TablePanel(Object[][] data, Object[] columns, String itemInfoPanel) {
        super();

        this.itemInfoPanel = itemInfoPanel;
        this.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(data, columns);
        this.content = new JTable(model);
        content.setGridColor(Color.BLACK);
        content.setShowGrid(true);
        content.setDefaultEditor(Object.class, null);
        content.getTableHeader().setFont(content.getTableHeader().getFont().deriveFont(Font.BOLD));
        content.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(content);

        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout());
        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new AddItemAction());
        buttons.add(buttonAdd);
        buttons.add(new JButton("Edit"));
        buttons.add(new JButton("Delete"));

        this.add(buttons, BorderLayout.NORTH);

    }

    public JTable getContent(){
        return content;
    }

    public String getItemInfoPanel(){
        return itemInfoPanel;
    }

    public void reloadData(Object[][] data, Object[] columns) {
        DefaultTableModel newModel = new DefaultTableModel(data, columns);
        content.setModel(newModel);
    }

}
