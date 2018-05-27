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

import com.etipl.pms.datalayer.CityStateMethods;
import com.etipl.pms.datalayer.Connect2MySql;
import com.etipl.pms.datalayer.GetSystemConfig;
import com.etipl.pms.entity.InsurancePlan;

import com.etipl.pms.datalayer.PatientInformationMethods;
import com.etipl.pms.datalayer.SlidingFeeMethods;
import com.etipl.pms.entity.CityState;
import com.etipl.pms.entity.PatientInformation;
import com.etipl.pms.entity.PatientInsurance;
import com.etipl.pms.entity.SlidingFee;

import java.awt.Dimension;
import java.awt.Toolkit; 
import java.awt.event.KeyEvent;

import org.gui.JCalendarCombo;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.swingx.autocomplete.Configurator;


/**
 *
 * @author  Administrator
 */
public class PatientRegistation extends javax.swing.JFrame {
    private String allstates[];
    private boolean callByBackNext=true;
    private  int ppp=0;
    private static String sip="", sport="", dbname="", uname="", passwd="";

    /** Creates new form PatientRegistation */
    public PatientRegistation() {
        initComponents();
        setVisible(true);

        SlidingFeePanel.setVisible(false);
        back.setVisible(false);
        next.setVisible(false);

       Configurator.enableAutoCompletion(this.cmbInsCompany);
       Configurator.enableAutoCompletion(this.cmbPlanType);
       Configurator.enableAutoCompletion(this.cmbRelation);
       Configurator.enableAutoCompletion(this.cmbms);
       Configurator.enableAutoCompletion(this.cmbtitle);
       Configurator.enableAutoCompletion(this.cmblstate);
       Configurator.enableAutoCompletion(this.cmbRace);
       Configurator.enableAutoCompletion(this.cmbReligion);
       Configurator.enableAutoCompletion(this.cmbNationality);
       Configurator.enableAutoCompletion(this.cmbOccupation);
       Configurator.enableAutoCompletion(this.cmbLangSpokenatHome);
       Configurator.enableAutoCompletion(this.cmbHighestLevelofEducation);
       Configurator.enableAutoCompletion(this.cmbEthnicity);

        uncheckSOFHIPAADate();
        uncheckDOSDate();        
        resetClicked();
        screenDisplay();
           jCalendarCombo1.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
           jCalendarCombo2.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
           dtpDOS.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
           cmbInsDOB.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
           
           enterage.setVisible(false);           
           connect_Setting("P");
           
        try
         {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           int i=0;
           Connection con = null;
           con = DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
           Statement stmt = con.createStatement();
       
        cmbInsCompany.removeAllItems();
        
        ResultSet rs=stmt.executeQuery("select * from INSURANCE_COMPANY_DETAILS") ;
        cmbInsCompany.insertItemAt("",i++);
        while(rs.next())
        {
            cmbInsCompany.insertItemAt(rs.getString("Company_Name")+"-"+rs.getString("Company_Id"),i++);
        }
        cmbInsCompany.setSelectedIndex(0);
        
        cmbPlanType.removeAllItems();
        i=0;
        rs=stmt.executeQuery("select Description from MASTER_TYPES where TypeCode='PT'") ;
        cmbPlanType.insertItemAt("",i++);
        while(rs.next())
        {
            cmbPlanType.insertItemAt(rs.getString("Description"),i++);
        }
        
        cmbPlanType.setSelectedIndex(0);
        
        jComboBox3.removeAllItems();
        i=0;
        rs=stmt.executeQuery("select Description from MASTER_TYPES where TypeCode='POTY'") ;
        jComboBox3.insertItemAt("",i++);
        while(rs.next())
        {
            jComboBox3.insertItemAt(rs.getString("Description"),i++);
            
        }
        jComboBox3.setSelectedIndex(0);

        jComboBox4.removeAllItems();
        i=0;
        rs=stmt.executeQuery("select Description from MASTER_TYPES where TypeCode='IS'") ;
        jComboBox4.insertItemAt("",i++);
        while(rs.next())
        {
            jComboBox4.insertItemAt(rs.getString("Description"),i++);
            
        }
        jComboBox4.setSelectedIndex(0);
        
        cmbRelation.removeAllItems();
        i=0;
        rs=stmt.executeQuery("select Description from MASTER_TYPES where TypeCode='PRI'") ;
        cmbRelation.addItem("");
        while(rs.next())
        {
            cmbRelation.addItem(rs.getString("Description"));
        }
        cmbRelation.setSelectedIndex(0);
        

            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();}

         }
        catch(Exception sdkj){
            sdkj.printStackTrace();
        }
	
        setDefault();
        
        ipno.setText(getHRN());
        jLabel51.setText(ipno.getText());
        age.setVisible(false);
        ageunits.setVisible(false);
        agelabel.setText("DOB");
        setVisible(true);
        getStates();
        dob.setVisible(true);
        update.setEnabled(false);
        cmblstate.setSelectedIndex(-1);
        ipno.setEnabled(false);
        registerdate.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dob.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpDOHS.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
                
        lname.requestFocus();
        
