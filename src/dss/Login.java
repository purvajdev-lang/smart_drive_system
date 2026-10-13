package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
    JPanel panel;
    JFrame f;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1;
    JPasswordField pf1;
    JButton b1,b2,b3;
        
    Login()
    {
        f=new JFrame("Login Account");
        f.setBackground(Color.WHITE);
        f.setLayout(null);
        
        l1=new JLabel();
        l1.setBounds(0,0,580,350);
        l1.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("dss/icons/login3.jpg"));
        Image i1=img.getImage().getScaledInstance(580,350,Image.SCALE_DEFAULT);
        ImageIcon img1=new ImageIcon(i1);
        l1.setIcon(img1);
        
        l2=new JLabel("Login Account");
        l2.setBounds(190,30,500,50);
        l2.setFont(new Font("Airal",Font.BOLD,30));
        l2.setForeground(Color.PINK);
        l1.add(l2);
        f.add(l1);
        
        l3=new JLabel("Username : ");
        l3.setBounds(120,120,150,30);
        l3.setForeground(Color.ORANGE);
        l3.setFont(new Font("Arial",Font.BOLD,20));
        l1.add(l3);
        
        t1=new JTextField();
        t1.setBounds(320,120,150,30);
        l1.add(t1);
        
        l4 = new JLabel("Password : ");
        l4.setBounds(120,170,150,30);
        l4.setForeground(Color.ORANGE);
        l4.setFont(new Font("Airal",Font.BOLD,20));
        l1.add(l4);

        pf1 = new JPasswordField();
        pf1.setBounds(320,170,150,30);
        l1.add(pf1);
        
        b1 = new JButton("Login");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(120,220,150,40);
        
        l1.add(b1);
        
        b2=new JButton("SignUp");   
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        b2.setBounds(320,220,150,40);
        
        l1.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        f.setVisible(true);
        f.setSize(500,350);
        f.setLocation(300,100);
        f.setResizable(false);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == b1)
        {
            try
            {
                ConnectionClass obj=new ConnectionClass();
                String name=t1.getText();
                String pass=pf1.getText();
                String q="select * from signup where username='"+name+"' and password='"+pass+"'";
                ResultSet rs=obj.stm.executeQuery(q);

                if(rs.next())
                {
                    new HomePage().setVisible(true);
                    f.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You have entered Wrong Username and Password !");
                    f.setVisible(false);
                    f.setVisible(true);   
                }    
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        if(ae.getSource()==b2)
        {
            this.f.setVisible(false);
            new Signup();
        }
    }
    public static void main(String[] args) 
    {
        new Login();
    }
}

