import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * We do not consider the case of tie in this class
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class BabyNamesSimplified {
    
    public void totalBirth (FileResource fr){
        int numFemale = 0;
        int numFemaleName = 0;         
        int totalFemale = 0;
        int numMale = 0;
        int numMaleName = 0;
        int totalMale = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals("F")){
                numFemaleName ++;
                numFemale = Integer.parseInt(rec.get(2));
                totalFemale += numFemale;
            }
            else {
                numMaleName ++;
                numMale = Integer.parseInt(rec.get(2));
                totalMale += numMale;
            }      
        }
        int totalBirths = totalFemale + totalMale;
        int totalName = numMaleName + numFemaleName;
        System.out.println("total name = " + totalName + " unique girl name = " + numFemaleName + " unique boy name = " + numMaleName); 
        System.out.println("total Births = " + totalBirths);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        String relativePathFileName = "example/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(relativePathFileName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord currentRow : parser){
        if (currentRow.get(1).equals(gender)){
                rank ++;
        if (currentRow.get(0).equals(name)) return rank;
            }
        }
                return -1;
        }
    
   
    public String getName(int year, int rank, String gender){
        String relativePathFileName = "example/us_babynames_by_year/yob" + year + ".csv"; 
        FileResource fr = new FileResource(relativePathFileName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord currentRow : parser){
            if (currentRow.get(1).equals(gender)){
                rank --;
                if (rank == 0){
                    return currentRow.get(0);
                }
            }
        }
        return "NO NAME";
}

    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender); ;
        System.out.println( name + " born in " + year + " would be :" + newName + " in " + newYear);
    }
    
    public int getRankFile(File f, String name, String gender, int highestRank){
        int rank = 0;
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord currentRow : parser){
            if (currentRow.get(1).equals(gender)){
                    rank ++;
                if (currentRow.get(0).equals(name)){
                    if (rank < highestRank) return rank;
                    else return -1;
                }
            }
        }
        return -1;
    }    
           
    public int yearOfHighestRank(String name, String gender){
        int highestRank = Integer.MAX_VALUE;
        int currentYear = -1;
        int currentRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            if (getRankFile(f, name, gender, highestRank) != -1){
                highestRank = getRankFile(f, name, gender, highestRank);
                currentYear = Integer.parseInt(f.getName().substring(3,7));
            }
        }
        return currentYear;
    }
    
    public double getAverageRank(String name, String gender){
        double sumOfRank = 0;
        double count = 0;
        double ave = 0;
        int currentRank=0;
        String year = "";
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
            year = f.getName().substring(3,7);
            currentRank = getRank(Integer.parseInt(year), name, gender);
            System.out.println(currentRank);
            if (currentRank != -1){
                sumOfRank += currentRank;
            }
            count ++;
        }
        ave = sumOfRank / count ;
        System.out.println(sumOfRank + " " + count);
        return ave;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){ // Didn't consider tie case
        int accumulator = 0;
        int currentRank=0;
        int rank = 0;
        int numOfNameBefore = 0;
        String relativePathFileName = "example/us_babynames_by_year/yob" + year + ".csv"; 
        FileResource fr = new FileResource(relativePathFileName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord currentRow : parser){
        if (currentRow.get(1).equals(gender)){
            accumulator += numOfNameBefore;
            if (numOfNameBefore != Integer.parseInt(currentRow.get(2))){
                rank ++;
                numOfNameBefore = Integer.parseInt(currentRow.get(2));
            }
            if (currentRow.get(0).equals(name)) return accumulator;
        }
        }
        return -1;
    }
    
    public void testGetTotalBirthsRankedHigher(){
    System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
    System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Jacob", "M"));
    }
    
    public void testYearOfHighestRank(){
    System.out.println(yearOfHighestRank("Genevieve","F"));
    System.out.println(yearOfHighestRank("Mich","M"));
    }
    
    public void testWhatIsNameInYear(){
    whatIsNameInYear("Owen", 1974, 2014, "M"); 
    whatIsNameInYear("Susan", 1972, 2014, "F");  
    }
    
    public void testGetname(){
        String name = getName(1980,350,"F");
        System.out.println("The baby's name is : " + name);
        String name2 = getName(1982,450,"M");
        System.out.println("The baby's name is : " + name2);
    }
    
    public void testGetRank(){
    int rank = getRank(1960,"Emily","F");
    System.out.println("The rank is " + rank);
    }
    
    public void testTotalBirth (){
    FileResource fr = new FileResource();
    totalBirth(fr);   
    }
}

