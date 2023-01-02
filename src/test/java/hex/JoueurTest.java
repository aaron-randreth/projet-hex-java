package test.java.hex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hex.Plateau;
import ihm.IJoueur;
import ihm.IPlateau;
import ihm.Pion;
import joueur.JoueurHumain;
import joueur.JoueurOrdi;



public class JoueurTest {
	
	@Test
	public void test() {
		IJoueur h0 = new JoueurHumain("Bob");
		IJoueur h1 = new JoueurHumain("Alice");
		IJoueur r0 = new JoueurOrdi();
		IJoueur r1 = new JoueurOrdi();
		IPlateau p0 = new Plateau(26);
		IPlateau p1 = new Plateau(26);
	
		assertEquals(h0.getNom(),"Bob");
		assertEquals(h1.getNom(),"Alice");
		assertEquals(r0.getNom(),"R0");
		assertEquals(r1.getNom(),"R1");

		r0.jouer("",p0);
		r1.jouer("", p0);
		assertEquals(p0.getNb(Pion.Vide),(p0.taille()*p0.taille())-2);
		
		h0.jouer("W4", p1);
		h1.jouer("B13", p1);
		assertEquals(p1.getCase("W4"),Pion.J1);
		assertEquals(p1.getCase("B13"),Pion.J2);
		assertEquals(p1.getNb(Pion.Vide),(p1.taille()*p1.taille())-2);

	}

}
