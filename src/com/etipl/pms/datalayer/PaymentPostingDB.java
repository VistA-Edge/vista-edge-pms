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
import com.etipl.pms.entity.PaymentPostingDef;
import com.etipl.pms.utilities.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PaymentPostingDB { 
    JFrame frame = new JFrame();
    public static String[] Reciept_ID =  new String[50];
    public static String[] ID =  new String[50];
    public static int dv0=0;
    
    public String setReceiptDate(String date){
        String Day,Month,Year,Date;
        int j=date.length();
        
         Month=date.substring(5,7);
         Day=date.substring(8,10);
         Year=date.substring(0,4);
         Date=Month+"/"+Day+"/"+Year;

    return Date;
    }


public String setDate(String date){
    String Day,Month,Year,Date;
    int j=date.length();
     Month=date.substring(5,7);
     Day=date.substring(8,10);
     Year=date.substring(0,4);
     Date=Month+"/"+Day+"/"+Year;
   
    return Date;
    }

public String currentDate() throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        DBConnection db=new DBConnection();
        con= db.getPMSConnection();
        String n="";
        try{            
            stmt =con.createStatement();
            SqlQuery="select date(now()) as DATE; ";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getString("DATE");

            return n;
            }
            catch(Exception gfx){ gfx.printStackTrace();
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

public String patientName(String hrn) throws SQLException{
        Connection con = null;
        DBConnection db=new DBConnection();
        Statement stmt1 = null;
        String SqlQuery1;
        ResultSet rs1=null;
        try{
        con= db.getPMSConnection();
        stmt1 =con.createStatement();
        SqlQuery1="select * from JP_PAT_INFO where HRN ='"+hrn+"'; ";
        rs1= stmt1.executeQuery(SqlQuery1);
        String name ="";
        while(rs1.next()){
        name=rs1.getString("FNAME")+" "+rs1.getString("LNAME")+" "+rs1.getString("MNAME");
        }
        return name;
      }
      catch(Exception gfx)
      {
         gfx.printStackTrace();
         return null;
      }finally{
            if(rs1!=null){
                rs1.close();
            }
            if(stmt1!=null){
                stmt1.close();
            }
            if(con!=null){
                con.close();
            }
        }
}

public String inscmpName(String cmp_id) throws SQLException{
        Connection con = null;
        DBConnection db=new DBConnection();
        Statement stmt1 = null;
        String SqlQuery1;
        ResultSet rs1=null;
        String name="";
        try{
        con= db.getPMSConnection();
        stmt1 =con.createStatement();
        SqlQuery1="select * from INSURANCE_COMPANY_DETAILS where Company_Id = '"+cmp_id+"';";
        rs1= stmt1.executeQuery(SqlQuery1);

        while(rs1.next()){        
            name=rs1.getString("Company_Name");
        }
        return name;
      }
      catch(Exception gfx)
      {
         gfx.printStackTrace();
         return null;
      }finally{
            if(rs1!=null){
                rs1.close();
            }
            if(stmt1!=null){
                stmt1.close();
            }
            if(con!=null){
                con.close();
            }
        }
}

public List<PaymentPostingDef> getInsuranceDetails() throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        List<PaymentPostingDef> obj=new ArrayList<PaymentPostingDef>();
        DBConnection db=new DBConnection();
        try{           
        con= db.getPMSConnection();
        stmt =con.createStatement();
        SqlQuery="select * from INSURANCE_COMPANY_DETAILS";
        rs= stmt.executeQuery(SqlQuery);
        
        while(rs.next())
        {
            PaymentPostingDef ppd=new PaymentPostingDef();
            String insurancename=rs.getString("Company_Id")+"-"+rs.getString("Company_Name");
            ppd.setInsurance(insurancename);
            obj.add(ppd);
        }
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
            if(con!=null){
                con.close();
            }
        }
    }

public PaymentPostingDef getInsuranceCompDetails(String name) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        
        PaymentPostingDef obj=new PaymentPostingDef();
        DBConnection db=new DBConnection();
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from INSURANCE_COMPANY_DETAILS where Company_Id ='"+name+"';";
            rs= stmt.executeQuery(SqlQuery);
             while(rs.next())
            {
                obj.setPaymentCode(rs.getString("Payment"));
                obj.setAdjCode(rs.getString("Adjust"));
                obj.setWithHoldCode(rs.getString("WithHold"));
                obj.setDeductibleCode(rs.getString("Deductible"));
                obj.setTakeBackCode(rs.getString("TakeBack"));
                obj.setAddress1(rs.getString("Address1"));
                obj.setAddress2(rs.getString("Address2"));
                obj.setCity(rs.getString("City"));
                obj.setState(rs.getString("State"));
                obj.setZip(rs.getString("Zip"));
                obj.setPhone(rs.getString("Phone"));                
            }
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
            if(con!=null){
                con.close();
            }
        }
    }

