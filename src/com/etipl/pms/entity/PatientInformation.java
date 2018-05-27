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


package com.etipl.pms.entity;

public class PatientInformation {
    private String ien;
    private String tcno;
    private String mlc;
    private String hrn;
    private String refered;
    private String lastname;
    private String firstname;
    private String age;
    private String ageunits="0";
    private String sex;
    private String dob;
    private String kinname;

    private String co;
    private String coname;

    private String bbname;
    private String bbphone;
    private String bbadd;

    private String remarks;

    private String ladd1;
    private String ladd2;
    private String lcity;
    private String lstate;
    private String lcountry;
    private String lpin;
    private String lphone;

    private String padd1;
    private String padd2;
    private String pcity;
    private String pstate;
    private String pcountry;
    private String ppin;
    private String pphone;

    private String nationality;
    private String religion;

    private String patienttype;
    private String modeofinjury;
    private String registerdate;
    private String referedbyname;
    private String ipno;
    private String race;
    private String occupaton;
    private String MessagePhone;
    private String AnnualIncome;
    private String FamilyMembers;
    private String LangSpoken;
    private String HighestLevelofEducation;
    private String SSN, Title, Salutation;
    private String MS, Comments, WPhone, CPhone, Email, EC, Guarantor, HOH;
    private String MiddleName;
    private String Ethnicity;

    private String mSOF;
    private String mDOS;

    private String mSlidingCode;

    public void setSlidingCode(String d){this.mSlidingCode=d;}
    public String getSlidingCode(){return this.mSlidingCode;}

    public void setDOS(String d){this.mDOS=d;}
    public String getDOS(){return this.mDOS;}

    public void setHRN(String h){this.hrn=h;}
    public String getHRN(){return this.hrn;}

    public void setSOF(String d){
                        this.mSOF=d;
            }
    public String getSOF(){return this.mSOF;}


    public void setEthnicity(String Ethnicity){
        this.Ethnicity=Ethnicity;
    }

    public String getEthnicity(){
        return this.Ethnicity;
    }

    public void setMiddleName(String MiddleName){
        this.MiddleName=MiddleName;
    }

    public String getMiddleName(){
        return this.MiddleName;
    }

    public void setMessagePhone(String MessagePhone){
        this.MessagePhone=MessagePhone;
    }

    public String getMessagePhone(){
        return this.MessagePhone;
    }

    public void setAnnualIncome(String AnnualIncome){
        this.AnnualIncome=AnnualIncome;
    }

    public String getAnnualIncome(){
        return this.AnnualIncome;
    }

    public void setFamilyMembers(String FamilyMembers){
        this.FamilyMembers=FamilyMembers;
    }

    public String getFamilyMembers(){
        return this.FamilyMembers;
    }

    public void setLangSpoken(String LangSpoken){
        this.LangSpoken=LangSpoken;
    }

    public String getLangSpoken(){
        return this.LangSpoken;
    }

    public String getHighestLevelofEducation(){
        return this.HighestLevelofEducation;
    }

    public void setHighestLevelofEducation(String HighestLevelofEducation){
        this.HighestLevelofEducation=HighestLevelofEducation;
    }

    public void setrace(String d){
        this.race=d;
    }
    public void setoccupation(String d){
        this.occupaton=d;
    }
    public void setIpNo(String d){
        this.ipno=d;
    }

    public String getIpNo(){
        return this.ipno;
    }

    public void setKinName(String d){
        this.kinname=d;
    }
    public String getKinName(){
        return this.kinname;
    }
    public void setRegistrationDate(String d){
        this.registerdate=d;
    }
    public void setRefered(String d){
        this.refered=d;
    }
    public String getRefered(){
        return this.refered;
    }

    public void setReferedByName(String d){
        this.referedbyname=d;
    }
    public String getReferedByName(){
        return this.referedbyname;
    }

    public void setIEN(String i){
        this.ien=i;
    }
    public String getIEN(){
        return this.ien;
    }

    public String getRegistrationDate(){
        return this.registerdate;
    }
     public void setModeOfInjury(String d){
        this.modeofinjury=d;
    }
    public String getModeOfInjury(){
        return this.modeofinjury;
    }
    public void setRemarks(String re){
        this.remarks=re;
    }
    public String getRemarks(){
        return this.remarks;
    }
    public void setTcno(String tn){
        this.tcno=tn;
    }
    public void setMLC(String mc){
        this.mlc=mc;
    }


    public void setLastName(String name){
        this.lastname=name;
    }
    public void setFirstName(String name){
        this.firstname=name;
    }
    public void setAge(String ag){
        this.age=ag;
    }
    public void setAgeUnits(String ag){
        this.ageunits=ag;
    }
    public void setDOB(String d){
        this.dob=d;
    }
    public String getDOB(){
        return this.dob;
    }
    public void setSex(String s){
        this.sex=s;
    }


    public void setCo(String c){
        this.co=c;
    }
    public void setCoName(String name){
        this.coname=name;
    }


