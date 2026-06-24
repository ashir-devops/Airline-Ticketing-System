import java.util.List;
import javax.swing.*;
import java.awt.*;

/*
 * UserMainPage - Modern and professional layout
 */
public class UserMainPage extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserMainPage.class.getName());

    // Buttons
    private JButton btnViewAllFlights;
    private JButton btnSearchFlights;
    private JButton btnViewMyBookings;
    private JButton btnCancelTicket;
    private JButton btnUserBack;

    // Labels
    private JLabel lblUserPortal;

    public UserMainPage() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Main title
        lblUserPortal = new JLabel("User Portal", SwingConstants.CENTER);
        lblUserPortal.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblUserPortal.setForeground(Color.WHITE);

        // Buttons
        btnViewAllFlights = createButton("View All Flights");
        btnSearchFlights = createButton("Search Flights");
        btnViewMyBookings = createButton("View My Bookings");
        btnCancelTicket = createButton("Cancel Ticket");
        btnUserBack = createButton("Back");

        // Actions
        btnViewAllFlights.addActionListener(e -> viewAllFlights());
        btnSearchFlights.addActionListener(e -> { this.dispose(); new SearchFlightsPage().setVisible(true); });
        btnViewMyBookings.addActionListener(e -> { this.dispose(); new MyBookingsPage().setVisible(true); });
        btnCancelTicket.addActionListener(e -> { this.dispose(); new CancelTicketPage().setVisible(true); });
        btnUserBack.addActionListener(e -> { this.dispose(); new WelcomePage().setVisible(true); });

        // Panel for buttons
        JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(new Color(100, 120, 160)); // medium blue-purple
        pnlButtons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing between buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; pnlButtons.add(btnViewAllFlights, gbc);
        gbc.gridx = 1; gbc.gridy = 0; pnlButtons.add(btnSearchFlights, gbc);
        gbc.gridx = 0; gbc.gridy = 1; pnlButtons.add(btnViewMyBookings, gbc);
        gbc.gridx = 1; gbc.gridy = 1; pnlButtons.add(btnCancelTicket, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; pnlButtons.add(btnUserBack, gbc);

        // Main layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(lblUserPortal, BorderLayout.NORTH);
        getContentPane().add(pnlButtons, BorderLayout.CENTER);

        // Frame settings
        getContentPane().setBackground(new Color(60, 80, 120)); // soft blue background
        setTitle("User Portal");
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBackground(new Color(180, 200, 230));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(160, 35));
        return btn;
    }

    private void viewAllFlights() {
        List<AirlineSystem.Flight> flights = AirlineSystem.FlightHandler.getAllFlights();

        if (flights.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flights available.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (AirlineSystem.Flight f : flights) {
            sb.append("ID: ").append(f.getFlightID())
                    .append("\nFrom: ").append(f.getSource())
                    .append("\nTo: ").append(f.getDestination())
                    .append("\nDate: ").append(f.getDate())
                    .append("\nTime: ").append(f.getTime())
                    .append("\nBase Fare: $").append(f.getBaseFare())
                    .append("\nFirst Class Seats: ").append(f.getFirstSeats())
                    .append("\nBusiness Seats: ").append(f.getBusinessSeats())
                    .append("\nEconomy Seats: ").append(f.getEconomySeats())
                    .append("\n----------------------------------------\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "All Flights", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(() -> new UserMainPage().setVisible(true));
    }
}
