public class Players {

	//------------------------------------------------------------------------------------------------------------
														//VARIABLES
	
	//Variables to be used throughout the methods. Uses will be explained in each method.
	private int dice, value, playerPosition, ID; 
	private double playerOrder;
	private boolean playerTie, playerOddTie;
	private boolean ladderHit = false;
	private boolean snakeHit = false;
	Players temp = null;

	
	//------------------------------------------------------------------------------------------------------------
														//CONSTRUCTOR
	
	/*The constructor of the class Players constructs all the valid variables used in 
	 * different methods. The parameter in the constructor allows the user to construct as many players
	 * as needed.
	 */
	
	public Players(int pInd) {
		playerPosition = 0;
		ID = pInd;
		playerTie = true;
		playerOddTie = false;
	}
	
	//------------------------------------------------------------------------------------------------------------
														//MUTATORS
	
	//Mutator to set players position
	public void setPosition() {
		this.playerPosition += dice;
	}
	
	//Mutator to move player back if they roll out of the range of 100
	public void setPosition100() {
		
		value = this.playerPosition - 100 ;
		this.playerPosition = 100 - value;
	}
	
	/*This mutator calculates the order players must be in based on their dice value
	 * It is also used to distinguish between the tied players and the other players
	 */
	public void calculateOrderOfPlayers(int dice, int count) {
		playerOrder += dice * Math.pow(2, - 3*count);
	}
	
	//Mutator to set the index of what the player is. EX: player 1, ID 1.
	public void setID(int ID) {
		this.ID = ID;
	}
    
	/*This mutator sets the odd tie to true
	 * Odd tie meaning if all 4 players are tied, or if there are 2 pairs of tied players
	 */
    public void setOddTieTrue() {playerOddTie = true;}
    
    //Sets odd tie to false
    public void setOddTieFalse() {playerOddTie = false;}
	
    //Mutator that sets a normal tie to true
    public void setTieTrue() {playerTie = true;}
    
    //Mutator that sets a normal tie to false
    public void setTieFalse() {playerTie = false;}
    
	//Flip dice method
	public void flipDice() {
		dice = (int)(Math.random()*6 + 1); //Random integer value between 1-6
	}
	
	//Mutator to set ladders using swtich cases of the players position
	public void ladderHit() {
				 
				switch ( playerPosition ) {
				case 1:
					ladderHit = true;
					playerPosition = 38;
					break;
				case 4:
					ladderHit = true;
					playerPosition = 14;
					break;
				case 9:
					ladderHit = true;
					playerPosition = 31;
					break;
				case 21:
					ladderHit = true;
					playerPosition = 42;
					break;
				case 28:
					ladderHit = true;
					playerPosition = 84;
					break;
				case 36:
					ladderHit = true;
					playerPosition = 44;
					break;
				case 51:
					ladderHit = true;
					playerPosition = 67;
					break;
				case 71:
					ladderHit = true;
					playerPosition = 91;
					break;
				 }
				
			}
			
		//Mutator that sets the snakes and moves players position using swtich cases
		public void snakeHit() {
	 
				switch ( playerPosition ) {
				case 16:
					playerPosition = 6;
					snakeHit = true ;
					break;
				case 48:
					playerPosition = 30;
					snakeHit = true ;
					break;
				case 64:
					playerPosition = 60;
					snakeHit = true ;
					break;
				case 79:
					playerPosition = 19;
					snakeHit = true ;
					break;
				case 93:
					playerPosition = 68;
					snakeHit = true ;
					break;
				case 95:
					playerPosition = 24;
					snakeHit = true ;
					break;
				case 97:
					playerPosition = 76;
					snakeHit = true ;
					break;
				case 98:
					playerPosition = 78;
					snakeHit = true ;
					break;
				
					}	
			}
		
	//Mutator to indicate that the player hit a ladder
	public void setLadderHitTrue() {this.ladderHit = true;}
	
	//Mutator to indicate that the player did not hit a ladder
	public void setLadderHitFalse() {this.ladderHit = false;}
	
	//Mutator to indicate that the player hit a snake
	public void setSnakeHitTrue() {
	    this.snakeHit = true;
	}
	
	//Mutator to indicate that the player did not hit a snake
	public void setSnakeHitFalse() {
	    this.snakeHit = false;
	}
	
	//------------------------------------------------------------------------------------------------------------
														//ACCESSORS
	
	//Accessor of setPosition
	public int getPosition() {return playerPosition;}
	
	//Accessor of setPosition100
	public int getPosition100() {return playerPosition;}
	
	//Accessor of setPlayerOrder
	public double getPlayerOrder() {return playerOrder;}

	//Accessor of flipDice
	public int getDice() {return dice;}
   
	//Accessor of setID
	public int getID() {return ID;}
	
	//Accessor of oddTie either false or true
    public boolean isOddTie(){return playerOddTie;}
    
    //Accessor of normal tie false or true
	public boolean isTie() {return playerTie;}
	
	//Ladder hit
	public boolean getLadderHitTrue() {return ladderHit ;}

	//Snake hit
	public boolean getSnakeHitTrue() {
		return snakeHit;
		}
}