
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


public class Lab extends javax.swing.JFrame {

    public Lab(String no) {
        initComponents();
        date();
        Connect();
        lab_table();
        loadDoctor();
        Channel_table();
        Strr();
        Channel_table1();
                txtrequest.setEditable(false);
                txtcost.setEditable(false);
                  deletebutton(no);
        txtbutton.setText(no);
        txtbutton.setVisible(false);
             txttable.setBackground(Color.LIGHT_GRAY);
       txttable.setSelectionBackground(Color.YELLOW);
            jTable1.setBackground(Color.LIGHT_GRAY);
       jTable1.setSelectionBackground(Color.YELLOW);
    }
    Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Lab() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            public void deletebutton(String no){
              if (no == "Admin") {
                 txtdel.setEnabled(true);
        } else {
                    txtdel.setEnabled(false);
        }
    }
     public void Channel_table(){
         try {
            pst = con.prepareStatement("select*from diagnosis where doctorname= 'Lab' ORDER BY i DESC");
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
            v2.add(rs.getString("channelno_lab"));
            v2.add(rs.getString("doctorname"));
            v2.add(rs.getString("patientname"));
            v2.add(rs.getString("date"));
            v2.add(rs.getString("description"));
                       v2.add(rs.getString("labrequest"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             
        }
  }
    public void save(){
        String chno = txtchannel.getText();
                        String doc = txtdoc.getText();
                        String pname = txtpatient.getText();
                        String date = txtdate.getText();
                        String type = txtdoc2.getSelectedItem().toString();
                        String cost = txtcost.getText();
                         String area = txtarea.getText();    
                try {
                              String sql = "select * from results where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                   new rojerusan.RSNotifyShadowFade("!ERROR!", "Patient data already exists...update", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
            }
                else{
                       pst = con.prepareStatement("Insert into results(channelno,doctorname,patientname,date,testtype,testcost,labresults)values(?, ?, ?,?,?, ?,?)");
            pst.setString(1, chno);
             pst.setString(2, doc);
              pst.setString(3,pname);
             pst.setString(4, date);
             pst.setString(5, type);
             pst.setString(6, cost);
             pst.setString(7, area);
               pst.executeUpdate(); 
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Results send successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               lab_table();
Channel_table();
           date();
                }     
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
     
    }
       public void update(){
            String chno = txtchannel.getText();
                        String doc = txtdoc.getText();
                        String pname = txtpatient.getText();
                        String date = txtdate.getText();
                        String type = txtdoc2.getSelectedItem().toString();
                        String cost = txtcost.getText();
                         String area = txtarea.getText();    
                try {
                              String sql = "select * from results where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == false) {
                   new rojerusan.RSNotifyShadowFade("!ERROR!", "No data to be updated", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
            }
                else{
                       pst = con.prepareStatement("Insert into results_1(channelno,doctorname,patientname,date,testtype,testcost,labresults)values(?, ?, ?,?,?, ?,?)");
            pst.setString(1, chno);
             pst.setString(2, doc);
              pst.setString(3,pname);
             pst.setString(4, date);
             pst.setString(5, type);
             pst.setString(6, cost);
             pst.setString(7, area);
               pst.executeUpdate(); 
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Results send successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               lab_table();
Channel_table();
           date();
                }     
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }                 
    }
          public void delete(){
              String pno = txtpatient.getText();
        try {
            pst = con.prepareStatement("delete from results where patientname = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Lab request deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                txtchannel.setText("");
               txtchannel.setText("");
           txtdoc.setText("");
           txtpatient.setText("");
           txtdate.setText("");
           txtrequest.setText("");
           txtarea.setText("");
           txtdoc2.setSelectedItem(-1);
           txtcost.setText("");
                         lab_table();
Channel_table();
date();
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } 
    }
          public class Doctor1{
    String id;
    String name;
    public Doctor1(String id,String name){
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
            pst = con.prepareStatement("select * from test ORDER BY i ASC");
           rs = pst.executeQuery();
            txtdoc2.removeAll();
            while(rs.next())
            {
         txtdoc2.addItem(new Doctor1(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
             public boolean validateFields(){
    boolean b = false;
       if (txtarea.getText().isEmpty()){
           b=false;
txterr1.setText("Field is empty");
       }
       
       else if (txtpatient.getText().isEmpty()){
           b=false;
txterr2.setText("Patient is empty");
       }
              else if (txtcost.getText().isEmpty()){
           b=false;
JOptionPane.showMessageDialog(this, "Cost is empty");
       }
       else{
    b=true;
    txterr1.setText("");
        txterr2.setText("");
    } 
       return b;
}
                          public boolean validateFieldss(){
    boolean b = false;
       if (txtpatient.getText().isEmpty()){
           b=false;
txterr2.setText("Field is empty");
       }
       else{
    b=true;
        txterr2.setText("");
    } 
       return b;
}
public void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("  HH:mm:ss yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    txtdate.setText(dtf.format(now).toString());
}
public void info(){
            try {
           
            String sql = "select * from results where channelno = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
   
                                    String report = rs.getString("testtype");
               txtdoc2.addItem(report);
                                    String report1 = rs.getString("testcost");
                txtcost.setText(report1);
                                      String report2 = rs.getString("labresults");
                txtarea.setText(report2);
        
            }
            
                 else{
           
                new rojerusan.RSNotifyShadowFade("!ERROR!", "Information not available", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    
}
public void report(){
    try {
                              String sql = "select * from results_1 where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
     String patientname;
            patientname = txtchannel.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report2.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results_1 ON patient.patientno = results_1.channelno WHERE results_1.i = (select MAX(i) from results_1 where results_1.channelno = '" + patientname + "') AND patient.patientno = '" + patientname + "'";
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
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report2.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno WHERE patient.patientno = '" + patientname + "'";
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
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }      
}
public void urina(){
        try {
                              String sql = "select * from results_1 where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
     String patientname;
            patientname = txtchannel.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report10.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results_1 ON patient.patientno = results_1.channelno WHERE patient.patientno = '" + patientname + "' AND results_1.i = (select MAX(i) from results_1 where results_1.channelno = '" + patientname + "')";
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
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report10.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno WHERE patient.patientno = '" + patientname + "'";
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
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }   
}
public void lab_table(){  
        try {
            pst = con.prepareStatement("select*from lab where doctorname= 'Lab' ORDER BY i DESC");
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
            v2.add(rs.getString("description"));
                       v2.add(rs.getString("labrequest"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             
        }
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtdoc1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Patientname = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtdoc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        txtadd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        searchData = new javax.swing.JTextField();
        txtpatient = new javax.swing.JLabel();
        txtchannel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtrequest = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txtdel = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txterr = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        txterr2 = new javax.swing.JLabel();
        txtcost = new javax.swing.JTextField();
        txtdoc2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtbutton = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txttable = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lab report form", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient no");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Doctor name");

        Patientname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Patientname.setForeground(new java.awt.Color(255, 255, 255));
        Patientname.setText("Patient name");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lab report");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        txtdoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtdoc.setForeground(new java.awt.Color(255, 255, 255));

        txtarea.setColumns(20);
        txtarea.setRows(5);
        txtarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtareaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtarea);

        txtadd.setBackground(new java.awt.Color(0, 204, 0));
        txtadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Channel no", "Doctor name", "Patient name", "date", "description", "labrequest"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        jScrollPane2.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(204, 51, 0));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDataKeyPressed(evt);
            }
        });

        txtpatient.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtpatient.setForeground(new java.awt.Color(255, 255, 255));

        txtchannel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtchannel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date");

        txtdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lab request");

        txtrequest.setColumns(20);
        txtrequest.setRows(5);
        jScrollPane3.setViewportView(txtrequest);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Test type");

        txtdel.setBackground(new java.awt.Color(102, 0, 0));
        txtdel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));
        txtdel.setText("Delete");
        txtdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdelActionPerformed(evt);
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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txterr.setForeground(new java.awt.Color(0, 153, 0));
        txterr.setText("Search patient name");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Test requests");

        txterr1.setForeground(new java.awt.Color(153, 0, 0));

        txterr2.setForeground(new java.awt.Color(153, 0, 0));

        txtdoc2.setMaximumRowCount(80);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Test cost");

        jButton1.setBackground(new java.awt.Color(102, 0, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Other");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 0, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Other");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("A4");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 153, 153));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Termal");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtbutton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
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

        jButton9.setBackground(new java.awt.Color(153, 153, 0));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Info");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Channel no", "Doctor name", "Patient name", "Date", "Description", "labrequest"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        txttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(txttable);
        if (txttable.getColumnModel().getColumnCount() > 0) {
            txttable.getColumnModel().getColumn(4).setHeaderValue("Description");
            txttable.getColumnModel().getColumn(5).setHeaderValue("labrequest");
        }

        jButton10.setBackground(new java.awt.Color(255, 255, 0));
        jButton10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Info");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Channel no", "Doctor name", "Patient name", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(Patientname)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(23, 23, 23)
                                        .addComponent(jButton4))
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(txtbutton))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(62, 62, 62)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdate))
                                .addGap(125, 125, 125))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtchannel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtdel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtdoc2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtpatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txterr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtcost)
                                    .addComponent(jScrollPane1))
                                .addGap(6, 6, 6))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txterr2)
                        .addGap(178, 178, 178)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(txterr, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(11, 11, 11))
                        .addComponent(txtchannel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(txtdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Patientname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txterr2)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtdate))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtbutton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jButton1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtdoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txterr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtadd)
                        .addComponent(txtupdate))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdel)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton6)
                        .addComponent(jButton11)
                        .addComponent(jButton9)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           txtchannel.setText("");
           txtdoc.setText("");
           txtpatient.setText("");
           txtdate.setText("");
           txtrequest.setText("");
           txtarea.setText("");
              txtdoc2.setSelectedItem("");
           txtcost.setText("");
                         lab_table();
