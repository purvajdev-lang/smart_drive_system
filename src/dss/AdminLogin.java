package dss;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton bt1, bt2;
    JPanel p1, p2, p3;
    Font f1, f2;

    AdminLogin() {
        super("Admin Login");
        setLocation(450, 250);
        setSize(800, 400);

        f1 = new Font("Arial", Font.BOLD, 20);
        f2 = new Font("Arial", Font.PLAIN, 15);

        l1 = new JLabel("Admin Name");
        l2 = new JLabel("Password");

        tf1 = new JTextField();
        pf1 = new JPasswordField();

        bt1 = new JButton("Login");
        bt2 = new JButton("Cancel");

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        l1.setFont(f2);
        l2.setFont(f2);
        tf1.setFont(f2);
        pf1.setFont(f2);
        bt1.setFont(f2);
        bt2.setFont(f2);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 2, 10, 10));
        p1.add(l1);
        p1.add(tf1);
        p1.add(l2);
        p1.add(pf1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 2, 10, 10));
        p2.add(bt1);
        p2.add(bt2);

        p3 = new JPanel();
        p3.setLayout(new BorderLayout(10, 10));
        p3.add(p1, "Center");
        p3.add(p2, "South");

        setLayout(new BorderLayout(10, 10));
        add(p3, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf1.getText();
            String password = new String(pf1.getPassword());

            if (checkCredentials(username, password)) {
                new AdminSection().setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials!");
            }
        } else if (e.getSource() == bt2) {
            this.setVisible(false);
        }
    }

    // Method to check credentials in the database
    private boolean checkCredentials(String username, String password) {
        boolean valid = false;
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivesmartsystem", "root", "qwerty");

            // Prepare a SQL statement to check the credentials
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            // Check if any records are returned
            if (rs.next()) {
                valid = true;
            }

            // Close connections
            rs.close();
            pst.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valid;
    }

    public static void main(String[] args) {
        new AdminLogin().setVisible(true);
    }
}
