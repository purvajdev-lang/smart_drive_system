package dss;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View_Profile extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
    JButton bt1,bt2;
    Font f,f1;
    JTextField usernameField;
    
    View_Profile()
    {
        super("View Your Profile");
        
        // Set frame to fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);
        
        l1 = new JLabel("View Your Profile");
        l2 = new JLabel("Username");
        l3 = new JLabel("Name");
        l4 = new JLabel("Age");
        l5 = new JLabel("Date of Birth");
        l6 = new JLabel("Address");
        l7 = new JLabel("Phone");
        l8 = new JLabel("Email");
        l9 = new JLabel("Country");
        l10 = new JLabel("Gender");
        l11 = new JLabel("Aadhar");
        
        usernameField = new JTextField();
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();
        
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        tf8.setEditable(false);
        tf9.setEditable(false);
        
        bt1 = new JButton("View Profile");
        bt2 = new JButton("Back");
        
        l1.setHorizontalAlignment(JLabel.CENTER);
        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        
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
        usernameField.setFont(f1);
        
        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);
        
        bt1.setFont(f1);
        bt2.setFont(f1);
        
        bt1.setBackground(Color.BLACK);
        bt2.setBackground(Color.RED);
        
        bt1.setForeground(Color.WHITE);
        bt2.setForeground(Color.WHITE);
        
        // Panel for the header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(l1, BorderLayout.CENTER);
        
        // Panel for form fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10));
        formPanel.add(l2);
        formPanel.add(usernameField);
        formPanel.add(l3);
        formPanel.add(tf1);
        formPanel.add(l4);
        formPanel.add(tf2);
        formPanel.add(l5);
        formPanel.add(tf3);
        formPanel.add(l6);
        formPanel.add(tf4);
        formPanel.add(l7);
        formPanel.add(tf5);
        formPanel.add(l8);
        formPanel.add(tf6);
        formPanel.add(l9);
        formPanel.add(tf7);
        formPanel.add(l10);
        formPanel.add(tf8);
        formPanel.add(l11);
        formPanel.add(tf9);
        formPanel.add(bt1);
        formPanel.add(bt2);
        
        // Panel for the image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("dss/icons/bookcab.jpg"));
        Image img1 = img.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH); // Scale image to fit the height
        ImageIcon ic1 = new ImageIcon(img1);
        JLabel imageLabel = new JLabel(ic1);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        // Add panels to the frame
        setLayout(new BorderLayout(10, 10));
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bt1)
        {
            String username = usernameField.getText();
            try
            {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM customer WHERE username = ?";
                PreparedStatement ps = obj.con.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next())
                {
                    tf1.setText(rs.getString("name"));
                    tf2.setText(rs.getString("age"));
                    tf3.setText(rs.getString("dob"));
                    tf4.setText(rs.getString("address"));
                    tf5.setText(rs.getString("phone"));
                    tf6.setText(rs.getString("email"));
                    tf7.setText(rs.getString("country"));
                    tf8.setText(rs.getString("gender"));
                    tf9.setText(rs.getString("aadhar"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Username not found");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == bt2)
        {
            this.setVisible(false);
            new HomePage().setVisible(true);
        }        
    }
    
    public static void main(String[] args) 
    {
        new View_Profile().setVisible(true);
    }
}
