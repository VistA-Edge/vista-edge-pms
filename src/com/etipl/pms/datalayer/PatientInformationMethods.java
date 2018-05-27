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

import com.etipl.pms.utilities.DBConnection;
import com.etipl.pms.entity.PatientInformation;

import com.etipl.pms.utilities.Utiles;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientInformationMethods {

   public int checkPatientInsurance(String hrn,String type) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="select * from PATIENT_INSURANCE where PATIENT_HRN='"+hrn+"' and INSURANCETYPE='"+type+"'";
        rs= stmt.executeQuery(sql);
        rs.next();
        if(rs.getRow()>0){
            return 1;
        }else{
          return 0;
        }
    }catch(Exception e){e.printStackTrace();return 0;}
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
  
public PatientInformation searchPatientFromMaster(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        int rows = 0;
        PatientInformation pf=new PatientInformation();
        DBConnection DB=new DBConnection();
        con=DB.getPMSConnection();
try{ 
            stmt=con.createStatement();

            sql="select * from JP_PAT_INFO where HRN='"+hrn+"'";

            rs= stmt.executeQuery(sql);
            rs.next();
            rows=rs.getRow();
            
            if(rows>0){
                    pf=setPatientInformationMaster(rs);
                    return pf;
            }
            else{
                    return null;
            }
            
     }catch(Exception e){
            DB.closePMSConnection(con);
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

public PatientInformation searchPatientFromVistA(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        int rows = 0;
        PatientInformation pf=new PatientInformation();
        DBConnection DB=new DBConnection();
        con=DB.getVistAConnection();
try{
            stmt=con.createStatement();

            sql="select * from PATIENT_DETAILS where HRN='"+hrn+"'";
            rs= stmt.executeQuery(sql) ;
            rs.next();
            rows=rs.getRow();
            
            if(rows>0){                    
                    pf=setPatientInformationVistA(rs);
                    return pf;                   
            }else{
                    return null;
            }            
     }catch(Exception e){
                    DB.closeVistAConnection(con);
                     e.printStackTrace();
                     return null;
     }finally{
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

    public PatientInformation setPatientInformationMaster(ResultSet rs){
        PatientInformation pf=new PatientInformation();
         try{
                 pf.setLastName(rs.getString("LName"));
                 pf.setLocalAdd(rs.getString("LADD1"));
                 pf.setLocalAdd2(rs.getString("LADD2"));                 
                 pf.setLocalCity(rs.getString("LADD_CITY"));
                 pf.setLocalPin(rs.getString("LADD_PIN"));
                 pf.setFirstName(rs.getString("FNAME"));
                 pf.setLocalState(rs.getString("LADD_STATE"));
                 pf.setLocalphone(rs.getString("PHONE"));
                 pf.setHRN(rs.getString("HRN"));
                 pf.setDOB(Utiles.convertMysqlDatetoUSFormat(rs.getString("DOB")));
                 pf.setSex(rs.getString("SEX"));
                 pf.setSSN(rs.getString("SSN"));
                 pf.setMS(rs.getString("MS"));
                 pf.setCPhone(rs.getString("CPhone"));
                 
                 return pf;
                
         }catch(Exception e){
                 e.printStackTrace();
                 return  null;
         }         
    }

    public PatientInformation setPatientInformationVistA(ResultSet rs){
        PatientInformation pf=new PatientInformation();
         try{
                 pf.setLastName(rs.getString("LName"));
                 pf.setLocalAdd(rs.getString("PATIENT_ADDRESS1"));
                 pf.setLocalAdd2(rs.getString("PATIENT_ADDRESS2"));
                 pf.setLocalCity(rs.getString("PATIENT_CITY"));
                 pf.setLocalPin(rs.getString("PATIENT_ZIPCODE"));
                 pf.setFirstName(rs.getString("FNAME"));
                 pf.setLocalState(rs.getString("PATIENT_STATE"));
                 pf.setLocalphone(rs.getString("PATIENT_PHONENUMBER"));
                 pf.setHRN(rs.getString("HRN"));
                 pf.setDOB(rs.getString("DOB"));
                 pf.setSex(rs.getString("SEX"));
                 pf.setMS(rs.getString("MARTIAL_STATUS"));
                 
                 return pf;

         }catch(Exception e){
                 e.printStackTrace();
                 return null;
         }
    }
}