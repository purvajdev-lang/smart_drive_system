package dss;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener
{
    JLabel l1;
    Font f, f1;

    HomePage()
    {
        super("PRIME WHEELS");
        setLocation(0, 0);
        setSize(1550, 800);

        f = new Font("Lucida Fax", Font.BOLD, 20);
        f1 = new Font("MS UI Gothic", Font.BOLD, 18);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("dss/icons/IndexBG2.png"));
        Image img = ic.getImage().getScaledInstance(1550, 800, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img);
        l1 = new JLabel(ic1);

        JMenuBar m1 = new JMenuBar();
        
        JMenu men1 = new JMenu("Customer Profile");
        JMenuItem ment1 = new JMenuItem("Complete Profile");
        JMenuItem ment16 = new JMenuItem("View Profile");
        JMenuItem ment17 = new JMenuItem("View Ride History");

        JMenu men2 = new JMenu("Manage Customer");
        JMenuItem ment3 = new JMenuItem("Update Customer Details");

        JMenu men3 = new JMenu("Book Intracity Cab");
        JMenuItem ment5 = new JMenuItem("Book Cab");

        JMenu men4 = new JMenu("Book Intercity Cab");
        JMenuItem ment7 = new JMenuItem("Book Intercity Cab");

        JMenu men5 = new JMenu("Transport");
        JMenuItem ment9 = new JMenuItem("Book Package");

        JMenu men6 = new JMenu("Bill");
        JMenuItem ment11 = new JMenuItem("Check Bill");

        JMenu men8 = new JMenu("About");
        JMenuItem ment13 = new JMenuItem("About");
        JMenuItem ment14 = new JMenuItem("Exit");

        JMenu men9 = new JMenu("Feedback");
        JMenuItem ment15 = new JMenuItem("Submit Feedback");

       

        // Add items to menus
        men1.add(ment1);
        men1.add(ment16);
         men1.add(ment17);
        men2.add(ment3);
        men3.add(ment5);
        men4.add(ment7);
        men5.add(ment9);
        men6.add(ment11);
        men8.add(ment13);
        men8.add(ment14);
        men9.add(ment15);
        

        // Add menus to menu bar
        m1.add(men1);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men2);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men3);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men4);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men5);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men6);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men8);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
        m1.add(men9);
        m1.add(new JSeparator(SwingConstants.VERTICAL)); // Add separator
       

        // Set fonts and colors
        men1.setFont(f);
        men2.setFont(f);
        men3.setFont(f);
        men4.setFont(f);
        men5.setFont(f);
        men6.setFont(f);
        men8.setFont(f);
        men9.setFont(f);
       

        ment1.setFont(f1);
        ment3.setFont(f1);
        ment5.setFont(f1);
        ment7.setFont(f1);
        ment9.setFont(f1);
        ment11.setFont(f1);
        ment13.setFont(f1);
        ment14.setFont(f1);
        ment15.setFont(f1);
        
         ment16.setFont(f1);
         ment17.setFont(f1);

        m1.setBackground(Color.BLACK);
        men1.setForeground(Color.GRAY);
        men2.setForeground(Color.GRAY);
        men3.setForeground(Color.GRAY);
        men4.setForeground(Color.GRAY);
        men5.setForeground(Color.GRAY);
        men6.setForeground(Color.GRAY);
        men8.setForeground(Color.GRAY);
        men9.setForeground(Color.GRAY);
       

        ment1.setBackground(Color.BLACK);
        ment3.setBackground(Color.BLACK);
        ment5.setBackground(Color.BLACK);
        ment7.setBackground(Color.BLACK);
        ment9.setBackground(Color.BLACK);
        ment11.setBackground(Color.BLACK);
        ment13.setBackground(Color.BLACK);
        ment14.setBackground(Color.BLACK);
        ment15.setBackground(Color.BLACK);
       
        ment16.setBackground(Color.BLACK);
         ment17.setBackground(Color.BLACK);

        ment1.setForeground(Color.YELLOW);
        ment3.setForeground(Color.YELLOW);
        ment5.setForeground(Color.YELLOW);
        ment7.setForeground(Color.YELLOW);
        ment9.setForeground(Color.YELLOW);
        ment11.setForeground(Color.YELLOW);
       
        ment13.setForeground(Color.YELLOW);
        ment14.setForeground(Color.YELLOW);
        ment15.setForeground(Color.YELLOW);
         ment16.setForeground(Color.YELLOW);
          ment17.setForeground(Color.YELLOW);

        // Add action listeners
        ment1.addActionListener(this);
        ment3.addActionListener(this);
        ment5.addActionListener(this);
        ment7.addActionListener(this);
        ment9.addActionListener(this);
        ment11.addActionListener(this);
        ment13.addActionListener(this);
        ment14.addActionListener(this);
        ment15.addActionListener(this);
       
        ment16.addActionListener(this);
         ment17.addActionListener(this);

        setJMenuBar(m1);
        add(l1);
    }

    public void actionPerformed(ActionEvent e)
    {
        String comnd = e.getActionCommand();
        if (comnd.equals("Complete Profile"))
        {
            new Add_Customer();
        }
         else if (comnd.equals("View Profile"))
        {
            new View_Profile().setVisible(true);
        }
          else if (comnd.equals("View Ride History"))
        {
            new View_Ride_History().setVisible(true);
        }
        else if (comnd.equals("Update Customer Details"))
        {
            new Update_Customer().setVisible(true);
        }
        else if (comnd.equals("Book Cab"))
        {
            new Book_Cab().setVisible(true);
        }
        else if (comnd.equals("Book Intercity Cab"))
        {
            new Book_Intercity_Cab().setVisible(true);
        }
        else if (comnd.equals("Book Package"))
        {
            new Book_Package().setVisible(true);
        }
        else if (comnd.equals("Check Bill"))
        {
            new CheckBill().setVisible(true);
        }
        else if (comnd.equals("About"))
        {
            new About().setVisible(true);
        }
        else if (comnd.equals("Exit"))
        {
            System.exit(0);
        }
        else if (comnd.equals("Submit Feedback"))
        {
            new FeedbackForm().setVisible(true);
        }
        else if (comnd.equals("Admin Login"))
        {
            new Index().setVisible(true);
        }
    }

    public static void main(String[] args)
    {
        new HomePage().setVisible(true);
    }
}
