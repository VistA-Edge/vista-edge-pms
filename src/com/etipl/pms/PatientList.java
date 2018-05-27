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
import com.etipl.pms.entity.PatientInformation;
import com.etipl.pms.entity.PaymentPostingDef;
import com.etipl.pms.reports.AZSlidingFee.AZReport;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

/**
 *
 * @author  gmc
 */


public class PatientList extends javax.swing.JFrame {
    public static String globalhrn="",globalname="",globalsex,globaldob,globalinscmp="",globalRecid="",globaldate="",globalClaimID="";
    public static int whichForm;
    public static int spatient=0,sAppt=1,sCase=2,sBill=3,sPayment=4,sPaymentPosting=5,sAZReport=6;
    private static String sip="", sport="", uname="", passwd="", dbname="";
    int i=0;
    JFrame jframe;
    
    public PatientList() {
        jframe = new JFrame();
        initComponents();
        setVisible(true);
        screenDisplay();
        connect_Setting("P");
    }

   public String getDate(String date,char ch){
        String Day;
        Day=date.substring(8,10);
        
        return Day;
    }

    public String getMonth(String date,char ch){
        String Month;
        Month=date.substring(5,7);

        return Month;
    }

    public String getYear(String date,char ch){
        String Year;
        Year=date.substring(0,4);

        return Year;
    }

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
    
