package pawn;

import main.Grid;

/**
* Lake is the class that creates a "lake" pawn.
*
* @author CAREDDA Giuliano, DUCOBU Alexandre
*/
public class Lake extends APawn{

	/**
	 * Main constructor of the lake pawn.
	 */
	public Lake(){
		setLevelPawn(0);
		setTeam(0);
		setNamePawn("lake");
		setURI("/image/lake2.png");
	}
	
	/**
	 * Tells that the lakes can't move.
	 * 
	 * @param grid
	 *    The grid of the game.
	 *    
	 * @param x
	 *    The abscissa of the 'lake' pawn.
	 *    
	 * @param y
	 *    The ordinate of the 'lake' pawn.
	 *    
	 * @return
	 *    False
	 */
	public boolean movePoss(Grid grid,int x, int y){
		return false;
	}
}
