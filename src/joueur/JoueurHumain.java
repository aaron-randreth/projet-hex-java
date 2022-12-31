package joueur;

import ihm.IJoueur;
import ihm.IPlateau;

public class JoueurHumain implements IJoueur {
	private String nom;
	
	public JoueurHumain(String n) {
		this.nom=n;
	}

	@Override
	public String getNom() {
		return this.nom.toUpperCase();
	}

	@Override
	public void jouer(String coord, IPlateau p) {
		p.jouer(coord);
	}

	@Override
	public boolean needs_input() {
		return true;
	}

}