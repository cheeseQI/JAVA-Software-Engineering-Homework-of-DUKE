
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class Part3 {
    //method to find stop codon of dna with an start index
    public int findStopCodon(String dna, int startIndex, String stopCodon){ 
        int stopIndex = dna.indexOf(stopCodon, startIndex +3); //note : this is only the first stop codon, but we may have more!!!
        
        while ( stopIndex != -1 && ((startIndex - stopIndex)%3 != 0) ) stopIndex = dna.indexOf(stopCodon, stopIndex + 3);
        if (stopIndex == -1) return dna.length(); //if there is no stop codon or the length is not times of 3, return the length of dna;
        else return stopIndex; //return the index of stop codon
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource(); //sr to store all gene meet requirement 
        int startIndex = dna.indexOf("ATG");
        int stopIndexTAA = 0, stopIndexTAG = 0, stopIndexTGA = 0;
        int stopIndex = 0; //smallest stop index
        int totalLength = dna.length();
        String gene = "";
        
        if(startIndex == -1) return sr; //if there is not ATG
        
        while( startIndex +1 < totalLength){ //when it has not reach the last one
            startIndex = dna.indexOf("ATG", startIndex); //get next ATG
            if (startIndex == -1) break; //when it has gone through all ATG, break
            
            stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
            stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
            stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
            stopIndex = Math.min(stopIndexTAA, Math.min(stopIndexTAG, stopIndexTGA));

            if (stopIndex == totalLength) startIndex += 3; //if the stop codon does not exist,go to next ATG;
            else {
                gene = dna.substring(startIndex, stopIndex + 3); //get gene start from ATG and end at least length 
                sr.add(gene);
                startIndex = stopIndex + 3; //start from the point next to stop codon
            }
    }
        return sr;
    }
    
    public float cgRatio(String dna){
            int count = 0;
            int currposition = dna.indexOf("C");
           
            while(currposition != -1){
            count++;
            currposition = dna.indexOf("C", currposition + 1);
            }
            currposition = dna.indexOf("G");
            while(currposition != -1){
            count++;
            currposition = dna.indexOf("G", currposition + 1);
            }
            return (float)count/dna.length();
            }
            
    public int countCTG(String dna){
        int count = 0;
        int currPosition = dna.indexOf("CTG");
        while(currPosition != -1){
            count ++;
            currPosition = dna.indexOf("CTG",currPosition + 3);
        }
        return count;
    }     
    
    public void processGene(StorageResource sr){
        StorageResource srSixty = new StorageResource();
        StorageResource srRatio = new StorageResource();
        int totalNum = 0;
        int numLongerSixty = 0;
        int numHigherRatio = 0;
        int longestLength = 0;
        int currLength = 0;
        
        for (String s: sr.data()){
        currLength = s.length();
        totalNum ++;
        if (currLength > 60) {
            srSixty.add(s);
            numLongerSixty ++;
        }
        if (cgRatio(s) > 0.35){
            srRatio.add(s);
            numHigherRatio ++;
        }
        if (currLength > longestLength) longestLength = currLength;
        }
        
        System.out.println(" There is " + totalNum +" genes in total");
        System.out.println(" Strings in sr that are longer than 60 characters are: ");
        for (String s: srSixty.data()){
            System.out.println(s);
        }
        System.out.println("number of Strings in sr that are longer than 60 characters = " + numLongerSixty);
        
        System.out.println("Strings in sr whose C-G ratio is higher than 0.35 are: ");
        for (String s: srRatio.data()){
            System.out.println(s);
        }
        System.out.println("number of Strings in sr whose C-G ratio is higher than 0.35 = " + numHigherRatio);
        System.out.println("longest gene length = " +longestLength);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource();
        int count = 0;
        String dna = fr.asString();
        dna = dna.toUpperCase(); 
        count = countCTG(dna);       
        StorageResource sr = getAllGenes(dna);
        processGene(sr);
        System.out.println("CTG appears " + count + " times");
    }
    
    public void testProcessGenesDebug(){
        int count = 0;
        String dna ="CTGONEATgONECCCGGGAAAXXXYYYGGGGTAGYYCTGCCCATGENDZZZTAAONEXXXYYYZZZtAaXXXXXTWOATGTWOYYYZZZCCCATGATGENDZZZTAGTWOXXTHREEATGATGTAATHREESTOPTAGAGGGCCCCCFOURATGTAGGXXXFIVEATGYYYFIVZZZAAAXXXFIVENDZZZTGAFIVESTOPSIXATGSIXCGGGCCGGGATCAAASIXENDTAASEVATGSIXCGGGCCGGGATCAAASEVENDENDTAAEIGSTOPTAGAGLASTONEATGtAACTG";
        dna = dna.toUpperCase(); 
        count = countCTG(dna);       
        StorageResource sr = getAllGenes(dna);
        for (String s: sr.data()){
        System.out.println(s);
        }

    }
}
