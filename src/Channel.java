import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSNotifyShadowFade;
public class Channel extends javax.swing.JFrame {
   
    public Channel(String no) {
        initComponents();
        txtdel.setText(no);
Connect();
        Channel_table();
        date();
        Strr();
        deletebutton(no);
        txtdel.setVisible(false);
          jTable1.setBackground(Color.LIGHT_GRAY);
       jTable1.setSelectionBackground(Color.YELLOW);
       jTable1.getTableHeader().setBackground(Color.GREEN);
    }
     Connection con; 
    PreparedStatement pst;
    ResultSet rs;
    String chno;

    Channel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void deletebutton(String no){
              if (no == "Admin") {
                 jButton4.setEnabled(true);
        } else {
                    jButton4.setEnabled(false);
        }
    }
public void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    txtdate.setText(dtf.format(now).toString());
    txtdate.setEnabled(false);
}

   public boolean validateFields(){
    boolean b = false;
       if (txtage.getText().isEmpty()){
           b=false;
txterr1.setText("Age is empty");
       }
       else{
    b=true;
    txterr1.setText("");
    } 
       return b;
}
   public void save(){
        String chno = lblpno.getText();
        String doc = txtdoc1.getSelectedItem().toString();
                String pname = txtpatient.getText();
         String room = txtroom.getValue().toString();
                        String date = txtdate.getText();
                         String pressure = txtblood.getText();
       int age = (Integer.parseInt(txtage.getText()));
                try {
          String sql = "select * from channel where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lblpno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data already exists....update", Color.white, Color.orange, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                            pst = con.prepareStatement("Insert into channel(channelno,doctorname,patientname,roomno,date,age,bpressure)values(?, ?, ?, ?,?,?,?)");
            pst.setString(1, chno);
             pst.setString(2, doc);
              pst.setString(3,pname);
             pst.setString(4, room);
             pst.setString(5, date);
             pst.setInt(6, age);
             pst.setString(7, pressure);
               pst.executeUpdate();
               
new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Channel created successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               Channel_table();
                date();
               txtdoc1.requestFocus();
                }

               
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    
   }
  public void update(){
        String doctorname = txtdoc1.getSelectedItem().toString();
 String patientname = txtpatient.getText();
String room = txtroom.getValue().toString();
         String date = txtdate.getText();
          String age = txtage.getText();
           String pressure = txtblood.getText();
         String pno = lblpno.getText();
                  try {
          String sql = "select * from channel where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lblpno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
        try {
            pst = con.prepareStatement("update channel set i=(select MAX(i)+1 from channel), doctorname = ?, patientname = ?, roomno = ?, date = ?, age = ?, bpressure = ? where channelno = ?");
           pst.setString(1, doctorname);
              pst.setString(2, patientname);
               pst.setString(3, room);
               pst.setString(4, date);
                  pst.setString(5, age);
               pst.setString(6, pressure);
               pst.setString(7, pno);
               pst.executeUpdate();              
new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Patient Updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
              txtdoc1.requestFocus();
               date();
               Channel_table(); 
        }
          catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
                   new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true); 
                }
                  } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

   }
  public void delete(){
      String pno = lblpno.getText();
        try {
            pst = con.prepareStatement("delete from channel where channelno = ?");
                pst.setString(1, pno);
               pst.executeUpdate(); 
new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Channel Deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               date();
               txtdoc1.setSelectedItem(0);
               txtpatient.setText("");
               txtroom.setValue(-1);
               txtage.setText("");
               txtblood.setText("");
              txtdoc1.requestFocus();
               Channel_table();
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }  
  }
        public void Connect(){      //connecting to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
          
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
          public void Channel_table(){ 
        try {
            pst = con.prepareStatement("select*from patient  ORDER BY i DESC");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while(rs.next()){
            Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) 
                {
            v2.add(rs.getString("patientno"));
           v2.add(rs.getString("patientname"));
            v2.add(rs.getString("age"));
                        v2.add(rs.getString("bpressure"));
            v2.add(rs.getString("date"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
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
        txtadd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblpno = new javax.swing.JLabel();
        txtdoc1 = new javax.swing.JComboBox();
        txtroom = new javax.swing.JSpinner();
        txtupdate = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtu = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpatient = new javax.swing.JLabel();
        txtage = new javax.swing.JLabel();
        txtblood = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtpatient1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        searchData = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txterr = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        txtdel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Channel creation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient no");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Send to");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Patient name");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Room");

        txtadd.setBackground(new java.awt.Color(0, 102, 0));
        txtadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblpno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblpno.setForeground(new java.awt.Color(255, 255, 255));

        txtdoc1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "null", "Doctor", "Lab attendant", "Pharmacy" }));

        txtupdate.setBackground(new java.awt.Color(0, 0, 102));
        txtupdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtupdate.setForeground(new java.awt.Color(255, 255, 255));
        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 0, 0));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete");
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

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Age");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bpressure");

        txtpatient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpatient.setForeground(new java.awt.Color(255, 255, 255));

        txtage.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtage.setForeground(new java.awt.Color(255, 255, 255));

        txtblood.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtblood.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient no", "Patient name", "Age", "Bpressure", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtpatient1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpatient1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Current date");

        txtdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));

        searchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDataActionPerformed(evt);
            }
        });
        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDataKeyPressed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setText("Patient database");

        txterr.setForeground(new java.awt.Color(204, 204, 0));
        txterr.setText("Search by Patient name");

        txterr1.setForeground(new java.awt.Color(255, 0, 51));

        txtdel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpatient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtu)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtroom)
                                    .addComponent(lblpno, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(txtpatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtdoc1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(txtblood, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txterr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(253, 253, 253)
                                .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(txtdel)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblpno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txterr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtroom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtpatient1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtblood, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtadd)
                            .addComponent(txtupdate)
                            .addComponent(jButton4)
                            .addComponent(jButton2)
                            .addComponent(jButton5)
                            .addComponent(txtdel))
                        .addGap(36, 36, 36))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
       if (validateFields()== true){
            save();
        }
        else{

        }  
    }//GEN-LAST:event_txtaddActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        date();
        lblpno.setText("");
        txtdoc1.setSelectedIndex(-1);
              txtpatient.setText("");
               txtroom.setValue(-1);
                txtage.setText("");
                txtblood.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
 if (validateFields()== true){
            update();
        }
        else{

        } 
    }//GEN-LAST:event_txtupdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      if (validateFields()== true){
            delete();
        }
        else{

        } 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorAdded
      
    }//GEN-LAST:event_jTable1AncestorAdded

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel(); 
        int SelectIndex = jTable1.getSelectedRow();
        lblpno.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtage.setText(d1.getValueAt(SelectIndex, 2).toString());  
        txtblood.setText(d1.getValueAt(SelectIndex, 3).toString());  
        Channel_table();
        date();
    }//GEN-LAST:event_jTable1MouseClicked

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed

    }//GEN-LAST:event_searchDataKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
     this.setVisible(false);
                           String no = txtdel.getText();
                           new Channel(no).setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 try {
            String sql = "select * from channel where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                   String setn = rs.getString("channelno");
                lblpno.setText(setn);
                   String setnam = rs.getString("doctorname");
                txtdoc1.setSelectedItem(setnam);
                                String setname = rs.getString("patientname");
                txtpatient.setText(setname);
                        String setnm = rs.getString("date");
                txtdate.setText(setnm);
                String age = rs.getString("age");
                txtage.setText(age);
                String gender = rs.getString("bpressure");
                txtblood.setText(gender);
            }
                 else{
                 txterr.setText("Channel not found");
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Channel not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                 searchData.setText("");
                 searchData.requestFocus();
                                  txterr.setText("Search by patient name");
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void searchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDataActionPerformed
 try {
           
            String sql = "select * from patient where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String setname = rs.getString("patientname");
                txtpatient.setText(setname);
                String age = rs.getString("age");
                txtage.setText(age);
                String gender = rs.getString("bpressure");
                txtblood.setText(gender);
            }
                 else{
                 searchData.setText("");
                 searchData.requestFocus();
                  txterr.setText("Search by patient name");
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_searchDataActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
    date();
    }//GEN-LAST:event_jPanel1MouseMoved
public void Strr(){
 TimerTask timerTask = new TimerTask() {
       @Override
       public void run() {
         Channel_table();
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
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Channel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Channel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblpno;
    private javax.swing.JTextField searchData;
    private javax.swing.JButton txtadd;
    private javax.swing.JLabel txtage;
    private javax.swing.JLabel txtblood;
    private javax.swing.JLabel txtdate;
    private javax.swing.JLabel txtdel;
    private javax.swing.JComboBox txtdoc1;
    private javax.swing.JLabel txterr;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txtpatient;
    private javax.swing.JLabel txtpatient1;
    private javax.swing.JSpinner txtroom;
    private javax.swing.JLabel txtu;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
