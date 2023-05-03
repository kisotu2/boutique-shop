import java.awt.*;
import javax.swing.*;

public class ViewCustomer extends JFrame{
    public ViewCustomer()
    {
        setTitle("View Customer Details");
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridLayout(1, 1));

        ViewCustomerDetailsPanel customerDetailsPanel  =  new ViewCustomerDetailsPanel();

        add(customerDetailsPanel);
        pack();
        setVisible(true);
    }
}

