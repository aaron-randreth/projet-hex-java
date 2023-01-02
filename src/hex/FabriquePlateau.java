package hex;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

import jeu.IFabriquePlateau;
import jeu.IPlateau;


public class FabriquePlateau implements IFabriquePlateau {
	
	
	public IPlateau recreation(int taille, String pos) {
		return new Plateau(taille,pos);
	}
	

	public IPlateau creer(int taille) {
		return new Plateau(taille);
	}
}
