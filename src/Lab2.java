
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rojerusan.RSNotifyShadowFade;


public class Lab2 extends javax.swing.JFrame {
    public Lab2(String no,String name) {
        initComponents();
        Connect();
              txtname.setText(name);
     txtno.setText(no);
     txtcost1.setEditable(false);
     txtcost2.setEditable(false);
      loadDoctor();
    }
        Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    public Lab2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void Connect(){                     
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
                      public class Doctor{
    String id;
    String name;
    public Doctor(String id,String name){
    this.id = id;
    this.name=name;
    }
    public String toString()
            {
            return name;
            }
    }
 public void loadDoctor(){
        try {
            pst = con.prepareStatement("select * from test  ORDER BY i ASC");
           rs = pst.executeQuery();
            txtdoc2.removeAll();
            while(rs.next())
            {
         txtdoc2.addItem(new Doctor(rs.getString(1),rs.getString(2)));
                  txtdoc3.addItem(new Doctor(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab2.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtadd = new javax.swing.JButton();
        txtcancel = new javax.swing.JButton();
        txtexit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txttype3 = new javax.swing.JTextField();
        txttype4 = new javax.swing.JTextField();
        txtcost3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcost4 = new javax.swing.JTextField();
        txtname = new javax.swing.JLabel();
        txtno = new javax.swing.JLabel();
        txtcost1 = new javax.swing.JTextField();
        txtcost2 = new javax.swing.JTextField();
        txtdoc2 = new javax.swing.JComboBox();
        txtdoc3 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        err1 = new javax.swing.JLabel();
        err3 = new javax.swing.JLabel();
        err4 = new javax.swing.JLabel();
        err2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        results1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        results3 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        results2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        results4 = new javax.swing.JTextArea();
        txtcancel1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lab tests  (Fields are not compulsary", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        txtadd.setBackground(new java.awt.Color(0, 102, 0));
        txtadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        txtcancel.setBackground(new java.awt.Color(153, 0, 0));
        txtcancel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtcancel.setForeground(new java.awt.Color(255, 255, 255));
        txtcancel.setText("Clear");
        txtcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcancelActionPerformed(evt);
            }
        });

        txtexit.setBackground(new java.awt.Color(0, 0, 0));
        txtexit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtexit.setForeground(new java.awt.Color(255, 255, 255));
        txtexit.setText("Exit");
        txtexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtexitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient no");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Patient name");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Test type1");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Test type2");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Test cost1");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Test cost2");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Test type3");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Test type4");

