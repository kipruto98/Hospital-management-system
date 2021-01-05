
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
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


public class Doctor extends javax.swing.JFrame implements TableModelListener{

    public Doctor(String no) {
        initComponents();
        Connect();
        Channel_table();
        date();
        loadDoctor();
        Strr();
        
       txtno.setVisible(false);
               deletebutton(no);
        txtbutton.setText(no);
        txtbutton.setVisible(false);
       txttable.setBackground(Color.LIGHT_GRAY);
       txttable.setSelectionBackground(Color.YELLOW);
       txttable.getTableHeader().setBackground(Color.GREEN);
    }
     Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Doctor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                public void deletebutton(String no){
              if (no == "Admin") {
                 txtdel.setEnabled(true);
        } else {
                    txtdel.setEnabled(false);
        }
    }
public void diagnosis(){
        String chno = txtchno.getText();
     String doc = txtdoc1.getSelectedItem().toString();
                String pname = txtpatient.getText();
       int age = (Integer.parseInt(txtage.getText()));
                         String pressure = txtblood.getText();
         String date = txtdate.getText();
                         String des = txtdescription.getText();
                         String lab = txtdoc2.getSelectedItem().toString();
                         String rx = txtrx.getText();
                         String conss = cons.getText();
                try {
                    String sql = "select * from lab where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == false) {
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.orange, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                                pst = con.prepareStatement("Insert into diagnosis(channelno_lab,doctorname,patientname,age,bpressure,date,description,labrequest,rx,cons)values(?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
             pst.setString(1, chno);
             pst.setString(2, doc);
              pst.setString(3,pname);
             pst.setInt(4, age);
             pst.setString(5, pressure);
             pst.setString(6, date);
             pst.setString(7, des);
             pst.setString(8, lab);
             pst.setString(9, rx);
              pst.setString(10, conss);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Request send successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                date();
               Channel_table();
               loadDoctor();
               txtdescription.requestFocus();
                }

               
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
}

    public void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    txtdate.setText(dtf.format(now).toString());
    //txtdate.setEnabled(false);
}
public void combo(){
    try {
             String sql = "select * from lab where channelno = ? ORDER BY i ASC ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            ResultSet rs = pst.executeQuery();
              txtdoc2.removeAllItems();
            while (rs.next()){
               String blood2 = rs.getString("labrequest");
               txtdoc2.addItem(blood2);
            }
    } catch (SQLException ex) {            
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
{            
            
        }
            
}
      public boolean validateFields(){
    boolean b = false;
       if (txtdescription.getText().isEmpty()){
           b=false;
txterr1.setText("Description is empty");
txtdescription.requestFocus();
       }
            else if (txtchno.getText().isEmpty()){
          b=false;
txterr3.setText("Patient number is empty");
       }
       else{
    b=true;
    txterr1.setText("");
    } 
       return b;
}

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public class Doctorr{
    String id;
    String name;
    public Doctorr(String id,String name){
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
         
         txtdoc2.addItem(new Doctorr(rs.getString(1),rs.getString(2)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
          public void Channel_table(){
        try {
            pst = con.prepareStatement("SELECT * FROM channel WHERE doctorname = 'Doctor' ORDER BY i DESC");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)txttable.getModel();
            df.setRowCount(0);
            while(rs.next()){
            Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) 
                {
            v2.add(rs.getString("channelno"));
            v2.add(rs.getString("doctorname"));
            v2.add(rs.getString("patientname"));
                        v2.add(rs.getString("roomno"));
             v2.add(rs.getString("age"));
            v2.add(rs.getString("date"));
            v2.add(rs.getString("bpressure"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
           
        }
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        txtadd = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtchno = new javax.swing.JLabel();
        txtpatient = new javax.swing.JLabel();
        txtupdate = new javax.swing.JButton();
        searchData = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtage = new javax.swing.JLabel();
        txtblood = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdescription = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txttable = new javax.swing.JTable();
        txtdel = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        txterr = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        txterr3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtrx = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txtdoc1 = new javax.swing.JComboBox<>();
        txtdoc2 = new javax.swing.JComboBox();
        txtbutton = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cons = new javax.swing.JTextField();
        err = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        txtadd.setBackground(new java.awt.Color(0, 153, 51));
        txtadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
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

        jButton3.setBackground(new java.awt.Color(204, 51, 0));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Send to");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Patient name");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Age");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient no");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Blood pressure");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Description");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        txtchno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtchno.setForeground(new java.awt.Color(255, 255, 255));

        txtpatient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpatient.setForeground(new java.awt.Color(255, 255, 255));

        txtupdate.setBackground(new java.awt.Color(0, 0, 102));
        txtupdate.setForeground(new java.awt.Color(255, 255, 255));
        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDataKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lab request");

        txtage.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtage.setForeground(new java.awt.Color(255, 255, 255));

        txtblood.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtblood.setForeground(new java.awt.Color(255, 255, 255));

        txtdescription.setColumns(20);
        txtdescription.setRows(5);
        txtdescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescriptionKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtdescription);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Date");

        txttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient no", "doctorname", "patientname", "room", "age", "date", "pressure"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        txttable.setSelectionBackground(new java.awt.Color(0, 51, 255));
        txttable.setSelectionForeground(new java.awt.Color(255, 102, 102));
        txttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttableMouseClicked(evt);
            }
        });
        txttable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txttableInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(txttable);

        txtdel.setBackground(new java.awt.Color(153, 0, 0));
        txtdel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));
        txtdel.setText("Delete");
        txtdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdelActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setForeground(new java.awt.Color(0, 0, 153));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txterr.setForeground(new java.awt.Color(204, 0, 0));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("Search by Patient name");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Channel table");

        txterr1.setForeground(new java.awt.Color(153, 0, 0));

        txterr3.setForeground(new java.awt.Color(153, 0, 0));

        jButton1.setBackground(new java.awt.Color(51, 0, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("MORE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 204, 204));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("A4");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Rx");

        txtrx.setColumns(20);
        txtrx.setRows(5);
        jScrollPane2.setViewportView(txtrx);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Info");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        txtdoc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lab", "Pharmacy", "Null" }));
        txtdoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdoc1ActionPerformed(evt);
            }
        });

        txtdoc2.setForeground(new java.awt.Color(255, 255, 255));
        txtdoc2.setMaximumRowCount(99);
        txtdoc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdoc2MouseClicked(evt);
            }
        });

        txtbutton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbutton.setForeground(new java.awt.Color(255, 255, 255));

        txtdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));
        txtdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdateMouseClicked(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(102, 0, 102));
        jButton11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Accounts");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Consultation");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        cons.setBackground(new java.awt.Color(255, 255, 255));
        cons.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cons.setForeground(new java.awt.Color(0, 0, 0));
        cons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                consKeyTyped(evt);
            }
        });

        err.setForeground(new java.awt.Color(204, 0, 0));

        jButton6.setBackground(new java.awt.Color(255, 255, 0));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Info");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(txterr3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(178, 178, 178)
                                        .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtblood, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtchno, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtage, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(txtdel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(344, 344, 344))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(327, 327, 327))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(txtbutton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton6))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cons, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(err, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(138, 138, 138)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(160, 160, 160)
                    .addComponent(txtdoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(903, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtchno)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtno)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(err, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtage))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txtblood))
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel7))
                                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel6)
                                                .addGap(57, 57, 57))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                        .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(114, 114, 114)
                                        .addComponent(txterr1)))))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jButton1)
                            .addComponent(txtdoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdel)
                    .addComponent(jButton3)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton2)
                    .addComponent(txtbutton)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(txtdoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(489, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Connect();
        a7();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed
try {
           
            String sql = "select * from lab where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                String pnn = rs.getString("channelno");
               txtchno.setText(pnn);
                String setname = rs.getString("doctorname");
                txtdoc2.setSelectedItem(setname);
               String sname = rs.getString("patientname");
                txtpatient.setText(sname);
                String age = rs.getString("age");
                txtage.setText(age);
                    String blood = rs.getString("bpressure");
                txtblood.setText(blood);
                 String date = rs.getString("date");
                 txtdate.setText(date);
              String blood1 = rs.getString("description");
                txtdescription.setText(blood1);
                                    String blood2 = rs.getString("labrequest");
                txtdoc2.addItem(blood2);
                 String date1 = rs.getString("rx");
                 txtrx.setText(date1);
                 searchData.setText("");
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }          
    }//GEN-LAST:event_searchDataKeyPressed

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
        date();
        if (validateFields()== true){
           save();
           txterr1.setText("");
           txterr3.setText("");
        }
        else{
        }
    }//GEN-LAST:event_txtaddActionPerformed
