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

import com.etipl.pms.datalayer.PaymentPostingDB;
import com.etipl.pms.entity.PaymentPostingDef;
import com.etipl.pms.utilities.Utiles;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import org.gui.JCalendarCombo;
import org.jdesktop.swingx.autocomplete.Configurator;


public class PaymentPosting extends javax.swing.JFrame {
    public static String ss1="",SSlotNo;
    public String slotNo="",MMMHRN,cptNo,valuepay,valueall,valueadj,BALANCE="";
    public static String pPaymode,pChelno="",pDesc,pPayAmt,pRecCode,unpostamt="",pDay1,pMonth1,pYear1,pDay2,pMonth2,pYear2;
    public static String[] FrUpdtBalance =  new String[50];

    private static Object payment="";
    private static Double setPayment=0.0;
    private int cServDate=1,cProcedure=2,cCharges=3,cBalance=4,cPayment=5,cDeduct=6;
    private int cWithh=7,cAllowed=8,cAdj=9,cTakeBack=10,cComplete=11,cRejection=12,cProv=13;
    private int cUnpostedAmt=7,rowTable1=0,curRowTable2=0;
    private int flag=0,cons=0,cons1=0,cons2=0,cons3=0,cons4=0,cons5=0;
    private int flagDontShowTotal=0;
    JFrame frame = new JFrame();