        if(PatientList.globalhrn==null) {
            PatientList.globalhrn="";
        }        
        if(!PatientList.globalhrn.equalsIgnoreCase("")) {
            searchPatientByHRN(PatientList.globalhrn);
        }
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        registerdate = new org.gui.JCalendarCombo();
        jLabel38 = new javax.swing.JLabel();
        ipno = new javax.swing.JTextField();
        regtime = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        back = new javax.swing.JButton();
        next = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        sex = new javax.swing.JComboBox();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        agelabel = new javax.swing.JLabel();
        age = new javax.swing.JTextField();
        dob = new org.gui.JCalendarCombo();
        jLabel57 = new javax.swing.JLabel();
        ageunits = new javax.swing.JComboBox();
        enterage = new javax.swing.JCheckBox();
        lname = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        tfssn = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cmbtitle = new javax.swing.JComboBox();
        tfsalutation = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jlmlc = new javax.swing.JLabel();
        cmbms = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacomments = new javax.swing.JTextArea();
        tfMiddleName = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        dtpDOHS = new org.gui.JCalendarCombo();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        optHIPAA = new javax.swing.JRadioButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ladd1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cmblstate = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfemail = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cmbReligion = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        tfAnnualHHIncome = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        tfFamilyMembers = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lphone = new javax.swing.JFormattedTextField();
        wphone = new javax.swing.JFormattedTextField();
        cphone = new javax.swing.JFormattedTextField();
        cmbNationality = new javax.swing.JComboBox();
        cmbRace = new javax.swing.JComboBox();
        cmbEthnicity = new javax.swing.JComboBox();
        cmbOccupation = new javax.swing.JComboBox();
        cmbLangSpokenatHome = new javax.swing.JComboBox();
        cmbHighestLevelofEducation = new javax.swing.JComboBox();
        tfMessagePhone = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        lpin = new javax.swing.JTextField();
        cmbCity = new javax.swing.JComboBox();
        lcountry = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfeccomments = new javax.swing.JTextArea();
        lecname = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfec = new javax.swing.JTextField();
        bec = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tfgurantor = new javax.swing.JTextField();
        bgurantor = new javax.swing.JButton();
        lgurantorname = new javax.swing.JLabel();
        lhhname = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfhousehold = new javax.swing.JTextField();
        bhousehold = new javax.swing.JButton();
        lecadd = new javax.swing.JLabel();
        lecphone = new javax.swing.JLabel();
        lgurantoradd = new javax.swing.JLabel();
        lgurantorphone = new javax.swing.JLabel();
        lhhadd = new javax.swing.JLabel();
        lhhphone = new javax.swing.JLabel();
        SlidingFeePanel = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        lblSlidingCode = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lblMax = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cmbPlanType = new javax.swing.JComboBox();
        txtPolicyNO = new javax.swing.JTextField();
        txtPlanName = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jCalendarCombo1 = new org.gui.JCalendarCombo();
        jCalendarCombo2 = new org.gui.JCalendarCombo();
        cmbRelation = new javax.swing.JComboBox();
        txtInsGroup = new javax.swing.JTextField();
        dtpDOS = new org.gui.JCalendarCombo();
        optSOF = new javax.swing.JRadioButton();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cmbInsCompany = new javax.swing.JComboBox();
        jLabel68 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtInsFName = new javax.swing.JTextField();
        txtInsAdd = new javax.swing.JTextField();
        txtInsZip = new javax.swing.JTextField();
        cmbInsDOB = new org.gui.JCalendarCombo();
        cmbInsSex = new javax.swing.JComboBox();
        txtInsCity = new javax.swing.JTextField();
        txtInsState = new javax.swing.JTextField();
        txtInsEmp = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtInsLName = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtInsPhone = new javax.swing.JFormattedTextField();
        jLabel69 = new javax.swing.JLabel();
        txtInsSSN = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        chkAnyBenefit = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient Registration");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Identifier"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setText("Registration Date & Time");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 150, 20));

        registerdate.setDoubleBuffered(false);
        registerdate.setEnabled(false);
        jPanel6.add(registerdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 110, -1));

        jLabel38.setText("Patient #");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        ipno.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        ipno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ipnoFocusLost(evt);
            }
        });
        ipno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ipnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ipnoKeyReleased(evt);
            }
        });
        jPanel6.add(ipno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 110, -1));

        regtime.setEditable(false);
        regtime.setText("12:12:12");
        regtime.setToolTipText("Enter time in format hh:mm:ss");
        regtime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                regtimeFocusLost(evt);
            }
        });
        regtime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                regtimeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                regtimeKeyReleased(evt);
            }
        });
        jPanel6.add(regtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 70, -1));

        save.setMnemonic('S');
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        save.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saveKeyPressed(evt);
            }
        });

        reset.setMnemonic('R');
        reset.setText("New Registration");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        back.setMnemonic('B');
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        next.setMnemonic('N');
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        update.setMnemonic('U');
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateKeyPressed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Basic Demographics"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Last Name");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("First Name");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, 20));

        fname.setText("MAN");
        fname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fnameKeyTyped(evt);
            }
        });
        jPanel5.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 110, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Sex");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 30, 20));

        sex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "MALE", "FEMALE", "UNKNOWN" }));
        jPanel5.add(sex, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 80, -1));

        jDesktopPane1.setBackground(new java.awt.Color(238, 238, 238));

        agelabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        agelabel.setText("DOB ");
        agelabel.setBounds(0, 0, 30, 20);
        jDesktopPane1.add(agelabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ageKeyTyped(evt);
            }
        });
        age.setBounds(40, 0, 30, 20);
        jDesktopPane1.add(age, javax.swing.JLayeredPane.DEFAULT_LAYER);

        dob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobFocusLost(evt);
            }
        });
        dob.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                dobVetoableChange(evt);
            }
        });
        dob.setBounds(40, 0, 110, 20);
        jDesktopPane1.add(dob, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel57.setForeground(new java.awt.Color(255, 0, 51));
        jLabel57.setText("*");
        jLabel57.setBounds(20, 0, -1, -1);
        jDesktopPane1.add(jLabel57, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ageunits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Years", "Months", "Days" }));
        ageunits.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ageunitsFocusLost(evt);
            }
        });
        ageunits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ageunitsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageunitsKeyReleased(evt);
            }
        });
        ageunits.setBounds(80, 0, 70, -1);
        jDesktopPane1.add(ageunits, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel5.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 170, 20));

        enterage.setText("Enter Age");
        enterage.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        enterage.setMargin(new java.awt.Insets(0, 0, 0, 0));
        enterage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterageActionPerformed(evt);
            }
        });
        enterage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                enterageFocusLost(evt);
            }
        });
        enterage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterageKeyPressed(evt);
            }
        });
        jPanel5.add(enterage, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, -1));

        lname.setText("MAN");
        lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lnameFocusLost(evt);
            }
        });
        lname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lnameKeyTyped(evt);
            }
        });
        jPanel5.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 90, -1));

        jLabel39.setText("SSN #");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, 20));

        tfssn.setText("AAA");
        jPanel5.add(tfssn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 110, -1));

        jLabel26.setText("Title");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        cmbtitle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "MR.", "MRS.", "MS.", "MISS.", "DR." }));
        cmbtitle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbtitleItemStateChanged(evt);
            }
        });
        cmbtitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbtitleFocusLost(evt);
            }
        });
        jPanel5.add(cmbtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 110, -1));

        tfsalutation.setText("AAA");
        jPanel5.add(tfsalutation, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 110, -1));

        jLabel5.setText("Comments");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel40.setText("Salutation");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 60, 20));

        jlmlc.setText("Marital Status");
        jPanel5.add(jlmlc, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 80, 20));

        cmbms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "MARRIED", "SINGLE", "DIVORCED", "WIDOW/WIDOWER", "OTHER", "NEVER MARRIED", "SEPARATED", "UNKNOWN" }));
        jPanel5.add(cmbms, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 130, -1));

        tacomments.setColumns(20);
        tacomments.setLineWrap(true);
        tacomments.setRows(5);
        tacomments.setNextFocusableComponent(save);
        tacomments.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tacommentsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tacomments);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 760, 310));
        jPanel5.add(tfMiddleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 40, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel46.setText("Middle");
        jPanel5.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, 20));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel55.setForeground(new java.awt.Color(255, 0, 51));
        jLabel55.setText("*");
        jPanel5.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel56.setForeground(new java.awt.Color(255, 0, 51));
        jLabel56.setText("*");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));
        jPanel5.add(dtpDOHS, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, -1, -1));

        jLabel59.setText("Date of Sign");
        jPanel5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, -1, 20));

        jLabel60.setText("HIPAA");
        jPanel5.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));

        optHIPAA.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        optHIPAA.setLabel("Signed");
        optHIPAA.setMargin(new java.awt.Insets(0, 0, 0, 0));
        optHIPAA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optHIPAAActionPerformed(evt);
            }
        });
        jPanel5.add(optHIPAA, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel51.setText("100038");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel58.setForeground(new java.awt.Color(255, 0, 51));
        jLabel58.setText("* Mandatory Fields");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(356, 356, 356)
                        .add(jLabel51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 781, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jLabel51)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel58)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Basic Demographics", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Additional Demographics"));

        ladd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ladd1KeyTyped(evt);
            }
        });

        jLabel12.setText("Main Address");

        jLabel13.setText("City");

        jLabel14.setText("State/Prov");

        jLabel17.setText("County");

        jLabel24.setText("H. Phone");

        jLabel27.setText("W. Phone");

        jLabel28.setText("C. Phone");

        jLabel7.setText("E-mail");

        tfemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfemailKeyPressed(evt);
            }
        });

        jLabel34.setText("Religion");

        cmbReligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "ADVENTIST ", "BRETHREN", "BUDDHIST ", "BAPTIST ", "EASTERN ORTHODOX", "ISLAM", "JEWISH  ", "OTHER" }));
        cmbReligion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cmbReligionKeyTyped(evt);
            }
        });

        jLabel33.setText("Nationality");

        jLabel11.setText("Race");

        jLabel52.setText("Occupation");

        jLabel47.setText("Annual H.H. Income");

        tfAnnualHHIncome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAnnualHHIncomeFocusLost(evt);
            }
        });
        tfAnnualHHIncome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAnnualHHIncomeKeyTyped(evt);
            }
        });

        jLabel48.setText("Highest level of Ed");

        jLabel49.setText("No. of Family Members");

        tfFamilyMembers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFamilyMembersFocusLost(evt);
            }
        });
        tfFamilyMembers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfFamilyMembersKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFamilyMembersKeyTyped(evt);
            }
        });

        jLabel50.setText("Message Ph.");

        jLabel53.setText("Lang Spk at Home");

        jLabel54.setText("Ethnicity");

        try {
            lphone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        lphone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        lphone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lphoneFocusLost(evt);
            }
        });
        lphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lphoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lphoneKeyTyped(evt);
            }
        });

        try {
            wphone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        wphone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        wphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wphoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                wphoneKeyTyped(evt);
            }
        });

        try {
            cphone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cphone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        cphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cphoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cphoneKeyTyped(evt);
            }
        });

        cmbNationality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "AMERICAN", "OTHER" }));

        cmbRace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "AMERICAN INDIAN / ALASKA NATIVE", "ASIAN", "BLACK/AFRICAN AMERICAN", "MORE THAN ONE RACE", "NATIVE HAWAIIAN", "UNREPORTED/REFUSED TO REPORT", "WHITE", "OTHER PACIFIC ISLANDER", " " }));

        cmbEthnicity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "DECLINED TO ANSWER ", "HISPANIC/ LATINO ", "NOT HISPANIC/ LATINO ", "UNREPORTED/REFUSED TO REPORT", "OTHER" }));

        cmbOccupation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "ARAMARK/HERTZ", "BABYSITTER", "BAKER", "BUSINESS", "BUS BOY", "CASHIER", "CARPENTER", "CHILD", "CHILD CARE", "COOK", "CONSTRUCTION", "CUSTODIAN", "DAYCARE", "ESCROW OFFICER", "FACTORY", "FAST FOOD", "GARDENER", "HOMEMAKER", "HOUSEKEEPER", "HOUSEWIFE", "JANITOR", "LABOR", "LANDSCAPE", "MECHANIC", "PAINTER", "RETAIL", "SECRETARY", "SELF EMPLOYED", "STAYS AT HOME", "STUDENT", "STYLIST", "TEACHER", "UNEMPLOYED", "WAITRESS", "OTHER" }));

        cmbLangSpokenatHome.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "AMERICAN ENGLISH", "SPANISH", "JAPANESE", "RUSSIAN", "AFRICAN", "FRENCH", "CHINESE", "VIETNAMESE", "CANTONESE", "FILIPINO", "OTHER" }));

        cmbHighestLevelofEducation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT", "NO-SCHOOL", "SOME-SCHOOL", "SCHOOL", "GRADUATE LEVEL", "POST-GRADUATE LEVEL", "OTHER" }));

        try {
            tfMessagePhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfMessagePhone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel15.setText("ZIPCode:");

        lpin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lpinFocusLost(evt);
            }
        });
        lpin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lpinKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lpinKeyTyped(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel12)
                    .add(jLabel17)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel27)
                                        .add(jLabel24)
                                        .add(jPanel1Layout.createSequentialGroup()
                                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel28)
                                                .add(jLabel50, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                                .add(jLabel7)
                                                .add(jLabel34)
                                                .add(jLabel33)
                                                .add(jLabel11)
                                                .add(jLabel54)
                                                .add(jLabel52)
                                                .add(jLabel47, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                                .add(jLabel49)
                                                .add(jLabel53))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel15)
                                        .add(68, 68, 68))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel14)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(jLabel13)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel48)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lpin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmbEthnicity, 0, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmbOccupation, 0, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmbNationality, 0, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cmbReligion, 0, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, tfemail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .add(cmbRace, 0, 246, Short.MAX_VALUE)
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, tfAnnualHHIncome)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, tfFamilyMembers)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, cmbLangSpokenatHome, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(cmbHighestLevelofEducation, 0, 205, Short.MAX_VALUE)))
                                    .add(cphone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, lphone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, wphone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, ladd1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, tfMessagePhone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .add(lcountry, 0, 246, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(cmblstate, 0, 246, Short.MAX_VALUE)
                            .add(cmbCity, 0, 246, Short.MAX_VALUE))))
                .add(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(ladd1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(lpin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmblstate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(lcountry, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(lphone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel27)
                    .add(wphone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel28)
                    .add(cphone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel50)
                    .add(tfMessagePhone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tfemail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbReligion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel34))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbNationality, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbRace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbEthnicity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel54))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbOccupation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel52))
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tfAnnualHHIncome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel47))
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tfFamilyMembers, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel49))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbLangSpokenatHome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel53))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cmbHighestLevelofEducation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel48))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Other Contacts"));

        jLabel10.setText("Comments");

        tfeccomments.setColumns(20);
        tfeccomments.setRows(5);
        tfeccomments.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfeccommentsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfeccommentsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfeccommentsKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tfeccomments);

        lecname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lecname.setText("Contact Details");
        lecname.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setText("Emergency Contact #");

        tfec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfecFocusLost(evt);
            }
        });
        tfec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfecKeyTyped(evt);
            }
        });

        bec.setText("Search");
        bec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                becActionPerformed(evt);
            }
        });
        bec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                becKeyTyped(evt);
            }
        });

        jLabel18.setText("Guarantor #");

        tfgurantor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfgurantorFocusLost(evt);
            }
        });
        tfgurantor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfgurantorKeyTyped(evt);
            }
        });

        bgurantor.setText("Search");
        bgurantor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgurantorActionPerformed(evt);
            }
        });
        bgurantor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bgurantorKeyTyped(evt);
            }
        });

        lgurantorname.setText("Contact Details");
        lgurantorname.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lhhname.setText("Contact Details");
        lhhname.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel21.setText("Head of House Hold #");

        tfhousehold.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfhouseholdFocusLost(evt);
            }
        });
        tfhousehold.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfhouseholdKeyTyped(evt);
            }
        });

        bhousehold.setText("Search");
        bhousehold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhouseholdActionPerformed(evt);
            }
        });
        bhousehold.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bhouseholdKeyTyped(evt);
            }
        });

        lecadd.setText(" ");

        lecphone.setText(" ");

        lgurantoradd.setText(" ");

        lgurantorphone.setText(" ");

        lhhadd.setText(" ");

        lhhphone.setText(" ");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel2)
                        .add(17, 17, 17)
                        .add(tfec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel10)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel21)
                        .add(15, 15, 15)
                        .add(tfhousehold, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bhousehold, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel18)
                        .add(69, 69, 69)
                        .add(tfgurantor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bgurantor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lhhphone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lhhadd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lhhname, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lgurantorphone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lgurantoradd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lgurantorname, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lecphone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lecadd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, lecname, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(bec)
                    .add(tfec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lecname)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lecadd)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lecphone)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(bgurantor)
                    .add(tfgurantor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lgurantorname)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lgurantoradd)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lgurantorphone)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel21)
                    .add(bhousehold)
                    .add(tfhousehold, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lhhname)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lhhadd)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lhhphone)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        SlidingFeePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sliding Fee scale for Arizona"));

        jLabel63.setText("Code :");

        lblSlidingCode.setForeground(new java.awt.Color(255, 102, 51));

        jLabel65.setText("Min-Income");

        jLabel67.setText("Max-Income");

        org.jdesktop.layout.GroupLayout SlidingFeePanelLayout = new org.jdesktop.layout.GroupLayout(SlidingFeePanel);
        SlidingFeePanel.setLayout(SlidingFeePanelLayout);
        SlidingFeePanelLayout.setHorizontalGroup(
            SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, SlidingFeePanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .add(SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel63)
                    .add(jLabel67)
                    .add(jLabel65))
                .add(25, 25, 25)
                .add(SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblMax, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(lblSlidingCode, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .add(lblMin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        SlidingFeePanelLayout.setVerticalGroup(
            SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(SlidingFeePanelLayout.createSequentialGroup()
                .add(SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel63)
                    .add(lblSlidingCode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblMin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel65))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(SlidingFeePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel67)
                    .add(lblMax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(SlidingFeePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(SlidingFeePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Additional Demographics", jPanel7);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Insurance"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Company");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel9.setText("Plan Type");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel16.setText("Policy #");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel19.setText("Plan Name");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, -1));

        jLabel20.setText("Policy Type");
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel22.setText("Source");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jLabel23.setText("Effective Date");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jLabel25.setText("Exp. Date");
        jPanel9.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, -1));

        jLabel29.setText("Relationship to holder");
        jPanel9.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel30.setText("Group #");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        jLabel31.setText("SOF Insurance");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        jLabel32.setText("Date of Sign");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, -1));

        cmbPlanType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PlanType" }));
        cmbPlanType.setFocusTraversalPolicyProvider(true);
        jPanel9.add(cmbPlanType, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 130, -1));
        jPanel9.add(txtPolicyNO, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 130, -1));
        jPanel9.add(txtPlanName, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 120, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PolicyType" }));
        jPanel9.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 130, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Source" }));
        jPanel9.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 150, -1));
        jPanel9.add(jCalendarCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));
        jPanel9.add(jCalendarCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 110, -1));

        cmbRelation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRelationActionPerformed(evt);
            }
        });
        jPanel9.add(cmbRelation, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 100, -1));
        jPanel9.add(txtInsGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 120, -1));
        jPanel9.add(dtpDOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        optSOF.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        optSOF.setLabel("Signed");
        optSOF.setMargin(new java.awt.Insets(0, 0, 0, 0));
        optSOF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optSOFActionPerformed(evt);
            }
        });
        jPanel9.add(optSOF, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel64.setForeground(new java.awt.Color(255, 0, 51));
        jLabel64.setText("*");
        jPanel9.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel66.setForeground(new java.awt.Color(255, 0, 51));
        jLabel66.setText("*");
        jPanel9.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        cmbInsCompany.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Insurance Company", "12312" }));
        jPanel9.add(cmbInsCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 400, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel68.setForeground(new java.awt.Color(255, 0, 51));
        jLabel68.setText("*");
        jPanel9.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Insureds Details"));

        txtInsZip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInsZipKeyTyped(evt);
            }
        });

        cmbInsSex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "MALE", "FEMALE", "UNKNOWN" }));

        jLabel35.setText("First Name");

        jLabel37.setText(" DOB");

        jLabel41.setText(" Address");

        jLabel42.setText("ZIPcode");

        jLabel43.setText("City");

        jLabel44.setText("Phone #");

        jLabel45.setText("Employers Name");

        jLabel4.setText(" Last Name");

        jLabel61.setText(" Sex");

        jLabel62.setText(" State");

        try {
            txtInsPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtInsPhone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel69.setText("SSN");

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jLabel61)
                    .add(jLabel41)
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(jLabel42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel10Layout.createSequentialGroup()
                                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(txtInsLName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(cmbInsSex, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(29, 29, 29)
                                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel35)
                                    .add(jLabel37))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(cmbInsDOB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .add(txtInsFName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
                            .add(txtInsAdd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel45)
                            .add(jLabel44)
                            .add(jLabel69))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(txtInsPhone)
                            .add(txtInsSSN)
                            .add(txtInsEmp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .add(4, 4, 4))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(txtInsZip, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel43)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtInsCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 193, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel62)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtInsState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 254, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtInsLName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jLabel35)
                    .add(txtInsFName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel44)
                    .add(txtInsPhone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel61)
                                .add(cmbInsSex, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel37))
                            .add(cmbInsDOB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel41)
                            .add(txtInsAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel69)
                            .add(txtInsSSN, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtInsEmp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel45))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtInsZip, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtInsState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel42)
                    .add(jLabel62)
                    .add(txtInsCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel43))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Company Name", "Plan Name", "Effective Date", "Expire Date", "Plan Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chkAnyBenefit.setText("Any Other Health Benefit Plan");
        chkAnyBenefit.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chkAnyBenefit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chkAnyBenefit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAnyBenefitActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(389, 389, 389)
                        .add(chkAnyBenefit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(33, 33, 33)
                        .add(jButton1)
                        .add(64, 64, 64))
                    .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(1, 1, 1)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(22, 22, 22)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chkAnyBenefit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Insurance coverage(s)", jPanel8);

        jButton2.setText("Search Patient");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 823, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .add(save, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(update, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(back, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(next, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(reset, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(229, 229, 229)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(reset)
                    .add(next)
                    .add(back)
                    .add(jButton2)
                    .add(update, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(save))
                .add(20, 20, 20))
        );

        jPanel6.getAccessibleContext().setAccessibleName(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uncheckSOFHIPAADate(){
        jLabel59.setVisible(false);
        dtpDOHS.setVisible(false);
    }
   
    private void checkSOFHIPAADate(){
        jLabel59.setVisible(true);
        dtpDOHS.setVisible(true);
    }

     private void uncheckDOSDate(){
        jLabel32.setVisible(false);
        dtpDOS.setVisible(false);
    }

    private void checkDOSDate(){
        jLabel32.setVisible(true);
        dtpDOS.setVisible(true);
    }
    private void cmbtitleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbtitleFocusLost
// TODO add your handling code here:
        if(!cmbtitle.getSelectedItem().toString().equalsIgnoreCase("-")) {
            tfsalutation.setText(cmbtitle.getSelectedItem().toString()+ " "+lname.getText());
        }
    }//GEN-LAST:event_cmbtitleFocusLost

    private void lnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnameFocusLost
// TODO add your handling code here
        if(!cmbtitle.getSelectedItem().toString().equalsIgnoreCase("-")) {
            tfsalutation.setText(cmbtitle.getSelectedItem().toString()+ " "+lname.getText());
        }
    }//GEN-LAST:event_lnameFocusLost
/*
    private void patientCoverageSave(){
    try{
                if((cmbInsCompany.getSelectedIndex()==0)||(cmbPlanType.getSelectedIndex()==0))
                {
                    JOptionPane.showMessageDialog(null,"Company name and plan type are compulsory");
                }
                else
                {
                    JOptionPane.showConfirmDialog(null,"Adding insurance");
                    addInsuranceDetails(ipno.getText());
                    showInsuranceDetailsongrid(ipno.getText());
                    ppp=0;
                    
                    update.setEnabled(true);
                }

          //}
    }catch(Exception e){e.printStackTrace(); }
    }
*/

    private void showInsuranceDetails()
    {
        int i=jTable1.getSelectedRow();
        String ins_type=jTable1.getValueAt(i,4).toString();
        ResultSet rs;
        String sqlstring;
        Statement stmt;
        
        try{
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();            
            Connection con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            stmt = con.createStatement();
            sqlstring="SELECT  * from PATIENT_INSURANCE a,INSURANCE_COMPANY_DETAILS b " +
                    "where PATIENT_HRN = '"+ipno.getText()+"'and " +
                    "a.INSURANCE_COMPANY_ID=b.Company_Id and INSURANCETYPE='"+ins_type+"'";
            stmt = con.createStatement();
            rs=stmt.executeQuery(sqlstring);
            
            int k = 0;
            
            rs.next();
             
            int y1,m1,d1,y2,m2,d2,y3,m3,d3;
            y1=Integer.parseInt(rs.getString("EFFECTIVE_DATE").substring(0,4));
            m1=Integer.parseInt(rs.getString("EFFECTIVE_DATE").substring(5,7));
            d1=Integer.parseInt(rs.getString("EFFECTIVE_DATE").substring(8,10));            
            y2=Integer.parseInt(rs.getString("EXPIRY_DATE").substring(0,4));
            m2=Integer.parseInt(rs.getString("EXPIRY_DATE").substring(5,7));
            d2=Integer.parseInt(rs.getString("EXPIRY_DATE").substring(8,10));

            cmbInsCompany.setSelectedItem(rs.getString("Company_Name")+"-"+rs.getString("Company_Id"));
            cmbPlanType.setSelectedItem(rs.getString("INSURANCETYPE"));
            txtPolicyNO.setText(rs.getString("GROUPNO"));
            txtPlanName.setText(rs.getString("INSURANCEPLANNAME"));
            jComboBox3.setSelectedItem(rs.getString("Policy_Type"));
            jComboBox4.setSelectedItem(rs.getString("Source"));

            jCalendarCombo1.setSelectedDate(y1,m1,d1);
            jCalendarCombo2.setSelectedDate(y2,m2,d2);
            cmbRelation.setSelectedItem(rs.getString("POLICY_HOLDER_RELATION"));
            txtInsGroup.setText(rs.getString("SUBSCRIBER_ID"));

            if(rs.getBoolean("SOFInsurance")){            
                optSOF.setSelected(rs.getBoolean("SOFInsurance"));
                checkDOSDate();
                y3=Integer.parseInt(rs.getString("DOS").substring(0,4));
                m3=Integer.parseInt(rs.getString("DOS").substring(5,7));
                d3=Integer.parseInt(rs.getString("DOS").substring(8,10));
                dtpDOS.setSelectedDate(y3, m3, d3);
            }
                        
            y1=Integer.parseInt(rs.getString("POLICY_HOLDER_DOB").substring(0,4));
            m1=Integer.parseInt(rs.getString("POLICY_HOLDER_DOB").substring(5,7));
            d1=Integer.parseInt(rs.getString("POLICY_HOLDER_DOB").substring(8,10));
            if(rs.getString("Other_Holder_DOB")!=null) {
                y2=Integer.parseInt(rs.getString("Other_Holder_DOB").substring(0,4));
                m2=Integer.parseInt(rs.getString("Other_Holder_DOB").substring(5,7));
                d2=Integer.parseInt(rs.getString("Other_Holder_DOB").substring(8,10));
            }
            txtInsFName.setText(rs.getString("POLICY_HOLDER_FNAME"));
            txtInsLName.setText(rs.getString("POLICY_HOLDER_LNAME"));
            
            cmbInsSex.setSelectedItem(rs.getString("POLICY_HOLDER_SEX"));
            txtInsSSN.setText(rs.getString("POLICY_HOLDER_SSN"));
            txtInsAdd.setText(rs.getString("Policy_Holder_Address"));
            txtInsZip.setText(rs.getString("Policy_Holder_Zipcode"));
            txtInsCity.setText(rs.getString("Policy_Holder_City"));
            txtInsState.setText(rs.getString("Policy_Holder_State"));
            txtInsPhone.setText(rs.getString("Policy_Holder_Phone"));
            txtInsEmp.setText(rs.getString("Policy_Holder_EmpSchool"));
            chkAnyBenefit.setSelected(rs.getBoolean("AnyOtherHealthBenfitPlan"));
            cmbInsDOB.setSelectedDate(y1,m1,d1);
            
        }catch (Exception asa){
            asa.printStackTrace();
        }
}
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
// TODO add your handling code here:
   try{
       if(jTable1.getRowCount()>0){
            showInsuranceDetails();
       }
   }catch(Exception e){e.printStackTrace();}        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
// TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane3MouseClicked

    
    private void updateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()=='\n') {
            saveUpdate(false);
        }
    }//GEN-LAST:event_updateKeyPressed

    private void tfhouseholdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfhouseholdFocusLost
