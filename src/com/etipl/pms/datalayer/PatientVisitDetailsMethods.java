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

import com.etipl.pms.entity.PatientVisitDetails;
import com.etipl.pms.utilities.DBConnection;
import com.etipl.pms.utilities.Utiles;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PatientVisitDetailsMethods { 
   private  List<PatientVisitDetails> lstPVD;
   private int count;

   public List<PatientVisitDetails> searchPatientVisitFromMaster(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        DBConnection DB=new DBConnection();
        con=DB.getPMSConnection();
     try{
            stmt=con.createStatement();
            count=0;

            sql = "select a.available_code,e.visitno,e.visit_date,e.seen_time";
            sql = sql + ",c.doctor_name,d.department_name, a.patient_code,b.date";
            sql = sql + ",a.from_time,a.to_time";
            sql = sql + " from SLOT a, AVAILABILITY b, DOCTOR c, DEPARTMENT d, VISIT e";
            sql = sql + " Where a.available_code = b.available_unique_code";
            sql = sql + " and b.doctor_code=c.doctor_code and c.speciality=d.department_code";
            sql = sql + " and a.slot_id=e.APPOINTMENT_ID";
            sql = sql + " and a.patient_code='"+hrn+"'";
            sql = sql + " order by e.visit_date desc, e.seen_time desc";

            rs= stmt.executeQuery(sql);
                count=0;
                while(rs.next()){
                    count++;
                }
                if(count>0){
                    lstPVD=setPatientVisitMaster(rs);
                    return lstPVD;
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

   public List<PatientVisitDetails> searchPatientVisitFromVistA(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        DBConnection DB=new DBConnection();
        con=DB.getVistAConnection();
     try{
            stmt=con.createStatement();
            count=0;

            sql = sql = "select * from ENCOUNTER where 1=1 and Status='A'";
            sql = sql + " and patientid ='"+hrn+"' order by encounterdatetime desc";
            rs= stmt.executeQuery(sql);

                count=0;
                while(rs.next()){
                    count++;
                }
                if(count>0){
                    lstPVD=setPatientVisitVistA(rs);
                    return lstPVD;
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

   public List<PatientVisitDetails> setPatientVisitMaster(ResultSet rs){

        List<PatientVisitDetails> lstPVD1 = new ArrayList<PatientVisitDetails>();
        String vd,vt,vdt,adt;

        try{
                rs.first();
                do{
                 PatientVisitDetails pvd=new PatientVisitDetails();                 

                 vd=rs.getString("visit_date");
                 vt=rs.getString("seen_time");                

                 vdt=Utiles.convertMysqlDatetoUSFormat(vd.substring(0, 10))
                         +" "+vt.substring(10,16);
                 vd=rs.getString("date");
                 vt=rs.getString("from_time");
                 adt=Utiles.convertMysqlDatetoUSFormat(vd.substring(0, 10))
                         +" "+vt.substring(10,16);

                 pvd.setVisitNo(rs.getString("visitno"));
                 pvd.setVisit_Datetime(vdt);
                 pvd.setDepartment(rs.getString("department_name"));
                 pvd.setProvider(rs.getString("doctor_name"));
                 pvd.setAppt_Datetime(adt);
                 pvd.setTypeofVisit("M");lstPVD1.add(pvd);

                 } while(rs.next());
                 return lstPVD1;

         }catch(Exception e){
                 e.printStackTrace();
                 return null;
         }
    }


    public List<PatientVisitDetails> setPatientVisitVistA(ResultSet rs){

        List<PatientVisitDetails> lstPVD1 = new ArrayList<PatientVisitDetails>();
        String vd,vdt,adt;

        try{
                 rs.first();
                do{
                 PatientVisitDetails pvd=new PatientVisitDetails();
                 vd=rs.getString("VisitDateTime");
                 vdt=Utiles.convertMysqlVistADatetoUSFormat(vd.substring(0,16));
                 vd=rs.getString("EncounterDatetime");
                 adt=Utiles.convertMysqlVistADatetoUSFormat(vd.substring(0,16));

                 pvd.setVisitNo(rs.getString("EncounterID"));
                 pvd.setVisit_Datetime(vdt);
                 pvd.setDepartment(rs.getString("Clinic"));
                 pvd.setProvider(rs.getString("PrimaryProvider"));
                 pvd.setAppt_Datetime(adt);
                 pvd.setTypeofVisit("V");
                 lstPVD1.add(pvd);

                 } while(rs.next());
                 return lstPVD1;

         }catch(Exception e){
                 e.printStackTrace();
                 return null;
         }
    }
}
