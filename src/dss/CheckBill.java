package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CheckBill extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JButton bt1, bt2, bt3, bt4, btFetch;
    JPanel p1, p2, p3;
    JTextField tfUsername, tf1, tf2, tf3, tf4;
    Font f, f1;

    public CheckBill() {
        super("Check Total Cab Bill");

        // Set frame to fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        l1 = new JLabel("Check Total Cab Bill");
        l2 = new JLabel("Username");
        l3 = new JLabel("Name");
        l4 = new JLabel("Email");
        l5 = new JLabel("Address");
        l6 = new JLabel("Price");

        tfUsername = new JTextField(); // To enter the username
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();

        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);

        bt1 = new JButton("Intercity Cab");
        bt2 = new JButton("Intracity Cab");
        bt3 = new JButton("Transport");
        bt4 = new JButton("Back");
        btFetch = new JButton("Fetch Details"); // New button to fetch details based on username

        l1.setHorizontalAlignment(JLabel.CENTER);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        btFetch.addActionListener(this); // Add action listener for fetching details

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);

        tfUsername.setFont(f1);
        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);
        bt3.setFont(f1);
        bt4.setFont(f1);
        btFetch.setFont(f1);

        bt1.setBackground(Color.ORANGE);
        bt2.setBackground(Color.ORANGE);
        bt3.setBackground(Color.ORANGE);
        bt4.setBackground(Color.BLACK);
        btFetch.setBackground(Color.BLUE);

        bt1.setForeground(Color.BLACK);
        bt2.setForeground(Color.BLACK);
        bt3.setForeground(Color.BLACK);
        bt4.setForeground(Color.WHITE);
        btFetch.setForeground(Color.WHITE);

        tf1.setBackground(Color.WHITE);
        tf2.setBackground(Color.WHITE);
        tf3.setBackground(Color.WHITE);
        tf4.setBackground(Color.WHITE);
        tf4.setForeground(Color.RED);

        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(l1, BorderLayout.CENTER);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(8, 2, 10, 10));

        p2.add(l2);
        p2.add(tfUsername); // Text field to enter the username
        p2.add(new JLabel()); // Empty space
        p2.add(btFetch); // Fetch button to get user details
        p2.add(l3);
        p2.add(tf1); // Name field
        p2.add(l4);
        p2.add(tf2); // Email field
        p2.add(l5);
        p2.add(tf3); // Address field
        p2.add(l6);
        p2.add(tf4); // Price field
        p2.add(bt1); // Intercity Cab button
        p2.add(bt2); // Intracity Cab button
        p2.add(bt3); // Transport button
        p2.add(bt4); // Back button

        p3 = new JPanel();
        p3.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("dss/icons/checkbill.jpg"));
        Image img1 = img.getImage().getScaledInstance(680, 800, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img1);
        l7 = new JLabel(ic1);
        p3.add(l7, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.WEST);
    }

    public void actionPerformed(ActionEvent e) {
        String username = tfUsername.getText();

        if (e.getSource() == btFetch) {
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username.");
                return;
            }

            // Fetch and display user details based on the entered username
            try {
                ConnectionClass obj = new ConnectionClass();
                String q1 = "select * from customer where username=?";
                PreparedStatement ps = obj.con.prepareStatement(q1);
                ps.setString(1, username);
                ResultSet rest1 = ps.executeQuery();

                if (rest1.next()) {
                    tf1.setText(rest1.getString("name"));
                    tf2.setText(rest1.getString("email"));
                    tf3.setText(rest1.getString("address"));
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found.");
                }
                rest1.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == bt1) {
            // Calculate total bill for Intercity Cab
            calculateBill("intercitycab", username);
        }
        if (e.getSource() == bt2) {
            // Calculate total bill for Intracity Cab
            calculateBill("intracitycab", username);
        }
        if (e.getSource() == bt3) {
            // Calculate total bill for Transport
            calculateBill("intransport", username);
        }
        if (e.getSource() == bt4) { // Handling Back button click
            this.setVisible(false);
            // Add code here to go back to the previous screen or homepage
        }
    }

    private void calculateBill(String tableName, String username) {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a username and fetch details first.");
            return;
        }

        double price = 0;
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "select price from " + tableName + " where username=?";
            PreparedStatement ps = obj.con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rest = ps.executeQuery();
            while (rest.next()) {
                price += rest.getDouble("price");
            }
            tf4.setText(String.valueOf(price));
            rest.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CheckBill().setVisible(true);
    }
}