    /** Creates new form PaymentPosting */
    public PaymentPosting() {
        initComponents();
        setVisible(true);
        
        dynamicWidthofColumnforTable3();
        
        Configurator.enableAutoCompletion(this.jComboBox1);
        Configurator.enableAutoCompletion(this.jComboBox12);
        Configurator.enableAutoCompletion(this.jComboBox13);
        Configurator.enableAutoCompletion(this.jComboBox14);
        Configurator.enableAutoCompletion(this.jComboBox16);
        Configurator.enableAutoCompletion(this.jComboBox2);
        Configurator.enableAutoCompletion(this.jComboBox3);
        Configurator.enableAutoCompletion(this.jComboBox4);
        Configurator.enableAutoCompletion(this.jComboBox5);
        Configurator.enableAutoCompletion(this.jComboBox6);
        Configurator.enableAutoCompletion(this.jComboBox7);
        Configurator.enableAutoCompletion(this.jComboBox8);
        Configurator.enableAutoCompletion(this.jComboBox9);

        screenDisplay();

        jPanel7.setVisible(false);
        jPanel6.setVisible(false);
        jLabel4.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        jPanel16.setVisible(false);
        jTextField1.setVisible(false);
        jLabel21.setText("");
        jLabel26.setText("");
        jLabel28.setText("");
        jLabel60.setText("");
        jButton12.setVisible(false);
        jCalendarCombo1.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        jCalendarCombo2.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        jCheckBox1.setSelected(true);
        jCheckBox1.setVisible(false);

    }
  private void dynamicWidthofColumnforTable3(){

               TableColumn tc;
               tc = tabPostingDetails.getColumnModel().getColumn(0);
               tc.setPreferredWidth(40);

               tc = tabPostingDetails.getColumnModel().getColumn(cServDate);
               tc.setPreferredWidth(150);

               tc = tabPostingDetails.getColumnModel().getColumn(cProcedure);
               tc.setPreferredWidth(100);

               tc = tabPostingDetails.getColumnModel().getColumn(cCharges);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cBalance);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cPayment);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cDeduct);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cWithh);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cAllowed);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cAdj);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cTakeBack);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cComplete);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cRejection);
               tc.setPreferredWidth(85);

               tc = tabPostingDetails.getColumnModel().getColumn(cProv);
               tc.setPreferredWidth(150);

    }

  private void dynamicWidthofColumnforTable3a(){

               TableColumn tc;
               tc = tabPostTotal.getColumnModel().getColumn(0);
               tc.setPreferredWidth(40);

               tc = tabPostTotal.getColumnModel().getColumn(cServDate);
               tc.setPreferredWidth(150);

               tc = tabPostTotal.getColumnModel().getColumn(cProcedure);
               tc.setPreferredWidth(100);

               tc = tabPostTotal.getColumnModel().getColumn(cCharges);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cBalance);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cPayment);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cDeduct);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cWithh);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cAllowed);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cAdj);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cTakeBack);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cComplete);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cRejection);
               tc.setPreferredWidth(85);

               tc = tabPostTotal.getColumnModel().getColumn(cProv);
               tc.setPreferredWidth(150);
    }

  private void dynamicWidthofColumnforTable1(){

               TableColumn tc;
               tc = jTable1.getColumnModel().getColumn(0);
               tc.setPreferredWidth(40);

               tc = jTable1.getColumnModel().getColumn(1);
               tc.setPreferredWidth(150);

               tc = jTable1.getColumnModel().getColumn(2);
               tc.setPreferredWidth(180);

               tc = jTable1.getColumnModel().getColumn(3);
               tc.setPreferredWidth(200);

               tc = jTable1.getColumnModel().getColumn(4);
               tc.setPreferredWidth(200);

               tc = jTable1.getColumnModel().getColumn(5);
               tc.setPreferredWidth(85);

               tc = jTable1.getColumnModel().getColumn(6);
               tc.setPreferredWidth(85);

               tc = jTable1.getColumnModel().getColumn(7);
               tc.setPreferredWidth(85);

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCalendarCombo1 = new org.gui.JCalendarCombo();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField4 = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jComboBox8 = new javax.swing.JComboBox();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jComboBox13 = new javax.swing.JComboBox();
        jComboBox14 = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jCalendarCombo2 = new org.gui.JCalendarCombo();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblStatus = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabPostingDetails = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabPostTotal = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        lblStsPayment = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        chkOldPayments = new javax.swing.JCheckBox();
        jComboBox16 = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        lblUnpostedAmt = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment Posting");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbedPane1.setAlignmentX(1.0F);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Receipt Dt Frm:");

        jLabel2.setText("Payor Type :");

        jLabel3.setText("Payment Mode :");

        jLabel4.setText("jLabel4");

        jLabel5.setText("Description :");

        jLabel6.setText("Payment Amt($) :");

        jLabel7.setText("Receipt Code :");

        jLabel8.setText("Receipt No. :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Patient", "Insurance", "Capitation" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Check", "Cash", "Credit Card", "Electronic" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" }));
        jComboBox3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox3FocusLost(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });

        jLayeredPane1.setInheritsPopupMenu(true);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Insurance :");

        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
            }
        });

        jLabel10.setText("Payment Code :");

        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel11.setText("Adjustment Code :");

        jLabel12.setText("WithHold Code :");

        jComboBox9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox9KeyPressed(evt);
            }
        });

        jLabel14.setText("Takeback Code :");

        jLabel13.setText("Deductible Code :");

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel14)
                    .add(jLabel13)
                    .add(jLabel12)
                    .add(jLabel11)
                    .add(jLabel10))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jComboBox5, 0, 166, Short.MAX_VALUE)
                    .add(jComboBox6, 0, 166, Short.MAX_VALUE)
                    .add(jComboBox7, 0, 166, Short.MAX_VALUE)
                    .add(jComboBox8, 0, 166, Short.MAX_VALUE)
                    .add(jComboBox9, 0, 166, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jLabel10)
                        .add(14, 14, 14)
                        .add(jLabel11)
                        .add(14, 14, 14)
                        .add(jLabel12)
                        .add(14, 14, 14)
                        .add(jLabel13)
                        .add(14, 14, 14)
                        .add(jLabel14))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jComboBox5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 62, Short.MAX_VALUE)
                .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 143, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel6.setBounds(0, 10, -1, 220);
        jLayeredPane1.add(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("Patient :");

        jLabel22.setText("Payment Code :");

        jLabel23.setText("Adjustment Code :");

        jLabel24.setText("Co-Pay Code :");

        jComboBox14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox14KeyPressed(evt);
            }
        });

        jLabel25.setText("SEX  :");

        jLabel27.setText("DOB :");

        jButton10.setMnemonic('s');
        jButton10.setText("-Patient Search-");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });

        jLabel19.setText("HRN :");

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel24)
                            .add(jPanel7Layout.createSequentialGroup()
                                .add(jLabel22)
                                .add(27, 27, 27)
                                .add(jComboBox12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel7Layout.createSequentialGroup()
                                .add(jLabel23)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jComboBox13, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(jComboBox14, 0, 240, Short.MAX_VALUE)))
                            .add(jPanel7Layout.createSequentialGroup()
                                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel7Layout.createSequentialGroup()
                                        .add(jLabel27)
                                        .add(18, 18, 18)
                                        .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel7Layout.createSequentialGroup()
                                        .add(jLabel20)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(10, 10, 10)
                                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jPanel7Layout.createSequentialGroup()
                                        .add(jLabel19)
                                        .add(18, 18, 18)
                                        .add(jLabel60, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .add(jPanel7Layout.createSequentialGroup()
                                        .add(jLabel25)
                                        .add(18, 18, 18)
                                        .add(jLabel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(65, 65, 65)
                        .add(jButton10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(31, 31, 31))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel20)
                    .add(jLabel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel19)
                    .add(jLabel60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel27)
                        .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel25))
                    .add(jLabel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(jComboBox12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel23)
                    .add(jComboBox13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(jComboBox14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBounds(0, 30, 390, 180);
        jLayeredPane1.add(jPanel7, javax.swing.JLayeredPane.PALETTE_LAYER);

        jButton9.setText("Search");
        jButton9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButton9StateChanged(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });

        jLabel15.setText("To:");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jLabel4)
                    .add(jLabel5)
                    .add(jLabel6)
                    .add(jLabel7)
                    .add(jLabel8)
                    .add(jLabel2)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel15)
                        .add(jLabel1)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jCalendarCombo1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jCalendarCombo2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTextField4)
                    .add(jTextField3)
                    .add(jTextField2)
                    .add(jComboBox3, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTextField1)
                    .add(jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jComboBox1, 0, 145, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 86, Short.MAX_VALUE)
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 413, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(jCalendarCombo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jCalendarCombo2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel15))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel2)
                                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel3)
                                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel4)
                                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel5)
                                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel6)
                                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel7)
                                    .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel8)
                                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jButton9)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setText("New");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jButton12.setText("View Rcpt Details");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton12KeyPressed(evt);
            }
        });

        jButton13.setText("Post");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jButton13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton13KeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(228, Short.MAX_VALUE)
                .add(jButton1)
                .add(18, 18, 18)
                .add(jButton2)
                .add(18, 18, 18)
                .add(jButton13)
                .add(18, 18, 18)
                .add(jButton12)
                .add(18, 18, 18)
                .add(jButton3)
                .add(19, 19, 19)
                .add(jButton4)
                .add(125, 125, 125))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton4)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jButton13)
                    .add(jButton12)
                    .add(jButton3))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Rec. Date", "Rec. No.", "Description", "Payor Name", "Payor Type", "Payment", "Unposted Amt($)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
        );

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblStatus.setForeground(new java.awt.Color(0, 51, 204));
        lblStatus.setText("Please search the previous receipt(s) or make a new by clicking the 'New' button.");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(lblStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblStatus))
        );

        jTabbedPane1.addTab("                        Receipt                               ", jPanel1);

        tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Serv. Date", "Procedure", "Charges", "Balance", "Payment", "Deduct.", "Withh", "Allowed", "Adj.", "Take Back", "Complete", "Rejection", "Rcpt. Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPostingDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPostingDetailsMouseClicked(evt);
            }
        });
        tabPostingDetails.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabPostingDetailsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabPostingDetailsFocusLost(evt);
            }
        });
        tabPostingDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabPostingDetailsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabPostingDetailsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tabPostingDetailsKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tabPostingDetails);
        tabPostingDetails.getColumnModel().getColumn(0).setResizable(false);
        tabPostingDetails.getColumnModel().getColumn(0).setPreferredWidth(1);
        tabPostingDetails.getColumnModel().getColumn(1).setResizable(false);
        tabPostingDetails.getColumnModel().getColumn(2).setResizable(false);
        tabPostingDetails.getColumnModel().getColumn(7).setResizable(false);
        tabPostingDetails.getColumnModel().getColumn(7).setPreferredWidth(60);
        tabPostingDetails.getColumnModel().getColumn(9).setResizable(false);
        tabPostingDetails.getColumnModel().getColumn(9).setPreferredWidth(50);
        tabPostingDetails.getColumnModel().getColumn(13).setResizable(false);

        tabPostTotal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabPostTotal);
        tabPostTotal.getColumnModel().getColumn(0).setResizable(false);
        tabPostTotal.getColumnModel().getColumn(1).setResizable(false);
        tabPostTotal.getColumnModel().getColumn(7).setResizable(false);
        tabPostTotal.getColumnModel().getColumn(9).setResizable(false);

        jLabel18.setText("Total");

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });

        jButton6.setText("Reset");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jButton8.setText("Close");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });

        jLabel31.setText("jLabel31");

        lblStsPayment.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblStsPayment.setForeground(new java.awt.Color(0, 51, 204));
        lblStsPayment.setText("To post the payment, select the claim no.");

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(lblStsPayment, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton5)
                .add(18, 18, 18)
                .add(jButton6)
                .add(18, 18, 18)
                .add(jButton7)
                .add(18, 18, 18)
                .add(jButton8)
                .add(208, 208, 208))
            .add(jPanel9Layout.createSequentialGroup()
                .add(jLabel31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(774, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton7)
                    .add(jButton8)
                    .add(jButton6)
                    .add(jButton5)
                    .add(lblStsPayment))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel31)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel54.setText("Patient :");

        jLabel56.setText("Sex :");

        jLabel58.setText("DOB :");

        jLabel61.setText("HRN :");

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel14Layout.createSequentialGroup()
                        .add(jLabel56)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel14Layout.createSequentialGroup()
                        .add(jLabel54)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel14Layout.createSequentialGroup()
                        .add(jLabel58)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel59, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel14Layout.createSequentialGroup()
                        .add(jLabel61)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel54)
                    .add(jLabel55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel61)
                    .add(jLabel62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel56)
                    .add(jLabel57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel58)
                    .add(jLabel59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Gurantor :");

        jLabel41.setText("Sex :");

        jLabel52.setText("Relation :");

        jLabel43.setText("DOB :");

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(jLabel16)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(jLabel52)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(jLabel43))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel13Layout.createSequentialGroup()
                        .add(34, 34, 34)
                        .add(jLabel41)))
                .add(18, 18, 18)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel44, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel42, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .add(42, 42, 42))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel16)
                    .add(jLabel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel41)
                    .add(jLabel42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(7, 7, 7)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel52)
                    .add(jLabel53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel43)
                    .add(jLabel44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setText("Ins Cmp Name :");

        jLabel30.setText("jLabel30");

        jLabel48.setText("Phone :");

        jLabel49.setText("jLabel49");

        jLabel50.setText("jLabel50");

        jLabel51.setText("jLabel51");

        jLabel47.setText("jLabel47");

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel29)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel50)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 37, Short.MAX_VALUE)
                        .add(jLabel47)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel51)
                        .add(14, 14, 14)
                        .add(jLabel48)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel49)
                        .add(32, 32, 32))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel30)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 224, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel29)
                    .add(jLabel32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel30)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel50)
                    .add(jLabel48)
                    .add(jLabel49)
                    .add(jLabel47)
                    .add(jLabel51))
                .addContainerGap())
        );

        jPanel10.setBounds(30, 10, 310, 80);
        jLayeredPane2.add(jPanel10, javax.swing.JLayeredPane.PALETTE_LAYER);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel33.setText("Patient Name :");

        jLabel34.setText("jLabel34");

        jLabel35.setText("HRN :");

        jLabel36.setText("jLabel36");

        jLabel37.setText("Sex :");

        jLabel38.setText("jLabel38");

        jLabel39.setText("DOB :");

        jLabel40.setText("jLabel40");

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel37)
                        .add(18, 18, 18)
                        .add(jLabel38))
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel33)
                        .add(18, 18, 18)
                        .add(jLabel34)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 48, Short.MAX_VALUE)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel35)
                    .add(jLabel39))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel36)
                    .add(jLabel40))
                .add(75, 75, 75))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel33)
                    .add(jLabel34)
                    .add(jLabel35)
                    .add(jLabel36))
                .add(18, 18, 18)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel37)
                    .add(jLabel38)
                    .add(jLabel39)
                    .add(jLabel40))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBounds(10, 10, 340, 70);
        jLayeredPane2.add(jPanel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel45.setText("Unposted Amt($) :");

        chkOldPayments.setText("Shw Old Payments");
        chkOldPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOldPaymentsActionPerformed(evt);
            }
        });

        jComboBox16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox16MouseClicked(evt);
            }
        });
        jComboBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox16ActionPerformed(evt);
            }
        });
        jComboBox16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox16KeyPressed(evt);
            }
        });

        jLabel46.setText("Select Claim :");

        jCheckBox1.setText("Incl Paid Claims");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(chkOldPayments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel46)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED))
                    .add(jPanel12Layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(jLabel45)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lblUnpostedAmt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jCheckBox1)
                    .add(jPanel12Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jComboBox16, 0, 116, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel45)
                    .add(lblUnpostedAmt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBox1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel46)
                        .add(jComboBox16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(chkOldPayments))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBounds(10, 100, 350, 60);
        jLayeredPane2.add(jPanel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton11.setText("-Patient Search-");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton11KeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel16Layout = new org.jdesktop.layout.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .add(47, 47, 47)
                .add(jButton11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 144, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton11)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel15Layout = new org.jdesktop.layout.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 142, Short.MAX_VALUE)
                .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 392, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLayeredPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(jPanel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel18)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel18)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42))
        );

        jTabbedPane1.addTab("                          Posting                                  ", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 557, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

public String getDOB(String date){
    String Day,Month,Year,Date;
    Day=date.substring(8,10);
    Month=date.substring(5,7);
    Year=date.substring(0,4);
    Date=Month+'/'+Day+'/'+Year;

    return Date;
    }

    public String setDate(String date){
    String Day,Month,Year,Date;
    int j=date.length();
    if(j==8)
    {
     Month=date.substring(2,3);
     Day=date.substring(0,1);
     Year=date.substring(4,8);
     Date=Year+"-0"+Month+"-0"+Day;
    }else
        if(j==9)
        {
        Month=date.substring(3,4);
        Day=date.substring(0,2);
        Year=date.substring(5,9);
        Date=Year+"-0"+Month+'-'+Day;
        }
        else{
            Month=date.substring(3,5);
            Day=date.substring(0,2);
            Year=date.substring(6,10);
            Date=Year+'-'+Month+'-'+Day;
        }
    return Date;
    }


    public String setReceiptDate(String date){

    String Day,Month,Year,Date,Year1="";
    int j=date.length();
    if(j==8)
    {
     Month=date.substring(2,3);
     Day=date.substring(0,1);
     Year=date.substring(4,8);
     Year1=Year.substring(2);
     Date=Year1+"0"+Month+"0"+Day;
    }else
        if(j==9)
        {
        Month=date.substring(3,4);
        Day=date.substring(0,2);
        Year=date.substring(5,9);
        Year1=Year.substring(2);
        Date=Year1+"0"+Month+""+Day;
        }
        else{
            Month=date.substring(3,5);
            Day=date.substring(0,2);
            Year=date.substring(6,10);
            Year1=Year.substring(2);
            Date=Year1+""+Month+""+Day;
        }
    return Date;
    }

    public String setAmount(String amount){
        String amt1="",amt2="",amt3="",amt="";
        if(amount.indexOf('.')==-1)
        {
            amt=amount+".00";
        }else{
            int i=0;
            i=amount.indexOf(".");
            amt1=amount.substring(i+1);
            if(amt1.length()<2){
                amt=amount+"0";
            }else{
                  if(amt1.length()>2){
                    String [] temp = null;
                    temp = amount.split(".");
                    amt1=temp[0];
                    amt2=temp[1];
                    amt3=amt2.substring(0,2);
                    amt=amt1+amt3;
                } else{amt=amount;}
            }

        }
    return amt;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        lblStatus.setText("To make receipt choose date, payor type, mode and enter other details and then press save.");
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void reset(){
        flag=0;
        jButton1.setText("Save");
        jPanel7.setVisible(false);
        jPanel6.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jButton12.setVisible(false);

        clearCBPanel6();
        clearCBPanel7();
        jComboBox1.setSelectedItem("Select");
        jComboBox2.setSelectedItem("Select");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jComboBox3.setSelectedItem("A");
        jComboBox4.removeAllItems();
        jComboBox5.removeAllItems();
        jComboBox6.removeAllItems();
        jComboBox7.removeAllItems();
        jComboBox8.removeAllItems();
        jComboBox9.removeAllItems();
        jComboBox12.removeAllItems();
        jComboBox13.removeAllItems();
        jComboBox14.removeAllItems();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"#","Rec. Date","Rec. No","Description","Payor Name","Payor Type","Payment","Unposted Amt"}));
        dynamicWidthofColumnforTable1();
        jLabel21.setText("");
        jLabel26.setText("");
        jLabel28.setText("");
        jLabel36.setText("");
        jLabel55.setText("");
        jLabel57.setText("");
        jLabel59.setText("");
        jLabel17.setText("");
        jLabel42.setText("");
        jLabel53.setText("");
        jLabel44.setText("");
        jLabel60.setText("");
        jLabel32.setText("");
        lblUnpostedAmt.setText("");
        jComboBox16.removeAllItems();
        tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));
        String[][] arr = new String[1][14];
         for(int i=0;i<14;i++){
            arr[0][i]="";
         }
        tabPostTotal.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"","","","","","","","","","","","","",""}
        )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false, false,false, false,false, false,false, false,false, false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );

        dynamicWidthofColumnforTable3a();
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);        
    }

    private void showDifferentLayerforPayor(){
     try{
        PaymentPostingDB ppdb=new PaymentPostingDB();
        List<PaymentPostingDef> lstppen =new ArrayList<PaymentPostingDef>();
        
        String payorType=jComboBox1.getSelectedItem().toString();
        
        if(payorType.compareTo("Patient")==0)
        {
            jPanel16.setVisible(false);
            jPanel7.setVisible(true);
            jPanel6.setVisible(false);
            jComboBox12.removeAllItems();
            jComboBox13.removeAllItems();
            jComboBox14.removeAllItems();

                lstppen=ppdb.getCodesDetails("Pay");
                int j1=lstppen.size();
                int i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox12.insertItemAt(ppen.getPaymentCode(),i++);
                   j1--;
                }
//====== Adjustment =========
                lstppen=ppdb.getCodesDetails("Adj");
                j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox13.insertItemAt(ppen.getAdjCode(),i++);
                   j1--;
                }
//===== Co-Payment ==========
                lstppen=ppdb.getCodesDetails("CoPay");
                j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox14.insertItemAt(ppen.getCoPayCode(),i++);
                   j1--;
                }
        }else{
            if(payorType.compareTo("Insurance")==0){
                jPanel6.setVisible(true);
                jPanel8.setVisible(true);
                jPanel7.setVisible(false);
                jComboBox4.removeAllItems();
                jComboBox5.removeAllItems();
                jComboBox6.removeAllItems();
                jComboBox7.removeAllItems();
                jComboBox8.removeAllItems();
                jComboBox9.removeAllItems();       

                int i;
//===== insurance companies details =======
                lstppen=ppdb.getInsuranceDetails();
                if(!lstppen.isEmpty()){
                int j=lstppen.size();
                i=0;
                while(j>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox4.insertItemAt(ppen.getInsurance(),i++);
                   j--;
                }
               }           

//======= Payment ==========
                lstppen=ppdb.getCodesDetails("Pay");
                int j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox5.insertItemAt(ppen.getPaymentCode(),i++);
                   j1--;
                }
//======= Adjustment ======
                lstppen=ppdb.getCodesDetails("Adj");
                j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox6.insertItemAt(ppen.getAdjCode(),i++);
                   j1--;
                }

