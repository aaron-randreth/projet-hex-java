package hex;


import ihm.IPlateau;


public class FabriquePlateau {
	public static IPlateau creer(int taille, String pos) {
		return pos != null ? new Plateau(taille) : new Plateau(taille,pos);
	}
}
