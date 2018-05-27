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

import com.etipl.pms.entity.Bill;
import com.etipl.pms.entity.BillAmt;
import com.etipl.pms.entity.CPT_ICD_LINK;
import com.etipl.pms.entity.ChargeCode;
import com.etipl.pms.utilities.DBConnection;
import com.etipl.pms.utilities.Utiles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BillMethods {  

public int saveBill_BillAmt(List<Bill> b,BillAmt billAmt) throws SQLException{
        Connection con;
        String sql;    
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        PreparedStatement insertBill=null;
        PreparedStatement insertBillAmt=null;

try{
            con.setAutoCommit(false);
            
            int billRecord=b.size();
            for(int i=0;i<billRecord;i++){
             Bill bill=new Bill();
             bill=b.get(i);
             sql=insertBill(bill,i);
             insertBill=con.prepareStatement(sql);
             insertBill.executeUpdate();
            }
            
            sql = "Insert into BILLAMT (BillNo,CaseID,BillDate,TotalCPTCount,TotalCPTUnits,TotalCharges";
            sql =sql+",TotalAdjAmt,TotalBalance,ICD1,ICD2,ICD3,ICD4) values('";

            sql= sql+ billAmt.getBillNo()+"'";
            sql= sql+",'"+billAmt.getCaseID()+"'";
            sql=sql+",'"+billAmt.getBillDate()+"'";
            if(billAmt.getTotalCPTCount()!=null)
            sql= sql+",'"+billAmt.getTotalCPTCount()+"'";
            else
            sql=sql+",Null";
            if(billAmt.getTotalCPTUnits()!=null)
            sql= sql+",'"+billAmt.getTotalCPTUnits()+"'";
            else
            sql=sql+",Null";
            sql= sql+",'"+billAmt.getTotalCharges()+"'";
            if(billAmt.getTotalAdjAmt()!=null)
            sql= sql+",'"+billAmt.getTotalAdjAmt()+"'";
            else
            sql=sql+",Null";
            sql= sql+",'"+billAmt.getTotalBalance()+"'";
            if(billAmt.getICD1()!=null)
            sql= sql+",'"+billAmt.getICD1()+"'";
            else
            sql=sql+",Null";
            if(billAmt.getICD2()!=null)
            sql= sql+",'"+billAmt.getICD2()+"'";
            else
            sql=sql+",Null";
            if(billAmt.getICD3()!=null)
            sql= sql+",'"+billAmt.getICD3()+"'";
            else
            sql=sql+",Null";
            if(billAmt.getICD4()!=null)
            sql= sql+",'"+billAmt.getICD4()+"'";
            else
            sql=sql+",Null";
            sql=sql+")";
            
            insertBillAmt=con.prepareStatement(sql);
            insertBillAmt.executeUpdate();
            con.commit();
            con.setAutoCommit(true);

   return 1;
   }catch(Exception e){e.printStackTrace(); return 0;}
   finally{
       if(insertBill!=null){
           insertBill.close();
       }
       if(insertBillAmt!=null){
           insertBillAmt.close();
       }
       if(con!=null){
           con.close();
       }       
   }
}

public String insertBill(Bill bill,int claimRows){    
                String sql;
    try{

                sql = "Insert into BILL (BillNo,DUMMYID,CaseID,CPT,CPTUnits,CPTRate,Charges,CoIns";
                sql =sql+",AdjCode,AdjAmt,Balance,ICD1,ICD2,ICD3,ICD4,ICDPointer,POS,ProviderID,ProviderTaxonomy,M1,M2) values('";

                sql= sql+ bill.getBillNo()+"'";
                sql= sql+",'"+claimRows+"'";

                sql= sql+",'"+bill.getCaseID()+"'";
                if(bill.getCPT()!=null)
                sql= sql+",'"+bill.getCPT()+"'";
                else
                sql=sql+",Null";
                if(bill.getCPT()!=null)
                sql= sql+",'"+bill.getCPTUnits()+"'";
                else
                sql=sql+",Null";
                if(bill.getCPTRate()!=null)
                sql= sql+",'"+bill.getCPTRate()+"'";
                else
                sql=sql+",Null";
                if(bill.getCharges()!=null)
                sql= sql+",'"+bill.getCharges()+"'";
                else
                sql=sql+",Null";

                if(bill.getCoIns()!=null)
                sql= sql+",'"+bill.getCoIns()+"'";
                else
                sql=sql+",Null";

                if(bill.getAdjCode()!=null)
                sql= sql+",'"+bill.getAdjCode()+"'";
                else
                sql=sql+",Null";

                if(bill.getAdjAmt()!=null)
                sql= sql+",'"+bill.getAdjAmt()+"'";
                else
                sql=sql+",Null";

                if(bill.getBalance()!=null)
                sql= sql+",'"+bill.getBalance()+"'";
                else
                sql=sql+",Null";

                if(bill.getICD1()!=null)
                sql= sql+",'"+bill.getICD1()+"'";
                else
                sql=sql+",Null";

                if(bill.getICD2()!=null)
                sql= sql+",'"+bill.getICD2()+"'";
                else
                sql=sql+",Null";

                if(bill.getICD3()!=null)
                sql= sql+",'"+bill.getICD3()+"'";
                else
                sql=sql+",Null";

                if(bill.getICD4()!=null)
                sql= sql+",'"+bill.getICD4()+"'";
                else
                sql=sql+",Null";

                if(bill.getICDPointer()!=null)
                sql= sql+",'"+bill.getICDPointer()+"'";
                else
                sql=sql+",Null";

                if(bill.getPOS()!=null)
                sql= sql+",'"+bill.getPOS()+"'";
                else
                sql=sql+",Null";

                String[] NPITAX=null;
                if(bill.getProviderId()!=null){
                     NPITAX=bill.getProviderId().split(";");

                    if(NPITAX.length>0){
                        sql= sql+",'"+NPITAX[0]+"'";
                    } else {sql=sql+",Null";
                           }
                }else sql=sql+",Null";

                if(bill.getProviderId()!=null){
                    if(NPITAX.length>1){
                        sql= sql+",'"+NPITAX[1]+"'";
                    }else { sql=sql+",Null";
                          }
                }else sql=sql+",Null";

                if(bill.getM1()!=null)
                sql= sql+",'"+bill.getM1()+"'";
                else
                sql=sql+",Null";

                if(bill.getM2()!=null)
                sql= sql+",'"+bill.getM2()+"'";
                else
                sql=sql+",Null";

                sql=sql+")";

        return sql;
    }catch(Exception e){e.printStackTrace(); return null;}
}

public int checkBillExists(String  billNo,String cpt) throws SQLException{
        Connection con;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
    try{
        stmt=con.createStatement();
        sql="select * from Bill where BillNo='"+billNo+"' and  CPT='"+cpt+"'";
        rs= stmt.executeQuery(sql);
        int count=0;
        while(rs.next()){
            count++;
        }
        if(count>0){
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

public int updateBill_BillAmt(List<Bill> b,BillAmt billAmt) throws SQLException{
        Connection con;
        String sql;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        PreparedStatement updateBill=null;
        PreparedStatement updateBillAmt=null;
 try{
     
        con.setAutoCommit(false);
        int billRecord=b.size();
        
        for(int i=0;i<billRecord;i++){
            
                Bill bill=new Bill();
                bill=b.get(i);
             
                sql = "UPDATE BILL set ";

                sql= sql+"BillNo='"+bill.getBillNo()+"'";
                sql= sql+",CaseID='"+bill.getCaseID()+"'";
                if(bill.getCPT()!=null)
                sql= sql+",CPT='"+bill.getCPT()+"'";
                else sql= sql+",CPT=Null";

                if(bill.getCPTUnits()!=null)
                sql= sql+",CPTUnits='"+bill.getCPTUnits()+"'";
                else sql= sql+",CPTUnits=Null";

                if(bill.getCPTRate()!=null)
                sql= sql+",CPTRate='"+bill.getCPTRate()+"'";
                else sql= sql+",CPTRate=Null";

                if(bill.getCharges()!=null)
                sql= sql+",Charges='"+bill.getCharges()+"'";
                else sql= sql+",Charges=Null";
                
                if(bill.getCoIns()!=null)
                sql= sql+",CoIns='"+bill.getCoIns()+"'";
                else sql=sql+",CoIns=Null";

                if(bill.getAdjCode()!=null)
                sql= sql+",AdjCode='"+bill.getAdjCode()+"'";
                else sql=sql+",AdjCode=Null";

                if(bill.getAdjAmt()!=null)
                sql= sql+",AdjAmt='"+bill.getAdjAmt()+"'";
                else sql=sql+",AdjAmt=Null";

                if(bill.getBalance()!=null)
                sql= sql+",Balance='"+bill.getBalance()+"'";
                else sql=sql+",Balance=Null";

                if(bill.getICD1()!=null)
                sql= sql+",ICD1='"+bill.getICD1()+"'";
                else sql=sql+",ICD1=Null";

                if(bill.getICD2()!=null)
                sql= sql+",ICD2='"+bill.getICD2()+"'";
                else sql=sql+",ICD2=Null";

                if(bill.getICD3()!=null)
                sql= sql+",ICD3='"+bill.getICD3()+"'";
                else sql=sql+",ICD3=Null";

                if(bill.getICD4()!=null)
                sql= sql+",ICD4='"+bill.getICD4()+"'";
                else sql=sql+",ICD4=Null";

                if(bill.getICDPointer()!=null)
                sql= sql+",ICDPointer='"+bill.getICDPointer()+"'";
                else sql=sql+",ICDPointer=Null";

                if(bill.getPOS()!=null)
                sql= sql+",POS='"+bill.getPOS()+"'";
                else sql=sql+",POS=Null";

                String[] NPITAX=null;

                if(bill.getProviderId()!=null){
                    NPITAX=bill.getProviderId().split(";");

                    if(NPITAX.length>0){
                        sql= sql+",ProviderID='"+NPITAX[0]+"'";
                    }else { sql=sql+",ProviderID=Null";
                          }
                }else sql=sql+",ProviderID=Null";


                if(bill.getProviderId()!=null){

                    if(NPITAX.length>1){
                        sql= sql+",ProviderTaxonomy='"+NPITAX[1]+"'";
                    }else { sql=sql+",ProviderTaxonomy=Null";
                          }
                }else sql=sql+",ProviderTaxonomy=Null";


                if(bill.getM1()!=null)
                sql= sql+",M1='"+bill.getM1()+"'";
                else sql=sql+",M1=Null";

                if(bill.getM2()!=null)
                sql= sql+",M2='"+bill.getM2()+"'";
                else sql=sql+",M2=Null";

                sql=sql+" where BillNo='"+bill.getBillNo()+"' and DUMMYID='"+i+"'";

                updateBill=con.prepareStatement(sql);
                updateBill.executeUpdate();           
        }

            sql = "Update BILLAMT set ";
           
            sql= sql+"CaseID='"+billAmt.getCaseID()+"'";

            sql=sql+",BillDate='"+billAmt.getBillDate()+"'";

            if(billAmt.getTotalCPTCount()!=null)
            sql= sql+",TotalCPTCount='"+billAmt.getTotalCPTCount()+"'";
            else
            sql=sql+",TotalCPTCount=Null";

            if(billAmt.getTotalCPTUnits()!=null)
            sql= sql+",TotalCPTUnits='"+billAmt.getTotalCPTUnits()+"'";
            else
            sql=sql+",TotalCPTUnits=Null";

            if(billAmt.getTotalCharges()!=null)
            sql= sql+",TotalCharges='"+billAmt.getTotalCharges()+"'";
            else
            sql=sql+",TotalCharges=Null";
            
            if(billAmt.getTotalAdjAmt()!=null)
            sql= sql+",TotalAdjAmt='"+billAmt.getTotalAdjAmt()+"'";
            else
            sql=sql+",TotalAdjAmt=Null";

            if(billAmt.getTotalBalance()!=null)
            sql= sql+",TotalBalance='"+billAmt.getTotalBalance()+"'";
            else
            sql=sql+",TotalBalance=Null";

            if(billAmt.getICD1()!=null)
            sql= sql+",ICD1='"+billAmt.getICD1()+"'";
            else
            sql=sql+",ICD1=Null";

            if(billAmt.getICD2()!=null)
            sql= sql+",ICD2='"+billAmt.getICD2()+"'";
            else
            sql=sql+",ICD2=Null";

            if(billAmt.getICD3()!=null)
            sql= sql+",ICD3='"+billAmt.getICD3()+"'";
            else
            sql=sql+",ICD3=Null";

            if(billAmt.getICD4()!=null)
            sql= sql+",ICD4='"+billAmt.getICD4()+"'";
            else
            sql=sql+",ICD4=Null";

            sql=sql+" where BillNo='"+billAmt.getBillNo()+"'";

            updateBillAmt=con.prepareStatement(sql);
            updateBillAmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
            
     return 1;
 }catch(Exception e){e.printStackTrace();return 0;}
 finally{
     
     if(updateBill!=null){
        updateBill.close();
     }
     if(updateBillAmt!=null){
         updateBillAmt.close();
     }
     if(con!=null){
        con.close();
     }
 }
}


public List<CPT_ICD_LINK> setIcds(String cptCode) throws SQLException {
        Connection con;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();  
try{
            
            stmt=con.createStatement();
            List<CPT_ICD_LINK> icds = new ArrayList<CPT_ICD_LINK>();

            sql="select * from CPT_ICD_LINK where 1=1";
            if(!cptCode.isEmpty()){
               sql=sql+" and ProcedureCode='"+cptCode+"'";
            }
            rs= stmt.executeQuery(sql);
            while(rs.next()){
                    count++;
            }
            rs.first();
            if(count>0){
            do{
                    CPT_ICD_LINK icd=new CPT_ICD_LINK();
                    icd.setProcdedureCode(rs.getString("ProcedureCode"));
                    icd.setDiagnosisCode(rs.getString("DiagnosisCode"));
                    icds.add(icd);
                    
              }while(rs.next());
                return icds;                
            }
            else{  
                return null;
            }
            
      }catch(Exception e){
          e.printStackTrace();          
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

public String[] setICDfromVistA(String hrn,String visitNo) throws SQLException{
        Connection con;
        Statement stmt = null;
        ResultSet rs =null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getVistAConnection();
  try{
        count=0;
        int  rowLength=0;
        stmt=con.createStatement();

        sql="select Distinct DiagnosisCode from ENCOUNTER e, ENCOUNTERPROCEDUREDIAGNOSIS d  ";
        sql=sql+" where d.EncounterID='"+visitNo+"' and e.PatientID='"+hrn+"' and d.Status='A'";
       
        rs=stmt.executeQuery(sql);
        while(rs.next()){
                count++;
        }

        String[] icdDetails =new String[count];
        
        rs.first();
        if(count>0){
            do{
                    icdDetails[rowLength]=rs.getString("DiagnosisCode");                    
                    rowLength++;

              }while(rs.next());
               return icdDetails;
        }else{            
            return null;
        }
  }catch(Exception e){      
      e.printStackTrace();
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

public Object[][] setCPTfromVistA(String hrn,String visitNo) throws SQLException{
        Connection con;
        Statement stmt =null;
        ResultSet rs =null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getVistAConnection();
      
      try{
            count=0;
            int  rowLength=0; 
            stmt=con.createStatement();

            sql="Select Distinct p.ProcedureCode,p.Quantity,p.PROVIDER from ENCOUNTERPROCEDURE p,ENCOUNTER e ";
            sql=sql+" where p.EncounterID='"+visitNo+"' and e.PatientID='"+hrn+"' and p.Status='A'";
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                    count++;
            }
            Object[][] cptDetails =new Object[6][17];
            
            rs.first();
            if(count>0){                
                do{
                        System.out.println("Pcode :"+rs.getString("ProcedureCode"));
                        cptDetails[rowLength][0]=rs.getString("ProcedureCode");
                        cptDetails[rowLength][1]=rs.getString("Quantity");
                        cptDetails[rowLength][14]=findDoctorNPI(rs.getString("Provider"));
                        rowLength++;
                        
                  }while(rs.next());                    
                    return cptDetails;
            }else{
                return null;
            }
      }catch(Exception e){
          e.printStackTrace();
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

public String findDoctorNPI(String doctor) throws SQLException{
          Connection con;
          Statement stmt = null;
          String sql;
          DBConnection DB=new DBConnection();
          con= DB.getPMSConnection();
          ResultSet rset = null;
          String npi="";
try{    
    stmt=con.createStatement();
    sql="select * from DOCTOR where DOCTOR_NAME like '"+doctor.replace(",","-")+"%'";
    System.out.print(sql);
    rset=stmt.executeQuery(sql);
    while(rset.next()){
        npi=rset.getString("NPI")+";"+rset.getString("TAXONOMY");
    }    
}catch(Exception e){
    e.printStackTrace();
}finally{
        if(stmt!=null){
            stmt.close();
        }
        if(rset!=null){
            rset.close();
        }
        if(con!=null){
            con.close();
        }        
        return npi;
 }
}

  public List<ChargeCode> setChargeCode(String cptCode,String chargeCode) throws SQLException{
            Connection con;
            Statement stmt = null;
            ResultSet rs =null;
            String sql;
            int count=0;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
try{
            List<ChargeCode> cc = new ArrayList<ChargeCode>();
            stmt=con.createStatement();

            sql="select * from CHARGE_CODE where 1=1";
            if(!cptCode.isEmpty()){
               sql=sql+" and CPTCode='"+cptCode+"'";
            }
            if(!chargeCode.isEmpty()){
                sql=sql+" and ChargeCode='"+chargeCode+"'";
            }

            rs=stmt.executeQuery(sql);
            while(rs.next()){
                    count++;
            }            
            rs.first();
            if(count>0){                
            do{
                    ChargeCode charge=new ChargeCode();
                    
                    charge.setChargeCode(rs.getString("ChargeCode"));
                    charge.setChargeCodeDes(rs.getString("ChargeCodeDes"));
                    charge.setPrice(rs.getString("Price"));
                    charge.setDefaultPOS(rs.getString("DefaultPOS"));
                    charge.setModifier(rs.getString("Modifier"));
                    cc.add(charge);
              }while(rs.next());
                return cc;
            }
            else{
                return null;
            }
      }catch(Exception e){
          e.printStackTrace();
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

   public List<Bill> setCPTDetails(String billNo) throws SQLException{
            Connection con;
            Statement stmt =null;
            ResultSet rs =null;
            String sql;
            int count=0;
            DBConnection DB=new DBConnection();
            con= DB.getPMSConnection();
  try{
           List<Bill> bill=new ArrayList<Bill>();
           stmt= con.createStatement();
           count=0;

           sql = "select * from BILL where billno='"+billNo+"'";

            rs=stmt.executeQuery(sql);
            while(rs.next()){
                    count++;
            }            
            if(count>0){
                rs.first();
                do{
                    Bill b=new Bill();
                    b.setCPT(rs.getString("CPT"));
                    b.setCPTUnits(rs.getString("CPTUnits"));
                    b.setM1(rs.getString("M1"));
                    b.setM2(rs.getString("M2"));
                    b.setICD1(rs.getString("ICD1"));
                    b.setICD2(rs.getString("ICD2"));
                    b.setICD3(rs.getString("ICD3"));
                    b.setICD4(rs.getString("ICD4"));               
                    b.setCPTRate(rs.getString("CPTRate"));                    
                    b.setCharges(rs.getString("Charges"));                    
                    b.setCoIns(rs.getString("CoIns"));                    
                    b.setAdjAmt(rs.getString("AdjAmt"));
                    b.setAdjCode(rs.getString("AdjCode"));
                    b.setBalance(rs.getString("Balance"));
                    b.setICDPointer(rs.getString("ICDPointer"));
                    
                    Object NPI,TAX;

                    NPI=rs.getObject("ProviderID");
                    TAX=rs.getObject("ProviderTaxonomy");
                    if(NPI==null){
                        NPI="";
                    }                    
                    if(TAX==null){
                        TAX="";
                    }
                    b.setProviderId(NPI+";"+TAX);                    
                    b.setPOS(rs.getString("POS"));
                    bill.add(b);
                }while(rs.next());
                    return bill;
            }
            else{
                     return null;
            }
            }catch(Exception e){                
                e.printStackTrace();
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


   public String[] finalICDs(String[] setICDs,String[] collectICDs){
        String finICDs[]=new String[setICDs.length];

                boolean exist=false;
                int f;
                int elementno=0;
                for(int bi=0;bi<collectICDs.length;bi++){
                   exist=false;
                   for(f=0;f<finICDs.length;f++){
                     if(collectICDs[bi]!=null){
                        if(collectICDs[bi].equalsIgnoreCase(finICDs[f])){
                            exist=true;
                            continue;
                      }
                     }
                   }
                   if(!exist){
                       if(collectICDs[bi]!=null){
                        finICDs[elementno]=collectICDs[bi];
                        elementno++;
                       }
                   }
                }
        return finICDs;
    }

   public String[] setICDDetails(String billNo) throws SQLException{
        Connection con;
        Statement stmt =null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        String[] setICDs = null;
      try{            
           stmt= con.createStatement();
           count=0;
           sql = "select * from BILL where billno='"+billNo+"'";

            rs=(ResultSet) stmt.executeQuery(sql);

            if(rs!=null){
                count=0;
                while(rs.next()){
                    count++;
                }
                String[] icd=new String[count*4];
                int k=0;

                rs.first();
                do{
                    icd[k]=rs.getString("ICD1");
                    icd[k+1]=rs.getString("ICD2");
                    icd[k+2]=rs.getString("ICD3");
                    icd[k+3]=rs.getString("ICD4");
                    k=k+4;
                }while(rs.next());
               
                Utiles uts=new Utiles();
                setICDs=uts.eliminateDuplicateICDs(icd);
                setICDs=finalICDs(setICDs,icd);
            }
                  return setICDs;
            }catch(Exception e){               
                e.printStackTrace();
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

    public BillAmt checkBill(String billNo) throws SQLException{
        Connection con;
        Statement stmt = null;
        ResultSet rs = null;
        String sql;
        int count=0;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        BillAmt ba=new BillAmt();
    try{            
           stmt= con.createStatement();
           count=0;

           sql = "select * from BILLAMT where billno='"+billNo+"'";
           rs= stmt.executeQuery(sql);

                count=0;
                while(rs.next()){
                    count++;
                }
                if(count>0){
                    ba=setTotalBillDetails(rs);
                    return ba;
                }
                else{
                    return null;
                }
     }catch(Exception e){
                     e.printStackTrace();
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

    public BillAmt setTotalBillDetails(ResultSet rs){
        BillAmt ba=new BillAmt();
        try{
            rs.first();
            ba.setTotalCPTUnits(rs.getString("TotalCPTUnits"));
            ba.setTotalCharges(rs.getString("TotalCharges"));
            ba.setTotalAdjAmt(rs.getString("TotalAdjAmt"));
            ba.setTotalBalance(rs.getString("TotalBalance"));
            ba.setDeductible(rs.getString("Deductible"));
            ba.setOutofPocket(rs.getString("OutofPocket"));
            ba.setBalancetobeClaimed(rs.getString("BalancetobeClaimed"));
            ba.setAmountPaidByPatient(rs.getString("AmountPaidByPatient"));
            ba.setModeofPayment(rs.getString("ModeofPayment"));
            ba.setModeofPaymentNo(rs.getString("ModeofPaymentNo"));
            ba.setAmountDue(rs.getString("AmountDue"));
            ba.setBankName(rs.getString("BankName"));
            return ba;
        }catch(Exception e){
                    e.printStackTrace();
                    return null;
        }
    } 
}
