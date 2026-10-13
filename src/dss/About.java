package dss;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class About extends JFrame implements ActionListener 
{
    JButton b1;
    JLabel l1;
    Font f, f1, f2;
    TextArea t1;
    String s;
    
    public About() 
    {
        super("About All Project Details");
        setLayout(null);
        JButton b1 = new JButton("Exit");
        add(b1);
        b1.setBounds(180, 430, 120, 20);
        b1.addActionListener(this);
        
        Font f = new Font("RALEWAY", Font.BOLD, 180);
        setFont(f);

      String s = "                                    About Projects          \n  "
                + "\nPRIME WHEELS is an excellent transportation system designed to meet an array of customer demands for intercity, intracity, and transport cab services. "
                + "The system simplifies the booking process and offers user-friendly interfaces, ensuring ease and accessibility for users.\n\n"
                + "A valuable aspect of the platform is its unique feature of retaining ride information post-trip, allowing administrators to examine trends, improve operations, "
                + "and make well-informed decisions.\n\n"
                + "By consistently integrating technological advancements and user feedback, the system is well-positioned for ongoing innovation and progression.";




        TextArea t1 = new TextArea(s, 10, 40, Scrollbar.VERTICAL);
        t1.setEditable(false);
        t1.setBounds(20, 100, 450, 300);

        add(t1);

        Font f1 = new Font("RALEWAY", Font.BOLD, 16);
        t1.setFont(f1);

        Container contentPane = this.getContentPane();
        t1 = new TextArea();

        JLabel l1 = new JLabel("About Project");
        add(l1);
        l1.setBounds(170, 10, 180, 80);
        l1.setForeground(Color.red);

        Font f2 = new Font("RALEWAY", Font.BOLD, 20);
        l1.setFont(f2);

        setBounds(700, 220, 500, 550);

        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) 
    {
        dispose();
    }
    public static void main(String args[]) 
    {
        new About().setVisible(true);
    }
}
