import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class WelcomePage extends JFrame {

    private javax.swing.JButton btnAdminLogin;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnUserLogin;
    private javax.swing.JLabel lblSubtitle;

    public WelcomePage() {
        initComponents();
    }

    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(220, 225, 240)); // soft purple-blue background
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        mainPanel.setLayout(new BorderLayout(0, 25));

        // Title / subtitle
        lblSubtitle = new JLabel("AIRLINE RESERVATION SYSTEM");
        lblSubtitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitle.setForeground(new Color(60, 50, 90)); // dark purple-blue text
        lblSubtitle.setOpaque(true);
        lblSubtitle.setBackground(new Color(200, 200, 230)); // soft purple-blue
        lblSubtitle.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 210), 2));
        lblSubtitle.setPreferredSize(new Dimension(450, 60));
        mainPanel.add(lblSubtitle, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(220, 225, 240));
        buttonsPanel.setLayout(new GridLayout(1, 3, 20, 10));

        btnAdminLogin = createButton("Admin Login", new Color(160, 160, 230));
        btnAdminLogin.addActionListener(evt -> {
            this.dispose();
            new AdminLoginPage().setVisible(true);
        });

        btnUserLogin = createButton("User Login", new Color(170, 180, 250));
        btnUserLogin.addActionListener(evt -> {
            this.dispose();
            new UserLoginPage().setVisible(true);
        });

        btnExit = createButton("Exit", new Color(230, 180, 200));
        btnExit.addActionListener(evt -> System.exit(0));

        buttonsPanel.add(btnAdminLogin);
        buttonsPanel.add(btnUserLogin);
        buttonsPanel.add(btnExit);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome");
        setSize(650, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setOpaque(true); // important! ensures color is painted
        btn.setBorderPainted(false);
        btn.setBackground(bgColor);
        btn.setForeground(new Color(50, 40, 70)); // dark text
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

    public static void main(String[] args) {
        // Make sure Nimbus or system look doesn't override colors
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new WelcomePage().setVisible(true));
    }

    class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int radius) { this.radius = radius; }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(180, 180, 200));
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
