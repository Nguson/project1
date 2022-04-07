
package cmsc256;  // do not remove or comment out this statement
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *  CMSC 256
 *  Project 1
 *  Nguyen, Minh
 */
// place any import statements here
public class Project1 {
    public static void main(String[] args) {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.

    }
    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {

        if (argv[0] == null){
            return promptForFileName();
        }
        else{
            return argv[0];
        }
    }
    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public String promptForFileName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a file name");
        String fileName = scan.nextLine();
        return fileName;
    }
    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
        try{    //If the file can't be opened or doesn't exist, the try block will catch it and notify the user.
            File inputFile = new File(fileName);
            return inputFile;
        }
        catch (Exception e) {
            System.out.println("the file cannot be opened or does not exist");
        }
        throw new FileNotFoundException();

    }
    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {
        Scanner input = new Scanner(file);
        String[][] data = new String[numRecords][3];

        for (int i = 0; i < numRecords + 1; i++) {
            if (i < 1) { //this skips the 3 words at the top of each column
                input.nextLine();
            }
            else { //This splits the file by the comma, allowing it to be stored into different elements for the array
                String Line[] = input.nextLine().trim().split(",");
                for (int j = 0; j < 3; j++) {
                    data[i-1][j] = Line[j];
                }
            }
        }

        return data;

    }
    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public static int findTallest(String[][] db) {
        int Tallest = 0;
        int placeholderheight = 0;
        for(int i = 0; i < db.length; i++){
            placeholderheight = Integer.parseInt(db[i][1]);
            if(Tallest < placeholderheight){
                Tallest = placeholderheight;
            }
        }
        return Tallest;
    }
    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
        int lightestWeight = 0;
        double placeHolderValue = Double.MAX_VALUE;
        double weightPlaceHolder = 0;
        for(int i = 0; i < db.length; i++){
            weightPlaceHolder = Double.parseDouble(db[i][2]);
            if(weightPlaceHolder < placeHolderValue){
                placeHolderValue = weightPlaceHolder;
                lightestWeight = i;
            }
        }
        return db[lightestWeight];
    }
    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int
            upperBound) {
        int age;
        int count = 0;
        double height = 0;
        double averageHeight;

        for (int i = 0; i < db.length; i++) { //This loop will look at all the ages and for each age, an if loop will run
            age = Integer.parseInt(db[i][0]);

            if (age >= lowerBound && age <= upperBound) { //this if loop checks if the age is less than or more than the bounds
                height += Double.parseDouble(db[i][1]); //it adds up the total height and keeps count of how many times it loops
                count++;
            }
        }
        averageHeight = height / count; //finally, it divides the total heights by the times it was added up. Creating the average
        return averageHeight;
    }
}
