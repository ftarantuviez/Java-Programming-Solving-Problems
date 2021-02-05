package SecondAssigment;
import java.util.*;
import edu.duke.*;

public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int lastIndex = startIndex;
        int stopCodonIndex = -1;
        while (lastIndex <= dna.length()-1){
            stopCodonIndex = dna.indexOf(stopCodon, lastIndex+1);
            if(stopCodonIndex%3==0 || stopCodonIndex == -1) break;
            lastIndex = stopCodonIndex;
        }
        return stopCodonIndex;
    }
    
    public String findGene(String dna){
        int atgIndex = dna.indexOf("ATG");
        if(atgIndex == -1) return "";
        int taaIndex = findStopCodon(dna, atgIndex, "TAA");
        int tagIndex = findStopCodon(dna, atgIndex, "TAG");
        int tgaIndex = findStopCodon(dna, atgIndex, "TGA");
        if(taaIndex == -1 && tagIndex == -1 && tgaIndex == -1) return "";
        
        List<Integer> indexes = new ArrayList();
        if(taaIndex != -1) indexes.add(taaIndex);
        if(tagIndex != -1) indexes.add(tagIndex);
        if(tgaIndex != -1) indexes.add(tgaIndex);
        
        Collections.sort(indexes);
        System.out.println(indexes.get(0));
        return dna.substring(atgIndex, indexes.get(0)+3);
    }
    
    public static void main(String[] args){
        Part1 part1 = new Part1();
        String dna = "ASDFASATGSDFFATAGIEOASGCTAA";
        FileResource fr = new FileResource();
        int indexFirst = 5;
        String stopCodon = "FAF";
        System.out.println(part1.findGene(fr.toString()));
    }
}
