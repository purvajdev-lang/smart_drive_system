package dss;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class View_Feedback extends JFrame {
    JTable t;
    String[] columnNames = {"Feedback"};
    String[][] data = new String[20][2]; // Adjust size if needed
    int i = 0;
    
    Font font;
    
    View_Feedback() {
        super("View Feedback Records");
        setSize(800, 400);
        setLocation(100, 100);
        font = new Font("MS UI Gothic", Font.BOLD, 14);
        
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT feedback FROM feedback";
            ResultSet rs = obj.stm.executeQuery(query);
            
            while (rs.next()) {
                data[i][0] = rs.getString("feedback");
                
                i++;
            }
            
            t = new JTable(data, columnNames);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        t.setFont(font);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(t);
        add(scrollPane);
    }
    
    public static void main(String[] args) {
       {
            new View_Feedback().setVisible(true);
        }
    }
}
