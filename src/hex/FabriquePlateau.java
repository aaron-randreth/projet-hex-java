package hex;


import ihm.IFabriqueInterface;
import ihm.IPlateau;


public class FabriquePlateau implements IFabriqueInterface {
	public static IPlateau recreation(int taille, String pos) {
		return new Plateau(taille,pos);
	}
	
	public static IPlateau creer(int taille) {
		return new Plateau(taille);
	}
}
