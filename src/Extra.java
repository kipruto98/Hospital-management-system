
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rojerusan.RSNotifyShadowFade;


public class Extra {
    public void update(){
//    String doc = txtdoc1.getSelectedItem().toString();
//                String pname = txtpatient.getText();
//       int age = (Integer.parseInt(txtage.getText()));
//                         String pressure = txtblood.getText();
//         String date = txtdate.getText();
//                         String des = txtdescription.getText();
//                         String lab = txtdoc2.getSelectedItem().toString();
//                         String rx1 = txtrx.getText();
//                         String conss = cons.getText();
//                          String chno = txtchno.getText(); 
//                           try {
//           String sql = "select * from lab where channelno = ? and patientname = ? ";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, txtchno.getText());
//            pst.setString(2, txtpatient.getText());
//            ResultSet rs = pst.executeQuery();
//                if (rs.next() == true) {
// try {
//            pst = con.prepareStatement("update lab set doctorname = ?, patientname = ?, age= ?, bpressure=?, date = ?,description=?,labrequest =?, rx = ?, cons = ? where channelno = ?");
//             pst.setString(1, doc);
//              pst.setString(2,pname);
//             pst.setInt(3, age);
//             pst.setString(4, pressure);
//             pst.setString(5, date);
//             pst.setString(6, des);
//             pst.setString(7, lab);
//             pst.setString(8, rx1);
//             pst.setString(9, conss);
//              pst.setString(10, chno);
//               pst.executeUpdate();
//               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Request updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
//               Channel_table();
//               date();
//               loadDoctor();
//               txtdescription.requestFocus();
//               
//        } catch (SQLException ex) {
//            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
//            }
//                else{
//         new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.ORANGE, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);
//                }
//                           } catch (SQLException ex) {
//            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
//        } 
}
}

//public void tableChanged(TableModelEvent e){
//    DefaultTableModel model = (DefaultTableModel)e.getSource();
//int row = e.getFirstRow();
//int column = e.getColumn();
//String columnName = model.getColumnName(column);
//Object data = model.getValueAt(row, column);
//JOptionPane.showMessageDialog(this,"Hey");
//}

//String pname = txtpatient.getText();     
//                  String date = txtdate1.getText();
//                  String type = txtpres.getText();
//                  String area = txtarea.getText();
//                                       String disease = txtdisease.getText();
//                         String cost = txtcost.getText();
//                   String chno = txtchannel.getText();
//           try {
//                              String sql = "select * from pharmacy where channelno = ? and patientname = ? ";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, txtchannel.getText());
//            pst.setString(2, txtpatient.getText());
//            ResultSet rs = pst.executeQuery();
//                if (rs.next() == ) {
//                        try {
//            pst = con.prepareStatement("update pharmacy set patientname = ?, date= ?, prescription=?, doctorsremarks = ? ,diseasetype = ?,cost = ? where channelno = ?");
//             pst.setString(1, pname);
//              pst.setString(2,date);
//             pst.setString(3, type);
//             pst.setString(4, area);
//             pst.setString(5, disease);
//             pst.setString(6, cost);
//              pst.setString(7, chno);
//               pst.executeUpdate();
//               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Data updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
//                       results_table();
//        date();
//               
//        } catch (SQLException ex) {
//                          Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
//             JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
//            }
//                else{
//                   new rojerusan.RSNotifyShadowFade("!WARNING!", "No data to be updated", Color.white, Color.ORANGE, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.WARNING).setVisible(true);       
//                }
//           } catch (SQLException ex) {
//            Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
//        }


//String doc = txtdoc.getText();
//                  String pname = txtpatient.getText();     
//                  String date = txtdate.getText();
//                  String type = txtdoc2.getSelectedItem().toString();
//                  String cost = txtcost.getText();
//                  String area = txtarea.getText();
//                   String chno = txtchannel.getText();
//                try {
//                              String sql = "select * from results where channelno = ? and patientname = ? ";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, txtchannel.getText());
//            pst.setString(2, txtpatient.getText());
//            ResultSet rs = pst.executeQuery();
//                if (rs.next() == true) {
//                              try {
//            pst = con.prepareStatement("update results set doctorname = ?, patientname = ?, date= ?, testtype=?, testcost = ?,labresults =? where channelno = ?");
//            pst.setString(1, doc);
//             pst.setString(2, pname);
//              pst.setString(3,date);
//             pst.setString(4, type);
//             pst.setString(5, cost);
//             pst.setString(6, area);
//              pst.setString(7, chno);
//               pst.executeUpdate();
//               new rojerusan.RSNotifyShadowFade("!SUCCESS!", "Request updated successfully", Color.white, Color.GREEN, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.SUCCESS).setVisible(true);
//              lab_table();
//Channel_table();
//date();
//               
//        } catch (SQLException ex) {
//            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
//             JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
//            }
//                else{
//                                      new rojerusan.RSNotifyShadowFade("!ERROR!", "No data to be updated", Color.white, Color.RED, Color.BLACK, 6
//,RSNotifyShadowFade.PositionNotify.BottomRight, RSNotifyShadowFade.TypeNotify.ERROR).setVisible(true); 
//                }
//                } catch (SQLException ex) {
//            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   