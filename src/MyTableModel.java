import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel{

    private String[] columnNames = {"Название", "Автор", "Жанр", "№", "В библиотеке"};

    private static ArrayList<Score> scores = Utils.scores;


    private Object[][] data;

    public MyTableModel(Object[][] data){
        this.data = data;

    }

    @Override
    public int getRowCount() {
        return scores.size();
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
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {

        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
