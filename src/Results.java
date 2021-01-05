
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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


public class Results extends javax.swing.JFrame {

    public Results(String no) {
        initComponents();
        date();
        Connect();
        results_table();
        results_table1();
        deletebutton(no);
        Strr();
        txtbutton.setText(no);
        txtbutton.setVisible(false);
             jTable1.setBackground(Color.LIGHT_GRAY);
       jTable1.setSelectionBackground(Color.YELLOW);
    }
      Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Results() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
                       Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
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
       if (txtarea.getText().isEmpty()){
           b=false;
txterr3.setText("Field is empty");
       }
       
       else if (txtpatient.getText().isEmpty()){
           b=false;
txterr1.setText("Field is empty");
       }
              else if (txtcost.getText().isEmpty()){
           b=false;
txterr5.setText("Field is empty");
       }
                          else if (txtdisease.getText().isEmpty()){
           b=false;
txterr4.setText("Field is empty");
       }
                                      else if (txtpres.getText().isEmpty()){
           b=false;
txterr2.setText("Field is empty");
       }
       else{
    b=true;
    txterr1.setText("");
        txterr2.setText("");
            txterr3.setText("");
        txterr4.setText("");
            txterr5.setText("");
    } 
       return b;
}
                                  public boolean validateFieldss(){
    boolean b = false;
       if (txtpatient.getText().isEmpty()){
           b=false;
txterr1.setText("Field is empty");
       }
       else{
    b=true;
    txterr1.setText("");
    } 
       return b;
}
public void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" HH:mm:ss yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    txtdate1.setText(dtf.format(now).toString());
    //txtdate.setEnabled(false);
}

