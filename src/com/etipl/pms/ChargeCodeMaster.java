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

import com.etipl.pms.datalayer.ChargecodeMethods;
import com.etipl.pms.entity.ChargeCode;
import com.etipl.pms.entity.CodeDes;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import org.jdesktop.swingx.autocomplete.Configurator;


/**
 *
 * @author MUTHU
 */
public class ChargeCodeMaster extends javax.swing.JFrame {
JFrame frame=new JFrame();
private static String[] cptCombo=null;


    /** Creates new form ChargeCodeMaster */
    public ChargeCodeMaster() {
        initComponents();
        setVisible(true);
        
        Configurator.enableAutoCompletion(this.cmbCPTCode);
        Configurator.enableAutoCompletion(this.cmbCategory);
        Configurator.enableAutoCompletion(this.cmbMod);
        Configurator.enableAutoCompletion(this.cmbPOS);
        Configurator.enableAutoCompletion(this.cmbTOS);

        screenDisplay();       
        loadDefaultData();
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

    private void loadDefaultData(){
        loadCPT();
        loadCategory();
        loadModifier();
        loadTOS();
        loadPOS();
    }

    
    private void loadCategory(){
    try{
        ChargecodeMethods ccm=new ChargecodeMethods();
        String cptCode="";
        List<CodeDes> lstcd=new ArrayList<CodeDes>();
        lstcd=ccm.loadCategory(cptCode);
        for(int i=0;i<lstcd.size();i++){
           CodeDes cd=new CodeDes();
           cd=lstcd.get(i);
           this.cmbCategory.addItem(cd.getCode());
        }    
        if(lstcd.size()>0){
            this.cmbCategory.setSelectedIndex(-1);
        }
    }catch(Exception e){e.printStackTrace();}
    }
    private void loadModifier(){
    try{
        ChargecodeMethods ccm=new ChargecodeMethods();
        String cptCode="";
        List<CodeDes> lstcd=new ArrayList<CodeDes>();
        lstcd=ccm.loadModifier(cptCode);
        for(int i=0;i<lstcd.size();i++){
           CodeDes cd=new CodeDes();
           cd=lstcd.get(i);
           this.cmbMod.addItem(cd.getCode());
        }
        if(lstcd.size()>0){
            this.cmbMod.setSelectedIndex(-1);
        }
    }catch(Exception e){e.printStackTrace();}
    }
    private void loadTOS(){
    try{
        ChargecodeMethods ccm=new ChargecodeMethods();
        String cptCode="";
        List<CodeDes> lstcd=new ArrayList<CodeDes>();
        lstcd=ccm.loadTOS(cptCode);
        for(int i=0;i<lstcd.size();i++){
           CodeDes cd=new CodeDes();
           cd=lstcd.get(i);
           this.cmbTOS.addItem(cd.getCode());
        }
        if(lstcd.size()>0){
            this.cmbTOS.setSelectedIndex(-1);
        }
}catch(Exception e){e.printStackTrace();}
    }
    private void loadPOS(){
    try{
        ChargecodeMethods ccm=new ChargecodeMethods();
        String cptCode="";
        List<CodeDes> lstcd=new ArrayList<CodeDes>();
        lstcd=ccm.loadPOS(cptCode);
        for(int i=0;i<lstcd.size();i++){
           CodeDes cd=new CodeDes();
           cd=lstcd.get(i);
           this.cmbPOS.addItem(cd.getCode());
        }
        if(lstcd.size()>0){
            this.cmbPOS.setSelectedIndex(-1);
        }
       }catch(Exception e){e.printStackTrace();}
    }
    private void loadCPT(){
    try{
        ChargecodeMethods ccm=new ChargecodeMethods();
        List<CodeDes> lstcd=new ArrayList<CodeDes>();
        if(cmbCategory.getSelectedIndex()!=-1){
            lstcd=ccm.loadCPT(cmbCategory.getSelectedItem().toString());
            cmbCPTCode.removeAllItems();
            cmbCPTCode.addItem(" ");
            for(int i=0;i<lstcd.size();i++){
               CodeDes cd=new CodeDes();
               cd=lstcd.get(i);               
               cmbCPTCode.addItem(cd.getCode());
            }
            if(lstcd.size()>0){
                this.cmbCPTCode.setSelectedIndex(-1);
            }
        }
     }catch(Exception e){e.printStackTrace();}
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        txtChargeCode = new javax.swing.JTextField();
        txtChargeDes = new javax.swing.JTextField();
        cmbMod = new javax.swing.JComboBox();
        cmbCategory = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtComments = new javax.swing.JTextField();
        cmbCPTCode = new javax.swing.JComboBox();
        jLabel104 = new javax.swing.JLabel();
        cmbPOS = new javax.swing.JComboBox();
        cmbTOS = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabCharge = new javax.swing.JTable();
        btSave = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Charge Code");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chargecode "));

