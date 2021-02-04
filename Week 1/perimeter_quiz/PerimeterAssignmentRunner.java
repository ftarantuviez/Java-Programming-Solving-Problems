package perimeter_quiz;

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
        int sum = 0;
        for(Point pt : s.getPoints()){
            sum = sum + 1;
        }
        return sum;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        double numberOfPoints = (double)getNumPoints(s);
        double average = perimeter / numberOfPoints;
        return average;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point lastPoint = s.getLastPoint();
        
        for(Point pt : s.getPoints()){
            double currDist = pt.distance(lastPoint);
            
            if(currDist > largestSide){
                largestSide = currDist;
            }
            
            lastPoint = pt;
        }
        
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for(Point pt : s.getPoints()){
            if(pt.getX()>largestX){
                largestX = pt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for(File file : dr.selectedFiles()){
            FileResource fr = new FileResource(file);
            Shape s = new Shape(fr);
            
            if(largestPerimeter < getPerimeter(s)){
                largestPerimeter = getPerimeter(s);
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        double largestPerimeter = 0.0;
        for(File file : dr.selectedFiles()){
            FileResource fr = new FileResource(file);
            Shape s = new Shape(fr);
            
            if(largestPerimeter < getPerimeter(s)){
                largestPerimeter = getPerimeter(s);
                temp = file;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double perimeter = getPerimeter(s);
        int numberOfPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + perimeter);
        System.out.println("Number of points = " + numberOfPoints);
        System.out.println("Average length = " + averageLength);
        System.out.println("Largest side = " + largestSide);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeterMultipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println(
            "Largest perimeter over the files = " 
            + largestPerimeterMultipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println(
            "Largest perimeter file = " 
            + file);
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
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
