
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.File;

public class Part1 {

    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        double count = 0;
        double current = 0;
        for (CSVRecord currentRow : parser){
            current = Double.parseDouble(currentRow.get("TemperatureF"));
            if (current == -9999) continue;
            
            sum += current;
            count ++;
        }
        return (sum / count) ;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double currentHumi = 0;
        double sum = 0;
        double count = 0;
        double currentTemp = 0;
        for (CSVRecord currentRow : parser){
            if ((currentRow.get("Humidity")).equals("N/A")) continue;
            currentHumi = Double.parseDouble(currentRow.get("Humidity"));
            if (currentHumi > value) {
                currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                sum += currentTemp;
                count ++; 
            }
        }
        return (sum / count) ;
        
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFarRow = null;
        double lowest = 0;
        double current = 0;
        
        for (CSVRecord currentRow: parser){
            current = Double.parseDouble(currentRow.get("TemperatureF"));
            if (current == -9999) continue;
            if (lowestSoFarRow == null){
            lowestSoFarRow = currentRow;
            }

            lowest = Double.parseDouble(lowestSoFarRow.get("TemperatureF"));
            if (current < lowest){
                lowestSoFarRow = currentRow;
                }
            
        }
        return lowestSoFarRow;
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord lowestSoFarRow = null;
        CSVRecord lowestCurrentFileRow = null;
        double lowest = 0;
        double current =0;
        String name = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            lowestCurrentFileRow =  coldestHourInFile(fr.getCSVParser());
            current = Double.parseDouble(lowestCurrentFileRow.get("TemperatureF"));
            if (current == -9999) continue;
            if (name == null){
                lowestSoFarRow = lowestCurrentFileRow;
                name = f.getPath();
            }
    
            lowest = Double.parseDouble(lowestSoFarRow.get("TemperatureF"));
            if (current < lowest){
                 name = f.getPath();
                 lowestSoFarRow = lowestCurrentFileRow;
                }
                
        }
                return name;
    }
        
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFarRow = null;
        double lowest = 0;
        double current = 0;
        
        for (CSVRecord currentRow: parser){

            if ((currentRow.get("Humidity")).equals("N/A")) continue;
            current = Double.parseDouble(currentRow.get("Humidity"));
            if (lowestSoFarRow == null){
            lowestSoFarRow = currentRow;
            }

            lowest = Double.parseDouble(lowestSoFarRow.get("Humidity"));
            if (current < lowest){
                lowestSoFarRow = currentRow;
                }
            
        }
        return lowestSoFarRow;
    }
   
    public CSVRecord lowestHumidityInManyFiles(){
        double lowest = 0;
        double current = 0;
        CSVRecord lowestSoFarRow = null;
        CSVRecord lowestCurrentFileRow = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestCurrentRow = lowestHumidityInFile(parser);
        if (lowestSoFarRow == null){
        lowestSoFarRow = lowestCurrentRow;
        }
        
        current = Double.parseDouble(lowestCurrentRow.get("Humidity"));
        lowest = Double.parseDouble(lowestSoFarRow.get("Humidity"));
        if (current < lowest){
        lowestSoFarRow = lowestCurrentRow;
        }
        }
        
        return lowestSoFarRow;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRow = coldestHourInFile(parser);
        System.out.println("coldest temperature was " + lowestRow.get("TemperatureF") 
        + " at " + lowestRow.get("TimeEST"));
    }
    
    public void testFileWithColdestTemperature(){
        String path = fileWithColdestTemperature();
        File f = new File(path);
        System.out.println("Coldest day was in file" + f.getName());
        
        FileResource fr = new FileResource(f);     
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRow = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + lowestRow.get("TemperatureF"));
        
        System.out.println("All the Temperatures on the coldest day were: ");
        CSVParser parserNew = fr.getCSVParser();
        for (CSVRecord record : parserNew){
        System.out.println(record.get("DateUTC") + ": " +record.get("TemperatureF") );
        }
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRow = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + lowestRow.get("Humidity") 
        + " at " + lowestRow.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles(){       
        CSVRecord lowestRow = lowestHumidityInManyFiles();
        
        System.out.println("Lowest Humidity was " +  lowestRow.get("Humidity") + " at " + lowestRow.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double ave = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(ave)){
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temp when high humidity is " + ave);
        }
    }
}
