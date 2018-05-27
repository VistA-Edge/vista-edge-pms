package com.etipl.pms;

import com.etipl.pms.datalayer.CaseMethods;
import com.etipl.pms.datalayer.GetSystemConfig;


import com.etipl.pms.datalayer.PatientInformationMethods;
import com.etipl.pms.datalayer.PatientVisitDetailsMethods;
import com.etipl.pms.entity.PatientInformation;
import com.etipl.pms.entity.PatientVisitDetails;
import com.etipl.pms.utilities.DBConnection;

import java.awt.Dimension;
import java.awt.Toolkit;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame; 
import javax.swing.JOptionPane;
import org.gui.JCalendarCombo;

/**
 *
 * @author Administrator
 */
public class Case extends javax.swing.JFrame {
    public Connection con=null;
    private int flag=1;
    public static String sip="", sport="",dbname="",uname="",passwd="";
    private  List<PatientVisitDetails> lstPVD;
    
    Statement stmt;
    ResultSet rs;

    /** Creates new form Case */
    public Case() throws SQLException {
        connect_Setting("P");
        initComponents();
        setVisible(true);
        screenDisplay();
        
        jRadioButton8.setSelected(true);
        clear();
        txtCaseID.setEditable(false);        
        dtpCur.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpSimilar.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpHosFrm.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpHosTo.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpUnableFrm.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpUnableTo.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        addBillingFacilities();
        addServiceFacilities();
        searchPatient();        
    }

    public String getDate1(String date){
        String Day;
        Day=date.substring(8,10);

        return Day;
    }

    public String getMonth(String date){
        String Month;
        Month=date.substring(5,7);

        return Month;
    }

    public String getYear(String date){
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
    public void addBillingFacilities() throws SQLException {
        try {
            String FacSer="";
            DBConnection db = new DBConnection();
            con= db.getPMSConnection();
            stmt=(Statement) con.createStatement();
            String sqlString= "Select * from FACILITY where Services='A' or Services='B'";
            rs= stmt.executeQuery(sqlString);
            cmbBilling.removeAllItems();
            while(rs.next()){
                FacSer=rs.getString("Code")+";"+rs.getString("Name");
                if(rs.getString("Address1")!=null){
                    FacSer+=";"+rs.getString("Address1");
                }
                if(rs.getString("Address2")!=null){
                    FacSer+=";"+rs.getString("Address2");
                }
                if(rs.getString("City")!=null){
                    FacSer+=";"+rs.getString("City");
                }
                if(rs.getString("State")!=null){
                    FacSer+=";"+rs.getString("State");
                }
                if(rs.getString("Zip")!=null){
                    FacSer+=";"+rs.getString("Zip");
                }
                cmbBilling.addItem(FacSer);
            }            
        }
        catch(Exception e) {e.printStackTrace();}
        finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();};
        }
    }

    public String showBillingFacilities(String code) throws SQLException{
    try {
            String FacSer="";
            DBConnection db = new DBConnection();
            con= db.getPMSConnection();
            stmt=(Statement) con.createStatement();
            String sqlString= "Select * from FACILITY where Code ='"+code+"' ;";
            //System.out.println("sqlString : "+sqlString);
            rs= stmt.executeQuery(sqlString);
            
            while(rs.next()){
                FacSer=rs.getString("Code")+";"+rs.getString("Name");
                if(rs.getString("Address1")!=null){
                    FacSer+=";"+rs.getString("Address1");
                }
                if(rs.getString("Address2")!=null){
                    FacSer+=";"+rs.getString("Address2");
                }
                if(rs.getString("City")!=null){
                    FacSer+=";"+rs.getString("City");
                }
                if(rs.getString("State")!=null){
                    FacSer+=";"+rs.getString("State");
                }
                if(rs.getString("Zip")!=null){
                    FacSer+=";"+rs.getString("Zip");
                }
            }
            
            return FacSer;
        }
        catch(Exception e) {e.printStackTrace();return null;}
        finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();};
        }        
    }

    public void addServiceFacilities() throws SQLException{
    try {
            String FacSer="";
            DBConnection db = new DBConnection();
            con= db.getPMSConnection();
            stmt=(Statement) con.createStatement();
            String sqlString= "Select * from FACILITY where Services='A' or Services='S'";
            rs= stmt.executeQuery(sqlString);
            
            cmbFacility.removeAllItems();
            while(rs.next()){
                FacSer=rs.getString("Code")+";"+rs.getString("Name");
                if(rs.getString("Address1")!=null){
                    FacSer+=";"+rs.getString("Address1");
                }
                if(rs.getString("Address2")!=null){
                    FacSer+=";"+rs.getString("Address2");
                }
                if(rs.getString("City")!=null){
                    FacSer+=";"+rs.getString("City");
                }
                if(rs.getString("State")!=null){
                    FacSer+=";"+rs.getString("State");
                }
                if(rs.getString("Zip")!=null){
                    FacSer+=";"+rs.getString("Zip");
                }

                cmbFacility.addItem(FacSer);
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();};
        }
    }