   public int fillup(String hrn,String ssn,String fname,String lname,String dob) throws SQLException
   {
      int record_count=0;
      Statement stmt = null;
      ResultSet rs = null;
      Connection con = null;

      try{
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         int i=0;
           con = DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
           stmt = con.createStatement();
           String sqlquery="select count(*) from JP_PAT_INFO where HRN like '"+hrn+"%' and SSN like '"+ssn+"%' and FNAME like '"+fname+"%' and LNAME like '"+lname+"%' and DOB like '"+dob+"%'";
           rs=stmt.executeQuery(sqlquery) ;
           rs.next();
           int no=rs.getInt("count(*)");
           sqlquery="select * from JP_PAT_INFO where HRN like '"+hrn+"%' and SSN like '"+ssn+"%' and FNAME like '"+fname+"%' and LNAME like '"+lname+"%' and DOB like '"+dob+"%'";
        
        rs=stmt.executeQuery(sqlquery);
        String[][] arr=new String[no][9];
        if(no>0) {
        while(rs.next())
        {
            try
            {
            arr[i][0] = (i+1) + "";
            arr[i][1]=rs.getString("HRN").toString();
            arr[i][2]=rs.getString("FNAME").toString();
            arr[i][3]=rs.getString("LNAME").toString();
            arr[i][4]=rs.getString("SEX").toString();
            arr[i][5]=rs.getString("SSN").toString();
            arr[i][6]=rs.getString("DOB").substring(5,7)+"/"+rs.getString("DOB").substring(8,10)+"/"+rs.getString("DOB").substring(0,4);
            arr[i][7]=rs.getString("PHONE").toString();
            arr[i][8]=rs.getString("Typepat").toString();
            }
            catch(Exception ashg){}
            i++;
            record_count++;
        }
        
        jTable1.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"SNo","HRN","First Name","Last Name","Sex","SSN","DOB","Telephone","Type"}
        )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false, false,false, false,false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );
        jTable1.setRowSelectionInterval(0,0);
        }

        if(record_count>0) {
            setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(jframe, "No Patient found");   
            setVisible(false);
        }             
               
      }catch(Exception js){js.printStackTrace();}
      finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();}
      }
      return record_count;
   }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient List");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNo", "HRN", "First Name", "Last Name", "Sex", "SSN", "DOB", "Telephone", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void showPatientpp() {
        System.out.println("in showPatientpp");
        int i=jTable1.getSelectedRow();
        String hrn,name1,Sex,dob,inscmp="";
        hrn=jTable1.getValueAt(i,1).toString();
        name1=jTable1.getValueAt(i,2).toString()+" "+jTable1.getValueAt(i,3).toString();
        Sex=jTable1.getValueAt(i,4).toString();
        dob=jTable1.getValueAt(i,6).toString();
        this.globalhrn=hrn;
        this.globalname=name1;
        this.globalinscmp=inscmp;
        this.dispose();

        PaymentPosting a=new PaymentPosting();
        int y1,m1,d1,y2,m2,d2;
        y1=Integer.parseInt(a.pYear1);
        m1=Integer.parseInt(a.pMonth1);
        d1=Integer.parseInt(a.pDay1);
        a.jCalendarCombo1.setSelectedDate(y1,m1,d1);

        y2=Integer.parseInt(a.pYear2);
        m2=Integer.parseInt(a.pMonth2);
        d2=Integer.parseInt(a.pDay2);
        a.jCalendarCombo2.setSelectedDate(y2,m2,d2);

        a.jComboBox1.setSelectedItem("Patient");
        a.jLabel60.setText(this.globalhrn);
        System.out.println("a.pPaymode : "+a.pPaymode);
        a.jComboBox2.setSelectedItem(a.pPaymode);
        if(a.pPaymode.compareTo("Check")==0)
        {
            a.jLabel4.setVisible(true);a.jLabel4.setText("Check No.");a.jTextField1.setVisible(true);
            a.jTextField1.setText(a.pChelno);
        }else{
            if(a.pPaymode.compareTo("Electronic")==0){
                a.jLabel4.setVisible(true);a.jLabel4.setText("ECFA No.");a.jTextField1.setVisible(true);
                a.jTextField1.setText(a.pChelno);
            }else{a.jLabel4.setVisible(false);a.jTextField1.setVisible(false);}
        }
        a.jTextField2.setText(a.pDesc);
        a.jTextField3.setText(a.pPayAmt);
        a.jComboBox3.setSelectedItem(a.pRecCode);
        
        a.jLabel21.setText(this.globalname);
        a.jLabel26.setText(Sex);
        a.jLabel28.setText(dob);
        a.jComboBox12.requestFocus();
        a.lblStatus.setText("To make receipt choose date, payor type, mode and enter other details and then press save.");
    }


    private void showPatient1() {
        int i=jTable1.getSelectedRow();
        String hrn,name,sex,dob,DOB;
        hrn=jTable1.getValueAt(i,1).toString();
        name=jTable1.getValueAt(i,2).toString();
        sex=jTable1.getValueAt(i,3).toString();
        dob=jTable1.getValueAt(i,5).toString();
        this.globalhrn=hrn;
        this.globalname=name;
        this.globalsex=sex;
        this.globaldob=dob;
        this.dispose();

            PaymentPosting a=new PaymentPosting();
            a.jTabbedPane1.setSelectedIndex(1);
            int y1,m1,d1,y2,m2,d2;
            y1=Integer.parseInt(a.pYear1);
            m1=Integer.parseInt(a.pMonth1);
            d1=Integer.parseInt(a.pDay1);
            a.jCalendarCombo1.setSelectedDate(y1,m1,d1);

            y2=Integer.parseInt(a.pYear2);
            m2=Integer.parseInt(a.pMonth2);
            d2=Integer.parseInt(a.pDay2);
            a.jCalendarCombo2.setSelectedDate(y2,m2,d2);

            a.search();
            a.showAll(a.SSlotNo);
            a.lblUnpostedAmt.setText(a.unpostamt);
            a.jPanel14.setVisible(true);
            a.jLabel62.setText(this.globalhrn);
            a.jLabel55.setText(this.globalname);
            a.jLabel57.setText(this.globalsex);
            a.jLabel59.setText(this.globaldob);

            a.jComboBox16.removeAllItems();
            checkPatIns(a);        
    }

    public int fillup(String a,String b)
   {

      int record_count=0;
      
      try
         {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         int i=0;
           Connection con = null;
           con = DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
           Statement stmt = con.createStatement();
           String sqlquery="select count(*) from JP_PAT_INFO where "+a+" like '"+b+"%' ; ";
           ResultSet rs=stmt.executeQuery(sqlquery) ;
           rs.next();
           int no=rs.getInt("count(*)");
           sqlquery="select * from JP_PAT_INFO where "+a+" like '"+b+"%'";
           rs=stmt.executeQuery(sqlquery);
      
        String[][] arr=new String[no][9];
        if(no>0) {
        while(rs.next())
        {
            try
            {
            arr[i][0] = (i+1) + "";
            arr[i][1]=rs.getString("HRN").toString();
            arr[i][2]=rs.getString("FNAME").toString();
            arr[i][3]=rs.getString("LNAME").toString();
            arr[i][4]=rs.getString("SEX").toString();
            arr[i][5]=rs.getString("SSN").toString();
            arr[i][6]=rs.getString("DOB").toString();
            arr[i][7]=rs.getString("PHONE").toString();
            arr[i][8]=rs.getString("Typepat").toString();
            }
            catch(Exception ashg){}
           i++;
           record_count++;
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"SNo","HRN","First Name","Last Name","Sex","SSN","DOB","Telephone","Type"}));
        jTable1.setRowSelectionInterval(0,0);
        }
        if(record_count>0) {
            setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(jframe, "No Patient found");
            setVisible(false);
        }

        if(rs!=null){rs.close();}
        if(stmt!=null){stmt.close();}
        if(con!=null){con.close();}
       
      }catch(Exception js){js.printStackTrace();}
      return record_count;
   }

