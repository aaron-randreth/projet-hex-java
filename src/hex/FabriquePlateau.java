package hex;


import ihm.IPlateau;


public class FabriquePlateau implements IFabriquePlateau {
	public static IPlateau recreation(int taille, String pos) {
		return new Plateau(taille,pos);
	}
	
	public static IPlateau creer(int taille) {
		return new Plateau(taille);
	}
}
