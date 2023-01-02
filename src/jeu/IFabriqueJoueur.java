package jeu;

/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

public interface IFabriqueJoueur {
	
	/**
	* @brief Crée un joueur humain
	* @param pseudo
	* @return Un joueur humain
	 */
	IJoueur creer(String pseudo);
	
	/**
	 * @brief Crée un joueur ordinateur
	 * @return Un joueur Robot
	 */
	IJoueur creer();
		
}