public void results_table(){  
        try {
            pst = con.prepareStatement("select * from results ORDER BY i DESC");
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
            v2.add(rs.getString("channelno"));
            v2.add(rs.getString("doctorname"));
            v2.add(rs.getString("patientname"));
            v2.add(rs.getString("date"));
            v2.add(rs.getString("testtype"));
                       v2.add(rs.getString("labresults"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
                      Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
            
        }
  }
public void results_table1(){  
        try {
            pst = con.prepareStatement("select * from results_1 ORDER BY i DESC");
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
            v2.add(rs.getString("channelno"));
            v2.add(rs.getString("doctorname"));
            v2.add(rs.getString("patientname"));
            v2.add(rs.getString("date"));
            v2.add(rs.getString("testtype"));
                       v2.add(rs.getString("labresults"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
                      Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
            
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtadd = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        txtdelete = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtchannel = new javax.swing.JLabel();
        txtpatient = new javax.swing.JLabel();
        txttype = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdisease = new javax.swing.JTextField();
        txtcost = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        searchData = new javax.swing.JTextField();
        txterr1 = new javax.swing.JLabel();
        txterror = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txterr2 = new javax.swing.JLabel();
        txterr3 = new javax.swing.JLabel();
        txterr4 = new javax.swing.JLabel();
        txterr5 = new javax.swing.JLabel();
        txtlab1 = new javax.swing.JScrollPane();
        txtlab = new javax.swing.JTextArea();
        txtlab2 = new javax.swing.JScrollPane();
        txtpres = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtbutton = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        txtdate1 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prescription", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel2.setText("Patient name");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Test type");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Lab results");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Treatment");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("History");

        txtadd.setBackground(new java.awt.Color(0, 153, 51));
        txtadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        txtupdate.setBackground(new java.awt.Color(0, 51, 102));
        txtupdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtupdate.setForeground(new java.awt.Color(255, 255, 255));
        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        txtdelete.setBackground(new java.awt.Color(102, 0, 0));
        txtdelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdelete.setForeground(new java.awt.Color(255, 255, 255));
        txtdelete.setText("Delete");
        txtdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdeleteActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 0));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cancel");
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

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("A4");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Channel no", "Doctor name", "Patient name", "Date", "Test  type", "lab results"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtchannel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtchannel.setForeground(new java.awt.Color(255, 255, 255));

        txtpatient.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtpatient.setForeground(new java.awt.Color(255, 255, 255));

        txttype.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txttype.setForeground(new java.awt.Color(255, 255, 255));

        txtarea.setColumns(20);
        txtarea.setRows(5);
        txtarea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtareaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtarea);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Disease type");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Consultation fee");

        txtdisease.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdiseaseKeyReleased(evt);
            }
        });

        txtcost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcostKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcostKeyReleased(evt);
            }
        });

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

        txterr1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txterr1.setForeground(new java.awt.Color(204, 0, 0));

        txterror.setForeground(new java.awt.Color(0, 153, 0));
        txterror.setText("Search by patient name");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lab results table");

        txterr2.setForeground(new java.awt.Color(204, 0, 0));

        txterr3.setForeground(new java.awt.Color(204, 0, 0));

        txterr4.setForeground(new java.awt.Color(204, 0, 0));

        txterr5.setForeground(new java.awt.Color(204, 0, 0));

        txtlab.setColumns(20);
        txtlab.setRows(5);
        txtlab1.setViewportView(txtlab);

        txtpres.setColumns(20);
        txtpres.setRows(5);
        txtlab2.setViewportView(txtpres);

        jButton9.setBackground(new java.awt.Color(102, 0, 102));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Other");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 102, 102));
        jButton10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Termal");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Info");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        txtbutton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbutton.setForeground(new java.awt.Color(255, 255, 255));

        jButton11.setBackground(new java.awt.Color(102, 0, 102));
        jButton11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Accounts");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        txtdate1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdate1.setForeground(new java.awt.Color(255, 255, 255));
        txtdate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdate1MouseClicked(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(204, 204, 0));
        jButton12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("Info");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
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
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txterr5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdisease, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtchannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttype, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtlab1, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txterr3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtlab2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txterr2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel10))
                                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(84, 84, 84)
                                        .addComponent(txtdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtbutton))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 19, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(txterr4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txterror, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtchannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbutton)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttype, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jButton9))
                                .addGap(43, 43, 43)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txterr2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txtlab1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtlab2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterror)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtdisease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton12)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txterr4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txterr3)
                            .addComponent(txterr5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadd)
                    .addComponent(txtupdate)
                    .addComponent(txtdelete)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void save(){
     String chno = txtchannel.getText();
                        String pname = txtpatient.getText();
                        String date = txtdate1.getText();
                         String pres = txtpres.getText();
                         String area = txtarea.getText();  
                           String disease = txtdisease.getText();
                         String cost = txtcost.getText();

                try {
                              String sql = "select * from pharmacy where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data already exists...update instead", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                         pst = con.prepareStatement("Insert into pharmacy(channelno,patientname,date,prescription,doctorsremarks,diseasetype,cost)values(?, ?, ?,?,?,?,?)");
            pst.setString(1, chno);
             pst.setString(2, pname);
              pst.setString(3,date);
             pst.setString(4, pres);
             pst.setString(5, area);
             pst.setString(6, disease);
             pst.setString(7, cost);
               pst.executeUpdate(); 
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data send to pharmacy", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               results_table();
           date();
                }

        } catch (SQLException ex) {
                        Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }           
}
public void update(){
    String chno = txtchannel.getText();
                        String pname = txtpatient.getText();
                        String date = txtdate1.getText();
                         String pres = txtpres.getText();
                         String area = txtarea.getText();  
                           String disease = txtdisease.getText();
                         String cost = txtcost.getText();

                try {
                              String sql = "select * from pharmacy where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                                            pst = con.prepareStatement("Insert into pharmacy_1(channelno_1,patientname,date,prescription,doctorsremarks,diseasetype,cost)values(?, ?, ?,?,?,?,?)");
            pst.setString(1, chno);
             pst.setString(2, pname);
              pst.setString(3,date);
             pst.setString(4, pres);
             pst.setString(5, area);
             pst.setString(6, disease);
             pst.setString(7, cost);
               pst.executeUpdate(); 
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data send to pharmacy", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               results_table();
           date();
                 
            }
                else{
 new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data not found{SAVE}", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
                }

        } catch (SQLException ex) {
                        Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }     
  
}
public void delete(){
    String pno = txtpatient.getText();
        try {
            pst = con.prepareStatement("delete from pharmacy where patientname = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Information deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               results_table();
        date();
        } catch (SQLException ex) {
                       Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
}
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        a4();
    }//GEN-LAST:event_jButton6ActionPerformed
public void a4(){
Connect();
try {
                              String sql = "select * from pharmacy_1 where channelno_1 = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
            String patientname;
            patientname = txtchannel.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report5.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy_1 ON patient.patientno = pharmacy_1.channelno_1 WHERE patient.patientno = '" + patientname + "' AND pharmacy_1.i = (select MAX(i)from pharmacy_1 where pharmacy_1.channelno_1 = '" + patientname + "' )";
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
                else{
     String patientname;
            patientname = txtchannel.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report5.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy ON patient.patientno = pharmacy.channelno WHERE patient.patientno = '" + patientname + "'";
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

        } catch (SQLException ex) {
                        Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } 
}
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
txtchannel.setText("");
txtpatient.setText("");
date();
txttype.setText("");
txtlab.setText("");
txtpres.setText("");
txtarea.setText("");
     txtdisease.setText("");
                              txtcost.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
             DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel(); 
        int SelectIndex = jTable1.getSelectedRow();
        txtchannel.setText(d1.getValueAt(SelectIndex, 0).toString());
         txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());
         txttype.setText(d1.getValueAt(SelectIndex, 4).toString()); 
                txtlab.setText(d1.getValueAt(SelectIndex, 5).toString());
date();
txtpres.setText("");
txtarea.setText("");
     txtdisease.setText("");
                              txtcost.setText("");
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
date();
                 if (validateFields()== true){
            update();
        }
        else{
        }          
    }//GEN-LAST:event_txtupdateActionPerformed

    private void txtdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdeleteActionPerformed
        
       if (validateFields()== true){
            delete();
        }
        else{
        }  
    }//GEN-LAST:event_txtdeleteActionPerformed

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
date();
        if (validateFields()== true){
            save();
        }
        else{
        }  
    }//GEN-LAST:event_txtaddActionPerformed

    private void txtcostKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcostKeyPressed
        try {
            int cost=Integer.parseInt(txtcost.getText());
        } catch (NumberFormatException e) {
            
        }
    }//GEN-LAST:event_txtcostKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
   this.setVisible(false);
                           String no = txtbutton.getText();
                           new Results(no).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
               try {
            String sql = "select * from pharmacy where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("channelno");
                txtchannel.setText(pnn);
                String age = rs.getString("patientname");
                txtpatient.setText(age);
                String report = rs.getString("prescription");
                txtpres.setText(report);
                                String setname = rs.getString("doctorsremarks");
                txtarea.setText(setname);
                                String setname1 = rs.getString("diseasetype");
                txtdisease.setText(setname1);
                                String setname2 = rs.getString("cost");
                txtcost.setText(setname2);
            }
                  else{
                 searchData.setText("");
                 searchData.requestFocus();
                 txterror.setText("Patient not found");
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Patient not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                  txterror.setText("Search by patient name");
                 }
        } catch (SQLException ex) {
                          Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed
        try {
            String sql = "select * from pharmacy where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("channelno");
                txtchannel.setText(pnn);
                String age = rs.getString("patientname");
                txtpatient.setText(age);
                String report = rs.getString("prescription");
                txtpres.setText(report);
                                String setname = rs.getString("doctorsremarks");
                txtarea.setText(setname);
                                String setname1 = rs.getString("diseasetype");
                txtdisease.setText(setname1);
                                String setname2 = rs.getString("cost");
                txtcost.setText(setname2);
            }
                  else{

                 }
        } catch (SQLException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_searchDataKeyPressed

    private void txtcostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcostKeyReleased
                      try {
            int i=Integer.parseInt(txtcost.getText());
            txterr5.setText("");
        } catch (NumberFormatException evtI) {
            txterr5.setText("Format is invalid");
            txtcost.setText("");
        }
    }//GEN-LAST:event_txtcostKeyReleased

    private void txtareaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtareaKeyReleased
        txterr3.setText("");
    }//GEN-LAST:event_txtareaKeyReleased

    private void txtdiseaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiseaseKeyReleased
       txterr4.setText("");
    }//GEN-LAST:event_txtdiseaseKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
  
                   if (validateFieldss()== true){
                    String no = txtchannel.getText();
               String name = txtpatient.getText();
new Results1(no,name).setVisible(true);
        }
        else{
        } 
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
results_table1();
results_table();
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        a7();
    }//GEN-LAST:event_jButton10ActionPerformed
