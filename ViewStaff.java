import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewStaff extends JFrame{

    private JButton searchButton;
    private JTextField searchField;
    private CustomTable dataTable;

    public void viewStaff(){


        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(100,200);
        frame.setVisible(true);
    }

    public ViewStaff() {
        super();
        setSize(400,400);
        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Create the table
        dataTable = new CustomTable() ;
        populateTable(" SELECT * FROM staff");

        // Set up the layout
        setLayout(new BorderLayout());

        // Add the components to the layout
        JPanel searchPanel = new JPanel(new GridLayout(1, 2));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);



        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoom();
            }
        });
    }

    private void searchRoom()
    {
        String search = searchField.getText();
        String sql = "SELECT * FROM staff WHERE staff_ID LIKE '%" + search + "%' OR " +
                "name LIKE '%" + search + "%' OR phone_number LIKE '%" + search + "%' OR " +
                "salary LIKE '%" + search + "%' OR  position LIKE '%" + search + "%'";

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


    public CustomTable getDataTable()
    {
        return dataTable;
    }
}