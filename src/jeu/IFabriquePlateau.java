package jeu;

/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

public interface IFabriquePlateau {

	/**
	* @brief Crée un plateau
	* @return Un nouveau plateau
	* @param taille du plateau
	 */
	IPlateau creer(int taille);
	
	/**
	 * @brief Création d'un plateau à partir d'un historique de plateau
	 * @param taille
	 * @param pos
	 * @return Un nouveau plateau
	 */
	IPlateau recreation(int taille, String pos);

}