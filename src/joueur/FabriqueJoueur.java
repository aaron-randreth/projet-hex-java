package joueur;

import ihm.IJoueur;
import ihm.IFabriqueJoueur;

public class FabriqueJoueur implements IFabriqueJoueur {
	public static IJoueur creer(String nom) {
		return new JoueurHumain(nom);
	}
	
	public static IJoueur creer() {
		return new JoueurOrdi();
	}

}