Channel_table();
date();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed
            try {
            String sql = "select * from results where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("channelno");
               txtchannel.setText(pnn);
                String setname = rs.getString("doctorname");
                txtdoc.setText(setname);
                String age = rs.getString("patientname");
                txtpatient.setText(age);  
                                    String report = rs.getString("testtype");
               txtdoc2.setSelectedItem(report); 
                                    String report1 = rs.getString("testcost");
                txtcost.setText(report1);
                                      String report2 = rs.getString("labresults");
                txtarea.setText(report2);
            }
            
                 else{
                 txterr.setText("Patient not found");
                  txterr.setText("Search by patient name");
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_searchDataKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();           //mouse click action performance
        int SelectIndex = jTable1.getSelectedRow();//Editing patient information from the table
        txtchannel.setText(d1.getValueAt(SelectIndex, 0).toString());
         txtdoc.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());  
         txtrequest.setText(d1.getValueAt(SelectIndex, 5).toString()); 
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
        
       if (validateFields()== true){
            save();
        }
        else{
        }                  
    }//GEN-LAST:event_txtaddActionPerformed

    private void txtdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdelActionPerformed
        
       if (validateFields()== true){
            delete();
        }
        else{
        }  
    }//GEN-LAST:event_txtdelActionPerformed

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
       
       if (validateFields()== true){
            update();
        }
        else{

        }  
    }//GEN-LAST:event_txtupdateActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
   this.setVisible(false);
                           String no = txtbutton.getText();
                           new Lab(no).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
                try {
           
            String sql = "select * from results where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("channelno");
               txtchannel.setText(pnn);
                String setname = rs.getString("doctorname");
                txtdoc.setText(setname);
                String age = rs.getString("patientname");
                txtpatient.setText(age);  
                                    String report = rs.getString("testtype");
               txtdoc2.setSelectedItem(report); 
                                    String report1 = rs.getString("testcost");
                txtcost.setText(report1);
                                      String report2 = rs.getString("labresults");
                txtarea.setText(report2);
                txtupdate.setEnabled(true);
                txtdel.setEnabled(true);
                txtadd.setEnabled(false);
            }
            
                 else{
                 searchData.setText("");
                 searchData.requestFocus();
                 txterr.setText("Patient not found");
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Patient not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                  txterr.setText("Search by patient name");
                 }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
