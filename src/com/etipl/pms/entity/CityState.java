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

public class CityState {

    private String mCity;
    private String mState;
    private String mCounty;

    public void setCity(String c){this.mCity=c;}
    public void setState(String d){this.mState=d;}
    public void setCounty(String d){this.mCounty=d;}

    public String getCity(){return this.mCity;}
    public String getState(){return this.mState;}
    public String getCounty(){return this.mCounty;}

}
