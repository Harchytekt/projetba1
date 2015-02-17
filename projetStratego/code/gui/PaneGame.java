package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.Grid;
import pawn.APawn;

public class PaneGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Image img, imgBack;
	public Grid grid;
	public int[][] arrow={{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

	public PaneGame(Grid grid) {
		this.grid = grid;
		
		
		try {
			java.net.URL urlBack = getClass().getResource("/image/back.jpg");
			// java.net.URL urlO = getClass().getResource(jeu.recupImg1());
			// java.net.URL urlX = getClass().getResource(jeu.recupImg2());
			// java.net.URL urlOOO =
			// getClass().getResource("/image/oth/OOO.png");
			// imgO = ImageIO.read(urlO);
			// imgX = ImageIO.read(urlX);
			// imgOOO = ImageIO.read(urlOOO);
			imgBack = ImageIO.read(urlBack);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {

		// dessine le quadrillage
		int nbrLigne = grid.getLine();
		int nbrCol = grid.getRow();

		g.drawImage(imgBack, 0, 0, this.getWidth(), this.getHeight(), this);

		for (int lig = 1; lig <= nbrCol - 1; lig++) {
			g.drawLine((this.getWidth() / nbrCol) * lig, 0,
					(this.getWidth() / nbrCol) * lig, this.getHeight());
		}
		for (int lig = 1; lig <= nbrLigne - 1; lig++) {
			g.drawLine(0, (this.getHeight() / nbrLigne) * lig, this.getWidth(),
					(this.getHeight() / nbrLigne) * lig);
		}

		// dessine les images des pions selon la grid
		int i;
		int j;
		for (i = 0; i <= (nbrLigne - 1); i++) {
			for (j = 0; j <= (nbrCol - 1); j++) {
				if (grid.get(i, j) != null) {
					APawn pawn = grid.get(i, j);
					String link = pawn.getURI();
					java.net.URL uri = getClass().getResource(link);
//					System.out.println(link);
//					System.out.println(uri);
					try {
						img = ImageIO.read(uri);
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(img, (j * (this.getWidth() / nbrCol)) + 10,
							(i * (this.getHeight() / nbrLigne)) + 10,this.getWidth() / nbrCol - 20, this.getHeight()/ nbrLigne - 20, this);
				}
			}
		}
		//TODO draw arrow
		int row = arrow[4][0];
		int line = arrow[4][0];
		if(arrow[0][0]!=-1){
			String linkUp = "image/up.png";
			java.net.URL uriUp = getClass().getResource(linkUp);
			try {
				img = ImageIO.read(uriUp);
			} catch (IOException e) {
				e.printStackTrace();}
			g.drawImage(img, (row * (this.getWidth() / nbrCol)) + 10,
					(line-1 * (this.getHeight() / nbrLigne)) + 10,this.getWidth() / nbrCol - 20, this.getHeight()/ nbrLigne - 20, this);
		}
		if(arrow[1][0]!=-1){
			String linkR = "image/right.png";
			java.net.URL uriR = getClass().getResource(linkR);
			try {
				img = ImageIO.read(uriR);
			} catch (IOException e) {
				e.printStackTrace();}
			g.drawImage(img, (row +1* (this.getWidth() / nbrCol)) + 10,
					(line * (this.getHeight() / nbrLigne)) + 10,this.getWidth() / nbrCol - 20, this.getHeight()/ nbrLigne - 20, this);
		}
		if(arrow[2][0]!=-1){
			String linkD = "image/down.png";
			java.net.URL uriD = getClass().getResource(linkD);
			try {
				img = ImageIO.read(uriD);
			} catch (IOException e) {
				e.printStackTrace();}
			g.drawImage(img, (row * (this.getWidth() / nbrCol)) + 10,
					(line+1 * (this.getHeight() / nbrLigne)) + 10,this.getWidth() / nbrCol - 20, this.getHeight()/ nbrLigne - 20, this);
			}
			if(arrow[2][0]!=-1){
				String linkL = "image/left.png";
				java.net.URL uriL = getClass().getResource(linkL);
				try {
					img = ImageIO.read(uriL);
				} catch (IOException e) {
					e.printStackTrace();}
				g.drawImage(img, (row * (this.getWidth() / nbrCol)) + 10,
						(line+1 * (this.getHeight() / nbrLigne)) + 10,this.getWidth() / nbrCol - 20, this.getHeight()/ nbrLigne - 20, this);
				}
		

	}
	public void recupArrow(int[][] nouvArrow){
		arrow=nouvArrow;
	}
	public void recupGrid(Grid nouvGrid) {
		grid = nouvGrid;
	}

//  public void recupGame(AGame type){
//  	jeu = type;
//  }
}