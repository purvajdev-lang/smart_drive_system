package dss;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminSection extends JFrame implements ActionListener {
    JLabel backgroundLabel, titleLabel;
    CardPanel cardPanel;
    JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, exitButton;
    Font titleFont, buttonFont;

    AdminSection() {
        super("Admin Section");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Define fonts
        titleFont = new Font("Arial", Font.BOLD, 30); // Increased title font size
        buttonFont = new Font("Arial", Font.BOLD, 15);

        // Create buttons
        bt1 = new JButton("Add Intercity Driver");
        bt2 = new JButton("Add Intracity Driver");
        bt3 = new JButton("Add Transport Driver");
        bt4 = new JButton("View Customer");
        bt5 = new JButton("View Intracity Booked Cab");
        bt6 = new JButton("View Booked Package");
        bt7 = new JButton("View Intercity Booked Cab");
        bt8 = new JButton("View Feedbacks");
        bt9 = new JButton("Delete Customer");
        exitButton = new JButton("X"); // Exit button

        bt1.setFont(buttonFont);
        bt2.setFont(buttonFont);
        bt3.setFont(buttonFont);
        bt4.setFont(buttonFont);
        bt5.setFont(buttonFont);
        bt6.setFont(buttonFont);
        bt7.setFont(buttonFont);
        bt8.setFont(buttonFont);
        bt9.setFont(buttonFont);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);

        bt1.setPreferredSize(new Dimension(100, 10)); // Decreased button width
        bt2.setPreferredSize(new Dimension(150, 30));
        bt3.setPreferredSize(new Dimension(150, 30));
        bt4.setPreferredSize(new Dimension(150, 30));
        bt5.setPreferredSize(new Dimension(150, 30));
        bt6.setPreferredSize(new Dimension(150, 30));
        bt7.setPreferredSize(new Dimension(150, 30));
        bt8.setPreferredSize(new Dimension(150, 30));
        bt9.setPreferredSize(new Dimension(150, 30));

        exitButton.setPreferredSize(new Dimension(40, 40)); // Size for the exit button

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
        bt6.addActionListener(this);
        bt7.addActionListener(this);
        bt8.addActionListener(this);
        bt9.addActionListener(this);
        exitButton.addActionListener(e -> System.exit(0)); // Exit application

        // Create card panel with background image
        cardPanel = new CardPanel(); // Use custom panel
        cardPanel.setLayout(new GridLayout(10, 1, 10, 10));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border around card
        cardPanel.setOpaque(false);

        // Add title label
        titleLabel = new JLabel("ADMIN SECTION", SwingConstants.CENTER);
        titleLabel.setFont(titleFont); // Set title font size
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(false);

        // Add buttons to card panel
        cardPanel.add(titleLabel); // Add title label
        cardPanel.add(bt1);
        cardPanel.add(bt2);
        cardPanel.add(bt3);
        cardPanel.add(bt4);
        cardPanel.add(bt5);
        cardPanel.add(bt6);
        cardPanel.add(bt7);
        cardPanel.add(bt8);
        cardPanel.add(bt9);

        // Background image
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(cardPanel, BorderLayout.CENTER);

        // Add exit button to top-right corner
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(exitButton, BorderLayout.EAST);

        // Set layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(backgroundLabel, BorderLayout.CENTER);

        // Set frame properties
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Update background image after JFrame is visible
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundImage();
            }
        });
    }

    private void updateBackgroundImage() {
        // Load and set background image
        ImageIcon background = new ImageIcon(getClass().getResource("/dss/icons/indexpage.jpg"));
        if (background.getIconWidth() > 0 && background.getIconHeight() > 0) {
            Image img = background.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(img));
        } else {
            System.err.println("Background image not found!");
        }
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            new Add_InterCity_Driver().setVisible(true);
        }
        if (e.getSource() == bt2) {
            new Add_IntraCity_Driver().setVisible(true);
        }
        if (e.getSource() == bt3) {
            new Add_Transport_Driver().setVisible(true);
        }
        if (e.getSource() == bt4) {
            new View_Customer().setVisible(true);
        }
        if (e.getSource() == bt5) {
            new View_BookedCab().setVisible(true);
        }
        if (e.getSource() == bt6) {
            new View_Booked_Package().setVisible(true);
        }
        if (e.getSource() == bt7) {
            new View_Intercity_Booked_Cab().setVisible(true);
        }
        if (e.getSource() == bt8) {
            new View_Feedback().setVisible(true);
        }
        if (e.getSource() == bt9) {
            new Delete_Customer().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminSection::new);
    }

    // Custom JPanel to paint the background image
    class CardPanel extends JPanel {
        private final ImageIcon backgroundImage;

        public CardPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("/dss/icons/yellow.jpeg")); // Path to your card background image
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