public PaymentPostingDef searchHRN(String HRN) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        PaymentPostingDef obj=new PaymentPostingDef();
        DBConnection db=new DBConnection();
try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from JP_PAT_INFO where HRN ='"+HRN+"';";
            rs= stmt.executeQuery(SqlQuery);
             while(rs.next())
            {
                obj.setPatientName(rs.getString("FNAME")+" "+rs.getString("LNAME"));
                obj.setPatientSex(rs.getString("SEX"));
                obj.setPatientDOB(rs.getString("DOB"));
            }
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
            if(con!=null){
                con.close();
            }
        }
    }

public List<PaymentPostingDef> getCodesDetails(String type) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        List<PaymentPostingDef> obj=new ArrayList<PaymentPostingDef>();
        DBConnection db=new DBConnection();
        try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        SqlQuery="select * from MASTER_TYPES where TypeCode='"+type+"'; ";
        rs= stmt.executeQuery(SqlQuery);
        rs.first();
        do{
            PaymentPostingDef ppd=new PaymentPostingDef();
            String Codetype=rs.getString("Code")+"-"+rs.getString("Description");
            if(type.compareTo("Pay")==0){
                ppd.setPaymentCode(Codetype);
                obj.add(ppd);
            }else{
                if(type.compareTo("Adj")==0){
                    ppd.setAdjCode(Codetype);
                    obj.add(ppd);
                }else{
                    if(type.compareTo("Withh")==0){
                        ppd.setWithHoldCode(Codetype);
                        obj.add(ppd);
                    }else{
                        if(type.compareTo("TakeB")==0){
                            ppd.setTakeBackCode(Codetype);
                            obj.add(ppd);
                        }else{
                            if(type.compareTo("Ded")==0){
                                 ppd.setDeductibleCode(Codetype);
                                 obj.add(ppd);
                            }else{
                                if(type.compareTo("CoPay")==0){
                                    ppd.setCoPayCode(Codetype);
                                    obj.add(ppd);
                                }
                            }
                        }
                    }
                }
            }
        }while(rs.next());
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
            if(con!=null){
                con.close();
            }
        }
    }

public String assignReceiptNo(PaymentPostingDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        DBConnection db=new DBConnection();
        String n="";
   try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select count(*) from RECIEPT a, PAYOR_RECIEPT b where a.ID=b.RECIEPT_ID and a.RECIEPT_DATE= '"+obj.getReceiptDate()+"';";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getString("count(*)");

            return n;
            }
        catch(Exception gfx){  
            gfx.printStackTrace();
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
        return null;
    }

