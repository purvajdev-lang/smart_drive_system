package dss;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class Book_Intercity_Cab extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l11;
    JButton bt1, bt2, btPrice; 
    JPanel p1, p2, p3;
    JTextField tf1, tf2, tf3, tf4, tf5, tf8, tfUsername; // Add tfUsername for user input
    Font f, f1;
    Choice chDriver;

    Book_Intercity_Cab() {
        super("Book Intercity Cab");
        
        // Set the window to fill the screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        chDriver = new Choice();

        chDriver.add("Select Driver");

        try {
            ConnectionClass obj = new ConnectionClass();
            String qDriver = "select driver from intercity_driver";
            ResultSet rsDriver = obj.stm.executeQuery(qDriver);
            while (rsDriver.next()) {
                chDriver.add(rsDriver.getString("driver"));
            }
            rsDriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        l1 = new JLabel("Book Intercity Cab");
        l2 = new JLabel("Book ID");
        l3 = new JLabel("Source");
        l4 = new JLabel("Destination");
        l5 = new JLabel("Username");
        l6 = new JLabel("Name");
        l7 = new JLabel("Driver Name");
        l8 = new JLabel("Car");
        l11 = new JLabel("Price");

        tf1 = new JTextField();
        tf2 = new JTextField();  
        tf3 = new JTextField();
        tf4 = new JTextField();  
        tf5 = new JTextField();
        tf8 = new JTextField();  
        tfUsername = new JTextField(); // Create the username text field

        tf1.setEditable(false);
        tf3.setEditable(false);
        tf5.setEditable(false);
        tf8.setEditable(false);

        Random r = new Random();
        tf1.setText("" + Math.abs(r.nextInt() % 100000));
        tf1.setForeground(Color.RED);

        bt1 = new JButton("Book Cab");
        bt2 = new JButton("Back");
        btPrice = new JButton("Generate Price"); 

        l1.setHorizontalAlignment(JLabel.CENTER);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        btPrice.addActionListener(this); 

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l11.setFont(f1);
        chDriver.setFont(f1);
        tfUsername.setFont(f1); // Set font for the username text field

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf8.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);
        btPrice.setFont(f1); 

        bt1.setBackground(Color.ORANGE);
        bt2.setBackground(Color.RED);
        btPrice.setBackground(Color.BLUE); 

        bt1.setForeground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
        btPrice.setForeground(Color.WHITE); 

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(10, 2, 10, 10)); 

        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2); 
        p2.add(l4);
        p2.add(tf4); 
        p2.add(l5);
        p2.add(tfUsername); // Use the text field for username input
        p2.add(l6);
        p2.add(tf3);
        p2.add(l7);
        p2.add(chDriver); 
        p2.add(l8);
        p2.add(tf5);
        p2.add(l11);
        p2.add(tf8);
        p2.add(btPrice); 
        p2.add(bt1);
        p2.add(bt2);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("dss/icons/bookcab.jpg"));
        Image img1 = img.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img1);
        JLabel l12 = new JLabel(ic1);
        p3.add(l12);

        setLayout(new BorderLayout(10, 10));
        add(p1, "North");
        add(p2, "Center");
        add(p3, "West");

        // Fetch the name when the username field loses focus or on pressing Enter
        tfUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText().trim(); // Get the entered username
                try {
                    ConnectionClass obj = new ConnectionClass();
                    String q1 = "select name from customer where username='" + username + "'";
                    ResultSet rest1 = obj.stm.executeQuery(q1);
                    if (rest1.next()) {
                        tf3.setText(rest1.getString("name"));
                    } else {
                        tf3.setText(""); // Clear the name field if username not found
                        JOptionPane.showMessageDialog(null, "Username not found!");
                    }
                    rest1.close();
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });

        chDriver.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedDriver = chDriver.getSelectedItem();
                    if (!selectedDriver.equals("Select Driver")) {
                        try {
                            ConnectionClass obj = new ConnectionClass();
                            String q1 = "select car from intercity_driver where driver='" + selectedDriver + "'";
                            ResultSet rest1 = obj.stm.executeQuery(q1);
                            if (rest1.next()) {
                                tf5.setText(rest1.getString("car"));
                            }
                            rest1.close();
                        } catch (Exception exx) {
                            exx.printStackTrace();
                        }
                    } else {
                        tf5.setText("");
                    }
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String bid = tf1.getText();
            String username = tfUsername.getText(); // Get the username from the text field
            String dname = chDriver.getSelectedItem();
            String car = tf5.getText();
            String source = tf2.getText();
            String destination = tf4.getText();
            String price = tf8.getText();
            String name = tf3.getText();

            try {
                ConnectionClass obj3 = new ConnectionClass();
                String q1 = "insert into intercityCab values('" + bid + "','" + username + "','" + name + "','" + dname + "','" + source + "','" + destination + "','" + car + "','" + price + "')";
                int aa = obj3.stm.executeUpdate(q1);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Your cab has been successfully booked");
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all details carefully");
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            this.setVisible(false);
        }

        if (e.getSource() == btPrice) {
            Random rand = new Random();
            int minPrice = 100; 
            int maxPrice = 300; 
            int generatedPrice = rand.nextInt(maxPrice - minPrice + 1) + minPrice;
            tf8.setText(String.valueOf(generatedPrice));
        }
    }

    public static void main(String[] args) {
        new Book_Intercity_Cab().setVisible(true);
    }
}
