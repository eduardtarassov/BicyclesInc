package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {

	
	
	
public static boolean checkTextField(String field){
    	if (field.length() < 1){
    		return false;
    	}
    	return true;
    }
    

    public static boolean checkDate(String inDate) { 
        String dateFormat = "MM/dd/yyyy"; 
        int dateFormatLength = dateFormat.length(); 
        try { 
          if (inDate.length() != dateFormatLength) 
            throw new Exception(); 
          else { 
            SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
            format.setLenient(false); 
            Date theDate = new Date(); 
            theDate = format.parse(inDate); 
            return true; 
          } 
        } 
        catch(Exception e) { 
          return false; 
        } 
      } 
	
}
