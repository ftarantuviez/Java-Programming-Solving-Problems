import edu.duke.*;
import java.io.File;

public class Part4 {
    public static void main(String[] args){
        Part4 fourthPart = new Part4();
        String URL = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        
        URLResource url = new URLResource(URL);
        for(String s : url.lines()){
            int indexOfYt = s.indexOf("youtube.com");
            int indexFirstQuotation = s.lastIndexOf("\"", indexOfYt);
            int indexSecondQuotation = s.indexOf("\"", indexOfYt+1);
            if(indexOfYt == -1) continue;
            
            System.out.println(s.substring(indexFirstQuotation+1, indexSecondQuotation));
            
        }
    }
}
