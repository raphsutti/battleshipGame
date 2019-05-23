import java.util.ArrayList;
/**
 * Contains an ArrayList of ships
 *
 * @author Raphael Suttiyotin
 * @version 17/10/18
 */
public class ShipList
{
    private ArrayList<Ship> ships;
    
    public ShipList()
    {
        ships = new ArrayList<Ship>();
    }
    
    /** 
     * Add new ship
     * @param shipName - Name of the ship
     * @param xPos - X coordinate
     * @param yPos - Y coordinate
     * @param noOfHitsNeeded - ship health
     * @return true if try again required, false if valid input
     */
    public boolean addShip(String shipName, int xPos, int yPos, int noOfHitsNeeded)
    {
        try
        {
            ships.add(new Ship(shipName,xPos,yPos,noOfHitsNeeded));
            System.out.println("Ship " + shipName + " added successfully");
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Error adding ship " + shipName + ". Invalid input");
            return true;
        }
    }
    
    /** 
     * Return ship using index value
     * @param index Index in the array of ships
     * @return Ship object if valid input 
     * (object not null and index not out of bounds)
     */
    public Ship getShip(int index)
    {
        try
        {
            return ships.get(index);
        }
        catch (NullPointerException | IndexOutOfBoundsException e)
        {
            System.out.println("Ship index out of bounds or no ship in index value");
            return null;
        }   
    }
    
    /** 
     * Return size of the ship list array
     * @return size of the array in integer format
     */
    public int getSize()
    {
        return ships.size();
    }
    
    /** 
     * Remove ship from the array
     * @param index Index in the array of ships 
     * @return void if valid input (object not null and index not out of bounds)
     */
    public void removeShip(int index)
    {
        try
        {
            ships.remove(index);
        }
        catch (NullPointerException | IndexOutOfBoundsException e)
        {
            System.out.println("Ship index out of bounds or no ship in index value");
        }
    }
}