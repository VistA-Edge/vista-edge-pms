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

import com.etipl.pms.reports.claim;
import com.etipl.pms.datalayer.BillMethods;
import com.etipl.pms.datalayer.CMS1500Methods;
import com.etipl.pms.datalayer.DoctorMethods;
import com.etipl.pms.datalayer.PatientInsuranceMethods;
import com.etipl.pms.datalayer.PatientInformationMethods;
import com.etipl.pms.datalayer.PatientVisitDetailsMethods;
import com.etipl.pms.entity.Bill;
import com.etipl.pms.entity.BillAmt;
import com.etipl.pms.entity.CPT_ICD_LINK;
import com.etipl.pms.entity.PatientInsurance;
import com.etipl.pms.entity.PatientInformation;
import com.etipl.pms.entity.PatientVisitDetails;
import com.etipl.pms.entity.ChargeCode;
import com.etipl.pms.entity.Doctor;
import com.etipl.pms.utilities.Utiles;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class Billing extends javax.swing.JFrame{
    JFrame frame=new JFrame();
    private int flag;
    private  List<PatientVisitDetails> lstPVD;
    private List<Bill> lstCPTDetails;
    private List<ChargeCode> charge;
    private List<CPT_ICD_LINK> icds;
    private int billAlreadyExists=1;
    private String doctorName="";
    private String billCaseID;

    private String typeofEncounter="";
    private int flagBillSave=0;
    private int flagPrintSave=0;
    private int flagVistACPT=0;
    private int flagPatientInsurance=0;

    //COLUMNS CONSTANT VISIT TABLE
    public static final int cVISITNO = 0;
    public static final int cVISITDT = 1;
    public static final int cCLINIC = 2;
    public static final int cPROVIDER =3;
    public static final int cAPPTDT = 4;
    public static final int cTYPE=5;
    //END COLUMNS CONSTANT VISIT TABLE

    //COLUMNS CONSTANT ICDs & pointer TABLE  
    public static final int cICD = 0;
    public static final int cPOINTER = 1;
    //END CONSTANT ICDs & pointer TABLE COLUMNS

    //COLUMNS CONSTANT CPTs & CHARGES TABLE
    public static final int cCPT = 0;
    public static final int cUNITS = 1;
    public static final int cM1 = 2;
    public static final int cM2 =3;
    public static final int cICD1 = 4;
    public static final int cICD2 = 5;
    public static final int cICD3 = 6;
    public static final int cICD4 = 7;
    public static final int cCHGCD=8;
    public static final int cCHG=9;
    public static final int cTOTCHG = 10;
    public static final int cADJCD = 11;
    public static final int cADJAMT = 12;
    public static final int cBAL=13;
    public static final int cPRD=14;
    public static final int cPTR=15;
    public static final int cPOS=16;    
    //END COLUMNS CONSTANT CPTs & CHARGES TABLE
    
    /** Creates new form Billing */

    public void setLstPVD(List<PatientVisitDetails> lstPVD){
       this.lstPVD=lstPVD;
   }

   public List<PatientVisitDetails> getLstPVD(){
       return lstPVD;
   }

    public Billing() throws SQLException {
        initComponents();
        screenDisplay();
        setVisible(true);
        lblStatus.setText("Please select the patient by pressing Alt+S or click 'Search Patient' button ...");
        billingTable.getMouseListeners();

        MouseListener[] m=billingTable.getMouseListeners();
        for(int l=0;l<m.length;l++){
            billingTable.removeMouseListener(m[l]);
        }  
        displayBothEncounters();
        displayBaseDetails();
        dynamicWidthofColumnforjTable1();
        dynamicWidthofColumnforTable3();
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
       }else{
           clearVisitDetails();
       }
       flag=2;
       
    }catch(Exception e){e.printStackTrace();}
}


private void displayPMSVisit() throws SQLException{
    
      PatientVisitDetailsMethods pvdm=new PatientVisitDetailsMethods();
      setLstPVD(pvdm.searchPatientVisitFromMaster(lblHRN.getText()));

                   if(lstPVD !=null && !lstPVD.isEmpty()){
                        getPatientVisit(lstPVD,cmbEncounter.getSelectedIndex());
                   }else{
                        clearVisitDetails();
                   }
 }

private void displayBaseDetails(){

    try{
        if(!PatientList.globalhrn.isEmpty()){
           jLabel6.setText(Utiles.convertMysqlDatetoUSFormat(Utiles.getCurrentDateTime()));

           //PatientDetails object module and datalayer
           PatientInformation pf=new PatientInformation();
           PatientInformationMethods pim=new PatientInformationMethods();

           //PatientInsurance object module and datalayer
           PatientInsurance ins=new PatientInsurance();
           PatientInsuranceMethods ipm=new PatientInsuranceMethods();

           pf=pim.searchPatientFromMaster(PatientList.globalhrn);
           
           if(pf!=null){
               getPatientInformation(pf);
               if(lblHRN!=null)
                   //=== Search Visit ===                   
                   cmbEncounter.setSelectedIndex(3);
                   displayBothEncounters();                 
               
                   ins=ipm.searchPatientInsuranceFromMaster(lblHRN.getText());
                   if(ins!=null){
                       getPatientInsurance(ins);
                       flagPatientInsurance=1;
                   }else{
                        JOptionPane.showMessageDialog(null,"Claim cannot be generated. \n No insurance detail of patient.");
                    }
                   flag=1;
           }
           else{
               clearAll();
           }
        }
      }catch(Exception e){e.printStackTrace();}
}
    
public void getPatientInformation(PatientInformation pf){
try{      
        lblHRN.setText(pf.getHRN());
        txtLastName.setText(pf.getLastName());
        if(pf.getLocalAdd2()!=null){
        txtAddress.setText(pf.getLocalAdd()+" "+pf.getLocalAdd2());
        }
        else {
            txtAddress.setText(pf.getLocalAdd());
        }
        txtCity.setText(pf.getLocalCity());
        txtZip.setText(pf.getLocalPin());
        txtFirstName.setText(pf.getFirstName());
        txtState.setText(pf.getLocalState());
        txtPhone.setText(pf.getLocalphone());
        txtDOB.setText(pf.getDOB());
        txtSex.setText(pf.getSex());
        txtSSN.setText(pf.getSSN());
        txtMS.setText(pf.getMS());
        txtMobile.setText(pf.getCPhone());
        
  }catch(Exception e){e.printStackTrace();}    
}

public void getPatientInsurance(PatientInsurance ins){
try{
        if(InsuranceTabbedPane.getSelectedIndex()==0){
            jTextField3.setText(ins.getcomp());
            jTextField5.setText(ins.getInsuredLName()+" "+ins.getInsuredFName());
            jTextField6.setText(ins.getidob());
            jTextField7.setText(ins.getrh());
            jTextField8.setText(ins.geteffdate());
            jTextField9.setText(ins.getexpdate());
        }
        if(InsuranceTabbedPane.getSelectedIndex()==1){
            jTextField10.setText(ins.getcomp());
            jTextField11.setText(ins.getInsuredLName()+" "+ins.getInsuredFName());
            jTextField12.setText(ins.getidob());
            jTextField13.setText(ins.getrh());
            jTextField14.setText(ins.geteffdate());
            jTextField15.setText(ins.getexpdate());
        }
        if(InsuranceTabbedPane.getSelectedIndex()==2){
            jTextField16.setText(ins.getcomp());
            jTextField17.setText(ins.getInsuredLName()+" "+ins.getInsuredFName());
            jTextField18.setText(ins.getidob());
            jTextField19.setText(ins.getrh());
            jTextField20.setText(ins.geteffdate());
            jTextField21.setText(ins.getexpdate());            
        }        
   }catch(Exception e){e.printStackTrace();}    
}

