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

import com.etipl.pms.LoginScreen;
import com.etipl.pms.entity.PatientInformation;
import java.sql.*;
import javax.swing.JOptionPane;

public class Connect2MySql {
    public static String sip="", sport="",dbname="",uname="",passwd="";
    
    /**
     * Creates a new instance of Connect2MySql
     */
    public Connect2MySql(){
        connect_Setting("P");
    }
    
    public int storePatient(PatientInformation ob,boolean printpatinfo) {
        ResultSet rs;
        String sqlstring;
        Statement stmt;
               
        java.util.Date today;
        java.text.SimpleDateFormat formatter;
        today = new java.util.Date();
        formatter = new java.text.SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(today);        
        
        try{
                    
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
            Connection con =  DriverManager.getConnection(url,uname, passwd);
            stmt = con.createStatement();
            
            String temp="";
            String Next_tc="";
            int tc=0;
            sqlstring="lock tables JP_PAT_INFO write";
            sqlstring=sqlstring.toUpperCase();
            stmt.executeUpdate(sqlstring);
            
            sqlstring="SELECT  max(HRN) as MAXTC from JP_PAT_INFO ";
          
              rs=stmt.executeQuery(sqlstring);
              rs.first();
              if(rs.getString("MAXTC") != null){
                   temp=rs.getString("MAXTC");
                   tc=Integer.parseInt(temp);
                   tc++;
                   Next_tc=""+tc;              
             }else{
                Next_tc="100001";
             } 
           
            ob.setTcno(Next_tc);
            ob.setIpNo(Next_tc);
            String temphrn=Next_tc;
            
            if(ob.getDOB() == null){
                sqlstring="INSERT INTO JP_PAT_INFO (FNAME,LNAME,TC_NO,REGISTRATION_DATE,"
                +"SEX,HRN," 
                +"LADD1,LADD_CITY,LADD_STATE,LADD_PIN,LADD_COUNTRY," 
                +"PHONE,RELIGION,NATIONALITY,RACE,Occupation"
                +"Created_By,Created_DateTime,"
                +"SSN,TITLE, SALUTATION,MS,COMMENTS,WPHONE, CPHONE, EMAIL, EC, GUARANTOR, HOH,"
                +"REMARKS,Update_Count,MESSAGEPHONE,FAMILY_INCOME,FAMILY_MEMBERS,"
                +"LANGSPOKENATHOME,HIGHESTLEVELOFEDUCATION,MNAME,ETHNICITY,SOFHIPAA,SLIDINGCODE,DOHS)"
            
                +"VALUES('"+ob.getFirstName()+"','"+ob.getLastName()
                +"','"+temphrn
                +"','"+ob.getRegistrationDate()
                +"','"+ob.getSex()+"','"+temphrn
                
                +"','"+ob.getLocalAdd()+"','"+ob.getLocalCity()
                +"','"+ob.getLocalState()+"','"+ob.getLocalPin()+"','"+ob.getLocalCountry()
                
                +"','"+ob.getLocalphone()+"','"+ob.getReligion()
                +"','"+ob.getNationality()
                +"','"+ob.getrace()
                +"','"+ob.getoccupation()
                +"','"+LoginScreen.presentuser+"','"+date+"'"
                +",'"+ob.getSSN()+"','"+ob.getTitle()+"','"+ob.getSalutation()+"','"+ob.getMS()+"','"+ob.getComments()+"','"+ob.getWPhone()+"','"+ob.getCPhone()+"','"+ob.getEmail()+"','"+ob.getEC()+"','"+ob.getGuarantor()+"','"+ob.getHOH()
                +"','"+ob.getRemarks()+"','0','"+ob.getMessagePhone()+"','"+ob.getAnnualIncome()+"','"+ob.getFamilyMembers()+"','"
                +ob.getLangSpoken()+"','"+ob.getHighestLevelofEducation()+"'"
                +",'"+ob.getMiddleName()+"','"+ob.getEthnicity()+"','"+ob.getSOF()+"','"+ob.getSlidingCode()+"'";
                
                if(ob.getDOS() == null){
                    sqlstring=sqlstring+","+ob.getDOS()+"";
                }else{
                    sqlstring=sqlstring+",'"+ob.getDOS()+"'";
                }
                
               sqlstring=sqlstring+")";

            }else{
            sqlstring="INSERT INTO JP_PAT_INFO (FNAME,LNAME,TC_NO,REGISTRATION_DATE,"
                +"DOB,AGE,AGEUNITS,SEX,HRN," 
                +"LADD1,LADD_CITY,LADD_STATE,LADD_PIN,LADD_COUNTRY," 
                +"PHONE,RELIGION,NATIONALITY,RACE,Occupation"
                +",Created_By,Created_DateTime,"
                +"SSN,TITLE, SALUTATION,MS,COMMENTS,WPHONE, CPHONE, EMAIL, EC, GUARANTOR, HOH,"
                +"REMARKS,Update_Count,MESSAGEPHONE,FAMILY_INCOME,FAMILY_MEMBERS,LANGSPOKENATHOME,HIGHESTLEVELOFEDUCATION,MNAME,ETHNICITY,SOFHIPAA,SLIDINGCODE,DOHS)"
            
                +"VALUES('"+ob.getFirstName()+"','"+ob.getLastName()
                +"','"+temphrn
                +"','"+ob.getRegistrationDate()+"','"+ob.getDOB()+"','"+ob.getAge()+"','"+ob.getAgeUnits()
                +"','"+ob.getSex()+"','"+temphrn
                
                +"','"+ob.getLocalAdd()+"','"+ob.getLocalCity()
                +"','"+ob.getLocalState()+"','"+ob.getLocalPin()+"','"+ob.getLocalCountry()
                +"','"+ob.getLocalphone()+"','"+ob.getReligion()
                +"','"+ob.getNationality()
                +"','"+ob.getrace()
                +"','"+ob.getoccupation()
                
                +"','"+LoginScreen.presentuser+"','"+date 
                +"','"+ob.getSSN()+"','"+ob.getTitle()+"','"+ob.getSalutation()+"','"+ob.getMS()+"','"+ob.getComments()+"','"+ob.getWPhone()+"','"+ob.getCPhone()+"','"+ob.getEmail()+"','"+ob.getEC()+"','"+ob.getGuarantor()+"','"+ob.getHOH()
                +"','"+ob.getRemarks()+"','0','"+ob.getMessagePhone()+"','"+ob.getAnnualIncome()+"','"+ob.getFamilyMembers()+"','"
                    +ob.getLangSpoken()+"','"+ob.getHighestLevelofEducation()
                    +"','"+ob.getMiddleName()+"','"+ob.getEthnicity()+"','"+ob.getSOF()+"','"+ob.getSlidingCode()+"'";

                if(ob.getDOS() == null){
                    sqlstring=sqlstring+","+ob.getDOS()+"";
                }else{
                    sqlstring=sqlstring+",'"+ob.getDOS()+"'";
                }

                 sqlstring=sqlstring+")";
               
            }  
            sqlstring=sqlstring.toUpperCase();
            stmt.executeUpdate(sqlstring);
                       
            sqlstring="unlock tables";
            sqlstring=sqlstring.toUpperCase();
            stmt.executeUpdate(sqlstring);           
             

            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();}
            
            return 1;
        }catch(Exception sql){
            sql.printStackTrace();            
            try
            {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
            Connection con =  DriverManager.getConnection(url,uname, passwd);
            Statement stmt1 = con.createStatement();
            
            sqlstring="unlock tables";
            sqlstring=sqlstring.toUpperCase();
            stmt1.executeUpdate(sqlstring);
            
            }
            catch(Exception sql1)
            {
                sql1.printStackTrace();
            }
            return 0;
        }
    }
      
      public static String Get_Next_TC(){
        String Next_tc="";
        int tc=0;
        String temp="";       
        ResultSet rs;
        Statement stmt;  
        String SqlString="";
        Connection con = null;
       
        SqlString="SELECT  max(HRN) as MAXTC from JP_PAT_INFO ";
          
        try{
              Class.forName("com.mysql.jdbc.Driver");
              String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
              con =  DriverManager.getConnection(url,uname, passwd);
            
              stmt = con.createStatement();
              rs=stmt.executeQuery(SqlString);
              rs.first();
              if(rs.getString("MAXTC") != null){
                   temp=rs.getString("MAXTC");
                   tc=Integer.parseInt(temp);
                   tc++;
                   Next_tc=""+tc;
             }else{
                Next_tc="1";
             }

            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}
            if(con!=null){con.close();}
              
          }catch(Exception e){
            e.printStackTrace();
        } 
        return Next_tc;
 }
      public String[] getStates(){
            String[] ob;
            ResultSet rs;
            Statement stmt;
            String SqlString="";
            int i=1;
            Connection con = null;
            SqlString="SELECT  * from STATES";
          
        try{
              Class.forName("com.mysql.jdbc.Driver");
              String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
              con =  DriverManager.getConnection(url,uname, passwd);
            
              stmt = con.createStatement();
              rs=stmt.executeQuery(SqlString);
              rs.first();
              if(rs.getRow()>0){
                do{
                   i++;
                }while(rs.next());
            }
            
            ob=new String[i];  
            i=0;
            rs.first();
            ob[0]="Select State";
            i++;
            if(rs.getRow()>0)
            {     
                 do{ 
                     ob[i]=rs.getString("ID")+", "+rs.getString("NAME");
                     i++;    
                 }while(rs.next());
            }

            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}
            if(con!=null){con.close();}
            
            return ob;
        }catch(Exception e){
            e.printStackTrace();
        } 
          return null;
      }
      
      
      public PatientInformation getPatDetails(String tcno){
          PatientInformation ob=new PatientInformation();
          ResultSet rs;
          Statement stmt;
          String sqlstring;

          try{
              Class.forName("com.mysql.jdbc.Driver");
              String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
              Connection con =  DriverManager.getConnection(url,uname, passwd);
              sqlstring="SELECT  * from JP_PAT_INFO where HRN like '"+tcno+"'";
              stmt = con.createStatement();
              rs=stmt.executeQuery(sqlstring);
              rs.first();
              if(rs.getRow()>0){
            
                        ob.setTcno(tcno);
                        ob.setIpNo(rs.getString("HRN"));
                        ob.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                        ob.setRefered(rs.getString("REFERED"));
                        ob.setReferedByName(rs.getString("REFERED_BY_NAME"));
                        ob.setLastName(rs.getString("LNAME"));
                        ob.setFirstName(rs.getString("FNAME"));
                        ob.setSex(rs.getString("SEX"));
                        ob.setKinName(rs.getString("KINNAME"));
                        ob.setAge(rs.getString("AGE"));
                        ob.setAgeUnits(rs.getString("AGEUNITS"));
                        ob.setDOB(rs.getString("DOB"));
                        ob.setMLC(rs.getString("MLC"));
                        
                        ob.setCo(rs.getString("CO"));
                        ob.setCoName(rs.getString("CONAME"));
                        ob.setPatientType(rs.getString("PATIENT_TYPE"));
                        ob.setRemarks(rs.getString("REMARKS"));
                        ob.setModeOfInjury(rs.getString("MOI"));
                        
                        ob.setBBName(rs.getString("BROUGHT_BY_NAME"));
                        ob.setBBAddress(rs.getString("BROUGHT_BY_ADD"));
                        ob.setBBPhone(rs.getString("BROUGHT_BY_PHONE"));
                        
                        ob.setLocalAdd(rs.getString("LADD1"));
                        ob.setLocalAdd2(rs.getString("LADD2"));
                        ob.setLocalCity(rs.getString("LADD_CITY"));
                        ob.setLocalCountry(rs.getString("LADD_COUNTRY"));
                        ob.setLocalPin(rs.getString("LADD_PIN"));
                        ob.setLocalState(rs.getString("LADD_STATE"));
                        ob.setLocalphone(rs.getString("PHONE"));
                        
                        ob.setPermanentAdd(rs.getString("PADD1"));
                        ob.setPermanentAdd2(rs.getString("PADD2"));
                        ob.setPermanentCity(rs.getString("PADD_CITY"));
                        ob.setPermanentCountry(rs.getString("PADD_COUNTRY"));
                        ob.setPermanentPin(rs.getString("PADD_PIN"));
                        ob.setPermanentState(rs.getString("PADD_STATE"));
                        ob.setPermanentphone(rs.getString("PPHONE"));
                        ob.setReligion(rs.getString("RELIGION"));
                        ob.setNationality(rs.getString("NATIONALITY"));
                        ob.setrace(rs.getString("RACE"));
                        ob.setoccupation(rs.getString("Occupation"));
                        ob.setSSN(rs.getString("SSN"));
                        ob.setTitle(rs.getString("TITLE")+"");
                        ob.setSalutation(rs.getString("SALUTATION"));
                        ob.setMS(rs.getString("MS"));
                        ob.setComments(rs.getString("Comments"));
                        ob.setWPhone(rs.getString("WPhone"));
                        ob.setCPhone(rs.getString("CPhone"));
                        ob.setEmail(rs.getString("Email"));
                        ob.setEC(rs.getString("EC"));
                        ob.setGuarantor(rs.getString("Guarantor"));
                        ob.setHOH(rs.getString("HOH"));
                        ob.setMessagePhone(rs.getString("MESSAGEPHONE"));
                        ob.setAnnualIncome(rs.getString("FAMILY_INCOME"));
                        ob.setFamilyMembers(rs.getString("FAMILY_MEMBERS"));
                        ob.setLangSpoken(rs.getString("LANGSPOKENATHOME"));
                        ob.setHighestLevelofEducation(rs.getString("HIGHESTLEVELOFEDUCATION"));
                        ob.setMiddleName(rs.getString("MNAME"));
                        ob.setEthnicity(rs.getString("ETHNICITY"));
                                                
                        return ob;
              }
              
            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}
            if(con!=null){con.close();}
              
         }catch(Exception e){
            e.printStackTrace();
         }
        
          return null; 
      }
      
       public PatientInformation getPatDetailsByHrn(String hrn){
          PatientInformation ob=new PatientInformation();
          ResultSet rs;
          Statement stmt;
          String sqlstring;
          try{
              Class.forName("com.mysql.jdbc.Driver");
              String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
              Connection con =  DriverManager.getConnection(url, uname, passwd);
              sqlstring="SELECT  * from JP_PAT_INFO where HRN like '"+hrn+"'";
              stmt = con.createStatement();
              rs=stmt.executeQuery(sqlstring);
              rs.first();
              if(rs.getRow()>0){            
                 
                      ob.setTcno(rs.getString("TCY")+rs.getString("HRN"));
                        ob.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                        ob.setIpNo(rs.getString("HRN"));
                        ob.setRefered(rs.getString("REFERED"));
                        ob.setReferedByName(rs.getString("REFERED_BY_NAME"));
                        ob.setLastName(rs.getString("LNAME"));
                        ob.setFirstName(rs.getString("FNAME"));
                        ob.setSex(rs.getString("SEX"));
                        ob.setKinName(rs.getString("KINNAME"));
                        ob.setAge(rs.getString("AGE"));
                        ob.setAgeUnits(rs.getString("AGEUNITS"));
                        ob.setDOB(rs.getString("DOB"));
                        ob.setMLC(rs.getString("MLC"));
                        ob.setCo(rs.getString("CO"));
                        ob.setCoName(rs.getString("CONAME"));
                        ob.setPatientType(rs.getString("PATIENT_TYPE"));
                        ob.setRemarks(rs.getString("REMARKS"));
                        ob.setModeOfInjury(rs.getString("MOI"));
                        
                        ob.setBBName(rs.getString("BROUGHT_BY_NAME"));
                        ob.setBBAddress(rs.getString("BROUGHT_BY_ADD"));
                        ob.setBBPhone(rs.getString("BROUGHT_BY_PHONE"));
                        
                        ob.setLocalAdd(rs.getString("LADD1"));
                        ob.setLocalAdd2(rs.getString("LADD2"));
                        ob.setLocalCity(rs.getString("LADD_CITY"));
                        ob.setLocalCountry(rs.getString("LADD_COUNTRY"));
                        ob.setLocalPin(rs.getString("LADD_PIN"));
                        ob.setLocalState(rs.getString("LADD_STATE"));
                        ob.setLocalphone(rs.getString("PHONE"));
                        
                        ob.setPermanentAdd(rs.getString("PADD1"));
                        ob.setPermanentAdd2(rs.getString("PADD2"));
                        ob.setPermanentCity(rs.getString("PADD_CITY"));
                        ob.setPermanentCountry(rs.getString("PADD_COUNTRY"));
                        ob.setPermanentPin(rs.getString("PADD_PIN"));
                        ob.setPermanentState(rs.getString("PADD_STATE"));
                        ob.setPermanentphone(rs.getString("PPHONE"));
                        ob.setReligion(rs.getString("RELIGION"));
                        ob.setNationality(rs.getString("NATIONALITY"));
                        ob.setrace(rs.getString("RACE"));
                        ob.setoccupation(rs.getString("Occupation"));
                        ob.setSSN(rs.getString("SSN"));
                        ob.setTitle(rs.getString("TITLE")+"");
                        ob.setSalutation(rs.getString("SALUTATION"));
                        ob.setMS(rs.getString("MS"));
                        ob.setComments(rs.getString("Comments"));
                        ob.setWPhone(rs.getString("WPhone"));
                        ob.setCPhone(rs.getString("CPhone"));
                        ob.setEmail(rs.getString("Email"));
                        ob.setEC(rs.getString("EC"));
                        ob.setGuarantor(rs.getString("Guarantor"));
                        ob.setHOH(rs.getString("HOH"));
                        
                        ob.setMessagePhone(rs.getString("MESSAGEPHONE"));
                        ob.setAnnualIncome(rs.getString("FAMILY_INCOME"));
                        ob.setFamilyMembers(rs.getString("FAMILY_MEMBERS"));
                        ob.setLangSpoken(rs.getString("LANGSPOKENATHOME"));
                        ob.setHighestLevelofEducation(rs.getString("HIGHESTLEVELOFEDUCATION"));
                        ob.setMiddleName(rs.getString("MNAME"));
                        ob.setEthnicity(rs.getString("ETHNICITY"));
                        ob.setSOF(rs.getString("SOFHIPAA"));
                        ob.setDOS(rs.getString("DOHS"));                        
              }else{
                  JOptionPane.showMessageDialog(null,"No Patient with HRN "+hrn);
              }
            
            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}
            if(con!=null){con.close();}
              
         }catch(Exception e){
            e.printStackTrace();
         }        
          return ob; 
          
      }
       public int updatePatient(PatientInformation ob,boolean printpatinfo) {
        ResultSet rs;
        String mdatetime;
        String sqlstring;
        Statement stmt;
        String temp="";
        int updatecount=0;
        java.util.Date today;
        java.text.SimpleDateFormat formatter;
        today = new java.util.Date();
        formatter = new java.text.SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(today);
        mdatetime="99991231235959";
        try{
                    
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
            Connection con =  DriverManager.getConnection(url, uname, passwd);
            stmt = con.createStatement();
            
            
            sqlstring="SELECT  Update_Count from JP_PAT_INFO where HRN like '"+ob.getTcno()+"'";
            stmt = con.createStatement();
            rs=stmt.executeQuery(sqlstring);
            rs.first();
            if(rs.getRow()>0){
                  temp=rs.getString("Update_Count");
                  updatecount=Integer.parseInt(temp);
                  updatecount++;
            }
           if(ob.getDOB() == null){
            sqlstring="UPDATE JP_PAT_INFO "
                +"SET FNAME='"+ob.getFirstName()
                +"',HRN='"+ob.getIpNo()
                +"',ageunits='"+ob.getAgeUnits()
                
                +"',LNAME='"+ob.getLastName()+"', REGISTRATION_DATE='"+ob.getRegistrationDate()+"',"
                +"DOB=NULL, AGE=NULL, SEX='"+ob.getSex()+"'," 
                
                +"LADD1='"+ob.getLocalAdd()+"'," 
                +"LADD_CITY='"+ob.getLocalCity()+"', LADD_STATE='"+ob.getLocalState()+"'," 
                +"LADD_PIN='"+ob.getLocalPin()+"', LADD_COUNTRY='"+ob.getLocalCountry()+"'," 
                    
                +"PHONE='"+ob.getLocalphone()+"',PPHONE='"+ob.getPermanentphone()+"'," 
                +"RELIGION='"+ob.getReligion()+"',NATIONALITY='"+ob.getNationality()+"'," 
                +"RACE='"+ob.getrace()+"',Occupation='"+ob.getoccupation()+"'," 
                +"Updated_By='"+LoginScreen.presentuser+"' ," 
                +"Update_Count='"+updatecount+"',"    
                +"MDATETIME='"+mdatetime+"',"    
                +"SSN='"+ob.getSSN()+"',Title='"+ob.getTitle()+"',Salutation='"+ob.getSalutation()+"',MS='"+ob.getMS()+"',Comments='"+ob.getComments()+"',WPhone='"+ob.getWPhone()+"',CPhone='"+ob.getCPhone()+"',Email='"+ob.getEmail()+"',EC='"+ob.getEC()+"',Guarantor='"+ob.getGuarantor()+"',HOH='"+ob.getHOH()    
                +",MESSAGEPHONE='"+ob.getMessagePhone()+"',FAMILY_INCOME='"+ob.getAnnualIncome()+"',FAMILY_MEMBERS='"+ob.getFamilyMembers()+"',LANGSPOKENATHOME='"+ob.getLangSpoken()+"',HIGHESTLEVELOFEDUCATION='"+ob.getHighestLevelofEducation()+""
                +"',MNAME='"+ob.getMiddleName()+""        
                +"',ETHNICITY='"+ob.getEthnicity()+"', SOFHIPAA='"+ob.getSOF()+"', SLIDINGCODE='"+ob.getSlidingCode()+"'";
            
                if(ob.getDOS()==null){
                   sqlstring=sqlstring+", DOHS="+ob.getDOS();
                }else{
                    sqlstring=sqlstring+", DOHS='"+ob.getDOS()+"'";
                }
                sqlstring=sqlstring+",Updated_DateTime='"+date+"' , Comments='"+ob.getComments()+"', REMARKS='"+ob.getRemarks()+"' where HRN='"+ob.getTcno()+"'";
                
            
           }else{
                sqlstring="UPDATE JP_PAT_INFO"
                +" SET FNAME='"+ob.getFirstName()
                +"',HRN='"+ob.getIpNo()
                +"',ageunits='"+ob.getAgeUnits()
                +"',LNAME='"+ob.getLastName()+"', REGISTRATION_DATE='"+ob.getRegistrationDate()+"',"
                +"DOB='"+ob.getDOB()+"', AGE='"+ob.getAge()+"',SEX='"+ob.getSex()+"'," 
                    
                +"LADD1='"+ob.getLocalAdd()+"'," 
                +"LADD_CITY='"+ob.getLocalCity()+"', LADD_STATE='"+ob.getLocalState()+"'," 
                +"LADD_PIN='"+ob.getLocalPin()+"', LADD_COUNTRY='"+ob.getLocalCountry()+"'," 
                
                    
                +"PHONE='"+ob.getLocalphone()+"',PPHONE='"+ob.getPermanentphone()+"'," 
                +"RELIGION='"+ob.getReligion()+"',NATIONALITY='"+ob.getNationality()+"'," 
                 +"RACE='"+ob.getrace()+"',Occupation='"+ob.getoccupation()+"'," 
                +"Updated_By='"+LoginScreen.presentuser+"' ," 
                +"Update_Count='"+updatecount+"',"    
                +"MDATETIME='"+mdatetime+"',"    
                +"SSN='"+ob.getSSN()+"',Title='"+ob.getTitle()+"',Salutation='"+ob.getSalutation()+"',MS='"+ob.getMS()+"',Comments='"+ob.getComments()+"',WPhone='"+ob.getWPhone()+"',CPhone='"+ob.getCPhone()+"',Email='"+ob.getEmail()+"',EC='"+ob.getEC()+"',Guarantor='"+ob.getGuarantor()+"',HOH='"+ob.getHOH()+"'"    
                 +",MESSAGEPHONE='"+ob.getMessagePhone()+"',FAMILY_INCOME='"+ob.getAnnualIncome()+"',FAMILY_MEMBERS='"+ob.getFamilyMembers()+"',LANGSPOKENATHOME='"+ob.getLangSpoken()+"',HIGHESTLEVELOFEDUCATION='"+ob.getHighestLevelofEducation()+""
                +"',MNAME='"+ob.getMiddleName()+""    
                +"',ETHNICITY='"+ob.getEthnicity()+"', SOFHIPAA='"+ob.getSOF()+"', SLIDINGCODE='"+ob.getSlidingCode()+"'";
                
                if(ob.getDOS()==null){
                    sqlstring=sqlstring+", DOHS="+ob.getDOS();
                }else{
                    sqlstring=sqlstring+", DOHS='"+ob.getDOS()+"'";
                }
                
                sqlstring=sqlstring+",Updated_DateTime='"+date+"' , Comments='"+ob.getComments()+"' , REMARKS='"+ob.getRemarks()+"' where HRN='"+ob.getTcno()+"'";
               
           }
            System.out.println(sqlstring);
            sqlstring=sqlstring.toUpperCase();
            stmt.executeUpdate(sqlstring);            
            
            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}
            if(con!=null){con.close();}
            
            return 1;
        }catch(Exception sql){
            sql.printStackTrace();
            return 0;
        }
    }
       public boolean checkTcNo(String tcno){
        ResultSet rs;
        Statement stmt;
        String SqlString="";
        SqlString="SELECT  HRN as TCNO, TCY as TCY from JP_PAT_INFO where HRN ='"+tcno+"'";
          
        try{
              Class.forName("com.mysql.jdbc.Driver");
              String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
              Connection con =  DriverManager.getConnection(url,uname, passwd);
            
              stmt = con.createStatement();
              rs=stmt.executeQuery(SqlString);
              rs.first();
              if(rs.getString("TCNO") != null){
                  JOptionPane.showMessageDialog(null,"TC No. "+tcno+" already exist");
                  return false;
              }
              }catch(Exception e){
                    e.printStackTrace();
              } 
        return true;
    }
      public static String Get_Next_HRN(){
        String TCN;
        String Next_tc="";
        int tc=0;
        String temp="";
       
        ResultSet rs;
        Statement stmt;
  
        String SqlString="";
        SqlString="SELECT max(HRN) as MAXTC from JP_PAT_INFO ";
        
        try{
              Class.forName("com.mysql.jdbc.Driver");
              String url ="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
              Connection con =  DriverManager.getConnection(url,uname, passwd);
            
              stmt = con.createStatement();
              rs=stmt.executeQuery(SqlString);
              rs.first();
              if(rs.getString("MAXTC") != null){
                   temp=rs.getString("MAXTC");
                   tc=Integer.parseInt(temp);
                   tc++;
                   Next_tc=""+tc;
              
             }else{
                Next_tc="100001";
             } 


            if(stmt!=null){stmt.close();}
            if(rs!=null){rs.close();}
            if(con!=null){con.close();}

          }catch(Exception e){
            e.printStackTrace();
        } 
        return Next_tc;
 }
      
    private static void connect_Setting(String which)
    {
       GetSystemConfig ob=new GetSystemConfig();
       dbname=ob.getDbName(which);
       sip=ob.getDbIp(which);
       sport=ob.getDbPort(which);
       uname=ob.getUserName(which);
       passwd=ob.getPassword(which);
    }
}
