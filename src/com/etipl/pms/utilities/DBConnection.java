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

import com.etipl.pms.datalayer.GetSystemConfig;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

static final String configurationfilePath="./config/db.config";
String connectionString;
DBParameters dbParameter;
public static String sip="", sport="",dbname="",uname="",passwd="";

public Connection getVistAConnection()
{
    Connection con=null;
	try {
		connect_Setting("V");
		Class.forName("com.mysql.jdbc.Driver");
	        connectionString="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
        	con=DriverManager.getConnection(connectionString,uname,passwd);
            return con;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
    return con;
}

public boolean closeVistAConnection(Connection con) {
	try {

		if(con !=null && !con.isClosed()) {
			con.close();
            return true;
		}
            return false;       

	}
	catch(Exception e) {
		e.printStackTrace();
	}
    return false;
}

public Connection getPMSConnection()
{
    Connection con=null;
	try {
		connect_Setting("P");
		Class.forName("com.mysql.jdbc.Driver");                
        connectionString="jdbc:mysql://"+sip+":"+sport+"/"+dbname;
        con=DriverManager.getConnection(connectionString,uname,passwd);

        return con;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
    return con;
}

public boolean closePMSConnection(Connection con) {
	try {

		if(con !=null && !con.isClosed()) {
			con.close();
            return true;
		}
        return false;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
    return false;
}

 private static void connect_Setting(String which) {     
       GetSystemConfig ob=new GetSystemConfig();
       dbname=ob.getDbName(which);
       sip=ob.getDbIp(which);
       sport=ob.getDbPort(which);
       uname=ob.getUserName(which);
       passwd=ob.getPassword(which);
    }
}
