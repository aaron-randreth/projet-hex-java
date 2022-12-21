package ihm;

import hex.Pion;

public interface IPlateau {

	void jouer(String coord);

	boolean estValide(String coord);
	

	Pion getCase(String coord);

	int getNb(Pion pion);

	int taille();
	
	boolean estPlacable(String coord);
	
	boolean aGagne(Pion p);

	boolean peutJouer();

}