// TODO add your handling code here:
        bhhpress();
    }//GEN-LAST:event_tfhouseholdFocusLost

    private void tfgurantorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfgurantorFocusLost
// TODO add your handling code here:
        bgpress();
    }//GEN-LAST:event_tfgurantorFocusLost

    private void tfecFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfecFocusLost
// TODO add your handling code here:
        becpress();
    }//GEN-LAST:event_tfecFocusLost

    private void tfeccommentsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfeccommentsKeyReleased
// TODO add your handling code here:
        if(evt.getKeyCode()==9) {
            evt.consume();
        }
    }//GEN-LAST:event_tfeccommentsKeyReleased

    private void tacommentsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tacommentsKeyPressed
// TODO add your handling code here:
    if(evt.getKeyCode()==9){
            if(save.isEnabled()) {
                save.requestFocus();
            }
            else {
                update.requestFocus();
            }
            evt.consume();
        }
    }//GEN-LAST:event_tacommentsKeyPressed

    private void dobVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_dobVetoableChange
// TODO add your handling code here:
        cmbtitle.requestFocus();
    }//GEN-LAST:event_dobVetoableChange

    private void bhouseholdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bhouseholdKeyTyped
// TODO add your handling code here:
        if(evt.getKeyChar()=='\n') {
                bhhpress();
                tfeccomments.requestFocus();
        }
    }//GEN-LAST:event_bhouseholdKeyTyped

    private void bgurantorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bgurantorKeyTyped
// TODO add your handling code here:
           if(evt.getKeyChar()=='\n') {
                bgpress();
                tfhousehold.requestFocus();
        }
    }//GEN-LAST:event_bgurantorKeyTyped

    private void becKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_becKeyTyped
// TODO add your handling code here:
        if(evt.getKeyChar()=='\n') {
            becpress();
            tfgurantor.requestFocus();
        }
    }//GEN-LAST:event_becKeyTyped

    private void dobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobFocusLost
// TODO add your handling code here:
    
    }//GEN-LAST:event_dobFocusLost

    private void cmbReligionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbReligionKeyTyped
// TODO add your handling code here:
        if(evt.getKeyCode()==9){
            cmbNationality.requestFocus();
        }
}//GEN-LAST:event_cmbReligionKeyTyped

    private void tfeccommentsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfeccommentsKeyTyped
// TODO add your handling code here:
        if(evt.getKeyCode()==9){
            evt.consume();
        }
    }//GEN-LAST:event_tfeccommentsKeyTyped

    private void tfeccommentsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfeccommentsKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()==9){
            if(save.isEnabled()) {
                save.requestFocus();
            }
            else {
                update.requestFocus();
            }
            evt.consume();
        }
    }//GEN-LAST:event_tfeccommentsKeyPressed

    private void tfemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfemailKeyPressed
// TODO add your handling code here:
           if(evt.getKeyCode()==9){
            tfec.requestFocus();
        }
    }//GEN-LAST:event_tfemailKeyPressed
    public void showInsuranceDetailsongrid(String hrn)
    {
        ResultSet rs;
        String sqlstring;
        Statement stmt;
        String[][] m = new String[5][5];
        int k = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();           
            Connection con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
            stmt = con.createStatement();
            
            sqlstring="SELECT  * from PATIENT_INSURANCE a,INSURANCE_COMPANY_DETAILS b where PATIENT_HRN = '"+hrn+"'and a.INSURANCE_COMPANY_ID=b.Company_Id";
            rs=stmt.executeQuery(sqlstring);                             
             
            while(rs.next()){
            InsurancePlan ob = new InsurancePlan();      

            ob.setcomp(rs.getString("Company_Name"));
            ob.setpt(rs.getString("INSURANCETYPE"));
            
            ob.setpn(rs.getString("INSURANCEPLANNAME"));
            
            ob.seteffdate(rs.getString("EFFECTIVE_DATE").substring(5,7)+"/"+rs.getString("EFFECTIVE_DATE").substring(8,10)+"/"+rs.getString("EFFECTIVE_DATE").substring(0,4));
            ob.setexpdate(rs.getString("EXPIRY_DATE").substring(5,7)+"/"+rs.getString("EXPIRY_DATE").substring(8,10)+"/"+rs.getString("EXPIRY_DATE").substring(0,4));
            
            m[k][0] = ob.getcomp();
            m[k][1] = ob.getpn();       
            m[k][2] = ob.geteffdate();
            m[k][3] = ob.getexpdate();
            m[k][4] = ob.getpt();
            k++;
              }
    }
    catch (Exception asa){
        asa.printStackTrace();
    }
         String[][] arr=new String[k][5];
         for(int u=0;u<k;u++)
         {
           arr[u][0]=m[u][0];
           arr[u][1]=m[u][1];
           arr[u][2]=m[u][2];
           arr[u][3]=m[u][3];
           arr[u][4]=m[u][4];
         }
        
        jTable1.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"Company Name","Plan Name","Effective Date","Expire Date","Plan Type"}
         )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );

        if(k>1) {
            jTable1.setRowSelectionInterval(0,0);
            showInsuranceDetails();
        }
    }
    
    private void tfhouseholdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfhouseholdKeyTyped
    // TODO add your handling code here:
        if(evt.getKeyChar()=='\n') {
            bhhpress();
            tfeccomments.requestFocus();
        }
    }//GEN-LAST:event_tfhouseholdKeyTyped

    private void tfgurantorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfgurantorKeyTyped
    // TODO add your handling code here:
        if(evt.getKeyChar()=='\n') {
            bgpress();
            tfhousehold.requestFocus();
        }
    }//GEN-LAST:event_tfgurantorKeyTyped

    private void tfecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfecKeyTyped
// TODO add your handling code here:

        if(evt.getKeyChar()=='\n') {
            becpress();
            tfgurantor.requestFocus();
        }
        
    }//GEN-LAST:event_tfecKeyTyped

    private void bhouseholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhouseholdActionPerformed
    // TODO add your handling code here:      
        bhhpress();        
    }//GEN-LAST:event_bhouseholdActionPerformed

    private void bhhpress() {
        if(tfhousehold.getText().trim().equalsIgnoreCase("")) {}
        else{
        
            PatientInformation ob=new PatientInformation();
            Connect2MySql connect=new Connect2MySql();

            ob=connect.getPatDetailsByHrn(tfhousehold.getText());
            if(ob==null){
                lhhname.setText("");
                lhhadd.setText("");
                lhhphone.setText("");
                tfhousehold.setText("");
                tfhousehold.requestFocus();
            }
            else
            {
            lhhname.setText(ob.getFirstName()+ " " + ob.getLastName());
            lhhadd.setText(ob.getLocalAdd()+" "+ob.getLocalAdd2());
            lhhphone.setText(ob.getLocalphone()+","+ob.getWPhone());
            tfeccomments.requestFocus();
           }
        }
        
    }
     
    private void bgurantorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgurantorActionPerformed
// TODO add your handling code here:      
            bgpress();
        
    }//GEN-LAST:event_bgurantorActionPerformed
    private void bgpress() {
        if(tfgurantor.getText().trim().equalsIgnoreCase("")) {}
        else{
            
            PatientInformation ob=new PatientInformation();
            Connect2MySql connect=new Connect2MySql();
             
            ob=connect.getPatDetailsByHrn(tfgurantor.getText());
            if(ob==null){
                lgurantorname.setText("");
                lgurantoradd.setText("");
                lgurantorphone.setText("");
                tfgurantor.setText("");
                tfgurantor.requestFocus();
            }
            else
            {
                lgurantorname.setText(ob.getFirstName()+ " " + ob.getLastName());
                lgurantoradd.setText(ob.getLocalAdd()+" "+ob.getLocalAdd2());
                lgurantorphone.setText(ob.getLocalphone()+","+ob.getWPhone());
                tfhousehold.requestFocus();
            }
        }
    }
    private void becActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_becActionPerformed
