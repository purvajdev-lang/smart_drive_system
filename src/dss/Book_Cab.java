package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Book_Cab extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l11;
    JButton bt1, bt2, bt3; // Added bt3 for Generate Price button
    JPanel p1, p2, p3;
    JTextField tf1, tf2, tf3, tf4, tf5, tf8, tfUsername,tf9; 
    Font f, f1;
    Choice ch2;

    // Define price ranges for intracity cabs
    int minPriceIntracity = 800;
    int maxPriceIntracity = 1000;

    Book_Cab() {
        super("Book Intracity Cab");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        tfUsername = new JTextField(); // TextField for username input
        tfUsername.setEditable(true); // Allow editing
        tfUsername.addActionListener(new ActionListener() { // Fetch name on Enter key
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchNameFromUsername();
            }
        });

        ch2 = new Choice();
        ch2.add("Select Driver");

        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT * FROM intracity_driver";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                String driverName = rest.getString("driver");
                ch2.add(driverName); // Populate driver dropdown
            }
            rest.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize labels
        l1 = new JLabel("Book Intracity Cab");
        l2 = new JLabel("Book ID");
        l3 = new JLabel("Source");
        l4 = new JLabel("Destination");
        l5 = new JLabel("Username");
        l6 = new JLabel("Name");
        l7 = new JLabel("Driver Name");
        l8 = new JLabel("Car");
        l11 = new JLabel("Price");

        tf1 = new JTextField();
        tf2 = new JTextField(); // Source
        tf3 = new JTextField(); // Destination
        tf4 = new JTextField(); // Name
        tf5 = new JTextField(); // Car
        tf8 = new JTextField(); // Price
        tf9 = new JTextField(); // Price

        tf1.setEditable(false);
        tf2.setEditable(true); // Source
        tf3.setEditable(true); // Destination
        tf4.setEditable(false); // Name
        tf5.setEditable(false); // Car
        tf8.setEditable(false); // Price
         tf9.setEditable(false);

        Random r = new Random();
        tf1.setText("" + Math.abs(r.nextInt() % 100000));
        tf1.setForeground(Color.RED);

        bt1 = new JButton("Book Cab");
        bt2 = new JButton("Back");
        bt3 = new JButton("Generate Price"); // Added button for generating price

        l1.setHorizontalAlignment(JLabel.CENTER);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this); // Add action listener for price generation button

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l11.setFont(f1); // Ensure this line is reached after l11 is initialized
        ch2.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf8.setFont(f1);
        tf8.setFont(f1);
        tfUsername.setFont(f1); // Set font for the username field

        bt1.setFont(f1);
        bt2.setFont(f1);
        bt3.setFont(f1); // Set font for the Generate Price button

        bt1.setBackground(Color.ORANGE);
        bt2.setBackground(Color.RED);
        bt3.setBackground(Color.BLUE); // Set background color for the Generate Price button

        bt1.setForeground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
        bt3.setForeground(Color.WHITE); // Set foreground color for the Generate Price button

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(10, 2, 10, 10)); // Adjusted rows to fit updated layout

        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l4);
        p2.add(tf3);
        p2.add(l5);
        p2.add(tfUsername); // Add username text field
        p2.add(l6);
        p2.add(tf4);
        p2.add(l7);
        p2.add(ch2);
        p2.add(l8);
        p2.add(tf5);
        p2.add(l11);
        p2.add(tf8);
        p2.add(bt3); // Add Generate Price button
        p2.add(bt1);
        p2.add(bt2);

        p3 = new JPanel();
        p3.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("dss/icons/bookcab1.png"));
        Image img1 = img.getImage().getScaledInstance(700, 600, Image.SCALE_DEFAULT); // Adjust size as needed
        ImageIcon ic1 = new ImageIcon(img1);
        JLabel l12 = new JLabel(ic1);
        p3.add(l12, BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(p3, BorderLayout.WEST); // Add image panel to the left
        add(p2, BorderLayout.CENTER); // Add content panel to the right

        // Fetch driver details when a driver is selected
        ch2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        ConnectionClass obj = new ConnectionClass();
                        String selectedDriver = ch2.getSelectedItem();
                        if (!selectedDriver.equals("Select Driver")) { // Only fetch details if a valid driver is selected
                            String q1 = "SELECT * FROM intracity_driver WHERE driver='" + selectedDriver + "'";
                            ResultSet rest1 = obj.stm.executeQuery(q1);
                            if (rest1.next()) {
                                tf5.setText(rest1.getString("car"));
                            }
                            rest1.close();
                        } else {
                            tf5.setText("");
                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }
                }
            }
        });
    }

    // Method to fetch name based on entered username
    private void fetchNameFromUsername() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String selectedUsername = tfUsername.getText(); // Get text from the username field
            String q1 = "SELECT name FROM customer WHERE username='" + selectedUsername + "'";
            ResultSet rest1 = obj.stm.executeQuery(q1);
            if (rest1.next()) {
                tf4.setText(rest1.getString("name"));
            } else {
                tf4.setText(""); // Clear the name field if no result is found
            }
            rest1.close();
        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String bid = tf1.getText();
            String username = tfUsername.getText(); // Get username from the text field
            String dname = ch2.getSelectedItem();
            String car = tf5.getText();
            String source = tf2.getText();
            String destination = tf3.getText();
            String price = tf8.getText();
            String name=tf9.getText();

            try {
                ConnectionClass obj3 = new ConnectionClass();
                if (obj3.stm != null) {
                    System.out.println("Database connection established.");
                } else {
                    System.out.println("Database connection failed.");
                }
               String q1 = "INSERT INTO intracitycab VALUES('" + bid + "','" + username + "','" + dname + "','" + car + "','" + source + "','" + destination + "','" + price + "','" + name + "')";

                int aa = obj3.stm.executeUpdate(q1);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Cab booked successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Booking failed");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == bt2) {
            new HomePage().setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == bt3) { // Generate price
            int randomPrice = minPriceIntracity + (int) (Math.random() * (maxPriceIntracity - minPriceIntracity + 1));
            tf8.setText(String.valueOf(randomPrice));
        }
    }

    public static void main(String[] args) {
        new Book_Cab().setVisible(true);
    }
}
