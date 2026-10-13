/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View_Intercity_Booked_Cab extends JFrame
{
    JTable t;
    JButton bt1;
    JTextField tf1;
    JPanel p1,p2,p3;
    String x[]={"Book ID","Username","Name","Driver Name","Source","Destination","Car","Price"};
    String y[][]=new String [20][9];
    int i=0,j=0;
    Font f,f1;
    JLabel l1,l2;
    
    View_Intercity_Booked_Cab()
    {
        super("All intercity Booked Cab records");
        setSize(1300,400);
        setLocation(0,10);
        f=new Font("MS UI Gothic",Font.BOLD,17);
        
        try
        {
            ConnectionClass obj=new ConnectionClass();
            String q="select * from intercitycab";
            ResultSet rest=obj.stm.executeQuery(q);
            while(rest.next())
            {
                y[i][j++]=rest.getString("BookID");
                y[i][j++]=rest.getString("username");
                y[i][j++]=rest.getString("name");
                y[i][j++]=rest.getString("drivername");
                y[i][j++]=rest.getString("source");
                y[i][j++]=rest.getString("destination");
               
                y[i][j++]=rest.getString("car");
                y[i][j++]=rest.getString("price");
                i++;
                j=0;
            }
            t=new JTable(y,x);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        t.setFont(f);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        
        JScrollPane js=new JScrollPane(t);
        add(js);
    }
    public static void main(String args[])
    {
        new View_Intercity_Booked_Cab().setVisible(true);
    }
}


