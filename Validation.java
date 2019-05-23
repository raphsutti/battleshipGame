/**
 * Validation class
 *
 * @author Raphael Suttiyotin
 * @version 17/10/18
 */
public class Validation
{
    public Validation()
    {

    }
    
    /** 
     * Check integer within min and max value inclusive
     * @param intInput Integer value to be checked
     * @param minValue Minimum value
     * @param maxValue Maximum value
     * @return true if within range, false if outside range
     * @throw IllegalStateException if minimum value is larger than maximum 
     */
    public boolean intWithinRange(int intInput, int minValue, int maxValue)
    {
        if (minValue > maxValue)
            throw new IllegalStateException("Minimum value cannot be larger than maximum");
        else
        {
            if (intInput >= minValue && intInput <= maxValue)
                return true;
            else
                return false;
        }
    }

    /** 
     * Check String is blank
     * @param stringInput String to be checked
     * @return true if blank, false if not blank
     */
    public boolean stringIsBlank(String stringInput)
    {
        if (stringInput.length() == 0)
            return true;
        else
            return false;    
    }
    
    /** 
     * Checks string if within min and max length inclusive
     * @param stringInput String to be checked
     * @param minLength Minimum length 
     * @param maxLength Maximum length
     * @return true if within range, false if outside range
     * @throw IllegalStateException if minimum value is larger than maximum 
     */
    public boolean stringLengthWithinRange(String stringInput, int minLength, int maxLength)
    {
        if (minLength > maxLength)
            throw new IllegalStateException("Minimum value cannot be larger than maximum");
        else
        {
            if (stringInput.length() >= minLength && 
                stringInput.length() <= maxLength)
                return true;
            else
                return false;
        }
    }
}
