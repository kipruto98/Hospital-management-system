import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSNotifyShadowFade;


public class Test extends javax.swing.JFrame {


    public Test(String no) {
        initComponents();
        Connect();
        AutoID();
        test_table();  
              deletebutton(no);
        txtbutton.setText(no);
        txtbutton.setVisible(false);
    }
    Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Test() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Connect(){                        
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
          Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
             public void deletebutton(String no){
              if (no == "Admin") {
                 txtdelete.setEnabled(true);
        } else {
                    txtdelete.setEnabled(false);
        }
    }
                 public boolean validateFields(){
    boolean b = false;
       if (txtname.getText().isEmpty()){
           b=false;
txterr.setText("name is empty");
txtname.requestFocus();
       }
       
       else if (txtcharges.getText().isEmpty()){
           b=false;
txterr1.setText("Charges is empty");
txtcharges.requestFocus();
       }
       else{
    b=true;
    txterr1.setText("");
        txterr.setText("");
    } 
       return b;
}
    public void save(){
          String testno = lblItemid.getText();
       String testname = txtname.getText();
       String testdes = txtcharges.getText(); 
       String des = txtdes.getText();
        try {
                pst = con.prepareStatement("select*from test WHERE testno = '" + testno + "' and testname = '" + testname + "'");
                pst.executeQuery();
                if (rs.next() == true) {
                    new rojerusan.RSNotifyShadowFade("!WARNING!", "Data already exists,update otherwise", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                            pst = con.prepareStatement("Insert into test(testno,testname,charges,des)values(?, ?,?,?)");
            pst.setString(1, testno);
             pst.setString(2, testname);    
              pst.setString(3, testdes);
              pst.setString(4, des);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Test added successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtname.requestFocus();
               test_table();
                }
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
        public void update(){
           String testname = txtname.getText(); 
           String testname1 = txtcharges.getText(); 
           String des1 = txtdes.getText();
        String testno = lblItemid.getText();  
                try {
                pst = con.prepareStatement("select*from test WHERE testno = '" + testno + "' and testname = '" + testname + "'");
                pst.executeQuery();
                if (rs.next() == true) {
try {
            pst = con.prepareStatement("update test set testname = ?,charges = ?, des = ? where testno = ?");
             pst.setString(1, testname); 
             pst.setString(2, testname1);
             pst.setString(3, des1);
                pst.setString(4, testno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Test updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtname.requestFocus();
               test_table();  
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
                           new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true); 
                }
                } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
            public void delete(){
           String pno = lblItemid.getText();
        try {
            pst = con.prepareStatement("delete from test where testno = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Test Deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtname.setText("");
               txtcharges.setText("");
              txtname.requestFocus();
               test_table();
               txtadd.setEnabled(true);
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
  public void AutoID(){   
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("select MAX(testno) from test");
            rs.next();
            rs.getString("MAX(testno)");
            if(rs.getString("MAX(testno)") == null)
            {
                lblItemid.setText("TS001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("MAX(testno)").substring(2,rs.getString("MAX(testno)").length()));
                id++;
                lblItemid.setText("TS"+String.format("%03d", id));  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }
  
  public void test_table(){   
        try {
            pst = con.prepareStatement("select*from test");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)jTable2.getModel();
            df.setRowCount(0);
            while(rs.next()){
            Vector v2 = new Vector();
                for (int i = 1; i <= c; i++)                 
                {
            v2.add(rs.getString("testno"));                
            v2.add(rs.getString("testname"));
            v2.add(rs.getString("charges"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtdoc1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtcharges = new javax.swing.JTextField();
        txtadd = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        txtdelete = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lblItemid = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        searchData = new javax.swing.JTextField();
        txterror = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txterr = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        txtbutton = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel14.setText("jLabel14");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Lab test and charges", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Test no");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Test name");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Charges");

        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnameKeyTyped(evt);
            }
        });

        txtcharges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtchargesKeyReleased(evt);
            }
        });

        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        txtdelete.setText("Delete");
        txtdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdeleteActionPerformed(evt);
            }
        });

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Test no", "Test name", "Charges"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        lblItemid.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblItemid.setForeground(new java.awt.Color(255, 255, 255));

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDataKeyPressed(evt);
            }
        });

        txterror.setForeground(new java.awt.Color(0, 153, 0));
        txterror.setText("Search by test name");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Test database");

        txterr.setForeground(new java.awt.Color(204, 0, 0));

        txterr1.setForeground(new java.awt.Color(204, 0, 0));

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Description");

        txtdes.setColumns(20);
        txtdes.setRows(5);
        jScrollPane1.setViewportView(txtdes);

        txtbutton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbutton.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblItemid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(txterror, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(txtdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton5)
                        .addGap(41, 41, 41)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(100, 100, 100))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcharges, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblItemid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterror)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcharges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadd)
                    .addComponent(txtupdate)
                    .addComponent(txtdelete)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4)
                    .addComponent(txtbutton))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        txtname.setText("");
        txtcharges.setText("");
           txtdes.setText("");
        txtname.requestFocus();
        AutoID();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed
        try {
            String sql = "select * from test where testname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("testno");
                lblItemid.setText(pnn);
                String setname = rs.getString("testname");
                txtname.setText(setname);
                String age = rs.getString("charges");
                txtcharges.setText(age);
                  String age1 = rs.getString("des");
                txtdes.setText(age1);
            }

            else{
                searchData.requestFocus();
            }
        } catch (SQLException ex) {
                 Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_searchDataKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String sql = "select * from test where testname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("testno");
                lblItemid.setText(pnn);
                String setname = rs.getString("testname");
                txtname.setText(setname);
                String age = rs.getString("charges");
                txtcharges.setText(age);
                  String age1 = rs.getString("des");
                txtdes.setText(age1);
            }

            else{
                searchData.setText("");
                searchData.requestFocus();
                txterror.setText("Test not found");
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Test not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                txterror.setText("Search by Test name");
            }
        } catch (SQLException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     this.setVisible(false);
                           String no = txtbutton.getText();
                           new Test(no).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        DefaultTableModel d1 = (DefaultTableModel)jTable2.getModel();      
        int SelectIndex = jTable2.getSelectedRow();                  
        lblItemid.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtname.setText(d1.getValueAt(SelectIndex, 1).toString()); 
        txtcharges.setText(d1.getValueAt(SelectIndex, 2).toString());         
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdeleteActionPerformed

        if (validateFields()== true){
            delete();
        }
        else{
        }
    }//GEN-LAST:event_txtdeleteActionPerformed

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
        if (validateFields()== true){
           update();
            txtadd.setEnabled(true);
            txtupdate.setEnabled(false);
        }
        else{
        }
    }//GEN-LAST:event_txtupdateActionPerformed

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed

        if (validateFields()== true){
            save();
        }
        else{
        }
    }//GEN-LAST:event_txtaddActionPerformed

    private void txtchargesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtchargesKeyReleased

              try {
            int i=Integer.parseInt(txtcharges.getText());
            txterr1.setText("");
        } catch (NumberFormatException evtI) {
            txterr1.setText("Format is invalid");
            txtcharges.setText("");
        }
    }//GEN-LAST:event_txtchargesKeyReleased

    private void txtnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyTyped
        txterr.setText("");
    }//GEN-LAST:event_txtnameKeyTyped

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased

    }//GEN-LAST:event_txtnameKeyReleased

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
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblItemid;
    private javax.swing.JTextField searchData;
    private javax.swing.JButton txtadd;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JTextField txtcharges;
    private javax.swing.JButton txtdelete;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JComboBox txtdoc1;
    private javax.swing.JLabel txterr;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterror;
    private javax.swing.JTextField txtname;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
