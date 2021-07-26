
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
public String findSimpleGene (String dna, String startCondon, String stopCondon){
        String result ="";
        if(dna.equals(dna.toLowerCase())) {
            startCondon = startCondon.toLowerCase();
            stopCondon = stopCondon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCondon);
        if (startIndex == -1) return "";
        int stopIndex = dna.indexOf(stopCondon, startIndex +3);
        if (stopIndex == -1) return "";
        result = dna.substring(startIndex, stopIndex +3);
        if ((startIndex - stopIndex)%3 == 0) return result;
        else return "";
    }
    
    public void testSimpleGene(){
        String[] dna_eg = {"ATGGGGTGTTAATGA","ADUSAHFEU","ATGJSSADI",
            "UYSHSHTAA","ATGGTGTGTGTAA","atggggtgttaatga"};
            for(int i = 0; i <= 5; i++){
                System.out.println("dna =" +dna_eg[i]);
                String gene = findSimpleGene(dna_eg[i], "ATG", "TAA");
                System.out.println("gene =" + gene);
    }
    }
}