// TODO add your handling code here:
        becpress();
        tfgurantor.requestFocus();
            
    }//GEN-LAST:event_becActionPerformed
    private void becpress() {
        if(tfec.getText().trim().equalsIgnoreCase("")) {}
        else{
        
            PatientInformation ob=new PatientInformation();
            Connect2MySql connect=new Connect2MySql();
            
            ob=connect.getPatDetailsByHrn(tfec.getText());
               if(ob==null){
                lecname.setText("");
                lecadd.setText("");
                lecphone.setText("");
                tfec.setText("");
                tfec.requestFocus();
            }
            else
            {
            lecname.setText(ob.getFirstName()+ " " + ob.getLastName());
            lecadd.setText(ob.getLocalAdd()+" "+ob.getLocalAdd2());
            lecphone.setText(ob.getLocalphone()+","+ob.getWPhone());
            tfgurantor.requestFocus();            
            }
        }
    }
    private void enterageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enterageFocusLost
// TODO add your handling code here:
        
                if(enterage.isSelected()) {
                    age.setVisible(true);
                    age.requestFocus();
                }
                else {
                    dob.setVisible(true);
                    dob.requestFocus();
                }
    }//GEN-LAST:event_enterageFocusLost

    private void ageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageKeyTyped
// TODO add your handling code here:
        if(evt.getKeyCode() == 9){
            tfssn.requestFocus();
        }
    }//GEN-LAST:event_ageKeyTyped

    private void ageunitsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageunitsFocusLost
// TODO add your handling code here:
       
    }//GEN-LAST:event_ageunitsFocusLost

    private void ladd1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ladd1KeyTyped
// TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
        char temp;
        if (ascii >=97 && ascii <=122 ) {
            ascii=ascii-32;            
            temp = (char)ascii;
            evt.setKeyChar(temp);
        }
    }//GEN-LAST:event_ladd1KeyTyped

    private void lnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnameKeyTyped
// TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
        char temp;
        if (ascii >=97 && ascii <=122 ) {
            ascii=ascii-32;            
            temp = (char)ascii;
            evt.setKeyChar(temp);
        }
        if(evt.getKeyCode()==9) {}              
    }//GEN-LAST:event_lnameKeyTyped

    /*
    private void validation_text(java.awt.event.KeyEvent evt) {
        if (fname.getText().length() >= 30 ) 
        {
                evt.consume();           
            }
        else
        {}

    }
     */
    private void fnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameKeyTyped
// TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
        char temp;
        if (ascii >=97 && ascii <=122 ) {
            ascii=ascii-32;            
            temp = (char)ascii;
            evt.setKeyChar(temp);
        }
        if(evt.getKeyCode()==9) {
            sex.requestFocus();
        }
            
    }//GEN-LAST:event_fnameKeyTyped

    private void ipnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipnoKeyPressed
// TODO add your handling code here:        
        if(evt.getKeyCode() == '\n'){
           lname.requestFocus();
        }
    }//GEN-LAST:event_ipnoKeyPressed

    private void ipnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipnoKeyReleased
// TODO add your handling code here:
        if(evt.getKeyCode() == 9){
           lname.requestFocus();
        }
    }//GEN-LAST:event_ipnoKeyReleased

    private void ipnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipnoFocusLost
// TODO add your handling code here:
        searchPatientByHRN(ipno.getText());
    }//GEN-LAST:event_ipnoFocusLost

    private void enterageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterageKeyPressed
// TODO add your handling  code here:
            if(evt.getKeyCode() == 9){
                if(enterage.isSelected()) {
                    age.setVisible(true);
                    age.requestFocus();
                }
                else {
                    dob.setVisible(true);
                    dob.requestFocus();
                }
        }            
    }//GEN-LAST:event_enterageKeyPressed

    private void ageunitsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageunitsKeyReleased
// TODO add your handling code here:
         if(evt.getKeyCode() == 9){}
    }//GEN-LAST:event_ageunitsKeyReleased

    private void ageunitsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageunitsKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode() == 9){}
    }//GEN-LAST:event_ageunitsKeyPressed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
// TODO add your handling code here:
        saveUpdate(false);
        
    }//GEN-LAST:event_updateActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
// TODO add your handling code here:
        try{
            int i=Integer.parseInt(ipno.getText());
            i++;
            callByBackNext=false;
            searchPatient(""+i);
            int abc=Integer.parseInt(jLabel51.getText())+1;
            jLabel51.setText(""+abc);
            ipno.setText(""+abc);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
// TODO add your handling code here:
        
        try{
            int i=Integer.parseInt(ipno.getText());
            i--;
            String tcno=""+i;
            callByBackNext=false;
            searchPatient(tcno);
            int abc=Integer.parseInt(jLabel51.getText())-1;
            
                jLabel51.setText(""+abc);
                ipno.setText(""+abc);
          
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_backActionPerformed
    
    private void searchPatientByHRN(String hrn){
            setDefault();
            PatientInformation ob=new PatientInformation();
            Connect2MySql connect=new Connect2MySql();
           
            ob=connect.getPatDetailsByHrn(hrn);
            
            if(ob !=  null){
                save.setEnabled(false);
                update.setEnabled(true);
                showPatientDetails(ob);
                jLabel51.setText(ob.getIpNo());
            }else{
                String temp="";
                temp=getHRN();
               
            }
         
            lname.requestFocus();
      
    }
    
    private void regtimeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regtimeKeyPressed
// TODO add your handling code here:
        try{
        if(evt.getKeyChar() == '\n' && regtime.getText().length() >= 8){
            regtime.setText(regtime.getText().substring(0,8));
            lname.requestFocus();
        }
        }catch(Exception e){
            e.printStackTrace();
        }
      
    }//GEN-LAST:event_regtimeKeyPressed

    private void regtimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regtimeKeyReleased
// TODO add your handling code here:
            String temp=regtime.getText();
            
            try{
                if(temp.charAt(2) != ':')
                    regtime.setText(temp.substring(0,2)+":");
            }catch(Exception e){
                   e.printStackTrace();
            }
            
            try{
                if(temp.charAt(5) != ':')
                    regtime.setText(temp.substring(0,5)+":");
            }catch(Exception e){
                   e.printStackTrace();
            }
            
            try{
                if(temp.length() == 2){
                    regtime.setText(temp+":");
                }
            }catch(Exception e){
                   e.printStackTrace();
            }
            
            try{    
                if(temp.length() == 5){
                    regtime.setText(temp+":");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
            try{
                if(temp.length() == 8){
                    lname.requestFocus();
                }
            }catch(Exception e){
            
               e.printStackTrace();
            }
            try{
                if(temp.length() > 8){
                    regtime.setText(regtime.getText().substring(0,8));
                    lname.requestFocus();
                }
            }catch(Exception e){
                e.printStackTrace();
            }            
    }//GEN-LAST:event_regtimeKeyReleased

    private void regtimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regtimeFocusLost
// TODO add your handling code here:
        String temp=regtime.getText();
        boolean flag=true;
        
        if(temp.charAt(2) == ':' && temp.charAt(5) == ':'){
            if(Integer.parseInt(temp.substring(0,2)) > 24){
                JOptionPane.showMessageDialog(null,"Invalid time format");
                regtime.setText("");
                regtime.requestFocus();
                flag=false;
            }
            if(Integer.parseInt(temp.substring(3,5)) >= 60 && flag){
                JOptionPane.showMessageDialog(null,"Invalid time format");
                regtime.setText("");
                regtime.requestFocus();
                flag=false;
            }         
            if(Integer.parseInt(temp.substring(6,8).trim().replace("\n","")) >= 60 && flag){
                regtime.requestFocus();
                JOptionPane.showMessageDialog(null,"Invalid time format");
            }
        }else{
            regtime.requestFocus();
             regtime.setText("");
            JOptionPane.showMessageDialog(null,"Invalid time format"); 
        }  
        
    }//GEN-LAST:event_regtimeFocusLost

    private void saveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saveKeyPressed
// TODO add your handling code here:
        if(evt.getKeyChar() == '\n'){
            saveUpdate(true);
        }
    }//GEN-LAST:event_saveKeyPressed

    private void enterageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterageActionPerformed
