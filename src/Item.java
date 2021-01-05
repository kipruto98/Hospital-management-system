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


public class Item extends javax.swing.JFrame {


    public Item(String no) {
        initComponents();
        Connect();
        AutoID();
        item_table();
          deletebutton(no);
        txtbutton.setText(no);
        txtbutton.setVisible(false);
    }
    Connection con; 
    PreparedStatement pst;
    ResultSet rs;

    Item() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Connect(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
  con = DriverManager.getConnection("jdbc:mysql://localhost/mfcc", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
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
txterr1.setText("name is empty");
txtname.requestFocus();
       }
       
       else if (txtdes.getText().isEmpty()){
           b=false;
txterr2.setText("Description is empty");
txtdes.requestFocus();
       }
              else if (txtsellprice.getText().isEmpty()){
           b=false;
txterr3.setText("Sell price is empty");
txtsellprice.requestFocus();
       }
                else if (txtbuyprice.getText().isEmpty()){
           b=false;
txterr4.setText("Buyprice is empty");
txtbuyprice.requestFocus();
       }  
                       else if (txtqty.getText().isEmpty()){
           b=false;
txterr5.setText("Quantity is empty");
txtqty.requestFocus();
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
    public void save(){
          String itemno = lblItemid.getText();
       String itemname = txtname.getText();
       String itemdes = txtdes.getText(); 
          String sellprice = txtsellprice.getText();
       String buyprice = txtbuyprice.getText();
       String itemqty = txtqty.getText(); 
       
        try {
                      String sql = "select * from item where itemno = ? and itemname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lblItemid.getText());
            pst.setString(2, txtname.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
       new rojerusan.RSNotifyShadowFade("!ERROR!", "Item data already exists", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
            }
                else{
                            pst = con.prepareStatement("Insert into item(itemno,itemname,description,sellprice,buyprice,qty)values(?, ?,?, ?,?,?)");
            pst.setString(1, itemno);
             pst.setString(2, itemname);
              pst.setString(3, itemdes);
               pst.setString(4, sellprice);
                pst.setString(5, buyprice);
               pst.setString(6, itemqty);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Item added successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtname.setText("");
               txtdes.setText("");
               txtsellprice.setText("");
               txtbuyprice.setText("");
               txtqty.setText("");
               txtname.requestFocus();
               item_table();
                }
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
        public void update(){
           String itemname = txtname.getText();
       String itemdes = txtdes.getText(); 
          String sellprice = txtsellprice.getText();
       String buyprice = txtbuyprice.getText();
       String itemqty = txtqty.getText(); 
        String itemno = lblItemid.getText(); 
        
        try {
                      String sql = "select * from item where itemno = ? and itemname = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, lblItemid.getText());
            pst.setString(2, txtname.getText());
            ResultSet rs = pst.executeQuery();
                if (rs.next() == true) {
                      try {
            pst = con.prepareStatement("update item set itemname = ?, description = ?, sellprice = ?, buyprice = ?, qty = ? where itemno = ?");
             pst.setString(1, itemname);                                
              pst.setString(2, itemdes);
               pst.setString(3, sellprice);
                pst.setString(4, buyprice);
               pst.setString(5, itemqty);
                pst.setString(6, itemno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Item updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtname.setText("");
               txtdes.setText("");
               txtsellprice.setText("");
               txtbuyprice.setText("");
               txtqty.setText("");
               txtname.requestFocus();
               item_table();
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            }
                else{
                        new rojerusan.RSNotifyShadowFade("!ERROR!", "No data to be updated", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);   
                }
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
            public void delete(){
           String pno = lblItemid.getText();
        try {
            pst = con.prepareStatement("delete from item where itemno = ?");
                pst.setString(1, pno);
               pst.executeUpdate();
               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Item Deleted successfully", Color.white, Color.GREEN, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
               AutoID();
               txtname.setText("");
               txtdes.setText("");
               txtsellprice.setText("");
               txtbuyprice.setText("");
               txtqty.setText("");
              txtname.requestFocus();
               item_table();
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
  public void AutoID(){                               
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery("select MAX(itemno) from item"); 
            rs.next();
            rs.getString("MAX(itemno)");
            if(rs.getString("MAX(itemno)") == null)
            {
                lblItemid.setText("IU001");
            }
            else
            {
                long id = Long.parseLong(rs.getString("MAX(itemno)").substring(2,rs.getString("MAX(itemno)").length()));
                id++;
                lblItemid.setText("IU"+String.format("%03d", id)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  }
  public void item_table(){    
        try {
            pst = con.prepareStatement("select*from item");
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
            v2.add(rs.getString("itemno"));                      
            v2.add(rs.getString("itemname"));
            v2.add(rs.getString("description"));
            v2.add(rs.getString("sellprice"));
             v2.add(rs.getString("buyprice"));
            v2.add(rs.getString("qty"));
            }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
  
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtdes = new javax.swing.JTextField();
        txtadd = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        txtdelete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lblItemid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsellprice = new javax.swing.JTextField();
        txtbuyprice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        searchData = new javax.swing.JTextField();
        txterror = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txterr1 = new javax.swing.JLabel();
        txterr2 = new javax.swing.JLabel();
        txterr3 = new javax.swing.JLabel();
        txterr4 = new javax.swing.JLabel();
        txterr5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Item No.");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Item name");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Description");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnameKeyTyped(evt);
            }
        });

        txtdes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdesKeyReleased(evt);
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item no", "Item name", "Description", "Sell price", "Buy price", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        lblItemid.setText("No");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sell price");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Buy price");

        txtsellprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsellpriceKeyReleased(evt);
            }
        });

        txtbuyprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuypriceActionPerformed(evt);
            }
        });
        txtbuyprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuypriceKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Qty");

        txtqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtqtyKeyReleased(evt);
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

        txterror.setForeground(new java.awt.Color(0, 153, 0));
        txterror.setText("Search by item name");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Item database");

        txterr1.setForeground(new java.awt.Color(204, 0, 0));

        txterr2.setForeground(new java.awt.Color(204, 0, 0));

        txterr3.setForeground(new java.awt.Color(204, 0, 0));

        txterr4.setForeground(new java.awt.Color(204, 0, 0));

        txterr5.setForeground(new java.awt.Color(204, 0, 0));

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Exit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblItemid, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtqty, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtbuyprice, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtsellprice, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtdes, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtname))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(txterr5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(txterr4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(txterr3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(txterr2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(txterr1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtupdate)
                        .addGap(35, 35, 35)
                        .addComponent(txtdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton5)
                        .addGap(35, 35, 35)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 264, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(lblItemid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterror)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(txterr2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtsellprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(txterr3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(txtbuyprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(txterr4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(txtbutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txterr5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadd)
                    .addComponent(txtupdate)
                    .addComponent(txtdelete)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6))
                .addGap(40, 40, 40))
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
    lblItemid.setText(d1.getValueAt(SelectIndex, 0).toString());
    txtname.setText(d1.getValueAt(SelectIndex, 1).toString());          
    txtdes.setText(d1.getValueAt(SelectIndex, 2).toString());
    txtsellprice.setText(d1.getValueAt(SelectIndex, 3).toString());
     txtbuyprice.setText(d1.getValueAt(SelectIndex, 4).toString());
    txtqty.setText(d1.getValueAt(SelectIndex, 5).toString());                                      
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed

             
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

    private void txtbuypriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuypriceActionPerformed

    }//GEN-LAST:event_txtbuypriceActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    this.setVisible(false);
                           String no = txtbutton.getText();
                           new Item(no).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String sql = "select * from item where itemname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("itemno");
                lblItemid.setText(pnn);
                String setname = rs.getString("itemname");
                txtname.setText(setname);
                String age = rs.getString("description");
                txtdes.setText(age);
                String report = rs.getString("sellprice");
                txtsellprice.setText(report);
                 String buy = rs.getString("buyprice");
                txtbuyprice.setText(buy);
                 String q = rs.getString("qty");
                txtqty.setText(report);
            }

            else{
                searchData.setText("");
                searchData.requestFocus();
                txterror.setText("Item not found");
                  new rojerusan.RSNotifyShadowFade("!ERROR!", "Item not found", Color.white, Color.RED, Color.BLACK, 6
,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true);
                txterror.setText("Search by item name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void searchDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyPressed
 try {
            String sql = "select * from item where itemname = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, searchData.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String pnn = rs.getString("itemno");
                lblItemid.setText(pnn);
                String setname = rs.getString("itemname");
                txtname.setText(setname);
                String age = rs.getString("description");
                txtdes.setText(age);
                String report = rs.getString("sellprice");
                txtsellprice.setText(report);
                 String buy = rs.getString("buyprice");
                txtbuyprice.setText(buy);
                 String q = rs.getString("qty");
                txtqty.setText(q);
            }

            else{
                searchData.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_searchDataKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
                  
               txtname.setText("");
               txtdes.setText("");
               txtsellprice.setText("");
               txtbuyprice.setText("");
               txtqty.setText("");
               txtname.requestFocus(); 
                 AutoID();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyTyped
 txterr1.setText("");
    }//GEN-LAST:event_txtnameKeyTyped

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased

    }//GEN-LAST:event_txtnameKeyReleased

    private void txtdesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyReleased
txterr2.setText("");
    }//GEN-LAST:event_txtdesKeyReleased

    private void txtsellpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsellpriceKeyReleased
      try {
            int i=Integer.parseInt(txtsellprice.getText());
            txterr3.setText("");
        } catch (NumberFormatException evtI) {
            txterr3.setText("Format is invalid");
            txtsellprice.setText("");
        }
    }//GEN-LAST:event_txtsellpriceKeyReleased

    private void txtbuypriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuypriceKeyReleased
             try {
            int i=Integer.parseInt(txtbuyprice.getText());
            txterr4.setText("");
        } catch (NumberFormatException evtI) {
            txterr4.setText("Format is invalid");
            txtbuyprice.setText("");
        }
    }//GEN-LAST:event_txtbuypriceKeyReleased

    private void txtqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyReleased
      try {
            int i=Integer.parseInt(txtqty.getText());
            txterr5.setText("");
        } catch (NumberFormatException evtI) {
            txterr5.setText("Format is invalid");
            txtqty.setText("");
        }
    }//GEN-LAST:event_txtqtyKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

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
                new Item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblItemid;
    private javax.swing.JTextField searchData;
    private javax.swing.JButton txtadd;
    private javax.swing.JLabel txtbutton;
    private javax.swing.JTextField txtbuyprice;
    private javax.swing.JButton txtdelete;
    private javax.swing.JTextField txtdes;
    private javax.swing.JLabel txterr1;
    private javax.swing.JLabel txterr2;
    private javax.swing.JLabel txterr3;
    private javax.swing.JLabel txterr4;
    private javax.swing.JLabel txterr5;
    private javax.swing.JLabel txterror;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsellprice;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
