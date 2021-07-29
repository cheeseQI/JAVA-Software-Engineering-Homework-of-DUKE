
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import org.apache.commons.csv.*;
import edu.duke.*;

public class Part1 {

    public void tester(){
    FileResource fr = new FileResource();
    
    CSVParser parser_0 = fr.getCSVParser();
    String info = countryInfo(parser_0, "Nauru");
    System.out.println(info);
    
    CSVParser parser_1 = fr.getCSVParser();
    listExportersTwoProducts(parser_1, "cotton", "flowers");
    
    CSVParser parser_2 = fr.getCSVParser();
    bigExporters(parser_2, "$999,999,999,999");
    
    CSVParser parser_3 = fr.getCSVParser();
    System.out.println(numberOfExporters(parser_3,"cocoa"));
    }
    
    public String countryInfo(CSVParser parser, String country){
        String countryName = "";
        String exports = "";
        String value = "";
        String result = "";
        for(CSVRecord record : parser){
            countryName = record.get("Country");
            if (countryName.equals(country)){
                exports = record.get("Exports");
                value = record.get("Value (dollars)");
                result = (countryName + ": " + exports + ": " + value);
                return result;
            }
        }
    return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
    String export = "";
        for(CSVRecord record : parser){
            export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2))
            System.out.println(record.get("Country"));
        }
    }
    
    public void bigExporters(CSVParser parser, String amount){
        String value = "";
        for(CSVRecord record : parser){
                value = record.get("Value (dollars)");
                if (value.length() > amount.length()) 
                System.out.println(record.get("Country") + "" + value);
            }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
                if ((record.get("Exports").contains(exportItem))){
                count ++;
                }
            }
            return count;
    }
}
