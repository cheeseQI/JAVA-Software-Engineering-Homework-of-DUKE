
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex +3);
        if (stopIndex == -1 || (startIndex - stopIndex)%3 != 0) return dna.length();
        else return stopIndex;
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
    
    public int countGenes(String dna){
    int startIndex = dna.indexOf("ATG");
        int stopIndexTAA = 0, stopIndexTAG = 0, stopIndexTGA = 0;
        int stopIndex = 0;
        int totalLength = dna.length();
        int count = 0;
        
        if(startIndex == -1) System.out.println("");
        
        while( startIndex +1 < totalLength){
            startIndex = dna.indexOf("ATG", startIndex);
            if (startIndex == -1) return count;
            
            stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
            stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
            stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
            if (stopIndexTAA <= stopIndexTAG && stopIndexTAA <= stopIndexTGA) 
            {stopIndex = stopIndexTAA;}
            else if (stopIndexTAG <= stopIndexTAA && stopIndexTAG <= stopIndexTGA)
            {stopIndex = stopIndexTAG;}
            else stopIndex = stopIndexTGA;
            
            if (stopIndex == totalLength) return count;
            else {
                count++;
                startIndex = stopIndex + 3;
            }
    }
       return count;
    }
    
}
