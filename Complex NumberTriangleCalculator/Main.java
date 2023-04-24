import java.util.Scanner;
//Caleb Millard
//SN: 613362
//CMPT 360 Spring 2023
//Assignment 1
//Title: Calculating Side Lengths And Angles Of A Triangle On A Complex Plane
class Main {
  
  public void input(){
  
  }
  public static void main(String[] args) {
    //intaking user input
    Scanner userInput = new Scanner(System.in);
    System.out.println("place three points in form A + Bi, one per line");
    Boolean entryValid = false;
    //instantiating side variables
    Double sideAB = 0.00;
    Double sideAC = 0.00;
    Double sideBC = 0.00;
    while(entryValid == false){
      String Point1 = userInput.nextLine();
      String Point2 = userInput.nextLine();
      String Point3 = userInput.nextLine();
    
      //cleaning the strings before use
      String[] Point1Plot = cleaner(Point1);
      String[] Point2Plot = cleaner(Point2);
      String[] Point3Plot = cleaner(Point3);
    



//try and catch loops to allow for safer input
      try{sideAB = distancefinder(Point1Plot,Point2Plot);
          sideAC = distancefinder(Point1Plot,Point3Plot);
          sideBC = distancefinder(Point2Plot,Point3Plot);
          entryValid = true;
         }
      catch(NumberFormatException x){    
          System.out.println("please re-enter points A, B and C in form A + Bi where A and B are integers, one per line");
          
      }
    }
    //calling the angle finder method
    anglefinder(sideBC,sideAC,sideAB); 
    //output for side lengths     
    System.out.println("side length between points A and B : " + sideAB);
    System.out.println("side length between points A and C : " + sideAC);
    System.out.println("side length between points B and C : " + sideBC);
    System.out.println("Caleb Millard - 613362 - assignment 1");
    userInput.close();


   
  }

  //this method finds the distance and returns the distance to be assigned to a variable
    public static Double distancefinder(String[] Point1 , String[] Point2)    {

      //these go through the string arrays and assign the x,y coordinates to points
      int pointAa = Integer.parseInt(Point1[0]); 
      int pointBa = Integer.parseInt(Point2[0]);
      int pointAb = Integer.parseInt(Point1[2]);
      int pointBb = Integer.parseInt(Point2[2]);
      //using pythagorean theorum calculating the side length using a2 + b2 = c2
      int xCoord = pointAa - pointBa;
      int yCoord = pointAb - pointBb;
      xCoord = xCoord * xCoord;
      yCoord = yCoord * yCoord;
      int C2 = yCoord + xCoord;

      double distance = Math.sqrt(C2);
      
      return distance;

    }

  public static void anglefinder(double a, double b, double c){
  
    
    Double squareA = a * a;
    Double squareB = b * b;
    Double squareC = c * c;

    //calculates angle using cosine theorum
    Double resultA = squareA - (squareB + squareC);
    double rightEQ = ((-2)*(b)*(c));
    resultA = resultA/rightEQ;
   
    
    Double resultB = squareB - (squareA + squareC);
    rightEQ = ((-2)*(a)*(c));
    resultB = resultB/rightEQ;
    
    
    Double resultC = squareC - (squareA + squareB);
    rightEQ = ((-2)*(a)*(b));
    resultC = resultC/rightEQ;

    resultA = Math.acos(resultA);
    resultA = resultA * (180 / 3.1415);
    System.out.println("angle of point A : " + resultA);
    
    resultC = Math.acos(resultC);
    resultC = resultC * (180 / 3.1415);
    System.out.println("angle of point C : " + resultC);
    
    resultB = Math.acos(resultB);
    resultB = resultB * (180 / 3.1415);

    System.out.println("angle of point B : " + resultB);
    
  }
  //cleans the strings to allow for easier calculations
  public static String[] cleaner(String Point1){
    Point1 = Point1.replace("i","");
    String[] cleaned =Point1.split(" ");
    return cleaned;
  }
}