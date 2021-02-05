package SecondAssigment;
import java.util.*;
import edu.duke.*;

public class Part2 {
    
    public int howMany(String stringa, String stringb){
        int sum = 0;
        int startIndex = 0;
        while(true){
            int stringaIndex = stringb.indexOf(stringa, startIndex);
            if(stringaIndex == -1) break;
            
            sum = sum + 1;
            startIndex = stringaIndex + stringa.length(); 
        }
        
        return sum;
        
    }
    
    public int howManyGenes(String dna){
        int index = dna.indexOf("atg");
        int count = 0;
        while(true){
            if(index == -1) break;
            int secondIndex = dna.indexOf("taa", index+3);
            if(secondIndex == -1) break;
            if(dna.substring(index, secondIndex+4).length()>60) count = count + 1;
            
            index = dna.indexOf("atg", index+3);
            
        }
        return count;
        
        
    }
    
    public String mystery(String dna) {
          int pos = dna.indexOf("T");
          int count = 0;
          int startPos = 0;
          String newDna = "";
          if (pos == -1) {
            return dna;
          }
          while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
              break;
            }
          }
          newDna = newDna + dna.substring(startPos);
          return newDna;
    }
    
    public static void main(String[] args){
        Part2 part2 = new Part2();
        FileResource fr = new FileResource();
        System.out.println(part2.mystery("GAAGTAGCTTAGAAGTAGCTTAGAAGTAGCTTA"));
    }
}
