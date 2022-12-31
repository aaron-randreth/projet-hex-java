package joueur;

import ihm.IJoueur;

public class FabriqueJoueur {
	public static IJoueur creer(String nom) {
		return new JoueurHumain(nom);
	}
	
	public static IJoueur creer() {
		return new JoueurOrdi();
	}

}
