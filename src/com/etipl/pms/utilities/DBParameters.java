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



package com.etipl.pms.utilities;

/**
 *
 * @author MUTHU
 */
public class DBParameters {

private String mSip="";
private String mSport="";
private String mDBName="";
private String mMysqlSQLUName="";
private String mMySQLPassword="";

public void setSip(String sip){ this.mSip=sip;}
public void setSport(String sport){this.mSport=sport;}
public void setDBName(String dbname){this.mDBName=dbname;}
public void setMysqlSQLUName(String mysqlSQLUName){this.mMysqlSQLUName=mysqlSQLUName;}
public void setMySQLPassword(String mySQLPassword){this.mMySQLPassword=mySQLPassword;}

public String getSip(){return this.mSip;}
public String getSport(){return this.mSport;}
public String getDBName(){return this.mDBName;}
public String getMysqlSQLUName(){return this.mMysqlSQLUName;}
public String getMySQLPassword(){return this.mMySQLPassword;}

        
}

