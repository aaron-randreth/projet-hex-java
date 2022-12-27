package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hex.Pion;
import hex.Plateau;
import ihm.IPlateau;

class PlateauTest {
	private String pos1 = ".X..XOXXOO.OX..."; 
	private String[] lignes1_rep = {
			".X..", 
			"XOXX",
			"OO.O",
			"X..."
	};
	private String display1_rep = 
			" A B C D\n" + 
			"1 . X . .\n" + 
			"2  X O X X\n" + 
			"3   O O . O\n" + 
			"4    X . . .\n";


	
	void test() {
		final int taille = 4;
		IPlateau p = new Plateau(taille);
		assertEquals(taille, p.taille());
		assertEquals(
				" A B C D\n" + 
				"1 . . . .\n" + 
				"2  . . . .\n" + 
				"3   . . . .\n" + 
				"4    . . . .\n", p.toString());
		
	
		// jouer un coup en B2
		p.jouer("B2");
		assertEquals(Pion.J1, p.getCase("B2"));
		System.out.println(p);
		
		p.jouer("C2");
		System.out.println(p);
	}
	
	public void testerPositions() {
		testerPosition(pos1, lignes1_rep, display1_rep);
	}

	private void testerPosition(String pos, String[] lignes_rep, String display_rep) {
		String[] lignes;
		lignes  = Plateau.decouper(pos);
		int taille = Plateau.getTaille(pos);
		
		for (int i = 0; i< taille; ++i)
			assertEquals(lignes_rep[i], lignes[i]);
		
		IPlateau p = new Plateau(taille, pos);
		assertEquals(display_rep, p.toString());
	}
	
	private String vert_win1_in = ".X.XOOXX.OOOX...";
	private String vert_win1_tostr =
			" A B C D\n" + 
			"1 . X . X\n" + 
			"2  O O X X\n" + 
			"3   . O O O\n" + 
			"4    X . . .\n";
	
	private String vert_win2_tostr =
			" A B C D\n" + 
			"1 X . . X\n" + 
			"2  O O X .\n" + 
			"3   X O X O\n" + 
			"4    X O O .\n";
	private String vert_win2_in = vert_win2_tostr.replaceAll("[1-9A-D \n]", "") ;
	
	@Test
	public void testWins() {
		IPlateau p1 = new Plateau(4, vert_win1_in);
		assertEquals(p1.toString(), vert_win1_tostr);
		System.out.println(p1);
		assertFalse(p1.aGagne(Pion.J1));
		assertTrue(p1.aGagne(Pion.J2));
		
		IPlateau p2 = new Plateau(4, pos1);
		assertFalse(p2.aGagne(Pion.J1));
		assertFalse(p2.aGagne(Pion.J2));
		
		IPlateau p3 = new Plateau(4, vert_win2_in);
		System.out.println(p3);
		assert(p3.aGagne(Pion.J1));
		assertFalse(p3.aGagne(Pion.J2));

	}

}