public String showServiceFacilities(String code) throws SQLException{
    try {
            String FacSer="";
            DBConnection db = new DBConnection();
            con= db.getPMSConnection();
            stmt=(Statement) con.createStatement();
            String sqlString= "Select * from FACILITY where Code ='"+code+"'";
         
            rs= stmt.executeQuery(sqlString);
                       
            while(rs.next()){
                FacSer=rs.getString("Code")+";"+rs.getString("Name");
                if(rs.getString("Address1")!=null){
                    FacSer+=";"+rs.getString("Address1");
                }
                if(rs.getString("Address2")!=null){
                    FacSer+=";"+rs.getString("Address2");
                }
                if(rs.getString("City")!=null){
                    FacSer+=";"+rs.getString("City");
                }
                if(rs.getString("State")!=null){
                    FacSer+=";"+rs.getString("State");
                }
                if(rs.getString("Zip")!=null){
                    FacSer+=";"+rs.getString("Zip");
                }
            }
           
            return FacSer;
        }
        catch(Exception e) {e.printStackTrace();return null;}
        finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();};
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbVisit = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbEncounter = new javax.swing.JComboBox();
        txtCaseID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblHRN = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSex = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDOB = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        txtPlace = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbDtp = new javax.swing.JComboBox();
        dtpCur = new org.gui.JCalendarCombo();
        dtpSimilar = new org.gui.JCalendarCombo();
        jPanel6 = new javax.swing.JPanel();
        txtLocaluse = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        dtpUnableFrm = new org.gui.JCalendarCombo();
        dtpUnableTo = new org.gui.JCalendarCombo();
        jLabel21 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        dtpHosFrm = new org.gui.JCalendarCombo();
        dtpHosTo = new org.gui.JCalendarCombo();
        jLabel19 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cmbFacility = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cmbBilling = new javax.swing.JComboBox();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        butSave = new javax.swing.JButton();
        butReset = new javax.swing.JButton();
        butClose = new javax.swing.JButton();
        butSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pre Claim Info");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Visit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("Visit No.");

        cmbVisit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbVisitMouseClicked(evt);
            }
        });
        cmbVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVisitActionPerformed(evt);
            }
        });

        jLabel3.setText("Case Id.");

        jLabel1.setText("Encounters of");

        cmbEncounter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "PMS", "VistA", "Both" }));
        cmbEncounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEncounterActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cmbEncounter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cmbVisit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtCaseID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(cmbVisit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(cmbEncounter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(txtCaseID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient Overview", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setText("HRN:");

        jLabel6.setText("Patient Name:");

        jLabel8.setText("Sex:");

        jLabel10.setText("DOB:");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lblHRN, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(7, 7, 7)
                        .add(txtSex, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(24, 24, 24)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel10)
                    .add(jLabel6))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(7, 7, 7)
                        .add(txtDOB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(4, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(lblHRN, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(txtSex, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel8)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6)
                            .add(txtName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(txtDOB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel10))))
                .add(11, 11, 11))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient Condition Related To", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel12.setText("Employment");

        jLabel13.setText("Auto Accident");

        jLabel14.setText("Other Accident");

        jLabel15.setText("Place(State)");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Yes");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("No");

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("Yes");

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("No");

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("No");

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setText("Yes");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel14)
                            .add(jLabel13))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jRadioButton3)
                            .add(jRadioButton6)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel12)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 40, Short.MAX_VALUE)
                        .add(jRadioButton1)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jRadioButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel15))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jRadioButton5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtPlace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jRadioButton4))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel12)
                        .add(jRadioButton1)
                        .add(jRadioButton2))
                    .add(jLabel15))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel14)
                            .add(jRadioButton3)
                            .add(jRadioButton5))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton6)
                            .add(jRadioButton4)
                            .add(jLabel13)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(txtPlace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(24, 24, 24)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Illness", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel16.setText("Date Of");

        jLabel17.setText("Similar Illness Give First Date");

        cmbDtp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "ILLNESS (First symptom)", "INJURY (Accident)", "PREGNANCY(LMP)" }));

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel16)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmbDtp, 0, 173, Short.MAX_VALUE))
                    .add(jLabel17))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dtpCur, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dtpSimilar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(146, 146, 146))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel16)
                        .add(cmbDtp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(dtpCur, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dtpSimilar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel17))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reserved for Local Use (19)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(txtLocaluse, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(txtLocaluse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unable to Work in Current Illness", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel20.setText("From Date");

        jLabel21.setText("To Date");

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jLabel20)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dtpUnableFrm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(jLabel21)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dtpUnableTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dtpUnableFrm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20)
                    .add(jLabel21)
                    .add(dtpUnableTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hospitalisation Related to Current Service", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel18.setText("From Date");

        jLabel19.setText("To Date");

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(jLabel18)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dtpHosFrm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(41, 41, 41)
                .add(jLabel19)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dtpHosTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel19)
                    .add(dtpHosFrm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel18)
                    .add(dtpHosTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Facility", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel22.setText("Service Facility");

        cmbFacility.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel22)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cmbFacility, 0, 578, Short.MAX_VALUE)
                .add(139, 139, 139))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(cmbFacility, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Billing Service", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel23.setText("Billing");

        cmbBilling.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));

        buttonGroup4.add(jRadioButton7);
        jRadioButton7.setText("Provider");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        buttonGroup4.add(jRadioButton8);
        jRadioButton8.setSelected(true);
        jRadioButton8.setText("Other");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel23)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cmbBilling, 0, 505, Short.MAX_VALUE)
                .add(141, 141, 141))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel23)
                    .add(jRadioButton7)
                    .add(jRadioButton8)
                    .add(cmbBilling, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        butSave.setFont(new java.awt.Font("Tahoma", 1, 11));
        butSave.setText("Save");
        butSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSaveActionPerformed(evt);
            }
        });

        butReset.setFont(new java.awt.Font("Tahoma", 1, 11));
        butReset.setText("Reset");
        butReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butResetActionPerformed(evt);
            }
        });

        butClose.setFont(new java.awt.Font("Tahoma", 1, 11));
        butClose.setText("Close");
        butClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCloseActionPerformed(evt);
            }
        });

        butSearch.setText("Search Patient");
        butSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSearchActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butSave)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butReset)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butClose)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(butSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 38, Short.MAX_VALUE)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(butSave)
                            .add(butReset)
                            .add(butClose)
                            .add(butSearch))
                        .add(41, 41, 41))
                    .add(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 399, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSearchActionPerformed
        // TODO add your handling code here:
    try{
        if(MDI.searchCriteria==null){
            SearchCriteria sp=new SearchCriteria();
            PatientList.whichForm = PatientList.sCase;
            this.dispose();
        }
    }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_butSearchActionPerformed

    public String getDOB(String date){
    String Day,Month,Year,Date;
    Day=date.substring(8,10);
    Month=date.substring(5,7);
    Year=date.substring(0,4);
    Date=Month+'/'+Day+'/'+Year;

    return Date;
    }

    public void searchPatient(){
        try{

            PatientInformation pf=new PatientInformation();
            PatientInformationMethods pim=new PatientInformationMethods();
            pf=pim.searchPatientFromMaster(PatientList.globalhrn);
             if(pf!=null){
               getPatientInformation(pf);
             }
        }catch (Exception e){e.printStackTrace();}    
    }

public void getPatientInformation(PatientInformation pf){
try{
        lblHRN.setText(pf.getHRN());
        txtName.setText(pf.getLastName()+" "+pf.getFirstName());        
        txtDOB.setText(pf.getDOB());
        txtSex.setText(pf.getSex());

        if(lblHRN!=null){
            cmbEncounter.setSelectedIndex(3);
        }
        
  }catch(Exception e){e.printStackTrace();}}

    public String getDate(String date,char ch){
        String Day;
        Day=date.substring(5,7);

        return Day;
    }

    public String getMonth(String date,char ch){
        String Month;
        Month=date.substring(8,10);

        return Month;
    }

    public String getYear(String date,char ch){
        String Year;
        Year=date.substring(0,4);

        return Year;
    }

    public String setDate(String date){
    String Day,Month,Year,Date;
    int j=date.length();

    if(j==8)
    {
     Day=date.substring(2,3);
     Month=date.substring(0,1);
     Year=date.substring(4,8);
     Date=Year+"-0"+Month+"-0"+Day;

    }else
        if(j==9)
        {
        if(date.substring(1,2).compareTo("/")==0)
           {
            Day=date.substring(2,4);
            Month=date.substring(0,1);
            Year=date.substring(5,9);
            Date=Year+"-0"+Month+"-"+Day.replace("/","");
           }else{
                Day=date.substring(3,4);
                Month=date.substring(0,2);
                Year=date.substring(5,9);
                Date=Year+"-"+Month+"-0"+Day.replace("/","");
           }
        }
        else{
            Day=date.substring(3,5);
            Month=date.substring(0,2);
            Year=date.substring(6,10);
            Date=Year+'-'+Month+'-'+Day;
        }
    return Date;
    }

    private void butSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSaveActionPerformed
        // TODO add your handling code here:
      try{
        ResultSet rs1=null;
        Statement stm = null;
        int dd=2,re=3,re1=3;
        
        try{
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            stm = con.createStatement();

//======== for unable to work in current illness fields should be less or equal to the current date
            String sqlQuery0="SELECT '"+setDate(dtpUnableFrm.getSelectedDate())+"' <= Date(NOW()) as dd;";
            rs1=stm.executeQuery(sqlQuery0);
            while(rs1.next()){
                dd=rs1.getInt("dd");
            }

//========= for unable to work in current illness fields From date should be less than To date
            sqlQuery0 = "select '"+setDate(dtpUnableFrm.getSelectedDate())+"' <= '"+setDate(dtpUnableTo.getSelectedDate())+"' as re ;";
            rs1=stm.executeQuery(sqlQuery0);

            while(rs1.next()){
                re=rs1.getInt("re");            
            }

///======== for Hospitalization fields From date should be less than To date
            sqlQuery0 = "select '"+setDate(dtpHosFrm.getSelectedDate())+"' <= '"+setDate(dtpHosTo.getSelectedDate())+"' as re1 ;";
            rs1=stm.executeQuery(sqlQuery0);

            while(rs1.next()){
                re1=rs1.getInt("re1");
            }

            }catch (Exception e){e.printStackTrace();}
            finally{
                if(rs1!=null){rs1.close();}
                if(stm!=null){stm.close();}
                if(con!=null){con.close();}
            }


        CaseMethods cm=new CaseMethods();
        int flag=cm.checkDuplicateCode(txtCaseID.getText());

        System.out.println("flag :"+flag);
        if(flag==0)
        {
            if(dd==1){
                if(re==1){
                    if(re1==1){
            Statement statement = null;
            JFrame frame = new JFrame();
            try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            statement = con.createStatement();

            int j=0;
            String x1="",x2="",x3="",x4="",x5="",x6="",x7="",x7a="",e="",aa="",oa="",b="";
            x1=dtpCur.getSelectedDate();
            x2=dtpSimilar.getSelectedDate();
            x3=dtpUnableFrm.getSelectedDate();
            x4=dtpUnableTo.getSelectedDate();
            x5=dtpHosFrm.getSelectedDate();
            x6=dtpHosTo.getSelectedDate();
            x7a=cmbFacility.getSelectedItem().toString();
            String [] temp = null;
            temp = x7a.split(";");
            x7=temp[0];


            if(buttonGroup1.isSelected(jRadioButton1.getModel()))
            {
                e="Y";
            }else{e="N";}
            //System.out.println("Employment :"+e);
            if(buttonGroup2.isSelected(jRadioButton3.getModel()))
            {
                aa="Y";
            }else{aa="N";}
            //System.out.println("AutoAccident :"+aa);
            if(buttonGroup3.isSelected(jRadioButton6.getModel()))
            {
                oa="Y";
            }else{oa="N";}
            if(buttonGroup4.isSelected(jRadioButton7.getModel()))
            {
                b="Provider";
            }else
                if(buttonGroup4.isSelected(jRadioButton8.getModel()))
                {
                    x7a=cmbBilling.getSelectedItem().toString();
                    String [] temp1 = null;
                    temp1 = x7a.split(";");
                    b=temp1[0];
                }

            String sqlQuery = "insert into PATIENT_CASE (CaseNo,CurrentIllnessStatus,CurrentIllnessDate,SimiliarIllnessDate,PatientUnableFromDate,PatientUnableToDate,HospitalizationFromDate,HospitalizationToDate,ServiceFacility,BillingService,Employment,Place,AutoAccident,OtherAccident,Remarks) values('"+txtCaseID.getText()+"','"+cmbDtp.getSelectedItem()+"','"+setDate(x1)+"','"+setDate(x2)+"','"+setDate(x3)+"','"+setDate(x4)+"','"+setDate(x5)+"','"+setDate(x6)+"','"+x7+"','"+b+"','"+e+"','"+txtPlace.getText()+"','"+aa+"','"+oa+"','"+txtLocaluse.getText()+"')";
            System.out.println("Sql :"+sqlQuery);
            statement.executeUpdate(sqlQuery);           

            JOptionPane.showMessageDialog(frame, "Patient Data Saved");

            }catch (Exception e){e.printStackTrace();}
            finally{
                if(statement!=null){statement.close();}
                if(con!=null){con.close();}
            }

                    }else{JOptionPane.showMessageDialog(null, "Hospitalization From-Date should be less than To-date!! ");
               }

                }else{JOptionPane.showMessageDialog(null, "Unable to Work in Current Illness From-Date should be less than To-date!! ");
               }
            }else{JOptionPane.showMessageDialog(null, "Unable to Work in Current Illness From Date should be <= to the current date!! ");
               }
        }
        else{
        Statement statement = null;
        JFrame frame = new JFrame();
        
        if(dd==1){
            if(re==1){
                if(re1==1){
                try{
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
            con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            statement = con.createStatement();

            int j=0;
            String x1="",x2="",x3="",x4="",x5="",x6="",x7="",x7a="",e="",aa="",oa="",b="";
            x1=dtpCur.getSelectedDate();
            x2=dtpSimilar.getSelectedDate();
            x3=dtpUnableFrm.getSelectedDate();
            x4=dtpUnableTo.getSelectedDate();
            x5=dtpHosFrm.getSelectedDate();
            x6=dtpHosTo.getSelectedDate();
            x7a=cmbFacility.getSelectedItem().toString();
            String [] temp = null;
            temp = x7a.split(";");
            x7=temp[0];
            if(buttonGroup1.isSelected(jRadioButton1.getModel()))
            {
                e="Y";
            }else{e="N";}
            if(buttonGroup2.isSelected(jRadioButton3.getModel()))
            {
                aa="Y";
            }else{aa="N";}
            if(buttonGroup3.isSelected(jRadioButton6.getModel()))
            {
                oa="Y";
            }else{oa="N";}
            if(buttonGroup4.isSelected(jRadioButton7.getModel()))
            {
                b="Provider";
            }else
                if(buttonGroup4.isSelected(jRadioButton8.getModel()))
                {
                    x7a=cmbBilling.getSelectedItem().toString();
                    String [] temp1 = null;
                    temp1 = x7a.split(";");
                    b=temp1[0];
                }

            String updateQuery = "update PATIENT_CASE set Employment= "+ "'" + e + "'" +" ,AutoAccident= "+ "'" + aa + "'"+" ,OtherAccident= "+ "'" + oa + "'"+" ,Place= " + "'" + txtPlace.getText() + "'" +" ,CurrentIllnessStatus= '" +cmbDtp.getSelectedItem()+"'"+" ,CurrentIllnessDate= '" +setDate(x1)+"'"+" ,SimiliarIllnessDate= '" +setDate(x2)+"'"+" ,PatientUnableFromDate= '" +setDate(x3)+"'"+" ,PatientUnableToDate= '" +setDate(x4)+"'"+" ,HospitalizationFromDate= '" +setDate(x5)+"'"+" ,HospitalizationToDate= '" +setDate(x6)+"'"+" ,ServiceFacility= '"+x7+"'"+" ,BillingService= '"+b+"'"+" ,Remarks= '"+txtLocaluse.getText()+"'"+" where CaseNo = '" + txtCaseID.getText() + "' ";
              statement.executeUpdate(updateQuery);            

            JOptionPane.showMessageDialog(frame, "Patient Data Updated");

            if(statement!=null){statement.close();}
            if(con!=null){con.close();}
            
        }catch (Exception e){e.printStackTrace();}
                 }else{JOptionPane.showMessageDialog(null, "Hospitalization From-Date should be less than To-date!! ");
                }
            }else{JOptionPane.showMessageDialog(null, "Unable to Work in Current Illness From-Date should be less than To-date!! ");
                }
        }else{JOptionPane.showMessageDialog(null, "Unable to Work in Current Illness From Date should be <= to the current date!! ");
        }
      }
    }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_butSaveActionPerformed

    private void displayCaseinfo() throws SQLException{

        ResultSet resultSet = null;
        Statement statement = null;
        JFrame frame = new JFrame();
        int force=0;
        
        try{
            clear();
            cmbVisit.setEnabled(true);

            if(cmbVisit.getSelectedItem()!=null){
                txtCaseID.setText(PatientList.globalhrn+"."+cmbVisit.getSelectedItem());
            }else{
                txtCaseID.setText("");
            }

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            statement = con.createStatement();

            String sqlstring = " Select * from PATIENT_CASE where CaseNo ='"+txtCaseID.getText()+"' ";
            resultSet=statement.executeQuery(sqlstring);

            while(resultSet.next()){
                force++;
            }
            if(force>=1)
              {
                resultSet.first();

                txtPlace.setText(resultSet.getString("Place"));
                cmbDtp.setSelectedItem(resultSet.getString("CurrentIllnessStatus"));
                cmbFacility.setSelectedItem(showServiceFacilities(resultSet.getString("ServiceFacility")));

                if(resultSet.getString("BillingService").equals("Provider")==true){
             
                      buttonGroup4.setSelected(jRadioButton7.getModel(),true);
                      cmbBilling.setSelectedItem("Select");
                      cmbBilling.setEnabled(false);
                }
                else{
                    buttonGroup4.setSelected(jRadioButton8.getModel(),true);
                    cmbBilling.setEnabled(true);
                    cmbBilling.setSelectedItem(showBillingFacilities(resultSet.getString("BillingService")));
                }

              String a,b="",c="",d="";
              char ch='z';

              a=resultSet.getString("CurrentIllnessDate");
              b=getDate(a,ch);
              c=getMonth(a,ch);
              d=getYear(a,ch);
              dtpCur.setSelectedDate(Integer.parseInt(d),Integer.parseInt(b),Integer.parseInt(c));

              a=resultSet.getString("SimiliarIllnessDate");
              b=getDate(a,ch);
              c=getMonth(a,ch);
              d=getYear(a,ch);
              dtpSimilar.setSelectedDate(Integer.parseInt(d),Integer.parseInt(b),Integer.parseInt(c));

              a=resultSet.getString("PatientUnableFromDate");
              b=getDate(a,ch);
              c=getMonth(a,ch);
              d=getYear(a,ch);
              dtpUnableFrm.setSelectedDate(Integer.parseInt(d),Integer.parseInt(b),Integer.parseInt(c));

              a=resultSet.getString("PatientUnableToDate");
              b=getDate(a,ch);
              c=getMonth(a,ch);
              d=getYear(a,ch);
              dtpUnableTo.setSelectedDate(Integer.parseInt(d),Integer.parseInt(b),Integer.parseInt(c));

              a=resultSet.getString("HospitalizationFromDate");
              b=getDate(a,ch);
              c=getMonth(a,ch);
              d=getYear(a,ch);
              dtpHosFrm.setSelectedDate(Integer.parseInt(d),Integer.parseInt(b),Integer.parseInt(c));

              a=resultSet.getString("HospitalizationToDate");
              b=getDate(a,ch);
              c=getMonth(a,ch);
              d=getYear(a,ch);
              dtpHosTo.setSelectedDate(Integer.parseInt(d),Integer.parseInt(b),Integer.parseInt(c));

              txtLocaluse.setText(resultSet.getString("Remarks"));
              if(resultSet.getString("Employment").equals("N")==true){
                  buttonGroup1.setSelected(jRadioButton2.getModel(),true);
              }
              else{buttonGroup1.setSelected(jRadioButton1.getModel(),true);}

              if(resultSet.getString("AutoAccident").equals("N")==true){
                  buttonGroup2.setSelected(jRadioButton5.getModel(),true);
              }
              else{buttonGroup2.setSelected(jRadioButton3.getModel(),true);}

              if(resultSet.getString("OtherAccident").equals("N")==true){
                  buttonGroup3.setSelected(jRadioButton4.getModel(),true);
              }
              else{buttonGroup3.setSelected(jRadioButton6.getModel(),true);}

              butSave.setText("Update");
              }else{
                      butSave.setText("Save");
               }
          }catch (Exception e){
              e.printStackTrace();
              JOptionPane.showMessageDialog(frame, "No Patient Exist with this CaseId.");
         }finally{
            if(resultSet!=null){resultSet.close();}
            if(statement!=null){statement.close();}
            if(con!=null){con.close();}
         }
    }

    private void cmbVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVisitActionPerformed
        try {
            // TODO add your handling code here:
            displayCaseinfo();
        } catch (SQLException ex) {ex.printStackTrace();}
}//GEN-LAST:event_cmbVisitActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton8.isEnabled()){
            cmbBilling.setEnabled(true);
        }

    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton8.isEnabled()){
            cmbBilling.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void butResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butResetActionPerformed
        // TODO add your handling code here:
        cmbVisit.removeAllItems();
        cmbVisit.setEnabled(false);
        txtCaseID.setText("");
        lblHRN.setText("");
        txtName.setText("");
        txtSex.setText("");
        txtDOB.setText("");
        jRadioButton8.setSelected(true);
        clear();
}//GEN-LAST:event_butResetActionPerformed

    private void butCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_butCloseActionPerformed

    private void displayMasterEncounter(){
    try{
        PatientVisitDetailsMethods pvdm=new PatientVisitDetailsMethods();
        setLstPVD(pvdm.searchPatientVisitFromMaster(lblHRN.getText()));

                   if(lstPVD !=null && !lstPVD.isEmpty()){
                        getPatientVisit(lstPVD,cmbEncounter.getSelectedIndex());
                   }
                   if(this.cmbVisit.getItemCount()>0){
                        cmbVisit.setSelectedIndex(0);
                   }
    }catch(Exception e){e.printStackTrace();}
    }
    
    private void cmbEncounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEncounterActionPerformed
        // TODO add your handling code here:
    try{
        if(cmbEncounter.getSelectedItem().toString().startsWith("P")){
            clearVisitDetails();            
            displayMasterEncounter();
        }
        if(cmbEncounter.getSelectedItem().toString().startsWith("V")){
            clearVisitDetails();
            displayVistAEncounters();
        }
        if(cmbEncounter.getSelectedItem().toString().startsWith("B")){
            clearVisitDetails();
            displayBothEncounters();
        }
        if(cmbEncounter.getSelectedItem().toString().isEmpty()){
              clearVisitDetails();
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_cmbEncounterActionPerformed

    private void cmbVisitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbVisitMouseClicked
        try {
            // TODO add your handling code here:
            displayCaseinfo();
        } catch (SQLException ex) {ex.printStackTrace();}
    }//GEN-LAST:event_cmbVisitMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    try{
        if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
            MDI.patientCase=null;
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed

      public void setLstPVD(List<PatientVisitDetails> lstPVD){
       this.lstPVD=lstPVD;
   }

   public List<PatientVisitDetails> getLstPVD(){
       return lstPVD;
   }

private void displayBothEncounters() throws SQLException{
try{
       PatientVisitDetailsMethods pvdm=new PatientVisitDetailsMethods();

       List<PatientVisitDetails> lstPVDMas=null;

       setLstPVD(pvdm.searchPatientVisitFromMaster(lblHRN.getText()));
       if(lstPVD !=null && !lstPVD.isEmpty()){
           lstPVDMas=lstPVD;
           int len=lstPVDMas.size();

           setLstPVD(pvdm.searchPatientVisitFromVistA(lblHRN.getText()));
           if(lstPVD !=null && !lstPVD.isEmpty()){

              for(int i=0;i<len;i++){
                lstPVD.add(lstPVDMas.get(i));
              }
               getPatientVisit(lstPVD,this.cmbEncounter.getSelectedIndex());
           }else{
               getPatientVisit(lstPVDMas,this.cmbEncounter.getSelectedIndex());
           }

       }else{

           setLstPVD(pvdm.searchPatientVisitFromVistA(lblHRN.getText()));

           if(lstPVD !=null && !lstPVD.isEmpty()){
               getPatientVisit(lstPVD,this.cmbEncounter.getSelectedIndex());
           }
       }
}catch(Exception e){e.printStackTrace();}
}


private void displayVistAEncounters(){
try{
       PatientVisitDetailsMethods pvdm=new PatientVisitDetailsMethods();

       setLstPVD(pvdm.searchPatientVisitFromVistA(lblHRN.getText()));
       if(lstPVD !=null && !lstPVD.isEmpty()){
           getPatientVisit(lstPVD,this.cmbEncounter.getSelectedIndex());
       }

       if(this.cmbVisit.getItemCount()>0){
              cmbVisit.setSelectedIndex(0);
       }
       flag=2;
       
    }catch(Exception e){e.printStackTrace();}
}

private void clearVisitDetails(){
    this.cmbVisit.removeAllItems();
}

private void getPatientVisit(List<PatientVisitDetails> lstPVD,int combosel){
try{
      int len=lstPVD.size();

      PatientVisitDetails pvd = new PatientVisitDetails();
        for(int j=0;j<len;j++){
          pvd=lstPVD.get(j);
          this.cmbVisit.addItem(pvd.getVisitNo());
        }      
}catch(Exception e){e.printStackTrace();}
}
    private void clear(){               
        txtPlace.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        jRadioButton6.setSelected(false);
        cmbDtp.setSelectedItem("Select");

        if(cmbFacility.getItemCount()>0){
            cmbFacility.setSelectedIndex(0);
        }else{
            cmbFacility.setSelectedIndex(-1);
        }

        if(cmbBilling.getItemCount()>0){
            cmbBilling.setSelectedIndex(0);
        }else{
           cmbBilling.setSelectedIndex(-1);
        }
        
        jRadioButton7.setSelected(false);
        jRadioButton8.setSelected(false);        
        txtLocaluse.setText("");

        buttonGroup1.setSelected(jRadioButton2.getModel(),true);
        buttonGroup2.setSelected(jRadioButton5.getModel(),true);
        buttonGroup3.setSelected(jRadioButton4.getModel(),true);
        buttonGroup4.setSelected(jRadioButton8.getModel(),true);

        ResultSet resultSet1=null;
        Statement statement = null;
        try{
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            statement = con.createStatement();
            String sqlstring1 = " SELECT DATE(Now())as Date; ";
            resultSet1=statement.executeQuery(sqlstring1);

            while(resultSet1.next()){
              String date,b,c,d;
              char ch='a';
              date=resultSet1.getString("Date");
              b=getDate1(date);
              c=getMonth(date);
              d=getYear(date);
              dtpCur.setSelectedDate(Integer.parseInt(d),Integer.parseInt(c),Integer.parseInt(b));
              dtpSimilar.setSelectedDate(Integer.parseInt(d),Integer.parseInt(c),Integer.parseInt(b));
              dtpHosFrm.setSelectedDate(Integer.parseInt(d),Integer.parseInt(c),Integer.parseInt(b));
              dtpHosTo.setSelectedDate(Integer.parseInt(d),Integer.parseInt(c),Integer.parseInt(b));
              dtpUnableFrm.setSelectedDate(Integer.parseInt(d),Integer.parseInt(c),Integer.parseInt(b));
              dtpUnableTo.setSelectedDate(Integer.parseInt(d),Integer.parseInt(c),Integer.parseInt(b));
            }
        }catch (Exception e){
           e.printStackTrace();           
        }
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

    /**
    * @param args the command line arguments
    */
  /*  public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Case().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton butClose;
    public javax.swing.JButton butReset;
    public javax.swing.JButton butSave;
    public javax.swing.JButton butSearch;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.ButtonGroup buttonGroup3;
    public javax.swing.ButtonGroup buttonGroup4;
    public javax.swing.JComboBox cmbBilling;
    public javax.swing.JComboBox cmbDtp;
    public javax.swing.JComboBox cmbEncounter;
    public javax.swing.JComboBox cmbFacility;
    public javax.swing.JComboBox cmbVisit;
    public org.gui.JCalendarCombo dtpCur;
    public org.gui.JCalendarCombo dtpHosFrm;
    public org.gui.JCalendarCombo dtpHosTo;
    public org.gui.JCalendarCombo dtpSimilar;
    public org.gui.JCalendarCombo dtpUnableFrm;
    public org.gui.JCalendarCombo dtpUnableTo;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    public javax.swing.JPanel jPanel11;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    public javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JRadioButton jRadioButton2;
    public javax.swing.JRadioButton jRadioButton3;
    public javax.swing.JRadioButton jRadioButton4;
    public javax.swing.JRadioButton jRadioButton5;
    public javax.swing.JRadioButton jRadioButton6;
    public javax.swing.JRadioButton jRadioButton7;
    public javax.swing.JRadioButton jRadioButton8;
    public javax.swing.JLabel lblHRN;
    public javax.swing.JTextField txtCaseID;
    public javax.swing.JLabel txtDOB;
    public javax.swing.JTextField txtLocaluse;
    public javax.swing.JLabel txtName;
    public javax.swing.JTextField txtPlace;
    public javax.swing.JLabel txtSex;
    // End of variables declaration//GEN-END:variables

}
