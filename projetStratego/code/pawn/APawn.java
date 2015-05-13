package pawn;


import java.util.Vector;

import main.Game;

/**
 * This class creates a "pawn" object.
 * 
 * @author CAREDDA Giuliano, DUCOBU Alexandre
 */
public abstract class APawn implements java.io.Serializable {

	private static final long serialVersionUID = 4209147509705686893L;
	protected int levelPawn;
	protected String namePawn;
	protected int team = 0;
	protected int value, nbPawns = 40, nb;
	public int posX;
	public int posY;
	protected String URI;
	private boolean show = false,know = false,
			moved = false, selected = false;
	protected Vector<int[]> place = new Vector<int[]>();

	/**
	 * Returns a string representation of a 'APawn' object.
	 */
	public String toString() {
		if (levelPawn == -6) {
			return String.valueOf(team) + "." + String.valueOf(levelPawn);
		}
		if (levelPawn < 10) {
			return String.valueOf(team) + "." + String.valueOf(levelPawn) + " ";
		}
		return String.valueOf(team) + "." + String.valueOf(levelPawn);
	}

	/**
	 * Sets the position on the grid of the pawn.
	 * 
	 * @param posX
	 *            The abscissa of the object.
	 * 
	 * @param posY
	 *            The ordinate of the object.
	 */
	public void setPos(int posX, int posY) {
		this.posY = posY;
		this.posX = posX;
		int[] pla = { posX, posY };
		place.addElement(pla);
	}

	/**
	 * Sets the value of the pawn.
	 * 
	 * @param valuePawn
	 *            The value of the pawn.
	 */
	protected void setValue(int valuePawn) {
		this.value = valuePawn;
	}

	/**
	 * Sets the URI of the image of the pawn.
	 * 
	 * @param URI
	 *            The URI of the image of the pawn.
	 */
	protected void setURI(String URI) {
		this.URI = URI;
	}

	/**
	 * Sets the level of the pawn.
	 * 
	 * @param levelPawn
	 *            The level of the pawn, must be between 0 and 12.
	 */
	protected void setLevelPawn(int levelPawn) {
		this.levelPawn = levelPawn;
	}

	/**
	 * Sets the name of the pawn.
	 * 
	 * @param namePawn
	 *            The name of the pawn.
	 */
	protected void setNamePawn(String namePawn) {
		this.namePawn = namePawn;
	}

	/**
	 * Gets the name of the pawn.
	 * 
	 * @return The name of the pawn.
	 */
	public String getNamePawn() {
		return namePawn;
	}

	/**
	 * Sets the team of the pawn.
	 * 
	 * @param team
	 *            The number of the team:<br/>
	 *            0 for neutral,<br/>
	 *            1 for red,<br/>
	 *            2 for blue.
	 */
	public void setTeam(int team) {
		this.team = team;
	}

	/**
	 * Gets the number of pawns in the game.
	 * 
	 * @return The number of pawns.
	 */
	public int getNbPawn() {
		return nb;
	}

	/**
	 * Sets the number of pawns.
	 * 
	 * @param nb
	 *            The number of pawns in the game.
	 */
	public void setNbPawn(int nb) {
		this.nb = nb;
	}