date();
        String dcode = txtdoc2.getSelectedItem().toString();
            try {
                pst = con.prepareStatement("select * from test where testname = ?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();
                if(rs.next() == false)
                {
                  
                }
                else{
                String dname = rs.getString("charges");
                txtcost.setText(dname.trim());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } 
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

                   if (validateFieldss()== true){
                    String no = txtchannel.getText();
               String name = txtpatient.getText();
new Lab1(no,name).setVisible(true);
        }
        else{

        }                                                           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      String chno = txtchannel.getText();
                String pname = txtpatient.getText();
              try {
           String sql = "select * from results where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,chno);
            pst.setString(2,pname);
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true && validateFieldss()== true ) {                  
                    String no = txtchannel.getText();
               String name = txtpatient.getText();
new Lab2(no,name).setVisible(true);
            }
                else{
                new rojerusan.RSNotifyShadowFade("!WARNING!", "Save request first", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
    }                                        

    }   catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
String usertype = txtdoc2.getSelectedItem().toString();
switch(usertype){
    case "Urinalysis":
        urina();
        break;
        case "Stool analysis":
            stool();
        break;
        default:
            report();
            break;
    
}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
try {
                              String sql = "select * from results_1 where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
     String patientname;
            patientname = txtchannel.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report16.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results_1 ON patient.patientno = results_1.channelno WHERE results_1.i = (select MAX(i) from results_1 where results_1.channelno = '" + patientname + "') AND patient.patientno = '" + patientname + "'";
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
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report16.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno WHERE patient.patientno = '" + patientname + "'";
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
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }      
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

    }//GEN-LAST:event_jPanel1MouseDragged

    private void txtareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtareaMouseClicked
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
                String dname = rs.getString("des");
                txtarea.setText(dname.trim());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                
            }   
    }//GEN-LAST:event_txtareaMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
          try {
                  String sql = "select * from results_1 where channelno = ? and patientname = ? ";
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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String chno = txtchannel.getText();
                String pname = txtpatient.getText();
              try {
           String sql = "select * from results where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,chno);
            pst.setString(2,pname);
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true && validateFieldss()== true ) {                  
                    String no = txtchannel.getText();
               String name = txtpatient.getText();
new Lab3(no,name).setVisible(true);
            }
                else{
                            new rojerusan.RSNotifyShadowFade("!ERROR!", "Information not available", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
    }                                        

    }   catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttableMouseClicked
 DefaultTableModel d1 = (DefaultTableModel)txttable.getModel();           //mouse click action performance
        int SelectIndex = txttable.getSelectedRow();//Editing patient information from the table
        txtchannel.setText(d1.getValueAt(SelectIndex, 0).toString());
         txtdoc.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());  
         txtrequest.setText(d1.getValueAt(SelectIndex, 5).toString());
    }//GEN-LAST:event_txttableMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
              String patientname;
            patientname = txtchannel.getText();     
        try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report14.jrxml");
            String query = "SELECT * FROM results_1 WHERE channelno = '" + patientname + "' UNION all SELECT * from results WHERE channelno = '" + patientname + "' ORDER BY date DESC";
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
    }//GEN-LAST:event_jButton10ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel d1 = (DefaultTableModel)table.getModel();           
        int SelectIndex = table.getSelectedRow();
        txtchannel.setText(d1.getValueAt(SelectIndex, 0).toString());
         txtdoc.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());  
    }//GEN-LAST:event_tableMouseClicked