public void getPatientVisit(List<PatientVisitDetails> lstPVD,int combosel){
try{
      int rowLength;
      int i=0;
      rowLength=0;      
      i=lstPVD.size();
      String[][] arr = new String[i][6];
            
      this.cmbEncounter.setSelectedIndex(combosel);

      do {  PatientVisitDetails pvd = new PatientVisitDetails();
            pvd=lstPVD.get(rowLength);
               
                arr[rowLength][0]=pvd.getVisitNo();
                arr[rowLength][1]=pvd.getVisit_Datetime();
                arr[rowLength][2]=pvd.getDepartment();
                arr[rowLength][3]=pvd.getProvider();
                arr[rowLength][4]=pvd.getAppt_Datetime().toString();
                arr[rowLength][5]=pvd.getTypeofVisit();
                rowLength++;
                
       }while(rowLength<i);
      
        tabVisits.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"Visit No","Visit Date/Time","Clinic","Provider","Appoitment Date/Time","Type"}
                )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false, false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );


        tabVisits.setAutoCreateRowSorter(true);
        dynamicWidthofColumnforjTable1();
        
        if(tabVisits.getRowCount()>0){
            lblStatus.setText("Single click on the 'List of Visits' grid to generate the bill or modify...");
        }

                
  }catch(Exception e){e.printStackTrace(); }
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        txtZip = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtState = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        txtSex = new javax.swing.JTextField();
        txtSSN = new javax.swing.JTextField();
        txtMS = new javax.swing.JTextField();
        txtMobile = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblHRN = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        billingTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        totalBillTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        billCheck = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtBillNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCaseID = new javax.swing.JTextField();
        lblBill = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabVisits = new javax.swing.JTable();
        cmbEncounter = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        icdsTable = new javax.swing.JTable();
        butSave = new javax.swing.JButton();
        butReset = new javax.swing.JButton();
        InsuranceTabbedPane = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        butSearch = new javax.swing.JButton();
        butPrint = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        butDelete = new javax.swing.JButton();

        org.jdesktop.layout.GroupLayout jFrame1Layout = new org.jdesktop.layout.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jFrame2Layout = new org.jdesktop.layout.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generate Claim");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Demographics"));

        jLabel80.setText("Zip");

        jLabel85.setText("First Name");

        jLabel86.setText("Mobile");

        jLabel91.setText("Address");

        jLabel92.setText("DOB");

        jLabel93.setText("Last Name");

        jLabel95.setText("Martial Status");

        jLabel96.setText("SSN");

        jLabel97.setText("Phone");

        jLabel101.setText("Sex");

        jLabel103.setText("City");

        jLabel105.setText("State");

        txtLastName.setEditable(false);

        txtAddress.setEditable(false);

        txtCity.setEditable(false);

        txtZip.setEditable(false);

        txtFirstName.setEditable(false);

        txtState.setEditable(false);

        txtPhone.setEditable(false);

        txtDOB.setEditable(false);

        txtSex.setEditable(false);

        txtSSN.setEditable(false);

        txtMS.setEditable(false);

        txtMobile.setEditable(false);

        jLabel7.setText("HRN");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel7)
                        .add(35, 35, 35)
                        .add(lblHRN, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel103)
                                    .add(jLabel80))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 44, Short.MAX_VALUE)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(txtZip, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jLabel105)
                                        .add(18, 18, 18)
                                        .add(txtState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(txtCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 176, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel97)
                                        .add(18, 18, 18)
                                        .add(txtPhone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel86)
                                        .add(19, 19, 19)
                                        .add(txtMobile, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel93)
                                    .add(jLabel91))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(txtAddress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(txtLastName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                        .add(18, 18, 18)
                                        .add(jLabel85)
                                        .add(19, 19, 19)
                                        .add(txtFirstName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel101)
                            .add(jLabel95)
                            .add(jLabel96)
                            .add(jLabel92))
                        .add(26, 26, 26)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, txtMS, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, txtDOB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .add(txtSex, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .add(txtSSN, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel7)
                    .add(lblHRN, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel92)
                            .add(txtDOB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel101)
                            .add(txtSex, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel96)
                            .add(txtSSN, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel95)
                            .add(txtMS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel85)
                                .add(txtFirstName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel93)
                                .add(txtLastName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel91)
                            .add(txtAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel103)
                            .add(txtCity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(txtPhone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel97))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(txtMobile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel86)
                                .add(txtState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel105)
                                .add(txtZip, 0, 0, Short.MAX_VALUE))
                            .add(jLabel80))))
                .add(11, 11, 11))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Billing"));

        billingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CPT", "Units", "M1", "M2", "ICD1", "ICD2", "ICD3", "ICD4", "Chargecode & Des", "Charges", "Tot.charges", "Adj.code", "Adj.amt", "Balance", "Provider(NPI;TAX)", "Pointer", "POS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billingTableMouseClicked(evt);
            }
        });
        billingTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                billingTableFocusGained(evt);
            }
        });
        billingTable.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                billingTableHierarchyChanged(evt);
            }
        });
        billingTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                billingTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                billingTableKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(billingTable);
        billingTable.getColumnModel().getColumn(9).setResizable(false);
        billingTable.getColumnModel().getColumn(16).setResizable(false);

        totalBillTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalBillTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalBillTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(totalBillTable);
        totalBillTable.getColumnModel().getColumn(0).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(1).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(2).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(3).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(4).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(5).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(6).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(7).setMaxWidth(200);
        totalBillTable.getColumnModel().getColumn(8).setMaxWidth(200);

        billCheck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "*"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        billCheck.setInheritsPopupMenu(true);
        billCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billCheckMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(billCheck);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane4, 0, 0, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill info"));

        txtBillNo.setEditable(false);

        jLabel2.setText("Bill.No");

        jLabel3.setText("Bill Date");

        jLabel4.setText("CaseID");

        txtCaseID.setEditable(false);

        lblBill.setForeground(new java.awt.Color(0, 51, 204));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtBillNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(12, 12, 12)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(txtCaseID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(lblBill, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel2)
                .add(txtBillNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel3)
                .add(lblBill, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(txtCaseID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel4))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("List of Visits"));

        tabVisits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Visit No", "Visit Date/Time", "Clinic", "Provider", "Appointment Date/Time", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tabVisits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabVisitsMouseClicked(evt);
            }
        });
        tabVisits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabVisitsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabVisits);

        cmbEncounter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "PMS", "VistA", "Both" }));
        cmbEncounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEncounterActionPerformed(evt);
            }
        });

        jLabel1.setText("Encounters of");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmbEncounter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbEncounter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("ICD"));

        icdsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ICD", "*"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(icdsTable);
        icdsTable.getColumnModel().getColumn(0).setResizable(false);
        icdsTable.getColumnModel().getColumn(1).setResizable(false);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        butSave.setMnemonic('A');
        butSave.setText("Save");
        butSave.setEnabled(false);
        butSave.setNextFocusableComponent(butPrint);
        butSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSaveActionPerformed(evt);
            }
        });
        butSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                butSaveKeyPressed(evt);
            }
        });

        butReset.setMnemonic('R');
        butReset.setText("Reset");
        butReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butResetActionPerformed(evt);
            }
        });

        InsuranceTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InsuranceTabbedPaneMouseClicked(evt);
            }
        });

        jLabel8.setText("Insurance Company");

        jLabel9.setText("Insured Name");

        jLabel10.setText("Insured DOB");

        jLabel11.setText("Relation");

        jLabel12.setText("Effective Date");

        jLabel13.setText("Expiration Date");

        jTextField3.setEditable(false);

        jTextField5.setEditable(false);

        jTextField6.setEditable(false);

        jTextField7.setEditable(false);

        jTextField8.setEditable(false);

        jTextField9.setEditable(false);

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel8)
                    .add(jLabel9)
                    .add(jLabel10)
                    .add(jLabel11)
                    .add(jLabel12)
                    .add(jLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField8)
                    .add(jTextField7)
                    .add(jTextField6)
                    .add(jTextField5)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(jTextField9))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel10)
                    .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        InsuranceTabbedPane.addTab("Primary", jPanel9);

        jLabel14.setText("Insurance Company");

        jTextField10.setEditable(false);

        jLabel15.setText("Insured Name");

        jTextField11.setEditable(false);

        jLabel16.setText("Insured DOB");

        jTextField12.setEditable(false);

        jLabel17.setText("Relation");

        jTextField13.setEditable(false);

        jLabel18.setText("Effective Date");

        jTextField14.setEditable(false);

        jLabel19.setText("Expiration Date");

        jTextField15.setEditable(false);

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel14)
                    .add(jLabel15)
                    .add(jLabel16)
                    .add(jLabel17)
                    .add(jLabel18)
                    .add(jLabel19))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField14)
                    .add(jTextField13)
                    .add(jTextField12)
                    .add(jTextField11)
                    .add(jTextField10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(jTextField15))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel14)
                    .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel16)
                    .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel19)
                    .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        InsuranceTabbedPane.addTab("Secondary", jPanel8);

        jLabel20.setText("Insurance Company");

        jTextField16.setEditable(false);

        jLabel21.setText("Insured Name");

        jTextField17.setEditable(false);

        jLabel22.setText("Insured DOB");

        jTextField18.setEditable(false);

        jLabel23.setText("Relation");

        jTextField19.setEditable(false);

        jTextField20.setEditable(false);

        jLabel25.setText("Expiration Date");

        jTextField21.setEditable(false);

        jLabel24.setText("Effective Date");

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 246, Short.MAX_VALUE)
            .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel20)
                        .add(jLabel21)
                        .add(jLabel22)
                        .add(jLabel23)
                        .add(jLabel24)
                        .add(jLabel25))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jTextField20)
                        .add(jTextField19)
                        .add(jTextField18)
                        .add(jTextField17)
                        .add(jTextField16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .add(jTextField21))
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 182, Short.MAX_VALUE)
            .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel20)
                        .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel21)
                        .add(jTextField17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel22)
                        .add(jTextField18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel23)
                        .add(jTextField19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel24)
                        .add(jTextField20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel25)
                        .add(jTextField21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        InsuranceTabbedPane.addTab("Teritary", jPanel7);

        butSearch.setMnemonic('S');
        butSearch.setText("Search Patient");
        butSearch.setNextFocusableComponent(butReset);
        butSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSearchActionPerformed(evt);
            }
        });

        butPrint.setMnemonic('P');
        butPrint.setText("Print Claim");
        butPrint.setEnabled(false);
        butPrint.setNextFocusableComponent(butSearch);
        butPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPrintActionPerformed(evt);
            }
        });

        lblStatus.setForeground(new java.awt.Color(0, 51, 153));
        lblStatus.setText("Please select the patient by clicking on the \"Search Patient\" button  or Alt+S ");

        butDelete.setText("Delete");
        butDelete.setEnabled(false);
        butDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDeleteActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(lblStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 507, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(butDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(4, 4, 4)
                        .add(butSave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(butPrint)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(butSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(butReset, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(InsuranceTabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(7, 7, 7)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(InsuranceTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 215, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(butReset)
                    .add(butSearch)
                    .add(butPrint)
                    .add(butSave)
                    .add(lblStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(butDelete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void butResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butResetActionPerformed
        // TODO add your handling code here:
        clearAll();
        butSearch.setEnabled(true);
}//GEN-LAST:event_butResetActionPerformed
    private void clearVisitDetails(){
        tabVisits.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"Visit No","Visit Date/Time","Clinic","Provider","Appoitment Date/Time","Type"}));
        icdsTable.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"ICD","*"}));
        billingTable.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"CPT","Units","M1","M2","ICD1","ICD2","ICD3","ICD4","Chargecode & Des","Charges","Tot.charges","Adj.code","Adj.amt","Balance","Provider(NPI;TAX)","Pointer","POS"}));
    }

    private void clearAll(){
    try{
        clearPatientDetails();
        clearInsuranceDetails();
        clearVisitDetails();         
        clearBillInfo();
        clearTotalBill();
        }catch(Exception e){e.printStackTrace();}
    }
    private void clearPatientDetails(){
        lblHRN.setText("");        
        txtLastName.setText("");
        txtFirstName.setText("");
        txtDOB.setText("");
        txtAddress.setText("");
        txtSex.setText("");
        txtCity.setText("");
        txtPhone.setText("");
        txtSSN.setText("");
        txtZip.setText("");
        txtState.setText("");
        txtMobile.setText("");
        txtMS.setText("");
    }
    
    private void clearBillInfo(){
        txtBillNo.setText("");
        txtCaseID.setText("");
        lblBill.setText("");
    }
    
    private void clearInsuranceDetails(){
    try{
            jTextField3.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");                
            //---------------------//
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jTextField15.setText("");                
            //---------------------//
            jTextField16.setText("");
            jTextField17.setText("");
            jTextField18.setText("");
            jTextField19.setText("");
            jTextField20.setText("");
            jTextField21.setText("");

        }catch(Exception e){e.printStackTrace();}
   }
    private void clearTotalBill(){
        totalBillTable.setValueAt(null, 0, cTOTCHG);
        totalBillTable.setValueAt(null, 0, cADJAMT);
        totalBillTable.setValueAt(null, 0, cBAL);
    }

    private void ICDsPointer(){
        try{
            int curRow=billingTable.getSelectedRow();
            
            Object cpt=new Object();
            Object pointer=new Object();       

            cpt=billingTable.getValueAt(curRow, cCPT);
            pointer=billingTable.getValueAt(curRow, cPTR);

            if(cpt!=null && pointer!=null){
               String[] ptr=pointer.toString().split(",");
               pointCPTwithICD(ptr);
            }
            
        }catch(Exception e){e.printStackTrace();  }
    }
    
    private void clearICDCheckBoxes(){
    try{
            int rowCount=icdsTable.getRowCount();
            if(rowCount!=0){
                 for(int j=1;j<=rowCount;j++){
                    icdsTable.setValueAt(false,j-1 , cPOINTER);
                    TableColumn tc = icdsTable.getColumnModel().getColumn(cPOINTER);
                    tc.setCellEditor(icdsTable.getDefaultEditor(Boolean.class));
                    tc.setCellRenderer(icdsTable.getDefaultRenderer(Boolean.class));
                 }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private void pointCPTwithICD(String[] ptr){
    try{
             int rowCount=icdsTable.getRowCount();
             int pt = 0;
             clearICDCheckBoxes();
             
             if(rowCount!=0){
                 for(int j=1;j<=rowCount;j++){
                 for(int i=0;i<ptr.length;i++){
                       if(!ptr[i].equalsIgnoreCase(" ")){
                        pt=Integer.parseInt(ptr[i]);
                       }
                       if(pt==j){
                        icdsTable.setValueAt(true,j-1 , cPOINTER);
                        TableColumn tc = icdsTable.getColumnModel().getColumn(cPOINTER);

                        tc.setCellEditor(icdsTable.getDefaultEditor(Boolean.class));
                        tc.setCellRenderer(icdsTable.getDefaultRenderer(Boolean.class));
                       }
                   }
                 }
             }
        }catch(Exception e){e.printStackTrace();}
    }

    private void InsuranceTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsuranceTabbedPaneMouseClicked
        // TODO add your handling code here:       
   try{
        if(flag==1){
             PatientInsurance ins=new PatientInsurance();
             PatientInsuranceMethods ipm=new PatientInsuranceMethods();
             String whichIns=InsuranceTabbedPane.getTitleAt(InsuranceTabbedPane.getSelectedIndex()).substring(0, 1);
             ins=ipm.searchPatientInsuranceFromMaster(lblHRN.getText(),whichIns);                
                if(ins!=null){
                   getPatientInsurance(ins);
                }
        }
       }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_InsuranceTabbedPaneMouseClicked

    private void showCPT(int rowCount,int rowSel){
        try{

        if(rowCount>0){
         
         String billNo;
         String visitNo=tabVisits.getValueAt(rowSel,cVISITNO).toString();

         typeofEncounter=tabVisits.getValueAt(rowSel,cTYPE).toString();
                 
         billNo = lblHRN.getText()+"."+visitNo;
         doctorName=tabVisits.getValueAt(rowSel,cPROVIDER).toString();
         txtBillNo.setText(billNo);
         txtCaseID.setText(billNo);

         BillMethods bm=new BillMethods();
         BillAmt ba=new BillAmt();

         ba = bm.checkBill(billNo);
         dynamicWidthofColumnforTable3();
             if(ba!=null){
                getTotalBillDetails(ba);
                lstCPTDetails=(List<Bill>) bm.setCPTDetails(billNo);
                if(lstCPTDetails!=null && !lstCPTDetails.isEmpty()){
                    getCPTDetails(lstCPTDetails);
                        //====== Set and Get ICD details ==========
                       int lenRetArr;
                       lenRetArr=bm.setICDDetails(billNo).length;
                       String[] icdDetails=new String[lenRetArr];
                       icdDetails=bm.setICDDetails(billNo);

                       if(icdDetails!=null){
                           getICDDetails(icdDetails);
                       }
                }
                    butSave.setText("Update");
                    butSave.requestFocus();
                    butSave.setEnabled(true);
             }else{
                    clearCPTDetails();
                    lblBill.setText("Generate New Bill ...");

                    if(typeofEncounter.equalsIgnoreCase("V")){

                       //======== Getting cpt from veq_vista_billing ==========
                       Object[][] s=new Object[6][17];

                       Object[][] arr=bm.setCPTfromVistA(lblHRN.getText(),visitNo);
                       billingTable.setModel(new javax.swing.table.DefaultTableModel(
                               bm.setCPTfromVistA(lblHRN.getText(),visitNo)
                               ,new String[]{"CPT","Units","M1","M2","ICD1","ICD2","ICD3"
                               ,"ICD4","Chargecode & Des","Charges","Tot.charges","Adj.code"
                               ,"Adj.amt","Balance","Provider(NPI;TAX)","Pointer","POS"}));

                       dynamicWidthofColumnforTable3();
                       
                       //======= Getting icd from veq_vista_billing ================
                       Object[] icdsFV=bm.setICDfromVistA(lblHRN.getText(),visitNo);
                       if(icdsFV!=null){
                           showICDfromVistA(icdsFV);
                           flagVistACPT=1;
                       }else{
                        clearICDCombo();
                        lblStatus.setText("Warning: No ICDs available from VistA.");
                        }
                       
                       if(bm.setCPTfromVistA(lblHRN.getText(),visitNo)!=null){
                        lblStatus.setText("Press Tab key to continue billing ...");
                       }else{
                        lblStatus.setText("No encounter available from VistA ...");
                       }
                    }else{
                       lblStatus.setText("Enter the CPT and Press Tab key to continue billing ...");
                    }

                    billAlreadyExists=0;
                    flagBillSave=0;
                    flagPrintSave=0;
                    butSave.setText("Save");
                    butSave.setEnabled(false);
                    butPrint.setEnabled(false);
                }
            }
           
                if(billingTable.getRowCount()!=0){
                    billingTable.changeSelection(0, 0, false, false);
                    billingTable.setFocusable(true);
                    billingTable.requestFocus();
                }

        int checkRows=billCheck.getRowCount();
        for(int r=0;r<checkRows;r++){
             billCheck.setValueAt(false, r, 0);
             billCheck.removeRowSelectionInterval(r, 0);
        }

        butDelete.setEnabled(false);

        }catch(Exception e){e.printStackTrace();}
    }

    private void tabVisitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabVisitsMouseClicked
        // TODO add your handling code here:
       int rowCount=tabVisits.getRowCount();
       int curRow=tabVisits.getSelectedRow();

       showCPT(rowCount,curRow);
}//GEN-LAST:event_tabVisitsMouseClicked
/**
   * <pre><b>Method Name:</b></pre> <showICDfromVistA>
   * <pre><b>Description:</b></pre>Shows icds for the particular encounter from the VistA
   * into comboboxes, when clicking the mouse on the ICDs on the Billing grid.
   * @param
   * @exception Exception
    @return
 */
    private void showICDfromVistA(Object[] icds){
    TableColumn ICD1fill = billingTable.getColumnModel().getColumn(cICD1);
        TableColumn ICD2fill = billingTable.getColumnModel().getColumn(cICD2);
        TableColumn ICD3fill = billingTable.getColumnModel().getColumn(cICD3);
        TableColumn ICD4fill = billingTable.getColumnModel().getColumn(cICD4);
        JComboBox comboBox = new JComboBox();
        
        int i=icds.length;
        int j=0;
        clearICDCombo();
        comboBox.addItem("");        
        try{
            do{                
                comboBox.addItem(icds[i-1]);                
                ICD1fill.setCellEditor(new DefaultCellEditor(comboBox));
                ICD2fill.setCellEditor(new DefaultCellEditor(comboBox));
                ICD3fill.setCellEditor(new DefaultCellEditor(comboBox));
                ICD4fill.setCellEditor(new DefaultCellEditor(comboBox));
                j++;i--;
            }while(i>0);
        }catch(Exception e){e.printStackTrace();}
}

private void clearCPTDetails(){
try{
       Object[][] arr=new Object[6][14];
       
            for(int i=0;i<6;i++){
              for(int j=0;j<14;j++){
                arr[i][j]=null;
              }
            }

            billingTable.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"CPT","Units"
            ,"M1","M2","ICD1","ICD2","ICD3","ICD4","Chargecode & Des","Charges","Tot.charges","Adj.code"
            ,"Adj.amt","Balance","Provider(NPI;TAX)","Pointer","POS"}));

            dynamicWidthofColumnforTable3();
             
            icdsTable.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"ICD","*"}));

            totalBillTable.setValueAt(null, 0, cCPT);
            totalBillTable.setValueAt(null, 0, cTOTCHG);
            totalBillTable.setValueAt(null, 0, cADJAMT);
            totalBillTable.setValueAt(null, 0, cBAL);
            dynamicWidthofColumnforTable5();
            
    }catch(Exception e){e.printStackTrace();}
}


