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
        CSVRecord lowestHumidity = null;
        for (CSVRecord row : parser){
            double rowH = Double.parseDouble(row.get("Humidity"));
            if(lowestHumidity == null) lowestHumidity = row;
            double lowestHumidityH = Double.parseDouble(lowestHumidity.get("Humidity"));
            if(lowestHumidityH > rowH){
                lowestHumidity = row;
            }
        }
        
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord test = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was: " + test.get("Humidity") + " at " + test.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        double lowestHumidity = 100.0;
        CSVRecord row = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
           
            CSVRecord lowestHumidityRow = lowestHumidityInFile(parser);
            double lowestHumidityRowH = Double.parseDouble(lowestHumidityRow.get("Humidity"));
            
            if(row == null) row=lowestHumidityRow;
            if(lowestHumidity > lowestHumidityRowH){
                lowestHumidity = lowestHumidityRowH;
                row = lowestHumidityRow;
            }
        }
        
        return row;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was: " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double i = 0;
        int j = 0;
        
        for(CSVRecord row : parser){
            i += Double.parseDouble(row.get("TemperatureF"));
            j += 1;
        }
        return i/j;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperatureTest = averageTemperatureInFile(parser);
        System.out.println("The average temperature in file is: " + averageTemperatureTest);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double i = 0.0;
        int j = 0;
        for(CSVRecord row : parser){
            double humidity = Double.parseDouble(row.get("Humidity"));
            if(humidity<value) continue;
            j+=1;
            i+=Double.parseDouble(row.get("TemperatureF"));
        }
        return j>0 ? i/j : -1000000;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double averageTemperatureTest = averageTemperatureWithHighHumidityInFile(parser, value);
        if(averageTemperatureTest<-1000) System.out.println("No temperatures with " + value + " as humidity");
        else System.out.println("Average temperature when humidity >= " + value + " is " + averageTemperatureTest);
    }
}
