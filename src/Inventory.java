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

public class Inventory extends javax.swing.JFrame {

     public Inventory(String no) {
        initComponents();
       Connect();
       date();
       sales_table();
       loadDoctor();
       Channel_table();
       lab_table();
         deletebutton(no);
         Strr();
       txtbutton.setText(no);
       txtbutton.setVisible(false);
         txtdoc.setBackground(Color.LIGHT_GRAY);
       txtdoc.setSelectionBackground(Color.YELLOW);
     jTable1.setBackground(Color.LIGHT_GRAY);
       jTable1.setSelectionBackground(Color.YELLOW);
            txttable1.setBackground(Color.LIGHT_GRAY);
       txttable1.setSelectionBackground(Color.YELLOW);
    }

      Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Inventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Connect(){                        //connecting to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
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
public void addition(){
       String pcode = txtname.getSelectedItem().toString();
        try {
            pst = con.prepareStatement("select * from item where itemname = ?");
            pst.setString(1, pcode);
            rs = pst.executeQuery();
            while(rs.next())
            {
                int currentqty;
                int sellingprice;
                int qty;

                currentqty = rs.getInt("qty");
                sellingprice = rs.getInt("sellprice");
                qty = Integer.parseInt(txtqty.getValue().toString());
                                int pay = sellingprice * qty;   
           int tot = (Integer.parseInt(txtqty.getValue().toString()));
           int cost = (Integer.parseInt(txtprice.getText()));
           int bal = tot * cost;
            txttotal.setText(String.valueOf(bal));              
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
          
}
                    public boolean validateFields(){
    boolean b = false;
       if (txtpatient.getText().isEmpty()){
           b=false;
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "Check your fields", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
       }
       else{
    b=true;
    txterr1.setText("");

    } 
       return b;
}
                    public void add(){
                     String patientname;
            patientname = txtchno.getText();
        try {
              String sql = ("SELECT * FROM pharmacy INNER JOIN results ON pharmacy.channelno = results.channelno INNER JOIN sales ON pharmacy.channelno = sales.patientno  WHERE sales.patientno = '" + patientname + "'");
            PreparedStatement pst;
            pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             if (rs.next()) { 
                 int age1 = rs.getInt("cost");
               int age2 = rs.getInt("testcost");
                int age3 = rs.getInt("cost1");
                 int age4 = rs.getInt("cost2");
                  int age5 = rs.getInt("cost3");
                   int age6 = rs.getInt("cost4");
                    int age7 = rs.getInt("total");
                     int age8 = rs.getInt("total1");
                      int age9 = rs.getInt("total2");
                       int age10 = rs.getInt("total3");
                      int pay = age1 + age2 + age3 + age4 + age5 + age6 +age7 + age8+ age9+ age10;
                       t.setText(String.valueOf(pay));
                     }
             else{
        
             }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
                    }
                              public void Channel_table(){       //getting data from table patient and displaying it jTable
        try {
            pst = con.prepareStatement("SELECT * FROM channel WHERE doctorname = 'Pharmacy' ORDER BY i DESC");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)txttable1.getModel();
            df.setRowCount(0);
            while(rs.next()){
            Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) 
                {
            v2.add(rs.getString("channelno"));
            v2.add(rs.getString("doctorname"));
            v2.add(rs.getString("patientname"));
             v2.add(rs.getString("age"));
            v2.add(rs.getString("date"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctorr.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }
                              public void lab_table(){
        try {
            pst = con.prepareStatement("select*from lab WHERE doctorname = 'Pharmacy' ORDER BY i DESC");
            rs = pst.executeQuery();
      ResultSetMetaData Rsm = rs.getMetaData();
      int c;
      c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel)txtdoc.getModel();
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
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
             //JOptionPane.showMessageDialog(this, ex.getMessage());
        }
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
            pst = con.prepareStatement("select * from item ORDER BY i ASC");
           rs = pst.executeQuery();
            txtname.removeAll();
            while(rs.next())
            {
         txtname.addItem(new Doctorr(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void save(){
        String patient1 = txtchno.getText();
                        String patient = txtpatient.getText();
                        String name = txtname.getSelectedItem().toString();
                        String code = txtcode.getText();
                        String date = txtdate.getText();
                        String qt = txtqty.getValue().toString();
                        String sell = txtprice.getText();
                         String tot = txttotal.getText();    
                try {
                              String sql = "select * from sales where patientno = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                 new rojerusan.RSNotifyShadowFade("!WARNING!", "Patient data already exists...update instead", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }
                else{
                            pst = con.prepareStatement("Insert into sales(patientno,patientname,drugname,drugcode,date,qty,sellprice,total)values(?, ?, ?,?,?, ?,?,?)");
             pst.setString(1, patient1);
                            pst.setString(2, patient);
             pst.setString(3, name);
              pst.setString(4,code);
             pst.setString(5, date);
             pst.setString(6, qt);
             pst.setString(7, sell);
             pst.setString(8, tot);
               pst.executeUpdate(); 
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data send successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                date();
                sales_table();
                Channel_table();
                lab_table();
                }

        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
  public void update(){
      String testdes3 = txtpatient.getText(); 
             String testname = txtname.getSelectedItem().toString();
       String testdes = txtcode.getText(); 
        String testno = txtdate.getText();
              String testdes1 = txtqty.getValue().toString(); 
        String testno1 = txtprice.getText();
       String testdes2 = txttotal.getText(); 
               String testdes4 = txtchno.getText(); 
               String bill = t.getText();
                   try {
                              String sql = "select * from sales where patientno = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtchno.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
     try {
            pst = con.prepareStatement("update sales set patientname = ?,drugname = ?,drugcode = ?,date = ?,qty=?,sellprice=?,total=? ,bill= ? where patientno = ?");
            pst.setString(1, testdes3); 
            pst.setString(2, testname);                        
              pst.setString(3, testdes);
                pst.setString(4, testno);
                           pst.setString(5, testdes1);                               
              pst.setString(6, testno1);
                pst.setString(7, testdes2);
                 pst.setString(8, bill);
                  pst.setString(9, testdes4);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Sales updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                 date();
                 sales_table();
                 Channel_table();
                 lab_table();
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
        new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);        
                    
                }
                   } catch (SQLException ex) {
             Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void delete(){
                   String pno = txtpatient.getText();
        try {
            pst = con.prepareStatement("delete from sales where patientname = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Sales Deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
                txtpatient.setText("");
            txtcode.setText("");
                txtprice.setText("");
           txttotal.setText("");
              txtqty.setValue(-1); 
              txtpres.setText("");
              txtremarks.setText("");
              txtdis.setText("");
              txtchno.setText("");
                       date();
                 sales_table();
                 Channel_table();
                 lab_table();
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
public void date()
{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" HH:mm:ss yyyy/MM/dd ");
    LocalDateTime now = LocalDateTime.now();
    txtdate.setText(dtf.format(now).toString());
}
public void sales_table(){ 
        try {
            pst = con.prepareStatement("select*from pharmacy  ORDER BY i DESC");
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
            v2.add(rs.getString("patientname"));
            v2.add(rs.getString("prescription"));
            v2.add(rs.getString("doctorsremarks"));
            v2.add(rs.getString("diseasetype"));
             v2.add(rs.getString("date"));
             v2.add(rs.getString("channelno"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
             //JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtpatient = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        txtqty = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtadd = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchData = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtreceipt = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        txtprice = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        txtdel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtdis = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtpres = new javax.swing.JTextArea();
        txterr1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtremarks = new javax.swing.JTextArea();
        txtupdate = new javax.swing.JButton();
        txtname = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        txtchno = new javax.swing.JLabel();
        t = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtdoc = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txttable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        txtbutton = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
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
        jLabel2.setText("Drug  name");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Drug code");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Qty");

        txtpatient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpatient.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient name", "Prescription", "Remarks", "Diseasetype", "date", "Patient no"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total");

        txtadd.setBackground(new java.awt.Color(0, 204, 0));
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setText("Save");
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date");

        txtdate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Selling price");

        searchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDataActionPerformed(evt);
            }
        });
        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchDataKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchDataKeyTyped(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtreceipt.setBackground(new java.awt.Color(102, 0, 102));
        txtreceipt.setForeground(new java.awt.Color(255, 255, 255));
        txtreceipt.setText("Billing");
        txtreceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtreceiptActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/H.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 0, 102));
        jButton7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Termal");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtprice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtprice.setForeground(new java.awt.Color(255, 255, 255));
        txtprice.setText("0");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/index.gif"))); // NOI18N
        jButton9.setText("Search");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txtdel.setBackground(new java.awt.Color(102, 0, 0));
        txtdel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtdel.setForeground(new java.awt.Color(255, 255, 255));
        txtdel.setText("Delete");
        txtdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdelActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Prescription");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Diseasetype");

        txtpres.setColumns(20);
        txtpres.setRows(5);
        jScrollPane2.setViewportView(txtpres);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Prescription database");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Doctors remarks");

        txtremarks.setColumns(20);
        txtremarks.setRows(5);
        jScrollPane3.setViewportView(txtremarks);

        txtupdate.setBackground(new java.awt.Color(0, 0, 153));
        txtupdate.setForeground(new java.awt.Color(255, 255, 255));
        txtupdate.setText("Update");
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });

        txtname.setMaximumRowCount(80);

        jButton5.setBackground(new java.awt.Color(102, 0, 102));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Other");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtchno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtchno.setForeground(new java.awt.Color(0, 153, 0));

        t.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        t.setForeground(new java.awt.Color(153, 0, 0));
        t.setText("Total");

        txtdoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient no", "doctorname", "patientname", "Date", "Prescription"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        txtdoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdocMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(txtdoc);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(" From Reception");

        txttable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient no", "doctorname", "patientname", "age", "date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        txttable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttable1MouseClicked(evt);
            }
        });
        txttable1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txttable1InputMethodTextChanged(evt);
            }
        });
        jScrollPane5.setViewportView(txttable1);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(" From Doctor");

        jButton10.setBackground(new java.awt.Color(102, 0, 102));
        jButton10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("A4");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(txtbutton))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txterr1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(txttotal))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtreceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtdel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))))
                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addComponent(txtdis, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(12, 12, 12)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel12))
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(txtchno, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtpatient, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtchno, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtdis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton5))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbutton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtreceipt)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtadd)
                                .addComponent(txtupdate))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7)
                                .addComponent(jButton4)
                                .addComponent(jButton3)
                                .addComponent(txtdel)
                                .addComponent(jButton10))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
       if (validateFields()== true){
            save();
        }
        else{
           
        }
       
    }//GEN-LAST:event_txtaddActionPerformed

    private void searchDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyTyped
       try {
            String sql = "select * from sales where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String age = rs.getString("patientname");
                txtpatient.setText(age);    
                 String age1 = rs.getString("drugname");
                txtname.setSelectedItem(age1);  
                 String age2 = rs.getString("drugcode");
                txtcode.setText(age2);  
                 String age4 = rs.getString("sellprice");
                txtprice.setText(age4);  
                 String age5 = rs.getString("total");
                txttotal.setText(age5); 
                  searchData.setText("");
                searchData.requestFocus();
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
             
    }//GEN-LAST:event_searchDataKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                     txtpatient.setText("");
            txtcode.setText("");
                txtprice.setText("");
           txttotal.setText("");
              txtqty.setValue(0); 
              txtpres.setText("");
              txtremarks.setText("");
              txtdis.setText("");
              txtchno.setText("");
                      date();
                 sales_table();
                 Channel_table();
                 lab_table();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    this.setVisible(false);
                           String no = txtbutton.getText();
                           new Inventory(no).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed
 
    private void txtreceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreceiptActionPerformed
        String testname = t.getText();
           String testname1 = txtchno.getText();   
        try {
            pst = con.prepareStatement("update sales set bill = ? where patientno = ?");
             pst.setString(1, testname); 
             pst.setString(2, testname1); 
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!INFORMATION!", "Billing", Color.white, Color.BLUE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true); 
        } catch (SQLException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }     
    }//GEN-LAST:event_txtreceiptActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Connect();
            String patientname;
            patientname = txtchno.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\reception.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy ON patient.patientno = pharmacy.channelno INNER JOIN sales ON patient.patientno = sales.patientno WHERE patient.patientno = '" + patientname + "'";
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
    }//GEN-LAST:event_jButton7ActionPerformed

    private void searchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDataActionPerformed

    }//GEN-LAST:event_searchDataActionPerformed

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed

    }//GEN-LAST:event_searchDataKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
              try {
            String sql = "select * from sales where patientname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String age = rs.getString("patientname");
                txtpatient.setText(age);    
                 String age1 = rs.getString("drugname");
                txtname.setSelectedItem(age1);  
                 String age2 = rs.getString("drugcode");
                txtcode.setText(age2);  
                 String age4 = rs.getString("sellprice");
                txtprice.setText(age4);  
                 String age5 = rs.getString("total");
                txttotal.setText(age5);  
                  searchData.setText("");
                searchData.requestFocus();
            }
                  else{
                searchData.setText("");
                searchData.requestFocus();
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Information not Availaible", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdelActionPerformed

        if (validateFields()== true){
            delete();
                     date();
                 sales_table();
                 Channel_table();
                 lab_table();
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          DefaultTableModel d1 = (DefaultTableModel)jTable1.getModel();
        int SelectIndex = jTable1.getSelectedRow();
        txtpatient.setText(d1.getValueAt(SelectIndex, 0).toString());
         txtpres.setText(d1.getValueAt(SelectIndex, 1).toString());
         txtdis.setText(d1.getValueAt(SelectIndex, 3).toString()); 
         txtremarks.setText(d1.getValueAt(SelectIndex, 2).toString());
         txtchno.setText(d1.getValueAt(SelectIndex, 5).toString());
           JOptionPane.showMessageDialog(this, d1.getValueAt(SelectIndex, 1));
           new rojerusan.RSNotifyShadowFade("!INFORMATION!", (String) d1.getValueAt(SelectIndex, 1), Color.white, Color.BLUE, Color.BLACK, 10
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true); 
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        date();
        String dcode = txtname.getSelectedItem().toString();
            try {
                pst = con.prepareStatement("select * from item where itemname = ?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();
                if(rs.next() == false)
                {
                }
                else{
                String dname = rs.getString("itemno");
                txtcode.setText(dname.trim());
                                String age = rs.getString("sellprice");
                txtprice.setText(age); 
                }
            } catch (SQLException ex) {
                
            } 
            addition();
            add();
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String chno = txtchno.getText();
        String pname = txtpatient.getText();
        try {
            String sql = "select * from sales where patientno = ? and patientname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,chno);
            pst.setString(2,pname);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                String no = txtchno.getText();
                String name = txtpatient.getText();
                new Inventory1(no,name).setVisible(true);
            }
            else{
                new rojerusan.RSNotifyShadowFade("!WARNING!", "Send request first", Color.white, Color.ORANGE, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
            }

        }   catch (SQLException ex) {
            Logger.getLogger(Doctorr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtdocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdocMouseClicked

        DefaultTableModel d1 = (DefaultTableModel)txtdoc.getModel();
        int SelectIndex = txtdoc.getSelectedRow();
        txtchno.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());  
  txtpres.setText(d1.getValueAt(SelectIndex, 4).toString());
           new rojerusan.RSNotifyShadowFade("!INFORMATION!", d1.getValueAt(SelectIndex, 4).toString(), Color.white, Color.BLUE, Color.BLACK, 10
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.INFORMATION).setVisible(true);
                 date();
                 sales_table();
                 Channel_table();
                 lab_table();
    }//GEN-LAST:event_txtdocMouseClicked

    private void txttable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttable1MouseClicked
             DefaultTableModel d1 = (DefaultTableModel)txttable1.getModel();
        int SelectIndex = txttable1.getSelectedRow();
        txtchno.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtpatient.setText(d1.getValueAt(SelectIndex, 2).toString());
                 date();
                 sales_table();
                 Channel_table();
                 lab_table(); 
    }//GEN-LAST:event_txttable1MouseClicked

    private void txttable1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txttable1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txttable1InputMethodTextChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
                 String patientname;
            patientname = txtchno.getText();
         try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Brian\\Documents\\NetBeansProjects\\Jss\\src\\report4.jrxml");
            String query = "SELECT * FROM patient INNER JOIN results ON patient.patientno = results.channelno INNER JOIN pharmacy ON patient.patientno = pharmacy.channelno INNER JOIN sales ON patient.patientno = sales.patientno WHERE patient.patientno = '" + patientname + "'";
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

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (validateFields()== true){
            String no = txtchno.getText();
            String name = txtpatient.getText();
            String button = txtbutton.getText();
            new Accounts(no,name,button).setVisible(true);
        }
        else{
        }
    }//GEN-LAST:event_jButton11ActionPerformed
public void Strr(){
 TimerTask timerTask = new TimerTask() {
       @Override
       public void run() {
          lab_table();
          sales_table();
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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchData;
    private javax.swing.JLabel t;
    private javax.swing.JButton txtadd;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JLabel txtchno;
    private javax.swing.JTextField txtcode;
    private javax.swing.JLabel txtdate;
    private javax.swing.JButton txtdel;
    private javax.swing.JTextField txtdis;
    private javax.swing.JTable txtdoc;
    private javax.swing.JLabel txterr1;
    private javax.swing.JComboBox txtname;
    private javax.swing.JLabel txtpatient;
    private javax.swing.JTextArea txtpres;
    private javax.swing.JLabel txtprice;
    private javax.swing.JSpinner txtqty;
    private javax.swing.JButton txtreceipt;
    private javax.swing.JTextArea txtremarks;
    private javax.swing.JTable txttable1;
    private javax.swing.JTextField txttotal;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
