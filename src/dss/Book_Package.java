package dss;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Book_Package extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JButton bt1, bt2, btPrice; // Added button for generating price
    JPanel p1, p2, p3;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tfUsername; // New JTextField for username
    Font f, f1;
    Choice ch1, ch4;
    String selectedDriver; // Store selected driver for price generation

    public Book_Package() {
        super("Book Truck");

        // Set frame to fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        ch1 = new Choice();
        ch4 = new Choice();

        // Add placeholder to driver choice dropdown
        ch1.add("Select Driver");

        // Populate driver choice (dropdown ch1)
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select DISTINCT driver from transport_driver";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("driver"));
            }
            rest.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Populate weight choices
        ch4.add("0 - 10 KG");
        ch4.add("10 - 50 KG");
        ch4.add("50 - 100 KG");
        ch4.add("100 - 500 KG");
        ch4.add("500 - 1000 KG");
        ch4.add("> 1000 KG");

        // Initialize labels and text fields
        l1 = new JLabel("Book Truck Package");
        l2 = new JLabel("Book ID");
        l3 = new JLabel("Source");
        l4 = new JLabel("Destination");
        l5 = new JLabel("Username");
        l6 = new JLabel("Name");
        l7 = new JLabel("Driver Name");
        l8 = new JLabel("Truck");
        l9 = new JLabel("Price");
        l10 = new JLabel("Weight");
        l11 = new JLabel("Distance");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tfUsername = new JTextField(); // Username as a text field

        tf1.setEditable(false);
        tf2.setEditable(true);
        tf3.setEditable(true);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        tf8.setEditable(false);

        Random r = new Random();
        tf1.setText("" + Math.abs(r.nextInt() % 100000));
        tf1.setForeground(Color.RED);

        bt1 = new JButton("Book Truck");
        bt2 = new JButton("Back");
        btPrice = new JButton("Generate Price"); // New button

        l1.setHorizontalAlignment(JLabel.CENTER);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        btPrice.addActionListener(this); // Add listener for new button

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
        ch1.setFont(f1);
        ch4.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tfUsername.setFont(f1); // Set font for username text field

        bt1.setFont(f1);
        bt2.setFont(f1);
        btPrice.setFont(f1); // Set font for new button

        bt1.setBackground(Color.ORANGE);
        bt2.setBackground(Color.RED);
        btPrice.setBackground(Color.BLUE); // Set background color for new button

        bt1.setForeground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
        btPrice.setForeground(Color.WHITE); // Set foreground color for new button

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(12, 2, 10, 10)); // Adjusted row count for new button

        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);  // Source as text field
        p2.add(l4);
        p2.add(tf3);  // Destination as text field
        p2.add(l5);
        p2.add(tfUsername); // Changed from Choice to JTextField for username
        p2.add(l6);
        p2.add(tf4);
        p2.add(l7);
        p2.add(ch1);  // Driver dropdown
        p2.add(l8);
        p2.add(tf5);
        p2.add(l9);
        p2.add(tf6);
        p2.add(l10);
        p2.add(ch4);
        p2.add(l11);
        p2.add(tf7);
        p2.add(btPrice); // Add new button
        p2.add(bt1);
        p2.add(bt2);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("dss/icons/transport.jpg"));
        Image img1 = img.getImage().getScaledInstance(700, 900, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img1);
        l12 = new JLabel(ic1);
        p3.add(l12);

        setLayout(new BorderLayout(10, 10));
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.WEST);

        // Listener to fetch customer name based on username input
        tfUsername.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fetchCustomerName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fetchCustomerName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fetchCustomerName();
            }

            private void fetchCustomerName() {
                String username = tfUsername.getText();
                if (!username.isEmpty()) {
                    try {
                        ConnectionClass obj = new ConnectionClass();
                        String q1 = "SELECT name FROM customer WHERE username = ?";
                        PreparedStatement pstmt = obj.con.prepareStatement(q1);
                        pstmt.setString(1, username);
                        ResultSet rest1 = pstmt.executeQuery();
                        if (rest1.next()) {
                            tf4.setText(rest1.getString("name"));
                        } else {
                            tf4.setText(""); // Clear name if not found
                        }
                        rest1.close();
                        pstmt.close();
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }
                } else {
                    tf4.setText(""); // Clear name if username is empty
                }
            }
        });

        // Listener to populate truck and distance based on driver selection
        ch1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedDriver = ch1.getSelectedItem();
                    if ("Select Driver".equals(selectedDriver)) {
                        // Handle the case when "Select Driver" is selected
                        tf5.setText("");
                        tf7.setText("");
                        return;
                    }

                    if (selectedDriver != null && !selectedDriver.isEmpty()) {
                        try {
                            ConnectionClass obj = new ConnectionClass();
                            String query = "SELECT truck, distance FROM transport_driver WHERE driver = ?";
                            PreparedStatement pstmt = obj.con.prepareStatement(query);
                            pstmt.setString(1, selectedDriver);
                            ResultSet rs = pstmt.executeQuery();

                            if (rs.next()) {
                                tf5.setText(rs.getString("truck"));
                                tf7.setText(rs.getString("distance"));
                            } else {
                                tf5.setText(""); // Clear if no truck found
                                tf7.setText(""); // Clear if no distance found
                            }
                            rs.close();
                            pstmt.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        // ActionListener for price button
        btPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int price = random.nextInt(1000) + 1000; // Generate price between 500 to 1500
                tf6.setText(String.valueOf(price));
            }
        });

        setVisible(true);
        setSize(1300, 700);
        setLocation(200, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
        if (s.equals("Back")) {
            this.setVisible(false);
            new HomePage(); // Navigate back to HomePage
        } else if (s.equals("Book Truck")) {
            String bookId = tf1.getText();
            String source = tf2.getText();
            String destination = tf3.getText();
            String username = tfUsername.getText(); // Get the username from JTextField
            String name = tf4.getText();
            String driverName = ch1.getSelectedItem();
            String truck = tf5.getText();
            String price = tf6.getText();
            String weight = ch4.getSelectedItem();
            String distance = tf7.getText();

            // Database insertion logic here
            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "INSERT INTO intransport (BookId, username, name, drivername, source, destination, distance, weight, truck, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = obj.con.prepareStatement(query);
                pstmt.setString(1, bookId);
                pstmt.setString(2, username);
                pstmt.setString(3, name);
                pstmt.setString(4, driverName);
                pstmt.setString(5, source);
                pstmt.setString(6, destination);
                pstmt.setString(7, distance);
                pstmt.setString(8, weight);
                pstmt.setString(9, truck);
                pstmt.setString(10, price);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Truck Booked Successfully!");
                this.setVisible(false);
                new HomePage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Book_Package().setVisible(true);
    }
}
