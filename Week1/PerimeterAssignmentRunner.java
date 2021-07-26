import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int n = 0;
        for (Point currPt : s.getPoints()){
        n++;
        }
        return n;
    }

    public double getAverageLength(Shape s) {
        double ave =  getPerimeter(s) / getNumPoints(s) ;
        return ave;
    }

    public double getLargestSide(Shape s) {
       double larggDist = 0.0; 
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
        double currDist = prevPt.distance(currPt);
            if (currDist > larggDist) larggDist = currDist;
            prevPt = currPt;
        }
        return larggDist;

    }

    public double getLargestX(Shape s) {
        Point prevPt = s.getLastPoint();
        double largX = prevPt.getX();
        for (Point currPt : s.getPoints()){
        double currX = currPt.getX();
            if (currX > largX) largX = currX;
            prevPt = currPt;
        }
                return largX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double larggPerim = 0.0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f); 
            Shape s = new Shape(fr);
        double currPerim = getPerimeter(s);
        if (currPerim > larggPerim) larggPerim = currPerim;
        }
        return larggPerim;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double larggPerim = 0.0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f); 
            Shape s = new Shape(fr);
        double currPerim = getPerimeter(s);
        if (currPerim > larggPerim) {
            larggPerim = currPerim;
            temp = f;
        }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int num = getNumPoints(s);
        double ave = getAverageLength(s);
        double largS = getLargestSide(s);
        double largX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number = " + num);
        System.out.println("average length = " + ave);
        System.out.println("largest side = " + largS);
        System.out.println("largest x = " + largX);
    }
    
    public void testPerimeterMultipleFiles() {
        double larggPerim = getLargestPerimeterMultipleFiles();// Put code here
        System.out.println("larggest perimeter among files = " + larggPerim);
    }

    public void testFileWithLargestPerimeter() {
     
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
