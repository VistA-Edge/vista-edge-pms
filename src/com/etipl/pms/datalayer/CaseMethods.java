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

/**
 *
 * @author MUTHU
 */
public class CaseMethods {  

   public int checkDuplicateCode(String code) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        int count=0;
    try{
        stmt=con.createStatement();
        sql="select * from PATIENT_CASE where CaseNo='"+code+"'";
        System.out.println(sql);
        rs= stmt.executeQuery(sql);
        while(rs.next()){
            count++;
        }
        System.out.println("Count :"+count);
        if(count>=1){
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
}