//======= WithHold =========
                lstppen=ppdb.getCodesDetails("Withh");
                j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox7.insertItemAt(ppen.getWithHoldCode(),i++);
                   j1--;
                }
//======= Deductible ========
                lstppen=ppdb.getCodesDetails("Ded");
                j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox8.insertItemAt(ppen.getDeductibleCode(),i++);
                   j1--;
                }

//======= TakeBake =========
                lstppen=ppdb.getCodesDetails("TakeB");
                j1=lstppen.size();
                i=0;
                while(j1>0){
                   PaymentPostingDef ppen=new PaymentPostingDef();
                   ppen=lstppen.get(i);
                   jComboBox9.insertItemAt(ppen.getTakeBackCode(),i++);
                   j1--;
                }
             }else{
 //========= capitation ===========
                  if(payorType.compareTo("Capitation")==0)
                    {
                      jPanel6.setVisible(true);jPanel8.setVisible(false);jPanel7.setVisible(false);//jPanel16.setVisible(true);
//======== insurance companies details ===========
                      jComboBox4.removeAllItems();
                      lstppen=ppdb.getInsuranceDetails();
                      if(!lstppen.isEmpty()){
                      int j=lstppen.size();
                      int i=0;
                      while(j>0){
                          PaymentPostingDef ppen=new PaymentPostingDef();
                          ppen=lstppen.get(i);
                          jComboBox4.insertItemAt(ppen.getInsurance(),i++);
                          j--;
                        }
                      }
                    }
             }
        }
       }catch(Exception e){e.printStackTrace();}        
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        showDifferentLayerforPayor();
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
       PaymentPostingDB ppdb=new PaymentPostingDB();
       PaymentPostingDef lstppen =new PaymentPostingDef();
       String s3="",s1="";

       if(jComboBox1.getSelectedItem().toString().compareTo("Insurance")==0)
       {
            try{
                s3=jComboBox4.getSelectedItem().toString();
                String [] temp = null;
                temp = s3.split("-");
                s1=temp[0];
                lstppen=ppdb.getInsuranceCompDetails(s1);
                jComboBox5.setSelectedItem(lstppen.getPaymentCode());
                jComboBox6.setSelectedItem(lstppen.getAdjCode());
                jComboBox7.setSelectedItem(lstppen.getWithHoldCode());
                jComboBox8.setSelectedItem(lstppen.getDeductibleCode());
                jComboBox9.setSelectedItem(lstppen.getTakeBackCode());
            }catch(Exception e){}
       }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        ResetPosting();
        jLabel60.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void ResetPosting(){
        payment="";
        cons=0;//for F2 into payment
        cons1=0;//for F2 into adjustment
        cons2=0;
        cons3=0;
        cons4=0;
        cons5=0;

        tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));
        String[][] arr = new String[1][14];
         for(int i=0;i<14;i++){
            arr[0][i]="";
         }
        tabPostTotal.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"","","","","","","","","","","","","",""}));
        dynamicWidthofColumnforTable3a();
        jLabel55.setText("");
         jLabel57.setText("");
         jLabel59.setText("");
         jLabel17.setText("");
         jLabel42.setText("");
         jLabel53.setText("");
         jLabel44.setText("");
         jLabel62.setText("");
         
         
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveUpdate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void saveUpdate(){
    try{
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();
        frame = new JFrame();
//=============== save =======================
        if(flag==0){
            jButton1.setText("Save");
        String ss2="",ss3="";
        ss1=ppdb.currentDate();

//========== insert reciept detsils ==========

        lstppen.setReceiptDate(ss1);
        lstppen.setPayorType(jComboBox1.getSelectedItem().toString());
        lstppen.setPaymentMode(jComboBox2.getSelectedItem().toString());
        if(jComboBox2.getSelectedItem().toString().compareTo("Check")==0){
            lstppen.setCheckNo(jTextField1.getText());
        }else{
              if(jComboBox2.getSelectedItem().toString().compareTo("Electronic")==0)
              {lstppen.setCheckNo(jTextField1.getText());}
        }
        lstppen.setDescription(jTextField2.getText());
        lstppen.setPaymentAmt(jTextField3.getText());
        lstppen.setReceiptCode(jComboBox3.getSelectedItem().toString());

//============ for receipt no. =========
        int h=Integer.valueOf(ppdb.assignReceiptNo(lstppen)).intValue()+1;
        String RNo=""+h;
        lstppen.setReceiptNo(setttinggg(RNo));
        ppdb.addReceipt(lstppen);
        jTextField4.setText(lstppen.getReceiptNo());

//======== insert payor_reciept details ==========
        PaymentPostingDB sppdb=new PaymentPostingDB();
        PaymentPostingDef sndppen =new PaymentPostingDef();
        if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0)
        {
            ss2="P";
        }else{
            if(jComboBox1.getSelectedItem().toString().compareTo("Insurance")==0){
                ss2="I";
            }else{
                if(jComboBox1.getSelectedItem().toString().compareTo("Capitation")==0){
                    ss2="C";
                }
            }
        }
        sndppen.setPayerName(ss2);

        if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0)
        {
            ss3=jLabel60.getText();
        }else{ ss3=jComboBox4.getSelectedItem().toString();
                String [] temp = null;
                temp = ss3.split("-");
                ss3=temp[0];
        }
        sndppen.setPayorId(ss3);

        if(jComboBox1.getSelectedItem().toString().compareTo("Insurance")==0)
        {
            sndppen.setPaymentCode(jComboBox5.getSelectedItem().toString());
            sndppen.setAdjCode(jComboBox6.getSelectedItem().toString());
            sndppen.setWithHoldCode(jComboBox7.getSelectedItem().toString());
            sndppen.setDeductibleCode(jComboBox8.getSelectedItem().toString());
            sndppen.setTakeBackCode(jComboBox9.getSelectedItem().toString());
        }else{
            if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0){
               sndppen.setPaymentCode(jComboBox12.getSelectedItem().toString());
               sndppen.setAdjCode(jComboBox13.getSelectedItem().toString());
               sndppen.setCoPayCode(jComboBox14.getSelectedItem().toString());
            }
        }

        sppdb.addPayor_Receipt(sndppen);
        JOptionPane.showMessageDialog(frame, "Receipt Saved Sucessfully...");
        search();

        flag=1;
        jButton1.setText("Update");

        }else{
//================= update =============================
            String ss2="",ss3="";        
//================ update reciept ======================
        lstppen.setPayorType(jComboBox1.getSelectedItem().toString());
        lstppen.setPaymentMode(jComboBox2.getSelectedItem().toString());
        if(jComboBox2.getSelectedItem().toString().compareTo("Check")==0){
            lstppen.setCheckNo(jTextField1.getText());
        }else{
              if(jComboBox2.getSelectedItem().toString().compareTo("Electronic")==0)
              {lstppen.setCheckNo(jTextField1.getText());}
        }
        lstppen.setDescription(jTextField2.getText());
        lstppen.setPaymentAmt(jTextField3.getText());
        int i=jTable1.getSelectedRow();
        lblUnpostedAmt.setText((String) jTable1.getValueAt(i,cUnpostedAmt));
        lstppen.setReceiptCode(jComboBox3.getSelectedItem().toString());
        lstppen.setReceiptNo(jTextField4.getText());
        if(slotNo.isEmpty())
        {
            slotNo=""+PaymentPostingDB.dv0;
        }
        ppdb.updateReciept(lstppen,slotNo);

//=========== update payor_reciept ===================
        PaymentPostingDB sppdb=new PaymentPostingDB();
        PaymentPostingDef sndppen =new PaymentPostingDef();
        if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0)
        {
            ss2="P";
        }else{
            if(jComboBox1.getSelectedItem().toString().compareTo("Insurance")==0){
                ss2="I";
            }else{
                if(jComboBox1.getSelectedItem().toString().compareTo("Capitation")==0){
                    ss2="C";
                }
            }
        }
        sndppen.setPayerName(ss2);

        if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0)
        {
            ss3=jLabel60.getText();
        }else{ ss3=jComboBox4.getSelectedItem().toString();
               String [] temp = null;
               temp = ss3.split("-");
               ss3=temp[0];
        }
        sndppen.setPayorId(ss3);

        if(jComboBox1.getSelectedItem().toString().compareTo("Insurance")==0)
        {
            sndppen.setPaymentCode(jComboBox5.getSelectedItem().toString());
            sndppen.setAdjCode(jComboBox6.getSelectedItem().toString());
            sndppen.setWithHoldCode(jComboBox7.getSelectedItem().toString());
            sndppen.setDeductibleCode(jComboBox8.getSelectedItem().toString());
            sndppen.setTakeBackCode(jComboBox9.getSelectedItem().toString());
        }else{
            if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0){
               sndppen.setPaymentCode(jComboBox12.getSelectedItem().toString());
               sndppen.setAdjCode(jComboBox13.getSelectedItem().toString());
               sndppen.setCoPayCode(jComboBox14.getSelectedItem().toString());
            }
        }

        sppdb.updatePayorReciept(sndppen,slotNo);

        JOptionPane.showMessageDialog(frame, "Information Updated");
        search();
        }
    }catch(Exception e){e.printStackTrace();}
    }


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        saveUpdatePosting();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void saveUpdatePosting(){
    try{
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef ppen =new PaymentPostingDef();
        String claim="";
        claim=jComboBox16.getSelectedItem().toString();
        
         int tag=0;
         double aj=0.0,bal=0.0;
         String r="",balance="",a="",adj="",takeback="";

     if(chkOldPayments.isSelected()==false)
     {
//======================== SAVE ===================
          int x=tabPostingDetails.getRowCount();
          if(Double.valueOf(lblUnpostedAmt.getText().toString()).doubleValue() >= 0.0 )
          {
          for(int i=0;i<x;i++)
          {
           if(tabPostingDetails.getValueAt(i,cPayment).toString().compareTo("")!=0)
           {
            if(!tabPostingDetails.getValueAt(i,cAdj).toString().isEmpty()){
                adj=tabPostingDetails.getValueAt(i,cAdj).toString();
            }else{
                adj=""+0.0;
            }
            if(!tabPostingDetails.getValueAt(i,cTakeBack).toString().isEmpty()){
                takeback=tabPostingDetails.getValueAt(i,cTakeBack).toString();
            }else{
                takeback=""+0.0;
            }
            
            bal=Double.valueOf(tabPostingDetails.getValueAt(i,cBalance).toString()).doubleValue()-Double.valueOf(tabPostingDetails.getValueAt(i,cPayment).toString()).doubleValue()-Double.valueOf(adj.toString()).doubleValue()-Double.valueOf(takeback.toString()).doubleValue();
            balance=""+bal;
            tabPostingDetails.setValueAt(balance, i,cBalance);
            ppen.setClaimId(claim);
            ppen.setPayorId(MMMHRN);
            ppen.setReceiptNo(slotNo);
            ppen.setCharges(tabPostingDetails.getValueAt(i,cCharges).toString());
            ppen.setBalance(tabPostingDetails.getValueAt(i,cBalance).toString());
            ppen.setPaymentCode(tabPostingDetails.getValueAt(i,cPayment).toString());
            ppen.setDeductibleCode(tabPostingDetails.getValueAt(i,cDeduct).toString());
            ppen.setWithHoldCode(tabPostingDetails.getValueAt(i,cWithh).toString());
            ppen.setAllowedCode(tabPostingDetails.getValueAt(i,cAllowed).toString());
            ppen.setAdjCode(tabPostingDetails.getValueAt(i,cAdj).toString());
            ppen.setTakeBackCode(tabPostingDetails.getValueAt(i,cTakeBack).toString());
            ppen.setProc(tabPostingDetails.getValueAt(i,cProcedure).toString());
            ppen.setProvider(tabPostingDetails.getValueAt(i,cProv).toString());
            ppdb.addPosting(ppen,lblUnpostedAmt.getText(),jTextField4.getText());
            search();
         //   jTable1.setRowSelectionInterval(rowTable1,rowTable1);
            jButton5.setEnabled(false);
            tag=1;
           }else{ System.out.println("in row : "+i+" ,Dont have any of the values"); }
        }
      }else{ tag=3; }          
   }else{
          int x=tabPostingDetails.getRowCount();
          String id[]=PaymentPostingDB.ID;
      if(Double.valueOf(lblUnpostedAmt.getText().toString()).doubleValue() >= 0.0 )
      {

          for(int i=0;i<x;i++)
          {
           if(tabPostingDetails.getValueAt(i,cPayment).toString().compareTo("")!=0)
           {
            if(!tabPostingDetails.getValueAt(i,cAdj).toString().isEmpty()){
                adj=tabPostingDetails.getValueAt(i,cAdj).toString();
            }else{
                adj=""+0.0;
            }
            if(!tabPostingDetails.getValueAt(i,cTakeBack).toString().isEmpty()){
                takeback=tabPostingDetails.getValueAt(i,cTakeBack).toString();
            }else{
                takeback=""+0.0;
            }
            bal=Double.valueOf(tabPostingDetails.getValueAt(i,cBalance).toString()).doubleValue()+Double.valueOf(FrUpdtBalance[i].toString()).doubleValue()-Double.valueOf(tabPostingDetails.getValueAt(i,cPayment).toString()).doubleValue()-Double.valueOf(adj.toString()).doubleValue()-Double.valueOf(takeback.toString()).doubleValue();
            balance=""+bal;
            tabPostingDetails.setValueAt(balance, i,cBalance);
            ppen.setid(id[i]);

            ppen.setClaimId(claim);
            ppen.setProc(tabPostingDetails.getValueAt(i,cProcedure).toString());
            ppen.setBalance(tabPostingDetails.getValueAt(i,cBalance).toString());
            ppen.setPaymentCode(tabPostingDetails.getValueAt(i,cPayment).toString());
            ppen.setDeductibleCode(tabPostingDetails.getValueAt(i,cDeduct).toString());
            ppen.setWithHoldCode(tabPostingDetails.getValueAt(i,cWithh).toString());
            ppen.setAllowedCode(tabPostingDetails.getValueAt(i,cAllowed).toString());
            ppen.setAdjCode(tabPostingDetails.getValueAt(i,cAdj).toString());
            ppen.setTakeBackCode(tabPostingDetails.getValueAt(i,cTakeBack).toString());
            ppen.setProvider(tabPostingDetails.getValueAt(i,cProv).toString());
            ppdb.editPosting(ppen,lblUnpostedAmt.getText(),jTextField4.getText());
            search();
            tag=2;
            jTable1.setRowSelectionInterval(rowTable1,rowTable1);
            }else{ System.out.println("in row : "+i+" ,Dont have any of the values"); }

        }
      }else{ tag=3;}
   } if(tag==0){
              JOptionPane.showMessageDialog(frame, "Please enter the data first");
          }else{
              if(tag==1){
              JOptionPane.showMessageDialog(frame, "Data Saved");
              }else{
                  if(tag==2){
                  JOptionPane.showMessageDialog(frame, "Data Updated");
                  }else{
                      JOptionPane.showMessageDialog(null, "Payment exceeded the Posted amount");
                      cons=0;cons1=0;cons2=0;cons3=0;cons4=0;cons5=0;
                      tabPostingDetails.changeSelection(0, cPayment, false, false);
                      tabPostingDetails.setFocusable(true);
                      tabPostingDetails.requestFocus();
                  }
              }
          }
}catch(Exception e){e.printStackTrace();}
    }


    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton9ActionPerformed

    public void search(){
    try{
        PaymentPostingDB ppdb=new PaymentPostingDB();
        ss1=ppdb.currentDate();
        
        PaymentPostingDef lstppen =new PaymentPostingDef();
        lstppen.setReceiptDate(Utiles.convertUSDatetoMysql(jCalendarCombo1.getSelectedDate().toString()));
        lstppen.setReceiptDate1(Utiles.convertUSDatetoMysql(jCalendarCombo2.getSelectedDate().toString()));
        lstppen.setPayorType(jComboBox1.getSelectedItem().toString());        
        
        jTable1.setModel(new javax.swing.table.DefaultTableModel(ppdb.searchPaymentPosting(lstppen),new String[]{"#","Rec. Date","Rec. No","Description","Payor Name","Payor Type","Payment","Unposted Amt"}
        )
                   {
                       boolean[] canEdit = new boolean [] {
                              false, false,false, false,false, false,false, false
                       };
                       public boolean isCellEditable(int rowIndex, int columnIndex) {
                               return canEdit [columnIndex];
                       }
                       }
                    );
        if(ppdb.searchPaymentPosting(lstppen)!=null){
            lblStatus.setText("Select any receipt record. \n To post payment click the 'Post' button. \n To view payment deposit click 'View Rcpt Details' button.");
        }else{
             JOptionPane.showMessageDialog(null, "No receipt available.");
            lblStatus.setText("To make receipt choose date, payor type, mode and enter other details and then press save.");
        }
        dynamicWidthofColumnforTable1();
    }catch(Exception e){e.printStackTrace();}
    }

