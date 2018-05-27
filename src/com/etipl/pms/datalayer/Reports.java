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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reports {

  public String[] getClaimNosfromCMS_CPTs(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs =null;
        String sql = "";
        int count = 0;
       
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select DISTINCT a.CLAIMID from CMS_CPTS a,CMS_HCFA1500 b ";
        sql=sql+" where a.CLAIMID=b.CLAIMID and b.HRN='"+hrn+"'";
        
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
               String[] claimNos=new String[count];
                for(int i=0;i<count;i++){
                    claimNos[i]=rs.getString("CLAIMID");
                    rs.next();
                }
                return claimNos;
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

  public String[] getClaimNosfromPayment(String hrn) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs =null;
        String sql = "";
        int count = 0;

        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select DISTINCT CLAIM_ID from PAYMENT A,CMS_HCFA1500 B";
        sql=sql+" where A.CLAIM_ID=b.CLAIMID and A.HRN='"+hrn+"'";

        rs= stmt.executeQuery(sql);

        System.out.println("ClaimIds :"+sql);

        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
               String[] claimNos=new String[count];
                for(int i=0;i<count;i++){
                    claimNos[i]=rs.getString("CLAIMID");
                    rs.next();
                }
                return claimNos;
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
}
