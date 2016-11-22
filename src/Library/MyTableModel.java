package Library;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


public class MyTableModel extends AbstractTableModel{

    String[] columnNames = {"Название", "Автор", "Жанр", "№", "В библиотеке"};

    Object[][] data;

    public MyTableModel(Object[][] data){
        this.data = data;


    }

    @Override
    public int getRowCount() {
        return data.length;
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
        return data[rowIndex][columnIndex];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
            return true;
    }

    public void setValueAt(Object value, int row, int col) {

        if (col == 3) {
            data[row][col] = calcId((int)value);
        } else {
            data[row][col] = value;
        }
        fireTableCellUpdated(row, col);
    }

    public void addRow(){

        Object[] defaultRaw = {"","","",calcId(),false};

        if ((data[data.length-1].equals(defaultRaw)) || (data[data.length-1][0].equals("") )&&(data[data.length-1][1].equals("")))
            return;

        Object[][] temp = new Object[data.length+1][columnNames.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                temp[i][j] = data[i][j];
            }
        }
        temp[data.length] = defaultRaw;
        data = temp;
    }

    public void removeRow(int row) {

        Object[][] temp = new Object[data.length-1][5];

        if (row == data.length - 1) {
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = data[i][j];
                }
            }
            data = temp;
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < 5; j++){
                    temp[i][j] = data[i][j];
                }
            }

            for (int i = row; i < data.length -1; i++){
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = data[i+1][j];
                }
            }
            data = temp;
    }


        data = temp;
    }

    public int calcId(){
        int x = 1;
        boolean flag = false;
            for (int i = 0; i < data.length; i++) {
                if (x == (int)data[i][3]) {
                    i = 0;
                    x++;
                }
            }return x;
    }

    public int calcId(int a){
        for (int i = 0; i < data.length; i++) {
            if (a == (int)data[i][3]) {
                return calcId();
            }
        }
        return a;
    }

    @Override
    public void fireTableCellUpdated(int row, int column) {
        super.fireTableCellUpdated(row, column);
        TestMain.saveButton.setEnabled(true);

    }

    public void setUpGenreColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Марш");
        comboBox.addItem("Вальс");
        comboBox.addItem("Этрада");
        comboBox.addItem("Попурри");
        comboBox.addItem("Классика");
        comboBox.addItem("Другое");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Нажмите для выбора");
        sportColumn.setCellRenderer(renderer);
    }
}