private void clearCBPanel6(){
         jComboBox4.removeAllItems();
         jComboBox5.removeAllItems();
         jComboBox6.removeAllItems();
         jComboBox7.removeAllItems();
         jComboBox8.removeAllItems();
         jComboBox9.removeAllItems();
}

private void clearCBPanel7(){
         jComboBox12.removeAllItems();
         jComboBox13.removeAllItems();
         jComboBox14.removeAllItems();
}

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        if(jComboBox2.getSelectedItem().toString().compareTo("Check")==0)
        {
            jLabel4.setVisible(true);jLabel4.setText("Check No.");jTextField1.setVisible(true);
        }else{
            if(jComboBox2.getSelectedItem().toString().compareTo("Electronic")==0){
                jLabel4.setVisible(true);jLabel4.setText("ECFA No.");jTextField1.setVisible(true);
            }else{jLabel4.setVisible(false);jTextField1.setVisible(false);}
        }
}//GEN-LAST:event_jComboBox2ActionPerformed

    public void setGurantor(PaymentPostingDef obj){
        try{
        jLabel17.setText(obj.getGurantorname());
        jLabel42.setText(obj.getGurantorSex());
       
        jLabel53.setText(obj.getRelation());
        String dob=getDOB(obj.getGurantorDOB());
        jLabel44.setText(dob);
        
        }catch(Exception e){}
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jButton12.setVisible(true);
        flag=1;
        clearCBPanel6();
        clearCBPanel7();
        jButton1.setText("Update");
        jComboBox16.removeAllItems();
        int click=0;
        click=evt.getClickCount();
        rowTable1=jTable1.getSelectedRow();
        String p[]=PaymentPostingDB.Reciept_ID;

        SSlotNo=p[rowTable1];
        showAll(p[rowTable1]);
    }//GEN-LAST:event_jTable1MouseClicked

