package pawn;

/**
 * Spy is the class that creates a "spy" pawn.
 *
 * @author CAREDDA Giuliano, DUCOBU Alexandre
 */
public class Spy extends APawn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main constructor of the spy pawn.
	 */
	public Spy() {
		setLevelPawn(1);
		setNbPawn(1);
		setNamePawn("spy");
		setValue(200);
	}

	/**
	 * Constructor of the spy pawn.
	 * 
	 * @param team
	 *            Team of the pawn, must be 1 or 2.
	 */
	public Spy(int team) {
		setLevelPawn(1);
		setNbPawn(1);
		setNamePawn("spy");
		setTeam(team);
		setValue(200);
		if (team == 1)
			setURI("/image/red/spy.png");

		if (team == 2)
			setURI("/image/blue/spy.png");
	}

	/**
	 * Returns the result of the fight between this pawn and the target.
	 * 
	 * @param tar
	 *            The pawn who is targeted by this pawn.
	 * 
	 * @return 0 if it's a drawn <br/>
	 *         1 if this pawn win <br/>
	 *         2 if this pawn loose.
	 */
	public int attack(APawn tar) {
		if (tar instanceof Marshal) {
			return 1;
		}
		if (tar.getLevel() == this.levelPawn) {
			return 0;
		}
		if (tar.getLevel() < this.levelPawn) {
			return 1;
		}
		if (tar.getLevel() > this.levelPawn) {
			return 2;
		}
		return -1;
	}
}
