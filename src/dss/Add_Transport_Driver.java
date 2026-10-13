package dss;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Add_Transport_Driver extends JFrame implements ActionListener {
    JLabel l1, l2, l5, l6, l7, l8;
    JButton bt1, bt2;
    JPanel p1, p2;
    JTextField tf1, tf4, tf5, tf6, tf7;
    Font f, f1;

    Add_Transport_Driver() {
        super("Add Transport Driver");
        setLocation(450, 50);
        setSize(450, 350);

        f = new Font("Arial", Font.BOLD, 20);
        f1 = new Font("Arial", Font.BOLD, 15);

        l1 = new JLabel("Add Transport Driver");
        l2 = new JLabel("Driver Name");
        l5 = new JLabel("Truck");
       
        l7 = new JLabel("Distance");
        l8 = new JLabel("Phone Number");

        tf1 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();

        bt1 = new JButton("Add Transport Driver");
        bt2 = new JButton("Back");

        l1.setHorizontalAlignment(JLabel.CENTER);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        l1.setFont(f);
        l2.setFont(f1);
        l5.setFont(f1);
       
        l7.setFont(f1);
        l8.setFont(f1);

        tf1.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 2, 10, 10));

        p2.add(l2);
        p2.add(tf1);
        p2.add(l5);
        p2.add(tf4);
       
        p2.add(l7);
        p2.add(tf6);
        p2.add(l8);
        p2.add(tf7);
        p2.add(bt1);
        p2.add(bt2);

        setLayout(new BorderLayout(10, 10));
        add(p1, "North");
        add(p2, "Center");
    }

    public void actionPerformed(ActionEvent e) {
        String name = tf1.getText();
        String truck = tf4.getText();
       
        String distance = tf6.getText();
        String phone = tf7.getText();

        if (e.getSource() == bt1) {
            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "insert into transport_driver(driver, truck, distance, phone) values('" + name + "','" + truck + "','" + distance + "','" + phone + "')";
                int aa = obj.stm.executeUpdate(q);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Your data was successfully inserted");
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
            new AdminSection().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Add_Transport_Driver().setVisible(true);
    }
}