    public void setBBPhone(String ph){
        this.bbphone=ph;
    }
    public void setBBName(String name){
        this.bbname=name;
    }
    public void setBBAddress(String add){
        this.bbadd=add;
    }


    public void setLocalAdd(String add1){
        this.ladd1=add1;
    }
    public void setLocalAdd2(String add2){
        this.ladd2=add2;
    }
    public void setLocalCity(String city){
        this.lcity=city;
    }
    public void setLocalState(String state){
        this.lstate=state;
    }
    public void setLocalCountry(String country){
        this.lcountry=country;
    }
    public void setLocalPin(String pin){
        this.lpin=pin;
    }
    public void setLocalphone(String ph){
        this.lphone=ph;
    }

    public void setPermanentAdd(String add1){
        this.padd1=add1;
    }
    public void setPermanentAdd2(String add2){
        this.padd2=add2;
    }
    public void setPermanentCity(String city){
        this.pcity=city;
    }
    public void setPermanentState(String state){
        this.pstate=state;
    }
    public void setPermanentCountry(String country){
        this.pcountry=country;
    }
    public void setPermanentPin(String pin){
        this.ppin=pin;
    }
    public void setPermanentphone(String ph){
        this.pphone=ph;
    }
    public void setPatientType(String pt){
        this.patienttype=pt;
    }

    public String getPatientType(){
        return this.patienttype;
    }
    public void setReligion(String rel){
        this.religion=rel;
    }
    public void setNationality(String nationality){
        this.nationality=nationality;
    }

    public String getrace(){
        return this.race;
    }
    public String getoccupation(){
        return this.occupaton;
    }
    public String getTcno(){
        return this.tcno;
    }
    public String getMLC(){
        return this.mlc;
    }
    public String getLastName(){
        return this.lastname;
    }
    public String getFirstName(){
        return this.firstname;
    }
    public String getAge(){
        return this.age;
    }
    public String getAgeUnits(){
        return this.ageunits;
    }
    public String getSex(){
        return this.sex;
    }

    public String getCo(){
        return this.co;
    }
    public String getCoName(){
        return this.coname;
    }

    public String getBBPhone(){
        return this.bbphone;
    }
    public String getBBName(){
        return this.bbname;
    }
    public String getBBAddress(){
        return this.bbadd;
    }

    public String getLocalAddress(){
        return this.ladd1+"^"+this.ladd2+"^"+this.lcity+"^"+this.lstate+"^"+this.lcountry+"^"+this.lpin;
    }

    public String getLocalAdd(){
        return this.ladd1;
    }
    public String getLocalAdd2(){
        return this.ladd2;
    }
    public String getLocalCity(){
        return this.lcity;
    }
    public String getLocalState(){
        return this.lstate;
    }
    public String getLocalCountry(){
        return this.lcountry;
    }
    public String getLocalPin(){
        return this.lpin;
    }
    public String getLocalphone(){
        return this.lphone;
    }

    public String getPermanentAddress(){
        return this.padd1+"^"+this.padd2+"^"+this.pcity+"^"+this.pstate+"^"+this.pcountry+"^"+this.ppin;
    }

    public String getPermanentAdd(){
        return this.padd1;
    }
    public String getPermanentAdd2(){
        return this.padd2;
    }
    public String getPermanentCity(){
        return this.pcity;
    }
    public String getPermanentState(){
        return this.pstate;
    }
    public String getPermanentCountry(){
        return this.pcountry;
    }
    public String getPermanentPin(){
        return this.ppin;
    }
    public String getPermanentphone(){
        return this.pphone;
    }



    public String getReligion(){
        return this.religion;
    }
    public String getNationality(){
        return this.nationality;
    }

    public String getSSN() {
        return this.SSN;
    }

    public void setSSN(String SSN){
        this.SSN=SSN;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title){
        this.Title=Title;
    }

    public String getSalutation() {
        return this.Salutation;
    }

    public void setSalutation(String Salutation){
        this.Salutation=Salutation;
    }

    public String getMS() {
        return this.MS;
    }

    public void setMS(String MS){
        this.MS=MS;
    }

    public String getComments() {
        return this.Comments;
    }

    public void setComments(String Comments){
        this.Comments=Comments;
    }

    public String getWPhone() {
        return this.WPhone;
    }

    public void setWPhone(String WPhone){
        this.WPhone=WPhone;
    }

    public String getCPhone() {
        return this.CPhone;
    }

    public void setCPhone(String CPhone){
        this.CPhone=CPhone;
    }

    public String getEmail() {
        return this.Email.toLowerCase();
    }

    public void setEmail(String Email){
        this.Email=Email;
    }
    public String getEC() {
        return this.EC;
    }

    public void setEC(String EC){
        this.EC=EC;
    }

    public String getGuarantor() {
        return this.Guarantor;
    }

    public void setGuarantor(String Guarantor){
        this.Guarantor=Guarantor;
    }
    public String getHOH() {
        return this.HOH;
    }

    public void setHOH(String HOH){
        this.HOH=HOH;
    }
}