private void getChargeCodeICDSofCPT(int cptRow,int cptCol,Object CPTfromTable){
    try{
               BillMethods charg=new BillMethods();
               String cptCode = null;
               
               if(CPTfromTable!=null && CPTfromTable!=""){
                    cptCode=CPTfromTable.toString();
               }

                if(CPTfromTable!=null && CPTfromTable!=""){
                    
                   String chargeCode="";

                   if(billingTable.getValueAt(cptRow, cUNITS)==null){
                    billingTable.setValueAt("1", cptRow, cUNITS);
                   }

                   //==== Get CHGCD related to CPT====
                   charge=charg.setChargeCode(cptCode,chargeCode);

                   if(charge!=null){
                       showChargeCodeCombo(charge);
                   }
                   else{
                       clearChargeCodeCombo();
                       lblStatus.setText("Warning: No chargecode is charge with this CPT "+cptCode+", please use 'ChargeCode' Master to make charges.");
                   }

                   if(typeofEncounter.equalsIgnoreCase("M")){
                       //==== Get Icds related to CPT ===
                       icds=charg.setIcds(cptCode);
                       if(icds!=null){
                            showICDCombo(icds);
                       }else if(cmbEncounter.getSelectedIndex()!=2){
                            clearICDCombo();
                            lblStatus.setText("Warning: No ICD is linked with this CPT "+cptCode+", please use 'CPT-ICD-Link' Master to link.");
                       }
                    }
           }else{
                   clearChargeCodeCombo();
                   lblStatus.setText("Warning: No chargecode available, please use 'ChargeCode' Master to charge the CPT.");
                   clearICDCombo();
                   lblStatus.setText("Warning: No ICDs link available, please use 'CPT-ICD-Link' Master to link.");
           }
     }catch(Exception e){e.printStackTrace();}
}

