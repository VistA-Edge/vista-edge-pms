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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author MUTHU
 */
public class Utiles {
    private String[] setICDs;
    
    
    public String[] eliminateDuplicateICDs(String[] icd){
                       // instant elimination of duplicates with HashSet!
                Set hashSet = new HashSet(Arrays.asList(icd));

                // convert back to an array
                setICDs = (String[]) hashSet.toArray(new String[hashSet.size()]);
                
                return setICDs;
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        String hashword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");            
            md5.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashword = hash.toString(16);
        }catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return hashword;
}

    public static String convertDateTime(Date dt){
    try{
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
           // java.util.Date date = new java.util.Date();
            String datetime = dateFormat.format(dt);
            
            return datetime;
    }catch(Exception e){e.printStackTrace();return "";}
    }

     public static String convertMysqlDatetoUSFormat(String dt){
    try{
        SimpleDateFormat formatter, FORMATTER;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String USDate=null;

        Date date = formatter.parse(dt);
        FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
        
        USDate=FORMATTER.format(date);

        return USDate;
    } catch (ParseException e){
            System.out.println("Exception :"+e);
                 return null;
    }
    }

    public static String convertMysqlVistADatetoUSFormat(String dt){
    try{
        SimpleDateFormat formatter, FORMATTER;
        formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String USDate=null;
        
        Date date = formatter.parse(dt);
        FORMATTER = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        
        USDate=FORMATTER.format(date);
        
        return USDate;
    } catch (ParseException e){
            System.out.println("Exception :"+e);
                 return null;
    }
    }

    public static String convertUSDatetoMysql(String dt){
    try{
            String mysqlDate=null;
            SimpleDateFormat parseStringtoDate,dateFormat;
            parseStringtoDate= new SimpleDateFormat("MM/dd/yyyy");
            //java.util.Date date = new java.util.Date();
            Date date = parseStringtoDate.parse(dt);
            dateFormat= new SimpleDateFormat("yyyy-MM-dd");
            mysqlDate = dateFormat.format(date);
            //System.out.println("Converted Date Time : " + mysqlDate);

            return mysqlDate;
    }catch(Exception e){e.printStackTrace(); return null;}
    }

    public static String getCurrentDateTime(){
    try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = new java.util.Date();
            String datetime = dateFormat.format(date);
            ////System.out.println("Current Date Time : " + datetime);

            return datetime;
    }catch(Exception e){e.printStackTrace(); return "";}
    }
}
