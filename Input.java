import java.util.Scanner;
/**
 * Input class
 * Accepts input for String or integer
 *
 * @author Raphael Suttiyotin
 * @version (17/10/2018)
 */
public class Input
{
    public Input()
    {

    }
 
    /** 
     * Obtain input of type integer from user
     * @param subject Subject for prompt
     * @return integer from user
     */
    public int acceptInputInt(String subject)
    {        
        Scanner console = new Scanner(System.in);      
        System.out.print("Enter " + subject + ": ");
        while (!console.hasNextInt())
        {
            System.out.println("Your choice has to be an integer");
            console.nextLine();
        }
        return console.nextInt();
    }
    
    /** 
     * Obtain input of type String from user
     * @param subject Subject for prompt
     * @return String from user
     */
    public String acceptInputString(String subject)
    {        
        Scanner console = new Scanner(System.in);
        System.out.print("Enter " + subject + ": ");
        return console.nextLine();
    }
}