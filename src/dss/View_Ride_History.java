package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View_Ride_History extends JFrame implements ActionListener {
    JTable t;
    JButton bt1;
    JTextField tf1;
    JPanel p1, p2;
    String x[] = {"BOOKING ID", "FIRSTNAME", "LASTNAME", "DRIVER NAME", "SOURCE", "DESTINATION", "CAR/TRUCK", "DISTANCE/WEIGHT", "PRICE"};
    String y[][] = new String[60][10]; // Increased the size to accommodate combined results
    int i = 0, j = 0;
    Font f;
    JLabel l1;

    View_Ride_History() {
        super("View Ride History");

        // Maximize the window to fill the screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Removed setUndecorated(true) to keep the exit cross

        f = new Font("MS UI Gothic", Font.BOLD, 17);

        l1 = new JLabel("Enter Username:");
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        tf1 = new JTextField(15);
        bt1 = new JButton("Search");
        bt1.setFont(new Font("Arial", Font.BOLD, 16));
        bt1.addActionListener(this);

        // Using BorderLayout to position components
        p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        p1.add(l1);
        p1.add(tf1);
        p1.add(bt1);

        add(p1, BorderLayout.NORTH);

        // Initialize the table with data array
        t = new JTable(y, x) {
            public boolean isCellEditable(int row, int column) {
                return false; // Makes cells non-editable
            }
        };

        t.setFont(f);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);

        JScrollPane js = new JScrollPane(t);
        add(js, BorderLayout.CENTER);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf1.getText();
            i = 0; // Reset row index
            j = 0; // Reset column index

            boolean userFound = false;

            try {
                ConnectionClass obj = new ConnectionClass();

                // Query for intracity bookings
                String q1 = "SELECT i.BookID, i.username, c.name, i.drivername, i.source, i.destination, i.car, i.price " +
             "FROM intracitycab i " +
             "JOIN customer c ON i.username = c.username " +
             "WHERE i.username = '" + username + "'";

                ResultSet rs1 = obj.stm.executeQuery(q1);
                while (rs1.next()) {
                    userFound = true;
                    y[i][j++] = rs1.getString("BookID");
                    y[i][j++] = rs1.getString("username");
                    y[i][j++] = rs1.getString("name"); // Name is not available in this table
                    y[i][j++] = rs1.getString("drivername");
                    y[i][j++] = rs1.getString("source");
                    y[i][j++] = rs1.getString("destination");
                    y[i][j++] = rs1.getString("car");
                    y[i][j++] = ""; // Distance/Weight is not available
                    y[i][j++] = rs1.getString("price");
                 
                    i++;
                    j = 0;
                }

                // Query for intercity bookings
                String q2 = "select * from intercitycab where username='" + username + "'";
                ResultSet rs2 = obj.stm.executeQuery(q2);
                while (rs2.next()) {
                    userFound = true;
                    y[i][j++] = rs2.getString("BookID");
                    y[i][j++] = rs2.getString("username");
                    y[i][j++] = rs2.getString("name");
                    y[i][j++] = rs2.getString("drivername");
                    y[i][j++] = rs2.getString("source");
                    y[i][j++] = rs2.getString("destination");
                    y[i][j++] = rs2.getString("car");
                    y[i][j++] = ""; // Distance/Weight is not available
                    y[i][j++] = rs2.getString("price");
                   
                    i++;
                    j = 0;
                }

                // Query for transport bookings
                String q3 = "select * from intransport where username='" + username + "'";
                ResultSet rs3 = obj.stm.executeQuery(q3);
                while (rs3.next()) {
                    userFound = true;
                    y[i][j++] = rs3.getString("BookID");
                    y[i][j++] = rs3.getString("username");
                    y[i][j++] = rs3.getString("name");
                    y[i][j++] = rs3.getString("drivername");
                    y[i][j++] = rs3.getString("source");
                    y[i][j++] = rs3.getString("destination");
                    y[i][j++] = rs3.getString("truck");
                    y[i][j++] = rs3.getString("distance");
                    y[i][j++] = rs3.getString("price");
                   
                    
                    i++;
                    j = 0;
                }

                if (!userFound) {
                    JOptionPane.showMessageDialog(null, "Invalid username");
                }

                // Refresh the table
                t.repaint();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new View_Ride_History().setVisible(true);
    }
}
