import edu.duke.*;
import java.io.File;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String dnaToSearch = dna.toLowerCase();
        int firstIndex = dnaToSearch.indexOf(startCodon.toLowerCase());
        int lastIndex = dnaToSearch.indexOf(stopCodon.toLowerCase(), firstIndex);
        String gene = dna.substring(firstIndex, lastIndex+3);
        System.out.println("In function gene: " + gene);
        if(firstIndex == -1 || lastIndex == -1 || gene.length() % 3 == 0){
            return "";
        }

        return gene;
    }
    
    public static void main(String[] args){
        Part2 secondPart = new Part2();
        FileResource fr = new FileResource();
        String gene = secondPart.findSimpleGene(fr.asString(), "ATG", "TAA");
        String useful = "AAATGCCCTAACTAGATTAAGAAACC";
    }
}
