package joueur;
import jeu.IJoueur;
import jeu.IPlateau;

public class JoueurHumain implements IJoueur {
	private String nom;
	
	public JoueurHumain(String n) {
		this.nom=n.substring(0,1).toUpperCase() + n.substring(1).toLowerCase();
	}

	@Override
	public String getNom() {
		return this.nom;
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