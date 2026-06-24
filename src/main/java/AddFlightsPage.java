import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ashir
 */
public class AddFlightsPage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddFlightsPage.class.getName());

    /**
     * Creates new form AddFlightsPage
     */
    public AddFlightsPage() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setForeground(java.awt.Color.BLACK);
        setTitle("Add New Flight");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnBusinessSeats = new javax.swing.JLabel();
        btnConfirmAddFlight = new javax.swing.JButton();
        btnEconomySeats = new javax.swing.JLabel();
        btnCancelButton = new javax.swing.JButton();
        txtFlightID = new javax.swing.JTextField();
        txtSource = new javax.swing.JTextField();
        lblFlightID = new javax.swing.JLabel();
        txtDestination = new javax.swing.JTextField();
        btnSource = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        btnDestination = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        btnDate = new javax.swing.JLabel();
        txtBaseFare = new javax.swing.JTextField();
        btnTime = new javax.swing.JLabel();
        txtFirstSeats = new javax.swing.JTextField();
        btnBaseFare = new javax.swing.JLabel();
        txtBusinessSeats = new javax.swing.JTextField();
        btnFirstSeats = new javax.swing.JLabel();
        txtEconomySeats = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(java.awt.Color.BLACK);
        jLabel1.setText("ADD NEW FLIGHT");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Flight"));

        btnBusinessSeats.setText("Business Class Seats:");
        btnBusinessSeats.setForeground(java.awt.Color.BLACK);

        btnConfirmAddFlight.setText("Confirm");
        btnConfirmAddFlight.setBackground(new java.awt.Color(75, 150, 255));
        btnConfirmAddFlight.setForeground(java.awt.Color.WHITE);
        btnConfirmAddFlight.setFocusPainted(false);
        btnConfirmAddFlight.addActionListener(this::btnConfirmAddFlightActionPerformed);

        btnEconomySeats.setText("Economy Seats:");
        btnEconomySeats.setForeground(java.awt.Color.BLACK);

        btnCancelButton.setText("Cancel");
        btnCancelButton.setBackground(new java.awt.Color(200, 50, 50));
        btnCancelButton.setForeground(java.awt.Color.WHITE);
        btnCancelButton.setFocusPainted(false);
        btnCancelButton.addActionListener(this::btnCancelButtonActionPerformed);

        txtFlightID.setText("FL101");
        txtFlightID.addActionListener(this::txtFlightIDActionPerformed);

        txtSource.setText("Lahore");

        lblFlightID.setText("Flight ID:");
        lblFlightID.setForeground(java.awt.Color.BLACK);

        txtDestination.setText("Islamabad");
        txtDestination.addActionListener(this::txtDestinationActionPerformed);

        btnSource.setText("Source:");
        btnSource.setForeground(java.awt.Color.BLACK);

        txtDate.setText("XXXX-XX-XX");

        btnDestination.setText("Destination:");
        btnDestination.setForeground(java.awt.Color.BLACK);

        txtTime.setText("XX:XX");

        btnDate.setText("Date:");
        btnDate.setForeground(java.awt.Color.BLACK);

        txtBaseFare.setText("500");

        btnTime.setText("Time:");
        btnTime.setForeground(java.awt.Color.BLACK);

        btnBaseFare.setText("Base Fare:");
        btnBaseFare.setForeground(java.awt.Color.BLACK);

        btnFirstSeats.setText("First Class Seats:");
        btnFirstSeats.setForeground(java.awt.Color.BLACK);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnConfirmAddFlight)
                                                .addGap(79, 79, 79)
                                                .addComponent(btnCancelButton))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblFlightID)
                                                        .addComponent(btnSource)
                                                        .addComponent(btnDestination)
                                                        .addComponent(btnDate)
                                                        .addComponent(btnTime)
                                                        .addComponent(btnBaseFare)
                                                        .addComponent(btnFirstSeats)
                                                        .addComponent(btnBusinessSeats)
                                                        .addComponent(btnEconomySeats))
                                                .addGap(40, 40, 40)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtFlightID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtBaseFare, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtFirstSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtBusinessSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtEconomySeats, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFlightID)
                                        .addComponent(txtFlightID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSource)
                                        .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnDestination)
                                        .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnDate)
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnTime)
                                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnBaseFare)
                                        .addComponent(txtBaseFare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnFirstSeats)
                                        .addComponent(txtFirstSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnBusinessSeats)
                                        .addComponent(txtBusinessSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnEconomySeats)
                                        .addComponent(txtEconomySeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConfirmAddFlight)
                                        .addComponent(btnCancelButton))
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jLabel1)
                                .addContainerGap(210, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDestinationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDestinationActionPerformed

    private void btnConfirmAddFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmAddFlightActionPerformed
        try {
            String flightID = txtFlightID.getText().trim();
            String source = txtSource.getText().trim();
            String destination = txtDestination.getText().trim();
            String date = txtDate.getText().trim();
            String time = txtTime.getText().trim();
            double baseFare = Double.parseDouble(txtBaseFare.getText().trim());
            int firstSeats = Integer.parseInt(txtFirstSeats.getText().trim());
            int businessSeats = Integer.parseInt(txtBusinessSeats.getText().trim());
            int economySeats = Integer.parseInt(txtEconomySeats.getText().trim());

            AirlineSystem.Flight newFlight = new AirlineSystem.Flight(
                    flightID, source, destination, date, time,
                    baseFare, firstSeats, businessSeats, economySeats
            );

            AirlineSystem.FlightHandler.addFlight(newFlight);

            JOptionPane.showMessageDialog(this, "Flight added successfully!");
            this.dispose();
            new ManageFlightsPage().setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for seats and fare.");
        }
    }//GEN-LAST:event_btnConfirmAddFlightActionPerformed

    private void btnCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelButtonActionPerformed
        this.dispose();
        new ManageFlightsPage().setVisible(true);
    }//GEN-LAST:event_btnCancelButtonActionPerformed

    private void txtFlightIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFlightIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFlightIDActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new AddFlightsPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBaseFare;
    private javax.swing.JLabel btnBusinessSeats;
    private javax.swing.JButton btnCancelButton;
    private javax.swing.JButton btnConfirmAddFlight;
    private javax.swing.JLabel btnDate;
    private javax.swing.JLabel btnDestination;
    private javax.swing.JLabel btnEconomySeats;
    private javax.swing.JLabel btnFirstSeats;
    private javax.swing.JLabel btnSource;
    private javax.swing.JLabel btnTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFlightID;
    private javax.swing.JTextField txtBaseFare;
    private javax.swing.JTextField txtBusinessSeats;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDestination;
    private javax.swing.JTextField txtEconomySeats;
    private javax.swing.JTextField txtFirstSeats;
    private javax.swing.JTextField txtFlightID;
    private javax.swing.JTextField txtSource;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