private void billingTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billingTableKeyPressed
        // TODO add your handling code here:
    try{  int cptRow;
          int cptCol;
          cptCol=0;
          cptRow=billingTable.getSelectedRow();
          cptCol=billingTable.getSelectedColumn();
          Object balance;

          if(evt.getKeyCode()==evt.VK_DOWN || evt.getKeyCode()==evt.VK_UP 
          ||evt.getKeyCode()==evt.VK_RIGHT || evt.getKeyCode()==evt.VK_LEFT
          ||evt.getKeyCode()==evt.VK_ENTER || evt.getKeyCode()==evt.VK_TAB){             
              
          Object CPTfromTable=billingTable.getValueAt(cptRow,0);
              
          if(CPTfromTable ==null && CPTfromTable ==""){
              lblStatus.setText("Enter the CPT and Press Tab key to continue billing ...");
          }else if(CPTfromTable!=null && CPTfromTable!=""){
              lblStatus.setText("Press Tab to continue ...");
          }

          if(cptCol==cPTR && lblBill.getText().startsWith("G")){
              lblStatus.setText("To Save the bill press Alt+A or click 'Save' button ...");
          }
          if(cptCol==cPTR && lblBill.getText().startsWith("B")){
              lblStatus.setText("To Update the bill press Alt+A or click 'Update' button ...");
          }

              if(typeofEncounter.equalsIgnoreCase("V")){

                  BillMethods bm=new BillMethods();
                  int i=tabVisits.getSelectedRow();
                  String visitNo=tabVisits.getValueAt(i,cVISITNO).toString();
                  Object[] icdsFV=bm.setICDfromVistA(lblHRN.getText(),visitNo);

                  if(icdsFV!=null){
                         showICDfromVistA(icdsFV);
                  }else{
                      clearICDCombo();
                      lblStatus.setText("Warning: No ICDs available from VistA");
                  }
                  getChargeCodeICDSofCPT(cptRow,cptCol,CPTfromTable);

              }else{
                getChargeCodeICDSofCPT(cptRow,cptCol,CPTfromTable);
                ICDsPointer();
              }

              if(evt.VK_ESCAPE!=evt.getKeyCode()){
                  if(cptCol==cICD1 || cptCol==cICD2
                   || cptCol==cICD3 || cptCol==cICD4
                   || cptCol==cCHGCD)
                  {                                      
                    evt.setKeyCode(evt.VK_F2);
                    lblStatus.setText("Select the ICDs by 'Down-Arrow-key' for the CPT. Press Enter and Tab to continue...");
                                                       
                  }
              }else{
                  evt.setKeyCode(evt.VK_ESCAPE);
              }              
          }

        if(evt.getKeyCode()==10){
              evt.setKeyCode(9);
              
          }          
          //--------- Start key movement -------------------------------------//

            if(evt.getKeyCode()==9){
                 
                 Object ChargeCode=(Object) billingTable.getValueAt(cptRow, cptCol);

                  if(cptCol==cCHGCD && ChargeCode!=null){
                      billingTable.changeSelection(cptRow, cADJCD, false, false);
                      billingTable.requestFocus();
                  }
                  if(cptCol==cPOS && cptRow==5){
                      billingTable.changeSelection(0, 0, false, false);
                      billingTable.setFocusable(true);
                      billingTable.requestFocus();
                  }else if(cptCol==cPOS ){                    
                      billingTable.setEditingColumn(0);
                      billingTable.setFocusable(true);                     
                  }             
              }
      
              //----------- END Enter key movement ---------------------------------//

              //----------------------- CPTs ChargeCode Calculation ----------------------------------//
               
                if(cptCol==cCHGCD){
                try{    String[] chargeCode=null;
                        lblStatus.setText("Select the ChargeCode by 'Down-Arrow-key' for the CPT. Press Enter and Tab key to continue ...");
                        if(billingTable.getValueAt(cptRow,cCHGCD)!=null){
                            chargeCode= (String[]) billingTable.getValueAt(cptRow,cCHGCD).toString().split(" | ");
                        
                        if(chargeCode[0]!=null){
                            
                              BillMethods charg=new BillMethods();
                              String cptCode="";
                              String p;

                              charge=charg.setChargeCode(cptCode,chargeCode[0].toString());
                              //===== Get charge code =====
                               if(charge!=null){
                                    do{
                                        ChargeCode cc=new ChargeCode();
                                        cc=charge.get(0);
                                        p=(String)cc.getPrice();
                                        Double price=Double.valueOf(p).doubleValue();
                                        billingTable.setValueAt(price, cptRow, cCHG);
                                        billingTable.setValueAt(cc.getDefaultPOS(), cptRow, cPOS);

                                        //==== Get Provider NPI ====
                                        DoctorMethods dm=new DoctorMethods();
                                        List<Doctor> lstD=new ArrayList<Doctor>();
                                        lstD=dm.loadDoctors(doctorName);
                                        String NPITax;
                                        if(lstD!=null){
                                            for(Doctor lst:lstD){
                                                NPITax=lst.getNPI()+";"+lst.getTAXONOMY();
                                                billingTable.setValueAt(NPITax, cptRow, cPRD);                                                
                                            }
                                        }

                                    }while(charge.size()<0);
                                    //==== Calculate the units * Price(charge)=TotalCharges
                                    try{
                                              Integer units;
                                              Double price;
                                              Double totalCharges;

                                              String u=(String) billingTable.getValueAt(cptRow, cUNITS).toString();
                                              units= Integer.parseInt(u);
                                              price= (Double) billingTable.getValueAt(cptRow, cCHG);
                                              totalCharges=units*price;
                                              balance=totalCharges;
                                              
                                              billingTable.setValueAt(totalCharges, cptRow, cTOTCHG);
                                              billingTable.setValueAt(balance, cptRow, cBAL);

                                       }catch(Exception e){e.printStackTrace();}
                               }
                              cptCalculate();
                              TotalCalculation();
                        }
                            if(evt.getKeyCode()==evt.VK_RIGHT ||evt.getKeyCode()==evt.VK_ENTER || evt.getKeyCode()==evt.VK_TAB){
                                billingTable.changeSelection(cptRow, cPRD, false, false);
                                billingTable.requestFocus();
                            }
                      }                        
                      
                    }catch(Exception e){e.printStackTrace();}
                  }
                  if(cptCol==cADJAMT && evt.getKeyCode()== 9){
                  //-- Calculate the TotalCharges - AdjAmt=Balance
                    try{
                          Object adjAmt;
                          Double totalCharges = null;                         

                          adjAmt= (Object) billingTable.getValueAt(cptRow, cADJAMT);
                          if(adjAmt!=null){
                              Double aa=Double.valueOf(adjAmt.toString()).doubleValue();
                              totalCharges=(Double) billingTable.getValueAt(cptRow, cTOTCHG);
                              balance=totalCharges-aa;
                              billingTable.setValueAt(balance, cptRow, cBAL);
                          }
                          
                  }catch(Exception e){e.printStackTrace();}
                  }
                  //--------------- END CPTs ChargeCode Calculation ---------------------//
      }catch(Exception e){
          e.printStackTrace();
      }  
}//GEN-LAST:event_billingTableKeyPressed


