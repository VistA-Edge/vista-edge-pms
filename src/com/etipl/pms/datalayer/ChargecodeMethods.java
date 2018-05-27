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
import com.etipl.pms.entity.ChargeCode;
import com.etipl.pms.entity.CodeDes;
import com.etipl.pms.utilities.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChargecodeMethods {   

   public int checkDuplicateCode(String code) throws SQLException{
       Connection con = null;
       Statement stmt = null;
       ResultSet rs =null;
       String sql;
       DBConnection DB=new DBConnection();
       con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="select * from CHARGE_CODE where ChargeCode='"+code+"'";
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

   public List<ChargeCode> loadChargesCode(ChargeCode code) throws SQLException{
        Connection con = null;
        Statement stmt = null;;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from CHARGE_CODE where 1=1";

        if(code.getChargeCode()!=null){
            sql=sql+" and ChargeCode like '"+code.getChargeCode()+"%'";
        }
        if(code.getChargeCodeDes()!=null){
            sql=sql+" and ChargeCodeDes like '"+code.getChargeCodeDes()+"%'";
        }
        if(code.getCategory()!=null){
            sql=sql+" and Category like '"+code.getCategory()+"%'";
        }
        if(code.getCPTCode()!=null){
            sql=sql+" and CPTCode like '"+code.getCPTCode()+"%'";
        }
        if(code.getModifier()!=null){
            sql=sql+" and Modifier like '"+code.getModifier()+"%'";
        }
        if(code.getDefaultTOS()!=null){
            sql=sql+" and DefaultTOS like '"+code.getDefaultTOS()+"%'";
        }
        if(code.getDefaultPOS()!=null){
            sql=sql+" and DefaultPOS like '"+code.getDefaultPOS()+"%'";
        }
        if(code.getComments()!=null){
            sql=sql+" and Comments like '"+code.getComments()+"%'";
        }
        if(code.getPrice()!=null){
            sql=sql+" and PRICE like '"+code.getPrice()+"%'";
        }

        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                List<ChargeCode> lstCC=new ArrayList<ChargeCode>();
                do{
                    ChargeCode cc=new ChargeCode();
                    cc.setChargeCode(rs.getString("ChargeCode"));
                    cc.setChargeCodeDes(rs.getString("ChargeCodeDes"));
                    cc.setCategory(rs.getString("Category"));
                    cc.setCPTCode(rs.getString("CPTCode"));
                    cc.setModifier(rs.getString("Modifier"));
                    cc.setDefaultTOS(rs.getString("DefaultTOS"));
                    cc.setDefaultPOS(rs.getString("DefaultPOS"));
                    cc.setComments(rs.getString("Comments"));
                    cc.setPrice(rs.getString("Price"));
                    lstCC.add(cc);
                }while(rs.next());                    
                    return lstCC;
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

  public List<CodeDes> loadCPT(String category) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        
        if(category.equalsIgnoreCase(" ")){
            sql="select * from CPT_MASTER";
        }else{
            sql="select M.PROCEDURECODE,M.description from CPT_MASTER M,CATEGORY C where M.CATEGORY=C.ID AND C.CATEGORY='"+category+"'";
        }
       
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }       
            rs.first();
            if(count>0){
            List<CodeDes> lstCD=new ArrayList<CodeDes>();
            do{
                CodeDes cd=new CodeDes();                
                cd.setCode(rs.getString("procedurecode")+"--"+rs.getString("description"));
                lstCD.add(cd);                
            }while(rs.next());
                return lstCD;
            }
            else{
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

  public List<CodeDes> loadCategory(String cptCode) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from CATEGORY where 1=1 order by CATEGORY asc";
        if(!cptCode.isEmpty()){
            sql=sql+" and Description='"+cptCode+"'";
        }
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
            List<CodeDes> lstCD=new ArrayList<CodeDes>();
            do{
                CodeDes cd=new CodeDes();
                cd.setCode(rs.getString("Category"));
                lstCD.add(cd);
            }while(rs.next());
                return lstCD;
            }
            else{
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


  public List<CodeDes> loadTOS(String cptCode) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from MASTER_TYPES where typecode='"+2+"'";

        if(!cptCode.isEmpty()){
            sql=sql+" and Description='"+cptCode+"'";
        }
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
            List<CodeDes> lstCD=new ArrayList<CodeDes>();
            do{
                CodeDes cd=new CodeDes();
                cd.setCode(rs.getString("Code")+"--"+rs.getString("description"));
                lstCD.add(cd);
            }while(rs.next());
                return lstCD;
            }
            else{
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

  public List<CodeDes> loadPOS(String cptCode) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from MASTER_TYPES where typecode='"+3+"'";

        if(!cptCode.isEmpty()){
            sql=sql+" and Description='"+cptCode+"'";
        }
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
            List<CodeDes> lstCD=new ArrayList<CodeDes>();
            do{
                CodeDes cd=new CodeDes();
                cd.setCode(rs.getString("Code")+"--"+rs.getString("description"));
                lstCD.add(cd);
            }while(rs.next());
                return lstCD;
            }
            else{
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


  public List<CodeDes> loadModifier(String cptCode) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from MASTER_TYPES where typecode='"+1+"'";

        if(!cptCode.isEmpty()){
            sql=sql+" and Description='"+cptCode+"'";
        }
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
            List<CodeDes> lstCD=new ArrayList<CodeDes>();
            do{
                CodeDes cd=new CodeDes();
                cd.setCode(rs.getString("Code")+"--"+rs.getString("description"));
                lstCD.add(cd);
            }while(rs.next());
                return lstCD;
            }
            else{
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


   public int deleteChargeCode(String code,String cpt) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="Delete from CHARGE_CODE where ChargeCode='"+code+"'";
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

   
    public int saveChargecodes(ChargeCode cc) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{

                stmt= con.createStatement();

                sql = "Insert into CHARGE_CODE (Category,ChargeCode,ChargeCodeDes,Price";
                sql =sql+",CPTCode,Modifier,DefaultTOS,DefaultPOS,Comments) values(";

                if(cc.getCategory()!=null)
                sql= sql+"'"+cc.getCategory() +"'";
                else sql=sql+"Null";
                
                if(cc.getChargeCode()!=null)
                sql= sql+",'"+cc.getChargeCode()+"'";
                else sql=sql+",Null";
                
                if(cc.getChargeCodeDes()!=null)
                sql= sql+",'"+cc.getChargeCodeDes()+"'";
                else sql=sql+",Null";
                
                if(cc.getPrice()!=null)
                sql= sql+",'"+cc.getPrice()+"'";
                else sql=sql+",Null";
                
                if(cc.getCPTCode()!=null)
                sql= sql+",'"+cc.getCPTCode()+"'";
                else sql=sql+",Null";
                
                if(cc.getModifier()!=null)
                sql= sql+",'"+cc.getModifier()+"'";
                else sql=sql+",Null";

                if(cc.getDefaultTOS()!=null)
                sql= sql+",'"+cc.getDefaultTOS()+"'";
                else sql=sql+",Null";

                if(cc.getDefaultPOS()!=null)
                sql= sql+",'"+cc.getDefaultPOS()+"'";
                else sql=sql+",Null";

                if(cc.getComments()!=null)
                sql= sql+",'"+cc.getComments()+"'";
                else sql=sql+",Null";

                sql=sql+")";
                
               stmt.executeUpdate(sql);
        
        return 1;
    }catch(Exception e){;e.printStackTrace(); return 0;}
         finally{            
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }


    public int updateChargeCodes(ChargeCode cc) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{

            stmt= con.createStatement();

            sql = "Update CHARGE_CODE set ";

            if(cc.getCategory()!=null)
            sql= sql+"Category='"+cc.getCategory()+"'";
            else sql=sql+"Category=Null";

            if(cc.getChargeCode()!=null)
            sql=sql+",ChargeCode='"+cc.getChargeCode()+"'";
            else sql=sql+",ChargeCode=Null";

            if(cc.getChargeCodeDes()!=null)
            sql= sql+",ChargeCodeDes='"+cc.getChargeCodeDes()+"'";
            else sql=sql+",ChargeCodeDes=Null";

            if(cc.getPrice()!=null)
            sql= sql+",Price='"+cc.getPrice()+"'";
            else sql=sql+",Price=Null";

            if(cc.getCPTCode()!=null)
            sql= sql+",CPTCode='"+cc.getCPTCode()+"'";
            else sql=sql+",CPTCode=Null";
            
            if(cc.getModifier()!=null)
            sql= sql+",Modifier='"+cc.getModifier()+"'";
            else sql=sql+",Modifier=Null";

            if(cc.getDefaultTOS()!=null)
            sql= sql+",DefaultTOS='"+cc.getDefaultTOS()+"'";
            else sql=sql+",DefaultTOS=Null";
            
            if(cc.getDefaultPOS()!=null)
            sql= sql+",DefaultPOS='"+cc.getDefaultPOS()+"'";
            else sql=sql+",DefaultPOS=Null";

            if(cc.getComments()!=null)
            sql= sql+",Comments='"+cc.getComments()+"'";
            else
            sql=sql+",Comments=Null";

            sql=sql+" where ChargeCode='"+cc.getChargeCode()+"'";

            stmt.executeUpdate(sql);

            return 1;
    }catch(Exception e){;e.printStackTrace();return 0;}
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
