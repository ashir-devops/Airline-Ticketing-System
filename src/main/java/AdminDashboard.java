/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class AdminDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboard.class.getName());

    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(248, 248, 248)); // light grayish background
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        jLabel1 = new JLabel("Admin Dashboard");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 30));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setForeground(new Color(44, 62, 80)); // dark charcoal
        mainPanel.add(jLabel1, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(mainPanel.getBackground());
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); // closer together

        btnManageFlights = createButton("Manage Flights", new Color(123, 104, 238)); // soft pastel purple
        btnManageFlights.addActionListener(this::btnManageFlightsActionPerformed);

        btnViewBookings = createButton("View Bookings", new Color(123, 104, 238));
        btnViewBookings.addActionListener(this::btnViewBookingsActionPerformed);

        buttonsPanel.add(btnManageFlights);
        buttonsPanel.add(btnViewBookings);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Logout button
        btnLogout = createButton("Logout", new Color(120, 160, 200)); // muted soft blue
// soft pastel blue
        btnLogout.setPreferredSize(new Dimension(120, 35));
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(mainPanel.getBackground());
        bottomPanel.add(btnLogout);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setSize(600, 280); // reduced height
        setLocationRelativeTo(null); // center
    }

    // Button actions
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new AdminLoginPage().setVisible(true);
    }

    private void btnManageFlightsActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new ManageFlightsPage().setVisible(true);
    }

    private void btnViewBookingsActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new ViewAllBookingsPage().setVisible(true);
    }

    /**
     * Main method
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new AdminDashboard().setVisible(true));
    }

    // Variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageFlights;
    private javax.swing.JButton btnViewBookings;
    private javax.swing.JLabel jLabel1;

    // Button factory
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(8));
        btn.setPreferredSize(new Dimension(140, 38));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor);
            }
        });

        return btn;
    }

    // Rounded border class
    class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int radius) { this.radius = radius; }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(150, 150, 150));
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