public void FinalCalculation(){
     try{
                cptCalculate();
                TotalCalculation();

        }catch(Exception e){e.printStackTrace();}
}

   public void clearICDCombo(){
       
       TableColumn ICD1fill = billingTable.getColumnModel().getColumn(cICD1);
       TableColumn ICD2fill = billingTable.getColumnModel().getColumn(cICD2);
       TableColumn ICD3fill = billingTable.getColumnModel().getColumn(cICD3);
       TableColumn ICD4fill = billingTable.getColumnModel().getColumn(cICD4);

       JComboBox comboBox = new JComboBox();
       comboBox.setSelectedIndex(-1);
       ICD1fill.setCellEditor(new DefaultCellEditor(comboBox));
       ICD2fill.setCellEditor(new DefaultCellEditor(comboBox));
       ICD3fill.setCellEditor(new DefaultCellEditor(comboBox));
       ICD4fill.setCellEditor(new DefaultCellEditor(comboBox));
   }

   public void clearChargeCodeCombo(){
       
         TableColumn chargeCodefill = billingTable.getColumnModel().getColumn(cCHGCD);
         JComboBox comboBox = new JComboBox();
         comboBox.setSelectedIndex(-1);
         chargeCodefill.setCellEditor(new DefaultCellEditor(comboBox));
    }       
    private void billingTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billingTableKeyReleased
        // TODO add your handling code here:
        evt.consume();
}//GEN-LAST:event_billingTableKeyReleased

    private void saveUpdateBill(){
    try{
         int rowCount=billingTable.getRowCount();
         int res=0;
         String cpt,chargeCode;
         String insType="";

         insType=InsuranceTabbedPane.getTitleAt(InsuranceTabbedPane.getSelectedIndex()).substring(0, 1);

         billCaseID=txtBillNo.getText();

         BillAmt billAmt=new BillAmt();
         BillMethods bmSave=new BillMethods();
         List<Bill> lstSaveCPTdetails=new ArrayList<Bill>();
         billCaseID=txtBillNo.getText();

          for(int i=0;i<rowCount;i++){
              Bill bill=new Bill();
              cpt=(String) billingTable.getValueAt(i, cCPT);
              chargeCode=(String) billingTable.getValueAt(i, cCHGCD);
              
                         bill.setBillNo(billCaseID);
                         bill.setCaseID(billCaseID); 

                     if(billingTable.getValueAt(i, cCPT)!=null){
                        bill.setCPT(billingTable.getValueAt(i, cCPT).toString());
                     }

                     if(billingTable.getValueAt(i, cUNITS)!=null){
                             bill.setCPTUnits(billingTable.getValueAt(i, cUNITS).toString());
                     }
                    
                     if(billingTable.getValueAt(i, cCHG)!=null)
                     bill.setCPTRate(billingTable.getValueAt(i, cCHG).toString());
                    
                     if(billingTable.getValueAt(i, cTOTCHG)!=null)
                     bill.setCharges(billingTable.getValueAt(i, cTOTCHG).toString());
                   
                     if(billingTable.getValueAt(i, cCHGCD)!=null){
                         if(billingTable.getValueAt(i, cCHGCD).toString().contains("|")){
                             String[] str=billingTable.getValueAt(i, cCHGCD).toString().split(" | ");
                             
                             if(str.length>1){
                               
                                bill.setCoIns(str[0]);
                             }
                         }else{
                                bill.setCoIns(billingTable.getValueAt(i, cCHGCD).toString());
                         }

                     }
                    
                     if(billingTable.getValueAt(i, cADJCD)!=null)
                     bill.setAdjCode(billingTable.getValueAt(i, cADJCD).toString());
                    
                     if(billingTable.getValueAt(i, cADJAMT)!=null)
                     bill.setAdjAmt(billingTable.getValueAt(i, cADJAMT).toString());
                    
                     if(billingTable.getValueAt(i, cBAL)!=null)
                     bill.setBalance(billingTable.getValueAt(i, cBAL).toString());
                    
                     if(billingTable.getValueAt(i, cICD1)!=null){
                     bill.setICD1(billingTable.getValueAt(i, cICD1).toString());                   
                     }

                     if(billingTable.getValueAt(i, cICD2)!=null){
                     bill.setICD2(billingTable.getValueAt(i, cICD2).toString());                    
                     }

                     if(billingTable.getValueAt(i, cICD3)!=null){
                     bill.setICD3(billingTable.getValueAt(i, cICD3).toString());                    
                     }

                     if(billingTable.getValueAt(i, cICD4)!=null) {
                     bill.setICD4(billingTable.getValueAt(i, cICD4).toString());                   
                     }

                     if(billingTable.getValueAt(i, cPRD)!=null) {
                     bill.setProviderId(billingTable.getValueAt(i, cPRD).toString());
                     }

                     if(billingTable.getValueAt(i, cPTR)!=null) {
                     bill.setICDPointer(billingTable.getValueAt(i, cPTR).toString());
                     }

                     if(billingTable.getValueAt(i, cPOS)!=null) {
                     bill.setPOS(billingTable.getValueAt(i, cPOS).toString());
                     }

                     if(billingTable.getValueAt(i, cM1)!=null) {
                     bill.setM1(billingTable.getValueAt(i, cM1).toString());
                     }

                     if(billingTable.getValueAt(i, cM2)!=null) {
                     bill.setM2(billingTable.getValueAt(i, cM2).toString());
                     }
                    lstSaveCPTdetails.add(bill);
                 }

             // ============= Bill amt setting ===========

                    if(billCaseID!=null){
                     billAmt.setBillNo(billCaseID);
                     billAmt.setCaseID(billCaseID);
                     }
                     billAmt.setBillDate(Utiles.convertUSDatetoMysql(jLabel6.getText()));

                     if(totalBillTable.getValueAt(0, cTOTCHG)!=null)
                     billAmt.setTotalCharges(totalBillTable.getValueAt(0, cTOTCHG).toString());
                     
                     if(totalBillTable.getValueAt(0, cADJAMT)!=null)
                     billAmt.setTotalAdjAmt(totalBillTable.getValueAt(0, cADJAMT).toString());
                     if(totalBillTable.getValueAt(0, cBAL)!=null)
                     billAmt.setTotalBalance(totalBillTable.getValueAt(0, cBAL).toString());

                    int rowICDCount=icdsTable.getRowCount();
                    String[] icdRow=new String[4];
                    int maxICD=4;
                    for(int k=0;k<maxICD;k++){
                        if(k>=rowICDCount){
                            icdRow[k]=null;
                        }else{
                            if(icdsTable.getValueAt(k, 0)!=null)
                                icdRow[k]=icdsTable.getValueAt(k, 0).toString();
                        }
                    }
                         billAmt.setICD1(icdRow[0]);
                         billAmt.setICD2(icdRow[1]);
                         billAmt.setICD3(icdRow[2]);
                         billAmt.setICD4(icdRow[3]);

             // ********************** Bill Amt setting End  *********** //

                 if(butSave.getText().equalsIgnoreCase("Save")){
                   res=bmSave.saveBill_BillAmt(lstSaveCPTdetails,billAmt);
                 }else{
                       res=bmSave.updateBill_BillAmt(lstSaveCPTdetails,billAmt);
                 }

                checkClaimExists();
                         
                if(res==1){
                     JOptionPane.showMessageDialog(frame,"Bill "+butSave.getText().toString()+"d Successfully...");
                     lblStatus.setText("Press Alt+P or click 'Print or Reprint' claim button to view the claim ...");
                     butSave.setEnabled(false);
                     flagBillSave=1;
                     butPrint.requestFocus();
                     butPrint.setEnabled(true);
                }

    }catch(Exception e){e.printStackTrace();}
    }
    
    private void butSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSaveActionPerformed
        // TODO add your handling code here:
        if(flagPatientInsurance!=1){
            JOptionPane.showMessageDialog(null,"Claim cannot be generated. \n No insurance detail of patient.");
            this.butSave.requestFocus();
            return;
        }
        saveUpdateBill();
}//GEN-LAST:event_butSaveActionPerformed

    private void TotalCalculation(){
    try{
            int rowCount=billingTable.getRowCount();

            Double totCharges = 0.0;
            Double totAdjAmt=0.0;
            Double totBalance=0.0;

            Object cpt=new Object();
            Object charges=new Object();
            Object adjAmt=new Object();
            Object balance=new Object();
            
                for(int i=0;i<rowCount;i++){
                        cpt=billingTable.getValueAt(i, cCPT);
                        charges=billingTable.getValueAt(i, cTOTCHG);
                        adjAmt=billingTable.getValueAt(i, cADJAMT);
                        balance=billingTable.getValueAt(i, cBAL);

                        if(cpt!=null && charges!=null && balance!=null){                            

                            Double chargess=Double.valueOf(charges.toString()).doubleValue();
                          
                            totCharges=totCharges+chargess;
                            totalBillTable.setValueAt(totCharges, 0, cTOTCHG);

                            if(adjAmt!=null){
                            String s=(String)adjAmt;
                            Double aAmt= Double.valueOf(s).doubleValue();
                            totAdjAmt=totAdjAmt+aAmt;
                            totalBillTable.setValueAt(totAdjAmt, 0, cADJAMT);
                            }

                            Double bal=Double.valueOf(balance.toString()).doubleValue();
                            totBalance=totBalance+bal;
                            totalBillTable.setValueAt(totBalance, 0, cBAL);                            
                        }
                        
                        if(billingTable.getValueAt(0, 0)==null){
                            clearTotalBill();
                        }                      
                }

        }catch(Exception e){e.printStackTrace();}
    }

    
    private void cptCalculate(){
        try{

                String[] collectICDs=new String[24];
                String[][] rowICds=new String[6][4];
                int rowCount=billingTable.getRowCount();
                //========== collectIcds to display unique icds ======
                int k=0;
                String icd;

                for(int i=0;i<rowCount;i++){
                     for(int j=4;j<8;j++){
                        if(billingTable.getValueAt(i, j)==null){                          
                        }else{                            
                            if(billingTable.getValueAt(i, j).toString().compareToIgnoreCase("")!=0){
                                collectICDs[k]=(String) billingTable.getValueAt(i, j);                                
                            }
                        }
                        if(billingTable.getValueAt(i, j)==null)icd="0.0";
                        else icd=(String)billingTable.getValueAt(i, j);
                        rowICds[i][j-4]=icd;
                        k++;
                    }
                }

                String[] setICDs;
                Utiles uts=new Utiles();
                setICDs=uts.eliminateDuplicateICDs(collectICDs);
                String finICDs[]=new String[setICDs.length];
                
                BillMethods bm=new BillMethods();

                finICDs=bm.finalICDs(setICDs,collectICDs);

                if(setICDs.length>5)
                    JOptionPane.showMessageDialog(frame, "Unique ICDs should not be more than 4.");
                else{
                    getICDDetails(finICDs);

                    //====== Point the Rowwise CPT with the unique ICDs ==========
                        String[] icdPTRs=new String[6];
                        int r=0,icdPtr=1;
                        icdPTRs[0]=" ";                       

                        for(int q=0;q<rowCount;q++){
                           for(int p=0;p<4;p++){
                             for(int b=1;b<finICDs.length;b++){
                                 
                                if(rowICds[q][p].equalsIgnoreCase(finICDs[b-1])){

                                  if(p==0) {
                                     icdPTRs[r]=String.valueOf(b).toString();                                       
                                  }
                                  else {             
                                     icdPTRs[r]=icdPTRs[r]+","+String.valueOf(b).toString();                                     
                                  }
                                }
                             }icdPtr=1;
                            } r++;
                        }
                        
                        for(int m=0;m<icdPTRs.length;m++){
                            billingTable.setValueAt(icdPTRs[m], m, cPTR);
                            if(flagBillSave!=1){
                                butSave.setEnabled(true);
                            }else{
                                butSave.setText("Update");
                                butSave.setEnabled(true);
                            }                            
                        }

               //======= END Point the Rowwise CPT with the unique ICDs =========
                }
        }catch(Exception e){e.printStackTrace();}
    }
