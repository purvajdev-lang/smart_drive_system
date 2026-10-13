package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Update_Customer extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JButton bt1, bt2, btFetch;
    JTextField tfUsername, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    Font f, f1;

    Update_Customer() {
        super("Update Customer");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        l1 = new JLabel("Update Customer");
        l2 = new JLabel("Username");
        l3 = new JLabel("Name");
        l4 = new JLabel("Age");
        l5 = new JLabel("Date of Birth");
        l6 = new JLabel("Address");
        l7 = new JLabel("Phone");
        l8 = new JLabel("Email");
        l9 = new JLabel("Country");
        l10 = new JLabel("Gender");
        l11 = new JLabel("Aadhar");

        tfUsername = new JTextField(); // For entering the username
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();

        bt1 = new JButton("Update Data");
        bt2 = new JButton("Back");
        btFetch = new JButton("Fetch Data"); // New button to fetch data based on username

        l1.setHorizontalAlignment(JLabel.CENTER);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        btFetch.addActionListener(this); // Add action listener for fetch button

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);
        l11.setFont(f1);

        tfUsername.setFont(f1);
        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);
        btFetch.setFont(f1);

        bt1.setBackground(Color.BLACK);
        bt2.setBackground(Color.RED);
        btFetch.setBackground(Color.BLUE);

        bt1.setForeground(Color.WHITE);
        bt2.setForeground(Color.WHITE);
        btFetch.setForeground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(12, 2, 10, 10));

        p2.add(l2);
        p2.add(tfUsername);
        p2.add(new JLabel()); // Empty cell to align fetch button
        p2.add(btFetch); // Add fetch button next to the username field
        p2.add(l3);
        p2.add(tf1);
        p2.add(l4);
        p2.add(tf2);
        p2.add(l5);
        p2.add(tf3);
        p2.add(l6);
        p2.add(tf4);
        p2.add(l7);
        p2.add(tf5);
        p2.add(l8);
        p2.add(tf6);
        p2.add(l9);
        p2.add(tf7);
        p2.add(l10);
        p2.add(tf8);
        p2.add(l11);
        p2.add(tf9);
        p2.add(bt1);
        p2.add(bt2);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("dss/icons/updatecustomer.jpg"));
        Image img1 = img.getImage().getScaledInstance(700, 800, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img1);
        JLabel l12 = new JLabel(ic1);

        p3.add(l12, BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.WEST);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btFetch) {
            // Fetch and display customer data based on username
            String username = tfUsername.getText();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a username.");
                return;
            }

            try {
                ConnectionClass obj2 = new ConnectionClass();
                String query = "SELECT * FROM customer WHERE username = ?";
                PreparedStatement ps = obj2.con.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    tf1.setText(rs.getString("name"));
                    tf2.setText(rs.getString("age"));
                    tf3.setText(rs.getString("dob"));
                    tf4.setText(rs.getString("address"));
                    tf5.setText(rs.getString("phone"));
                    tf6.setText(rs.getString("email"));
                    tf7.setText(rs.getString("country"));
                    tf8.setText(rs.getString("gender"));
                    tf9.setText(rs.getString("aadhar"));
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == bt1) {
            // Update customer data based on user input
            String username = tfUsername.getText();
            String name = tf1.getText();
            String age = tf2.getText();
            String dob = tf3.getText();
            String address = tf4.getText();
            String phone = tf5.getText();
            String email = tf6.getText();
            String country = tf7.getText();
            String gender = tf8.getText();
            String aadhar = tf9.getText();

            // Validate that all fields are filled
            if (username.isEmpty() || name.isEmpty() || age.isEmpty() || dob.isEmpty() || address.isEmpty() ||
                phone.isEmpty() || email.isEmpty() || country.isEmpty() || gender.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }

            try {
                ConnectionClass obj3 = new ConnectionClass();
                String query = "UPDATE customer SET name=?, age=?, dob=?, address=?, phone=?, email=?, country=?, gender=?, aadhar=? WHERE username=?";
                PreparedStatement ps = obj3.con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, age);
                ps.setString(3, dob);
                ps.setString(4, address);
                ps.setString(5, phone);
                ps.setString(6, email);
                ps.setString(7, country);
                ps.setString(8, gender);
                ps.setString(9, aadhar);
                ps.setString(10, username);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated == 1) {
                    JOptionPane.showMessageDialog(null, "Your data has been successfully updated.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update data. Please try again.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == bt2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Update_Customer().setVisible(true);
    }
}
