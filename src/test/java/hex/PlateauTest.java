package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hex.Plateau;
import ihm.IPlateau;
import ihm.Pion;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

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

	@Test
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
	
	private String vert_win1_in = ".O.XOOXX.OXOOX..";
	private String vert_win1_tostr =
			" A B C D\n" + 
			"1 . O . X\n" + 
			"2  O O X X\n" + 
			"3   . O X O\n" + 
			"4    O X . .\n";
	
	private String vert_win2_tostr =
			" A B C D\n" + 
			"1 X . . X\n" + 
			"2  O O X .\n" + 
			"3   X O X O\n" + 
			"4    X O O .\n";
	private String vert_win2_in = vert_win2_tostr.replaceAll("[1-9A-D \n]", "") ;
	
	@Test
	public void testMisc() {
		IPlateau p1 = new Plateau(4, "XXXXOOOOXXXXOOOO");
		assertFalse(p1.peutJouer());
		
		assertTrue(p1.estValide("A1"));
		assertFalse(p1.estPlacable("A1"));
		
		assertTrue(p1.estValide("C2"));
		assertFalse(p1.estPlacable("C2"));
		
		assertTrue(p1.estValide("D4"));
		assertFalse(p1.estPlacable("D4"));
	}
	
	private String J1_win_ligne_v = 
			" A B C D\n" + 
			"1 . O X .\n" + 
			"2  O O X X\n" + 
			"3   O O X O\n" + 
			"4    X . X .\n";
	
	private String J1_win_ligne_v_in =".OX.OOXXOOXOX.X."; 
	
	
	private String J2_win_ligne_h = 
			" A B C D\n" + 
			"1 . X . .\n" + 
			"2  X X X X\n" + 
			"3   O O O O\n" + 
			"4    X O . .\n";
	
	private String J2_win_ligne_h_in = ".X..XXXXOOOOXO..";


	
	@Test
	public void testWins() {
		//Dans le cas ou le J1 gagne
		IPlateau p1 = new Plateau(4, vert_win1_in);
		assertEquals(p1.toString(), vert_win1_tostr);
		assertTrue(p1.aGagne(Pion.J1));
		assertFalse(p1.aGagne(Pion.J2));
		
		//Dans le cas ou personne ne gagne 
		IPlateau p2 = new Plateau(4, pos1);
		assertFalse(p2.aGagne(Pion.J1));
		assertFalse(p2.aGagne(Pion.J2));
		
		//Dans le cas ou le J2 gagne
		IPlateau p3 = new Plateau(4, vert_win2_in);
		assertFalse(p3.aGagne(Pion.J1));
		assertTrue(p3.aGagne(Pion.J2));
		
		//Dans le cas ou le J2 gagne en ligne droite
		IPlateau p4=new Plateau(4,J2_win_ligne_h_in);
		assertEquals(p4.toString(), J2_win_ligne_h);
		assertFalse(p4.aGagne(Pion.J1));
		assertTrue(p4.aGagne(Pion.J2));
		
		//Dans le cas ou le J1 gagne en ligne droite
		IPlateau p5=new Plateau(4,J1_win_ligne_v_in);
		assertEquals(p5.toString(), J1_win_ligne_v);
		assertFalse(p5.aGagne(Pion.J2));
		assertTrue(p5.aGagne(Pion.J1));

	}

}
