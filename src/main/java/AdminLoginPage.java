import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ashir
 */
public class AdminLoginPage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminLoginPage.class.getName());

    /**
     * Creates new form AdminLoginPage
     */
    public AdminLoginPage() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 245, 245));
        lblAdminLoginPage.setForeground(java.awt.Color.BLACK);
        lblUsername.setForeground(java.awt.Color.BLACK);
        lblPassword.setForeground(java.awt.Color.BLACK);

        btnAdminLoginConfirm.setBackground(new java.awt.Color(75, 150, 255));
        btnAdminLoginConfirm.setForeground(java.awt.Color.WHITE);
        btnAdminLoginConfirm.setFocusPainted(false);

        btnAdminBack.setBackground(new java.awt.Color(200, 50, 50));
        btnAdminBack.setForeground(java.awt.Color.WHITE);
        btnAdminBack.setFocusPainted(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAdminLoginPage = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtAdminUsername = new javax.swing.JTextField();
        txtAdminPassword = new javax.swing.JPasswordField();
        btnAdminLoginConfirm = new javax.swing.JButton();
        btnAdminBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 245));

        lblAdminLoginPage.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblAdminLoginPage.setText("Admin Login Page");

        lblUsername.setText("Username:");

        lblPassword.setText("Password:");

        btnAdminLoginConfirm.setText("Login");
        btnAdminLoginConfirm.addActionListener(this::btnAdminLoginConfirmActionPerformed);

        btnAdminBack.setText("Back");
        btnAdminBack.addActionListener(this::btnAdminBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblUsername)
                                        .addComponent(lblPassword)
                                        .addComponent(btnAdminBack))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAdminUsername)
                                        .addComponent(txtAdminPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(btnAdminLoginConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(150, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(lblAdminLoginPage)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblAdminLoginPage)
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblUsername)
                                        .addComponent(txtAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPassword)
                                        .addComponent(txtAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdminLoginConfirm)
                                        .addComponent(btnAdminBack))
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdminBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminBackActionPerformed
        this.dispose();
        new WelcomePage().setVisible(true);
    }//GEN-LAST:event_btnAdminBackActionPerformed

    private void btnAdminLoginConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminLoginConfirmActionPerformed
        String username = txtAdminUsername.getText().trim();
        String password = new String(txtAdminPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean loginSuccessful = false;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("admins.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    if (username.equals(fileUsername) && password.equals(filePassword)) {
                        loginSuccessful = true;
                        break;
                    }
                }
            }
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading admin data file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new AdminDashboard().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAdminLoginConfirmActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new AdminLoginPage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdminBack;
    private javax.swing.JButton btnAdminLoginConfirm;
    private javax.swing.JLabel lblAdminLoginPage;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtAdminPassword;
    private javax.swing.JTextField txtAdminUsername;
    // End of variables declaration//GEN-END:variables
}
