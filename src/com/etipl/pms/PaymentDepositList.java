/* ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; ;
; Copyright © 2009 Edgeware Technologies (India) Pvt Ltd ;
; ;
; This source code contains the intellectual property ;
; of its copyright holder(s), and is made available ;
; under a license. If you do not know the terms of ;
; the license, please stop and do not read further. ;
; ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
*/

package com.etipl.pms;
import com.etipl.pms.datalayer.GetSystemConfig;
import com.etipl.pms.datalayer.PaymentPostingDB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public class PaymentDepositList extends javax.swing.JFrame {
    private static String sip="", sport="", uname="", passwd="", dbname="";
    JFrame jframe;

    /** Creates new form PaymentDepositList */
    public PaymentDepositList() {
        
        jframe = new JFrame();
        initComponents();
        setVisible(true);
        dynamicWidthofColumnforTable1();
        screenDisplay();
        connect_Setting("P");
    }

public void dynamicWidthofColumnforTable1(){

               TableColumn tc;
               tc = jTable1.getColumnModel().getColumn(0);
               tc.setPreferredWidth(40);

               tc = jTable1.getColumnModel().getColumn(1);
               tc.setPreferredWidth(150);

               tc = jTable1.getColumnModel().getColumn(2);
               tc.setPreferredWidth(150);

               tc = jTable1.getColumnModel().getColumn(3);
               tc.setPreferredWidth(200);

               tc = jTable1.getColumnModel().getColumn(4);
               tc.setPreferredWidth(70);

               tc = jTable1.getColumnModel().getColumn(5);
               tc.setPreferredWidth(85);

               tc = jTable1.getColumnModel().getColumn(6);
               tc.setPreferredWidth(85);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment Deposit List");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Claim_ID", "DateofService", "Patient Name", "Mode", "Payment", "Adjustment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    try{
        if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
            MDI.paymentDepList=null;
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed

    /**
    * @param args the command line arguments
    */

    private void screenDisplay(){
    Dimension frameDimension = getSize();

		// position the topFrame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (frameDimension.height > screenSize.height)
        {
		frameDimension.height = screenSize.height;
        }

        if (frameDimension.width > screenSize.width)
        {
			frameDimension.width = screenSize.width;
        }
        setLocation((screenSize.width - frameDimension.width) / 2,
				(screenSize.height - frameDimension.height) / 2);
     }

    private static void connect_Setting(String which)
    {
       GetSystemConfig ob=new GetSystemConfig();
       dbname=ob.getDbName(which);
       sip=ob.getDbIp(which);
       sport=ob.getDbPort(which);
       uname=ob.getUserName(which);
       passwd=ob.getPassword(which);
    }

     public String setDate(String date){

    String Day,Month,Year,Date;
    int j=date.length();

     Month=date.substring(5,7);
     Day=date.substring(8,10);
     Year=date.substring(0,4);
     Date=Month+"/"+Day+"/"+Year;

     return Date;
    }

     public void fillup(String Receipt_id, String Mode){

         int record_count=0;
         try
         {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
            int k=0,n1=0;
            String ress="";
           Connection con = null;
           con = DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
           Statement stmt = con.createStatement();
           
            String SqlQuery="select count(*) from PAYMENT where RECIEPT_ID='"+Receipt_id+"';";
            ResultSet rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n1=rs.getInt("count(*)");
            
           if(n1!=0){

                Object[][] arr = new Object[n1][7];
            try
            {
            SqlQuery="select distinct c.ServiceFromDate from PAYMENT p,CMS_CPTS c where c.CLAIMID = p.claim_ID and c.cpt=p.cpt and p.RECIEPT_ID='"+Receipt_id+"' ;";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            String ff="";
            ff=rs.getString("ServiceFromDate");

            SqlQuery="select * from PAYMENT where RECIEPT_ID='"+Receipt_id+"' order by CPT ;";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                
                arr[k][0] = ""+(k+1);
                arr[k][1] = rs.getString("CLAIM_ID");
                arr[k][2] = setDate(ff);
                arr[k][3] = new PaymentPostingDB().patientName(rs.getString("HRN"));
                arr[k][4] = Mode;
                arr[k][5] = rs.getString("PAYMENT");
                arr[k][6] = rs.getString("ADJUSTMENT");
                k++;
                //ress=ress+arr[k][4]+arr[k][5]+arr[k][6]+arr[k][7];

               ress=rs.getString("CLAIM_ID");
               record_count++;
             }
            }catch(Exception e){ }

                
            if(ress.compareTo("")==0){ 
            }else{                
                jTable1.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"#","Claim_ID","DateofService","Patient Name","Mode","Payment","Adjustment"}
                )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false, false,false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );
                dynamicWidthofColumnforTable1();
            }
        }else{
             JOptionPane.showMessageDialog(null, " NO Payment EXISTS ");
        }
            
         if(record_count>0) {
            setVisible(true);
        }
        else {
            MDI.paymentDepList=null;
            setVisible(false);
        }
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();}
           
      }catch(Exception js){
          js.printStackTrace();       
      }         
   }



    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentDepositList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