public void a7(){
Connect();
try {
                              String sql = "select * from pharmacy_1 where channelno_1 = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
            String patientname;
            patientname = txtchannel.getText();
         try {
        JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report15.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy_1 ON patient.patientno = pharmacy_1.channelno_1  WHERE patient.patientno = '" + patientname + "' AND pharmacy_1.i = (select MAX(i) from pharmacy_1 where pharmacy_1.channelno_1 = '" + patientname + "')";
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
                else{
     String patientname;
            patientname = txtchannel.getText();
         try {
        JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report15.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy ON patient.patientno = pharmacy.channelno WHERE patient.patientno = '" + patientname + "'";
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

        } catch (SQLException ex) {
                        Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } 
}
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
                  try {
            String sql = "select * from pharmacy where channelno = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String report = rs.getString("prescription");
                txtpres.setText(report);
                                String setname = rs.getString("doctorsremarks");
                txtarea.setText(setname);
                                String setname1 = rs.getString("diseasetype");
                txtdisease.setText(setname1);
                                String setname2 = rs.getString("cost");
                txtcost.setText(setname2);
                                 String setname3 = rs.getString("date");
                txtdate1.setText(setname3);
            }
                  else{
new rojerusan.RSNotifyShadowFade("!INFORMATION!", "Information not available", Color.white, Color.BLUE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true); 
                 }
        } catch (SQLException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
          try {
                  String sql = "select * from pharmacy_1 where channelno_1 = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
        if (validateFields()== true){
            String no = txtchannel.getText();
            String name = txtpatient.getText();
            String button = txtbutton.getText();
            new Accounts1(no,name,button).setVisible(true);
        }
                }
        else{
               if (validateFields()== true){
            String no = txtchannel.getText();
            String name = txtpatient.getText();
            String button = txtbutton.getText();
            new Accounts(no,name,button).setVisible(true);
        }  
        }
        } 
        catch (SQLException e) {
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtdate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdate1MouseClicked
        date();
    }//GEN-LAST:event_txtdate1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
                String patientname;
            patientname = txtchannel.getText();     
        try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report13.jrxml");
            String query = "SELECT * FROM pharmacy_1 WHERE channelno_1 = '" + patientname + "' UNION all SELECT * from pharmacy WHERE channelno = '" + patientname + "' ORDER BY date DESC";
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
    }//GEN-LAST:event_jButton12ActionPerformed
public void Strr(){
 TimerTask timerTask = new TimerTask() {
       @Override
       public void run() {
          results_table();
          results_table1();
       }
   };
 Timer timer = new Timer("");
 timer.scheduleAtFixedRate(timerTask, 0, 20000);
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
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Results().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchData;
    private javax.swing.JButton txtadd;
    private javax.swing.JTextArea txtarea;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JLabel txtchannel;
    private javax.swing.JTextField txtcost;
    private javax.swing.JLabel txtdate1;
    private javax.swing.JButton txtdelete;
    private javax.swing.JTextField txtdisease;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterr2;
    private javax.swing.JLabel txterr3;
    private javax.swing.JLabel txterr4;
    private javax.swing.JLabel txterr5;
    private javax.swing.JLabel txterror;
    private javax.swing.JTextArea txtlab;
    private javax.swing.JScrollPane txtlab1;
    private javax.swing.JScrollPane txtlab2;
    private javax.swing.JLabel txtpatient;
    private javax.swing.JTextArea txtpres;
    private javax.swing.JLabel txttype;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
