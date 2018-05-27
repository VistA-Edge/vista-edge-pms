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

package com.etipl.pms.reports.AZSlidingFee;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.sf.jasperreports.view.JasperViewer;


public class AZSlidingStatement {

  public int generateAZSlidingStms(String claimNo) throws SQLException{
        Connection con;
        ResultSet rs = null;
        DBConnection DB=new DBConnection();
        con=DB.getPMSConnection();
        int Amt=0;
        int sumSlidAmt=0;
        PreparedStatement selAmt=null;
        String sql="";
       
    try{
          sql="select a.amt from CPT_MASTER m,SLIDINGFEE_CPT_AMT a,CMS_CPTS cp"
                 + ",category_sliding cs, jp_pat_info jp"
                   +" where a.category_id=cs.id "
                   +" and m.procedurecode=cp.cpt"                   
                   +" and cp.claimid='"+claimNo+"'"
                   +" and jp.hrn=mid(cp.claimid,1,instr(cp.claimid,'.')-1)"
                   +" and a.CATEGORY_CODE=jp.slidingcode"
                   +" and cp.cpt!=''"
                   +" and cp.cpt between cs.cpt_min and cs.cpt_max"
                   +" group by cs.types,a.amt"
                   +" order by cp.claimid";
        
           selAmt= con.prepareStatement(sql);
            rs=selAmt.executeQuery();
            while(rs.next()){
                Amt=rs.getInt("amt");
                sumSlidAmt=sumSlidAmt+Amt;
            }

           JasperDesign jasperDesign = JRXmlLoader.load("javaReport/AZReport/statement/AZStmts.jrxml");
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


          Map model = new HashMap();

          model.put("claim_id",claimNo);
          model.put("sumSlidAmt",String.valueOf(sumSlidAmt).toString());

           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, model,con);
           JasperViewer.viewReport(jasperPrint,false);

           return 1;
     }catch(Exception ex) {ex.printStackTrace();System.out.print(ex); return 0;}
    finally{
        if(rs!=null){
            rs.close();
        }
        if(selAmt!=null){
            selAmt.close();
        }
        if(con!=null){
            con.close();
        }
    }
    }

public String[] removeDuplicate(String[] cat,String[] amt){
try{
    String[] tamt=new String[cat.length];
    int flag=0;
    
    for(int i=0;i<cat.length;i++){
      for(int j=i+1;j<cat.length;j++){

        if(cat[i].compareTo(cat[j])==0){
            if(flag==0){
             tamt[i]=amt[i];
            }else{
              tamt[i]=amt[i];
            }
            flag=flag+1;
        }else{
            tamt[i]=amt[i];
        }
      }
    }
     return tamt;
}catch(Exception e){e.printStackTrace(); return null;}
   
}

  public String amtAdj(int amt){
  try{
      if(amt==-1){
          amt=0;
      }
      return String.valueOf(amt);
  }catch(Exception e){e.printStackTrace(); return null;}
     
  }

  public String calAdj(int charges,int amt){
  try{
      int adj=0;
      if(charges<amt){
          adj=charges;
      }
      if(charges>amt){
          adj=charges-amt;
      }
      if(amt==0 || amt==-1){
          adj=0;
      }
      return String.valueOf(adj);
  }catch(Exception e){
      e.printStackTrace();
      return null;
  }
  
  }

  public int generateAZSlidingSum(String fromDate,String toDate) throws SQLException{
        Connection con;
        DBConnection DB=new DBConnection();
        con=DB.getPMSConnection();
    try{
           JasperDesign jasperDesign = JRXmlLoader.load("javaReport/AZReport/summary/AZSum.jrxml");
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


          Map model = new HashMap();

          model.put("frmDtp",fromDate);
          model.put("toDtp",toDate);

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