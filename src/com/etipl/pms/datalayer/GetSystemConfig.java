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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GetSystemConfig {
      
    public static String getDbName(String which){
        File file = new File("./Settings/db.config");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;
        String dbname="";
    try{
            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null){
                    contents.append(text)
                        .append(System.getProperty(
                            "line.separator"));   
                    
                    if(which.equalsIgnoreCase("P")){                        
                            if(text.startsWith("<DatabaseP>")){
                                dbname=text;
                                 break;
                            }                       
                    }                    
                    if(which.equalsIgnoreCase("V")){                        
                            if(text.startsWith("<DatabaseV>")){
                                dbname=text;
                                 break;
                            }                        
                    }                    
            }
            dbname=dbname.replaceAll("<DatabaseP>","");
            dbname=dbname.replaceAll("</DatabaseP>","");
            
            dbname=dbname.replaceAll("<DatabaseV>","");
            dbname=dbname.replaceAll("</DatabaseV>","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return dbname;
    }
    public static String getDbIp(String which){
        File file = new File("./Settings/db.config");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;
        String dbip="";
    try{
            reader = new BufferedReader(new FileReader(file));       

                while ((text = reader.readLine()) != null ){
                        contents.append(text)
                            .append(System.getProperty(
                                "line.separator"));
                       if(text.startsWith("<DBIP>")){
                            dbip=text;
                            break;
                        }                   
                }        
            
            dbip=dbip.replaceAll("<DBIP>","");
            dbip=dbip.replaceAll("</DBIP>","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return dbip;
    }
    public static String getDbPort(String which){
        File file = new File("./Settings/db.config");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;
        String dbport="";
    try{
            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null){
                    contents.append(text)
                        .append(System.getProperty(
                            "line.separator"));
                    if(text.startsWith("<DBPort>")){
                        dbport=text;
                        break;
                    }
            }
            dbport=dbport.replaceAll("<DBPort>","");
            dbport=dbport.replaceAll("</DBPort>","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return dbport;
    }
    public static String getUserName(String which){
        File file = new File("./Settings/db.config");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;
        String name="";
    try{
            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null){
                    contents.append(text)
                        .append(System.getProperty(
                            "line.separator"));
                    if(text.startsWith("<UserName>")){
                        name=text;
                        break;
                    }
            }
            name=name.replaceAll("<UserName>","");
            name=name.replaceAll("</UserName>","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
    public static String getPassword(String which){
        File file = new File("./Settings/db.config");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;
        String name="";        
    try{
            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null){
                    contents.append(text)
                        .append(System.getProperty(
                            "line.separator"));
                    if(text.startsWith("<Password>")){
                        name=text;
                        break;
                    }
            }
            name=name.replaceAll("<Password>","");
            name=name.replaceAll("</Password>","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
     public static String getEndpoint(String start){
        File file = new File("./Settings/db.config");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        String text = null;
        String name="";
         boolean flag=false;
    try{
            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null){
                    contents.append(text)
                        .append(System.getProperty(
                            "line.separator"));
                    if(text.startsWith(start)){
                        flag=true;
                    }
                    if(text.startsWith("<Endpoint>") && flag){
                        name=text;
                        break;
                    }
            }
            name=name.replaceAll("<Endpoint>","");
            name=name.replaceAll("</Endpoint>","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }    
}
