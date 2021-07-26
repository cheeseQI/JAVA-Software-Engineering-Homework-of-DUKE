
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex +3);
        if (stopIndex == -1 || (startIndex - stopIndex)%3 != 0) return dna.length();
        else return stopIndex;
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1) return "";
        int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
        int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
        int stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
        int stopIndex = 0;
        if (stopIndexTAA <= stopIndexTAG && stopIndexTAA <= stopIndexTGA) 
        {stopIndex = stopIndexTAA;}
        else if (stopIndexTAG <= stopIndexTAA && stopIndexTAG <= stopIndexTGA)
        {stopIndex = stopIndexTAG;}
        else stopIndex = stopIndexTGA;
                
        if (stopIndex == dna.length()) return "";
        else {
            String gene = dna.substring(startIndex, stopIndex + 3);
            return gene;}
    }
    
    public void printAllGenes(String dna){
        int startIndex = dna.indexOf("ATG");
        int stopIndexTAA = 0, stopIndexTAG = 0, stopIndexTGA = 0;
        int stopIndex = 0;
        int totalLength = dna.length();
        
        if(startIndex == -1) System.out.println("");
        
        while( startIndex +1 < totalLength){
            startIndex = dna.indexOf("ATG", startIndex);
            if (startIndex == -1) break;
            
            stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
            stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
            stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
            if (stopIndexTAA <= stopIndexTAG && stopIndexTAA <= stopIndexTGA) 
            {stopIndex = stopIndexTAA;}
            else if (stopIndexTAG <= stopIndexTAA && stopIndexTAG <= stopIndexTGA)
            {stopIndex = stopIndexTAG;}
            else stopIndex = stopIndexTGA;
            
            if (stopIndex == totalLength) {
                System.out.println("");
                break;}
            else {
                System.out.println(dna.substring(startIndex, stopIndex + 3));
                startIndex = stopIndex + 3;
            }
    }
    }
    
    //Test dna series: "AGTATGGGGTGTTAATGAAAAGTATGTGATAGTAAAGAATGTTTGATAAATGGTGTGTGTTAGGTGA"
         
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        int stopIndexTAA = 0, stopIndexTAG = 0, stopIndexTGA = 0;
        int stopIndex = 0;
        int totalLength = dna.length();
        String gene = "";
        if(startIndex == -1) System.out.println("");
        
        while( startIndex +1 < totalLength){
            startIndex = dna.indexOf("ATG", startIndex);
            if (startIndex == -1) break;
            
            stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
            stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
            stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
            if (stopIndexTAA <= stopIndexTAG && stopIndexTAA <= stopIndexTGA) 
            {stopIndex = stopIndexTAA;}
            else if (stopIndexTAG <= stopIndexTAA && stopIndexTAG <= stopIndexTGA)
            {stopIndex = stopIndexTAG;}
            else stopIndex = stopIndexTGA;
            
            if (stopIndex == totalLength) break;
            else {
                gene = dna.substring(startIndex, stopIndex + 3);
                sr.add(gene);
                startIndex = stopIndex + 3;
            }
    }
        return sr;
    }
    
    public void testGetAllGene(){
    StorageResource sr = getAllGenes("AGTATGGGGTGTTAATGAAAAGTATGTGATAGTAAAGAATGTTTGATAAATGGTGTGTGTTAGGTGA");
    for (String s: sr.data()){
    System.out.println(s);
    }
}
    
    public void testFindGene(){
        String[] dna_eg = {"AGTATGGGGTGTTAATGA","TGAADUSAHFEU","AGTATGTGATAGTAA",
                "AGAATGTTTGA","TAAATGGTGTGTGTTAGGTGA"};
        for(int i = 0; i <= 4; i++){
            System.out.println("dna = " + dna_eg[i]);
            String gene = findGene(dna_eg[i]);
            
            System.out.println("gene =" + gene);
    }
    }
    
    
    
    public void testFindStopCodon(){
        String[] dna_eg = {"ATGGGGTGTTAATGA","ADUSAHFEU","ATGTGATAGTAA",
                "ATGTTTGA","ATGGTGTGTGTAGTGA"};
            for(int i = 0; i <= 4; i++){
                System.out.println("dna =" +dna_eg[i]);
                int gene =     findStopCodon(dna_eg[i], 0,"TAA" );
                System.out.println("gene =" + gene);
    }
    }
}
