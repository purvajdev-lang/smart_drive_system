/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dss;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class FeedbackForm extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea feedbackArea;
    JButton submitButton;
    
    FeedbackForm() {
        super("Feedback Form");
        setLocation(500,300);
        setSize(800,570);
        
        l1 = new JLabel("Please provide your feedback:");
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        
        feedbackArea = new JTextArea(10, 30);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(l1, BorderLayout.NORTH);
        panel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton)
        {
            String feedback=feedbackArea.getText();
             try
            {
                ConnectionClass obj3=new ConnectionClass();
                  String query = "INSERT INTO feedback (feedback) VALUES (?)";
                   PreparedStatement pstmt = obj3.con.prepareStatement(query);
                pstmt.setString(1, feedback);
                 int result = pstmt.executeUpdate();
                 if (result == 1) {
                    JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
                    this.setVisible(false); // Close the feedback form
                } else {
                    JOptionPane.showMessageDialog(this, "Please try again.");
                }
                   pstmt.close();
            }
             catch(Exception ee)
             {
                  ee.printStackTrace();
                  JOptionPane.showMessageDialog(this, "An error occurred while submitting feedback.");
            
             }
        }
    }
     public static void main(String args[])
    {
        new FeedbackForm().setVisible(true);
    }
}


