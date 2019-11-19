/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Subeventos;


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
    
    public static Timestamp converterStringParaTimestamp(String datahora) throws Exception{
        Timestamp datahoraF;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date parsedDate = dateFormat.parse(datahora);
            datahoraF = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) { 
            throw e;
        }
        return datahoraF;
    }
    
    public boolean compararInicioFimSubeventos(List<Subeventos> subeventos, Subeventos subeventoAtual) throws Exception{
        boolean flag = true;
        try{
            Timestamp datahoraInicio;
            Timestamp datahoraInicioSubA = converterStringParaTimestamp(subeventoAtual.getDatahorainicio());
            Timestamp datahoraFim;
            Timestamp datahoraFimSubA = converterStringParaTimestamp(subeventoAtual.getDatahorafim());
            for(Subeventos subevento: subeventos){
                datahoraInicio = converterStringParaTimestamp(subevento.getDatahorainicio());
                datahoraFim = converterStringParaTimestamp(subevento.getDatahorainicio());
                if((datahoraInicioSubA.compareTo(datahoraFim) <= 0 && datahoraInicioSubA.compareTo(datahoraInicio) >= 0) || 
                        (datahoraFimSubA.compareTo(datahoraInicio) >= 0 && datahoraFimSubA.compareTo(datahoraFim) <= 0)){
                    flag = false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}


