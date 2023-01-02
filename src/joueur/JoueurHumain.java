package joueur;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */
import ihm.IJoueur;
import ihm.IPlateau;

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