	/**
	 * Makes a vector of the 40 pawn in a team at the begin of the game.
	 * 
	 * @param toInit
	 * 
	 * 
	 * @param team
	 * 
	 * 
	 * @param nbPawns
	 * 
	 * @return The vector of 40 or 10 pawns.
	 */
	public static Vector<APawn> createTeam(int toInit, int team, int nbPawns) {
		Vector<APawn> listPawn = new Vector<APawn>();

		if (toInit != 1) {
			if (nbPawns == 40) {

				for (int i = 0; i < 6; i++) {
					listPawn.add(new Bomb(team));
				}
				listPawn.add(new Spy(team));
				for (int i = 0; i < 8; i++) {
					listPawn.add(new Scout(team));
				}

				for (int i = 0; i < 5; i++) {
					listPawn.add(new Miner(team));
				}

				for (int i = 0; i < 4; i++) {
					listPawn.add(new Sergeant(team));
				}

				for (int i = 0; i < 4; i++) {
					listPawn.add(new Captain(team));
				}

				for (int i = 0; i < 4; i++) {
					listPawn.add(new Lieutenant(team));
				}

				for (int i = 0; i < 3; i++) {
					listPawn.add(new Major(team));
				}

				for (int i = 0; i < 2; i++) {
					listPawn.add(new Colonel(team));
				}
			} else if (nbPawns == 10) {

				for (int i = 0; i < 2; i++) {
					listPawn.add(new Bomb(team));
				}
				listPawn.add(new Spy(team));
				for (int i = 0; i < 2; i++) {
					listPawn.add(new Scout(team));
				}

				for (int i = 0; i < 2; i++) {
					listPawn.add(new Miner(team));
				}

			}
			listPawn.add(new General(team));
			listPawn.add(new Marshal(team));

			listPawn.add(new Flag(team));
		}

		return listPawn;
	}

	/**
	 * Gets the level of the pawn.
	 * 
	 * @return The level of the pawn.
	 */
	public int getLevel() {
		return levelPawn;
	}

	/**
	 * Gets the value of the pawn to create a starter grid for AI.
	 * 
	 * @return The value of the pawn.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the team of the pawn.
	 * 
	 * @return The number of the team.
	 */
	public int getTeam() {
		return this.team;
	}

	/**
	 * Gets the URI of the pawn.
	 * 
	 * @return The URI of the pawn.
	 */
	public String getURI() {
		String ur = "/image/";
		if(team==0 && levelPawn==0 && !(this instanceof Lake)){
			ur+="no_pawn/no_";
		}
		else if (team == 1){
			ur+="red/";
		}
		else if (team==2){
			ur+="blue/";
		}
		ur= ur+this.namePawn+".png";
		return ur;
	}

	/**
	 * Gets the show value of the pawn.
	 * 
	 * @return The show value of the pawn.
	 */
	public boolean getShow() {
		return this.show;
	}

	/**
	 * Sets the show value of the pawn.
	 */
	public void setShow(boolean show) {
		this.show = show;
	}

	/**
	 * Tests if a move is possible.
	 * 
	 * @param grid
	 *            The grid of the game.
	 * 
	 * @param x
	 *            The abscissa of the object.
	 * 
	 * @param y
	 *            The ordinate of the object.
	 * 
	 * @return true if the move is possible, false otherwise.
	 */
	public boolean movePoss(Game grid, int x, int y) {

		if (x < 0 || y < 0 || x > grid.getLine() || y > grid.getRow()) {
			return false;
		}
		APawn target = grid.getPawn(x, y);
		if (place.size() >= 6) {
			int[] tab1 = place.get(place.size() - 4);
			int[] tab2 = place.get(place.size() - 6);
			int[] tab3 = place.get(place.size() - 2);

			if (tab1[0] == x && x == tab2[0] && tab1[1] == y && tab2[1] == y
					&& tab3[0] == x && tab3[1] == y) {
				return false;
			}

		}
		if (target instanceof Lake) { // test if the target isn't a lake
			return false;
		}
		if (target instanceof APawn) { // test if the target isn't a pawn of the
										// same team
			if (target.getTeam() == this.team) {
				return false;
			}
		}

		if (Math.abs(x - this.posX) > 1) { // test if the move is
											// not too long
			return false;
		}
		if (Math.abs(y - this.posY) > 1) { // test if the move is
											// not too long
			return false;
		}
		if (x - this.posX != 0 && y - this.posY != 0) { // test if the move is
														// in the same lane
			return false;
		}
		if (y - this.posY == 0 && x - this.posX == 0) { // test if the pawn
														// isn't already on the
														// target
			return false;
		}
		return true;
	}

