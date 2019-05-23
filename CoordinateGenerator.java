/**
 * CoordinateGenerator class
 * Creates random number beween a set of minimum
 * and maximum values (inclusive)
 *
 * @author Raphael Neo Suttiyotin
 * @version 17/10/2018
 */
public class CoordinateGenerator
{
    private int minimumValue;
    private int maximumValue;

    /** 
     * Default constructor
     * Create default minimum and maximum values
     */
    public CoordinateGenerator()
    {
        minimumValue = 0;
        maximumValue = 0;
    }
    
    /** 
     * Display minimum and maximum value
     */
    public void displayValues()
    {
        System.out.println("Min value is: " + minimumValue);
        System.out.println("Max value is: " + maximumValue);
    }
    
    /** 
     * Return minimum value
     */
    public int getMinValue()
    {
        return minimumValue;
    }
    
    /** 
     * Return maximum value
     */
    public int getMaxValue()
    {
        return maximumValue;
    }
    
    /** 
     * Generate random number between minimum and maximum value
     * @param minimumValue Minimum value
     * @param maximumValue Maximum value
     * @throw IllegalStateException if minimum value is larger than maximum 
     */
    public int rand(int minimumValue, int maximumValue)
    {
        if (minimumValue > maximumValue)
            throw new IllegalStateException("Minimum value cannot be larger than maximum");
        else
        {
            // +1 to include upper limit as (int) rounds down
            return (int)(Math.random() * 
            (maximumValue - minimumValue + 1)) + 
            minimumValue;
        }
    }
    
    /** 
     * Set minimum value
     * @param inputMin Minimum value
     */
    public void setMinValue(int inputMin)
    {
        minimumValue = inputMin;
    }
    
    /** 
     * Set maximum value
     * @param inputMax Maximum value
     */
    public void setMaxValue(int inputMax)
    {
        maximumValue = inputMax;
    }

}