public void save(){
        String chno = txtchno.getText();
     String doc = txtdoc1.getSelectedItem().toString();
                String pname = txtpatient.getText();
       int age = (Integer.parseInt(txtage.getText()));
                         String pressure = txtblood.getText();
         String date = txtdate.getText();
                         String des = txtdescription.getText();
                         String lab = txtdoc2.getSelectedItem().toString();
                         String rx = txtrx.getText();
                         String conss = cons.getText();
                try {
           String sql = "select * from lab where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data already exists....update", Color.white, Color.orange, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                                pst = con.prepareStatement("Insert into lab(channelno,doctorname,patientname,age,bpressure,date,description,labrequest,rx,cons)values(?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
             pst.setString(1, chno);
             pst.setString(2, doc);
              pst.setString(3,pname);
             pst.setInt(4, age);
             pst.setString(5, pressure);
             pst.setString(6, date);
             pst.setString(7, des);
             pst.setString(8, lab);
             pst.setString(9, rx);
              pst.setString(10, conss);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Request send successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                date();
               Channel_table();
               loadDoctor();
               txtdescription.requestFocus();
                }

               
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          txtchno.setText("");
                              txtpatient.setText("");
               txtage.setText("");
                              txtdescription.setText("");
                              txtdate.setText("");
               txtdoc2.setSelectedItem("null");
               txtrx.setText("");
               txtdescription.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
date();
       if (validateFields()== true){
           diagnosis();
           txterr1.setText("");
           txterr3.setText("");
        }
        else{
           
        }
    }//GEN-LAST:event_txtupdateActionPerformed

    private void txttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttableMouseClicked

        DefaultTableModel d1 = (DefaultTableModel)txttable.getModel();
        int SelectIndex = txttable.getSelectedRow();
        txtchno.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());
        txtage.setText(d1.getValueAt(SelectIndex, 4).toString());
        txtblood.setText(d1.getValueAt(SelectIndex, 6).toString());
        Channel_table();
       txtdoc2.removeAllItems();
        txtdescription.setText("");
               txtrx.setText("");
               txtdescription.requestFocus();
                loadDoctor();
    }//GEN-LAST:event_txttableMouseClicked

    private void txttableInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txttableInputMethodTextChanged
   
    }//GEN-LAST:event_txttableInputMethodTextChanged

    private void txtdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdelActionPerformed
