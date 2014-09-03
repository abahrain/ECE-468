import java.util.StringTokenizer;
import java.io.*;

public class Micro 
{
  public static void main(String[] args)
  {
      //Name of the file
      String fileName="args[0]";
      try{

          //Create object of FileReader
          FileReader inputFile = new FileReader(fileName);

          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);
          //Variable to hold the one line data
          String line;
    
          // Read file line by line and print on the console
          while ((line = bufferReader.readLine()) != null)
          {
              StringTokenizer st = new StringTokenizer(line);
              while(st.hasMoreElements())
              {
                  System.out.println(st.nextElement());
              }
          }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){
            System.out.println("Error while reading file line by line:" 
            + e.getMessage());                      
        }

  }
}