// TODO add your handling code here:
        java.util.Date today;
        java.text.SimpleDateFormat formatter;
        today = new java.util.Date();
        formatter = new java.text.SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(today);
        int i=0;
        int j=0;
        int x=0;
        if(enterage.isSelected()){
            
            agelabel.setText("Age");
            dob.setVisible(false);
            age.setVisible(true);
            age.requestFocus();
            ageunits.setVisible(true);
            ageunits.setSelectedIndex(0);
            age.setText("");
            if(dob.getSelectedDate().equalsIgnoreCase("0/0/0")){
                
            }else{
                if(dob.getSelectedYear().equalsIgnoreCase(date.substring(0,4))){
                    age.setText("");
                    if(dob.getSelectedMonth().equalsIgnoreCase(date.substring(0,4))){
                        
                    }else{
                        if(dob.getSelectedDay().equalsIgnoreCase(date.substring(6,8)))
                        {
                            age.setText("");
                            ageunits.setSelectedIndex(0);
                        }
                        else {
                        ageunits.setSelectedIndex(1);
                        i=0;
                        j=0;
                        x=0;
                        x=(Integer.parseInt(date.substring(4,6))-Integer.parseInt(dob.getSelectedMonth()));
                        if(x < 0){
                            x=x+12;
                        }else{
                            if(x==0) {
                                age.setText("");
                            }
                            else {
                                
                            age.setText(""+x);
                            }
                        }
                        
                     }
                    }
                }else{
                    i=0;
                    i=(Integer.parseInt(date.substring(0,4))-Integer.parseInt(dob.getSelectedYear()));
                    if(i != 1){
                        if(i==0) {
                            age.setText("");
                        }
                        else {
                            age.setText(""+i); 
                        }
                    }else{
                        if(Integer.parseInt(dob.getSelectedMonth()) < Integer.parseInt(date.substring(4,6))){
                            
                            if (i==0) {
                                age.setText("");
                            }
                            else {
                                age.setText(""+i); 
                            }
                        }else{
                               if(dob.getSelectedDay().equalsIgnoreCase(date.substring(6,8)))
                                {
                                    age.setText("");
                                    ageunits.setSelectedIndex(0);
                                }
                                else {
                        
                                ageunits.setSelectedIndex(1);
                            
                            j=0;
                            j=Integer.parseInt(date.substring(4,6))-Integer.parseInt(dob.getSelectedMonth())+ 12;
                            if(j==0) {
                                age.setText("");
                            }
                            else {
                                age.setText(""+j); 
                            }                            
                        }
                        }
                    }
                }
                
            }
        }else{
            dob.requestFocus();
            agelabel.setText("DOB");
            dob.setVisible(true);
            
            age.setVisible(false);
            ageunits.setVisible(false);
            if(ageunits.getSelectedIndex() == 0){
                if(age.getText() == null || age.getText().equalsIgnoreCase("")){
                    dob.setSelectedDate(Integer.parseInt(date.substring(0,4)),
                           Integer.parseInt(date.substring(4,6)),
                            Integer.parseInt(date.substring(6))); 
                }else{
                    try{
                   dob.setSelectedDate(Integer.parseInt(date.substring(0,4))-Integer.parseInt(age.getText()),
                           Integer.parseInt(dob.getSelectedMonth()),
                           Integer.parseInt(dob.getSelectedDay())); 
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }else{
                if(ageunits.getSelectedIndex() == 1){
                if(age.getText() == null || age.getText().equalsIgnoreCase("")){
                    dob.setSelectedDate(Integer.parseInt(date.substring(0,4)),
                           Integer.parseInt(date.substring(4,6)),
                            Integer.parseInt(date.substring(6))); 
                }else{
                       try{
                       i=0;
                       j=0;
                       x=0;
                       i=Integer.parseInt(age.getText())%12;
                       j=Integer.parseInt(age.getText())/12;
                       x=Integer.parseInt(date.substring(4,6))-i;
                       if( x <= 0){
                           j++;
                           x=x+12;
                       }
                       dob.setSelectedDate(Integer.parseInt(date.substring(0,4))-j,
                               x,
                               Integer.parseInt(dob.getSelectedDay())); 
                       }catch(Exception e){
                            e.printStackTrace();
                       }  
                       }
                }
            }
        }
    }//GEN-LAST:event_enterageActionPerformed
 
    private void saveUpdate(boolean upsa){
    try{
        int z=0;
         boolean flag = true;       
        
        PatientInformation ob=new PatientInformation();
        String temp="";
        String te="";
        int t;
        int x=0;
        
        java.util.Date today;
        java.text.SimpleDateFormat formatter;
        today = new java.util.Date();
        formatter = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
        String date = formatter.format(today);        
        
        String tempdate="";        
                    
        ob.setTcno(ipno.getText());
        ob.setIpNo(ipno.getText());
        
        tempdate=registerdate.getSelectedYear();
        temp=registerdate.getSelectedMonth();
        if(temp.length() == 1){
                    tempdate=tempdate+"0"+temp;
        }else{
                    tempdate=tempdate+temp;
        }
        
        temp=registerdate.getSelectedDay();
        
        if(temp.length() == 1){
                  tempdate=tempdate+"0"+temp;
        }else{
                  tempdate=tempdate+temp;
        }
         
        ob.setRegistrationDate(tempdate+regtime.getText().replace(":",""));
        
        temp="";
        temp=lname.getText();
        if(temp.equalsIgnoreCase("") && flag){
    
              ob.setLastName("lname");  
        }else{
            ob.setLastName(temp);
        }
        temp="";
        temp=fname.getText();
        if(temp.equalsIgnoreCase("") && flag){
            JOptionPane.showMessageDialog(null,"First Name is empty");
            fname.requestFocus();
            flag=false;
        }else{
            ob.setFirstName(temp);
        }        
            ob.setMiddleName(tfMiddleName.getText());
                
        date=date.substring(0,8);
        if(sex.getSelectedIndex() == 0 && flag){
            JOptionPane.showMessageDialog(null,"Enter sex");
            sex.requestFocus();
            flag=false;
        }else{
            ob.setSex(sex.getSelectedItem().toString());
        }
        
        if(flag){
        if(enterage.isSelected()){
            try{
                t=Integer.parseInt(age.getText());
                ob.setAge(age.getText());
                ob.setAgeUnits(ageunits.getSelectedIndex()+"");
                if(ageunits.getSelectedIndex() == 0)
                    x=Integer.parseInt(date.substring(0,4))-t;
                else
                    x=Integer.parseInt(date.substring(0,4));
                ob.setDOB(x+"0101");
          
            }catch(Exception e){
                e.printStackTrace();
                if(flag)
                    JOptionPane.showMessageDialog(null,"Age must be numeric");                    
                flag=false;
            }
            
        }else{
            if(dob.getSelectedDate().equalsIgnoreCase("0/0/0")){
                z=0;
                z=JOptionPane.showConfirmDialog(null, "Patient Date of birth is not set \nDo you want to continue?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(z == 1){
                    flag=false;
                }else{
                    ob.setDOB(null);
                }
                
            }else{
                x=Integer.parseInt(dob.getSelectedYear());
                tempdate=dob.getSelectedYear();
                te=dob.getSelectedMonth();
                if(te.length() == 1){
                        tempdate=tempdate+"0"+te;
                }else{
                        tempdate=tempdate+te;
                }
                te=dob.getSelectedDay();
                if(te.length() == 1){
                      tempdate=tempdate+"0"+te;
                }else{
                      tempdate=tempdate+te;
                }
                
                ob.setDOB(tempdate);

                x=Integer.parseInt(date.substring(0,4))-x;
                ob.setAge(x+"");
            }
        }
    }
        if(ladd1.getText().length() > 30 && flag){
            
            JOptionPane.showMessageDialog(null,"Local Address must be less then 30 character");
            ladd1.requestFocus();
            flag=false;
        }else{
            ob.setLocalAdd(ladd1.getText());
        }
          
        if(cmbCity.getSelectedItem()!=null){
        ob.setLocalCity(cmbCity.getSelectedItem().toString());
        }

        if(lcountry.getSelectedItem()!=null){
        ob.setLocalCountry(lcountry.getSelectedItem().toString());
        }
        
        if(lpin.getText().length() > 6 && flag){
            
            JOptionPane.showMessageDialog(null,"Local pin code must be 6-digits");
            lpin.requestFocus();
            flag=false;
        }else{
            ob.setLocalPin(lpin.getText());
        }
        
        if(cmblstate.getSelectedIndex() == -1){
            ob.setLocalState("");
        }else{            
            if(cmblstate.getSelectedIndex()!=0)
            ob.setLocalState(((String)cmblstate.getSelectedItem()).substring(0,((String)cmblstate.getSelectedItem()).indexOf(",")));
        }
        
        if(lphone.getText().equalsIgnoreCase("") || lphone.getText() == null)
            ob.setLocalphone("");
        else
            ob.setLocalphone(lphone.getText());
        
        if(wphone.getText().equalsIgnoreCase("") || wphone.getText() == null)
            ob.setWPhone("");
        else
            ob.setWPhone(wphone.getText());
            
        if(cphone.getText().equalsIgnoreCase("") || cphone.getText() == null)
            ob.setCPhone("");
        else
            ob.setCPhone(cphone.getText());                
        
        ob.setReligion((String)cmbReligion.getSelectedItem());

        ob.setNationality(cmbNationality.getSelectedItem().toString());
        ob.setrace(cmbRace.getSelectedItem().toString());
        ob.setEthnicity(cmbEthnicity.getSelectedItem().toString());
        ob.setoccupation(cmbOccupation.getSelectedItem().toString());
        ob.setAnnualIncome(tfAnnualHHIncome.getText());
        ob.setMessagePhone(tfMessagePhone.getText());
        ob.setFamilyMembers(tfFamilyMembers.getText());
        ob.setLangSpoken(cmbLangSpokenatHome.getSelectedItem().toString());
        ob.setHighestLevelofEducation(cmbHighestLevelofEducation.getSelectedItem().toString());
        
        ob.setSSN(tfssn.getText());
        ob.setTitle(cmbtitle.getSelectedItem().toString());
        ob.setSalutation(tfsalutation.getText());
        ob.setMS(cmbms.getSelectedItem().toString());
        ob.setComments(tacomments.getText());
        ob.setRemarks(tfeccomments.getText());       
        ob.setEmail(tfemail.getText());
        ob.setEC(tfec.getText());
        ob.setGuarantor(tfgurantor.getText());
        ob.setHOH(tfhousehold.getText());

        ob.setSlidingCode(lblSlidingCode.getText());

        if(optHIPAA.isSelected()==true){
            ob.setSOF("Y");
            ob.setDOS(dtpDOHS.getSelectedYear()+"/"+dtpDOHS.getSelectedMonth()+"/"+dtpDOHS.getSelectedDay());
        }else{
            ob.setSOF("N");            
        }     
       
        z=0;
        if(flag){
           int strAddUpd=0;
           boolean printpatinfo=false;
           Connect2MySql store=new Connect2MySql();
           
            PatientInformationMethods pim=new PatientInformationMethods();

            int chkIns=pim.checkPatientInsurance(ipno.getText(),cmbPlanType.getSelectedItem().toString());
            
            if(save.isEnabled()){
                int a=0;
                a=expdatedetail();
                
               if(a==1){
                strAddUpd=store.storePatient(ob,printpatinfo);
               if((chkIns==0) && (strAddUpd==1)){
               if(cmbInsCompany.getSelectedIndex()>=1) {
                   int res=addInsuranceDetails(ipno.getText());
                   if(res==1){
                       showInsuranceDetailsongrid(ipno.getText());
                       ppp=0;                      
                    }
                }
                }
                save.setEnabled(false);
                update.setEnabled(true);
                JOptionPane.showMessageDialog(null,"Patient Saved Successfully");
             }else{JOptionPane.showMessageDialog(null, "Expiry Date should be greater than the Effective Date");}
           }else{               
                int a=0;
                a=expdatedetail();
                if(a==1){
                strAddUpd=store.updatePatient(ob,printpatinfo);
               
                if((chkIns==0) && (strAddUpd==1)){
                if(cmbInsCompany.getSelectedIndex()>=1){
                    int res=addInsuranceDetails(ipno.getText());
                   if(res==1){
                       showInsuranceDetailsongrid(ipno.getText());
                       ppp=0;                       
                    }
                }
                }
                
                if((chkIns==1) && (strAddUpd==1)){
                if(cmbInsCompany.getSelectedIndex()>=1){
                    updateInsuranceDetails(ipno.getText());
                    showInsuranceDetailsongrid(ipno.getText());
                }
                }
                JOptionPane.showMessageDialog(null,"Patient Updated Successfully");
              }else{JOptionPane.showMessageDialog(null, "Expiry Date should be greater than the Effective Date");}
             }
        }
   }catch(Exception e){e.printStackTrace();}
    }

    private void updateInsuranceDetails(String HRN)
    {
            PatientInsurance ob=new PatientInsurance();

            ob.setcomp(cmbInsCompany.getSelectedItem().toString().split("-")[1]);
            ob.setpt(cmbPlanType.getSelectedItem().toString());
            ob.setgn(txtPolicyNO.getText());
            ob.setpn(txtPlanName.getText());
            ob.setpoty(jComboBox3.getSelectedItem().toString());
            ob.setsource(jComboBox4.getSelectedItem().toString());
            ob.seteffdate(jCalendarCombo1.getSelectedYear()+"/"+jCalendarCombo1.getSelectedMonth()+"/"+jCalendarCombo1.getSelectedDay());
            ob.setexpdate(jCalendarCombo2.getSelectedYear()+"/"+jCalendarCombo2.getSelectedMonth()+"/"+jCalendarCombo2.getSelectedDay());
            ob.setrh(cmbRelation.getSelectedItem().toString());
            ob.setinsid(txtInsGroup.getText());
            
            if(optSOF.isSelected()){
                ob.setsof(optSOF.isSelected());
                ob.setdsign(dtpDOS.getSelectedYear()+"/"+dtpDOS.getSelectedMonth()+"/"+dtpDOS.getSelectedDay());
            }else{
                ob.setsof(optSOF.isSelected());
            }
            
            ob.setInsuredFName(txtInsFName.getText());
            ob.setInsuredLName(txtInsLName.getText());
            ob.setidob(cmbInsDOB.getSelectedYear()+"/"+cmbInsDOB.getSelectedMonth()+"/"+cmbInsDOB.getSelectedDay());
           
            if(cmbInsSex.getSelectedItem()==null) {
                ob.setisex(null);
            } else {
            ob.setisex(cmbInsSex.getSelectedItem().toString());
            }
            ob.setiadd(txtInsAdd.getText());
            ob.setizcode(txtInsZip.getText());
            ob.seticity(txtInsCity.getText());
            ob.setistate(txtInsState.getText());
            ob.setiphone(txtInsPhone.getText());
            ob.setiename(txtInsEmp.getText());
            ob.setissn(txtInsSSN.getText());
            ob.setanyother(chkAnyBenefit.isSelected());
    
            String sqlstring;
            Statement stmt;
        
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
                stmt = con.createStatement();
              
                sqlstring="update PATIENT_INSURANCE set "+
                    "INSURANCE_COMPANY_ID = '" +ob.getcomp()+"',"+
                    "GROUPNO = '" +ob.getgn()+"',"+
                    "INSURANCEPLANNAME = '" +ob.getpn()+"',"+
                    "Policy_Type = '" +ob.getpoty()+"',"+
                    "Source = '" +ob.getsource()+"',"+
                    "EFFECTIVE_DATE = '" +ob.geteffdate()+"',"+
                    "EXPIRY_DATE = '" +ob.getexpdate()+"',"+
                    "POLICY_HOLDER_RELATION = '" +ob.getrh()+"',"+
                    "SUBSCRIBER_ID = '" +ob.getinsid()+"',"+
                    "SOFInsurance = '" +ob.getsof()+"'";

                    if(ob.getdsign() == null){
                        sqlstring=sqlstring+",DOS =Null";
                    }else{
                        sqlstring=sqlstring+",DOS = '" +ob.getdsign()+"'";
                    }
                
                    sqlstring=sqlstring+",POLICY_HOLDER_FNAME= '" + ob.getInsuredFName()+"',"+
                    "POLICY_HOLDER_LNAME= '" + ob.getInsuredLName() +"',"+
                    "POLICY_HOLDER_DOB= '" + ob.getidob()+"',"+
                    "POLICY_HOLDER_SEX= '" + ob.getisex()+"',"+
                    "POLICY_HOLDER_SSN= '" + ob.getissn()+"',"+
                    "Policy_Holder_Address= '" + ob.getiadd()+"',"+
                    "Policy_Holder_Zipcode= '" + ob.getizcode()+"',"+
                    "Policy_Holder_City= '" + ob.geticity()+"',"+
                    "Policy_Holder_State= '" + ob.getistate()+"',"+
                    "Policy_Holder_Phone= '" + ob.getiphone()+"',"+
                    "Policy_Holder_EmpSchool= '" + ob.getiename()+"',"+
                    "AnyOtherHealthBenfitPlan= '" + ob.getanyother()+"',"+
                    "Other_Holder_Name= '" + ob.getoename()+"',";
                
                    if(ob.getoidob()!=null) {
                        sqlstring=sqlstring+"Other_Holder_DOB= '" + ob.getoidob()+"',";
                    }
                    sqlstring=sqlstring+"Other_Holder_Sex= '" + ob.getoisex()+"',"+
                    "Other_Holder_Group= '" + ob.getogno()+"',"+
                    "Other_Holder_EmpSchool= '" + ob.getoename()+"',"+
                    "Other_Holder_PlanName= '" + ob.getopname()+"'"+        
                    " where PATIENT_HRN='"+ipno.getText()+"' and " +
                    "INSURANCETYPE='"+cmbPlanType.getSelectedItem()+"'";
                stmt.executeUpdate(sqlstring);
                con.close();
                stmt.close();
            }
            catch(Exception saa)
            {
                saa.printStackTrace();
            }
           
    }
    
    private int addInsuranceDetails(String HRN){
    try{
        if((cmbInsCompany.getSelectedIndex()==0)||(cmbPlanType.getSelectedIndex()==0)) {
                JOptionPane.showMessageDialog(null,"Company name and plan type are compulsory");
        } else {
                PatientInsurance ob=new PatientInsurance();
                ob.setcomp(cmbInsCompany.getSelectedItem().toString().split("-")[1]);
                ob.setpt(cmbPlanType.getSelectedItem().toString());
                ob.setgn(txtPolicyNO.getText());
                ob.setpn(txtPlanName.getText());
                ob.setpoty(jComboBox3.getSelectedItem().toString());
                ob.setsource(jComboBox4.getSelectedItem().toString());
                ob.seteffdate(jCalendarCombo1.getSelectedYear()+"/"+jCalendarCombo1.getSelectedMonth()+"/"+jCalendarCombo1.getSelectedDay());
                ob.setexpdate(jCalendarCombo2.getSelectedYear()+"/"+jCalendarCombo2.getSelectedMonth()+"/"+jCalendarCombo2.getSelectedDay());
                ob.setrh(cmbRelation.getSelectedItem().toString());
                ob.setinsid(txtInsGroup.getText());
                
                if(optSOF.isSelected()){
                    ob.setsof(optSOF.isSelected());
                    ob.setdsign(dtpDOS.getSelectedYear()+"/"+dtpDOS.getSelectedMonth()+"/"+dtpDOS.getSelectedDay());
                }else{
                    ob.setsof(optSOF.isSelected());
                }
                
                ob.setInsuredFName(txtInsFName.getText());
                ob.setInsuredLName(this.txtInsLName.getText());
                ob.setidob(cmbInsDOB.getSelectedYear()+"/"+cmbInsDOB.getSelectedMonth()+"/"+cmbInsDOB.getSelectedDay());
                ob.setisex(cmbInsSex.getSelectedItem().toString());
                ob.setissn(txtInsSSN.getText());
                ob.setiadd(txtInsAdd.getText());
                ob.setizcode(txtInsZip.getText());
                ob.seticity(txtInsCity.getText());
                ob.setistate(txtInsState.getText());
                ob.setiphone(txtInsPhone.getText());
                ob.setiename(txtInsEmp.getText());
                ob.setoisex(cmbInsSex.getSelectedItem().toString());

                ResultSet rs;
                String sqlstring;
                Statement stmt;

                try{
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String url = "jdbc:mysql:///"+dbname;

                    Connection con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
                    stmt = con.createStatement();

                    sqlstring="insert into PATIENT_INSURANCE" +
                            "(PATIENT_HRN,INSURANCE_COMPANY_ID,INSURANCETYPE,GROUPNO,INSURANCEPLANNAME," +
                            "Policy_Type,Source,EFFECTIVE_DATE,EXPIRY_DATE,POLICY_HOLDER_RELATION," +
                            "SUBSCRIBER_ID,SOFInsurance,DOS,POLICY_HOLDER_FNAME,POLICY_HOLDER_LNAME,POLICY_HOLDER_DOB," +
                            "POLICY_HOLDER_SEX,POLICY_HOLDER_SSN,Policy_Holder_Address,Policy_Holder_Zipcode,Policy_Holder_City," +
                            "Policy_Holder_State,Policy_Holder_Phone,Policy_Holder_EmpSchool," +
                            "AnyOtherHealthBenfitPlan,Other_Holder_Name,Other_Holder_DOB," +
                            "Other_Holder_Sex,Other_Holder_Group,Other_Holder_EmpSchool," +
                            "Other_Holder_PlanName) values('"
                            +HRN+"','"+ob.getcomp()+"','"+ob.getpt()+"','"+ob.getgn()+"','"+ob.getpn()
                            +"','"+ob.getpoty()+"','"+ob.getsource()+"','"+ob.geteffdate()+"','"+ob.getexpdate()+"','"+ob.getrh()
                            +"','"+ob.getinsid()+"','"+ob.getsof()+"'";
                    
                            if(ob.getdsign()==null){
                                sqlstring=sqlstring+","+ob.getdsign()+"";
                            }else{
                                sqlstring=sqlstring+",'"+ob.getdsign()+"'";
                            }

                            sqlstring=sqlstring+",'"+ob.getInsuredFName()+"','"+ob.getInsuredLName()+"','"+ob.getidob()
                            +"','"+ob.getisex()+"','"+ob.getissn()+"','"+ob.getiadd()+"','"+ob.getizcode()+"','"+ob.geticity()
                            +"','"+ob.getistate()+"','"+ob.getiphone()+"','"+ob.getiename();
                            if(ob.getanyother()==null){
                                sqlstring=sqlstring+"',NULL";
                            }
                            else {
                                sqlstring=sqlstring+"','"+ob.getanyother()+"'";
                            }

                            sqlstring=sqlstring+",'"+ob.getoename()+"',";
                            if(ob.getoidob()==null){
                                sqlstring=sqlstring+"NULL";
                            }
                            else {
                                sqlstring=sqlstring+"'"+ob.getoidob()+"'";
                            }
                            
                            sqlstring=sqlstring+",'"+ob.getissn()+"','"+ob.getogno()+"','"+ob.getoename()
                            +"','"+ob.getopname()+"');";

                    stmt.executeUpdate(sqlstring);
                    con.close();
                    stmt.close();
                    return 1;
                }
                catch(Exception saa)    {
                 saa.printStackTrace();
                 return 0;
                 }
     }
        return 1;
    }catch(Exception e){e.printStackTrace();return 0;}
    }

     private String datedayformat(String tempyear ,String tempmonth ,String tempday){

       if(tempmonth.length() == 1){
           tempyear=tempyear+"0"+tempmonth;
       }else{
             tempyear=tempyear+tempmonth;
       }

       if(tempday.length() == 1){
         tempyear=tempyear+"0"+tempday;
       }else{
           tempyear=tempyear+tempday;
       }

       return tempyear;
   }

   private int expdatedetail(){
       ResultSet rs;

                Statement stmt;

                String tempyear="",tempmonth="",tempday="",datejCalCom1="",datejCalCom2="";
                int dd=0;

                 try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String url = "jdbc:mysql:///"+dbname;
                Connection con =  DriverManager.getConnection("jdbc:mysql://"+sip+":"+sport+"/"+dbname,uname, passwd);
                stmt = con.createStatement();

                tempyear=jCalendarCombo1.getSelectedYear();
                tempmonth=jCalendarCombo1.getSelectedMonth();
                tempday=jCalendarCombo1.getSelectedDay();

                datejCalCom1 = datedayformat(tempyear,tempmonth,tempday);

                tempyear=jCalendarCombo2.getSelectedYear();
                tempmonth=jCalendarCombo2.getSelectedMonth();
                tempday=jCalendarCombo2.getSelectedDay();

                datejCalCom2 = datedayformat(tempyear,tempmonth,tempday);

                String sqlQuery0="SELECT '"+datejCalCom1+"' <= '"+datejCalCom2+"' as dd;";
                rs=stmt.executeQuery(sqlQuery0);
                  while(rs.next()){
                    dd=rs.getInt("dd");
                   }
                }catch (Exception e){
                 e.printStackTrace();
                }
                return dd;
   }


   private void saveUpdateAllDetails(){
       int k=1;
        k = validation();
        if (k==1)
        {
            saveUpdate(true);

        }
   }
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
 // TODO add your handling code here:
        saveUpdateAllDetails();
    }//GEN-LAST:event_saveActionPerformed
    
    private int validation()
    {
        if (fname.getText().length() >= 15 ) 
        {
              
               fname.requestFocus();
               return 0;
        }
        
        if (lname.getText().length() >= 15 ) 
        {
               JOptionPane.showMessageDialog(null,"Last Name can not be greater than 15 characters");
               lname.requestFocus();
               return 0;
        }
        
          if (ladd1.getText().length() >= 35 ) 
        {
               JOptionPane.showMessageDialog(null,"Address 1  can not be greater than 35 characters");
               ladd1.requestFocus();
               return 0;
        }        
  
        return 1;
    }
    private void clearInsCoverage(){
        cmbInsCompany.setSelectedIndex(0);
        cmbPlanType.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        cmbRelation.setSelectedIndex(0);
        cmbInsSex.setSelectedIndex(0);        
        txtPolicyNO.setText("");
        txtPlanName.setText("");
        txtInsGroup.setText("");
        txtInsFName.setText("");
        txtInsAdd.setText("");
        txtInsZip.setText("");
        txtInsCity.setText("");
        txtInsState.setText("");
        txtInsPhone.setText("");
        txtInsEmp.setText("");
        chkAnyBenefit.setSelected(false);
    }

