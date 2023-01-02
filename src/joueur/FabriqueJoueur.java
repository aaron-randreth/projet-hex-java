package joueur;
import jeu.IFabriqueJoueur;
import jeu.IJoueur;

public class FabriqueJoueur implements IFabriqueJoueur {
	
	public IJoueur creer(String nom) {
		return new JoueurHumain(nom);
	}
	
	public IJoueur creer() {
		return new JoueurOrdi();
	}

}
