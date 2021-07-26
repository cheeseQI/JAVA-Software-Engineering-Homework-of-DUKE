
/**
 * 在这里给出对类 Part1 的描述。
 * For DNA idenification
 * @CheeseQI（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    public String findSimpleGene (String dna){
        String result ="";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        int stopIndex = dna.indexOf("TAA", startIndex +3);
        if (stopIndex == -1) return "";
        result = dna.substring(startIndex, stopIndex +3);
        if ((startIndex - stopIndex)%3 == 0) return result;
        else return "";
    }
    
    public void testSimpleGene(){
        String[] dna_eg = {"ATGGGGTGTTAATGA","ADUSAHFEU","ATGJSSADI",
            "UYSHSHTAA","ATGGTGTGTGTAA"};
            for(int i = 0; i <= 4; i++){
                System.out.println("dna =" +dna_eg[i]);
                String gene = findSimpleGene(dna_eg[i]);
                System.out.println("gene =" + gene);
    }
    }
}
