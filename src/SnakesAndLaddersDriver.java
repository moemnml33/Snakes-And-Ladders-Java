/**
* Ladders and Snakes
* The program is a program that runs and allows 2 to 4 players 
* to play the classic Ladders and Snakes game.  
* <p>
* @author  Nader Nasr & Mohamad Boukaili
* @since   2021-02-08 
*/

/**
 * The main driver, where three main methods are invoked to run the game.
 * 
 * @param game  
 * 
 *
 */
public class SnakesAndLaddersDriver {

	
	public static void main(String[] args) {
		
		 LadderAndSnake game = new LadderAndSnake(4); //Object of Class LadderAndSnake
		 
		 game.prompt(); //Calls the Welcome Message and prompts user to set number of players
		 game.playerOrder(); //Calls the method to organize the players based on their dice roll values
		 game.play(); //Method that starts the game
		 
	}
}