String pno = txtpatient.getText();
        try {
            pst = con.prepareStatement("delete from lab where patientname = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Lab request deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
           txtchno.setText("");
                              txtpatient.setText("");
               txtage.setText("");
               txtdate.setText("");
               txtblood.setText("");
                              txtdescription.setText("");
               txtdoc2.setSelectedItem("null");
               txtrx.setText("");
               txtdescription.requestFocus();
               cons.setText("");
               Channel_table();
            date();
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }                          
    }//GEN-LAST:event_txtdelActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
   this.setVisible(false);
                           String no = txtbutton.getText();
                           new Doctor(no).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtdescriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescriptionKeyTyped
       txterr1.setText("");
    }//GEN-LAST:event_txtdescriptionKeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     try {
           
            String sql = "select * from lab where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                String pnn = rs.getString("channelno");
               txtchno.setText(pnn);
                String setname = rs.getString("doctorname");
                txtdoc1.setSelectedItem(setname);
               String sname = rs.getString("patientname");
                txtpatient.setText(sname);
                String age = rs.getString("age");
                txtage.setText(age);
                    String blood = rs.getString("bpressure");
                txtblood.setText(blood);
                    String blood1 = rs.getString("description");
                txtdescription.setText(blood1);
                                    String blood2 = rs.getString("labrequest");
                txtdoc2.addItem(blood2);
                 String date1 = rs.getString("rx");
                 txtrx.setText(date1);
                 searchData.setText("");
            }
             else{
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Patient not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                 searchData.setText("");
                 searchData.requestFocus();
                 }
         
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }          
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String chno = txtchno.getText();
                String pname = txtpatient.getText();
              try {
           String sql = "select * from lab where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,chno);
            pst.setString(2,pname);
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {                  
                    String no = txtchno.getText();
               String name = txtpatient.getText();
new Doctor1(no,name).setVisible(true);
            }
                            else{
                new rojerusan.RSNotifyShadowFade("!WARNING!", "Send request first", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
    }                                        

    }   catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        a4();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        date();
    }//GEN-LAST:event_jPanel1MouseMoved
