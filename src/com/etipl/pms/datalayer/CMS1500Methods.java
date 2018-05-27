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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CMS1500Methods {

   public String replaceBrackets(String phone){
   try{
        String s="";
        char[] phoneR=new char[phone.length()];

        for(int i=0;i<phone.length();i++){
            char j=phone.charAt(i);
            if(j == '(' || j == ')' ){
                phoneR[i]=' ';
            }else{
                phoneR[i]=phone.charAt(i);
            }
        }
        for(int p=0;p<phoneR.length;p++){
           s=s+String.valueOf(phoneR[p]).toString();
        }
        return s;
   }catch(Exception e){e.printStackTrace();return null;}
   }

   public int checkClaim(String claimID) throws SQLException{
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement check = null;
        DBConnection DB=new DBConnection();
        con= DB.getPMSConnection();
        String sql;        
    try{
        sql="select * from CMS_CPTS where CLAIMID='"+claimID+"'";
        check=con.prepareStatement(sql);
        rs= check.executeQuery();
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
         if(check!=null){
             check.close();
         }
         if(con!=null){
             con.close();
         }
     }
    }

   public int SaveCMSHCFA1500_CPTS(String hrn,String claimNo
           ,String insType,String visitNo
           ,String provider,String visitDate) throws SQLException{

           Connection con = null;
           Statement stmt = null;
           DBConnection DB=new DBConnection();
           con= DB.getPMSConnection();
           String localUse="";
           ResultSet insRS,caseRS,serFacilityRS
                   ,billSerRS,docRS,refDocRS
                   ,billAmtRS,patRS,billRS;
           String sql,insSQl,caseSQl
                   ,serFacilitySQL = null,billSerSQL = null
                   ,docSQL,refDOCSQL,billAmtSQL
                   ,patSQL;
           PreparedStatement selectCase=null;
           PreparedStatement selectBillAmt=null;
           PreparedStatement selectPat=null;
           PreparedStatement selectPatIns=null;
           PreparedStatement selectSerFacility=null;
           PreparedStatement selectDoc=null;
           PreparedStatement selectDoctor=null;
           PreparedStatement insertCMS1500=null;
           PreparedStatement updateBill=null;
           PreparedStatement insertCMScpts=null;
           PreparedStatement selectBillFacility=null;
           PreparedStatement selectCaseSql=null;
  try{

            con.setAutoCommit(false);
            localUse="";
            caseSQl="Select * from PATIENT_CASE where CaseNo='"+claimNo+"'";
            selectCase=con.prepareStatement(caseSQl);
            caseRS=selectCase.executeQuery();

            int patCaseCount=0;
            while(caseRS.next()){
               patCaseCount++;
            }
            if(patCaseCount>0){
                caseRS.first();
               
                    if(!caseRS.getString("ServiceFacility").isEmpty()){
                        serFacilitySQL="Select * from FACILITY where Code='"+caseRS.getString("ServiceFacility")+"'";
                    }else{
                        serFacilitySQL="Select * from FACILITY where Code=''";
                    }
                    if(!caseRS.getString("BillingService").isEmpty()){
                        billSerSQL="Select * from FACILITY where Code='"+caseRS.getString("BillingService")+"'";
                    }else{
                        billSerSQL="Select * from FACILITY where Code=''";
                    }
                    if(!caseRS.getString("Remarks").isEmpty()){
                        localUse=caseRS.getString("Remarks");
                    }
            }else{
                JOptionPane.showMessageDialog(null, "Please Check whether the Facilities available for the Patient visit");
            }

            refDOCSQL="Select * from DOCTOR where DOCTOR_NAME=";
            refDOCSQL=refDOCSQL+"(Select Distinct s.REFERRED_FROM from SLOT s,VISIT v where s.SLOT_ID=v.APPOINTMENT_ID";
            refDOCSQL=refDOCSQL+" and s.PATIENT_CODE='"+hrn+"' and v.VISITNO='"+visitNo+"')";

            sql = "Insert into CMS_HCFA1500 (HRN,CLAIMID,Pat_Acct_No,ICD1,ICD2,ICD3,ICD4";
            sql = sql+ ",TotalCharges,AmtPaid,Balance,PName,PAdd2";
            sql = sql+ ",PCity,PState,PZip,PPhone,PStatus,PSSN,PSex,PDOB,SOFHIPAA,DOHS,PCarrier";
            sql = sql+ ",LocalUse,PInsurancePlan,InsuranceID,INSURANCE_CLASS,InsuranceCompany";
            sql = sql+ ",InsuranceAdd,InsuranceCity,InsuranceState,InsuranceZip,Relation";
            sql = sql+ ",GurantorName,GurantorAdd,GurantorID,GurantorDOB,GurantorSex";
            sql = sql+ ",GroupNo,GurantorCity,GurantorState,GurantorZip,GurantorPhone";
            sql = sql +",AcceptsAssign,AnyBenfitPlan,OtherInsuredName";
            sql = sql+ ",OtherInsuredDOB,OtherInsuredSex,OtherInsuredCarrier,OtherInsuredPlanName";
            sql = sql+ ",SOFInsurance,DOIS,ClaimType,FacilityName,FacilityAdd,FacilityCity";
            sql = sql+ ",FacilityState,FacilityZip,FacilityPhone,FacilityNpi,FacilityFederalID,ProviderName";
            sql = sql+ ",ProviderDegree,FederalTaxID,RENPROVIDERTAXONOMY,RefProviderName,NPIRefNo,RefProvTaxonomy,BillingProviderName,BillingProviderAdd";
            sql = sql+ ",BillingProviderCity,BillingProviderState,BillingProviderZip,BillingProviderPhone";
            sql = sql+ ",BIllingProviderFederalID,BillingProviderNPI,BillingProviderTaxonomy,ProAuthorizationNo,CurrentIllnessDate";
            sql = sql+ ",SimiliarIllnessDate,MedicaidResubmission,PatientUnableFromDate,PatientUnableToDate";
            sql = sql+ ",HospitalizationFromDate,HospitalizationToDate,Employment";
            sql = sql+ ",AutoAccident,OtherAccident,Place,OutsideLab,ChargesOutsideLab,Status,FLAG";
            sql = sql+")values('";
            sql= sql+hrn+"'";
            
            billAmtSQL="Select * from BILLAMT where BillNo='"+claimNo+"'";
            selectBillAmt=con.prepareStatement(billAmtSQL);
            billAmtRS=selectBillAmt.executeQuery();
            
            int billAmtCount=0;
            while(billAmtRS.next()){
               billAmtCount++;
            }
            if(billAmtCount>0){
                billAmtRS.first();
                
                    sql= sql+",'"+billAmtRS.getString("BillNo")+insType+"'";
                    sql= sql+",'"+billAmtRS.getString("BillNo")+"'";
                    if(billAmtRS.getString("ICD1")!=null )
                    sql= sql+",'"+billAmtRS.getString("ICD1")+"'";
                    else sql=sql+",'"+"'";
                    if(billAmtRS.getString("ICD2")!=null)
                    sql= sql+",'"+billAmtRS.getString("ICD2")+"'";
                    else sql=sql+",'"+"'";
                    if(billAmtRS.getString("ICD3")!=null)
                    sql= sql+",'"+billAmtRS.getString("ICD3")+"'";
                    else sql=sql+",'"+"'";
                    if(billAmtRS.getString("ICD4")!=null)
                    sql= sql+",'"+billAmtRS.getString("ICD4")+"'";
                    else sql=sql+",'"+"'";
                    if(billAmtRS.getString("TotalCharges")!=null)
                    sql= sql+",'"+billAmtRS.getString("TotalCharges")+"'";
                    else sql=sql+",'"+"'";
                    if(billAmtRS.getString("AmountPaidByPatient")!=null)
                    sql= sql+",'"+billAmtRS.getString("AmountPaidByPatient")+"'";
                    else sql=sql+",'"+"'";
                    if(billAmtRS.getString("TotalBalance")!=null)
                    sql= sql+",'"+billAmtRS.getString("TotalBalance")+"'";
                    else sql=sql+",'"+"'";               
            }else{
                sql=sql+"'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
            }
            patSQL="Select * from JP_PAT_INFO where HRN='"+hrn+"'";
            selectPat=con.prepareStatement(patSQL);
            patRS=selectPat.executeQuery();
            
            int patCount=0;
            while(patRS.next()){
                patCount++;
            }
            if(patCount>0) {
            patRS.first();
            
                sql=sql+",'"+patRS.getString("LNAME")+" "+patRS.getString("FNAME")+"'";
                if(patRS.getString("LADD1")!=null || patRS.getString("LADD2")!=null)
                sql= sql+",'"+patRS.getString("LADD1")+" "+patRS.getString("LADD2")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("LADD_CITY")!=null)
                sql= sql+",'"+patRS.getString("LADD_CITY")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("LADD_STATE")!=null)
                sql= sql+",'"+patRS.getString("LADD_STATE")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("LADD_PIN")!=null)
                sql= sql+",'"+patRS.getString("LADD_PIN")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("PHONE")!=null){                    
                    sql= sql+",'"+replaceBrackets(patRS.getString("PHONE"))+"'";
                }
                else sql=sql+",'"+"'";
                if(patRS.getString("MS")!=null)
                sql= sql+",'"+patRS.getString("MS")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("SSN")!=null)
                sql= sql+",'"+patRS.getString("SSN")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("SEX")!=null)
                sql= sql+",'"+patRS.getString("SEX")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("DOB")!=null)
                sql= sql+",'"+patRS.getString("DOB")+"'";
                else sql=sql+",'"+"'";
                if(patRS.getString("SOFHIPAA").equalsIgnoreCase("Y"))
                sql= sql+",'"+"1'";
                else sql=sql+",'"+"0'";
                if(patRS.getString("DOHS")!=null)
                sql= sql+",'"+patRS.getString("DOHS")+"'";
                else sql=sql+",'"+"'";
                
            }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"0'";
                sql=sql+",'"+"'";
            }
            sql= sql+",'PCarrier'";
            sql= sql+",'"+localUse+"'";

            insSQl = "select * from PATIENT_INSURANCE pi,INSURANCE_COMPANY_DETAILS ic";
            insSQl = insSQl+" where ic.Company_Id=pi.INSURANCE_COMPANY_ID and pi.PATIENT_HRN='"+hrn+"' and pi.INSURANCETYPE like '"+insType+"%'";
            selectPatIns=con.prepareStatement(insSQl);
            insRS=selectPatIns.executeQuery();
            int insCount=0;
            
            while(insRS.next()){
                insCount++;
            }
                if(insCount>0){
                  insRS.first();
                  
                    if(insRS.getString("INSURANCEPLANNAME")!=null)
                    sql= sql+",'"+insRS.getString("INSURANCEPLANNAME")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("INSURANCE_COMPANY_ID")!=null)
                    sql= sql+",'"+insRS.getString("INSURANCE_COMPANY_ID")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("INSURANCE_CLASS")!=null)
                    sql= sql+",'"+insRS.getString("INSURANCE_CLASS")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Company_Name")!=null)
                    sql= sql+",'"+insRS.getString("INSURANCE_COMPANY_ID")+"-"+insRS.getString("Company_Name")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Address2")!=null)
                    sql= sql+",'"+insRS.getString("Address2")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("City")!=null)
                    sql= sql+",'"+insRS.getString("City")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("State")!=null)
                    sql= sql+",'"+insRS.getString("State")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Zip")!=null)
                    sql= sql+",'"+insRS.getString("Zip")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("POLICY_HOLDER_RELATION")!=null)
                    sql= sql+",'"+insRS.getString("POLICY_HOLDER_RELATION")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("POLICY_HOLDER_LNAME")!=null || insRS.getString("POLICY_HOLDER_FNAME")!=null)
                    sql= sql+",'"+insRS.getString("POLICY_HOLDER_LNAME")+" "+insRS.getString("POLICY_HOLDER_FNAME")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Policy_Holder_Address")!=null)
                    sql= sql+",'"+insRS.getString("Policy_Holder_Address")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("SUBSCRIBER_ID")!=null)
                    sql= sql+",'"+insRS.getString("SUBSCRIBER_ID")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("POLICY_HOLDER_DOB")!=null)
                    sql= sql+",'"+insRS.getString("POLICY_HOLDER_DOB")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("POLICY_HOLDER_SEX")!=null)
                    sql= sql+",'"+insRS.getString("POLICY_HOLDER_SEX")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("GROUPNO")!=null)
                    sql= sql+",'"+insRS.getString("GROUPNO")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Policy_Holder_City")!=null)
                    sql= sql+",'"+insRS.getString("Policy_Holder_City")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Policy_Holder_State")!=null)
                    sql= sql+",'"+insRS.getString("Policy_Holder_State")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Policy_Holder_Zipcode")!=null)
                    sql= sql+",'"+insRS.getString("Policy_Holder_Zipcode")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Policy_Holder_Phone")!=null)
                    sql= sql+",'"+replaceBrackets(insRS.getString("Policy_Holder_Phone"))+"'";
                    else sql=sql+",'"+"'";
                    sql= sql+",'1'";
                    sql= sql+",'0'";
                    if(insRS.getString("Other_Holder_Name")!=null)
                    sql= sql+",'"+insRS.getString("Other_Holder_Name")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Other_Holder_DOB")!=null)
                    sql= sql+",'"+insRS.getString("Other_Holder_DOB")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Other_Holder_Sex")!=null)
                    sql= sql+",'"+insRS.getString("Other_Holder_Sex")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Other_Holder_EmpSchool")!=null)
                    sql= sql+",'"+insRS.getString("Other_Holder_EmpSchool")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("Other_Holder_PlanName")!=null)
                    sql= sql+",'"+insRS.getString("Other_Holder_PlanName")+"'";
                    else sql=sql+",'"+"'";
                    if(insRS.getString("SOFInsurance").equalsIgnoreCase("Y"))
                    sql= sql+",'"+"1'";
                    else sql=sql+",'"+"0'";
                    if(insRS.getString("DOS")!=null)
                    sql= sql+",'"+insRS.getString("DOS")+"'";
                    else sql=sql+",'"+"'";
                    sql= sql+",'Paper'";
                  
               }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql= sql+",'0'";
                sql= sql+",'0'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql= sql+",'0'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
            }

            insRS.close();

            selectSerFacility=con.prepareStatement(serFacilitySQL);
            serFacilityRS=selectSerFacility.executeQuery();
            int serCount=0;
            while(serFacilityRS.next()){
                serCount++;
            }

            if(serCount>0){
                serFacilityRS.first();
                
                        if(serFacilityRS.getString("Name")!=null)
                        sql= sql+",'"+serFacilityRS.getString("Name")+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("Address2")!=null)
                        sql= sql+",'"+serFacilityRS.getString("Address2")+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("City")!=null)
                        sql= sql+",'"+serFacilityRS.getString("City")+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("State")!=null)
                        sql= sql+",'"+serFacilityRS.getString("State")+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("Zip")!=null)
                        sql= sql+",'"+serFacilityRS.getString("Zip")+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("Phone")!=null)
                        sql= sql+",'"+replaceBrackets(serFacilityRS.getString("Phone"))+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("NPI")!=null)
                        sql= sql+",'"+serFacilityRS.getString("NPI")+"'";
                        else sql=sql+",'"+"'";
                        if(serFacilityRS.getString("FederalTaxId")!=null)
                        sql= sql+",'"+serFacilityRS.getString("FederalTaxId")+"'";
                        else sql=sql+",'"+"'";
                    
            }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
            }

            serFacilityRS.close();

            docSQL="Select * from DOCTOR where DOCTOR_NAME like '"+provider.replace(", ","-")+"%'";
            selectDoc=con.prepareStatement(docSQL);
            docRS=selectDoc.executeQuery();
            int docCount=0;
            while(docRS.next()){
                docCount++;
            }
            if(docCount>0){
                docRS.first();
                
                if(docRS.getString("DOCTOR_NAME")!=null)
                sql= sql+",'"+docRS.getString("DOCTOR_NAME")+"'";
                else sql=sql+",'"+"'";
                if(docRS.getString("CREDENTIALS")!=null)
                sql= sql+",'"+docRS.getString("CREDENTIALS")+"'";
                else sql=sql+",'"+"'";
                if(docRS.getString("FEDERALTAXID")!=null)
                sql= sql+",'"+docRS.getString("FEDERALTAXID")+"'";
                else sql=sql+",'"+"'";
                if(docRS.getString("TAXONOMY")!=null)
                sql= sql+",'"+docRS.getString("TAXONOMY")+"'";
                else sql=sql+",'"+"'";
                
            }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
            }
            docRS.close();

            selectDoctor=con.prepareStatement(refDOCSQL);
            refDocRS=selectDoctor.executeQuery();
            int refCount=0;
            while(refDocRS.next()){
                refCount++;
            }
            if(refCount>0){
               refDocRS.first();
               
                    if(refDocRS.getString("DOCTOR_NAME")!=null)
                    sql= sql+",'"+refDocRS.getString("DOCTOR_NAME")+"'";
                    else sql=sql+",'"+"'";
                    if(refDocRS.getString("NPI")!=null)
                    sql= sql+",'"+refDocRS.getString("NPI")+"'";
                    else sql=sql+",'"+"'";
                    if(refDocRS.getString("TAXONOMY")!=null)
                    sql= sql+",'"+refDocRS.getString("TAXONOMY")+"'";
                    else sql=sql+",'"+"'";
                    
            }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
            }

            refDocRS.close();
            
            selectBillFacility=con.prepareStatement(billSerSQL);
            billSerRS=selectBillFacility.executeQuery();
            int billCount=0;
            while(billSerRS.next()){
                billCount++;
            }
            if(billCount>0){
                billSerRS.first();
                
                    if(billSerRS.getString("Name")!=null)
                    sql= sql+",'"+billSerRS.getString("Name")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("Address2")!=null)
                    sql= sql+",'"+billSerRS.getString("Address2")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("City")!=null)
                    sql= sql+",'"+billSerRS.getString("City")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("State")!=null)
                    sql= sql+",'"+billSerRS.getString("State")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("Zip")!=null)
                    sql= sql+",'"+billSerRS.getString("Zip")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("Phone")!=null)
                    sql= sql+",'"+replaceBrackets(billSerRS.getString("Phone"))+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("FederalTaxId")!=null)
                    sql= sql+",'"+billSerRS.getString("FederalTaxId")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("NPI")!=null)
                    sql= sql+",'"+billSerRS.getString("NPI")+"'";
                    else sql=sql+",'"+"'";
                    if(billSerRS.getString("TaxonomyID")!=null)
                    sql= sql+",'"+billSerRS.getString("TaxonomyID")+"'";
                    else sql=sql+",'"+"'";
                
                    String str="Select * from BILL where BILLNo='"+claimNo+"' and CPT!=' '";
                    PreparedStatement selectBill=con.prepareStatement(str);
                    int flag=0;
                    billRS=selectBill.executeQuery();
                    while(billRS.next()){
                        if(billRS.getString("CPT").startsWith("8")){
                                flag=1;
                        }
                    }
                    if(flag==1 && billSerRS.getString("CLIA_NO")!=null){
                        sql= sql+",'"+billSerRS.getString("CLIA_NO")+"'";                        
                    }else sql=sql+",'"+"'";                  
            }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
            }
            billSerRS.close();
            
            selectCaseSql=con.prepareStatement(caseSQl);
            caseRS=selectCaseSql.executeQuery();
            int caseCount=0;
            while(caseRS.next()){
               caseCount++;
            }
            if(caseCount>0){
                caseRS.first();
               
                    if(caseRS.getString("CurrentIllnessDate")!=null)
                    sql= sql+",'"+caseRS.getString("CurrentIllnessDate")+"'";
                    else sql=sql+",'"+"'";
                    if(caseRS.getString("SimiliarIllnessDate")!=null)
                    sql= sql+",'"+caseRS.getString("SimiliarIllnessDate")+"'";
                    else sql=sql+",'"+"'";
                    sql= sql+",'"+"'";
                    if(caseRS.getString("PatientUnableFromDate")!=null)
                    sql= sql+",'"+caseRS.getString("PatientUnableFromDate")+"'";
                    else sql=sql+",'"+"'";
                    if(caseRS.getString("PatientUnableToDate")!=null)
                    sql= sql+",'"+caseRS.getString("PatientUnableToDate")+"'";
                    else sql=sql+",'"+"'";
                    if(caseRS.getString("HospitalizationFromDate")!=null)
                    sql= sql+",'"+caseRS.getString("HospitalizationFromDate")+"'";
                    else sql=sql+",'"+"'";
                    if(caseRS.getString("HospitalizationToDate")!=null)
                    sql= sql+",'"+caseRS.getString("HospitalizationToDate")+"'";
                    else sql=sql+",'"+"'";
                    sql= sql+",'"+caseRS.getString("Employment")+"'";
                    sql= sql+",'"+caseRS.getString("AutoAccident")+"'";
                    sql= sql+",'"+caseRS.getString("OtherAccident")+"'";
                    if(caseRS.getString("Place")!=null)
                    sql= sql+",'"+caseRS.getString("Place")+"'";
                    else sql=sql+",'"+"'";
                    
            }else{
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql=sql+",'"+"'";
                sql= sql+",'N'";
                sql= sql+",'N'";
                sql= sql+",'N'";
                sql=sql+",'"+"'";
            }
            sql= sql+",'N'";
            sql= sql+",'"+"'";
            sql= sql+",'S'";
            sql= sql+",'0'";
            sql=sql+")";
             
            insertCMS1500=con.prepareStatement(sql);
            insertCMS1500.executeUpdate(); 

            insRS.close();
            caseRS.close();
            serFacilityRS.close();
            billSerRS.close();
            docRS.close();
            refDocRS.close();
            billAmtRS.close();
            patRS.close();

            ResultSet billRS1;
            String billSQL;

            stmt= con.createStatement();

            billSQL="Select * from BILL where BillNo='"+claimNo+"'";
            updateBill=con.prepareStatement(billSQL);
            billRS1=updateBill.executeQuery();

            while(billRS1.next()){
            sql = "Insert into CMS_CPTS (CLAIMID,ServiceFromDate,ServiceToDate,POS,EMG,CPT";
            sql = sql+ ",Modifier1,Modifier2,Modifier3,Modifier4,DiagnosisPointer,Charges";
            sql = sql+ ",Units,EPSDT,Quality,ProviderID,ProviderTaxonomy,DUMMYID";
            sql = sql+")values('";

            sql= sql+ billRS1.getString("BillNo")+insType+"'";

            if(billRS1.getString("CPT")!=null)
            sql= sql+",'"+visitDate+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("CPT")!=null)
            sql= sql+",'"+visitDate+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("POS")!=null)
            sql= sql+",'"+billRS1.getString("POS")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("EMG")!=null)
            sql= sql+",'"+billRS1.getString("EMG")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("CPT")!=null)
            sql= sql+",'"+billRS1.getString("CPT")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("M1")!=null)
            sql= sql+",'"+billRS1.getString("M1")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("M2")!=null)
            sql= sql+",'"+billRS1.getString("M2")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("M3")!=null)
            sql= sql+",'"+billRS1.getString("M3")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("M4")!=null)
            sql= sql+",'"+billRS1.getString("M4")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("ICDPointer")!=null)
            sql= sql+",'"+billRS1.getString("ICDPointer")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("Charges")!=null)
            sql= sql+",'"+billRS1.getString("Charges")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("CPTUnits")!=null)
            sql= sql+",'"+billRS1.getString("CPTUnits")+"'";
            else sql=sql+",'"+"'";
            sql= sql+",'"+"'";
            sql= sql+",'"+"'";
            if(billRS1.getString("ProviderID")!=null)
            sql= sql+",'"+billRS1.getString("ProviderID")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("ProviderTaxonomy")!=null)
            sql= sql+",'"+billRS1.getString("ProviderTaxonomy")+"'";
            else sql=sql+",'"+"'";
            if(billRS1.getString("DUMMYID")!=null)
            sql= sql+",'"+billRS1.getString("DUMMYID")+"'";
            else sql=sql+",'"+"'";
            sql=sql+")";

            insertCMScpts=con.prepareStatement(sql);
            insertCMScpts.executeUpdate();
            }
           con.commit();
           con.setAutoCommit(true);

           return 1;
  }catch(Exception e){e.printStackTrace(); DB.closePMSConnection(con); return 0; }
  finally{
          if(selectCase!=null){selectCase.close();}
          if(selectBillAmt!=null){selectBillAmt.close();}
          if(selectPat!=null){selectPat.close();}
          if(selectPatIns!=null){selectPatIns.close();}
          if(selectSerFacility!=null){selectSerFacility.close();}
          if(selectDoc!=null){selectDoc.close();}
          if(selectDoctor!=null){selectDoctor.close();}
          if(insertCMS1500!=null){insertCMS1500.close();}
          if(updateBill!=null){updateBill.close();}
          if(insertCMScpts!=null){insertCMScpts.close();}
          if(selectBillFacility!=null){selectBillFacility.close();}
          if( selectCaseSql!=null){ selectCaseSql.close();}
          if(con!=null){con.close();}
  }
 }

 public int updateCMSHCFA1500_CPTS(String hrn,String claimNo
           ,String insType,String visitNo
           ,String provider,String visitDate) throws SQLException{

           Connection con;
           String localUse="";
           DBConnection DB=new DBConnection();
           con= DB.getPMSConnection();
           ResultSet insRS,caseRS,serFacilityRS
                   ,billSerRS,docRS,refDocRS
                   ,billAmtRS,patRS,billRS;
           String sql,insSQl,caseSQl
                   ,serFacilitySQL = null,billSerSQL = null
                   ,docSQL,refDOCSQL,billAmtSQL
                   ,patSQL;
           PreparedStatement selectPcase=null;
           PreparedStatement selectBillAmt=null;
           PreparedStatement selectPat=null;
           PreparedStatement ins=null;
           PreparedStatement serFacility=null;
           PreparedStatement selectDoc=null;
           PreparedStatement refDoc=null;
           PreparedStatement billFacility=null;
           PreparedStatement selectBill=null;
           PreparedStatement selectCase=null;
           PreparedStatement updateClaim=null;
           PreparedStatement selBill=null;
           PreparedStatement updateCMSCpts=null;
  try{

           con.setAutoCommit(false);
           localUse="";
           
            String CLAIMID = null;
            caseSQl="Select * from PATIENT_CASE where CaseNo='"+claimNo+"'";
            selectPcase=con.prepareStatement(caseSQl);
            caseRS=selectPcase.executeQuery();

            int pCaseCount=0;
            while(caseRS.next()){
                pCaseCount++;
            }            
            if(pCaseCount>0){
                caseRS.first();
                do{
                    if(!caseRS.getString("ServiceFacility").isEmpty()){
                        serFacilitySQL="Select * from FACILITY where Code='"+caseRS.getString("ServiceFacility")+"'";
                    }else{serFacilitySQL="Select * from FACILITY where Code=''";  }
                    if(!caseRS.getString("BillingService").isEmpty()){
                        billSerSQL="Select * from FACILITY where Code='"+caseRS.getString("BillingService")+"'";
                    }else{ billSerSQL="Select * from FACILITY where Code=''"; }
                    if(!caseRS.getString("Remarks").isEmpty()){
                        localUse=caseRS.getString("Remarks");
                    }
                }while(caseRS.next());
            }else{
                JOptionPane.showMessageDialog(null, "Please Check whether the Facilities available for the Patient visit");
            }

            docSQL="Select * from DOCTOR where DOCTOR_NAME like '"+provider.replace(", ","-")+"%'";
            System.out.println("doc "+docSQL);
            refDOCSQL="Select * from DOCTOR where DOCTOR_NAME=";
            refDOCSQL=refDOCSQL+"(Select Distinct s.REFERRED_FROM from SLOT s,VISIT v where s.SLOT_ID=v.APPOINTMENT_ID";
            refDOCSQL=refDOCSQL+" and s.PATIENT_CODE='"+hrn+"' and v.VISITNO='"+visitNo+"')";
           
        sql = "UPDATE CMS_HCFA1500 set ";

        //--- Get billamt information ----//
        billAmtSQL="Select * from BILLAMT where BillNo='"+claimNo+"'";
        selectBillAmt=con.prepareStatement(billAmtSQL);
        billAmtRS=selectBillAmt.executeQuery();

        if(billAmtRS.next()){
            CLAIMID=billAmtRS.getString("BillNo")+insType+"";
            sql= sql+"Pat_Acct_No='"+billAmtRS.getString("BillNo")+"'";
            if(billAmtRS.getString("ICD1")!=null )
            sql= sql+",ICD1='"+billAmtRS.getString("ICD1")+"'";
            else sql=sql+",ICD1='"+"'";
            if(billAmtRS.getString("ICD2")!=null)
            sql= sql+",ICD2='"+billAmtRS.getString("ICD2")+"'";
            else sql=sql+",ICD2='"+"'";
            if(billAmtRS.getString("ICD3")!=null)
            sql= sql+",ICD3='"+billAmtRS.getString("ICD3")+"'";
            else sql=sql+",ICD3='"+"'";
            if(billAmtRS.getString("ICD4")!=null)
            sql= sql+",ICD4='"+billAmtRS.getString("ICD4")+"'";
            else sql=sql+",ICD4='"+"'";
            if(billAmtRS.getString("TotalCharges")!=null)
            sql= sql+",TotalCharges='"+billAmtRS.getString("TotalCharges")+"'";
            else sql=sql+",TotalCharges='"+"'";
            if(billAmtRS.getString("AmountPaidByPatient")!=null)
            sql= sql+",AmtPaid='"+billAmtRS.getString("AmountPaidByPatient")+"'";
            else sql=sql+",AmtPaid='"+"'";
            if(billAmtRS.getString("TotalBalance")!=null)
            sql= sql+",Balance='"+billAmtRS.getString("TotalBalance")+"'";
            else sql=sql+",Balance='"+"'";
        }else{
            sql=sql+"CLAIMID='"+"'";
            sql=sql+",Pat_Acct_No='"+"'";
            sql=sql+",ICD1='"+"'";
            sql=sql+",ICD2='"+"'";
            sql=sql+",ICD3='"+"'";
            sql=sql+",ICD4='"+"'";
            sql=sql+",TotalCharges='"+"'";
            sql=sql+",AmtPaid='"+"'";
            sql=sql+",Balance='"+"'";
        }

        //--- End billamt information ----//

        //--- Get patient information ----//
        patSQL="Select * from JP_PAT_INFO where HRN='"+hrn+"'";
        selectPat=con.prepareStatement(patSQL);
        patRS=selectPat.executeQuery(patSQL);
        
        if(patRS.next()){
            sql=sql+",PName='"+patRS.getString("LNAME")+" "+patRS.getString("FNAME")+"'";
            if(patRS.getString("LADD1")!=null){
                sql= sql+",PAdd2='"+patRS.getString("LADD1")+"";
                    if(patRS.getString("LADD2")!=null)
                    sql= sql+" "+patRS.getString("LADD2")+"'";
                    else sql=sql+"'";
            }
            else sql=sql+",PAdd2='"+"'";
            if(patRS.getString("LADD_CITY")!=null)
            sql= sql+",PCity='"+patRS.getString("LADD_CITY")+"'";
            else sql=sql+",PCity='"+"'";
            if(patRS.getString("LADD_STATE")!=null)
            sql= sql+",PState='"+patRS.getString("LADD_STATE")+"'";
            else sql=sql+",PState='"+"'";
            if(patRS.getString("LADD_PIN")!=null)
            sql= sql+",PZip='"+patRS.getString("LADD_PIN")+"'";
            else sql=sql+",PZip='"+"'";
            if(patRS.getString("PHONE")!=null)
            sql= sql+",PPhone='"+replaceBrackets(patRS.getString("PHONE"))+"'";
            else sql=sql+",PPhone='"+"'";
            if(patRS.getString("MS")!=null)
            sql= sql+",PStatus='"+patRS.getString("MS")+"'";
            else sql=sql+",PStatus='"+"'";
            if(patRS.getString("SSN")!=null)
            sql= sql+",PSSN='"+patRS.getString("SSN")+"'";
            else sql=sql+",PSSN='"+"'";
            if(patRS.getString("SEX")!=null)
            sql= sql+",PSex='"+patRS.getString("SEX")+"'";
            else sql=sql+",PSex='"+"'";
            if(patRS.getString("DOB")!=null)
            sql= sql+",PDOB='"+patRS.getString("DOB")+"'";
            else sql=sql+",PDOB='"+"'";
            if(patRS.getString("SOFHIPAA").equalsIgnoreCase("Y")){
                sql= sql+",SOFHIPAA='1'";
            }else{
                sql= sql+",SOFHIPAA='0'";
            }
            if(patRS.getString("DOHS")!=null){
                    sql= sql+",DOHS='"+patRS.getString("DOHS")+"'";
            }else sql=sql+",DOHS='"+"'";
        }else{
            sql=sql+",PName='"+"'";
            sql=sql+",PAdd2='"+"'";
            sql=sql+",PCity='"+"'";
            sql=sql+",PState='"+"'";
            sql=sql+",PZip='"+"'";
            sql=sql+",PPhone='"+"'";
            sql=sql+",PStatus='"+"'";
            sql=sql+",PSSN='"+"'";
            sql=sql+",PSex='"+"'";
            sql=sql+",PDOB='"+"'";
        }

        //--- Get patient information ----//
        sql= sql+",PCarrier='"+"'";
        sql= sql+",LocalUse='"+localUse+"'";

        //--- Get patient insurance    ----------------------------------//
        insSQl = "select * from PATIENT_INSURANCE pi,INSURANCE_COMPANY_DETAILS ic";
        insSQl = insSQl+" where ic.Company_Id=pi.INSURANCE_COMPANY_ID and PATIENT_HRN='"+hrn+"' and pi.INSURANCETYPE like '"+insType+"%'";
        ins=con.prepareStatement(insSQl);
        insRS= ins.executeQuery(insSQl);

        if(insRS.next()){
            if(insRS.getString("INSURANCEPLANNAME")!=null)
            sql= sql+",PInsurancePlan='"+insRS.getString("INSURANCEPLANNAME")+"'";
            else sql=sql+",'"+"'";
            if(insRS.getString("INSURANCE_COMPANY_ID")!=null)
            sql= sql+",InsuranceID='"+insRS.getString("INSURANCE_COMPANY_ID")+"'";
            else sql=sql+",InsuranceID='"+"'";
            if(insRS.getString("INSURANCE_CLASS")!=null)
            sql= sql+",INSURANCE_CLASS='"+insRS.getString("INSURANCE_CLASS")+"'";
            else sql=sql+",INSURANCE_CLASS='"+"'";
            if(insRS.getString("Company_Name")!=null)
            sql= sql+",InsuranceCompany='"+insRS.getString("INSURANCE_COMPANY_ID")+"-"+insRS.getString("Company_Name")+"'";
            else sql=sql+",InsuranceCompany='"+"'";
            if(insRS.getString("Address2")!=null)
            sql= sql+",InsuranceAdd='"+insRS.getString("Address2")+"'";
            else sql=sql+",InsuranceAdd='"+"'";
            if(insRS.getString("City")!=null)
            sql= sql+",InsuranceCity='"+insRS.getString("City")+"'";
            else sql=sql+",InsuranceCity='"+"'";
            if(insRS.getString("State")!=null)
            sql= sql+",InsuranceState='"+insRS.getString("State")+"'";
            else sql=sql+",InsuranceState='"+"'";
            if(insRS.getString("Zip")!=null)
            sql= sql+",InsuranceZip='"+insRS.getString("Zip")+"'";
            else sql=sql+",InsuranceZip='"+"'";
            if(insRS.getString("POLICY_HOLDER_RELATION")!=null)
            sql= sql+",Relation='"+insRS.getString("POLICY_HOLDER_RELATION")+"'";
            else sql=sql+",Relation='"+"'";
            if(insRS.getString("POLICY_HOLDER_LNAME")!=null || insRS.getString("POLICY_HOLDER_FNAME")!=null)
            sql= sql+",GurantorName='"+insRS.getString("POLICY_HOLDER_LNAME")+" "+insRS.getString("POLICY_HOLDER_FNAME")+"'";
            else sql=sql+",GurantorName='"+"'";
            if(insRS.getString("Policy_Holder_Address")!=null)
            sql= sql+",GurantorAdd='"+insRS.getString("Policy_Holder_Address")+"'";
            else sql=sql+",GurantorAdd='"+"'";
            if(insRS.getString("SUBSCRIBER_ID")!=null)
            sql= sql+",GurantorID='"+insRS.getString("SUBSCRIBER_ID")+"'";
            else sql=sql+",GurantorID='"+"'";
            if(insRS.getString("POLICY_HOLDER_DOB")!=null)
            sql= sql+",GurantorDOB='"+insRS.getString("POLICY_HOLDER_DOB")+"'";
            else sql=sql+",GurantorDOB='"+"'";
            if(insRS.getString("POLICY_HOLDER_SEX")!=null)
            sql= sql+",GurantorSex='"+insRS.getString("POLICY_HOLDER_SEX")+"'";
            else sql=sql+",GurantorSex='"+"'";
            if(insRS.getString("GROUPNO")!=null)
            sql= sql+",GroupNo='"+insRS.getString("GROUPNO")+"'";
            else sql=sql+",GroupNo='"+"'";
            if(insRS.getString("Policy_Holder_City")!=null)
            sql= sql+",GurantorCity='"+insRS.getString("Policy_Holder_City")+"'";
            else sql=sql+",GurantorCity='"+"'";
            if(insRS.getString("Policy_Holder_State")!=null)
            sql= sql+",GurantorState='"+insRS.getString("Policy_Holder_State")+"'";
            else sql=sql+",GurantorState='"+"'";
            if(insRS.getString("Policy_Holder_Zipcode")!=null)
            sql= sql+",GurantorZip='"+insRS.getString("Policy_Holder_Zipcode")+"'";
            else sql=sql+",GurantorZip='"+"'";
            if(insRS.getString("Policy_Holder_Phone")!=null)
            sql= sql+",GurantorPhone='"+replaceBrackets(insRS.getString("Policy_Holder_Phone"))+"'";
            else sql=sql+",GurantorPhone='"+"'";
            sql= sql+",AcceptsAssign='1'";
            sql= sql+",AnyBenfitPlan='0'";
            if(insRS.getString("Other_Holder_Name")!=null)
            sql= sql+",OtherInsuredName='"+insRS.getString("Other_Holder_Name")+"'";
            else sql=sql+",OtherInsuredName='"+"'";
            if(insRS.getString("Other_Holder_DOB")!=null)
            sql= sql+",OtherInsuredDOB='"+insRS.getString("Other_Holder_DOB")+"'";
            else sql=sql+",OtherInsuredDOB='"+"'";
            if(insRS.getString("Other_Holder_Sex")!=null)
            sql= sql+",OtherInsuredSex='"+insRS.getString("Other_Holder_Sex")+"'";
            else sql=sql+",OtherInsuredSex='"+"'";
            if(insRS.getString("Other_Holder_EmpSchool")!=null)
            sql= sql+",OtherInsuredCarrier='"+insRS.getString("Other_Holder_EmpSchool")+"'";
            else sql=sql+",OtherInsuredCarrier='"+"'";
            if(insRS.getString("Other_Holder_PlanName")!=null)
            sql= sql+",OtherInsuredPlanName='"+insRS.getString("Other_Holder_PlanName")+"'";
            else sql=sql+",OtherInsuredPlanName='"+"'";
            if(insRS.getString("SOFInsurance").equalsIgnoreCase("Y"))
            sql= sql+",SOFInsurance='"+"1'";
            else sql=sql+",SOFInsurance='"+"0'";
            if(insRS.getString("DOS")!=null)
            sql= sql+",DOIS='"+insRS.getString("DOS")+"'";
            else sql=sql+",DOIS='"+"'";
            sql= sql+",ClaimType='Paper'";
        }else{
            sql=sql+",PInsurancePlan='"+"'";
            sql=sql+",InsuranceID='"+"'";
            sql=sql+",INSURANCE_CLASS='"+"'";
            sql=sql+",InsuranceCompany='"+"'";
            sql=sql+",InsuranceAdd='"+"'";
            sql=sql+",InsuranceCity='"+"'";
            sql=sql+",InsuranceState='"+"'";
            sql=sql+",InsuranceZip='"+"'";
            sql=sql+",Relation='"+"'";
            sql=sql+",GurantorName='"+"'";
            sql=sql+",GurantorAdd='"+"'";
            sql=sql+",GurantorID='"+"'";
            sql=sql+",GurantorDOB='"+"'";
            sql=sql+",GurantorSex='"+"'";
            sql=sql+",GroupNo='"+"'";
            sql=sql+",GurantorCity='"+"'";
            sql=sql+",GurantorState='"+"'";
            sql=sql+",GurantorZip='"+"'";
            sql=sql+",GurantorPhone='"+"'";
            sql= sql+",AcceptsAssign='0'";
            sql= sql+",AnyBenfitPlan='0'";
            sql=sql+",OtherInsuredName='"+"'";
            sql=sql+",OtherInsuredDOB='"+"'";
            sql=sql+",OtherInsuredSex='"+"'";
            sql=sql+",OtherInsuredCarrier='"+"'";
            sql=sql+",OtherInsuredPlanName='"+"'";
            sql=sql+",SOFInsurance='"+"0'";
            sql=sql+",DOIS='"+"'";
            sql=sql+",ClaimType='"+"'";
        }

        insRS.close();
        //--- End patient insurance    ------------------------------------------//

        //--- Get Service Facility information    ----------------------------------//
        serFacility=con.prepareStatement(serFacilitySQL);
        serFacilityRS= serFacility.executeQuery();
        
        if(serFacilityRS.next()){
            if(serFacilityRS.getString("Name")!=null)
            sql= sql+",FacilityName='"+serFacilityRS.getString("Name")+"'";
            else sql=sql+",FacilityName='"+"'";
            if(serFacilityRS.getString("Address2")!=null)
            sql= sql+",FacilityAdd='"+serFacilityRS.getString("Address2")+"'";
            else sql=sql+",FacilityAdd='"+"'";
            if(serFacilityRS.getString("City")!=null)
            sql= sql+",FacilityCity='"+serFacilityRS.getString("City")+"'";
            else sql=sql+",FacilityCity='"+"'";
            if(serFacilityRS.getString("State")!=null)
            sql= sql+",FacilityState='"+serFacilityRS.getString("State")+"'";
            else sql=sql+",FacilityState='"+"'";
            if(serFacilityRS.getString("Zip")!=null)
            sql= sql+",FacilityZip='"+serFacilityRS.getString("Zip")+"'";
            else sql=sql+",FacilityZip='"+"'";
            if(serFacilityRS.getString("Phone")!=null)
            sql= sql+",FacilityPhone='"+replaceBrackets(serFacilityRS.getString("Phone"))+"'";
            else sql=sql+",FacilityPhone='"+"'";
            if(serFacilityRS.getString("NPI")!=null)
            sql= sql+",FacilityNpi='"+serFacilityRS.getString("NPI")+"'";
            else sql=sql+",FacilityNpi='"+"'";
            if(serFacilityRS.getString("FederalTaxId")!=null)
            sql= sql+",FacilityFederalID='"+serFacilityRS.getString("FederalTaxId")+"'";
            else sql=sql+",FacilityFederalID='"+"'";
        }else{
            sql=sql+",FacilityName='"+"'";
            sql=sql+",FacilityAdd='"+"'";
            sql=sql+",FacilityCity='"+"'";
            sql=sql+",FacilityState='"+"'";
            sql=sql+",FacilityZip='"+"'";
            sql=sql+",FacilityPhone='"+"'";
            sql=sql+",FacilityNpi='"+"'";
            sql=sql+",FacilityFederalID='"+"'";
        }

        serFacilityRS.close();
        //--- END service facility information    ----------------------------------//

        //--- Get Doctor information    -----------------------------------------------//
        selectDoc=con.prepareStatement(docSQL);
        docRS= selectDoc.executeQuery();
        
        if(docRS.next()){
            if(docRS.getString("DOCTOR_NAME")!=null)
            sql= sql+",ProviderName='"+docRS.getString("DOCTOR_NAME")+"'";
            else sql=sql+",ProviderName='"+"'";
            if(docRS.getString("CREDENTIALS")!=null)
            sql= sql+",ProviderDegree='"+docRS.getString("CREDENTIALS")+"'";
            else sql=sql+",ProviderDegree='"+"'";
            if(docRS.getString("FEDERALTAXID")!=null)
            sql= sql+",FederalTaxID='"+docRS.getString("FEDERALTAXID")+"'";
            else sql=sql+",FederalTaxID='"+"'";
            if(docRS.getString("TAXONOMY")!=null)
            sql= sql+",RENPROVIDERTAXONOMY='"+docRS.getString("TAXONOMY")+"'";
            else sql=sql+",RENPROVIDERTAXONOMY='"+"'";

        }else{
            sql=sql+",ProviderName='"+"'";
            sql=sql+",ProviderDegree='"+"'";
            sql=sql+",FederalTaxID='"+"'";
            sql=sql+",RENPROVIDERTAXONOMY='"+"'";
        }
        docRS.close();
        //--- END Doctor information    -----------------------------------------//

        //--- Get RefDoctor information    -----------------------------------------//
        refDoc=con.prepareStatement(refDOCSQL);
        refDocRS= refDoc.executeQuery(refDOCSQL);
        
        if(refDocRS.next()){
            if(refDocRS.getString("DOCTOR_NAME")!=null)
            sql= sql+",RefProviderName='"+refDocRS.getString("DOCTOR_NAME")+"'";
            else sql=sql+",'"+"'";
            if(refDocRS.getString("NPI")!=null)
            sql= sql+",NPIRefNo='"+refDocRS.getString("NPI")+"'";
            else sql=sql+",NPIRefNo='"+"'";
            if(refDocRS.getString("TAXONOMY")!=null)
            sql= sql+",RefProvTaxonomy='"+refDocRS.getString("TAXONOMY")+"'";
            else sql=sql+",RefProvTaxonomy='"+"'";
        }else{
            sql=sql+",RefProviderName='"+"'";
            sql=sql+",NPIRefNo='"+"'";
            sql=sql+",RefProvTaxonomy='"+"'";
        }

        refDocRS.close();
        //--- End RefDoctor information    ----------------------------------//

        //--- Get Bill Service information    ----------------------------------//
        billFacility=con.prepareStatement(billSerSQL);
        billSerRS= billFacility.executeQuery(billSerSQL);
        
        if(billSerRS.next()){
            if(billSerRS.getString("Name")!=null)
            sql= sql+",BillingProviderName='"+billSerRS.getString("Name")+"'";
            else sql=sql+",BillingProviderName='"+"'";
            if(billSerRS.getString("Address2")!=null)
            sql= sql+",BillingProviderAdd='"+billSerRS.getString("Address2")+"'";
            else sql=sql+",BillingProviderAdd='"+"'";
            if(billSerRS.getString("City")!=null)
            sql= sql+",BillingProviderCity='"+billSerRS.getString("City")+"'";
            else sql=sql+",BillingProviderCity='"+"'";
            if(billSerRS.getString("State")!=null)
            sql= sql+",BillingProviderState='"+billSerRS.getString("State")+"'";
            else sql=sql+",BillingProviderState='"+"'";
            if(billSerRS.getString("Zip")!=null)
            sql= sql+",BillingProviderZip='"+billSerRS.getString("Zip")+"'";
            else sql=sql+",BillingProviderZip='"+"'";
            if(billSerRS.getString("Phone")!=null)
            sql= sql+",BillingProviderPhone='"+replaceBrackets(billSerRS.getString("Phone"))+"'";
            else sql=sql+",BillingProviderPhone='"+"'";
            if(billSerRS.getString("FederalTaxId")!=null)
            sql= sql+",BIllingProviderFederalID='"+billSerRS.getString("FederalTaxId")+"'";
            else sql=sql+",BIllingProviderFederalID='"+"'";
            if(billSerRS.getString("NPI")!=null)
            sql= sql+",BillingProviderNPI='"+billSerRS.getString("NPI")+"'";
            else sql=sql+",BillingProviderNPI='"+"'";
            if(billSerRS.getString("TaxonomyID")!=null)
            sql= sql+",BillingProviderTaxonomy='"+billSerRS.getString("TaxonomyID")+"'";
            else sql=sql+",BillingProviderTaxonomy='"+"'";

            String str="Select * from BILL where BILLNo='"+claimNo+"' and CPT!=' '";
            selectBill=con.prepareStatement(str);
            int flag=0;
            billRS=selectBill.executeQuery();
            while(billRS.next()){
                if(billRS.getString("CPT").startsWith("8")){
                    flag=1;
                }
            }
            if(flag==1 && billSerRS.getString("CLIA_NO")!=null){
                sql= sql+",ProAuthorizationNo='"+billSerRS.getString("CLIA_NO")+"'";
            }else sql=sql+",ProAuthorizationNo='"+"'";            
        }else{
            sql=sql+",BillingProviderName='"+"'";
            sql=sql+",BillingProviderAdd='"+"'";
            sql=sql+",BillingProviderCity='"+"'";
            sql=sql+",BillingProviderState='"+"'";
            sql=sql+",BillingProviderZip='"+"'";
            sql=sql+",BillingProviderPhone='"+"'";
            sql=sql+",BIllingProviderFederalID='"+"'";
            sql=sql+",BillingProviderNPI='"+"'";
            sql=sql+",BillingProviderTaxonomy='"+"'";
            sql=sql+",ProAuthorizationNo='"+"'";
        }
        billSerRS.close();
        //--- END Bill Service information    ----------------------------------//

        //--- Get patient case information    ----------------------------------//
        selectCase=con.prepareStatement(caseSQl);
        caseRS= selectCase.executeQuery(caseSQl);
        
        if(caseRS.first()){
            if(caseRS.getString("CurrentIllnessDate")!=null)
            sql= sql+",CurrentIllnessDate='"+caseRS.getString("CurrentIllnessDate")+"'";
            else sql=sql+",'"+"'";
            if(caseRS.getString("SimiliarIllnessDate")!=null)
            sql= sql+",SimiliarIllnessDate='"+caseRS.getString("SimiliarIllnessDate")+"'";
            else sql=sql+",'"+"'";
            sql= sql+",MedicaidResubmission='"+"'";
            if(caseRS.getString("PatientUnableFromDate")!=null)
            sql= sql+",PatientUnableFromDate='"+caseRS.getString("PatientUnableFromDate")+"'";
            else sql=sql+",PatientUnableFromDate='"+"'";
            if(caseRS.getString("PatientUnableToDate")!=null)
            sql= sql+",PatientUnableToDate='"+caseRS.getString("PatientUnableToDate")+"'";
            else sql=sql+",PatientUnableToDate='"+"'";
            if(caseRS.getString("HospitalizationFromDate")!=null)
            sql= sql+",HospitalizationFromDate='"+caseRS.getString("HospitalizationFromDate")+"'";
            else sql=sql+",HospitalizationFromDate='"+"'";
            if(caseRS.getString("HospitalizationToDate")!=null)
            sql= sql+",HospitalizationToDate='"+caseRS.getString("HospitalizationToDate")+"'";
            else sql=sql+",HospitalizationToDate='"+"'";
            sql= sql+",Employment='"+caseRS.getString("Employment")+"'";
            sql= sql+",AutoAccident='"+caseRS.getString("AutoAccident")+"'";
            sql= sql+",OtherAccident='"+caseRS.getString("OtherAccident")+"'";
            if(caseRS.getString("Place")!=null)
            sql= sql+",Place='"+caseRS.getString("Place")+"'";
            else sql=sql+",Place='"+"'";
        }else{
            sql=sql+",CurrentIllnessDate='"+"'";
            sql=sql+",SimiliarIllnessDate='"+"'";
            sql=sql+",MedicaidResubmission='"+"'";
            sql=sql+",PatientUnableFromDate='"+"'";
            sql=sql+",PatientUnableToDate='"+"'";
            sql=sql+",HospitalizationFromDate='"+"'";
            sql=sql+",HospitalizationToDate='"+"'";
            sql= sql+",Employment='N'";
            sql= sql+",AutoAccident='N'";
            sql= sql+",OtherAccident='N'";
            sql=sql+",Place='"+"'";
        }
        sql= sql+",OutsideLab='N'";
        sql= sql+",ChargesOutsideLab='"+"'";
        //--- END patient case information    ----------------------------------//

        sql=sql+" where CLAIMID='" +CLAIMID+ "'";
        updateClaim=con.prepareStatement(sql);
        updateClaim.executeUpdate();        

        insRS.close();
        caseRS.close();
        serFacilityRS.close();
        billSerRS.close();
        docRS.close();
        refDocRS.close();
        billAmtRS.close();
        patRS.close();

        //*********************************** Update Cms_cpts ********************** //
        ResultSet billRS1;
        String billSQL;
        Statement stmtcms = null,stmt = null;
        stmt= con.createStatement();
        stmtcms=con.createStatement();
        billSQL="Select * from BILL where BillNo='"+claimNo+"'";
        selBill=con.prepareStatement(billSQL);
        billRS1=selBill.executeQuery();
        
      while(billRS1.next()){
        sql = "UPDATE CMS_CPTS set ";

        if(!visitDate.isEmpty()){
            if(billRS1.getString("CPT")!=null){
            sql= sql+"ServiceFromDate='"+visitDate+"'";
            }else sql=sql+"ServiceFromDate='"+"'";
        }else sql=sql+"ServiceFromDate='"+"'";
        if(!visitDate.isEmpty()){
            if(billRS1.getString("CPT")!=null){
                 sql= sql+",ServiceToDate='"+visitDate+"'";
            }else sql=sql+",ServiceToDate='"+"'";
        }else sql=sql+",ServiceToDate='"+"'";
        if(billRS1.getString("POS")!=null)
        sql= sql+",POS='"+billRS1.getString("POS")+"'";
        else sql=sql+",POS='"+"'";
        if(billRS1.getString("EMG")!=null)
        sql= sql+",EMG='"+billRS1.getString("EMG")+"'";
        else sql=sql+",EMG='"+"'";
        if(billRS1.getString("CPT")!=null)
        sql= sql+",CPT='"+billRS1.getString("CPT")+"'";
        else sql=sql+",CPT='"+"'";
        if(billRS1.getString("M1")!=null)
        sql= sql+",Modifier1='"+billRS1.getString("M1")+"'";
        else sql=sql+",Modifier1='"+"'";
        if(billRS1.getString("M2")!=null)
        sql= sql+",Modifier2='"+billRS1.getString("M2")+"'";
        else sql=sql+",Modifier2='"+"'";
        if(billRS1.getString("M3")!=null)
        sql= sql+",Modifier3='"+billRS1.getString("M3")+"'";
        else sql=sql+",Modifier3='"+"'";
        if(billRS1.getString("M4")!=null)
        sql= sql+",Modifier4='"+billRS1.getString("M4")+"'";
        else sql=sql+",Modifier4='"+"'";
        if(billRS1.getString("ICDPointer")!=null)
        sql= sql+",DiagnosisPointer='"+billRS1.getString("ICDPointer")+"'";
        else sql=sql+",DiagnosisPointer='"+"'";
        if(billRS1.getString("Charges")!=null)
        sql= sql+",Charges='"+billRS1.getString("Charges")+"'";
        else sql=sql+",Charges='"+"'";
        if(billRS1.getString("CPTUnits")!=null)
        sql= sql+",Units='"+billRS1.getString("CPTUnits")+"'";
        else sql=sql+",Units='"+"'";
        sql= sql+",EPSDT='"+"'";
        sql= sql+",Quality='"+"'";
        if(billRS1.getString("ProviderID")!=null)
        sql= sql+",ProviderID='"+billRS1.getString("ProviderID")+"'";
        else sql=sql+",ProviderID='"+"'";
        if(billRS1.getString("ProviderTaxonomy")!=null)
        sql= sql+",ProviderTaxonomy='"+billRS1.getString("ProviderTaxonomy")+"'";
        else sql=sql+",ProviderTaxonomy='"+"'";
        sql=sql+" where CLAIMID='"+billRS1.getString("BillNo")+insType+"' and DUMMYID='"+billRS1.getString("DUMMYID")+"'";
      
        updateCMSCpts=con.prepareStatement(sql);
        updateCMSCpts.executeUpdate();        
        }
        con.commit();
        con.setAutoCommit(true);

           return 1;
  }catch(Exception e){e.printStackTrace(); DB.closePMSConnection(con); return 0; }
   finally{
           //if(selectPcase=!null){selectPcase.close();}
           if(selectBillAmt!=null){selectBillAmt.close();}
           if(selectPat!=null){selectPat.close();}
           if(ins!=null){ins.close();}
           if(serFacility!=null){serFacility.close();}
           if(selectDoc!=null){selectDoc.close();}
           if(refDoc!=null){refDoc.close();}
           if(billFacility!=null){billFacility.close();}
           if(selectBill!=null){selectBill.close();}
           if(selectCase!=null){selectCase.close();}
           if(updateClaim!=null){updateClaim.close();}
           if(selBill!=null){selBill.close();}
           if(updateCMSCpts!=null){updateCMSCpts.close();}
       if(con!=null){
           con.close();
       }
   }
        
 }
}
