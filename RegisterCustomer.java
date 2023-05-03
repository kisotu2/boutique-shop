import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterCustomer extends JFrame{
    private JTextField nameentry, phonenumberentry,customerIDentry;
    private JLabel namelbl, phonenumberlbl,customerIDlbl;
    private JButton Submitbtn,Viewbtn;


    public RegisterCustomer(){
        setTitle("Customer Registration");
        setLayout(new GridLayout(4,2));
        setSize(400,180);

        nameentry = new JTextField();
        phonenumberentry = new JTextField();
        customerIDentry=new JTextField();


        namelbl = new JLabel("Enter your name");
        phonenumberlbl = new JLabel("Phone number");
        customerIDlbl = new JLabel("Enter ID number");

        Submitbtn = new JButton("Submit ");
        Viewbtn= new JButton("View Customer");

        add(namelbl);
        add(nameentry);

        add(phonenumberlbl);
        add(phonenumberentry);

        add(customerIDlbl);
        add(customerIDentry);

        add(Submitbtn);
        add(Viewbtn);

        Submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCustomerDatabase();
            }
        });

        Viewbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCustomer room = new ViewCustomer();
            }
        });
        setVisible(true);
    }
    private void registerCustomerDatabase()
    {
        String name = nameentry.getText().trim();
        String phonenumber = phonenumberentry.getText().trim();
        String  customerID = customerIDentry.getText().trim();

        if (!(name.isEmpty() || name == null) && (!(phonenumber.isEmpty() || phonenumber == null)) && (!(customerID.isEmpty() || customerID == null)))


        {
            String URL = "jdbc:mysql://localhost:3306/boutique";
            String USER = "root";  // Define database credentials
            String PASSWORD = "";

            Connection connection = null;
            PreparedStatement ps = null;
            try {

                boolean success = false;
                connection = DriverManager.getConnection(URL, USER, PASSWORD);  // Create connection to database
                String sql = "INSERT INTO customers(name,phone_number) " +
                        "VALUES (?, ?)";
                ps = connection.prepareStatement(sql);

                ps.setString(1, nameentry.getText());
                ps.setString(2, phonenumberentry.getText());

                ps. executeUpdate();
                success = true;

                if (success)
                {
                    JOptionPane.showMessageDialog(null, "Customer Registration Successful");

                    nameentry.setText("");
                    phonenumberentry.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Customer Registration Failed");
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();  // Print error message and stack trace
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
        }

    }

}