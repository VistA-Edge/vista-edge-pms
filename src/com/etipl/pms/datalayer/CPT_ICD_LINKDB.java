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

import com.etipl.pms.entity.CPT_ICD_LINKDef;
import java.sql.*;
import com.etipl.pms.utilities.*;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CPT_ICD_LINKDB {
    JFrame frame = new JFrame();
    public static String setDescription,setNotes;
    public static String[] procedurecode =  new String[100];    

    public String[][] searchProcedureCode(CPT_ICD_LINKDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        DBConnection db=new DBConnection();
        int n,k=0;
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select count(*) from CPT_MASTER where procedurecode like '"+obj.getProcedureCode()+"%' and description like '"+obj.getDescription()+"%' ;";

            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getInt("count(*)");
            stmt.close();
            rs.close();

            stmt =con.createStatement();
            String[][] arr = new String[n][1];
            SqlQuery="select * from CPT_MASTER where procedurecode like '"+obj.getProcedureCode()+"%' and description like '"+obj.getDescription()+"%' ;";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next())
            {
              arr[k][0] = rs.getString("procedurecode");
              k++;             
            }
            if(n!=0)
            {
                return arr;
            }else{
                JOptionPane.showMessageDialog(frame, "Record does not exist");                
                return null;
            }

        }
        catch(Exception gfx)
        {   
            gfx.printStackTrace();
        }finally{
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();}
        }
       return null;

    }

public CPT_ICD_LINKDef showSelectedRowDatacpt(String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        CPT_ICD_LINKDef obj=new CPT_ICD_LINKDef();
        DBConnection db=new DBConnection();
try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from CPT_MASTER where procedurecode = '"+Rid+"';";
            rs= stmt.executeQuery(SqlQuery);
            while(rs.next()){
                obj.setProcedureCode(rs.getString("procedurecode"));
                obj.setDescription(rs.getString("description"));
                obj.setNotes(rs.getString("notes"));
            }           
            return obj;
        }catch(Exception gfx){
            gfx.printStackTrace();
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

public String[][] showSelectedRowDatacpticdlink(String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        DBConnection db=new DBConnection();
        try{
            int n=0;
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select count(*) from CPT_ICD_LINK where ProcedureCode = '"+Rid+"' ;";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getInt("count(*)");

            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}

            stmt =con.createStatement();
            String[][] arr = new String[n][1];

            int k=0;
            SqlQuery="select * from CPT_ICD_LINK where ProcedureCode = '"+Rid+"';";
            rs= stmt.executeQuery(SqlQuery);
            
            while(rs.next()){
                arr[k][0]=rs.getString("DiagnosisCode");
                k++;
            }           
            
            return arr;
        }catch(Exception gfx){
            db.closePMSConnection(con);
            gfx.printStackTrace();
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


public String[][] searchDiagnosisCode(CPT_ICD_LINKDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        DBConnection db=new DBConnection();
        int n,k=0;
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select count(*) from ICD_MASTER where DiagnosisCode like '"+obj.getDiagnosisCode()+"%' and Description like '"+obj.getDescription()+"%' ;";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getInt("count(*)");

            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}

            stmt =con.createStatement();
            String[][] arr = new String[n][1];
            SqlQuery="select * from ICD_MASTER where DiagnosisCode like '"+obj.getDiagnosisCode()+"%' and Description like '"+obj.getDescription()+"%' ;";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next())
            {
              arr[k][0] = rs.getString("DiagnosisCode");
              k++;
            }
            if(n!=0)
            {
                System.out.println(arr[0][0]);
               
                return arr;
            }else{
                JOptionPane.showMessageDialog(frame, "Record does not exist ");
                return null;
            }
        }
        catch(Exception gfx){  gfx.printStackTrace();}
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
        return null;
}

public CPT_ICD_LINKDef showSelectedRowDataicd(String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        CPT_ICD_LINKDef obj=new CPT_ICD_LINKDef();
        DBConnection db=new DBConnection();
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from ICD_MASTER where DiagnosisCode = '"+Rid+"';";
            rs= stmt.executeQuery(SqlQuery);
            while(rs.next()){
                obj.setDiagnosisCode(rs.getString("DiagnosisCode"));
                obj.setDescription(rs.getString("Description"));
                obj.setNotes(rs.getString("Notes"));
            }           
            return obj;
        }catch(Exception gfx){
            gfx.printStackTrace();
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

public int checkicd(String ProcedureCode, String DiagnosisCode) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String SqlQuery = "";
        DBConnection db=new DBConnection();
        ResultSet rs1 = null;
     try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
       
           SqlQuery="select count(*) from CPT_ICD_LINK where ProcedureCode = '"+ProcedureCode+"' and  DiagnosisCode = '"+DiagnosisCode+"' ; ";
           rs1= stmt.executeQuery(SqlQuery);rs1.next();
           int n=rs1.getInt("count(*)");
       
       return n;       
      }
      catch(Exception gfx){
         return 0;
      }finally{
            if(rs1!=null){
                rs1.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
}

public void addCPT_ICD_LINK(CPT_ICD_LINKDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        DBConnection db=new DBConnection();        
try{
        int i=checkicd(obj.getProcedureCode(),obj.getDiagnosisCode());
        if(i==0){
         try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            String SqlQuery1="insert into CPT_ICD_LINK(ProcedureCode,DiagnosisCode) values ('"+obj.getProcedureCode()+"','"+obj.getDiagnosisCode()+"') ;";
            stmt.executeUpdate(SqlQuery1);
            
        }catch(Exception gfx){
         db.closePMSConnection(con);
         gfx.printStackTrace();
      }finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
     }
}catch(Exception e){e.printStackTrace();}
}

public CPT_ICD_LINKDef deleteSelectedData(String ProcedureCode,String DiagnosisCode) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String SqlQuery = "";
        CPT_ICD_LINKDef obj=new CPT_ICD_LINKDef();
        DBConnection db=new DBConnection();
        try{
            
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="delete from CPT_ICD_LINK where ProcedureCode = '"+ProcedureCode+"' and DiagnosisCode = '"+DiagnosisCode+"'";
            stmt.executeUpdate(SqlQuery);
            JOptionPane.showMessageDialog(frame, "Record Updated :  Deleted ICD : "+DiagnosisCode+" from CPT : "+ProcedureCode);
            
            return obj;
      }
      catch(Exception gfx){
         gfx.printStackTrace();
         return null;
      }finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }
}
