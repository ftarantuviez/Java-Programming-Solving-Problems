import edu.duke.*;
import java.io.File;

public class Part3 {
    public boolean twoOcurrences(String stringa, String stringb){
        stringa = stringa.toLowerCase();
        stringb = stringb.toLowerCase();
        int firstOcurrence = stringb.indexOf(stringa);
        int secondOcurrence = stringb.indexOf(stringa, firstOcurrence + stringa.length());
        
        if(secondOcurrence == -1) return false;
        
        return true;
    }
    
    public String lastPart(String stringa, String stringb){
        String stringaLower = stringa.toLowerCase();
        String stringbLower = stringb.toLowerCase();
        
        int stringaIndex = stringbLower.indexOf(stringaLower);
        
        return stringaIndex != -1 ? stringb.substring(stringaIndex) : stringb;
        
    }
    
    public static void main(String[] args){
        Part3 thirdPart = new Part3();
        FileResource fr = new FileResource();
        String stringa = "tion";
        String stringb = "By nacionalization bme";
        
        System.out.println(thirdPart.lastPart(stringa, stringb));
    }
}
