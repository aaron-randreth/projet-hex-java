package hex;


import ihm.IFabriquePlateau;
import ihm.IPlateau;


public class FabriquePlateau implements IFabriquePlateau {
	public IPlateau recreation(int taille, String pos) {
		return new Plateau(taille,pos);
	}
	
	public IPlateau creer(int taille) {
		return new Plateau(taille);
	}
}