/*
    private String sortPointer(String p){
    try{
        String arrange[]=null;
        String temp;       

        if(p!=null){
          arrange=p.split(",");
          if(arrange.length>1){
            for(int i=0;i<arrange.length;i++){
               for(int j=i+1;j<arrange.length;j++){
                    if(Integer.parseInt(arrange[i])>Integer.parseInt(arrange[j])){
                        temp=arrange[i];
                        arrange[i]=arrange[j];
                        arrange[j]=temp;                        
                    }
               }
            }            
            p="";
            for(int h=0;h<arrange.length;h++){
                p=p+arrange[h]+",";
            }
          }
        }
        return p;
    }catch(Exception e){e.printStackTrace();return null;}
    }
*/
    @SuppressWarnings("empty-statement")
    private void totalBillTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalBillTableMouseClicked
        // TODO add your handling code here:
        cptCalculate();
}//GEN-LAST:event_totalBillTableMouseClicked

    private void butSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSearchActionPerformed
        // TODO add your handling code here:
    try{
        if(MDI.searchCriteria==null){
            MDI.searchCriteria=new SearchCriteria();
            PatientList.whichForm = PatientList.sBill;
            butSearch.setEnabled(false);
            this.dispose();
        }
    }catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_butSearchActionPerformed

    private void cmbEncounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEncounterActionPerformed
        // TODO add your handling code here:
try{
        if(cmbEncounter.getSelectedItem().toString().startsWith("P")){
            displayPMSVisit();
        }
        if(cmbEncounter.getSelectedItem().toString().startsWith("V")){
            displayVistAEncounters();
        }
        if(cmbEncounter.getSelectedItem().toString().startsWith("B")){            
                displayBothEncounters();            
        }
        if(cmbEncounter.getSelectedItem().toString().isEmpty()){
            clearVisitDetails();
        }
        
}catch(Exception e){e.printStackTrace();}
}//GEN-LAST:event_cmbEncounterActionPerformed

    private void claimGenerate(){
    try{
         int res=0;
         
         String insType=null;
         CMS1500Methods cms1500=new CMS1500Methods();
         billCaseID=txtBillNo.getText();
         
         insType=InsuranceTabbedPane.getTitleAt(InsuranceTabbedPane.getSelectedIndex()).substring(0, 1);

         String visitNo,visitDate,provider;
         int selRow=tabVisits.getSelectedRow();
         visitNo=(String) tabVisits.getValueAt(selRow, cVISITNO);
         visitDate=Utiles.convertUSDatetoMysql(tabVisits.getValueAt(selRow, cVISITDT).toString());
         provider=(String)tabVisits.getValueAt(selRow, cPROVIDER);

         if(flagBillSave==1){
            if(butPrint.getText().equalsIgnoreCase("Print Claim")){
                res=cms1500.SaveCMSHCFA1500_CPTS(lblHRN.getText(),billCaseID,insType,visitNo,provider,visitDate);
             }else{
                res=cms1500.updateCMSHCFA1500_CPTS(lblHRN.getText(),billCaseID,insType,visitNo,provider,visitDate);
             }
            if(res==1){
                 int gen=0;
                 claim r=new claim();
                 gen=r.generateReport(billCaseID+insType);
                 lblStatus.setText("");
                 butPrint.setEnabled(false);
                 flagPrintSave=1;
            }
        }

    }catch(Exception e){e.printStackTrace();}
    }

    private void checkClaimExists(){
    try{
         String insType=null;
         int res=0;
         CMS1500Methods cms1500=new CMS1500Methods();
         billCaseID=txtBillNo.getText();
         
         insType=InsuranceTabbedPane.getTitleAt(InsuranceTabbedPane.getSelectedIndex()).substring(0, 1);
 
         res=cms1500.checkClaim(billCaseID+insType);

         if(res==0){
             butPrint.setText("Print Claim");
         }else{
              butPrint.setText("Reprint");
         }         
    }catch(Exception e){e.printStackTrace();}
    }
    private void butPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPrintActionPerformed
        // TODO add your handling code here:
        claimGenerate();
}//GEN-LAST:event_butPrintActionPerformed

    private void butSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_butSaveKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            saveUpdateBill();
        }
    }//GEN-LAST:event_butSaveKeyPressed

  
    private void billingTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_billingTableFocusGained
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(frame,"Focus"+ evt.getID());
    }//GEN-LAST:event_billingTableFocusGained

    private void billingTableHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_billingTableHierarchyChanged
        // TODO add your handling code here:
       // JOptionPane.showMessageDialog(frame, evt.getChanged());
    }//GEN-LAST:event_billingTableHierarchyChanged

    private void billCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billCheckMouseClicked
        // TODO add your handling code here:
    try{
        int curRow=billCheck.getSelectedRow();
        int curCol=billCheck.getSelectedColumn();

        billingTable.changeSelection(curRow,curCol, false, false);
        billingTable.setFocusable(true);
        billingTable.requestFocus();

        butDelete.setEnabled(true);
        
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_billCheckMouseClicked

    private void butDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDeleteActionPerformed
        // TODO add your handling code here:
    try{
        int curCol=billCheck.getSelectedColumn();
        int chk=1;
        int totRow=billCheck.getRowCount();
        DefaultTableModel model = null;
        if(billingTable.getRowCount()>0){
          for(int curRow=0;curRow<totRow;curRow++){
              
               if(billCheck.getValueAt(curRow, 0).equals(true)){
                String cptCode=billingTable.getValueAt(curRow,0).toString();
               
                   if(chk==1){
                    chk=JOptionPane.showConfirmDialog(frame,"Do U want to delete the checked CPTs");
                   }
                    if(chk==0){
                        
                        model = new DefaultTableModel();
                        model=     (DefaultTableModel) billingTable.getModel();
                        model.removeRow(curRow);
                        model.insertRow(curRow, new Object[]{null});                       
                        
                        DefaultTableModel modelCheck = new DefaultTableModel();
                        modelCheck=     (DefaultTableModel) billCheck.getModel();
                        modelCheck.removeRow(curRow);
                        modelCheck.insertRow(curRow, new Object[]{null});
                    }               
            }
           }
                    if(chk==0){
                        int len=billingTable.getRowCount();
                        for(int n=0;n<len-1;n++){
                            for(int m=0;m<len-1;m++){
                                if(billingTable.getValueAt(m, 0)==null){
                                    model.moveRow(m+1, m+1, m);
                                }
                            }
                        }
                       butDelete.setEnabled(false);
                       FinalCalculation();
                       saveUpdateBill();                       
                    }
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_butDeleteActionPerformed

    private void tabVisitsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabVisitsKeyPressed
        // TODO add your handling code here:
         try{
        int rowCount=tabVisits.getRowCount();
        int curRow=tabVisits.getSelectedRow();

        if(evt.getKeyCode()==evt.VK_DOWN){
            if(curRow==rowCount-1){
                    evt.setKeyCode(evt.VK_ENTER);
                    tabVisits.setFocusable(true);
                    tabVisits.requestFocus();
                    curRow=0;
            }else{
                    curRow=curRow+1;
            }
        }

        if(evt.getKeyCode()==evt.VK_UP){
            if(curRow!=0 && curRow<rowCount){
                curRow=curRow-1;
            }
        }

         if(curRow!=-1){
             showCPT(rowCount,curRow);
         }

    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_tabVisitsKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    try{
        if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
            MDI.billing=null;
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed

    private void billingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billingTableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_billingTableMouseClicked

    public void showChargeCodeCombo(List<ChargeCode> chargeCode){
    try{
            TableColumn chargeCodefill = billingTable.getColumnModel().getColumn(8);
            JComboBox comboBox = new JComboBox();
            int i=chargeCode.size();
            int j=0;
                  
            clearChargeCodeCombo();
            comboBox.addItem("");
        
            do{
                ChargeCode cc=new ChargeCode();
                cc=chargeCode.get(j);
                comboBox.addItem(cc.getChargeCode()+" | "+cc.getChargeCodeDes());                
                j++;i--;
            }while(i>0);
            chargeCodefill.setCellEditor(new DefaultCellEditor(comboBox));
            
        }catch(Exception e){e.printStackTrace();}
    }

    public void showICDCombo(List<CPT_ICD_LINK> icds){
        
        TableColumn ICD1fill = billingTable.getColumnModel().getColumn(cICD1);
        TableColumn ICD2fill = billingTable.getColumnModel().getColumn(cICD2);
        TableColumn ICD3fill = billingTable.getColumnModel().getColumn(cICD3);
        TableColumn ICD4fill = billingTable.getColumnModel().getColumn(cICD4);
        JComboBox comboBox = new JComboBox();
        
        int i=icds.size();
        int j=0;
        clearICDCombo();
        comboBox.addItem("");
        
        try{
            do{
                CPT_ICD_LINK icd=new CPT_ICD_LINK();
                icd=icds.get(j);               
                comboBox.addItem(icd.getDiagnosisCode());                
                ICD1fill.setCellEditor(new DefaultCellEditor(comboBox));
                ICD2fill.setCellEditor(new DefaultCellEditor(comboBox));
                ICD3fill.setCellEditor(new DefaultCellEditor(comboBox));
                ICD4fill.setCellEditor(new DefaultCellEditor(comboBox));
                j++;i--;
            }while(i>0);
            
        }catch(Exception e){e.printStackTrace();}
    }

    public void getICDDetails(String[] icdDetails){        
    try{            
            int lenArr=icdDetails.length;
            Object[][] arr=new Object[lenArr-1][2];
            
            for(int i=0;i<lenArr-1;i++){
            arr[i][cICD]=icdDetails[i];
            arr[i][cPOINTER]=(Object) new Boolean(false);
            }            
            icdsTable.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"ICD","*"}
             )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );
            TableColumn tc = icdsTable.getColumnModel().getColumn(cPOINTER);
            tc.setCellEditor(icdsTable.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(icdsTable.getDefaultRenderer(Boolean.class));
        
        }catch(ArrayIndexOutOfBoundsException e){e.printStackTrace(); }        
    }
    
    private void getCPTDetails(List<Bill> lstCPTDetails){
    try{
          int rowLength;
          int i=0;
          rowLength=0;
          lblBill.setText("Bill already exists ...");
          lblStatus.setText("Press Tab key to continue billing ...");
          i=lstCPTDetails.size();
          Object[][] arr = new Object[i][17];
          
          do {  Bill cptDetails = new Bill();
                cptDetails=lstCPTDetails.get(rowLength);

                arr[rowLength][cCPT]=cptDetails.getCPT();
                arr[rowLength][cUNITS]=cptDetails.getCPTUnits();
                arr[rowLength][cM1]=cptDetails.getM1();
                arr[rowLength][cM2]=cptDetails.getM2();
                arr[rowLength][cICD1]=cptDetails.getICD1();
                arr[rowLength][cICD2]=cptDetails.getICD2();
                arr[rowLength][cICD3]=cptDetails.getICD3();
                arr[rowLength][cICD4]=cptDetails.getICD4();
                arr[rowLength][cCHGCD]=cptDetails.getCoIns();
                arr[rowLength][cCHG]=cptDetails.getCPTRate();
                arr[rowLength][cTOTCHG]=cptDetails.getCharges();
                arr[rowLength][cADJCD]=cptDetails.getAdjCode();
                arr[rowLength][cADJAMT]=cptDetails.getAdjAmt();
                arr[rowLength][cBAL]=cptDetails.getBalance();

                arr[rowLength][cPRD]=cptDetails.getProviderId();

                arr[rowLength][cPTR]=cptDetails.getICDPointer();
                arr[rowLength][cPOS]=cptDetails.getPOS();

                rowLength++;
             }while(rowLength<i);
               
               billingTable.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"CPT","Units","M1","M2","ICD1","ICD2","ICD3","ICD4","Chargecode & Des","Charges","Tot.charges","Adj.code","Adj.amt","Balance","Provider(NPI;TAX)","Pointer","POS"}));

               if(billingTable.getValueAt(0,0)==null){
                    clearTotalBill();
               }

               dynamicWidthofColumnforTable3();               
               billAlreadyExists=1;
               
               if(billAlreadyExists==1){
                try{
                    int rowCount=billingTable.getRowCount();
                    if(rowCount>0){
                       //lblStatus.setText
                     }
                   }catch(Exception e){e.printStackTrace();}
              
               }
     }catch(Exception e){e.printStackTrace(); }                
    }


    private void dynamicWidthofColumnforTable5(){
        TableColumn tc;
               tc = totalBillTable.getColumnModel().getColumn(cUNITS);
               tc.setPreferredWidth(50);

               tc = totalBillTable.getColumnModel().getColumn(cM1);
               tc.setPreferredWidth(50);

               tc = totalBillTable.getColumnModel().getColumn(cM2);
               tc.setPreferredWidth(50);

               tc = totalBillTable.getColumnModel().getColumn(cICD1);
               tc.setPreferredWidth(75);

               tc = totalBillTable.getColumnModel().getColumn(cICD2);
               tc.setPreferredWidth(75);

               tc = totalBillTable.getColumnModel().getColumn(cICD3);
               tc.setPreferredWidth(75);

               tc = totalBillTable.getColumnModel().getColumn(cICD4);
               tc.setPreferredWidth(75);

               tc = totalBillTable.getColumnModel().getColumn(cCHGCD);
               tc.setPreferredWidth(200);

               tc = totalBillTable.getColumnModel().getColumn(cPRD);
               tc.setPreferredWidth(200);

               tc = totalBillTable.getColumnModel().getColumn(cPOS);
               tc.setPreferredWidth(50);        
    }

    private void dynamicWidthofColumnforjTable1(){
        
               TableColumn tc;
               tc = tabVisits.getColumnModel().getColumn(cVISITNO);
               tc.setPreferredWidth(50);

               tc = tabVisits.getColumnModel().getColumn(cVISITDT);
               tc.setPreferredWidth(100);

               tc = tabVisits.getColumnModel().getColumn(cCLINIC);
               tc.setPreferredWidth(100);
              
               tc = tabVisits.getColumnModel().getColumn(cPROVIDER);
               tc.setPreferredWidth(100);

               tc = tabVisits.getColumnModel().getColumn(cAPPTDT);
               tc.setPreferredWidth(100);

               tc = tabVisits.getColumnModel().getColumn(cTYPE);
               tc.setPreferredWidth(5);
    }

    private void dynamicWidthofColumnforTable3(){       
        
               TableColumn tc;
               tc = billingTable.getColumnModel().getColumn(cUNITS);
               tc.setPreferredWidth(50);

               tc = billingTable.getColumnModel().getColumn(cM1);
               tc.setPreferredWidth(50);

               tc = billingTable.getColumnModel().getColumn(cM2);
               tc.setPreferredWidth(50);

               tc = billingTable.getColumnModel().getColumn(cICD1);
               tc.setPreferredWidth(75);

               tc = billingTable.getColumnModel().getColumn(cICD2);
               tc.setPreferredWidth(75);

               tc = billingTable.getColumnModel().getColumn(cICD3);
               tc.setPreferredWidth(75);

               tc = billingTable.getColumnModel().getColumn(cICD4);
               tc.setPreferredWidth(75);

               tc = billingTable.getColumnModel().getColumn(cCHGCD);
               tc.setPreferredWidth(200);

               tc = billingTable.getColumnModel().getColumn(cPRD);
               tc.setPreferredWidth(200);

               tc = billingTable.getColumnModel().getColumn(cPOS);
               tc.setPreferredWidth(50);

    }
    private void getTotalBillDetails(BillAmt ba){
    try{
            Float totunits = null,totCharges=null,totAdjAmt=null,totBalance=null;
           
            if(ba.getTotalCharges()==null){                
            }else{
                totCharges=Float.valueOf(ba.getTotalCharges()).floatValue();
            }

            if(ba.getTotalBalance()==null){                
            }else{
                totBalance=Float.valueOf(ba.getTotalBalance()).floatValue();
            }

            if(totunits!=null)
            totalBillTable.setValueAt(totunits, 0, cUNITS);
            if(totCharges!=null)
            totalBillTable.setValueAt(totCharges, 0, cTOTCHG);
            if(totAdjAmt!=null)
            totalBillTable.setValueAt(totAdjAmt, 0, cADJAMT);
            if(totBalance!=null)
            totalBillTable.setValueAt(totBalance, 0, cBAL);

            dynamicWidthofColumnforTable5();
            
    }catch(Exception e){e.printStackTrace();}
}

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            try{
                    new Billing().setVisible(true);                    
              }catch(Exception e){e.printStackTrace();}               
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane InsuranceTabbedPane;
    private javax.swing.JTable billCheck;
    private javax.swing.JTable billingTable;
    private javax.swing.JButton butDelete;
    private javax.swing.JButton butPrint;
    private javax.swing.JButton butReset;
    private javax.swing.JButton butSave;
    private javax.swing.JButton butSearch;
    private javax.swing.JComboBox cmbEncounter;
    private javax.swing.JTable icdsTable;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblBill;
    private javax.swing.JLabel lblHRN;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tabVisits;
    private javax.swing.JTable totalBillTable;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBillNo;
    private javax.swing.JTextField txtCaseID;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMS;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSSN;
    private javax.swing.JTextField txtSex;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables
    
}