        jLabel85.setText("Description");

        jLabel91.setText("CPT Code");

        jLabel92.setText("Category");

        jLabel93.setText("Code");

        jLabel97.setText("Price ($)");

        jLabel101.setText("Modifier");

        jLabel103.setText("Default TOS");

        cmbMod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });

        jLabel1.setText("Comments");

        cmbCPTCode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jLabel104.setText("Default POS");

        cmbPOS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        cmbTOS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPriceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel93)
                            .addComponent(jLabel103)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(54, 54, 54)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCPTCode, 0, 358, Short.MAX_VALUE)
                    .addComponent(cmbTOS, 0, 358, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtComments, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChargeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel85)
                        .addGap(24, 24, 24)
                        .addComponent(txtChargeDes, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104)
                    .addComponent(jLabel101)
                    .addComponent(jLabel92))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCategory, 0, 265, Short.MAX_VALUE)
                    .addComponent(cmbPOS, 0, 265, Short.MAX_VALUE)
                    .addComponent(cmbMod, 0, 265, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel93)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtChargeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(cmbCPTCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101)
                            .addComponent(cmbMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel103)
                            .addComponent(cmbTOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104)
                            .addComponent(cmbPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel85)
                        .addComponent(txtChargeDes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel92)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97)
                    .addComponent(jLabel12)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chargecode Details"));

        tabCharge.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chargecode", "Description", "Category", "CPT Code", "Modifier", "TOS", "POS", "Comments", "Price"
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
        tabCharge.setNextFocusableComponent(txtChargeCode);
        tabCharge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabChargeMouseClicked(evt);
            }
        });
        tabCharge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabChargeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabCharge);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        btSave.setMnemonic('a');
        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        btSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btSaveKeyPressed(evt);
            }
        });

        jButton1.setMnemonic('d');
        jButton1.setText("Delete");
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

        jButton6.setMnemonic('r');
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

        jButton2.setMnemonic('e');
        jButton2.setText("Search");
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

        jButton3.setMnemonic('c');
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btSave)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jButton6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3)
                            .addGap(192, 192, 192)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton1)
                    .addComponent(btSave)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void showData(int rowCount,int rowSel){
             txtChargeCode.setEditable(false);
    try{
        String comboSel = null;
        ChargecodeMethods ccm=new ChargecodeMethods();
        List<ChargeCode> lstcc=new ArrayList<ChargeCode>();
        ChargeCode code=new ChargeCode();

        if(rowCount>0){
            if(tabCharge.getValueAt(rowSel, 0)!=null){
               code.setChargeCode(tabCharge.getValueAt(rowSel, 0).toString());
            }
        }
        lstcc=ccm.loadChargesCode(code);

        if(lstcc!=null){

            for(int i=0;i<lstcc.size();i++){
               ChargeCode cc=new ChargeCode();
               cc=lstcc.get(i);
               this.txtChargeCode.setText(cc.getChargeCode());
               this.txtChargeDes.setText(cc.getChargeCodeDes());

               if(cc.getCategory()!=null){
                       this.cmbCategory.setSelectedItem(cc.getCategory().toString());
               }else{this.cmbCategory.setSelectedIndex(-1); }

               if(cc.getCPTCode()!=null){
                   comboSel=checkCode(cc.getCPTCode().toString(),this.cmbCPTCode);
                   if(comboSel!=null){
                       this.cmbCPTCode.setSelectedItem(comboSel);
                   }
               }else {this.cmbCPTCode.setSelectedIndex(-1); }

               if(cc.getModifier()!=null){
                   comboSel=checkCode(cc.getModifier().toString(),this.cmbMod);
                   if(comboSel!=null){
                       this.cmbMod.setSelectedItem(comboSel);
                   }
               }else{ this.cmbMod.setSelectedIndex(-1);  }

               if(cc.getDefaultTOS()!=null){
                   comboSel=checkCode(cc.getDefaultTOS().toString(),this.cmbTOS);
                   if(comboSel!=null){
                       this.cmbTOS.setSelectedItem(comboSel);
                   }
               }else{ this.cmbTOS.setSelectedIndex(-1);  }

               if(cc.getDefaultPOS()!=null){
                   comboSel=checkCode(cc.getDefaultPOS().toString(),this.cmbPOS);
                   if(comboSel!=null){
                       this.cmbPOS.setSelectedItem(comboSel);
                   }
               }else{ this.cmbPOS.setSelectedIndex(-1); }

               this.txtComments.setText(cc.getComments());
               this.txtPrice.setText(cc.getPrice());
           }

        }else{
             clearAll();
        }
        btSave.setText("Update");

    }catch(Exception e){e.printStackTrace();} 
    }
    private void tabChargeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabChargeMouseClicked
        // TODO add your handling code here:
       int rowCount=tabCharge.getRowCount();
       int curRow=tabCharge.getSelectedRow();
       showData(rowCount,curRow);
}//GEN-LAST:event_tabChargeMouseClicked
    private String checkCode(String code,javax.swing.JComboBox combo){
    try{
        String[] splitCode;
        String codeName = null;
        String codeCombo=null;
        
        if(combo.getItemCount()!=0){
            int items=combo.getItemCount();
            for(int i=1;i<items;i++){
                splitCode=combo.getItemAt(i).toString().split("--");
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

    private ChargeCode settingChargeCodeObject(){
    try{
         ChargeCode code=new ChargeCode();
          String[] splitCode=null;

                code.setChargeCode(txtChargeCode.getText().toString());
                code.setChargeCodeDes(txtChargeDes.getText().toString());

                if(cmbCPTCode.getSelectedItem()!=null){
                    splitCode=cmbCPTCode.getSelectedItem().toString().split("--");
                    if(splitCode.length>1){
                        code.setCPTCode(splitCode[0]);
                    }
                }else{
                    cmbCPTCode.setSelectedIndex(-1);
                }

                if(cmbCategory.getSelectedItem()!=null){
                    code.setCategory(cmbCategory.getSelectedItem().toString());
                }else{
                    cmbCategory.setSelectedIndex(-1);
                }

                if(cmbTOS.getSelectedItem()!=null){
                    splitCode=cmbTOS.getSelectedItem().toString().split("--");
                    if(splitCode.length>1){
                        code.setDefaultTOS(splitCode[0]);
                    }
                }else{
                    cmbTOS.setSelectedIndex(-1);
                }
                 if(cmbMod.getSelectedItem()!=null){
                    splitCode=cmbMod.getSelectedItem().toString().split("--");
                    if(splitCode.length>1){
                        code.setModifier(splitCode[0]);
                    }
                }
                if(cmbPOS.getSelectedItem()!=null){
                    splitCode=cmbPOS.getSelectedItem().toString().split("--");
                    if(splitCode.length>1){
                        code.setDefaultPOS(splitCode[0]);
                    }
                }else{
                    cmbPOS.setSelectedIndex(-1);
                }
                code.setComments(txtComments.getText().toString());
                code.setPrice(txtPrice.getText().toString());

                return code;                
    }catch(Exception e){ e.printStackTrace(); return null; }
    }

    private void saveUpdate(){
         try{

            int res=0,chk=0;
           
            if(txtChargeCode.getText().isEmpty() || txtChargeCode.getText().equalsIgnoreCase(" ")){
                JOptionPane.showMessageDialog(frame,"ChargeCode should not be left empty");
                this.txtChargeCode.requestFocus();
                return;
            }
            if(cmbCPTCode.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(frame,"CPT should not be left empty");
                this.cmbCPTCode.requestFocus();
                return;
            }
            if(txtPrice.getText().isEmpty() || txtPrice.getText().equalsIgnoreCase("")){
                 JOptionPane.showMessageDialog(frame,"Price should not be left empty ");
                 this.txtPrice.requestFocus();
                 return;
            }               
            ChargecodeMethods ccm=new ChargecodeMethods();
                
                    if(!btSave.getText().equalsIgnoreCase("Update")){
                         chk=ccm.checkDuplicateCode(txtChargeCode.getText().toString());
                         if(chk==0){
                            res=ccm.saveChargecodes(settingChargeCodeObject());
                            if(res==1){
                                
                                JOptionPane.showMessageDialog(frame,"Chargecode Saved Successfully ... ");
                                clearAll();
                                filltabCharge();
                            }
                         }else{
                                JOptionPane.showMessageDialog(frame,"Chargecode and CPTCode already exists");
                        }
                    }else{
                        res=ccm.updateChargeCodes(settingChargeCodeObject());
                        if(res==1){
                            JOptionPane.showMessageDialog(frame,"Chargecode Updated Successfully ... ");
                            btSave.setText("Save");
                            clearAll();
                            filltabCharge();
                        }
                    }                
                this.txtChargeCode.requestFocus();            

        }catch(Exception e){e.printStackTrace();}        
    }
    
    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // TODO add your handling code here:
       saveUpdate();       
}//GEN-LAST:event_btSaveActionPerformed


    private void clearTabCharge(){
     tabCharge.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"Chargecode","Description","Category","CPT","Modifier","TOS","POS","Comments","Price"}));
    }

    private void filltabCharge(){
    try{
        ChargecodeMethods ccm=new ChargecodeMethods();
        List<ChargeCode> lstcc=new ArrayList<ChargeCode>();        
        lstcc=ccm.loadChargesCode(settingChargeCodeObject());
        
        if(lstcc!=null){
            String[][] arr=new String[lstcc.size()][9];
            for(int i=0;i<lstcc.size();i++){
               ChargeCode cc=new ChargeCode();
               cc=lstcc.get(i);
               arr[i][0]=cc.getChargeCode();
               arr[i][1]=cc.getChargeCodeDes();
               arr[i][2]=cc.getCategory();
               arr[i][3]=cc.getCPTCode();
               arr[i][4]=cc.getModifier();
               arr[i][5]=cc.getDefaultTOS();
               arr[i][6]=cc.getDefaultPOS();
               arr[i][7]=cc.getComments();
               arr[i][8]=cc.getPrice();
            }
            tabCharge.setModel(new javax.swing.table.DefaultTableModel(arr,new String[]{"Chargecode","Description","Category","CPT","Modifier","TOS","POS","Comments","Price"}
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
        }else{
            tabCharge.setModel(new javax.swing.table.DefaultTableModel(null,new String[]{"Chargecode","Description","Category","CPT","Modifier","TOS","POS","Comments","Price"}));
        }
    }catch(Exception e){e.printStackTrace();}
    }


    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        txtChargeCode.setEditable(true);
        clearAll();
        this.btSave.setText("Save");
        clearTabCharge();
}//GEN-LAST:event_jButton6ActionPerformed

    private void delete(){
    txtChargeCode.setEditable(true);
    try{
       int chk=1;

        if(cmbCPTCode.getSelectedIndex()==-1 || cmbCPTCode.getSelectedIndex() ==0){
                return;
        }else{
            chk=JOptionPane.showConfirmDialog(frame,"Do U want to delete the Record");
            if(chk==0){
                
                int res=0;
                String[] splitCode=null;
                String cpt=null;

                ChargecodeMethods objDel=new ChargecodeMethods();

                if(cmbCPTCode.getSelectedIndex()!= -1 || cmbCPTCode.getSelectedIndex()!= 0){
                            splitCode=cmbCPTCode.getSelectedItem().toString().split("--");

                            if(splitCode.length>1)
                            cpt=splitCode[0];
                 }

                res=objDel.deleteChargeCode(txtChargeCode.getText(),cpt);
                if(res==1){
                    JOptionPane.showMessageDialog(frame,"Deleted Successfully");
                    clearAll();
                    filltabCharge();
                }
        }
        }
    }catch(Exception e){e.printStackTrace();}        
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:          
                delete();
                txtChargeCode.setEditable(true);
                clearAll();
                this.btSave.setText("Save");         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        filltabCharge();
        if(tabCharge.getRowCount()!=0){
                    tabCharge.changeSelection(0, 0, false, false);
                    tabCharge.setFocusable(true);
                    tabCharge.requestFocus();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btSaveKeyPressed
        // TODO add your handling code here:
        saveUpdate();
    }//GEN-LAST:event_btSaveKeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
         delete();        
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        // TODO add your handling code here:
        txtChargeCode.setEditable(true);
        clearAll();
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        filltabCharge();
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        // TODO add your handling code here:
        loadCPT();
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void txtPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyTyped
        // TODO add your handling code here:
         int ascii = (int) evt.getKeyChar();
        //evt.VK_F1
            if ((ascii >=48 && ascii <=57) || ascii ==46) {

            }
            else
            {
                if (evt.getKeyChar()==evt.VK_BACK_SPACE)
                {

                }
                else
                {
                    evt.consume();
                }

            }
    }//GEN-LAST:event_txtPriceKeyTyped

    private void tabChargeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabChargeKeyPressed
        // TODO add your handling code here:
    try{
        int rowCount=tabCharge.getRowCount();
        int curRow=tabCharge.getSelectedRow();

        if(evt.getKeyCode()==evt.VK_DOWN){
            if(curRow==rowCount-1){
                    evt.setKeyCode(evt.VK_ENTER);
                    tabCharge.setFocusable(true);
                    tabCharge.requestFocus();
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
             showData(rowCount,curRow);
         }

    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_tabChargeKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    try{
        if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
            MDI.chargeCodeMaster=null;
        }
    }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed

     private void clearAll(){
            this.txtChargeCode.setText("");
            this.txtChargeDes.setText("");
            this.cmbCPTCode.setSelectedIndex(-1);
            this.cmbCategory.setSelectedIndex(-1);
            this.cmbMod.setSelectedIndex(-1);
            this.cmbPOS.setSelectedIndex(-1);
            this.cmbTOS.setSelectedIndex(-1);
            this.txtComments.setText("");
            this.txtPrice.setText("");
            
     }

     
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChargeCodeMaster().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox cmbCPTCode;
    private javax.swing.JComboBox cmbCategory;
    private javax.swing.JComboBox cmbMod;
    private javax.swing.JComboBox cmbPOS;
    private javax.swing.JComboBox cmbTOS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabCharge;
    private javax.swing.JTextField txtChargeCode;
    private javax.swing.JTextField txtChargeDes;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables

}
