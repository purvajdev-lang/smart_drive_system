package dss;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Index extends JFrame implements ActionListener {
    JLabel backgroundLabel;
    CardPanel cardPanel;
    JButton adminLoginButton, customerLoginButton, exitButton;
    Font titleFont, buttonFont;

    Index() {
        super("Home Page");

        // Set the frame to full screen and remove window decorations
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Define fonts
        titleFont = new Font("Arial", Font.BOLD, 30);
        buttonFont = new Font("Arial", Font.BOLD, 15);

        // Create buttons
        adminLoginButton = new JButton("Admin Login");
        customerLoginButton = new JButton("Customer Login");
        exitButton = new JButton("X"); // Exit button

        adminLoginButton.setFont(buttonFont);
        customerLoginButton.setFont(buttonFont);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);

        adminLoginButton.addActionListener(this);
        customerLoginButton.addActionListener(this);
        exitButton.addActionListener(e -> System.exit(0)); // Exit application

        // Create card panel
        cardPanel = new CardPanel(); // Use custom panel
        cardPanel.setLayout(new GridLayout(3, 1, 10, 10));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border around card
        cardPanel.setPreferredSize(new Dimension(400, 250));

        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("PRIME WHEELS");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLACK); // Title color
        titlePanel.add(titleLabel);
        titlePanel.setOpaque(false);

        // Add components to card panel
        cardPanel.add(titlePanel);
        cardPanel.add(customerLoginButton);
        cardPanel.add(adminLoginButton);

        // Create a main panel to center the card panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false); // Transparent panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the card panel
        mainPanel.add(cardPanel, gbc);

        // Background image
        backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(mainPanel, BorderLayout.CENTER);

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
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("dss/icons/indexpage.jpg"));
        if (background.getIconWidth() > 0 && background.getIconHeight() > 0) {
            Image img = background.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(img));
        }
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            new AdminLogin().setVisible(true);
        }
        if (e.getSource() == customerLoginButton) {
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Index::new);
    }

    // Custom JPanel to paint the background image
    class CardPanel extends JPanel {
        private final ImageIcon backgroundImage;

        public CardPanel() {
            backgroundImage = new ImageIcon(ClassLoader.getSystemResource("dss/icons/yellow.jpeg")); // Path to your card background image
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
