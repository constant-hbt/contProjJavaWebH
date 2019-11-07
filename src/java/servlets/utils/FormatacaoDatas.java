/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatacaoDatas {
    public static java.sql.Date formataData(String data) throws Exception { 
        if (data == null || data.equals(""))
            return null;

         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (Exception e){            
             throw e;
         }
         return date;
    }
    
    public static String formatDateUser(String data){  
         SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");  
         Date d1 = null;  
         try {  
             d1 = f.parse(data);              
         } catch (Exception e) {
             e.printStackTrace();  
         }  

         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  

         data = format.format(d1);  

         return data;         

    }
    
    public static java.util.Date formataDataBr(Date dataEn) throws Exception{
        if(dataEn == null || dataEn.equals("")){
            return null;
        }
        
        try{
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            String dataBr = null;
            dataBr = f.format(dataEn);
            java.util.Date dataBrf = formataData(dataBr);
            return dataBrf;
        }catch(ParseException e){
            throw e;
        }
    }
}


