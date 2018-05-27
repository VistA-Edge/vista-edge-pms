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

import com.etipl.pms.entity.Departments;
import com.etipl.pms.entity.Doctor;
import com.etipl.pms.utilities.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class DoctorMethods {

   public int checkDuplicateCode(String code) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            String sql;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="select * from DOCTOR where DOCTOR_CODE='"+code+"'";
        rs= stmt.executeQuery(sql);
        rs.next();
        if(rs.getRow()>0){
            return 1;
        }else{
          return 0;
        }
    }catch(Exception e){e.printStackTrace();return 0;}
        finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }

   public List<Departments> loadDepartments() throws SQLException{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            String sql = "";
            int count = 0;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from DEPARTMENT where 1=1";
        
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                List<Departments> lstD=new ArrayList<Departments>();
                do{
                    Departments cd=new Departments();
                    cd.setCode(rs.getString("DEPARTMENT_CODE"));
                    cd.setName(rs.getString("DEPARTMENT_NAME"));
                    lstD.add(cd);
                }while(rs.next());
                    return lstD;
            }
            return null;           
   }catch(Exception e){e.printStackTrace();return null;}
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

   public List<Doctor> loadDoctors(Doctor dr) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            String sql = "";
            int count = 0;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
            String[] splitName = null;
   try{
        stmt=con.createStatement();
        
        sql="select * from DOCTOR where 1=1";

        if(dr.getDOCTOR_CODE()!=null && !dr.getDOCTOR_CODE().isEmpty()){
            sql=sql+" and DOCTOR_CODE = '"+dr.getDOCTOR_CODE()+"'";
        }

       if(dr.getDOCTOR_NAME()!=null && !dr.getDOCTOR_NAME().isEmpty()){
            splitName=dr.getDOCTOR_NAME().split("-");
            if(splitName.length>0){
                if(splitName[0]!=null){
                     sql=sql+" and DOCTOR_NAME like '"+splitName[0]+"%'";
                }                
            }
            if(splitName.length>1){
                if(splitName[1]!=null){
                     sql=sql+" and DOCTOR_NAME like '%"+splitName[1]+"%'";
                }
            }
            if(splitName.length>2){
                if(splitName[2]!=null){
                     sql=sql+" and DOCTOR_NAME like '%"+splitName[2]+"%'";
                }
            }
        }
        
        if(dr.getSPECIALITY()!=null && !dr.getSPECIALITY().isEmpty()){
            sql=sql+" and SPECIALITY = '"+dr.getSPECIALITY()+"'";
        }
        if(dr.getCREDENTIALS()!=null && !dr.getCREDENTIALS().isEmpty()){
            sql=sql+" and CREDENTIALS = '"+dr.getCREDENTIALS()+"'";
        }
        if(dr.getAdd2()!=null && !dr.getAdd2().isEmpty()){
            sql=sql+" and ADD2 like '"+dr.getAdd2()+"%'";
        }
        if(dr.getCity()!=null && !dr.getCity().isEmpty()){
            sql=sql+" and CITY like '"+dr.getCity()+"%'";
        }
        if(dr.getZip()!=null && !dr.getZip().isEmpty()){
            sql=sql+" and ZIP = '"+dr.getZip()+"'";
        }
        if(dr.getState()!=null && !dr.getState().isEmpty()){
            sql=sql+" and STATE like '"+dr.getState()+"%'";
        }
        if(dr.getCELL()!=null && !dr.getCELL().isEmpty()){
            sql=sql+" and CELL = '"+dr.getCELL()+"'";
        }
        if(dr.getNPI()!=null && !dr.getNPI().isEmpty()){
            sql=sql+" and NPI = '"+dr.getNPI()+"'";
        }
        if(dr.getFEDERALTAXID()!=null && !dr.getFEDERALTAXID().isEmpty()){
            sql=sql+" and FEDERALTAXID = '"+dr.getFEDERALTAXID()+"'";
        }
        if(dr.getTAXONOMY()!=null && !dr.getTAXONOMY().isEmpty()){
            sql=sql+" and TAXONOMY = '"+dr.getTAXONOMY()+"'";
        }

        if(dr.getDEANO()!=null && !dr.getDEANO().isEmpty()){
            sql=sql+" and DEANO = '"+dr.getDEANO()+"'";
        }

        if(dr.getStateLicence()!=null && !dr.getStateLicence().isEmpty()){
            sql=sql+" and STATE_LICENCE = '"+dr.getStateLicence()+"'";
        }

        if(dr.getMEDICAIDGROUP()!=null && !dr.getMEDICAIDGROUP().isEmpty()){
            sql=sql+" and MEDICAID_GROUP like '"+dr.getMEDICAIDGROUP()+"%'";
        }
        if(dr.getMEDICAREGROUP()!=null && !dr.getMEDICAREGROUP().isEmpty()){
            sql=sql+" and MEDICARE_GROUP like '"+dr.getMEDICAREGROUP()+"%'";
        }
        if(dr.getBCBSGROUP()!=null && !dr.getBCBSGROUP().isEmpty()){
            sql=sql+" and BCBS_GROUP like '"+dr.getBCBSGROUP()+"%'";
        }
        if(dr.getRailRoadGroup()!=null && !dr.getRailRoadGroup().isEmpty()){
            sql=sql+" and RAIL_ROAD_GROUP like '"+dr.getRailRoadGroup()+"%'";
        }
        if(dr.getMEDICAID()!=null && !dr.getMEDICAID().isEmpty()){
            sql=sql+" and MEDICAID like '"+dr.getMEDICAID()+"%'";
        }
        if(dr.getMEDICARE()!=null && !dr.getMEDICARE().isEmpty()){
            sql=sql+" and MEDICARE like '"+dr.getMEDICARE()+"%'";
        }
        if(dr.getBCBS()!=null && !dr.getBCBS().isEmpty()){
            sql=sql+" and BCBS like '"+dr.getBCBS()+"%'";
        }
        if(dr.getRailRoad()!=null && !dr.getRailRoad().isEmpty()){
            sql=sql+" and RAIL_ROAD_MEDICARE like '"+dr.getRailRoad()+"%'";
        }
        
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                List<Doctor> lstDoc=new ArrayList<Doctor>();
                do{
                    Doctor doc=new Doctor();
                    doc.setDOCTOR_CODE(rs.getString("DOCTOR_CODE"));
                    doc.setDOCTOR_NAME(rs.getString("DOCTOR_NAME"));
                    doc.setAdd2(rs.getString("Add2"));
                    doc.setZip(rs.getString("Zip"));
                    doc.setCity(rs.getString("City"));
                    doc.setState(rs.getString("State"));
                    doc.setNPI(rs.getString("NPI"));
                    doc.setFEDERALTAXID(rs.getString("FEDERALTAXID"));
                    doc.setTAXONOMY(rs.getString("TAXONOMY"));
                    doc.setCELL(rs.getString("CELL"));
                    doc.setSPECIALITY(rs.getString("SPECIALITY"));
                    doc.setCREDENTIALS(rs.getString("CREDENTIALS"));                    

                    doc.setMEDICAIDGROUP(rs.getString("MEDICAID_GROUP"));
                    doc.setMEDICAREGROUP(rs.getString("MEDICARE_GROUP"));
                    doc.setBCBSGROUP(rs.getString("BCBS_GROUP"));
                    doc.setRailRoadGroup(rs.getString("RAIL_ROAD_GROUP"));
                    doc.setMEDICAID(rs.getString("MEDICAID"));
                    doc.setMEDICARE(rs.getString("MEDICARE"));
                    doc.setBCBS(rs.getString("BCBS"));
                    doc.setRailRoad(rs.getString("RAIL_ROAD_MEDICARE"));

                    doc.setDEANO(rs.getString("DEANO"));
                    doc.setStateLicence(rs.getString("STATE_LICENCE"));

                    lstDoc.add(doc);
                }while(rs.next());
                    return lstDoc;
            }else{
                JOptionPane.showMessageDialog(null,"Record does not exist");
                return null;
            }
   }catch(Exception e){e.printStackTrace();return null;}
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


   public List<Doctor> loadDoctors(String code) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            String sql = "";
            int count = 0;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();

        sql="select * from DOCTOR where 1=1";

        if(code!=null){
            sql=sql+" and DOCTOR_NAME='"+code+"'";
        }

        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                List<Doctor> lstDoc=new ArrayList<Doctor>();
                do{
                    Doctor doc=new Doctor();
                    doc.setDOCTOR_CODE(rs.getString("DOCTOR_CODE"));
                    doc.setDOCTOR_NAME(rs.getString("DOCTOR_NAME"));
                    doc.setAdd2(rs.getString("Add2"));
                    doc.setZip(rs.getString("Zip"));
                    doc.setCity(rs.getString("City"));
                    doc.setState(rs.getString("State"));
                    doc.setNPI(rs.getString("NPI"));
                    doc.setFEDERALTAXID(rs.getString("FEDERALTAXID"));
                    doc.setTAXONOMY(rs.getString("TAXONOMY"));
                    doc.setCELL(rs.getString("CELL"));
                    doc.setSPECIALITY(rs.getString("SPECIALITY"));
                    doc.setCREDENTIALS(rs.getString("CREDENTIALS")); 
                    doc.setMEDICAIDGROUP(rs.getString("MEDICAID_GROUP"));
                    doc.setMEDICAREGROUP(rs.getString("MEDICARE_GROUP"));
                    doc.setBCBSGROUP(rs.getString("BCBS_GROUP"));
                    doc.setRailRoadGroup(rs.getString("RAIL_ROAD_GROUP"));
                    doc.setMEDICAID(rs.getString("MEDICAID"));
                    doc.setMEDICARE(rs.getString("MEDICARE"));
                    doc.setBCBS(rs.getString("BCBS"));
                    doc.setRailRoad(rs.getString("RAIL_ROAD_MEDICARE"));

                    doc.setDEANO(rs.getString("DEANO"));
                    doc.setStateLicence(rs.getString("STATE_LICENCE"));
                    
                    lstDoc.add(doc);
                }while(rs.next());
                    return lstDoc;
            }else{
                return null;
            }
   }catch(Exception e){e.printStackTrace();return null;}
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

   public int deleteDoctor(String code) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            String sql = "";
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="Delete from DOCTOR where DOCTOR_CODE='"+code+"'";
        stmt.executeUpdate(sql);
        return 1;

    }catch(Exception e){e.printStackTrace();return 0;}
        finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }
  
    public int saveDoctor(Doctor doc) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            String sql = "";
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
    try{

                stmt= con.createStatement();

                sql = "Insert into DOCTOR (DOCTOR_CODE,DOCTOR_NAME,Add2,Zip";
                sql =sql+",City,State,NPI,FEDERALTAXID,SPECIALITY,TAXONOMY,CELL," +
                        "CREDENTIALS," +
                        "MEDICAID_GROUP,MEDICARE_GROUP,BCBS_GROUP,RAIL_ROAD_GROUP," +
                        "MEDICAID,MEDICARE,BCBS,RAIL_ROAD_MEDICARE,DEANO,STATE_LICENCE) values('";

                if(doc.getDOCTOR_CODE()!=null)
                sql= sql+doc.getDOCTOR_CODE() +"'";
                else sql=sql+",''";
                
                if(doc.getDOCTOR_NAME()!=null)
                sql= sql+",'"+doc.getDOCTOR_NAME()+"'";
                else sql=sql+",''";
                
                if(doc.getAdd2()!=null)
                sql= sql+",'"+doc.getAdd2()+"'";
                else sql=sql+",''";

                if(doc.getZip()!=null)
                sql= sql+",'"+doc.getZip()+"'";
                else sql=sql+",''";

                if(doc.getCity()!=null)
                sql= sql+",'"+doc.getCity()+"'";
                else sql=sql+",''";

                if(doc.getState()!=null)
                sql= sql+",'"+doc.getState()+"'";
                else sql=sql+",''";

                if(doc.getNPI()!=null)
                sql= sql+",'"+doc.getNPI()+"'";
                else sql=sql+",''";

                if(doc.getFEDERALTAXID()!=null)
                sql= sql+",'"+doc.getFEDERALTAXID()+"'";
                else sql=sql+",''";

                if(doc.getSPECIALITY()!=null)
                sql= sql+",'"+doc.getSPECIALITY()+"'";
                else sql=sql+",''";

                if(doc.getTAXONOMY()!=null)
                sql= sql+",'"+doc.getTAXONOMY()+"'";
                else sql=sql+",''";

                if(doc.getCELL()!=null)
                sql= sql+",'"+doc.getCELL()+"'";
                else sql=sql+",''";

                if(doc.getCREDENTIALS()!=null)
                sql= sql+",'"+doc.getCREDENTIALS()+"'";
                else sql=sql+",''";


                if(doc.getMEDICAIDGROUP()!=null)
                sql= sql+",'"+doc.getMEDICAIDGROUP()+"'";
                else sql=sql+",''";

                if(doc.getMEDICAREGROUP()!=null)
                sql= sql+",'"+doc.getMEDICAREGROUP()+"'";
                else sql=sql+",''";

                if(doc.getBCBSGROUP()!=null)
                sql= sql+",'"+doc.getBCBSGROUP()+"'";
                else sql=sql+",''";

                if(doc.getRailRoadGroup()!=null)
                sql= sql+",'"+doc.getRailRoadGroup()+"'";
                else sql=sql+",''";

                if(doc.getMEDICAID()!=null)
                sql= sql+",'"+doc.getMEDICAID()+"'";
                else sql=sql+",''";

                if(doc.getMEDICARE()!=null)
                sql= sql+",'"+doc.getMEDICARE()+"'";
                else sql=sql+",''";

                if(doc.getBCBS()!=null)
                sql= sql+",'"+doc.getBCBS()+"'";
                else sql=sql+",''";

                if(doc.getRailRoad()!=null)
                sql= sql+",'"+doc.getRailRoad()+"'";
                else sql=sql+",''";

                if(doc.getDEANO()!=null)
                sql= sql+",'"+doc.getDEANO()+"'";
                else sql=sql+",''";

                if(doc.getStateLicence()!=null)
                sql= sql+",'"+doc.getStateLicence()+"'";
                else sql=sql+",''";

                sql=sql+")";

                stmt.executeUpdate(sql);
                
        return 1;
    }catch(Exception e){e.printStackTrace(); return 0;}
        finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }


    public int updateDoctor(Doctor doc) throws SQLException{
         Connection con = null;
         Statement stmt = null;
         String sql = "";
         DBConnection DB=new DBConnection();
         con= DB.getPMSConnection();
    try{

            stmt= con.createStatement();

            sql = "Update DOCTOR set ";
            if(doc.getDOCTOR_CODE()!=null)
            sql= sql+"DOCTOR_CODE='"+doc.getDOCTOR_CODE()+"'";
            else sql=sql+",DOCTOR_CODE=Null";
            
            if(doc.getDOCTOR_NAME()!=null)
            sql=sql+",DOCTOR_NAME='"+doc.getDOCTOR_NAME()+"'";
            else sql=sql+",DOCTOR_NAME=Null";
            
            if(doc.getAdd2()!=null)
            sql= sql+",Add2='"+doc.getAdd2()+"'";
            else sql=sql+",Add2=Null";

            if(doc.getCity()!=null)
            sql= sql+",City='"+doc.getCity()+"'";
            else sql=sql+",City=Null";

            if(doc.getState()!=null)
            sql= sql+",State='"+doc.getState()+"'";
            else sql=sql+",State=Null";

            if(doc.getZip()!=null)
            sql= sql+",Zip='"+doc.getZip()+"'";
            else sql=sql+",Zip=Null";

            if(doc.getNPI()!=null)
            sql= sql+",NPI='"+doc.getNPI()+"'";
            else sql=sql+",NPI=Null";

            if(doc.getFEDERALTAXID()!=null)
            sql= sql+",FEDERALTAXID='"+doc.getFEDERALTAXID()+"'";
            else sql=sql+",FEDERALTAXID=Null";

            if(doc.getSPECIALITY()!=null)
            sql= sql+",SPECIALITY='"+doc.getSPECIALITY()+"'";
            else sql=sql+",SPECIALITY=Null";

            if(doc.getTAXONOMY()!=null)
            sql= sql+",TAXONOMY='"+doc.getTAXONOMY()+"'";
            else sql=sql+",TAXONOMY=Null";

            if(doc.getCELL()!=null)
            sql= sql+",CELL='"+doc.getCELL()+"'";
            else sql=sql+",CELL=Null";

            if(doc.getCREDENTIALS()!=null)
            sql= sql+",CREDENTIALS='"+doc.getCREDENTIALS()+"'";
            else sql=sql+",CREDENTIALS=Null";


            if(doc.getMEDICAIDGROUP()!=null)
            sql= sql+",MEDICAID_GROUP='"+doc.getMEDICAIDGROUP()+"'";
            else sql=sql+",MEDICAID_GROUP=Null";

            if(doc.getMEDICAREGROUP()!=null)
            sql= sql+",MEDICARE_GROUP='"+doc.getMEDICAREGROUP()+"'";
            else sql=sql+",MEDICARE_GROUP=Null";

            if(doc.getBCBSGROUP()!=null)
            sql= sql+",BCBS_GROUP='"+doc.getBCBSGROUP()+"'";
            else sql=sql+",BCBS_GROUP=Null";

            if(doc.getRailRoadGroup()!=null)
            sql= sql+",RAIL_ROAD_GROUP='"+doc.getRailRoadGroup()+"'";
            else sql=sql+",RAIL_ROAD_GROUP=Null";

            if(doc.getMEDICAID()!=null)
            sql= sql+",MEDICAID='"+doc.getMEDICAID()+"'";
            else sql=sql+",MEDICAID=Null";

            if(doc.getMEDICARE()!=null)
            sql= sql+",MEDICARE='"+doc.getMEDICARE()+"'";
            else sql=sql+",MEDICARE=Null";

            if(doc.getBCBS()!=null)
            sql= sql+",BCBS='"+doc.getBCBS()+"'";
            else sql=sql+",BCBS=Null";

            if(doc.getRailRoad()!=null)
            sql= sql+",RAIL_ROAD_MEDICARE='"+doc.getRailRoad()+"'";
            else sql=sql+",RAIL_ROAD_MEDICARE=Null";

            if(doc.getDEANO()!=null)
            sql= sql+",DEANO='"+doc.getDEANO()+"'";
            else sql=sql+",DEANO=Null";

            if(doc.getStateLicence()!=null)
            sql= sql+",STATE_LICENCE='"+doc.getStateLicence()+"'";
            else sql=sql+",STATE_LICENCE=Null";

            sql=sql+" where DOCTOR_CODE='"+doc.getDOCTOR_CODE()+"'";
            stmt.executeUpdate(sql);
            
            return 1;
    }catch(Exception e){e.printStackTrace();return 0;}
        finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }
}
