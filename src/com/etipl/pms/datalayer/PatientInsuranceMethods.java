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

package com.etipl.pms.datalayer;

import com.etipl.pms.entity.PatientInsurance;
import com.etipl.pms.utilities.DBConnection;
import com.etipl.pms.utilities.Utiles;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientInsuranceMethods {  

   public PatientInsurance searchPatientInsuranceFromMaster(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        int rows = 0;
        PatientInsurance ins=new PatientInsurance();
        DBConnection DB=new DBConnection();
        con=DB.getPMSConnection();
   try{
                stmt=con.createStatement();

                sql="select * from PATIENT_INSURANCE where PATIENT_HRN='"+hrn+"'";
                rs= stmt.executeQuery(sql) ;
                rs.next();
                rows=rs.getRow();

                if(rows>0){
                    ins=setPatientInsuranceFromMaster(rs);
                    return ins;
                }else{
                    return null;
                }
     }catch(Exception e){
             e.printStackTrace();
             return null;
     }
      finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }


   public PatientInsurance searchPatientInsuranceFromMaster(String hrn,String instype) throws SQLException{
         Connection con = null;
         Statement stmt = null;
         ResultSet rs = null;
         String sql = "";
         int rows = 0;
         PatientInsurance ins=new PatientInsurance();
         DBConnection DB=new DBConnection();
         con=DB.getPMSConnection();
         try{
                stmt=con.createStatement();

                sql="select * from PATIENT_INSURANCE where PATIENT_HRN='"+hrn+"' and INSURANCETYPE like '"+instype+"%'";
                
                rs= stmt.executeQuery(sql) ;
                rs.next();
                rows=rs.getRow();

                if(rows>0){
                    ins=setPatientInsuranceFromMaster(rs);
                    return ins;
                }else{
                    return null;
                }

     }catch(Exception e){
                     e.printStackTrace();
                     return null;
     }
    finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }


   public PatientInsurance searchPatientInsuranceFromVistA(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        int rows = 0;
        PatientInsurance ins=new PatientInsurance();
        DBConnection DB=new DBConnection();
        con=DB.getVistAConnection();
   try{
            stmt=con.createStatement();

            sql="select * from PATIENT_INSURANCE where PATIENT_HRN='"+hrn+"'";
            rs= stmt.executeQuery(sql) ;
             rs.next();
            rows=rs.getRow();
            if(rows>0){
                ins=setPatientInsuranceFromVistA(rs);
                return ins;
            }else{
                return null;
            }
     }catch(Exception e){
                 e.printStackTrace();
                 return null;
     }
     finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }

   public PatientInsurance searchPatientInsuranceFromVistA(String hrn,String instype) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        int rows = 0;
        PatientInsurance ins=new PatientInsurance();
        DBConnection DB=new DBConnection();
        con=DB.getVistAConnection();
   try{
            stmt=con.createStatement();

            sql="select * from PATIENT_INSURANCE where PATIENT_HRN='"+hrn+"' and INSURANCETYPE like '"+instype+"%'";
            rs= stmt.executeQuery(sql) ;
             rs.next();
            rows=rs.getRow();
            if(rows>0){
                ins=setPatientInsuranceFromVistA(rs);
                return ins;
            }else{
                return null;
            }
     }catch(Exception e){
                 e.printStackTrace();
                 return null;
     }
    finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }

    public PatientInsurance setPatientInsuranceFromMaster(ResultSet rs){
        PatientInsurance ins=new PatientInsurance();
         try{
                 ins.setcomp(rs.getString("INSURANCE_COMPANY_ID"));
                 ins.setinsid(rs.getString("SUBSCRIBER_ID"));
                 ins.setInsuredLName(rs.getString("POLICY_HOLDER_LNAME"));
                 ins.setInsuredFName(rs.getString("POLICY_HOLDER_FNAME"));
                 ins.setidob(Utiles.convertMysqlDatetoUSFormat(rs.getString("POLICY_HOLDER_DOB")));
                 ins.setrh(rs.getString("POLICY_HOLDER_RELATION"));
                 ins.seteffdate(Utiles.convertMysqlDatetoUSFormat(rs.getString("EFFECTIVE_DATE")));
                 ins.setexpdate(Utiles.convertMysqlDatetoUSFormat(rs.getString("EXPIRY_DATE")));

                 return ins;

         }catch(Exception e){
             e.printStackTrace();
             return null;
         }
    }

  public PatientInsurance setPatientInsuranceFromVistA(ResultSet rs){
        PatientInsurance ins=new PatientInsurance();
         try{
                 ins.setcomp(rs.getString("INSURANCE_COMPANY_ID"));
                 ins.setinsid(rs.getString("SUBSCRIBER_ID"));
                 ins.setInsuredLName(rs.getString("POLICY_HOLDER_LNAME"));
                 ins.setInsuredFName(rs.getString("POLICY_HOLDER_FNAME"));
                 ins.setidob(rs.getString("POLICY_HOLDER_DOB"));
                 ins.setrh(rs.getString("POLICY_HOLDER_RELATION"));
                 ins.seteffdate(rs.getString("EFFECTIVE_DATE"));
                 ins.setexpdate(rs.getString("EXPIRY_DATE"));

                 return ins;

         }catch(Exception e){
             e.printStackTrace();
             return null;
         }
    }
}
