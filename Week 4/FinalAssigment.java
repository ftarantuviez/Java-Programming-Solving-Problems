import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.File;

public class FinalAssigment {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <=100){
                System.out.println("Name " + rec.get(0) + 
                                    " Gender" + rec.get(1) +
                                    " Num Born" + rec.get(2));
            }
        }   
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;
        int totalNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            
            if(rec.get(1).equals("M")){
                totalBoys += numBorn;
                totalBoysNames +=1;
            } else {
                totalGirls += numBorn;
                totalGirlsNames += 1;
            }
            totalNames +=1;
        }   
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total girls = " + totalGirls);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total names = " + totalNames);
        System.out.println("Total girls names = " + totalGirlsNames);
        System.out.println("Total boys names = " + totalBoysNames);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        String URL = "datasets/us_babynames_test/yob" + Integer.toString(year) + "short.csv";
        FileResource fr = new FileResource(URL);
        int position = 0;
        boolean isName = false;
        for(CSVRecord row : fr.getCSVParser(false)){
            String gnder = row.get(1);
            if(!gnder.equals(gender))continue;
            position +=1;
            if(name.equals(row.get(0))){
                isName = true;
                break;
            }
        }
        
        return isName ? position : -1;
    }
    
    public void testGetRank(){
        int year = 2012;
        String name = "Mason";
        String gender = "F";
        System.out.println(getRank(year, name, gender));
    }
    
    public String getName(int year, int rank, String gender){
        String URL = "datasets/us_babynames_test/yob" + Integer.toString(year) + "short.csv";
        FileResource fr = new FileResource(URL);
        int position = 0;
        String name = null;
        boolean isRank = false;
        for(CSVRecord row : fr.getCSVParser(false)){
            String gnder = row.get(1);
            if(!gnder.equals(gender))continue;
            position += 1;
            if(position == rank){
                name = row.get(0);
                isRank = true;
                break;
            }
        }
        
        return isRank ? name : "NO NAME";
    }
    
    public void testGetName(){
        System.out.println(getName(2012, 1, "M"));
    }
}
