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


public class Patient extends javax.swing.JFrame {

    public Patient(String no) {
        initComponents();
        Connect();
        AutoID();
        patient_table(); 
        date();
        deletebutton(no);
        txtdel.setText(no);
        txtdel.setVisible(false);
             jTable2.setBackground(Color.LIGHT_GRAY);
       jTable2.setSelectionBackground(Color.YELLOW);
       
    }
    Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Patient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Connect(){             
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
             Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
        public void deletebutton(String no){
              if (no == "Admin") {
                 jButton3.setEnabled(true);
        } else {
                    jButton3.setEnabled(false);
        }
    }
     public boolean validateFields(){
    boolean b = false;
       if (txtpname.getText().isEmpty()){
           b=false;
txterr.setText("Name is empty");
txtpname.requestFocus();
       }
      else if (txtage.getText().isEmpty()){
          b=false;
txterr1.setText("Age is empty");
txtage.requestFocus();
       }
                    else if (txtid.getText().isEmpty()){
           b=false;
txterr2.setText("ID is empty");
txtid.requestFocus();
       }
                          else  if (txtblood.getText().isEmpty()){
           b=false;
txterr3.setText("Pressure is empty");
txtblood.requestFocus();
       }
                                                    else  if (txtweight.getText().isEmpty()){
           b=false;
txterr4.setText("Weight is empty");
txtweight.requestFocus();
       }
                                                                              else  if (txtphone.getText().isEmpty()){
           b=false;
txterr5.setText("Phone is empty");
txtphone.requestFocus();
       }
                                                                                                        else  if (txtaddress.getText().isEmpty()){
           b=false;
txterr6.setText("Pressure is empty");
txtaddress.requestFocus();
       }
       else{
    b=true;
    txterr.setText("");
    txterr1.setText("");
    txterr2.setText("");
    txterr3.setText("");
        txterr4.setText("");
    txterr5.setText("");
    txterr6.setText("");
    } 
       return b;
}
    public void save(){
          String pno = txtpno.getText();
       String pname = txtpname.getText();
        int age = (Integer.parseInt(txtage.getText()));
       String gender = txtgender.getSelectedItem().toString();
       String id = txtid.getText();
       String pressure = txtblood.getText();
       String weight = txtweight.getText(); 
       String date = txtdate.getText();
       String phone = txtphone.getText();
       String address = txtaddress.getText(); 
       String pay = txtpay.getSelectedItem().toString();
       
        try {
                      String sql = "select * from patient where patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtpname.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data already exists...update instead", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                pst = con.prepareStatement("Insert into patient(patientno,patientname,age,gender,id,bpressure,weight,date,phone,address,pay)values(?, ?,?, ?,?,?,?,?,?,?,?)");
            pst.setString(1, pno);
             pst.setString(2, pname);                              
              pst.setInt(3, age);
               pst.setString(4, gender);
                pst.setString(5,id);
             pst.setString(6, pressure);                           
              pst.setString(7, weight);
               pst.setString(8, date);
                pst.setString(9, phone);
               pst.setString(10, address);
               pst.setString(11, pay);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Patient added successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtpno.setText("");
               txtpname.setText("");
               txtage.setText("");
               txtgender.setSelectedIndex(0);
               txtid.setText("");
               txtblood.setText("");
               txtweight.setText("");
                txtdate.setText("");
               txtphone.setText("");
               txtaddress.setText("");
               txtaddress.requestFocus();
               patient_table();  
               AutoID();
               date();
                }
        } catch (SQLException ex) {
                   Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
        public void update(){
            
        String pname = txtpname.getText();
       int age = (Integer.parseInt(txtage.getText()));
  String gender = txtgender.getSelectedItem().toString();
       String id = txtid.getText();
        String pblood = txtblood.getText();
       String weight = txtweight.getText();
       String date = txtdate.getText();
       String phone = txtphone.getText();
       String address = txtaddress.getText();
       String pay = txtpay.getSelectedItem().toString();
       String pno = txtpno.getText();
              
        try {
                      String sql = "select * from patient where patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtpname.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                         try {
            pst = con.prepareStatement("update patient set i=(select MAX(i)+1 from patient), patientname = ?, age = ?, gender = ?, id = ?, bpressure = ?, weight = ?, date = ?, phone = ?, address = ?, pay = ? where patientno = ? ");
            pst.setString(1, pname);
              pst.setInt(2, age);
               pst.setString(3, gender);
                pst.setString(4, id);
             pst.setString(5, pblood);
              pst.setString(6, weight);
               pst.setString(7, date);
                pst.setString(8, phone);
                pst.setString(9, address);
                pst.setString(10, pay);
                  pst.setString(11, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Patient Updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
              txtpno.setText("");
               txtpname.setText("");
               txtage.setText("");
               txtgender.setSelectedIndex(0);
               txtid.setText("");
               txtblood.setText("");
               txtweight.setText("");
               txtdate.setText("");
               txtphone.setText("");
               txtaddress.setText("");
              txtpno.requestFocus();
               AutoID();
               date();
               patient_table();
        } catch (SQLException ex) {
               Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
                                new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true); 
                }
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
            public void delete(){
                 
       String pno = txtpname.getText();
        try {
            pst = con.prepareStatement("delete from patient where patientname = ?");
           
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Patient Deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtpno.setText("");
               txtpname.setText("");
               txtage.setText("");
               txtgender.setSelectedIndex(0);
               txtid.setText("");
               txtblood.setText("");
               txtweight.setText("");
               //txtdate1.setDateFormatString("");
               txtdate.setText("");
               txtphone.setText("");
               txtaddress.setText("");
              txtpno.requestFocus();
               patient_table();
               AutoID();
               date();
        } catch (SQLException ex) {
                   Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
public void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    txtdate.setText(dtf.format(now).toString());
    txtdate.setEditable(false);
}
  public void AutoID(){                               
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("select MAX(patientno) from patient"); 
            rs.next();
            rs.getString("MAX(patientno)");
            if(rs.getString("MAX(patientno)") == null)
            {
                txtpno.setText("PS001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("MAX(patientno)").substring(2,rs.getString("MAX(patientno)").length()));
                id++;
                txtpno.setText("PS"+String.format("%03d", id)); 
            }
        } catch (SQLException ex) {
             Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }
  public void patient_table(){  
        try {
            pst = con.prepareStatement("SELECT * FROM `patient` ORDER BY i DESC");
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
            v2.add(rs.getString("patientno"));                      
            v2.add(rs.getString("patientname"));
            v2.add(rs.getString("age"));
            v2.add(rs.getString("gender"));
             v2.add(rs.getString("id"));                         
            v2.add(rs.getString("bpressure"));
            v2.add(rs.getString("weight"));
            v2.add(rs.getString("date"));
            v2.add(rs.getString("phone"));
             v2.add(rs.getString("address"));
            }
                df.addRow(v2);
            }
            date();
            
        } catch (SQLException ex) {
                Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtadd = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtgender = new javax.swing.JComboBox<>();
        txtpname = new javax.swing.JTextField();
        txtage = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtblood = new javax.swing.JTextField();
        txtweight = new javax.swing.JTextField();
        txtphone = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtpno = new javax.swing.JLabel();
        searchData = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtpay = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        txterror = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txterr = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        txterr2 = new javax.swing.JLabel();
        txterr3 = new javax.swing.JLabel();
        txterr4 = new javax.swing.JLabel();
        txterr5 = new javax.swing.JLabel();
        txterr6 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient No.");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Patient name");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Address");

        txtadd.setBackground(new java.awt.Color(0, 102, 0));
        txtadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        txtupdate.setBackground(new java.awt.Color(0, 0, 204));
        txtupdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtupdate.setForeground(new java.awt.Color(255, 255, 255));
        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 0, 0));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient No.", "Patient Name", "Age", "Gender", "ID", "B.pressure", "Weight", "Date", "Phone", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable2MouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Age");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gender");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("B.Pressure");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Weight");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Date");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Phone");

        jButton5.setBackground(new java.awt.Color(255, 102, 0));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtgender.setForeground(new java.awt.Color(255, 255, 255));
        txtgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female" }));

        txtpname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpnameKeyReleased(evt);
            }
        });

        txtage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtageKeyReleased(evt);
            }
        });

        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidKeyReleased(evt);
            }
        });

        txtblood.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbloodKeyReleased(evt);
            }
        });

        txtweight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtweightKeyReleased(evt);
            }
        });

        txtphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtphoneKeyReleased(evt);
            }
        });

        txtaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddressActionPerformed(evt);
            }
        });
        txtaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaddressKeyReleased(evt);
            }
        });

        txtpno.setBackground(new java.awt.Color(255, 255, 255));
        txtpno.setForeground(new java.awt.Color(51, 153, 0));

        searchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDataActionPerformed(evt);
            }
        });
        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDataKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchDataKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Date");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Payment");

        txtpay.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpay.setForeground(new java.awt.Color(255, 255, 255));
        txtpay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NHIF", "Mpesa", "Aon", "Cash" }));

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txterror.setForeground(new java.awt.Color(0, 204, 0));
        txterror.setText("Search by Patient name");

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txterr.setForeground(new java.awt.Color(153, 0, 0));

        txterr1.setForeground(new java.awt.Color(153, 0, 0));

        txterr2.setForeground(new java.awt.Color(153, 0, 0));

        txterr3.setForeground(new java.awt.Color(153, 0, 0));

        txterr4.setForeground(new java.awt.Color(153, 0, 0));

        txterr5.setForeground(new java.awt.Color(153, 0, 0));

        txterr6.setForeground(new java.awt.Color(153, 0, 0));

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("A4");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 153, 153));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Termal");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txtdel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtadd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txterr2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(91, 91, 91))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txterr3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(111, 111, 111))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtphone)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(25, 25, 25)
                                                        .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtage)
                                                    .addComponent(txtgender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtid)
                                                    .addComponent(txtblood)
                                                    .addComponent(txtweight)
                                                    .addComponent(txtdate)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtpno, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtpname, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(22, 22, 22)
                                        .addComponent(jButton7)))
                                .addGap(6, 6, 6))))
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtdel))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(txterror, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txterr6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(txterr5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(txterr4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpno, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterror))
                    .addComponent(jButton7))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(5, 5, 5)
                        .addComponent(txterr6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(8, 8, 8)
                        .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtgender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtblood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txterr3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtweight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(3, 3, 3)
                        .addComponent(txtdel)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(txtphone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txterr5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadd)
                    .addComponent(txtupdate)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
    DefaultTableModel d1 = (DefaultTableModel)jTable2.getModel();          
    int SelectIndex = jTable2.getSelectedRow();                
    txtpno.setText(d1.getValueAt(SelectIndex, 0).toString());
    txtpname.setText(d1.getValueAt(SelectIndex, 1).toString());    
    txtage.setText(d1.getValueAt(SelectIndex, 2).toString());
    txtgender.setSelectedItem(d1.getValueAt(SelectIndex, 3).toString());
     txtid.setText(d1.getValueAt(SelectIndex, 4).toString());
    txtblood.setText(d1.getValueAt(SelectIndex, 5).toString());   
    txtweight.setText(d1.getValueAt(SelectIndex, 6).toString());
      txtdate.setText(d1.getValueAt(SelectIndex, 7).toString());
    txtphone.setText(d1.getValueAt(SelectIndex, 8).toString());  
    txtaddress.setText(d1.getValueAt(SelectIndex, 9).toString());
patient_table();
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
  if (validateFields()== true){
            update();
        }
        else{
        }  
    }//GEN-LAST:event_txtupdateActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
  if (validateFields()== true){
            delete();
        }
        else{
        }       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtpno.setText("");
               txtpname.setText("");
               txtage.setText("");
               txtgender.setSelectedIndex(0);
               txtid.setText("");
               txtblood.setText("");
               txtweight.setText("");
               //txtdate1.setDateFormatString("");
               txtdate.setText("");
               txtphone.setText("");
               txtaddress.setText("");
               AutoID();
               date();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void searchDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyReleased

    }//GEN-LAST:event_searchDataKeyReleased

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed
  //String data = searchData.getText();
        try {
           
            String sql = "select * from patient where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("patientno");
               txtpno.setText(pnn);
                String setname = rs.getString("patientname");
                txtpname.setText(setname);
                String age = rs.getString("age");
                txtage.setText(age);
                String gender = rs.getString("gender");
                txtgender.setSelectedItem(gender);
                 String id = rs.getString("id");
                 txtid.setText(id);
                  String bpressure = rs.getString("bpressure");
                 txtblood.setText(bpressure);
 String weight = rs.getString("weight");
                 txtweight.setText(weight);
                  String date = rs.getString("date");
                 txtdate.setText(date);
 String phone = rs.getString("phone");
                 txtphone.setText(phone);
 String address = rs.getString("address");
                 txtaddress.setText(address);
               
            }
              txterror.setText("Search by Patient name");
              txtupdate.setEnabled(true);
        } catch (SQLException ex) {
                   Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_searchDataKeyPressed

    private void searchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDataActionPerformed
       
    }//GEN-LAST:event_searchDataActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
           
            String sql = "select * from patient where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("patientno");
               txtpno.setText(pnn);
                String setname = rs.getString("patientname");
                txtpname.setText(setname);
                String age = rs.getString("age");
                txtage.setText(age);
                String gender = rs.getString("gender");
                txtgender.setSelectedItem(gender);
                 String id = rs.getString("id");
                 txtid.setText(id);
                  String bpressure = rs.getString("bpressure");
                 txtblood.setText(bpressure);
 String weight = rs.getString("weight");
                 txtweight.setText(weight);
                  String date = rs.getString("date");
                 txtdate.setText(date);
 String phone = rs.getString("phone");
                 txtphone.setText(phone);
 String address = rs.getString("address");
                 txtaddress.setText(address);
                  txterror.setText("Patient found");
            }
                 else{
                 txterror.setText("Patient not found");
                   new rojerusan.RSNotifyShadowFade("!ERROR!", "Patient not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                 searchData.setText("");
                 searchData.requestFocus();
                 txterror.setText("Search by patient name");
                 }
            txtupdate.setEnabled(true);
            
        } catch (SQLException ex) {
                 Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
     this.setVisible(false);
                           String no = txtdel.getText();
                           new Patient(no).setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaddressActionPerformed

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseEntered

    private void txtageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtageKeyReleased
        try {
            int i=Integer.parseInt(txtage.getText());
            txterr1.setText("");
        } catch (NumberFormatException evtI) {
            txterr1.setText("Format is invalid");
            txtage.setText("");
        }
    }//GEN-LAST:event_txtageKeyReleased

    private void txtpnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpnameKeyReleased
      txterr.setText("");
    }//GEN-LAST:event_txtpnameKeyReleased

    private void txtidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyReleased
      try {
            int i=Integer.parseInt(txtid.getText());
            txterr2.setText("");
        } catch (NumberFormatException evtI) {
            txterr2.setText("Format is invalid");
            txtid.setText("");
        }
    }//GEN-LAST:event_txtidKeyReleased

    private void txtbloodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbloodKeyReleased

    }//GEN-LAST:event_txtbloodKeyReleased

    private void txtweightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtweightKeyReleased
 try {
            int i=Integer.parseInt(txtweight.getText());
            txterr4.setText("");
        } catch (NumberFormatException evtI) {
            txterr4.setText("Format is invalid");
            txtweight.setText("");
        }
    }//GEN-LAST:event_txtweightKeyReleased

    private void txtphoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphoneKeyReleased
 try {
            int i=Integer.parseInt(txtphone.getText());
            txterr5.setText("");
        } catch (NumberFormatException evtI) {
            txterr5.setText("Format is invalid");
            txtphone.setText("");
        }
    }//GEN-LAST:event_txtphoneKeyReleased

    private void txtaddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaddressKeyReleased
 txterr6.setText("");
    }//GEN-LAST:event_txtaddressKeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
