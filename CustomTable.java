import javax.swing.*;

public class CustomTable extends JTable {
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
}

