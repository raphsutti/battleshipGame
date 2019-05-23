/**
 * This is where the game starts
 * Links to playerShips and computerShips arrayList
 * Obtains input from coordinateGenerator, fileIO, input, and validation
 * 
 * @author Raphael Suttiyotin
 * @version 17/10/18
 */
public class Game
{
    private ShipList playerShips;
    private ShipList computerShips;
    private CoordinateGenerator coordinate;
    private Validation validation;
    private Input input;
    private FileIO file;
    
    /** 
     * Default constructor
     * Create shiplist (player and computer
     * Create coordinate generator object
     * Create validation object
     * Create input object
     * Create fileIO object (default setting and result filenames)
     */
    public Game()
    {
        playerShips = new ShipList();
        computerShips = new ShipList();
        coordinate = new CoordinateGenerator();
        validation = new Validation();
        input = new Input();
        file = new FileIO();
    }
    
    /** 
     * Computer ship coordinate check
     * @param shipXCoord Ship x coordinate
     * @param shipYCoord Ship y coordinate
     * @return true if coordinate taken, false if coordinate not taken
     */
    public boolean computerShipCoordinateFound(int shipXCoord, int shipYCoord)
    {
        boolean shipCoordinateTaken = false;
        int shipCounter = 0;
        while (shipCoordinateTaken == false && shipCounter < computerShips.getSize())
        {
            if (computerShips.getShip(shipCounter).getXPos() == shipXCoord &&
            computerShips.getShip(shipCounter).getYPos() == shipYCoord)
                shipCoordinateTaken = true;
                
            shipCounter++;
        }
        if (shipCoordinateTaken)
            return true;
        else
            return false;
    }
    
    /** 
     * Computer ship number search using coordinate
     * @param shipXCoord Ship x coordinate
     * @param shipYCoord Ship y coordinate
     * @return index value of computer ship
     */
    public int compShipCoordIndexFound(int shipXCoord, int shipYCoord)
    {
        int shipCounter = 0;
        while (shipCounter < computerShips.getSize())
        {
            if (computerShips.getShip(shipCounter).getXPos() == shipXCoord &&
            computerShips.getShip(shipCounter).getYPos() == shipYCoord)
                return shipCounter;
                
            shipCounter++;
        }     
        return 0;
    }
    
    /** 
     * Computer ship health check 
     * @param shipIndex Ship index value
     * @return true if ship is destroyed, false if ship still alive
     */
    public boolean computerShipDestroyed(int shipIndex)
    {
        if (computerShips.getShip(shipIndex).getNoOfHitsMade() >= 
            computerShips.getShip(shipIndex).getNoOfHitsNeeded())
            return true;
        else
            return false;
    }

