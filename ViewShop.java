import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewShop extends JFrame{

    private JButton searchButton;
    private JTextField searchField;
    private CustomTable dataTable;

    public ViewShop() {

        setSize(400,400);
        setLayout(new GridLayout(4,2));

        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Create the table
        dataTable = new CustomTable();
        populateTable(" SELECT * FROM shop");

        // Set up the layout
        setLayout(new BorderLayout());

        // Add the components to the layout
        JPanel searchPanel = new JPanel(new GridLayout(1, 2));

        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoom();
            }
        });

        setVisible(true);
    }

    private void searchRoom()
    {
        String search = searchField.getText();
        String sql = "SELECT * FROM shop WHERE shop_no LIKE '%" + search + "%' OR " +
                " manager LIKE '%" + search + "%' OR rent_amount LIKE '%" + search + "%' OR " +
                "rent_start LIKE  '%" + search + "%' OR  rent_due LIKE '%" + search +
                "%' OR location LIKE '%" + search + "%' OR stocks_available LIKE '% " + search + "%'";

        populateTable(sql);
    }

    private void populateTable(String sql)
    {
        try {
            String URL = "jdbc:mysql://localhost:3306/boutique";
            String USER = "root";  // Define database credentials
            String PASSWORD = "";

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            int numCols = rs.getMetaData().getColumnCount();

            // Create a DefaultTableModel with the correct number of columns
            DefaultTableModel model = new DefaultTableModel();
            for (int i = 1; i <= numCols; i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Add the data to the model
            while (rs.next()) {
                Object[] rowData = new Object[numCols];
                for (int i = 1; i <= numCols; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
            dataTable.setModel(model);

            con.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private void setModel(DefaultTableModel model) {
    }

    public CustomTable getCustomTable()
    {
        return dataTable;
    }
}