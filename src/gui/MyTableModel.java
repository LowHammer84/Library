package gui;

import database.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class MyTableModel extends AbstractTableModel{

    private String[] columnNames = {"№", "Название", "Автор", "Жанр", "В библиотеке"};

    Database database = Database.getData();

    public MyTableModel(){

    }

    @Override
    public int getRowCount() {
        return database.getScores().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return database.getValueAt(rowIndex, columnIndex);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
            return true;
    }

    public void setValueAt(Object value, int row, int col) {

        database.setValueAt(value, row, col);
        
        fireTableCellUpdated(row, col);
    }

    public void addRow(){
        
    }

    public void removeRow(int row) {
        
    }

    public int calcId(){
        return 0;
    }

    public int calcId(int a){
        return 0;
    }

    @Override
    public void fireTableCellUpdated(int row, int column) {
        super.fireTableCellUpdated(row, column);
        //TestMain.saveButton.setEnabled(true);

    }

    public void setUpGenreColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Марш");
        comboBox.addItem("Вальс");
        comboBox.addItem("Эcтрада");
        comboBox.addItem("Попурри");
        comboBox.addItem("Классика");
        comboBox.addItem("Другое");
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Нажмите для выбора");
        sportColumn.setCellRenderer(renderer);
    }
}