    /** 
     * Display grid with computer ships
     * (O = ship, D = damaged ship, X = destroyed ship, ~ = unoccupied)
     * @param maxGridDimension Grid width and height
     * @param compVisible Visibility of computer ship (true = visible)
     */
    public void displayComputerGridShips(int maxGridDimension, boolean compVisible)
    {
        for (int yCoord = maxGridDimension; yCoord >= 0; yCoord--)
        {
            System.out.println("");
            for (int xCoord = 0; xCoord <= maxGridDimension; xCoord++)
            {
                if (computerShipCoordinateFound(xCoord,yCoord))
                {
                    if (computerShips.getShip(compShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsMade() > 0 &&
                        computerShips.getShip(compShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsMade() <
                        computerShips.getShip(compShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsNeeded())
                        System.out.print("D");
                    else if (computerShips.getShip(compShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsMade() >= 
                        computerShips.getShip(compShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsNeeded())
                        System.out.print("X");
                    else if (compVisible)
                        System.out.print("O");
                    else
                        System.out.print("~");
                }
                else
                    System.out.print("~");
            }
        }
        System.out.println("");
        System.out.println("");
    }
    
    /** 
     * Display grid for player ships
     * (O = ship, D = damaged ship, X = destroyed ship, ~ = unoccupied)
     * @param maxGridDimension Grid width and height
     */
    public void displayPlayerGridShips(int maxGridDimension)
    {
        for (int yCoord = maxGridDimension; yCoord >= 0; yCoord--)
        {
            System.out.println("");
            for (int xCoord = 0; xCoord <= maxGridDimension; xCoord++)
            {
                if (playerShipCoordinateFound(xCoord,yCoord))
                {
                    if (playerShips.getShip(playerShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsMade() > 0 &&
                        playerShips.getShip(playerShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsMade() <
                        playerShips.getShip(playerShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsNeeded())
                        System.out.print("D");
                    else if (playerShips.getShip(playerShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsMade() >= 
                        playerShips.getShip(playerShipCoordIndexFound(xCoord,yCoord)).getNoOfHitsNeeded())
                        System.out.print("X");
                    else
                        System.out.print("O");
                }
                else
                    System.out.print("~");
            }
        }
        System.out.println("");
        System.out.println("");
    }
    
    /** 
     * Read settings file
     * @return Array of strings with settings in specific index position
     */
    public String[] fetchSettings()
    {
        return file.readFile();
    }
    
    /** 
     * Starting the game method
     * @param maxGridDimension Grid width and height
     * @param compVisible Computer visibility mode
     */
    public void gameStart(int maxGridDimension, boolean compVisible)
    {
        boolean gameFinished = false;
        int playerScore = 0;
        int computerScore = 0;
        int playerNumShips = playerShips.getSize();
        int computerNumShips = computerShips.getSize();
        while (!gameFinished)
        {
            System.out.println("Displaying player grid..");
            displayPlayerGridShips(maxGridDimension); // grid display for player
            System.out.println("Displaying computer grid..");
            displayComputerGridShips(maxGridDimension, compVisible); // grid display for computer
            System.out.println("Player to make a guess");
            System.out.println("Please enter X and Y coordinate between 0 and " + maxGridDimension);
            int xGuess = input.acceptInputInt("ship x position");
            while (xGuess < 0 || xGuess > maxGridDimension)
            {
                System.out.println("X coordinate must be between 0 and " + maxGridDimension);
                xGuess = input.acceptInputInt("ship x position");
            }
            int yGuess = input.acceptInputInt("ship y position");
            while (yGuess < 0 || yGuess > maxGridDimension)
            {
                System.out.println("Y coordinate must be between 0 and " + maxGridDimension);
                yGuess = input.acceptInputInt("ship y position");
            }

            // Check if guess correct and ship not destroyed
            if (computerShipCoordinateFound(xGuess,yGuess) && 
                computerShips.getShip(compShipCoordIndexFound(xGuess,yGuess)).getNoOfHitsMade() <
                computerShips.getShip(compShipCoordIndexFound(xGuess,yGuess)).getNoOfHitsNeeded())
            {
                playerScore += 10;
                int shipIndex = compShipCoordIndexFound(xGuess,yGuess);
                System.out.println("Player hits!!");
                System.out.println("Player score: " + playerScore);
                System.out.println("");
                computerShips.getShip(shipIndex).incNoOfHitsMade();
                if (computerShipDestroyed(shipIndex))
                {
                    System.out.println("Unfortunately " + 
                    computerShips.getShip(shipIndex).getShipName() + 
                    " was destroyed");
                    System.out.println("");
                    computerNumShips--;
                }
            }
            else
            {
                System.out.println("Player missed!");
                System.out.println("Player score: " + playerScore);
                System.out.println("");
            }
            
            // Computer random guess
            int compXGuess = coordinate.rand(0,maxGridDimension);
            int compYGuess = coordinate.rand(0,maxGridDimension);
            System.out.println("Computer guess x position: " + compXGuess);
            System.out.println("Computer guess y position: " + compYGuess);
            
            // Check if guess correct and ship not destroyed
            if (playerShipCoordinateFound(compXGuess,compYGuess) &&
                playerShips.getShip(playerShipCoordIndexFound(xGuess,yGuess)).getNoOfHitsMade() <
                playerShips.getShip(playerShipCoordIndexFound(xGuess,yGuess)).getNoOfHitsNeeded())
            {
                computerScore += 10;
                int shipIndex = playerShipCoordIndexFound(compXGuess,compYGuess);
                System.out.println("Computer hits!!");
                System.out.println("Computer score: " + computerScore);
                System.out.println("");
                playerShips.getShip(shipIndex).incNoOfHitsMade();
                if (playerShipDestroyed(shipIndex))
                {
                    System.out.println("Unfortunately " + 
                    playerShips.getShip(shipIndex).getShipName() + 
                    " was destroyed");
                    System.out.println("");
                    playerNumShips--;
                }
            }
            else
            {
                System.out.println("Computer missed!");
                System.out.println("Computer score: " + computerScore);
                System.out.println("");
            }
            if (computerNumShips == 0)
            {
                gameFinished = true;
                System.out.println("Congratulations, you win!");
                file.writeFile("Player",playerScore,computerScore); // Output result
            }
            if (playerNumShips == 0)
            {
                gameFinished = true;
                System.out.println("Game over. All of your ships are destroyed");
                file.writeFile("Computer",playerScore,computerScore); // Output result
            }
        }
    }
    
    /** 
     * Display settings information
     * @param gridWidth Grid width
     * @param gridHeight Grid height
     * @param maxShips Number of ships
     * @param multiHits Multi hits allowed or not
     * @param compVisible Computer visibility mode
     */
    public void infoMenu(int gridWidth, int gridHeight, int maxShips, boolean multiHits, boolean compVisible)
    {
        System.out.println("The game will use the grid size defined in the settings file.");
        System.out.println("Playing grid size set as " + gridWidth + " X " + gridHeight);
        System.out.println("Maximum number of ships allowed: " + maxShips);
        System.out.println("Multiple hits allowed per ships: " + multiHits);
        System.out.println("Computer ships visible: " + compVisible);
        System.out.println("");
    }
    
    /** 
     * Input ship details
     * @param inputNoShip Number of ships to be created
     * @param maxGridDimension Grid dimension
     * @param multiHits Multi hits allowed or not
     */
    public void inputShipDetails(int inputNoShip, int maxGridDimension, boolean multiHits)
    {
        playerShips = new ShipList();
        computerShips = new ShipList();
        String name = "";
        int xCoord = 0;
        int yCoord = 0;
        int maxHealth = 0;   
        
        // Creating player ships and state
        for (int counter = 0; counter < inputNoShip; counter++)
        {
            boolean finished = false;
            while (!finished)
            {
                System.out.println("Setting up ship# " + (counter + 1) + "...");
                name = input.acceptInputString("ship name");
                while (validation.stringIsBlank(name) ||
                    !validation.stringLengthWithinRange(name, 3, 15))
                {
                    System.out.println("Name must be between 3 and 15 characters long");
                    name = input.acceptInputString("ship name");
                }
                
                boolean coordinateFound = false;
                do
                {
                    coordinateFound = false;
                    System.out.println("Please enter X and Y coordinate between 0 and " + 
                    maxGridDimension);
                    
                    xCoord = input.acceptInputInt("ship x position");
                    while (xCoord < 0 || xCoord > maxGridDimension)
                    {
                        System.out.println("X coordinate must be between 0 and " + maxGridDimension);
                        xCoord = input.acceptInputInt("ship x position");
                    }
                    
                    yCoord = input.acceptInputInt("ship y position");
                    while (yCoord < 0 || yCoord > maxGridDimension)
                    {
                        System.out.println("Y coordinate must be between 0 and " + maxGridDimension);
                        yCoord = input.acceptInputInt("ship y position");
                    }
                    
                    int shipCounter = 0;
                    while (coordinateFound == false && shipCounter < playerShips.getSize())
                    {
                        if (playerShips.getShip(shipCounter).getXPos() == xCoord &&
                        playerShips.getShip(shipCounter).getYPos() == yCoord)
                        {
                            System.out.println("Ship location occupied, pick another coordinate");
                            coordinateFound = true;
                        }
                        shipCounter++;
                    }
                    
                } while (coordinateFound == true);
                
                if (multiHits)
                    maxHealth = coordinate.rand(1,5);
                else
                    maxHealth = 1;
                    
                finished = true;
                boolean tryAgain = true;
                while (tryAgain)
                {
                    tryAgain = playerShips.addShip(name,xCoord,yCoord,maxHealth);
                }
                System.out.println("Finished setting up ship# " + (counter + 1));
                System.out.println("");
            }
        }
        
        // Creating computer ships and state
        System.out.println("Generating computer ships data...");
        for (int counter = 0; counter < inputNoShip; counter++)
        {
            boolean shipCoordinateFound = false;
            int shipXCoord = 0;
            int shipYCoord = 0;
            while (shipCoordinateFound == false)
            {
                // assign random coordinate
                shipXCoord = coordinate.rand(0,maxGridDimension);
                shipYCoord = coordinate.rand(0,maxGridDimension);
                if (multiHits)
                    maxHealth = coordinate.rand(1,5);
                else
                    maxHealth = 1;
                    
                if (!computerShipCoordinateFound(shipXCoord,shipYCoord))
                {
                    computerShips.addShip("USS Hopper " + 
                    counter,shipXCoord,shipYCoord,maxHealth);
                    shipCoordinateFound = true;
                }     
            }
        }
        System.out.println("Computer ships data generated successfully");
        System.out.println("");
    }
    
    /** 
     * Player ship coordinate check
     * @param shipXCoord Ship x coordinate
     * @param shipYCoord Ship y coordinate
     * @return true if coordinate taken, false if not taken
     */
    public boolean playerShipCoordinateFound(int shipXCoord, int shipYCoord)
    {
        boolean shipCoordinateTaken = false;
        int shipCounter = 0;
        while (shipCoordinateTaken == false && shipCounter < playerShips.getSize())
        {
            if (playerShips.getShip(shipCounter).getXPos() == shipXCoord &&
                playerShips.getShip(shipCounter).getYPos() == shipYCoord)
                shipCoordinateTaken = true;   
            shipCounter++;
        }
        if (shipCoordinateTaken)
            return true;
        else
            return false;
    }
    
    /** 
     * Player ship index search using coordinate
     * @param shipXCoord Ship x coordinate
     * @param shipYCoord Ship y coordinate
     * @return ship index number
     */
    public int playerShipCoordIndexFound(int shipXCoord, int shipYCoord)
    {
        int shipCounter = 0;
        while (shipCounter < playerShips.getSize())
        {
            if (playerShips.getShip(shipCounter).getXPos() == shipXCoord &&
                playerShips.getShip(shipCounter).getYPos() == shipYCoord)
                return shipCounter;
            shipCounter++;
        } 
        return 0;
    }
    
    /** 
     * Player ship health check 
     * @param shipIndex Ship index value
     * @return true if ship is destroyed, false if ship still alive
     */
    public boolean playerShipDestroyed(int shipIndex)
    {
        if (playerShips.getShip(shipIndex).getNoOfHitsMade() >= 
            playerShips.getShip(shipIndex).getNoOfHitsNeeded())
            return true;
        else
            return false;
    }
    
    /** 
     * Main starting method
     * Calls other necessary methods for the game
     */
    public void start()
    {
        String[] settings = new String[4];
        welcomeDisplay();
        settings = fetchSettings();
        int gridWidthHeight = Integer.parseInt(settings[0]);
        boolean multiHits = Boolean.valueOf(settings[1]);
        boolean compVisible = Boolean.valueOf(settings[2]);
        int noOfShips = Integer.parseInt(settings[3]);
        // Display settings result
        infoMenu(gridWidthHeight, 
            gridWidthHeight, 
            noOfShips, 
            multiHits, 
            compVisible); 
        // Enter user input mode
        inputShipDetails(noOfShips, gridWidthHeight-1, multiHits); 
        // Start the game with obtained settings
        gameStart(gridWidthHeight-1, compVisible); 
    }
    
    /** 
     * Display welcome message
     */
    public void welcomeDisplay()
    {
        System.out.println("------------------------------------------");
        System.out.println("Welcome to Battleship Game with a Twist!");
        System.out.println("------------------------------------------");  
    }
}