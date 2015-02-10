package pawn;
import java.util.Vector;

import main.Grid;

/**
* This class creates a "pawn" object.
*
* @author CAREDDA Giuliano, DUCOBU Alexandre
*/
public abstract class APawn{

	protected int levelPawn;
	protected String namePawn;
	protected int team=0;
	protected int value;
	public int posX;
	public int posY;
	
	public String toString(){
		if (levelPawn<10){
		return String.valueOf(levelPawn)+" ";
		}
		return String.valueOf(levelPawn);
	}
	
	public void setPos(int posX,int posY){
		this.posY=posY;
		this.posX=posX;
	}
	
	protected void setValue(int valuePawn){
		this.value=valuePawn;
	}
	
	/**
	 * Set the level of the pawn.
	 * 
	 * @param levelPawn
	 *    The level of the pawn, must be between 0 and 12  
	 */
	protected void setLevelPawn(int levelPawn){
		this.levelPawn=levelPawn;
	}
	
	/**
	 * Set the name of the pawn.
	 * 
	 * @param namePawn
	 *    The name of the pawn
	 */
	protected void setNamePawn(String namePawn){
		this.namePawn=namePawn;
	}
	
	/**
	 * Set the team of the pawn.
	 * 
	 * @param team
	 *    The number of the team, 0 for neutral, 1 for red, 2 for blue
	 */
	protected void setTeam(int team){
		this.team=team;
	}
	
	/**
	 * Tell if the pawn can move.
	 * 
	 * @return
	 *    A boolean: true or false
	 */
	public boolean canMove(){
		return true;
	}
	
	/**
	 * Make a vector of the 40 pawn in a team at the begin of the game.
	 * 
	 * @return
	 *    The vector of 40 pawn
	 */
	public static Vector<APawn> createTeam(){
		Vector<APawn> listPawn = new Vector<APawn>();
		for (int i=0;i<6;i++){
			listPawn.add(new Bomb());
		}
		for (int i=0;i<8;i++){
			listPawn.add(new Scout());
		}
		for (int i=0;i<5;i++){
			listPawn.add(new Miner());
		}
		for (int i=0;i<4;i++){
			listPawn.add(new Sergeant());
		}
		for (int i=0;i<4;i++){
			listPawn.add(new Captain());
		}
		for (int i=0;i<4;i++){
			listPawn.add(new Lieutenant());
		}
		for (int i=0;i<3;i++){
			listPawn.add(new Major());
		}
		for (int i=0;i<2;i++){
			listPawn.add(new Colonel());
		}
		listPawn.add(new Spy());
		listPawn.add(new Marshal());
		listPawn.add(new General());
		listPawn.add(new Flag());
		
		return listPawn;
	}
	
	public int getLevel(){
		return levelPawn;
	}
	
	public int getValue(){
		return value;
	}

	public int getTeam(){
		return this.team;
	}
	/**
	 * test if a move is possible
	 * @param grid the grid of the game
	 * @param x the coord to move
	 * @param y the coord to move
	 * @return the grid after the moving
	 */
	public boolean movePoss(Grid grid,int x, int y){
		APawn target = grid.get(x, y); 
		if (target instanceof Lake){ //test if the target isnt a lake
			return false;
		}
		else if (target instanceof APawn){ //test if the target isnt a pawn of the same team
			if (target.getTeam()==this.team){
				return false;
			}
		}
		else if (x-this.posX!=1 && x-this.posX!=-1 ){ //test if the move is not too long
			return false;
		}
		else if (y-this.posY!=1 && y-this.posY!=-1){ //test if the move is not too long
			return false;
		}
		else if (x-this.posX!=0 && y-this.posY!=0){ //test if the move is in the same lane
			return false;
		}
		else if (y-this.posY==0 && x-this.posX==0){ //test if the pawn isnt already on the target
			return false;
		}
		return true;
	}
	/**
	 * return the result of the fight between this pawn and the target
	 * @param tar the pawn who is targeted by this pawn
	 * @return 0 if it's a drawn <br/> 1 if this pawn win <br/> 2 if this pawn loose
	 */
	public int attack(APawn tar){
		if(tar.getLevel()==this.levelPawn){
			return 0;
		}
		if(tar.getLevel()<this.levelPawn){
			return 1;
		}
		if(tar.getLevel()>this.levelPawn){
			return 2;
		}
		return -1;
	}
	/**
	 * move the pawn !warning: be careful to test if the moving is possible BEFORE with movePoss(Grid grid,int x, int y)
	 * @param grid the grid of the game
	 * @param x the coord to move
	 * @param y the coord to move
	 * @return the grid after the moving
	 */
	public Grid move(Grid grid, int x, int y){
		APawn tar = grid.get(x, y);
		if (tar==null){
			grid.set(x, y, this);
			return grid;
		}
		int res = this.attack(tar);
		if (res==0){
			tar.setPos(-1, -1);
			this.setPos(-1,-1);
			grid.set(x, y, null);
			grid.set(this.posX, this.posY, null);
		}
		else if (res==1){
			tar.setPos(-1, -1);
			grid.set(x,y,this);
		}
		else if (res==2){
			this.setPos(-1, -1);
			grid.set(this.posX,this.posY,null);
		}
		return grid;
	}
}
