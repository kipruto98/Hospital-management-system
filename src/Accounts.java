import java.awt.Color;
import static java.awt.PageAttributes.MediaType.A;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import rojerusan.RSNotifyShadowFade;

public class Accounts extends javax.swing.JFrame {

    public Accounts(String no, String name,String button) {
        initComponents();
        txtname.setText(name);
        txtno.setText(no);
        txtbutton.setText(button);
        Connect();
        Doctor();
        Doctor1();
        Doctor2();
        deletebutton(button);
             bal1.setEnabled(false);
        bal2.setEnabled(false);
        bal3.setEnabled(false);
        access();
        //search();
        date();
        Strr();
    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    Accounts() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
           public void deletebutton(String button){
              if (button == "Admin") {
                 txtdel.setEnabled(true);
        } else {
                    txtdel.setEnabled(false);
        }
    }
           public void Doctor(){
try { 
     String patientname;
            patientname = txtname.getText();
            String sql = "select * from lab where patientname = '" + patientname + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                String pnn1 = rs.getString("cons");
              consultation.setText(String.valueOf(pnn1));
            }
             else{
                 }
         
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }                     
}
            public  void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("  HH:mm:ss yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    txtdate.setText(dtf.format(now).toString());
}
                        public boolean validateFields(){
    boolean b = false;
       if (txtname.getText().isEmpty()){
           b=false;
           new rojerusan.RSNotifyShadowFade("!WARNING!", "Name is empty", Color.white, Color.ORANGE, Color.BLACK, 5, RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
       }
       else{
    b=true;
    } 
       return b;
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        consultation = new javax.swing.JTextField();
        paid1 = new javax.swing.JTextField();
        bal1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lab = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        paid2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bal2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pharmacy = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        paid3 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        bal3 = new javax.swing.JTextField();
        txtbutton = new javax.swing.JLabel();
        txtsave = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        txtdel = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        err2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        err1 = new javax.swing.JLabel();
        err = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Accounts", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient name");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Patient no");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Paid ");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Consultation");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Balance");

        txtno.setBackground(new java.awt.Color(255, 255, 255));
        txtno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtno.setForeground(new java.awt.Color(0, 0, 0));

        txtname.setBackground(new java.awt.Color(255, 255, 255));
        txtname.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtname.setForeground(new java.awt.Color(0, 0, 0));
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnameKeyPressed(evt);
            }
        });

        consultation.setBackground(new java.awt.Color(255, 255, 255));
        consultation.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        consultation.setForeground(new java.awt.Color(0, 0, 0));
        consultation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                consultationKeyTyped(evt);
            }
        });

        paid1.setBackground(new java.awt.Color(255, 255, 255));
        paid1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        paid1.setForeground(new java.awt.Color(0, 0, 0));
        paid1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paid1KeyTyped(evt);
            }
        });

        bal1.setBackground(new java.awt.Color(255, 255, 255));
        bal1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bal1.setForeground(new java.awt.Color(0, 0, 0));
        bal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bal1MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lab tests     ");

        lab.setBackground(new java.awt.Color(255, 255, 255));
        lab.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lab.setForeground(new java.awt.Color(0, 0, 0));
        lab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                labKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Paid ");

        paid2.setBackground(new java.awt.Color(255, 255, 255));
        paid2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        paid2.setForeground(new java.awt.Color(0, 0, 0));
        paid2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paid2KeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Balance");

        bal2.setBackground(new java.awt.Color(255, 255, 255));
        bal2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bal2.setForeground(new java.awt.Color(0, 0, 0));
        bal2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bal2MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Pharmacy    ");

        pharmacy.setBackground(new java.awt.Color(255, 255, 255));
        pharmacy.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        pharmacy.setForeground(new java.awt.Color(0, 0, 0));
        pharmacy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pharmacyKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Paid ");

        paid3.setBackground(new java.awt.Color(255, 255, 255));
        paid3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        paid3.setForeground(new java.awt.Color(0, 0, 0));
        paid3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paid3KeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Balance");

        bal3.setBackground(new java.awt.Color(255, 255, 255));
        bal3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bal3.setForeground(new java.awt.Color(0, 0, 0));
        bal3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bal3MouseClicked(evt);
            }
        });

        txtbutton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbutton.setForeground(new java.awt.Color(0, 204, 0));

        txtsave.setBackground(new java.awt.Color(0, 204, 0));
        txtsave.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtsave.setForeground(new java.awt.Color(255, 255, 255));
        txtsave.setText("Save");
        txtsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsaveActionPerformed(evt);
            }
        });

        txtupdate.setBackground(new java.awt.Color(0, 0, 153));
        txtupdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtupdate.setForeground(new java.awt.Color(255, 255, 255));
        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        txtdel.setBackground(new java.awt.Color(204, 0, 0));
        txtdel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));
        txtdel.setText("Delete");
        txtdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdelActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Exit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Date");

        txtdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));
        txtdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdateMouseClicked(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));

        jLabel15.setForeground(new java.awt.Color(204, 0, 0));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Termal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        err2.setForeground(new java.awt.Color(204, 0, 0));

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));

        jLabel18.setForeground(new java.awt.Color(204, 0, 0));

        err1.setForeground(new java.awt.Color(204, 0, 0));

        err.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(consultation, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(paid1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(err, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(bal1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lab, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(paid2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bal2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtsave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtupdate)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(err2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pharmacy, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtdel)
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton4)
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(paid3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel13)
                                                .addGap(11, 11, 11))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bal3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(292, 292, 292)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(215, 215, 215)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(292, 292, 292)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(215, 215, 215)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consultation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(err, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pharmacy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paid3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtdate, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                            .addComponent(txtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsave)
                            .addComponent(txtupdate)
                            .addComponent(txtdel)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton2)))
                    .addComponent(err2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(204, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(102, 102, 102)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(204, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(102, 102, 102)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
     mathss();
     date();
    }//GEN-LAST:event_jPanel1MouseMoved
public void mathss(){
maths();
maths1();
maths2();
}
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       paid1.setText("");
       paid2.setText("");
       paid3.setText("");
       bal1.setText("");
       bal2.setText("");
       bal3.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bal1MouseClicked
        maths();
    }//GEN-LAST:event_bal1MouseClicked

    private void bal2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bal2MouseClicked
        maths1();
    }//GEN-LAST:event_bal2MouseClicked

    private void bal3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bal3MouseClicked
      maths2();
    }//GEN-LAST:event_bal3MouseClicked

    private void paid1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid1KeyTyped
                              try {
            int i=Integer.parseInt(paid1.getText());
            jLabel6.setText("");
        } catch (NumberFormatException evtI) {
          jLabel6.setText("Format is invalid");
            paid1.setText("");
        }
    }//GEN-LAST:event_paid1KeyTyped

    private void paid2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid2KeyTyped
                                     try {
            int i=Integer.parseInt(paid2.getText());
            jLabel7.setText("");
        } catch (NumberFormatException evtI) {
            jLabel7.setText("Format is invalid");
            paid2.setText("");
        }
    }//GEN-LAST:event_paid2KeyTyped

    private void paid3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid3KeyTyped
                                             try {
            int i=Integer.parseInt(paid3.getText());
            jLabel15.setText("");
        } catch (NumberFormatException evtI) {
            jLabel15.setText("Format is invalid");
            paid3.setText("");
        }
    }//GEN-LAST:event_paid3KeyTyped

    private void txtsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsaveActionPerformed
              if (validateFields()== true){
            save();
        }
        else{
        } 
    }//GEN-LAST:event_txtsaveActionPerformed

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
        if (validateFields()== true){
            update();
        }
        else{

        } 
    }//GEN-LAST:event_txtupdateActionPerformed

    private void txtdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdelActionPerformed
  if (validateFields()== true){
            delete();
        }
        else{

        } 
    }//GEN-LAST:event_txtdelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
            String sql = "select * from accounts where name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                   String setn = rs.getString("name");
                txtname.setText(setn);
                   String setnam = rs.getString("no");
               txtno.setText(setnam);
                                String setname = rs.getString("cons");
                consultation.setText(setname);
                        String setnm = rs.getString("lab");
                lab.setText(setnm);
                String age = rs.getString("phar");
                pharmacy.setText(age);
                String gender = rs.getString("paid1");
                paid1.setText(gender);
                 String gender1 = rs.getString("paid2");
                paid2.setText(gender1);
                 String gender2 = rs.getString("paid3");
                paid3.setText(gender2);
                 String gender3= rs.getString("bal1");
                bal1.setText(gender3);
                 String gender4 = rs.getString("bal2");
                bal2.setText(gender4);
                 String gender5 = rs.getString("bal3");
                bal3.setText(gender5);
                 String gender6 = rs.getString("date");
                txtdate.setText(gender6);
                 String gender7 = rs.getString("utype");
               
            }
                 else{
                   new rojerusan.RSNotifyShadowFade("!ERROR!", "Data not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
               
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateMouseClicked
     date();
    }//GEN-LAST:event_txtdateMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String patientname;
        patientname = txtname.getText();
        try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\accounts.jrxml");
            String query = "SELECT * FROM accounts WHERE name = '" + patientname + "'";
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyPressed
       search();
    }//GEN-LAST:event_txtnameKeyPressed

    private void consultationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consultationKeyTyped
                                           try {
            int i=Integer.parseInt(consultation.getText());
            err.setText("");
        } catch (NumberFormatException evtI) {
    err.setText(evtI.toString());
            consultation.setText("");
        }
    }//GEN-LAST:event_consultationKeyTyped

    private void labKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labKeyTyped
                                           try {
            int i=Integer.parseInt(lab.getText());
            err1.setText("");
        } catch (NumberFormatException evtI) {
err1.setText(evtI.toString());
            lab.setText("");
        }  
    }//GEN-LAST:event_labKeyTyped

    private void pharmacyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pharmacyKeyTyped
                                                try {
            int i=Integer.parseInt(pharmacy.getText());
            err2.setText("");
        } catch (NumberFormatException evtI) {
          err2.setText(evtI.toString());
            pharmacy.setText("");
        }    
    }//GEN-LAST:event_pharmacyKeyTyped