//        String patientname;    
//        patientname = txtpno.getText();
//         try {
//            JasperDesign jdesign = JRXmlLoader.load("C:\\report4.jrxml");
//            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy ON patient.patientno = pharmacy.channelno INNER JOIN sales ON patient.patientno = sales.patientno WHERE patient.patientno = '" + patientname + "'";
//            JRDesignQuery updateQuery = new  JRDesignQuery();
//            updateQuery.setText(query);
//            jdesign.setQuery(updateQuery);
//            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//            JasperPrint jprint = JasperFillManager.fillReport(jreport, new HashMap<>(),con);
//            JasperViewer.viewReport(jprint);
//        } catch (JRException ex) {
//                      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//             JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
JOptionPane.showMessageDialog(this, "Currently not available");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
//                String patientname;    
//        patientname = txtpno.getText();
//         try {
//            JasperDesign jdesign = JRXmlLoader.load("C:\\reception.jrxml");
//            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy ON patient.patientno = pharmacy.channelno INNER JOIN sales ON patient.patientno = sales.patientno WHERE patient.patientno = '" + patientname + "'";
//            JRDesignQuery updateQuery = new  JRDesignQuery();
//            updateQuery.setText(query);
//            jdesign.setQuery(updateQuery);
//            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//            JasperPrint jprint = JasperFillManager.fillReport(jreport, new HashMap<>(),con);
//            JasperViewer.viewReport(jprint);
//        } catch (JRException ex) {
//                      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//             JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
JOptionPane.showMessageDialog(this, "Currently not available");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
     date();
    }//GEN-LAST:event_jPanel1MouseMoved

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
                new Patient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField searchData;
    private javax.swing.JButton txtadd;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtage;
    private javax.swing.JTextField txtblood;
    private javax.swing.JTextField txtdate;
    private javax.swing.JLabel txtdel;
    private javax.swing.JLabel txterr;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterr2;
    private javax.swing.JLabel txterr3;
    private javax.swing.JLabel txterr4;
    private javax.swing.JLabel txterr5;
    private javax.swing.JLabel txterr6;
    private javax.swing.JLabel txterror;
    private javax.swing.JComboBox<String> txtgender;
    private javax.swing.JTextField txtid;
    private javax.swing.JComboBox<String> txtpay;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtpname;
    private javax.swing.JLabel txtpno;
    private javax.swing.JButton txtupdate;
    private javax.swing.JTextField txtweight;
    // End of variables declaration//GEN-END:variables
}
