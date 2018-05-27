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


import com.etipl.pms.entity.CodeDes;
import com.etipl.pms.entity.InsuranceCompany;
import com.etipl.pms.utilities.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InsuranceCompanyMethods {

   public int checkDuplicateCode(String code) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs =null;
            String sql;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="select * from INSURANCE_COMPANY_DETAILS where Company_Id='"+code+"'";
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

public List<CodeDes> loadInsuranceType() throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs =null;
        String sql;
        int count = 0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from MASTER_TYPES where typecode='INSTYP' order by code asc";
        
        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
            List<CodeDes> lstCD=new ArrayList<CodeDes>();
            do{
                CodeDes cd=new CodeDes();
                cd.setCode(rs.getString("Description")+"-"+rs.getString("Code"));
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


   public List<InsuranceCompany> loadCompany(InsuranceCompany insCom) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs =null;
        String sql;
        int count = 0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
   try{
        stmt=con.createStatement();
        sql="select * from INSURANCE_COMPANY_DETAILS where 1=1";

        if(insCom.getCode()!=null && !insCom.getCode().isEmpty()){
            sql= sql+" and Company_Id like '"+insCom.getCode()+"%'";
        }
        if(insCom.getType()!=null && !insCom.getType().isEmpty()){
            sql= sql+" and INSURANCE_CLASS like '"+insCom.getType()+"%'";
        }
        if(insCom.getName()!=null && !insCom.getName().isEmpty()){
            sql= sql+" and Company_Name like '"+insCom.getName()+"%'";
        }
        if(insCom.getAddress()!=null && !insCom.getAddress().isEmpty()){
            sql= sql+" and Address2 like '"+insCom.getAddress()+"%'";
        }
        if(insCom.getCity()!=null && !insCom.getCity().isEmpty()){
            sql= sql+" and City like '"+insCom.getCity()+"%'";
        }
        if(insCom.getState()!=null && !insCom.getState().isEmpty()){
            sql= sql+" and State like '"+insCom.getState()+"%'";
        }
        if(insCom.getCZip()!=null && !insCom.getCZip().isEmpty()){
            sql= sql+" and Zip like '"+insCom.getCZip()+"%'";
        }
        if(insCom.getFax()!=null && !insCom.getFax().isEmpty()){
            sql= sql+" and Fax like '"+insCom.getFax()+"%'";
        }
        if(insCom.getPhone()!=null && !insCom.getPhone().isEmpty()){
            sql= sql+" and Phone like '"+insCom.getPhone()+"%'";
        }
        if(insCom.getContPerson()!=null && !insCom.getContPerson().isEmpty()){
            sql= sql+" and Contact_Person_Name like '"+insCom.getContPerson()+"%'";
        }

        if(insCom.getPaymentCode()!=null && !insCom.getPaymentCode().isEmpty()){
            sql= sql+" and Payment like '"+insCom.getPaymentCode()+"%'";
        }

        if(insCom.getAdjustment()!=null && !insCom.getAdjustment().isEmpty()){
            sql= sql+" and Adjust like '"+insCom.getAdjustment()+"%'";
        }
        if(insCom.getDeductible()!=null && !insCom.getDeductible().isEmpty()){
            sql= sql+" and Deductible like '"+insCom.getDeductible()+"%'";
        }
        if(insCom.getTakeBack()!=null && !insCom.getTakeBack().isEmpty()){
            sql= sql+" and TakeBack like '"+insCom.getTakeBack()+"%'";
        }
        if(insCom.getWithHold()!=null && !insCom.getWithHold().isEmpty()){
            sql= sql+" and WithHold like '"+insCom.getWithHold()+"%'";
        }

        rs= stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }
            rs.first();
            if(count>0){
                List<InsuranceCompany> lstIC=new ArrayList<InsuranceCompany>();
                do{
                    InsuranceCompany ic=new InsuranceCompany();
                    ic.setCode(rs.getString("Company_Id"));
                    ic.setType(rs.getString("INSURANCE_CLASS"));
                    ic.setName(rs.getString("Company_Name"));
                    ic.setPhone(rs.getString("Phone"));
                    ic.setddress(rs.getString("Address2"));
                    ic.setFax(rs.getString("Fax"));
                    ic.setZip(rs.getString("Zip"));
                    ic.setCity(rs.getString("City"));
                    ic.setState(rs.getString("State"));
                    ic.setContPerson(rs.getString("Contact_Person_Name"));
                    ic.setPaymentCode(rs.getString("Payment"));
                    ic.setAdjustment(rs.getString("Adjust"));
                    ic.setDeductible(rs.getString("Deductible"));
                    ic.setTakeBack(rs.getString("TakeBack"));
                    ic.setWithHold(rs.getString("WithHold"));
                    
                    lstIC.add(ic);
                }while(rs.next());
                    return lstIC;
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


   public int deleteInsurance(String code) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            String sql;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="Delete from INSURANCE_COMPANY_DETAILS where Company_Id='"+code+"'";
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

    public int saveInsurance(InsuranceCompany ic) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String sql;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
                stmt= con.createStatement();

                sql = "Insert into INSURANCE_COMPANY_DETAILS(Company_Id,INSURANCE_CLASS,Company_Name";
                sql =sql+",Phone,Address2,Fax,Zip,City,State,Contact_Person_Name";
                sql=sql+",Payment,Adjust,Deductible,TakeBack,WithHold) values('";

                if(ic.getCode()!=null)
                sql= sql+ic.getCode() +"'";
                else sql=sql+",Null";

                if(ic.getType()!=null)
                sql= sql+",'"+ic.getType()+"'";
                else sql=sql+",Null";

                if(ic.getName()!=null)
                sql= sql+",'"+ic.getName()+"'";
                else sql=sql+",Null";

                if(ic.getPhone()!=null)
                sql= sql+",'"+ic.getPhone()+"'";
                else sql=sql+",Null";

                if(ic.getAddress()!=null)
                sql= sql+",'"+ic.getAddress()+"'";
                else sql=sql+",Null";

                if(ic.getFax()!=null)
                sql= sql+",'"+ic.getFax()+"'";
                else sql=sql+",Null";

                if(ic.getCZip()!=null)
                sql= sql+",'"+ic.getCZip()+"'";
                else sql=sql+",Null";

                if(ic.getCity()!=null)
                sql= sql+",'"+ic.getCity()+"'";
                else sql=sql+",Null";

                if(ic.getState()!=null)
                sql= sql+",'"+ic.getState()+"'";
                else sql=sql+",Null";

                if(ic.getContPerson()!=null)
                sql= sql+",'"+ic.getContPerson()+"'";
                else sql=sql+",Null";

                if(ic.getPaymentCode()!=null)
                sql= sql+",'"+ic.getPaymentCode()+"'";
                else sql=sql+",Null";

                if(ic.getAdjustment()!=null)
                sql= sql+",'"+ic.getAdjustment()+"'";
                else sql=sql+",Null";

                if(ic.getDeductible()!=null)
                sql= sql+",'"+ic.getDeductible()+"'";
                else sql=sql+",Null";

                if(ic.getTakeBack()!=null)
                sql= sql+",'"+ic.getTakeBack()+"'";
                else sql=sql+",Null";

                if(ic.getWithHold()!=null)
                sql= sql+",'"+ic.getWithHold()+"'";
                else sql=sql+",Null";

                sql=sql+")";
                
               stmt.executeUpdate(sql);
        return 1;
    }catch(Exception e){DB.closePMSConnection(con);e.printStackTrace(); return 0;}
        finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }


    public int updateInsurance(InsuranceCompany ic) throws SQLException{
            Connection con = null;
            Statement stmt = null;
            String sql;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
    try{
            stmt= con.createStatement();

            sql = "Update INSURANCE_COMPANY_DETAILS set ";
            if(ic.getCode()!=null)
            sql= sql+"Company_Id='"+ic.getCode()+"'";
            else sql=sql+",Company_Id=Null";

            if(ic.getType()!=null)
            sql=sql+",INSURANCE_CLASS='"+ic.getType()+"'";
            else sql=sql+",INSURANCE_CLASS=Null";

            if(ic.getName()!=null)
            sql= sql+",Company_Name='"+ic.getName()+"'";
            else sql=sql+",Company_Name=Null";

            if(ic.getPhone()!=null)
            sql= sql+",Phone='"+ic.getPhone()+"'";
            else sql=sql+",Phone=Null";

            if(ic.getAddress()!=null)
            sql= sql+",Address2='"+ic.getAddress()+"'";
            else sql=sql+",Address2=Null";

            if(ic.getFax()!=null)
            sql= sql+",Fax='"+ic.getFax()+"'";
            else sql=sql+",Fax=Null";

            if(ic.getCZip()!=null)
            sql= sql+",Zip='"+ic.getCZip()+"'";
            else sql=sql+",Zip=Null";

            if(ic.getCity()!=null)
            sql= sql+",City='"+ic.getCity()+"'";
            else sql=sql+",City=Null";
            
            if(ic.getState()!=null)
            sql= sql+",State='"+ic.getState()+"'";
            else sql=sql+",State=Null";

            if(ic.getContPerson()!=null)
            sql= sql+",Contact_Person_Name='"+ic.getContPerson()+"'";
            else sql=sql+",Contact_Person_Name=Null";

            if(ic.getPaymentCode()!=null)
            sql= sql+",Payment='"+ic.getPaymentCode()+"'";
            else sql=sql+",Payment=Null";

            if(ic.getAdjustment()!=null)
            sql= sql+",Adjust='"+ic.getAdjustment()+"'";
            else sql=sql+",Adjust=Null";

            if(ic.getDeductible()!=null)
            sql= sql+",Deductible='"+ic.getDeductible()+"'";
            else sql=sql+",Deductible=Null";

            if(ic.getTakeBack()!=null)
            sql= sql+",TakeBack='"+ic.getTakeBack()+"'";
            else sql=sql+",TakeBack=Null";

            if(ic.getWithHold()!=null)
            sql= sql+",WithHold='"+ic.getWithHold()+"'";
            else sql=sql+",WithHold=Null";

            sql=sql+" where Company_Id='"+ic.getCode()+"'";
            
            stmt.executeUpdate(sql);

            return 1;
    }catch(Exception e){DB.closePMSConnection(con);e.printStackTrace();return 0;}
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
