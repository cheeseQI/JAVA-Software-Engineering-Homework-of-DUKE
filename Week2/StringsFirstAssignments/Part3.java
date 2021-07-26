
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    public boolean twoOccurrence(String stringa, String stringb){
    int count = 0;
    int currposition = stringb.indexOf(stringa) ;
    if (currposition == -1) {
        System.out.println("occurrence = " + count);
        return false;
    }
    while(currposition != -1){
    count++;
    currposition = stringb.indexOf(stringa, currposition + 1);
    }
    System.out.println("occurrence = " + count);
    if (count == 2)  return true;
    else return false;
}

    public String lastPart(String stringa, String stringb){
    int currposition = stringb.indexOf(stringa);
    if (currposition == -1) return stringb; 
    else return stringb.substring(currposition + stringa.length());
    }
    
    public void testTwoOccurrence(){
    String stringa = "who", stringb = "hey,who is your dad, who ?";
    System.out.println("String b = " + stringb);
    System.out.println("String a = " + stringa);
    if(twoOccurrence(stringa,stringb)) System.out.println("yes, it is 2 occurrence");
    else System.out.println("no, it is not 2 occurrence");
    }
    
    public void testLastPart(){
    String stringa = "who", stringb = "hey,who is your dad,who ?";
    String stringlast = lastPart(stringa, stringb);
    System.out.printf("The part of the string after %s in %s is %s\n", 
                            stringa, stringb, stringlast);
    }
}
