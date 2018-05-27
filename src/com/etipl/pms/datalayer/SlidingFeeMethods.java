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

public class SlidingFeeMethods {

    public String[] getCodeandRange(int fmySize,int fmyIncome) throws SQLException{
       Connection con = null;
       Statement stmt = null;
       ResultSet rs =null;
       String sql = "";
       int count = 0;

        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select Distinct * from SLIDING_FEE where FAMILY_SIZE='"+fmySize+"'"+" and " +fmyIncome+" between MINIMUM_INCOME and MAXIMUM_INCOME";
        System.out.println(sql);
        
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                
                String[] sliding=new String[3];

                 sliding[0]=rs.getString("CODES");
                 sliding[1]=rs.getString("MINIMUM_INCOME");
                 sliding[2]=rs.getString("MAXIMUM_INCOME");
                return  sliding;
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
}