/**
 * Individual ships information
 *
 * @author Raphael Suttiyotin
 * @version 17/10/18
 */
public class Ship
{
    private String shipName;
    private int xPos;
    private int yPos;
    private int noOfHitsMade;
    private int noOfHitsNeeded;
    
    /** 
     * Default constructor
     * Create default ship values
     */
    public Ship()
    {
        shipName = "USS Hopper";
        xPos = 2;
        yPos = 3;
        noOfHitsMade = 0;
        noOfHitsNeeded = 4;
    }
    
    /** 
     * Non default constructor
     * Create Ship object
     * @param inputShipName Name of the ship
     * @param inputXPos X coordinate
     * @param inputYPos Y coordinate
     * @param inputNoOfHitsNeeded ship health
     * @return Ship object
     * @throw IllegalStateException if inputShipName is blank 
     *          or inputXPos is negative
     *          or inputYPos is negative
     *          or inputNoOfHitsNeeded is less than 1
     */
    public Ship(String inputShipName, int inputXPos, int inputYPos, int inputNoOfHitsNeeded)
    {
        if (inputShipName == null)
            inputShipName = "";
        if (inputShipName.length() < 3 || inputShipName.length() > 15)
            inputShipName = "USS Zumwalt ";
        if (inputXPos < 0)
            inputXPos = -1;
        if (inputYPos < 0)
            inputYPos = -1;
        if (inputNoOfHitsNeeded < 1)
            inputNoOfHitsNeeded = -1;
        shipName = inputShipName.trim();
        xPos = inputXPos;
        yPos = inputYPos;
        noOfHitsNeeded = inputNoOfHitsNeeded;
        if (shipName.length() == 0)
            throw new IllegalStateException("Ship name cannot be blank");
        if (inputXPos == -1 || inputYPos == -1 || inputNoOfHitsNeeded == -1)
            throw new IllegalStateException("X,Y coordinates cannot be negative");
        if (inputNoOfHitsNeeded == -1)
            throw new IllegalStateException("Number of hits required must be more than 0");
    }
    
    /** 
     * Display ship name and coordinate position
     */
    public void display()
    {
        System.out.println("Ship name: " + shipName);
        System.out.println("Located at (x,y): " + "(" + xPos + "," + yPos + ")");
    }
    
    /** 
     * Return number of hits made
     */
    public int getNoOfHitsMade()
    {
        return noOfHitsMade;
    }
    
    /** 
     * Return number of hits needed (health)
     */
    public int getNoOfHitsNeeded()
    {
        return noOfHitsNeeded;
    }
    
    /** 
     * Return ship name
     */
    public String getShipName()
    {
        return shipName;
    }
    
    /** 
     * Return X coordinate position
     */
    public int getXPos()
    {
        return xPos;
    }
    
    /** 
     * Return Y coordinate position
     */
    public int getYPos()
    {
        return yPos;
    }

    /** 
     * Increase number of hits made
     */
    public void incNoOfHitsMade()
    {
        noOfHitsMade++;
    }
    
    /** 
     * Set number of hits made
     * @param inputHitsMade Number of hits made
     * @throw IllegalStateException if inputHitsMade is negative 
     */
    public void setNoOfHitsMade(int inputHitsMade)
    {
        if (inputHitsMade < 0)
            throw new IllegalStateException("Number of hits made cannot be negative");
        else
            noOfHitsMade = inputHitsMade;
    }
    
    /** 
     * Set number of hits needed
     * @param inputHitsneeded ship health
     * @throw IllegalStateException if inputHitsneeded is less than 1 
     */
    public void setNoOfHitsNeeded(int inputHitsneeded)
    {
        if (inputHitsneeded < 1)
            throw new IllegalStateException("Number of hits needed must be 1 or more");
        else
            noOfHitsNeeded = inputHitsneeded;
    }
    
    /** 
     * Set ship name
     * @param inputName ship name
     * @throw IllegalStateException if inputName is not between 3 and 15 characters
     */    
    public void setShipName(String inputName)
    {
        if (inputName.length() < 3 || inputName.length() > 15)
            throw new IllegalStateException("Ship name must be between 3 and 15 characters");
        else
            shipName = inputName;
    }

    /** 
     * Set X position
     * @param inputXPos X position
     * @throw IllegalStateException if inputXPos is negative 
     */
    public void setXPos(int inputXPos)
    {
        if (inputXPos < 0)
            throw new IllegalStateException("Coordinate cannot be negative");
        else
            xPos = inputXPos;
    }

    /** 
     * Set Y position
     * @param inputXPos Y position
     * @throw IllegalStateException if inputYPos is negative 
     */
    public void setYPos(int inputYPos)
    {
        if (inputYPos < 0)
            throw new IllegalStateException("Coordinate cannot be negative");
        else
            yPos = inputYPos;
    }
}