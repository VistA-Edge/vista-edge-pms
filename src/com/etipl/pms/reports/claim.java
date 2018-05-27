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

package com.etipl.pms.reports;

import com.etipl.pms.utilities.DBConnection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JasperReport;
import java.sql.Connection;
import net.sf.jasperreports.view.JasperViewer;


public class claim {
    
    public int generateReport(String claimNo) throws SQLException{
        Connection con;
        DBConnection DB=new DBConnection();
        con=DB.getPMSConnection();
    try{
           JasperDesign jasperDesign = JRXmlLoader.load("javaReport/report/claim.jrxml");
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

           Map model = new HashMap();          
           model.put("claim_id",claimNo);

           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, model,con);
           JasperViewer.viewReport(jasperPrint,false);

           return 1;
     }catch(Exception ex) {ex.printStackTrace();System.out.print(ex); return 0;}
    finally{
        if(con!=null){
            con.close();
        }
    }
    }
}