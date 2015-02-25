package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pawn.APawn;
import pawn.Lake;

/**
 * Grid is the class that creates a "grid" object.
 * 
 * @author CAREDDA Giuliano, DUCOBU Alexandre
 */
public class Grid implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8927958880942845647L;
	private APawn[][] grid;
	private int row, line;
	public int view=0;

	/**
	 * Constructor of the grid.
	 * 
	 * @param size
	 *            Size of the grid.
	 */
	public Grid(int size) {
		row = size;
		line = size;
		grid = new APawn[line][row];
		APawn lake = new Lake();
		grid[4][2] = lake;
		grid[4][3] = lake;
		grid[5][2] = lake;
		grid[5][3] = lake;
		grid[4][6] = lake;
		grid[4][7] = lake;
		grid[5][6] = lake;
		grid[5][7] = lake;
	}

	/**
	 * Put the pawn on the grid.
	 * 
	 * @param i
	 *            Line of the grid.
	 * 
	 * @param j
	 *            Column of the grid.
	 * 
	 * @param x
	 *            The pawn to put.
	 */
	public void set(int i, int j, APawn x) {
		grid[i][j] = x;
		if (x instanceof APawn) {
			x.setPos(i, j);
		}
	}

	/**
	 * Displays the grid.
	 */
	public void showGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == null) {
					System.out.print("n     ");
				} else {
					System.out.print(grid[i][j] + "    ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// /**
	// * Check if the pawn can move.
	// *
	// * @param i
	// * Line of the pawn.
	// *
	// * @param j
	// * Column of the pawn.
	// *
	// * @return
	// * A boolean: true or false.
	// */
	// public boolean pawnCanMove(int i, int j){
	// APawn pawn = grid[i][j];
	// if (pawn!= null){
	// if (pawn.canMove()){
	// if (i!=0){
	// if (grid[i-1][j]==null){
	// return true;
	// }
	// }
	// }
	// }
	// return false;
	// }

	/**
	 * Gets the pawn at the given coordinates.
	 * 
	 * @param i
	 *            Line of the pawn.
	 * 
	 * @param j
	 *            Column of the pawn.
	 * 
	 * @return Pawn at the given coordinates.
	 */
	public APawn get(int i, int j) {
		return grid[i][j];
	}
	/**
	 * Places the teams on each side of the grid.
	 * 
	 * @param tgrid
	 *    The grid of pawn to place in the grid.
	 * 
	 * @param side
	 *    The side of the grid: 1 in the bottom of the grid
	 *    and 2 on the top of the grid.
	 */
	public void placeTeam(APawn[][] tgrid, int side) {
		if (side == 1) {
			for (int i = 0; i < tgrid.length; i++) {
				for (int j = 0; j < tgrid[0].length; j++) {
					// grid[6+i][0+j]=tgrid[i][j];
					this.set(6 + i, 0 + j, tgrid[i][j]);}}}
			if (side==2){
				for (int i=0; i < tgrid.length; i++){
						for (int j=0; j < tgrid[0].length; j++){
//								grid[3-i][9-j]=tgrid[i][j];
//								tgrid[i][j].setPos(3-i, 9-j);
								this.set( 3-i, 9-j, tgrid[i][j]);
				}
			}
		}
		}

	/**
	 * Accessor of the row.
	 * 
	 * @return
	 *    The forward row.
	 */
	public int getRow(){
		return row-1;
	}

	public int getLine() {
		return line - 1;
	}

	public void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("grid.save"));
			out.writeObject(this);
			out.close();
		} catch (IOException e) {

		}
	}

	public static Grid load() {
		try {
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream("grid.save"));
	        Grid grid = (Grid)in.readObject();
	        in.close();
	        return grid;
	} catch( ClassNotFoundException e1 ) {
	 
	} catch( IOException e2 ) {
	 
	} 
		return null;
	
	}
	public int getView(){
		return view;
	}
	public void setView(int vie){
		this.view=vie;
	}
}
