import edu.duke.*;
import java.io.File;

public class Part1 {
    public String findSimpleGene(String s){
        int firstIndex = s.indexOf("ATG");
        int lastIndex = s.indexOf("TAA", firstIndex);
        String gene = s.substring(firstIndex, lastIndex+3);
        
        if(firstIndex == -1 || lastIndex == -1 || gene.length() % 3 == 0){
            return "";
        }

        System.out.println("Gene " + gene);
        return gene;
    }
    
    public static void main(String[] args){
        Part1 firstPart = new Part1();
        FileResource fr = new FileResource();
        String gene = firstPart.findSimpleGene(fr.asString());

        System.out.println(gene);
    }
}
