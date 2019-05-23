import java.io.*;
import java.util.Scanner;
/**
 * Reads from file and write to file using file name
 *
 * @author Raphael Suttiyotin
 * @version 17/10/18
 */
public class FileIO
{
    private String settingsFileName;
    private String resultsFileName;
    
    /** 
     * Default constructor
     * Using default file names
     */
    public FileIO()
    {
        settingsFileName = "gamesettings.txt";
        resultsFileName = "gameoutcome.txt";
    }
    
    /** 
     * Non default constructor
     * Create FileIO object
     * @param inputSettingsFileName File name of settings
     * @param inputResultsFileName File name of results output
     * @return Ship object
     * @throw IllegalStateException if inputSettingsFileName is blank 
     *          or inputResultsFileName is blank
     */
    public FileIO(String inputSettingsFileName, String inputResultsFileName)
    {
        if (settingsFileName == null)
            settingsFileName = "";
        if (resultsFileName == null)
            resultsFileName = "";
        settingsFileName = inputSettingsFileName;
        resultsFileName = inputResultsFileName;
        if (settingsFileName.length() == 0 || resultsFileName.length() == 0)
            throw new IllegalStateException("File name cannot be blank");
    }
    
    /** 
     * Return file name of settings file
     */
    public String getSettingsFileName()
    {
        return settingsFileName;
    }
    
    /** 
     * Return file name of results file
     */
    public String getResultsFileName()
    {
        return resultsFileName;
    }
    
    /** 
     * Return array of string from reading a file
     * Separated by ","
     * @param index Index in the array of ships
     * @return array if file found
     */
    public String[] readFile()
    {
        String fileName = getSettingsFileName();
        String[] resultList = new String[4];
        try
        {
            FileReader fileReader = new FileReader(fileName);
            System.out.println("Reading file from " + fileName);
            try
            {
                Scanner parser = new Scanner(fileReader);
                String text = parser.nextLine();
                resultList = text.split(",");
                return resultList;
            }
            finally
            {
                System.out.println("File read successfully.. closing file");
                fileReader.close();
            }
        }
        catch(FileNotFoundException exception) 
        {
            System.out.println("Cannot find file '" + fileName + "'");
        }
        catch(IOException exception)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        return resultList;
    }
    
    /** 
     * Set file name of settings file
     * @param inputFileName settings file name
     * @throw IllegalStateException if inputFileName is blank 
     */
    public void setSettingsFileName(String inputFileName)
    {
        if (settingsFileName.length() == 0)
            throw new IllegalStateException("Settings file name cannot be blank");
        else
            settingsFileName = inputFileName;
    }
    
    /** 
     * Set file name of results file
     * @param inputFileName results file name
     * @throw IllegalStateException if inputFileName is blank 
     */
    public void setResultsFileName(String inputFileName)
    {
        if (inputFileName.length() == 0)
            throw new IllegalStateException("Output file name cannot be blank");
        else
            resultsFileName = inputFileName;
    }

    /** 
     * Write results into file name
     * @param winner Winner (player/computer)
     * @param playerScore Player final score
     * @param computerScore Computer final score
     */
    public void writeFile(String winner, int playerScore, int computerScore)
    {
        String fileName = getResultsFileName();
        try
        {
            PrintWriter writer = new PrintWriter(fileName);
            System.out.println("Writing result to " + fileName);
            try
            {
                writer.write(winner + 
                " wins. Final Score Player(" + 
                playerScore + 
                ") and Computer(" + 
                computerScore + 
                ")");
            }
            finally
            {
                System.out.println("File " + fileName + 
                " written successfully.. closing file");
                writer.close();
            }
        }
        catch(IOException exception)
        {
            System.out.println("Unable to write file to " + fileName);
        }
    }  
}