public void addReceipt(PaymentPostingDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;

        DBConnection db=new DBConnection();
try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        String SqlQuery1="insert into RECIEPT(RECIEPT_NO,RECIEPT_DATE,PAYOR_TYPE,PAYMENT_MODE,CHECK_NO,DESCRIPTION,PAYMENT_AMT,RECEIPT_CODE,UNPOSTED_AMT) " +
                "values ('"+obj.getReceiptNo()+"','"+obj.getReceiptDate()+"','"+obj.getPayorType()+"','"
                +obj.getPaymentMode()+"','"+obj.getCheckNo()+"','"+obj.getDescription()+"','"
                +obj.getPaymentAmt()+"','"+obj.getReceiptCode()+"','"+obj.getPaymentAmt()+"')";

        stmt.executeUpdate(SqlQuery1);
      }
      catch(Exception gfx)
      {
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

public void addPayor_Receipt(PaymentPostingDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        DBConnection db=new DBConnection();
        try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        SqlQuery="select MAX(ID)as dif from RECIEPT ";
        rs=stmt.executeQuery(SqlQuery);
        while(rs.next()){
              dv0=rs.getInt("dif");
       }
        
        String SqlQuery1="insert into PAYOR_RECIEPT(PAYOR,PAYOR_ID,PAYMENT_CODE,ADJUSTMENT_CODE,COPAY_CODE,WITHHOLD_CODE,DEDUCTIBLE_CODE,TAKEBACK_CODE,RECIEPT_ID) " +
                "values ('"+obj.getPayerName()+"','"+obj.getPayorId()+"','"+obj.getPaymentCode()+"','"+obj.getAdjCode()+"','"+obj.getCoPayCode()+"','"
                +obj.getWithHoldCode()+"','"+obj.getDeductibleCode()+"','"+obj.getTakeBackCode()+"','"+dv0+"')";

        stmt.executeUpdate(SqlQuery1);
      }
      catch(Exception gfx)
      {
         gfx.printStackTrace();
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

public String[][] searchPaymentPosting(PaymentPostingDef obj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        DBConnection db=new DBConnection();
        int n,k=0;
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select count(*) from RECIEPT a, PAYOR_RECIEPT b where a.ID=b.RECIEPT_ID and a.RECIEPT_DATE >= '"+obj.getReceiptDate()+"' and a.RECIEPT_DATE <= '"+obj.getReceiptDate1()+"'";
            if(obj.getPayorType().compareTo("Select")==0){
                SqlQuery = SqlQuery+" ;";
            }else{
                SqlQuery = SqlQuery+" and a.PAYOR_TYPE = '"+obj.getPayorType()+"' ;";
            }
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getInt("count(*)");
            stmt.close();
            rs.close();

            stmt =con.createStatement();
            String[][] arr = new String[n][8];
            SqlQuery="select * from RECIEPT a, PAYOR_RECIEPT b where a.ID=b.RECIEPT_ID and a.RECIEPT_DATE >= '"+obj.getReceiptDate()+"' and a.RECIEPT_DATE <= '"+obj.getReceiptDate1()+"'";
            if(obj.getPayorType().compareTo("Select")==0){
                SqlQuery=SqlQuery+" ;";
            }else{
                SqlQuery=SqlQuery+" and a.PAYOR_TYPE = '"+obj.getPayorType()+"' ;";
            }
            rs= stmt.executeQuery(SqlQuery);
         
            while(rs.next())
            {
              arr[k][0] = ""+(k+1);
              arr[k][1] = setReceiptDate(rs.getString("RECIEPT_DATE"));
              arr[k][2] = rs.getString("RECIEPT_NO");
              arr[k][3] = rs.getString("DESCRIPTION");
              if(rs.getString("PAYOR").compareTo("P")==0){
                   arr[k][4] = patientName(rs.getString("PAYOR_ID"));
              }else{
                  arr[k][4] = inscmpName(rs.getString("PAYOR_ID"));
              }
              arr[k][5] = rs.getString("PAYOR_TYPE");
              arr[k][6] = rs.getString("PAYMENT_AMT");
              arr[k][7] = rs.getString("UNPOSTED_AMT");
              Reciept_ID[k]=rs.getString("RECIEPT_ID");
              k++;
            }
            if(n!=0)
            {
                return arr;
            }else{
                JOptionPane.showMessageDialog(frame, "Data doesnt exist on this Date");
                return null;
            }
        }
        catch(Exception gfx)
        { 
            gfx.printStackTrace();
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
        return null;
    }

public PaymentPostingDef showSelectedRowData(String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        PaymentPostingDef obj=new PaymentPostingDef();
        DBConnection db=new DBConnection();
        try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        SqlQuery="select * from RECIEPT a, PAYOR_RECIEPT b where a.ID=b.RECIEPT_ID and b.RECIEPT_ID= '"+Rid+"';";
        
        rs= stmt.executeQuery(SqlQuery);
        while(rs.next()){
            obj.setPayorType(rs.getString("PAYOR_TYPE"));
            obj.setPaymentMode(rs.getString("PAYMENT_MODE"));
            obj.setCheckNo(rs.getString("CHECK_NO"));
            obj.setDescription(rs.getString("DESCRIPTION"));
            obj.setPaymentAmt(rs.getString("PAYMENT_AMT"));
            obj.setReceiptCode(rs.getString("RECEIPT_CODE"));
            obj.setReceiptNo(rs.getString("RECIEPT_NO"));
            obj.setPayorId(rs.getString("PAYOR_ID"));
            
            if(rs.getString("PAYOR").compareTo("I")==0){
                obj.setInsurance(rs.getString("PAYOR_ID"));
                obj.setPaymentCode(rs.getString("PAYMENT_CODE"));
                obj.setAdjCode(rs.getString("ADJUSTMENT_CODE"));
                obj.setWithHoldCode(rs.getString("WITHHOLD_CODE"));
                obj.setDeductibleCode(rs.getString("DEDUCTIBLE_CODE"));
                obj.setTakeBackCode(rs.getString("TAKEBACK_CODE"));
            }else{
                if(rs.getString("PAYOR").compareTo("P")==0){
                    obj.setPayerName(rs.getString("PAYOR_ID"));
                    obj.setPaymentCode(rs.getString("PAYMENT_CODE"));
                    obj.setAdjCode(rs.getString("ADJUSTMENT_CODE"));
                    obj.setCoPayCode(rs.getString("COPAY_CODE"));
                }else{
                    if(rs.getString("PAYOR").compareTo("C")==0){
                        obj.setInsurance(rs.getString("PAYOR_ID"));
                    }
                }
            }
        }
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
            if(con!=null){
                con.close();
            }
        }
    }

public PaymentPostingDef deleteSelectedData(String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String SqlQuery = "";
        
        PaymentPostingDef obj=new PaymentPostingDef();
        DBConnection db=new DBConnection();
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="delete from RECIEPT where ID= '"+Rid+"'";
            stmt.executeUpdate(SqlQuery);
            String SqlQuery1="delete from PAYOR_RECIEPT where RECIEPT_ID= '"+Rid+"'";
            stmt.executeUpdate(SqlQuery1);
            JOptionPane.showMessageDialog(frame, "Data Deleted");

            return obj;
      }
      catch(Exception gfx)
      {
         gfx.printStackTrace();
         return null;
      }
        finally{
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }

public void updateReciept(PaymentPostingDef obj,String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        
        DBConnection db=new DBConnection();
try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        String updateQuery="update RECIEPT set RECIEPT_NO= '"+obj.getReceiptNo()+"' ,PAYOR_TYPE= '"+obj.getPayorType()+"' ,PAYMENT_MODE= '"+obj.getPaymentMode()+"' ,CHECK_NO= '"+obj.getCheckNo()+"' ,DESCRIPTION= '"+obj.getDescription()+"' ,PAYMENT_AMT= '"+obj.getPaymentAmt()+"' ,RECEIPT_CODE= '"+obj.getReceiptCode()+"' ,UNPOSTED_AMT= '"+obj.getPaymentAmt()+"' where ID= '"+Rid+"'; ";
        stmt.executeUpdate(updateQuery);
      }
      catch(Exception gfx)
      {
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

public void updatePayorReciept(PaymentPostingDef obj,String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        
        DBConnection db=new DBConnection();
try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
       String updateQuery1="update PAYOR_RECIEPT set PAYOR= '"+obj.getPayerName()+"' ,PAYOR_ID= '"+obj.getPayorId()+"' ,PAYMENT_CODE= '"+obj.getPaymentCode()+"' ,ADJUSTMENT_CODE= '"+obj.getAdjCode()+"' ,COPAY_CODE= '"+obj.getCoPayCode()+"' ,WITHHOLD_CODE= '"+obj.getWithHoldCode()+"' ,DEDUCTIBLE_CODE= '"+obj.getDeductibleCode()+"' ,TAKEBACK_CODE= '"+obj.getTakeBackCode()+"' where RECIEPT_ID= '"+Rid+"'; ";
       stmt.executeUpdate(updateQuery1);
      }
      catch(Exception gfx)
      {
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

public PaymentPostingDef getGurantorInfo(String crt,String name) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        
        PaymentPostingDef obj=new PaymentPostingDef();
        DBConnection db=new DBConnection();
        try{
            String ress="";
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from CMS_HCFA1500 where "+crt+" = '"+name+"';";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                obj.setGurantorname(rs.getString("GurantorName"));
                obj.setGurantorSex(rs.getString("GurantorSex"));
                obj.setGurantorDOB(rs.getString("GurantorDOB"));
                obj.setRelation(rs.getString("Relation"));
               ress=rs.getString("GurantorName");
            }

            if(ress.compareTo("")==0){
                JOptionPane.showMessageDialog(frame, "NO Gurantor for this Payer");
            }else{  return obj;   }

            return null;
      }
      catch(Exception gfx)
      {
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


public List<PaymentPostingDef> getcmshcfa1500(String crt,String name) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        List<PaymentPostingDef> obj=new ArrayList<PaymentPostingDef>();
        DBConnection db=new DBConnection();
       try{
            String ress="";
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from CMS_HCFA1500 where "+crt+" = '"+name+"';";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                PaymentPostingDef ppd=new PaymentPostingDef();
                ppd.setGurantorname(rs.getString("GurantorName"));
                ppd.setGurantorSex(rs.getString("GurantorSex"));
                ppd.setGurantorDOB(rs.getString("GurantorDOB"));

                ppd.setClaimId(rs.getString("CLAIMID"));
                obj.add(ppd);
               ress=rs.getString("CLAIMID");
            }

            if(ress.compareTo("")==0){
                JOptionPane.showMessageDialog(frame, "NO Claims EXISTS ");
            }else{  return obj;   }

            return null;
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
            if(con!=null){
                con.close();
            }
        }
    }

public PaymentPostingDef getHRNcmshcfa1500(String name) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        
        PaymentPostingDef obj=new PaymentPostingDef();
        DBConnection db=new DBConnection();
       try{
            String ress="";
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from CMS_HCFA1500 where CLAIMID= '"+name+"';";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                obj.setPayorId(rs.getString("HRN"));
                obj.setPatientName(rs.getString("PName"));
                obj.setPatientSex(rs.getString("PSex"));
                obj.setPatientDOB(rs.getString("PDOB"));
                obj.setGurantorname(rs.getString("GurantorName"));
                obj.setGurantorSex(rs.getString("GurantorSex"));
                obj.setGurantorDOB(rs.getString("GurantorDOB"));
                obj.setClaimId(rs.getString("CLAIMID"));                
                ress=rs.getString("CLAIMID");
            }
            if(ress.compareTo("")==0){
                JOptionPane.showMessageDialog(frame, "NO Patient EXISTS for this Claim");
            }else{  return obj;   }

            return null;
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
            if(con!=null){
                con.close();
            }
        }
    }

