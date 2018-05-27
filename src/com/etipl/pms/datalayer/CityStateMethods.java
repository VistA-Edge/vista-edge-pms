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

import com.etipl.pms.entity.CityState;
import com.etipl.pms.utilities.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CityStateMethods {

public List<CityState> loadCityState(String zipcode) throws SQLException{
        Connection con = null;
        ResultSet rs = null;
        String sql;
        int count = 0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        PreparedStatement loadCS = null;
   try{
        sql="select DISTINCT z.CITY,s.ID,z.COUNTY from ZIP z,STATES s where z.state=s.name and MAILCODE='"+zipcode+"'";
        loadCS=con.prepareStatement(sql);
        rs=loadCS.executeQuery();            
            
            while(rs.next()){
                    count++;
            }
            rs.first();
            if(count>0){
            List<CityState> lstCS=new ArrayList<CityState>();
            do{
                CityState cs=new CityState();
                cs.setCity(rs.getString("CITY"));
                cs.setState(rs.getString("ID"));
                cs.setCounty(rs.getString("COUNTY"));
                lstCS.add(cs);
            }while(rs.next());
                return lstCS;          
            }else{
                return null;
            }
   }catch(Exception e){e.printStackTrace();return null;}
   finally{
            if(rs!=null){
                rs.close();
            }
            if(loadCS!=null){
                loadCS.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }
}
