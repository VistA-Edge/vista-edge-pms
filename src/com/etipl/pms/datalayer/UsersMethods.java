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

import com.etipl.pms.entity.Users;
import com.etipl.pms.utilities.DBConnection;
import com.etipl.pms.utilities.Utiles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersMethods {

   public int checkDuplicateCode(String name,String pwd) throws SQLException{
       Connection con = null;
       Statement stmt = null;
       ResultSet rs =null;
       String sql = "";

       DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="select * from USERS where UNAME='"+name+"'";

        if(!pwd.isEmpty()){
            sql=sql+" and PASSWORD='"+Utiles.hashPassword(pwd)+"'";
        }
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
  
   public String[][] loadUsers(Users ur) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs =null;
        String sql = "";
        int count = 0;
        
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();

        sql="select * from USERS where 1=1";

        if(ur.getUname()!=null){
            sql=sql+" and UNAME like '"+ur.getUname()+"%'";
        }

       if(ur.getCategory()!=null){            
            sql=sql+" and CATEGORY like '%"+ur.getCategory()+"%'";
        }

        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                String[][] arr=new String[count][2];
                int i=0;
                do{
                    arr[i][0]=rs.getString("UNAME").toString();
                    arr[i][1]=rs.getString("CATEGORY").toString();
                    i++;
                }while(rs.next());

                return arr;
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
  
   public int deleteUser(String name) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql = "";

        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="Delete from USERS where UNAME='"+name+"'";
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

 public int saveUsers(Users usr) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql;
       
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
       
                stmt= con.createStatement();

                sql = "Insert into USERS (UNAME,USER,PASSWORD,CATEGORY";
                sql=sql+") values('";

                if(usr.getUname()!=null)
                sql= sql+usr.getUname() +"'";
                else sql=sql+",Null";

                if(usr.getUser()!=null)
                sql= sql+",'"+usr.getUser()+"'";
                else sql=sql+",Null";

                if(usr.getPassword()!=null)
                sql= sql+",'"+usr.getPassword()+"'";
                else sql=sql+",Null";

                if(usr.getCategory()!=null)
                sql= sql+",'"+usr.getCategory()+"'";
                else sql=sql+",Null";
               
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

    public int updateUsers(Users usr,String olduser) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql = "";

        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
            stmt= con.createStatement();

            sql = "Update USERS set ";          

            if(usr.getPassword()!=null)
            sql=sql+"PASSWORD='"+usr.getPassword()+"'";
            else sql=sql+"PASSWORD";

            sql=sql+" where UNAME='"+olduser+"'";
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

   