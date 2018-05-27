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

package com.etipl.pms.reports.Receipt;

import com.etipl.pms.MDI;
import com.etipl.pms.utilities.Utiles;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.gui.JCalendarCombo;
public class Receipt extends javax.swing.JFrame {

    /** Creates new form Receipt */
    public Receipt() {
        initComponents();
        setVisible(true);
         
        screenDisplay();
        sdate.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
        edate.setDateFormat(JCalendarCombo.MONTH, JCalendarCombo.DAY, JCalendarCombo.YEAR_BIG, '/');
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
        jLabel3 = new javax.swing.JLabel();
        genrep = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        panelSummary = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        sdate = new org.gui.JCalendarCombo();
        jLabel2 = new javax.swing.JLabel();
        edate = new org.gui.JCalendarCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Receipt Reports");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("Receipt Reports");

        genrep.setText("Generate Report");
        genrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genrepActionPerformed(evt);
            }
        });
        genrep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                genrepKeyPressed(evt);
            }
        });

        panelSummary.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Start Date ");

        jLabel2.setText("End Date ");

        javax.swing.GroupLayout panelSummaryLayout = new javax.swing.GroupLayout(panelSummary);
        panelSummary.setLayout(panelSummaryLayout);
        panelSummaryLayout.setHorizontalGroup(
            panelSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSummaryLayout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(jComboBox4, 0, 0, Short.MAX_VALUE)
                .addGap(74, 74, 74))
            .addGroup(panelSummaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(edate, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSummaryLayout.setVerticalGroup(
            panelSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSummaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(panelSummaryLayout.createSequentialGroup()
                        .addGroup(panelSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                            .addComponent(panelSummary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(genrep, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))))
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel3)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(genrep)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void getReport(){
    try{
        ReceiptMethods rm=new ReceiptMethods();
        rm.generateReceipt(Utiles.convertUSDatetoMysql(sdate.getSelectedDate())
                ,Utiles.convertUSDatetoMysql(edate.getSelectedDate()));
        
    }catch(Exception e){e.printStackTrace();}
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
    private void genrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genrepActionPerformed
        // TODO add your handling code here:
        getReport();
}//GEN-LAST:event_genrepActionPerformed

    private void genrepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genrepKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar() == '\n'){
            getReport();
        }        
}//GEN-LAST:event_genrepKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
     try{
         if(this.getDefaultCloseOperation()==MDI.defaultWindowClose){
             MDI.receiptReport=null;
         }
     }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_formWindowClosed
  
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.gui.JCalendarCombo edate;
    private javax.swing.JButton genrep;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelSummary;
    private org.gui.JCalendarCombo sdate;
    // End of variables declaration//GEN-END:variables

}
