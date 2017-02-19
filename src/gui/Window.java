package gui;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame
        implements ActionListener {
    
    private static JButton  addButton;
    private static JButton  saveButton;
    private static JButton  removeButton;
    Database database;
    
    MyTableModel myTableModel;
    JTable table;
    
    public Window(Database database){
        
        this.database = database;
        myTableModel = new MyTableModel(database);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
        setMinimumSize(new Dimension(400, 400));
    }
    
    public void create(String title){
    
        table = new JTable(myTableModel);
        myTableModel.setUpGenreColumn(table, table.getColumnModel().getColumn(3));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        setUpRowsSize();
        this.getContentPane().add(scrollPane);
        
        JPanel buttonPanel = createButtonsPanel();
        this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        String buttonCommand = e.getActionCommand();
        switch (buttonCommand) {
            case "Сохранить":
                database.save();
                break;
            case "Добавить":
                database.add();
                break;
            case "Удалить":
                int rowIndex = table.getSelectedRow();
                database.remove(rowIndex);
                break;
        }
        myTableModel.fireTableDataChanged();
    }
    
    // creates button panel
    private JPanel createButtonsPanel(){
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        saveButton = new JButton("Сохранить");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);
        addButton = new JButton("Добавить");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
        removeButton = new JButton("Удалить");
        removeButton.addActionListener(this);
        buttonPanel.add(removeButton);
        return buttonPanel;
    }
    
    public void setUpRowsSize(){
        for (int i = 0; i < database.getScoresSize(); i++) {
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(4).setWidth(200);
            table.setRowHeight(25);
        }
    }
    
    
}
