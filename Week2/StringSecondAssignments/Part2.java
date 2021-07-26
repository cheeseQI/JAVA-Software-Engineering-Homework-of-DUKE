
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int currposition = stringb.indexOf(stringa);
        
        if (currposition == -1)  return 0;
        while(currposition != -1){
        count++;
        currposition = stringb.indexOf(stringa, currposition + stringa.length());
        }
        return count;
        }
    
    public void testHowMany(){
    int count = howMany("oo","whoops, I prefer to the zoo");
    System.out.println("it appears for " + count +" times");
    int times = howMany("ee","cheers, cheese man, take it eeeeeasy");
    System.out.println("sencond one appears for " + times + " times");
    int num = howMany("okay","oka");
    System.out.println("third one appears for " + num + " times");
    }
}
