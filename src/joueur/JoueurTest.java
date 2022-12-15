package joueur;

import org.junit.Test;

import ihm.IJoueur;
import ihm.IPlateau;



public class JoueurTest {
	
	@Test
	public void test() {
		IJoueur h = new JoueurHumain("Bob");
		IJoueur r = new JoueurOrdi();
		IPlateau p = new Plateau();
	
		assertEquals(h.getNom(),"Bob");
		assertEquals(r.getNom(),"r0");
		
		assertEquals
	
	}

}