private String checkCode(String code,javax.swing.JComboBox combo){
    try{
        String[] splitCode;
        String codeName = null;
        String codeCombo=null;

        if(combo.getItemCount()!=0){
            int items=combo.getItemCount();
            for(int i=1;i<items;i++){
                splitCode=combo.getItemAt(i).toString().split(",");
                if(splitCode.length>1){
                    codeCombo=splitCode[0].trim();
                    if(codeCombo.equalsIgnoreCase(code.trim())){
                        codeName=combo.getItemAt(i).toString();
                        
                        return codeName;
                    }
                }
            }
        }
        return codeName;
    }catch(Exception e){e.printStackTrace();return null;}
    }

    private void cityState(){
     try{
                List<CityState> lstCS=new ArrayList<CityState>();
                CityStateMethods csm = new CityStateMethods();
                lstCS=csm.loadCityState(lpin.getText());
                String comboSel;
                
                if(lstCS!=null){
                    cmbCity.removeAllItems();
                    lcountry.removeAllItems();
                    
                    CityState cs =new CityState();
                    for(int i=0;i<lstCS.size();i++){
                        cs=lstCS.get(i);
                        cmbCity.addItem(cs.getCity());                        
                    }
                    lcountry.addItem(cs.getCounty());
                    
                   comboSel=checkCode(cs.getState(),this.cmblstate);
                   if(comboSel!=null){
                       cmblstate.setSelectedItem(comboSel);
                   }                    
                    cmbCity.setSelectedIndex(0);
                    lcountry.setSelectedIndex(0);
                }else{
                    cmbCity.removeAllItems();
                    cmblstate.setSelectedIndex(-1);
                    lcountry.setSelectedIndex(-1);
                }
            }catch(Exception e){e.printStackTrace();}

}

    private void setDefault(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"Company Name","Plan Name","Effective Date","Expire Date","Plan Type"}
       )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );


        ppp=0;
        
        clearInsCoverage();
        
        save.setEnabled(true);
        update.setEnabled(false); 
        lname.setText("");
        fname.setText("");
        age.setText("");
       
        sex.setSelectedIndex(0);
        callByBackNext=true;
      
        java.util.Date today;
        java.text.SimpleDateFormat formatter;
        today = new java.util.Date();
        formatter = new java.text.SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(today);
        registerdate.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dob.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dob.setSelectedDate(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6)));
       
        jCalendarCombo1.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        jCalendarCombo1.setSelectedDate(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6)));
        jCalendarCombo2.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        jCalendarCombo2.setSelectedDate(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6)));
        dtpDOS.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        dtpDOS.setSelectedDate(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6)));
        cmbInsDOB.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        cmbInsDOB.setSelectedDate(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6)));
              

        ladd1.setText("");        
        cmbCity.setSelectedIndex(-1);
        lpin.setText("");
        lphone.setText("");
        
        tfssn.setText("");
                
                cmbtitle.setSelectedIndex(0);
                tfsalutation.setText("");
                cmbms.setSelectedIndex(0);
                tacomments.setText("");
                tfeccomments.setText("");
                wphone.setText("");
                cphone.setText("");
                tfemail.setText("");
                tfec.setText("");
                tfgurantor.setText("");
                tfhousehold.setText("");
                
        ageunits.setSelectedIndex(0);
        cmbReligion.setSelectedIndex(0);
        cmbNationality.setSelectedIndex(0);
        cmbRace.setSelectedItem("SELECT");
        cmbOccupation.setSelectedItem("SELECT");

        cmblstate.setSelectedIndex(-1);
        lcountry.setSelectedIndex(-1);

        lblSlidingCode.setText("");
        lblMin.setText("");
        lblMax.setText("");
        
        
        java.util.Date today1;
        java.text.SimpleDateFormat formatter1;
        today1 = new java.util.Date();
        formatter1 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String date1 = formatter1.format(today1);
        registerdate.setSelectedDate(Integer.parseInt(date1.substring(0,4)),
                                    Integer.parseInt(date1.substring(4,6)),
                                Integer.parseInt(date1.substring(6,8)));

        date1=date1.substring(8);
        
        date1=date1.substring(0,2)+":"+date1.substring(2,4)+":"+date1.substring(4);
        regtime.setText(date1);
        
        lecname.setText("");
        lecadd.setText("");
        lecphone.setText("");
        lhhname.setText("");
        lhhadd.setText("");
        lhhphone.setText("");
        lgurantorname.setText("");
        lgurantoradd.setText("");
        lgurantorphone.setText("");
       
        ipno.setText("");
        tfeccomments.setText("");
        ipno.setEnabled(false);
        lname.requestFocus();
        
        tfMessagePhone.setText("");
        tfAnnualHHIncome.setText("");
        tfFamilyMembers.setText("");
        cmbLangSpokenatHome.setSelectedItem("SELECT");
        cmbHighestLevelofEducation.setSelectedItem("SELECT");
           
        tfMiddleName.setText("");        
        cmbEthnicity.setSelectedItem("SELECT");
    }
    
    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
