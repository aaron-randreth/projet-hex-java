package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hex.Pion;

class PionTest {

	@Test
	void test() {
		assertEquals("X",Pion.J1.toString());
		assertEquals("O",Pion.J2.toString());
		assertEquals(".",Pion.Vide.toString());
		
		assertTrue(Pion.J1 == Pion.get('X'));
		assertTrue(Pion.J2 == Pion.get('O'));
		assertTrue(Pion.Vide == Pion.get('.'));
		
		assertThrows(IllegalArgumentException.class,
				() -> {
					Pion.get('*');
				}
				);
	}

}