public int fillup(String hrn,String ssn,String fname,String lname,String dob,String inscmpname)
{
   i=1;
   String [] temp = null;
   temp = inscmpname.split("-");
   globalinscmp=temp[0];
   int record_count=0;
   int no=0;

      try
         {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           int i=0;
           Connection con = null;
           con = DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
           Statement stmt = con.createStatement();
            String sqlquery="select * from JP_PAT_INFO j, patient_insurance p where j.HRN=p.PATIENT_HRN and j.HRN like '"+hrn+"%' and j.SSN like '"+ssn+"%' and j.FNAME like '"+fname+"%' and j.LNAME like '"+lname+"%' and j.DOB like '"+dob+"%' and p.INSURANCE_COMPANY_ID ='"+temp[0]+"' group by j.hrn;";
             ResultSet rs=stmt.executeQuery(sqlquery);
            while(rs.next()){
              no=no+1;
            }
            if(no>0) {
            String[][] arr=new String[no][9];
            rs.first();
            do{
            arr[i][0] = (i+1) + "";
            arr[i][1]=rs.getString("HRN").toString();
            arr[i][2]=rs.getString("FNAME").toString();
            arr[i][3]=rs.getString("LNAME").toString();
            arr[i][4]=rs.getString("SEX").toString();
            arr[i][5]=rs.getString("SSN").toString();
            arr[i][6]=rs.getString("DOB").toString();
            arr[i][7]=rs.getString("PHONE").toString();
            arr[i][8]=rs.getString("Typepat").toString();         
            i++;
            record_count++;
        } while(rs.next());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"SNo","HRN","First Name","Last Name","Sex","SSN","DOB","Telephone","Type"}));

        jTable1.setRowSelectionInterval(0,0);
        }
        if(record_count>0) {
            setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(jframe, "No Patient found");
            setVisible(false);
        }
     
       if(rs!=null){rs.close();}
       if(stmt!=null){stmt.close();}
       if(con!=null){con.close();}
      }catch(Exception js){js.printStackTrace();}
      return record_count;
   }

private void checkPatIns(PaymentPosting a){
  try{
      
      PaymentPostingDB pp1db=new PaymentPostingDB();
      PaymentPostingDef ppen =new PaymentPostingDef();
       a.jPanel10.setVisible(true);
       a.jPanel14.setVisible(true);

       a.jPanel13.setVisible(true);
       PaymentPostingDef ggppen1 =new PaymentPostingDef();
       ggppen1=pp1db.getGurantorInfo("HRN",this.globalhrn);
       a.setGurantor(ggppen1);
       List<PaymentPostingDef> lstppen1 =new ArrayList<PaymentPostingDef>();
       lstppen1=pp1db.getcmshcfa1500("HRN",this.globalhrn);
       int j=0;
       double dd=5.0;
       try{
             j=lstppen1.size();
             int k=0,l=0;
             while(j>0){
             PaymentPostingDef ppen1=new PaymentPostingDef();
             ppen1=lstppen1.get(k);
             if(a.jCheckBox1.isSelected()==false){
                 dd=pp1db.checkPaid(ppen1.getClaimId());
             }
             if(dd==0.0){
                j--;
                k++;
                continue;
             }
             a.jComboBox16.insertItemAt(ppen1.getClaimId(),l++);
             k++;
             j--;
             }
             }catch(Exception e){a.jComboBox16.removeAllItems();}

}catch(Exception e){e.printStackTrace();}
}

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()==10){
            if(i==0){
            showPatient();
        }else{ showPatient();}
        }            
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
// TODO add your handling code here:
      //
        if(i==0){
            showPatient();
        }else{
            showPatient();
        }           
    }//GEN-LAST:event_jTable1MouseClicked
    
    private void showPatient() {
    try{
        int i=jTable1.getSelectedRow();
        String hrn=jTable1.getValueAt(i,1).toString();        
        this.globalhrn=hrn;
        
        switch (whichForm) {
        case 0:          
                try{
                    if(MDI.patRegistration==null){
                        MDI.patRegistration=new PatientRegistation();
                    }
                }catch(Exception e){e.printStackTrace();}
        break;
        case 1:
                try{
                    if(MDI.basicAppointment==null){
                        MDI.basicAppointment=new BasicAppointment();
                    }
                }catch(Exception e){e.printStackTrace();}
        break;
        case 2:
                 try{
                      if(MDI.patientCase==null){
                            MDI.patientCase=new Case();
                        }
                }catch(Exception e){e.printStackTrace();}
        break;
        case 3:
                 try{
                      if(MDI.billing==null){
                          MDI.billing=new Billing();
                      }
                 }catch(Exception e){e.printStackTrace();}
        break;
        case 4:
             showPatientpp();
             break;
        case 5:
             showPatient1();
             break;
        case 6:
                 try{
                    if(MDI.azSlidingReport==null){
                        MDI.azSlidingReport=new AZReport();
                    }
                 }catch(Exception e){e.printStackTrace();}
        break;
        default:           
        }    
        this.dispose();
     }catch(Exception e){e.printStackTrace();}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientList().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
}
