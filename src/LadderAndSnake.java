import java.util.Scanner;

public class LadderAndSnake {
	//------------------------------------------------------------------------------------------------------------
														//VARIABLES

	/**Array of objects of class Players, each object for each game player.
	 * 
	 */
	Players players[] = new Players[4]; 
	
	/**
	 * Object of type Players used for sorting 
	 */
	Players temp; 
	
/**
  *Scanner to allow users to input the number of players they want
  *and prompts them to press enter to continue to next round
  *
  */
	Scanner in = new Scanner(System.in);
							
	
	/**
	 * Integer used in loops.
	 */
	private int a,b,c,i; 
	/**
	 * Private static integer, the user's input, indicates the number of players chosen by the user.
	 */
    private static int number; 
    
    /**
	 * Private static integer that indicates the number of rounds that the players have been through.
	 */
    private static int round;
    
    /**
	 * Private static integer that increments each time the user inputs a wrong value of players.
	 * If the count reaches 4, the game shuts down.
	 */
    private static int count;
    
    /**
	 * Private static boolean that indicates if the user's input is valid, used for loop control 
	 */
	private static boolean validInput = false; 
	
	/**
	 * Private boolean responsible of initializing the game, used for loop control. 
	 */
	private boolean start; //BOOLEANS TO BE USED FOR TIE BREAKER METHOD
	
	/**
	 * Private boolean responsible of setting the tie, used for loop control. 
	 */
	private boolean tie;
	
	/**
	 * Private boolean responsible of setting the oddTie, used for loop control. 
	 */
	private boolean oddTie;
	//------------------------------------------------------------------------------------------------------------
														//CONSTRUCTOR
	/**
	 * Constructor that allows the creation of 4 players onto the driver, 
	 * with a built in loop to fill out the players array depending on 
	 * the user's input. 
	 * 
	 * @param numberOfPlayers Constructor's parameter dictated by the user's input.  
	 * 
	 */
	public LadderAndSnake(int numberOfPlayers) { 
		
		players = new Players[numberOfPlayers+1]; 
		
		for (a = 1; a <players.length; a++) { 
			players[a] = new Players(a);
		}
	}
	
	//------------------------------------------------------------------------------------------------------------
														//GAMEPLAY
	
