import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){;
        String URL_NEW_YEAR = "datasets/us_babynames_test/yob" + Integer.toString(newYear) + "short.csv";
        FileResource fr = new FileResource(URL_NEW_YEAR);
        CSVParser parser = fr.getCSVParser(false);
        
        int currentPosition = getRank(year, name, gender);
        int newPosition = 0;
        
        for(CSVRecord row : parser){
            String gnder = row.get(1);
            if(!gnder.equals(gender))continue;
            newPosition +=1;
            if(newPosition == currentPosition){
                String rankName = row.get(0);
                System.out.println(name 
                                    + " born in " + year + 
                                     " would be " + rankName +
                                     " if she was born in " + newYear
                                     );
                break;
            }
        }
    }
    
    public void testWhatIsNameInYear(){
        String name = "Isabella";
        int year = 2012;
        String gnder = "F";
        int newYear = 2014;
        whatIsNameInYear(name, year, newYear, gnder);
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestPosition = 0;
        File highestYear = null;
        boolean isName = false;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int position = 0;
            for(CSVRecord row : fr.getCSVParser(false)){
                String gnder = row.get(1);
                if(!gnder.equals(gender))continue;
                position +=1;
                if(name.equals(row.get(0))){
                    isName = true;
                    break;
                }
            }

            
            if(position<highestPosition || highestPosition == 0){
                highestPosition = position;
                highestYear = f;
            }
        }
        
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(highestYear.getName());
        boolean matchFound = matcher.find();
        int finalYear = Integer.parseInt(matcher.group(0));
        return isName ? finalYear : -1;
    }
    
    public void testYearOfHighestRank(){
        String name = "Mason";
        String year  = "M";
        System.out.println(yearOfHighestRank(name,year));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int total = 0;
        double rank = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int position = 0;
            for (CSVRecord row : fr.getCSVParser(false)){
                String gnder = row.get(1);
                if(!gnder.equals(gender))continue;
                position += 1;
                if(name.equals(row.get(0))){
                    total += 1;
                    rank += position;
                }
            }
        }
        
        return total > 0 ? rank/total : -1.0;
    }
    
    public void testGetAverageRank(){
        String name = "Mason";
        String gender = "M";
        System.out.println(getAverageRank(name, gender));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year, name, gender);
        
        String URL = "datasets/us_babynames_test/yob" + Integer.toString(year) + "short.csv";
        FileResource fr = new FileResource(URL);
        CSVParser parser = fr.getCSVParser(false);
        int position = 0;
        int totalBirths = 0;
        for(CSVRecord row : parser){
            String gnder = row.get(1);
            if(!gnder.equals(gender))continue;
            position += 1;
            if(position==rank) break;
            else totalBirths += Integer.parseInt(row.get(2));
        }
        
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int year = 2012;
        String name = "Ethan";
        String gender = "M";
        System.out.println(getTotalBirthsRankedHigher(year,name,gender));
    }
}