public Object[][] getCmsCpt(PaymentPostingDef obj,int flag) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        
        DBConnection db=new DBConnection();
        con= db.getPMSConnection();
       try{
            int n=0,k=0;
            double dd=5.0;
            String ress="";
            stmt =con.createStatement();

            SqlQuery="select count(*) from CMS_CPTS where CLAIMID= '"+obj.getClaimId()+"' and CPT<> '';";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n=rs.getInt("count(*)");
            stmt.close();
            rs.close();

            stmt =con.createStatement();
            Object[][] arr = new Object[n][14];
            SqlQuery="select * from CMS_CPTS where CLAIMID = '"+obj.getClaimId()+"' and CPT<> '' order by CPT ;";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                ress=rs.getString("CLAIMID");
                arr[k][0] = ""+(k+1);
                arr[k][1] = setDate(rs.getString("ServiceFromDate"));
                arr[k][2] = rs.getString("CPT");
                arr[k][3] = rs.getString("Charges");
                String prr=rs.getString("ProviderID");
                if(setalance(obj.getClaimId(),rs.getString("CPT")).isEmpty()){
                        arr[k][4] = rs.getString("Charges");
                }else{
                    arr[k][4] = setalance(obj.getClaimId(),rs.getString("CPT"));
                }

                arr[k][5] = "";
                arr[k][6] = "";
                arr[k][7] = "";
                arr[k][8] = "";
                arr[k][9] = "";
                arr[k][10] = "";
                arr[k][11] = "";
                arr[k][12] = "";
                arr[k][13] = obj.getReceiptDate();
                k++;
            }
            if(ress.compareTo("")==0){
                JOptionPane.showMessageDialog(frame, "NO Claims EXISTS for this Patient");
            }else{
                rs.close();
                stmt.close();
                return arr;
            }
            return null;
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
            if(con!=null){
                con.close();
            }
        }
    }