	/**
	 * Welcome method that greets the user and prompts them to input the number of desired players.
	 * It will allow them of 4 chances of wrong input. 
	 */
	public void prompt() {
		
		//Initializing counter and tie variables
		count = 0;
		oddTie = false;
		tie = false;
		
		//Welcome message
		System.out.println("--------------------------WELCOME TO SNAKES AND LADDERS--------------------------");
		System.out.println("\nThis game can only be played by 2-4 players! STICK. TO. THE. BOUNDARIES! >:(");
		System.out.print("\nPlease specify the number of players you would like: ");
		
		
	    number = in.nextInt(); //User input for the number of players wanted
		
		
		if( number >= 2 && number <= 4) //If a user inputs the correct number of players
		{
		    validInput = true; //validInput to determine whether the input is correct
			System.out.println( "\nThe Game is played by " + number +  " players. ");
			System.out.println();
		}
	    
	    else {
		        while (validInput == false && count != 3) //While loop for bad attempts
			        {
		        	    count++;
			            if (number <= 4 && number >= 2) //If a user inputs the correct number of players
			            {
			                validInput = true;
			                System.out.println( "\nGame is played by " + number  +  " players. ");
			    			System.out.println();
			    			break;
			            }
			            if (validInput == false && count == 3) //Users last attempt before exiting game
			            {
			                System.out.print("Bad Attempt " + count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively. This is your last attempt: ");
			                number = in.nextInt();
			            }
			            else //Repeats bad attempt 3 times
			            {
			                System.out.print("Bad Attempt " + count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");
			                number = in.nextInt();
			            }
			           
			        }
			        //Terminate program after 4 bad attempts.
			        if (validInput == false && count == 3) { //After user places last attempt wrong, exits game
			        System.out.print("Bad Attempt 4! You have exhausted all your chances. You are not allowed to play anymore!");
			        System.out.println();
			        System.out.println("\n~|||||||||||||||||||||||||||||||||||||||||||||||||||||||||~|");
			        System.out.println();
			        System.out.println("                                You should've followed my rules...");
			        System.exit(0);
			        }

			    }
		for ( i = 1 ; i <= number ; i++ ) { //Initializes how many players the user wants using a loop
			players[number] = new Players(number); //Fills array of objects of type Players
		}
	}
	
	/**
	 * Method that allows the players to each roll their dices, and arranges the player's order depending on their
	 * dices values. In case of tie, the tied players get to roll again, and get their positions re-arranged 
	 * depending on their new dices values. 
	 * <p>
	 * Once they're sorted, they're ready to play.
	 */
	public void playerOrder() {	
		
		do {
			
			if(oddTie) { //Odd tie is a tie between 4 players or 2 pairs of players
				
				System.out.print("\nA tie was achieved between ");
				
				for (b = 1; b <= number; b++) { //for loop to fill in array of odd tied players
					
					if (players[b].isOddTie())
						System.out.print("Player " + players[b].getID() +", "); //Get ID method to mention all players who tied
				}
				System.out.println("Attempting to break the tie.");
				System.out.println();
			}
			
			for ( c = 1; c <= number; c++) {  //Loop to break the odd tie between players in order of higher dice value to lowest
				
		    	if (players[c].isOddTie()) {
		    		
		    		players[c].flipDice(); //Flips dice for all players
					players[c].calculateOrderOfPlayers(players[c].getDice(), count ); //Organizes the players in order of highest dice roll to lowest
					System.out.println( "Player " + players[c].getID() + " rolled the dice and got a " + players[c].getDice() +"." );
		    	}
		    	players[c].setOddTieFalse(); //Set the odd tie to false to proceed to normal tie
			}
			
			if(tie) { //Normal tie method
				
				System.out.print("\nA tie was achieved between ");
				
				for (b = 1; b <= number; b++) { //Does the same function as odd tie, but only for a tie between 2 or 3 players
					
					if (players[b].isTie())
						System.out.print("Player " + players[b].getID() +", ");
				}
				System.out.print("attempting to break the tie.");
				System.out.println();
				
			}
			
			for ( c = 1; c <= number; c++) { 
				
		    	if (players[c].isTie()) {
		    		
		    		players[c].flipDice(); //Flip dice for players
					players[c].calculateOrderOfPlayers(players[c].getDice(), count); //Organizes from highest to lowest dice value
					System.out.println( "Player " + players[c].getID() + " rolled the dice and got a " + players[c].getDice() + ".");
		    	}
		    	players[c].setTieFalse(); //Sets tie to false if tie not achieved
			}
			
			//Nested for loops to arrange players in order
			for ( a = 1 ; a <= number-1 ; a++ ) {
				 
				temp = players[a];
				
			    for ( b = a + 1; b <= number; b++) { 

	         	  //Main sort, Use temp sorting tool
				  if (players[b].getPlayerOrder() > temp.getPlayerOrder()) { 
	                     temp = players[b];
	                     players[b] = players[a];
	                     players[a] = temp;
	                 }
			    }
			}
			
			count++; //Iterate for count
			oddTie = false;
			tie = false;
			
			for(a = 1; a <= number-1; a++ ) {
				
				for (b = a+1 ; b <= number ; b++ ) {
					
					if(players[a].getPlayerOrder() == players[b].getPlayerOrder()) { //If the players have the same value of dice, split into 2 if statements
						
						if(a == 1) { //Odd tie case
							
							players[a].setOddTieTrue();
							players[b].setOddTieTrue();
							oddTie = true;
						}
						else if(a!=1 && !players[b].isOddTie()) { //Normal tie case
							
							players[a].setTieTrue();
							players[b].setTieTrue();
							tie = true;
						}	
					}
				}
			}	
		} 
		
		while(tie||oddTie); //Do while loop to run through the code first, then stops the loop if the tie or odd tie is false

		System.out.print("\nThe order of players is as it follows: "); //Displays order of player
		
		for (a = 1; a < number; a++) { //Loops through the number of players and displays their index. Player 1,2,3, or 4
			
			System.out.print("Player " + players[a].getID() + ", ");	
		}
		
		 System.out.print("Player " + players[number].getID() + "."); //"number" static integer is just the number inputed by how many players are in the game
	
		//Start game
		start = true ;
		System.out.println ( "\n\nGame starting. ");
		
	}

	/**
	 * The play method, allows each player to roll their dice by pressing enter, move the board, slide down a snake or climb up a ladder, and race for the win.
	 * Players go by order, dictated by the playerOrder method. Game ends with the first player hitting the 100th block.
	 */
  public void play() { 
	  
	do {  //Do while loop to actually run the code first, then stops when the user hits 100
		
		round++; //iterates for every round until a player hits 100
		String userPrompt = in.nextLine();
		System.out.println ( "\n\nRound " + round  + ". Press any key to continue."+userPrompt);
		
		for ( i = 1 ; i <= number ; i++ ) { //Initializes snakes and ladders on the board and sets all players to position 0.

		 players[i].flipDice();	//Rolls the dice every round
		 players[i].setPosition();
		 
		 players[i].ladderHit();
		 players[i].snakeHit();
		 
		 
		    if ( players[i].getPosition() < 100 && !players[i].getLadderHitTrue() && !players[i].getSnakeHitTrue() && players[i].getPosition() != 80) { //When the player hits an empty position on the board
		
		    	System.out.print("\nPlayer "+ players[i].getID() + " rolled the dice and got a value of " + players[i].getDice() + ". New Position: " + players[i].getPosition());
		    }
		    
		    //Ladder hit display
		    if ( players[i].getLadderHitTrue()) {
                   		    	
		    	System.out.print("\nPlayer "+ players[i].getID() + " rolled the dice and got a value of " + players[i].getDice() + " and hit a ladder! Now going up! New Position: " + players[i].getPosition());
		    	players[i].setLadderHitFalse();
		    }
		    
		    //Snake hit display
            if ( players[i].getSnakeHitTrue() ) {
		    	
		    	System.out.print("\nPlayer "+ players[i].getID() + " rolled the dice and got a value of " + players[i].getDice() + " and hit a snake! Darn, must go down. New Position: " + players[i].getPosition());
		    	players[i].setSnakeHitFalse();	
		    }
		    
		    if ( players[i].getPosition() > 100 ) { //If the players rolls the dice and exceeds position 100, moves them back.
			 
			 players[i].setPosition100(); //Method to set players position at 100
			 System.out.print("\nPlayer "+ players[i].getID() + " rolled the dice and got a value of " + players[i].getDice() + ". New Position: " + players[i].getPosition100()); //getPosition100 moves the player back from 100 depending on dice	 
		    }
		 
		    if (players[i].getPosition() == 100 ) { //When a player reaches 100 normally
			 
			 System.out.print("\nPlayer "+ players[i].getID() + " rolled the dice and got a value of " + players[i].getDice() + ". New Position: " + players[i].getPosition());
			 System.out.println("\n\nPlayer "+ players[i].getID() + " wins. \nGame over. \nInsert a coin to play again :) " );
			 
			 start = false; //Conditions start to false, thus ending the game
			 break; //Break differentiates this case from the next
		    }
		 
		    if (players[i].getPosition() == 80) { //There is a ladder at position 80 that leads to 100. if a player hits 80, they win by moving up to 100
		    	
		    	 System.out.print("\nPlayer "+ players[i].getID() + " rolled the dice and got a value of " + players[i].getDice() + ", and hit the ladder on 80 straight to the win! " );
		    	 System.out.print(" \n\nGame over. \nInsert a coin to play again :)  ");
		    	 start = false; //Conditions start to false, thus ending the game
		    	 break; //Break differentiates winning by going from 80 to 100, and winning normally
		    }
		}
	} while (start);
 }	
}