// TODO add your handling code here:        
        resetClicked();              
    }//GEN-LAST:event_resetActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    try{
        if(MDI.searchCriteria==null){
            MDI.searchCriteria=new SearchCriteria();
            PatientList.whichForm = PatientList.spatient;
            this.dispose();
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRelationActionPerformed
        // TODO add your handling code here:
    try{        
        if(this.cmbRelation.getItemCount()>0){
          if(this.cmbRelation.getSelectedItem()!=null) {
            if(this.cmbRelation.getSelectedItem().toString().startsWith("Self")){
                this.txtInsLName.setText(this.lname.getText());
                this.txtInsFName.setText(this.fname.getText());
                this.cmbInsSex.setSelectedIndex(this.sex.getSelectedIndex());
                this.txtInsPhone.setText(this.lphone.getText());
                this.txtInsSSN.setText(this.tfssn.getText());
                this.cmbInsDOB.setDate(this.dob.getDate());
                this.txtInsAdd.setText(this.ladd1.getText());
                if(cmbCity.getSelectedItem()!=null)
                this.txtInsCity.setText(this.cmbCity.getSelectedItem().toString());
                if(cmblstate.getSelectedItem()!=null)
                this.txtInsState.setText(this.cmblstate.getSelectedItem().toString());
                this.txtInsZip.setText(this.lpin.getText());
                //this.txtInsPhone.setText(this.l);
            }else{
                this.txtInsLName.setText("");
                this.txtInsFName.setText("");
                this.cmbInsSex.setSelectedIndex(-1);
                this.txtInsPhone.setText("");
                this.cmbInsDOB.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
                this.txtInsAdd.setText("");
                this.txtInsCity.setText("");
                this.txtInsState.setText("");
                this.txtInsZip.setText("");
                this.txtInsSSN.setText("");
            }
          }
       }
    }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_cmbRelationActionPerformed

    private void CalculateRange(){
    try{
        int income=0;
        int fmySize=0;
        int Max=92526;

        String in="";
        String fm="";

        in=tfAnnualHHIncome.getText();
        fm=tfFamilyMembers.getText();
        
        
            if(!in.equalsIgnoreCase("") && !in.isEmpty() && !fm.equalsIgnoreCase("") && !fm.isEmpty()){
            
                       SlidingFee sf=new SlidingFee();
                       SlidingFeeMethods sfm=new SlidingFeeMethods();

                       String[] slid=null;
                       fmySize=Integer.parseInt(tfFamilyMembers.getText());
                       income=Integer.parseInt(tfAnnualHHIncome.getText());
                       slid=sfm.getCodeandRange(fmySize,income);
                       if(fmySize<9){
                            slid=sfm.getCodeandRange(fmySize,income);
                            if(slid!=null){
                                lblSlidingCode.setText(slid[0]);
                                lblMin.setText(slid[1]);
                                if(Integer.parseInt(slid[2])<=Max){
                                    lblMax.setText(slid[2]);
                                }else{
                                    lblMax.setText("Unlimited");
                                }
                            }else{
                               lblSlidingCode.setText("");
                               lblMin.setText("");
                               lblMax.setText("");
                            }
                        }else{
                           lblSlidingCode.setText("");
                           lblMin.setText("");
                           lblMax.setText("");
                        }
            }
      }catch(Exception e){ e.printStackTrace(); }    
    }
    private void tfFamilyMembersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFamilyMembersKeyPressed
        // TODO add your handling code here:
    try{
        if(evt.getKeyCode()==10){
            CalculateRange();            
        }
       }catch(Exception e){ e.printStackTrace(); }           
      
    }//GEN-LAST:event_tfFamilyMembersKeyPressed

    private void tfAnnualHHIncomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAnnualHHIncomeFocusLost
        // TODO add your handling code here:
         CalculateRange();
    }//GEN-LAST:event_tfAnnualHHIncomeFocusLost

    private void tfFamilyMembersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFamilyMembersFocusLost
        // TODO add your handling code here:
         CalculateRange();
    }//GEN-LAST:event_tfFamilyMembersFocusLost

    private void tfAnnualHHIncomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAnnualHHIncomeKeyTyped
        // TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
            if (ascii >=48 && ascii <=57 ) {}
            else
            {
                if (evt.getKeyChar()==evt.VK_BACK_SPACE) {}
                else{
                    evt.consume();
                }
            }
    }//GEN-LAST:event_tfAnnualHHIncomeKeyTyped

    private void tfFamilyMembersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFamilyMembersKeyTyped
        // TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
            if (ascii >=48 && ascii <=57 ) {}
            else
            {
                if (evt.getKeyChar()==evt.VK_BACK_SPACE){}
                else
                {
                    evt.consume();
                }

            }
    }//GEN-LAST:event_tfFamilyMembersKeyTyped

    private void txtInsZipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInsZipKeyTyped
        // TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
        //evt.VK_F1
            if (ascii >=48 && ascii <=57 ) {}
            else
            {
                if (evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)
                {}
                else
                {
                    evt.consume();
                }
            }
    }//GEN-LAST:event_txtInsZipKeyTyped

    private void optHIPAAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optHIPAAActionPerformed
        // TODO add your handling code here:
        if(optHIPAA.isSelected()){
             checkSOFHIPAADate();
        }else{
            uncheckSOFHIPAADate();
        }
}//GEN-LAST:event_optHIPAAActionPerformed

    private void optSOFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optSOFActionPerformed
        // TODO add your handling code here:
        if(optSOF.isSelected()){
            checkDOSDate();
        }else{
            uncheckDOSDate();
        }
    }//GEN-LAST:event_optSOFActionPerformed

    private void lphoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lphoneFocusLost
        // TODO add your handling code here:       
}//GEN-LAST:event_lphoneFocusLost

    private void lphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lphoneKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==9){
            wphone.requestFocus();
        }
}//GEN-LAST:event_lphoneKeyPressed

    private void lphoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lphoneKeyTyped
        // TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
        //evt.VK_F1
            if (ascii >=48 && ascii <=57 ) {}
            else
            {
                if (evt.getKeyChar()==evt.VK_BACK_SPACE)
                {}
                else
                {
                    evt.consume();
                }
            }
}//GEN-LAST:event_lphoneKeyTyped

    private void wphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wphoneKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==9){
            cphone.requestFocus();
        }
}//GEN-LAST:event_wphoneKeyPressed

    private void wphoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wphoneKeyTyped
        // TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
        //evt.VK_F1
            if (ascii >=48 && ascii <=57 ) {}
            else
            {
                if (evt.getKeyChar()==evt.VK_BACK_SPACE)
                {}
                else
                {
                    evt.consume();
                }
            }
}//GEN-LAST:event_wphoneKeyTyped

    private void cphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cphoneKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==9){
            tfemail.requestFocus();
        }
}//GEN-LAST:event_cphoneKeyPressed

    private void cphoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cphoneKeyTyped
        // TODO add your handling code here:
        int ascii = (int) evt.getKeyChar();
            if (ascii >=48 && ascii <=57 ) {}
            else
            {
                if (evt.getKeyChar()==evt.VK_BACK_SPACE)
                {}
                else
                {
                    evt.consume();
                }
            }
}//GEN-LAST:event_cphoneKeyTyped

    private void lpinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lpinFocusLost
        // TODO add your handling code here:
        cityState();
}//GEN-LAST:event_lpinFocusLost

    private void lpinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lpinKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            cityState();
        }
}//GEN-LAST:event_lpinKeyPressed

    private void lpinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lpinKeyTyped
        // TODO add your handling code here:

        int   ascii = (int) evt.getKeyChar();
        if (ascii >=48 && ascii <=57 ) {} else {
            if (evt.getKeyChar()==evt.VK_BACK_SPACE) {

            } else {  
                if(evt.getKeyCode()==9) {
                    lphone.requestFocus();
                } else {
                    evt.consume();
                }
            }
        }
}//GEN-LAST:event_lpinKeyTyped

    private void cmbtitleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbtitleItemStateChanged
        // TODO add your handling code here:
        if(!cmbtitle.getSelectedItem().toString().equalsIgnoreCase("-")) {
            tfsalutation.setText(cmbtitle.getSelectedItem().toString()+ " "+lname.getText());
        }
    }//GEN-LAST:event_cmbtitleItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clearInsCoverage();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chkAnyBenefitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAnyBenefitActionPerformed
        // TODO add your handling code here:
        try{

            if(chkAnyBenefit.isSelected()){
                PatientInformationMethods pim=new PatientInformationMethods();
                int chk=pim.checkPatientInsurance(jLabel51.getText(),cmbPlanType.getSelectedItem().toString());

                if(chk==0){
                    System.out.println("save :"+save.isEnabled());
                    if(save.isEnabled()){
                        save.requestFocus();
                    }else{
                        update.requestFocus();
                    }
                    this.saveUpdateAllDetails();
                    clearInsCoverage();
                    cmbInsCompany.requestFocus();
                }
                cmbInsCompany.setFocusable(true);
            }else{
            }
        }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_chkAnyBenefitActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    try{
        if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
            MDI.patRegistration=null;
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed
    
    public void resetClicked() {
            setDefault();            
            String temp123="";
            temp123=getHRN();           
            ipno.setText(getHRN());
            jLabel51.setText(ipno.getText());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientRegistation().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel SlidingFeePanel;
    public static javax.swing.JTextField age;
    public static javax.swing.JLabel agelabel;
    public static javax.swing.JComboBox ageunits;
    public static javax.swing.JButton back;
    public static javax.swing.JButton bec;
    public static javax.swing.JButton bgurantor;
    public static javax.swing.JButton bhousehold;
    public static javax.swing.JCheckBox chkAnyBenefit;
    public static javax.swing.JComboBox cmbCity;
    public static javax.swing.JComboBox cmbEthnicity;
    public static javax.swing.JComboBox cmbHighestLevelofEducation;
    public static javax.swing.JComboBox cmbInsCompany;
    public static org.gui.JCalendarCombo cmbInsDOB;
    public static javax.swing.JComboBox cmbInsSex;
    public static javax.swing.JComboBox cmbLangSpokenatHome;
    public static javax.swing.JComboBox cmbNationality;
    public static javax.swing.JComboBox cmbOccupation;
    public static javax.swing.JComboBox cmbPlanType;
    public static javax.swing.JComboBox cmbRace;
    public static javax.swing.JComboBox cmbRelation;
    public static javax.swing.JComboBox cmbReligion;
    public static javax.swing.JComboBox cmblstate;
    public static javax.swing.JComboBox cmbms;
    public static javax.swing.JComboBox cmbtitle;
    public static javax.swing.JFormattedTextField cphone;
    public static org.gui.JCalendarCombo dob;
    public static org.gui.JCalendarCombo dtpDOHS;
    public static org.gui.JCalendarCombo dtpDOS;
    public static javax.swing.JCheckBox enterage;
    public static javax.swing.JTextField fname;
    public static javax.swing.JTextField ipno;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static org.gui.JCalendarCombo jCalendarCombo1;
    public static org.gui.JCalendarCombo jCalendarCombo2;
    public static javax.swing.JComboBox jComboBox3;
    public static javax.swing.JComboBox jComboBox4;
    public static javax.swing.JDesktopPane jDesktopPane1;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    public static javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel16;
    public static javax.swing.JLabel jLabel17;
    public static javax.swing.JLabel jLabel18;
    public static javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel20;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel22;
    public static javax.swing.JLabel jLabel23;
    public static javax.swing.JLabel jLabel24;
    public static javax.swing.JLabel jLabel25;
    public static javax.swing.JLabel jLabel26;
    public static javax.swing.JLabel jLabel27;
    public static javax.swing.JLabel jLabel28;
    public static javax.swing.JLabel jLabel29;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel30;
    public static javax.swing.JLabel jLabel31;
    public static javax.swing.JLabel jLabel32;
    public static javax.swing.JLabel jLabel33;
    public static javax.swing.JLabel jLabel34;
    public static javax.swing.JLabel jLabel35;
    public static javax.swing.JLabel jLabel36;
    public static javax.swing.JLabel jLabel37;
    public static javax.swing.JLabel jLabel38;
    public static javax.swing.JLabel jLabel39;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel40;
    public static javax.swing.JLabel jLabel41;
    public static javax.swing.JLabel jLabel42;
    public static javax.swing.JLabel jLabel43;
    public static javax.swing.JLabel jLabel44;
    public static javax.swing.JLabel jLabel45;
    public static javax.swing.JLabel jLabel46;
    public static javax.swing.JLabel jLabel47;
    public static javax.swing.JLabel jLabel48;
    public static javax.swing.JLabel jLabel49;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel50;
    public static javax.swing.JLabel jLabel51;
    public static javax.swing.JLabel jLabel52;
    public static javax.swing.JLabel jLabel53;
    public static javax.swing.JLabel jLabel54;
    public static javax.swing.JLabel jLabel55;
    public static javax.swing.JLabel jLabel56;
    public static javax.swing.JLabel jLabel57;
    public static javax.swing.JLabel jLabel58;
    public static javax.swing.JLabel jLabel59;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel60;
    public static javax.swing.JLabel jLabel61;
    public static javax.swing.JLabel jLabel62;
    public static javax.swing.JLabel jLabel63;
    public static javax.swing.JLabel jLabel64;
    public static javax.swing.JLabel jLabel65;
    public static javax.swing.JLabel jLabel66;
    public static javax.swing.JLabel jLabel67;
    public static javax.swing.JLabel jLabel68;
    public static javax.swing.JLabel jLabel69;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    public static javax.swing.JPanel jPanel6;
    public static javax.swing.JPanel jPanel7;
    public static javax.swing.JPanel jPanel8;
    public static javax.swing.JPanel jPanel9;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JLabel jlmlc;
    public static javax.swing.JTextField ladd1;
    public static javax.swing.JLabel lblMax;
    public static javax.swing.JLabel lblMin;
    public static javax.swing.JLabel lblSlidingCode;
    public static javax.swing.JComboBox lcountry;
    public static javax.swing.JLabel lecadd;
    public static javax.swing.JLabel lecname;
    public static javax.swing.JLabel lecphone;
    public static javax.swing.JLabel lgurantoradd;
    public static javax.swing.JLabel lgurantorname;
    public static javax.swing.JLabel lgurantorphone;
    public static javax.swing.JLabel lhhadd;
    public static javax.swing.JLabel lhhname;
    public static javax.swing.JLabel lhhphone;
    public static javax.swing.JTextField lname;
    public static javax.swing.JFormattedTextField lphone;
    public static javax.swing.JTextField lpin;
    public static javax.swing.JButton next;
    public static javax.swing.JRadioButton optHIPAA;
    public static javax.swing.JRadioButton optSOF;
    public static org.gui.JCalendarCombo registerdate;
    public static javax.swing.JTextField regtime;
    public static javax.swing.JButton reset;
    public static javax.swing.JButton save;
    public static javax.swing.JComboBox sex;
    public static javax.swing.JTextArea tacomments;
    public static javax.swing.JTextField tfAnnualHHIncome;
    public static javax.swing.JTextField tfFamilyMembers;
    public static javax.swing.JFormattedTextField tfMessagePhone;
    public static javax.swing.JTextField tfMiddleName;
    public static javax.swing.JTextField tfec;
    public static javax.swing.JTextArea tfeccomments;
    public static javax.swing.JTextField tfemail;
    public static javax.swing.JTextField tfgurantor;
    public static javax.swing.JTextField tfhousehold;
    public static javax.swing.JTextField tfsalutation;
    public static javax.swing.JTextField tfssn;
    public static javax.swing.JTextField txtInsAdd;
    public static javax.swing.JTextField txtInsCity;
    public static javax.swing.JTextField txtInsEmp;
    public static javax.swing.JTextField txtInsFName;
    public static javax.swing.JTextField txtInsGroup;
    public static javax.swing.JTextField txtInsLName;
    public static javax.swing.JFormattedTextField txtInsPhone;
    public static javax.swing.JTextField txtInsSSN;
    public static javax.swing.JTextField txtInsState;
    public static javax.swing.JTextField txtInsZip;
    public static javax.swing.JTextField txtPlanName;
    public static javax.swing.JTextField txtPolicyNO;
    public static javax.swing.JButton update;
    public static javax.swing.JFormattedTextField wphone;
    // End of variables declaration//GEN-END:variables
    
    private String getTcNumber(){
        Connect2MySql ob=new Connect2MySql();
        java.util.Date today;
        java.text.SimpleDateFormat formatter;
        today = new java.util.Date();
        formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String temp="";
        String date = formatter.format(today);
        temp=date.substring(2,4);
        date=date.substring(8);
        date=date.substring(0,2)+":"+date.substring(2,4)+":"+date.substring(4);
        regtime.setText(date);
        //registime=date;
        
        temp="TC"+temp+ob.Get_Next_TC();
       // //System.out.println("temp   "+temp);
        return temp;
    }
    
    private String getHRN(){
        Connect2MySql ob=new Connect2MySql();
        String temp=ob.Get_Next_HRN();
        //System.out.println("temp   "+temp);
        return temp;
    }
    
    private void getStates(){
        Connect2MySql ob=new Connect2MySql();
        String temp[];
        temp=ob.getStates();
        allstates=temp;
        cmblstate.setModel(new javax.swing.DefaultComboBoxModel(temp));
        
    //    pstate.setModel(new javax.swing.DefaultComboBoxModel(temp));
    }
    
    private void searchPatient(String ttcno){
        setDefault();
        
        PatientInformation ob=new PatientInformation();
        Connect2MySql connect=new Connect2MySql();
      // problemInLooseFocus=false;
        //System.out.println("Searching for : " + ttcno);
        ob=connect.getPatDetails(ttcno);
        
        // System.out.print("In PatientList Patientccccccccccccccccccc");
        if(ob !=  null){
            save.setEnabled(false);
            update.setEnabled(true);
            showPatientDetails(ob);
            if(callByBackNext){
                ipno.setText(getHRN());
            }else{
                JOptionPane.showMessageDialog(null,"No Patient with TC No. - "+ttcno);
                callByBackNext=true;
            }            
        }
        becpress();
        bgpress();
        bhhpress();
        lname.requestFocus();
    }
    
    private void showPatientDetails(PatientInformation ob){
        String temp="";
        String temp123="";
        boolean flag=true;
        String te="";
        int i=0;
        try{
            //System.out.print("showPatientDetails");
            
            //System.out.println(ob.getRegistrationDate());
        regtime.setText(ob.getRegistrationDate().substring(11,19));    
        //registerdate.setSelectedDate(ob.getRegistrationDate(),00,00);
        temp=ob.getRegistrationDate();
        registerdate.setSelectedDate(Integer.parseInt(temp.substring(0,4)),
                Integer.parseInt(temp.substring(5,7)),
                Integer.parseInt(temp.substring(8,10)));
        
       
        temp=ob.getTcno();
        //ytcno.setText(temp.substring(0,4));
        //tcno.setText(temp.substring(4));
        //tcno.setText(getHRN());
        ipno.setText(ob.getIpNo());

        showInsuranceDetailsongrid(ipno.getText());    
        //showInsuranceDetails();
        //Mycode 4
        //grid addition
        
        //end Mycode 4 end
        fname.setText(ob.getFirstName());
        lname.setText(ob.getLastName());

        

       // jCalendarCombo5.set
        //System.out.println("dddddddddddddddd"+ob.getSex()+"dddddddddddddddddddd");
        sex.setSelectedItem(ob.getSex());
        temp=ob.getDOB();
        if(temp == null){
            agelabel.setText("DOB");
            dob.setSelectedDate(0000,00,00);
            age.setVisible(false);
            ageunits.setVisible(false);
            dob.setVisible(true);
        }else{
     
        try{
           // jTextField15.setText(ob.getMS());
            if(temp.substring(4).equalsIgnoreCase("-01-01")){
                age.setVisible(true);
                agelabel.setText("Age");
                dob.setVisible(false);
                dob.setSelectedDate(Integer.parseInt(temp.substring(0,4)),01,01);
                age.setText(ob.getAge());
                enterage.setSelected(true);
                ageunits.setVisible(true);
                ageunits.setSelectedIndex(Integer.parseInt(ob.getAgeUnits()));
                
                tfssn.setText(ob.getSSN());
               // //System.out.println("Title : " + ob.getTitle());
                cmbtitle.setSelectedItem(ob.getTitle());
                tfsalutation.setText(ob.getSalutation());
                cmbms.setSelectedItem(ob.getMS());
                tacomments.setText(ob.getComments());
                tfeccomments.setText(ob.getRemarks());
                //wphone.setText(ob.getWPhone());
                //cphone.setText(ob.getCPhone());
                tfemail.setText(ob.getEmail());
                tfec.setText(ob.getEC());
                tfgurantor.setText(ob.getGuarantor());
                tfhousehold.setText(ob.getHOH());
                tfMessagePhone.setText(ob.getMessagePhone());
                tfAnnualHHIncome.setText(ob.getAnnualIncome());
                tfFamilyMembers.setText(ob.getFamilyMembers());
                cmbLangSpokenatHome.setSelectedItem(ob.getLangSpoken());
                cmbHighestLevelofEducation.setSelectedItem(ob.getHighestLevelofEducation());
                tfMiddleName.setText(ob.getMiddleName());
                
                
            }else{
                age.setVisible(false);
                ageunits.setVisible(false);
                agelabel.setText("DOB");
                dob.setVisible(true);
                //System.out.println(temp);
                enterage.setSelected(false);
                age.setText(ob.getAge());
                ageunits.setSelectedItem(ob.getAgeUnits());
                
                tfssn.setText(ob.getSSN());
                cmbtitle.setSelectedItem(ob.getTitle());
                tfsalutation.setText(ob.getSalutation());
                cmbms.setSelectedItem(ob.getMS());
                tacomments.setText(ob.getComments());
                tfeccomments.setText(ob.getRemarks());
                //wphone.setText(ob.getWPhone());
                //cphone.setText(ob.getCPhone());
                tfemail.setText(ob.getEmail());
                tfec.setText(ob.getEC());
                tfgurantor.setText(ob.getGuarantor());
                tfhousehold.setText(ob.getHOH());
                    tfMessagePhone.setText(ob.getMessagePhone());
                tfAnnualHHIncome.setText(ob.getAnnualIncome());
                tfFamilyMembers.setText(ob.getFamilyMembers());
                cmbLangSpokenatHome.setSelectedItem(ob.getLangSpoken());
                cmbHighestLevelofEducation.setSelectedItem(ob.getHighestLevelofEducation());
            
                tfMiddleName.setText(ob.getMiddleName());
                
                dob.setSelectedDate(Integer.parseInt(temp.substring(0,4)),Integer.parseInt(temp.substring(5,7)),Integer.parseInt(temp.substring(8)));
                //dob.setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        
        /*if(ob.getRefered().equalsIgnoreCase("1")){
            refered.setSelected(true);
            referedbyname.setEditable(true);
            referedbyname.setText(ob.getReferedByName());
        }else{
            refered.setSelected(false);
            referedbyname.setEditable(false);
        }
        co.setSelectedIndex(Integer.parseInt(ob.getCo()));
        coname.setText(ob.getCoName());
        mlc.setSelectedIndex(Integer.parseInt(ob.getMLC()));
        if(mlc.getSelectedIndex()==1){
            jlmlc.setForeground(new java.awt.Color(255, 0, 51));
            jlmlc.setFont(new java.awt.Font("Tahoma", 1, 11));
        }else{
            jlmlc.setForeground(new java.awt.Color(0, 0, 0));
            jlmlc.setFont(new java.awt.Font("Tahoma", 1, 11));
        }
        
        modeofinjury.setSelectedIndex(Integer.parseInt(ob.getModeOfInjury()));
        remarks.setText(ob.getRemarks());
        
        patienttype.setSelectedIndex(Integer.parseInt(ob.getPatientType()));
        bbname.setText(ob.getBBName());
        bbadd.setText(ob.getBBAddress());*/
        /*te="";
        te=ob.getBBPhone();
        i=0;
        if(te.startsWith("+91")){
            //bbisd.setText("+91");
            i=te.indexOf("-");
            i++;
            //bbstd.setText(te.substring(i,te.indexOf("-",i)));
            i=te.indexOf("-",i);
            i++;
            //bbphone.setText(te.substring(i));
            //lstd.setText(i);
            
        }else{
            //bbphone.setText(te);
        }*/
        //bbphone.setText(ob.getBBPhone());
        //System.out.println("Religion : .................... " + ob.getReligion());
      /*
        temp=ob.getReligion().toUpperCase();
        String temp1;
        i=0;
        do{
          //System.out.println("item at " + i + " : " + religion1.getItemAt(i));
          temp1= (String) religion1.getItemAt(i);
          temp1= temp1.toUpperCase();
          if (temp1.startsWith(temp))
          {
              break;
          }else{
              i++;
          }  
        }while(true);
        */

       ////System.out.println(i);
        cmbReligion.setSelectedItem(ob.getReligion());

       if(ob.getSOF() != null){
            if(ob.getSOF().equalsIgnoreCase("Y")){
                temp=ob.getDOS();
                optHIPAA.setSelected(true);
                checkSOFHIPAADate();
                dtpDOHS.setSelectedDate(Integer.parseInt(temp.substring(0,4)),
                    Integer.parseInt(temp.substring(5,7)),
                    Integer.parseInt(temp.substring(8,10)));
            }
       }

        //religion1.setSelectedItem(ob.getReligion());
        //religion1.
        //religion.setText();
        //System.out.println("Nationality : .................... " + ob.getNationality());

        cmbNationality.setSelectedItem(ob.getNationality());
        //nationality.setSelectedIndex(2);
        //System.out.println("rACE : .................... " + ob.getrace());
        cmbRace.setSelectedItem(ob.getrace());
        cmbEthnicity.setSelectedItem(ob.getoccupation());
        cmbEthnicity.setSelectedItem(ob.getEthnicity());
        cmbOccupation.setSelectedItem(ob.getoccupation());
        ladd1.setText(ob.getLocalAdd());

       cmbCity.removeAllItems();
       //System.out.println("City"+ob.getLocalCity());
       
       if(!ob.getLocalCity().equalsIgnoreCase("NULL")){
            cmbCity.addItem(ob.getLocalCity());
            cmbCity.setSelectedIndex(0);
        }

        lcountry.removeAllItems();
        if(!ob.getLocalCountry().equalsIgnoreCase("NULL")){
             lcountry.addItem(ob.getLocalCountry());
        }
        temp=ob.getLocalState();
        i=0;
        //System.out.println(temp);
        if(!ob.getLocalState().equalsIgnoreCase("NULL")){
            do{
              if(allstates[i].startsWith(temp)){
                  break;
              }else{
                  i++;
              }
            }while(true);
       ////System.out.println(i);
        cmblstate.setSelectedIndex(i);
        }
        
        lpin.setText(ob.getLocalPin());
        
        i=0;
        te="";
        te=ob.getLocalphone();
        if(te.startsWith("+1")){
            
            i=te.indexOf("-");
            i++;
            
            i=te.indexOf("-",i);
            i++;
            //System.out.println(te.substring(i));
            lphone.setText(te.substring(i));
            //lstd.setText(i);
            //System.out.println("inside if");
        }else{
            lphone.setText(te);
            //System.out.println("inside else");
        }
        
        i=0;
        te="";
        te=ob.getWPhone();
        if(te.startsWith("+1")){
            
            i=te.indexOf("-");
            i++;
            
            i=te.indexOf("-",i);
            i++;
            //System.out.println(te.substring(i));
            wphone.setText(te.substring(i));
            //lstd.setText(i);
            //System.out.println("inside if");
        }else{
            wphone.setText(te);
            //System.out.println("inside else");
        }
        
        i=0;
        te="";
        te=ob.getCPhone();
        if(te.startsWith("+1")){
            
            i=te.indexOf("-");
            i++;
            
            i=te.indexOf("-",i);
            i++;
            //System.out.println(te.substring(i));
            cphone.setText(te.substring(i));
            //lstd.setText(i);
            //System.out.println("inside if");
        }else{
            cphone.setText(te);
            //System.out.println("inside else");
        }
       // lphone.setText(ob.getLocalphone());
        
      /*  padd1.setText(ob.getPermanentAdd());
        padd2.setText(ob.getPermanentAdd2());
        pcity.setText(ob.getPermanentCity());
        pcountry.setText(ob.getPermanentCountry());*/
       /* temp=ob.getPermanentState();
        //System.out.println("State : " + temp);
        i=0;
        do{
          if(allstates[i].startsWith(temp)){
              break;
          }else{
              i++;
          }  
        }while(true);
        */
        /*pstate.setSelectedIndex(i);
        ppin.setText(ob.getPermanentPin());*/
        te="";
        i=0;
        te=ob.getPermanentphone();
        /*if(te.startsWith("+91")){
            pisd.setText("+91");
            i=te.indexOf("-");
            i++;
            pstd.setText(te.substring(i,te.indexOf("-",i)));
            i=te.indexOf("-",i);
            i++;
            pphone.setText(te.substring(i));
            //lstd.setText(i);
            
        }else{
            pphone.setText(te);
        }
        //pphone.setText(ob.getPermanentphone());
        
        
        kinname.setText(ob.getKinName());*/
      
        }catch(Exception e){
            e.printStackTrace();
        }
    /*    if(padd1.getText().equalsIgnoreCase(ladd1.getText())){
            if(padd2.getText().equalsIgnoreCase(ladd2.getText())){
                if(pcity.getText().equalsIgnoreCase(lcity.getText())){
                    if(pstate.getSelectedIndex() == lstate.getSelectedIndex()){
                        if(pcountry.getText().equalsIgnoreCase(lcountry.getText())){
                            if(lphone.getText().equalsIgnoreCase(pphone.getText())){
                                if(ppin.getText().equalsIgnoreCase(lpin.getText())){
                                    flag=true;
                                }else{
                                    flag=false;
                                }
                            }else{
                                flag=false;
                            }
                        }else{
                            flag=false;
                        }
                    }else{
                        flag=false; 
                    }
                }else{
                   flag=false; 
                }
            }else{
                flag=false;
            }
        }else{
            flag=false;
        }
        */
        
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
    
}