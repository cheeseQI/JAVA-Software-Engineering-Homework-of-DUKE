
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.io.File;
import edu.duke.*;
public class Part4 {

public void printURL(URLResource url){
        String link = "";
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : ur.words()) {
            int posOf = word.indexOf("youtube.com");
        
            if (posOf != -1){
                int posleft = word.lastIndexOf("\"");
                int posright = word.indexOf("\"", posOf+1);
                
                link = word.substring(posleft, posright + 1); 
                System.out.println(link);
            }
            
        }
        
}
public void testPrintURL(){
    URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    printURL(url);
    System.out.println("what");
}

}
