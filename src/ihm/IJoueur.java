package ihm;

/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

public interface IJoueur {
	
	/**
	 * @brief renvoie le nom du joueur
	 * @return le nom du joueur
	 */
	String getNom();
	
	/**
	 * @brief Permet au joueur de jouer sur un plateau � partir de coordonn�e
	 * @param coord les coordonn�es
	 * @param p le plateau
	 */
	void jouer(String coord,IPlateau p);
	
	/**
	 * @brief Renvoie si le joueur peut passer des coordonn�es
	 * @return true ou false
	 */
	boolean needs_input();
}
