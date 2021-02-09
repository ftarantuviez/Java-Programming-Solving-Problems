import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.File;


public class Exercises2 {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestOne = null;
        for (CSVRecord row : parser){
            double rowT = Double.parseDouble(row.get("TemperatureF"));
            if(coldestOne == null && rowT > -33.0) coldestOne = row;
            double coldestOneT = Double.parseDouble(coldestOne.get("TemperatureF"));
            if( coldestOneT > rowT && rowT > -33.0){
                coldestOne = row;
            }
        }
        
        return coldestOne;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord test = coldestHourInFile(parser);
        
        System.out.print("The coldest temperature was: " + test.get("TemperatureF") + " at :" + test.get("DateUTC"));
    }
    
    public void fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        double coldestTemp = 100.0;
        File ff = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            if(ff == null) ff=f;
           
            CSVRecord coldestHour = coldestHourInFile(parser);
            double coldestHourTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
            if(coldestTemp > coldestHourTemp){
                coldestTemp = coldestHourTemp;
                ff = f;
            }
            
        }
        
        FileResource fr = new FileResource(ff);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTempHour = coldestHourInFile(parser);
        System.out.println("Coldest day was in file " + ff);
        System.out.println("Coldest temperature on that day was "+ coldestTempHour.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        CSVParser parserCsv = fr.getCSVParser();
        for(CSVRecord row : parserCsv) {
            System.out.println(row.get("DateUTC") + " " + row.get("TemperatureF"));
        }
        
        
    }
    
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        
    }
}