public double checkPaid(String claim) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String SqlQuery = "";
        ResultSet rs1 = null;
        
        DBConnection db=new DBConnection();
        con= db.getPMSConnection();        
     try{
        
        stmt =con.createStatement();
       
       SqlQuery="select p.CHARGES,SUM(PAYMENT),p.ALLOWED,ADJUSTMENT from PAYMENT p,CMS_CPTS c where c.CLAIMID=p.claim_ID and c.cpt=p.cpt and p.claim_id='"+claim+"' group by c.CLAIMID  ";
       
       rs1= stmt.executeQuery(SqlQuery);
       rs1.next();
       String paym,adjm,chr,balance;
       paym=rs1.getString("SUM(PAYMENT)");
       adjm=rs1.getString("ADJUSTMENT");
       chr=rs1.getString("p.CHARGES");

       return setBalance(chr,paym,adjm);
      }
      catch(Exception gfx){
         gfx.printStackTrace();
         return 1;
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

public String setalance(String adj,String bdj) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        String SqlQuery = "";
        ResultSet rs1 = null;
        String paym,adjm,chr,balance="";

        DBConnection db=new DBConnection();
        con= db.getPMSConnection();
 try{
        stmt =con.createStatement();
        
       SqlQuery="select SUM(PAYMENT), ADJUSTMENT ,CHARGES from PAYMENT where CLAIM_ID = '"+adj+"' and CPT='"+bdj+"' group by CPT ";
       rs1= stmt.executeQuery(SqlQuery);
       while(rs1.next()){           
           paym=rs1.getString("SUM(PAYMENT)");
           adjm=rs1.getString("ADJUSTMENT");
           chr=rs1.getString("CHARGES");
           balance=setBalance(chr,paym,adjm).toString();           
         }
       return balance;
      }
      catch(Exception gfx){
         gfx.printStackTrace();
         return null;
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


public Object[][] getCmsCpt2(PaymentPostingDef obj,int flag) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        
        DBConnection db=new DBConnection();
        con= db.getPMSConnection();
try{
            int n=0,n1=0,n2=0,k=0;
            double dd=5.0,bal=0.0;
            String ress1="",ress="";
            stmt =con.createStatement();
            SqlQuery="select count(*) from PAYMENT where CLAIM_ID= '"+obj.getClaimId()+"' and RECIEPT_ID='"+obj.getReceiptId()+"';";
           
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            n1=rs.getInt("count(*)");
            System.out.println(n1);
            n2=n1;
            if(n2!=0){

                Object[][] arr = new Object[n2][14];
            try
            {
            SqlQuery="select distinct c.ServiceFromDate from PAYMENT p,CMS_CPTS c where c.CLAIMID = p.claim_ID and c.cpt=p.cpt and p.claim_id='"+obj.getClaimId()+"' ;";
            rs= stmt.executeQuery(SqlQuery);
            rs.next();
            String ff="";
            ff=rs.getString("ServiceFromDate");

            SqlQuery="select * from PAYMENT where CLAIM_ID = '"+obj.getClaimId()+"' and RECIEPT_ID='"+obj.getReceiptId()+"' order by CPT ;";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                arr[k][0] = ""+(k+1);
                arr[k][1] = setDate(ff);
                arr[k][2] = rs.getString("CPT");
                arr[k][3] = rs.getString("Charges");
                arr[k][4]=rs.getString("BALANCE");
                arr[k][5] = rs.getString("PAYMENT");
                arr[k][6] = "";
                arr[k][7] = "";
                arr[k][8] = rs.getString("ALLOWED");
                arr[k][9] = rs.getString("ADJUSTMENT");
                arr[k][10] = "";
                arr[k][11] = "";
                arr[k][12] = "";
                arr[k][13] = obj.getReceiptDate();
                ID[k]=rs.getString("ID");
                k++;

               ress=rs.getString("CLAIM_ID");
             }
            }catch(Exception e){e.printStackTrace();}
            if(ress.compareTo("")==0){
                JOptionPane.showMessageDialog(frame, "NO Claims EXISTS for this Patient ");
            }else{
                return arr;
            }
            return null;

        }else{
             JOptionPane.showMessageDialog(frame, " NO Payment EXISTS ");
             return null;
        }
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
            if(con!=null){
                con.close();
            }
        }
    }

 public Double setBalance(String charges,String payment,String adj){
       double Balance=0.0,Payment=0.0,Charges=0.0,Adjust=0.0;
       Charges=Double.valueOf(charges).doubleValue();
       if(payment.compareTo("")!=0){
       Payment=Double.valueOf(payment).doubleValue();
       }
       if(adj.compareTo("")!=0){
       Adjust=Double.valueOf(adj).doubleValue();
       }
       Balance=Charges-Payment-Adjust;
     return Balance;
    }

