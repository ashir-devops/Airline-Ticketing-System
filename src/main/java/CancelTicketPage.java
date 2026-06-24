import java.awt.Font;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CancelTicketPage extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CancelTicketPage.class.getName());

    private JTextField txtCancelSearch;
    private JTextArea txtCancelTicketDetails;
    private JButton btnCancelSearch, btnConfirmCancelTicket, btnCancelBack;
    private String[] selectedTicket;

    public CancelTicketPage() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cancel Ticket");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("Cancel Ticket");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(200, 10, 200, 30);
        add(lblTitle);

        JLabel lblSearch = new JLabel("Enter TicketID or CNIC:");
        lblSearch.setBounds(20, 60, 180, 25);
        add(lblSearch);

        txtCancelSearch = new JTextField();
        txtCancelSearch.setBounds(200, 60, 150, 25);
        add(txtCancelSearch);

        btnCancelSearch = new JButton("Search");
        btnCancelSearch.setBounds(370, 60, 100, 25);
        btnCancelSearch.addActionListener(e -> performSearch());
        add(btnCancelSearch);

        txtCancelTicketDetails = new JTextArea();
        txtCancelTicketDetails.setEditable(false);
        txtCancelTicketDetails.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtCancelTicketDetails.setLineWrap(true);
        txtCancelTicketDetails.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtCancelTicketDetails);
        scrollPane.setBounds(20, 100, 550, 150);
        add(scrollPane);

        btnConfirmCancelTicket = new JButton("Cancel Ticket");
        btnConfirmCancelTicket.setBounds(120, 270, 150, 30);
        btnConfirmCancelTicket.setEnabled(false);
        btnConfirmCancelTicket.addActionListener(e -> performCancel());
        add(btnConfirmCancelTicket);

        btnCancelBack = new JButton("Back");
        btnCancelBack.setBounds(320, 270, 150, 30);
        btnCancelBack.addActionListener(e -> {
            this.dispose();
            new UserMainPage().setVisible(true);
        });
        add(btnCancelBack);
    }

    private void performSearch() {
        String searchValue = txtCancelSearch.getText().trim();
        ArrayList<String[]> tickets = DataManager.readTickets();

        for (String[] t : tickets) {
            if (t[0].equalsIgnoreCase(searchValue) || t[3].equals(searchValue)) {
                selectedTicket = t;
                txtCancelTicketDetails.setText(
                        "Ticket ID : " + t[0] + "\n" +
                                "Passenger : " + t[2] + "\n" +
                                "CNIC      : " + t[3] + "\n" +
                                "Flight    : " + t[1] + "\n" +
                                "Class     : " + t[5] + "\n" +
                                "Seat      : " + t[6] + "\n" +
                                "Price     : " + t[7]
                );
                btnConfirmCancelTicket.setEnabled(true);
                return;
            }
        }
        txtCancelTicketDetails.setText("Ticket not found!");
        btnConfirmCancelTicket.setEnabled(false);
    }

    private void performCancel() {
        String input = txtCancelSearch.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Booking ID or CNIC.");
            return;
        }

        // Must search first
        if (selectedTicket == null) {
            JOptionPane.showMessageDialog(this, "Search the ticket first!");
            return;
        }

        // Ticket ID from selected ticket
        String ticketID = selectedTicket[0];
        String flightID = selectedTicket[4];
        String seatClass = selectedTicket[5];

        // Load all tickets
        ArrayList<String[]> tickets = DataManager.readTickets();
        ArrayList<String[]> updatedTickets = new ArrayList<>();

        boolean found = false;

        // Remove the matching ticket
        for (String[] t : tickets) {
            if (t[0].equalsIgnoreCase(ticketID)) {
                found = true;
                continue; // skip → effectively removes it
            }
            updatedTickets.add(t);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Error cancelling booking!");
            return;
        }

        // Restore seat in flight
        AirlineSystem.Flight flight = AirlineSystem.FlightHandler.getFlightByID(flightID);
        if (flight != null) {
            flight.restoreSeat(seatClass);
            List<AirlineSystem.Flight> flights = AirlineSystem.FlightHandler.getAllFlights();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getFlightID().equals(flight.getFlightID())) {
                    flights.set(i, flight);
                    break;
                }
            }
            AirlineSystem.FlightHandler.updateFlights(flights);
        }

        // Save updated ticket list (without this ticket)
        DataManager.saveTickets(updatedTickets);

        JOptionPane.showMessageDialog(this, "Ticket cancelled successfully!");

        // Reset UI
        txtCancelSearch.setText("");
        txtCancelTicketDetails.setText("");
        btnConfirmCancelTicket.setEnabled(false);
        selectedTicket = null;
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new CancelTicketPage().setVisible(true));
    }
}
