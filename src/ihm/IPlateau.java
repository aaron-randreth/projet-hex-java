package ihm;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */
public interface IPlateau {
	
	/**
	 * @brief Permet de jouer un coup sur le plateau
	 * @param coord les coordonn�es du coup 
	 */
	void jouer(String coord);

	/**
	 * @brief V�rifie si un coup est valide 
	 * @param coord les coordonn�es du coup 
	 * @return true ou false
	 */
	boolean estValide(String coord);

	/**
	 * @brief Permet de selectionner le Pion dans une case
	 * @param coord les coordonn�es du pion
	 * @return le pion
	 */
	Pion getCase(String coord);

	/**
	 * @brief Permet de r�cup�rer le nombre de pion sur le plateau
	 * @param pion
	 * @return le nombre de pion
	 */
	int getNb(Pion pion);

	/**
	 * @brief Renvoie la taille du plateau
	 * @return la taille du plateau
	 */
	int taille();
	
	/**
	 * @brief V�rifie si un coup est placable
	 * @param coord les coordonn�es d'un coup
	 * @return true ou false
	 */
	boolean estPlacable(String coord);
	
	/**
	 * @brief V�rifie si un joueur � gagn�
	 * @param p le joueur qui poss�de le pion 
	 * @return true ou false
	 */
	boolean aGagne(Pion p);

	/**
	 * @brief V�rifie si il est toujours possible de faire un nouveau coup
	 * @return true ou false
	 */
	boolean peutJouer();

}