public void addPosting(PaymentPostingDef obj,String unposted_Amt,String receiptNo) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";

        DBConnection db=new DBConnection();
 try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        String status="";
        double s=0.0;
        s=setBalance( obj.getCharges(), obj.getPaymentCode(),obj.getAdjCode());
        if(s==0.0){
            status="C";
        }else{
            status="R";
        }
        String SqlQuery1="insert into PAYMENT(RECIEPT_ID,CLAIM_ID,HRN,CPT,CHARGES,BALANCE,PAYMENT,DEDUCTIBLE,WITHHOLD,ALLOWED,ADJUSTMENT,TAKEBACK,PROVIDER,STATUS) " +
                "values ('"+obj.getReceiptNo()+"','"+obj.getClaimId()+"','"+obj.getPayorId()+"','"
                +obj.getProc()+"','"+obj.getCharges()+"','"+obj.getBalance()+"','"+obj.getPaymentCode()+"','"
                +obj.getDeductibleCode()+"','"+obj.getWithHoldCode()+"','"+obj.getAllowedCode()+"','"
                +obj.getAdjCode()+"','"+obj.getTakeBackCode()+"','"+obj.getProvider()+"','"+status+"')";
        
        stmt.executeUpdate(SqlQuery1);
        String sqlUpdate="UPDATE RECIEPT set UNPOSTED_AMT='"+unposted_Amt+"'"+" where RECIEPT_NO='"+receiptNo+"'";
        stmt.executeUpdate(sqlUpdate);
        String updateQuery1="update CMS_CPTS set STATUS = 'P' where CLAIMID='"+obj.getClaimId()+"' and CPT='"+obj.getProc()+"' ; ";
        stmt.executeUpdate(updateQuery1);
      }
      catch(Exception gfx){         
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

public void editPosting(PaymentPostingDef obj,String unposted_Amt,String receiptNo) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        DBConnection db=new DBConnection();
try{
        con= db.getPMSConnection();
        stmt =con.createStatement();
        String updateQuery1="update PAYMENT set BALANCE= '"+obj.getBalance()+"' ,PAYMENT= '"+obj.getPaymentCode()+"' ,DEDUCTIBLE = '"+obj.getDeductibleCode()+"' ,WITHHOLD = '"+obj.getWithHoldCode()+"' ,ALLOWED= '"+obj.getAllowedCode()+"' ,ADJUSTMENT= '"+obj.getAdjCode()+"' ,TAKEBACK= '"+obj.getTakeBackCode()+"' ,PROVIDER= '"+obj.getProvider()+"' where CLAIM_ID= '"+obj.getClaimId()+"' and CPT= '"+obj.getProc()+"' and ID= '"+obj.getid()+"'; ";
        stmt.executeUpdate(updateQuery1);
        String sqlUpdate="UPDATE RECIEPT set UNPOSTED_AMT='"+unposted_Amt+"'"+" where RECIEPT_NO='"+receiptNo+"'";
        stmt.executeUpdate(sqlUpdate);
      }
      catch(Exception gfx){
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

public String deletePosting(String cpt,String Rid) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SqlQuery = "";
        
        DBConnection db=new DBConnection();
        String a="";
        try{
            con= db.getPMSConnection();
            stmt =con.createStatement();
            SqlQuery="select * from PAYMENT where ID = '"+Rid+"' ;";
            rs= stmt.executeQuery(SqlQuery);

            while(rs.next()){
                a=rs.getString("PAYMENT");                               
            }
            SqlQuery="delete from PAYMENT where ID = '"+Rid+"' ;";
            stmt.executeUpdate(SqlQuery);
            JOptionPane.showMessageDialog(frame,"Deleted Successfully");
           
            return a;
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

public void updatingReceiptAfPosting(String unposted_Amt,String receiptNo) throws SQLException{
        Connection con = null;
        Statement stmt = null;
        
        DBConnection db=new DBConnection();
        con= db.getPMSConnection();
try{            
        stmt =con.createStatement();
        String sqlUpdate="UPDATE RECIEPT set UNPOSTED_AMT='"+unposted_Amt+"'"+" where RECIEPT_NO='"+receiptNo+"'";
        stmt.executeUpdate(sqlUpdate);
        }catch(Exception gfx){
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
}
