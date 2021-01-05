
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Main extends javax.swing.JFrame {
public JFrame frame;
    public Main() {
        initComponents();
        Connect();
    }
    Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Main(String pname) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   public void Connect(){       
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void report(){
        try {
            
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report3.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientname = results.patientname INNER JOIN pharmacy ON patient.patientname = pharmacy.patientname INNER JOIN \n" +
"sales ON patient.patientname = sales.patientname";
            JRDesignQuery updateQuery = new  JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, new HashMap<>(),con);
            JasperViewer.viewReport(jprint);
        } catch (JRException ex) {
                      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

   int idd;
     String uctype;
    int newid;
    String uname;
    String usertype;
public Main(int id,String username,String usertype) {
        initComponents();
        this.uname = username;
        txtusername.setText(uname);
        this.usertype = usertype;
        txtutype.setText(usertype);
        this.newid = id;
        idd = newid;
        uctype = txtutype.getText();
      switch (usertype) {
            case "Lab_attedant": 
             txtchannel.setVisible(false);
             txtpatient.setVisible(false);
                txtinv.setVisible(false);
                   txtsales.setVisible(false);
                      txtuser.setVisible(false);
                         txtitem.setVisible(false);
                            txtdoctor.setVisible(false);
                               txtresults.setVisible(false);
                               
                break;
            case "Doctor":
                txtchannel.setVisible(false);
             txtpatient.setVisible(false);
                txtinv.setVisible(false);
                   txtlab.setVisible(false);
                         txtitem.setVisible(false);
                         txttest.setVisible(false);
                         
                break;
                   case "Lab":
                txtinv.setVisible(false);
                         txtitem.setVisible(false);
                          txtinv.setVisible(false);
                   txtsales.setVisible(false);
                      txtuser.setVisible(false);
                         txtitem.setVisible(false);
                            txtdoctor.setVisible(false);
                               txtresults.setVisible(false); 
                break;
            case "Receptionist":
                txtinv.setVisible(false);
                   txtlab.setVisible(false);
                   txtsales.setVisible(false);
                      txtuser.setVisible(false);
                         txtitem.setVisible(false);
                            txtdoctor.setVisible(false);
                               txtresults.setVisible(false); 
                               txttest.setVisible(false);
                break;
            case "Pharmacist":             
                   txtchannel.setVisible(false);
             txtpatient.setVisible(false);
                   txtlab.setVisible(false);
                   txtsales.setVisible(false);
                      txtuser.setVisible(false);
                            txtdoctor.setVisible(false);
                               txtresults.setVisible(false);
                                   txttest.setVisible(false);
                               
                break;
            default:
                break;
        }  
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        txtlab = new javax.swing.JButton();
        txtpatient = new javax.swing.JButton();
        txtchannel = new javax.swing.JButton();
        txtuser = new javax.swing.JButton();
        txtitem = new javax.swing.JButton();
        txtsales = new javax.swing.JButton();
        txtinv = new javax.swing.JButton();
        txtresults = new javax.swing.JButton();
        txtdoctor2 = new javax.swing.JButton();
        txttest = new javax.swing.JButton();
        txtlogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtusername = new javax.swing.JLabel();
        txtutype = new javax.swing.JLabel();
        txtdoctor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setBackground(new java.awt.Color(0, 0, 153));
        jInternalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setVisible(true);

        txtlab.setBackground(new java.awt.Color(255, 255, 255));
        txtlab.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtlab.setForeground(new java.awt.Color(0, 0, 0));
        txtlab.setText("Laboratory");
        txtlab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabActionPerformed(evt);
            }
        });

        txtpatient.setBackground(new java.awt.Color(255, 255, 255));
        txtpatient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpatient.setForeground(new java.awt.Color(0, 0, 0));
        txtpatient.setText("Create patient");
        txtpatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpatientActionPerformed(evt);
            }
        });

        txtchannel.setBackground(new java.awt.Color(255, 255, 255));
        txtchannel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtchannel.setForeground(new java.awt.Color(0, 0, 0));
        txtchannel.setText("Create channel");
        txtchannel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtchannelActionPerformed(evt);
            }
        });

        txtuser.setBackground(new java.awt.Color(255, 255, 255));
        txtuser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtuser.setForeground(new java.awt.Color(0, 0, 0));
        txtuser.setText("Create User");
        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });

        txtitem.setBackground(new java.awt.Color(255, 255, 255));
        txtitem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtitem.setForeground(new java.awt.Color(0, 0, 0));
        txtitem.setText("Create drug item");
        txtitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtitemActionPerformed(evt);
            }
        });

        txtsales.setBackground(new java.awt.Color(255, 255, 255));
        txtsales.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtsales.setForeground(new java.awt.Color(0, 0, 0));
        txtsales.setText("Sales report");
        txtsales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalesActionPerformed(evt);
            }
        });

        txtinv.setBackground(new java.awt.Color(255, 255, 255));
        txtinv.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtinv.setForeground(new java.awt.Color(0, 0, 0));
        txtinv.setText("Pharmacy");
        txtinv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtinvActionPerformed(evt);
            }
        });

        txtresults.setBackground(new java.awt.Color(255, 255, 255));
        txtresults.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtresults.setForeground(new java.awt.Color(0, 0, 0));
        txtresults.setText("Lab results");
        txtresults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtresultsActionPerformed(evt);
            }
        });

        txtdoctor2.setBackground(new java.awt.Color(255, 255, 255));
        txtdoctor2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdoctor2.setForeground(new java.awt.Color(0, 0, 0));
        txtdoctor2.setText("Daily sales");
        txtdoctor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdoctor2ActionPerformed(evt);
            }
        });

        txttest.setBackground(new java.awt.Color(255, 255, 255));
        txttest.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txttest.setForeground(new java.awt.Color(0, 0, 0));
        txttest.setText("Create lab tests");
        txttest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttestActionPerformed(evt);
            }
        });

        txtlogout.setBackground(new java.awt.Color(204, 0, 0));
        txtlogout.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtlogout.setForeground(new java.awt.Color(0, 0, 0));
        txtlogout.setText("Logout");
        txtlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlogoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("User name");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("User type");

        txtusername.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtusername.setForeground(new java.awt.Color(0, 153, 0));

        txtutype.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtutype.setForeground(new java.awt.Color(0, 153, 0));

        txtdoctor.setBackground(new java.awt.Color(255, 255, 255));
        txtdoctor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdoctor.setForeground(new java.awt.Color(0, 0, 0));
        txtdoctor.setText("Doctor");
        txtdoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlab, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtchannel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtuser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(29, 29, 29)))))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtinv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtsales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addComponent(txtresults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtitem, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtutype, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtdoctor2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtdoctor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txttest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtusername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtutype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtitem)
                        .addComponent(txtlogout))
                    .addComponent(txtuser))
                .addGap(34, 34, 34)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtchannel)
                    .addComponent(txtsales)
                    .addComponent(txtdoctor2))
                .addGap(34, 34, 34)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpatient)
                    .addComponent(txtinv)
                    .addComponent(txttest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlab)
                    .addComponent(txtresults)
                    .addComponent(txtdoctor))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlogoutActionPerformed
        frame =new JFrame();
                if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit", "HANNEX Medical Clinic",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                            this.setVisible(false);
                   Login dc = new Login();
            dc.setVisible(true);
                    }

    }//GEN-LAST:event_txtlogoutActionPerformed

    private void txttestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttestActionPerformed
                                                    String no = txtutype.getText();
