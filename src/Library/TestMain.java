package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TestMain {
    private static boolean created = false;

    static         Database database;
    private static JFrame   frame;
    private static JButton  addButton;
    public  static JButton  saveButton;
    private static JButton  removeButton;


    public static void main(String[] args) {

        database = new Database();

        create("Score Library");
    }

    public static void create(String title){
        if (created)
            return;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1050,768);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        MyTableModel myTableModel = new MyTableModel(database.getData());
        JTable table = new JTable(myTableModel);
        //table.addMouseListener();
        myTableModel.setUpGenreColumn(table, table.getColumnModel().getColumn(2));
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setLocation(10,10);
        scrollPane.setSize(1000,214);
        frame.getContentPane().add(scrollPane);

        // Add button
        addButton = new JButton("Добавить");
        addButton.setLocation(10, 230);
        addButton.setSize(100, 30);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTableModel.addRow();
                table.repaint();
            }
        });

        // Save button
        saveButton = new JButton("Сохранить");
        saveButton.setEnabled(false);
        saveButton.setLocation(230, 230);
        saveButton.setSize(100,30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database.saveToFile(myTableModel.data);
                    saveButton.setEnabled(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Remove button
        removeButton = new JButton("Убрать");
        removeButton.setEnabled(true);
        removeButton.setLocation(120, 230);
        removeButton.setSize(100,30);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTableModel.removeRow(table.getSelectedRow());
                System.out.println(table.getEditingRow());
                saveButton.setEnabled(true);
                table.repaint();
            }
        });

        frame.getContentPane().add(saveButton);
        frame.getContentPane().add(removeButton);
        frame.getContentPane().add(addButton);

        frame.setVisible(true);
    }

}
