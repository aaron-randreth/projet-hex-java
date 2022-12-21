package joueur;

import ihm.IJoueur;

public class FabriqueJoueur {
	public static IJoueur creer(String type, String... nom) {
		switch(type) {
		case "humain":
			return new JoueurHumain(nom[0]);
		case "ordinateur":
			return new JoueurOrdi();
		default:
			throw new IllegalArgumentException(
					"Type inconnu " + type);
		}
	}

}