new Test(no).setVisible(true);
    }//GEN-LAST:event_txttestActionPerformed

    private void txtdoctor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdoctor2ActionPerformed
Connect();
        try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report11.jrxml");
            String query = "SELECT * FROM accounts1 UNION all SELECT * from accounts ORDER BY i DESC";
            JRDesignQuery updateQuery = new  JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, new HashMap<>(),con);
            JasperViewer.viewReport(jprint);
        } catch (JRException ex) {
                      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_txtdoctor2ActionPerformed

    private void txtresultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtresultsActionPerformed
String no = txtutype.getText();
new Results(no).setVisible(true);
    }//GEN-LAST:event_txtresultsActionPerformed

    private void txtinvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtinvActionPerformed
  
                                       String no = txtutype.getText();
new Inventory(no).setVisible(true);
    }//GEN-LAST:event_txtinvActionPerformed

    private void txtsalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalesActionPerformed
//report();
JOptionPane.showMessageDialog(this, "Not available<null>");
    }//GEN-LAST:event_txtsalesActionPerformed

    private void txtitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtitemActionPerformed

                                       String no = txtutype.getText();
new Item(no).setVisible(true);
    }//GEN-LAST:event_txtitemActionPerformed

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
                               String no = txtutype.getText();
new User(no).setVisible(true);
    }//GEN-LAST:event_txtuserActionPerformed

    private void txtchannelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtchannelActionPerformed

                            String no = txtutype.getText();
new Channel(no).setVisible(true);

    }//GEN-LAST:event_txtchannelActionPerformed

    private void txtpatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpatientActionPerformed
                                  String no = txtutype.getText();
new Patient(no).setVisible(true);
    }//GEN-LAST:event_txtpatientActionPerformed

    private void txtlabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabActionPerformed
                                       String no = txtutype.getText();
new Lab(no).setVisible(true);
    }//GEN-LAST:event_txtlabActionPerformed

    private void txtdoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdoctorActionPerformed
                                               String no = txtutype.getText();
new Doctor(no).setVisible(true);
    }//GEN-LAST:event_txtdoctorActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton txtchannel;
    private javax.swing.JButton txtdoctor;
    private javax.swing.JButton txtdoctor2;
    private javax.swing.JButton txtinv;
    private javax.swing.JButton txtitem;
    private javax.swing.JButton txtlab;
    private javax.swing.JButton txtlogout;
    private javax.swing.JButton txtpatient;
    private javax.swing.JButton txtresults;
    private javax.swing.JButton txtsales;
    private javax.swing.JButton txttest;
    private javax.swing.JButton txtuser;
    private javax.swing.JLabel txtusername;
    private javax.swing.JLabel txtutype;
    // End of variables declaration//GEN-END:variables
}
