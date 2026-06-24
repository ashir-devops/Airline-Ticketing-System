import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ManageFlightsPage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageFlightsPage.class.getName());

    /**
     * Inner class for updating a flight
     */
    private class UpdateFlightDialog extends JDialog {

        private AirlineSystem.Flight flight;
        private JTextField txtSource, txtDestination, txtDate, txtTime;
        private JTextField txtBaseFare, txtFirstSeats, txtBusinessSeats, txtEconomySeats;
        private JButton btnUpdate, btnCancel;

        public UpdateFlightDialog(Frame parent, boolean modal, AirlineSystem.Flight flight) {
            super(parent, modal);
            this.flight = flight;
            initDialog();
        }

        private void initDialog() {
            setTitle("Update Flight: " + flight.getFlightID());
            setSize(450, 400);
            setLocationRelativeTo(getParent());
            setLayout(new GridLayout(9, 2, 10, 10));
            getContentPane().setBackground(new Color(245, 245, 245));

            Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

            add(new JLabel("Source:")).setFont(labelFont);
            txtSource = new JTextField(flight.getSource());
            add(txtSource);

            add(new JLabel("Destination:")).setFont(labelFont);
            txtDestination = new JTextField(flight.getDestination());
            add(txtDestination);

            add(new JLabel("Date (YYYY-MM-DD):")).setFont(labelFont);
            txtDate = new JTextField(flight.getDate());
            add(txtDate);

            add(new JLabel("Time (HH:MM):")).setFont(labelFont);
            txtTime = new JTextField(flight.getTime());
            add(txtTime);

            add(new JLabel("Base Fare:")).setFont(labelFont);
            txtBaseFare = new JTextField(String.valueOf(flight.getBaseFare()));
            add(txtBaseFare);

            add(new JLabel("First Class Seats:")).setFont(labelFont);
            txtFirstSeats = new JTextField(String.valueOf(flight.getFirstSeats()));
            add(txtFirstSeats);

            add(new JLabel("Business Seats:")).setFont(labelFont);
            txtBusinessSeats = new JTextField(String.valueOf(flight.getBusinessSeats()));
            add(txtBusinessSeats);

            add(new JLabel("Economy Seats:")).setFont(labelFont);
            txtEconomySeats = new JTextField(String.valueOf(flight.getEconomySeats()));
            add(txtEconomySeats);

            btnUpdate = new JButton("Update");
            btnUpdate.setBackground(new Color(75, 150, 255));
            btnUpdate.setForeground(Color.WHITE);
            btnUpdate.setFocusPainted(false);

            btnCancel = new JButton("Cancel");
            btnCancel.setBackground(new Color(200, 50, 50));
            btnCancel.setForeground(Color.WHITE);
            btnCancel.setFocusPainted(false);

            add(btnUpdate);
            add(btnCancel);

            btnUpdate.addActionListener(this::updateFlight);
            btnCancel.addActionListener(e -> dispose());
        }

        private void updateFlight(ActionEvent e) {
            try {
                String source = txtSource.getText().trim();
                String destination = txtDestination.getText().trim();
                String date = txtDate.getText().trim();
                String time = txtTime.getText().trim();

                double baseFare = Double.parseDouble(txtBaseFare.getText().trim());
                int firstSeats = Integer.parseInt(txtFirstSeats.getText().trim());
                int businessSeats = Integer.parseInt(txtBusinessSeats.getText().trim());
                int economySeats = Integer.parseInt(txtEconomySeats.getText().trim());

                AirlineSystem.Flight updatedFlight = new AirlineSystem.Flight(
                        flight.getFlightID(),
                        source,
                        destination,
                        date,
                        time,
                        baseFare,
                        firstSeats,
                        businessSeats,
                        economySeats
                );

                List<AirlineSystem.Flight> flights = AirlineSystem.FlightHandler.getAllFlights();
                for (int i = 0; i < flights.size(); i++) {
                    if (flights.get(i).getFlightID().equals(flight.getFlightID())) {
                        flights.set(i, updatedFlight);
                        break;
                    }
                }

                AirlineSystem.FlightHandler.updateFlights(flights);

                JOptionPane.showMessageDialog(this, "Flight updated successfully!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid values. Please check your inputs.");
            }
        }
    }

    /**
     * Refreshes the flight table
     */
    private void refreshFlightTable() {
        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) tblFlights.getModel();

        model.setRowCount(0); // clear table

        for (AirlineSystem.Flight f : AirlineSystem.FlightHandler.getAllFlights()) {
            model.addRow(new Object[]{
                    f.getFlightID(),
                    f.getSource(),
                    f.getDestination(),
                    f.getDate(),
                    f.getTime(),
                    f.getBaseFare(),
                    f.getFirstSeats(),
                    f.getBusinessSeats(),
                    f.getEconomySeats()
            });
        }
    }

    /**
     * Creates new form ManageFlightsPage
     */
    public ManageFlightsPage() {
        initComponents();
        getContentPane().setBackground(new Color(245, 245, 245));
        refreshFlightTable();

        tblFlights.setRowHeight(25);
        tblFlights.setGridColor(Color.GRAY);
        tblFlights.setShowGrid(true);
        tblFlights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFlightsTitle = new javax.swing.JLabel();
        scrollFlights = new javax.swing.JScrollPane();
        tblFlights = new javax.swing.JTable();
        btnAddFlight = new javax.swing.JButton();
        btnUpdateFlight = new javax.swing.JButton();
        btnRemoveFlight = new javax.swing.JButton();
        btnFlightsBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFlightsTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblFlightsTitle.setForeground(Color.BLACK);
        lblFlightsTitle.setText("Manage Flights");

        tblFlights.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Flight ID", "Source", "Destination", "Date", "Time", "Base Fare", "First Seats", "Business Seats", "Economy Seats"
                }
        ));
        scrollFlights.setViewportView(tblFlights);

        btnAddFlight.setText("Add Flight");
        btnAddFlight.setBackground(new Color(75, 150, 255));
        btnAddFlight.setForeground(Color.WHITE);
        btnAddFlight.setFocusPainted(false);
        btnAddFlight.addActionListener(this::btnAddFlightActionPerformed);

        btnUpdateFlight.setText("Update Flight");
        btnUpdateFlight.setBackground(new Color(75, 150, 255));
        btnUpdateFlight.setForeground(Color.WHITE);
        btnUpdateFlight.setFocusPainted(false);
        btnUpdateFlight.addActionListener(this::btnUpdateFlightActionPerformed);

        btnRemoveFlight.setText("Remove Flight");
        btnRemoveFlight.setBackground(new Color(200, 50, 50));
        btnRemoveFlight.setForeground(Color.WHITE);
        btnRemoveFlight.setFocusPainted(false);
        btnRemoveFlight.addActionListener(this::btnRemoveFlightActionPerformed);

        btnFlightsBack.setText("Back");
        btnFlightsBack.setBackground(new Color(100, 100, 100));
        btnFlightsBack.setForeground(Color.WHITE);
        btnFlightsBack.setFocusPainted(false);
        btnFlightsBack.addActionListener(this::btnFlightsBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(38, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblFlightsTitle)
                                                .addGap(300, 300, 300))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(scrollFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(btnAddFlight)
                                .addGap(78, 78, 78)
                                .addComponent(btnUpdateFlight)
                                .addGap(90, 90, 90)
                                .addComponent(btnRemoveFlight)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFlightsBack)
                                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lblFlightsTitle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAddFlight)
                                        .addComponent(btnUpdateFlight)
                                        .addComponent(btnRemoveFlight)
                                        .addComponent(btnFlightsBack))
                                .addContainerGap(111, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFlightsBackActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new AdminDashboard().setVisible(true);
    }

    private void btnAddFlightActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new AddFlightsPage().setVisible(true);
    }

    private void btnUpdateFlightActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblFlights.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a flight to update.");
            return;
        }

        String flightID = tblFlights.getValueAt(selectedRow, 0).toString();
        AirlineSystem.Flight flightToUpdate = AirlineSystem.FlightHandler.getAllFlights()
                .stream()
                .filter(f -> f.getFlightID().equals(flightID))
                .findFirst()
                .orElse(null);

        if (flightToUpdate != null) {
            new UpdateFlightDialog(this, true, flightToUpdate).setVisible(true);
            refreshFlightTable();
        }
    }

    private void btnRemoveFlightActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblFlights.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a flight to remove.");
            return;
        }

        String flightID = tblFlights.getValueAt(selectedRow, 0).toString();
        boolean removed = AirlineSystem.FlightHandler.removeFlight(flightID);
        if (removed) {
            JOptionPane.showMessageDialog(this, "Flight removed successfully.");
            refreshFlightTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove flight.");
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new ManageFlightsPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFlight;
    private javax.swing.JButton btnFlightsBack;
    private javax.swing.JButton btnRemoveFlight;
    private javax.swing.JButton btnUpdateFlight;
    private javax.swing.JLabel lblFlightsTitle;
    private javax.swing.JScrollPane scrollFlights;
    private javax.swing.JTable tblFlights;
    // End of variables declaration//GEN-END:variables
}
