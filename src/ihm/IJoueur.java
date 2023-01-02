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
	 * @brief Permet au joueur de jouer sur un plateau à partir de coordonnée
	 * @param coord les coordonnées
	 * @param p le plateau
	 */
	void jouer(String coord,IPlateau p);
	
	/**
	 * @brief Renvoie si le joueur peut passer des coordonnées
	 * @return true ou false
	 */
	boolean needs_input();
}
