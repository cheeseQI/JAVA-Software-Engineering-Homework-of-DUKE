
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
   
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
       
    public void testCGRatio(){
        float ratio = cgRatio("ATGCCATAG");
        System.out.println(ratio);
    }
    
    
}
