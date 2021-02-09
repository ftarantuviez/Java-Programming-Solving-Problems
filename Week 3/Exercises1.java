import edu.duke.*;
import org.apache.commons.csv.*;
public class Exercises1{
    
    public void getCountryInfo(CSVParser parser, String country){
        for(CSVRecord row : parser){
            String rowCountry = row.get("Country").toLowerCase();;
            if(rowCountry.equals(country.toLowerCase())){
                System.out.print(country + ": ");
                System.out.print(row.get("Exports") + ": ");
                System.out.println(row.get("Value (dollars)"));
            }
        }    
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord row : parser){
            String exports = row.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(row.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int sum = 0;
        for(CSVRecord row : parser){
            if(row.get("Exports").contains(exportItem)) sum = sum + 1;
        }
        
        return sum;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord row : parser){
            String cAmount = row.get("Value (dollars)");
            if(cAmount.length() > amount.length()) {
                System.out.print(row.get("Country")+": ");
                System.out.println(cAmount);  
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //getCountryInfo(parser, "Nauru");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }

     public static void main(String[] args){
        Exercises1 ex = new Exercises1();
        ex.tester();
     }
}