	/**
	 * Returns the result of the fight between this pawn and the target.
	 * 
	 * @param currentPawn
	 *            The pawn who is targeted by this pawn.
	 * 
	 * @return 0 if it's a drawn <br/>
	 *         1 if this pawn win <br/>
	 *         2 if this pawn loose.
	 */
	public int attack(APawn currentPawn) {
		if (currentPawn.getLevel() == this.levelPawn) {
			return 0;
		}
		if (currentPawn.getLevel() < this.levelPawn) {
			this.setKnow();
			return 1;
		}
		currentPawn.setKnow();
		return 2;
	}

	/**
	 * Moves the pawn. <br/>
	 * ! warning: be careful to test if the moving is possible BEFORE with
	 * movePoss(Grid grid, int x, int y).
	 * 
	 * @param grid
	 *            The grid of the game.
	 * 
	 * @param x
	 *            The abscissa of the object.
	 * 
	 * @param y
	 *            The ordinate of the object.
	 * 
	 * @return The grid after the move.
	 */
	public Game move(Game grid, int x, int y) {
		int[] move = { this.posX, this.posY, -1 };
		moved = true;
		if (this.posX > x) {
			move[2] = 0;
		}
		if (this.posX < x) {
			move[2] = 2;
		}
		if (this.posY > y) {
			move[2] = 3;
		}
		if (this.posY < y) {
			move[2] = 1;
		}
		grid.setLastMove(move);

		APawn tar = grid.getPawn(x, y);
		if (tar == null) {// no pawn on the coordinates targeted
			grid.set(this.posX, this.posY, null);// delete the old coordinates
													// of the pawn
			grid.set(x, y, this);// set the new coordinates of the pawn
			grid.resetMove(this);
			return grid;
		}

		int res = this.attack(tar);// get the result of the fight
		if (res == 0) {// it's a draw
			grid.set(x, y, null);// delete the pawn targeted
			grid.set(this.posX, this.posY, null);// delete the pawn who attack
		} else if (res == 1) {// the pawn who attack win
			grid.set(this.posX, this.posY, null);// delete the old coordinates
													// of the pawn
			grid.set(x, y, this);// set the new coordinates of the pawn
		} else if (res == 2) {// the pawn who attack loose
			grid.set(this.posX, this.posY, null);// delete the pawn who attack
		}
		grid.resetMove(this);
		return grid;
	}

	/**
	 * Focus a pawn and checks if move is available for him, stores the result
	 * into the array 'arrow'.
	 * 
	 * @param grid
	 *            The grid of the game.
	 */
	public int[] focus(Game grid) {
		int[] arrow = { -1, -1, -1, -1, posX, posY };
		if (posX != grid.getLine()) {// check down move
			if (this.movePoss(grid, posX + 1, posY)) {
				arrow[2] = 1;
			} else {
				arrow[2] = -1;
			}
		} else {
			arrow[2] = -1;

		}
		if (posY != grid.getRow()) {// check right move
			if (this.movePoss(grid, posX, posY + 1)) {
				arrow[1] = 1;
			} else {
				arrow[1] = -1;
			}
		} else {
			arrow[1] = -1;
		}
		if (posX != 0) {// check up move
			if (this.movePoss(grid, posX - 1, posY)) {
				arrow[0] = 1;
			} else {
				arrow[0] = -1;
			}
		} else {
			arrow[0] = -1;
		}
		if (posY != 0) {// check left move
			if (this.movePoss(grid, posX, posY - 1)) {
				arrow[3] = 1;
			} else {
				arrow[3] = -1;
			}
		} else {
			arrow[3] = -1;
		}
		for (int i = 0; i < arrow.length; i++) {
		}
		return arrow;
	}

	public boolean equals(Object obj) {
		if (obj instanceof APawn) {
			if (((APawn) obj).getLevel() == this.getLevel()) {
				return true;
			}
		}
		return false;
	}

	public void resetMove() {
		place.removeAllElements();
	}

	public boolean getMoved() {
		return moved;
	}

	public boolean getKnow() {
		return know;
	}

	public void setKnow() {
		know = true;
	}

	public boolean getSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}