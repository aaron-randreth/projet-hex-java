package ihm;

import hex.Pion;

public interface IPlateau {

	void jouer(String coord);

	boolean estValide(String coord);
	

	Pion getCase(String coord);

	int getNb(Pion pion);

	int taille();

	String toString();
	
	boolean estPlacable(String coord);

}