public void Doctor1(){
try { 
     String patientname;
            patientname = txtname.getText();
            String sql = "SELECT * FROM pharmacy INNER JOIN lab ON pharmacy.patientname = lab.patientname WHERE lab.patientname = '" + patientname + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                int pnn = rs.getInt("cost");
                int pnn1 = rs.getInt("cons");
                int tot = pnn + pnn1;
              consultation.setText(String.valueOf(tot));
            }
             else{
                 }
         
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }                     
}
public void Doctor2(){
try { 
            String sql = "select * from results where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                int pnn = rs.getInt("testcost");
              int pnn1 = rs.getInt("cost1");
              int pnn2 = rs.getInt("cost2");
              int pnn3 = rs.getInt("cost3");
              int pnn4 = rs.getInt("cost4");
              int total = pnn + pnn1 + pnn2 + pnn3 + pnn4;
                 lab.setText(String.valueOf(total)); 
              
            }
             else{
                 }
         
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }                     
}
public void Doctor3(){
try { 
            String sql = "select * from sales where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                       
              int pnn1 = rs.getInt("total");
              int pnn2 = rs.getInt("total1");
              int pnn3 = rs.getInt("total2");
              int pnn4 = rs.getInt("total3");
              int total = pnn1 + pnn2 + pnn3 + pnn4;
                 pharmacy.setText(String.valueOf(total)); 
            }
             else{
                 }
         
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }                     
}
public void maths(){
           int tot = (Integer.parseInt(consultation.getText()));
           int cost = (Integer.parseInt(paid1.getText()));
           int bal = tot - cost;
            bal1.setText(String.valueOf(bal)); 
                    
}
public void maths1(){
            int tot1 = (Integer.parseInt(lab.getText()));
           int cost1 = (Integer.parseInt(paid2.getText()));
           int bal1 = tot1 - cost1;
            bal2.setText(String.valueOf(bal1));         
}
public void maths2(){
         int tot2 = (Integer.parseInt(pharmacy.getText()));
           int cost2 = (Integer.parseInt(paid3.getText()));
           int bal2 = tot2 - cost2;
            bal3.setText(String.valueOf(bal2));      
}
public void save(){
        String chno =txtname.getText();
                String pname = txtno.getText();
                        String date = consultation.getText();
                         String pressure = lab.getText();
      String date1 = pharmacy.getText();
      String date2 = paid1.getText();
      String date3 = paid2.getText();
      String date4 = paid3.getText();
      String date5 = bal1.getText();
      String date6 = bal2.getText();
      String date7 = bal3.getText();
      String date8 = txtdate.getText();
      String date9 = txtbutton.getText();
                try {
          String sql = "select * from accounts where name = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 new rojerusan.RSNotifyShadowFade("!INFORMATION!", "Data already exists...Update instead", Color.white, Color.BLUE, Color.BLACK, 7,
                         RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true);
            }
                else{
                            pst = con.prepareStatement("Insert into accounts(name,no,cons,lab,phar,paid1,paid2,paid3,bal1,bal2,bal3,date,utype)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, chno);
             pst.setString(2, pname);
              pst.setString(3,date);
             pst.setString(4, pressure);
             pst.setString(5, date1);
             pst.setString(6, date2);
             pst.setString(7, date3);
                          pst.setString(8, date4);
             pst.setString(9, date5);
             pst.setString(10, date6);
                          pst.setString(11, date7);
             pst.setString(12, date8);
             pst.setString(13, date9);
               pst.executeUpdate(); 
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data saved successfully", Color.white, Color.GREEN, Color.BLACK, 7
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                date();
                }
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    
   }
  public void update(){
                String pname = txtno.getText();
                        String date = consultation.getText();
                         String pressure = lab.getText();
      String date1 = pharmacy.getText();
      String date2 = paid1.getText();
      String date3 = paid2.getText();
      String date4 = paid3.getText();
      String date5 = bal1.getText();
      String date6 = bal2.getText();
      String date7 = bal3.getText();
      String date8 = txtdate.getText();
      String date9 = txtbutton.getText();
         String chno =txtname.getText();
               try{
          String sql = "select * from accounts where name = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                try {
            pst = con.prepareStatement("update accounts set no = ?, cons = ?, lab = ?, phar = ?, paid1 = ?, paid2 = ?, paid3 = ?, bal1 = ?, bal2 = ?, bal3 = ?, date = ?, utype = ? where name = ?");
             pst.setString(1, pname);
              pst.setString(2,date);
             pst.setString(3, pressure);
             pst.setString(4, date1);
             pst.setString(5, date2);
             pst.setString(6, date3);
                          pst.setString(7, date4);
             pst.setString(8, date5);
             pst.setString(9, date6);
                          pst.setString(10, date7);
             pst.setString(11, date8);
             pst.setString(12, date9);
               pst.setString(13, chno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data Updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               date(); 
        }
          catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
      new rojerusan.RSNotifyShadowFade("!INFORMATION!", "Data not available", Color.white, Color.BLUE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true); 
                }
   }
                   catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }
 public void delete(){
      String pno = txtname.getText();
        try {
            pst = con.prepareStatement("delete from accounts where name = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!INFORMATION!", "Data Deleted successfully", Color.white, Color.BLUE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true); 
               date();
               paid1.setText("");
               paid2.setText("");
               paid3.setText("");
               bal1.setText("");
               bal2.setText("");
               bal3.setText("");    
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }  
  }
 public void access(){
 String usertype = txtbutton.getText();
switch(usertype){
    case "Admin":
        break;
            case "Pharmacist":
        paid1.setEnabled(false);
        paid2.setEnabled(false);
                      consultation.setEnabled(false);
        lab.setEnabled(false);
        pharmacy.setEnabled(true);
        break;
            case "Doctor":
        paid3.setEnabled(false);
        paid2.setEnabled(false);
                      consultation.setEnabled(true);
        lab.setEnabled(false);
        pharmacy.setEnabled(false);
        break;
                    case "Lab_attedant":  	
        paid1.setEnabled(false);
        paid3.setEnabled(false);
                      consultation.setEnabled(false);
        lab.setEnabled(false);
        pharmacy.setEnabled(false);
        break;
                          case "Lab":  	
        paid1.setEnabled(false);
        paid3.setEnabled(false);
                      consultation.setEnabled(false);
        lab.setEnabled(false);
        pharmacy.setEnabled(false);
        break;
        default:
  
            break;   
}
 }
 public void search(){
   try {
            String sql = "select * from accounts where name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                   String setn = rs.getString("name");
                txtname.setText(setn);
                   String setnam = rs.getString("no");
               txtno.setText(setnam);
                                String setname = rs.getString("cons");
                consultation.setText(setname);
                        String setnm = rs.getString("lab");
                lab.setText(setnm);
                String age = rs.getString("phar");
                pharmacy.setText(age);
                String gender = rs.getString("paid1");
                paid1.setText(gender);
                 String gender1 = rs.getString("paid2");
                paid2.setText(gender1);
                 String gender2 = rs.getString("paid3");
                paid3.setText(gender2);
                 String gender3= rs.getString("bal1");
                bal1.setText(gender3);
                 String gender4 = rs.getString("bal2");
                bal2.setText(gender4);
                 String gender5 = rs.getString("bal3");
                bal3.setText(gender5);
                 String gender6 = rs.getString("date");
                txtdate.setText(gender6);
                 String gender7 = rs.getString("utype");
               //JOptionPane.showMessageDialog(this, gender7);
            }
                 else{
//          new rojerusan.RSNotifyShadowFade("!ERROR!", "Data not found", Color.white, Color.BLACK, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);       
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
 }

public void Strr(){
 TimerTask timerTask = new TimerTask() {
       @Override
       public void run() {
          // date();
       }
   };
 Timer timer = new Timer("");
 timer.scheduleAtFixedRate(timerTask, 0, 1000);
} 

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
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Accounts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bal1;
    private javax.swing.JTextField bal2;
    private javax.swing.JTextField bal3;
    private javax.swing.JTextField consultation;
    private javax.swing.JLabel err;
    private javax.swing.JLabel err1;
    private javax.swing.JLabel err2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lab;
    private javax.swing.JTextField paid1;
    private javax.swing.JTextField paid2;
    private javax.swing.JTextField paid3;
    private javax.swing.JTextField pharmacy;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JLabel txtdate;
    private javax.swing.JButton txtdel;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtno;
    private javax.swing.JButton txtsave;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
