import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    JButton registerCustomerBtn, viewCustomerBtn, viewShopBtn, viewStockBtn, viewStaffBtn;

    Dashboard() {
        setTitle("Boutique Shop");
        setLayout(new GridLayout(5, 1));
        setSize(600, 350);

        registerCustomerBtn= new JButton("Register Customer");
        viewCustomerBtn = new JButton("view customer details");
        viewShopBtn = new JButton("View Shop");
        viewStockBtn = new JButton("Room Stock");
        viewStaffBtn = new JButton("View Staff");

        add(registerCustomerBtn);
        add(viewCustomerBtn);
        add(viewShopBtn);
        add(viewStockBtn);
        add(viewStaffBtn);

        registerCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCustomer registerCustomer  = new RegisterCustomer();

            }
        });

        viewCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCustomerDetailsPanel customerDetailsPanel = new ViewCustomerDetailsPanel();
            }
        });

        viewStaffBtn.addActionListener(e -> {
            ViewStaff staff = new ViewStaff();
        });

        viewShopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewShop viewShop =  new ViewShop();
            }
        });


        viewStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStock stock = new ViewStock();

            }
        });

        setVisible(true);

    }

    public static void main(String[] args)
    {
        Dashboard dashboard = new Dashboard();
    }
}

