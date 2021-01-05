
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User extends javax.swing.JFrame {

    public User(String no) {
        initComponents();
        Connect();
        user_table();
        deletebutton(no);
        txtdel.setText(no);
      txtdel.setVisible(false);   
    }
  Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://192.168.1.101;3306/mfcc", "root", "12345678m");
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
        public void deletebutton(String no){
              if (no == "Admin") {
                 jButton6.setEnabled(true);
        } else {
                    jButton6.setEnabled(false);
        }
    }
    public boolean validateFields(){
    boolean b = false;
       if (txtname.getText().isEmpty()){
           b=false;
txterr.setText("Name is empty");
txtname.requestFocus();
       }
      else if (txtusername.getText().isEmpty()){
          b=false;
txterr1.setText("Username is empty");
txtusername.requestFocus();
       }
                    else if (txtpassword.getText().isEmpty()){
           b=false;
txterr2.setText("Password is empty");
txtpassword.requestFocus();
       }
                          else  if (txtphone.getText().isEmpty()){
           b=false;
txterr3.setText("Phone is empty");
txtphone.requestFocus();
       }
       else{
    b=true;
    txterr.setText("");
    txterr1.setText("");
    txterr2.setText("");
    txterr3.setText("");
    } 
       return b;
}
          public void user_table(){ 
                try {
            pst = con.prepareStatement("select*from user");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)jTable3.getModel();
            df.setRowCount(0);
            while(rs.next()){
            Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) 
                {
            v2.add(rs.getString("name"));
           v2.add(rs.getString("username"));
          v2.add(rs.getString("usertype"));
          v2.add(rs.getString("phone"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
               Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }
          public void delete(){
                      String pno = txtname.getText();
        try {
            pst = con.prepareStatement("delete from user where name = ?");
            pst.setString(1, pno);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "User Deleted successfully");
            user_table();
            txterr.setText("");
            txterr1.setText("");
            txterr2.setText("");
            txterr3.setText("");
            jButton1.setEnabled(true);
        } catch (SQLException ex) {
           Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
          }
          public void save(){
               String name = txtname.getText();   
      String  username = txtusername.getText();
      String  password = txtpassword.getText();
      String usertype = txtutype.getSelectedItem().toString();
      String phone = txtphone.getText();
        try {
                      String sql = "select * from user where username = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtusername.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 JOptionPane.showMessageDialog(this, "Patient data already exists");
            }
                else{
                     pst = con.prepareStatement("Insert into user(name,username,password,usertype,phone)values(?,?,?,?,?)"); //sql query for inserting into database
            pst.setString(1, name);
            pst.setString(2, username);   
            pst.setString(3, password);
            pst.setString(4, usertype);
            pst.setString(5, phone);
            pst.executeUpdate(); //perform the action of insertion
            JOptionPane.showMessageDialog(this,"User added successfully");
            user_table();
               txtname.requestFocus(); }
        } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }catch (Exception e){
        }
          }
          public void update(){
           String username = txtusername.getText();
         String pass = txtpassword.getText();
          String usert = txtutype.getSelectedItem().toString();
           String phone = txtphone.getText();
           String name = txtname.getText();
                  try {
                      String sql = "select * from user where username = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtusername.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                   try {
            pst = con.prepareStatement("update user set username = ?, password = ?, usertype = ?, phone = ? where name = ?");
              pst.setString(1, username);
               pst.setString(2, pass);
               pst.setString(3, usert);
                  pst.setString(4, phone);
                           pst.setString(5, name);
               pst.executeUpdate();
               JOptionPane.showMessageDialog(this, "User Updated successfully");
               txtname.requestFocus();
               user_table();    
        }
          catch (SQLException ex) {
         Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
                    JOptionPane.showMessageDialog(this, "Data doesn't exist");    
                }
                  } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
  
          }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        txtutype = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        txterr = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        txterr2 = new javax.swing.JLabel();
        txterr3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        txtdel = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Creation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(51, 0, 51));

        jLabel1.setBackground(new java.awt.Color(51, 0, 51));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");

        jLabel2.setBackground(new java.awt.Color(51, 0, 51));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UserName");

        jLabel3.setBackground(new java.awt.Color(51, 0, 51));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");

        jLabel4.setBackground(new java.awt.Color(51, 0, 51));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("UserType");

        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnameKeyTyped(evt);
            }
        });

        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusernameKeyTyped(evt);
            }
        });

        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpasswordKeyTyped(evt);
            }
        });

        txtutype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pharmacist", "Doctor", "Receptionist", "Lab_attedant", "Lab" }));

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 0, 51));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Phone");

        txtphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtphoneKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtphoneKeyTyped(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 0, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 0));
        jLabel5.setText("Users details table");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "username", "usertype", "phone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable3AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        txterr.setForeground(new java.awt.Color(153, 0, 0));

        txterr1.setForeground(new java.awt.Color(204, 0, 0));

        txterr2.setForeground(new java.awt.Color(204, 0, 0));

        txterr3.setForeground(new java.awt.Color(204, 0, 0));

        jButton6.setBackground(new java.awt.Color(204, 0, 0));
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtdel.setBackground(new java.awt.Color(51, 0, 51));
        txtdel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(31, 31, 31))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtutype, javax.swing.GroupLayout.Alignment.TRAILING, 0, 277, Short.MAX_VALUE)
                            .addComponent(txtpassword)
                            .addComponent(txtusername)
                            .addComponent(txtname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtphone)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(txterr2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(266, 266, 266))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txterr3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)
                        .addGap(46, 46, 46)))
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(txtdel)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(txterr)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(txterr1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addComponent(txterr2)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtutype, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txterr3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(txtdel))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
  if (validateFields()== true){
            delete();
        }
        else{

        } 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        DefaultTableModel d1 = (DefaultTableModel)jTable3.getModel();
        int SelectIndex = jTable3.getSelectedRow();
        txtname.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtusername.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtutype.setSelectedItem(d1.getValueAt(SelectIndex, 2).toString());
        txtphone.setText(d1.getValueAt(SelectIndex, 3).toString());
        user_table();
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable3AncestorAdded

    }//GEN-LAST:event_jTable3AncestorAdded

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     this.setVisible(false);
                           String no = txtdel.getText();
                           new User(no).setVisible(true);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (validateFields()== true){
            update();
        }
        else{

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtphoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphoneKeyReleased
        try {
            int i=Integer.parseInt(txtphone.getText());
            txterr3.setText("");
        } catch (NumberFormatException evtI) {
            txterr3.setText("Format is invalid");
            txtphone.setText("");
        }
    }//GEN-LAST:event_txtphoneKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtname.setText("");
        txtusername.setText("");
        txtpassword.setText("");
        txtutype.setSelectedIndex(1);
        txtphone.setText("");
        txterr.setText("");
        txterr1.setText("");
        txterr2.setText("");
        txterr3.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validateFields()== true){
            save();
        }
        else if(validateFields()==false){
txtname.requestFocus();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtphoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphoneKeyTyped
        txterr3.setText("");
    }//GEN-LAST:event_txtphoneKeyTyped

    private void txtnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyTyped
         txterr.setText("");
    }//GEN-LAST:event_txtnameKeyTyped

    private void txtusernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyTyped
         txterr1.setText("");
    }//GEN-LAST:event_txtusernameKeyTyped

    private void txtpasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyTyped
      txterr2.setText("");
    }//GEN-LAST:event_txtpasswordKeyTyped

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() // Create and display the form
        {
            public void run() {
                new User().setVisible(true);   //setting to active
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel txtdel;
    private javax.swing.JLabel txterr;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterr2;
    private javax.swing.JLabel txterr3;
    private javax.swing.JTextField txtname;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtusername;
    private javax.swing.JComboBox<String> txtutype;
    // End of variables declaration//GEN-END:variables
}
