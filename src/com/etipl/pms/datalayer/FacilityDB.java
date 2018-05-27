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

import java.sql.*;
import com.etipl.pms.entity.*;
import com.etipl.pms.*;
import javax.swing.JOptionPane;



public class FacilityDB {
    
    /** Creates a new instance of FacilityDB */        
  
    public FacilityDef showFacility(String name) throws SQLException{
        Statement stmt = null;
        String SqlQuery;
        ResultSet rs = null;
        FacilityDef obj=new FacilityDef();
    try{  
        stmt = MDI.global_con.createStatement();        
        SqlQuery="select * from FACILITY where Code='"+name+"'";
        rs= stmt.executeQuery(SqlQuery); 
        rs.next();
                obj.setcode(rs.getString("Code"));
                obj.setservicename(rs.getString("Name"));
                obj.setaddress(rs.getString("Address2"));
                obj.setzipcode(rs.getString("Zip"));
                obj.setcity(rs.getString("City"));
                obj.setstate(rs.getString("State"));
                obj.setnpi(rs.getString("NPI"));
                obj.setphone(rs.getString("Phone"));
                obj.setfax(rs.getString("Fax"));
                obj.setfederaltaxid(rs.getString("FederalTaxId"));
                obj.setservices(rs.getString("Services"));
                obj.setTaxonomy(rs.getString("TaxonomyID"));
                obj.setCLIA(rs.getString("CLIA_NO"));
        
        return obj;
      }
      catch(Exception gfx)
      {
         gfx.printStackTrace();
          return null;
      }finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
        }       
    }

    public int checkDuplicateCode(String code) throws SQLException{
            Statement stmt = null;
            String SqlQuery;
            ResultSet rs = null;
    try{
       
        stmt = MDI.global_con.createStatement();
        SqlQuery="select * from FACILITY where Code='"+code+"'";
        rs= stmt.executeQuery(SqlQuery);
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
    }
    }
    
    public void addFacility(FacilityDef obj) throws SQLException{
             Statement stmt = null;
             String SqlQuery;
             ResultSet rs = null;
      try{  
        stmt = MDI.global_con.createStatement();

        SqlQuery="insert into FACILITY(Code,Name,Address2,Zip,City,State,NPI,Phone,Fax,FederalTaxId,TaxonomyID,CLIA_NO,Services) values('"
                +obj.getcode()+"','"+obj.getservicename()+"','"+obj.getaddress()+"','"
                +obj.getzipcode()+"','"+obj.getcity()+"','"+obj.getstate()+"','"
                +obj.getnpi()+"','"+obj.getphone()+"','"+obj.getfax()+"','"
                +obj.getfederaltaxid()+"','"+obj.getTaxonomy()+"','"+obj.getCLIA()+"','"+obj.getservices()+"')";
        stmt.executeUpdate(SqlQuery); 
      }
      catch(Exception gfx){gfx.printStackTrace();}
      finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
    }      
    }
    public void removeFacility(String code) throws SQLException{
             Statement stmt = null;
             String SqlQuery;
             ResultSet rs = null;
    try{  
        stmt = MDI.global_con.createStatement();        
        SqlQuery="delete from FACILITY where Code = '"+code+"'";
        stmt.executeUpdate(SqlQuery); 
      }
      catch(Exception gfx){gfx.printStackTrace();}
      finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
    }    
    }
    public void updateFacility(FacilityDef obj) throws SQLException{
             Statement stmt = null;
             String SqlQuery;
             ResultSet rs = null;
    try{  
        stmt = MDI.global_con.createStatement();        
        SqlQuery="update FACILITY set "
                +"Name= '"+obj.getservicename()+"',"
                +"Address2= '"+obj.getaddress()+"',"
                +"Zip= '"+obj.getzipcode()+"',"
                +"City= '"+obj.getcity()+"',"
                +"State= '"+obj.getstate()+"',"
                +"NPI= '"+obj.getnpi()+"',"
                +"Phone= '"+obj.getphone()+"',"
                +"Fax= '"+obj.getfax()+"',"
                +"FederalTaxId= '"+obj.getfederaltaxid()+"',"
                +"Services= '"+obj.getservices()+"',"
                +"TaxonomyID= '"+obj.getTaxonomy()+"',"
                +"CLIA_NO= '"+obj.getCLIA()+"' "                
                +"where Code='"+obj.getcode()+"'";
                
        stmt.executeUpdate(SqlQuery); 
      }
      catch(Exception gfx){gfx.printStackTrace();}
      finally{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
    }    
    }
    public String[][] searchFaciltiy(FacilityDef obj,int flag){
             Statement stmt = null;
             String SqlQuery = "";
             ResultSet rs = null;
             int n=0;
  try{
        if(flag==0){
        stmt = MDI.global_con.createStatement();        
        SqlQuery="select count(*) from FACILITY where "
                +"Code like '"+obj.getcode()+"%' and "
                +"Name like '"+obj.getservicename()+"%' and "
                +"Address2 like '"+obj.getaddress()+"%' and "
                +"Zip like '"+obj.getzipcode()+"%' and "
                +"City like '"+obj.getcity()+"%' and "
                +"State like '"+obj.getstate()+"%' and "
                +"NPI like '"+obj.getnpi()+"%' and "
                +"Phone like '"+obj.getphone()+"%' and "
                +"Fax like '"+obj.getfax()+"%' and "
                +"FederalTaxId like '"+obj.getfederaltaxid()+"%' and "
                +"TaxonomyID like '"+obj.getTaxonomy()+"%' and "
                +"CLIA_NO like '"+obj.getCLIA()+"%' and "
                +"(Services = '"+obj.getservices()+"' or Services = '"+obj.getservices1()+"'); ";
       
        rs= stmt.executeQuery(SqlQuery); 
        rs.next();
        n=rs.getInt("count(*)");

        SqlQuery="select * from FACILITY where "
                +"Code like '"+obj.getcode()+"%' and "
                +"Name like '"+obj.getservicename()+"%' and "
                +"Address2 like '"+obj.getaddress()+"%' and "
                +"Zip like '"+obj.getzipcode()+"%' and "
                +"City like '"+obj.getcity()+"%' and "
                +"State like '"+obj.getstate()+"%' and "
                +"NPI like '"+obj.getnpi()+"%' and "
                +"Phone like '"+obj.getphone()+"%' and "
                +"Fax like '"+obj.getfax()+"%' and "
                +"FederalTaxId like '"+obj.getfederaltaxid()+"%' and "
                +"TaxonomyID like '"+obj.getTaxonomy()+"%' and "
                +"CLIA_NO like '"+obj.getCLIA()+"%' and "
                +"(Services = '"+obj.getservices()+"' or Services = '"+obj.getservices1()+"'); ";
        
        rs= stmt.executeQuery(SqlQuery); 
        String[][] arr = new String[n][10];
        int k =0;
        while(rs.next())
        {
            arr[k][0] = rs.getString("Code");
            arr[k][1] = rs.getString("Name"); 
            arr[k][2] = rs.getString("Address2");
            arr[k][3] = rs.getString("Phone");
            arr[k][4] = rs.getString("Fax");
            arr[k][5] = rs.getString("FederalTaxId");
            arr[k][6] = rs.getString("NPI");
            arr[k][7] = rs.getString("Services");
            arr[k][8] = rs.getString("TaxonomyID");
            arr[k][9] = rs.getString("CLIA_NO");

            k++;
        }
        if(n==0){
            JOptionPane.showMessageDialog(null,"Record does not exist");
            return null;
        }else{return arr;}

        }else{
            stmt = MDI.global_con.createStatement();
            SqlQuery="select count(*) from FACILITY where "
                +"Code like '"+obj.getcode()+"%' and "
                +"Name like '"+obj.getservicename()+"%' and "
                +"Address2 like '"+obj.getaddress()+"%' and "
                +"Zip like '"+obj.getzipcode()+"%' and "
                +"City like '"+obj.getcity()+"%' and "
                +"State like '"+obj.getstate()+"%' and "
                +"NPI like '"+obj.getnpi()+"%' and "
                +"Phone like '"+obj.getphone()+"%' and "
                +"Fax like '"+obj.getfax()+"%' and "
                +"FederalTaxId like '"+obj.getfederaltaxid()+"%' and "
                +"TaxonomyID like '"+obj.getTaxonomy()+"%' and "
                +"CLIA_NO like '"+obj.getCLIA()+"%' ";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getInt("count(*)");
            SqlQuery="select * from FACILITY where "
                +"Code like '"+obj.getcode()+"%' and "
                +"Name like '"+obj.getservicename()+"%' and "
                +"Address2 like '"+obj.getaddress()+"%' and "
                +"Zip like '"+obj.getzipcode()+"%' and "
                +"City like '"+obj.getcity()+"%' and "
                +"State like '"+obj.getstate()+"%' and "
                +"NPI like '"+obj.getnpi()+"%' and "
                +"Phone like '"+obj.getphone()+"%' and "
                +"Fax like '"+obj.getfax()+"%' and "
                +"FederalTaxId like '"+obj.getfederaltaxid()+"%' and "
                +"TaxonomyID like '"+obj.getTaxonomy()+"%' and "
                +"CLIA_NO like '"+obj.getCLIA()+"%' ";
            rs= stmt.executeQuery(SqlQuery);
            String[][] arr = new String[n][10];
            int k =0;
            while(rs.next())
            {
                arr[k][0] = rs.getString("Code");
                arr[k][1] = rs.getString("Name");
                arr[k][2] = rs.getString("Address2");
                arr[k][3] = rs.getString("Phone");
                arr[k][4] = rs.getString("Fax");
                arr[k][5] = rs.getString("FederalTaxId");
                arr[k][6] = rs.getString("NPI");
                arr[k][7] = rs.getString("Services");
                arr[k][8] = rs.getString("TaxonomyID");
                arr[k][9] = rs.getString("CLIA_NO");

                k++;
            }
            if(n==0){
                JOptionPane.showMessageDialog(null,"Record does not exist");
                return null;
            }else{return arr;}
      }        
      }catch(Exception gfx)
      {         
          gfx.printStackTrace();
          JOptionPane.showMessageDialog(null,"Record does not exist");
      }
      return null;    
    }    
}