public void a7(){
    Connect();
    try {
        String sql = "select * from diagnosis where channelno_lab = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) { 
            String patientname;
            patientname = txtchno.getText();
         try {
        JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report7.jrxml");
            String query = "SELECT * FROM patient INNER JOIN diagnosis ON patient.patientno = diagnosis.channelno_lab WHERE patient.patientno = '" + patientname + "' AND diagnosis.i = (select MAX(i)from diagnosis where diagnosis.channelno_lab = '" + patientname + "')";
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
            patientname = txtchno.getText();
         try {
 JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report7.jrxml");
            String query = "SELECT * FROM patient INNER JOIN lab ON patient.patientno = lab.channelno WHERE patient.patientno = '" + patientname + "'";
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
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public void a4(){
    try {
        String sql = "select * from diagnosis where channelno_lab = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) { 
            String patientname;
            patientname = txtchno.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report1.jrxml");
            String query = "SELECT * FROM patient INNER JOIN diagnosis ON patient.patientno = diagnosis.channelno_lab WHERE  patient.patientno = '" + patientname + "' AND diagnosis.i = (select MAX(i)from diagnosis  where diagnosis.channelno_lab = '" + patientname +"')";
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
            patientname = txtchno.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report1.jrxml");
            String query = "SELECT * FROM patient INNER JOIN lab ON patient.patientno = lab.channelno WHERE patient.patientno = '" + patientname + "'";
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
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        combo();
        try {
            String sql = "select * from lab where channelno = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
              String blood1 = rs.getString("description");
                txtdescription.setText(blood1);               
                 String date1 = rs.getString("rx");
                 txtrx.setText(date1); 
                   String date2 = rs.getString("date");
                 txtdate.setText(date2);
            }
                                    else{
                new rojerusan.RSNotifyShadowFade("!WARNING!", "Information not available", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
    } 
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void txtdoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdoc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdoc1ActionPerformed

    private void txtdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateMouseClicked
        date();
    }//GEN-LAST:event_txtdateMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
                  String sql = "select * from diagnosis where channelno_lab = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
        if (validateFields()== true){
            String no = txtchno.getText();
            String name = txtpatient.getText();
            String button = txtbutton.getText();
            new Accounts1(no,name,button).setVisible(true);
        }
                }
        else{
               if (validateFields()== true){
            String no = txtchno.getText();
            String name = txtpatient.getText();
            String button = txtbutton.getText();
            new Accounts(no,name,button).setVisible(true);
        }  
        }
        } 
        catch (SQLException e) {
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void consKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consKeyTyped
        try {
            int i=Integer.parseInt(cons.getText());
            err.setText("");
        } catch (NumberFormatException evtI) {
            err.setText("Format is invalid");
            cons.setText("");
        }
    }//GEN-LAST:event_consKeyTyped

    private void txtdoc2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdoc2MouseClicked
       txtdoc2.removeAllItems();
        loadDoctor();
    }//GEN-LAST:event_txtdoc2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String patientname;
            patientname = txtchno.getText();     
        try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report12.jrxml");
            String query = "SELECT * FROM diagnosis WHERE channelno_lab = '" + patientname + "' UNION all SELECT * from lab WHERE channelno = '" + patientname + "' ORDER BY date DESC";
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
    }//GEN-LAST:event_jButton6ActionPerformed
   public void Strr(){
 TimerTask timerTask = new TimerTask() {
     @Override
     public void run() {
       Channel_table();    
     }
   };
 Timer timer = new Timer("");
 timer.scheduleAtFixedRate(timerTask, 0,8000);
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
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Doctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cons;
    private javax.swing.JLabel err;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField searchData;
    private javax.swing.JButton txtadd;
    private javax.swing.JLabel txtage;
    private javax.swing.JLabel txtblood;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JLabel txtchno;
    private javax.swing.JLabel txtdate;
    private javax.swing.JButton txtdel;
    private javax.swing.JTextArea txtdescription;
    private javax.swing.JComboBox<String> txtdoc1;
    private javax.swing.JComboBox txtdoc2;
    private javax.swing.JLabel txterr;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterr3;
    private javax.swing.JLabel txtno;
    private javax.swing.JLabel txtpatient;
    private javax.swing.JTextArea txtrx;
    private javax.swing.JTable txttable;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