public void showAll(String a){
try{
        slotNo=a;
        
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();
        lstppen=ppdb.showSelectedRowData(a);
        String Payorid=lstppen.getPayorId()+"-"+jTable1.getValueAt(rowTable1,4);
        jComboBox1.setSelectedItem(lstppen.getPayorType());
        jComboBox2.setSelectedItem(lstppen.getPaymentMode());
        jTextField1.setText(lstppen.getCheckNo());
        jTextField2.setText(lstppen.getDescription());
        jTextField3.setText(lstppen.getPaymentAmt());
        jComboBox3.setSelectedItem(lstppen.getReceiptCode());
        jTextField4.setText(lstppen.getReceiptNo());
        int i=jTable1.getSelectedRow();
        lblUnpostedAmt.setText(jTable1.getValueAt(i,cUnpostedAmt).toString());
        if(lstppen.getPayorType().compareTo("Patient")==0){
            PaymentPostingDB pp1db=new PaymentPostingDB();
            PaymentPostingDef ppen =new PaymentPostingDef();
            String HRN=lstppen.getPayerName();
            ppen=pp1db.searchHRN(HRN);
            jLabel21.setText(ppen.getPatientName());
            jLabel26.setText(ppen.getPatientSex());
            jLabel60.setText(HRN);
            String dob=getDOB(ppen.getPatientDOB());
            jLabel28.setText(dob);
            jComboBox12.setSelectedItem(lstppen.getPaymentCode());
            jComboBox13.setSelectedItem(lstppen.getAdjCode());
            jComboBox14.setSelectedItem(lstppen.getCoPayCode());
            jPanel10.setVisible(false);
            jPanel11.setVisible(true);
            jPanel14.setVisible(false);
            jLabel34.setText(ppen.getPatientName());
            jLabel36.setText(HRN);
            jLabel38.setText(ppen.getPatientSex());
            jLabel40.setText(dob);
            jPanel13.setVisible(true);
            PaymentPostingDef ggppen1 =new PaymentPostingDef();
            ggppen1=pp1db.getGurantorInfo("HRN",jLabel36.getText());
            setGurantor(ggppen1);
            List<PaymentPostingDef> lstppen1 =new ArrayList<PaymentPostingDef>();
            lstppen1=pp1db.getcmshcfa1500("HRN",jLabel36.getText());

                int j=0;
                double dd=5.0;
                try{
                j=lstppen1.size();
                int k=0,l=0;
                while(j>0){
                   PaymentPostingDef ppen1=new PaymentPostingDef();
                   ppen1=lstppen1.get(k);
                    if(jCheckBox1.isSelected()==false)
                    {
                        dd=pp1db.checkPaid(ppen1.getClaimId());
                    }

                    if(dd<=0.0){
                        j--;
                        k++;
                        continue;
                    }
                   jComboBox16.insertItemAt(ppen1.getClaimId(),l++);
                   k++;
                   j--;
                }
                }catch(Exception e){
                    jComboBox16.removeAllItems();
                    tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));

                }
        }else{
            if(lstppen.getPayorType().compareTo("Insurance")==0){
              PaymentPostingDB pp1db=new PaymentPostingDB();
              jComboBox4.setSelectedItem(lstppen.getInsurance()+"-"+pp1db.inscmpName(lstppen.getInsurance()));
              jComboBox5.setSelectedItem(lstppen.getPaymentCode());
              jComboBox6.setSelectedItem(lstppen.getAdjCode());
              jComboBox7.setSelectedItem(lstppen.getWithHoldCode());
              jComboBox8.setSelectedItem(lstppen.getDeductibleCode());
              jComboBox9.setSelectedItem(lstppen.getTakeBackCode());
              jPanel10.setVisible(true);
              jPanel11.setVisible(false);
              jLabel32.setText(Payorid);
              List<PaymentPostingDef> lstppen1 =new ArrayList<PaymentPostingDef>();
              lstppen1=pp1db.getcmshcfa1500("InsuranceCompany",jLabel32.getText());
              int j=0;
              double dd=5.0;
              try{
                j=lstppen1.size();
                int k=0,l=0;
                while(j>0){
                  PaymentPostingDef ppen1=new PaymentPostingDef();
                  ppen1=lstppen1.get(k);

                    if(jCheckBox1.isSelected()==false)
                    {
                        dd=pp1db.checkPaid(ppen1.getClaimId());
                    }

                    if(dd<=0.0){
                        j--;
                        k++;
                        continue;
                    }
                  jComboBox16.insertItemAt(ppen1.getClaimId(),l++);
                  k++;
                  j--;
                }
              }catch(Exception gfx){
                  jComboBox16.removeAllItems();
                  tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));
               }
              String sLabel32="",s1="";

             if(jLabel32.getText().compareTo("jLabel32")!=0)
             {
                try{
                    sLabel32=jLabel32.getText();
                    String [] temp = null;
                    temp = sLabel32.split("-");
                    s1=temp[0];
                    lstppen=ppdb.getInsuranceCompDetails(s1);
                    if(lstppen.getAddress1()!=null){
                    jLabel30.setText(lstppen.getAddress1()+" "+lstppen.getAddress2());
                    }else{
                        jLabel30.setText(lstppen.getAddress2());
                    }
                    jLabel50.setText(lstppen.getCity());
                    jLabel47.setText(lstppen.getState());
                    jLabel51.setText(lstppen.getZip());
                    jLabel49.setText(lstppen.getPhone());
                }catch(Exception e){}
             }
           }else{
                if(lstppen.getPayorType().compareTo("Capitation")==0){
                    PaymentPostingDB pp1db=new PaymentPostingDB();
                    jComboBox4.setSelectedItem(lstppen.getInsurance()+"-"+pp1db.inscmpName(lstppen.getInsurance()));

                    jPanel10.setVisible(true);
                    jPanel11.setVisible(false);
                    jLabel32.setText(Payorid);
                   List<PaymentPostingDef> lstppen1 =new ArrayList<PaymentPostingDef>();
                   lstppen1=pp1db.getcmshcfa1500("InsuranceCompany",jLabel32.getText());
                   int j1=0;
                   double dd=5.0;
                    try{
                        j1=lstppen1.size();
                        int k=0,l=0;
                        while(j1>0){
                        PaymentPostingDef ppen1=new PaymentPostingDef();
                        ppen1=lstppen1.get(k);

                        if(jCheckBox1.isSelected()==false)
                        {
                            dd=pp1db.checkPaid(ppen1.getClaimId());
                        }

                        if(dd<=0.0){
                           j1--;
                           k++;
                            continue;
                        }

                        jComboBox16.insertItemAt(ppen1.getClaimId(),l++);
                        k++;
                        j1--;
                        }
                    }catch(Exception gfx){
                        jComboBox16.removeAllItems();
                        tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));
                    }
                    String sLabel32="",s1="";

                    if(jLabel32.getText().compareTo("jLabel32")!=0)
                    {
                        try{
                            sLabel32=jLabel32.getText();
                            String [] temp = null;
                            temp = sLabel32.split("-");
                            s1=temp[0];
                            lstppen=ppdb.getInsuranceCompDetails(s1);
                            jLabel30.setText(lstppen.getAddress1()+" "+lstppen.getAddress2());
                            jLabel50.setText(lstppen.getCity());
                            jLabel51.setText(lstppen.getZip());
                            jLabel49.setText(lstppen.getPhone());
                        }catch(Exception e){}
                    }
                }
            }
        }
}catch(Exception e){e.printStackTrace();}
}

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void searchPatient(String a, String b) {
        int record_count=0;
        PatientList ob = new PatientList();
        record_count=ob.fillup(a,b);

        if(record_count>0){
            this.dispose();
        }
        ob.whichForm = ob.sPayment;
    }

    private void jComboBox16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox16ActionPerformed
        // TODO add your handling code here:
          showClaimDetails();
          for(int rowt2i=0;rowt2i<tabPostingDetails.getRowCount();rowt2i++){
          FrUpdtBalance[rowt2i]=(String) tabPostingDetails.getValueAt(rowt2i,cPayment);
          }          
    }//GEN-LAST:event_jComboBox16ActionPerformed

    private void selectCombo(){
        ResetPosting();
        jButton5.setEnabled(true);
        jPanel13.setVisible(true);
        
        if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0)
            jPanel14.setVisible(false);
        else
            jPanel14.setVisible(true);
        String claimid="";
        if(chkOldPayments.isSelected()==false){
            jButton5.setText("Save");
            jButton7.setVisible(false);
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();
        try{
        claimid=jComboBox16.getSelectedItem().toString();
        lstppen.setClaimId(claimid);
        lstppen.setReceiptId(slotNo);
        int rowt1i=0;
        rowt1i=jTable1.getSelectedRow();
        lstppen.setReceiptDate(jTable1.getValueAt(rowt1i,1).toString());
        lblUnpostedAmt.setText(jTable1.getValueAt(rowt1i,cUnpostedAmt).toString());
        
            tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(ppdb.getCmsCpt(lstppen,0),new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));
            dynamicWidthofColumnforTable3();
        lstppen=ppdb.getHRNcmshcfa1500(claimid);
        MMMHRN=lstppen.getPayorId();
        jLabel62.setText(MMMHRN);
        jLabel55.setText(lstppen.getPatientName());
        jLabel57.setText(lstppen.getPatientSex());
        String dob=getDOB(lstppen.getPatientDOB());
        jLabel59.setText(dob);
        lstppen=ppdb.getGurantorInfo("HRN",MMMHRN);
        setGurantor(lstppen);
        setTotalData();
        setUnposted();

        }catch(Exception e){}
     }else{
            jButton5.setText("Update");
            jButton7.setVisible(true);

            PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();
        try{
        claimid=jComboBox16.getSelectedItem().toString();
        lstppen.setClaimId(claimid);
        lstppen.setReceiptId(slotNo);
        int rowt1i=0;
        rowt1i=jTable1.getSelectedRow();
        lstppen.setReceiptDate(jTable1.getValueAt(rowt1i,1).toString());
        lblUnpostedAmt.setText(jTable1.getValueAt(rowt1i,cUnpostedAmt).toString());
        tabPostingDetails.setModel(new javax.swing.table.DefaultTableModel(ppdb.getCmsCpt2(lstppen,0),new String[]{"#","Serv. Date","Procedure","Charges","Balance","Payment","Deduct.","Withh","Allowed","Adj","TakeBack","Complete","Rejection","Rcpt. Date"}));
        dynamicWidthofColumnforTable3();
        lstppen=ppdb.getHRNcmshcfa1500(claimid);
        MMMHRN=lstppen.getPayorId();
        jLabel62.setText(MMMHRN);
        jLabel55.setText(lstppen.getPatientName());
        jLabel57.setText(lstppen.getPatientSex());
        String dob=getDOB(lstppen.getPatientDOB());
        jLabel59.setText(dob);
        lstppen=ppdb.getGurantorInfo("HRN",MMMHRN);
        setGurantor(lstppen);
        setTotalData();
        setUnposted();

        }catch(Exception e){}
      }
    }
    private void tabPostingDetailsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPostingDetailsKeyPressed
       // TODO add your handling code here:
       int i=0,j=0;
       
       String r="",charges="",balance="",a="",allowed="",adj="",adjust="",takeback="";
       
       i=tabPostingDetails.getSelectedRow();
       j=tabPostingDetails.getSelectedColumn();

       if(curRowTable2==i){}else{
            cons=0;cons1=0;cons2=0;cons3=0;cons4=0;cons5=0;
            curRowTable2=i;
       }
       if(evt.getKeyCode()==10){
          evt.setKeyCode(9);
       }
       if( i == (tabPostingDetails.getRowCount()-1) && j == cProv )
           jButton5.requestFocus();       
       try{
           cptNo=tabPostingDetails.getValueAt(i,cProcedure).toString();
            
       }catch(Exception e){
           cptNo="";           
       }

       if(j==cPayment){
           if(cons==0){
                cons=1;
                evt.setKeyCode(evt.VK_F2);
                
             }
       }else if(j==cDeduct){
                  if(cons1==0){
                     cons1=1;
                     evt.setKeyCode(evt.VK_F2);
                 }
             }else if(j==cWithh){
                        if(cons2==0){
                            cons2=1;
                            evt.setKeyCode(evt.VK_F2);
                        }
                    }else if(j==cAllowed){
                            if(cons3==0){
                                cons3=1;
                                evt.setKeyCode(evt.VK_F2);
                            }
                        }else if(j==cAdj){
                            if(cons4==0){
                                cons4=1;
                                evt.setKeyCode(evt.VK_F2);
                            }
                        }else if(j==cTakeBack){
                            if(cons5==0){
                                cons5=1;
                                evt.setKeyCode(evt.VK_F2);
                            }
                        }

      String pa="",amt="";
      payment=tabPostingDetails.getValueAt(i,cPayment);
       
      if(!payment.toString().isEmpty() && (evt.getKeyCode()==evt.VK_TAB
               || evt.getKeyCode()==evt.VK_LEFT
               || evt.getKeyCode()==evt.VK_RIGHT
               || evt.getKeyCode()==evt.VK_UP
               || evt.getKeyCode()==evt.VK_DOWN
               || evt.getKeyCode()==evt.VK_ENTER ) )
       {
         
         if(j>=cPayment && j<=cTakeBack){
             payment=tabPostingDetails.getValueAt(i,cPayment);
            charges=tabPostingDetails.getValueAt(i,cCharges).toString();
            balance=tabPostingDetails.getValueAt(i,cBalance).toString();
            allowed=tabPostingDetails.getValueAt(i,cAllowed).toString();
            
            if(!tabPostingDetails.getValueAt(i,cAdj).toString().isEmpty()){
                adj=tabPostingDetails.getValueAt(i,cAdj).toString();
            }else{
                adj=""+0.0;
            }
            if(!tabPostingDetails.getValueAt(i,cTakeBack).toString().isEmpty()){
                takeback=tabPostingDetails.getValueAt(i,cTakeBack).toString();
            }else{
                takeback=""+0.0;
            }

            double aj=0.0,bal=0.0;
            setTotalData();
            setUnposted();
            focuslost();             
       }
   }
}//GEN-LAST:event_tabPostingDetailsKeyPressed

     private void setTotalData(){
       String r="",charges="",balance="",payment="",a="",allowed="",adj="",adjust="",takeback="";
       int x=0;
       if(flagDontShowTotal!=1){
       x=tabPostingDetails.getRowCount();
       double Payment=0.0,Charges=0.0,Balance=0.0,Allowed=0.0,Adjust=0.0,TakeBack=0.0;
       for(int q=0;q<x;q++)
       {
             double Payment1=0.0,Charges1=0.0,Balance1=0.0,Allowed1=0.0,Adjust1=0.0,TakeBack1=0.0;
             payment=tabPostingDetails.getValueAt(q,cPayment).toString();
             charges=tabPostingDetails.getValueAt(q,cCharges).toString();
             balance=tabPostingDetails.getValueAt(q,cBalance).toString();
             allowed=tabPostingDetails.getValueAt(q,cAllowed).toString();
             adjust=tabPostingDetails.getValueAt(q,cAdj).toString();
             takeback=tabPostingDetails.getValueAt(q,cTakeBack).toString();

             if(payment.compareTo("")!=0){
                Payment1=Double.valueOf(tabPostingDetails.getValueAt(q,cPayment).toString()).doubleValue();
             }
             if(charges.compareTo("")!=0){
                Charges1=Double.valueOf(tabPostingDetails.getValueAt(q,cCharges).toString()).doubleValue();
             }
             if(balance.compareTo("")!=0){
                Balance1=Double.valueOf(tabPostingDetails.getValueAt(q,cBalance).toString()).doubleValue();
             }
             if(allowed.compareTo("")!=0){
                Allowed1=Double.valueOf(tabPostingDetails.getValueAt(q,cAllowed).toString()).doubleValue();
             }
             if(adjust.compareTo("")!=0){
                Adjust1=Double.valueOf(tabPostingDetails.getValueAt(q,cAdj).toString()).doubleValue();
             }
             if(takeback.compareTo("")!=0){
                TakeBack1=Double.valueOf(tabPostingDetails.getValueAt(q,cTakeBack).toString()).doubleValue();
             }
             Payment+=Payment1;
             Charges+=Charges1;
             Balance+=Balance1;
             Allowed+=Allowed1;
             Adjust+=Adjust1;
             TakeBack+=TakeBack1;
       }
       setPayment=Payment;
       tabPostTotal.setValueAt(Payment, 0, cPayment);
       tabPostTotal.setValueAt(Charges, 0, cCharges);
       tabPostTotal.setValueAt(Balance, 0, cBalance);
       tabPostTotal.setValueAt(Allowed, 0, cAllowed);
       tabPostTotal.setValueAt(Adjust, 0, cAdj);
       tabPostTotal.setValueAt(TakeBack, 0, cTakeBack);
      }
     }

     private void setUnposted(){
       String unpamt="";
       double Unpamt=0.0;
       int i=0,j=0;
       Double suminitPayment=0.0;
       i=jTable1.getSelectedRow();
       unpamt=(String) jTable1.getValueAt(i,cUnpostedAmt);
       j=tabPostingDetails.getSelectedRow();
       if(chkOldPayments.isSelected()==false){
            Unpamt=Double.valueOf(unpamt).doubleValue()-setPayment;
       }else{
                for(int k=0;k<tabPostingDetails.getRowCount();k++){
                    suminitPayment+=Double.valueOf(FrUpdtBalance[k]).doubleValue();
                }
                Unpamt=Double.valueOf(unpamt).doubleValue()+suminitPayment-setPayment;               
       }
       unpamt=""+Unpamt;
       lblUnpostedAmt.setText(setAmount(unpamt));
     }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    try{
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();
        int chk=1;
             chk=JOptionPane.showConfirmDialog(frame,"Do U want to delete the Record");
             if(chk==0){
                    ppdb.deleteSelectedData(slotNo);
                    reset();
                    search();
             }
        
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
      
}//GEN-LAST:event_jTextField4FocusLost

     private String setttinggg(String a){

        String rn="",rd="";
        if(a.length()==1){
            rn="000"+a;
        }else{
            if(a.length()==2){rn="00"+a;}
            else{
                if(a.length()==3){rn="0"+a;}
                else{rn=""+a;}
            }
        }
        rd=setReceiptDate(Utiles.convertMysqlDatetoUSFormat(ss1))+rn;

        return rd;
   }

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        String pa="",amt="";
        pa=jTextField3.getText();
        amt=setAmount(pa);
        jTextField3.setText(amt);        
    }//GEN-LAST:event_jTextField3FocusLost

    private void showClaimDetails(){
        selectCombo();
        tabPostingDetails.changeSelection(0, cPayment, false, false);
        tabPostingDetails.setFocusable(true);
        tabPostingDetails.requestFocus();
        lblStsPayment.setText("Use only 'Enter key' after entering any of the posting details");
    }
    private void jComboBox16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox16MouseClicked
        // TODO add your handling code here:
              showClaimDetails();
    }//GEN-LAST:event_jComboBox16MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // TODO add your handling code here:
            deletePosting();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void deletePosting() throws SQLException, SQLException{
        String a="";
        try{
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();

        int i=tabPostingDetails.getSelectedRow();
        String p[]=PaymentPostingDB.ID;

        if(tabPostingDetails.getRowCount()==0){
        }else{
             int chk=1;
             chk=JOptionPane.showConfirmDialog(frame,"Do U want to delete the Record");
             if(chk==0){
                 a=ppdb.deletePosting(cptNo,p[i]);
                  selectCombo();
             }
        }
        }catch(Exception e){e.printStackTrace();}
       String unpamt="";
       int i1=0;
       i1=rowTable1;
       unpamt=(String) jTable1.getValueAt(i1,cUnpostedAmt);
       double Unpamt=Double.valueOf(unpamt).doubleValue()+Double.valueOf(a).doubleValue();
       unpamt=""+Unpamt;
       lblUnpostedAmt.setText(setAmount(unpamt));
       try{
       PaymentPostingDB ppdb=new PaymentPostingDB();
       ppdb.updatingReceiptAfPosting(setAmount(unpamt),jTextField4.getText());
       }catch(Exception  e){}
       search();
       jTable1.setRowSelectionInterval(rowTable1,rowTable1);       
    }

    private void tabPostingDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPostingDetailsMouseClicked
        // TODO add your handling code here:        
}//GEN-LAST:event_tabPostingDetailsMouseClicked

    private void tabPostingDetailsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabPostingDetailsFocusLost
        // TODO add your handling code here:
        //focuslost();
}//GEN-LAST:event_tabPostingDetailsFocusLost

    private void focuslost(){
        String pa="",amt="";
        int i=0,j=0,i1=0,j1=0,x,y;
        i=tabPostingDetails.getRowCount();
        j=tabPostingDetails.getColumnCount();
        for(x=0;x<i;x++){
            for(y=cCharges;y<j-1;y++){
                if(tabPostingDetails.getValueAt(x,y).toString().compareTo("")!=0){
                pa=""+tabPostingDetails.getValueAt(x,y);
                amt=setAmount(pa);
                tabPostingDetails.setValueAt(amt,x,y);
                }
            }
        }

        i1=tabPostTotal.getRowCount();
        j1=tabPostTotal.getColumnCount();
        for(x=0;x<i1;x++){
            for(y=cCharges;y<j1-1;y++){
                if(tabPostTotal.getValueAt(x,y).toString().compareTo("")!=0){
                pa=""+tabPostTotal.getValueAt(x,y);
                amt=setAmount(pa);
                tabPostTotal.setValueAt(amt,x,y);
                }
            }
        }

    }
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        flag=1;
        clearCBPanel6();
        clearCBPanel7();
        jButton1.setText("Update");
        jComboBox16.removeAllItems();
        showAll(SSlotNo);
}//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        recSearchPatient();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void recSearchPatient(){
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef ppen =new PaymentPostingDef();
        String valuelike="",scrit="";
        pDay1=jCalendarCombo1.getSelectedDay();
        pMonth1=jCalendarCombo1.getSelectedMonth();
        pYear1=jCalendarCombo1.getSelectedYear();

        pDay2=jCalendarCombo2.getSelectedDay();
        pMonth2=jCalendarCombo2.getSelectedMonth();
        pYear2=jCalendarCombo2.getSelectedYear();

        pPaymode=(String) jComboBox2.getSelectedItem();
        if(pPaymode.compareTo("Check")==0 || pPaymode.compareTo("Electronic")==0)
        {
            pChelno = jTextField2.getText();
        }
        pDesc=jTextField2.getText();
        pPayAmt=jTextField3.getText();
        pRecCode=(String) jComboBox3.getSelectedItem();
        SearchCriteria sp=new SearchCriteria();
        PatientList.whichForm = PatientList.sPayment;
        this.dispose();
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        paySearchPatient();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void paySearchPatient(){
        
        String valuelike="",scrit="",inscmp="";
        inscmp=jLabel32.getText();
        pDay1=jCalendarCombo1.getSelectedDay();
        pMonth1=jCalendarCombo1.getSelectedMonth();
        pYear1=jCalendarCombo1.getSelectedYear();

        pDay2=jCalendarCombo2.getSelectedDay();
        pMonth2=jCalendarCombo2.getSelectedMonth();
        pYear2=jCalendarCombo2.getSelectedYear();

        SearchCriteria sp=new SearchCriteria();
        sp.InsCompName=jLabel32.getText();
        sp.flag=1;
        PatientList.whichForm = PatientList.sPaymentPosting;
        this.dispose();
    }

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            recSearchPatient();
        }
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 ){
            paySearchPatient();
        }
    }//GEN-LAST:event_jButton11KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        
}//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox14KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 || evt.getKeyCode()==9){
          jButton1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox14KeyPressed

    private void jComboBox9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox9KeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==10 || evt.getKeyCode()==9){
          jButton1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox9KeyPressed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 || evt.getKeyCode()==9){
            if(jComboBox1.getSelectedItem().toString().compareTo("Capitation")==0)
                jButton1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox4KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        int recRow,recCol=0;
        recRow=jTable1.getSelectedRow();
        recCol=jTable1.getSelectedColumn();
        if(recRow < jTable1.getRowCount() && recCol==cUnpostedAmt)
            jCalendarCombo1.requestFocus();
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(null, evt.getKeyCode());
        if(evt.getKeyCode()==9){
          jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButton9StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9StateChanged

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 || evt.getKeyCode()==9)
        {
          if(jComboBox1.getSelectedItem().toString().compareTo("Patient")==0)
                jButton10.requestFocus();
          else
                jComboBox4.requestFocus();
        }
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 ){
        saveUpdate();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){reset();  }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
    try{
        if(evt.getKeyCode()==10 || evt.getKeyCode()==9){
        PaymentPostingDB ppdb=new PaymentPostingDB();
        PaymentPostingDef lstppen =new PaymentPostingDef();
        int chk=1;
             chk=JOptionPane.showConfirmDialog(frame,"Do U want to delete the Record");
             if(chk==0){
                    ppdb.deleteSelectedData(slotNo);
                    reset();
                    search();
             }
        
        }
     }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 ){
        this.dispose();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            saveUpdatePosting();
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            ResetPosting();
            jLabel60.setText("");
        }
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        try {
            // TODO add your handling code here:
            deletePosting();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            this.dispose();
        }
    }//GEN-LAST:event_jButton8KeyPressed

    private void tabPostingDetailsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabPostingDetailsFocusGained
        // TODO add your handling code here:
}//GEN-LAST:event_tabPostingDetailsFocusGained

    private void jComboBox16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox16KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
          showClaimDetails();
        }
    }//GEN-LAST:event_jComboBox16KeyPressed

    private void chkOldPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOldPaymentsActionPerformed
        // TODO add your handling code here:
       jComboBox16.removeAllItems();
       showAll(SSlotNo);
       flagDontShowTotal=1;
}//GEN-LAST:event_chkOldPaymentsActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
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
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jComboBox3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3FocusLost

    private void tabPostingDetailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPostingDetailsKeyReleased
        // TODO add your handling code here:
        //evt.consume();
}//GEN-LAST:event_tabPostingDetailsKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        viewReceipt();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==9 || evt.getKeyCode()==10)
            viewReceipt();
    }//GEN-LAST:event_jButton12KeyPressed

    private void viewReceipt(){
    try{
        if(MDI.paymentDepList==null){
            MDI.paymentDepList=new PaymentDepositList();
            MDI.paymentDepList.fillup(slotNo,jComboBox2.getSelectedItem().toString());
        }
    }catch(Exception e){e.printStackTrace();}
    }

    private void ReceiptToPosting(){
        jTabbedPane1.setSelectedIndex(1);
    }

    private void tabPostingDetailsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPostingDetailsKeyTyped
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
}//GEN-LAST:event_tabPostingDetailsKeyTyped

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        ReceiptToPosting();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==9 || evt.getKeyCode()==10)
            ReceiptToPosting();
    }//GEN-LAST:event_jButton13KeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    try{
        if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
            MDI.paymentPosting=null;
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed

    

    private void searchPatient1(String a, String b, String c) {
        int record_count=0;
        PatientList ob = new PatientList();
        if(record_count>0){ this.dispose();    }
    }


 public Double setBalance(String charges,String payment,String adj){
       double Balance=0.0,Payment=0.0,Charges=0.0,Adjust=0.0;
       String [] temp = null;
       Charges=Double.valueOf(charges).doubleValue();
       if(payment.compareTo("")!=0){
       Payment=Double.valueOf(payment).doubleValue();
       }
       if(adj.compareTo("")!=0){
       Adjust=Double.valueOf(adj).doubleValue();
       }
       Balance=Charges-Payment-Adjust;

       return Balance;
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentPosting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox chkOldPayments;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton10;
    public javax.swing.JButton jButton11;
    public javax.swing.JButton jButton12;
    public javax.swing.JButton jButton13;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    public javax.swing.JButton jButton9;
    public org.gui.JCalendarCombo jCalendarCombo1;
    public org.gui.JCalendarCombo jCalendarCombo2;
    public javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JComboBox jComboBox1;
    public javax.swing.JComboBox jComboBox12;
    public javax.swing.JComboBox jComboBox13;
    public javax.swing.JComboBox jComboBox14;
    public javax.swing.JComboBox jComboBox16;
    public javax.swing.JComboBox jComboBox2;
    public javax.swing.JComboBox jComboBox3;
    public javax.swing.JComboBox jComboBox4;
    public javax.swing.JComboBox jComboBox5;
    public javax.swing.JComboBox jComboBox6;
    public javax.swing.JComboBox jComboBox7;
    public javax.swing.JComboBox jComboBox8;
    public javax.swing.JComboBox jComboBox9;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
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
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    public javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel38;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel40;
    public javax.swing.JLabel jLabel41;
    public javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel43;
    public javax.swing.JLabel jLabel44;
    public javax.swing.JLabel jLabel45;
    public javax.swing.JLabel jLabel46;
    public javax.swing.JLabel jLabel47;
    public javax.swing.JLabel jLabel48;
    public javax.swing.JLabel jLabel49;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel50;
    public javax.swing.JLabel jLabel51;
    public javax.swing.JLabel jLabel52;
    public javax.swing.JLabel jLabel53;
    public javax.swing.JLabel jLabel54;
    public javax.swing.JLabel jLabel55;
    public javax.swing.JLabel jLabel56;
    public javax.swing.JLabel jLabel57;
    public javax.swing.JLabel jLabel58;
    public javax.swing.JLabel jLabel59;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel60;
    public javax.swing.JLabel jLabel61;
    public javax.swing.JLabel jLabel62;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JLayeredPane jLayeredPane2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    public javax.swing.JPanel jPanel11;
    public javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel13;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel15;
    public javax.swing.JPanel jPanel16;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JLabel lblStatus;
    public javax.swing.JLabel lblStsPayment;
    public javax.swing.JLabel lblUnpostedAmt;
    public javax.swing.JTable tabPostTotal;
    public javax.swing.JTable tabPostingDetails;
    // End of variables declaration//GEN-END:variables

}