        txttype4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttype4ActionPerformed(evt);
            }
        });

        txtcost3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcost3KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Test cost3");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Test cost4");

        txtcost4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcost4KeyReleased(evt);
            }
        });

        txtname.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtname.setForeground(new java.awt.Color(255, 255, 255));

        txtno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtno.setForeground(new java.awt.Color(255, 255, 255));

        txtcost1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcost1KeyReleased(evt);
            }
        });

        txtcost2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcost2MouseClicked(evt);
            }
        });
        txtcost2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcost2KeyReleased(evt);
            }
        });

        txtdoc2.setMaximumRowCount(80);

        txtdoc3.setMaximumRowCount(80);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Results1");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Results2");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Results3");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Results4");

        err1.setForeground(new java.awt.Color(153, 0, 0));

        err3.setForeground(new java.awt.Color(153, 0, 0));

        err4.setForeground(new java.awt.Color(153, 0, 0));

        err2.setForeground(new java.awt.Color(153, 0, 0));

        results1.setColumns(20);
        results1.setRows(5);
        jScrollPane1.setViewportView(results1);

        results3.setColumns(20);
        results3.setRows(5);
        jScrollPane2.setViewportView(results3);

        results2.setColumns(20);
        results2.setRows(5);
        jScrollPane3.setViewportView(results2);

        results4.setColumns(20);
        results4.setRows(5);
        jScrollPane4.setViewportView(results4);

        txtcancel1.setBackground(new java.awt.Color(0, 204, 204));
        txtcancel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtcancel1.setForeground(new java.awt.Color(255, 255, 255));
        txtcancel1.setText("Update");
        txtcancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdoc3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txttype3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(err3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtcost3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txttype4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(308, 308, 308)
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcost4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtexit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(err4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 2, Short.MAX_VALUE)))))
                        .addGap(117, 117, 117))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtdoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtcost1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(err2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtcost2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(txtcancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtno))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtcost1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtdoc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(txtcost2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(err2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txttype3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtcost3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(err3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtcost4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txttype4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(err4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtadd)
                            .addComponent(txtcancel)
                            .addComponent(txtexit)
                            .addComponent(txtcancel1))
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txttype4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttype4ActionPerformed
       
    }//GEN-LAST:event_txttype4ActionPerformed

    private void txtexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtexitActionPerformed
      this.setVisible(false);
    }//GEN-LAST:event_txtexitActionPerformed

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
        try {
                     String sql = "select * from results_1 where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtno.getText());
            pst.setString(2, txtname.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()==false) {
        String type = txtdoc2.getSelectedItem().toString();
       String no1 = txtcost1.getText();
       String no2 = results1.getText();
         String type1 = txtdoc3.getSelectedItem().toString();
       String no3 = txtcost2.getText();
       String no4 = results2.getText();
       String no5 = txttype3.getText();
       String no6 = txtcost3.getText();
       String no7 = results3.getText();
       String no8 = txttype4.getText();
       String no9 = txtcost4.getText();
       String no10 = results4.getText();
          String no = txtno.getText();
        try {
            pst = con.prepareStatement("update results set type1=?, cost1 = ?,results1=?, type2=?, cost2 = ?,results2=?, type3=?, cost3 = ?,results3=?, type4=?, cost4 = ?,results4=? where channelno = ?");
           pst.setString(1,type);
           pst.setString(2,no1);
           pst.setString(3,no2);
           pst.setString(4,type1);
             pst.setString(5,no3);
           pst.setString(6,no4);
           pst.setString(7,no5);
           pst.setString(8,no6);
             pst.setString(9,no7);
           pst.setString(10,no8);
           pst.setString(11,no9);
           pst.setString(12,no10);
           pst.setString(13,no);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data added successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
            else{
               new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data exists{Update}", Color.white, Color.orange, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
 
         } catch (Exception e) {
        }
    }//GEN-LAST:event_txtaddActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
                                             String dcode = txtdoc2.getSelectedItem().toString();
            try {
                pst = con.prepareStatement("select * from test where testname = ?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();
                if(rs.next() == false)
                {
                    //JOptionPane.showMessageDialog(this, "test not found");
                    
                }
                else{
                String dname = rs.getString("charges");
                txtcost1.setText(dname.trim());
                }
            } catch (SQLException ex) {
            } 
            tett();
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
     
    }//GEN-LAST:event_jPanel1MouseDragged
public void tett(){
                                             String dcode = txtdoc3.getSelectedItem().toString();
                                             
            try {
                pst = con.prepareStatement("select * from test where testname = ?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();
                if(rs.next() == false)
                {
                    //JOptionPane.showMessageDialog(this, "test not found");
                }
                else{
                String dname = rs.getString("charges");
                txtcost2.setText(dname.trim());
                }
            } catch (SQLException ex) {
            } 
}
    private void txtcost2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcost2MouseClicked

    }//GEN-LAST:event_txtcost2MouseClicked

    private void txtcost1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcost1KeyReleased
           try {
            int i=Integer.parseInt(txtcost1.getText());
            err1.setText("");
        } catch (NumberFormatException evtI) {
            err1.setText("Format is invalid");
            txtcost1.setText("");
        }
    }//GEN-LAST:event_txtcost1KeyReleased

    private void txtcost2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcost2KeyReleased
               try {
            int i=Integer.parseInt(txtcost2.getText());
            err2.setText("");
        } catch (NumberFormatException evtI) {
            err2.setText("Format is invalid");
            txtcost2.setText("");
        }
    }//GEN-LAST:event_txtcost2KeyReleased

    private void txtcost3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcost3KeyReleased
                 try {
            int i=Integer.parseInt(txtcost3.getText());
            err3.setText("");
        } catch (NumberFormatException evtI) {
            err3.setText("Format is invalid");
            txtcost3.setText("");
        }
    }//GEN-LAST:event_txtcost3KeyReleased

    private void txtcost4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcost4KeyReleased
              try {
            int i=Integer.parseInt(txtcost4.getText());
            err4.setText("");
        } catch (NumberFormatException evtI) {
            err4.setText("Format is invalid");
            txtcost4.setText("");
        }
    }//GEN-LAST:event_txtcost4KeyReleased

    private void txtcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcancelActionPerformed
        results1.setText("");
         results2.setText("");
          results3.setText("");
           results4.setText("");
            txttype3.setText("");
             txttype4.setText("");
             txtcost3.setText("");
               txtcost4.setText("");
           
    }//GEN-LAST:event_txtcancelActionPerformed

    private void txtcancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcancel1ActionPerformed
    try {
                     String sql = "select * from results_1 where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtno.getText());
            pst.setString(2, txtname.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()==true) {
              String type = txtdoc2.getSelectedItem().toString();
       String no1 = txtcost1.getText();
       String no2 = results1.getText();
         String type1 = txtdoc3.getSelectedItem().toString();
       String no3 = txtcost2.getText();
       String no4 = results2.getText();
       String no5 = txttype3.getText();
       String no6 = txtcost3.getText();
       String no7 = results3.getText();
       String no8 = txttype4.getText();
       String no9 = txtcost4.getText();
       String no10 = results4.getText();
          String no = txtno.getText();
        try {
            pst = con.prepareStatement("update results_1 set type1=?, cost1 = ?,results1=?, type2=?, cost2 = ?,results2=?, type3=?, cost3 = ?,results3=?, type4=?, cost4 = ?,results4=? where  i = (select MAX(i)from results_1) AND  channelno = ?");
           pst.setString(1,type);
           pst.setString(2,no1);
           pst.setString(3,no2);
           pst.setString(4,type1);
             pst.setString(5,no3);
           pst.setString(6,no4);
           pst.setString(7,no5);
           pst.setString(8,no6);
             pst.setString(9,no7);
           pst.setString(10,no8);
           pst.setString(11,no9);
           pst.setString(12,no10);
           pst.setString(13,no);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data added successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }  
            }        
            else{
               new rojerusan.RSNotifyShadowFade("!WARNING!", "No data found,SAVE first", Color.white, Color.orange, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
 
         } catch (Exception e) {
        }    
    }//GEN-LAST:event_txtcancel1ActionPerformed


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
            java.util.logging.Logger.getLogger(Lab2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lab2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lab2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lab2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lab2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel err1;
    private javax.swing.JLabel err2;
    private javax.swing.JLabel err3;
    private javax.swing.JLabel err4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea results1;
    private javax.swing.JTextArea results2;
    private javax.swing.JTextArea results3;
    private javax.swing.JTextArea results4;
    private javax.swing.JButton txtadd;
    private javax.swing.JButton txtcancel;
    private javax.swing.JButton txtcancel1;
    private javax.swing.JTextField txtcost1;
    private javax.swing.JTextField txtcost2;
    private javax.swing.JTextField txtcost3;
    private javax.swing.JTextField txtcost4;
    private javax.swing.JComboBox txtdoc2;
    private javax.swing.JComboBox txtdoc3;
    private javax.swing.JButton txtexit;
    private javax.swing.JLabel txtname;
    private javax.swing.JLabel txtno;
    private javax.swing.JTextField txttype3;
    private javax.swing.JTextField txttype4;
    // End of variables declaration//GEN-END:variables
}
