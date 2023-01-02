package joueur;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */
import ihm.IFabriqueJoueur;
import ihm.IJoueur;

public class FabriqueJoueur implements IFabriqueJoueur {
	
	public IJoueur creer(String nom) {
		return new JoueurHumain(nom);
	}
	
	public IJoueur creer() {
		return new JoueurOrdi();
	}

}