public void Strr(){
 TimerTask timerTask = new TimerTask() {
       @Override
       public void run() {
        lab_table();
        Channel_table();
        Channel_table1();
       }
   };
 Timer timer = new Timer("");
 timer.scheduleAtFixedRate(timerTask, 0, 8000);
}
public void stool(){
        try {
                              String sql = "select * from results_1 where channelno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchannel.getText());
            pst.setString(2, txtpatient.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
     String patientname;
            patientname = txtchannel.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report17.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results_1 ON patient.patientno = results_1.channelno WHERE patient.patientno = '" + patientname + "' AND results_1.i = (select MAX(i) from results_1 where results_1.channelno = '" + patientname + "')";
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
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report17.jrxml");
            String query =  "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno WHERE patient.patientno = '" + patientname + "'";
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
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }   
}
    public void Channel_table1(){
         try {
            pst = con.prepareStatement("select * from channel where doctorname= 'Lab attendant' ORDER BY i DESC");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)table.getModel();
            df.setRowCount(0);
            while(rs.next()){
            Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) 
                {
            v2.add(rs.getString("channelno"));
            v2.add(rs.getString("doctorname"));
            v2.add(rs.getString("patientname"));
            v2.add(rs.getString("date"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             
        }
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
            java.util.logging.Logger.getLogger(Lab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lab().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Patientname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchData;
    private javax.swing.JTable table;
    private javax.swing.JButton txtadd;
    private javax.swing.JTextArea txtarea;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JLabel txtchannel;
    private javax.swing.JTextField txtcost;
    private javax.swing.JLabel txtdate;
    private javax.swing.JButton txtdel;
    private javax.swing.JLabel txtdoc;
    private javax.swing.JComboBox txtdoc1;
    private javax.swing.JComboBox txtdoc2;
    private javax.swing.JLabel txterr;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterr2;
    private javax.swing.JLabel txtpatient;
    private javax.swing.JTextArea txtrequest;
    private javax.swing.JTable txttable;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
