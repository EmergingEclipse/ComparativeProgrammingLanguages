using System;
using System.IO;
using System.Text;


//Caleb Millard
//SN: 613362
//CMPT 360 Spring 2023
//Assignment 2
//Title: Find And Implement an Efficient Sorting Algorithm


namespace Caleb{
    public class QuickSort{
        //the method that gathers the data from an input file
        public static int[] obtainData()
        {
            // inputing a data file via a path
            string systemPath = @"C:\Users\Caleb Millard\Documents\input1000.txt";
            //this allows the file to read each line of the csv or txt file as a new number
            string[] lines = System.IO.File.ReadAllLines(systemPath);
            int strlength = lines.Length;
            int[] numbers = new int[strlength];
            for(int i = 0;i < strlength;i++)
            {
                numbers[i] = Int32.Parse(lines[i]);
            }
            // we would be able to implement a dictionary to allow us to parse through a number and assign it a value
            // with that we would also be able to sort alphabetically because normally the > and < operands dont work on char
            return numbers;
        }
        // pick a number within the array to be your bench point or where you are splitting the array
        public static int split(int[] data ,int bottom ,int top)
        {
            int bench = data[top];
            int splitpoint = bottom-1;
            //this is iterating throught eh available numbers and swapping them so they are in the appropriate places in the array
            for (int i = bottom; i<top; i++)
            {
                if (data[i] <= bench)
                {
                    splitpoint += 1;
                    (data[splitpoint], data[i]) = (data[i], data[splitpoint]);
                }
            }
            (data[splitpoint+1], data[top]) = (data[top], data[splitpoint+1]);
            return (splitpoint+1);
        }
        // the sort algorithm brings al the pieces together and uses the split method to combine them and actually sort the data
        public static void sort(int[] data,int bottom,int top)
        {
            if (bottom<top)
            {
                int position = split(data,bottom,top);
                sort(data,bottom,position-1);
                sort(data,position+1,top);
            }
        }
        //this method driver combines all the aspects of the program and drives it, calling the data and the sorting algorthm methods
        public static void driver()
        {
            int[] data = obtainData();
            int length = data.Length;
            sort(data, 0, length-1);
            string path = @"C:\Users\Caleb Millard\Desktop\sorted1000.txt";          
            int datalength = data.Length;
            string input = "";
                for (int i = 0; i<datalength; i++)
                {
                    input = string.Join(", ", data);
                }
                //creates a new file or overwrites the current output file
                File.WriteAllText(path,input);
                Console.WriteLine("Sort completed and printed");
        }
        static void Main()
        {
            driver();
        }
    }
}
